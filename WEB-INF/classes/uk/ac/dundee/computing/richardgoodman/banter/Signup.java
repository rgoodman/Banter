package uk.ac.dundee.computing.richardgoodman.banter;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.*;
import java.sql.*;

public class Signup extends HttpServlet
{ 
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException
	{
		HttpSession currentSession = req.getSession(true);
		ErrorMessageBean errorBean = (ErrorMessageBean)currentSession.getAttribute("errorBean");

		String Username = req.getParameter("userName");
		String Password = req.getParameter("password");
		String ConfirmPassword = req.getParameter("confirm");
		String Email = req.getParameter("email");
		
		if(Username.length() <= 0 || Password.length() <= 0 || ConfirmPassword.length() <= 0 || Email.length() <= 0)
		{
			errorBean.setErrorMessage("A field has been left blank! Please try again!");
			req.getSession().setAttribute("errorBean", errorBean);
			RequestDispatcher rd = req.getRequestDispatcher("CreateAccount.jsp");
			rd.forward(req,res);
		}
		
		if(Username.length() > 20 || Password.length() > 20 || ConfirmPassword.length() > 20 || Email.length() > 20)
		{
			errorBean.setErrorMessage("A field is too long! Please try again!");
			req.getSession().setAttribute("errorBean", errorBean);
			RequestDispatcher rd = req.getRequestDispatcher("CreateAccount.jsp");
			rd.forward(req,res);
		}
		
		if(!(ConfirmPassword.equals(Password)))
		{
			errorBean.setErrorMessage("Passwords do not match! Please try again!");
			req.getSession().setAttribute("errorBean", errorBean);
			RequestDispatcher rd = req.getRequestDispatcher("CreateAccount.jsp");
			rd.forward(req,res);
		}
		
		else
		{
			try 
			{
				Class.forName("org.gjt.mm.mysql.Driver");
				Connection MyConnection = DriverManager.getConnection("jdbc:mysql://arlia.computing.dundee.ac.uk/richardgoodman","richardgoodman","ac31004");
				
				//static Connection conn = null;
				//conn = ConnectionBean.makeConnection();
				
				String searchQuery = "SELECT * FROM account WHERE Username = ? ";
				PreparedStatement pstmt = MyConnection.prepareStatement( searchQuery );
				pstmt.setString( 1, Username);
				ResultSet rs = pstmt.executeQuery();
			
				if (rs.next())
				{
					errorBean.setErrorMessage("Username is already taken! Please try again!");
					req.getSession().setAttribute("errorBean", errorBean);
					rs.close();
					MyConnection.close();
					RequestDispatcher rd = req.getRequestDispatcher("CreateAccount.jsp");
					rd.forward(req,res);
				}
			
				rs.close();
			
				searchQuery = "INSERT INTO account (Username, Password, EmailAddress) VALUES ( ? , ? , ? ) ";
				pstmt = MyConnection.prepareStatement( searchQuery );
				pstmt.setString( 1, Username);
				pstmt.setString( 2, Password);
				pstmt.setString( 3, Email);
				pstmt.executeUpdate();
			
				MyConnection.close();
				
				errorBean.setErrorMessage("You have successfully signed up! Please login to continue...");
				req.getSession().setAttribute("errorBean", errorBean);
				
				RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
				rd.forward(req,res);
			}
			catch(Exception E)
			{
				System.out.print(E);	
			}
		}
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws java.io.IOException, ServletException
	{
		RequestDispatcher rd = req.getRequestDispatcher("CreateAccount.jsp");
		rd.forward(req,res);
	}
}