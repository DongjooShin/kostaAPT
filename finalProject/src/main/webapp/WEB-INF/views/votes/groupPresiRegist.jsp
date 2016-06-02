<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content= "IE=edge">      <!-- 구형 익스플로러를 위해 써주는 부분 -->
<meta name="viewport" content= "width=device-width, initial-scale=1" >    <!-- 이부분이 바로 반응형 웹!! 디바이스의 가로크기에 맞춰 컨텐트의 가로를 맞춘다. -->
<title>Insert title here</title>

<script type="text/javascript">
function fn_levelDown(){
	location.href="voter/levelDown";
}
</script>
</head>
<body>
<%-- -----------------Header include----------------- --%>	
	<div class="header">
		 <jsp:include page="../include/head.jsp"></jsp:include> 
	</div>	

<%-- ------------------------------Body------------------------------------------- --%>
	<div id= "page">
		<div class="col-md-3">
					<jsp:include page="../Community/CommunitySidebar.jsp"></jsp:include>
		</div>
		<div class="col-md-8" style="margin-top:50px;margin-left:10px;">
		<%-- -----------IF GROUPPRESI EXIST-------------- --%>
			<c:if test="${gpm != null}">
			   
			   <h3><img alt="" src="/resources/images/vote/exclamation.png"> 현 입주자대표를 일반 입주자로 변경해야 후보를 등록할 수 있습니다.</h3>
			   <br>
			   <div class="col-md-4"></div>
			   <div class="col-md-2"><span><marquee behavior="alternate" width="280"> ${gpm.getM_name()} (${gpm.getM_buildingNo()}동 ${gpm.getM_roomNo()}호)</marquee></span></div> 
			   <div class="col-md-6"></div><br><br>
			   <div class="col-md-5"></div>
			   <div class="col-md-2"><input type="button" value="권한 하향하기" onclick="fn_levelDown()"></div>
			   <div class="col-md-5"></div>
			   <br><br>
			</c:if>
			
		<%-- -------------LIST  --%>	
		</div>
	</div>	
<%-- ----------------------Footer include----------------- --%>
	<div class = "footer">
	
	</div>

</body>
</html>