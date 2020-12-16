---
title: MacOS setup for Docker
---

{% include nav.html %}


## Install Docker Desktop

[https://docs.docker.com/docker-for-mac/install/](https://docs.docker.com/docker-for-mac/install/)

## Open a terminal

Open terminal (in Applications/Utilities)

## Install Git
In your terminal, type the following command. If git is not yet installed, you will be prompted to install developer tools.  Proceed with the installation.

```shell
git version
```

## Verify Running Docker from your Terminal

```shell
docker run -it --rm ubuntu /bin/bash
```

Enter `echo hello` then enter `exit`

```output
root@653eeaeb274b:/# echo hello
hello
root@653eeaeb274b:/# exit
exit
```

## Verify Docker Compose

```
docker-compose version
```
