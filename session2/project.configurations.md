---
title: "Docker Compose Project Configurations (-p)"
nextpage: uc3.orchestration.examples
---

{% include nav.html %}

In this exercise, we will run our sample stack twice using 2 different project settings.
- "simpsons" for the simpsons.csv file
- "flanders" for the flanders.csv file

## Start the stack with a project name of "simpsons".

```
docker-compose -p simpsons \
  -f examples/session2/servers-with-volume.yml \
  up -d
```

```output
Creating network "simpsons_mynet" with the default driver
Creating volume "simpsons_db-volume" with default driver
Creating mydb ... done
Creating rubyserver ... done
```

Import the simpsons.csv file
```
docker-compose -p simpsons \
  -f examples/session2/servers.yml \
  -f examples/session2/loader.yml \
  run data-load simpsons.csv
```

View the contents of the database from the webserver.  Note that **5** users exist.
- [http://localhost:4567/listing](http://localhost:4567/listing)

Stop the stack
```
docker-compose -p simpsons \
  -f examples/session2/servers-with-volume.yml \
  down
```

```output
Stopping rubyserver ... done
Stopping mydb       ... done
Removing rubyserver ... done
Removing mydb       ... done
Removing network simpsons_mynet
```

## Start the stack with a project name of "flanders".

```
docker-compose -p flanders \
  -f examples/session2/servers-with-volume.yml \
  up -d
```

```output
Creating network "flanders_mynet" with the default driver
Creating volume "flanders_db-volume" with default driver
Creating mydb ... done
Creating rubyserver ... done
```

Import the flanders.csv file
```
docker-compose -p flanders \
  -f examples/session2/servers.yml \
  -f examples/session2/loader.yml \
  run data-load flanders.csv
```

View the contents of the database from the webserver.  Note that **4** users exist.
- [http://localhost:4567/listing](http://localhost:4567/listing)

Stop the stack
```
docker-compose -p flanders \
  -f examples/session2/servers-with-volume.yml \
  down
```

```output
Stopping rubyserver ... done
Stopping mydb       ... done
Removing rubyserver ... done
Removing mydb       ... done
Removing network flanders_mynet
```

## Note that the volumes are still present

```
docker volume ls | grep 'db-volume'
```

```output
local     flanders_db-volume
local     simpsons_db-volume
```

## Restart each stack with the -p option and verify that the content is preserved

This makes it possible to manage different test environments with different project names.

Restart the stack with the simpsons volume
```
docker-compose -p simpsons \
  -f examples/session2/servers-with-volume.yml \
  up -d
```

[http://localhost:4567/listing](http://localhost:4567/listing)

```
docker-compose -p simpsons \
  -f examples/session2/servers-with-volume.yml \
  down
```

Restart the stack with the flanders volume

```
docker-compose -p flanders \
  -f examples/session2/servers-with-volume.yml \
  up -d
```

[http://localhost:4567/listing](http://localhost:4567/listing)

```
docker-compose -p flanders \
  -f examples/session2/servers-with-volume.yml \
  down
```

## Remove the volumes
```
docker volume rm flanders_db-volume simpsons_db-volume
```

## Could I run both stacks at once?

Not without some work.  

By default, docker compose will prefix network resources, containers and volumes with the project name.

In our docker compose file, we define an explicit container name which overrides this behavior.  This makes it easy to reference a running container without using the prefix.

The use of port 4567 has been hard-coded in our compose file.  You would need to expose that port differently in each running instance. 

{% include next.html %}