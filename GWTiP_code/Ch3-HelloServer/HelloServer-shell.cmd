@echo off

SET ENTRY_POINT=com.manning.gwtip.helloserver.HelloServer
SET HOST_PAGE=HelloServer.html

SET CLASSPATH=%~dp0\src
SET CLASSPATH=%CLASSPATH%;%~dp0\bin
SET CLASSPATH=%CLASSPATH%;%GWT_HOME%\gwt-user.jar
SET CLASSPATH=%CLASSPATH%;%GWT_HOME%\gwt-dev-windows.jar


@java -cp "%CLASSPATH%" com.google.gwt.dev.GWTShell -logLevel TRACE -out "%~dp0\www" %* %ENTRY_POINT%\%HOST_PAGE%