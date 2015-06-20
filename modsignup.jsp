<%@ page import="java.util.Random;" %>
<html xmlns:v="urn:schemas-microsoft-com:vml" xmlns:o="urn:schemas-microsoft-com:office:office" xmlns="http://www.w3.org/TR/REC-html40">
<head>
<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<link href="default.css" rel="stylesheet" type="text/css" />
<title>New Page 1</title>
<script>
function che()
{
var code=document.forms[0].t1.value;
alert("Secret code : "+code)
}
function si()
{
alert("hai")

var id=document.forms[0].p2.value;
var scode=document.forms[0].sec.value;
var code=document.forms[0].t1.value;
 
if(id=="" || scode=="")
alert("Please Enter all fields")
else if(scode!=code)
alert("Please Enter correct security code")
else
{
alert("correct")
document.forms[0].action="Signup";
document.forms[0].submit();
}

}
function hom()
{
document.forms[0].action="home.jsp";
document.forms[0].submit();
}
</script>
</head>
<body background="image/theme.gif">
<div id="header">
	<div id="logo">
		<h1><a href="#">Pshark</a></h1>
		<p></p>
	</div>
	<div id="menu">
		<ul>
			<li><a href="index.jsp">Home</a></li>
			<li class="current_page_item"><a href="signup.jsp">Registrarion</a></li>
			<li><a href="signin.jsp">Login</a></li>
			<li><a href="forget.jsp">Forget Password</a></li>

		</ul>
	</div>
</div>
<div style="position: absolute; width: 361px; height: 30px; z-index: 6; left: 522px; top: 354px" id="layer12">
<center><font color=#CC0000>
<b>
<font color=#009900>Show Msg:</font>
<%
out.println(request.getAttribute("msg"));
%></b></font></center>
</div>

<p>&nbsp;</p>
<p>
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
<!--<marquee behavior=slide bgcolor=#FFFFCC><font size=7 color=red face="Arial"><%=co%></font></marquee>
-->
</p>

<p>&nbsp;</p>
<p>&nbsp;</p>
<blockquote>
	<p>&nbsp;</p>
	

	<form method=post>		

						<pre><b><font color="#00FFFF">Name</font></b>		   <font color="#008080"><input type=text name=p1 value="<%=request.getAttribute("name").toString()%>" readonly ></font><br>
<b><font color="#00FFFF">Username</font></b>	   <input type=text name=p2><br>
<b><font color="#00FFFF">Password</font></b>	   <input type=password name=p31 value="<%=request.getAttribute("pwd").toString()%>" readonly><br>
<b><font color="#00FFFF">Retype Password</font></b>    <input type=password name=p32 value="<%=request.getAttribute("pwd").toString()%>" readonly><br>
<b><font color="#00FFFF">Secret Question</font></b>    <input type=text name=p4 value="<%=request.getAttribute("secq").toString()%>" size="30" readonly><br>

<b><font color="#00FFFF">	  	  	  	  
	  	   	  	  	  	  
	  	   <input value="<%=co%>" style="border:1px solid #FFFFFF; borer: 0px solid #FFFFFF; width:145; height:30; font-family:Pristina; font-size:20pt; font-weight:bold; color:#800000; letter-spacing:1; text-align:center; background-image:url('image/1.gif')" tabindex="80" name="t1" size="12" readonly>  <input type=button value="Show Secret Code" onclick="che()" style="font-family: Book Antiqua; font-weight: bold; color: #800000; background-color: #000000; background-image: url('image/b1.jpg')"><br>
<b><font color="#00FFFF"><font color="#00FFFF"><b>Secret Code</font></b>	   <input type=text name=sec><br>
  
		     			<input type=button onclick="si()" style="border:0px solid #FFFFFF; background-image: url('image/signup1.gif')" value="              ">

			</blockquote>
	</form>
</blockquote>
</body>

</html>