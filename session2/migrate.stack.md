{% include nav.html %}

# Migrate Stack to Docker Compose

- [examples/session2/docker-compose.yml]()

## Start docker-compose stack

```
docker-compose -f examples/session2/docker-compose.yml build 
```


```
docker-compose -f examples/session2/docker-compose.yml up -d 
```

```output
Creating network "session2_mynet" with the default driver
Creating mydb ... done
Creating data-load  ... done
Creating rubyserver ... done
```

```
docker ps -a
```

```output
CONTAINER ID   IMAGE      COMMAND                  CREATED         STATUS                     PORTS                    NAMES
e69b51f82ba2   myruby     "bundle exec ruby ap…"   8 seconds ago   Up 7 seconds               0.0.0.0:4567->4567/tcp   rubyserver
ea29796aed5d   my-java    "java -jar IngestDem…"   8 seconds ago   Exited (0) 7 seconds ago                            data-load
eb7163622910   my-mysql   "docker-entrypoint.s…"   9 seconds ago   Up 8 seconds               3306/tcp, 33060/tcp      mydb
```

```
docker network ls
```

```output
NETWORK ID     NAME                         DRIVER    SCOPE
c52974406014   session2_mynet               bridge    local
```