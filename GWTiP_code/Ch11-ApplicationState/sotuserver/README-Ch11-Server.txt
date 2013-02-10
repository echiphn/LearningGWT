Manning GWT In Practice Ch11 Server project
-------------------------------------------------------------

This folder contains the GWT Server application discussed in Chapter 11.

This project uses Maven.

The GWT-Maven plugin for Maven is required (http://code.google.com/p/gwt-maven/)
and explained in the text (Chapter 7). Once the plugin is present 
(running the project will download it for you), and configured (see ../README.txt) 
you can use Maven as follows:

1. Maven 2:
mvn clean gwt:gwt (this will run the Chat application, but will not be able to serve images)
mvn clean install (create a WAR)

2. Maven 1:
maven clean gwt (this will run the Chat application, but will not be able to serve images)
maven clean gwt:war (create a WAR)

Once you have deployed the WAR file to a container instance, you can browse it
via:
http://localhost:8080/sotu-server/gwtip.sotu.SOTU/SOTU.html

(Also see ../README.txt.)