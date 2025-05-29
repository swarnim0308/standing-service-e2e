# Running our App with Docker

Kindly follow the steps to build a Docker image for the application and then run it as a Docker container.

---

## 1. Build the Docker Image

First, we need to build a Docker image from our application's source code. Make sure we are in the root directory of our application where our `Dockerfile` is located.

Use the following command to build the image:

```bash
docker build -t standing-service .
```

## 2. Run the Docker Container

Here we are using host port 8090 which maps with the exposed container port 8080 where our application is running.

```bash
docker run -p 8090:8080 standing-service
```