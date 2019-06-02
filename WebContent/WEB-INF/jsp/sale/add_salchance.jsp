<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%
	String path = request.getContextPath();
	String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>
<html>
<head>
 <base href="<%=basePath%>">
<meta charset="UTF-8">
<title>Insert title here</title>
<title>jb-aptech毕业设计项目</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="css/test1.css" rel="stylesheet" type="text/css">
<script src="script/common.js"></script>
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

	<form action="sale/add_salchance/baocun" method="post">
		<table class="query_form_table" cellSpacing=1 cellPadding=1>
			<tr>
				<td width=100% height=30 align=left
					background="images/m_table_top.jpg"
					colspan="6"><strong>&nbsp;新建销售机会</strong></td>
			</tr>
			<tr>
				<th>编号</th>
				<td>${size }</td>
				<th>机会来源</th>
				<td><input type="text" name="chcSource" size="20" /></td>
			</tr>
			<tr>
				<th>客户名称</th>
				<td><input type="text" name="chcCustName" id="txt" /><span
					class="red_star">*</span></td>
				<th>成功机率</th>
				<td><input type="text" name="chcRate" /><span class="red_star">*</span></td>
			</tr>
			<tr>
				<th>概要</th>
				<td><input type="text" name="chcTitle" /><span
					class="red_star">*</span></td>
				<th>联系地址</th>
				<td><select name="custRegion">
						<option value="">请选择</option>
						<option value="北京">北京</option>
						<option value="华北">华北</option>
						<option value="中南">中南</option>
						<option value="东北">东北</option>
						<option value="西部">西部</option>
				</select><span class="red_star">*</span></td>
			</tr>
			<tr>
				<th>联系人</th>
				<td><input type="text" name="chcLinkman" size="20" /></td>
				<th>联系人电话</th>
				<td><input type="text" name="chcTel" size="20" /></td>
			</tr>
			<tr>
				<th>机会描述</th>
				<td colspan="3"><textarea rows="6" cols="50" name="chcDesc"></textarea><span
					class="red_star">*</span></td>
			</tr>
			<tr>
				<th>创建人</th>
				<td><input name="chcCreateBy"
					value="" readonly size="20" /><span
					class="red_star">*</span></td>
			</tr>
			<tr>
				<td width=100% height=32 align=center colspan=6 bgcolor=#ffffff>
					<a href="help"><input class="common_button" type="button"
						value="帮助"></a> &nbsp;&nbsp;&nbsp; 
					<input type="submit" value="保存/返回">
				</td>
			</tr>
		</table>
	</form>
</body>
</html>