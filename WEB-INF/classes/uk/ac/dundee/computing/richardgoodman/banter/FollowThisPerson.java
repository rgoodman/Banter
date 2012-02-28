package uk.ac.dundee.computing.richardgoodman.banter;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.*;
import java.sql.*;
import java.util.*;

public class FollowThisPerson extends HttpServlet
{ 
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException
	{
		HttpSession currentSession = req.getSession(true);
		ErrorMessageBean errorBean = (ErrorMessageBean)currentSession.getAttribute("errorBean");
		LoginBean userBean = (LoginBean)currentSession.getAttribute("userBean");
		
		int userID = userBean.getAccountID();
		int ProfileID = -1;
		String Profile = null;
		String profileURL = req.getRequestURI();
		System.out.println(profileURL);
		
		if (profileURL.length() > 23)
		{
			Profile = profileURL.substring(23);
		}
		else
		{
			errorBean.setErrorMessage("User does not exist!");
			req.getSession().setAttribute("errorBean", errorBean);
			res.sendRedirect("/richardgoodman/Newsfeed");
		}
		
		try 
		{
			Class.forName("org.gjt.mm.mysql.Driver");
			Connection MyConnection = DriverManager.getConnection("jdbc:mysql://arlia.computing.dundee.ac.uk/richardgoodman","richardgoodman","ac31004");
			
			String findProfileQuery = "SELECT * from account WHERE Username = ? ";
			PreparedStatement pstmt = MyConnection.prepareStatement( findProfileQuery );
			pstmt.setString( 1, Profile );
			ResultSet rs = pstmt.executeQuery();
			System.out.println("1");
			if (rs.next())
			{
				ProfileID = rs.getInt("AccountID");
			}
			else
			{
				rs.close();
				MyConnection.close();
				errorBean.setErrorMessage("User could not be found in the database");
				res.sendRedirect("/richardgoodman/Newsfeed");
			}
			
			String searchQuery = "SELECT * FROM friendship WHERE PosterID = ? AND ReaderID = ? ";
			pstmt = MyConnection.prepareStatement( searchQuery );
			pstmt.setInt( 1, ProfileID);
			pstmt.setInt( 2, userID);
			rs = pstmt.executeQuery();
			System.out.println("2");
			
			if (rs.next())
			{
				String deleteQuery = "DELETE FROM friendship WHERE PosterID = ? AND ReaderID = ? ";
				pstmt = MyConnection.prepareStatement( deleteQuery );
				pstmt.setInt( 1, ProfileID);
				pstmt.setInt( 2, userID);
				pstmt.executeUpdate();System.out.println("3");
				errorBean.setErrorMessage("You have unfollowed " + Profile);
			}
			else
			{
				String addQuery = "INSERT INTO friendship (PosterID, ReaderID) VALUES ( ? , ? ) ";
				pstmt = MyConnection.prepareStatement( addQuery );
				pstmt.setInt( 1, ProfileID);
				pstmt.setInt( 2, userID);
				pstmt.executeUpdate();
				System.out.println("3");
				errorBean.setErrorMessage("You are now following " + Profile);
			}
			
			rs.close();
			MyConnection.close();

			req.getSession().setAttribute("errorBean", errorBean);
			
			RequestDispatcher rd = req.getRequestDispatcher("Follow.jsp");
			rd.forward(req,res);
		}
		catch(Exception E)
		{	
			System.out.println(E);
		}	
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws java.io.IOException, ServletException
	{
	}
}