var zTree;
var setting = {
		data : {   
			simpleData : {
				enable : true,
				idKey : "id",  //节点数据中保存唯一标识的属性名称
				pIdKey : "parentId",  //节点数据中保存其父节点唯一标识的属性名称
				rootPId : null  //根节点id
			}
		}
}

$(function(){
	$("#editTypeForm")
	.on("click",".btn-primary",doSaveOrUpdateType)
	.on("click",".btn-warning",doBack)
	.on("click","#parentNameId",showEditTree);
	$("#typeLayer")
	.on("click",".btn-confirm",setSelectedTreeNode)
	.on("click",".btn-cancle",doHideTree);
	
	var id=$(".content").data("id");
	if(id)doInitTypeData(id);
})

function doInitTypeData(id){
	var url="type/doFindTypeById.do";
	$.post(url,{"id":id},function(result){
		if(result.state==1){
			initTypeData(result.data[0]);
		}else{
			alert(result.message)
		}
	})
}

function initTypeData(obj){
	$("#typeNameId").val(obj.name);
	$("#parentNameId").val(obj.parentName);
	$("#editTypeForm").data("parentId",obj.parentId);
	$("#typeSortId").val(obj.sort);
	$("#typeNoteId").html(obj.note);
}

function doSaveOrUpdateType(){
	var id=$(".content").data("id");
	var params=doGetAllParams();
	var saveUrl="type/doSaveType.do";
	var updateUrl="type/doUpdateType.do";
	var url=id?updateUrl:saveUrl;
	if(id){params.id=id}
	$.post(url,params,function(result){
		if(result.state==1){
			doBack();
		}else{
			alert(result.message)
		}
	})
}

function doBack(){
	$(".dynamicClear").val('');
	$(".content").removeData("id");
	$("#editTypeForm").removeData("parentId");
	var url="type/listUI.do?t="+Math.random(1000);
	$(".content").load(url);
}

function doGetAllParams(){
	var params={
			"name":$("#typeNameId").val(),
			"sort":$("#typeSortId").val(),
			"note":$("#typeNoteId").html(),
			"parentId":$("#editTypeForm").data("parentId")
	}
	return params;
}

function setSelectedTreeNode(){
	var selectedNodes=zTree.getSelectedNodes();
	var node=selectedNodes[0];
	console.log(selectedNodes)
	$("#parentNameId").val(node.name);
	$("#editTypeForm").data("parentId",node.id);
	doHideTree();
}

function doHideTree(){
	$("#typeLayer").css("display","none");
}

function showEditTree(){
	$("#typeLayer").css("display","block");
	var url="type/doFindTreeObjects.do";
	$.getJSON(url,function(result){
		if(result.state==1){
			zTree=$.fn.zTree.init($("#typeTree"),setting,result.data);
		}else{
			alert(result.message)
		}
	})
}