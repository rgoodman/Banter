package uk.ac.dundee.computing.richardgoodman.banter;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.*;
import java.sql.*;

import javax.naming.InitialContext;
import javax.naming.Context;
import javax.naming.NamingException;

public class Profile extends HttpServlet
{ 	
	private static final String Pathname = "../";

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws java.io.IOException, ServletException
	{
		HttpSession currentSession = req.getSession(true);
		LoginBean userBean = (LoginBean)currentSession.getAttribute("userBean");
		ErrorMessageBean errorBean = (ErrorMessageBean)currentSession.getAttribute("errorBean");
		
		int accountID = -1;
		String Username, FirstName, Surname, Email, City, Country, Image, Homepage, Biography;
		
		ProfileBean profileBean = new ProfileBean();
		TweetBean tweetBean = new TweetBean();
		
		String usernameURL = req.getRequestURI();
		System.out.println(usernameURL);
		
		if (usernameURL.length() > 24)
		{
			errorBean.setPathname(Pathname);
			Username = usernameURL.substring(24);
		}
		else
		{
			Username = userBean.getUsername();
		}
		
		try 
		{
			Class.forName("org.gjt.mm.mysql.Driver");
			Connection MyConnection = DriverManager.getConnection("jdbc:mysql://arlia.computing.dundee.ac.uk/richardgoodman","richardgoodman","ac31004");
			
			String searchQuery = "SELECT * FROM account WHERE Username = ? ";
			PreparedStatement pstmt = MyConnection.prepareStatement( searchQuery );
			pstmt.setString( 1, Username);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next())
			{
				profileBean.setAccountID(rs.getInt("AccountID"));				
				profileBean.setUsername(rs.getString("Username"));
				profileBean.setFirstName(rs.getString("FirstName"));
				profileBean.setSurname(rs.getString("Surname"));
				profileBean.setEmail(rs.getString("EmailAddress"));
				profileBean.setCity(rs.getString("City"));
				profileBean.setCountry(rs.getString("Country"));
				profileBean.setImage(rs.getString("Image"));
				profileBean.setHomepage(rs.getString("Homepage"));
				profileBean.setBiography(rs.getString("Biography"));
			}
			
			searchQuery = "SELECT * FROM tweet WHERE AccountID = ? ORDER BY tweet.TimePosted DESC";
			pstmt = MyConnection.prepareStatement( searchQuery );
			pstmt.setInt( 1, profileBean.getAccountID());
			rs = pstmt.executeQuery();
			
			while (rs.next())
			{
				tweetBean.setTweetIDs(rs.getInt("TweetID"));
				tweetBean.setAccountIDs(rs.getInt("AccountID"));
				tweetBean.setMessages(rs.getString("Message"));
				tweetBean.setTimePosteds(rs.getDate("TimePosted"));
			}

			rs.close();
			MyConnection.close();
		
			req.getSession().setAttribute("profileBean", profileBean);
			req.getSession().setAttribute("tweetBean", tweetBean);
			RequestDispatcher rd = req.getRequestDispatcher("/Profile.jsp");
			rd.forward(req,res);
		}
		catch(Exception E)
		{	
			System.out.println(E);
		}
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws java.io.IOException, ServletException
	{
		HttpSession currentSession = req.getSession(true);
		LoginBean userBean = (LoginBean)currentSession.getAttribute("userBean");
		ErrorMessageBean errorBean = (ErrorMessageBean)currentSession.getAttribute("errorBean");
		String Username = req.getParameter("searchUserame");
		
		ProfileBean profileBean = new ProfileBean();
		TweetBean tweetBean = new TweetBean();
		
		String usernameURL = req.getRequestURI() + Username;
		System.out.println(usernameURL);
		
		if (usernameURL.length() > 24)
		{
			errorBean.setPathname(Pathname);
			Username = usernameURL.substring(24);
		}
		else
		{
			Username = userBean.getUsername();
		}
		
		try 
		{
			Class.forName("org.gjt.mm.mysql.Driver");
			Connection MyConnection = DriverManager.getConnection("jdbc:mysql://arlia.computing.dundee.ac.uk/richardgoodman","richardgoodman","ac31004");
				
			String searchQuery = "SELECT * FROM account WHERE Username = ? ";
			PreparedStatement pstmt = MyConnection.prepareStatement( searchQuery );
			pstmt.setString( 1, Username);
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next())
			{
				profileBean.setAccountID(rs.getInt("AccountID"));				
				profileBean.setUsername(rs.getString("Username"));
				profileBean.setFirstName(rs.getString("FirstName"));
				profileBean.setSurname(rs.getString("Surname"));
				profileBean.setEmail(rs.getString("EmailAddress"));
				profileBean.setCity(rs.getString("City"));
				profileBean.setCountry(rs.getString("Country"));
				profileBean.setImage(rs.getString("Image"));
				profileBean.setHomepage(rs.getString("Homepage"));
				profileBean.setBiography(rs.getString("Biography"));
			}
		
			searchQuery = "SELECT * FROM tweet WHERE AccountID = ? ORDER BY tweet.TimePosted DESC";
			pstmt = MyConnection.prepareStatement( searchQuery );
			pstmt.setInt( 1, profileBean.getAccountID());
			rs = pstmt.executeQuery();
			
			while (rs.next())
			{
				tweetBean.setTweetIDs(rs.getInt("TweetID"));
				tweetBean.setAccountIDs(rs.getInt("AccountID"));
				tweetBean.setMessages(rs.getString("Message"));
				tweetBean.setTimePosteds(rs.getDate("TimePosted"));
			}

			rs.close();
			MyConnection.close();
		
			req.getSession().setAttribute("errorBean", errorBean);
			req.getSession().setAttribute("profileBean", profileBean);
			req.getSession().setAttribute("tweetBean", tweetBean);
			RequestDispatcher rd = req.getRequestDispatcher("/Profile.jsp");
			rd.forward(req,res);
		}
		catch(Exception E)
		{	
			System.out.println(E);
		}
	}
}