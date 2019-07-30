function getRootPath_dc() {
    var pathName = window.location.pathname.substring(1);
     var webName = pathName == '' ? '' : pathName.substring(0, pathName.indexOf('/'));
     if (webName == "") {
        return window.location.protocol + '//' + window.location.host;
     }
     else {
         return window.location.protocol + '//' + window.location.host + '/' + webName;
     }
}

console.log(getRootPath_dc());

layui.config({
    base: getRootPath_dc() + '/module/'
}).extend({
    treetable: 'treetable-lay/treetable',
    treeSelect: "treeSelect/treeSelect"
})