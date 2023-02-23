FROM openjdk:17
EXPOSE 9999
COPY "./target/*.jar" "/app.jar"
ENTRYPOINT ["java","-jar","app.jar"]