Più Bella
=========

M1 Webdev project to use Nantes Open Data on trash disposal.
Goal is to have a running app by 04/12/2013.

App is live at https://trashnao.appspot.com/

The address may change later on.

Maven 3 is required and should be usable directly:

    mvn verify
to check that everything is fine after a `git clone` or `git pull`.

    mvn appengine:devserver
to make the app accessible on `http://localhost:8080/`

    mvn appengine:update
to upload the app to https://trashnao.appspot/com/

Atm, Java 6 is used because the 1.7.5 maven plugin doesn't seem quite ready yet to handle Java 7. Maybe we'll upgrade before the end of the project.

Any IDE should be fine to open this project and contribute: Netbeans, Eclipse and IntelliJ all have maven support at this point.