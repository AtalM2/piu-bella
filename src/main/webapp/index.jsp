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
                    <li class="active"><a href="/">Accueil</a></li>
                    <li><a href="#">À propos</a></li>
                </ul>
                <span id="email">email</span>
                <h3 class="muted">Projet Più Bella</h3>
            </div>

            <hr>

            <div class="jumbotron">
                <h1>Restez connecté à vos poubelles!</h1>
                <p class="lead">Profitez d'alertes incroyables pour éviter que vos ordures débordent.</p>
                <a class="btn btn-large btn-success piubella-notconnected" id="authorize" href="#">Je m'inscris!</a>
            </div>

    <h2>Mes Alertes</h2>
    <div class="accordion" id="accordion2">
        
    </div>

      <hr>

            <div class="row-fluid marketing piubella-connected">

                <div class="span6">
                    <!--
                    <section>
                        <a href="https://trashnao.appspot.com/app/">Go to the app</a>
                        <br/>
                        <a href="https://trashnao.appspot.com/fetch">Run the dataset update</a>
                    </section> -->
                </div>


                <div class="row-fluid marketing piubella-connected">
                    <div class="span6" id="address-query-wrapper">
                        <!-- <h1>Memberz app</h1>
                        <a href="https://trashnao.appspot.com/">Welcome</a>
                        <br/>
                        <a href="https://trashnao.appspot.com/fetch">Fetch</a>
                        <br /> -->
                        <div class="address-query-container">
                            <input placeholder="Votre rue ou quartier..." type="text" data-provide="typeahead" id="addressquery">
                            <img src="/images/ajax-loader.gif" id="loading-indicator" style="display:none" />
                        </div>
                    </div>
                </div>
                <div class="footer">
                    <p>© M1 ATAL 2013</p>
                </div>

            </div>
        </div> <!-- /container-narrow -->

    <!-- Le javascript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script type="text/javascript" src="js/jquery-1.9.1.js"></script>
    <script type="text/javascript" src="js/bootstrap-typeahead.js"></script>
    <script type="text/javascript" src="js/bootstrap-tooltip.js"></script>
    <script type="text/javascript" src="js/bootstrap-popover.js"></script>
    <script type="text/javascript" src="js/bootstrap-collapse.js"></script>
    <script type="text/javascript" src="js/underscore.js"></script>
    <script type="text/javascript" src="js/piubella.js"></script>
    <script type="text/javascript" src="js/oauth.js"></script>
    <script type="text/javascript" src="js/addressquery.js"></script>
    <script type="text/javascript" src="https://apis.google.com/js/client.js?onload=load"></script>  
    <script type="text/javascript">
    var pb = null;

    function onConnect() {
        $('.piubella-notconnected').hide();
        $('.piubella-connected').show();

        pb = new PiuBella();
        pb.loadNotifications('#accordion2');
    }

    function onDisconnect() {
        $('.piubella-notconnected').show();
        $('.piubella-connected').hide();
        pb = null;
    }
    </script> 

    </body>
</html>
