---
title: "Re-create Containers - What Persists?"
nextpage: docker.volumes
---

{% include nav.html %}

_This section presumes that you have a running stack of containers created in the [prior section](start-stack)._

## Restart the Containers - Data Persists!

Stop the services
```
docker-compose \
  -f examples/session2/servers.yml \
  -f examples/session2/loader.yml \
  stop
```

```output
Stopping rubyserver ... done
Stopping mydb       ... done
```

Start the services
```
docker-compose \
  -f examples/session2/servers.yml \
  -f examples/session2/loader.yml \
  start
```

Disregard any errors with the data-load service
```output
Starting mydb       ... done
Starting data-load  ... failed
Starting rubyserver ... done
```

View the contents of the database from the webserver.  Note that **9** users still exist.
- [http://localhost:4567/listing](http://localhost:4567/listing)

## Re-create the Containers - Data does NOT persist

Bring down the stack of services
```
docker-compose \
  -f examples/session2/servers.yml \
  -f examples/session2/loader.yml \
  down
```

```output
Stopping rubyserver ... done
Stopping mydb       ... done
Removing session2_data-load_run_ac2942bca619 ... done
Removing session2_data-load_run_fcb31794012b ... done
Removing rubyserver                          ... done
Removing mydb                                ... done
Removing network session2_mynet
```

## Re-create the database and the ruby server
```
docker-compose \
  -f examples/session2/servers.yml \
  up -d
```

```output
Creating network "session2_mynet" with the default driver
Creating mydb ... done
Creating rubyserver ... done
```

View the contents of the database from the webserver.  Note that **0** users exist.
- [http://localhost:4567/listing](http://localhost:4567/listing)

In the next section we will discuss how to persist content created inside a docker container.

{% include next.html %}