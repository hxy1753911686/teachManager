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
                    con = getFarMenu(con,result, item);
                    if(typeof con == "undefined" || con == null || con == " ") {
                        //each中结束本次循环
                        return true;
                    }else{

                        //获取其父标签
                        var parTag = $('#' + con).parent("li");
                        //判断li下是否有ul标签，无则填并获取，有则获取
                        if (parTag.find("ul").length == 0) {
                            parTag.append("<ul class=\"sidebar-nav sidebar-nav-sub\"></ul>")
                        }
                        var ulTag = parTag.children("ul");

                        //在ul标签下追加三级菜单
                        ulTag.append("<li class=\"sidebar-nav-link\"><a href=\"javascript:void(0)\" id=" + item.id + ">" +
                            "<span class=\"am-icon-angle-right sidebar-nav-link-logo\"></span> " + item.name + "</a></li>");

                        //更新2级菜单a标签
                        $("#" + con).append("<span class=\"am-icon-chevron-down am-fr am-margin-right-sm sidebar-nav-sub-ico\"></span>");
                        $("#" + con).attr("onclick","showFunction(this);");
                    }
                })
            },
            error: function () {
                alert('失败');
            }
        })
    }

    function drawSecondMenu(comm, item) {
        var con = comm;
        if (item.permissionLevel == 2) {
            con += "<li class=\"sidebar-nav-link\"> <a href=\"javascript:void(0);\" id= " + item.htmlID + " onclick = \" doPost('"+ item.url +"'); \" >" +
                "<i class=\"" + item.icon + " sidebar-nav-link-logo\"></i>" + item.name + "</a>  </li>";
            return con;
        } else {
            return con;
        }
    }

    function getFarMenu(con,result, item) {
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
