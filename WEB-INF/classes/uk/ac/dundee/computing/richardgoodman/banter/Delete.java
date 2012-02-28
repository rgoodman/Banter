package uk.ac.dundee.computing.richardgoodman.banter;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.*;
import java.sql.*;
import java.util.*;

public class Delete extends HttpServlet
{ 
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException
	{		
		HttpSession currentSession = req.getSession(true);
		LoginBean userBean = (LoginBean)currentSession.getAttribute("userBean");
		ErrorMessageBean errorBean = (ErrorMessageBean)currentSession.getAttribute("errorBean");
		int accountID = userBean.getAccountID();
		int TweetID = -1;
		
		String tweetURL = req.getRequestURI();
		System.out.println(tweetURL);
		
		if (tweetURL.length() > 23)
		{
			TweetID = Integer.parseInt(tweetURL.substring(23));
		}
		else
		{
			errorBean.setErrorMessage("Tweet does not exist!");
			req.getSession().setAttribute("errorBean", errorBean);
			res.sendRedirect("/richardgoodman/Newsfeed");
		}
		
		try 
		{
			Class.forName("org.gjt.mm.mysql.Driver");
			Connection MyConnection = DriverManager.getConnection("jdbc:mysql://arlia.computing.dundee.ac.uk/richardgoodman","richardgoodman","ac31004");
			
			String searchQuery = "SELECT * FROM tweet WHERE AccountID = ? AND TweetID = ? ";
			PreparedStatement pstmt = MyConnection.prepareStatement( searchQuery );
			pstmt.setInt( 1, userBean.getAccountID());
			pstmt.setInt( 2, TweetID);
			ResultSet rs = pstmt.executeQuery();
			
			if (rs.next())
			{
				String deleteQuery = "DELETE FROM tweet WHERE AccountID = ? AND TweetID = ? ";
				pstmt = MyConnection.prepareStatement( deleteQuery );
				pstmt.setInt( 1, userBean.getAccountID());
				pstmt.setInt( 2, TweetID);
				pstmt.executeUpdate();
			}
			else
			{
				errorBean.setErrorMessage("Only the poster can delete the tweet!");
				req.getSession().setAttribute("errorBean", errorBean);
			}

			rs.close();
			MyConnection.close();
			
			res.sendRedirect("/richardgoodman/Newsfeed");
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