<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    
<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content= "IE=edge">      <!-- 구형 익스플로러를 위해 써주는 부분 -->
<meta name="viewport" content= "width=device-width, initial-scale=1" >    <!-- 이부분이 바로 반응형 웹!! 디바이스의 가로크기에 맞춰 컨텐트의 가로를 맞춘다. -->

<link rel="stylesheet" href="/resources/voteCss/vote.css"> 
<link rel='stylesheet' id='spacious-genericons-css'
	href='/resources/bootstrap/bootstrap.min.css' type='text/css' media='all' />
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">

<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js" ></script>	

<title>Building President Main</title>
</head>
<body>
<%-- -----------------Header include----------------- --%>	
	<div class="header">
		 <jsp:include page="head.jsp"></jsp:include> 
	</div>	
<%-- ------------------------------Body------------------------------------------- --%>
	<div id= "page">
		<div class="col-md-3">
			<jsp:include page="../Community/CommunitySidebar.jsp"></jsp:include>
		</div>
		<div class="col-md-8">
			<c:choose>
			
			
			</c:choose>
		</div>
	</div>		
<%-- ----------------------Footer include----------------- --%>
	<div class = "footer">
	
	</div>
</body>
</html>