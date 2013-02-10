Manning GWT In Practice Ch7 Notes
-------------------------------------------------------------

"Packaging, Distribution, and Deployment."

This project is intended to be used with Ant and Maven. Both Ant
and Maven are explained in the text. The sample project contained
here can be used with either. 

===
Ant
===

An Ant build file that can create a module and a deployable
war artifact is included - build.xml (and this is explained in the text).
The provided Ant goals (available via "ant -p" in the project) are:

Ant 
Main targets:

 compile     Compile src to build/bin
 copyclient  Copy GWT generated files to root of dist
 copylib     Copy required libs to dist/WEB-INF/lib
 copyserver  Copy javac output to dist/WEB-INF/classes
 copywebinf  Copy web/WEB-INF contents to dist/WEB-INF
 distwar     Perform steps to create dist directory for war
 gwtcompile  Invoke the GWTCompiler against Java source files
 jar         Package up the project as a jar
 war         Package up the dist directory as a war

The command "ant war" will create a deployable war file.

=====
Maven
=====

Maven project files are included as well (Maven 1 and Maven 2). 

The GWT-Maven plugin (1 or 2) is required (http://code.google.com/p/gwt-maven/)
and explained in the text. Once the plugin is present (running the project will download it for you),
 and configured (see ../README.txt) the following commands will run the shell, or create a WAR, etc.


Maven 2:

mvn clean install (install target will invoke needed goals and create a WAR, because packaging type is war)
mvn clean gwt:gwt (runs the GWTShell)


Maven 1:

maven clean gwt (runs the GWTShell)
maven clean gwt:war (creates a WAR)

(Also see ../README.txt.)
