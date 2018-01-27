function doSetProjects(idname){
	var url="team/doQueryAllProjectName.do";
	$.ajaxSettings.async = false;
	$.getJSON(url,function(result){
		if(result.state==1){
			doSetProject(idname,result.data);
		}else{
			alert(result.message);
		}
	})
}

function doSetProject(idname,data){
	var select=$(idname);
	select.append('<option value="">==请选择==</option>');
	for(var i in data){
		select.append("<option value="+data[i].id+">"+data[i].name+"</option>");
	}
	$("#modal-dialog").data("project",data);
}