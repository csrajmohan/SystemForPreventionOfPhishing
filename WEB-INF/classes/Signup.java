import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import javax.servlet.*;
import javax.servlet.http.*;

import Appn.Member;
public class Signup extends HttpServlet
{
public void doPost(HttpServletRequest request,HttpServletResponse response)
throws ServletException, IOException
{
PrintWriter out = response.getWriter();
Member member=new Member();
ServerReg s=new ServerReg();
member.name=request.getParameter("P_name");
String user=request.getParameter("P_username");
member.id=user+"@pshark.com";
member.pwd=request.getParameter("P_password");
member.secq=request.getParameter("P_secret");
member.seca=request.getParameter("P_answer");

boolean result=signup(member);
if(result)
{

		request.setAttribute("user",member.id);
		request.setAttribute("pwd",member.pwd);
		request.setAttribute("secq",member.secq);
		request.setAttribute("seca",member.seca);
		request.setAttribute("name",member.name);

	RequestDispatcher rq=request.getRequestDispatcher("modsignup.jsp");
	request.setAttribute("msg","Id already exist,Please choose another ID");
	rq.forward(request,response);

}
else
{
	HttpSession ses=request.getSession(true);
	if(ses!=null)
	{
	ses.setAttribute("user",member.id);
	ses.setAttribute("pwd",member.pwd);
	ses.setAttribute("seca",member.seca);
	ses.setAttribute("secq",member.secq);
	}
	s.Reg(user,member.pwd,getServletConfig().getServletContext().getInitParameter("path"),getServletConfig().getServletContext().getInitParameter("mailserver"));
	RequestDispatcher rq=request.getRequestDispatcher("welcome.jsp");
	rq.forward(request,response);
}
}
public boolean signup(Member member)
{
	boolean exist=false;
	try
	{
		DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver() );
		Connection conn = DriverManager.getConnection( "jdbc:oracle:thin:@"+getServletConfig().getServletContext().getInitParameter("server"),"system","redhat" );
		PreparedStatement st = conn.prepareStatement("Select * from MEMBER WHERE id = '"+member.id+"'");
		System.out.println("member id$$$"+member.id);
		ResultSet rs = st.executeQuery();
		while(rs.next())
		{
			exist=true;
		}

	if(!exist)
	{
		String query="insert into MEMBER values('"+member.name+"','"+member.id+"','"+member.pwd+"','"+member.secq+"','"+member.seca+"')";
		st.executeUpdate(query);

	}

	st.close();
	conn.close();

	}
	catch(Exception e)
	{
		return true;
	}

	return exist;
}

}