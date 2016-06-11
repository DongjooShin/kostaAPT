<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
<link rel='stylesheet' href='/resources/bootstrap/bootstrap.min.css'
	type='text/css' rel="stylesheet" />
<link rel='stylesheet' href='/resources/bootstrap/mypage-bootstrap.css'
	type='text/css' rel="stylesheet" />
<script type="text/javascript"
	src="/resources/jquery/jQuery-2.1.4.min.js"></script>

<script type="text/javascript">
	$(function() {
		$('#firstManu').css('background', '#09b9e5');
		$('#firstContent').css('display', 'block');

		$('#firstManu').click(function() {
			$('.manu').css('background-color', 'rgba(0, 0, 0, 0.3)');
			$('#firstManu').css('background-color', '#09b9e5');
			$('.content').css('display', 'none');
			$('#firstContent').css('display', 'block');
		});

		$('#secondManu').click(function() {
			$('.manu').css('background-color', 'rgba(0, 0, 0, 0.3)');
			$('#secondManu').css('background-color', '#09b9e5');
			$('.content').css('display', 'none');
			$('#secondContent').css('display', 'block');
		});

		$('#thirdManu').click(function() {
			$('.manu').css('background-color', 'rgba(0, 0, 0, 0.3)');
			$('#thirdManu').css('background-color', '#09b9e5');
			$('.content').css('display', 'none');
			$('#thirdContent').css('display', 'block');

		});

	});
</script>

<style type="text/css">
#mypageUl {
	margin-top: -53px;
}

#mypageUl li {
	display: inline;
	float: left;
	position: relative;
	margin-right: 5px;
	padding: 10px;
	background-color: rgba(0, 0, 0, 0.3);
	padding: 15px 35px 15px 35px;
	border-radius: 3px;
	color: #fff;
	font-size: 21px;
}

#mypageLayout {
	border: 3px solid #eaeaea;
	border-top: 4px solid #09b9e5;
	height: 350px;
	min-height: 340px;
	margin-top: 70px;
}

.content {
	display: none;
	margin-top: 15px;
}


</style>
</head>
<body>
	<div class="header">

		<jsp:include page="../include/head.jsp"></jsp:include>

	</div>
	<div id="page" style="background: white; height: 500px;">
		<div class="col-md-12">
			<div id="mypageLayout">
				<ul id=mypageUl>
					<li id="firstManu" class="manu ">Properties</li>
					<li id="secondManu" class="manu">Flights</li>
					<li id="thirdManu" class="manu">Vacations</li>
				</ul>
				<br>
				
				
				<div class="col-md-12" >
					<div id="firstContent" class="content  " style=" height: 300px;">
							
					
					</div>
				</div>
				
				<div class="col-md-12"> 
					<div id="secondContent" class="content "  	style="background: orange; height: 300px;" >

					</div>
					</div>
					
				<div class="col-md-12" >

				<div id="thirdContent" class="content "  style="background: red; height: 300px;">

				</div>
			</div>








		</div>
	</div>
	</div>




</body>
</html>