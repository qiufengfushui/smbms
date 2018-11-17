var backBtn = null;

$(function(){
	backBtn = $("#back");
	backBtn.on("click",function(){
        history.back(-1);
	});
});