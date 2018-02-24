$(function(){
	$("#queryFormId")
		.on("click","#pageAll",doChoosePageAll)
		.on("click","input[name='id']",doPostInfo)
})

var flag=true
function doChoosePageAll(a){
	var check=$("#pageAll")[0].checked;
	if(a==1){
		flag=true
		$("#pageAll")[0].checked = false
		return
	}
	
	var boxs=document.getElementsByName("id");
	if(check && flag){
		flag=false
		for(var i=0; i<boxs.length; i++){
			boxs[i].checked = true
		}
	}else if(!check){
		flag=true
		$("#pageAll")[0].checked = false
		for(var i=0; i<boxs.length; i++){
			boxs[i].checked = false
		}
	}
}

function doPostInfo(){
	
	if(!$(this)[0].checked){
		doChoosePageAll(1)
	}
}

