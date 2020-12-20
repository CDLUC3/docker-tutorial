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
docker run --rm --name mydb -p 3306:3306 -d --network mynet my-mysql
```

```

```

{% include next.html %}
