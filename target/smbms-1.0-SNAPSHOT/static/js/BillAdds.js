function goAdd() {
    var $all=$("form").find(":input,select");
    var $params=$all.serializeArray();
    var content=$.param($params);
    $.ajax({
        "url":"../jsp/bill.do?method=insert",
        "type":"POST",
        "data":content,
        "dataType":"text",
        "success":function (data) {
            if (data=="yes"){
                layer.msg("添加成功");
                $("form").find(":input").val("");
                $("#providerId").selectedIndex=0;
            }else {
                layer.msg("添加失败！");
            }
        },
        "error":function () {
            layer.msg("系统异常！");
        }
    });
    return false;
}