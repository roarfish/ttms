$(function(){
	$("#modal-dialog").on("click",".ok",doSaveOrUpdateTeams)
	$("#modal-dialog").on("hidden.bs.modal",function(){
		$(this).off("click",".ok").removeData("id")
	})
	var idname="#projectId";
	doSetProjects(idname);
	var id=$("#modal-dialog").data("id");
	if(id){doInitData(id)}
})

function doSaveOrUpdateTeams(){
	if(!$("#editFormId").valid())return;
	var saveUrl="team/doSaveTeams.do";
	var updateUrl="team/doUpdateTeams.do";
	var params=getTeamParams();
	var id=$("#modal-dialog").data("id");
	var url=id?updateUrl:saveUrl;
	id?params.id=id:"";
	$.post(url,params,function(result){
		if(result.state==1){
			$("#modal-dialog").modal("hide");
			doGetObjects();
		}else{
			alert(result.message);
		}
	})
}

function getTeamParams(){
	var params={
			"name":$("#nameId").val(),
			"note":$("#noteId").html(),
			"projectId":$("#projectId").val(),
			"valid":$("input[name='valid']:checked").val()
	}
	return params;
}

function doInitData(id){
	var url="team/doQueryTeamById.do";
	$.post(url,{"id":id},function(result){
		if(result.state==1){
			doSetTeam(result.data);
		}else{
			alert(result.message);
		}
	})
} 

function doSetTeam(data){
	$("#nameId").val(data.name);
	$("#noteId").html(data.note);
	$("input[name='valid']").each(function(){
		if(data.valid==$(this).val()){
			$(this).prop("checked",true);
		}
	})
	$("#projectId").val(data.projectId);
}