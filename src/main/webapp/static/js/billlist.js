var billObj;
$(function () {
    $.ajax({
        type:"post",
        url:path+"/provider/getProList.do",
        dataType:"JSON",
        success:function (data) {
            if(data != null){
                var proId = $("#proId").val()
                $("#provider").html("");
                var options = "<option value='0'>--请选择--</option>"
                for (var i = 0;i < data.length;i++){
                    if(proId != null && proId != undefined && data[i].id == proId){
                        options += "<option selected=\"selected\" value='"+data[i].id+"'>"+data[i].proName+"</option>"
                    }else{
                        options += "<option value='"+data[i].id+"'>"+data[i].proName+"</option>"
                    }
                }
                $("#provider").html(options);
            }
        }
    })
})

//订单管理页面上点击删除按钮弹出删除框(billlist.jsp)
function deleteBill(obj){
    $.ajax({
        type:"POST",
        url:path+"/bill/del.do",
        data:{id:$(obj).attr("billid")},
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

	$(".deleteBill").on("click",function(){
		billObj = $(this)
        if(confirm('您确定删除订单【'+billObj.attr("billcc")+'】吗？')){
            deleteBill(billObj);
        }
	});
});