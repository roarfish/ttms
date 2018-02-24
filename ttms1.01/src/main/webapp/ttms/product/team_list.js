$(function(){
	$("#queryFormId").on("click",".btn-search",doQueryTeamsForCondition)
	$("#queryFormId").on("click",".btn-invalid,.btn-valid",doSetValid)
	$("#queryFormId").on("click",".btn-add,.btn-update",showEditWindow)
	doGetObjects();
	var idname="#searchPrjId";
	doSetProjects(idname);
})

function showEditWindow(){
	if($(this).hasClass("btn-add")){
		title="添加团信息";
	}
	if($(this).hasClass("btn-update")){
		title="修改团信息";
		var id=$(this).parent().parent().children().children("input").val();
		$("#modal-dialog").data("id",id);
	}
	var url="team/editUI.do";
	$(".modal-body").load(url,function(){
		$("#myModalLabel").html(title);
		$("#modal-dialog").modal("show");
	})
}

function doSetValid(){
	var checked=$("#tbodyId").children().children().children("input:checked");
	var ids="";
	checked.each(function(){
		var id=$(this).val();
		ids+=","+id;
	})
	var params={"ids":ids};
	if($(this).hasClass("btn-valid")){
		params.valid=1;
	}else if($(this).hasClass("btn-invalid")){
		params.valid=0;
	}
	var url="team/doSetValid.do";
	$.post(url,params,function(result){
		if(result.state==1){
			doGetObjects();
		}else{
			alert(result.messge)
		}
	})
}

function doQueryTeamsForCondition(){
	$("#pageCurrent").data("pageCurrent",0);
	$(".first,.pre").hide();
	$(".next,.last").show() ;
	doGetObjects();
}


//分页查询
function doGetObjects(){
	var url="team/doQueryAllTeasByPage.do";
	var pages=$("#pageId").data("pageCurrent");
	var params={
			pageCurrent:pages==null?0:pages,
			valid:$("#searchValidId").val(),
			projectId:$("#searchPrjId").val()
	}
	$.post(url,params,function(result){
		if(result.state==1){
			doSetTeamsTable(result.data.List);
			setPagination(result.data.Page);
		}else{
			alert(result.message)
		}
	})
}


function doSetTeamsTable(data){
	var tbody=$("#tbodyId");
	tbody.empty();
	doChoosePageAll(1)
	for(var i in data){
		var tr=$("<tr></tr>");
		tr.append("<td><input type='checkbox' name='id' value="+data[i].id+"></td>");
		tr.append("<td>"+data[i].name+"</td>");
		tr.append("<td>"+data[i].projectName+"</td>");
		tr.append("<td>"+(data[i].valid==1?"启用":"禁用")+"</td>");
		tr.append("<td><button type='button' class='btn btn-default btn-update'>修改</button></td>");
		tbody.append(tr);
	}
}

