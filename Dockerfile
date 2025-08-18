# -------- Stage 1: Build --------
FROM openjdk:21-jdk-slim AS build
WORKDIR /app

#--- Copy source code-----
COPY BarqSystems.java .

# Compile Java program into .class
RUN javac BarqSystems.java

# -------- Stage 2: Runtime --------
FROM openjdk:21-slim	
WORKDIR /app

# Copy compiled class file only
COPY --from=build /app/BarqSystems.class .

# Run the application
ENTRYPOINT ["java", "BarqSystems"]

