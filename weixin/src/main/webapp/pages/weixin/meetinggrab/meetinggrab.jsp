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
		<script type="text/javascript">
		
		$(function(){
			loadMeetingpubTname();
			loadMeetingpubList("-1");
		})
		
			function loadMeetingpubTname(){
					$.ajax({
						type:"post",
						url:"meetingtype/list",
						success:function(msg){
							var appendHtml="";
							for(var i=0;i<msg.length;i++){
								appendHtml+="<option value='"+msg[i].tname+"'>"+msg[i].tname+"</option>";
							}
							$("#selectStatus").append(appendHtml);
						}
					});
			}
		
		function loadMeetingpubList(tname){
			 $.mobile.loading( "show", {
				            text: "加载中",
				            textVisible: true,
				    });
			var uid=$("#uid").val();
			$.ajax({
				type:"post",
				url:"meetingpub/selectListByZoneAndStatus",
				data:{"uid":uid,"tname":tname},
				success:function(msg){
					$("#two_line").css("border-top", "5px solid #4E90C7");
					//$("#two_tab").css("color","#777777");
					$("#one_line").css("border-top", "5px solid white");
					//$("#one_tab").css("color","white");
					$("#two").css("display", "none");
					$("#one").css("display", "block");
					var appendHtml = "";
					$("#grabDiv").empty();
					$("#grabNum").empty();
					$("#grabNum").append(msg.length);
					for(var i=0;i<msg.length;i++){
						appendHtml += "<div style='width: 100%;background-color: white;margin-top: -3px;padding:10px 10px 10px 15px;display: inline-block;'>" +
						"<div style='width: 70%;float: left;' onclick='showMeetingInfo(\"" + msg[i].pid + "\");'>" +
						"<div style='white-space: nowrap;overflow: hidden;text-overflow:ellipsis;display: block;font-size:18px;'>" +
						msg[i].ptitle + "</div>" +
						"<div style='white-space: nowrap;overflow: hidden;text-overflow:ellipsis;display: block;color: #777777;font-size:16px;padding-top:1px'>" +
						"会议时间"+msg[i].ptime + " / " +msg[i].tname + " / " + msg[i].remark + "</div></div>" +
						"<div style='width: 30%;float: right;'>" +
						"<button class='able-btn' onclick='toGrabPage("+uid+",\""+msg[i].pid+"\")' >点击抢单</button></div></div>";	
					}
					var length=msg.length;
					$("#grabDiv").append(appendHtml);
					$("#grabNum").val("length");
					$.mobile.loading( "hide" );
				}
				
			});
			
		}
		
		function showPubDiv(){
			var tname=$("#selectStatus").val();
			loadMeetingpubList(tname);
		}
		
		function toGrabPage(uid,pid){
			$("#pid").val(pid);
			document.meetinggrabForm.submit();
		}
			
		function showMyMeetings() {
			
			 $.mobile.loading( "show", {
				            text: "加载中",
				            textVisible: true,
				    });
				var uid=$("#uid").val();
				$("#two_line").css("border-top", "5px solid white");
				$("#one_line").css("border-top", "5px solid #4E90C7");
			
					$("#one").css("display", "none");
					$("#two").css("display", "block");

					$("#two").empty();
					$.ajax({
						type:"post",
						url:"meetinggrab/mylist/"+uid,
						success:function(msg){
							var appendHtml = "<font style='padding: 10px 10px 10px 15px;display: block;color: #777777;'>您共抢了"+msg.length+"场会议</font>";
							for(var i=0;i<msg.length;i++){
								var grabstatus=msg[i].grabstatus;
								appendHtml += "<div style='width: 100%;background-color: white;margin-top: -3px;padding:10px 10px 10px 15px;display: inline-block;'>" +
								"<div style='width: 70%;float: left;' >" +
								"<div style='white-space: nowrap;overflow: hidden;text-overflow:ellipsis;display: block;font-size:18px;'>" +
								msg[i].meetingpub.ptitle + "</div>" +
								"<div style='white-space: nowrap;overflow: hidden;text-overflow:ellipsis;display: block;color: #777777;font-size:16px;padding-top:1px'>" +
								msg[i].meetingpub.pcode + " / " + msg[i].meetingpub.tname + "</div></div>" +
								"<div style='width: 30%;float: right;'>" 
								if(grabstatus==0){
									appendHtml+="未审批";
								}else if(grabstatus==1){
									appendHtml+="<button class='able-btn' >已抢单</button>"
								}else{
									appendHtml+="抢单失败";
								}
								appendHtml+="</div></div>";
								}
								$("#two").append(appendHtml);
								$.mobile.loading( "hide" );
							}
					});
			}
		
		</script>	
	</head>

	<body>
	<form action="meetinggrab/tomeetinggrab" method="post" name="meetinggrabForm">
	
	<input id="uid" type="hidden" value="${param.uid }" name="uid">
	<input id="pid" type="hidden" name="pid">
	</form>
	<div data-role="page" id="pageDetail">
			<div style="padding:0px;background-color: #4E90C7;width: 100%;height:40px;line-height:40px;font-size:18px;text-align: center;cursor: pointer;" data-role="none">
				<div style="width: 50%;float: left;color: white;" onclick="showPubDiv();" id="one_tab">
					抢单
					<div style="border-right: 1px solid white;float: right;margin-top: 10px;height: 20px;"></div>
				</div>
				<div style="width: 50%;float: left;color: white;" onclick="showMyMeetings();" id="two_tab">我的抢单</div>
				<div style="border-top:5px solid white;width: 50%;float: left;" id="one_line"></div>
				<div style="border-top:5px solid #4E90C7;width: 50%;float: left;" id="two_line"></div>
			</div>
	<div id="one" class="ui-body-d ui-content" style="padding:0;display: block;width: 100%;">
		<div style="padding:0 0 0 15px;display: block;width: 55%;float: left;line-height: 60px;color: #777777;">
			可参与<font id="grabNum"></font>场会议的抢单
		</div>
		<div style="width: calc(45% - 15px);float: right;">
			<select id="selectStatus" onchange="loadMeetingpubList(this.value)" >
				<option value="-1" >可抢单</option>
			</select>
		</div>
		
		<div id="grabDiv" style="width: 100%;float: left;">
		
		
		</div>
	</div>
	<div id="two" class="ui-body-d ui-content" style="padding: 0;display: none;width: 100%;">
		
	</div>
	</div>		

	
	
			
	
	</body>

</html>