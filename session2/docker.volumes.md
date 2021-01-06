---
title: "Persist Content with Docker Volumes"
nextpage: project.configurations
---

{% include nav.html %}

Docker containers are intended to be destroyed and re-created frequently.  Unlike a server instance which patched and maintained, a docker container can be destroyed and re-created quickly from a built docker image.

When designing a system comprised of docker files, it is important to identify what data requires persistence.  That data can be persisted using **docker volumes**.

In the course of working through these exercises, some unnamed docker volumes have likely been created.

List your docker volumes
```
docker volume ls
```

You may have several volumes identified only by a hash code
```output
DRIVER    VOLUME NAME
local     0b7fe6a770a088ee1a848d011a12438b0088b388979bb53d476efdc7044656d3
...
local     0c77eac6d97e772f5ee280624afdaf6fe937c8321f36e293d02a872ac935b7ff
```

You can also create a named volume
```
docker volume create test-example
```

```output
test-example
```

You can verify that this volume exists
```
docker volume ls |grep test-example
```

```output
local     test-example
```

## Add content to a docker volume

Mount our volume as /data.  Note that it is empty.
```
docker run --rm -it -v "test-example:/data" ubuntu ls /data
```

Output -- Empty
```output
```

Create some content within the volume.
```
docker run --rm -it -v "test-example:/data" ubuntu touch /data/test1.txt
docker run --rm -it -v "test-example:/data" ubuntu touch /data/test2.txt
```

Start a new container and list the contents of the volume
```
docker run --rm -it -v "test-example:/data" ubuntu ls /data
```

```output
test1.txt  test2.txt
```

Create a new volume
```
docker volume create test-example-new
```

Mount that volume instead
```
docker run --rm -it -v "test-example-new:/data" ubuntu ls /data
```

Empty output
```output
```

Re-mount to the original volume
```
docker run --rm -it -v "test-example:/data" ubuntu ls /data
```

```output
test1.txt  test2.txt
```

You can remove these 2 volume2
```
docker volume rm test-example test-example-new
```

```output
test-example
test-example-new
```

## What is a Docker Volume?

A docker volume is a blob of storage that is managed by docker.

Docker volumes can be accessed by one or more containers.

Docker volumes can be defined in a docker-compose file.

Docker volumes persist even after a stack has been destroyed.

For our sample application, we will persist the MySql database content into a volume.




{% include next.html %}