Manning GWT In Practice Ch6 Notes
-------------------------------------------------------------

"Integrating Legacy and Third Party AJAX Libraries"

The samples for this chapter include the following:
MooFx - project that creates a GWT module supporting MooFx.
MooFxDemo - project that uses the GWT MooFx module in an example.
Scriptaculous - project that creates a GWT module supporting Scriptaculous.
ScriptaculousDemo - project that uses the GWT Scriptaculous module in an example.

Each of the projects in this chapter is built using Maven and the
GWT-Maven plugin. Support for Maven is explained in detail in Chapter
7, and setup information is included in ../README.txt.

To run each project (once Maven is installed, and configured):
MooFx - mvn clean install  
MooFxDemo - mvn clean gwt:gwt
Scriptaculous - mvn clean install
ScriptaculousDemo - mvn clean gwt:gwt

Note that you do not have to build the GWT module projects (MooFx and Scriptaculous)
unless you want to. You can alternatively just run the demo projects and those 
modules will be included as dependencies and downloaded from a public Maven repository 
(which we have configured).

(Also see ../README.txt.)