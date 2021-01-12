---
title: Cleanup Resources
nextpage: ../session2
---

{% include nav.html %}

## Stop and delete any running containers

```
docker ps -a
```

Note the names of any remaining containers
```output
CONTAINER ID   IMAGE      COMMAND                  CREATED       STATUS       PORTS                 NAMES
cd048ee5861a   my-mysql   "docker-entrypoint.s…"   2 hours ago   Up 2 hours   3306/tcp, 33060/tcp   mydb
```

```
docker stop mydb
```

If the containers were not started with `--rm`, then run the following to remove them
```
docker rm mydb
```

## Delete the docker network
```
docker network ls
```

Look for `mynet`.  
```output
NETWORK ID     NAME                         DRIVER    SCOPE
71e8bf90299   mynet                        bridge    local
```

If mynet exists, delete it
```
docker network rm mynet
```

{% include next.html %}
