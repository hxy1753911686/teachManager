$(function () {

    //ajax的方式获取左边栏
    makeMenu = function (farLevel, farId) {
        alert('farLevel:' + farLevel + 'farId:' + farId);

        $.ajax({
            type: "POST",
            url: "/makeMenu",
            data: {
                id: farId,
                level: farLevel
            },
            success: function (result) {
                debugger;
                alert('成功');
                alert(result);
            },
            error : function () {
                alert('失败');
            }
        })
    }
})
