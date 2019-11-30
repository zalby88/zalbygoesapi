# zalbygoesapi
A Pet Project of a (Dockerized) Java based API

## Basic Kubernetes commands to deploy in Google Cloud Platform

- kubectl apply -f deployment.yaml

- kubectl expose pod origamiapi --type=LoadBalancer --port 8080 --target-port 8080

## Kubernetes deployment.yaml file example

    ---
     apiVersion: v1
     kind: Pod
     metadata:
       name: origamiapi
       labels:
         app: web
     spec:
       containers:
         - name: zalbygoesapi
           image: gcr.io/serene-mender-249412/zalbygoesapi:1.1.1_mongo
           ports:
             - containerPort: 8080
         - name: stuffmongo
           image: gcr.io/serene-mender-249412/stuffmongo:1.0
           ports:
             - containerPort: 27017
