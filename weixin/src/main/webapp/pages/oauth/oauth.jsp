<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%> 
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>oauth测试界面</h1>
	
	<h2>用户昵称:${userInfo.nickname }</h2>
	<h2>用户头像<img alt="" src="${userInfo.headimgurl }"></h2>
</body>
</html>