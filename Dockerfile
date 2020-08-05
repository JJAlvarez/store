FROM anapsix/alpine-java

LABEL maintainer="alvarez171074@unis.edu.gt"

COPY /target/spring-petclinic-1.5.1.jar /home/spring-petclinic-1.5.1.jar

CMD ["java","-jar","/home/spring-petclinic-1.5.1.jar"]
