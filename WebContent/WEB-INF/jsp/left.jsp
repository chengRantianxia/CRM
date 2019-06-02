<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta charset="utf-8">
<title>客户关系管理系统</title>

<link rel="stylesheet" type="text/css" href="css/nav.css">
<link rel="stylesheet" type="text/css" href="css/iconfont.css">

<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/nav.js"></script>

<script type="text/javascript">
	//点击跳转
	function forward(url) {
		parent.frames["mainFrame"].location.href = url;
	}
</script>
</head>
<body>
	<div class="nav">
		<div class="nav-top">
			<div id="mini"
				style="border-bottom: 1px solid rgba(255, 255, 255, .1)">
				<img src="images/mini.png">
			</div>
		</div>
		<ul>
		<c:if test=""></c:if>
			<!--只有主管3 高管2 客户经理4有营销管理-->
			<c:if test="${usrRoleId!=1 }">
			<li class="nav-item"><a href="javascript:;"><i
					class="my-icon nav-icon  icon-iconset0253"></i><span>营销管理</span><i
					class="my-icon nav-more"></i></a>
				<ul>
					<c:if test="${usrRoleId==3||usrRoleId==2 }">
					<!--只有主管和高管才有销售机会管理   -->
					<li onclick="forward('1/sale/list')"><a  target="mainFrame"><span>销售机会管理</span></a></li>
					</c:if>
					<c:if test="${usrRoleId==4||usrRoleId==2 }">
					<!--只有经理和高管才有客户开发计划  -->
					<li onclick="forward('1/sale/dev')"><a  target="mainFrame"><span>客户开发计划</span></a></li>
					</c:if>
				</ul></li>
		</c:if>
			<!-- 都有 -->
			<li class="nav-item"><a href="javascript:;"><i
					class="my-icon nav-icon icon-touxiang"></i><span>客户管理</span><i
					class="my-icon nav-more"></i></a>
				<ul>
					<li onclick="forward('1/cust_info/list')"><a 
						target="mainFrame"><span>客户信息管理</span></a></li>
				</ul></li>    <!--  href="cust_info/list"  -->
			
			
			<!--只有高管2 主管3 经理有4  -->
			<c:if test="${usrRoleId!=1 }">
			<li class="nav-item"><a href="javascript:;"><i
					class="my-icon nav-icon icon-worldwide"></i><span>服务管理</span><i
					class="my-icon nav-more"></i></a>
				<ul>
					<li onclick=""><a href="cust_service/add"
						target="mainFrame"><span>服务创建</span></a></li>
					<li onclick=""><a href="1/cust_service/dispatch"
						target="mainFrame"><span>服务分配</span></a></li>
					<li onclick=""><a href="1/cust_service	/deal"
						target="mainFrame"><span>服务处理</span></a></li>
					<li onclick=""><a href="1/cust_service/feedback"
						target="mainFrame"><span>服务反馈</span></a></li>
				</ul></li>
			</c:if>
			<!--只有管理员1 高管2 主管3有  -->
			<c:if test="${usrRoleId!=4 }">
			<li class="nav-item"><a href="javascript:;"><i
					class="my-icon nav-icon icon-alarm"></i><span>统计报表</span><i
					class="my-icon nav-more"></i></a>
				<ul>
					<li onclick=""><a href="jfreechart/contr"
						target="mainFrame"><span>客户贡献分析</span></a></li>
					<li onclick=""><a href="jfreechart/cons?condit=0"
						target="mainFrame"><span>客户构成分析</span></a></li>
				</ul></li>
			</c:if>

			<!-- 都有 -->
			<li class="nav-item"><a href="javascript:;"><i
					class="my-icon nav-icon icon-shengyuliuliang"></i><span>基础数据</span><i
					class="my-icon nav-more"></i></a>
				<ul>
					<li onclick="forward('1/basic/product')"><a target="mainFrame"><span>查询产品信息</span></a></li>
					<li onclick="forward('1/basic/storage')"><a  target="mainFrame"><span>查询库存</span></a></li>
				</ul></li>
			<c:if test="${usrRoleId<3 }">
			<!--只有系统管理员和高管有  -->
			<li class="nav-item"><a href="javascript:;"><i
					class="my-icon nav-icon icon-chilun"></i><span>系统设置</span><i
					class="my-icon nav-more"></i></a>
				<ul>
					<li onclick=""><a href="systemset/role_manage"
						target="mainFrame"><span>角色管理</span></a></li>
					<li onclick=""><a href="systemset/user_manage"
						target="mainFrame"><span>用户管理</span></a></li>
					<li onclick=""><a href="systemset/log_review"
						target="mainFrame"><span>日志查看</span></a></li>
				</ul></li>
				</c:if>
		</ul>
	</div>
</body>
</html>