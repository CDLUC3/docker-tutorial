---
title: Verify Install 
nextpage: ../session1
---
{% include nav.html %}

## Verify Running Docker from your Terminal

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
