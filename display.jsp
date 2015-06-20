<%@ page language="java" import="org.apache.axis.encoding.*,org.apache.axis.utils.*,org.apache.axis.client.*,javax.xml.namespace.*,java.io.*,java.net.*,java.util.*;" %>

<html>
<head>
<meta http-equiv="Content-Language" content="en-us">
<meta http-equiv="Content-Type" content="text/html; charset=windows-1252">
<link href="default.css" rel="stylesheet" type="text/css" />
<%! Vector vt = new Vector(); 
String getemail,act,prevmail;
%>
<title>username 
signout</title>
<script language=javascript >

function backk()
{
 history.back();
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
<div style="position: absolute; width: 71px; height: 28px; z-index: 1; left: 897px; top: 167px" id="layer9">
<input type=button name=b onclick="backk()" style="font-family: Lucida Calligraphy; font-weight: bold; width: 70; height: 27; border: 1px solid #FFFFFF; background-image: url('image/pre.gif')"></div>

<table id="log" border="1" style="position: absolute;width: 454px; height: 200px; left:260px; top:209px">
<tr><td><center><font color=#0099CC>Domain Details</font></center></td>
</tr>
<%
String from = session.getAttribute("user").toString();
String domainst = request.getParameter("dom").toString();
String fromemail = request.getParameter("fromemail").toString();
String status = request.getParameter("hid").toString();
String milmsg = request.getParameter("emailmessage").toString();
String outdate = request.getParameter("out_date").toString();
out.println(outdate);
session.setAttribute("statu",status );
prevmail = milmsg; 
boolean exist=false;
try{
	FileInputStream propFile = null;

	String path=request.getRealPath("/");
	path=path+"WEB-INF\\classes\\";
	propFile=new FileInputStream(path+"service.properties");
	

	Properties props=new Properties();
	props.load(propFile);
	String endpoint=props.getProperty("domainservice");
	System.out.println("endpoint==="+endpoint);
	String st = from.substring(from.lastIndexOf("@")+1);
	String getemil = st.substring(0,st.indexOf("."));
	Service service1=new Service();
	Call call1=(Call)service1.createCall();
	call1.setTargetEndpointAddress(new java.net.URL(endpoint));
	call1.setOperationName(new QName("getEmailaddress"));
	getemail=(String)call1.invoke(new Object[]{getemil});	
	if(getemail.equals(from) && outdate.equals("outdate")) {
		session.setAttribute("milmsg",milmsg);
		response.sendRedirect("flood.jsp");
	}
	if(!getemail.equals(from)) {
		//out.println("111");
		Service service2=new Service();
		Call call2=(Call)service2.createCall();
		call2.setTargetEndpointAddress(new java.net.URL(endpoint));
		call2.setOperationName(new QName("getdomainall_list"));
		vt=(Vector)call2.invoke(new Object[]{getemil});
	
	} else {
		//out.println("2222");
		String current="";
		milmsg=milmsg.substring(milmsg.indexOf("<a"),milmsg.length());
		current=milmsg.substring(0,milmsg.indexOf("/a>")+3);
		String act=current.substring(0,current.indexOf(">"));
		act=act.substring(act.indexOf("=")+1,act.length());
		String vis=current.substring(current.indexOf(">")+1,current.lastIndexOf("<"));
		milmsg=milmsg.substring(current.length(),milmsg.length());
		URL url = new URL(act);
		String dd = url.getAuthority();
		//System.out.println("2222**** "+dd);
		
		String actdomain = act.substring(act.indexOf(dd));
		String act_dom = actdomain.substring(actdomain.indexOf("/")+1);
		String act_domain = act_dom.substring(0,act_dom.indexOf("/"));
      
		Service service3=new Service();
		Call call3=(Call)service3.createCall();
		call3.setTargetEndpointAddress(new java.net.URL(endpoint));
		call3.setOperationName(new QName("getdomainall_list"));
		vt=(Vector)call3.invoke(new Object[]{act_domain});
		if(status.equals("Phising Mail")) {
			//out.println("act_domain=="+act_domain+"act=="+act+"status=="+status);
			Service service4=new Service();
			Call call4=(Call)service4.createCall();
			call4.setTargetEndpointAddress(new java.net.URL(endpoint));
			call4.setOperationName(new QName("setPhishing"));
			exist=(Boolean)call4.invoke(new Object[]{act_domain,act,status,"Not Clear"});
		}

		
	}

} catch(Exception ex) {
	ex.printStackTrace();
}
%>

<%
if(vt.size()>0) {
%>
<%
String fname = vt.get(0).toString();
String lname = vt.get(1).toString();
String add1 = vt.get(2).toString();
String add2 = vt.get(3).toString();
String city = vt.get(4).toString();
String country = vt.get(5).toString();
String state = vt.get(6).toString();
String email = vt.get(8).toString();
String domainname = vt.get(9).toString();
String buss = vt.get(10).toString();
String sys = vt.get(11).toString();
%>
<tr><td>DomainName</td><td>
<font><center><%=domainname %></center> </font></td>
</tr>
<tr><td>FirstName</td><td>
<font><center><%=fname %></center> </font></td>
</tr>
<tr><td>LastName</td><td>
<font><center><%=lname %></center> </font></td>
</tr>
<tr><td>add1</td><td>
<font><center><%=add1 %></center> </font></td>
</tr>
<tr><td>add2</td><td>
<font><center><%=add2 %></center> </font></td>
</tr>
<tr><td>City</td><td>
<font><center><%=city %></center> </font></td>
</tr>
<tr><td>Country </td><td>
<font><center><%=country %></center> </font></td>
</tr>
<tr><td>State</td><td>
<font><center><%=state %></center> </font></td>
</tr>
<tr><td>Business Type</td><td>
<font><center><%=buss %></center> </font></td>
</tr>
<tr><td>System</td><td>
<font><center><%=sys %></center> </font></td>
</tr>
<tr><td>Contact Email</td><td>
<font><center><a href="modCompose.jsp"><%=email %></a></center> </font><% session.setAttribute("toemailmsg",email); %> 
<% session.setAttribute("tobodymsg",prevmail); %></td>
</tr>

<%
}
%>
</table>
</form>
</body>

</html>