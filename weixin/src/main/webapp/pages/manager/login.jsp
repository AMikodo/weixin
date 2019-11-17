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
<meta charset="utf-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<script src="https://apps.bdimg.com/libs/jquery/2.1.4/jquery.min.js"></script>
<link href="js/H-ui/static/h-ui/css/H-ui.min.css" rel="stylesheet" type="text/css" />
<link href="js/H-ui/static/h-ui.admin/css/H-ui.login.css" rel="stylesheet" type="text/css" />
<link href="js/H-ui/static/h-ui.admin/css/style.css" rel="stylesheet" type="text/css" />
<link href="js/H-ui/lib/Hui-iconfont/1.0.8/iconfont.css" rel="stylesheet" type="text/css" />

<title>会议达人后台管理系统</title>
<script type="text/javascript">
	
	function changeCode(){
		var codeId=$("#codeimg");
		codeId.attr("src","validate.code?a="+Math.random());
	}
	function checkInfo(){
		var uname=$("#uname").val();
		if(uname==""||uname==undefined){
			alert("用户名不能为空");
			return;
		}
		var upass=$("#upass").val();
		if(upass==""||upass==undefined){
			alert("密码不能为空");
			return;
		}
		var validatecode=$("#validatecode").val();
		if(validatecode==""||validatecode==undefined){
			alert("验证码不能为空");
			return;
		}
		document.formdata.submit();
	}
</script>
</head>

<body>
<input type="hidden" id="TenantId" name="TenantId" value="" />
<div class="header"></div>
<div class="loginWraper">
  <div id="loginform" class="loginBox">
    <form class="form form-horizontal" action="adminUser/login" method="post" name="formdata">
      <div class="row cl">
        <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60d;</i></label>
        <div class="formControls col-xs-8">
          <input id="uname" name="uname" value="${param.uname }" type="text" placeholder="账户" class="input-text size-L">
        </div>
      </div>
      <div class="row cl">
        <label class="form-label col-xs-3"><i class="Hui-iconfont">&#xe60e;</i></label>
        <div class="formControls col-xs-8">
          <input id="upass" name="upass" type="password" placeholder="密码" class="input-text size-L">
        </div>
      </div>
      <div class="row cl">
        <div class="formControls col-xs-8 col-xs-offset-3" >
          <input id="validatecode" name="validatecode" class="input-text size-L" type="text" placeholder="验证码"  onclick="if(this.value=='验证码:'){this.value='';}" style="width:130px;">
          <img id="codeimg" src="validate.code"> <a id="kanbuq" onclick="changeCode()">看不清，换一张</a> </div>
      </div>
      <div class="row cl">
        <div class="formControls col-xs-8 col-xs-offset-3">
          <label for="online">
            <input type="checkbox" name="online" id="online" value="">
            使我保持登录状态</label><div style="color:red;font-size:16px" >
            <c:if test="${param.tips==1}">
            	验证码错误
            </c:if>
            <c:if test="${param.tips==2}">
            	账户或者密码错误
            </c:if>
            </div>
        </div>
      </div>
      <div class="row cl">
        <div class="formControls col-xs-8 col-xs-offset-3">
          <input name="" type="button" onclick="checkInfo()" class="btn btn-success radius size-L" value="&nbsp;登&nbsp;&nbsp;&nbsp;&nbsp;录&nbsp;">
          <input name="" type="reset" class="btn btn-default radius size-L" value="&nbsp;取&nbsp;&nbsp;&nbsp;&nbsp;消&nbsp;">
        </div>
      </div>
    </form>
  </div>
</div>
<div class="footer">会议达人</div>
<script type="text/javascript" src="js/H-ui/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="js/H-ui/static/h-ui/js/H-ui.min.js"></script>
</body>
</html>