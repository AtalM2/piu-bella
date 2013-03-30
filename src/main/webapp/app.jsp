<%-- 
    Document   : app
    Created on : Mar 1, 2013, 1:35:43 AM
    Author     : mog
--%>

<%@page import="com.google.appengine.api.users.UserServiceFactory"%>
<%@page import="com.google.appengine.api.users.UserService"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<!-- saved from url=(0066)http://twitter.github.com/bootstrap/examples/marketing-narrow.html -->
<html lang="en"><head><meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    <meta charset="utf-8">
    <title>Tr@sh Nao</title>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <meta name="description" content="">
    <meta name="author" content="">

    <!-- Le styles -->
    <link href="/css/bootstrap.css" rel="stylesheet">
   
    <link href="/css/bootstrap-responsive.css" rel="stylesheet">

    <link href="/css/piubellapage.css" rel="stylesheet">

    <!-- HTML5 shim, for IE6-8 support of HTML5 elements -->
    <!--[if lt IE 9]>
      <script src="../assets/js/html5shiv.js"></script>
    <![endif]-->

    <!-- Fav and touch icons -->
    <link rel="apple-touch-icon-precomposed" sizes="144x144" href="http://twitter.github.com/bootstrap/assets/ico/apple-touch-icon-144-precomposed.png">
    <link rel="apple-touch-icon-precomposed" sizes="114x114" href="http://twitter.github.com/bootstrap/assets/ico/apple-touch-icon-114-precomposed.png">
      <link rel="apple-touch-icon-precomposed" sizes="72x72" href="http://twitter.github.com/bootstrap/assets/ico/apple-touch-icon-72-precomposed.png">
                    <link rel="apple-touch-icon-precomposed" href="http://twitter.github.com/bootstrap/assets/ico/apple-touch-icon-57-precomposed.png">
                                   <link rel="shortcut icon" href="http://twitter.github.com/bootstrap/assets/ico/favicon.png">
  </head>

  <body>

    <div class="container-narrow">

      <div class="masthead">
        <ul class="nav nav-pills pull-right">
          <li class="active"><a href="http://twitter.github.com/bootstrap/examples/marketing-narrow.html#">Home</a></li>
          <li><a href="http://twitter.github.com/bootstrap/examples/marketing-narrow.html#">About</a></li>
          <li><a href="http://twitter.github.com/bootstrap/examples/marketing-narrow.html#">Contact</a></li>
        </ul>
        <h3 class="muted">Project Piu Bella</h3>
      </div>

      <hr>

      <div class="row-fluid marketing">
        <div class="span6">
            <!-- <h1>Memberz app</h1>
            <a href="https://trashnao.appspot.com/">Welcome</a>
            <br/>
            <a href="https://trashnao.appspot.com/fetch">Fetch</a>
            <br /> -->
            <input placeholder="Votre rue ou quartier..." type="text" data-provide="typeahead" id="addressquery">
        </div>
      </div>


      <hr>

      <div class="row-fluid marketing">
        <div class="span6">
          <h4>Subheading</h4>
          <p>Donec id elit non mi porta gravida at eget metus. Maecenas faucibus mollis interdum.</p>

          <h4>Subheading</h4>
          <p>Morbi leo risus, porta ac consectetur ac, vestibulum at eros. Cras mattis consectetur purus sit amet fermentum.</p>

          <h4>Subheading</h4>
          <p>Maecenas sed diam eget risus varius blandit sit amet non magna.</p>
        </div>

        <div class="span6">
          <h4>Subheading</h4>
          <p>Donec id elit non mi porta gravida at eget metus. Maecenas faucibus mollis interdum.</p>

          <h4>Subheading</h4>
          <p>Morbi leo risus, porta ac consectetur ac, vestibulum at eros. Cras mattis consectetur purus sit amet fermentum.</p>

          <h4>Subheading</h4>
          <p>Maecenas sed diam eget risus varius blandit sit amet non magna.</p>
        </div>
      </div>

      <hr>

    </div> <!-- /container -->


    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->

    <script type="text/javascript" src="js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="js/bootstrap-typeahead.js"></script>
    <script type="text/javascript" src="js/bootstrap-tooltip.js"></script>
    <script type="text/javascript" src="js/bootstrap-popover.js"></script>
    <script type="text/javascript" src="js/underscore.js"></script>
    <script type="text/javascript" src="js/oauth.js"></script>
    <script type="text/javascript" src="js/addressquery.js"></script>
    <script type="text/javascript" src="https://apis.google.com/js/client.js?onload=load"></script>  

</body></html>
