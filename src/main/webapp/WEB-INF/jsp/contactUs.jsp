<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
		<div class="contact">
	<h2>CONTACT US...</h2>
				<div class="contact-in">
				<div class=" col-md-3 contact-right">
				     	<h5>KritiFab and textiles</h5>
						   		<p>12/62, Kaveri Path, Mansarovar,</p>
						   		<p>Jaipur,Rajasthan-302020</p>
						   		<p>India</p>
				   		<p>Phone:(00) 222 666 444</p>
				   		<p>Fax: (000) 000 00 00 0</p>
				 	 	<p>Email: <a href="mailto:info@kritifab.com">info@kritifab.com</a></p>
				   		<p>Follow on: <a href="#">Facebook</a>, <a href="#">Twitter</a></p>
				    </div>
				<div class=" col-md-9 contact-left">
				  
					    <form >
					    	<div>
						    	<span>Name</span>
						    	<input name="userName" type="text" class="textbox">
						    </div>
						    <div>
						    	<span>E-Mail</span>
						    	<input name="userEmail" type="text" class="textbox">
						    </div>
						    <div>
						    	<span>Subject</span>
						    	<textarea name="userMsg"> </textarea>
						    </div>
						   <div>
						   		<input type="submit" value="Submit">
								
						  </div>
					    </form>
				  </div>

					
					  <div class="clearfix"></div>
				 </div>
			    
      		</div>
	</div>
		<div class="map">
				 <iframe src="https://www.google.com/maps/embed?pb=!1m18!1m12!1m3!1d505145.6949089349!2d115.07157704999999!3d-8.455471450000001!2m3!1f0!2f0!3f0!3m2!1i1024!2i768!4f13.1!3m3!1m2!1s0x2dd22f7520fca7d3%3A0x2872b62cc456cd84!2sBali%2C+Indonesia!5e0!3m2!1sen!2sin!4v1418170815897"></iframe>
				</div>


	
	<!--footer-->	
	<%@include file="footer.jsp"%>
<!-- End footer -->
	
<a href="#" id="toTop">To Top</a>
</body>
</html>