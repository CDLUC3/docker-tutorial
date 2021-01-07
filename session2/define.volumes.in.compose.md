---
title: "Define Docker Volumes in Docker Compose"
nextpage: project.configurations
---

{% include nav.html %}

The [mysql image README on dockerhub](https://hub.docker.com/_/mysql) describes how to persist database content in a docker volume. 

For this purpose, we will create a docker volume to store the contents of the container directory `/var/lib/mysql`.

First, we will define our volume globally in the docker-compose file
```yaml
volumes:
  db-volume:
```

Next, we will mount that volume into the the my-db container
```yaml
  mydb:
    volumes:
    - db-volume:/var/lib/mysql
```

See the following file
- [examples/session2/servers-with-volume.yml](https://github.com/CDLUC3/docker-tutorial/blob/main/examples/session2/servers-with-volume.yml)

## Start the service

Start the server components
```
docker-compose \
  -f examples/session2/servers-with-volume.yml \
  up -d
```

Note that a volume is created in addition to the network and the containers
```output
Creating network "session2_mynet" with the default driver
Creating volume "session2_db-volume" with default driver
Creating mydb ... done
Creating rubyserver ... done
```

Verify that the server initialization is complete
```
docker-compose \
  -f examples/session2/servers-with-volume.yml \
  logs -f
```

View the contents of the database from the webserver.  Note that the list is empty.
- [http://localhost:4567/listing](http://localhost:4567/listing)


Now run the loader to load the [simpsons.csv](https://github.com/CDLUC3/docker-tutorial/blob/main/examples/session2/simpsons.csv)

```
docker-compose \
  -f examples/session2/servers-with-volume.yml \
  -f examples/session2/loader.yml \
  run data-load simpsons.csv
```

View the contents of the database from the webserver.  Note that **5** users exist.
- [http://localhost:4567/listing](http://localhost:4567/listing)

## Bring down the stack

```
docker-compose \
  -f examples/session2/servers-with-volume.yml \
  down
```

Note that the containers and the network are deleted.
```output
Stopping rubyserver ... done
Stopping mydb       ... done
Removing rubyserver ... done
Removing mydb       ... done
Removing network session2_mynet
```

Note that the volume still exists
```
docker volume ls | grep 'db-volume'
```

Note that the volume name is prefixed with "session2".  That prefix is known as the **project** name.  By default, the name of the folder containing the docker-compose file is used as a project name.
```output
local     session2_db-volume
```

## Recreate the stack

```
docker-compose \
  -f examples/session2/servers-with-volume.yml \
  up -d
```

View the contents of the database from the webserver.  Note that **5** users exist.
- [http://localhost:4567/listing](http://localhost:4567/listing)


## Clean up resources

Stop the stack
```
docker-compose \
  -f examples/session2/servers-with-volume.yml \
  down
```

Delete the volume as well
```
docker volume rm session2_db-volume
```


In the next section, we will experiment with different **project** settings using this docker-compose file.

{% include next.html %}