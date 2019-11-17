<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%> 
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />

<link rel="stylesheet" type="text/css" href="js/H-ui/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="js/H-ui/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="js/H-ui/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="js/H-ui/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="js/H-ui/static/h-ui.admin/css/style.css" />

<title>我的桌面</title>
</head>
<body>
<div class="page-container">
	<p class="f-20 text-success">欢迎进入后台管理系统 <span class="f-14">v1.0</span></p>
	<p>登录次数：${sessionScope.adminUser.count } </p>
	<p>上次登录IP：${sessionScope.adminUser.lastip }  上次登录时间：
	<fmt:formatDate value="${sessionScope.adminUser.lastdate }" pattern="yyyy-MM-dd HH:mm:ss"/>
	</p>
</div>
<footer class="footer mt-20">
	<div class="container">
		<p>感谢jQuery、layer、laypage、Validform、UEditor、My97DatePicker、iconfont、Datatables、WebUploaded、icheck、highcharts、bootstrap-Switch<br>
			Copyright &copy;2015-2017 H-ui.admin v3.0 All Rights Reserved.  <a href="http://www.mycodes.net/" target="_blank">源码之家</a><br>
			本后台系统由<a href="http://www.h-ui.net/" target="_blank" title="H-ui前端框架">H-ui前端框架</a>提供前端技术支持</p>
	</div>
</footer>
<script type="text/javascript" src="js/H-ui/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="js/H-ui/static/h-ui/js/H-ui.min.js"></script> 

</body>
</html>