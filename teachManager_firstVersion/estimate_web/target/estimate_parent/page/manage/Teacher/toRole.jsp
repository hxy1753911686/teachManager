<%@ page contentType="text/html; charset=utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
	<link rel="stylesheet"
		href="${pageContext.request.contextPath}/assets/css/amazeui.min.css" />
	<link rel="stylesheet"
		href="${pageContext.request.contextPath}/assets/css/amazeui.datatables.min.css" />
	<link rel="stylesheet"
		href="${pageContext.request.contextPath}/assets/css/app.css">
	<script src="${pageContext.request.contextPath}/js/jquery-1.11.0.min.js"></script>
</head>

<script type="text/javascript">
     window.location.href = "userAction_update?id="+${userId};		//javascript页面跳转
</script>
<body>

	得到页面啦
<script
	src="${pageContext.request.contextPath}/assets/js/amazeui.min.js"></script>
<script
	src="${pageContext.request.contextPath}/assets/js/amazeui.datatables.min.js"></script>
<script
	src="${pageContext.request.contextPath}/assets/js/dataTables.responsive.min.js"></script>
<script src="${pageContext.request.contextPath}/assets/js/app.js"></script>
</body>

</html>