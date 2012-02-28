package uk.ac.dundee.computing.richardgoodman.banter;

import java.util.*;
import java.sql.Timestamp;
import java.util.ArrayList;

public class TweetBean 
{
	ArrayList<Integer> accountIDs;
	ArrayList<Integer> tweetIDs;
	ArrayList<String> userNames;
	ArrayList<Date> timePosteds;
	ArrayList<String> messages;
	ArrayList<String> images;
  
	public TweetBean()
	{
		accountIDs = new ArrayList<Integer>();
		tweetIDs = new ArrayList<Integer>();
		userNames = new ArrayList<String>();
		timePosteds = new ArrayList<Date>();
		messages = new ArrayList<String>();
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
  
	public int getTweetIDs(int index)
	{
		return tweetIDs.get(index);
	}
  
	public void setTweetIDs(int TweetID)
	{
		tweetIDs.add(TweetID);
	}
  
	public String getUsernames(int index) 
	{
		return userNames.get(index);
	}
  
	public void setUsernames(String Username) 
	{
		userNames.add(Username);
	}
  
	public Date getTimePosteds(int index)
	{
		//String time = "" + timePosteds.get(index).getDate() + "." + timePosteds.get(index).getMonth() + "." + timePosteds.get(index).getYear() + " at " + timePosteds.get(index).getHours() + ":" + timePosteds.get(index).getMinutes();
		//String time = (timePosteds.get(index)).toString();
		return timePosteds.get(index);
	}
  
	public void setTimePosteds(Date TimePosted)
	{
		timePosteds.add(TimePosted);
	}
  
	public String getMessages(int index)
	{
		return messages.get(index);
	}
  
	public void setMessages(String Message)
	{
		messages.add(Message);
	}
  
	public String getImages(int index)
	{
		return images.get(index);
	}
  
	public void setImages(String image)
	{
		images.add(image);
	}
  
	public int getMessageSize()
	{
		return messages.size();
	}
}