#!/bin/bash

################################################################################
# editable variables
CONFIG="config"
BUILD="build"
TARGET="target"
JAR_NAME_PREFIX="nginx-file-download"

######################################################################
############### !! DO NOT EDIT THE STATEMENTS BELOW !! ###############
base_path=$(cd `dirname $0`; pwd)
APP="app"

cd ${base_path}

mvn clean
mvn package

rm -rf ${BUILD}
mkdir -p ${BUILD}/${APP}

cp -r ${APP}/data ${BUILD}/${APP}/

mkdir -p ${BUILD}/${CONFIG}
cp -r ${CONFIG}/application.properties ${BUILD}/${CONFIG}
cp -r ${CONFIG}/logback.xml ${BUILD}/${CONFIG}

cp db.sqlite ${BUILD}/

cp ${TARGET}/${JAR_NAME_PREFIX}*.jar ${BUILD}/

cp startup.sh ${BUILD}/

exit 0
