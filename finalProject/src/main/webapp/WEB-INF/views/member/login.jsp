<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%-- <%
	String check1 = request.getParameter("check");
	
	 int check =0;
	
	 if(check1 !=null){
		 check = Integer.parseInt(check1);
	 }

	if (check == 1) {
		System.out.println("비밀번호 틀림");
		check = 10;
	}
	
	
	request.setAttribute("check2", check); 
%> --%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html lang="en-US">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge">
<meta name="viewport" content="width=device-width, initial-scale=1">
<title>APT Management Login Page</title>

<!-- CSS -->
<!-- <link rel="stylesheet" href="http://fonts.googleapis.com/css?family=Roboto:400,100,300,500"> -->
<link rel="stylesheet" href="/resources/dist/css/dong/form-elements.css">
<link rel="stylesheet" href="/resources/dist/css/dong/style.css">



<style type="text/css">
#changeLetter1 {
	color: red;
	margin-top: -15px;
	margin-bottom: 3px;
	font-weight: bold;
	font-size: 13px;
}

#changeLetter2 {
	color: red;
	margin-bottom: 15px;
	font-weight: bold;
	font-size: 13px;
}

#loginSpace {
	margin-bottom: 10px;
}

#btn {
	font-weight: bold;
}
</style>


<script type="text/javascript">
	function movePage() {
		location.href = "/loginCheck";
	}

	function focusIt() {
		form.formUsername.focus();
	}

	function checkIt() {
		if (!document.form.formUsername.value) {
			alert("ID를 입력하세요");
			return false;
		}

		if (!document.form.formPassword.value) {
			alert("비밀번호를 입력하세요");
			return false;
		}
	}
</script>
</head>

<body
	class="home singular page page-id-7 page-template page-template-page-templates page-template-business page-template-page-templatesbusiness-php "
	onload="focusIt()">

	<div class="header">

		<jsp:include page="../include/head.jsp"></jsp:include>

	</div>

	<div id="page" class="hfeed site"
		style="height: 800px; border-top: 2px solid;">
		<div class="col-md-12">
			<!-- Top content -->
			<div class="top-content" id="widthsize">

				<div class="container">

						<h1>
							<strong>Login</strong>
						</h1>
						<div class="description">
							<p>
								<strong id="titleStrong">APT Management</strong>에 오신것을 환영합니다
							</p>
						</div>
		


					<div class="row">
						<div class="col-sm-6 col-sm-offset-3 form-box">
							<div class="form-top">
								<div class="form-top-left">
									<h3>Login to our site</h3>
									<p>Enter your id and password to log on:</p>

								</div>
								<div class="form-top-right">
									<i class="fa fa-key"></i>
								</div>
							</div>
							<div class="form-bottom">
								<form name="form" action="login" method="post"
									class="login-form" onsubmit="return checkIt()">
									<div class="form-group">
										<label class="sr-only" for="form-username">Username</label> <input
											type="text" name="formUsername" placeholder="Username..."
											class="form-username form-control" id="form-username">
									</div>
									<div class="form-group">
										<label class="sr-only" for="form-password">Password</label> <input
											type="password" name="formPassword" placeholder="Password..."
											class="form-password form-control" id="form-password">
									</div>
									<c:if test="${check ==10 }">

										<p id="changeLetter1">아이디 또는 비밀번호를 다시 확인하세요.</p>
										<p id="changeLetter2">등록되지 않은 아이디이거나, 아이디 또는 비밀번호를 잘못
											입력하셨습니다.</p>

									</c:if>

									<button type="submit" class="btn" id="loginSpace">로 그 인</button>
									<button type="button" class="btn" onclick="movePage()">회원가입</button>
								</form>
							</div>
						</div>
					</div>
				</div>


			</div>


		</div>

	</div>




</body>
</html>