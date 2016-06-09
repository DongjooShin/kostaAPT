<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<script src="//code.jquery.com/jquery-1.11.0.min.js"></script>
<div class="header">
	<jsp:include page="../include/head.jsp"></jsp:include>
</div>
<div>
	<jsp:include page="../mypage/MypageHead.jsp" />
</div>

<div>

	<div class="col-lg-2"></div>
	<div class="col-lg-10" style="background-color: white;">
		<!-- Main content -->
		<section class="content">
			<div class="row">
				<!-- left column -->
				<div class="col-md-12">
					<!-- general form elements -->
					<div class="box box-primary">
						<div class="box-header">
							<h3 class="box-title">글등록</h3>
						</div>
						<!-- /.box-header -->
						<form role="form" method="post">
							<div class="box-body">
								<div class="form-group">
									<label for="exampleInputEmail1">cp_title</label> <input
										type="text" name='cp_title' class="form-control" height="20">
								</div>
								<div class="form-group">
									<label for="exampleInputPassword1">cp_content</label>
									<textarea class="form-control" name="cp_content" rows="3"
										height="8"></textarea>
								</div>
							</div>
							<!-- /.box-body -->

							<div class="box-footer">
								<button type="submit" class="btn btn-primary">Submit</button>
							</div>
						</form>


					</div>
					<!-- /.box -->
				</div>
				<!--/.col (left) -->

			</div>
			<!-- /.row -->
		</section>
	</div>
</div>
<!-- /.content -->
<!-- /.content-wrapper -->