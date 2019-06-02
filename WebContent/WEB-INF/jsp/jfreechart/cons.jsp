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
<title>My JSP 'cons.jsp' starting page</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<link href="css/style.css"
	rel="stylesheet" type="text/css">
<script src="script/common.js"></script>
<script src="script/jquery-1.4.1.js"></script>
<script type="text/javascript">
	function chaxun() {
		window.location.href ="jfreechart/cons?condit=" + $("#condit").val();
	}
</script>
</head>
<body>
  <div class="page_title">客户构成分析</div>
<div class="button_bar">
	<a href="help"><input class="common_button"  type="button" value="帮助"></a>
	<button class="common_button" onclick="chaxun();">查询</button> 
	</div>
<table class="query_form_table">
	<tr>
		<th>报表方式</th>
		<td>
			<select name="condit" id="condit">
				<option value="0">按等级</option>
				<option value="1">按信用度</option>
				<option value="2">按满意度</option>
			</select>
		</td>
	<th>&nbsp;</th>
		<td>
			&nbsp;
		</td>
			</tr>
	</table>
<br />
 <div style="width:800px;margin:20px auto">
	 <img src="${sessionScope.consURL }" width=800 height=400 border=0 id="cstimg">
	</div> 
</body>
</html>