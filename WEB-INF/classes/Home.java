import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
public class Home extends HttpServlet
{
	String password;
public void doGet(HttpServletRequest request,HttpServletResponse response)
throws ServletException, IOException
{
PrintWriter out = response.getWriter();
	RequestDispatcher rq=request.getRequestDispatcher("home.html");
	rq.include(request,response);
}


}