package uk.ac.dundee.computing.richardgoodman.banter;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.*;
import java.sql.*;
import java.util.Date;

public class ManageProfile extends HttpServlet
{ 
	static Connection MyConnection = null;

	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException
	{		
		HttpSession currentSession = req.getSession(true);
		LoginBean userBean = (LoginBean)currentSession.getAttribute("userBean");
		int accountID = userBean.getAccountID();
		
		String FirstName = req.getParameter("firstName");
		String Surname = req.getParameter("surname");
		String EmailAddress = req.getParameter("email");
		String City = req.getParameter("city");
		String Country = req.getParameter("country");
		String Image = req.getParameter("image");
		String Homepage = req.getParameter("homepage");
		String Biography = req.getParameter("biography");

		try 
		{
			MyConnection = DatabaseConnectionBean.makeConnection();			
			String searchQuery = "UPDATE account SET FirstName = ? , Surname = ? , EmailAddress = ? , City = ? , Country = ? , Image = ? , Homepage = ? , Biography = ? WHERE AccountID = ? ";
			PreparedStatement pstmt = MyConnection.prepareStatement( searchQuery );
			pstmt.setString( 1, FirstName );
			pstmt.setString( 2, Surname );
			pstmt.setString( 3, EmailAddress );
			pstmt.setString( 4, City );
			pstmt.setString( 5, Country );
			pstmt.setString( 6, Image );
			pstmt.setString( 7, Homepage );
			pstmt.setString( 8, Biography );
			pstmt.setInt( 9, accountID );
			pstmt.executeUpdate();
									
			MyConnection.close();
			
			userBean.setFirstName(FirstName);
			userBean.setSurname(Surname);
			userBean.setEmail(EmailAddress);
			userBean.setCity(City);
			userBean.setCountry(Country);
			userBean.setImage(Image);
			userBean.setHomepage(Homepage);
			userBean.setAccountID(accountID);
			userBean.setBiography(Biography);
			
			req.getSession().setAttribute("userBean", userBean);
			res.sendRedirect("Newsfeed");
		}
		catch(Exception E)
		{	
			System.out.println(E);
		}
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws java.io.IOException, ServletException
	{
		RequestDispatcher rd = req.getRequestDispatcher("/ManageProfile.jsp");
		rd.forward(req,res);
	}
}