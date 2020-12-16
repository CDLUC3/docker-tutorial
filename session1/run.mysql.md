---
title: Run MySql in Docker
nextpage: 
---

```
docker run --rm --name mydb -e MYSQL_ROOT_PASSWORD=password -d mysql
```

```
docker logs -f mydb
```

```output
2020-12-16 21:44:51+00:00 [Note] [Entrypoint]: Temporary server started.
```


```
docker exec -it mydb mysql --version
```

```output
mysql  Ver 8.0.22 for Linux on x86_64 (MySQL Community Server - GPL)
```

```
docker stop mydb
```

## Run a different version of mysql
[Available MySql Tags on GitHub](https://hub.docker.com/_/mysql?tab=tags&page=1&ordering=last_updated)

```
docker run --rm --name mydb -e MYSQL_ROOT_PASSWORD=password -d mysql:5.7
```

```
docker exec -it mydb mysql --version
```

```output
mysql  Ver 14.14 Distrib 5.7.31, for Linux (x86_64) using  EditLine wrapper
```

## Connect to contailer using mysql cli

```
docker exec -it mydb mysql -uroot --password=password
```


- Run mysql
  - Connect to mysql container from local mysql cli (if available)
  - Use docker exec to run mysql
  - Create database table and content
- Run apache
  - Connect from browser
  - Use bind volume to serve files
- Create Dockerfile/container to customize database and load default content
- Create Dockerfile/java container to ingest content from a CSV
- Create Dockerfile/ruby/puma container to read from the database and display content on a web page

{% include nav.html %}
