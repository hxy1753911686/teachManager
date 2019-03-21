// 侧边菜单
showFunction = function(obj) {
	
	$(obj).siblings('.sidebar-nav-sub').slideToggle(80)
		.end()
		.find('.sidebar-nav-sub-ico').toggleClass('sidebar-nav-sub-ico-rotate');
}
