<%@page import="kosta.apt.domain.member.Member"%>
<%@page import="java.util.List"%>
<%@page import="kosta.apt.service.VoteService"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    <%
    	
    	VoteService aptService = VoteService.getInstance();
		
    	Member m = aptService.selectOneMemberService("tndusdla92");
    	
    	aptService.levelDownBuildingPresiService(m.getApt_APTGNo());
    	
		response.sendRedirect("voteBuildingPresiRegists.jsp"); 
    %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>

</body>
</html>