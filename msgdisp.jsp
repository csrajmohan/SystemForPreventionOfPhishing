<%@ page import="Appn.*;" %>
<%! Appn.MsgForm mf; %>

<html>
<head>
<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<link href="default.css" rel="stylesheet" type="text/css" />
<%! String filedemo="",outdate; %>
<title>username 
signout</title>
<script language=javascript >

onload=function() {
//var ss = "<%=filedemo %>";
var ss = document.forms[0].hid.value;
var out_date = document.forms[0].out_date.value;
//alert(ss);
if(ss =="Phising Mail") {
//alert(out_date);
alert(ss);
document.getElementById('phisd').style.display='block';
}

}
function backk()
{
 history.back();
}
function domaindetail() {
document.forms[0].action="display.jsp";
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
<!--<div style="position: absolute; width: 45px; height: 48px; z-index: 2; left:710px; top:181px" id="layer2">
<div style="position: absolute; width: 834px; height: 100px; z-index: 1; left: -731px; top: -184px" id="layer3">
<div style="position: absolute; width: 172px; height: 28px; z-index: 1; left: 329px; top: 49px" id="layer7">
<b><font size="7" color="#FFFFFF" face="Chiller">Global Mail</font></b></div>
<img src="image/in.jpg"></div>
&nbsp;</div>-->
<p><font color="#970000">
</font>
<font color="#970000"></font></a></font></p>
<div style="position: absolute; width: 669px; height: 60px; z-index: 1; left:6px; top:156px" id="layer1">
	
	<blockquote>
		<blockquote>
			<h1><b><font size="7" color="#99FF66" face="Chiller">Message Details
			</font></b><font color="#000080" face="French Script MT"><b>
			<font size="7" face="Chiller" color="#800000">
			<input type=hidden name="index" size="20"></font></b></font></h1>
			<div style="position: absolute; width: 317px; height: 59px; z-index: 1; left: 650px; top: 10px" id="layer5">
				<center>
				<font size="4" face="Bell MT" color="#33CCCC"><%=session.getAttribute("user")%></font></center>
			</div>
		</blockquote>
	</blockquote>
	<% mf=(Appn.MsgForm)session.getAttribute("msg"); %>



	<div style="position: absolute; width: 770px; height: 241px; z-index: 3; left: 5px; top: 125px" id="layer8">
	<center><table width="106%" id="table1" height="234" cellpadding=7>
	<tr>
		<td width="252" height="40" background="image/b11.jpg"><b><font color="#959500" size="5">&nbsp;&nbsp;&nbsp;&nbsp;</font><font color="#800080" size="5">&nbsp;
		</font>
		<font face="Chiller" color="#FF0000" size="5">From</font></b></td>
	

	<% String fr=mf.from; %>
		<%boolean ok=true; %>
		<%if(!(mf.sp).equals("")){ %>
		<%fr=mf.sp; %>
		<% ok=false; %>
		<%} %>
		<% if(ok){%>
		<% fr=fr.replace("@"+(String)application.getInitParameter("mailserver"),"@pshark.com"); %>
        <%} %>			
			
	
		<td height="40"  background="image/b11.jpg"><font color="#000080">
		<div style="position: absolute; width: 71px; height: 28px; z-index: 1; left: 744px; top: -33px" id="layer9">
	<input type=button name=b onclick="backk()" style="font-family: Lucida Calligraphy; font-weight: bold; width: 70; height: 27; border: 1px solid #FFFFFF; background-image: url('image/pre.gif')"></div>
		<%=fr %><input name="fromemail" type="hidden" value="<%=fr %>"></font></td>
	</tr>
	<tr>
		<td  background="image/b11.jpg" width="252" height="39"><b><font color="#959500" size="5">&nbsp;&nbsp;&nbsp;&nbsp; </font>
		<font color="#800080" size="5" >&nbsp;</font><font face="Chiller" color="#FF0000" size="5">Subject</font></b></td>
		<td height="39"  background="image/b11.jpg"><font color="#000080"><%=mf.subject%></font></td>
	</tr>
	<tr>
		<td width="252"  background="image/b11.jpg"><b><font color="#000080" size="5">&nbsp;&nbsp;&nbsp;&nbsp; </font>
		<font face="Bradley Hand ITC" size="5" color="#800080">&nbsp;</font><font face="Chiller" size="5" color="#FF0000">Date</font></b></td>
		<td  background="image/b11.jpg"><font color="#000080"><%=mf.date%></font></td>
	</tr>
	<tr>
		<td width="252"  background="image/b11.jpg"><b><font color="#000080" size="5">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </font>
		<font face="Chiller" size="5" color="#FF0000">Size</font></b></td>
		<td  background="image/b11.jpg"><font color="#000080"><%=mf.size%></font></td>
	</tr>
	<% outdate = mf.outdate; %>
	<%String status[]={"Phising Mail","Possible of Phising","Not Phising"}; %>
	<%int s=0; %>
	<% String domain=fr.substring(fr.lastIndexOf("@")+1,fr.lastIndexOf(".")); %>
	<%Appn.Analyzer ob=new Appn.Analyzer();%>
	<%s=ob.analyze(mf.msg,(String)application.getInitParameter("server"),domain)+1;%>
	<%mf.msg=ob.changeLink(mf.msg,(String)application.getInitParameter("server"),domain);%>
	<% filedemo =(String)status[s]; %>
	<tr>
		<td width="252" height="36"  background="image/b11.jpg"><b><font size="5" color="#959500">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </font>
		<font face="Chiller" size="5" color="#FF0000">Status</font></b></td>
		<td height="36"  background="image/b11.jpg"><font color="#000080"><%=status[s]%></font><input type=hidden name="hid" value="<%=filedemo  %>"></td>
	</tr>
	<tr>
		<td width="252" height="33"  background="image/b11.jpg"><b><font size="5" color="#959500">&nbsp;&nbsp;&nbsp;&nbsp;&nbsp; </font>
		<font face="Chiller" size="5" color="#FF0000">Message</font></b></td>
		<%
		String hide = status[s].toString();
		if(!hide.equals("Phising Mail")) {
		%>
		<td height="33"  background="image/b11.jpg"><font color="#000080"></font><input name="dom" type=hidden value="<%= domain %>"></td>
		<%
		} else {
		%>
		<td height="33"  background="image/b11.jpg"><font color="#000080"><%=mf.msg%></font><input name="dom" type=hidden value="<%= domain %>"></td>
		<%
		}
		%>
		<input name="out_date" type=hidden value="<%= outdate %>">
	</tr>

	
</table></center></div>
<input type=hidden name="emailmessage" value="<%=mf.msg %>">
</div> 
<div id="phisd" name="phisd" style="position: absolute; top=567px; left: 270px; display:none"  >
	
<input type="button" value="Submit" onclick="domaindetail()">	

</div>
</form>
</body>

</html>