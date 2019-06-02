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
<title>jb-aptech毕业设计项目</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="css/test1.css"
	rel="stylesheet" type="text/css">
<script src="script/common.js"></script>
<script src="js/changetrcolor.js"
	type="text/javascript"></script>
</head>
<body>
  <table width="100%" cellSpacing=0 cellPadding=0 border="0px">
		<tr>
			<TD class="page_title">客户开发计划</TD>
			<TD class="page_title_middle">&nbsp;</TD>
			<TD width=3><IMG height=32
				src="images/m_mpr.gif" width=3></TD>
		</tr>
	</table>
	<form
		action="sale/dev/find"
		method="post">
		<table class="query_form_table" cellSpacing=1 cellPadding=1>
			<tr>
				<th>客户编号</th>
				<td><input type="text" name=plaId /></td>
				<th>事件</th>
				<td><input type="text" name="plaTodo" /></td>
				<th>结果</th>
				<td><input type="text" name="plaResult" size="20" /></td>
			</tr>
			<tr>
				<td width=100% height=32 align=center colspan=6 bgcolor=#ffffff>
					<a href="help"><input
						class="common_button" type="button" value="帮助"></a>&nbsp;&nbsp;&nbsp;
					&nbsp;&nbsp; <input class="common_button" type="submit" value="查询">
				
				&nbsp;&nbsp;&nbsp;&nbsp;<a href="sale/dev_plan"><img
										title="制定计划"
										src="images/bt_plan.gif"
										class="op_button" /></a> 
				</td>
			</tr>
		</table>
	</form>
	<br />
	<table width="100%" cellSpacing=0 cellPadding=0 border="0px">
	<tr>
		<TD class="page_title">检索结果</TD>
		<TD class="page_title_middle">&nbsp;</TD>
		<TD width=3><IMG height=32
			src="images/m_mpr.gif" width=3></TD>
	</tr>
	</table>

	<table id="data_list_table" class="data_list_table" cellSpacing=1
		cellPadding=1>
		<tr>
			<th id="firstth">编号</th>
			<th>客户名称</th>
			<th>概要</th>
			<th>联系人</th>
			<th>联系人电话</th>
			<th>状态</th>
			<th>操作</th>
		</tr>
			<c:forEach items="${planByPage.list}" var="plan">
							<tr>
								<td class="list_data_number">${plan.plaId }</td>
								<td class="list_data_text">${plan.plaChcId }</td>
								<td class="list_data_ltext">${plan.plaTodo }</td>
								<td class="list_data_text">${plan.plaResult }</td>
								<td class="list_data_text">${plan.plaId }</td>
								<td class="list_data_text">${plan.plaId }</td>

								<td class="list_data_op">
								<img
								onClick="to('${plan.plaId}/sale/dev_detail');"
								title="查看"
								src="images/bt_detail.gif"
								class="op_button" />
								</td>
							</tr>
						</c:forEach>
					

		<tr>
			<th colspan="10" class="pager">共 ${planByPage.total }条记录 每页 ${planByPage.pageSize }条 第${planByPage.pageNum }页 
			<a href="1/sale/dev">首页</a>
				<c:choose>
			<c:when test="${sessionScope.planByPage.hasPreviousPage }">
				<a href="${sessionScope.planByPage.prePage }/sale/dev">上一页</a> 
			</c:when>
				<c:otherwise>
					<font color="#ABA8AB">上一页</font>
				</c:otherwise>
			</c:choose>
			<c:choose>
			<c:when test="${sessionScope.planByPage.hasNextPage }">
				<a href="${sessionScope.planByPage.nextPage }/sale/dev">下一页</a> 
			</c:when>
				<c:otherwise>
					<font color="#ABA8AB">下一页</font>
				</c:otherwise>
			</c:choose>
 			
			 <a href="${sessionScope.planByPage.pages }/sale/dev">尾页</a>
			</th>
		</tr>
	</table>
</body>
</html>