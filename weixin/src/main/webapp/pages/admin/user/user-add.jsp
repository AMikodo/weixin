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
<title>角色编辑界面</title>
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="Bookmark" href="/favicon.ico" >
<link rel="Shortcut Icon" href="/favicon.ico" />

<link rel="stylesheet" type="text/css" href="js/H-ui/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="js/H-ui/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="js/H-ui/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="js/H-ui/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="js/H-ui/static/h-ui.admin/css/style.css" />

<!--/meta 作为公共模版分离出去-->

<title>用户修改</title>

</head>
<body>
<article class="page-container">
	<form class="form form-horizontal" id="form-user-add" name="form-user-add" method="post" action="user/insert">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>用户名称：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text"  placeholder="" id="name" name="name">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">邮箱：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" placeholder="" id="email" name="email">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">手机号码：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text"  placeholder="" id="telphone" name="telphone">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">省份：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" placeholder="" id="province" name="province">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">城市：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text"  placeholder="" id="city" name="city">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">地区：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text"  placeholder="" id="zone" name="zone">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">角色ID：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text"  placeholder="" id="rid" name="rid">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">状态：</label>
				<div class="check-box">
					<input type="checkbox"  onclick="change(this)" value="">
					<label for="checkbox-pinglun" id="statusId" >无效</label>
					<input type="hidden" id="status" name="status" value="0">
					
		</div>
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
				<button onClick="article_save_submit();" class="btn btn-primary radius" type="submit"><i class="Hui-iconfont">&#xe632;</i>点击添加</button>
				<button onClick="removeIframe();" class="btn btn-default radius" type="button">&nbsp;&nbsp;取消&nbsp;&nbsp;</button>
			</div>
		</div>
	</form>
</article>

<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="js/H-ui/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="js/H-ui/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="js/H-ui/static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="js/H-ui/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer /作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="js/H-ui/lib/My97DatePicker/4.8/WdatePicker.js"></script>
<script type="text/javascript" src="js/H-ui/lib/jquery.validation/1.14.0/jquery.validate.js"></script> 
<script type="text/javascript" src="js/H-ui/lib/jquery.validation/1.14.0/validate-methods.js"></script> 
<script type="text/javascript" src="js/H-ui/lib/jquery.validation/1.14.0/messages_zh.js"></script>
<script type="text/javascript" src="js/H-ui/lib/webuploader/0.1.5/webuploader.min.js"></script> 
<script type="text/javascript" src="js/H-ui/lib/ueditor/1.4.3/ueditor.config.js"></script> 
<script type="text/javascript" src="js/H-ui/lib/ueditor/1.4.3/ueditor.all.min.js"> </script> 
<script type="text/javascript" src="js/H-ui/lib/ueditor/1.4.3/lang/zh-cn/zh-cn.js"></script>
<script type="text/javascript">
function change(obj){
	if(obj.checked==true){
		$("#statusId").html("有效");
		$("#status").val("1")
	}else{
		$("#statusId").html("无效");
		$("#status").val("0")
	}
}

$(function(){
	$('.skin-minimal input').iCheck({
		checkboxClass: 'icheckbox-blue',
		radioClass: 'iradio-blue',
		increaseArea: '20%'
	});
	
	//表单验证
	$("#form-user-add").validate({
		rules:{
			name:{
				required:true,
			},
			sortnum:{
				digits:true,
			},	
			
			email:{
				
				email:true,
			},
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){
			document.form-user-add.submit();
			//$(form).ajaxSubmit();
			var index = parent.layer.getFrameIndex(window.name);
			//parent.$('.btn-refresh').click();
			parent.layer.close(index);
		}
	});
});
	
</script>
<!--/请在上方写此页面业务相关的脚本-->
</body>
</html>