package com.briup.crm.service.impl;

import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.briup.crm.common.bean.CstCustomer;
import com.briup.crm.common.bean.CstCustomerExample;
import com.briup.crm.common.bean.SysRole;
import com.briup.crm.common.bean.SysRoleExample;
import com.briup.crm.common.bean.SysUser;
import com.briup.crm.common.bean.SysUserExample;
import com.briup.crm.common.exception.CrmCommonException;
import com.briup.crm.dao.mappleInterface.SysRoleMapper;
import com.briup.crm.dao.mappleInterface.SysUserMapper;
import com.briup.crm.service.interfaces.ISysUserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.github.pagehelper.util.StringUtil;

@Service
public class ISysUserServiceImpl implements ISysUserService {
	
	@Autowired
	private SysUserMapper sysUserMapper;//用户的映射
	@Autowired
	private SysRoleMapper sysRoleMapper;//角色的映射
	
	public SysUser login(String username, String password) throws CrmCommonException {
		//判断账号密码是否为空
		/*if(username==null||password==null){*/ //相等于
		if(StringUtils.isBlank(username)||StringUtils.isBlank(password)){
			//抛出一个异常
			CrmCommonException.getMessage(401);
		}
		//需要的是example对象
		SysUserExample userExample = new SysUserExample();
		userExample.createCriteria().andUsrNameEqualTo(username).andUsrPasswordEqualTo(password);
		//根据example对象查询数据库
		List<SysUser> list = sysUserMapper.selectByExample(userExample);
		//判断list中有值
		if(list==null&&list.size()==0){
			throw CrmCommonException.getException(402);
		}
		return list.get(0);
	}

	public List<SysUser> findAllMgr() throws CrmCommonException {
		SysUserExample userExample = new SysUserExample();
		userExample.createCriteria().andUsrIdIsNotNull();
		List<SysUser> list = sysUserMapper.selectByExample(userExample);
		return list;
	}

	public List<SysRole> findAllRole() throws CrmCommonException {
		SysRoleExample roleExample = new SysRoleExample();
		roleExample.createCriteria().andRoleIdIsNotNull();
		List<SysRole> list = sysRoleMapper.selectByExample(roleExample);
		return list;
	}
	//根据角色编号来查找角色
	public SysRole findRoleBySysRoleId(long roleId) throws CrmCommonException {
		return sysRoleMapper.selectByPrimaryKey(roleId);
	}

	public void updateRole(SysRole role) throws CrmCommonException {
		sysRoleMapper.updateByPrimaryKey(role);
	}

	public PageInfo<SysUser> findAllSysUser(int curpage, int row) throws CrmCommonException {
		PageHelper.startPage(curpage, row);
		return new PageInfo<SysUser>(findAllMgr());
	}

	public int getAllRows() throws CrmCommonException {
		SysRoleExample example = new SysRoleExample();
		List<SysRole> list = sysRoleMapper.selectByExample(example);
		return list.size();
	}

	public SysUser findOneUser(long usrId) throws CrmCommonException {
		return sysUserMapper.selectByPrimaryKey(usrId);
	}

	public void updateUser(SysUser user) throws CrmCommonException {
		sysUserMapper.updateByPrimaryKeySelective(user);
	}

	public void addUser(SysUser user) throws CrmCommonException {
		sysUserMapper.insert(user);
	}

	public void deleteUser(long usrId) throws CrmCommonException {
		sysUserMapper.deleteByPrimaryKey(usrId);
	}

	public void addRole(SysRole role) throws CrmCommonException {
		sysRoleMapper.insertSelective(role);
	}

	public void deleteRole(long roleId) throws CrmCommonException {
		sysRoleMapper.deleteByPrimaryKey(roleId);
	}

}
