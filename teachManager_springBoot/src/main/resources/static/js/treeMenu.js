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
                var comm = " ";
                //先绘制2级菜单
                $.each(result, function (index, item) {
                    comm = drawSecondMenu(comm, item);
                });

                document.getElementById("menuCol").innerHTML = comm;
            },
            error: function () {
                alert('失败');
            }
        })
    }

    function drawSecondMenu(comm, item) {
        var con = comm;
        if (item.permissionLevel == 2) {
            con += "<li class=\"sidebar-nav-link\"> <a href=\"javascript:;\">" +
                "<i class=\"" + item.icon + " sidebar-nav-link-logo\"></i>" + item.name + "</a>  </li>";
            return con;
        }else{
            return con;
        }
    }
})
