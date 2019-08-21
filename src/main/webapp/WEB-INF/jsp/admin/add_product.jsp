
<%
	response.setHeader("Cache-Control", "no-cache");
	response.setHeader("Cache-Control", "no-store");
	response.setHeader("Pragma", "no-cache");
	response.setDateHeader("Expires", 0);
%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<!-- Required meta tags -->
<meta charset="utf-8">
<meta name="viewport"
	content="width=device-width, initial-scale=1, shrink-to-fit=no">
<title>Star Admin Premium Bootstrap Admin Dashboard Template</title>
<!-- plugins:css -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/vendors/iconfonts/mdi/css/materialdesignicons.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/vendors/iconfonts/ionicons/css/ionicons.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/vendors/iconfonts/typicons/src/font/typicons.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/vendors/iconfonts/flag-icon-css/css/flag-icon.min.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/vendors/css/vendor.bundle.base.css">
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/vendors/css/vendor.bundle.addons.css">
<!-- endinject -->
<!-- plugin css for this page -->
<!-- End plugin css for this page -->
<!-- inject:css -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/shared/style.css">
<!-- endinject -->
<!-- Layout styles -->
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/assets/css/demo_1/style.css">
<!-- End Layout styles -->
<link rel="shortcut icon"
	href="${pageContext.request.contextPath}/assets/images/favicon.png" />
</head>
<body>
	<div class="container-scroller">
		<!-- partial:partials/_navbar.html -->
		<%@include file="../master/admin/topheader.jsp"%>

		<!-- partial -->
		<div class="container-fluid page-body-wrapper">
			<!-- partial:partials/_sidebar.html -->
			<%@include file="../master/admin/leftnav.jsp"%>
			<!-- partial -->
			<div class="main-panel">
				<div class="content-wrapper">
					<!-- Page Title Header Starts-->
					<div class="row page-title-header">
						<div class="col-12">
							<div class="page-header">
								<h4 class="page-title">Add Menu</h4>
							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12 grid-margin stretch-card">
							<!-- partial -->
							<div class="content-wrapper">
								<div class="row">

									<div class="col-md-12 grid-margin stretch-card"></div>
									<div class="col-12 grid-margin">
										<div class="card">
											<div class="card-body">
												<h4 class="card-title">Horizontal Two column</h4>
												
													<p class="card-description">Add Menu Cateogies</p>
													<div class="row">
														<div class="col-md-4">
															<div class="form-group row">
																<label class="col-sm-6 col-form-label">categories 1</label>
															
															</div>
														</div>
														<div class="col-md-6">
															<div class="form-group row">
																<div class="col-sm-9">
																
															------------------------
																</div>
															</div>
														</div>
														
													</div>
													<div class="row">
														<div class="col-md-4">
															<div class="form-group row">
																<label class="col-sm-6 col-form-label">categories 2</label>
															
															</div>
														</div>
														<div class="col-md-6">
															<div class="form-group row">
																<div class="col-sm-4">
																------------------------
																</div>
															</div>
														</div>
														
													</div>
													<div class="row">
														<div class="col-md-4">
															<div class="form-group row">
																<label class="col-sm-6 col-form-label">categories 3</label>
																
															</div>
														</div>
														<div class="col-md-6">
															<div class="form-group row">
																<div class="col-sm-9">
																	------------------
																</div>
															</div>
														</div>
													
													
														
													</div>
		<div class="row">
														<div class="col-md-4">
															<div class="form-group row">
																<label class="col-sm-6 col-form-label">Product Title</label>
																
															</div>
														</div>
														<div class="col-md-6">
															<div class="form-group row">
																<div class="col-sm-9">
																	------------------
																</div>
															</div>
														</div>
													
													
														
													</div>
														<div class="row">
														<div class="col-md-4">
															<div class="form-group row">
																<label class="col-sm-6 col-form-label">Product Description</label>
																
															</div>
														</div>
														<div class="col-md-6">
															<div class="form-group row">
																<div class="col-sm-9">
																	------------------
																</div>
															</div>
														</div>
													
													
														
													</div>	
											</div>
										</div>
									</div>
								</div>

								<!-- content-wrapper ends -->
						
							</div>


							<!-- main-panel ends -->

						</div>
					</div>

				</div>
				<!-- content-wrapper ends -->
				<!-- partial:partials/_footer.html -->
				<footer class="footer">
					<div class="container-fluid clearfix">
						<span
							class="text-muted d-block text-center text-sm-left d-sm-inline-block">Copyright
							� 2019 <a href="http://www.bootstrapdash.com/" target="_blank">Bootstrapdash</a>.
							All rights reserved.
						</span> <span
							class="float-none float-sm-right d-block mt-1 mt-sm-0 text-center">Hand-crafted
							& made with <i class="mdi mdi-heart text-danger"></i>
						</span>
					</div>
				</footer>
				<!-- partial -->
			</div>
			<!-- main-panel ends -->
		</div>
		<!-- page-body-wrapper ends -->
	</div>
	<!-- container-scroller -->
	<!-- plugins:js -->
	<script
		src="${pageContext.request.contextPath}/assets/vendors/js/vendor.bundle.base.js"></script>
	<script
		src="${pageContext.request.contextPath}/assets/vendors/js/vendor.bundle.addons.js"></script>
	<!-- endinject -->
	<!-- Plugin js for this page-->
	<!-- End plugin js for this page-->
	<!-- inject:js -->
	<script
		src="${pageContext.request.contextPath}/assets/js/shared/off-canvas.js"></script>
	<script
		src="${pageContext.request.contextPath}/assets/js/shared/misc.js"></script>
	<!-- endinject -->
	<!-- Custom js for this page-->
	<script
		src="${pageContext.request.contextPath}/assets/js/demo_1/dashboard.js"></script>
	<!-- End custom js for this page-->
		<script
		src="${pageContext.request.contextPath}/js/custom/separate.js"></script>
</body>
</html>