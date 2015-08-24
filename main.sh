#!/bin/bash

################################################################################
# editable variables
JAR_BASE_NAME=nginx-file-download

################################################################################
# !! DO NOT EDIT THE STATEMENTS BELOW !!
base_path=$(cd `dirname $0`; pwd)
cd $base_path

java -jar ./$JAR_BASE_NAME*.jar

exit 0
