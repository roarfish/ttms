$(function(){
	 $("#uploadFormId")
	   .on("click",".btn-upload",doUpload)
	   .on("click",".btn-down",doDownload)
	doGetObjects();
})

function doGetObjects(){
	var url="attachment/doQueryAllAttachment.do";
	$.getJSON(url,function(result){
		if(result.state==1){
			setTableBodyRows(result.data);
		}else{
			alert(reuslt.message);
		}
	})
}

function setTableBodyRows(data){
	var tbody=$("#tbodyId");
	tbody.empty();
	for(var i in data){
		var tr=$("<tr></tr>");
		tr.append("<td>"+data[i].title+"</td>");
		tr.append("<td>"+data[i].fileName+"</td>");
		tr.append("<td>"+data[i].contentType+"</td>");
		tr.append("<td><button type='button' class='btn btn-default btn-down' value='"+data[i].id+"'>下载</button></td>");
		tbody.append(tr);
	}
}

function doUpload(){
	$("#uploadFormId").ajaxSubmit({
		type:"post",
		url:"attachment/doUpload.do",
		dataType:"json",
		success:function(result){
		alert(result.message);
		doGetObjects();
		}
	});
	return false;
}

function doDownload(){
	var id=$(this).val();
	var url="attachment/doDownload.do?id="+id;
	document.location.href=url;
}