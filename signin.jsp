<%@ page import="java.util.Random;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Strict//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-strict.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="content-type" content="text/html; charset=utf-8" />
<title>Pshark Framework</title>
<meta name="keywords" content="" />
<meta name="description" content="" />
<link href="default.css" rel="stylesheet" type="text/css" />
<script>
function login()
{
var uname=""+document.forms[0].P_username.value;
var pwd=""+document.forms[0].P_password.value;
//uname=uname.trim();
//pwd=pwd.trim();
if(uname==""||pwd=="")
alert("Please Enter All Fields")
else if(pwd.length<8)
alert("Password must greater than 7 Letter")
else
{
document.forms[0].action="Signin";
document.forms[0].submit();
}
}
</script>
</head>
<body>
<form method="post">
<!-- start header -->
<div id="header">
	<div id="logo">
		<h1><a href="#">Pshark</a></h1>
		<p></p>
	</div>
	<div id="menu">
		<ul>
			<li><a href="index.jsp">Home</a></li>
			<li><a href="signup.jsp">Registration</a></li>
			<li class="current_page_item"><a href="signin.jsp">Login</a></li>
			<li><a href="forget.jsp">Forget Password</a></li>

		</ul>
	</div>
</div>
<!-- end header -->

<!-- start page -->
<div id="page">
<div style="position: absolute; width: 100px; height: 100px; z-index: 4; right: 0; top: 173px" id="layer7">
<input border="0" src="image/earth04.gif" name="I5" width="86" height="83" type="image">
</div>
	<!-- start content -->
	<div id="content">
		<div class="post">
		<table border=0 cellpadding=5 cellspacing=7 style="position: absolute; left: 200px; width:600px">
		<tr><td></td>
		<td>
		<u><font face="Book Antiqua" size="4" color="#EE0000">Login Page</font></u>
		</td>
		</tr>
		<tr></tr>
		<tr>
			<td><b>UserName:*</td>
			<td><input type=text name=P_username><td>
		</tr>
		
		<tr></tr>
		<tr>	<td><b>Password:*</td>
			<td><input type=password name=P_password></td>
		</tr>
		
		<tr>	<td></td>
			<td><input type=button onclick="login()" style="border:0px solid #FFFFFF; background-image: url('image/find.gif')" value="        "></td>
		</tr>
		</table>
		</div>
		<div class="post">
		</div>
		</div>
		<div class="post">
		</div>
		<div class="post">
		</div>
		<div class="post">
		
	</div>

	<!-- end content -->
</div>
<!-- end page -->
<!-- start footer -->
<!--<div id="footer">
	<p class="legal"> &copy;2009 All Rights Reserved.
 </p>

</div>-->
<!-- end footer -->
</form>
</body>
</html>