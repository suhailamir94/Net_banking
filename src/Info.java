import java.io.*;

import java.sql.ResultSet;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.*;
import javax.servlet.http.*;
public class Info extends HttpServlet
{private static final long serialVersionUID = 1L;

protected void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
{ res.setContentType("text/html");

PrintWriter pw=res.getWriter();
HttpSession session=req.getSession();
String acc=(String)session.getAttribute("accno");
int i=5;
try{
	
	  Class.forName("com.mysql.jdbc.Driver");
	  Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");
	  PreparedStatement ps1=conn.prepareStatement("select * from transaction where acc_no=? order by reg_no desc ");
	  ps1.setString(1, acc);
	  ResultSet rs1=ps1.executeQuery();
	  if(!rs1.next())
	  {
		  pw.print("No last 5 transactions available!!!");
	  }
	  else
	  {
	  pw.print("<br><br>Last 5 Actions :<br><br>");
	  int j=1;
	  while(i>0 )
	  {
	   pw.print("<br><br>"+j +". "+" Action: "+rs1.getString("action")+"<br><br>Amount: "+rs1.getString("balance")+"<br><br>Date & Time: "+rs1.getString("date"));
	  j++;
	  i--;
	  rs1.next();
	  }
	 
}
}
	 catch(Exception ex)
	 {	
	  ex.printStackTrace();
	 }
	}
}
