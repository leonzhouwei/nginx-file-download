#!/bin/bash

######################################################################
# editable variables
JAR_NAME_PREFIX="nginx-file-download"
MYLOGDIR="./var/log"

######################################################################
# !! DO NOT EDIT THE STATEMENTS BELOW !!
GC_LOG_DIR="/dev/shm"
base_path=$(cd `dirname $0`; pwd)

cd ${base_path}

if [ ! -d "${MYLOGDIR}" ]; then
	mkdir -p "${MYLOGDIR}"
fi

if [ ! -d "${GC_LOG_DIR}" ]; then
	GC_LOG_DIR="${MYLOGDIR}/${GC_LOG_DIR}"
	mkdir -p ${GC_LOG_DIR}
fi

java -server  \
	-XX:-UseBiasedLocking  \
	-XX:AutoBoxCacheMax=20000  \
	-XX:+AlwaysPreTouch  \
	-XX:+UseConcMarkSweepGC  \
	-XX:+UseCMSInitiatingOccupancyOnly  \
	-XX:CMSInitiatingOccupancyFraction=75  \
	-XX:MaxTenuringThreshold=6  \
	-XX:+ExplicitGCInvokesConcurrent  \
	-XX:+ParallelRefProcEnabled  \
	-Xms1024m  \
	-Xmx1024m  \
	-XX:NewRatio=1  \
	-XX:MaxDirectMemorySize=1024m  \
	-XX:MetaspaceSize=128m  \
	-XX:MaxMetaspaceSize=512m  \
	-Xloggc:${GC_LOG_DIR}/gc-${JAR_NAME_PREFIX}.log  \
	-XX:+PrintGCDateStamps  \
	-XX:+PrintGCDetails  \
	-XX:+PrintGCApplicationStoppedTime  \
	-XX:+PrintCommandLineFlags  \
	-XX:-OmitStackTraceInFastThrow  \
	-XX:ErrorFile=${MYLOGDIR}/hs_err_%p.log  \
	-XX:+HeapDumpOnOutOfMemoryError  \
	-XX:HeapDumpPath=${MYLOGDIR}/  \
	-Dspring.profiles.active=prod  \
	-jar  \
	${JAR_NAME_PREFIX}*.jar  \
	>/dev/null  \
	2>${MYLOGDIR}/startup.err.log  \
	&

exit 0
