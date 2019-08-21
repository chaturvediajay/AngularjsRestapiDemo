
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
																<label class="col-sm-3 col-form-label">Menu1</label>
																<div class="col-sm-9">
																
																<select class="form-control"
												id="optMenu1"
												onchange="changeCate('optMenu2','menu1','optMenu1')"
												name="optMenu1">
												<option value="0">---select---</option>
												<c:forEach var="menus" items="${menu}">
													<option value="${menus.m1id}">${menus.menu}</option>
												</c:forEach>
											</select>
																</div>
															</div>
														</div>
														<div class="col-md-4">
															<div class="form-group row">
																<div class="col-sm-9">
																
																<input type="text" class="form-control" id="txtMenu1"
													name="txtMenu1" data-onload="isValidInput('txtMenu1','')" placeholder="menu1"> 
																</div>
															</div>
														</div>
															<div class="col-md-4">
															<input class="submit btn btn-danger" type="submit"
												value="add Menu-1"
												onclick="submitMenu('txtMenu1','optMenu1','optMenu1');">
															</div>
														
													</div>
													<div class="row">
														<div class="col-md-4">
															<div class="form-group row">
																<label class="col-sm-3 col-form-label">Menu2</label>
																<div class="col-sm-9">
																	<select class="form-control" id="optMenu2"
												onchange="changeCate('optMenu3','menu2','optMenu2')">
												<option value="0">select</option>
											</select>
																</div>
															</div>
														</div>
														<div class="col-md-4">
															<div class="form-group row">
																<div class="col-sm-9">
																<input type="text" class="form-control" id="txtMenu2"
													name="txtcompany"  placeholder="menu2"
													data-onload="isValidInput('txtcompany','optMenu1')">
																</div>
															</div>
														</div>
														<div class="col-md-4">
															<input class="submit btn btn-danger"
												onclick="submitMenu('txtMenu2','optMenu2','optMenu1');"
												type="submit" value="add Menu-2">
												</div>
													</div>
													<div class="row">
														<div class="col-md-4">
															<div class="form-group row">
																<label class="col-sm-3 col-form-label">Menu 3</label>
																<div class="col-sm-9">
																<select class="form-control" id="optMenu3">
												<option value="0">---select---</option>
											</select>
																</div>
															</div>
														</div>
														<div class="col-md-4">
															<div class="form-group row">
																<div class="col-sm-9">
																	<input type="text" class="form-control" id="txtMenu3"
													name="txtMenu3" placeholder="menu3"
													data-onload="isValidInput('txtMenu3','optMenu3')">
																</div>
															</div>
														</div>
														<div class="col-sm-4">
														<input class="submit btn btn-danger" type="submit"
												onclick="submitMenu('txtMenu3','optMenu3','optMenu2');"
												value="add Menu-3">
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
							© 2019 <a href="http://www.bootstrapdash.com/" target="_blank">Bootstrapdash</a>.
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
	<script type="text/javascript">
		var select = '';
		function changeCate(divId, categories, cateid) {
			var id = $('#' + cateid + ' :selected').val();
			$select = $("#" + divId);
			$select.find("option").remove();
			var js1 = new Object();
			js1.id = id;
			js1.select = categories;
			js1.opt = "opt.retrive";
			if (id > 0) {
				var json = {
					'json' : JSON.stringify(js1)
				};
				//ajaxRequest(json, 'get', '/admin/${sessionScope.admin}/menus',divTest);
				ajaxCallRequestResponse('json', json, 'POST',
						'../retrive_menu', divTest);
			} else
				alert("no data found");
		}
		function divTest(data) {
			result = $.parseJSON(data.retrive);
			console.log(result);
			$("<option>").val('0').text('----select----').appendTo($select);
			$.each(result, function(k, v) {
				    $("<option>").val(v.m2id).text(v.submenu).appendTo($select);
			    
			    
			});
			
			$select.val('');
			/* alert(data.status); */
			//$select.find("option").remove();
			//var obj = JSON.stringify(data);
			//var jsonobject = JSON.parse(obj);

			//	console.log(JSON.parse(jsonobject.data));
			//read key
			//	alert(jsonobject.res);
			// $.each(JSON.parse(jsonobject.data), function(key, value) {
			// 	console.log(key + ":" + value)
			//		   $("<option>").val(key).text(value).appendTo(
			//					$select);
			//  });
			select = '';

		}
		function submitMenu(id, val, sget) {
			var condition = false;
			var menu1 = $('#optMenu1 option:selected').val();
			var menu2 = $('#optMenu2 option:selected').val();
			var menu3 = $('#optMenu3 option:selected').val();
			$('#somediv').empty();
			var ur = '';
			if (id == 'txtMenu1') {
				ur = '../addMenus?m1id=' + menu1 + '&m2id=' + menu2
						+ "&categories_val=" + $('#' + id).val()
						+ "&categories_key=" + val + '&m3id=' + menu3
						+ '&cate=' + id;
				
				condition=true;

			} else if (val == 'optMenu2') {
				if (menu1 > 0){
					ur = '../addMenus?m1id=' + menu1 + '&m2id=' + menu2
							+ "&categories_val=" + $('#' + id).val()
							+ "&categories_key=" + val + '&m3id=' + menu3
							+ '&cate=optMenu2';
					condition=true;	
				}
			} else if (val == 'optMenu3') {
				if (menu1 > 0 & menu2>0){
					ur = '../addMenus?m1id=' + menu1 + '&m2id=' + menu2
							+ "&categories_val=" + $('#' + id).val()
							+ "&categories_key=" + val + '&m3id=' + menu3
							+ '&cate=optMenu3';
					condition=true;	
				}
				else
					$('#somediv').html("<span class=\"label label-danger\">Enter menu 1</span>");
			} 
			
			else

				$('#somediv')
						.html(
								"<span class=\"label label-danger\">Enter menu 1</span>");

			/*  else if (categories == 'menu2') {
												if (cateId > 0) {
													if ($('#txtcompany').val().trim().length > 0) {
														ur += '&title=' + $('#txtcompany').val().trim();
														condition = true;
													} else
														$('#somediv').html("<span class=\"label label-danger\">Enter menu 2</span>");
												} else
													$('#somediv').html("<span class=\"label label-warning\">Choose menu 1</span>");
											} else if (categories = 'menu3') {
												if (compID > 0) {
													if ($('#txtmodel').val().trim().length > 0) {
														ur += '&title=' + $('#txtmodel').val().trim();
														condition = true;
													} else
														$('#somediv')
																.html(
																		"<span class=\"label label-danger\">Enter menu 3</span>");
												} else
													$('#somediv')
															.html(
																	"<span class=\"label label-warning\">Choose menu 2</span>");
											}
			 */
			//'${pageContext.request.contextPath}/admin/${sessionScope.user}/categoriesAdd?id='+id+"&categories.select="+categories;
			
			console.log(ur);
			if (condition) {

				$.ajax({
					type : "POST",
					url : ur,
					timeout : 100000,
					success : function(data) {
						//alert(condition);
						//location.reload(true);
						console.log("SUCCESS: ", data);

					},
					error : function(e) {
						console.log("ERROR 456: ", e);
						$('#somediv').html(e.responseText);
						/* 	$('#txtcategories').val('');
						$('#txtcompany').val('');
						$('#txtmodel').val(''); */
						alert(e.responseText);
						/* location.reload(); */
					},
					done : function(e) {
						console.log("DONE");
						$('#somediv').html(e.responseText);

					}
				});
			}

		}
		function checkNull(num) {
			if (num < 1)
				return 0;
			else
				return num;

		}
	</script>
	
	
	
</body>
</html>