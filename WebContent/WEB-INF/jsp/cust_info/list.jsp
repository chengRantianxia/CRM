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
<script type="text/javascript">
function queryCustomer(){
		$("#queryform").submit();
	}
</script>
 <base href="<%=basePath%>">
<meta charset="UTF-8">
<title>My JSP 'list.jsp' starting page</title>

<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">

<link href="css/test1.css" rel="stylesheet" type="text/css">
<script src="script/jquery-1.4.1.js"></script>
<script src="script/common.js" charset="utf-8"></script>
<script src="js/changetrcolor.js" type="text/javascript"></script>
</head>
<body>
	<table width="100%" cellSpacing=0 cellPadding=0 border="0px">
		<tr>
			<td class="page_title">客户信息管理</td>
			<td class="page_title_middle">&nbsp;</td>
			<td width=3><IMG height=32 src="images/m_mpr.gif" width=3></td>
		</tr>
	</table>
	<form action="cust_info/list/queryByCust" method="post" id="queryform">
		<table class="query_form_table" cellSpacing=1 cellPadding=1>
			<tr>
				<th>客户编号</th>
				<td><input type="text" name="custNo" /></td>
				<th>名称</th>
				<td><input type="text" name="custName" /></td>
				<th>地区</th>
				<td><select name="custRegion">
						<option value="">全部</option>
						<option value="北京">北京</option>
						<option value="华北">华北</option>
						<option value="中南">中南</option>
						<option value="东北">东北</option>
						<option value="西部">西部</option>
				</select></td>
			</tr>
			<tr>
				<th>客户经理</th>
				<td><input type="text" name="custManagerName" /></td>
				<th>客户等级</th>
				<td><select name="custLevelLabel">
						<option value="">全部</option>
						<option value="战略合作伙伴">战略合作伙伴</option>
						<option value="合作伙伴">合作伙伴</option>
						<option value="大客户">大客户</option>
						<option value="普通客户">普通客户</option>
				</select></td>
				<th></th>
				<td></td>
			</tr>
			<tr>
				<td width=100% height=32 align=center colspan=6 bgcolor=#ffffff>
					<a href="help"><input class="common_button"
						type="button" value="帮助"></a>&nbsp;&nbsp;&nbsp; <a
					href="cust_info/add_customer"><input class="common_button"
						type="button" value="新建"></a>&nbsp;&nbsp;&nbsp;
					<button onclick="queryCustomer()" class="common_button">查询</button>
				</td>
			</tr>
		</table>
	</form>

	<br />
	<table width="100%" cellSpacing=0 cellPadding=0 border="0px">
		<tr>
			<td class="page_title">检索结果</td>
			<td class="page_title_middle">&nbsp;</td>
			<td width=3><IMG height=32 src="images/m_mpr.gif" width=3></td>
		</tr>
	</table>

	<table id="data_list_table" class="data_list_table" cellSpacing=1
		cellPadding=1>
		<tr>
			<th id="firstth">序号</th>
			<th>客户编号</th>
			<th>名称</th>
			<th>地区</th>
			<th>客户经理</th>
			<th>客户等级</th>
			<th>操作</th>
		</tr>
		<c:forEach items="${pageInfo.list }" var="Customer" >
		<tr>
			<td class="list_data_number">${Customer.custId }</td>
			<td class="list_data_text">${Customer.custNo }</td>
			<td class="list_data_ltext">${Customer.custName }</td>
			<td class="list_data_text">${Customer.custRegion }</td>
			<td class="list_data_text">${Customer.custManagerName }</td>
			<td class="list_data_text">${Customer.custLevelLabel }</td>
			<td class="list_data_op">
			<c:if test="${usrRoleId==4 }">
			<img
				onClick="to('${Customer.custId }/cust_info/customer_edit');" title="编辑"
				src="images/bt_edit.gif" class="op_button" /> 
				</c:if>
				<c:if test="${usrRoleId!=4 }">
				<img title="编辑"
				src="images/bt_edit.png" class="op_button" /> 
				</c:if>
				
				<img
				onClick="to('${Customer.custId}/cust_info/linkman');" title="联系人"
				src="images/bt_linkman.gif" class="op_button" />
				
				 <img
				onClick="to('${Customer.custId}/cust_info/activities');" title="交往记录"
				src="images/bt_orders.gif"> <!-- 经理只能删除属于自己的客户 --> 
				<c:if test="${usrRoleId==4 }">
				<a
				href="javascript:if(confirm('确认删除？')){window.location.href='${Customer.custId}/cust_info/list/remove'}">
					<img title="删除" src="images/bt_del.gif" class="op_button" /></a> 
					</c:if>
					<c:if test="${usrRoleId!=4 }">
			<img title="删除" src="images/bt_del.png" class="op_button" />
			</c:if>
			</td>
		</tr>
	</c:forEach>
		<tr>
			<th colspan="100" class="pager">共 ${pageInfo.total }条记录 每页 ${pageInfo.pageSize }条 第${pageInfo.pageNum }页 
			<a href="1/cust_info/list">首页</a>
			<c:choose>
			<c:when test="${sessionScope.pageInfo.hasPreviousPage }">
				<a href="${sessionScope.pageInfo.prePage }/cust_info/list">上一页</a> 
			</c:when>
				<c:otherwise>
					<font color="#ABA8AB">上一页</font>
				</c:otherwise>
			</c:choose>
			<c:choose>
			<c:when test="${sessionScope.pageInfo.hasNextPage }">
				<a href="${sessionScope.pageInfo.nextPage }/cust_info/list">下一页</a> 
			</c:when>
				<c:otherwise>
					<font color="#ABA8AB">下一页</font>
				</c:otherwise>
			</c:choose>
 			
			 <a href="${sessionScope.pageInfo.pages }/cust_info/list">尾页</a>
			</th>
		</tr>
	</table>
</body>
</html>