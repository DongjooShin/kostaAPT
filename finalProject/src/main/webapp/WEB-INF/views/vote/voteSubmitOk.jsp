
<%@page import="kosta.apt.domain.vote.Voter"%>
<%@page import="kosta.apt.domain.member.Member"%>
<%@page import="kosta.apt.service.VoteService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<%
	String[] id = request.getParameterValues("onechoose");
	String pass = request.getParameter("pass");
	VoteService aptService = VoteService.getInstance();
	Member mpass = aptService.selectOneMemberService("rleo91");
	if (pass.equals(mpass.getM_pass())) {
		for (int i = 0; i < id.length; i++) {
			System.out.println(id[i]);
		}
		int hit = 0;
		if (id != null) {
			hit = Integer.parseInt(id[0]);
		}

		aptService.updateHitService(hit);

		aptService.updateVflagService(3, "ehdwn89");
		Member m = aptService.selectOneMemberService("ehdwn89");

		Voter v = new Voter(aptService.maxVoterNoService()+1, m.getM_buildingNo(), m.getM_roomNo(),
				m.getM_memberNo(), m.getApt_APTGNo(),1);
		aptService.insertVoterService(v);

		response.sendRedirect("voteGroupPresi.jsp?changeVflag=3");
	}
%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
</head>
<body>
	<script type="text/javascript">
		alert("잘못된 비밀번호입니다.");
		history.go(-1);
	</script>
</body>
</html>