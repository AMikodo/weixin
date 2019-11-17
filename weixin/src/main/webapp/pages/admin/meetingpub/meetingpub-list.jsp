<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%> 
<!DOCTYPE html>
<html>
<head>
<base href="<%=basePath%>">
<meta charset="UTF-8">
<meta name="renderer" content="webkit|ie-comp|ie-stand">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<meta name="viewport" content="width=device-width,initial-scale=1,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no" />
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="stylesheet" type="text/css" href="js/H-ui/static/h-ui/css/H-ui.min.css" />
<link rel="stylesheet" type="text/css" href="js/H-ui/static/h-ui.admin/css/H-ui.admin.css" />
<link rel="stylesheet" type="text/css" href="js/H-ui/lib/Hui-iconfont/1.0.8/iconfont.css" />
<link rel="stylesheet" type="text/css" href="js/H-ui/static/h-ui.admin/skin/default/skin.css" id="skin" />
<link rel="stylesheet" type="text/css" href="js/H-ui/static/h-ui.admin/css/style.css" />

<title>发单列表</title>
</head>
<body>
<nav class="breadcrumb">
	<i class="Hui-iconfont">
		&#xe67f;
	</i> 
	首页 <span class="c-gray en">&gt;</span> 
	后台管理 <span class="c-gray en">&gt;</span>
	 发单管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" >
	 <i class="Hui-iconfont">&#xe68f;</i></a>
 </nav>
<div class="page-container">
	<form action="meetingpubManager/list" method="post" name="meetinggrabFrom" id="meetinggrabFrom">
		<div class="text-c">
			<button onclick="removeIframe()" class="btn btn-primary radius">关闭选项卡</button>
		 <span class="select-box inline">
			<select name="status" class="select">
				<option value="-1">全部分类</option>
				<option value="1" <c:if test="${meetingpubForm.status==1 }">selected="selected"</c:if>  >有效</option>
				<option value="0" <c:if test="${meetingpubForm.status==0 }">selected="selected"</c:if>>无效</option>
			</select>
			</span>
			
			<input type="text" name="pcode" id="" value="${meetingpubForm.pcode }"  placeholder="订单编号" style="width:150px" class="input-text">
			<input type="text" name="tname" id="" value="${meetingpubForm.tname }"  placeholder=" 课题类别" style="width:150px" class="input-text">
			<input type="text" name="user.name" id="" value="${meetingpubForm.user.name }"  placeholder=" 用户名称" style="width:150px" class="input-text">
			<button name="" id="" class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe665;</i> 查询</button>
			 <span class="select-box inline">
			<select name="meetinggrab.grabstatus" class="select" onchange="selectByGrabstatus()" style="width:150px">
				<option value="-1">全部订单</option>
				<option value="0" <c:if test="${meetingpubForm.meetinggrab.grabstatus==0 }">selected="selected"</c:if> >未匹配</option>
				<option value="1" <c:if test="${meetingpubForm.meetinggrab.grabstatus==1 }">selected="selected"</c:if> >匹配成功</option>
			</select>
			</span>
			</div>
		</form>	
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-20">
	 <span class="l">
		 <a href="javascript:;" onclick="deletebatch()" class="btn btn-danger radius">
		 	<i class="Hui-iconfont">&#xe6e2;</i> 批量删除
		 </a> 
	 </span>
	 <span class="r">共有数据：<strong>${meetingpubList.size()}</strong> 条</span> </div>
	<div class="mt-20">
		<table class="table table-border table-bordered table-bg table-hover table-sort">
			<thead>
				<tr class="text-c">
					<th width="25"><input type="checkbox" name="check-ids" value=""></th>
					<th width="60">主键</th>
					<th width="100">订单编号</th>
					<th width="80">会议时间</th>
					<th width="80">会议名称</th>
					<th width="70">会议类别</th>
					<th width="70">抢单者地区</th>
					<th width="60">备注</th>
					<th width="50">用户ID</th>
					<th >创建时间</th>
					<th width="60">状态</th>
					<th width="100">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${meetingpubList}" var="l">
				<tr class="text-c">
					<td><input type="checkbox" value="${l.pid}" name="ids"></td>
					<td>${l.pid}</td>
					<td class="text-l">
					<u style="cursor:pointer" class="text-primary" 
					onClick="article_edit('查看','article-zhang.html','10002')" title="查看">
					${l.pcode}</u></td>
					<td>${l.ptime}</td>
					<td>${l.ptitle}</td>
					<td>${l.tname}</td>
					<td>${l.zone}</td>
					<td>${l.remark}</td>
					<td>${l.uid}</td>
					<td>
						<fmt:formatDate value="${l.createdate}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td class="td-status">
						<c:if test="${l.status==1}">
							<span class="label label-success radius">有效</span>
						</c:if>
						<c:if test="${l.status==0}">
							<span class="label label-danger radius">无效</span>
						</c:if>
					</td>
					<td class="f-14 td-manage">
						<c:if test="${l.status==1}">
							<a style="text-decoration:none" onClick="meetingpub_stop('${l.pid}',this)" href="javascript:;" title="修改为无效">
							<i class="Hui-iconfont">&#xe6de;
							</i>
						</a>
						</c:if>
						<c:if test="${l.status==0}">
							<a style="text-decoration:none" onClick="meetingpub_start('${l.pid}',this)" href="javascript:;" title="修改为有效">
							<i class="Hui-iconfont">&#xe6dc;
							</i>
						</a>
						</c:if>
						 <a style="text-decoration:none" class="ml-5" onClick="meetingpub_edit('修改','meetingpubManager/select?pid=${l.pid}','10001')" href="javascript:;" title="修改">
						 	<i class="Hui-iconfont">&#xe6df;</i>
						 </a>       													
						 <a style="text-decoration:none" class="ml-5" onClick="meetingpub_del('${l.pid}',this)" href="javascript:;" title="删除">
						 	<i class="Hui-iconfont">&#xe6e2;</i>
						 </a>
					 </td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</div>
<!--_footer 作为公共模版分离出去-->
<script type="text/javascript" src="js/H-ui/lib/jquery/1.9.1/jquery.min.js"></script> 
<script type="text/javascript" src="js/H-ui/lib/layer/2.4/layer.js"></script>
<script type="text/javascript" src="js/H-ui/static/h-ui/js/H-ui.min.js"></script> 
<script type="text/javascript" src="js/H-ui/static/h-ui.admin/js/H-ui.admin.js"></script> <!--/_footer 作为公共模版分离出去-->

<!--请在下方写此页面业务相关的脚本-->
<script type="text/javascript" src="js/H-ui/lib/My97DatePicker/4.8/WdatePicker.js"></script> 
<script type="text/javascript" src="js/H-ui/lib/datatables/1.10.0/jquery.dataTables.min.js"></script> 
<script type="text/javascript" src="js/H-ui/lib/laypage/1.2/laypage.js"></script>
<script type="text/javascript">
$('.table-sort').dataTable({
	"aaSorting": [[ 1, "desc" ]],//默认第几个排序
	"bStateSave": true,//状态保存
	"pading":false,
	"aoColumnDefs": [
	  //{"bVisible": false, "aTargets": [ 3 ]} //控制列的隐藏显示
	  {"orderable":false,"aTargets":[0,6]}// 不参与排序的列
	]
});

function selectByGrabstatus(){
	document.meetinggrabFrom.submit();
	
}

/*资讯-编辑*/
function meetingpub_edit(title,url,id,w,h){
	var index = layer.open({
		type: 2,
		title: title,
		content: url
	});
	layer.full(index);
}
/*批量删除*/
function deletebatch(){
	
	layer.confirm('确认要删除吗？',function(index){
		var objs=document.getElementsByName("ids");
		var ids="";
		for(var i=0;i<objs.length;i++){
			if(objs[i].checked==true){
				ids+=objs[i].value+",";
			}
		}
		if(ids==""){
			layer.msg('请选择要删除的数据!',{icon:2,time:2000});
		}
		$.ajax({
			type: 'POST',
			url: 'meetingpubManager/deletebatch/'+ids,
			dataType: 'json',
			success: function(data){
				location.replace(location.href);
				layer.msg('已删除!',{icon:1,time:1000});
			},
			error:function(data) {
				console.log(data.msg);
			},
		});		
	});
	
}

/*角色-删除*/
function meetingpub_del(pid,obj){
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			type: 'POST',
			url: 'meetingpubManager/delete/'+pid,
			dataType: 'json',
			success: function(data){
				$(obj).parents("tr").remove();
				
				layer.msg('已删除!',{icon:1,time:1000});
				location.replace(location.href);
			},
			error:function(data) {
				console.log(data.msg);
			},
		});		
	});
}

/*资讯-审核*/
function article_shenhe(obj,id){
	layer.confirm('审核文章？', {
		btn: ['通过','不通过','取消'], 
		shade: false,
		closeBtn: 0
	},
	function(){
		$(obj).parents("tr").find(".td-manage").prepend('<a class="c-primary" onClick="article_start(this,id)" href="javascript:;" title="申请上线">申请上线</a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">已发布</span>');
		$(obj).remove();
		layer.msg('已发布', {icon:6,time:1000});
	},
	function(){
		$(obj).parents("tr").find(".td-manage").prepend('<a class="c-primary" onClick="article_shenqing(this,id)" href="javascript:;" title="申请上线">申请上线</a>');
		$(obj).parents("tr").find(".td-status").html('<span class="label label-danger radius">未通过</span>');
		$(obj).remove();
    	layer.msg('未通过', {icon:5,time:1000});
	});	
}
/*角色-下架*/
function meetingpub_stop(pid,obj){
	layer.confirm('确认要修改为无效吗？',function(index){
		$.ajax({
			type:"post",
			url:"meetingpubManager/updateStatus",
			data:{"status":0,"pid":pid},
			success:function(){
				$(obj).parents("tr").find(".td-manage").prepend("<a style='text-decoration:none' onClick='meetingpub_start(\""+pid+"\",this)' href='javascript:;' title='修改为有效'><i class='Hui-iconfont'>&#xe6de;</i></a>");
				$(obj).parents("tr").find(".td-status").html('<span class="label label-danger radius">无效</span>');
				$(obj).remove();
				layer.msg('已修改!',{icon: 5,time:1000});
			}
		});
		
	});
}

/*角色-上架*/
function meetingpub_start(pid,obj){
	layer.confirm('确认要修改为有效吗？',function(index){
		$.ajax({
			type:"post",
			url:"meetingpubManager/updateStatus",
			data:{"status":1,"pid":pid},
			success:function(){
		$(obj).parents("tr").find(".td-manage").prepend("<a style='text-decoration:none' onClick='meetingpub_stop(\""+pid+"\",this)' href='javascript:;' title='修改为无效'><i class='Hui-iconfont'>&#xe6de;</i></a>");
		$(obj).parents("tr").find(".td-status").html('<span class="label label-success radius">有效</span>');
		$(obj).remove();
		layer.msg('已修改!',{icon: 6,time:1000});
			}
		});
	});
}
/*资讯-申请上线*/
function article_shenqing(obj,id){
	$(obj).parents("tr").find(".td-status").html('<span class="label label-default radius">待审核</span>');
	$(obj).parents("tr").find(".td-manage").html("");
	layer.msg('已提交申请，耐心等待审核!', {icon: 1,time:2000});
}

</script> 
</body>
</html>