---
title: "Creating Docker Images and Containers Using the docker command"
nextpage: search.dockerhub
---

{% include nav.html %}

## What can I do with Docker?

Docker allows you to run many different applications, operating system shells, and programming language environments from your computure.  This is done by locating a *Docker Image* of interest to you and running that image as a *Docker Container*.

## Sample Docker Images to Get Started

- [Finding a Docker Image](search.dockerhub.md)
- [Run Simple Linux Commands](simple.linux.commands.md)
- [Run Apache in Docker](run.apache.md)
- [Run MySql](run.mysql.md)
- [Run Java](run.java.md)
- [Run Ruby](run.ruby.md)
- [Clean Up Resources](cleanup.md)

## Conventions used in this tutorial

```
This style of box is used for input that should be entered by the user.
```

```output
This style of box is used to display expected terminal output from a command.
```

```container
This style of box is used to represent an interactive session within a docker container.
# sample-prompt >
```

## Notes about the docker command

The first non-option parameter to the docker command is an action.

```
docker ps
```

```
docker start ...
```

Note that **rm** is the action to remove a container.
```
docker rm ...
```

Many options can be specified with a single dash `-` and a single letter
```
docker run -i -t -p 8081:8081 ...
```

Options that take no parameters can be combined
```
docker run -it -p 8081:8081 ...
```

Named parameters are prefixed with a double dash `--`.
```
docker run --name foo ...
```

There is a named parameter `--rm` that indicates that a container should be removed when the container is stopped.
```
docker run --rm ...
```

Note that `docker rm` and `docker run --rm` are different.


{% include next.html %}
