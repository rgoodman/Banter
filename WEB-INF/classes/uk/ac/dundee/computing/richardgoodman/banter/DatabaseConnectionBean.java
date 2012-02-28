package uk.ac.dundee.computing.richardgoodman.banter;

import java.sql.*;
import java.util.*;

public class DatabaseConnectionBean
{
    static Connection MyConnection;
   
    public static Connection makeConnection()
    {
        try
        {
            Class.forName("org.gjt.mm.mysql.Driver");
           
            try
            {
                MyConnection = DriverManager.getConnection("jdbc:mysql://arlia.computing.dundee.ac.uk/richardgoodman", "richardgoodman", "ac31004");
            }
            catch(SQLException e)
            {
                System.out.println("Can't connect to database");
            }
        }
        catch(ClassNotFoundException e)
        {
            System.out.println("Can't Find Driver");
        }
        return MyConnection;
    }
}