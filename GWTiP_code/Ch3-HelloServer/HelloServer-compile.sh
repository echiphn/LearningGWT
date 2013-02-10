#!/bin/sh
# LINUX compiler invoker

ENTRY_POINT=com.manning.gwtip.helloserver.HelloServer

APPDIR=`dirname $0`;
CLASSPATH=$APPDIR/src
CLASSPATH=$CLASSPATH:$APPDIR/bin
CLASSPATH=$CLASSPATH:$GWT_HOME/gwt-user.jar
CLASSPATH=$CLASSPATH:$GWT_HOME/gwt-dev-linux.jar

java -cp $CLASSPATH com.google.gwt.dev.GWTCompiler -out "$APPDIR/www" "$@" $ENTRY_POINT;
