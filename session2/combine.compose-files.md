---
title: "Combine Compose Files"
nextpage: what.persists
---

{% include nav.html %}

In the prior example, we had 2 components that ran as servers: MySql and the Ruby Server.

The Java Data Load program is an initialization task.

Our stack would be more flexible if we were to break separate these into 2 different components.

## Component Files

These 2 compose files comprise the system that we have been testing.
- [examples/session2/servers.yml](https://github.com/CDLUC3/docker-tutorial/blob/main/examples/session2/servers.yml)
- [examples/session2/loader.yml](https://github.com/CDLUC3/docker-tutorial/blob/main/examples/session2/loader.yml)

## Run docker-compose config to view a combined configuration file:

```
docker-compose \
  -f examples/session2/servers.yml \
  -f examples/session2/loader.yml \
  config
```

Note that all 3 containers are defined in the combined configuration

```
docker-compose \
  -f examples/session2/servers.yml \
  -f examples/session2/loader.yml \
  config | egrep 'image:'
```

```output
    image: my-java
    image: my-mysql
    image: myruby
```

It is possible to overlay configuration overrides for specific containers.
- [examples/session2/environment.yml](https://github.com/CDLUC3/docker-tutorial/blob/main/examples/session2/environment.yml)
- [examples/session2/environment-override.yml](https://github.com/CDLUC3/docker-tutorial/blob/main/examples/session2/environment-override.yml)

Include **environment.yml**
```
docker-compose \
  -f examples/session2/servers.yml \
  -f examples/session2/loader.yml \
  -f examples/session2/environment.yml \
  config 
```

Search for HELLO/HOWDY in the combined config
```
docker-compose \
  -f examples/session2/servers.yml \
  -f examples/session2/loader.yml \
  -f examples/session2/environment.yml \
  config | egrep 'HELLO|HOWDY'
```

Note that the environment variable HELLO has been added to each container
```output
      HELLO: sample message for data-load
      HELLO: sample message for mydb
      HELLO: sample message for ruby server
```

Add environment-override.yml to the configuration
```
docker-compose \
  -f examples/session2/servers.yml \
  -f examples/session2/loader.yml \
  -f examples/session2/environment.yml \
  -f examples/session2/environment-override.yml \
  config | egrep 'HELLO|HOWDY'
```

Note that the values have been overridden for HELLO and HOWDY for mydb
```
      HELLO: sample message for data-load
      HELLO: ovverride value for mydb
      HOWDY: new value for mydb
      HELLO: sample message for ruby server
```

Switch the order of environment.yml and environment-override.yml.  
```
docker-compose \
  -f examples/session2/servers.yml \
  -f examples/session2/loader.yml \
  -f examples/session2/environment-override.yml \
  -f examples/session2/environment.yml \
  config | egrep 'HELLO|HOWDY'
```

Note that the last value for HELLO is retained.
```output
      HELLO: sample message for data-load
      HELLO: sample message for mydb
      HOWDY: new value for mydb
      HELLO: sample message for ruby server
```

When multiple compose files are combined, the results are MERGED.  

For hash objects like environment variables, values can be overridden, but keys cannot be removed when combining properties.

## Use component files to create our stack

Start the server components
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

Verify that the server initialization is complete
```
docker-compose \
  -f examples/session2/servers.yml \
  logs -f
```

Look for the following
```output
mydb          | 2021-01-06T01:27:54.243318Z 0 [System] [MY-010931] [Server] /usr/sbin/mysqld: ready for connections. Version: '8.0.22'  socket: '/var/run/mysqld/mysqld.sock'  port: 3306  MySQL Community Server - GPL.
```

and

```output
rubyserver    | [2021-01-06 01:27:41] INFO  WEBrick::HTTPServer#start: pid=1 port=4567
```

View the contents of the database from the webserver.  Note that the list is empty.
- http://localhost:4567/listing


Now run the loader to load the [simpsons.csv](https://github.com/CDLUC3/docker-tutorial/blob/main/examples/session2/simpsons.csv)

```
docker-compose \
  -f examples/session2/servers.yml \
  -f examples/session2/loader.yml \
  run data-load simpsons.csv
```

```output
Creating session2_data-load_run ... done
Import the contents of [simpsons.csv]
    1        Homer         Simpson                homer@powerplant.com  555-111-2222
    2        Marge         Simpson                       marge@aol.com  555-111-2222
    2        Marge         Simpson                     marge@yahoo.com  555-323-2222
    3         Bart         Simpson                bart@springfield.edu            
    4         Lisa         Simpson                lisa@springfield.edu            
    5       Maggie         Simpson                                                
```

View the contents of the database from the webserver.  Note that **5** users exist.
- http://localhost:4567/listing

Now run the loader to load the [flanders.csv](https://github.com/CDLUC3/docker-tutorial/blob/main/examples/session2/flanders.csv)

```
docker-compose \
  -f examples/session2/servers.yml \
  -f examples/session2/loader.yml \
  run data-load flanders.csv
```

```output
Creating session2_data-load_run ... done
Import the contents of [flanders.csv]
    6          Ned        Flanders                       ned@yahoo.com  555-333-1111
    7        Maude        Flanders                     maude@yahoo.com  555-333-1111
    8         Rodd        Flanders                                                
    9         Todd        Flanders        
```

View the contents of the database from the webserver.  Note that **9** users exist.
- http://localhost:4567/listing


{% include next.html %}