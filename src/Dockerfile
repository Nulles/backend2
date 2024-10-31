# Use the OpenJDK 21 image
FROM ubuntu:latest

# Update the package list and install necessary dependencies
RUN apt-get update && \
    apt-get install -y wget git && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*
RUN wget https://download.oracle.com/java/21/latest/jdk-21_linux-x64_bin.deb
RUN apt install ./jdk-21_linux-x64_bin.deb

# Cloning backend repository
RUN git clone https://github.com/Daniil-Zakharov/backend.git /backend

# Build your application using Gradle
RUN cd backend && chmod +x /backend/gradlew && /backend/gradlew build "-Pvaadin.productionMode"

# Set the working directory
WORKDIR /app

# Copy your application code into the container
COPY . /app

# Specify the command to run application
CMD ["java", "-jar", "/backend/build/libs/Git-0.0.1-SNAPSHOT.jar"]