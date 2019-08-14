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
			 <a class="checkout" href="#">Edit Shopping Cart</a>
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
			 <a class="order" href="#">Place Order with payment</a>
			 <div class="total-item">
			 <h3><ins>Shipping Address</ins></h3>
				<address>
			<strong>${sessionScope.ship.sName}</strong><br> ${sessionScope.ship.sAddress1}<br>
			${sessionScope.ship.sAddress2}<br> ${sessionScope.ship.sCity}<br>
			${sessionScope.ship.sSate}.${sessionScope.ship.sPincode}<br><abbr
		title="Phone">P:</abbr> +91-${sessionScope.ship.sMobile}
		</address>
				 <p><a href="shipping">Edit Shipping Address</a></p>
			 </div> 
			</div>
		<form action="ccavenueRequest" autocomplete="off" name="ccPayment"
						method=POST>
						<table>
							<tr>
								<td colspan="4"><input type="submit" value="Payment" /></td>
							</tr>
						</table>
					</form>
			<div class="clearfix"> </div>
	 </div>
	 </div>
	


	
	<!--footer-->	
	<%@include file="footer.jsp"%>
<!-- End footer -->
	
<a href="#" id="toTop">To Top</a>
</body>
</html>