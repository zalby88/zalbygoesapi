## ----------------------------
## Using JDK 8 image with Maven

FROM maven:3.3-jdk-8

## ----------------------
## Exposing the HTTP Port

EXPOSE 8080

## -------------------------------------------
## Copying the files into the target directory

COPY src /zalbygoesapi/src/
COPY pom.xml /zalbygoesapi/


## --------------------------------------
## Setup Commands when creating the Image

RUN cd zalbygoesapi/ && mvn clean package


## -------------------------------------------
## FINAL Command run when the container is run

CMD java -jar /zalbygoesapi/target/zalbygoesapi.jar --zalbygoesapi.db.type=mocked

## -----------------------------------
## Build the image with this command: 
## docker build -t zalbygoesapi:v1 .

# ---------------------------------------------------------------
# Run the image with this command: 
# docker run --name apitest -p <<host_port>>:8080 zalbygoesapi:v1

# --------------------------------------
# ... then try this endpoint
# GET http://localhost:<<host_port>>/zalbygoesapi/origamis