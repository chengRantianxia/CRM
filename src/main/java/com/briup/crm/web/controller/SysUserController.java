package com.briup.crm.web.controller;

import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.briup.crm.common.bean.SysRole;
import com.briup.crm.common.bean.SysUser;
import com.briup.crm.common.exception.CrmCommonException;
import com.briup.crm.service.interfaces.ISysUserService;

@Controller
public class SysUserController {
	@Autowired
	private ISysUserService iSysUserService;
	
	@RequestMapping(value="/login",method=RequestMethod.POST)
	public String showMain(@RequestParam String username,@RequestParam String password,HttpSession session){
		//得到一个用户对象sysuser
		SysUser sysUser;
		try {
			sysUser = iSysUserService.login(username, password);
			//通过sysuser的属性（roleID）来查找role
			//获得一个sysrole对象
			SysRole sysRole=iSysUserService.findRoleBySysRoleId(sysUser.getUsrRoleId());
			//获取userRoleId
			Long usrRoleId = sysUser.getUsrRoleId();
			session.setAttribute("usrRoleId", usrRoleId);
			//验证
			if(sysUser.getUsrFlag()==1&&sysRole.getRoleFlag()==1){
				//将登陆后的用户信息保存在session
				session.setAttribute("username", username);
				session.setAttribute("password", password);
				return "main";
			}
		} catch (CrmCommonException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return "index";
	}
	@RequestMapping("/close")
	public String close(HttpSession session){
		Enumeration attributeNames = session.getAttributeNames();
		while(attributeNames.hasMoreElements()){
			session.removeAttribute(attributeNames.nextElement().toString());
		}
		return "index";
	}
	
}
