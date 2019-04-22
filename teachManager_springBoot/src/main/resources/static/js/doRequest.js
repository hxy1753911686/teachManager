//左边栏2/3级菜单get请求
//调用方式  get(url, {html :prnhtml,cm1:'sdsddsd',cm2:'haha'});
get = function (URL, PARAMS) {
    if (typeof URL == "undefined" || URL == null || URL == "") {
        return;
    } else {
        var temp = document.createElement("form");
        temp.action = URL;
        temp.method = "get";
        temp.style.display = "none";
        // for (var x in PARAMS) {
        //     var opt = document.createElement("textarea");
        //     opt.name = x;
        //     opt.value = PARAMS[x];
        //     //alert(opt.name);
        //     temp.appendChild(opt);
        // }
        document.body.appendChild(temp);
        temp.submit();
        document.getElementById("rollback").submit();
        return temp;
    }
}

//arguments为不定参数，用来接收未知个数的参数
doMenuGet = function (url) {
    get(url);
}