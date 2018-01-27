$(function(){
	//给查询按钮绑定单击事件
	$("#queryFormId").on('click',".btn-search",doQueryObjects);
	//给添加,修改绑定单击事件
	$("#queryFormId").on('click','.btn-add,.btn-update',showEditModal);
	//给启用与禁用绑定单击事件
	$("#queryFormId").on("click",".btn-invalid,.btn-valid",doSetValid);
	//执行查询
	doGetObjects();
})
//异步提交所选中的要更改状态的行,以及要修改的值
function doSetValid(){
	if($(this).hasClass("btn-valid")){
		var params={"valid":1}
	}else if($(this).hasClass("btn-invalid")){
		var params={"valid":0}
	}
	var ids="";
	var inputs=$("#tbodyId").children().children().children("input[name='id']:checked");
	inputs.each(function(){
		var id=$(this).val();
		ids+=","+id;
	})
	params.ids=ids;
	var url="project/doSetValid.do";
	$.post(url,params,function(result){
		if(result.state==1){
			doGetObjects();
		}else{
			alert(result.message)
		}
	})
}
//弹出模态框,并写入相关信息
function showEditModal(){
	var url="project/editUI.do";
	var title;
	if($(this).hasClass("btn-add")){
		title="新增项目";
	}else if($(this).hasClass("btn-update")){
		title="修改项目";
		var id=$(this).parent().parent().children().children("input").val();
		//绑定要修改的id值到编辑页面
		$("#modal-dialog").data("id",id);
	}
	//异步加载编辑页面
	$(".modal-body").load(url,function(){
		$(".modal-title").html(title);
		$("#modal-dialog").modal("show");
	});
}
//设置模糊查询首页信息
function doQueryObjects(){
	$("#pageId").data("pageCurrent",0);
	$(".first,.pre").hide();
	$(".next,.last").show() ;
	doGetObjects();
}
//执行分页查询
function doGetObjects(){
	var url="project/doQueryPeojectByPage.do";
	var current=$("#pageId").data("pageCurrent");
	var params={
			 pageCurrent:current==null?0:current,
			 name:$("#searchNameId").val(),
			 valid:$("#searchValidId").val()
	};
	$.post(url,params,function(result){
		if(result.state==1){
			showList(result.data.projectList);
			setPagination(result.data.page);
		}else{
			alert(result.message);
		}
	})
}
//设置表格信息
function showList(result){
	var tbody=$("#tbodyId");
	tbody.empty();
	for(var i in result){
		var tr=$("<tr></tr>");
		tr.append('<td><input type="checkbox" name="id" value='+result[i].id+'></td>');
		tr.append("<td>"+result[i].code+"</td>");
		tr.append("<td>"+result[i].name+"</td>");
		tr.append("<td>"+result[i].beginDate+"</td>");
		tr.append("<td>"+result[i].endDate+"</td>");
		tr.append("<td>"+(result[i].valid==1?"启用":"禁用")+"</td>");
		tr.append("<td><button type='button' class='btn btn-default btn-update'>修改</button></td>");
		tbody.append(tr);
	}
}
