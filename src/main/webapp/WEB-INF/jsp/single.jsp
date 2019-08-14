<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ page import="java.util.UUID"%>
<html>
<!--JS-->	
	<%@include file="mainJs.jsp"%>
<!-- End JS -->
<!-- head -->
	<%@include file="head.jsp"%>
<!-- End head -->
<%!
    public String generateRandomNumber(){
        return UUID.randomUUID().toString().replace("-", "");
    }
%>
<body> <c:set var="rnd"
		value="<%=generateRandomNumber()%>"
		scope="page"></c:set>
<!--header-->	
	<%@include file="header.jsp"%>
<!-- End header -->
	<c:forEach var="fp" items="${product_desc}">
<div class="content">
	<div class="container">
		<div class="single">
				<div class="col-md-9 top-in-single">
					<div class="col-md-5 single-top">	
						<ul id="etalage" class="etalage" style="display: block; width: 302px; height: 537px;">
							<li class="etalage_thumb thumb_1" style="display: none; background-image: none; opacity: 0;">
								<a href="optionallink.html">
									<img class="etalage_thumb_image img-responsive" src="images/si.jpg" style="display: inline; width: 300px; height: 400px; opacity: 1;">
									<img class="etalage_source_image img-responsive" src="images/s1.jpg" alt="">
								</a>
							</li>
							<li class="etalage_thumb thumb_2 etalage_thumb_active" style="background-image: none; display: list-item; opacity: 1;">
								<img class="etalage_thumb_image img-responsive" src="images/si1.jpg" style="display: inline; width: 300px; height: 400px; opacity: 1;">
								<img class="etalage_source_image img-responsive" src="images/s2.jpg" alt="">
							</li>
							<li class="etalage_thumb thumb_3" style="background-image: none; display: none; opacity: 0;">
								<img class="etalage_thumb_image img-responsive" src="images/si2.jpg" style="display: inline; width: 300px; height: 400px; opacity: 1;">
								<img class="etalage_source_image img-responsive" src="images/s3.jpg" alt="">
							</li>
						    <li class="etalage_thumb thumb_4" style="background-image: none; display: none; opacity: 0;">
								<img class="etalage_thumb_image img-responsive" src="images/si3.jpg" style="display: inline; width: 300px; height: 400px; opacity: 1;">
								<img class="etalage_source_image img-responsive" src="images/s4.jpg" alt="">
							</li>
						<li class="etalage_magnifier" style="margin: 0px; padding: 0px; left: 75px; top: 221px; display: none; opacity: 0.019027;"><div style="margin: 0px; padding: 0px; width: 200px; height: 179px;"><img src="http://localhost/shop/images/si1.jpg" style="margin: 0px; padding: 0px; width: 300px; height: 400px; display: inline; left: -75px; top: -221px;"></div></li><li class="etalage_icon" style="display: list-item; top: 278px; left: 20px; opacity: 1;">&nbsp;</li><li class="etalage_hint" style="display: none; margin: 0px; top: -15px; right: -15px;">&nbsp;</li><li class="etalage_zoom_area" style="margin: 0px; opacity: 0; left: 312px; display: none; background-image: none;"><div class="etalage_description" style="width: 560px; bottom: 0px; left: 0px; opacity: 0.7; display: none;"></div><div style="width: 600px; height: 537px;"><img class="etalage_zoom_img" src="images/s2.jpg" style="width: 900px; height: 1200px; left: -210.352px; top: -463.363px;"></div></li><li class="etalage_small_thumbs" style="width: 302px; top: 412px;"><ul style="width: 724px;"><li class="etalage_smallthumb_navtoend" style="opacity: 0.4; margin: 0px 10px 0px 0px; left: -72px;"><img class="etalage_small_thumb" src="http://localhost/shop/images/si3.jpg" width="92" style="width: 92px; height: 123px;"></li><li class="etalage_smallthumb_first" style="opacity: 0.4; margin: 0px 10px 0px 0px; left: -72px;"><img class="etalage_small_thumb" src="http://localhost/shop/images/si.jpg" width="92" style="width: 92px; height: 123px;"></li><li class="etalage_smallthumb_active" style="opacity: 1; margin: 0px 10px 0px 0px; left: -72px;"><img class="etalage_small_thumb" src="http://localhost/shop/images/si1.jpg" width="92" style="width: 92px; height: 123px;"></li><li class="etalage_smallthumb_last" style="opacity: 0.4; margin: 0px 10px 0px 0px; left: -72px;"><img class="etalage_small_thumb" src="http://localhost/shop/images/si2.jpg" width="92" style="width: 92px; height: 123px;"></li><li class="" style="opacity: 0.4; margin: 0px 10px 0px 0px; left: -72px;"><img class="etalage_small_thumb" src="http://localhost/shop/images/si3.jpg" width="92" style="width: 92px; height: 123px;"></li><li class="etalage_smallthumb_navtostart" style="opacity: 0.4; margin: 0px 10px 0px 0px; left: -72px;"><img class="etalage_small_thumb" src="http://localhost/shop/images/si.jpg" width="92" style="width: 92px; height: 123px;"></li></ul></li></ul>

					</div>	
					<div class="col-md-7 single-top-in">
						<div class="single-para">
							<h4>${fp.title}</h4>
							<p>${fp.description}</p>
							<div class="star-on">
								<ul>
									<li><a href="#"><i> </i></a></li>
									<li><a href="#"><i> </i></a></li>
									<li><a href="#"><i> </i></a></li>
									<li><a href="#"><i> </i></a></li>
									<li><a href="#"><i> </i></a></li>
								</ul>
								<div class="review">
									<a href="#"> 3 reviews </a>/
									<a href="#">  Write a review</a>
								</div>
							<div class="clearfix"> </div>
							</div>
								<label class="add-to">&#x20B9;${fp.smrp}</label>
							
							<div class="available">
								<h6>Available Options :</h6>
								<ul>
in stock message<div id="msg"></div>
			
								<li>Size:<select id="size" onchange="changeLayer('size','${fp.pkey}')">
									<option value="0">SELECT</option>
									
												<c:forTokens var="sp"  delims="," items="${fp.size}">
								   <option value="${sp}">${sp}</option>
								</c:forTokens>

								</select></li>
								<li>Color:
										<select id="color" onchange="changeLayer('color','${fp.pkey}')">
										<option value="0">SELECT</option>
									</select></li>
							</ul>
						</div>
								<a id="call" href="${pageContext.request.contextPath}/checkout?scope=${rnd}-${fp.pkey}" class="cart">add to cart</a>
							
						</div>
					</div>
				<div class="clearfix"> </div>
				  <!----- tabs-box ---->
		
				</div>
				
				<div class="clearfix"> </div>		
		</div>
	</div>
</div>
</c:forEach>
	
	<!--footer-->	
	<%@include file="footer.jsp"%>
<!-- End footer -->
	
<a href="#" id="toTop">To Top</a>



<script type="text/javascript">
function changeLayer(value, pkey) {
	$(".label").addClass("label label-success");
	$("#msg").text('select size and color');
	var js1 = new Object();
	js1.pkey = pkey;
	js1.value = $('#' + value + ' :selected').val();
	js1.cate = value;
	var json = {
		'json' : JSON.stringify(js1)
	};
	
	if(js1.value==0){
		//$(".label").removeClass().addClass("label label-danger");
		$('#call').removeClass().addClass("btn disabled cart");
 		console.log('ls-0-- '+json);
	}
	 	else {
	 		console.log('ls--1- '+json);
	 		ajaxCallRequestResponse('json', json, 'post', getUrl('/products_size'),	homeview);
	 		
	 	}
	 		
	 		
	 		
	ajaxCallRequestResponse('json', json, 'get', getUrl('/products_size'),	homeview);
	console.log('sdsd  '+js1.value);
	
}
function homeview(response) {
	$(".label").addClass("label label-success");
	var amt = 0;
	var obj = JSON.stringify(response);
	var par = JSON.parse(obj);
	if (par.cate == 'size') {
		var da = eval(par.data);
		selectGet('#color', da, '#size');
	} else if (par.cate == 'color') {
		var da = eval(response);
		var par = JSON.parse(obj);obj.put("data", k) 
			par = JSON.parse(par.data);
			$(".label").removeClass().addClass("label label-success");
			$('a.btn').removeClass().addClass("btn btn-theme m-b-1");
			$("a").attr("href", 'cart?key='+par.title.replace(" ","-")+"-"+par.pkey+par.ppid);
			console.log('coloris:  '+par.description);
			$('.price').html("<div>&#8377;"+par.smrp+"<span class=\"label label-default arrowed\">10%"+
			"</span></div><span class=\"price-old\">&#8377;"+par.mrp+"</span>");
			$("#msg").text('Ready Stock');
			$("#locStatus").val(par.location);
			$('#pin').attr("disabled",false);
		} else {
			$(".label").removeClass().addClass("label label-danger");
			$('a.btn').removeClass().addClass(
					"btn btn-theme m-b-1 disabled");
			$("#msg").text('not available=');
		}
	}


</script>


</body>
</html>