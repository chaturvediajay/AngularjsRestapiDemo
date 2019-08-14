
<div class="row">



<c:if test="${not empty proList}">
	<c:set var="total" value="0" scope="page" />
	<c:set var="proCount" value="${fn:length(featuredPro)}" scope="page" />
	<c:forEach var="fp" items="${proList}">
	
	<c:set var="total" value="${total+1}" scope="page" />
	
	
  <div class="col-md-3 product simpleCart_shelfItem text-center">
			<a href="single.html"><img style="width:201px;" src="https://p.w3layouts.com/demos/e_shop/web/images/p1.jpg" alt="" /></a>
						<div class="mask">
							<a href="single.html">Quick View</a>
						</div>
						<a class="product_name" href="single?pkey=${fp.pkey}">${fp.title}</a>
						<p><a class="item_add" href="#"><i></i> <span class="item_price">${fp.smrp}</span></a></p>
					</div>
					
					
					
					
  <div class="col-md-3 product simpleCart_shelfItem text-center">
			<a href="single.html"><img style="width:201px;" src="https://p.w3layouts.com/demos/e_shop/web/images/p1.jpg" alt="" /></a>
						<div class="mask">
							<a href="single.html">Quick View</a>
						</div>
						<a class="product_name" href="single.html">${fp.title}</a>
						<p><a class="item_add" href="#"><i></i> <span class="item_price">${fp.smrp}</span></a></p>
					</div>
					
					
	  <div class="col-md-3 product simpleCart_shelfItem text-center">
			<a href="single.html"><img style="width:201px;" src="https://p.w3layouts.com/demos/e_shop/web/images/p1.jpg" alt="" /></a>
						<div class="mask">
							<a href="single.html">Quick View</a>
						</div>
						<a class="product_name" href="single.html">${fp.title}</a>
						<p><a class="item_add" href="#"><i></i> <span class="item_price">${fp.smrp}</span></a></p>
					</div>
					
					
					
	  <div class="col-md-3 product simpleCart_shelfItem text-center">
			<a href="single.html"><img style="width:201px;" src="https://p.w3layouts.com/demos/e_shop/web/images/p1.jpg" alt="" /></a>
						<div class="mask">
							<a href="single.html">Quick View</a>
						</div>
						<a class="product_name" href="single.html">${fp.title}</a>
						<p><a class="item_add" href="#"><i></i> <span class="item_price">${fp.smrp}</span></a></p>
					</div>
					
					
  <div class="col-md-3 product simpleCart_shelfItem text-center">
			<a href="single.html"><img style="width:201px;" src="https://p.w3layouts.com/demos/e_shop/web/images/p1.jpg" alt="" /></a>
						<div class="mask">
							<a href="single.html">Quick View</a>
						</div>
						<a class="product_name" href="single.html">${fp.title}</a>
						<p><a class="item_add" href="#"><i></i> <span class="item_price">${fp.smrp}</span></a></p>
					</div>
	
	</c:forEach>

</c:if>









         </div>  