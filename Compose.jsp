<jsp:useBean 
	id="userBean" 
	scope="session" 
	class="uk.ac.dundee.computing.richardgoodman.banter.LoginBean">
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
<SCRIPT LANGUAGE="JavaScript">
function textCounter(field,cntfield,maxlimit) {
if (field.value.length > maxlimit)
field.value = field.value.substring(0, maxlimit);
else
cntfield.value = maxlimit - field.value.length;
}
</script>
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
                    <h2>What's On Your Mind?</h2>
					<form action="Compose" name="newTweet" method="post"> 
						<textarea name="message" id="message" cols="40" rows="6"
						onKeyDown="textCounter(document.newTweet.message,document.newTweet.counter,140)"
						onKeyUp="textCounter(document.newTweet.message,document.newTweet.counter,140)"></textarea>
						<br><br>Character Count: <input readonly id="counter" type="text" name="counter" size="3" maxlength="3" value="140"><br><br>
						<input type="submit" id="submitTweet" name="submitTweet" value="Submit"/>
					</form>
                </article>
            </section>
        </section>
    </div>
</body>
</html>
