Manning GWT In Practice Ch3 Notes
-------------------------------------------------------------

"Communicating with the Server" includes GWT server side RPC code. 
Project requires the GWTCompiler (for client side code) 
and the standard javac compiler (for server side code). 

This project is intended to be used with Eclipse, based on the 
supplied .project and .classpath files. As noted in the text, 
if you import the project into Eclipse (and have GWT_HOME 
defined as a classpath variable in Eclipse, pointing to where GWT
is installed locally) the project will compile.

Once compiled in Eclipse (which creates a "bin" output directory
in the project directory, among other artifacts) you can then launch
the GWTShell or GWTCompiler for the project via the shell/cmd scripts
provided.

If you wish to run the project *without* using Eclipse, you need to 
perform the javac step manually. For this we have provided an 
additional shell/cmd script - HelloServer-compile-javac. The project
must have a "bin" output folder created by javac before it is run. 
This can be done either with Eclipse, or with the supplied compile script. 

(Also see ../README.txt.)

