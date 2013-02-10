@echo off

SET CLASSPATH=%~dp0\src
SET CLASSPATH=%CLASSPATH%;%~dp0\bin
SET CLASSPATH=%CLASSPATH%;%GWT_HOME%\gwt-user.jar
SET CLASSPATH=%CLASSPATH%;%GWT_HOME%\gwt-dev-windows.jar

IF NOT EXIST %~dp0\bin MKDIR %~dp0\bin


@javac -cp "%CLASSPATH%" -d %~dp0\bin %~dp0\src\com\manning\gwtip\helloserver\server\*.java