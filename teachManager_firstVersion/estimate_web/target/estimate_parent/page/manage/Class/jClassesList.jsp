<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>班级管理</title>
<meta name="keywords" content="index">
<meta name="viewport" content="width=device-width, initial-scale=1">
<meta name="renderer" content="webkit">
<meta http-equiv="Cache-Control" content="no-siteapp" />
<link rel="icon" type="image/png"
	href="${pageContext.request.contextPath}/assets/i/favicon.png">
<link rel="apple-touch-icon-precomposed"
	href="${pageContext.request.contextPath}/assets/i/app-icon72x72@2x.png">
<meta name="apple-mobile-web-app-title" content="Amaze UI" />
<script src="http://cdn.bootcss.com/echarts/3.3.2/echarts.min.js"></script>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/amazeui.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/amazeui.datatables.min.css" />
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/app.css">
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
					<span class="user-panel-logged-in-text"> <i
						class="am-icon-circle-o am-text-success tpl-user-panel-status-icon"></i>
						${session._user_name }
					</span>
					<div id="nowClock"></div>

					<script type="text/javascript">
						document.getElementById("nowClock").onload = function() {
							disptime()
						};
						function disptime() {
							//获取当前时间
							var today = new Date();
							//获取时分秒
							var hh = today.getHours();
							if (hh >= 0 && hh < 10) {
								hh = "0" + hh;
							}
							var mm = today.getMinutes();
							if (mm >= 0 && mm < 10) {
								mm = "0" + mm;
							}
							var ss = today.getSeconds();
							if (ss >= 0 && ss < 10) {
								ss = "0" + ss;
							}
							//设置div的内容为当前时间
							document.getElementById("nowClock").innerHTML = "<h2>"
									+ hh + ":" + mm + ":" + ss + "</h2>";
						}
						setInterval("disptime()", 1000);
					</script>
					<a href="${pageContext.request.contextPath }/manage/userAction_toUserSetting" class="tpl-user-panel-action-link"> <span class="am-icon-pencil"></span> 账号设置</a>
				</div>
			</div>

			<!-- 菜单 -->
			<ul class="sidebar-nav">
				<li class="sidebar-nav-link"><a href="${pageContext.request.contextPath }/pageAction_toIndex"> <i
						class="am-icon-home sidebar-nav-link-logo"></i> 首页
				</a></li>
				<li class="sidebar-nav-link"><a href="roleAction_toList"> <i
						class="am-icon-table sidebar-nav-link-logo"></i> 角色管理
				</a></li>
				<li class="sidebar-nav-link"><a href="moduleAction_toList"> <i
						class="am-icon-table sidebar-nav-link-logo"></i> 模块管理
				</a></li>
				<li class="sidebar-nav-link"><a href="teacherAction_toList" > <i
						class="am-icon-table sidebar-nav-link-logo"></i> 教师管理
				</a></li>
				<li class="sidebar-nav-link"><a href="classesAction_toList" class="active"> <i
						class="am-icon-table sidebar-nav-link-logo"></i> 班级管理
				</a></li>
				<li class="sidebar-nav-link"><a href="estimateAction_toList"> <i
						class="am-icon-table sidebar-nav-link-logo"></i> 评价管理
				</a></li>
				<li class="sidebar-nav-link"><a href=""> <i
						class="am-icon-table sidebar-nav-link-logo"></i> 文章管理
				</a></li>

			</ul>
		</div>


		<!-- 内容区域 -->
		<div class="tpl-content-wrapper">
			<div class="row-content am-cf">
				<div class="row">
					<div class="am-u-sm-12 am-u-md-12 am-u-lg-12">
						<div class="widget am-cf">
							<div class="widget-head am-cf">
								<div class="widget-title  am-cf">班级列表</div>


							</div>
							<div class="widget-body  am-fr">

								<div class="am-u-sm-12 am-u-md-6 am-u-lg-6">
									<div class="am-form-group">
										<div class="am-btn-toolbar">
											<div class="am-btn-group am-btn-group-xs">
												<button type="button" onclick="toCreate()"
													class="am-btn am-btn-default am-btn-success">
													<span class="am-icon-plus"></span> 新增
												</button>
												<button type="button" onclick="toOld()"
													class="am-btn am-btn-default am-btn-secondary">
													<span class="am-icon-clock-o"></span> 回溯
												</button>
												<script type="text/javascript">
													function toCreate(){
														location.href = "classesAction_toCreate";
													}
													function toOld(){
														location.href = "classesAction_toOld";
													}
												</script>
											</div>
										</div>
									</div>
								</div>
								<!-- <div class="am-u-sm-12 am-u-md-6 am-u-lg-3">
                                    <div class="am-form-group tpl-table-list-select">
										<select data-am-selected="{btnSize: 'sm'}">
											<option value="option1">所有类别</option>
											<option value="option2">IT业界</option>
											<option value="option3">数码产品</option>
											<option value="option3">笔记本电脑</option>
											<option value="option3">平板电脑</option>
											<option value="option3">只能手机</option>
											<option value="option3">超极本</option>
										</select>
									</div>
                                </div>
                                <div class="am-u-sm-12 am-u-md-12 am-u-lg-3">
                                    <div class="am-input-group am-input-group-sm tpl-form-border-form cl-p">
                                        <input type="text" class="am-form-field ">
                                        <span class="am-input-group-btn">
            <button class="am-btn  am-btn-default am-btn-success tpl-table-list-field am-icon-search" type="button"></button>
          </span>
                                    </div>
                                </div> -->

								<div class="am-u-sm-12">
									<table width="100%"
										class="am-table am-table-compact am-table-striped tpl-table-black "
										id="example-r">
										<thead>
											<tr>
												<th>序号</th>
												<th>班级名称</th>
												<th>班主任</th>
												<th>操作</th>
											</tr>
										</thead>
										<tbody>
										${links }
										<c:forEach items="${classesList}" var="classes" varStatus="status">
											<tr class="gradeX">
												<td>${status.index+1}</td>
												<td>${classes.name }</td>
												<td>${classes.ban }</td>
												<td>
													<div class="tpl-table-black-operation">
														<a href="classesAction_toUpdate?id=${classes.id}" > <i class="am-icon-pencil"></i>
															编辑
														</a>
														<a href="classesAction_toView?id=${classes.id}" 
															class="tpl-table-black-operation-del"> <i
															class="am-icon-book"></i> 查看
														</a>
														
													</div>
												</td>
											</tr>
											
											</c:forEach>
											<!-- more data -->
										</tbody>
									</table>
								</div>
								<div class="am-u-lg-12 am-cf">

									<div class="am-fr">
										<ul class="am-pagination tpl-pagination">
											<li class="am-disabled"><a href="#">«</a></li>
											<li class="am-active"><a href="#">1</a></li>
											<li><a href="#">2</a></li>
											<li><a href="#">3</a></li>
											<li><a href="#">4</a></li>
											<li><a href="#">5</a></li>
											<li><a href="#">»</a></li>
										</ul>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	</div>
	<script
		src="${pageContext.request.contextPath}/assets/js/amazeui.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/assets/js/amazeui.datatables.min.js"></script>
	<script
		src="${pageContext.request.contextPath}/assets/js/dataTables.responsive.min.js"></script>
	<script src="${pageContext.request.contextPath}/assets/js/app.js"></script>
	
	
</body>

</html>