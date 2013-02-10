Manning GWT In Practice Ch4 Notes
-------------------------------------------------------------

"Core Application Structure" is a more advanced project
that uses JPA enabled beans, and requires a local MySQL server. 

This project also includes GWT server side RPC code. 
Project requires the GWTCompiler (for client side code) 
and the standard javac compiler (for server side code). 

Shell scripts are supplied to compile and run this project.
You must use the -javac script for your platform first, and you
can then run the -shell script to start the GWT shell. 

This code is meant to be run from the GWT shell. In order to run
this example in an external container you would need to create a WAR
file that contains the correct entries and a deployment descriptor (web.xml). 
See chapter 7 for more information.

(Also see ../README.txt.)

-----
MySQL
-----
MySQL must be running on localhost, and as shown in persistence.xml 
you will need the following configuration:

Databse: bookstore
User: bookstore
Password: books

The structure of the database will automatically be created, but the database itself, 
and a user with access to it, is a prerequisite. 




