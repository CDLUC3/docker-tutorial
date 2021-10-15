---
title: Verify Install 
nextpage: ../session1
---
{% include nav.html %}

## Verify Running Docker from your Terminal

### Verify Docker Version
```shell
docker version
```

You should see something like the following output
```output
Client:
 Cloud integration: 1.0.17
 Version:           20.10.8
 API version:       1.41
 Go version:        go1.16.6
 Git commit:        3967b7d
 Built:             Fri Jul 30 19:55:20 2021
 OS/Arch:           darwin/amd64
 Context:           default
 Experimental:      true

Server: Docker Engine - Community
 Engine:
  Version:          20.10.8
  API version:      1.41 (minimum version 1.12)
  Go version:       go1.16.6
  Git commit:       75249d8
  Built:            Fri Jul 30 19:52:10 2021
  OS/Arch:          linux/amd64
  Experimental:     false
 containerd:
  Version:          1.4.9
  GitCommit:        e25210fe30a0a703442421b0f60afac609f950a3
 runc:
  Version:          1.0.1
  GitCommit:        v1.0.1-0-g4144b63
 docker-init:
  Version:          0.19.0
  GitCommit:        de40ad0
```

## Verify Running an interactive docker shell from your terminal

### MacOS/Linux
```shell
docker run -it --rm ubuntu /bin/bash
```

### Windows 10
_Remember that **winpty** is needed to run a docker terminal on Windows.  Remember this throughout the tutorial._

```shell
winpty docker run -it --rm ubuntu //bin/bash
```

## Interact with the Docker Container
Enter `echo hello` then enter `exit`

```container
root@653eeaeb274b:/# echo hello
hello
root@653eeaeb274b:/# exit
exit
```

## Verify Docker Compose

```
docker-compose version
```

## Verify git version

```
git version
```

```output
git version 2.21.0 (Apple Git-122)
```

## Verify git repo

```
git remote -v
```

```output
origin  https://github.com/CDLUC3/docker-tutorial.git (fetch)
origin  https://github.com/CDLUC3/docker-tutorial.git (push)
```

## Check that you are in the root folder for the repo and that exercise files are available
```
ls examples/
```

Verify that example folders are present.
```output
session1        session2        session3
```

{% include next.html %}
