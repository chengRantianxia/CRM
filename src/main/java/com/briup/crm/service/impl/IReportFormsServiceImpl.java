package com.briup.crm.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Set;

import org.apache.commons.lang3.StringUtils;
import org.jfree.data.category.CategoryDataset;
import org.jfree.data.category.DefaultCategoryDataset;
import org.omg.CORBA.PRIVATE_MEMBER;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.crm.common.bean.CstCustomer;
import com.briup.crm.common.bean.CstCustomerExample;
import com.briup.crm.common.bean.Orders;
import com.briup.crm.common.bean.OrdersExample;
import com.briup.crm.common.bean.OrdersLine;
import com.briup.crm.common.bean.OrdersLineExample;
import com.briup.crm.common.exception.CrmCommonException;
import com.briup.crm.dao.mappleInterface.CstCustomerMapper;
import com.briup.crm.dao.mappleInterface.OrdersLineMapper;
import com.briup.crm.dao.mappleInterface.OrdersMapper;
import com.briup.crm.service.interfaces.ICustomerService;
import com.briup.crm.service.interfaces.IReportFormsService;
@Service
public class IReportFormsServiceImpl implements IReportFormsService{

	@Autowired 
	private OrdersMapper ordersMapper;
	@Autowired 
	private OrdersLineMapper ordersLineMapper;
	@Autowired 
	private CstCustomerMapper cstCustomerMapper;
	@Autowired
	private ICustomerService iCustomerService;
	//客户贡献分析
	public CategoryDataset findCstcontribute(String cust_name) throws Exception {
		//查出所有的客户，我们要根据客户的ID来判断是否下了订单
		//再根据订单的编号，去查询他的订单列表，获得总金额
		if(StringUtils.isBlank(cust_name)){
		CstCustomerExample example = new CstCustomerExample();
		List<CstCustomer> list = cstCustomerMapper.selectByExample(example);
		//这个数据集应用在柱状图中
		DefaultCategoryDataset dataset = new DefaultCategoryDataset();
		for(CstCustomer cstCustomer:list){
		dataset.addValue(orderMoney(cstCustomer.getCustName()), "客户贡献分析", cstCustomer.getCustName());
		}
		return dataset;
		}
		else{
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			dataset.addValue(orderMoney(cust_name), "客户贡献分析", cust_name);
			return dataset;
		}
	}

	public CategoryDataset findCstMakeup(int condit) throws CrmCommonException {
		double sum=iCustomerService.findAllCustomer().size();
		if(condit==0){
			Set<String> levelLableset = iCustomerService.findAllCustomerLevelLable();
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			double perscent = 0;
			for(String string:levelLableset){
				double size = iCustomerService.findCstCustomerByLevelLable(string).size();
				perscent=(double)size/sum;
				dataset.addValue(perscent, "客户构成按等级分析",string);
			}
			return dataset;
		}
		if(condit==1){
			Set<Integer> creditset = iCustomerService.findAllCustomerCredit();
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			double perscent = 0;
			for(Integer integer:creditset){
				int size = iCustomerService.findCstCustomerByCredit(integer).size();
				perscent=size/sum;
				dataset.addValue(perscent, "客户构成按信誉度分析",integer);
			}
			return dataset;
		}
		if(condit==2){
			Set<Integer> satisfyset = iCustomerService.findAllCustomerSatisfy();
			DefaultCategoryDataset dataset = new DefaultCategoryDataset();
			double perscent = 0;
			for(Integer integer:satisfyset){
				int size = iCustomerService.findCstCustomerBySatisfy(integer).size();
				perscent=size/sum;
				dataset.addValue(perscent, "客户构成按满意度分析",integer);
				}
			return dataset;
		}
		return null;
	}
	//这个方法是查询订单的总金额
	public double orderMoney(String custname){
		//查询客户表中的custname客户
		CstCustomerExample cstexample = new CstCustomerExample();
		cstexample.createCriteria().andCustNameEqualTo(custname);
		CstCustomer cst = (cstCustomerMapper.selectByExample(cstexample)).get(0);
		//根据客户ID去找订单ID
		OrdersExample ordersexample=new OrdersExample();
		ordersexample.createCriteria().andOdrCustIdEqualTo(cst.getCustId());
		//一个客户可以有多个订单
		List<Orders> orderlist = ordersMapper.selectByExample(ordersexample);
		//根据订单ID去找订单列表，拿到总金额
		OrdersLineExample lineExample = new OrdersLineExample();
		double sum=0;
		for(Orders order:orderlist){
			//循环遍历得到订单项的金额总和
			lineExample.createCriteria().andOddOrderIdEqualTo(new BigDecimal(order.getOdrId()));
			List<OrdersLine> line = ordersLineMapper.selectByExample(lineExample);
			for(OrdersLine ordersLine:line){
				sum+=ordersLine.getOddPrice();
			}
		}
		return sum;
	}
}
