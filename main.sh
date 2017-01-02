#!/bin/bash

################################################################################
# editable variables
JAR_BASE_NAME=nginx-file-download

################################################################################
# !! DO NOT EDIT THE STATEMENTS BELOW !!
base_path=$(cd `dirname $0`; pwd)
cd $base_path

-server -XX:+UseConcMarkSweepGC -XX:CMSInitiatingOccupancyFraction=75 -XX:+UseCMSInitiatingOccupancyOnly -XX:MaxTenuringThreshold=6 -XX:+ExplicitGCInvokesConcurrent -XX:+ParallelRefProcEnabled -Xms1024m -Xmx1024m -XX:NewRatio=1 -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=512m -Xloggc:/Users/zhouwei/Desktop/tmp/dev/shm/gc-myapplication.log -XX:+PrintGCApplicationStoppedTime -XX:+PrintGCDateStamps -XX:+PrintGCDetails -jar ./$JAR_BASE_NAME*.jar

exit 0
