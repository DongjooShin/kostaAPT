
<%@page import="kosta.apt.service.VoteService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	//String candi_id = request.getParameter("new_id");
	
	VoteService aptService = VoteService.getInstance();
	/*
	//1. 회원의 id 가 null이 아닌지 확인.
	
	if (candi_id == null) {
		out.println("<script>alert('id가 입력되지 않았습니다.');</script>");
		RequestDispatcher rd = request.getRequestDispatcher("voteGroupPresiRegists.jsp");
		rd.forward(request, response);
	} else {
		Integer dupl = aptService.selectOneCandiService(candi_id);
		if (dupl != null) {
			out.println("<script>alert('이미 존재하는 후보 id입니다.');</script>");
			response.sendRedirect("voteGroupPresiRegists.jsp");
		} else {
			Integer re = aptService.searchSymbolService(isymbol);
			Member m = aptService.selectOneMemberService(candi_id);
			//2. symbol이 중복되지 않는지 확인.
			if (re != null) {
				out.println("<script>alert('이미 사용중인 기호 입니다.');</script>");
				response.sendRedirect("voteGroupPresiRegists.jsp");
			} else {
				int no = 0;
				no = aptService.maxCandiNoService() + 1;
				Candidate c = new Candidate(no, "입주자대표", isymbol, eduLevel, job, career, promise, candi_id,
						m.getApt_APTGNo(), img_name);
				aptService.insertCandidateService(c);
				response.sendRedirect("voteGroupPresiRegists.jsp");
			}
		}
	}
	*/
	
	aptService.insertCandidateService(request);
	response.sendRedirect("voteGroupPresiRegists.jsp");
%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
</html>