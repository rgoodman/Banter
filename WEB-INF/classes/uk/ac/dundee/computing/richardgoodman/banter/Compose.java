package uk.ac.dundee.computing.richardgoodman.banter;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.*;
import java.sql.*;

import org.apache.commons.lang.*;

public class Compose extends HttpServlet
{ 
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException
	{
		HttpSession currentSession = req.getSession(true);
		LoginBean userBean = (LoginBean)currentSession.getAttribute("userBean");
		ErrorMessageBean errorBean = (ErrorMessageBean)currentSession.getAttribute("errorBean");
		int accountID = userBean.getAccountID();
		String banterMessage = req.getParameter("message");
		
		String tweetedMessage = StringEscapeUtils.escapeHtml(banterMessage);
		tweetedMessage = StringEscapeUtils.escapeJavaScript(tweetedMessage);
		tweetedMessage.trim();
		
		if (tweetedMessage.length() <= 0 || tweetedMessage.length() > 140)
		{
			errorBean.setErrorMessage("Message is too short! Please try again!");
			req.getSession().setAttribute("errorBean", errorBean);
			RequestDispatcher rd = req.getRequestDispatcher("CreateAccount.jsp");
			rd.forward(req,res);
		}
		
		try 
		{
			Class.forName("org.gjt.mm.mysql.Driver");
			Connection MyConnection = DriverManager.getConnection("jdbc:mysql://arlia.computing.dundee.ac.uk/richardgoodman","richardgoodman","ac31004");
			
			String searchQuery = "INSERT INTO tweet (AccountID, Message) VALUES ( ? , ? )";
			PreparedStatement pstmt = MyConnection.prepareStatement( searchQuery );
			pstmt.setInt( 1, accountID);
			pstmt.setString( 2, tweetedMessage);
			pstmt.executeUpdate();
			
			MyConnection.close();
			res.sendRedirect("Newsfeed");
		}
		catch(Exception E)
		{
			System.out.print(E);
			res.sendRedirect("Compose.jsp");
		}
	}
	
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws java.io.IOException, ServletException
	{
		RequestDispatcher rd = req.getRequestDispatcher("/Compose.jsp");
		rd.forward(req,res);
	}
}