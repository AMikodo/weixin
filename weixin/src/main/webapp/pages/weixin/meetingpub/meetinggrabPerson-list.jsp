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
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

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
	    
	      $(function(){
	    	  showMyMeetings();
	      });
	    
			function showMyMeetings() {
					var pid=$("#pid").val();
					$("#two").empty();
					$.ajax({
						type:"post",
						url:"meetinggrab/selectMeetinggrabListByPid",
						data:{"pid":pid},
						success:function(msg){
							if(msg.length<=0){
								var appendHtml = "<font style='padding: 20px 20px 20px 30px;display: block;color: #777777;'>暂无人抢单</font>";
							}
							var appendHtml = "<font style='padding: 10px 10px 10px 15px;display: block;color: #777777;'>共有"+msg.length+"人抢单</font>";
							for(var i=0 ; i<msg.length;i++){
				        		appendHtml += "<div style='width: 100%;background-color: white;margin-top: -3px;padding:10px 10px 10px 15px;display: inline-block;'>" +
								"<div style='width: 70%;float: left;' onclick='showMeetingInfo(\"" +1 + "\");'>" +
								"<div style='white-space: nowrap;overflow: hidden;text-overflow:ellipsis;display: block;font-size:18px;'>" +
								msg[i].user.name + "</div>" +
								"<div style='white-space: nowrap;overflow: hidden;text-overflow:ellipsis;display: block;color: #777777;font-size:16px;padding-top:1px'>" +
								msg[i].user.zone  + " / " +msg[i].user.city + " / " +msg[i].remark +"</div></div>" +
								"<div style='width: 30%;float: right;'>" +
								"<button class='able-btn' onclick='chooseGrap("+msg[i].uid+",\""+msg[i].pid+"\")' >就选你</button></div></div>";
							}
							$("#two").append(appendHtml);
						}
						
					});

			}
			
			function chooseGrap(uid,pid){
				var pubuid=$("#uid").val();
				if(confirm("确定选择该用户作为讲者吗?")==true){
					$.ajax({
						type:"post",
						url:"meetinggrab/updateGrabstatus",
						data:{"uid":uid,"pid":pid},
						success:function(msg){
							window.location.href="pages/weixin/meetingpub/meetingpub.jsp?uid="+pubuid;
						}
						
					});
				}
			}
		
		</script>
</head>
<body>
<form name="formMeetingInfo" action="" method="post">
<input  type="hidden" id="pid" name="pid" value="${param.pid }">
<input  type="hidden" id="uid" name="uid" value="${param.uid }">
</form>	
<div data-role="page" id="pageDetail">
	<div style="padding:0px;background-color: #4E90C7;width: 100%;height:40px;line-height:40px;font-size:18px;text-align: center;cursor: pointer;" data-role="none">
		<div style="color: white;" onclick="showMyMeetings();" id="two_tab">抢单者列表</div>
	</div>
	<div id="two" class="ui-body-d ui-content" style="padding: 0;width: 100%;">
	</div>
	
	</div>
	</body>

</html>