<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>综合评价</title>
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
  .scrollspy-nav {
    top: 0;
    z-index: 100;
    background: #0e90d2;
    width: 100%;
    padding: 0 10px;
  }

  .scrollspy-nav ul {
    margin: 0;
    padding: 0;
  }

  .scrollspy-nav li {
    display: inline-block;
    list-style: none;
  }

  .scrollspy-nav a {
    color: #eee;
    padding: 10px 20px;
    display: inline-block;
  }

  .scrollspy-nav a.am-active {
    color: #fff;
    font-weight: bold;
  }

  .am-panel {
    margin-top: 20px;
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


		<!-- 内容区域 -->
		<div class="tpl-content-myWrapper">
			<div class="row-content am-cf">
				
				<div class="row" >
						<div class="am-u-sm-12 am-u-md-12 am-u-lg-12"  >
							<div class="widget am-cf">
								<div class="widget-head am-cf">
									<div class="widget-title am-fl">[${name }]
									<c:if test="${term != -1 }">
										 第${term }学期
									</c:if>
									<c:if test="${term == -1 }">
										 <font color="red" >ERROR</font>
									</c:if>
									评价</div>
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
								<nav class="scrollspy-nav" data-am-scrollspynav="{offsetTop: 45}" data-am-sticky>
								  <ul>
								    <li><a href="#citizen">公民素养</a></li>
								    <li><a href="#study">学习能力</a></li>
								    <li><a href="#innovation">创新实践</a></li>
								    <li><a href="#sport">运动与健康</a></li>
								    <li><a href="#craft">艺术素养</a></li>
								    <li><a href="#subject">科目评价</a></li>
								  </ul>
								</nav>
								 
								 <section data-am-widget="accordion" class="am-accordion am-accordion-gapped" data-am-accordion='{  }'>
								      <dl class="am-accordion-item am-active">
								        <dt class="am-accordion-title" id="citizen">
								          	公民素养
								        </dt>
								        
								        <dd class="am-accordion-bd am-collapse am-in">
								          <!-- 规避 Collapase 处理有 padding 的折叠内容计算计算有误问题， 加一个容器 -->
								          <div class="am-accordion-content">
								          <table class="am-table am-table-striped am-table-hover">
								          <thead>
										  <tr>
										    <th>评价要素</th>
										    <th>评价名称</th>
										    <th>评价人</th>
										    <th>评价时间</th>
										    <th>分数/满分</th>
										  </tr>
										  </thead>
										  <tbody>
										  <c:forEach items="${list }" var="estimate" >
										  <tr>
										  
								        	<c:if test="${estimate.estimateDimension eq '公民素养' }">
									          
										    <td>${estimate.estimateElement }</td>
										    <td>${estimate.estimateName }</td>
										    <td>${estimate.teacherName }</td>
										    <td><fmt:formatDate value="${estimate.time }" type="DATE" ></fmt:formatDate></td>
										    <td>${estimate.score }/${estimate.maxValue }</td>
												
								            </c:if>
								       	
								       	 </tr>
								       	  </c:forEach>
								       	 </table>
								          </div>
								        </dd>
								      </dl>
								      
								 </section>
								 <section data-am-widget="accordion" class="am-accordion am-accordion-gapped" data-am-accordion='{  }'>
								      <dl class="am-accordion-item am-active">
								        <dt class="am-accordion-title" id="study">
								          	学习能力
								        </dt>
								        
								        <dd class="am-accordion-bd am-collapse am-in">
								          <!-- 规避 Collapase 处理有 padding 的折叠内容计算计算有误问题， 加一个容器 -->
								          <div class="am-accordion-content">
								          <table class="am-table am-table-striped am-table-hover">
								          <thead>
										  <tr>
										    <th>评价要素</th>
										    <th>评价名称</th>
										    <th>评价人</th>
										    <th>评价时间</th>
										    <th>分数/满分</th>
										  </tr>
										  </thead>
										  <tbody>
										  <c:forEach items="${list }" var="estimate" >
										  <tr>
										  
								        	<c:if test="${estimate.estimateDimension eq '学习能力' }">
									          
										    <td>${estimate.estimateElement }</td>
										    <td>${estimate.estimateName }</td>
										    <td>${estimate.teacherName }</td>
										    <td><fmt:formatDate value="${estimate.time }" type="DATE" ></fmt:formatDate></td>
										    <td>${estimate.score }/${estimate.maxValue }</td>
												
								            </c:if>
								       	
								       	 </tr>
								       	  </c:forEach>
								       	 </table>
								          </div>
								        </dd>
								      </dl>
								      
								 </section>
								 
								 <section data-am-widget="accordion" class="am-accordion am-accordion-gapped" data-am-accordion='{  }'>
								      <dl class="am-accordion-item am-active">
								        <dt class="am-accordion-title" id="innovation">
								          	创新实践
								        </dt>
								        
								        <dd class="am-accordion-bd am-collapse am-in">
								          <!-- 规避 Collapase 处理有 padding 的折叠内容计算计算有误问题， 加一个容器 -->
								          <div class="am-accordion-content">
								          <table class="am-table am-table-striped am-table-hover">
								          <thead>
										  <tr>
										    <th>评价要素</th>
										    <th>评价名称</th>
										    <th>评价人</th>
										    <th>评价时间</th>
										    <th>分数/满分</th>
										  </tr>
										  </thead>
										  <tbody>
										  <c:forEach items="${list }" var="estimate" >
										  <tr>
										  
								        	<c:if test="${estimate.estimateDimension eq '创新实践' }">
									          
										    <td>${estimate.estimateElement }</td>
										    <td>${estimate.estimateName }</td>
										    <td>${estimate.teacherName }</td>
										    <td><fmt:formatDate value="${estimate.time }" type="DATE" ></fmt:formatDate></td>
										    <td>${estimate.score }/${estimate.maxValue }</td>
												
								            </c:if>
								       	
								       	 </tr>
								       	  </c:forEach>
								       	 </table>
								          </div>
								        </dd>
								      </dl>
								      
								 </section>
								 
								 <section data-am-widget="accordion" class="am-accordion am-accordion-gapped" data-am-accordion='{  }'>
								      <dl class="am-accordion-item am-active">
								        <dt class="am-accordion-title" id="sport">
								          	运动与健康
								        </dt>
								        
								        <dd class="am-accordion-bd am-collapse am-in">
								          <!-- 规避 Collapase 处理有 padding 的折叠内容计算计算有误问题， 加一个容器 -->
								          <div class="am-accordion-content">
								          <table class="am-table am-table-striped am-table-hover">
								          <thead>
										  <tr>
										    <th>评价要素</th>
										    <th>评价名称</th>
										    <th>评价人</th>
										    <th>评价时间</th>
										    <th>分数/满分</th>
										  </tr>
										  </thead>
										  <tbody>
										  <c:forEach items="${list }" var="estimate" >
										  <tr>
										  
								        	<c:if test="${estimate.estimateDimension eq '运动与健康' }">
									          
										    <td>${estimate.estimateElement }</td>
										    <td>${estimate.estimateName }</td>
										    <td>${estimate.teacherName }</td>
										    <td><fmt:formatDate value="${estimate.time }" type="DATE" ></fmt:formatDate></td>
										    <td>${estimate.score }/${estimate.maxValue }</td>
												
								            </c:if>
								       	
								       	 </tr>
								       	  </c:forEach>
								       	 </table>
								          </div>
								        </dd>
								      </dl>
								      
								 </section>
								 
								 <section data-am-widget="accordion" class="am-accordion am-accordion-gapped" data-am-accordion='{  }'>
								      <dl class="am-accordion-item am-active">
								        <dt class="am-accordion-title" id="craft">
								          	艺术素养
								        </dt>
								        
								        <dd class="am-accordion-bd am-collapse am-in">
								          <!-- 规避 Collapase 处理有 padding 的折叠内容计算计算有误问题， 加一个容器 -->
								          <div class="am-accordion-content">
								          <table class="am-table am-table-striped am-table-hover">
								          <thead>
										  <tr>
										    <th>评价要素</th>
										    <th>评价名称</th>
										    <th>评价人</th>
										    <th>评价时间</th>
										    <th>分数/满分</th>
										  </tr>
										  </thead>
										  <tbody>
										  <c:forEach items="${list }" var="estimate" >
										  <tr>
										  
								        	<c:if test="${estimate.estimateDimension eq '艺术素养' }">
									          
										    <td>${estimate.estimateElement }</td>
										    <td>${estimate.estimateName }</td>
										    <td>${estimate.teacherName }</td>
										    <td><fmt:formatDate value="${estimate.time }" type="DATE" ></fmt:formatDate></td>
										    <td>${estimate.score }/${estimate.maxValue }</td>
												
								            </c:if>
								       	
								       	 </tr>
								       	  </c:forEach>
								       	 </table>
								          </div>
								        </dd>
								      </dl>
								      
								 </section>
								 
								 <section data-am-widget="accordion" class="am-accordion am-accordion-gapped" data-am-accordion='{  }'>
								      <dl class="am-accordion-item am-active">
								        <dt class="am-accordion-title" id="subject">
								          	科目评价
								        </dt>
								        
								        <dd class="am-accordion-bd am-collapse am-in">
								          <!-- 规避 Collapase 处理有 padding 的折叠内容计算计算有误问题， 加一个容器 -->
								          <div class="am-accordion-content">
								          <table class="am-table am-table-striped am-table-hover">
								          <thead>
										  <tr>
										    <th>评价要素</th>
										    <th>评价名称</th>
										    <th>评价人</th>
										    <th>评价时间</th>
										    <th>分数/满分</th>
										  </tr>
										  </thead>
										  <tbody>
										  <c:forEach items="${list }" var="estimate" >
										  <tr>
										  
								        	<c:if test="${estimate.estimateDimension eq '科目评价' }">
									          
										    <td>${estimate.estimateElement }</td>
										    <td>${estimate.estimateName }</td>
										    <td>${estimate.teacherName }</td>
										    <td><fmt:formatDate value="${estimate.time }" type="DATE" ></fmt:formatDate></td>
										    <td>${estimate.score }/${estimate.maxValue }</td>
												
								            </c:if>
								       	
								       	 </tr>
								       	  </c:forEach>
								       	 </table>
								          </div>
								        </dd>
								      </dl>
								      
								 </section>
								 
								 
								 
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