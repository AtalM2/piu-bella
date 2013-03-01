<%@page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@page import="com.google.appengine.api.users.UserService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tr4sh N@0</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
            UserService userService = UserServiceFactory.getUserService();

            String thisURL = request.getRequestURI();

            response.setContentType("text/html");
            if (request.getUserPrincipal() != null) {
                out.println("<p>Hello, "
                        + request.getUserPrincipal().getName()
                        + "!  You can <a href=\""
                        + userService.createLogoutURL(thisURL)
                        + "\">sign out</a>.</p>");
            } else {
                out.println("<p>Please <a href=\""
                        + userService.createLoginURL(thisURL)
                        + "\">sign in</a>.</p>");
            }
        %>
        <a href="https://trashnao.appspot.com/app/">Go to the app</a>
        <br/>
        <a href="https://trashnao.appspot.com/fetch">Run the dataset update</a>
    </body>
</html>
