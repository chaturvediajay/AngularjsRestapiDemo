<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%><%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!-- head -->
	<%@include file="head.jsp"%>
<!-- End head -->
<!--JS-->	
	<%@include file="mainJs.jsp"%>
<!-- End JS -->
<body> 
<!--header-->	
	<%@include file="header.jsp"%>
<!-- End header -->



<div class="container">
	<div class="check">	 
			
		 <div class="col-md-9 cart-items">
			 <h1>My Shopping Bag (2)</h1>
			 <c:set var="num" value="0" scope="page" />
			 <c:set var="total" value="0" scope="page" />
			 <c:if test="${not empty retrive}">
			 <c:forEach var="fp" items="${retrive}">
			 
			 	 <div class="cart-header">
				 <div id="close${num}" class="close1"> </div>
				 <div class="cart-sec simpleCart_shelfItem">
						<div class="cart-item cyc">
							 <img src="images/pic1.jpg" class="img-responsive" alt="">
						</div>
					   <div class="cart-item-info">
						<h3><a href="#">${fp.title}</a><span>Model No: 3578</span></h3>
						<ul class="qty">
							<li><p>Size : ${fp.size}</p></li>
							<li><p>color : ${fp.color}</p></li>
							<li><p>Qty : 1</p></li>
						</ul>
							 <div class="delivery">
							 <p>Charges-- : Rs.${1*fp.smrp}</p>
							 <span>Delivered in 2-3 bussiness days</span>
							 <c:set var="total" value="${total+(1*fp.smrp)}" />
							 
							 
							 <div class="clearfix"></div>
				        </div>	
					   </div>
					   <div class="clearfix"></div>
											
				  </div>
			 </div>
			 <c:set var="num" value="${num+1}" />
			 </c:forEach>
		</c:if>	 
		 </div>
		  <div class="col-md-3 cart-total">
			 <a class="continue" href="shipping">Add Shipping Address</a>
			 <div class="price-details">
				 <h3>Price Details</h3>
				 <span>Total</span>
				 <span class="total1">${total}</span>
				 <span>Delivery Charges</span>
				 <span class="total1">0</span>
				 <div class="clearfix"></div>				 
			 </div>	
			 <ul class="total_price">
			   <li class="last_price"> <h4>TOTAL</h4></li>	
			   <li class="last_price"><span>${total}</span></li>
			   <div class="clearfix"> </div>
			 </ul>
			
			 
			 <div class="clearfix"></div>
			 <a class="order" href="#">Place Order</a>
			 <div class="total-item">
				<!-- <h3>OPTIONS</h3>
				 <h4>COUPONS</h4>
				 <a class="cpns" href="#">Apply Coupons</a>-->
				 <p><a href="login">Log In</a> to use accounts</p>
			 </div> 
			</div>
		
			<div class="clearfix"> </div>
	 </div>
	 </div>
	


	
	<!--footer-->	
	<%@include file="footer.jsp"%>
<!-- End footer -->
	
<a href="#" id="toTop">To Top</a>

				<script>$(document).ready(function(c) {
					$('.close1').on('click', function(c){
						$('.cart-header').fadeOut('slow', function(c){
							$('.cart-header').remove();
						});
						});	  
					});
			   </script>
 <script>$(document).ready(function(c) {
					$('.close2').on('click', function(c){
							$('.cart-header2').fadeOut('slow', function(c){
						$('.cart-header2').remove();
					});
					});	  
					});
			 </script>			   
			   

</body>
</html>