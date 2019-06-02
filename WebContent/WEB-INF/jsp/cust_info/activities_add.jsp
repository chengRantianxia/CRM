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
<title>添加交互记录</title>
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
<script type="text/javascript"
	src="js/activities_add.js"></script>
</head>
<body>
  <div class="page_title">客户信息管理 &gt; 客户信息 &gt; 交往记录 &gt; 新建交往记录</div>

<form action="${custId}/cust_info/activities_add/save" method="post" id="addatvform">
	<div class="button_bar">
	<a href="help"><input class="common_button"  type="button" value="帮助"></a>
	<button class="common_button" onclick="back();">返回</button>
	<input type="submit" value="保存" /> 
	</div>
<input type="hidden" name="cust_id" value=""/>
<table class="query_form_table">
	<tr>
		<th>时间</th>
		<td><input type="text" name="atvCustName" id="atv_date"/><span class="red_star" id="atv_datespan">*</span></td>
		<th>地点</th>
		<td>
			<input type="text" name="atvPlace" size="20" id="atv_place"/><span class="red_star" id="atv_placespan">*</span></td>
	</tr>
	<tr>
		<th>概要</th>
		<td><input type="text" name="atvTitle" id="atv_title"/><span class="red_star" id="atv_titlespan">*</span></td>
		<th>备注</th>
		<td><input type="text" name="atvDesc" /></td>
	</tr>	
</table>  
</form>
</body>
</html>