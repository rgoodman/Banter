package uk.ac.dundee.computing.richardgoodman.banter;

import java.util.*;
import java.sql.Timestamp;
import java.util.ArrayList;

public class FriendshipBean 
{
	ArrayList<Integer> accountIDs;
	ArrayList<String> userNames;
	ArrayList<String> biographies;
	ArrayList<String> images;
	
	String PageTitle;
  
	public FriendshipBean()
	{
		accountIDs = new ArrayList<Integer>();
		biographies = new ArrayList<String>();
		userNames = new ArrayList<String>();
		images = new ArrayList<String>();
	}
  
	public int getAccountIDs(int index)
	{
		return accountIDs.get(index);
	}
  
	public void setAccountIDs(int AccountID)
	{
		accountIDs.add(AccountID);
	}
  
	public String getUsernames(int index) 
	{
		return userNames.get(index);
	}
  
	public void setUsernames(String Username) 
	{
		userNames.add(Username);
	}
  
	public String getImages(int index)
	{
		return images.get(index);
	}
  
	public void setImages(String image)
	{
		images.add(image);
	}
	
	public String getBiographies(int index)
	{
		return biographies.get(index);
	}
  
	public void setBiographies(String biography)
	{
		biographies.add(biography);
	}
	
	public int getSize()
	{
		return accountIDs.size();
	}
	
	public void setPageTitle(String pageTitle)
	{
		this.PageTitle = pageTitle;
	}
	
	public String getPageTitle()
	{
		return PageTitle;
	}
}