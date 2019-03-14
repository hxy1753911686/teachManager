<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://shiro.apache.org/tags" prefix="shiro" %>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>什贴中学教学评价系统</title>
    <meta name="keywords" content="index">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <meta name="renderer" content="webkit">
    <meta http-equiv="Cache-Control" content="no-siteapp" />
    <link rel="icon" type="image/png" href="assets/i/favicon.png">
    <link rel="apple-touch-icon-precomposed" href="assets/i/app-icon72x72@2x.png">
    <meta name="apple-mobile-web-app-title" content="Amaze UI" />
    <script src="http://cdn.bootcss.com/echarts/3.3.2/echarts.min.js"></script>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/amazeui.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/amazeui.datatables.min.css" />
    <link rel="stylesheet" href="${pageContext.request.contextPath}/assets/css/app.css">
    <script src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js"></script>

</head>

<body data-type="widgets">
	<script src="${pageContext.request.contextPath}/assets/js/theme.js"></script>
	<div class="am-g tpl-g">
		<!-- 头部 -->
		<header>
				<!-- logo -->
				<div class="am-fl tpl-header-logo">
					<img src="${pageContext.request.contextPath }/assets/img/shentie.png" alt="" style="height:55px ;width: 55px;margin-left:-10px ;">
					<h3 style="display: inline;font: '楷体';">什贴中学教学评价系统</h3>
				</div>
			<!-- 右侧内容 -->
			<div class="tpl-header-fluid">
				<!-- 侧边切换 -->
				<div class="am-fl tpl-header-switch-button am-icon-list"
					style="padding-top: 20px; padding-bottom: 10px">
					<span> </span>
				</div>

				<!-- 其它功能-->
				<div class="am-fr tpl-header-navbar">
					<ul>
						<!-- 欢迎语 -->
						<li class="am-text-sm tpl-header-navbar-welcome"><a
							href="javascript:;">欢迎您, <span>${session._user_name }</span>
						</a></li>



						<!-- 退出 -->
						<li class="am-text-sm"><a href="${pageContext.request.contextPath}/loginAction_logout"> <span
								class="am-icon-sign-out"></span> 退出
						</a></li>
					</ul>
				</div>
			</div>

        </header>
        <!-- 侧边导航栏 -->
        <div class="left-sidebar">
            <!-- 用户信息 -->
            <div class="tpl-sidebar-user-panel">
                <div class="tpl-user-panel-slide-toggleable">
                    <!-- <div class="tpl-user-panel-profile-picture">
                        <img src="assets/img/user04.png" alt="">
                    </div> -->
                    <span class="user-panel-logged-in-text">
              <i class="am-icon-circle-o am-text-success tpl-user-panel-status-icon"></i>
             	${session._user_name }
          </span>
          <div id="nowClock"></div>
          
          <script type="text/javascript">
          	document.getElementById("nowClock").onload = function(){disptime()};
          	function disptime(){
          		//获取当前时间
          		var today = new Date();
          		//获取时分秒
          		var hh = today.getHours();
          		if(hh >=0 && hh < 10){
          			hh = "0" + hh;
          		}
          		var mm = today.getMinutes();
          		if(mm >=0 && mm < 10){
          			mm = "0" + mm;
          		}
          		var ss = today.getSeconds();
          		if(ss >=0 && ss < 10){
          			ss = "0" + ss;
          		}
          		//设置div的内容为当前时间
          		document.getElementById("nowClock").innerHTML = 
          			"<h2>"+hh+":"+mm+":"+ss+"</h2>";
          	}
          	setInterval("disptime()",1000);
          </script>
                    <a href="${pageContext.request.contextPath }/manage/userAction_toUserSetting" class="tpl-user-panel-action-link"> <span class="am-icon-pencil"></span> 账号设置</a>
                </div>
            </div>

            <!-- 菜单 -->
            <ul class="sidebar-nav">
            
            	<!-- 当jsp页面碰到shiro标签时就执行AuthRealm中授权方法 -->
	
				<li class="sidebar-nav-link">
                    <a href="${pageContext.request.contextPath }/pageAction_toIndex" class="active">
                        <i class="am-icon-home sidebar-nav-link-logo"></i> 首页
                    </a>
                </li>
	
				<shiro:hasPermission name="角色管理">
				<li class="sidebar-nav-link">
                    <li class="sidebar-nav-link"><a href="${pageContext.request.contextPath }/manage/roleAction_toList"> <i
						class="am-icon-table sidebar-nav-link-logo"></i> 角色管理
					</a></li>
                </li>
                </shiro:hasPermission>
                
                <shiro:hasPermission name="模块管理">
				<li class="sidebar-nav-link">
                    <li class="sidebar-nav-link"><a href="${pageContext.request.contextPath }/manage/moduleAction_toList"> <i
						class="am-icon-table sidebar-nav-link-logo"></i> 模块管理
					</a></li>
                </li>
                </shiro:hasPermission>
                
                <shiro:hasPermission name="教师管理">
				<li class="sidebar-nav-link">
                    <li class="sidebar-nav-link"><a href="${pageContext.request.contextPath }/manage/teacherAction_toList"> <i
						class="am-icon-table sidebar-nav-link-logo"></i> 教师管理
					</a></li>
                </li>
                </shiro:hasPermission>
                
                <shiro:hasPermission name="班级管理">
				<li class="sidebar-nav-link">
                    <li class="sidebar-nav-link"><a href="${pageContext.request.contextPath }/manage/classesAction_toList"> <i
						class="am-icon-table sidebar-nav-link-logo"></i> 班级管理
					</a></li>
                </li>
                </shiro:hasPermission>
                
                <shiro:hasPermission name="评价管理">
				<li class="sidebar-nav-link">
                    <li class="sidebar-nav-link"><a href="${pageContext.request.contextPath }/manage/estimateAction_toList"> <i
						class="am-icon-table sidebar-nav-link-logo"></i> 评价管理
					</a></li>
                </li>
                </shiro:hasPermission>
                
                <shiro:hasPermission name="文章管理">
				<li class="sidebar-nav-link">
                    <li class="sidebar-nav-link"><a href="${pageContext.request.contextPath }/manage/articleAction_toList"> <i
						class="am-icon-table sidebar-nav-link-logo"></i> 文章管理
					</a></li>
                </li>
                </shiro:hasPermission>
                
                
                <shiro:hasPermission name="综合评价">
				<li class="sidebar-nav-link">
                    <li class="sidebar-nav-link"><a href="${pageContext.request.contextPath }/main/estimateAction_toClassStudent"> <i
						class="am-icon-table sidebar-nav-link-logo"></i> 综合评价
					</a></li>
                </li>
                </shiro:hasPermission>
                
                <shiro:hasPermission name="科目评价">
				<li class="sidebar-nav-link">
                    <li class="sidebar-nav-link"><a href="${pageContext.request.contextPath }/main/estimateAction_toSubjectList"> <i
						class="am-icon-table sidebar-nav-link-logo"></i> 科目评价
					</a></li>
                </li>
                </shiro:hasPermission>
                
                <shiro:hasPermission name="管理班级">
				<li class="sidebar-nav-link">
                    <li class="sidebar-nav-link"><a href="${pageContext.request.contextPath }/printAction_toList"> <i
						class="am-icon-table sidebar-nav-link-logo"></i> 管理班级
					</a></li>
                </li>
                </shiro:hasPermission>
				
				<shiro:hasPermission name="留言列表">
				<li class="sidebar-nav-link">
                    <li class="sidebar-nav-link"><a href="${pageContext.request.contextPath }/main/estimateAction_toList"> <i
						class="am-icon-table sidebar-nav-link-logo"></i> 留言列表
					</a></li>
                </li>
                </shiro:hasPermission>
                
                <shiro:hasPermission name="查看评价">
				<li class="sidebar-nav-link">
                    <li class="sidebar-nav-link"><a href="${pageContext.request.contextPath }/main/estimateAction_viewSelf"> <i
						class="am-icon-table sidebar-nav-link-logo"></i> 查看评价
					</a></li>
                </li>
                </shiro:hasPermission>
               
                <shiro:hasPermission name="留言">
				<li class="sidebar-nav-link">
                    <li class="sidebar-nav-link"><a href="${pageContext.request.contextPath }/main/estimateAction_toList"> <i
						class="am-icon-table sidebar-nav-link-logo"></i> 留言
					</a></li>
                </li>
                </shiro:hasPermission>
                
                

            </ul>
        </div>


        <!-- 内容区域 -->
		<div class="tpl-content-wrapper">
			<div class="row-content am-cf">
				<div class="row">
					<div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
						<div class="widget am-cf">
						<div style="text-align: center;">
							<font size="7" >校园风采</font>
						</div>
						<div class="am-slider am-slider-default" data-am-flexslider id="demo-slider-0"  style="width: 600px;margin-left: 200px"> 
  							<ul class="am-slides">
							    <li><img src="${pageContext.request.contextPath }/img/st000.jpg" /></li>
							    <li><img src="${pageContext.request.contextPath }/img/st001.jpg" /></li>
							    <li><img src="${pageContext.request.contextPath }/img/st002.jpg" /></li>
							    <li><img src="${pageContext.request.contextPath }/img/st003.jpg" /></li>
							    <li><img src="${pageContext.request.contextPath }/img/st004.jpg" /></li>
							    <li><img src="${pageContext.request.contextPath }/img/st005.jpg" /></li>
							    <li><img src="${pageContext.request.contextPath }/img/st006.jpg" /></li>
							  </ul>
							</div>
							
						</div>
						<footer data-am-widget="footer"
					          class="am-footer am-footer-default"
					           data-am-footer="{  }">
					    <div class="am-footer-switch">
					    <a id="godesktop" data-rel="desktop" class="am-footer-desktop" style="color: gray;">
					          友情链接 :
					      </a>
					    <a id="godesktop" style="color: blue;" href="https://jinzhong.xueanquan.com/">
					          晋中市安全教育平台
					      </a>
					      | 
					      <a id="godesktop" style="color: blue;"  href="http://www.wsbedu.com/">
					     为您服务教育网
					      </a>
					       | 
					      <a id="godesktop" style="color: blue;"  href="http://www.k12.com.cn/">
					     K12教育空间
					      </a>
					      | 
					      <a id="godesktop" style="color: blue;"  href="http://www.k12.com.cn/">
					             课件中心
					      </a>
					    </div>
					  </footer>
					
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
    <script src="assets/js/amazeui.min.js"></script>
    <script src="assets/js/amazeui.datatables.min.js"></script>
    <script src="assets/js/dataTables.responsive.min.js"></script>
    <script src="assets/js/app.js"></script>

</body>

</html>