# Docker Tutorial for UC3 Developers
This will consist of three 90 minute sessions covering various topics in the use of Docker.  The sessions will start in late Jan 2021.

UC3 staff use MySql, Java, and Ruby, so the sample docker files will use these technologies.

## Pre Work: Install docker and docker-compose
_Please complete the following before the first session.  Reach out for help if the installation steps do not work for you._
- [Windows 10 Setup](prework/Windows10.md)
- [MacOS Setup](prework/MacOS.md)
- [Linux Setup](prework/Linux.md)

## Session 1: Docker Images and Containers
Execute code using the docker command.  Use docker command line ags.

- Run mysql
  - Connect to mysql container from local mysql cli (if available)
  - Use docker exec to run mysql
  - Create database table and content
- Run apache
  - Connect from browser
  - Use bind volume to serve files
- Create Dockerfile/container to customize database and load default content
- Create Dockerfile/java container to ingest content from a CSV
- Create Dockerfile/ruby/puma container to read from the database and display content on a web page

## Session 2: Orchestrate sample containers with docker-compose

- Migrate stack to docker-compose
- Start/stop/repeat with docker-compose
- Persist content with docker volumes
- Switch "projects" (-p option) to use different sets of volumes
- Orchestration discussion
  - Docker Swarm (used at UCB)
  - Kubernetes
  - Amazon ECS and EKS

## Session 3: Using Docker in common tools

- Running Docker on a server
  - Using published ports
  - Running software that is not already installed
- VSCode and Docker
- GitHub Actions and Docker
- Packaging Docker Containers for Lambda deployment
- Container Registries
  - DockerHub
  - Amazon ECR
  - GitHub ??Containers
  
## Notes
_This site reuses content from https://dspace-labs.github.io/DSpace-Docker-Images/documentation/workshop/info.html_
