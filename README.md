ei# Nginx-based Large Files Downloading Service
This is a large files downloading service based on Nginx, Spring Boot, PostgreSQL and SSDB. Dynamically adding more files and more Nginx nodes are supportive, which simply requireds some registration steps on the administraion Web pages.

## Technology Selections
- Web Server: [Nginx](http://nginx.org/) v1.8.0
- App Server: Embedded Tomcat v8.0.26
- Web Framework: [Spring Boot](https://github.com/spring-projects/spring-boot/)  v1.2.6
- ORM for RDBMS: [MyBatis-Spring](http://mybatis.github.io/spring/) v1.1.1
- Download History Data Storage: [SSDB](https://www.github.com/ideawu/ssdb/)  v1.8.2
	- About 30 download history records per GB
- Other Data Storage (users, large Files for downloading, download tasks, etc.): [PostgreSQL](http://www.postgresql.org/)  v9.2

## Some Screenshots of the Ordinary User
### Main
![](doc/readme/images/ordinary/main.png)
### File list
![](doc/readme/images/ordinary/file_list.png)
### Download task list
![](doc/readme/images/ordinary/download_task_list.png)

## Some Screenshots of the Administrator
### Main
![](doc/readme/images/admin/main.png)
### Production list
![](doc/readme/images/admin/production_list.png)
### File list
![](doc/readme/images/admin/file_list_1.png)
![](doc/readme/images/admin/file_list_2.png)
### Download task list
![](doc/readme/images/admin/download_task_list.png)
### File service group list
![](doc/readme/images/admin/file_service_group_list.png)
### File service list
![](doc/readme/images/admin/file_service_list.png)



