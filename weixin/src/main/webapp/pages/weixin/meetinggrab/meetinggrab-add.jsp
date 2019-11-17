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
		<meta http-equiv="Cache-Control" content="no-cache,must-revalidate">
		<meta http-equiv="pragma" content="no-cache">
		<meta http-equiv="expires" content="0">
		<meta name="format-detection" content="telephone=no, address=no">
		<meta content="yes" name="apple-mobile-web-app-capable">
		<meta content="black-translucent" name="apple-mobile-web-app-status-bar-style">
		<title></title>
		<link rel="stylesheet" href="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.css">
		<link href="https://cdn.bootcss.com/jquery-mobile/1.4.5/jquery.mobile.theme.css" rel="stylesheet">
		
		<script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
		<script src="http://code.jquery.com/mobile/1.3.2/jquery.mobile-1.3.2.min.js"></script>


  	
	<script type="text/javascript">
	function subMeetingPub(){
		var remark=$("#remark").val();
		var uid=$("#uid").val();
		var pid=$("#pid").val();
		if(remark.trim()==""){
			alert("备注不能为空");
			return;
		}
		alert(uid);
		alert(pid);
		var formData=$("#formGrab").serialize();
		$.ajax({
			type:"post",
			url:"meetinggrab/insert",
			data:formData,
			success:function(msg){
				window.location.href="pages/weixin/meetinggrab/meetinggrab.jsp?uid="+uid;
			}
		});
	}
			
			
	</script>
</head>
<body>
	
<div data-role="page" id="pageDetail">
			<div style="padding:0px;background-color: #4E90C7;width: 100%;height:40px;line-height:40px;font-size:18px;text-align: center;cursor: pointer;" data-role="none">
				<div style="width: 100%;float: left;color: white;" id="one_tab">
					抢单
					
				</div>
			
				
			</div>
			<div id="one" class="ui-body-d ui-content" style="padding:0;display: block;width: 100%;">
				<font style="padding:10px 10px 10px 15px;display: block;color: #777777;">请填写会议抢单相关信息</font>
				<div style="width: 100%;background-color: white;padding:10px 0 10px 0;">
					<form  id="formGrab">
						<input type="hidden" name="uid" id="uid" value="${uid}">
						<input type="hidden" name="pid" id="pid" value="${pid}">
						<div style="padding-right:15px;padding-left:15px">
							<label for="premark" class="font-label">备注(必填，50字以内)</label>
							<textarea name="remark" id="remark" placeholder="请输入会议备注" maxlength="50" class="font-blue input-lightblue" style="box-shadow: none;"></textarea>
						</div>
						<div style="padding-right:15px;padding-left:15px">
							<input type="button" value="进行抢单"  onclick="subMeetingPub()" id="btnId" />
						</div>
					</form>
				</div>
			</div>
	</div>
	</body>

</html>