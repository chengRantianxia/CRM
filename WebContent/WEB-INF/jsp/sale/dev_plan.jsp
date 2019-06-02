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
<title>jb-aptech毕业设计项目</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="css/test1.css"
	rel="stylesheet" type="text/css">
<script type="text/javascript"
	src="script/common.js">
</script>
</head>
<body>
<form action="sale/dev_plan/add" method="post" >
   <table width="100%" cellSpacing=0 cellPadding=0 border="0px">
		<tr>
			<td class="page_title">销售机会管理</td>
			<td class="page_title_middle">&nbsp;</td>
			<td width=3><IMG height=32
				src="images/m_mpr.gif" width=3></td>
		</tr>
	</table>
	<table class="query_form_table" cellSpacing=1 cellPadding=1>
		<tr>
			<td width=100% height=30 align=left
				background="images/m_table_top.jpg"
				colspan="6"><strong>&nbsp;制定计划</strong></td>
		</tr>
		<tr>
			<th>编号</th>
			<td>${plaId }</td>
			<th>客户编号</th>
			<td>2</td>
		</tr>
		<tr>
			<th>事件</th>
			<td><input type="text" name="plaTodo" id="plaTodo"/></td>
			<th>结果</th>
			<td><input type="text" name="plaResult" id="plaResult"/></td>
		</tr>
		<tr>
			<td width=100% height=32 align=center colspan=6 bgcolor=#ffffff>
				<a href="help"><input class="common_button" type="button"
					value="帮助"></a>&nbsp;&nbsp;&nbsp; 
						<!-- <a href="sale/dev_plan/add"> -->
						<input type="submit"  class="common_button"></input><!-- </a>  -->&nbsp;&nbsp;&nbsp; 
						<font color="#ABA8AB"></font>
					 <a href="1/sale/dev"><input class="common_button"
					type="button" value="返回"></a>
			</td>
		</tr>
	</table>
	</form>
	<br />
</body>
</html>