$(document).ready(function(){
	$(".btn-search").click(doQueryByCondition);
	$("#formHead")
	.on("click",".btn-add,.btn-update",doAddOrUpdateRole)
	.on("click",".btn-delete",doDeleteRole)	
	doGetObjects();
});
/**
 * 新增或修改角色信息
 */
function doAddOrUpdateRole(){
	$(".content").removeData("type");
	if($(this).hasClass("btn-add")){
		$(".content").data("type","");
	}
	if($(this).hasClass("btn-update")){
		var id=getCheckId();
		if(!id){
			alert('请选择要操作的选项！');
			return;
		}
		$(".content").data("type",id);
	}
	var url="role/editUI.do?t"+Math.random(1000);
	$(".content").load(url);
}
/**
 * 删除角色
 */
function doDeleteRole(){
	var url="role/doDeleteRole.do";
	var id=getCheckId();
	if(!id){
		alert('请选择要操作的选项！');
		return;
	}
	$.post(url,{id:id},function(result){
		if(result.state==1){
			doGetObjects();
		}else{
			alert(result.message)
		}
	})
}

function getCheckId(){
	var id="";
	var ids=$("#tbodyId").children().children().children("input[name='id']:checked");
	ids.each(function(){
		id=$(this).val();
	})
	return id;
}
/**
 *条件查询
 */
function doQueryByCondition(){
	$("#pageId").data("pageCurrent",0);
	doGetObjects();
}
/**
 * 分页查询
 */
function doGetObjects(){
	var url="role/queryRolesByPages.do";
	var pageCurrent=$("#pageId").data("pageCurrent");
	var params={
			name:$("#roleName").val(),
			pageCurrent:pageCurrent==null ? 0 : pageCurrent 
	}
	$.post(url,params,function(result){
		if(result.state==1){
			doSetRoleTable(result.data.List);
			setPagination(result.data.Page)
		}else{
			alert(result.message);
		}
	})
}
/**
 * 设置表格
 * @param data
 */
function doSetRoleTable(data){
	var tbody=$("#tbodyId");
	tbody.empty();
	for(var i in data){
		var tr=$("<tr></tr>");
		var tds=$("<td><input type='radio'name='id' value='"+data[i].id+"'/></td>" +
				  "<td>"+data[i].id+"</td>"+
				  "<td>"+data[i].name+"</td>"+
				  "<td>"+data[i].note+"</td>")
		tr.append(tds)
		tbody.append(tr)
	}
}