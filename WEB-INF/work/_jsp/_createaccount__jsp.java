/*
 * JSP generated by Resin-3.0.21 (built Thu, 10 Aug 2006 12:03:19 PDT)
 */

package _jsp;
import javax.servlet.*;
import javax.servlet.jsp.*;
import javax.servlet.http.*;

public class _createaccount__jsp extends com.caucho.jsp.JavaPage{
  private boolean _caucho_isDead;
  
  public void
  _jspService(javax.servlet.http.HttpServletRequest request,
              javax.servlet.http.HttpServletResponse response)
    throws java.io.IOException, javax.servlet.ServletException
  {
    javax.servlet.http.HttpSession session = request.getSession(true);
    com.caucho.server.webapp.Application _jsp_application = _caucho_getApplication();
    javax.servlet.ServletContext application = _jsp_application;
    com.caucho.jsp.PageContextImpl pageContext = com.caucho.jsp.QJspFactory.allocatePageContext(this, _jsp_application, request, response, null, session, 8192, true, false);
    javax.servlet.jsp.JspWriter out = pageContext.getOut();
    javax.servlet.ServletConfig config = getServletConfig();
    javax.servlet.Servlet page = this;
    response.setContentType("text/html");
    try {
      uk.ac.dundee.computing.richardgoodman.banter.LoginBean userBean;
      synchronized (session) {
        userBean = (uk.ac.dundee.computing.richardgoodman.banter.LoginBean) session.getAttribute("userBean");
        if (userBean == null) {
          userBean = new uk.ac.dundee.computing.richardgoodman.banter.LoginBean();
          session.setAttribute("userBean", userBean);
          out.write(_jsp_string0, 0, _jsp_string0.length);
        }
      }
      out.write(_jsp_string0, 0, _jsp_string0.length);
      uk.ac.dundee.computing.richardgoodman.banter.ErrorMessageBean errorBean;
      synchronized (session) {
        errorBean = (uk.ac.dundee.computing.richardgoodman.banter.ErrorMessageBean) session.getAttribute("errorBean");
        if (errorBean == null) {
          errorBean = new uk.ac.dundee.computing.richardgoodman.banter.ErrorMessageBean();
          session.setAttribute("errorBean", errorBean);
          out.write(_jsp_string0, 0, _jsp_string0.length);
        }
      }
      out.write(_jsp_string0, 0, _jsp_string0.length);
      
if (userBean.getUsername() != null)
{
	response.sendRedirect("Newsfeed");
}

      out.write(_jsp_string1, 0, _jsp_string1.length);
      
						if(errorBean.getErrorMessage() != null)
						{
							if(errorBean.getErrorMessage().length() > 0)
							{
					
      out.write(_jsp_string2, 0, _jsp_string2.length);
      out.print(( errorBean.getErrorMessage() ));
      out.write(_jsp_string3, 0, _jsp_string3.length);
      
								errorBean.setErrorMessage("");
							}
						}
					
      out.write(_jsp_string4, 0, _jsp_string4.length);
    } catch (java.lang.Throwable _jsp_e) {
      pageContext.handlePageException(_jsp_e);
    } finally {
      com.caucho.jsp.QJspFactory.freePageContext(pageContext);
    }
  }

  private java.util.ArrayList _caucho_depends = new java.util.ArrayList();

  public java.util.ArrayList _caucho_getDependList()
  {
    return _caucho_depends;
  }

  public void _caucho_addDepend(com.caucho.make.PersistentDependency depend)
  {
    super._caucho_addDepend(depend);
    com.caucho.jsp.JavaPage.addDepend(_caucho_depends, depend);
  }

  public boolean _caucho_isModified()
  {
    if (_caucho_isDead)
      return true;
    if (com.caucho.util.CauchoSystem.getVersionId() != -6418134904411496499L)
      return true;
    for (int i = _caucho_depends.size() - 1; i >= 0; i--) {
      com.caucho.make.Dependency depend;
      depend = (com.caucho.make.Dependency) _caucho_depends.get(i);
      if (depend.isModified())
        return true;
    }
    return false;
  }

  public long _caucho_lastModified()
  {
    return 0;
  }

  public void destroy()
  {
      _caucho_isDead = true;
      super.destroy();
  }

  public void init(com.caucho.vfs.Path appDir)
    throws javax.servlet.ServletException
  {
    com.caucho.vfs.Path resinHome = com.caucho.util.CauchoSystem.getResinHome();
    com.caucho.vfs.MergePath mergePath = new com.caucho.vfs.MergePath();
    mergePath.addMergePath(appDir);
    mergePath.addMergePath(resinHome);
    com.caucho.loader.DynamicClassLoader loader;
    loader = (com.caucho.loader.DynamicClassLoader) getClass().getClassLoader();
    String resourcePath = loader.getResourcePathSpecificFirst();
    mergePath.addClassPath(resourcePath);
    com.caucho.vfs.Depend depend;
    depend = new com.caucho.vfs.Depend(appDir.lookup("CreateAccount.jsp"), 7604674182928866170L, false);
    com.caucho.jsp.JavaPage.addDepend(_caucho_depends, depend);
  }

  private final static char []_jsp_string4;
  private final static char []_jsp_string1;
  private final static char []_jsp_string0;
  private final static char []_jsp_string2;
  private final static char []_jsp_string3;
  static {
    _jsp_string4 = "\r\n					<br><br>\r\n					<span class=\"inputTitle\">Desired Username: </span><input type=\"text\" name=\"userName\">\r\n					<br><br>\r\n					<span class=\"inputTitle\">Password: </span><input type=\"password\" name=\"password\">\r\n					<br><br>\r\n					<span class=\"inputTitle\">Confirm Password: </span><input type=\"password\" name=\"confirm\">\r\n					<br><br>\r\n					<span class=\"inputTitle\">Email Address: </span><input type=\"text\" name=\"email\">\r\n					<br><br>\r\n					<br><input type=\"submit\" name=\"Submit\" value=\"Submit\">\r\n					</form>\r\n                </article>\r\n            </section>\r\n        </section>\r\n    </div>\r\n</body>\r\n</html>".toCharArray();
    _jsp_string1 = "\r\n<!doctype html>\r\n<head>\r\n<title>Banter - A Simple Twitter Clone</title>\r\n<meta charset=\"utf-8\" />\r\n<link rel=\"icon\" href=\"images/B.ico\" type=\"x-icon\"/>\r\n<link rel=\"stylesheet\" href=\"css/style.css\" type=\"text/css\"/>\r\n</head>\r\n<body>	\r\n	<div id=\"wrapper\">\r\n        <header>\r\n			<a href=\"index.jsp\"><img alt=\"\" src=\"images/banter logo (2).png\" />\r\n			<h1><a title=\"Banter - A Simple Twitter Clone\">Banter - A Simple Twitter Clone</a></h1>\r\n        </header>\r\n		<nav>\r\n            <ul class=\"menu\">\r\n                <li><a title=\"Log in\" href=\"login\">Login</a></li>\r\n				<li><a class=\"active\" title=\"Create an Account\" href=\"register\">Signup</a></li>\r\n            </ul>\r\n        </nav>\r\n        <section id=\"main\" class=\"clearfix\">\r\n            <section id=\"content\">\r\n                <article class=\"initialform\">\r\n				<h2>Create an Account</h2>\r\n                    <form name=\"signupform\" method=\"post\" action=\"Register\">\r\n					".toCharArray();
    _jsp_string0 = "\r\n".toCharArray();
    _jsp_string2 = "\r\n								".toCharArray();
    _jsp_string3 = "\r\n					".toCharArray();
  }
}
