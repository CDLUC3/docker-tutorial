---
title: Run Java in Docker
nextpage: run.ruby.html
---
---

{% include nav.html %}

```
docker network create mynet
```

```
docker run --rm --name mydb -d --network mynet my-mysql
```

```
docker build -t my-java .
docker run --rm --name java --network mynet -v "$(pwd)/test.csv:/tmp/test.csv" my-java 
```

{% include next.html %}
