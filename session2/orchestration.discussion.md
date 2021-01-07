---
title: "Container Orchestration Discussion"
nextpage: ../session3
---

{% include nav.html %}

Docker Compose is useful for simple unit testing in development mode.

To run Docker containers in production, container orchestration is needed.

What benefits does orchestration provide?
- Starting and stopping services
- Health check and dependency management
- Auto scaling a container to multiple nodes
- Service discovery (without awareness of the number of nodes running)

## Orchestration Options
  - [Docker Swarm](https://docs.docker.com/engine/swarm/) 
    - Fyi, used at UCB library
    - This is a paid service of Docker
    - At one time, Swarm was going to be deprecated.  It may have been un-deprecated at some time in 2020.
  - [Kubernetes](https://kubernetes.io/)
    - Created by Google, now open-source
    - An open source standard implemented by multiple vendors
    - More complex and more powerful than docker-compose
    - [Amazon EKS](https://aws.amazon.com/eks/) - AWS implementation of Kubernetes
  - [Amazon ECS](https://aws.amazon.com/ecs/) 
    - Amazon implementation of containerization resembling other AWS services
    - [Deploy applications on Amazon ECS using Docker Compose](https://aws.amazon.com/blogs/containers/deploy-applications-on-amazon-ecs-using-docker-compose/)

{% include next.html %}