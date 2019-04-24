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
                        dlTag.append("<dd><a id='" + item.htmlID + "' href=\"javascript:;\" data-id='" + item.dataID + "' data-url='" + item.url + "' " +
                            "data-title='" + item.name + "' data-type=\"tabAdd\" >" + item.name + "</a></dd>");

                        //删除2级菜单属性
                        $('#' + con).removeAttr("data-id");
                        $('#' + con).removeAttr("data-url");
                        $('#' + con).removeAttr("data-type");
                        $('#' + con).removeAttr("data-title");


                    }
                })

                //重新渲染导航栏
                layui.use('element', function () {
                    var element = layui.element;

                    element.render('nav');
                    var active = {
                        tabAdd: function (url, id, name) {
                            //新增一个Tab项 传入三个参数，分别对应其标题，tab页面的地址，还有一个规定的id，是标签中data-id的属性值
                            //关于tabAdd的方法所传入的参数可看layui的开发文档中基础方法部分
                            element.tabAdd('mainContent', {
                                title: name,
                                content: '<iframe data-frameid="' + id + '" scrolling="no" frameborder="0" src="' + url + '" style="width:100%;"></iframe>',
                                id: id //规定好的id
                            })
                            var h = $(window).height();
                            $("iframe").css("height", h + "px");
                            element.render('tab');

                        },
                        tabChange: function (id) {
                            //切换到指定Tab项
                            element.tabChange('mainContent', id); //根据传入的id传入到指定的tab项
                        }
                    }
                    $('.layui-nav-tree [data-id]').on('click', function () {
                        debugger;
                        var dataid = $(this);

                        //这时会判断右侧.layui-tab-title属性下的有lay-id属性的li的数目，即已经打开的tab项数目
                        if ($(".layui-tab-title li[lay-id]").length <= 0) {
                            //如果比零小，则直接打开新的tab项
                            active.tabAdd(dataid.attr("data-url"), dataid.attr("data-id"), dataid.attr("data-title"));
                        } else {
                            //否则判断该tab项是否以及存在

                            var isData = false; //初始化一个标志，为false说明未打开该tab项 为true则说明已有
                            $.each($(".layui-tab-title li[lay-id]"), function () {
                                //如果点击左侧菜单栏所传入的id 在右侧tab项中的lay-id属性可以找到，则说明该tab项已经打开
                                if ($(this).attr("lay-id") == dataid.attr("data-id")) {
                                    isData = true;
                                }
                            })
                            if (isData == false) {
                                //标志为false 新增一个tab项
                                active.tabAdd(dataid.attr("data-url"), dataid.attr("data-id"), dataid.attr("data-title"));
                            }
                        }
                        //最后不管是否新增tab，最后都转到要打开的选项页面上
                        active.tabChange(dataid.attr("data-id"));
                    })
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
