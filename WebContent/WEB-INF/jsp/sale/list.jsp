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
<title>jb-aptech毕业设计项目</title>
<meta charset="UTF-8">
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="css/test1.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="script/common.js"></script>
<script src="js/changetrcolor.js" type="text/javascript"></script>
</head>
<body>
   <table width="100%" cellSpacing=0 cellPadding=0 border="0px">
		<tr>
			<TD class="page_title">销售机会管理</TD>
			<TD class="page_title_middle">&nbsp;</TD>
			<TD width=3><IMG height=32
				src="images/m_mpr.gif" width=3></TD>
		</tr>
	</table>
	<form
		action="sale/list/findchange"
		method="post">
		<table class="query_form_table" cellSpacing=1 cellPadding=1>
			<tr>
				<th>客户姓名</th>
				<td><input type="text" name="chcCustName" /></td>
				<th>概要</th>
				<td><input type="text" name="chcTitle" /></td>
				<th>联系人</th>
				<td><input type="text" name="chcLinkman" size="20" /></td>
			</tr>

			<tr>
				<td width=100% height=32 align=center colspan=6 bgcolor=#ffffff>
					<a href="help"><input class="common_button" type="button"
						value="帮助"></a>&nbsp;&nbsp;&nbsp;
						<a href="sale/add_salchance"><input class="common_button"
							type="button" value="新建"></a>
					 &nbsp;&nbsp; <input class="common_button" type="submit" value="查询">
				</td>
			</tr>
		</table>
	</form>
	<br />
	<table width="100%" cellSpacing=0 cellPadding=0>
		<tr>
			<TD width="247" class="page_title">检索结果</TD>
			<TD width="718" class="page_title_middle">&nbsp;</TD>
			<TD width=25><IMG height=32
				src="mages/m_mpr.gif" width=3></TD>
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
			<th>操作</th>
		</tr>
		<c:forEach items="${findSalChanceByPage.list}" var="Chance">
			<tr>
				<td class="list_data_number">${Chance.chcId }</td>
				<td class="list_data_text">${Chance.chcCustName}</td>
				<td class="list_data_ltext">${Chance.chcTitle  }</td>
				<td class="list_data_text">${Chance.chcLinkman }</td>
				<td class="list_data_text">${Chance.chcTel }</td>
				<td class="list_data_op">
				<c:if test="${usrRoleId!=4 }">
							<a
								href="${Chance.chcId}/sale/dispatch">
								<img title="指派"
								src="images/bt_linkman.gif"
								class="op_button" />
							</a>
					</c:if>
							<a
								href="${Chance.chcId}/sale/edit">
								<img title="编辑"
								src="images/bt_edit.gif"
								class="op_button" />
							</a>
					<c:if test="${usrRoleId!=4 }">
							<a
								href="${Chance.chcId}/sale/list/remove">
								<img title="删除"
								src="images/bt_del.gif"
								class="op_button" />
							</a>
					</c:if>
					<c:if test="${usrRoleId==4 }">
							<img title="指派"
								src="images/bt_linkman.png"
								class="op_button" />
					</c:if>		
							<!-- <img title="编辑"
								src="images/bt_edit.png"
								class="op_button" />  -->
	
					<c:if test="${usrRoleId==4 }">
							<img title="删除"
								src="images/bt_del.png"
								class="op_button" />
					</c:if>
			   </td>
			</tr>
	</c:forEach>
		<tr>
			<th colspan="7" class="pager">共 ${findSalChanceByPage.total }条记录 每页 ${findSalChanceByPage.pageSize }条 第${findSalChanceByPage.pageNum }页 
			<a href="1/sale/list">首页</a>
				<c:choose>
			<c:when test="${sessionScope.findSalChanceByPage.hasPreviousPage }">
				<a href="${sessionScope.findSalChanceByPage.prePage }/sale/list">上一页</a> 
			</c:when>
				<c:otherwise>
					<font color="#ABA8AB">上一页</font>
				</c:otherwise>
			</c:choose>
			<c:choose>
			<c:when test="${sessionScope.findSalChanceByPage.hasNextPage }">
				<a href="${sessionScope.findSalChanceByPage.nextPage }/sale/list">下一页</a> 
			</c:when>
				<c:otherwise>
					<font color="#ABA8AB">下一页</font>
				</c:otherwise>
			</c:choose>
 			
			 <a href="${sessionScope.findSalChanceByPage.pages }/sale/list">尾页</a>
			</th>
		</tr>
	</table>
</body>
</html>