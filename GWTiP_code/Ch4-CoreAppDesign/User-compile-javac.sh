#!/bin/sh
# LINUX javac compiler

APPDIR=`dirname $0`;
CLASSPATH=$APPDIR/src
CLASSPATH=$CLASSPATH:$APPDIR/bin
CLASSPATH=$CLASSPATH:$GWT_HOME/gwt-user.jar
CLASSPATH=$CLASSPATH:$GWT_HOME/gwt-dev-linux.jar

for file in $(ls $APPDIR/lib/*.jar )
 do
   CLASSPATH=$CLASSPATH:$file
 done

if [ ! -d ./bin ]
  then
    mkdir ./bin
fi

javac -cp $CLASSPATH -d $APPDIR/bin $APPDIR/src/com/manning/gwtip/user/server/*.java  
