import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

import javax.servlet.*;
import javax.servlet.http.*;

import Appn.Member;


public class Signin extends HttpServlet
{
public void doPost(HttpServletRequest request,HttpServletResponse response)
throws ServletException, IOException
{
PrintWriter out = response.getWriter();
Member member=new Member();
member.id=request.getParameter("P_username")+"@pshark.com";
member.pwd=request.getParameter("P_password");
boolean result=signin(member);

if(result)
{
    HttpSession ses=request.getSession(true);

	if(ses!=null)
	{

	ses.setAttribute("user",member.id);
	ses.setAttribute("pwd",member.pwd);
	}

	RequestDispatcher rq=request.getRequestDispatcher("inbox.jsp");
	rq.forward(request,response);


}
else
{
	RequestDispatcher rq=request.getRequestDispatcher("index.jsp");
	request.setAttribute("msg","In valid entries");
	rq.forward(request,response);
}
}
public boolean signin(Member member)
{
	boolean exist=false;
	try
	{
		DriverManager.registerDriver( new oracle.jdbc.driver.OracleDriver());
		Connection conn = DriverManager.getConnection( "jdbc:oracle:thin:@"+getServletConfig().getServletContext().getInitParameter("server"),"system","redhat" );
		//PreparedStatement st = conn.prepareStatement("select * from MEMBER where id = '"+member.id+"'"+"and pwd= '"+member.pwd+"'");
		Statement st = conn.createStatement();
		ResultSet rs = st.executeQuery("select * from MEMBER where id ='"+member.id+"' and pwd='"+member.pwd+"'");
		System.out.println(member.id+"ggg:"+member.pwd);
		while(rs.next())
		{
			exist=true;
			System.out.println("step1");
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