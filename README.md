# Nginx-based Large Files Downloading Service

## Tech. Choices
- Web Server: [Nginx](http://nginx.org/) v1.8.0
- App Server: Embedded Tomcat v8.0.23
- Web Framework: [Spring Boot](https://github.com/spring-projects/spring-boot/)  v1.2.5
- ORM for RDBMS: [MyBatis-Spring](http://mybatis.github.io/spring/) v1.1.1
- Download History Data Storage: [SSDB](https://www.github.com/ideawu/ssdb/)  v1.8.2
	- About 30 download history records per GB
- Other Data Storage: [PostgreSQL](http://www.postgresql.org/)  v9.2
