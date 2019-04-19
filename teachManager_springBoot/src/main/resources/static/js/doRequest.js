//左边栏2/3级菜单post请求
//调用方式  post(url, {html :prnhtml,cm1:'sdsddsd',cm2:'haha'});
post = function(URL, PARAMS)
{
    if(typeof url == "undefined" || url == null || url == ""){
        return;
    }else{
        var temp = document.createElement("form");
        temp.action = URL;
        temp.method = "get";
        temp.style.display = "none";
        for (var x in PARAMS)
        {
            var opt = document.createElement("textarea");
            opt.name = x;
            opt.value = PARAMS[x];
            //alert(opt.name);
            temp.appendChild(opt);
        }
        document.body.appendChild(temp);
        temp.submit();
        document.getElementById("rollback").submit();
        return temp;
    }
}

doPost = function (url) {
    
}