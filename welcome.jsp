<%@ page import="Appn.*;" %>
<%!  Appn.MsgForm mf; %>

<html>
<head>
<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<link href="default.css" rel="stylesheet" type="text/css" />
<title>username 
signout</title>
<script language=javascript >
function nextt()
{
document.forms[0].action="signin.jsp";
document.forms[0].submit();
}
</script>
</head>

<body>
<div id="header">
	<div id="logo">
		<h1><a href="#">Pshark</a></h1>
		<p></p>
	</div>
</div>
<form method="post">
<div style="position: absolute; width: 253px; height: 47px; z-index: 6; left: 348px; top: 188px" id="layer12">
	<b><font face="Chiller" size="7" color="#33CCFF">PsharkMail</font></b></div>
<div style="position: absolute; width: 253px; height: 23px; z-index: 8; left: 348px; top: 329px" id="layer14">
<b><font size="3">
<%=session.getAttribute("user")%></font></b></div>
<p><font color="#970000">
</font>
<font color="#970000"></font></a></font></p>
 <div style="position: absolute; width: 345px; height: 23px; z-index: 8; left: 349px; top: 275px" id="layer20"> 
 <b><font color=#00FFFF>Welcome, Your account is successfully Created..</font></b>
 </div>
</form>
<div style="position: absolute; width: 75px; height: 35px; z-index: 3; left: 264px; top: 330px" id="layer8">
	<b><font color="#FF9900">Email-Id</font></b><font color="#FF9900"> </font>
	
</div>
<div style="position: absolute; width: 99px; height: 65px; z-index: 4; left: 440px; top: 387px" id="layer10">
<input type=button name=b1 style="width: 85; height: 55; border: 1px solid #FFFFFF; background-color: #FFFFFF; background-image: url('image/login.gif')" onclick="nextt()"></div>
</body>

</html>