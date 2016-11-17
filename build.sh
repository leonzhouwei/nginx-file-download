#!/bin/bash

################################################################################
# editable variables
CUSTOM=custom
CONFIG=config
BUILD=build
TARGET=target
JAR_BASE_NAME=nginx-file-download

################################################################################
#################### !! DO NOT EDIT THE STATEMENTS BELOW !! ####################
base_path=$(cd `dirname $0`; pwd)
cd $base_path
mvn clean
mvn package -Dmaven.test.skip=true

APP=app
JAR_FILE_BASE_NAME=$(basename $TARGET/$JAR_BASE_NAME*.jar)
VERSION=${JAR_FILE_BASE_NAME/$JAR_BASE_NAME-/''}
VERSION=${VERSION/'.jar'/''}
BUILD_VERSION=$BUILD/$VERSION

rm -rf $BUILD
mkdir -p $BUILD_VERSION/$APP
mkdir -p $BUILD_VERSION/$CONFIG

cp -r $APP/data $BUILD_VERSION/$APP/
cp -r $CONFIG/application.properties.production $BUILD_VERSION/$CONFIG/application.properties
cp -r $CONFIG/logback.xml.production $BUILD_VERSION/$CONFIG/logback.xml
cp -r $CUSTOM $BUILD_VERSION/

cp sqlite.sqlite $BUILD_VERSION/
cp *.sh $BUILD_VERSION/
rm $BUILD_VERSION/build.sh
rm $BUILD_VERSION/daily_build.sh

cp $TARGET/$JAR_BASE_NAME*.jar $BUILD_VERSION/

exit 0
