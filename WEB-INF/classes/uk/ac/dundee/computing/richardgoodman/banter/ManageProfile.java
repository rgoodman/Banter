package uk.ac.dundee.computing.richardgoodman.banter;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.*;
import java.sql.*;
import java.util.Date;

public class ManageProfile extends HttpServlet
{ 
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
			Class.forName("org.gjt.mm.mysql.Driver");
			Connection MyConnection = DriverManager.getConnection("jdbc:mysql://arlia.computing.dundee.ac.uk/richardgoodman","richardgoodman","ac31004");
			Statement st = MyConnection.createStatement();
			//ResultSet rs;
			
			String searchQuery = "UPDATE account SET FirstName = '" 
									+ FirstName + 
									"', Surname = '" 
									+ Surname + 
									"', EmailAddress = '" 
									+ EmailAddress + 
									"', City = '" 
									+ City + 
									"', Country = '" 
									+ Country + 
									"', Image = '" 
									+ Image + 
									"', Homepage = '" 
									+ Homepage + 
									"', Biography = '" 
									+ Biography + 
									"' WHERE AccountID = '" + accountID + "'";
									
			st.executeUpdate(searchQuery);
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