---
title: Run Ruby in Docker
nextpage: cleanup
---

{% include nav.html %}

## Sample Ruby App that Reads Mysql

- [Sinatra Controller: examples/session1/ruby/app.rb](https://github.com/CDLUC3/docker-tutorial/blob/main/examples/session1/ruby/app.rb)
  - [Home Page View: examples/session1/ruby/views/index.erb](https://github.com/CDLUC3/docker-tutorial/blob/main/examples/session1/ruby/views/index.erb)
  - [Listing Page View: examples/session1/ruby/views/listing.erb](https://github.com/CDLUC3/docker-tutorial/blob/main/examples/session1/ruby/views/listing.erb)
- [Ruby Dependencies: examples/session1/ruby/Gemfile](https://github.com/CDLUC3/docker-tutorial/blob/main/examples/session1/ruby/Gemfile)

Dockerfile that builds the Ruby App
- [Sinatra Controller: examples/session1/ruby/Dockerfile](https://github.com/CDLUC3/docker-tutorial/blob/main/examples/session1/ruby/Dockerfile)

## Build the Ruby Image

```
docker build -t myruby examples/session1/ruby/
```

## Run the Ruby Container

```
docker run --rm -it -p 4567:4567 --network mynet --name rubyserver myruby
```

## Open a web page for the application

- [Sample Ruby App](http://localhost:4567)
- Navigate to the listing page
- Note that the users are present in the table

Press `Cntl-C` to exit the container.


{% include next.html %}
