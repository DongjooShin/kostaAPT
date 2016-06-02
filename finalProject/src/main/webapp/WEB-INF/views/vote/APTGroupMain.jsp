<%@page import="java.util.Random"%>
<%@page import="kosta.apt.domain.vote.APTGroup"%>
<%@page import="java.util.List"%>
<%@page import="kosta.apt.service.VoteService"%>
<%@ page language="java" contentType="text/html; charset=EUC-KR"
    pageEncoding="EUC-KR"%>
    
    <%
    	VoteService aptService = VoteService.getInstance();
    	APTGroup apt = aptService.selectOneAPTService(1);
    	
    	if(apt.getApt_mainImage() == null){
    		Random random = new Random();
    		int data = random.nextInt(7) + 1;
    		
    		APTGroup temp = new APTGroup();
    		temp.setApt_APTGNo(apt.getApt_APTGNo());
    		temp.setApt_mainImage("apartmentGroupMain"+data+".jpg"); 
    		System.out.println(temp.getApt_mainImage());
    		aptService.updateGroupMainImageService(temp);
    	}
    	
    	request.setAttribute("apt", apt);
    %>

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=EUC-KR">
<title>Insert title here</title>
<script type="text/javascript">
function fn_changeImage(){
	var retVal = window.showModalDialog("modal.html",0,"dialogWidth:440px;dialogHeight:400px;scroll=no;resizable=no;");
	alert("showmodal2");
	if(retVal){
		alert(retVal);
	}
}

$('#main').tooltip({
	placement: 'right',
	viewport: {selector: 'body', padding:2}
});
</script>
</head>
<body>
<div style="width:600px; height:700px;border:1px solid #bbb;" id="main" data-toggle="tooltip" data-placement="right" title="클릭으로  메인사진 변경">
<img alt="" src="../images/jeong/${apt.getApt_mainImage() }" onclick="fn_changeImage()">
</div>

</body>
</html>