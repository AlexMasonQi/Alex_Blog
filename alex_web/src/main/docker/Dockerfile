FROM openjdk:10
VOLUME /tmp
ADD alex_blog.jar app.jar
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-jar","/app.jar"]