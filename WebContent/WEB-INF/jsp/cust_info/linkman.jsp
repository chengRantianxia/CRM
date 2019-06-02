<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<input type="hidden" name="custId" value="${custId }"/>
<head>
 <base href="<%=basePath%>">
<title>联系人</title>
<meta charset="UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="css/test1.css" rel="stylesheet" type="text/css">
<script src="script/common.js"></script>
<script src="script/jquery-1.4.1.js"></script>
</head>
<body>
<table width="100%" cellSpacing=0 cellPadding=0 border="0px">
		<tr>
			<td class="page_title">客户信息管理</td>
			<td class="page_title_middle">&nbsp;</td>
			<td width=3><IMG height=32
				src="images/m_mpr.gif" width=3></td>
		</tr>
	</table>
	<table class="query_form_table" cellSpacing=1 cellPadding=1>
		<tr>
			<td width=100% height=30 align=left
				background="images/m_table_top.jpg"
				colspan="6"><strong>&nbsp;客户信息 > 联系人 </strong></td>
		</tr>
		<tr>
			<td colspan="4" align="center"><a href="help"><input
					class="common_button" type="button" value="帮助"></a>&nbsp;&nbsp;
				<a href="${custId}/cust_info/linkman_add"><button class="common_button" type="button">新建</button></a>&nbsp;&nbsp;
				<a href="1/cust_info/list"><input
					class="common_button" type="button" value="返回"></a></td>
		</tr>
	</table>
	<br />
	<table class="data_list_table" cellSpacing=1 cellPadding=1>
		<tr>
			<th>姓名</th>
			<th>性别</th>
			<th>职位</th>
			<th>办公电话</th>
			<th>手机</th>
			<th>备注</th>
			<th>操作</th>
		</tr>
			<c:forEach items="${findAllLinkManByCstId }"  var="LinkMan">
			<tr>
				<td class="list_data_text">${LinkMan.lkmName }</td>
				<td class="list_data_ltext">${LinkMan.lkmSex }</td>
				<td class="list_data_text">${LinkMan.lkmPostion }</td>
				<td class="list_data_text">${LinkMan.lkmTel }</td>
				<td class="list_data_text">${LinkMan.lkmMobile }</td>
				<td class="list_data_op">${LinkMan.lkmMemo }</td>
				<td class="list_data_op">
				<img
					onClick="to('${LinkMan.lkmId}/cust_info/linkman_edit');" title="编辑"
					src="images/bt_edit.gif"
					class="op_button" />
					<a
				href="javascript:if(confirm('确认删除？')){window.location.href='${LinkMan.lkmId}/cust_info/linkman/remove'}">
					<img
					title="删除"
					src="images/bt_del.gif"
					class="op_button" />
					</a></td>
			</tr>
			</c:forEach>
	</table>
</body>
</html>
