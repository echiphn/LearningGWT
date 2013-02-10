Manning GWT In Practice Ch10 Notes
------------------------------------

Drag and Drop

This is a small drag and drop example that re-uses some of the "Bookstore" concepts. 
For more information on Bookstore see Chapter 9 example code. 

This project uses Maven (Maven 1 and 2 examples provided).

The GWT-Maven plugin for Maven is required (http://code.google.com/p/gwt-maven/)
and explained in the text (Chapter 7). Once the plugin is present 
(running the project will download it for you), and configured (see ../README.txt) 
you can use Maven as follows:

1. Maven 2:
mvn clean gwt:gwt
mvn clean install (create a WAR)

2. Maven 1:
maven clean gwt
maven clean gwt:war (create a WAR)

(See ../README.txt.)