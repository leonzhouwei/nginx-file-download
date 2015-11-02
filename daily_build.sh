#!/bin/bash

################################################################################
# editable variables
CUSTOM=custom
CONFIG=config
BUILD=daily_build
TARGET=target
JAR_BASE_NAME=nginx-file-download

################################################################################
#################### !! DO NOT EDIT THE STATEMENTS BELOW !! ####################
APP=app
base_path=$(cd `dirname $0`; pwd)
cd $base_path

mvn clean
mvn package -Dmaven.test.skip=true

rm -rf $BUILD
mkdir -p $BUILD/$APP

cp -r $APP/data $BUILD/$APP/
cp -r $CONFIG $BUILD/
cp -r $CUSTOM $BUILD/

cp *.sh $BUILD/
#rm $BUILD/build.sh
rm $BUILD/daily_build.sh

cp $TARGET/$JAR_BASE_NAME*.jar $BUILD/

exit 0
