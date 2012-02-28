package uk.ac.dundee.computing.richardgoodman.banter;

public class ErrorMessageBean 
{  
	String ErrorMessage;
	String Pathname = "";

	public String getErrorMessage()
	{
		return ErrorMessage;
	}
	
	public void setErrorMessage(String errorMessage)
	{
		this.ErrorMessage = errorMessage;
	}
	
	public String getPathname()
	{
		return Pathname;
	}
  
	public void setPathname(String pathname)
	{
		this.Pathname = pathname;
	}
}