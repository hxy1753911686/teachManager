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
		tr td [type="number"]{
			width: 80px;
		}
	</style>
	<script>
	  	$(function() {
		  $('#honour').popover({
		    content: '由学校出题考核进行评价',
		    trigger: 'click',
		    theme: 'success'
		  })
		 
		});
		$(function() {
		  $('#bigThing').popover({
		    content: '由学校出题考核进行评价',
		    trigger: 'click',
		    theme: 'success'
		  })
		 
		});
		$(function() {
		  $('#upAndDown').popover({
		    content: '每学期由班主任进行考核,学期末汇总</br>凡点名批评者不得分',
		    trigger: 'click',
		    theme: 'success'
		  })
		 
		});
		$(function() {
		  $('#classMeetting').popover({
		    content: '每学期由班主任进行考核,学期末汇总</br>班会表现不好者酌情扣分',
		    trigger: 'click',
		    theme: 'success'
		  })
		 
		});
		$(function() {
		  $('#layer').popover({
		    content: '凡有违纪行为者,扣分<br>&nbsp;&nbsp;|值班教师指出问题,扣除0.5分<br> &nbsp;&nbsp;|打架斗殴直接扣除3分<br> &nbsp;&nbsp;|两次打架事件,得0分',
		    trigger: 'click',
		    theme: 'success'
		  })
		 
		});
		$(function() {
		  $('#activity').popover({
		    content: '班主任考核,一学期学校组织四次集体活动<br>每次积极参加得一分,不守纪律者不得分',
		    trigger: 'click',
		    theme: 'success'
		  })
		 
		});
		$(function() {
		  $('#dignity').popover({
		    content: '无损伤同学自尊的行为可记满分',
		    trigger: 'click',
		    theme: 'success'
		  })
		 
		});
		$(function() {
		  $('#wrongAndRight').popover({
		    content: '虚心接受老师批评即可给分',
		    trigger: 'click',
		    theme: 'success'
		  })
		 
		});
		$(function() {
		  $('#AndOther').popover({
		    content: '由卫生委员记录,学期末与班主任核计<br>打扫卫生多次不参与直接扣除两分',
		    trigger: 'click',
		    theme: 'success'
		  })
		 
		});
		$(function() {
		  $('#classHealth').popover({
		    content: '由值日组长统计并与交与卫生委员<br>学期末卫生委员与班主任核计',
		    trigger: 'click',
		    theme: 'success'
		  })
		 
		});
		$(function() {
		  $('#schoolHealth').popover({
		    content: '由值班教师统计,并与班主任核计',
		    trigger: 'click',
		    theme: 'success'
		  })
		 
		});
		$(function() {
		  $('#attend').popover({
		    content: '值班教师提供者得满分,其他视情况班主任决定',
		    trigger: 'click',
		    theme: 'success'
		  })
		 
		});
		$(function() {
		  $('#clean').popover({
		    content: '由值日组长统计并与交与卫生委员<br>学期末卫生委员与班主任核计',
		    trigger: 'click',
		    theme: 'success'
		  })
		 
		});
		$(function() {
		  $('#learnAttitude').popover({
		    content: '班主任与学习委员共同考核',
		    trigger: 'click',
		    theme: 'success'
		  })
		 
		});
		$(function() {
		  $('#learnHabits').popover({
		    content: '班主任与学习委员共同考核',
		    trigger: 'click',
		    theme: 'success'
		  })
		 
		});
		$(function() {
		  $('#ownLearn').popover({
		    content: '班主任与学习委员共同考核',
		    trigger: 'click',
		    theme: 'success'
		  })
		 
		});
		$(function() {
		  $('#teacherTask').popover({
		    content: '教师反馈,班主任考核',
		    trigger: 'click',
		    theme: 'success'
		  })
		 
		});
		$(function() {
		  $('#moreExam').popover({
		    content: '由教务处提供成绩<br>等次要符合目标要求，最低1分',
		    trigger: 'click',
		    theme: 'success'
		  })
		 
		});
		$(function() {
		  $('#researchLearn').popover({
		    content: '由综合实践课或是任何一门代课老师协助学生选题<br>学生做出研究报告，负责协助选题的老师审核学生资料<br>做出评价共8分，没有选题的学生不得分',
		    trigger: 'click',
		    theme: 'success'
		  })
		 
		});
		$(function() {
		  $('#socialPractice').popover({
		    content: '每学期学校组织一次社会实践活动，如问卷调查等<br>学生负责发放问卷，并收集总结问卷结果做出结论并提出合理化建议，即可得满分',
		    trigger: 'click',
		    theme: 'success'
		  })
		  $('#canjia').popover({
		    content: '每天参加及效果各记0.1<br>期末由班主任统计',
		    trigger: 'click',
		    theme: 'success'
		  })
		  $('#award').popover({
		    content: '获奖记1分,两次以上满分',
		    trigger: 'click',
		    theme: 'success'
		  })
		  $('#doMeat').popover({
		    content: '成功完成一次做饭记1分',
		    trigger: 'click',
		    theme: 'success'
		  })
		  $('#music').popover({
		    content: '由美术与音乐代课教师统计汇总，期末交与班主任核计',
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
									综合评价</div>
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
								    <li class="am-active"><a href="#tab1">公民素养</a></li>
								    <li><a href="#tab2">学习能力</a></li>
								    <li><a href="#tab3">创新实践</a></li>
								    <li><a href="#tab4">运动、健康及艺术素养</a></li>
								  </ul>
								
								  <div class="am-tabs-bd">
								    <div class="am-tab-panel am-fade am-in am-active" id="tab1">
								     <form method="post" id="loveForm" action="estimateAction_saveLoveCountry">
								     <input type="hidden" name="studentId" value="${id }" >
								     <input type="hidden" name="term" value="${term }" >
								     <table class="am-table am-table-bordered am-table-centered am-table-striped am-table-hover">
									  <tr>
									    <th>评价要素</th>
									    <th>评价内容</th>
									    <th>评分/满分</th>
									  </tr>
									  <tr>
									  
									    <td rowspan="6" class="am-text-middle">爱国爱党</td>
									    <td id="honour">维护国家荣耀<span class="am-badge am-badge-warning am-round">note</span></td>
									    <td class=""><input type="number" max="2" min="0" name="honourValue" value="${honourValue }" 
									     	<c:if test="${honourDisabled eq 'yes' }">disabled="disabled"</c:if> 
									      >/2</td>
									  </tr>
									  <tr>
									    <td id="bigThing">关心国家大事<span class="am-badge am-badge-warning am-round">note</span></td>
									    <td ><input type="number" max="2" min="0" name="bigThingValue" value="${ bigThingValue}" 
									    	<c:if test="${bigThingDisabled eq 'yes' }">disabled="disabled"</c:if>
									    >/2</td>
									  </tr>
									  <tr>
									    <td>尊重国旗国徽</td>
									    <td><input type="number" max="2" min="0" name="nationalFlagValue" value="${nationalFlagValue }" 
									    	<c:if test="${nationalFlagDisabled eq 'yes' }">disabled="disabled"</c:if>
									    >/2</td>
									  </tr>
									  <tr>
									    <td>会唱国歌团歌校歌</td>
									    <td><input type="number" max="2" min="0" name="nationalAnthemValue" value="${nationalAnthemValue }" 
									    	<c:if test="${nationalAnthemDisabled eq 'yes' }">disabled="disabled"</c:if>
									    >/2</td>
									  </tr>
									  <tr>
									    <td id="upAndDown">升降国旗有礼仪<span class="am-badge am-badge-warning am-round">note</span></td>
									    <td><input type="number" max="15" min="0" name="upAndDownValue" value="${upAndDownValue }" 
									    	<c:if test="${upAndDownDisabled eq 'yes' }">disabled="disabled"</c:if>
									    >/15</td>
									  </tr>
									  <tr>
									    <td id="classMeetting">班会活动<span class="am-badge am-badge-warning am-round">note</span></td>
									    <td><input type="number" max="15" min="0" name="classMeettingValue" value="${classMeettingValue }" 
									    	<c:if test="${classMeettingDisabled eq 'yes' }">disabled="disabled"</c:if>
									    >/15</td>
									  </tr>
									  
									  <tr>
									    <td rowspan="2" class="am-text-middle">遵纪守法</td>
									    <td id="layer">遵守校纪校规<span class="am-badge am-badge-warning am-round">note</span></td>
									    <td><input type="number" max="5" min="0" step="0.5" name="layerValue" value="${layerValue }"
									    	<c:if test="${layerDisabled eq 'yes' }">disabled="disabled"</c:if>
									     >/5</td>
									  </tr>
									  <tr>
									    <td id="activity">集体活动<span class="am-badge am-badge-warning am-round">note</span></td>
									    <td ><input type="number" max="4" min="0" name="activityValue" value="${activityValue }" 
									    	<c:if test="${activityDisabled eq 'yes' }">disabled="disabled"</c:if>
									    >/4</td>
									  </tr>
									  
									  <tr>
									    <td rowspan="3" class="am-text-middle">明理诚信</td>
									    <td >讲文明,懂礼仪</td>
									    <td><input type="number" max="1" min="0" name="civilizationValue" value="${civilizationValue }" 
									    	<c:if test="${civilizationDisabled eq 'yes' }">disabled="disabled"</c:if>
									    >/1</td>
									  </tr>
									  <tr>
									    <td >讲信用,守诺言</td>
									    <td ><input type="number" max="1" min="0" name="creditValue" value="${ creditValue}" 
									    	<c:if test="${creditDisabled eq 'yes' }">disabled="disabled"</c:if>
									    >/1</td>
									  </tr>
									  <tr>
									    <td >尊敬师长,有感恩之心</td>
									    <td ><input type="number" max="1" min="0" name="respectValue" value="${respectValue }" 
									    	<c:if test="${respectDisabled eq 'yes' }">disabled="disabled"</c:if>
									    >/1</td>
									  </tr>
									  
									  <tr>
									    <td rowspan="3" class="am-text-middle">自尊自卑</td>
									    <td id="dignity">维护人格尊严<span class="am-badge am-badge-warning am-round">note</span></td>
									    <td><input type="number" max="1" min="0" name="dignityValue" value="${dignityValue }"
									    	<c:if test="${dignityDisabled eq 'yes' }">disabled="disabled"</c:if>
									     >/1</td>
									  </tr>
									  <tr>
									    <td >有上进心</td>
									    <td ><input type="number" max="2" min="0" name="desireAdvanceValue" value="${desireAdvanceValue }"
									    	<c:if test="${desireAdvanceDisabled eq 'yes' }">disabled="disabled"</c:if>
									     >/2</td>
									  </tr>
									  <tr>
									    <td id="wrongAndRight">正确对待优缺点,有错就改<span class="am-badge am-badge-warning am-round">note</span></td>
									    <td ><input type="number" max="2" min="0" name="wrongAndRightValue" value="${wrongAndRightValue }"
									    	<c:if test="${wrongAndRightDisabled eq 'yes' }">disabled="disabled"</c:if>
									     >/2</td>
									  </tr>
									  
									  <tr>
									    <td rowspan="2" class="am-text-middle">团队精神</td>
									    <td >关心集体,珍惜荣誉</td>
									    <td><input type="number" max="2" min="0" name="collectiveValue" value="${collectiveValue }"
									    	<c:if test="${collectiveDisabled eq 'yes' }">disabled="disabled"</c:if>
									     >/2</td>
									  </tr>
									  <tr>
									    <td id="AndOther">善于和他人合作共同完成任务<span class="am-badge am-badge-warning am-round">note</span></td>
									    <td ><input type="number" max="2" min="0" name="andOtherValue" value="${andOtherValue }"
									    	<c:if test="${andOtherDisabled eq 'yes' }">disabled="disabled"</c:if>
									     >/2</td>
									  </tr>

								      <tr>
									    <td rowspan="4" class="am-text-middle">环保意识</td>
									    <td id="classHealth">爱护教室环境卫生<span class="am-badge am-badge-warning am-round">note</span></td>
									    <td><input type="number" max="3" min="0" step="0.5" name="classHealthValue" value="${classHealthValue }"
									    	<c:if test="${classHealthDisabled eq 'yes' }">disabled="disabled"</c:if>
									     >/3</td>
									  </tr>
									  <tr>
									    <td id="schoolHealth">爱护校园卫生<span class="am-badge am-badge-warning am-round">note</span></td>
									    <td ><input type="number" max="2" min="0" name="schoolHealthValue" value="${schoolHealthValue }"
									    	<c:if test="${schoolHealthDisabled eq 'yes' }">disabled="disabled"</c:if>
									     >/2</td>
									  </tr>
									  <tr>
									    <td id="attend">积极参加环保活动<span class="am-badge am-badge-warning am-round">note</span></td>
									    <td ><input type="number" max="1" min="0" name="attendValue" value="${attendValue }" 
									    	<c:if test="${attendDisabled eq 'yes' }">disabled="disabled"</c:if>
									    >/1</td>
									  </tr>
									  <tr>
									    <td id="clean">值日次数<span class="am-badge am-badge-warning am-round">note</span></td>
									    <td ><input type="number" max="8" min="0" step="0.5" name="cleanValue" value="${cleanValue }"
									    	<c:if test="${cleanDisabled eq 'yes' }">disabled="disabled"</c:if>
									     >/8</td>
									  </tr>
								     </table>
								     <div class="am-form-group am-u-md-12" style="text-align: center;">
											<button type="button" class="am-btn am-btn-default am-btn-secondary" onclick="formSubmit()" ><span class="am-icon-save"></span> 保存</button>
										</div>
								     </form>
								     
								     <script type="text/javascript">
										
										function formSubmit(){
											document.getElementById("loveForm").submit();
										}
									</script>
								     
								     
								    </div>
								    <div class="am-tab-panel am-fade" id="tab2">
								    <form method="post" id="methodForm" action="estimateAction_saveMethod">
								     <input type="hidden" name="studentId" value="${id }" >
								     <input type="hidden" name="term" value="${term }" >
								    	<table class="am-table am-table-bordered am-table-centered am-table-striped am-table-hover">
									  <tr>
									    <th>评价要素</th>
									    <th>评价内容</th>
									    <th>评分/满分</th>
									  </tr>
									  <tr>
									    <td rowspan="5" class="am-text-middle">方法兴趣</td>
									    <td id="learnAttitude">学习态度积极<span class="am-badge am-badge-warning am-round">note</span></td>
									    <td ><input type="number" max="2" min="0"  name="learnAttitudeValue" value="${learnAttitudeValue }"
									    	<c:if test="${learnAttitudeDisabled eq 'yes' }">disabled="disabled"</c:if>
									     >/2</td>
									  </tr>
									  <tr>
									    <td id="learnHabits">学习习惯良好<span class="am-badge am-badge-warning am-round">note</span></td>
									    <td ><input type="number" max="1" min="0" name="learnHabitsValue" value="${learnHabitsValue }"
									    	<c:if test="${learnHabitsDisabled eq 'yes' }">disabled="disabled"</c:if>
									     >/1</td>
									  </tr>
									  <tr>
									    <td id="ownLearn">有自主学习能力<span class="am-badge am-badge-warning am-round">note</span></td>
									    <td><input type="number" max="2" min="0" name="ownLearnValue" value="${ownLearnValue }" 
									    	<c:if test="${ownLearnDisabled eq 'yes' }">disabled="disabled"</c:if>
									    >/2</td>
									  </tr>
									  <tr>
									    <td id="teacherTask">能够完成老师下达的学习任务<span class="am-badge am-badge-warning am-round">note</span></td>
									    <td><input type="number" max="2" min="0" name="teacherTaskValue" value="${teacherTaskValue }"
									    	<c:if test="${teacherTaskDisabled eq 'yes' }">disabled="disabled"</c:if>
									     >/2</td>
									  </tr>
									  <tr>
									    <td id="moreExam">阶段性考试成绩良好<span class="am-badge am-badge-warning am-round">note</span></td>
									    <td><input type="number" max="2" min="1" name="moreExamValue" value="${moreExamValue }"
									    	<c:if test="${moreExamDisabled eq 'yes' }">disabled="disabled"</c:if>
									     >/2</td>
									  </tr>
								     </table>
								     <div class="am-form-group am-u-md-12" style="text-align: center;">
											<button type="button" class="am-btn am-btn-default am-btn-secondary" onclick="formSubmit2()" ><span class="am-icon-save"></span> 保存</button>
										</div>
								     </form>
								     <script type="text/javascript">
										
										function formSubmit2(){
											document.getElementById("methodForm").submit();
										}
									</script>
								    </div>
								    <div class="am-tab-panel am-fade" id="tab3">
								    	<form method="post" id="studyForm" action="estimateAction_saveStudy">
								     <input type="hidden" name="studentId" value="${id }" >
								     <input type="hidden" name="term" value="${term }" >
								    	<table class="am-table am-table-bordered am-table-centered am-table-striped am-table-hover">
									  <tr>
									    <th>评价要素</th>
									    <th>评价内容</th>
									    <th>评分/满分</th>
									  </tr>
									  <tr>
									    <td rowspan="4" class="am-text-middle" id="researchLearn">研究性学习<span class="am-badge am-badge-warning am-round">note</span></td>
									    <td >选择好课题</td>
									    <td ><input type="number" max="1" min="0" name="topicValue" value="${topicValue }"  
									    	<c:if test="${topicDisabled eq 'yes' }">disabled="disabled"</c:if>
									    >/1</td>
									  </tr>
									  <tr>
									    <td >制定计划</td>
									    <td ><input type="number" max="1" min="0" name="planValue" value="${planValue }" 
									    	<c:if test="${planDisabled eq 'yes' }">disabled="disabled"</c:if>
									    >/1</td>
									  </tr>
									  <tr>
									    <td >过程性资料</td>
									    <td><input type="number" max="3" min="0" name="processValue" value="${processValue }"
									    	<c:if test="${processDisabled eq 'yes' }">disabled="disabled"</c:if>
									    >/3</td>
									  </tr>
									  <tr>
									    <td >研究报告</td>
									    <td><input type="number" max="3" min="0" name="researchValue" value="${researchValue }"
									    	<c:if test="${researchDisabled eq 'yes' }">disabled="disabled"</c:if>
									    >/3</td>
									  </tr>
									  
									  <tr>
									    <td rowspan="3" class="am-text-middle" id="socialPractice">社会实践<span class="am-badge am-badge-warning am-round">note</span></td>
									    <td >有照片，视频</td>
									    <td ><input type="number" max="2" min="0"  name="photoValue" value="${photoValue }"
									    	<c:if test="${photoDisabled eq 'yes' }">disabled="disabled"</c:if>
									    >/2</td>
									  </tr>
									  <tr>
									    <td >总结</td>
									    <td ><input type="number" max="3" min="0" name="summaryValue" value="${summaryValue }"
									    	<c:if test="${summaryDisabled eq 'yes' }">disabled="disabled"</c:if>
									    >/3</td>
									  </tr>
									  <tr>
									    <td >科技创新</td>
									    <td><input type="number" max="3" min="0" name="scienceValue" value="${scienceValue }"
									    	<c:if test="${scienceDisabled eq 'yes' }">disabled="disabled"</c:if>
									    >/3</td>
									  </tr>
									  
								     </table>
								     <div class="am-form-group am-u-md-12" style="text-align: center;">
											<button type="button" class="am-btn am-btn-default am-btn-secondary" onclick="formSubmit3()" ><span class="am-icon-save"></span> 保存</button>
										</div>
								     </form>
								     <script type="text/javascript">
										
										function formSubmit3(){
											document.getElementById("studyForm").submit();
										}
									</script>
								    	
								    </div>
								    
								    <div class="am-tab-panel am-fade" id="tab4">
								    <form method="post" id="healthForm" action="estimateAction_saveHealth">
								     <input type="hidden" name="studentId" value="${id }" >
								     <input type="hidden" name="term" value="${term }" >
								    	<table class="am-table am-table-bordered am-table-centered am-table-striped am-table-hover">
									  <tr>
									    <th>评价要素</th>
									    <th>评价内容</th>
									    <th>评分/满分</th>
									  </tr>
									  <tr>
									    <td rowspan="1" class="am-text-middle">两操</td>
									    <td id="canjia">参加与效果<span class="am-badge am-badge-warning am-round">note</span></td>
									    <td ><input type="number" max="20" min="0"  name="canjiaValue" value="${canjiaValue }"
									    	<c:if test="${canjiaDisabled eq 'yes' }">disabled="disabled"</c:if>
									     >/20</td>
									  </tr>
									  <tr>
									    <td rowspan="1" class="am-text-middle">运动会</td>
									    <td id="award">获奖<span class="am-badge am-badge-warning am-round">note</span></td>
									    <td ><input type="number" max="2" min="0"  name="awardValue" value="${awardValue }"
									    	<c:if test="${awardDisabled eq 'yes' }">disabled="disabled"</c:if>
									     >/2</td>
									  </tr>
									  <tr>
									    <td rowspan="1" class="am-text-middle">家务劳动</td>
									    <td id="doMeat">做饭<span class="am-badge am-badge-warning am-round">note</span></td>
									    <td ><input type="number" max="3" min="0"  name="doMeatValue" value="${doMeatValue }"
									    	<c:if test="${doMeatDisabled eq 'yes' }">disabled="disabled"</c:if>
									     >/3</td>
									  </tr>
									  <tr>
									    <td rowspan="1" class="am-text-middle">技能测评</td>
									    <td id="music">音乐或美术技能<span class="am-badge am-badge-warning am-round">note</span></td>
									    <td ><input type="number" max="5" min="0"  name="musicValue" value="${musicValue }"
									    	<c:if test="${musicDisabled eq 'yes' }">disabled="disabled"</c:if>
									     >/5</td>
									  </tr>
								     </table>
								     <div class="am-form-group am-u-md-12" style="text-align: center;">
											<button type="button" class="am-btn am-btn-default am-btn-secondary" onclick="formSubmit4()" ><span class="am-icon-save"></span> 保存</button>
										</div>
								     </form>
								     <script type="text/javascript">
										
										function formSubmit4(){
											document.getElementById("healthForm").submit();
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