package com.briup.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.crm.common.bean.CstService;
import com.briup.crm.common.bean.CstServiceExample;
import com.briup.crm.common.bean.SysUser;
import com.briup.crm.common.exception.CrmCommonException;
import com.briup.crm.dao.mappleInterface.CstServiceMapper;
import com.briup.crm.service.interfaces.ICstService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

@Service
public class ICstServiceImpl implements ICstService{

	@Autowired
	private CstServiceMapper cstServiceMapper;
	public void save(CstService cstService) throws CrmCommonException {
		cstServiceMapper.insert(cstService);
	}

	public List<SysUser> findAllManagerName() throws CrmCommonException {
		// TODO Auto-generated method stub
		return null;
	}

	public PageInfo<CstService> findCstServiceByCondition(CstService con, int curPage, int row)
			throws CrmCommonException {
		PageHelper.startPage(curPage, row);
		CstServiceExample example = new CstServiceExample();
		example.createCriteria().andSvrCustNameLike("%"+con.getSvrCustName()+"%").andSvrTitleLike("%"+con.getSvrTitle()+"%").
		andSvrTypeLike("%"+con.getSvrType()+"%");
		return new PageInfo<CstService> (cstServiceMapper.selectByExample(example));
	}

	public PageInfo<CstService> findServiceOnDealed(CstService con, int curPage, int row) throws CrmCommonException {
		// TODO Auto-generated method stub
		return null;
	}

	public PageInfo<CstService> findAllServicenew(int curPage, int row) throws CrmCommonException {
		// TODO Auto-generated method stub
		return null;
	}

	public PageInfo<CstService> findByfp(int curPage, int row) throws CrmCommonException {
		// TODO Auto-generated method stub
		return null;
	}

	public PageInfo<CstService> findBycl(int curPage, int row) throws CrmCommonException {
		// TODO Auto-generated method stub
		return null;
	}

	public void delete(long svrId) throws CrmCommonException {
		cstServiceMapper.deleteByPrimaryKey(svrId);
	}

	public void updateCstService(CstService cstService) throws CrmCommonException {
		cstServiceMapper.updateByPrimaryKey(cstService);
	}

	public CstService queryOneByid(long svrId) throws CrmCommonException {
		return cstServiceMapper.selectByPrimaryKey(svrId);
	}

	public void updateinfo(CstService cstService) throws CrmCommonException {
		cstServiceMapper.updateByPrimaryKey(cstService);
	}

	public void Dealresult(CstService cstService) throws CrmCommonException {
		// TODO Auto-generated method stub
		
	}

	public PageInfo<CstService> findAllService(int curPage, int row) throws CrmCommonException {
		PageHelper.startPage(curPage, row);
		CstServiceExample example = new CstServiceExample();
		return new PageInfo<CstService> (cstServiceMapper.selectByExample(example));
	}
	public List<CstService> findAllService2() throws CrmCommonException {
		CstServiceExample example = new CstServiceExample();
		return cstServiceMapper.selectByExample(example);
	}

}
