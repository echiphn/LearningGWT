 Manning GWT in Practice Supplied Project Code Notes
====================================================

The text of GWTiP has three main parts:
 
1 - Introduction 
2 - Practical Problems and Solutions 
3 - Larger Cumulative Examples.  

Throughout the book we are using different development and build tools 
with different chapters. We do this intentionally, to show the various 
tools available and also emphasize the important aspects that apply regardless 
of the tool. Among the tools used are command line shell scripts, 
GWT provided tools, Eclipse, NetBeans, IDEA, Ant, and Maven. 


PART 1
------
Ch01 - Introduction 	- No external project code.
Ch02 - CalculatorClient - Command line ApplicationCreator.
Ch03 - HelloServer 	- Command line ProjectCreator and Eclipse IDE.

PART 2
------
Ch04 - CoreAppDesign 	- No specific tool noted in text, example uses command line. 
Ch05 - ServerTechniques	- NetBeans IDE and gwt4nb plugin.
Ch06 - IntegrateJS 	- No specific tool noted in text, examples use Maven (See ch7 for more Maven info).
Ch07 - BuildAntMaven 	- Uses Ant and Maven, introduces those tools. 
Ch08 - Testing		- Uses Maven.

PART 3
------
Ch09 - Bookstore 	- Two applications, admin app and store. Uses Maven.
Ch10 - DragAndDrop 	- Uses Maven. 
Ch11 - ApplicationState - Two applications, server and client. Server uses Maven, client uses Netbeans. 


*Each chapter's examples also contains a README-ChX.txt file that has
more detail specific to the respective chapter.


========
GWT_HOME
========
Some projects, and all tools (command line scripts, IDE files), 
have been modified from the default to refer to the environment
variable GWT_HOME. When running any of the command line tools or examples, 
this variable must be present on your system and it should refer to the location 
where you have GWT installed. (Tested with GWT 1.4.60.)


=================
SHELL/BAT SCRIPTS
=================
Shell/Batch startup scripts for GWTCompiler and GWTShell
(ProjectName-compile and ProjectName-shell) are provided
for projects using that technique for EACH platform.

The script extensions identify which platform each
is intended for use with as follows:

ProjectName-shell.cmd = Windows
ProjectName-shell.sh = Linux 
ProjectName-shell = Mac


=========
IDE FILES
=========
In projects that use IDEs we are also including the IDE
related project files (.project/.classpath for Eclipse, etc).
We have generally NOT provided IDE files specific to each platform.
This is because these files are typically more portable than shell scripts
and a single version should work on all platforms.  

We have modified IDE specific project files only to refer to
GWT_HOME rather than to a hard coded path. GWT_HOME needs to be 
defined as an environment variable, and as an IDE variable.


===========
Maven Notes
===========
POM (pom.xml Maven 2.x, and project.xml Maven 1.x) configuration files 
for Maven are included with each project that uses Maven.
The Maven GWT plugin used in the text, GWT-Maven, supports both Maven 1 and Maven 2.

Maven 2 is used in the text. In order to work with the Maven examples you
will need to setup your environment as described in the respective Maven sections below:

Maven 2:
--------

You need to have Maven installed and configured before proceeding:
http://maven.apache.org/download.html (Tested with 2.0.8).

Requires Maven 2 "settings.xml" profile file in users home directory/.m2 subdirectory (~/.m2/settings.xml).
For GWT-Maven you must define where GWT is installed with the google.webtoolkit.home property.
If you are using a Mac you also need to pass -XstartOnFirstThread to JVM via the 
google.webtoolkit.extrajvmargs property. 

Example ~/.m2/settings.xml:

<?xml version="1.0" encoding="UTF-8"?>
 <settings>
   <profiles>
     <profile>
       <id>gwt-1.4.60</id> 
       <properties>
         <google.webtoolkit.home>
           [PATH_TO_GWT]/gwt-[mac|windows|linux]-1.4.60
         </google.webtoolkit.home>
         <!-- uncomment for Mac 
         <google.webtoolkit.extrajvmargs>
           -XstartOnFirstThread
         </google.webtoolkit.extrajvmargs>
         -->
       </properties>
     </profile>
   </profiles> 
   <activeProfiles>
     <activeProfile>
       gwt-1.4.60
     </activeProfile>
   </activeProfiles>
</settings>

Note that a "settings.xml" file is included with each Maven project as an example, 
but a ~/.m2/settings.xml file is required. You need to use the example file as 
a template to setup your environment.


Maven 1:
-------

You need to have Maven installed configured before proceeding:
http://maven.apache.org/maven-1.x/start/install.html (Tested with 1.1).

Requires Maven 1 "build.properties" for machine wide settings in users home directory ~
(this is the Maven 1 equivalent of Maven 2 settings.xml).

For GWT-Maven you must define where GWT is installed with the google.webtoolkit.home property.
ALSO for projects that have GwtTestCase based tests, you must define the "platform" to one of 
"windows" "linux" or "mac" and add the gwt-dev jar to the depedency list in the project.xml file
(This is done for you in each project.xml, but it relies on the correct "platform" setting in 
build.properties - this is required for projects with tests due to the way the GWT JUnit support works.) 
If you are using a Mac you also need to pass -XstartOnFirstThread to the JVM via the 
google.webtoolkit.extrajvmargs property. 

Example ~/build.properties:

platform=[mac|windows|linux]
google.webtoolkit.home=[PATH]/gwt-${platform}-1.4.60
# uncomment the next two properties for GWT-Maven usage on the Mac
#google.webtoolkit.extrajvmargs=-XstartOnFirstThread
#maven.junit.jvmargs=-XstartOnFirstThread

Note that a "build.properties" file is included with each Maven project as an example, 
but you still should create a ~/build.properties which will OVERRIDE any local project file. 
You should use the example file as a template to setup your environment.



