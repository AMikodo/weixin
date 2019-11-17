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
		<script src="js/jquerymobile_popup.js"></script>
		<script src="https://cdn.bootcss.com/jquery.form/4.2.1/jquery.form.js"></script>
  	
	<script type="text/javascript">
		$(function(){
			
			loadTname();
		})
			function loadTname(){
				$.ajax({
					type:"post",
					url:"meetingtype/list",
					success:function(msg){
						var appendHtml="";
						for(var i=0;i<msg.length;i++){
							appendHtml+="<option value='"+msg[i].tname+"'>"+msg[i].tname+"</option>";
						}
						$("#tname").append(appendHtml);
					}
				});
		}
	
			function showPubDiv() {
				$("#two_line").css("border-top", "5px solid #4E90C7");
				//$("#two_tab").css("color","#777777");
				$("#one_line").css("border-top", "5px solid white");
				//$("#one_tab").css("color","white");
				$("#two").css("display", "none");
				$("#one").css("display", "block");
			}

			function showMyMeetings() {
				 $.mobile.loading( "show", {
					            text: "加载中",
					            textVisible: true,
					    });

				$("#two_line").css("border-top", "5px solid white");
				$("#one_line").css("border-top", "5px solid #4E90C7");
			
					$("#one").css("display", "none");
					$("#two").css("display", "block");

					$("#two").empty();
					
					var uid=$("#uid").val();
					$.ajax({
						type:"post",
						url:"meetingpub/list/"+uid,
						success:function(msg){
							var appendHtml = "<font style='padding: 10px 10px 10px 15px;display: block;color: #777777;'>您共发布了"+msg.length+"场会议</font>";
							for(var i=0;i<msg.length;i++){
								appendHtml += "<div style='width: 100%;background-color: white;margin-top: -3px;padding:10px 10px 10px 15px;display: inline-block;'>" +
								"<div style='width: 70%;float: left;' onclick='showMeetingInfo(\"" + msg[i].pid + "\")'>" +
								"<div style='white-space: nowrap;overflow: hidden;text-overflow:ellipsis;display: block;font-size:18px;'>" +
								msg[i].ptitle + "</div>" +
								"<div style='white-space: nowrap;overflow: hidden;text-overflow:ellipsis;display: block;color: #777777;font-size:16px;padding-top:1px'>" +
								msg[i].tname + " / " + msg[i].remark + "</div></div>" +
								"<div style='width: 30%;float: right;'>" ;
								if(msg[i].meetinggrab==null){
									appendHtml+="暂无人抢单";
								}else{
									if(msg[i].meetinggrab.grabstatus==0){
										appendHtml+="<button class='able-btn' onclick='checkPerson(\""+ msg[i].pid +"\")'>选择讲者</button>";
									}else{
										appendHtml+="<button class='able-btn' onclick='seePersonInfo(\""+ msg[i].pid +"\")'>查看讲者";
									}
								}
								appendHtml+="</div></div>";	
							}
							  
							$("#two").append(appendHtml);
							$.mobile.loading( "hide" );
						}
						
					});	
			}
			function showMeetingInfo(pid){
				window.location.href="meetingpub/meetingpubInfo?pid="+pid;
			}
			
			//会议发布
			function subMeetingPub(){
				var ptitle= $("#ptitle").val();
				var ptime= $("#ptime").val();
				var tname= $("#tname").val();
				if(ptitle.trim()==""){
					alert("会议名称不能为空");
					return;
				}
				if(ptime.trim()==""){
					alert("会议日期不能为空");
					return;
				}
				if(tname=="请选择"){
					alert("会议类别不能为空");
					return;
				}
			
				var formData = $("#pubForm").serialize();
				$("#ptitle").val("");
				$("#ptime").val("");
				$.ajax({
					type:"post",
					url:"meetingpub/insert",
					data:formData,
					success:function(msg){
						if(msg=="1"){
							alert("会议发布成功");
							
						}else{
							alert("发布失败");
						}
					}
				});
				
			}
			function checkPerson(pid){
				var uid=$("#uid").val();
				window.location.href="pages/weixin/meetingpub/meetinggrabPerson-list.jsp?pid="+pid+"&uid="+uid;
			}
			function seePersonInfo(pid){
				$.ajax({
					type:"post",
					url:"meetinggrab/selectGrabUserInfo",
					data:{"pid":pid},
					success:function(msg){
						alert("姓名 :"+msg.user.name+"  所在区域:"+msg.user.zone +"    联系方式:"+msg.user.telphone);
					}
				});
			}
		</script>
</head>
<body>
	
<div data-role="page" id="pageDetail">
			<div style="padding:0px;background-color: #4E90C7;width: 100%;height:40px;line-height:40px;font-size:18px;text-align: center;cursor: pointer;" data-role="none">
				<div style="width: 50%;float: left;color: white;" onclick="showPubDiv();" id="one_tab">
					发布
					<div style="border-right: 1px solid white;float: right;margin-top: 10px;height: 20px;"></div>
				</div>
				<div style="width: 50%;float: left;color: white;" onclick="showMyMeetings();" id="two_tab">我的会议</div>
				<div style="border-top:5px solid white;width: 50%;float: left;" id="one_line"></div>
				<div style="border-top:5px solid #4E90C7;width: 50%;float: left;" id="two_line"></div>
			</div>
			<div id="one" class="ui-body-d ui-content" style="padding:0;display: block;width: 100%;">
				<font style="padding:10px 10px 10px 15px;display: block;color: #777777;">请填写会议相关信息</font>
				<div style="width: 100%;background-color: white;padding:10px 0 10px 0;">
					
					<form id="pubForm" method="post" action="meetingpub/insert" name="pubForm">
						<input type="hidden" name="uid" id="uid" value="${param.uid}"/>
						
						<div style="padding-right:15px;padding-left:15px">
							<label for="pname" class="font-label">会议名称:</label>
							<input name="ptitle" id="ptitle" placeholder="请输入会议名称" ></input>
						</div>						
						<div  style="padding-right:15px;padding-left:15px">			
								<label for="ptime" class="font-label">会议日期</label>
	      					   <input type="datetime-local" name="ptime" id="ptime" />							
						</div>
						<div style="padding-right:15px;padding-left:15px">
							<label for="tname" class="font-label">类别：</label>
							<select name="tname" id="tname">
								<option>请选择</option>
							</select>
						</div>
						<div style="padding-right:15px;padding-left:15px">
							<label for="zone" class="font-label">地区：</label>
							<select name="zone" id="zone">
							<option value="全国">全国</option>
							<option value="全国">东区</option>
							<option value="全国">西区</option>
							<option value="全国">南区</option>
							<option value="全国">北区</option>
							</select>
						</div>
						<div style="padding-right:15px;padding-left:15px">
							<label for="premark" class="font-label">备注(选填，100字以内)</label>
							<textarea name="remark" id="remark" placeholder="请输入会议备注" maxlength="100" class="font-blue input-lightblue" style="box-shadow: none;"></textarea>
						</div>
						<div style="padding-right:15px;padding-left:15px">
							<input type="button" value="发布会议"  onclick="subMeetingPub()" id="btnId" />
						</div>
					</form>
				</div>
			</div>
	<div id="two" class="ui-body-d ui-content" style="padding: 0;display: none;width: 100%;">
		
	</div>
	</div>
	</body>

</html>