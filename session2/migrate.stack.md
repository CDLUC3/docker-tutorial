---
title: "Migrate Sample Stack to Docker Compose"
nextpage: combine.compose-files
---

{% include nav.html %}

# Migrate Stack to Docker Compose

- [examples/session2/docker-compose.yml](https://github.com/CDLUC3/docker-tutorial/blob/main/examples/session2/docker-compose.yml)

## Build docker-compose stack

Build the images (if needed) referenced in the docker compose file.
```
docker-compose -f examples/session2/docker-compose.yml build 
```

Start the containers defined in the compose file
```
docker-compose -f examples/session2/docker-compose.yml up -d 
```

```output
Creating network "session2_mynet" with the default driver
Creating mydb ... done
Creating data-load  ... done
Creating rubyserver ... done
```

Build and Start the containers defined in the compose file in one step
```
docker-compose -f examples/session2/docker-compose.yml up -d --build
```

Verify running containers
```
docker ps -a
```

```output
CONTAINER ID   IMAGE      COMMAND                  CREATED         STATUS                     PORTS                    NAMES
e69b51f82ba2   myruby     "bundle exec ruby ap…"   8 seconds ago   Up 7 seconds               0.0.0.0:4567->4567/tcp   rubyserver
ea29796aed5d   my-java    "java -jar IngestDem…"   8 seconds ago   Exited (0) 7 seconds ago                            data-load
eb7163622910   my-mysql   "docker-entrypoint.s…"   9 seconds ago   Up 8 seconds               3306/tcp, 33060/tcp      mydb
```

Note that **data-load** is in an **Exited** status.  That is because the program execution is complete.  The other containers are services that should stay **Up** until they are explicitly stopped.

Note that a network was also created
```
docker network ls
```

```output
NETWORK ID     NAME                         DRIVER    SCOPE
c52974406014   session2_mynet               bridge    local
```

## Open a web page for the application

- [Sample Ruby App](http://localhost:4567)

## View Log Files

View a single log file
```
docker logs rubyserver
```

```
docker logs data-load
```

Or, use docker-compose to view all log files
```
docker-compose -f examples/session2/docker-compose.yml logs
```

## Stop the container stack

The stop command will stop the containers defined in the docker-compose file.
```
docker-compose -f examples/session2/docker-compose.yml stop
```

```output
Stopping rubyserver ... done
Stopping mydb       ... done
```

Check the status of the containers
```
docker ps -a
```

```output
CONTAINER ID   IMAGE      COMMAND                  CREATED        STATUS                          PORTS     NAMES
9f583d819964   my-java    "java -jar IngestDem…"   41 hours ago   Exited (0) 5 minutes ago                  data-load
06fbadb18594   myruby     "bundle exec ruby ap…"   41 hours ago   Exited (0) About a minute ago             rubyserver
6e90767ff4d8   my-mysql   "docker-entrypoint.s…"   41 hours ago   Exited (0) About a minute ago             mydb
```

## Restart the containers

```
docker-compose -f examples/session2/docker-compose.yml up -d
```

Note that the services are in an up state again.
```
CONTAINER ID   IMAGE      COMMAND                  CREATED        STATUS                     PORTS                    NAMES
9f583d819964   my-java    "java -jar IngestDem…"   41 hours ago   Exited (0) 4 seconds ago                            data-load
06fbadb18594   myruby     "bundle exec ruby ap…"   41 hours ago   Up 7 seconds               0.0.0.0:4567->4567/tcp   rubyserver
6e90767ff4d8   my-mysql   "docker-entrypoint.s…"   41 hours ago   Up 8 seconds               3306/tcp, 33060/tcp      mydb
```

## Stop and delete the containers with the DOWN command

```
docker-compose -f examples/session2/docker-compose.yml down
```

```output
Stopping rubyserver ... done
Stopping mydb       ... done
Removing data-load  ... done
Removing rubyserver ... done
Removing mydb       ... done
Removing network session2_mynet
```

Note that the containers have been deleted.

```
docker ps -a
```

```output
CONTAINER ID   IMAGE      COMMAND                  CREATED        STATUS                     PORTS                    NAMES
```

{% include next.html %}