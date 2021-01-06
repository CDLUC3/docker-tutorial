---
title: "Start Container Stack from Compose Files"
nextpage: what.persists
---

{% include nav.html %}

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
- [http://localhost:4567/listing](http://localhost:4567/listing)


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
- [http://localhost:4567/listing](http://localhost:4567/listing)

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
- [http://localhost:4567/listing](http://localhost:4567/listing)


{% include next.html %}