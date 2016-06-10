<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
    
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body style="background-color: #eaeaea;">
<%-- -----------------Header include----------------- --%>	
	<div class="header">
		 <jsp:include page="../include/head.jsp"></jsp:include> 
	</div>
	
<%-- ------------------------------Body------------------------------------------- --%>	
	<div class="main" id="page">
		<div class="container">
			<div class="col-md-3">
				<jsp:include page="../siteNotice/CustomerSidebar.jsp"></jsp:include>
			</div>
			
			<!-- 리스트,입력,상세보기 -->
			<div class="col-md-8" style="margin-top: 50px; margin-left:50px;">
				<c:choose>	
					<%-- ----------CASE : INQUIRY BY MEMBER---------------- --%>
					<c:when test="${member.m_grade != 0 }">
							<jsp:include page="/resources/editor/InqSmartEditor.jsp"></jsp:include>		
					</c:when>
					<%-- ----------CASE : ANSWER BY SITE MANAGER--------------- --%>
					<c:otherwise>
					
					
					
					</c:otherwise>
				</c:choose>
			</div>
		</div>
			
	</div>
	
<%-- ----------------------Footer include----------------- --%>
	<div class = "footer">
	
	</div>
	
</body>
</html>