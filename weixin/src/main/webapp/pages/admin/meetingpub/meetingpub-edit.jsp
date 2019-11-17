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
<title>发单编辑界面</title>
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

<title>发单修改</title>

</head>
<body>
<article class="page-container">
	<form class="form form-horizontal" id="form-meetingpub-edit" name="form-meetingpub-edit" method="post" action="meetingpubManager/updateTo">
	<input type="hidden" name="pid" id="pid" value="${meetingpub.pid }">
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2"><span class="c-red">*</span>订单编号：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${meetingpub.pcode }" placeholder="" id="pcode" name="pcode" readonly="readonly">
			</div>
		</div>
		
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">会议名称：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${meetingpub.ptitle }" placeholder="会议名称" id="ptitle" name="ptitle">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">会议类别：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${meetingpub.tname }" placeholder="会议类别" id="tname" name="tname">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">抢者区域：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${meetingpub.zone }" placeholder="抢者区域" id="zone" name="zone">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">会议时间：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<input type="text" class="input-text" value="${meetingpub.ptime }" placeholder="会议时间" id="ptime" name="ptime">
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">备注：</label>
			<div class="formControls col-xs-8 col-sm-9">
				<textarea id="remark" name="remark" cols="" rows="" class="textarea">${role.remark }</textarea>
				
			</div>
		</div>
		<div class="row cl">
			<label class="form-label col-xs-4 col-sm-2">状态：</label>
				<div class="check-box">
					<c:if test="${meetingpub.status==0 }">
					<input type="checkbox"  onclick="change(this)" value="">
					<label for="checkbox-pinglun" id="statusId" >无效</label>
					<input type="hidden" id="status" name="status" value="${meetingpub.status}">
					</c:if>
					<c:if test="${meetingpub.status==1 }">
					<input type="checkbox" checked="checked" onclick="change(this)" value="">
					<label for="checkbox-pinglun" id="statusId" >有效</label>
					<input type="hidden" id="status" name="status" value="${meetingpub.status}">
					</c:if>
				</div>
		</div>
		<div class="row cl">
			<div class="col-xs-8 col-sm-9 col-xs-offset-4 col-sm-offset-2">
				<button onClick="article_save_submit();" class="btn btn-primary radius" type="submit"><i class="Hui-iconfont">&#xe632;</i>点击更新</button>
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
	$("#form-role-edit").validate({
		rules:{
			ptitile:{
				required:true,
			},
			tname:{
				required:true,
			},
			zone:{
				required:true,
			},
			ptime:{
				required:true,
			},
			remark:{
				required:true,
			},
		},
		onkeyup:false,
		focusCleanup:true,
		success:"valid",
		submitHandler:function(form){
			document.form-meetingpub-edit.submit();
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