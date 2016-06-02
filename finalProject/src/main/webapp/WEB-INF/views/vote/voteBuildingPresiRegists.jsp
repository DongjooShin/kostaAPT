<%@page import="java.util.List"%>
<%@page import="kosta.apt.domain.vote.Candidate"%>
<%@page import="kosta.apt.domain.member.Member"%>
<%@page import="kosta.apt.service.VoteService"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
    <%
	VoteService aptService = VoteService.getInstance();
	Member m = aptService.selectOneMemberService("tndusdla92");

	List<Member> gpm = aptService.selectBuildingPresiService(m.getApt_APTGNo());
	 
	Candidate c = new Candidate();
	c.setCd_group("동대표"); 
	c.setApt_APTGNo(m.getApt_APTGNo());
	List<Candidate> candilist = aptService.selectAllEachCandiService(c);
	
	if(!candilist.isEmpty()){
		aptService.updateV2flagService(2,"tndusdla92"); 
	}
	
	request.setAttribute("m_id", m.getM_memberNo());
	request.setAttribute("m", m);
	request.setAttribute("gpm", gpm);
	request.setAttribute("clist", candilist);
%>

<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content= "IE=edge">      <!-- 구형 익스플로러를 위해 써주는 부분 -->
<meta name="viewport" content= "width=device-width, initial-scale=1" >    <!-- 이부분이 바로 반응형 웹!! 디바이스의 가로크기에 맞춰 컨텐트의 가로를 맞춘다. -->

<title>Insert title here</title>
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap-theme.min.css">
<link rel="stylesheet" href="../css/jeong/vote.css">
<link href="../css/bootstrap.min.css" rel ="stylesheet">
<script type="text/javascript">
	function fn_insertForm() {
		$('.insertForm').prepend('<div class="col-md-2"></div><div class="col-md-8" style="padding-top:20px;"><form action="voteInsertCandi2.jsp" method="post" enctype="multipart/form-data"><table border="1px"><tr><td>입주민 선택</td><td>'+
				'<input type="text" name="new_id" style="width:100%;">'+
				'</td></tr><tr><td>이미지파일</td><td id="fileInto" data-toggle="tooltip" data-placement="right" title="jpg,png,gif만 지원. 투명배경 지원X"><input type="file" name="img_name" style="width:100%;"></td></tr>'
				+'<tr><td>기호</td><td><input type="text" name="new_symbol" style="width:100%;"></td></tr>'+
				'<tr><td>학력</td><td><input type="text" name="new_eduLevel" style="width:100%;"></td></tr>'+
				'<tr><td>직업</td><td><input type="text" name="new_job" style="width:100%;"></td></tr><tr><td colspan="2">경력</td></tr>'
				+'<tr><td colspan="2"><textarea rows="7" cols="100%" name="new_career"></textarea></td></tr><tr><td colspan="2">공약</td></tr>'
				+'<tr><td colspan="2"><textarea rows="7" cols="100%" name="new_promise"></textarea> </td></tr></table><br>'+
				'<input type="submit" value="추가" style="margin-left:550px;"><input type="hidden" name="group" value="동대표"></form></div><div class="col-md-2"></div>');
	}
	
	function fn_levelDown2(){
		location.href="levelDown2.jsp";
	}
	
	function fn_cancel(){
		location.href="voteBuildingPresiRegists.jsp";
	}

	function fn_voteStart(){
		location.href="voteBuildingPresi.jsp?changeVflag=3";
	}
	function fn_searching(){
		
	}
	
	$('#putInto').tooltip({
		placement: 'right',
		viewport: {selector: 'body', padding:2}
	});
	$('#fileInto').tooltip({
		placement: 'right',
		viewport: {selector: 'body', padding:2}
	});
</script>

</head>
<body>


<div id="outline">
<!-- manager didn't changed grade who president now --> 

<c:if test="${gpm != null}">
   
   <h2><img alt="" src="../images/jeong/exclamation.png"> 현재 입주자대표를 일반 입주자로 변경해야 후보를 등록할 수 있습니다.</h2>
   <br><br>
   
   <c:forEach items="${gpm}" var="gpms">
   <div class="col-md-4"></div>
   <div class="col-md-2"><span><marquee behavior="alternate" width="280"> ${gpms.m_name} (${gpms.m_buildingNo}동 ${gpms.m_roomNo}호)</marquee></span></div> 
   <div class="col-md-6"></div><br> 
   </c:forEach>
  <br><br>
   <div class="col-md-5"></div>
   <div class="col-md-2"><input type="button" value="모든 권한 하향하기" onclick="fn_levelDown2()"></div>
   <div class="col-md-5"></div>
   <br><br>
</c:if>

<c:if test="${!clist.isEmpty() }">
	<br><br>
	<h2>현재 등록된 후보자</h2><br><br>
	<c:forEach var="cn" items="${clist }">
		<div class="col-md-6">
		<form action="BuildingPresiDelete.jsp?candi_no=${cn.candidateNo }" method="post">
		<table class="candidatelist" border="1px" >
			<tr>
				<td rowspan="4" width="300px">
						<img alt="" src="../upload/${cn.cd_imageName}">
				 </td>
				<td width="100px">분류</td>
				<td width="300px">${cn.cd_group}</td>
			</tr>
			<tr><td>직업</td><td>${cn.cd_job }</td></tr>
			<tr><td>최종학력</td><td>${cn.cd_eduLevel }</td></tr>
			<tr><td>기호</td><td>${cn.cd_symbol }</td></tr>
			<tr><td colspan="3">경력</td></tr>
			<tr><td colspan="3" height="150">${cn.cd_career }</td></tr>
			<tr><td colspan="3">공약</td></tr>
			<tr><td colspan="3" height="150">${cn.cd_promise }</td></tr>
		</table>
		<br><input type="submit" value="삭제하기" style="margin-left:580px;">
		</form>
		</div>
	</c:forEach>
	<br><br>
</c:if>

<div class="insertForm" ></div>
<div class="col-md-5"></div>
<div class="col-md-2" style="padding-top:50px"  id="putInto" data-toggle="tooltip" data-placement="right" title="클릭하면 추가를 위한 폼이 나타납니다.">
<img src="../images/jeong/candidate_plus.png" onclick="fn_insertForm()"></div>
<div class="col-md-5"></div>
<div class="col-md-12"></div>

<div class="col-md-5" ></div>
<div class="col-md-1" style="padding-top:50px"><input type="button" value="투표시작" onclick="fn_voteStart()"></div>
<div class="col-md-1" style="padding-top:50px"><input type="button" value="취소" onclick="fn_cancel()"></div>
<div class="col-md-5"></div>	
</div>	
<br/><br/><br/>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js" ></script>
<script src="../js/gidae/bootstrap.min.js"></script>
<!-- <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>  -->

</body>
</html>