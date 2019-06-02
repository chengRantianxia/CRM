<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
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
<title>交往记录</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="css/style.css"
	rel="stylesheet" type="text/css">
<script src="script/common.js"></script>
<script type="text/javascript"
	src="script/jquery-1.4.1.js"></script>
</head>
<body>
  <div class="page_title">客户信息管理 &gt; 客户信息 &gt; 交往记录</div>
	<div class="button_bar">
		<a href="help"><input
			class="common_button" type="button" value="帮助"></a>
		<a href="${custId} /cust_info/activities_add"><button class="common_button">新建</button></a>
		<a href="1/cust_info/list"><input
			class="common_button" type="button" value="返回"></a>
	</div>

	<br />

	<table class="data_list_table">
		<tr>
			<th>地点</th>
			<th>概要</th>
			<th>备注</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${ findAllCstActivityByCstNo}"  var="CstActivity">
			<tr>
				<td class="list_data_text">${CstActivity.atvPlace }</td>
				<td class="list_data_ltext">${CstActivity.atvTitle }</td>
				<td class="list_data_ltext">${CstActivity.atvDesc }</td>
				<td class="list_data_op">
				 <img
					onclick="to('${CstActivity.atvId}/cust_info/activities_edit');"
					title="编辑"
					src="images/bt_edit.gif"
					class="op_button" />
				<a href="javascript:if(confirm('确认删除？')){window.location.href='${CstActivity.atvId}/cust_info/activities/remove'}">
					<img
					title="删除"
					src="images/bt_del.gif"
					class="op_button" /> </a> </td>  
			</tr>
			</c:forEach>
	</table>
</body>
</html>