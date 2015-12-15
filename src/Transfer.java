import java.io.*;
 




//import java.sql.Connection;
//import java.sql.DriverManager;
//import java.sql.PreparedStatement;
import javax.servlet.*;
import javax.servlet.http.*;
public class Transfer extends HttpServlet
{private static final long serialVersionUID = 1L;
protected void service(HttpServletRequest req,HttpServletResponse res)throws IOException,ServletException
{
	res.setContentType("text/html");
	RequestDispatcher rd=req.getRequestDispatcher("/transfer.html");
    rd.include(req, res);


}

}
