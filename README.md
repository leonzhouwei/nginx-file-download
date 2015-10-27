# Nginx-based File Download Service

## Tech. Choices
- Web Server: [Nginx](http://nginx.org/)
- App Server: Embedded Tomcat
- Web Framework: [Spring Boot](https://github.com/spring-projects/spring-boot/)
- ORM for RDBMS: [MyBatis-Spring](http://mybatis.github.io/spring/)
- Download History Data Storage: [SSDB](https://www.github.com/ideawu/ssdb/)
	- About 30 download history records per GB
- Other Data Storage: [PostgreSQL](http://www.postgresql.org/)
