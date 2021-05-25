![build](https://github.com/bytebang/simpleserver/actions/workflows/maven.yml/badge.svg)

# simpleserver
A simple webserver for serving static content

# usage
The server can serve either content which is compiled into the jar file, or it can serve content which comes from an existing directory on the machine.

usually the server just has to be started via commandline

`java -jar simpleserver.jar`

then it will serve the content by default on port 8080. If you want to change the port, then you can do this via the vm parameter _port_ like this:

`java -Dport=9999 -jar simpleserver.jar`

This will serve the content from within the jar file on port 9999.

## serving content from within the jar
The package `at.htlle.simpleserver.staticfiles` in the resources section of the maven project holds the files that should be served statically. Whatever is in this directory will be served by default from the server.

## serving content from a directory
If the data should not be compiled into the jar the it is possible to point the server towards a directory. This is also done via a vm parameter _wwwroot_ like this:

`java -Dwwwroot=/tmp/www -jar simpleserver.jar`

With the commandline from above the server will look for files within the /tmp/www directory (instead of serving content from within the jar)

#compiling

Compilation is done via maven:

```
mvn package
```