FROM openjdk:8
EXPOSE 9001
ADD target/Stores.war Stores.war
ENTRYPOINT ["java","-jar","/Stores.war"]
