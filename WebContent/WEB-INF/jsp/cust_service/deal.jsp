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
<title>服务处理</title>
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
					<td class="page_title">客户服务管理</td>
					<td class="page_title_middle">&nbsp;</td>
					<td width=3><IMG height=32
						src="images/m_mpr.gif" width=3>
					</td>
				</tr>
			</table>
			<form action="cust_service/deal/find" name="frm" method="post">
				<table class="query_form_table" cellSpacing=1 cellPadding=1>
					<tr>
						<td width=100% height=30 align=left
							background="images/m_table_top.jpg"
							colspan="6"><strong>&nbsp;服务处理</strong></td>
					</tr>
					<tr>
						<th height="28">客户</th>
						<td><input type="text" name="svrCustName" /></td>
						<th height="28">概要</th>
						<td><input type="text" name="svrTitle" /></td>
					</tr>
					<tr>
						<th height="28">服务类型</th>
						<td><select name="svrType" id="con.svr_type">
								<option value="" selected="selected">全部</option>
								<option value="咨询">咨询</option>
								<option value="建议">建议</option>
								<option value="投诉">投诉</option>
						</select></td>
						<th height="22">状态</th>
						<td><input type="text" name="svr_status" value="已分配" />
						</td>
					</tr>
					<tr>
						<td width=100% height=32 align=center colspan=6 bgcolor=#ffffff>
							<a href="help"><input class="common_button" type="button"
								value="帮助"></a> &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
							<button class="common_button">查询</button>
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
						src="images/m_mpr.gif" width=3>
					</TD>
				</tr>
			</table>
			<table id="data_list_table" class="data_list_table" cellSpacing=1 cellPadding=1>
				<tr>
					<th id="firstth">编号</th>
					<th>客户</th>
					<th>概要</th>
					<th>服务类型</th>
					<th>创建人</th>
					<th>操作</th>
				</tr>
				<c:forEach items="${allService2.list }" var="service">
						<tr>
							<td class="list_data_number">${service.svrId }</td>
							<td class="list_data_text">${service.svrCustName }</td>
							<td class="list_data_ltext">${service.svrTitle }</td>
							<td class="list_data_text">${service.svrType }</td>
							<td class="list_data_text">${service.svrCreateBy }</td>
							<td>
									<a
										href="${service.svrId }/cust_service/deal_detail">
										<img
										src="images/bt_deal.gif"
										class="op_button" />
									</a>
								</td>
						</tr>
			</c:forEach>
				<tr>
			<th colspan="8" class="pager">共 ${allService2.total }条记录 每页 ${allService2.pageSize }条 第${allService2.pageNum }页 
			<a href="1/cust_service/deal">首页</a>
				<c:choose>
			<c:when test="${sessionScope.allService2.hasPreviousPage }">
				<a href="${sessionScope.allService2.prePage }/cust_service/deal">上一页</a> 
			</c:when>
				<c:otherwise>
					<font color="#ABA8AB">上一页</font>
				</c:otherwise>
			</c:choose>
			<c:choose>
			<c:when test="${sessionScope.allService2.hasNextPage }">
				<a href="${sessionScope.allService2.nextPage }/cust_service/deal">下一页</a> 
			</c:when>
				<c:otherwise>
					<font color="#ABA8AB">下一页</font>
				</c:otherwise>
			</c:choose>
 			
			 <a href="${sessionScope.allService2.pages }/cust_service/deal">尾页</a>
			</th>
		</tr>
			</table>
</body>
</html>