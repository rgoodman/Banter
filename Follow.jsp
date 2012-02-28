<jsp:useBean 
	id="userBean" 
	scope="session" 
	class="uk.ac.dundee.computing.richardgoodman.banter.LoginBean">
</jsp:useBean>
<jsp:useBean 
	id="friendshipBean" 
	scope="session" 
	class="uk.ac.dundee.computing.richardgoodman.banter.FriendshipBean">
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
<link rel="stylesheet" href="css/style.css" type="text/css"/>
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
					<h1><u><%= friendshipBean.getPageTitle() %></u></h1>
					<%
						if (friendshipBean.getSize() <= 0)
						{
						}
						else
						{
							for(int i = 0; i < friendshipBean.getSize(); i++)
							{
					%>
								<div class="post">
									<h5><a href="/richardgoodman/Profile/<%= friendshipBean.getUsernames(i) %>"><%= friendshipBean.getUsernames(i) %></a></h5>
									<img src="<%= friendshipBean.getImages(i) %>"/>
									<br>Biography: <%= friendshipBean.getBiographies(i) %>
									<br><br><hr /><br>
								</div>
					<%
							}
						}
					%>
					<br><hr /><br>
                </article>
            </section>
        </section>
    </div>
</body>
</html>
