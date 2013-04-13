Più Bella
=========

M1 Webdev project to use Nantes Open Data on trash disposal.
Goal is to have a running app by 04/12/2013.

App is live at https://piu-bella.appspot.com/

Doc is live at https://atalm1.github.io/piu-bella/


Maven 3 is required and should be usable directly:

    mvn verify
to check that everything is fine after a `git clone` or `git pull`.

    mvn appengine:devserver
to make the app accessible on `http://localhost:8080/`

    mvn appengine:update
to upload the app to https://piu-bella.appspot/com/

Any IDE should be fine to open this project and contribute: Netbeans, Eclipse and IntelliJ all have maven support at this point.
