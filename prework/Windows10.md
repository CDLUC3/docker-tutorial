---
title: Windows 10 setup for Docker 
nextpage: VerifyInstall
---
{% include nav.html %}

## Install Docker Desktop

[https://docs.docker.com/docker-for-windows/install/](https://docs.docker.com/docker-for-windows/install/)

## Install Git Bash as a terminal for running Docker
_This shell most closely mirrors the user experience for MacOS and Linux users_

[https://git-scm.com/downloads](https://git-scm.com/downloads)

Run the following command to verify your install
```shell
  git version
```

## Note for Windows users running docker commands

- When running a Docker command that requires terminal interaction, prefix your docker command with `winpty`
- When referencing a fully qualified path or the root directory of a docker container, use `//` instead of `/`.  
  - Example use `//tmp` for `/tmp`

## Verify Running Docker from your Terminal

```shell
winpty docker run -it --rm ubuntu //bin/bash
```

Enter `echo hello` then enter `exit`

```container
root@653eeaeb274b:/# echo hello
hello
root@653eeaeb274b:/# exit
exit
```

{% include next.html %}
