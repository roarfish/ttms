/**
 * 设置树结构
 */
var ztree;
var setting={
		data:{
			simpleData:{
				enable:true,
				idKey:"id",
				pIdKey:"parentId",
				rootPId:null
			}
		},
		check:{
			enable:true,
			oncheckInherit:true
		}
}
/**
 * 初始化页面
 */
$(function() {
	setBtnVal();
	$(".btn_save").click(getAddOrUpdate);
	$(".btn_return").click(doBack);
})
/**
 * 判断加载页面(添加or修改)
 */
function setBtnVal(){
	var id=$(".content").data("type");
	if(!id){
		$("#editTitle").text("添加角色");
		$(".btn_save").val("保存");
	}else{
		$("#editTitle").text("修改角色");
		$(".btn_save").val("修改");
		doGetViewData(id)
	}
	doFindZtreeNodes();
}
/**
 * 回显修改数据
 * @param id
 */
function doGetViewData(id){
	var url="role/doQueryRoleById.do";
	$.post(url,{"id":id},function(result){
		if(result.state==1){
			showViewData(result.data)
		}else{
			alert(result.message)
		}
	})
}
/**
 * 获得需要修改的数据
 * @param data
 */
function showViewData(data){
	$("#roleName").val(data.role.name);
	$("#note").val(data.role.note);
	console.log(data.permissions);
	doFindZtreeNodes(data.permissions);
}
/**
 * 进行新增or修改
 */
function getAddOrUpdate(){
	var params=getAddOrUpdateParams();
	var addUrl="role/doAddRole.do";
	var updateUrl="role/doUpdateRole.do";
	var id=$(".content").data("type");
	var url=id ? updateUrl : addUrl;
	if(id){
		params.id=id
	}
	$.post(url,params,function(result){
		if(result.state==1){
			doBack();
			doGetObjects();
		}else{
			alert(result.message)
		}
	})
}
/**
 * 获得需要新增or修改的数据
 */
function getAddOrUpdateParams(){
	var params={
			name:$("#roleName").val(),
			note:$("#note").val(),
			menuIdList:getSelectedNodes()
	}
	return params;
}
/**
 * 获得选中的节点数据(权限)
 * @returns {String}
 */
function getSelectedNodes(){
	var nodes=ztree.getCheckedNodes(true);
	var id="";
	for(var i in nodes){
		id+=nodes[i].id+',';
	}
	return id;
}
/**
 * 初始化树结构
 * @param permissions
 */
function doFindZtreeNodes(permissions){
	var url = 'role/doFindZtreeNodes.do';
	$.getJSON(url,function(result){
		if(result.state==1){
			ztree=$.fn.zTree.init($("#menuTree"),setting,result.data);
			if(permissions){
				ztree.expandAll(true);
				//勾选角色所拥有的菜单
				var menuIds = permissions;
				for(var i=0; i<menuIds.length; i++) {
					var node = ztree.getNodeByParam("id",menuIds[i]);
					ztree.checkNode(node, true, false);
				}
			}
		}else{
			alert(result.message);
		}
	})
}
/**
 * 返回按钮
 */
function doBack(){
	var url="role/listUI.do?t="+Math.random(1000);
	$(".content").load(url);
}