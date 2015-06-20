import java.io.*;
import java.util.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import org.apache.axis.client.Call;
import org.apache.axis.client.Service;
import org.apache.axis.encoding.XMLType;
import org.apache.axis.utils.Options;
import javax.xml.rpc.ParameterMode;
import javax.xml.rpc.ServiceException;
import javax.xml.namespace.QName;
import javax.servlet.*;
import javax.servlet.http.*;
import Appn.*;

public class Sendmail extends HttpServlet {
	public Vector output;
	public String smtp_server="";
public void doPost(HttpServletRequest request,HttpServletResponse response)
throws ServletException, IOException
{
PrintWriter out = response.getWriter();

String to=request.getParameter("t1").trim();
String sub=request.getParameter("t2");
String body=request.getParameter("t3");
String spoof="";
//boolean result=check(to);
boolean result=true;

String st = to.substring(to.lastIndexOf("@")+1);
String domainst = st.substring(0,st.indexOf("."));

try{
	FileInputStream propFile = null;

	String path=request.getRealPath("/");
	path=path+"WEB-INF\\classes\\";
	propFile=new FileInputStream(path+"service.properties");

	Properties props=new Properties();
	props.load(propFile);
	String endpoint=props.getProperty("domainservice");

	Service service=new Service();
	Call call=(Call)service.createCall();
	call.setTargetEndpointAddress(new java.net.URL(endpoint));
	call.setOperationName(new QName("getdomainlist"));
	smtp_server=(String)call.invoke(new Object[]{domainst});

} catch(Exception ex) {
	ex.printStackTrace();
}
System.out.println("hhhh :"+output);
/*if(output.size()>0) {

	smtp_server = output.get(0).toString();
}*/

if(result)
{
	Mail m=new Mail();
	HttpSession ses=request.getSession();
	boolean ack=m.send(smtp_server,to,(String)ses.getAttribute("user"),sub,body,spoof);
	//boolean ack=m.send(getServletConfig().getServletContext().getInitParameter("mailserver"),to,(String)ses.getAttribute("user"),sub,body,spoof);
	if(ack)
	{
	RequestDispatcher rq=request.getRequestDispatcher("compose.jsp");
	request.setAttribute("msg","Your Message Sent");
	rq.forward(request,response);
	}
	else
	{

		RequestDispatcher rq=request.getRequestDispatcher("compose.jsp");
		request.setAttribute("msg","The Message Sending failure");
		rq.forward(request,response);

	}

}
else
{
	RequestDispatcher rq=request.getRequestDispatcher("compose.jsp");
	request.setAttribute("msg","The receipient not exist");
	rq.forward(request,response);
}
}

public boolean check(String to)
{
	boolean exist=false;
	try
	{
		DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver() );
		Connection conn = DriverManager.getConnection( "jdbc:oracle:thin:@"+getServletConfig().getServletContext().getInitParameter("server"),"system","redhat" );
		PreparedStatement st = conn.prepareStatement("Select * from MEMBER WHERE id = '"+to+"'");
		ResultSet rs = st.executeQuery();
		while(rs.next())
		{
			exist=true;
		}

		st.close();
	conn.close();

}
catch(Exception e)
{
	e.printStackTrace();
	return false;
}

return exist;
}


}