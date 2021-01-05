---
title: Run Java in Docker
nextpage: run.ruby
---

{% include nav.html %}

In this exercise, we will write a java container that will insert data into the MySql container.

## Create a Docker Netowrk
In order for the java container to find the mysql container they will both need to be run in the same docker network.  
```
docker network create mynet
```

To view the network you created, run the following
```
docker network ls
```

## Run the mysql container we built in the prior exercise within the docker network
```
docker run --rm --name mydb -d --network mynet my-mysql
```

To confirm that the database has started properly, run the following
```
docker logs -f mydb
```

Once you see `/usr/sbin/mysqld: ready for connections.`, press **Cntl-C** to exit the database logs.
```output
2021-01-03T00:48:06.136441Z 0 [System] [MY-010931] [Server] /usr/sbin/mysqld: ready for connections. Version: '8.0.22'  socket: '/var/run/mysqld/mysqld.sock'  port: 3306  MySQL Community Server - GPL.
```

## Sample Test Data

The following CSV file contains sample data that will be inserted into our database.

- [examples/session1/java/test.csv](https://github.com/CDLUC3/docker-tutorial/blob/main/examples/session1/java/test.csv)

Sample data notes
- Note that each user can have 0 or more phone numbers.  
  - Users can share a phone number such as a land line.  
- Each user can have 0 or more email addresses.  
  - Users cannot share email addresses.

## Sample Java Program Bundled into a Container

The following is a very simple program that parses a CSV file and loads data into our database schema.

- [examples/session1/java/src/main/java/org/cdluc3/IngestCLI.java](https://github.com/CDLUC3/docker-tutorial/blob/main/examples/session1/java/src/main/java/org/cdluc3/IngestCLI.java)

The following maven pom file defines the mysql dependency.
- [examples/session1/java/pom.xml](https://github.com/CDLUC3/docker-tutorial/blob/main/examples/session1/java/pom.xml)

The following Dockerfile uses maven to build a directory containing the compiled java application (as a jar) along with all jar file dependencies.
- [examples/session1/java/Dockerfile](https://github.com/CDLUC3/docker-tutorial/blob/main/examples/session1/java/Dockerfile)

This Dockerfile is a **multi-stage** Dockerfile.  
- The first stage of the Dockerfile uses maven (maven:3-jdk-8) to compile java code and assemble assets in an install directory.
- The second stage of the Dockerfile uses the java jre (openjdk:8) to run the java code.  
  - At container runtime, maven is no longer needed so it is not a part of the final image that is assembled.

## Build the java container
```
docker build -t my-java examples/session1/java
```

## Run the java container to insert data into the database

This container will be run in the same network that was used to run the database container.

By default the program looks for a file named test.csv.

A local file (the test.csv file) will be bound into the file system of the running container.  

```
docker run --rm --name java --network mynet -v "$(pwd)/examples/session1/java/test.csv:/tmp/test.csv" my-java 
```

Any additional parameters added to the end of the docker command are passed as parameters to the java program.
```java
    public static final void main(String[] argv) {
        String file = argv.length > 0 ? argv[0] : "test.csv";
        System.out.println(String.format("Import the contents of [%s]", file));
        IngestCLI cli = new IngestCLI();
        cli.processInput(file);
    }
```

Try the following
```
docker run --rm --name java --network mynet -v "$(pwd)/examples/session1/java/test.csv:/tmp/test.csv" my-java simpsons.csv
```

Note the following output
```output
Import the contents of [simpsons.csv]
java.io.FileNotFoundException: simpsons.csv (No such file or directory)
```

If we change the name of the bind mount target, the file will be read as simpsons.csv. 
```
docker run --rm --name java --network mynet -v "$(pwd)/examples/session1/java/test.csv:/tmp/simpsons.csv" my-java simpsons.csv
```

{% include next.html %}
