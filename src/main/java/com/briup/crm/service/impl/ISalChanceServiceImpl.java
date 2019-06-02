package com.briup.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.crm.common.bean.CstCustomerExample;
import com.briup.crm.common.bean.SalChance;
import com.briup.crm.common.bean.SalChanceExample;
import com.briup.crm.common.bean.SalPlan;
import com.briup.crm.common.bean.SalPlanExample;
import com.briup.crm.common.exception.CrmCommonException;
import com.briup.crm.dao.mappleInterface.SalChanceMapper;
import com.briup.crm.dao.mappleInterface.SalPlanMapper;
import com.briup.crm.service.interfaces.ISalChanceService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
@Service
public class ISalChanceServiceImpl implements ISalChanceService{

	@Autowired
	private SalChanceMapper salChanceMapper;
	@Autowired
	private SalPlanMapper salPlanMapper;
	
	public void saveChance(SalChance sc) throws CrmCommonException {
		salChanceMapper.insert(sc);
	}

	public PageInfo<SalChance> findSalChanceByPage(int curpage, int row) throws CrmCommonException {
		PageHelper.startPage(curpage, row);
		List<SalChance> list = salChanceMapper.selectByExample(new SalChanceExample());
		return new PageInfo<SalChance>(list);
	}

	public List<SalPlan> findAllSalPlan() throws CrmCommonException {
		return salPlanMapper.selectByExample(new SalPlanExample());
	}

	public List<SalPlan> findSalPlans(long chcId) throws CrmCommonException {
		// TODO Auto-generated method stub
		return null;
	}

	public PageInfo<SalPlan> findSalPlanByPage(int curpage, int row) throws CrmCommonException {
		PageHelper.startPage(curpage, row);
		return new PageInfo<SalPlan>(findAllSalPlan());
	}

	public void updateChance(SalChance sc) throws CrmCommonException {
		salChanceMapper.updateByPrimaryKey(sc);
	}

	public SalChance findOneSalChance(long chcId) throws CrmCommonException {
		return salChanceMapper.selectByPrimaryKey(chcId);
	}

	public SalPlan findSalPlan(long chcId) throws CrmCommonException {
		SalPlan plan = salPlanMapper.selectByPrimaryKey(chcId);
		return plan;
	}

	public void deleteSalChance(long chcId) throws CrmCommonException {
		salChanceMapper.deleteByPrimaryKey(chcId);
	}

	public void savePlan(SalPlan sp) throws CrmCommonException {
		salPlanMapper.insert(sp);
	}

	public void deleteSalPlan(long plaId) throws CrmCommonException {
		salPlanMapper.deleteByPrimaryKey(plaId);
	}

	public void updateSalPlan(SalPlan sp) throws CrmCommonException {
		salPlanMapper.updateByPrimaryKey(sp);
	}

	public PageInfo<SalChance> findSalPageByCon(int curpage, int row, SalChance sc) throws CrmCommonException {
		PageHelper.startPage(curpage, row);
		SalChanceExample example = new SalChanceExample();
		example.createCriteria().andChcCustNameLike("%"+sc.getChcCustName()+"%").andChcTitleLike("%"+sc.getChcTitle()+"%").
		andChcLinkmanLike("%"+sc.getChcLinkman()+"%");
		
		return new PageInfo<SalChance>(salChanceMapper.selectByExample(example));
	}

	public PageInfo<SalPlan> findSalPlansMore(int curpage, int row,SalPlan salPlan) throws CrmCommonException {
		PageHelper.startPage(curpage, row);
		SalPlanExample example = new SalPlanExample();
		example.createCriteria().andPlaIdLike("%"+salPlan.getPlaId()+"%").andPlaTodoLike("%"+salPlan.getPlaTodo()+"%").
		andPlaResultLike("%"+salPlan.getPlaResult()+"%");
		return  new PageInfo<SalPlan>(salPlanMapper.selectByExample(example));
	}

}
