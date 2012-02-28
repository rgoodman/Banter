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
	id="errorBean"
	scope="session"
	class="uk.ac.dundee.computing.richardgoodman.banter.ErrorMessageBean">
</jsp:useBean>
<%
if (userBean.getUsername() == null)
{
	response.sendRedirect("login");
}
%>
<!doctype html>
<head>
<title>Banter - A Simple Twitter Clone</title>
<meta charset="utf-8" />
<link rel="icon" href="images/B.ico" type="x-icon"/>
<link rel="stylesheet" href="css/style.css" type="text/css" media="screen" />
</head>
<body>	
	<div id="wrapper">     
        <header>
			<a href="index.jsp"><img alt="" src="images/banter logo (2).png" />
			<h1><a title="Banter - A Simple Twitter Clone">Banter - A Simple Twitter Clone</a></h1>
        </header>
        <section id="main" class="clearfix">
            <aside id="sidebar">
				<img class="userImage" src="<%= userBean.getImage() %>"/>
				<h3><%= userBean.getUsername() %>'s Profile</h3>
                <ul class="clearfix">
                    <li><a title="Home" href="Newsfeed">Home</a></li>
					<li><a title="View Profiles" href="Profile">View Profiles</a></li>
					<li><a title="Manage Your Profile" href="ManageProfile">Manage Your Profile</a></li>
                    <li><a title="Post Some Banter" href="Compose">Post Some Banter</a></li>
                    <li><a title="Who Is Following You" href="Followers">Who Is Following You</a></li>
                    <li><a title="Who You Are Following" href="Following">Who You Are Following</a></li>
					<li><a title="Logout" href="logout">Logout</a></li>
                </ul>
            </aside>
            <section id="content">
                <article>
					<h1><u>Newsfeed</u></h1>
					<%
						for(int i = 0; i < tweetBean.getMessageSize(); i++)
						{
					%>
							<div class="post">
								<h5><a href="/richardgoodman/Profile/<%= tweetBean.getUsernames(i) %>"><%= tweetBean.getUsernames(i) %></a></h5>
								<img src="<%= tweetBean.getImages(i) %>"/>
								<%= tweetBean.getMessages(i) %>
								<br>Posted on: <%= tweetBean.getTimePosteds(i) %><br>
								<% if(userBean.getAccountID() == tweetBean.getAccountIDs(i))
									{
								%>
											<a class="deleteLink" href="/richardgoodman/Delete/<%= tweetBean.getTweetIDs(i) %>">Delete Tweet</a>
								<%
									}
								%>
								<br><br><hr /><br>
							</div>
					<%
						}
					%>
					<br><hr /><br>
                </article>
            </section>
        </section>
    </div>
</body>
</html>