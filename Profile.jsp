<jsp:useBean
	id="tweetBean"
	scope="session"
	class="uk.ac.dundee.computing.richardgoodman.banter.TweetBean">
</jsp:useBean>
<jsp:useBean 
	id="userBean" 
	scope="session" 
	class="uk.ac.dundee.computing.richardgoodman.banter.LoginBean">
</jsp:useBean>
<jsp:useBean 
	id="profileBean" 
	scope="session" 
	class="uk.ac.dundee.computing.richardgoodman.banter.ProfileBean">
</jsp:useBean>
<jsp:useBean
	id="errorBean"
	scope="session"
	class="uk.ac.dundee.computing.richardgoodman.banter.ErrorMessageBean">
</jsp:useBean>
<%
	if (userBean.getUsername() == null)
	{
		response.sendRedirect("login.jsp");
	}
%>
<!doctype html>
<head>
<title>Banter - A Simple Twitter Clone</title>
<meta charset="utf-8" />
<link rel="icon" href="/richardgoodman/images/B.ico" type="x-icon"/>
<link rel="stylesheet" href="/richardgoodman/css/style.css" type="text/css"/>
</head>
<body>	
	<div id="wrapper">     
        <header>
			<a href="index.jsp"><img alt="" src="/richardgoodman/images/banter logo (2).png" />
			<h1><a title="Banter - A Simple Twitter Clone">Banter - A Simple Twitter Clone</a></h1>
        </header>
        <section id="main" class="clearfix">
            <aside id="sidebar">
				<img class="userImage" src="<%= userBean.getImage() %>"/>
				<h3><%= userBean.getUsername() %>'s Profile</h3>
                <ul class="clearfix">
                    <li><a title="Home" href="/richardgoodman/Newsfeed">Home</a></li>
					<li><a title="View Profiles" href="/richardgoodman/Profile">View Profiles</a></li>
					<li><a title="Manage Your Profile" href="/richardgoodman/ManageProfile">Manage Your Profile</a></li>
                    <li><a title="Post Some Banter" href="/richardgoodman/Compose">Post Some Banter</a></li>
                    <li><a title="Who Is Following You" href="/richardgoodman/Followers">Who Is Following You</a></li>
                    <li><a title="Who You Are Following" href="/richardgoodman/Following">Who You Are Following</a></li>
					<li><a title="Logout" href="/richardgoodman/logout">Logout</a></li>
                </ul>
            </aside>
            <section id="content">
                <article>
					<form action="Profile" name="searchProfile" method="post">
						<h3>Search Username: </h3><input type="text" name="searchUsername">
					<input type="submit" id="searchProfile" name="searchProfile" value="Submit"/>
					</form>
					<br><hr /><br>
					
					<%
						if(profileBean.getUsername() == null || profileBean.getUsername() == "")
						{
					%>
							<h1>User does not exist!</h1>
					<%
						}
						else
						{
					%>
							<h1><%= profileBean.getUsername() %>'s Profile</h1>
							<img id="profileImage" src="<%= profileBean.getImage() %>"/>
							Real name: <%= profileBean.getFirstName() %> <%= profileBean.getSurname() %><br>
							Email Address: <%= profileBean.getEmail() %><br>
							Location: <%= profileBean.getCity() %>, <%= profileBean.getCountry() %><br>
							Website: <a href="<%= profileBean.getHomepage() %>">Website</a><br>
							Biography: <%= profileBean.getBiography() %><br>
							<br><a class="followLink" href="/richardgoodman/Follow/<%= profileBean.getUsername() %>">Follow/Unfollow</a>
							<br><hr /><br>
							<h1><%= profileBean.getUsername() %>'s Banterful Posts</h1>
							<%
								for(int i = 0; i < tweetBean.getMessageSize(); i++)
								{		
							%>
									<div class="post">
										<h5><%= profileBean.getUsername() %></h5>
										<img src="<%= profileBean.getImage() %>"/>
							<%
										out.print(tweetBean.getMessages(i));
							%>
										<br>Posted on: <%= tweetBean.getTimePosteds(i) %><br>
								
									<% if(userBean.getAccountID() == tweetBean.getAccountIDs(i))
										{
									%>
											<a class="deleteLink" href="/richardgoodman/Delete/<%= tweetBean.getTweetIDs(i) %>">Delete Tweet</a>
									<%
										}
									%>
									<br><br><hr /><br>
							<%
								}
							%>
										
									</div>
					<%
						}
					%>
                </article>
            </section>
        </section>
    </div>
</body>
</html>
