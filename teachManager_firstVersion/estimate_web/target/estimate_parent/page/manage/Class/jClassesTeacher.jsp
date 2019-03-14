<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <title>教师管理</title>
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
        <div class="tpl-content-myWrapper">

				<div class="row-content am-cf">

					<div class="row">
						<div class="am-u-md-3 am-u-lg-3" ><font color="#E9ECF3">1</font></div>
						<div class="am-u-sm-12 am-u-md-6 am-u-lg-6">
							<div class="widget am-cf">
								<div class="widget-head am-cf">
									<div class="widget-title am-fl">[${name }]教师管理</div>
									
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
								<div class="am-alert am-alert-warning" data-am-alert>
									  <button type="button" class="am-close">&times;</button>
									  <p>初一没有物理化学，初二没有化学，初三没有地理生物</p>
									</div>
									<div class="am-alert am-alert-success" data-am-alert>
									  <button type="button" class="am-close">&times;</button>
									  <p>如果选错，请选择【==请选择==】来消除</p>
									</div>

								<div class="widget-body am-fr">

									<form class="am-form tpl-form-border-form tpl-form-border-br" id="classesForm" action="classesAction_saveTeacher" method="post" >
										<input type="hidden" name="id" value="${id }" >
										<div class="am-form-group am-u-md-12">
											<label for="user-tel" class="am-u-sm-3 am-form-label">语文</label>
											<div class="am-u-sm-9">
												<select data-am-selected="{maxHeight: 170}" name="chineseId" id="chineseId" >
												  <option value="-1">==请选择==</option>
												  
												  	
												  <c:forEach items="${teacherList }" var="teacher">
													  <option value="${teacher.id }"  <c:if test="${teacher.id != null && manageMap.chineseTeacher != null && teacher.id == manageMap.chineseTeacher }" >selected="selected"</c:if> >${teacher.name }</option>
												  </c:forEach>
												</select>
											</div>
										</div>
										
										<div class="am-form-group am-u-md-12">
											<label for="user-tel" class="am-u-sm-3 am-form-label">数学</label>
											<div class="am-u-sm-9">
												<select data-am-selected="{maxHeight: 170}" name="mathId">
												  <option value="-1">==请选择==</option>
												  
												  	
												  <c:forEach items="${teacherList }" var="teacher">
													  <option value="${teacher.id }"  <c:if test="${teacher.id != null && manageMap.mathTeacher != null && teacher.id == manageMap.mathTeacher }" >selected="selected"</c:if> >${teacher.name }</option>
												  </c:forEach>
												</select>
											</div>
										</div>
										
										<div class="am-form-group am-u-md-12">
											<label for="user-name" class="am-u-sm-3 am-form-label">英语</label>
											<div class="am-u-sm-9">
												<select data-am-selected="{maxHeight: 170}" name="englishId">
												  <option value="-1">==请选择==</option>
												  
												  	
												  <c:forEach items="${teacherList }" var="teacher">
													  <option value="${teacher.id }"  <c:if test="${teacher.id != null && manageMap.englishTeacher != null && teacher.id == manageMap.englishTeacher }" >selected="selected"</c:if> >${teacher.name }</option>
												  </c:forEach>
												</select>
											</div>
										</div>

										<div class="am-form-group am-u-md-12">
											<label for="user-name" class="am-u-sm-3 am-form-label">政治</label>
											<div class="am-u-sm-9">
												<select data-am-selected="{maxHeight: 170}" name="politicsId">
												  <option value="-1">==请选择==</option>
												  
												  	
												  <c:forEach items="${teacherList }" var="teacher">
													  <option value="${teacher.id }"  <c:if test="${teacher.id != null && manageMap.politicsTeacher != null && teacher.id == manageMap.politicsTeacher }" >selected="selected"</c:if> >${teacher.name }</option>
												  </c:forEach>
												</select>
											</div>
										</div>

										<div class="am-form-group am-u-md-12">
											<label for="user-name" class="am-u-sm-3 am-form-label">历史</label>
											<div class="am-u-sm-9">
												<select data-am-selected="{maxHeight: 170}" name="historyId">
												  <option value="-1">==请选择==</option>
												  
												  	
												  <c:forEach items="${teacherList }" var="teacher">
													  <option value="${teacher.id }"  <c:if test="${teacher.id != null && manageMap.historyTeacher != null && teacher.id == manageMap.historyTeacher }" >selected="selected"</c:if> >${teacher.name }</option>
												  </c:forEach>
												</select>
											</div>
										</div>

										
										<div class="am-form-group am-u-md-12">
											<label for="user-name" class="am-u-sm-3 am-form-label">物理</label>
											<div class="am-u-sm-9">
												<select data-am-selected="{maxHeight: 170}" name="physicsId">
												  <option value="-1">==请选择==</option>
												  
												  	
												  <c:forEach items="${teacherList }" var="teacher">
													  <option value="${teacher.id }"  <c:if test="${teacher.id != null && manageMap.physicsTeacher != null && teacher.id == manageMap.physicsTeacher }" >selected="selected"</c:if> >${teacher.name }</option>
												  </c:forEach>
												</select>
											</div>
										</div>
										
										<div class="am-form-group am-u-md-12">
											<label for="user-name" class="am-u-sm-3 am-form-label">化学</label>
											<div class="am-u-sm-9">
												<select data-am-selected="{maxHeight: 170}" name="chemistryId">
												  <option value="-1">==请选择==</option>
												  
												  	
												  <c:forEach items="${teacherList }" var="teacher">
													  <option value="${teacher.id }"  <c:if test="${teacher.id != null && manageMap.chemistryTeacher != null && teacher.id == manageMap.chemistryTeacher }" >selected="selected"</c:if> >${teacher.name }</option>
												  </c:forEach>
												</select>
											</div>
										</div>
										
										<div class="am-form-group am-u-md-12">
											<label for="user-name" class="am-u-sm-3 am-form-label">地理</label>
											<div class="am-u-sm-9">
												<select data-am-selected="{maxHeight: 170}" name="geographyId">
												  <option value="-1">==请选择==</option>
												  
												  	
												  <c:forEach items="${teacherList }" var="teacher">
													  <option value="${teacher.id }"  <c:if test="${teacher.id != null && manageMap.geographyTeacher != null && teacher.id == manageMap.geographyTeacher }" >selected="selected"</c:if> >${teacher.name }</option>
												  </c:forEach>
												</select>
											</div>
										</div>
										
										<div class="am-form-group am-u-md-12">
											<label for="user-name" class="am-u-sm-3 am-form-label">生物</label>
											<div class="am-u-sm-9">
												<select data-am-selected="{maxHeight: 170}" name="biologyTeacherId">
												  <option value="-1">==请选择==</option>
												  
												  	
												  <c:forEach items="${teacherList }" var="teacher">
													  <option value="${teacher.id }"  <c:if test="${teacher.id != null && manageMap.biologyTeacher != null && teacher.id == manageMap.biologyTeacher }" >selected="selected"</c:if> >${teacher.name }</option>
												  </c:forEach>
												</select>
											</div>
										</div>
										
										<div class="am-form-group am-u-md-12">
											<label for="user-name" class="am-u-sm-3 am-form-label">美术</label>
											<div class="am-u-sm-9">
												<select data-am-selected="{maxHeight: 170}" name="artId">
												  <option value="-1">==请选择==</option>
												  
												  	
												  <c:forEach items="${teacherList }" var="teacher">
													  <option value="${teacher.id }"  <c:if test="${teacher.id != null && manageMap.artTeacher != null && teacher.id == manageMap.artTeacher }" >selected="selected"</c:if> >${teacher.name }</option>
												  </c:forEach>
												</select>
											</div>
										</div>
										
										<div class="am-form-group am-u-md-12">
											<label for="user-name" class="am-u-sm-3 am-form-label">音乐</label>
											<div class="am-u-sm-9">
												<select data-am-selected="{maxHeight: 170}" name="musicId">
												  <option value="-1">==请选择==</option>
												  
												  	
												  <c:forEach items="${teacherList }" var="teacher">
													  <option value="${teacher.id }"  <c:if test="${teacher.id != null && manageMap.musicTeacher != null && teacher.id == manageMap.musicTeacher }" >selected="selected"</c:if> >${teacher.name }</option>
												  </c:forEach>
												</select>
											</div>
										</div>
										
										<div class="am-form-group am-u-md-12">
											<label for="user-name" class="am-u-sm-3 am-form-label">信息技术</label>
											<div class="am-u-sm-9">
												<select data-am-selected="{maxHeight: 170}" name="ITId">
												  <option value="-1">==请选择==</option>
												  
												  	
												  <c:forEach items="${teacherList }" var="teacher">
													  <option value="${teacher.id }"  <c:if test="${teacher.id != null && manageMap.ITTeacher != null && teacher.id == manageMap.ITTeacher }" >selected="selected"</c:if> >${teacher.name }</option>
												  </c:forEach>
												</select>
											</div>
										</div>
										
										<div class="am-form-group am-u-md-12">
											<label for="user-name" class="am-u-sm-3 am-form-label">体育</label>
											<div class="am-u-sm-9">
												<select data-am-selected="{maxHeight: 170}" name="sportId">
												  <option value="-1">==请选择==</option>
												  
												  	
												  <c:forEach items="${teacherList }" var="teacher">
													  <option value="${teacher.id }"  <c:if test="${teacher.id != null && manageMap.sportTeacher != null && teacher.id == manageMap.sportTeacher }" >selected="selected"</c:if> >${teacher.name }</option>
												  </c:forEach>
												</select>
											</div>
										</div>
										
										<div class="am-form-group am-u-md-12">
											<label for="user-name" class="am-u-sm-3 am-form-label">心理健康</label>
											<div class="am-u-sm-9">
												<select data-am-selected="{maxHeight: 170}" name="healthId">
												  <option value="-1">==请选择==</option>
												  
												  	
												  <c:forEach items="${teacherList }" var="teacher">
													  <option value="${teacher.id }"  <c:if test="${teacher.id != null && manageMap.healthTeacher != null && teacher.id == manageMap.healthTeacher }" >selected="selected"</c:if> >${teacher.name }</option>
												  </c:forEach>
												</select>
											</div>
										</div>
										
										<div class="am-form-group am-u-md-12">
											<label for="user-name" class="am-u-sm-3 am-form-label">书法</label>
											<div class="am-u-sm-9">
												<select data-am-selected="{maxHeight: 170}" name="writeId">
												  <option value="-1">==请选择==</option>
												  
												  	
												  <c:forEach items="${teacherList }" var="teacher">
													  <option value="${teacher.id }"  <c:if test="${teacher.id != null && manageMap.writeTeacher != null && teacher.id == manageMap.writeTeacher }" >selected="selected"</c:if> >${teacher.name }</option>
												  </c:forEach>
												</select>
											</div>
										</div>
										<script type="text/javascript">
										$(function() {
											  $('#safe').popover({
											    content: '如无必要,请勿作修改',
											    trigger: 'hover',
											    theme: 'danger'
											  })
											 
											});
										</script>
										<div class="am-form-group am-u-md-12">
											<label for="user-name" class="am-u-sm-3 am-form-label" id="safe">安全法制</label>
											<div class="am-u-sm-9">
												<select data-am-selected="{maxHeight: 170}" name="safeId">
												  <option value="-1">==请选择==</option>
												  
												  	
												  <c:forEach items="${teacherList }" var="teacher">
													  <option value="${teacher.id }"  <c:if test="${teacher.id != null && manageMap.safeTeacher != null && teacher.id == manageMap.safeTeacher }" >selected="selected"</c:if> >${teacher.name }</option>
												  </c:forEach>
												</select>
											</div>
										</div>
										
										<div class="am-form-group am-u-md-12">
											<label for="user-name" class="am-u-sm-3 am-form-label">活动</label>
											<div class="am-u-sm-9">
												<select data-am-selected="{maxHeight: 170}" name="activityId">
												  <option value="-1">==请选择==</option>
												  
												  	
												  <c:forEach items="${teacherList }" var="teacher">
													  <option value="${teacher.id }"  <c:if test="${teacher.id != null && manageMap.activityTeacher != null && teacher.id == manageMap.activityTeacher }" >selected="selected"</c:if> >${teacher.name }</option>
												  </c:forEach>
												</select>
											</div>
										</div>
										
										<div class="am-form-group am-u-md-12" style="text-align: center;">
											<button type="button" class="am-btn am-btn-default am-btn-secondary" onclick="formSubmit()" ><span class="am-icon-save"></span> 保存</button>
										</div>
									</form>
									
									<script type="text/javascript">
										function formSubmit(){
											document.getElementById("classesForm").submit();
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