<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%><%@ taglib prefix="c"
	uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!-- head -->
<%@include file="head.jsp"%>

<style>
.products-grid {
	padding: 30px 0;
}

.product {
	position: relative;
	margin-top: 50px;
	padding: 0 0;
}
</style>
<!-- End head -->
<body>
	<!--header-->
	<%@include file="header.jsp"%>
	<!-- End header -->
	<!--JS-->
	<%@include file="mainJs.jsp"%>
	<!-- End JS -->

	<!--banner-->

	<div class="content">
		<div class="container">
				<c:if test="${not empty shippingAdd}">
			<b>${order_no}</b>
			<c:set var="totalValue" scope="page" value="0" />
			<div class="row">
				<div
					class="well col-xs-10 col-sm-10 col-md-6 col-xs-offset-1 col-sm-offset-1 col-md-offset-3">
					<p>
						<em>Date:<fmt:formatDate type="date"
								value="${orderDetail.cdate}" /></em>
					</p>
					<p>
						<em>Receipt #: ${orderDetail.orderId}</em>
					</p>
					<p>
						<em>other #: </em>
					</p>
					<div class="row">
						<div class="col-xs-6 col-sm-6 col-md-6">
							<span class="label label-info">Bill to</span>
							<address>
								<strong>${billingAdd.name}</strong> <br>${billingAdd.street},
								<br>${billingAdd.address}<br> 
								<br>${billingAdd.city},${billingAdd.state}-${billingAdd.pincode}
								<br> <abbr title="Phone">P:</abbr>(+91)&nbsp;${billingAdd.mobile}
							</address>
						</div>
						<div class="col-xs-6 col-sm-6 col-md-6 text-right">
							<span class="label label-info">Ship to</span>
								<address>
									<strong>${shippingAdd.sName}</strong> <br>${shippingAdd.sStreet},
								<br>${shippingAdd.sAddress}<br> 
								<br>${shippingAdd.sCity},${shippingAdd.sState}-${shippingAdd.sPincode}
								<br> <abbr title="Phone">P:</abbr>(+91)&nbsp;${shippingAdd.sContact}
								</address>
						</div>
					</div>
					<div class="row">
						<div class="text-center">
							<h1>Receipt</h1>
						</div>
						</span>
						<table class="table table-hover">
							<thead>
								<tr>
									<th>Product</th>
									<th>#</th>
									<th class="text-center">Price</th>
									<th class="text-center">Total</th>
								</tr>
							</thead>
							<tbody>
								<c:forEach items="${itemDetail}" var="num">
									<tr>

										<td class="col-md-9"><em>${num.title}</em>(${num.gid})</td>
										<td class="col-md-1" style="text-align: center">${num.qty}</td>
										<td class="col-md-1 text-center">&#8377;&nbsp;${num.smrp}</td>
										<td class="col-md-1 text-center">&#8377;${(num.smrp*num.qty)}</td>
										<c:set var="totalValue"
											value="${totalValue=totalValue+(num.smrp*num.qty)}" />
										<c:set var="sValue" value="${sValue=sValue+1}" />
									</tr>
								</c:forEach>
								${other}
							</tbody>
						</table>
					</div>
				</div>

			</div>
		</c:if>
		<c:if test="${empty shippingAdd}">
			<div class="text-center">
				<img src="${pageContext.request.contextPath}/images/404.png"
					class="img-responsive center-block" alt="no request found">

			</div>
		</c:if>
	
		</div>
	</div>









</body>

</html>