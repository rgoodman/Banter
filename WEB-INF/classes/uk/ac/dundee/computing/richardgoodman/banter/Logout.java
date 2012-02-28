package uk.ac.dundee.computing.richardgoodman.banter;

import java.io.*;
import javax.servlet.*;
import javax.servlet.http.*;
import javax.sql.*;
import java.sql.*;

public class Logout extends HttpServlet
{ 
	public void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException,IOException
	{
		HttpSession currentSession = req.getSession(true);
		currentSession.invalidate();
		RequestDispatcher rd = req.getRequestDispatcher("index.jsp");
		rd.forward(req, res);
	}
}