FROM java:8

MAINTAINER leonxyzh "leonxyzh@hotmail.com"

WORKDIR /home/nginx-file-download

COPY build ./

RUN mkdir -p ./var/log

EXPOSE 10101 10102

CMD [  \
	"java",  \
	"-XX:AutoBoxCacheMax=20000",  \
	"-XX:+AlwaysPreTouch",  \
	"-XX:+UseConcMarkSweepGC",  \
	"-XX:+UseCMSInitiatingOccupancyOnly",  \
	"-XX:CMSInitiatingOccupancyFraction=75",  \
	"-XX:MaxTenuringThreshold=6",  \
	"-XX:+ExplicitGCInvokesConcurrent",  \
	"-XX:+ParallelRefProcEnabled",  \
	"-Xms1024m",  \
	"-Xmx1024m",  \
	"-XX:NewRatio=1",  \
	"-XX:MaxDirectMemorySize=1024m",  \
	"-XX:MetaspaceSize=128m",  \
	"-XX:MaxMetaspaceSize=512m",  \
	"-Xloggc:/dev/shm/gc.log",  \
	"-XX:+PrintGCDateStamps",  \
	"-XX:+PrintGCDetails",  \
	"-XX:+PrintGCApplicationStoppedTime",  \
	"-XX:+PrintCommandLineFlags",  \
	"-XX:-OmitStackTraceInFastThrow",  \
	"-XX:ErrorFile=./var/log/hs_err_%p.log",  \
	"-XX:+HeapDumpOnOutOfMemoryError",  \
	"-XX:HeapDumpPath=./var/log/",  \
	"-Dspring.profiles.active=prod",  \
	"-jar",  \
	"nginx-file-download-0.10.0.jar",  \
	">/dev/null",  \
	"2>./var/log/startup.err.log",  \
	"&"  \
    ]
