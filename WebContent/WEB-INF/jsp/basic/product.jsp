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
<title>产品查询</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript">
function queryCustomer(){
		$("#queryform").submit();
	}
</script>
<link href="css/test1.css"
	rel="stylesheet" type="text/css">
<script src="js/changetrcolor.js"
	type="text/javascript"></script>
</head>
<body>
   <table width="100%" cellSpacing=0 cellPadding=0 border="0px">
	<tr>
		<TD class="page_title">产品查询</TD>
		<TD class="page_title_middle">&nbsp;</TD>
		<TD width=3><IMG height=32
			src="images/m_mpr.gif" width=3></TD>
	</tr>
	</table>
	<form
		action="basic/product/find"
		method="post">
		<table class="query_form_table" cellSpacing=1 cellPadding=1>
			<tr>
				<th>名称</th>
				<td><input type="text" name="prodName" /></td>
				<th>型号</th>
				<td><input type="text" name="prodType" /></td>
				<th>批次</th>
				<td><input type="text" name="prodBatch" /></td>
			</tr>
			<tr>
				<td width=100% height=32 align=center colspan=6 bgcolor=#ffffff>
					<a href="help"><input class="common_button" type="button" value="帮助"></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
					<button onclick="queryCustomer()" class="common_button">查询</button>
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
			<th>名称</th>
			<th>型号</th>
			<th>等级/批次</th>
			<th>单位</th>
			<th>单价（元）</th>
			<th>备注</th>
		</tr>
		
	<c:forEach items="${findProductAll.list }" var="product" >
			<tr>
				<td class="list_data_number">${product.prodId }</td>
				<td class="list_data_text">${product.prodName }</td>
				<td class="list_data_text">${product.prodType }</td>
				<td class="list_data_text">${product.prodBatch }</td>
				<td class="list_data_text">${product.prodUnit }</td>
				<td class="list_data_text">${product.prodPrice }</td>
				<td class="list_data_text">${product.prodMemo }</td>
			</tr>
	</c:forEach>
		<tr>
				<th colspan="7" class="pager" align="right"		style="padding-right: 20px;">共 ${findProductAll.total }条记录 每页 ${findProductAll.pageSize }条 第${findProductAll.pageNum }页 
			<a href="1/basic/product">首页</a>
			<c:choose>
			<c:when test="${sessionScope.findProductAll.hasPreviousPage }">
				<a href="${sessionScope.findProductAll.prePage }/basic/product">上一页</a> 
			</c:when>
				<c:otherwise>
					<font color="#ABA8AB">上一页</font>
				</c:otherwise>
			</c:choose>
			<c:choose>
			<c:when test="${sessionScope.findProductAll.hasNextPage }">
				<a href="${sessionScope.findProductAll.nextPage }/basic/product">下一页</a> 
			</c:when>
				<c:otherwise>
					<font color="#ABA8AB">下一页</font>
				</c:otherwise>
			</c:choose>
 			
			 <a href="${sessionScope.findProductAll.pages }/basic/product">尾页</a>
			</th>
		</tr>
	</table>
</body>
</html>