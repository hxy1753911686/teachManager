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
                alert('成功');
                var comm = " ";
                //先绘制2级菜单
                $.each(result, function (index, item) {
                    comm = drawSecondMenu(comm, item);
                });

                document.getElementById("menuCol").innerHTML = comm;

                $.each(result, function (index, item) {
                    debugger;
                    var con = " ";
                    con = getFarMenu(con, result, item);
                    if (typeof con == "undefined" || con == null || con == " ") {
                        //each中结束本次循环
                        return true;
                    } else {

                        //获取其父标签
                        var parTag = $('#' + con).parent("li");
                        //判断li下是否有dl标签，无则填并获取，有则获取
                        if (parTag.find("dl").length == 0) {
                            parTag.append("<dl class=\"layui-nav-child\"></dl>")
                        }
                        var dlTag = parTag.children("dl");

                        //在dl标签下追加三级菜单
                        dlTag.append("<dd><a id='"+item.htmlID+"' href=\"javascript:;\" data-id='" + item.dataID + "' data-url='" + item.url + "' " +
                            "data-title='" + item.name + "' data-type=\"tabAdd\" >" + item.name + "</a></dd>");

                    }
                })

                //重新渲染导航栏
                layui.use('element', function () {
                    var element = layui.element;

                    element.render('nav');
                });
            },
            error: function () {
                alert('失败');
            }
        })
    }

    function drawSecondMenu(comm, item) {
        var con = comm;
        if (item.permissionLevel == 2) {
            con += "<li class=\"layui-nav-item\"><a id='" + item.htmlID + "' href=\"javascript:;\" data-id='" + item.dataID + "' " +
                "data-url='" + item.url + "' data-title='" + item.name + "' data-type=\"tabAdd\">" + item.name + "</a></li>";
            // con += "<li class=\"sidebar-nav-link\"> <a href=\"javascript:void(0);\" id= " + item.htmlID + " onclick = \" doMenuGet('"+ item.url +"'); \" >" +
            //     "<i class=\"" + item.icon + " sidebar-nav-link-logo\"></i>" + item.name + "</a>  </li>";
            return con;
        } else {
            return con;
        }
    }

    function getFarMenu(con, result, item) {
        var resultValue = con;
        if (item.permissionLevel == 3) {
            $.each(result, function (index, newItem) {
                if (item.parentId == newItem.id) {
                    resultValue = newItem.htmlID;
                }
            })
            return resultValue;
        } else {
            return resultValue;
        }
    }
})
