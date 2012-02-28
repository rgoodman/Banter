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
if (userBean.getUsername() != null)
{
	response.sendRedirect("Newsfeed");
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
		<nav>
            <ul class="menu">
                <li><a class="active" title="Log in" href="login">Login</a></li>
				<li><a title="Create an Account" href="register">Signup</a></li>
            </ul>
        </nav>
        <section id="main" class="clearfix">
            <section id="content">
                <article class="initialform">
				<h2 id="nowraptitle">Please login to use the site!</h2>
                    <form name="loginform" method="post" action="Login">
					<%
						if(errorBean.getErrorMessage() != null)
						{
							if(errorBean.getErrorMessage().length() > 0)
							{
					%>
								<%= errorBean.getErrorMessage() %>
					<%
								errorBean.setErrorMessage("");
							}
						}
					%>
					<br><br>
					<span class="inputTitle">Username: </span><input id="username" type="text" name="userName" value="admin">
					<br><br>
					<span class="inputTitle">Password: </span><input id="password" type="password" name="password" value="admin">
					<br><br><input type="submit" name="Submit" value="Submit">
					</form>
                </article>
            </section>
        </section>
    </div>
</body>
</html>