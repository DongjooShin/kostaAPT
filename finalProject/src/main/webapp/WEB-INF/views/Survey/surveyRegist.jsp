<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">


<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.11.0/jquery.min.js"></script>
<script src="/resources/dist/js/Min/script2.js"></script>
<link type="text/css" rel="stylesheet" href="../css/Min/survey/styles.css"/>
<title>jQuery To Do List Plugin Demo</title>
<link rel="stylesheet" href="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/css/bootstrap.min.css">
<script src="http://netdna.bootstrapcdn.com/bootstrap/3.1.1/js/bootstrap.min.js"></script>
<!-- Custom CSS -->
<link rel="stylesheet" href="../css/Min/survey/styles.css">
<link href="http://www.jqueryscript.net/css/jquerysctipttop.css" rel="stylesheet" type="text/css">

<link rel="stylesheet" href="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/css/bootstrap.min.css">
<script src="http://maxcdn.bootstrapcdn.com/bootstrap/3.2.0/js/bootstrap.min.js"></script>
<script src="http://code.jquery.com/jquery-latest.min.js"></script>

</head>

<body>


<div class="container" id="main">





<h1>설문지 작성하기</h1>


<form role ="form" id="main_input_box" >

<input type="text" class ="form-control" id="custom_textbox" name="Item" placeholder="설문지 질문을 적으세요!">

<input type="hidden" name="count" value="count">
<input type="submit" value="Add" class="btn btn-primary add_button" >

</form>

<form action="/Survey/surveyRegist" method="post">

<h1>설문지 제목
	<input type="text" name="su_title" placeholder="설문지 제목을 입하세요">
</h1>



<ol class="list-group list_of_items">

</ol>

<div>
시작일 
<select class="form-control2" name="st1" id="select">


<%
	for(int i=1; i<13;i++){
		%>	
		
		<option><%=i %></option>
<%		
	}
		%>
</select>월

<select class="form-control2" name="st2" id="select">
<%
	for(int i=1; i<32;i++){
		%>	
		
		<option><%=i %></option>
<%		
	}
		%>
</select>일

종료일
<select class="form-control2" name="end1" id="select">

<%
	for(int i=1; i<13;i++){
		%>	
		
		<option><%=0+i %></option>
<%		
	}
		%>
</select>월

<select class="form-control2" name="end2" id="select">
<%
	for(int i=1; i<32;i++){
		%>	
		
		<option><%=i %></option>
<%		
	}
		%>
</select>일



</div>

<div>
<input type="submit" value="설문지 작성완료">
</div>

</form>

</div>
<script type="text/javascript">

  var _gaq = _gaq || [];
  _gaq.push(['_setAccount', 'UA-36251023-1']);
  _gaq.push(['_setDomainName', 'jqueryscript.net']);
  _gaq.push(['_trackPageview']);

  (function() {
    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
  })();

</script>

</body>
</html>
