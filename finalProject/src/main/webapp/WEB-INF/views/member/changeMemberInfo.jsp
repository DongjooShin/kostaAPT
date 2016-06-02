<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html" ; charset="UTF-8">
<script type="text/javascript" src="/resources/jquery/jQuery-2.1.4.min.js"></script>

<meta name="viewport" content="width=device-width, initial-scale=1.0">



<style type="text/css">
#phoneNumfirst {
	padding-left: 0px;
}

#phoneNum1first {
	padding-left: 0px;
}

#emailCheck {
	margin-left: -26px;
	margin-right: -50px;
	font-size: 20px;
}

#emailDomain {
	padding-left: 0;
	margin-left: 0;
}

#idCheck {
	margin: 0;
	padding: 0 15px;
}
</style>

<title>Insert title here</title>
</head>
<body>
	<script type="text/javascript">

		
		
		function checkIt() {
			if (!document.userInput.m_memberNo.value) {
				alert("ID를 입력하세요");
				return false;
			}

			if (!document.userInput.m_pass.value) {
				alert("비밀번호를 입력하세요");
				return false;
			}

			if (!document.userInput.m_pass2.value) {
				alert("비밀번호확인에 비밀번호를 입력하세요");
				return false;
			}

			if (!document.userInput.m_email.value) {
				alert("이메일을 입력하세요");
				return false;
			}
			if (!document.userInput.m_domain.value) {
				alert("이메일 도메인을 입력하세요");
				return false;
			}
			if (!document.userInput.s_mphone1.value) {
				alert("핸드폰 번호를 입력하세요");
				return false;
			}
			if (!document.userInput.s_mphone2.value) {
				alert("핸드폰 번호를 입력하세요");
				return false;
			}
			if (!document.userInput.s_mphone3.value) {
				alert("핸드폰 번호를 입력하세요");
				return false;
			}
			if (!document.userInput.m_name.value) {
				alert("이름을 입력하세요");
				return false;
			}

			if (!document.userInput.m_buildingNo.value) {
				alert("동을 입력하세요");
				return false;
			}

			if (!document.userInput.m_roomNo.value) {
				alert("호를 입력하세요");
				return false;
			}

			if (document.userInput.m_pass.value != document.userInput.m_pass2.value) {
				alert("비밀번호가 일치하지 않습니다.")
				return false;
			}
		

		}

		function selectCheck(inputid) {

			if (inputid.select.value != '직접입력') {
				userInput.m_domain.value = inputid.select.value;
			} else {
				userInput.m_domain.value = "";
				userInput.m_domain.focus();
			}
		}

		$(document).on("keyup","#s_mphone1",function(){
			$(this).val($(this).val().replace(/[^0-9]/gi,""));
		});
		
		$(document).on("keyup","#s_mphone2",function(){
			$(this).val($(this).val().replace(/[^0-9]/gi,""));
		});
		
		$(document).on("keyup","#s_mphone3",function(){
			$(this).val($(this).val().replace(/[^0-9]/gi,""));
		});
		
		$(document).on("keyup","#s_phone1",function(){
			$(this).val($(this).val().replace(/[^0-9]/gi,""));
		});
		
		$(document).on("keyup","#s_phone2",function(){
			$(this).val($(this).val().replace(/[^0-9]/gi,""));
		});
		
		$(document).on("keyup","#s_phone3",function(){
			$(this).val($(this).val().replace(/[^0-9]/gi,""));
		});
		$(document).on("keyup","#m_age",function(){
			$(this).val($(this).val().replace(/[^0-9]/gi,""));
		});
		$(document).on("keyup","#m_buildingNo",function(){
			$(this).val($(this).val().replace(/[^0-9]/gi,""));
		});
		$(document).on("keyup","#m_roomNo",function(){
			$(this).val($(this).val().replace(/[^0-9]/gi,""));
		});
	</script>

	<div class="header">
		<jsp:include page="../include/head.jsp"></jsp:include>
	</div>
	<div id="page" class="hfeed site"
		style="height: 950px; border-top: 2px solid;">
		<div class="col-md-12">
			<div class="col-lg-3"
				style="margin-right: 0; margin-left: 26px; margin-top: 17px;">
				<div class="loginSidebar">
					<jsp:include page="memberSidebar.jsp"></jsp:include>
				</div>
			</div>

			<div class="col-lg-8" style="margin-top: 40px; margin-left: 70px; border: 1px solid #eaeaea;">
			
			<form class="form-horizontal" action="changeMemberInfo" method="post"
						name="userInput" onsubmit="return checkIt()">
						<br>
						<span style="font-size: 40px;  margin-top: 40px">회원가입</span>

						<div class="form-group">
						
							<div class="col-lg-12">
								<input type="hidden" name="v_flag" value="${member.getV_flag() }">
								<input type="hidden" name="apt_APTGNo" value="${member.getApt_APTGNo() }">
								<input type="hidden" name="m_grade" value="${member.getM_grade() }">  
								<!-- <input type="hidden" name="idCheckOK" id = "idCheckOK" value="0"> -->
								<input type="hidden" name="id" id="id" value="${member.getM_memberNo() }">  <br><br><br>
								
								<label class="col-lg-3 control-label">아 이 디</label>
								<div class="col-lg-7">
									<input type="text" class="form-control" name="m_memberNo" value="${member.getM_memberNo() }" disabled="disabled">
								</div>
								
							</div>
						</div>
						<div class="form-group">
							<div class="col-lg-12">
								<label class="col-lg-3 control-label">비밀번호</label>
								<div class="col-lg-7">
									<input type="password" class="form-control" name="m_pass">
								</div>
							</div>
						</div>

						<div class="form-group">
							<div class="col-lg-12">
								<label class="col-lg-3 control-label">비밀번호확인</label>
								<div class="col-lg-7">
									<input type="password" class="form-control" name="m_pass2">
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-lg-12">
								<label class="col-lg-3 control-label">이 메 일</label>
								<div class="col-lg-3">
									<input type="text" class="form-control" name="m_email" value="${member.getM_email() }">
								</div>
								<div class="col-lg-1" id="emailCheck">@</div>
								<div class="col-lg-2">
									<input type="text" class="form-control" id="m_domain"
										name="m_domain" value="${member.getM_domain() }">
								</div>
								<div class="col-lg-2" id="emailDomain">
									<select class="form-control" id="select"
										onclick="selectCheck(this.form)">
										<option>kosta.com</option>
										<option>naver.com</option>
										<option>hanmail.net</option>
										<option>직접입력</option>
									</select>
								</div>
							</div>
						</div>


						<div class="form-group">
							<div class="col-lg-12">
								<label class="col-lg-3 control-label">휴대폰번호</label>
								<div class="col-lg-7">
									<div class="col-lg-4" id="phoneNumfirst">
										<input type="text" class="form-control" name="s_mphone1"  
										style='IME-MODE: disabled' id="s_mphone1" maxlength="3" value="${member.getS_mphone1() }">

									</div>

									<div class="col-lg-4 phoneNum">
										<input type="text" class="form-control" name="s_mphone2"  
										style='IME-MODE: disabled' id="s_mphone2" maxlength="4"value="${member.getS_mphone2() }">
									</div>

									<div class="col-lg-4 phoneNum">
										<input type="text" class="form-control" name="s_mphone3" 
										style='IME-MODE: disabled'  id="s_mphone3" maxlength="4"value="${member.getS_mphone3() }">
									</div>
								</div>
							</div>
						</div>

						<div class="form-group">
							<div class="col-lg-12">
								<label class="col-lg-3 control-label">전화번호</label>
								<div class="col-lg-7">
									<div class="col-lg-4 phoneNumfirst" id="phoneNum1first">
										<input type="text" class="form-control" name="s_phone1"  
										style='IME-MODE: disabled' id="s_phone1" maxlength="3" value="${member.getS_phone1() }">
									</div>

									<div class="col-lg-4 phoneNum">
										<input type="text" class="form-control" name="s_phone2" 
										style='IME-MODE: disabled' id="s_phone2" maxlength="4" value="${member.getS_phone2() }">
									</div>

									<div class="col-lg-4 phoneNum">
										<input type="text" class="form-control" name="s_phone3" 
										style='IME-MODE: disabled'  id="s_phone3" maxlength="4" value="${member.getS_phone3() }">
									</div>
								</div>
							</div>
						</div>

						<div class="form-group">
							<div class="col-lg-12">
								<label class="col-lg-3 control-label">이 름</label>
								<div class="col-lg-3">
									<input type="text" class="form-control" name="m_name" value="${member.getM_name() }">
								</div>

								<label class="col-lg-1 control-label">나 이</label>
								<div class="col-lg-3">
									<input type="text" class="form-control" name="m_age" id="m_age" style='IME-MODE: disabled' maxlength="2" value="${member.getM_age() }">
								</div>
							</div>
						</div>
						<div class="form-group">
							<div class="col-lg-12">
								<label class="col-lg-3 control-label">주소</label>
								<div class="col-lg-7">
									<input type="text" class="form-control" disabled="disabled"
										value="${address }">
								</div>
							</div>
						</div>

						<div class="form-group">
							<div class="col-lg-12">
								<label class="col-lg-3 control-label">동 </label>
								<div class="col-lg-3">
									<input type="text" class="form-control" name="m_buildingNo" style='IME-MODE: disabled'  id="m_buildingNo" maxlength="4" value="${member.getM_buildingNo() }">
								</div>

								<label class="col-lg-1 control-label">호 </label>
								<div class="col-lg-3">
									<input type="text" class="form-control" name="m_roomNo" style='IME-MODE: disabled'  id="m_roomNo" maxlength="4" value="${member.getM_roomNo() }">
								</div>
							</div>
						</div>

						<div class="form-group">
							<div class="col-lg-6 col-lg-offset-5">
								<button type="reset" class="btn btn-default">Cancel</button>
								<button type="submit" class="btn btn-primary">Submit</button>
							</div>
						</div>
					</form>
			
			</div>
		</div>
	</div>
	
	<div class="footer col-md-12"></div>

</body>
</html>