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
		<meta name="viewport" content="width=device-width, initial-scale=1.0, minimum-scale=1.0, maximum-scale=1.0, user-scalable=no">

		<title></title>
		<link rel="stylesheet" href="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.css">
		<link href="https://cdn.bootcss.com/jquery-mobile/1.4.5/jquery.mobile.theme.css" rel="stylesheet">
		<script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
		<script src="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.js"></script>
		<script src="https://cdn.bootcss.com/jquery-validate/1.16.0/jquery.validate.js"></script>
		<script src="https://cdn.bootcss.com/jquery.form/4.2.1/jquery.form.js"></script>

<style type="text/css">
	body {
		font-family:"微软雅黑,Arial";
		font-size:18px;
		padding:0px;
		margin:0 auto;
	}
	
	.font-blue{
		color:#4E90C7!important;
	}
	
	.input-lightblue{
		background-color: #E5F2FD!important;
	}
	
	.font-label{
		color: black!important;
		font-size: 19px!important;
	}
</style>
</head>
<body>
<div data-role="page" id="pageMain" style="">
	<div data-role="header" data-theme="c" style="background-color: #4E90C7;" data-position="fixed">
		<a href="javascript:WeixinJSBridge.call('closeWindow');" >返回</a>
		<h1>召开排行榜</h1>
	</div>
	<div data-role="content" style="padding: 0;text-align:center;width: 100%;">
		<s:iterator value="rankInfoList" id="rankInfoList" status="index">
			<div style="padding-top:0.7em;text-align:center;width: 100%;line-height:50px;border-bottom: 1px solid #86D0F5;height: 60px;">
				<div style="width: 40px;float: left;font-size:30px;padding-left: 10px;">
				<s:if test="#index.index == 0">
					<img style="height:40px;margin-left: 5px;"src="img/ic_1.png">
				</s:if>
				<s:elseif test="#index.index == 1">
					<img style="height:40px;margin-left: 5px;"src="img/ic_2.png">
				</s:elseif>
				<s:elseif test="#index.index == 2">
					<img style="height:40px;margin-left: 5px;"src="img/ic_3.png">
				</s:elseif>
				<s:else>${index.index + 1}</s:else>
				</div>
				<div style="border:0px solid blue;width:50px;height:50px;float: left;padding-left: 10px;">
		  			<img style="width:50px;height:50px;border-radius:25px;"src="${headimgurl}">
				</div>
				<div style="float: left;color: black;font-size:20px;width:auto;max-width:40%;padding-left: 15px;white-space: nowrap;overflow:hidden;text-overflow:ellipsis;text-algin:left;">
					${nickname}
				</div>
				<div style="text-align: right;padding-right:10px;white-space: nowrap;">
					<font style="font-size:28px;color:#E57330">${success_num}/${pub_num}</font> 场
				</div>
			</div>
		</s:iterator>
	</div>
</div>


	</body>
</html>