package com.briup.crm.service.impl;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.LongConsumer;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.crm.common.bean.CstActivity;
import com.briup.crm.common.bean.CstActivityExample;
import com.briup.crm.common.bean.CstCustomer;
import com.briup.crm.common.bean.CstCustomerExample;
import com.briup.crm.common.bean.CstLinkman;
import com.briup.crm.common.bean.CstLinkmanExample;
import com.briup.crm.common.exception.CrmCommonException;
import com.briup.crm.dao.mappleInterface.CstActivityMapper;
import com.briup.crm.dao.mappleInterface.CstCustomerMapper;
import com.briup.crm.dao.mappleInterface.CstLinkmanMapper;
import com.briup.crm.service.interfaces.ICustomerService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ICustomerServiceImpl implements ICustomerService{
	
	@Autowired
	private CstCustomerMapper cstCustomerMapper;//客户映射
	@Autowired
	private CstLinkmanMapper cstLinkmanMapper ;
	@Autowired
	private CstActivityMapper cstActivityMapper  ;
	
	public List<CstCustomer> findAllCustomer() throws CrmCommonException {
		/*//新建一个example对象
		CstCustomerExample example = new CstCustomerExample();
		//根据example查询(限定条件可加
		List<CstCustomer> list = cstCustomerMapper.selectByExample(example);*/
		return cstCustomerMapper.selectByExample(new CstCustomerExample());
	}

	public CstCustomer findCustomerById(long id) throws CrmCommonException {
		/*CstCustomerExample example = new CstCustomerExample();
		example.createCriteria().andCustIdEqualTo(id);
		List<CstCustomer> list = cstCustomerMapper.selectByExample(example);
		if(list.size()==0){
			throw CrmCommonException.getException(404);
		}*/
		return cstCustomerMapper.selectByPrimaryKey(id);
	}

	public CstCustomer findCustomerByCstName(String cstName) throws CrmCommonException {
		CstCustomerExample example = new CstCustomerExample();
		example.createCriteria().andCustNameEqualTo(cstName);
		List<CstCustomer> list = cstCustomerMapper.selectByExample(example);
		if(list.size()==0){
			throw CrmCommonException.getException(404);
		}
		return list.get(0);
	}

	public Set<String> findAllCustomerLevelLable() throws CrmCommonException {
		CstCustomerExample example = new CstCustomerExample();
		example.createCriteria().andCustLevelLabelIsNotNull();
		List<CstCustomer> list = cstCustomerMapper.selectByExample(example);
		Set<String> set =new HashSet<String>();
		for(CstCustomer cstCustomer:list){
			set.add(cstCustomer.getCustLevelLabel());
		}
		if(list.size()==0){
			throw CrmCommonException.getException(404);
		}
		return set;
	}

	public List<CstCustomer> findCstCustomerByLevelLable(String levelLable) throws CrmCommonException {
		CstCustomerExample example = new CstCustomerExample();
		example.createCriteria().andCustLevelLabelEqualTo(levelLable);
		List<CstCustomer> list = cstCustomerMapper.selectByExample(example);
		if(list.size()==0){
			throw CrmCommonException.getException(404);
		}
		return list;
	}

	public Set<Integer> findAllCustomerCredit() throws CrmCommonException {
		CstCustomerExample example = new CstCustomerExample();
		example.createCriteria().andCustCreditIsNotNull();
		List<CstCustomer> list = cstCustomerMapper.selectByExample(example);
		Set<Integer> set =new HashSet<Integer>();
		for(CstCustomer cstCustomer:list){
			set.add((cstCustomer.getCustCredit()).intValue());
		}
		if(list.size()==0){
			throw CrmCommonException.getException(404);
		}
		return set;
	}

	public List<CstCustomer> findCstCustomerByCredit(int credit) throws CrmCommonException {
		long temp=(long) credit;
		CstCustomerExample example = new CstCustomerExample();
		example.createCriteria().andCustCreditEqualTo(temp);
		List<CstCustomer> list = cstCustomerMapper.selectByExample(example);
		if(list.size()==0){
			throw CrmCommonException.getException(404);
		}
		return list;
	}

	public Set<Integer> findAllCustomerSatisfy() throws CrmCommonException {
		CstCustomerExample example = new CstCustomerExample();
		example.createCriteria().andCustSatisfyIsNotNull();
		List<CstCustomer> list = cstCustomerMapper.selectByExample(example);
		Set<Integer> set =new HashSet<Integer>();
		for(CstCustomer cstCustomer:list){
			set.add(cstCustomer.getCustSatisfy().intValue());
		}
		if(list.size()==0){
			throw CrmCommonException.getException(404);
		}
		return set;
	}

	public List<CstCustomer> findCstCustomerBySatisfy(int satisfy) throws CrmCommonException {
		CstCustomerExample example = new CstCustomerExample();
		example.createCriteria().andCustSatisfyEqualTo((long) satisfy);
		List<CstCustomer> list = cstCustomerMapper.selectByExample(example);
		return list;
	}

	public CstCustomer findCustomerByCstNo(String cstNo) throws CrmCommonException {
		// TODO Auto-generated method stub
		return null;
	}

	public void updateCustomer(CstCustomer cstCustomer) throws CrmCommonException {
		System.out.println("==========================="+cstCustomer);
		cstCustomerMapper.updateByPrimaryKey(cstCustomer);
	}

	public void saveLinkMan(CstLinkman cstLinkman) throws CrmCommonException {
		cstLinkmanMapper.insert(cstLinkman);
	}

	public void deleteLinkMan(long id) throws CrmCommonException {
		cstLinkmanMapper.deleteByPrimaryKey(id);
	}

	public PageInfo<CstCustomer> findAllCstCustomer(int curpage, int row) throws CrmCommonException {
		//设置分页信息（传入的是当前页码和每一页有多少条数据）
		PageHelper.startPage(curpage, row);
	/*	//需要找到拿需要分页的数据的list
	    List<CstCustomer> findAllCustomer = findAllCustomer();
	    //返回一个查询页码后的PageInfo对象
	    PageInfo<CstCustomer> p = new PageInfo<CstCustomer>(findAllCustomer);*/
		return new PageInfo<CstCustomer>(findAllCustomer());
	}
	//根据条件查找所有的客户 带分页
	public PageInfo<CstCustomer> findCustomerPageByCon(int curpage, int row, CstCustomer cstCustomer)
			throws CrmCommonException {
			PageHelper.startPage(curpage, row);
		//需要一个list来进行分页
		//依据条件来查询（cstcustomer）
		CstCustomerExample example = new CstCustomerExample();
		//加限定条件，拼接查询的条件
		example.createCriteria().andCustNoLike("%"+cstCustomer.getCustNo()+"%").
			andCustManagerNameLike("%"+cstCustomer.getCustManagerName()+"%").
			andCustNameLike("%"+cstCustomer.getCustName()+"%").andCustLevelLabelLike("%"+cstCustomer.getCustLevelLabel()+"%").
			andCustRegionLike("%"+cstCustomer.getCustRegion()+"%");
		return new PageInfo<CstCustomer> (cstCustomerMapper.selectByExample(example));
	}

	public List<CstLinkman> findAllLinkManByCstId(long cstId) throws CrmCommonException {
		CstLinkmanExample example = new CstLinkmanExample();
		example.createCriteria().andLkmCustIdEqualTo(cstId);
		List<CstLinkman> list = cstLinkmanMapper.selectByExample(example);	
		if(list.size()==0){
			throw CrmCommonException.getException(404);
		}
		return list;
	}

	public CstLinkman findLinkManById(long id) throws CrmCommonException {
		return cstLinkmanMapper.selectByPrimaryKey(id);
	}

	public void updateLinkman(CstLinkman lkm) throws CrmCommonException {
		cstLinkmanMapper.updateByPrimaryKey(lkm);
	}

	public void deleteCustomerById(long id) throws CrmCommonException {
		cstCustomerMapper.deleteByPrimaryKey(id);
	}

	public void saveCstActivity(CstActivity cstActivity) throws CrmCommonException {
		cstActivityMapper.insert(cstActivity);
	}

	public List<CstActivity> findAllCstActivityByCstNo(String custId) throws CrmCommonException {
		CstActivityExample example = new CstActivityExample();
		example.createCriteria().andAtvCustNoLike("%"+custId+"%");
		List<CstActivity> list = cstActivityMapper.selectByExample(example);
		if(list.size()==0){
			throw CrmCommonException.getException(404);
		}
		return list;
	}

	public CstActivity findCstActivityById(long atvId) throws CrmCommonException {
		return cstActivityMapper.selectByPrimaryKey(atvId);
	}

	public void updateAtv(CstActivity cstActivity) throws CrmCommonException {
		cstActivityMapper.updateByPrimaryKey(cstActivity);
	}

	public void deleteAtvById(long atvId) throws CrmCommonException {
		cstActivityMapper.deleteByPrimaryKey(atvId);
	}

	public void saveCustomer(CstCustomer cst) throws CrmCommonException {
		cstCustomerMapper.insert(cst);
	}

	public List<CstLinkman> findAllLinkMan() throws CrmCommonException {
		CstLinkmanExample example = new CstLinkmanExample();
		return cstLinkmanMapper.selectByExample(example);
	}

	public List<CstActivity> findCstActivity() throws CrmCommonException {
		CstActivityExample example = new CstActivityExample();
		List<CstActivity> list = cstActivityMapper.selectByExample(example);
		return list;
	}

}
