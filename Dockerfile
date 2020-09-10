FROM maven:3-openjdk-11

WORKDIR /workspace
COPY . .

RUN mvn package

