<%@page import="kosta.apt.service.VoteService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    
    <%
    	String no = request.getParameter("candi_no");
    	int candidateNo=0;
    	if(no != null){
    		candidateNo = Integer.parseInt(no);
    	}
    	VoteService aptService = VoteService.getInstance();
    	aptService.deleteCandidateService(candidateNo); 
    	
		response.sendRedirect("voteBuildingPresiRegists.jsp");
    	
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

</body>
</html>