$(function(){
	//给保存按钮绑定单击事件
	$("#modal-dialog").on("click",".ok",doSaveOrUpdateProject);
	//当模态框完全隐藏时执行的事件
	$("#modal-dialog").on("hidden.bs.modal",function(){
		//移除绑定的id,以及取消单击事件
		$(this).removeData("id").off("click",".ok");
	});
	//判断是否为修改页面,并执行初始化数据
	doInitData();
})
//初始化修改页面数据
var id=0;
function doInitData(){
	id=$("#modal-dialog").data("id");
	if(id){doSetModal(id)};
}
//保存或修改数据
function doSaveOrUpdateProject(){
	//验证编辑页面数据是否符合要求
	if(!$("#editFormId").valid())return;
	var params=doGetProjectData();
	var addUrl="project/doAddProject.do";
	var updateUrl="project/doUpdateProject.do";
	if(id){
		params.id=id;
	}
	//判断执行的是增加还是修改
	var url=id?updateUrl:addUrl;
	$.post(url,params,function(result){
		if(result.state==1){
			doResult();
		}else if(result.state==2){
			alert(result.message);
		}
	})
}
//结果的回显
function doResult(){
	$("#modal-dialog").modal("hide");
	doGetObjects();
}
//获得增加页面所输入的值
function doGetProjectData(){
	var params={
		name:$("#nameId").val(),
		code:$("#codeId").val(),
		beginDate:$("#beginDateId").val(),
		endDate:$("#endDateId").val(),
		valid:$("input[name='valid']:checked").val(),
		note:$("#noteId").val()
	}
	return params;
}
//根据id设置修改页面所要修改的值
function doSetModal(id){
	var url="project/doGetProjectById.do";
	var param={
			"id":id
	}
	$.post(url,param,function(result){
		if(result.state==1){
			doInitEditFormData(result.data);
		}else{
			alert(result.message)
		}
	})
}
//初始化修改页面(注入所要修改的值)
function doInitEditFormData(obj){
	$("#nameId").val(obj.name);
	$("#codeId").val(obj.code);
	$("#beginDateId").val(obj.beginDate);
	$("#endDateId").val(obj.endDate);
	$("#noteId").html(obj.note);
	$("#editFormId input[name='valid']").each(function(){
		if($(this).val()==obj.valid){
		   $(this).prop("checked",true)
		}
	});
}