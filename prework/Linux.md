---
title: Linux Desktop setup for Docker 
---
{% include nav.html %}

## Install Git

_Instructions will vary based on your Linux distribution._
_These instructions have not been tested.  Please report your results with these instructions._

### CentOS

```shell
sudo yum install git
```
### Debian, Ubuntu

```shell
sudo apt-get install git
```

### Fedora

```shell
sudo dnf install git
```

## Install Docker CE for your Linux distribution

Install both docker and docker-compose.

Start with the following instructions.  Reach out for assistance.

https://docs.docker.com/engine/install/

## Verify Running Docker from your Terminal

```shell
winpty docker run -it --rm ubuntu /bin/bash
```

Enter `echo hello` then enter `exit`

```
root@653eeaeb274b:/# echo hello
hello
root@653eeaeb274b:/# exit
exit
```

## Verify Docker Compose

```
docker-compose version
```
