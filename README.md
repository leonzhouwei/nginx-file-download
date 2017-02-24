# Nginx-based Large File Downloading Service
This is a large file (typically GB in size) downloading service based on Nginx, Spring Boot, SQLite(or MySQL). Dynamically adding more files and more Nginx nodes are supportive, which simply requires some registration steps on the administraion Web pages. What's more, the fundamental support from Nginx is [X-accel](https://www.nginx.com/resources/wiki/start/topics/examples/x-accel/).

## Technology Stack
- Web Server: [Nginx](http://nginx.org/) v1.8.0
- App Server: Embedded Tomcat v8.0.26
- Web Framework: [Spring Boot](https://github.com/spring-projects/spring-boot/)  v1.2.6
- ORM for RDBMS: [MyBatis-Spring](http://mybatis.github.io/spring/) v1.1.1
- RDBMS: SQLite(default) or MySQL

## Getting Started
If this program runs in the default mode (SQLite is used), then two accounts already exist. One is ***admin***, whose password is ***changeme***. The other is ***alice***, whose password is ***test***.

### 1. Enter the directory in which this project is located

### 2. Prepare downloadable files under /usr/local/
```
sudo cp -r ./app/external/var /usr/local/
```

### 3. Configure your Nginx
The complete nginx.conf can be found [here](https://github.com/leonzhouwei/nginx-file-download/blob/master/app/external/conf/nginx/nginx.conf).

```
# 1. enable sendfile
sendfile        on;

# 2. configure for this project
       location /protected/ {
    		charset utf-8;
    		root /usr/local/var/www;
    		internal;
    	}
    	
    	location / {
    	    proxy_pass  http://127.0.0.1:10101/;  
                proxy_redirect     off;  
                proxy_set_header   Host             $host;  
                proxy_set_header   X-Real-IP        $remote_addr;  
                proxy_set_header   X-Forwarded-For  $proxy_add_x_forwarded_for;  
      
                client_max_body_size       10m;  
                client_body_buffer_size    128k;  
      
                proxy_connect_timeout      90;  
                proxy_send_timeout         90;  
                proxy_read_timeout         90;  
      
                proxy_buffer_size          4k;  
                proxy_buffers              4 32k;  
                proxy_busy_buffers_size    64k;  
                proxy_temp_file_write_size 64k;
    	}
```


### 4. Run this project
#### if you have Docker 
#### {
```
# install the image named 'localhost/nfd' into your local docker repo,
# or you can type in shell docker build -t localhost/nfd . 
sh docker_daily_build.sh

# run the program with default ports assignment
docker run -d -p 10101:10101 -p 10102:10102 localhost/nfd

# open your browser, and enter http://127.0.0.1/
# DONE!
# Have Fun!
```
#### } 

#### else 
#### {
Requirements

- Java 8
- Maven 3.x

Build

```
sh daily_build.sh
```

Run

```
cd build
sh startup.sh

# open your browser, and enter http://127.0.0.1/
# DONE!
# Have Fun!
```
### }

## Screenshots of the Ordinary Users
### Main
![](doc/static/v0.10.1/images/ordinary/main.png)
### File List
![](doc/static/v0.10.1/images/ordinary/file_list.png)
### Download Task List
![](doc/static/v0.10.1/images/ordinary/download_task_list.png)

## Screenshots of the Administrators
### Main
![](doc/static/v0.10.1/images/admin/main.png)
### Account List
![](doc/static/v0.10.1/images/admin/account_list.png)
### Production List
![](doc/static/v0.10.1/images/admin/production_list.png)
### File List
![](doc/static/v0.10.1/images/admin/file_list.png)
### Download Task List
![](doc/static/v0.10.1/images/admin/download_task_list.png)
### File Service Group List
![](doc/static/v0.10.1/images/admin/file_service_group_list.png)
### File Service List
![](doc/static/v0.10.1/images/admin/file_service_list.png)

## Code Quality
![](doc/static/v0.10.1/images/sonar_code_quality.png)




