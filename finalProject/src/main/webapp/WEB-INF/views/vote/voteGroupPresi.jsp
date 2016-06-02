<%@page import="kosta.apt.domain.vote.Voter"%>
<%@page import="java.util.HashMap"%>
<%@page import="java.util.ArrayList"%>
<%@page import="kosta.apt.domain.vote.Candidate"%>
<%@page import="java.util.List"%>
<%@page import="kosta.apt.domain.member.Member"%>
<%@page import="kosta.apt.service.VoteService"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
	pageEncoding="EUC-KR"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%
	String vf = request.getParameter("changeVflag");		
	HashMap<String,Integer> hash = new HashMap<String,Integer>();
	
	int changeVflag = 0;
	if(vf != null){
		changeVflag = Integer.parseInt(vf);
		aptService.updateVflagService(changeVflag, "tndusdla92");
	}
	Member m = aptService.selectOneMemberService("tndusdla92"); 
	//rleo91
	if(m.getM_grade() == 2){	
		if(changeVflag == 1){
			hash.put("vflag", 1);
			hash.put("groupNo", m.getApt_APTGNo());
			aptService.updateAllResiVflagService(hash);
			aptService.updateVflagService(changeVflag, "tndusdla92");
		}else if(changeVflag == 3){
			hash.put("vflag", 2);
			hash.put("groupNo", m.getApt_APTGNo());
			aptService.updateAllResiVflagService(hash);
			aptService.updateVflagService(changeVflag, "tndusdla92");
		}else if(changeVflag == 4){
			hash.put("vflag", 4);
			hash.put("groupNo", m.getApt_APTGNo());
			aptService.updateAllResiVflagService(hash);
			aptService.updateVflagService(changeVflag, "tndusdla92");
			String newGroupPresi = aptService.selectTopGroupCandiService(m.getApt_APTGNo());
			aptService.updateGPGradeService(newGroupPresi);
		}
	}
	Candidate c = new Candidate();
	c.setApt_APTGNo(m.getApt_APTGNo());
	c.setCd_group("�����ڴ�ǥ");
	
	List<Candidate> groupPresi = aptService.selectAllEachCandiService(c); 
	
	int groupNum = aptService.totalGroupNumService(m.getApt_APTGNo());
	int voterNum = aptService.totalGPVoterNum(m.getApt_APTGNo());
 
	for(int i=0; i<groupPresi.size(); i++){
		String s = groupPresi.get(i).getM_memberNo();
		Member mtemp = aptService.selectOneMemberService(s);
		groupPresi.get(i).setCd_group(mtemp.getM_name());
		
		int t = (groupPresi.get(i).getCd_voteNum()*100)/groupNum;
		groupPresi.get(i).setApt_APTGNo(t);	//��ǥ%�� �ӽ�����.
	}
	
	int rate = voterNum*100/groupNum;
//----------------------------------------------------------------------------
	
//------------------- resident's code ------------------------------------
	Member gp = aptService.selectGroupPresiService(m.getApt_APTGNo());
	
	for(int i=0; i<groupPresi.size(); i++){
		Member mtemp = aptService.selectOneMemberService(groupPresi.get(i).getM_memberNo());
		groupPresi.get(i).setCandidateNo(mtemp.getM_buildingNo());	//��
		groupPresi.get(i).setCd_voteNum(mtemp.getM_roomNo());		//ȣ�� 	�ӽ�����
	}
	
	Voter v = new Voter();
	v.setApt_APTGNo(m.getApt_APTGNo());
	v.setV_group(1); 
	List<Voter> vlist = aptService.selectAllVoterService(v);
	
//-----------------------------------------------------------------------	

	request.setAttribute("groupNum", groupNum);
	request.setAttribute("voterNum", voterNum);
	request.setAttribute("m", m);
	request.setAttribute("voterate",rate);
	request.setAttribute("groupPresi",groupPresi);
	request.setAttribute("ngp", gp);
	request.setAttribute("gpSize", groupPresi.size());

	request.setAttribute("vlist", vlist);
%>

<%
	//about v_flag
	//case:manager
	//1: there are no election. candidate regist button here
	//2: manager is ongoing to put candidate. if manager changed that all president's grade, then v_flag will changed 2
	//3: manager finished putting candidate and start election already. election is ongoing 
	//4: election is end. result is noticing. if manager click the finish noticing, v_flag will change 1	
	
	//case:resident
	//1: there are no election. Two president's information here
	//2: election is start. And resident is not vote yet.
	//3: election is ongoing. And resident is already votes. resident can see real time result
	//4: election is end. Result is noticing. if result noticing is finished, v_flag will change 1
		
%>

<html>
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content= "IE=edge">      <!-- ���� �ͽ��÷η��� ���� ���ִ� �κ� -->
<meta name="viewport" content= "width=device-width, initial-scale=1" >    <!-- �̺κ��� �ٷ� ������ ��!! ����̽��� ����ũ�⿡ ���� ����Ʈ�� ���θ� �����. -->

<title>Insert title here</title>
<link rel="stylesheet" href="../css/jeong/vote.css"> 
<link href="../css/bootstrap.min.css" rel ="stylesheet">
<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<script type="text/javascript">

function fn_initialize(){
	location.href="voteGroupPresi.jsp?changeVflag=1";
}

</script>

</head>
<body>

	<c:choose>
		<c:when test="${m.getM_grade() == 2 }"> <!-- case: manager -->
			<c:choose>
				<c:when test="${m.getV_flag() == 1 }">
					<form class="vote_back" action="voteGroupPresiRegists.jsp" method="post">
							<input type="hidden" name="m_id" value="${m.getM_memberNo() }">
							<input id="candiRegiButton" class="btn btn-primary btn-lg" type="submit" value="�ĺ��� ���">
					</form>
						<br><br><br>
				</c:when>
				<c:when test="${m.getV_flag() == 2 }">
					<%response.sendRedirect("voteGroupPresiRegists.jsp"); %>
				</c:when>
				<c:when test="${m.getV_flag() == 3 }">
					<br><br><br>
					<h2>���� ���� ��Ȳ</h2><br>
					<div class="col-md-2"></div>
					<div class="col-md-8">
						<form action="voteGroupPresi.jsp?changVflag=4" method="post">
							<div class="progress">
								<div class="progress-bar" role="progressbar" aria-valuenow="2" 
									aria-valuemin="0" aria-valuemax="100" style="min-width: 2em; width: ${voterate}%;">
											 ${voterate} %
								</div>
							</div>
							<h4>���� �� �� ������ <b>${groupNum }</b> ���� ��  <b>${voterNum }</b> ������ ��ǥ�� ${voterate }% �� ��ǥ�� �̷�������ϴ�.</h4>
							<input type="hidden" name="m_id" value="${m.getM_memberNo() }">
							<input type="hidden" name="changeVflag" value="4">
							<div class="col-md-5"></div>
							<div class="col-md-2" style="padding-left:0px;"><input id="closeElection" type="submit" value="��������"></div>
							<div class="col-md-5"></div>
						</form></div>
					<div class="col-md-2"></div>
				</c:when>
				<c:when test="${m.getV_flag() == 4 }">
					<br><h2><img alt="" src="../images/jeong/fireworks.png">��������<img alt="" src="../images/jeong/fireworks.png"></h2>
					<br><br><h3>���Ű��</h3><br><br>
					<div class="col-md-3"></div>
					<div class="col-md-6">
						<div class="hGraph">
							<ul>
								<c:forEach var="gp" items="${groupPresi }">
									<li><span class="gTerm">${gp.cd_group }</span>
									<span class="gBar" style="width:${gp.apt_APTGNo }">
									<span> ${gp.apt_APTGNo }%</span>
									</span></li>
								</c:forEach>
							</ul>
						</div>
						<br><br>
					</div>
					<div class="col-md-3"></div>
					<div class="col-md-12" style="margin:20 0 20 0;"><h4>������ǥ��</h4></div>
					<div class="col-md-2"></div>
					<div class="col-md-8">
							<div class="progress">
								<div class="progress-bar" role="progressbar" aria-valuenow="2" 
									aria-valuemin="0" aria-valuemax="100" style="min-width: 2em; width: ${voterate}%;">
											 ${voterate} %
								</div>
							</div>
							<h4 style="margin-top:30;">�� �� ������ <b>${groupNum }</b><br></h4>
							<h4 style="margin-top:5;">��ǥ������ <b>${voterNum }</b><br></h4>
							<h4 style="margin-top:5;">��ǥ�� <b>${voterate }%</b><br></h4>
					</div>
					<div class="col-md-2"></div>
					<br><br>
					<div class="col-md-5"></div>
					<div class="col-md-2" style="padding-left:0px;">
					<input type="button" value="����Խ�����" onclick="fn_initialize()" style="margin-top:20px;margin-left:50px;">
					</div>
					<div class="col-md-5"></div>
				</c:when>
			</c:choose>
		</c:when>

		<c:otherwise>
			<!-- case: resident -->
			<c:choose>
				<c:when test="${m.getV_flag() == 1 }">		
					<br><h2>�츮 ������ ������ ��ǥ</h2><br><br>
					<table class="nowPresi">
						<tr><td colspan="2">${ngp.getM_name()}</td></tr>
						<tr><td colspan="2">${ngp.getM_memberNo() }</td></tr>
						<tr><td>${ngp.getM_buildingNo()} ��</td><td>${ngp.getM_roomNo() } ȣ</td></tr>
						<tr><td colspan="2">${ngp.getM_age() } ��</td></tr>
						<tr><td colspan="2">${ngp.getM_email() }</td></tr>
					</table>
				</c:when>	
				<c:when test="${m.getV_flag() == 2 }">
					<c:forEach var="br" items="${vlist }">
						<c:if test="${m.m_buildingNo == br.v_buildingNo }">
							<c:if test="${m.m_roomNo == br.v_roomNo }">
								<% response.sendRedirect("voteGroupPresi.jsp?changeVflag=3"); %>
							</c:if>
						</c:if>
					</c:forEach>
					<br><h2>�����ڴ�ǥ ���Ű� �������Դϴ�.</h2><br><br>
					<h4>�������� ������ �� ǥ�� �츮 ����Ʈ�� ������ ����� �����ϴ�.</h4><br>
					<form action="voteSubmitOk.jsp" method="post">
						<div class="container">
							<c:forEach var="cn" items="${groupPresi }">
								<div class="col-md-4" >
									<div class="radio">
										<table class="voteCandiList" style="width:250px; height:400px">
											<tr><td><img alt="" src="../upload/${cn.cd_imageName}"></td></tr>
											<tr><td>��ȣ ${cn.cd_symbol} ��</td></tr>
											<tr><td>${cn.candidateNo} �� ${cn.cd_voteNum } ȣ</td></tr>
											<tr><td style="padding:0 0 7 30;"><input type="radio" name="onechoose" value="${cn.cd_symbol }"></td></tr>
										</table>
									</div>
								</div>
							</c:forEach>
						</div>
						
							
						<br><br>
						<div class="col-md-4"></div>
						<div class="col-md-1"><input type="text" style="margin-right:50px" name = "pass" placeholder="��й�ȣ"></div>
						<div class="col-md-1"></div>
						<div class="col-md-1"><input type="submit" value="����"></div>
						<div class="col-md-5"></div>
						<br><Br>
						
					</form>
				</c:when>
				<c:when test="${m.getV_flag() == 3 }">
					<div style="width:100%; height:100%;">
					<marquee direction="up" behavior="alternate" height="100%"><marquee behavior="alternate" height="100%">
						<h1><img alt="" src="../images/jeong/progressing1.png" width="100px">�츮 ������ ��ǥ�� �������Դϴ�.<img alt="" src="../images/jeong/progressing2.png" width="100px"> </h1>
					</marquee></marquee>
					<br><br>	
					</div>
					
				</c:when>
				<c:when test="${m.getV_flag() == 4 }">
					<br><h2><img alt="" src="../images/jeong/fireworks.png">��������<img alt="" src="../images/jeong/fireworks.png"></h2>
					<br><br><h3>���Ű��</h3><br><br>
					<div class="col-md-3"></div>
					<div class="col-md-6">
						<div class="hGraph">
							<ul>
								<c:forEach var="gp" items="${groupPresi }">
									<li><span class="gTerm">${gp.cd_group }</span>
									<span class="gBar" style="width:${gp.apt_APTGNo }">
									<span> ${gp.apt_APTGNo }%</span>
									</span></li>
								</c:forEach>
							</ul>
						</div>
						<br><br>
					</div>
					<div class="col-md-3"></div>
					<div class="col-md-12" style="margin:20 0 20 0;"><h4>������ǥ��</h4></div>
					<div class="col-md-2"></div>
					<div class="col-md-8">
							<div class="progress">
								<div class="progress-bar" role="progressbar" aria-valuenow="2" 
									aria-valuemin="0" aria-valuemax="100" style="min-width: 2em; width: ${voterate}%;">
											 ${voterate} %
								</div>
							</div>
							<h4 style="margin-top:30;">�� �� ������ <b>${groupNum }</b><br></h4>
							<h4 style="margin-top:5;">��ǥ������ <b>${voterNum }</b><br></h4>
							<h4 style="margin-top:5;">��ǥ�� <b>${voterate }%</b><br></h4>
					</div>
					<div class="col-md-2"></div>
					<br><br>
				</c:when>
			</c:choose>
		</c:otherwise>
	</c:choose>


<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js" ></script>
<script src="../js/gidae/bootstrap.min.js"></script>
</body>
</html>