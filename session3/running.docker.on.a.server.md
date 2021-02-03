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
docker run --rm -it -v "$(pwd)/examples/session3/hello/:/code" python:2 python /code/hello.py 
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
docker run --rm -it -v "$(pwd)/examples/session3/hello/:/code" ruby:2.1 ruby /code/hello.rb 
```

```output
hello world
```

## Run any version of Java

Run the latest version of java

```
docker run --rm -it openjdk javac -version
```

```output
javac 15.0.2
```

Run an old version of java
```
docker run --rm -it openjdk:8 javac -version
```

```output
javac 1.8.0_275
```

### Compile and run a java program

[examples/session3/hello/java/Hello.java](https://github.com/CDLUC3/docker-tutorial/blob/main/examples/session3/hello/java/Hello.java)

Compile a program - note that the class file is saved to your local volume mount
```
docker run --rm -it -v "$(pwd)/examples/session3/hello:/code" openjdk:8 javac /code/java/Hello.java
```

Run a compiled program - note that the class file is saved to your local volume mount
```
docker run --rm -it -v "$(pwd)/examples/session3/hello:/code" openjdk:8 java -cp /code/java Hello
```

```output
Hello world
```

### Compile and run a java program using a Java 15 Feature

- [Java 15 - Text Blocks](https://openjdk.java.net/jeps/355)
- [examples/session3/hello/java/HelloWithTextBlock.java](https://github.com/CDLUC3/docker-tutorial/blob/main/examples/session3/hello/java/HelloWithTextBlock.java)

#### Compile with Java 8
```
docker run --rm -it -v "$(pwd)/examples/session3/hello:/code" openjdk:8 javac /code/java/HelloWithTextBlock.java
```

```output
/code/java/HelloWithTextBlock.java:3: error: unclosed string literal
        String str = """
                       ^
/code/java/HelloWithTextBlock.java:4: error: not a statement
              Hello, world. 
              ^
/code/java/HelloWithTextBlock.java:4: error: ';' expected
              Hello, world. 
                   ^
/code/java/HelloWithTextBlock.java:5: error: ';' expected
              This string has been generated from inside of a Java 13 Text Block 
                         ^
/code/java/HelloWithTextBlock.java:5: error: ';' expected
              This string has been generated from inside of a Java 13 Text Block 
                                  ^
/code/java/HelloWithTextBlock.java:5: error: ';' expected
              This string has been generated from inside of a Java 13 Text Block 
                                                 ^
/code/java/HelloWithTextBlock.java:5: error: ';' expected
              This string has been generated from inside of a Java 13 Text Block 
                                                           ^
/code/java/HelloWithTextBlock.java:5: error: ';' expected
              This string has been generated from inside of a Java 13 Text Block 
                                                                  ^
/code/java/HelloWithTextBlock.java:5: error: ';' expected
              This string has been generated from inside of a Java 13 Text Block 
                                                                                ^
/code/java/HelloWithTextBlock.java:6: error: not a statement
              What do you think of the new feature?
              ^
/code/java/HelloWithTextBlock.java:6: error: ';' expected
              What do you think of the new feature?
                  ^
/code/java/HelloWithTextBlock.java:6: error: ';' expected
              What do you think of the new feature?
                               ^
/code/java/HelloWithTextBlock.java:6: error: variable declaration not allowed here
              What do you think of the new feature?
                          ^
/code/java/HelloWithTextBlock.java:6: error: '(' expected
              What do you think of the new feature?
                                  ^
/code/java/HelloWithTextBlock.java:6: error: '(' or '[' expected
              What do you think of the new feature?
                                                  ^
/code/java/HelloWithTextBlock.java:7: error: unclosed string literal
              """;
                ^
/code/java/HelloWithTextBlock.java:7: error: ')' expected
              """;
                  ^
/code/java/HelloWithTextBlock.java:8: error: ';' expected
        System.out.println(str);
              ^
18 errors
```

#### Compile/Run with Java 15

Compile with java 15
```
docker run --rm -it -v "$(pwd)/examples/session3/hello:/code" openjdk:15 javac /code/java/HelloWithTextBlock.java
```

Run with java 15
```
docker run --rm -it -v "$(pwd)/examples/session3/hello:/code" openjdk:15 java -cp /code/java HelloWithTextBlock
```

```output
Hello, world.
This string has been generated from inside of a Java 15 Text Block
What do you think of the new feature?
```

{% include next.html %}
