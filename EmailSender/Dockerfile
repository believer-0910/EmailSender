FROM openjdk:16
COPY build/libs/EmailSender-0.0.1-SNAPSHOT.jar emailsender.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "emailsender.jar"]
