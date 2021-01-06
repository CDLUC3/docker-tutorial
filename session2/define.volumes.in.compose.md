---
title: "Define Docker Volumes in Docker Compose"
nextpage: project.configurations
---

{% include nav.html %}

The [mysql image README on dockerhub](https://hub.docker.com/_/mysql) describes how to persist database content in a docker volume. 

For this purpose, we will create a docker volume to store the contents of the container directory `/var/lib/mysql`.

First, we will define our volume globally in the docker-compose file
```yaml
volumes:
  db-volume:
```

Next, we will mount that volume into the the my-db container
```yaml
  mydb:
    volumes:
    - db-volume:/var/lib/mysql
```

See the following file
- [examples/session2/servers-with-volume.yml](https://github.com/CDLUC3/docker-tutorial/blob/main/examples/session2/servers-with-volume.yml)


{% include next.html %}