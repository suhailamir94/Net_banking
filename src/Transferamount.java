import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import javax.servlet.*;

import java.sql.ResultSet;
import java.util.Date;

import javax.servlet.http.*;
public class Transferamount extends HttpServlet
{private static final long serialVersionUID = 1L;

protected void service(HttpServletRequest req,HttpServletResponse res)throws ServletException,IOException
{ res.setContentType("text/html");


PrintWriter pw=res.getWriter();

String q=req.getParameter("accno.");
String r=req.getParameter("amount");
Date date=new Date();
String time=date.toString(); 
HttpSession session=req.getSession();
String acc=(String)session.getAttribute("accno");

try{
	
	  Class.forName("com.mysql.jdbc.Driver");
	  Connection conn=DriverManager.getConnection("jdbc:mysql://localhost:3306/bank","root","root");
	  PreparedStatement ps=conn.prepareStatement("update customer set balance=?,status=? where acc_no=?");
	 PreparedStatement ps1=conn.prepareStatement("update customer set balance=?, status=? where acc_no=?");
	  PreparedStatement pst=conn.prepareStatement("select * from customer where acc_no=? "); 
	  PreparedStatement pst1=conn.prepareStatement("select * from customer where acc_no=? ");
	  PreparedStatement ps2=conn.prepareStatement("insert into transaction(balance,action,date,acc_no)values(?,?,?,?)");
	  int t=0;
	  if(q.equals("") || r.equals(""))
	  {
	  	  pw.print("Please enter valid  account no.s");
	  	   RequestDispatcher rd=req.getRequestDispatcher("/transfer.html");  
	  		 rd.include(req,res);
	  		 t=1;
	  } 
	  pst.setString(1,acc);
	  ResultSet rs=pst.executeQuery();
	  
	
	  boolean x=rs.next();
	  pst1.setString(1,q);
	  ResultSet rst=pst1.executeQuery();
	  boolean y=rst.next();
	  int i=0;
	
	  if(!x && !y && t==0)
	  { pw.print("Please enter valid  account no.s");
	   RequestDispatcher rd=req.getRequestDispatcher("/transfer.html");  
		 rd.include(req,res);
	  }
	  String status= rs.getString("status");
	 
	 int o=0;
	 
	  String 	status1= rst.getString("status");
	 
	  int balance=rs.getInt("balance");
	  int amount=Integer.parseInt(r.trim());
	  int balance1=rst.getInt("balance");
	  if (status.equals("X") || status1.equals("X") )
	  {pw.print("Sorry!! Either The Source Account has been Closed<br><br>OR<br><br> The Destination Account Has Been Closed<br><br>Can't transfer money");
	   RequestDispatcher rd=req.getRequestDispatcher("/main_menu.html");  
		 rd.include(req,res);
	  i=1;
	  }
	  if(amount>balance && i==0)
	  {pw.print("your balance is less than the entered amount<br>please add some money to your account");
	   RequestDispatcher rd=req.getRequestDispatcher("/main_menu.html");  
		 rd.include(req,res);
	  o=1;}
	  if((status.equals("N"))&&o==0)
	  {pw.print("your balance is Rs 0<br>please add some money to your account");
	   RequestDispatcher rd=req.getRequestDispatcher("/main_menu.html");  
		 rd.include(req,res);
	  }
	 
	  
	  
	  int n =balance - Integer.parseInt(r.trim()) ;
	   String value= Integer.toString(n);
	  int m=balance1 + Integer.parseInt(r.trim()) ;
	  String value1= Integer.toString(m);
	  if(status.equals("O") && status1.equals("N"))
	  {
		  ps.setString(1,value);
	  
	   ps.setString(2, status);
	   ps.setString(3, acc);
	  ps.executeUpdate();
	  ps1.setString(1,value1);
	  
	   ps1.setString(2, "O");
	   ps1.setString(3, q);
	  ps1.executeUpdate();
	  ps2.setString(1,r);
	  ps2.setString(2,"Transfered to acccount no."+q );
	  ps2.setString(3,time );
	  ps2.setString(4, acc);
	  ps2.executeUpdate();
	  ps2.setString(1,r);
	  ps2.setString(2,"transfered by acccount no."+acc );
	  ps2.setString(3,time );
	  ps2.setString(4, q);
	  ps2.executeUpdate();
	  pw.print("Amount Transfered successfully!!");
	   RequestDispatcher rd=req.getRequestDispatcher("/main_menu.html");  
		
	  rd.include(req,res);
	  }
	  if(status.equals("C"))
	  {pw.print("The source Account is Ciezed<br>Cant Transfer any amount");
	  RequestDispatcher rd=req.getRequestDispatcher("/main_menu.html");  
		
	  rd.include(req,res);  
	  }
			 if( status1.equals("C"))
			 {
				 pw.print("The Destination Account is Ciezed<br>Cant Transfer any amount");
				  RequestDispatcher rd=req.getRequestDispatcher("/main_menu.html");  
					
				  rd.include(req,res);
			 }
	  if(status.equals("O") && status1.equals("O"))
	  {
		  ps.setString(1,value);
	  
	   ps.setString(2, status);
	   ps.setString(3, acc);
	  ps.executeUpdate();
	  ps1.setString(1,value1);
	  
	   ps1.setString(2, "O");
	   ps1.setString(3, q);
	  ps1.executeUpdate();
	  ps2.setString(1,r);
	  ps2.setString(2,"Transfered to acccount no. "+q );
	  ps2.setString(3,time );
	  ps2.setString(4, acc);
	  ps2.executeUpdate();
	  ps2.setString(1,r);
	  ps2.setString(2,"transfered by acccount no. "+acc );
	  ps2.setString(3,time );
	  ps2.setString(4, q);
	  ps2.executeUpdate();
	  pw.print("Amount Transfered successfully!!");
	   RequestDispatcher rd=req.getRequestDispatcher("/main_menu.html");  
		
	  rd.include(req,res);
	  }
}
catch(Exception ex)
 {	
  ex.printStackTrace();
 }
}



}
