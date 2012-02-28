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
	response.sendRedirect("login.jsp");
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
					<li><a title="View Profiles" href="/richardgoodman/Profile">View Profiles</a></li>
					<li><a title="Manage Your Profile" href="ManageProfile">Manage Your Profile</a></li>
                    <li><a title="Post Some Banter" href="Compose">Post Some Banter</a></li>
                    <li><a title="Who Is Following You" href="Followers">Who Is Following You</a></li>
                    <li><a title="Who You Are Following" href="Following">Who You Are Following</a></li>
					<li><a title="Logout" href="logout">Logout</a></li>
                </ul>
            </aside>
            <section id="content">
                <article>
				<h2>Manage Your Profile</h2>
                    <form name="manageprofileform" method="post" action="ManageProfile">
					<br><br>
					<span class="inputTitle">First Name: </span><input class="manageProfileBox" type="text" name="firstName" value="<%= userBean.getFirstName() %>">
					<br><br>
					<span class="inputTitle">Surname: </span><input class="manageProfileBox" type="text" name="surname" value="<%= userBean.getSurname() %>">
					<br><br>
					<span class="inputTitle">Email Address: </span><input class="manageProfileBox" type="text" name="email" value="<%= userBean.getEmail() %>">
					<br><br>
					<span class="inputTitle">City: </span><input class="manageProfileBox" type="text" name="city" value="<%= userBean.getCity() %>">
					<br><br>
					<span class="inputTitle">Country: </span><input class="manageProfileBox" type="text" name="country" value="<%= userBean.getCountry() %>">
					<br><br>
					<span class="inputTitle">Website: </span><input class="manageProfileBox" type="text" name="websiteURL" value="<%= userBean.getHomepage() %>">
					<br><br>
					<span class="inputTitle">Avatar Location: </span><input class="manageProfileBox" type="text" name="image" value="<%= userBean.getImage() %>">
					<br><br>
					<span class="inputTitle">Biography: </span><textarea id="biography" rows="6" cols="40" name="biography"><%= userBean.getBiography() %></textarea>
					<br><br>
					<br><input type="submit" name="Submit" value="Submit Changes">
					</form>
                </article>
            </section>
        </section>
    </div>
</body>
</html>