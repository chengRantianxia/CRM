<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="java.util.List"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
 <base href="<%=basePath%>">
<meta charset="UTF-8">
<title>My JSP 'user_manage.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="css/test1.css"
	rel="stylesheet" type="text/css">
<script src="script/common.js"></script>
</head>
<body>
  	<table width="100%" cellSpacing=0 cellPadding=0 border="0px">
		<tr>
			<td class="page_title">系统设置</td>
			<td class="page_title_middle">&nbsp;</td>
			<td width=3><IMG height=32
				src="images/m_mpr.gif" width=3></td>
		</tr>
	</table>
	<p style="font-size: 11px;">角色等级说明: 1-系统管理员 2-高管 3-销售主管 4-客户经理</p>
	<table class="query_form_table" cellSpacing=1 cellPadding=1
		style="text-align: center;">
		<tr>
			<td width=100% height=30 align=left
				background="images/m_table_top.jpg"
				colspan="6"><strong>&nbsp;用户管理</strong> 
					<a href="systemset/add"><input class="common_button" type="button" value="新增"></a>
				</td>
		</tr>
		<tr>
			<th id="firstth">编号</th>
			<th>用户名</th>
			<th>角色等级</th>
			<th>状态</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${findAllMgr }" var="User" >
			<tr>
				<td class="list_data_number">${User.usrId }</td>
				<td class="list_data_ltext" >${User.usrName }</td>
				<td class="list_data_ltext" >${User.usrFlag }</td>
				<td class="list_data_text">
			                                  正常
			    </td>
				<td class="list_data_op">
							<img
								onClick="to('${User.usrId }/systemset/user_update');" 
								title="编辑"
								src="images/bt_edit.gif"
								class="op_button" />
		<a href="javascript:if(confirm('确认删除？')){window.location.href='${User.usrId}/systemset/user_manage/remove'}">
						<img
						title="删除"
						src="images/bt_del.gif"
						class="op_button" />
								</a>
				</td>
			</tr>
</c:forEach>

		<tr>
			<td colspan="5">共条记录 每页 5 条 第页 <a
				href="">首页</a>
			
			
					<a href="">上一页</a>
			
					<font color="#ABA8AB">上一页</font>
		
				
			
					<a href="">下一页</a>
			
				
		
					<font color="#ABA8AB">下一页</font>
			
				<a href="">尾页</a>
			</td>
		</tr>
		
	</table>
	<br />
</body>
</html>