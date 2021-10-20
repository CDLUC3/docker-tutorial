---
title: "Combine Compose Files"
nextpage: start-stack
---

{% include nav.html %}

In the prior example, we had 2 components that ran as servers: MySql and the Ruby Server.

The Java Data Load program is an initialization task.

Our stack would be more flexible if we were separate these components into 2 different files.

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
      HELLO: override value for mydb
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

{% include next.html %}