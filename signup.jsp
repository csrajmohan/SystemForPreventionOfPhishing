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
function che()
{
var code=document.forms[0].t1.value;
alert("Secret code : "+code)
}
function si()
{

var name=document.forms[0].P_name.value;
var id=document.forms[0].P_username.value;
var pass=document.forms[0].P_password.value;
var pass1=document.forms[0].P_Reenter.value;
var myindex=document.forms[0].P_secret.selectedIndex;
var quest=document.forms[0].P_secret.options[myindex].text;
var ans=document.forms[0].P_answer.value;
var scode=document.forms[0].P_secode.value;
var code=document.forms[0].t1.value;
 
if(name==""||id==""||pass==""||pass1==""||quest==""||ans==""||scode=="")
alert("Please Enter all fields")
else if(pass!=pass1)
alert("Password Mismatch")
else if(pass.length<8)
alert("Password must greater than 7 Letter")
else if(scode!=code)
alert("Please Enter correct secret code")
else
{
//alert("correct")
document.forms[0].action="Signup";
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
			<li class="current_page_item"><a href="signup.jsp">Registration</a></li>
			<li><a href="signin.jsp">Login</a></li>
			<li><a href="forget.jsp">Forget Password</a></li>

		</ul>
	</div>
</div>
<!-- end header -->
<%! String co; %>
<%
Random r=new Random();
String s="ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz123456789";
char c=s.charAt(r.nextInt(60));
char c1=s.charAt(r.nextInt(60));
char c2=s.charAt(r.nextInt(60));
char c3=s.charAt(r.nextInt(60));
char c4=s.charAt(r.nextInt(60));
co=""+c+c1+c2+c3+c4;
%>
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
		<u><font face="Book Antiqua" size="4" color="#EE0000">Registration Form</font></u>
		</td>
		</tr>
		<tr></tr>
		<tr>
		<td><b>Name:</td>
		<td><input type=text name=P_name><td>
		</tr>
		<tr></tr>
		<tr>
			<td><b>UserName:*</td>
			<td><input type=text name=P_username><td>
		</tr>
		<tr></tr>
		<tr>
			<td><b>Password:*</td>
			<td><input type=password name=P_password><td>
		</tr>
		<tr></tr>
		<tr>
			<td><b>Re-enter Password:*</td>
			<td><input type=password name=P_Reenter><td>
		</tr>
		<tr></tr>
		<tr>
			<td><b>Secret Question:</td>
			<td>
			<select size="1" name="P_secret" style="font-weight: bold; border-style: solid; border-width: 1px">
			<option selected>Who is your favourite player?</option>
			<option>What is your first phone no?</option>
			<option>What is your favourite color?</option>
			<option>What is your favourite food?</option>
			<option>What is your favourite color</option></select><td>
		</tr>
		<tr></tr>
		<tr>	<td><b>Secret Answer:</td>
			<td><input type=text name=P_answer></td>
		</tr>
		<tr>	<td></td>
			<td><input value="<%=co%>" style="border:1px solid #FFFFFF; borer: 0px solid #FFFFFF; width:145; height:30; font-family:Pristina; font-size:20pt; font-weight:bold; color:#800000; letter-spacing:1; text-align:center; background-image:url('image/1.gif')" tabindex="80" name="t1" size="12" readonly></td>
			<td><input type=button value="Show Secret Code" onclick="che()" style="font-family: Book Antiqua; font-weight: bold; color: #800000; background-color: #000000; background-image: url('image/b1.jpg')"></td>
		</tr>
			<tr><td><b>Secret Code:</td>
			<td><input type=text name=P_secode></td>
		</tr>
		<tr>	<td></td>
			<td><input type=button onclick="si()" style="border:0px solid #FFFFFF; background-image: url('image/signup1.gif')" value="               "></td>
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