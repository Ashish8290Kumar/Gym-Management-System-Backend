# Step 1: Build the application using Java 21 and Maven
FROM maven:3.9.6-eclipse-temurin-21 AS build
WORKDIR /app
# Poore project files (pom.xml aur src) copy karne ke liye
COPY . .
RUN mvn clean package -DskipTests

# Step 2: Run the application using Java 21 Runtime
FROM eclipse-temurin:21-jre-jammy
WORKDIR /app
COPY --from=build /app/target/*.jar app.jar

# Aapka custom port 8090 hai
EXPOSE 8090
ENTRYPOINT ["java","-jar","app.jar"]
