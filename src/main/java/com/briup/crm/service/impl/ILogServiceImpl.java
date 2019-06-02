package com.briup.crm.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.crm.common.bean.CstLog;
import com.briup.crm.common.bean.CstLogExample;
import com.briup.crm.common.exception.CrmCommonException;
import com.briup.crm.dao.mappleInterface.CstLogMapper;
import com.briup.crm.service.interfaces.ILogService;
import com.github.pagehelper.PageInfo;

@Service
public class ILogServiceImpl implements ILogService{
	
	@Autowired
	private CstLogMapper logMapper;
	public void saveLog(CstLog log) throws CrmCommonException {
		// TODO Auto-generated method stub
		
	}

	public PageInfo<CstLog> findAllLog(int curpage, int row) throws CrmCommonException {
		// TODO Auto-generated method stub
		return null;
	}

	public List<CstLog> findAllLog() throws CrmCommonException {
		CstLogExample cstLogExample = new CstLogExample();
		cstLogExample.createCriteria().andLogDateIsNotNull();
		List<CstLog> list = logMapper.selectByExample(cstLogExample);
		return list;
	}

}
