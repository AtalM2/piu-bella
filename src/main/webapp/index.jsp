<%@page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@page import="com.google.appengine.api.users.UserService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Tr4sh N@0</title>
        <link rel="stylesheet" type="text/css" media="all" href="css/bootstrap.css">
        <link rel="stylesheet" type="text/css" media="all" href="css/bootstrap-responsive.css">
        <link rel="stylesheet" type="text/css" media="all" href="css/all.css">
    </head>
    <body>
        <h1>Hello World!</h1>
        <h2>OAuth 2 test</h2>
        
        <section id="log_in">
            <command type="command" id="authorize" value="Log in with Google" />
        </section>
        
        <section id="logged_in">
            You are logged in as <span id="email">email</span>.
        </section>
        
        <section>
        <a href="https://trashnao.appspot.com/app/">Go to the app</a>
        <br/>
        <a href="https://trashnao.appspot.com/fetch">Run the dataset update</a>
        </section>
        
        <script type="text/javascript" src="js/jquery-1.9.1.js"></script>

        <script type="text/javascript" src="js/bootstrap.js"></script>

        <script type="text/javascript" src="js/underscore.js"></script>

        <script type="text/javascript" src="js/oauth.js"></script>

        <script type="text/javascript" src="https://apis.google.com/js/client.js?onload=load"></script>
    </body>
</html>
