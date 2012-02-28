package uk.ac.dundee.computing.richardgoodman.banter;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.*;
import java.sql.*;

public class Login extends HttpServlet
{ 
	static Connection MyConnection = null;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException
	{		
		HttpSession currentSession = req.getSession(true);
		ErrorMessageBean errorBean = (ErrorMessageBean)currentSession.getAttribute("errorBean");
	
		String Username = req.getParameter("userName");
		String Password = req.getParameter("password");
		
		if (Username.length() > 20 || Password.length() > 20)
		{
			errorBean.setErrorMessage("Username/Password too long!");
			RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
			rd.forward(req,res);
		}
		
		try 
		{
			MyConnection = DatabaseConnectionBean.makeConnection();
			String searchQuery = "SELECT * FROM account WHERE Username = ? AND Password = ? ";
			PreparedStatement pstmt = MyConnection.prepareStatement( searchQuery );
			pstmt.setString( 1, Username);
			pstmt.setString( 2, Password);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{		
				int accountID = rs.getInt("AccountID");
				String FirstName = rs.getString("FirstName");
				String Surname = rs.getString("Surname");
				String Email = rs.getString("EmailAddress");
				String City = rs.getString("City");
				String Country = rs.getString("Country");
				String Image = rs.getString("Image");
				String Homepage = rs.getString("Homepage");
				String Biography = rs.getString("Biography");
				
				rs.close();
				MyConnection.close();
			
				LoginBean userBean;
				if (req.getSession().getAttribute("userBean") == null)
				{
					userBean = new LoginBean();
				}
				else
				{
					userBean = (LoginBean)req.getSession().getAttribute("userBean");
				}
				
				userBean.setAccountID(accountID);
				userBean.setUsername(Username);
				userBean.setPassword(Password);
				userBean.setFirstName(FirstName);
				userBean.setSurname(Surname);
				userBean.setEmail(Email);
				userBean.setCity(City);
				userBean.setCountry(Country);
				userBean.setImage(Image);
				userBean.setHomepage(Homepage);
				userBean.setBiography(Biography);
				
				req.getSession().setAttribute("userBean", userBean);
				res.sendRedirect("Newsfeed");
			}
			else
			{
				errorBean.setErrorMessage("Incorrect Username/Password!");
				rs.close();
				MyConnection.close();
				RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
				rd.forward(req,res);
			}
		}
		catch(Exception E)
		{	
			System.out.println(E);
		}
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException
	{
		RequestDispatcher rd = req.getRequestDispatcher("login.jsp");
		rd.forward(req,res);
	}
}