@echo off

SET ENTRY_POINT=com.manning.gwtip.user.User
SET HOST_PAGE=index.html

SET CLASSPATH=%~dp0\src
SET CLASSPATH=%CLASSPATH%;%~dp0\bin

SET CLASSPATH=%CLASSPATH%;%~dp0\lib\antlr-2.7.6.jar
SET CLASSPATH=%CLASSPATH%;%~dp0\lib\asm-1.5.3.jar
SET CLASSPATH=%CLASSPATH%;%~dp0\lib\asm-attrs-1.5.3.jar
SET CLASSPATH=%CLASSPATH%;%~dp0\lib\cglib-2.1_3.jar
SET CLASSPATH=%CLASSPATH%;%~dp0\lib\commons_collections-2.1.1.jar
SET CLASSPATH=%CLASSPATH%;%~dp0\lib\dom4j-1.6.1.jar
SET CLASSPATH=%CLASSPATH%;%~dp0\lib\ehcache-1.2.3.jar
SET CLASSPATH=%CLASSPATH%;%~dp0\lib\GWTx-20070605.jar
SET CLASSPATH=%CLASSPATH%;%~dp0\lib\hibernate-3.2.1.ga.jar
SET CLASSPATH=%CLASSPATH%;%~dp0\lib\hibernate-annotations-3.2.0.ga.jar
SET CLASSPATH=%CLASSPATH%;%~dp0\lib\hibernate-entitymanager-3.2.0.ga.jar
SET CLASSPATH=%CLASSPATH%;%~dp0\lib\javassist-3.3.jar
SET CLASSPATH=%CLASSPATH%;%~dp0\lib\jboss-archive-browsing-5.0.0alpha-200607201-119.jar
SET CLASSPATH=%CLASSPATH%;%~dp0\lib\jta-1_0_1B-classes.jar
SET CLASSPATH=%CLASSPATH%;%~dp0\lib\mysql-connector-java-3.1.14.jar
SET CLASSPATH=%CLASSPATH%;%~dp0\lib\persistence-api-1.0.jar

SET CLASSPATH=%CLASSPATH%;%GWT_HOME%\gwt-user.jar
SET CLASSPATH=%CLASSPATH%;%GWT_HOME%\gwt-dev-windows.jar


@java -cp "%CLASSPATH%" com.google.gwt.dev.GWTShell -logLevel TRACE -out "%~dp0\www" %* %ENTRY_POINT%\%HOST_PAGE%