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

<title>抢单列表</title>
</head>
<body>
<nav class="breadcrumb">
	<i class="Hui-iconfont">
		&#xe67f;
	</i> 
	首页 <span class="c-gray en">&gt;</span> 
	后台管理 <span class="c-gray en">&gt;</span>
	 抢单管理 <a class="btn btn-success radius r" style="line-height:1.6em;margin-top:3px" href="javascript:location.replace(location.href);" title="刷新" >
	 <i class="Hui-iconfont">&#xe68f;</i></a>
 </nav>
<div class="page-container">
	<form action="meetinggrabmManager/list" method="post" >
		<div class="text-c">
			<button onclick="removeIframe()" class="btn btn-primary radius">关闭选项卡</button>
		 <span class="select-box inline">
			<select name="meetingpub.tname" class="select">
			<option value="课题分类" <c:if test="${meetinggrabForm.meetingpub.tname==课题分类}">selected="selected"</c:if>>课题分类</option>
				<option value="数据库" <c:if test="${meetinggrabForm.meetingpub.tname==数据库}">selected="selected"</c:if>>数据库</option>
				
				<option value="java" <c:if test="${meetinggrabForm.meetingpub.tname==java}">selected="selected"</c:if>  >java</option>
				<option value="H5" <c:if test="${meetinggrabForm.meetingpub.tname==H5}">selected="selected"</c:if>>H5</option>
				
				
			</select>
			</span>
			<input type="text" name="user.name" id="" value="${meetinggrabForm.user.name }"  placeholder="抢单用户姓名" style="width:150px" class="input-text">
			<button name="" id="" class="btn btn-success" type="submit"><i class="Hui-iconfont">&#xe665;</i> 查询</button>
			</div>
		</form>	
	</div>
	<div class="cl pd-5 bg-1 bk-gray mt-20">
	 <span class="l">
		 <a href="javascript:;" onclick="deletebatch()" class="btn btn-danger radius">
		 	<i class="Hui-iconfont">&#xe6e2;</i> 批量删除
		 </a> 
	 </span>
	 <span class="r">共有数据：<strong>${meetinggrabList.size()}</strong> 条</span> </div>
	<div class="mt-20">
		<table class="table table-border table-bordered table-bg table-hover table-sort">
			<thead>
				<tr class="text-c">
					<th width="25"><input type="checkbox" name="check-ids" value=""></th>
					<th width="60">主键</th>
					<th width="100">发单编号</th>
					<th width="80">发单标题</th>
					<th width="60">备注</th>
					<th width="50">用户ID</th>
					<th >创建时间</th>
					<th width="100">匹配时间</th>
					<th width="60">状态</th>
					<th width="100">操作</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${meetinggrabList}" var="l">
				<tr class="text-c">
					<td><input type="checkbox" value="${l.gid}" name="ids"></td>
					<td>${l.gid}</td>
					<td class="text-l">
					<u style="cursor:pointer" class="text-primary" 
					onClick="article_edit('查看','article-zhang.html','10002')" title="查看">
					${l.pid}</u></td>
					<td>${l.meetingpub.ptitle}</td>
					<td>${l.remark}</td>
					<td>${l.uid}</td>
					<td>
						<fmt:formatDate value="${l.createdate}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td>
						<fmt:formatDate value="${l.grabdate}" pattern="yyyy-MM-dd HH:mm:ss"/>
					</td>
					<td class="td-status">
						<c:if test="${l.grabstatus==0}">
							<span class="label label-primary radius">选择讲者中</span>
						</c:if>
						<c:if test="${l.grabstatus==1}">
							<span class="label label-success radius">此人为讲者</span>
						</c:if>
						<c:if test="${l.grabstatus==3}">
							<span class="label label-danger radius">已选择其他人为讲者</span>
						</c:if>
					</td>
					<td class="f-14 td-manage">
						 <a style="text-decoration:none" class="ml-5" onClick="meetinggrab_edit('${l.pid}')" href="javascript:;" title="修改">
						 	<i class="Hui-iconfont">&#xe6df;</i>
						 </a>       													
						 <a style="text-decoration:none" class="ml-5" onClick="meetinggrab_del('${l.gid}',this)" href="javascript:;" title="删除">
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


/*资讯-编辑*/
function meetinggrab_edit(pid){
	layer.confirm('确定要重新抢单吗? 重新选择讲者吗',function(index){
		alert(pid);
		$.ajax({
			type:"post",
			url:"meetinggrabmManager/updateGrabstatus",
			data:{"pid":pid},
			success:function(msg){
				location.replace(location.href);
			}
			
		});
		
		
		
	});

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
			url: 'meetinggrabmManager/deletebatch/'+ids,
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


function meetinggrab_del(gid,obj){
	layer.confirm('确认要删除吗？',function(index){
		$.ajax({
			type: 'POST',
			url: 'meetinggrabmManager/delete/'+gid,
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
/*资讯-申请上线*/
function article_shenqing(obj,id){
	$(obj).parents("tr").find(".td-status").html('<span class="label label-default radius">待审核</span>');
	$(obj).parents("tr").find(".td-manage").html("");
	layer.msg('已提交申请，耐心等待审核!', {icon: 1,time:2000});
}

</script> 
</body>
</html>