import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.*;
import javax.servlet.http.*;

import Appn.Member;

public class Forget extends HttpServlet
{
	String password;
public void doPost(HttpServletRequest request,HttpServletResponse response)
throws ServletException, IOException
{
PrintWriter out = response.getWriter();
Member member=new Member();
member.id=request.getParameter("P_username");
member.secq=request.getParameter("P_secret");
member.seca=request.getParameter("P_answer");

boolean result=search(member);
if(result)
{

	RequestDispatcher rq=request.getRequestDispatcher("forget.jsp");
	request.setAttribute("msg","U r member.Ur Password is "+password);
	rq.include(request,response);
}
else
{
	RequestDispatcher rq=request.getRequestDispatcher("forget.jsp");
	request.setAttribute("msg","Invalid Entry,Try again");
	rq.include(request,response);
}
}
public boolean search(Member member)
{
	boolean exist=false;
	try
	{

		DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver() );
		Connection con = DriverManager.getConnection( "jdbc:oracle:thin:@"+getServletConfig().getServletContext().getInitParameter("server"),"system","redhat" );
		Statement st=con.createStatement();
	ResultSet rs=st.executeQuery("Select * from MEMBER");
	while(rs.next())
	{
		if(member.id.equals(rs.getString(2))&&member.secq.equals(rs.getString(4))&&member.seca.equals(rs.getString(5)))
				{
			password=rs.getString(3);
			exist=true;
			break;
				}
	}
	st.close();
	con.close();

	}
	catch(Exception e)
	{
		return false;
	}

	return exist;
}


}