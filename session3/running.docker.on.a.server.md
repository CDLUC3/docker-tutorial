---
title: "Running Docker on a Server"
nextpage: vscode.and.docker
---

{% include nav.html %}

- Publish container ports to external facing server ports
- For tools that are infrequently needed, you can rely on Docker containers to provide without installing tools on your server.

## Run any version of python regardless of the local server installation

Run the latest version
```
docker run --rm -it python
```

```container
Python 3.9.1 (default, Jan 12 2021, 16:45:25) 
[GCC 8.3.0] on linux
Type "help", "copyright", "credits" or "license" for more information.
>>> exit()
```

Run python 2
```
docker run --rm -it python:2
```

```container
Python 2.7.18 (default, Apr 20 2020, 19:27:10) 
[GCC 8.3.0] on linux2
Type "help", "copyright", "credits" or "license" for more information.
Use exit() or Ctrl-D (i.e. EOF) to exit
>>> exit()
```

### Run a python program

[examples/session3/hello/hello.py](https://github.com/CDLUC3/docker-tutorial/blob/main/examples/session3/hello/hello.py)

Mount a file to pass it to the interpreter
```
docker run --rm -it -v "$(pwd)/examples/session3/hello/hello.py:/tmp/hello.py" python:2 python /tmp/hello.py 
```

```output
hello world
```

## Run any version of ruby

Run the latest version
```
docker run --rm -it ruby
```

```container
irb(main):002:0> RUBY_VERSION
=> "3.0.0"
irb(main):003:0> exit
```

Run an old version
```
docker run --rm -it ruby:2.1
```

```container
iirb(main):001:0> RUBY_VERSION
=> "2.1.10"
irb(main):002:0> exit
```

### Run a ruby program

[examples/session3/hello/hello.rb](https://github.com/CDLUC3/docker-tutorial/blob/main/examples/session3/hello/hello.rb)

```
docker run --rm -it -v "$(pwd)/examples/session3/hello/hello.rb:/tmp/hello.rb" ruby:2.1 ruby /tmp/hello.rb 
```

```output
hello world
```

## Run any version of Java

Run the latest version of java

```
docker run -it openjdk javac -version
```

```output
javac 15.0.2
```

Run an old version of java
```
docker run -it openjdk:8 javac -version
```

```output
javac 1.8.0_275
```

### Compile and run a java program

[examples/session3/hello/java/Hello.java](https://github.com/CDLUC3/docker-tutorial/blob/main/examples/session3/hello/java/Hello.java)

Compile a program - note that the class file is saved to your local volume mount
```
docker run -it -v "$(pwd)/examples/session3/hello/java:/tmp/java" openjdk:8 javac /tmp/java/Hello.java
```

Run a compiled program - note that the class file is saved to your local volume mount
```
docker run -it -v "$(pwd)/examples/session3/hello/java:/tmp/java" openjdk:8 java -cp /tmp/java Hello
```

```output
Hello world
```

{% include next.html %}
