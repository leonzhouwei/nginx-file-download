# 基于 Nginx 的大文件下载服务
本应用可以提供大文件下载服务，典型的文件大小是 GB。应用基于 Nginx、Spring Boot 和 SQLite(或者 MySQL) 开发。该应用支持动态地增加更多的文件和（或）更多的 Nginx，你只需要在管理员用户界面上做一些注册工作。此外，Nginx 提供的基础服务是 [X-Accel](https://www.nginx.com/resources/wiki/start/topics/examples/x-accel/)。

## 1. 技术栈
- Web Server: [Nginx](http://nginx.org/) v1.8.0
- App Server: Embedded Tomcat v8.0.26
- Web Framework: [Spring Boot](https://github.com/spring-projects/spring-boot/)  v1.2.6
- ORM for RDBMS: [MyBatis-Spring](http://mybatis.github.io/spring/) v1.1.1
- 关系型数据库: SQLite(默认) 或者 MySQL

## 2. 开始玩耍
如果程序以默认模式运行（所用数据库为 SQLite，配置文件为 application.properties），那么有两个用户已经存在了。一个是 ***admin***, 其密码为 ***changeme***。另一个是 ***alice***， 其密码为 ***test***。

### 2.1. 进入工程所在目录

### 2.2. 在目录 /usr/local/var/www/protected 下面准备可供下载的文件
```
sudo mkdir -p /usr/local/var/www
sudo cp -r ./app/external/var/www/protected /usr/local/var/www/
```

### 2.3. 配置你的 Nginx
完整的 nginx.conf 在[这里](https://github.com/leonzhouwei/nginx-file-download/blob/master/app/external/conf/nginx/nginx.conf).

```
# 1. 开启 sendfile
sendfile        on;

# 2. 本工程所需配置
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


### 2.4. 运行本工程
#### 如果你有 Docker 
#### {
```
# 将名为 'localhost/nfd' 的 Docker 镜像装入你本地的 Docker 仓库,
# 或者你可以在终端中输入: docker build -t localhost/nfd
sh docker_daily_build.sh

# 配置端口并运行
docker run -d -p 10101:10101 -p 10102:10102 localhost/nfd
```
#### } 

#### 否则 
#### {
需要

- Java 8
- Maven 3.x

编译

```
sh daily_build.sh
```

运行

```
cd build
sh startup.sh
```
### }

### 2.5. 打开浏览器，跳转到 http://127.0.0.1/

### 2.6. 登录 (alice:test)
![](doc/static/v0.10.2/images/login.png)

### 2.7. 主界面
![](doc/static/v0.10.2/images/ordinary/main.png)

### 2.8. 文件列表
![](doc/static/v0.10.2/images/ordinary/file_list.png)

### 2.9. 下载 gopher.jpg
![](doc/static/v0.10.2/images/ordinary/download_action.png)

### 2.10. 下载任务列表
回到主界面，单击“我的下载任务列表”。
![](doc/static/v0.10.2/images/ordinary/download_task_list.png)

## 3. 管理员用户界面截图
### 3.1. 主界面
![](doc/static/v0.10.2/images/admin/main.png)
### 3.2. 用户列表
![](doc/static/v0.10.2/images/admin/account_list.png)
### 3.3. 产品列表
![](doc/static/v0.10.2/images/admin/production_list.png)
### 3.4. 文件列表
![](doc/static/v0.10.2/images/admin/file_list.png)
### 3.5. 下载任务列表
![](doc/static/v0.10.2/images/admin/download_task_list.png)
### 3.6. 文件服务组列表
![](doc/static/v0.10.2/images/admin/file_service_group_list.png)
### 3.7. 文件服务列表
![](doc/static/v0.10.2/images/admin/file_service_list.png)

## 4. 代码质量
![](doc/static/v0.10.2/images/sonar_code_quality.png)




