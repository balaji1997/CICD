FROM openjdk
ADD target/Supermart-0.0.1-SNAPSHOT.jar Supermart-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java", "-jar", "Supermart-0.0.1-SNAPSHOT.jar"]