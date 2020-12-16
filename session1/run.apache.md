---
title: Run Apache in Docker
nextpage: run.mysql.html
--- 

{% include nav.html %}

An [Apache docker image](https://hub.docker.com/_/httpd) is published on Docker Hub.

The following command will start apache.  

The container will run in the foreground and log files will be visible in the terminal.

```
docker run --rm --name apache httpd
```

Hit `Cntl-C` to stop the container.

Add `-d` to run the container in the background.

```
docker run --rm --name apache -d httpd
```

Run *docker ps* to confirm that the container is running.

```
docker ps -a
```

Note the container is running.

```output
CONTAINER ID        IMAGE               COMMAND              CREATED             STATUS              PORTS               NAMES
b2df9372ee88        httpd               "httpd-foreground"   53 seconds ago      Up 52 seconds       80/tcp              apache
```

You can view the logs for the container.

```
docker logs apache
```

The command above will *cat* the log file.

```output
AH00558: httpd: Could not reliably determine the server's fully qualified domain name, using 172.17.0.2. Set the 'ServerName' directive globally to suppress this message
AH00558: httpd: Could not reliably determine the server's fully qualified domain name, using 172.17.0.2. Set the 'ServerName' directive globally to suppress this message
[Wed Dec 16 17:17:24.867258 2020] [mpm_event:notice] [pid 1:tid 140030725198976] AH00489: Apache/2.4.46 (Unix) configured -- resuming normal operations
[Wed Dec 16 17:17:24.868151 2020] [core:notice] [pid 1:tid 140030725198976] AH00094: Command line: 'httpd -D FOREGROUND'
```

Add `-f` to *tail* the log file

```
docker logs -f apache
```

## Attempt to connect to apache.

From a browser: [http://localhost:80](http://localhost:80)

_If you already have a web server running on your machine, you will need to stop that web server or skip the next couple steps._

With curl:
```
curl -v "http://localhost:80"
```

Note that the connection fails
```output
*   Trying 127.0.0.1...
* TCP_NODELAY set
* Connection failed
* connect to 127.0.0.1 port 80 failed: Connection refused
*   Trying ::1...
* TCP_NODELAY set
* Connection failed
* connect to ::1 port 80 failed: Connection refused
* Failed to connect to localhost port 80: Connection refused
* Closing connection 0
curl: (7) Failed to connect to localhost port 80: Connection refused
```

## Expose a container port
In order to access Apache, we need to expose a port from the container to your desktop.

Stop the existing container
```
docker stop apache
```

Restart the container with port 80 from the container mapped to port 80 on your desktop.

Add `-p 80:80` to expose port 80 to your desktop
```
docker run --rm --name apache -d -p 80:80 httpd
```

## Attempt to connect to apache.

From a browser: [http://localhost:80](http://localhost:80) 

_If you already have a web server running on your machine, you will need to stop that web server or skip the next couple steps._

With curl:
```
curl "http://localhost:80"
```

Note the success:
```output
<html><body><h1>It works!</h1></body></html>
```

Run *docker ps*.  Note the port mapping on the container.

```
docker ps -a
```

```output
CONTAINER ID        IMAGE               COMMAND              CREATED              STATUS              PORTS                NAMES
eba7c4d0b827        httpd               "httpd-foreground"   About a minute ago   Up About a minute   0.0.0.0:80->80/tcp   apache
```

Stop the container.
```
docker stop apache
```

## Map container to a different port

Restart the container with port 80 from the container mapped to port 9999 on your desktop.

Add `-p 9999:80` to expose port 80 to port 9999 your desktop
```
docker run --rm --name apache -d -p 9999:80 httpd
```

## Attempt to connect to apache.

From a browser: [http://localhost:9999](http://localhost:9999) 

_If you already have a web server running on your machine, you will need to stop that web server or skip the next couple steps._

With curl:
```
curl "http://localhost:9999"
```

Note the success:
```output
<html><body><h1>It works!</h1></body></html>
```

Run *docker ps*.  Note the port mapping on the container.

```
docker ps -a
```


```output
CONTAINER ID        IMAGE               COMMAND              CREATED             STATUS              PORTS                  NAMES
f63163c0e425        httpd               "httpd-foreground"   39 seconds ago      Up 37 seconds       0.0.0.0:9999->80/tcp   apache
```

## Copy content into the container

The [httpd container documentation](https://hub.docker.com/_/httpd) indicates that files will be served from */usr/local/apache2/htdocs/*.

Copy a sample file *into* the apache container.
```
docker cp examples/session1/copied.txt apache:/usr/local/apache2/htdocs/
```

View the file in a browser: [http://localhost:9999/copied.txt](http://localhost:9999/copied.txt)

View the file with curl
```
curl "http://localhost:9999/copied.txt"
```

```output
File copied into container.
```

Stop the container.
```
docker stop apache
```

## Mount a data volume into the container

In the next example, we will mount a folder of test files into our apache container.

```
ls examples/session1
```

```output
README.md       copied.txt      example1.txt    example2.txt
```

### MacOS/Linux
```
docker run --rm --name apache -d -p 9999:80 -v "$(pwd)/examples/session1:/usr/local/apache2/htdocs/" httpd
```

### Windows - you must reference the root directory of the container with **//**

winpty is not needed since a terminal is not being used.
```
docker run --rm --name apache -d -p 9999:80 -v "$(pwd)/examples/session1://usr/local/apache2/htdocs/" httpd
```

## Use docker exec to verify the directory mount

### MacOS/Linux
```
docker exec -it apache ls /usr/local/apache2/htdocs
```

### Windows
```
winpty docker exec -it apache ls //usr/local/apache2/htdocs
```

```output
README.md  copied.txt  example1.txt  example2.txt
```
## View the following files in a browser or with curl
- [http://localhost:9999/example1.txt](http://localhost:9999/example1.txt)
- [http://localhost:9999/example2.txt](http://localhost:9999/example2.txt)
- [http://localhost:9999/copied.txt](http://localhost:9999/copied.txt)

```
curl "http://localhost:9999/copied.txt"
```

```output
File copied into container.
```

```
curl "http://localhost:9999/example1.txt"
```

```output
Example 1
```

```
curl "http://localhost:9999/example2.txt"
```

```output
Example 2
```

Stop the container.
```
docker stop apache
```

{% include next.html %}

