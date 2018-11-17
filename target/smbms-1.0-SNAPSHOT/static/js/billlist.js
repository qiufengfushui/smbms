var billObj;
//供应商下拉列表请求
$.ajax({
	type:"POST",
	url:"/provider/getAll"
})

//订单管理页面上点击删除按钮弹出删除框(billlist.jsp)
function deleteBill(obj){
	$.ajax({
		type:"POST",
		url:path+"/bill/del.do",
		data:{billid:$(obj).attr("billid")},
		success:function () {
			location.reload();
        }
	});
}

function openYesOrNoDLG(){
	$('.zhezhao').css('display', 'block');
	$('#removeBi').fadeIn();
}

function cancleBtn(){
	$('.zhezhao').css('display', 'none');
	$('#removeBi').fadeOut();
}
function changeDLGContent(contentStr){
	var p = $(".removeMain").find("p");
	p.html(contentStr);
}

$(function(){
	$(".viewBill").on("click",function(){
		//将被绑定的元素（a）转换成jquery对象，可以使用jquery方法
		var obj = $(this);
		window.location.href=path+"/bill/view.do?billid="+ obj.attr("billid");
	});
	
	$(".modifyBill").on("click",function(){
		var obj = $(this);
		window.location.href=path+"/bill/modify.do?billid="+ obj.attr("billid");
	});
	$('#no').click(function () {
		cancleBtn();
	});
	
	$('#yes').click(function () {
		deleteBill(billObj);
	});

	/*$(".deleteBill").on("click",function(){
		billObj = $(this);
		//changeDLGContent("你确定要删除订单【"+billObj.attr("billcc")+"】吗？");
        //询问框
        openYesOrNoDLG();
        confirm('您确定删除订单【'+billObj.attr("billcc")+'】吗？', {
            btn: ['是的','三思'] //按钮
        }, function(){
            deleteBill(billObj);
        }, function(){

        });

	});*/
});