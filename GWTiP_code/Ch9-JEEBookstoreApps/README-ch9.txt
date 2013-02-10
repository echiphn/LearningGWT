Manning GWT In Practice Ch9 Notes
-------------------------------------------------------------

"Bookstore"

This project uses Maven.

The GWT-Maven plugin is required (http://code.google.com/p/gwt-maven/)
and explained in the text (Chapter 7). Once the plugin is present 
(running the project will download it for you), and configured (see ../README.txt) 
you can use Maven as shown below (works with Maven 1 and Maven 2).

Includes some advanced options necessary for running Spring and Toplink JPA within the GWT shell. 

Also, this project contains two GWT examples (two separate modules), 
the "Bookstore" admin interface, and the "Shopping" interface. 
You can run either with the correct runtarget.
M2 will compile BOTH projects at once, M1 will only compile one or the other at a time. 
(For M1 see project.properties, where you can change the compileTarget.)

To change which example is run when you launch the shell, you can adjust the runTarget 
in the POM (M1 project.xml, M2 pom.xml) - or you can just type to adjust the path in
the browser location bar.  Shopping is the default), but you can also run Bookstore
(change from .../Shopping/Shopping.html to .../Bookstore/Bookstore.html and vice-versa).


-- PREREQUISITES --
-------------------

1. Must have a local MySQL instance with a "bookstore" schema. 

2. Must have a MySQL user named "bookstore" with password "books" that has full rights to the bookstore schema.

3. Requires a local installation of Xerces 2.9.0 for the shell to correctly be able to 
operate Toplink Essentials. Download Xerces 2.9.0 (http://xerces.apache.org/xerces2-j/download.cgi)
and unzip it to a known location - XERCES_HOME. 


-- SETUP --
-----------

1. Xerces must be overriden with the Java Endorsed Standards Override Mechanism
(http://java.sun.com/j2se/1.5.0/docs/guide/standards/). This is required because 
Tomcat 5.0.28, which GWT uses in the shell, has an older version of Xerces that 
takes priority in the classpath, and is not compatible with Toplink/Spring.

Pass "-Djava.endorsed.dirs=$XERCES_HOME" (whatever the correct path to the Xerces directory is) 
in "google.webtoolkit.extrajvmargs" and "maven.junit.jvmargs" properties - 
place settings in build.properties (Maven 1.x) or settings.xml (Maven 2.x) - see ../README.txt.

If you do not override Xerces you will see StackOverflow errors when attempting to run.

2. The Toplink agent, which will be downloaded by Maven into your local repository, must be passed as 
a system property to the JVM as well. 

Pass "-Djavaagent=${env.USER}/.maven/repository/toplink.essentials/jars/toplink-essentials-agent-2.0-58.jar"
 in "google.webtoolkit.extrajvmargs" and "maven.junit.jvmargs" 
- again place settings in build.properties (M1) or settings.xml (M2).

See build.properties (M1) and settings.xml (M2) included with the project for examples.


-- RUN --
-----------

Once you have things configured, MySQL running locally, have Maven, have settings.xml (M2) or have build.properties (M1),
then you can run the project using Maven.

1. Maven 2:
mvn clean gwt:gwt
mvn clean -Dmaven.test.skip=true gwt:gwt (skip tests)
mvn clean install (create a WAR)

2. Maven 1:
maven clean gwt
maven clean -Dmaven.test.skip=true gwt (skip tests)
maven clean gwt:war (create a WAR)

-- NOTES --
-----------

1. The file upload for the cover art will not operate in the shell (won't work with the embedded Tomcat).
You can also deploy the project as a WAR file to see this feature work 
("maven clean war" M1, or "mvn clean install" M2, then deploy to a servlet container).

2. If you see GWTx related errors such as "[ERROR] Line 81:  The method close() is undefined for 
the type OutputStream . . ." these are related to classpath order, but are NOT really a problem. 
These can safely be disregarded. 

(See ../README.txt.)