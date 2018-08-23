<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html lang="en">

<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<title>科目评价</title>
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
		tr td [type="number"]{
			width: 80px;
		}
	</style>
	<script>
	  	$(function() {
		  $('#task').popover({
		    content: '由各科老师记录，共18次，每次0.5分，满分9分',
		    trigger: 'click',
		    theme: 'success'
		  })
		 
		});
		
		
		
	  </script>

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
									评分详情</div>
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

								
								<div class="am-tabs" data-am-tabs>
								  <ul class="am-tabs-nav am-nav am-nav-tabs">
								    <li class="am-active"><a href="#tab1">科目评价</a></li>
								  </ul>
								
								  <div class="am-tabs-bd">
								    <div class="am-tab-panel am-fade am-in am-active" id="tab1">
								     <form method="post" id="subjectFrom" action="estimateAction_saveSubject">
								     <input type="hidden" name="subjectId" value="${subjectId }"  >
								     <input type="hidden" name="classId" value="${classId }" >
								     <input type="hidden" name="studentId" value="${id }" >
								     <input type="hidden" name="term" value="${term }" >
								     <table class="am-table am-table-bordered am-table-centered am-table-striped am-table-hover">
									  <tr>
									    <th>评价要素</th>
									    <th>评价内容</th>
									    <th>评分/满分</th>
									  </tr>
									  <tr>
									  
									    <td rowspan="5" class="am-text-middle">${subjectName }</td>
									    <td >上课考勤</td>
									    <td class=""><input type="number" max="1" min="0" name="attendanceValue" value="${attendanceValue }" 
									     	<c:if test="${attendanceDisabled eq 'yes' }">disabled="disabled"</c:if> 
									      >/1</td>
									  </tr>
									  <tr>
									    <td >上课表现</td>
									    <td ><input type="number" max="3" min="0" name="performanceValue" value="${ performanceValue}" 
									    	<c:if test="${performanceDisabled eq 'yes' }">disabled="disabled"</c:if>
									    >/3</td>
									  </tr>
									  <tr>
									    <td id="task">作业<span class="am-badge am-badge-warning am-round">note</span></td>
									    <td><input type="number" max="9" min="0" name="taskValue" value="${taskValue }" 
									    	<c:if test="${taskDisabled eq 'yes' }">disabled="disabled"</c:if>
									    >/9</td>
									  </tr>
									  <tr>
									    <td>学科获奖</td>
									    <td><input type="number" max="1" min="0" name="winningValue" value="${winningValue }" 
									    	<c:if test="${winningDisabled eq 'yes' }">disabled="disabled"</c:if>
									    >/1</td>
									  </tr>
									  <tr>
									    <td >考试成绩</td>
									    <td><input type="number" max="4" min="0" name="examResultsValue" value="${examResultsValue }" 
									    	<c:if test="${examResultsDisabled eq 'yes' }">disabled="disabled"</c:if>
									    >/4</td>
									  </tr>
									  
								     </table>
								     <div class="am-form-group am-u-md-12" style="text-align: center;">
											<button type="button" class="am-btn am-btn-default am-btn-secondary" onclick="formSubmit()" ><span class="am-icon-save"></span> 保存</button>
										</div>
								     </form>
								     
								     <script type="text/javascript">
										
										function formSubmit(){
											document.getElementById("subjectFrom").submit();
										}
									</script>
								     
								     
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