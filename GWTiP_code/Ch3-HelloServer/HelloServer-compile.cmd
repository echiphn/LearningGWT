@echo off

SET ENTRY_POINT=com.manning.gwtip.helloserver.HelloServer

SET CLASSPATH=%~dp0\src
SET CLASSPATH=%CLASSPATH%;%~dp0\bin
SET CLASSPATH=%CLASSPATH%;%GWT_HOME%\gwt-user.jar
SET CLASSPATH=%CLASSPATH%;%GWT_HOME%\gwt-dev-windows.jar

@java -cp "%CLASSPATH%" com.google.gwt.dev.GWTCompiler -out "%~dp0\www" %* %ENTRY_POINT%