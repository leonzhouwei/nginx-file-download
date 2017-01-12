#!/bin/bash

################################################################################
# editable variables
JAR_BASE_NAME=nginx-file-download
MYLOGDIR="/Users/zhouwei/Desktop/tmp/log"

# PLEASE DO set GC_LOG_FILE as "/dev/shm/gc-myapplication.log" on Linux
GC_LOG_FILE="${MYLOGDIR}/dev/shm/gc-myapplication.log"

################################################################################
# !! DO NOT EDIT THE STATEMENTS BELOW !!
base_path=$(cd `dirname $0`; pwd)
cd $base_path

java -XX:-UseBiasedLocking -XX:AutoBoxCacheMax=20000 -XX:+AlwaysPreTouch -server -XX:-UseCounterDecay -XX:-TieredCompilation -XX:+UseConcMarkSweepGC -XX:CMSInitiatingOccupancyFraction=75 -XX:+UseCMSInitiatingOccupancyOnly -XX:MaxTenuringThreshold=6 -XX:+ExplicitGCInvokesConcurrent -XX:+ParallelRefProcEnabled -Xms1024m -Xmx1024m -XX:NewRatio=1 -XX:MaxDirectMemorySize=1024m -XX:MetaspaceSize=128m -XX:MaxMetaspaceSize=512m -Xloggc:${GC_LOG_FILE} -XX:+PrintGCDateStamps -XX:+PrintGCDetails -XX:+PrintGCApplicationStoppedTime -XX:+PrintCommandLineFlags -XX:-OmitStackTraceInFastThrow -XX:ErrorFile=${MYLOGDIR}/hs_err_%p.log -XX:+HeapDumpOnOutOfMemoryError -XX:HeapDumpPath=${MYLOGDIR}/ -Dspring.profiles.active=prod -jar ./$JAR_BASE_NAME*.jar

exit 0
