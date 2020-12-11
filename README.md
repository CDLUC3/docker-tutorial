# Docker Tutorial for UC3 Developers
This will consist of three 90 minute sessions covering various topics in the use of Docker.  The sessions will start in late Jan 2021.

UC3 staff use MySql, Java, and Ruby, so the sample docker files will use these technologies.

## Pre Work

### Install docker and docker-compose
_Please complete the following before the first session.  Reach out for help if the installation steps do not work for you._
- [Windows 10 Setup](prework/Windows10.md)
- [MacOS Setup](prework/MacOS.md)
- [Linux Setup](prework/Linux.md)

### Clone this repository
```
git clone https://github.com/CDLUC3/docker-tutorial.git
```

## Session 1: Docker Images and Containers
Execute code using the docker command.  Use docker command line ags.

[Session 1](session1/)

## Session 2: Orchestrate sample containers with docker-compose
Hands-on orchestration with docker-compose.  Discussion of other orchestration options.

[Session 2](session2/)

## Session 3: Using Docker in common tools
Using Docker Containters in existing tools and services

[Session 3](session3/)

## Essential Resources
- [docker command line reference](https://docs.docker.com/engine/reference/commandline/docker/)
- [docker-compose commend line reference](https://docs.docker.com/compose/reference/overview/)
- [docker hub -- image registry for UC3](https://hub.docker.com/u/cdluc3)

### Additional Reading
- [Amazon Elastic Container Registry](https://aws.amazon.com/ecr/)
- [Amazon Elastic Container Service](https://aws.amazon.com/ecs/)
- [Kubernetes](https://kubernetes.io/)
- [GitHub Packages / Container Registry](https://docs.github.com/en/free-pro-team@latest/packages/guides/about-github-container-registry)  
- [GitHub Actions / Docker Containers](https://docs.github.com/en/free-pro-team@latest/actions/creating-actions/creating-a-docker-container-action)
- [Deploy Lambda as Docker Container -- Ruby Example](https://docs.aws.amazon.com/lambda/latest/dg/ruby-image.html)

## Notes
_This site reuses content from https://dspace-labs.github.io/DSpace-Docker-Images/documentation/workshop/info.html_
