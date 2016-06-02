<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
   
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>    
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content= "IE=edge">      <!-- 구형 익스플로러를 위해 써주는 부분 -->
<meta name="viewport" content= "width=device-width, initial-scale=1" >    <!-- 이부분이 바로 반응형 웹!! 디바이스의 가로크기에 맞춰 컨텐트의 가로를 맞춘다. -->
<title>Group President Vote</title>
<link rel="stylesheet" href="/resources/voteCss/vote.css"> 
<link rel='stylesheet' id='spacious-genericons-css'
	href='/resources/bootstrap/bootstrap.min.css' type='text/css' media='all' />
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
	
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
		<div class="col-md-8">
			<c:choose>
			<%-- ****************************CASE : MANAGER************************ --%>
				<c:when test="${m.getM_grade() == 2 }">
					<c:choose>
					<%-- ......FLAG 1 : NO ELECTION. THERE IS GROUP PRESI....... --%>
						<c:when test="${m.getV_flag() == 1 }">
							<form action="/voter/groupPresiRegist">
								<input type="submit" value="후보자 등록" id="candiRegiButton" class="btn btn-primary btn-lg" >
							</form>
						</c:when>
					<%-- ......FLAG 2 : THIS CASE PROCESS IN CONTROLLER....... --%>					

					<%-- ......FLAG 3 : ELECTION IS PROCESSING...... --%>	
						<c:when test="${m.getV_flag() == 3 }">
							<br><br><br>
							<h2>선거 진행 현황</h2><br>
							<div class="col-md-2"></div>
							<div class="col-md-8">
								<form action="voteGroupPresi.jsp?changVflag=4" method="post">
									<div class="progress">
										<div class="progress-bar" role="progressbar" aria-valuenow="2" 
											aria-valuemin="0" aria-valuemax="100" style="min-width: 2em; width: ${voterate}%;">
													 ${voterate} %
										</div>
									</div>
									<h4>현재 총 실 가구수 <b>${groupNum }</b> 가구 중  <b>${voterNum }</b> 가구의 투표로 ${voterate }% 의 투표가 이루어졌습니다.</h4>
									<input type="hidden" name="m_id" value="${m.getM_memberNo() }">
									<input type="hidden" name="changeVflag" value="4">
									<div class="col-md-5"></div>
									<div class="col-md-2" style="padding-left:0px;"><input id="closeElection" type="submit" value="선거종료"></div>
									<div class="col-md-5"></div>
								</form></div>
							<div class="col-md-2"></div>
						</c:when>
					<%-- ......FLAG 4 : RESULT IS NOTICING NOW...... --%>	
						<c:when test="${m.getV_flag() == 4 }">
						
						</c:when>
					</c:choose>
				</c:when>
			<%-- **************END OF MANAGER CASE*************** --%>	
			
			<%-- ***********************CASE : RESIDENT************************** --%>	
				<c:otherwise>
				
			
				</c:otherwise>
			<%-- ***********END OF RESIDENT CASE***************** --%>	
			</c:choose>
		</div>
	</div>


<%-- ----------------------Footer include----------------- --%>
	<div class = "footer">
	
	</div>



<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js" ></script>

</body>
</html>