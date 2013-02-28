<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Hello World!</h1>
        <%
        StringBuilder sb = new StringBuilder();
        sb.append("https://accounts.google.com/o/oauth2/auth?")
            .append("response_type=token&")
            .append("client_id=311668897898.apps.googleusercontent.com&")
            .append("redirect_uri=https://trashnao.appspot.com/login&")
            .append("scope=https://www.googleapis.com/auth/userinfo.profile+")
            .append("https://www.googleapis.com/auth/userinfo.email");
        %>
        <a href="<%= sb.toString() %>">
            Login
        </a>
    </body>
</html>
