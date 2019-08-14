<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<!-- head -->
	<%@include file="head.jsp"%>
<!-- End head -->
<style type="text/css">
body {
    background:#333;
}

.form_bg {
    background-color:#eee;
    color:#666;
    padding:20px;
    border-radius:10px;
    position: absolute;
    border:1px solid #fff;
    margin: auto;
    top: 0;
    right: 0;
    bottom: 0;
    left: 0;
    width: 320px;
    height: 280px;
}
.align-center {
    text-align:center;
}</style>
<body> 

<!--banner-->

	<div class="container">
    <div class="row">
        <div class="form_bg">
            <form>
                 <h2 class="text-center">admin login</h2>
                <br/>
                <div class="form-group">
                    <input type="email" class="form-control" id="userid" placeholder="User id">
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" id="pwd" placeholder="Password">
                
                    </div>
                    <br/>
                   <div class="align-center">
                <button type="submit" class="btn btn-default" id="login">Login</button>
                    </div>
            </form>
        </div>
    </div>
</div>
<!--   part : Just a footer -->
<div style="position:fixed;bottom:10px;left:10px;background:#4679BC;padding:4px;border-radius:2px;border:1px solid #4679AA">
<a href="http://www.kritifab.com/" title="more ..." style="padding:6px;text-decoration:none;font-size:12px;color:#fff;letter-spacing: 1.5px;">
back to home</a>
</div>
	
	<!---->
	<!---->
	<!--footer-->	
<!-- End footer -->
	
<a href="#" id="toTop">To Top</a>

</body>

</html>