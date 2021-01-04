---
title: Run Simple Linux Commands in Docker
nextpage: run.apache.html
---

{% include nav.html %}

When a docker image is **run**, it becomes a **docker container**.  

Containers can be started, stopped, and deleted using the **docker** command.

## Docker Command
[docker command line reference](https://docs.docker.com/engine/reference/commandline/docker/)

```
docker help
```

## Run an ubuntu container

```
docker run --rm ubuntu echo 'hello'
```

In this command, the **ubuntu** image is being run.  

The **entrypoint** for the ubuntu image is being overridden with the command **echo hello**.

The `--rm` option tells docker to delete the container when the process termintes.  By default, stopped containers are not deleted.  It is possible to view their log files.  In most examples in this tutorial, we will utilize the --rm option.

## Run ubuntu with the default entrypoint

The default entrypoint for ubuntu is to run a shell. 

```
docker run --rm ubuntu 
```

Note that nothing happened.  A shell requires a terminal.  You must indicate to docker to provide a terminal.

```
docker run -it --rm ubuntu 
```

You can issue commands in your your container.
```container
root@91abfa3e77ba:/# ls
bin  boot  dev  etc  home  lib  lib32  lib64  libx32  media  mnt  opt  proc  root  run  sbin  srv  sys  tmp  usr  var
root@91abfa3e77ba:/# hostname
91abfa3e77ba
root@91abfa3e77ba:/# uname -a
Linux 91abfa3e77ba 5.4.39-linuxkit #1 SMP Fri May 8 23:03:06 UTC 2020 x86_64 x86_64 x86_64 GNU/Linux
```

Type `exit` to terminate your container.

When you wish to network your containers, you can assign names to the containers to make them easy to locate.

```
docker run -it --rm --name mycontainer ubuntu 
```

Browse the /tmp directory, note that it is empty

```container
root@8bb9eb68e3e9:/# ls /tmp
root@8bb9eb68e3e9:/# 
```

The *docker ps* command can be used to see your running containers.

In a *new console window*, run 

```
docker ps -a
```

Note the results.  You can refer to your running container by the container id or by the name you assigned to the container.
```output
CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS              PORTS               NAMES
8bb9eb68e3e9        ubuntu              "/bin/bash"         2 minutes ago       Up 2 minutes                            mycontainer
```

## The *docker exec* lets you connect to a running container.

Run docker exec to attach to your container
```
docker exec -it mycontainer bash
```

From this new terminal, create a file
``````container
root@8bb9eb68e3e9:/# echo "hello from docker exec" > /tmp/hello.txt
root@8bb9eb68e3e9:/# ls /tmp
hello.txt
```

Return to your original terminal.  From there, run `ls /tmp`
```output
root@8bb9eb68e3e9:/# ls /tmp
hello.txt
```

Type `exit` to terminate your container.

Note that the container is no longer running.

```
docker ps -a
```

Result
```output
CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS              PORTS               NAMES
```

Restart your container
```
docker run -it --rm --name mycontainer ubuntu 
```

Note that when a new container is created, the /tmp directory is empty.

```container
root@c5eb44f52795:/# ls /tmp
root@c5eb44f52795:/# 
```

Type `exit` to terminate your container.

## Stopping and starting a container without --rm

Restart your container without --rm
```
docker run -it --name mycontainer ubuntu 
```

Create a test file
```output
root@f3c626708043:/# echo "hello" > /tmp/new_hello.txt
```

Type `exit` to terminate your container.

Run *docker ps* to view your containers.  
```
docker ps -a
```

Note that *mycontainer* is stopped.

```output
CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS                      PORTS               NAMES
f3c626708043        ubuntu              "/bin/bash"         39 seconds ago      Exited (0) 17 seconds ago                       mycontainer
```

Run *docker start* to restart your container.
```
docker start mycontainer
```

Run *docker ps* to view your containers.  
```
docker ps -a
```

Note that *mycontainer* is restarted.
```output
CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS              PORTS               NAMES
f3c626708043        ubuntu              "/bin/bash"         3 minutes ago       Up 2 seconds                            mycontainer
```

Run *docker exec* to reconnect to your container.  
```
docker exec -it mycontainer bash
```


List the contents of /tmp.  Note that the test file is still present.

```container
root@f3c626708043:/# ls /tmp
new_hello.txt
```

Type `exit` to terminate your exec session.

Run *docker ps* to view your containers.  
```
docker ps -a
```

Note that *mycontainer* is still running.
```output
CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS              PORTS               NAMES
f3c626708043        ubuntu              "/bin/bash"         3 minutes ago       Up 2 seconds                            mycontainer
```

Run *docker stop* to stop the container.
```
docker stop mycontainer
```

Run *docker ps* to view your containers.  
```
docker ps -a
```

Note that *mycontainer* is now stopped.
```output
CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS                     PORTS               NAMES
f3c626708043        ubuntu              "/bin/bash"         6 minutes ago       Exited (0) 3 seconds ago                       mycontainer
```

Run *docker rm* to delete the container.
```
docker rm mycontainer
```

Run *docker ps* to view your containers.  
```
docker ps -a
```

Note that *mycontainer* is no longer present.
```output
CONTAINER ID        IMAGE               COMMAND             CREATED             STATUS              PORTS               NAMES
```

While base images such as ubuntu can be used to solve many problems, docker provides a convenient way to run pre-built application containers.

{% include next.html %}