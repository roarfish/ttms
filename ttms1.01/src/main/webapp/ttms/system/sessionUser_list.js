$(function(){
	doGetObjects();
})

function doGetObjects(){
	var url="session/online.do";
	$.getJSON(url,function(result){
		if(result.state == 1){
			doSetSessionTable(result.data)
		}else{
			alert(result.message)
		}
	})
}

function doSetSessionTable(data){
	var tbody=$("#tbody");
	tbody.empty();
	for(var i in data){
		var tr=$("<tr></tr>");
		var td=$("<td id="+i+">"+data[i].sessionId+"</td>" +
				"<td>"+data[i].username+"</td>" +
				"<td>"+data[i].email+"</td>" +
				"<td>"+data[i].startTime+"</td>" +
				"<td>"+data[i].lastLoginTime+"</td>" +
				"<td>"+(data[i].valid == 1 ? "有效" : "无效")+"</td>" +
				"<td><a href='javascript:void(0)'onclick='go2Page1("+i+")'>详情</a></td>");
		tr.append(td);
		tbody.append(tr);
	}
}

function go2Page1(i){
	var sessionId=$("#"+i).html();
	var url="session/dataDetail.do?sessionId="+sessionId+"&t="+Math.random(1000);
	$(".content").load(url);
}
