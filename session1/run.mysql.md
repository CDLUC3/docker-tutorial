---
title: Run MySql in Docker
nextpage: run.java
---

{% include nav.html %}

## Start a MySql container in docker
```
docker run --rm --name mydb -e MYSQL_ROOT_PASSWORD=password -d mysql
```

View the log files for the server startup
```
docker logs -f mydb
```

```output
2021-01-12T22:21:15.985343Z 0 [System] [MY-010931] [Server] /usr/sbin/mysqld: ready for connections. Version: '8.0.22'...
```

Press `Cntl-C` to exit the log.


Display the version of the server that is running
```
docker exec -it mydb mysql --version
```

```output
mysql  Ver 8.0.22 for Linux on x86_64 (MySQL Community Server - GPL)
```

Stop the container
```
docker stop mydb
```

## Run a different version of mysql
[Available MySql Tags on GitHub](https://hub.docker.com/_/mysql?tab=tags&page=1&ordering=last_updated)

Start MySql 5.7 using a tagged version of the MySql Image
```
docker run --rm --name mydb -e MYSQL_ROOT_PASSWORD=password -d mysql:5.7
```

Display the version of the server that is running
```
docker exec -it mydb mysql --version
```

```output
mysql  Ver 14.14 Distrib 5.7.31, for Linux (x86_64) using  EditLine wrapper
```

## Connect to container using mysql cli _within_ the container

```
docker exec -it mydb mysql -uroot --password=password -e "select 1, user(), now()"
```

```output
+---+----------------+---------------------+
| 1 | user()         | now()               |
+---+----------------+---------------------+
| 1 | root@localhost | 2020-12-18 23:01:26 |
+---+----------------+---------------------+
```

You can also interacively modify your database server.

```
docker exec -it mydb mysql -uroot --password=password
```

Create a database and a database table.
```sql
create database mydb;
use mydb;
create table users (
  id int, 
  first_name varchar(40), 
  last_name varchar(40)
);
describe users;
```

```container
+------------+-------------+------+-----+---------+-------+
| Field      | Type        | Null | Key | Default | Extra |
+------------+-------------+------+-----+---------+-------+
| id         | int(11)     | YES  |     | NULL    |       |
| first_name | varchar(40) | YES  |     | NULL    |       |
| last_name  | varchar(40) | YES  |     | NULL    |       |
+------------+-------------+------+-----+---------+-------+
3 rows in set (0.02 sec)
```

Type exit to exit the mysql session
```container
mysql> exit
```

Stop the container
```
docker stop mydb
```

## Reflect - What is challenging about the MySql approach so far?

- Lots of specific steps
- Lots of command line parameters
- Difficult to reproduce

## Use a Dockerfile to build your image

A Dockerfile creates a reproduceable definition for a Docker image. ([Dockerfile Reference](https://docs.docker.com/engine/reference/builder/)).

A Dockerfile starts with a base image (such as ubuntu, apache, mysql) upon which you commit layers of changes.

As each layer is applied, Docker can cache the result of the build making it faster to rebuild your image in the future.  The RUN command in a Dockerfile executes a shell command.

Hints:
- place the least volatile parts of your image definition at the top to take advantage of caching
- minimize the number of layers in your dockerfile to keep the image small and fast to build

The following 2 steps 
```dockerfile
RUN echo "woof" > dog.txt
RUN echo "meow" > cat.txt
```

Could be combined as
```dockerfile
RUN echo "woof" > dog.txt && echo "meow" > cat.txt
```

To make this more readable...
```dockerfile
RUN echo "woof" > dog.txt && \
    echo "meow" > cat.txt
```

## Creating a Dockerfile for MySql

The following reference information was used to assemble the Dockerfile that will be used in this lesson.

[MySql Docker Image Referece](https://hub.docker.com/_/mysql)

In particular, check the following
- Environment Variables
- Initializing a Fresh Instance

## Sample files
- [examples/session1/mysql/Dockerfile](https://github.com/CDLUC3/docker-tutorial/blob/main/examples/session1/mysql/Dockerfile)
- [examples/session1/mysql/init.sql](https://github.com/CDLUC3/docker-tutorial/blob/main/examples/session1/mysql/init.sql)

Database Schema Notes
- Note that each user can have 0 or more phone numbers.  
  - Users can share a phone number such as a land line.  
- Each user can have 0 or more email addresses.  
  - Users cannot share email addresses.

## Build the dockerfile

From the root directory of the tutorial, run the following
```
docker build -t my-mysql examples/session1/mysql
```

You will see the following output.  Note that the build contains 10 steps.  

These are the layers added to the dockerfile.
```output
Sending build context to Docker daemon  3.584kB
Step 1/10 : FROM mysql

...

Successfully built 8e5d78c1a034
Successfully tagged my-mysql:latest
```

Run the following to start a container using the image that you built.

```
docker run --rm --name mydb -d my-mysql
```

Run the following command to confirm that the database initialization is complete.

```
docker logs mydb
```

```output
...
2020-12-19T00:45:36.285095Z 0 [System] [MY-010931] [Server] /usr/sbin/mysqld: ready for connections. Version: '8.0.22'  socket: '/var/run/mysqld/mysqld.sock'  port: 3306  MySQL Community Server - GPL.
```

Run the following to confirm that the tables that we defined were added to the database.

```
docker exec -it mydb mysql -u root --password=password -D userdb -e 'show tables;'
```

You should see the following output
```output
mysql: [Warning] Using a password on the command line interface can be insecure.
+------------------+
| Tables_in_userdb |
+------------------+
| email            |
| phone            |
| users            |
+------------------+
```

Stop the container
```
docker stop mydb
```


{% include next.html %}
