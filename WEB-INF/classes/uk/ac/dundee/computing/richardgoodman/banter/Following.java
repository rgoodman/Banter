package uk.ac.dundee.computing.richardgoodman.banter;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.*;
import java.sql.*;
import java.util.*;

public class Following extends HttpServlet
{ 
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException
	{
		HttpSession currentSession = req.getSession(true);
		LoginBean userBean = (LoginBean)currentSession.getAttribute("userBean");
		FriendshipBean friendshipBean = new FriendshipBean();
		
		try 
		{
			Class.forName("org.gjt.mm.mysql.Driver");
			Connection MyConnection = DriverManager.getConnection("jdbc:mysql://arlia.computing.dundee.ac.uk/richardgoodman","richardgoodman","ac31004");
			
			String searchQuery = "SELECT * FROM friendship JOIN account ON friendship.PosterID = account.AccountID WHERE friendship.ReaderID = ? ";
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
			
			friendshipBean.setPageTitle("Who you are following");
			
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