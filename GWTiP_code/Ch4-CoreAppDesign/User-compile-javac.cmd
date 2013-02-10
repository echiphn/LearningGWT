@echo off

SET CLASSPATH=%~dp0\src
SET CLASSPATH=%CLASSPATH%;%~dp0\bin
SET CLASSPATH=%CLASSPATH%;%GWT_HOME%\gwt-user.jar
SET CLASSPATH=%CLASSPATH%;%GWT_HOME%\gwt-dev-windows.jar

SET CLASSPATH=%CLASSPATH%;%~dp0\lib\antlr-2.7.6.jar
SET CLASSPATH=%CLASSPATH%;%~dp0\lib\asm-1.5.3jar
SET CLASSPATH=%CLASSPATH%;%~dp0\lib\asm-attrs-1.5.3.jar
SET CLASSPATH=%CLASSPATH%;%~dp0\lib\cglib-2.1.3.jar
SET CLASSPATH=%CLASSPATH%;%~dp0\lib\commons-collections-2.1.1.jar
SET CLASSPATH=%CLASSPATH%;%~dp0\lib\commons-logging-1.1.jar
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

IF NOT EXIST %~dp0\bin MKDIR %~dp0\bin

@javac -cp "%CLASSPATH%" -d %~dp0\bin %~dp0\src\com\manning\gwtip\user\server\*.java
