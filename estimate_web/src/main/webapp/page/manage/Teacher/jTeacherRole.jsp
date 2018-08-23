<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>修改角色</title>
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

	<style>
		.tpl-content-myWrapper {
		  transition: all 0.4s ease-in-out;
		  position: relative;
		  /*margin-left: 240px;*/
		  z-index: 1101;
		  min-height: 922px;
		  border-bottom-left-radius: 3px;
		}
		#myCheck + label{
		    background-color: white;
		    border-radius: 5px;
		    border:1px solid #d3d3d3;
		    width:20px;
		    height:20px;
		    display: inline-block;
		    text-align: center;
		    vertical-align: middle;
		    line-height: 20px;
		}
		#myCheck:checked + label{
		    background-color: #eee;
		}
		#myCheck:checked + label:after{
		    content:"\2714";
		}
	</style>
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
                <!-- 其它功能-->
                <div class="am-fr tpl-header-navbar">
                    <ul>
                        <!-- 欢迎语 -->
                        <li class="am-text-sm tpl-header-navbar-welcome">
                            <a href="javascript:;">欢迎你, <span>${session._user_name }</span> </a>
                        </li>

                        <!-- 退出 -->
                        <li class="am-text-sm">
                            <a href="${pageContext.request.contextPath}/loginAction_logout">
                                <span class="am-icon-sign-out"></span> 退出
                            </a>
                        </li>
                    </ul>
                </div>
            </div>

        </header>


        <!-- 内容区域 -->
        <div class="tpl-content-myWrapper"  >

				<div class="row-content am-cf" >

					<div class="row" >
						<div class="am-u-md-3 am-u-lg-3" ><font color="#E9ECF3">1</font></div>
						<div class="am-u-sm-12 am-u-md-6 am-u-lg-6"  >
							<div class="widget am-cf">
								<div class="widget-head am-cf">
									<div class="widget-title am-fl">修改[${name }]角色</div>
									
									<div class="am-u-sm-12 am-u-md-6 am-u-lg-6">
										<div class="am-form-group">
											<div class="am-btn-toolbar">
												<div class="am-btn-group am-btn-group-xs">
													<button type="button" onclick="history.go(-1)"
														class="am-btn am-btn-default am-btn-success">
														<span class="am-icon-reply"></span> 返回
													</button>
												</div>
											</div>
										</div>
									</div>
									
								</div>

								<div class="widget-body am-fr">

									<form class="am-form tpl-form-border-form tpl-form-border-br" id="roleForm" action="teacherAction_updateRole">
										<input type="hidden" name="id" value="${id}"/>
										<c:forEach items="${roleList }" var="role" >
										<div>
											
											<input type="checkbox" name="roleIds" value="${role.id }"  
												<c:if test="${fn:contains(roleStr,role.name)}">
											       checked="checked"
											   </c:if>
											 >
											${role.name }
											</div>
										</c:forEach>
										
										

										
										<div class="am-form-group am-u-md-12" style="text-align: center;">
											<button type="button" class="am-btn am-btn-default am-btn-secondary" onclick="formSubmit()" ><span class="am-icon-save"></span> 保存</button>
										</div>
									</form>
									
									<script type="text/javascript">
										function formSubmit(){
											document.getElementById("roleForm").submit();
										}
									</script>
								</div>
							</div>
						</div>
						<div class="am-u-md-3 am-u-lg-3" ></div>
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