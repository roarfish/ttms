var columns = [
{
field : 'selectItem',
radio : true
},
{
title : '分类id',
field : 'id',
visible : false,
align : 'center',
valign : 'middle',
width : '80px'
},
{
title : '分类名称',
field : 'name',
align : 'center',
valign : 'middle',
sortable : true,
width : '180px'
},
{
title : '上级分类',
field : 'parentName',
align : 'center',
valign : 'middle',
sortable : true,
width : '180px'
},
{
title : '排序号',
field : 'sort',
align : 'center',
valign : 'middle',
sortable : true,
width : '100px'
}];

$(document).ready(function(){
	$("#formHead")
	.on("click",".btn-delete",doDeleteObject)
	.on("click",".btn-add,.btn-update",doLoadEditPage);
	doGetObjects();
});

function doGetObjects(){
	 var tableId="typeTable";//对象type_list.jsp中的table id
	 var url="type/doFindObjectsForTree.do";
	 var table=new TreeTable(tableId,url,columns);
	 table.setIdField("id");//设置选中记录的返回id()
	 table.setCodeField("id");//设置级联关系的id
	 table.setParentCodeField("parentId");//设置级联关系中的parentId
	 table.setExpandColumn(2);//设置默认展开列
	 table.setExpandAll(false);//设置默认不展开
	 table.init();//初始化对象树(底层会发起异步请求)
}

function doGetId(){
	var ids=$("#typeTable").bootstrapTreeTable("getSelections");
	var flag=true;
	if(ids.length==0){
		return flag=false;
	}
	return ids[0].id;
}

function doDeleteObject(){
	var id=doGetId();
	if(!id){
		alert("请选择一行进行操作!");
		return;
	}
	var url="type/doDeleteObject.do";
	$.post(url,{"id":id},function(result){
		if(result.state==1){
			doGetObjects();
		}else{
			alert(result.message);
		}
	})
}

function doLoadEditPage(){
	if($(this).hasClass("btn-add")){
		var title="添加分类信息";
	}else if($(this).hasClass("btn-update")){
		var title="修改分类信息";
		var id=doGetId();
		if(!id){
			alert("请选择一行进行操作!");
			return;
		}
		$(".content").data("id",id);
	}
	var url="type/editUI.do";
	$(".content").load(url,function(){
		$(".container .active").html(title);
	});
}