package uk.ac.dundee.computing.richardgoodman.banter;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.*;
import java.sql.*;
import java.util.*;

import org.apache.commons.lang.StringEscapeUtils;

public class Newsfeed extends HttpServlet
{ 
	static Connection MyConnection = null;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException
	{		
		HttpSession currentSession = req.getSession(true);
		LoginBean userBean = (LoginBean)currentSession.getAttribute("userBean");
		ErrorMessageBean errorBean = (ErrorMessageBean)currentSession.getAttribute("errorBean");
		int accountID = userBean.getAccountID();
		TweetBean tweetBean = new TweetBean();
		String tweet = null;
		
		try 
		{
			MyConnection = DatabaseConnectionBean.makeConnection();
			String searchQuery = "SELECT * FROM tweet JOIN account ON tweet.AccountID = account.AccountID WHERE tweet.AccountID IN (SELECT PosterID FROM tweet JOIN friendship ON tweet.AccountID = friendship.ReaderID WHERE friendship.ReaderID = ? ) OR tweet.AccountID = ? ORDER BY tweet.TimePosted DESC";
			PreparedStatement pstmt = MyConnection.prepareStatement( searchQuery );
			pstmt.setInt( 1, userBean.getAccountID());
			pstmt.setInt( 2, userBean.getAccountID());
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next())
			{
				tweet = StringEscapeUtils.escapeJavaScript(rs.getString("Message"));
				tweet = StringEscapeUtils.escapeHtml(tweet);
				tweetBean.setTweetIDs(rs.getInt("TweetID"));
				tweetBean.setAccountIDs(rs.getInt("AccountID"));
				tweetBean.setUsernames(rs.getString("Username"));
				tweetBean.setMessages(tweet.trim());
				tweetBean.setTimePosteds(rs.getDate("TimePosted"));
				tweetBean.setImages(rs.getString("Image"));
			}

			rs.close();
			MyConnection.close();
			
			req.getSession().setAttribute("tweetBean", tweetBean);
			RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
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