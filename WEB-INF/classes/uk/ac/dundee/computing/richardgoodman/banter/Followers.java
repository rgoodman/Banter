package uk.ac.dundee.computing.richardgoodman.banter;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.*;
import java.sql.*;
import java.util.*;

public class Followers extends HttpServlet
{ 
	static Connection MyConnection = null;

	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException
	{
		HttpSession currentSession = req.getSession(true);
		LoginBean userBean = (LoginBean)currentSession.getAttribute("userBean");
		FriendshipBean friendshipBean = new FriendshipBean();
		
		try 
		{
			MyConnection = DatabaseConnectionBean.makeConnection();
			String searchQuery = "SELECT * FROM friendship JOIN account ON friendship.ReaderID = account.AccountID WHERE friendship.PosterID = ? ";
			PreparedStatement pstmt = MyConnection.prepareStatement( searchQuery );
			pstmt.setInt( 1, userBean.getAccountID());
			ResultSet rs = pstmt.executeQuery();
			
			while (rs.next())
			{
				friendshipBean.setAccountIDs(rs.getInt("AccountID"));
				friendshipBean.setUsernames(rs.getString("Username"));
				friendshipBean.setImages(rs.getString("Image"));
				friendshipBean.setBiographies(rs.getString("Biography"));
			}
			
			rs.close();
			MyConnection.close();
			
			friendshipBean.setPageTitle("Who is following you");
			
			req.getSession().setAttribute("friendshipBean", friendshipBean);
			
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