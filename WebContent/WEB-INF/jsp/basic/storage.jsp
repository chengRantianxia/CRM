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
<title>库存查询</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="css/test1.css" rel="stylesheet" type="text/css">
<script type="text/javascript">
function queryCustomer(){
		$("#queryform").submit();
	}
</script>
<script src="js/changetrcolor.js" type="text/javascript"></script>
</head>
<body>
  	<table width="100%" cellSpacing=0 cellPadding=0 border="0px">
		<tr>
			<TD class="page_title">库存查询</TD>
			<TD class="page_title_middle">&nbsp;</TD>
			<TD width=3><IMG height=32
				src="images/m_mpr.gif" width=3></TD>
		</tr>
	</table>
	<form
		action="basic/storage/find"
		method="post">
		<table class="query_form_table" cellSpacing=1 cellPadding=1>
			<tr>
				<th>产品</th>
				<td><input type="text" name="stkName" /></td>
				<th>仓库</th>
				<td><input type="text" name="stkWarehourse" /></td>
			</tr>
			<tr>
				<td width=100% height=32 align=center colspan=6 bgcolor=#ffffff>
					<a href="help"><input
						class="common_button" type="button" value="帮助"></a>&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
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
			<th id="firstth">序号</th>
			<th>产品</th>
			<th>仓库</th>
			<th>货位</th>
			<th>件数</th>
			<th>备注</th>
		</tr>
	<c:forEach items="${findStorageAll.list }" var="Storage" >
			<tr>
				<td class="list_data_number">${Storage.stkId }</td>
				<td class="list_data_text">${Storage.stkName }</td>
				<td class="list_data_text">${Storage.stkWarehourse }</td>
				<td class="list_data_text">${Storage.stkWare }</td>
				<td class="list_data_text">${Storage.stkCount }</td>
				<td class="list_data_text">${Storage.stkMemo }</td>
			</tr>
	</c:forEach>
	
		<tr>
			<th colspan="6" class="pager" align="right"
				style="padding-right: 20px;">共 ${findStorageAll.total }条记录 每页 ${findStorageAll.pageSize }条 第${findStorageAll.pageNum }页 
			<a href="1/basic/storage">首页</a>
			<c:choose>
			<c:when test="${sessionScope.findStorageAll.hasPreviousPage }">
				<a href="${sessionScope.findStorageAll.prePage }/basic/storage">上一页</a> 
			</c:when>
				<c:otherwise>
					<font color="#ABA8AB">上一页</font>
				</c:otherwise>
			</c:choose>
			<c:choose>
			<c:when test="${sessionScope.findStorageAll.hasNextPage }">
				<a href="${sessionScope.findStorageAll.nextPage }/basic/storage">下一页</a> 
			</c:when>
				<c:otherwise>
					<font color="#ABA8AB">下一页</font>
				</c:otherwise>
			</c:choose>
 			
			 <a href="${sessionScope.findStorageAll.pages }/basic/storage">尾页</a>
			</th>
		</tr>
	</table>
</body>
</html>