
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
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

		<title>个人信息页面</title>
		<link rel="stylesheet" href="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.css">
		<link href="https://cdn.bootcss.com/jquery-mobile/1.4.5/jquery.mobile.theme.css" rel="stylesheet">
		<script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
		<script src="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.js"></script>
		<script src="https://cdn.bootcss.com/jquery-validate/1.16.0/jquery.validate.js"></script>
		<script src="https://cdn.bootcss.com/jquery.form/4.2.1/jquery.form.js"></script>
		<script src="js/jquerymobile_popup.js"></script>
<style type="text/css">
	body {
		font-family:微软雅黑,arial;
		font-size:16px;
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
	
	/* 验证错误提示信息*/ 
	form label.error{color: Red;padding:0px;margin:0px;line-height:0px;white-space: nowrap;} 
</style>
<script type="text/javascript">
	function close(){
		WeixinJSBridge.call('closeWindow');
	}
	function update(){
		var name=$("#name").val();
		var telphone=$("#telphone").val();
		if(name.trim()==""){
			openPopup('用户名不能为空','提示',undefined,true,undefined,'error','cn');
			return;
		}else if(telphone.trim()==""){
			openPopup('手机号不能为空','提示',undefined,true,undefined,'error','cn');
			return;
		}
		var formData = $("#form").serialize();
		$.ajax({
			type:"post",
			url:"userWechat/update",
			data:formData,
			success:function(msg){
				openPopup('更新成功','提示',undefined,true,undefined,'error','cn');
				setTimeout(function(){WeixinJSBridge.call('closeWindow')},1000)
				
			}
		});
	}
</script>
</head>
<body>
<div data-role="page" id="pageMain">
	<div data-role="header" data-theme="c" style="background-color: #4E90C7;" data-position="fixed">
		<a href="javascript:close()" >返回</a>
		<h1>个人信息</h1>
	</div>
<div style="padding-top:20px;padding-bottom: 20px">
	<form id="form" name="form"  method="post">
		<!-- <div>基本信息</div> -->
		<div>
		     <div style="padding-right:20px;padding-left:20px">
		     	<label for="name"  class="font-label">姓名(<font color="red">必填</font>)</label>
				<input type="text" name="name" id="name" value="${user.name }"  placeholder="请输入您的真实姓名" class="required font-blue input-lightblue">
		     </div>
			<input type="hidden" id="uid" name="uid" value="${user.uid }">
			<div class="ui-grid-a" style="padding-right:20px;padding-left:20px">
				<div class="ui-block-a" style="padding-right:10px">
					<label for="province"  class="font-label">省份</label>
		    		<input type="text" name="province" id="province" value="${user.province }"  class="font-blue input-lightblue">
				</div>
				<div class="ui-block-b" style="padding-left:10px">
					<label for="city"  class="font-label">城市</label>
		    		<input type="text" name="city" id="city" value="${user.city }" class="font-blue input-lightblue">
				</div>
			</div>
			<div class="ui-grid-a" style="padding-right:20px;padding-left:20px">
				<div class="ui-block-a" style="padding-right:10px">
					<label for="zone"  class="font-label">地区</label>
		    		<input type="text"  value="${user.zone }"  readonly="readonly"  class="font-blue input-lightblue">
				</div>
				<div class="ui-block-b" style="padding-left:10px">
					<label for="rid"  class="font-label">权限</label>
					
		    		<input type="text" 
		    			<c:if test="${user.rid==1}">value="抢单组"</c:if>
		    			<c:if test="${user.rid==2}">value="发单组"</c:if>
		    		  readonly="readonly" class="font-blue input-lightblue">
				</div>
			</div>
			
			<div style="padding-right:20px;padding-left:20px">
				<label for="cellphone"  class="font-label">手机号(<font color="red">必填</font>)</label>
		    	<input type="text" name="telphone" id="telphone" value="${user.telphone }"   class="required isPhone font-blue input-lightblue" >
		    	
		    	<label for="section"  class="font-label">邮箱(此项不可更改)</label>
		    	<input type="text" id="email"  class="font-blue input-lightblue" value="${user.email }" readonly="readonly">
			</div>
					
			
	    </div>
	    
	    <div style="padding-right:20px;padding-left:20px;padding-top:20px;">
	    	<input id="updateBtn" onclick="update()" type="button" value="更新"  style="padding:10px;background: #e57330;text-shadow: none;opacity:100;color:white;font-size:20px;text-indent:0px;font-family:微软雅黑;" >
	    </div>
	</form>
</div>
</div>
</body>
</html>
