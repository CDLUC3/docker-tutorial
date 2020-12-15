{% include nav.html %}

# Run Simple Linux Commands in Docker

When a docker image is **run**, it becomes a **docker container**.  

Containers can be started, stopped, and deleted using the `docker` command.

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

You can ask the container what its hostname is.
```
root@1b2d258d1575:/# hostname
1b2d258d1575
```

When you wish to network your containers, you can assign names to the containers to make them easy to locate.

