# Kubernetes Microservices Demo

A production-style Spring Boot microservices project demonstrating how to deploy distributed applications on Kubernetes using Docker, Kafka, Redis, and Kubernetes.

## Overview

This project moves beyond running individual Spring Boot applications with Docker by deploying an entire microservices ecosystem on Kubernetes.

The application consists of two independent Spring Boot microservices communicating asynchronously through Apache Kafka while Redis is used for caching. The complete system is containerized with Docker and deployed locally on Kubernetes, providing hands-on experience with container orchestration, networking, service discovery, and self-healing capabilities.

---

## Features

- Spring Boot Microservices
- Apache Kafka Event-Driven Communication
- Redis Caching
- Docker Containerization
- Kubernetes Deployment
- Kubernetes Services
- Self-Healing Pods
- REST APIs
- Docker Compose for Local Testing

---

## Tech Stack

| Technology | Purpose |
|------------|---------|
| Java 17 | Programming Language |
| Spring Boot | Microservices |
| Spring Web | REST APIs |
| Spring Data Redis | Redis Integration |
| Spring Kafka | Event Streaming |
| Apache Kafka | Messaging |
| Redis | Distributed Cache |
| Docker | Containerization |
| Docker Compose | Local Environment |
| Kubernetes | Container Orchestration |

---

# System Architecture

```
                +---------------------+
                |     REST Client     |
                +----------+----------+
                           |
                           |
                    POST /orders
                           |
                           ▼
                +----------------------+
                |    Order Service     |
                |----------------------|
                | REST API             |
                | Redis Cache          |
                | Kafka Producer       |
                +----------+-----------+
                           |
                  order-events Topic
                           |
                           ▼
                  +----------------+
                  | Apache Kafka   |
                  +----------------+
                           |
                           ▼
               +------------------------+
               | Notification Service   |
               |------------------------|
               | Kafka Consumer         |
               | Logs Notification      |
               +------------------------+

                    Redis
                      ▲
                      │
              Order Service Cache
```

---

# Project Structure

```
k8s-demo
│
├── order-service
│   ├── Dockerfile
│   ├── src
│   └── pom.xml
│
├── notification-service
│   ├── Dockerfile
│   ├── src
│   └── pom.xml
│
├── k8s
│   ├── kafka-deployment.yaml
│   ├── kafka-service.yaml
│   ├── redis-deployment.yaml
│   ├── redis-service.yaml
│   ├── order-service.yaml
│   ├── notification-service.yaml
│   └── zookeeper.yaml
│
└── README.md
```

---

# Workflow

1. Client sends an HTTP request to Order Service.
2. Order Service stores the order in Redis with a TTL of 60 seconds.
3. Order Service publishes an event to the Kafka topic **order-events**.
4. Notification Service consumes the event from Kafka.
5. Notification Service simulates sending a notification by logging the order information.

---

# Kubernetes Concepts Practiced

- Pods
- Deployments
- Services
- NodePort
- Container Lifecycle
- Replica Management
- Self-Healing
- Image Pull Policy
- Local Kubernetes Cluster (Docker Desktop)

---

# Problems Solved

During development several real-world deployment issues were encountered and resolved:

- Kafka starting before Zookeeper
- Container restart loops
- ImagePullBackOff for local Docker images
- Making local Docker images visible to Kubernetes
- NodePort accessibility on Windows
- Kafka broker connection issues
- Pod recreation after failures
- Service networking between Pods

---

# Self-Healing Demonstration

One of Kubernetes' most powerful features was verified during testing.

Deleting a running Pod automatically triggered Kubernetes to recreate the Pod within seconds without any manual intervention.

This demonstrates Kubernetes' built-in self-healing capability.

---

# Running the Project

### Clone Repository

```bash
git clone https://github.com/your-username/k8s-demo.git
```

### Build Services

```bash
mvn clean package
```

### Build Docker Images

```bash
docker build -t order-service ./order-service

docker build -t notification-service ./notification-service
```

### Deploy to Kubernetes

```bash
kubectl apply -f k8s/
```

### Check Running Pods

```bash
kubectl get pods
```

### Check Services

```bash
kubectl get svc
```

---

# Learning Outcomes

This project provided hands-on experience with:

- Designing event-driven microservices
- Docker containerization
- Kubernetes deployments
- Kubernetes networking
- Redis caching
- Kafka messaging
- Troubleshooting distributed systems
- Container orchestration
- Infrastructure debugging
- Self-healing deployments

---

# Future Improvements

- Jenkins CI/CD Pipeline
- Helm Charts
- ConfigMaps & Secrets
- Horizontal Pod Autoscaler
- Prometheus & Grafana Monitoring
- Ingress Controller
- Spring Cloud Config
- Kubernetes Persistent Volumes

---

# Author

**Mihir Mehar**

Java Backend Developer

Spring Boot | Microservices | Kafka | Redis | Docker | Kubernetes
