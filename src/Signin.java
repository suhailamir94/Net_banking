import java.io.*;



import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
 


import javax.servlet.http.*;
public class Signin extends HttpServlet
{private static final long serialVersionUID = 1L;
protected void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
{     
     res.setContentType("text/html");
     PrintWriter pw=res.getWriter();
     String p=req.getParameter("accno.");
     String q=req.getParameter("upass");
     try{
    	 
     Class.forName("com.mysql.jdbc.Driver");
     Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");
     PreparedStatement ps=conn.prepareStatement("select * from net_banking where acc_no=? and password=? ");
     PreparedStatement ps1=conn.prepareStatement("select * from customer where acc_no=? ");


		

   ps.setString(1,p);
   ps.setString(2,q);
     ResultSet rs=ps.executeQuery();
     ps1.setString(1,p);
     ResultSet rs1=ps1.executeQuery();
    
  
     if(rs.next())
     {   
    	 rs1.next();
     String r=rs1.getString("customer_name");
     HttpSession session=req.getSession();
		session.setAttribute("uname",r);
		
		session.setAttribute("accno",p);
     
      pw.println("WELCOME "+r) ;
     RequestDispatcher rd=req.getRequestDispatcher("/main_menu.html");
    rd.include(req, res);
     
     }
     
     else{
    	 pw.println("<div align='center'>Please Enter Correct Details!!!</div>");
    	 RequestDispatcher rd=req.getRequestDispatcher("/index.html");
         rd.include(req, res);
     }
     }
     catch(Exception ex)
	 {	
	  ex.printStackTrace();
	 }

    	 
     
}

}
