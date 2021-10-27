# Build and install dependencies with maven.
FROM maven:3-jdk-8 as build

WORKDIR /tmp

# First download as many pom dependencies as possible regardless of changes to the java code
# This will expedite subsequent builds that are run
# https://medium.com/@nieldw/caching-maven-dependencies-in-a-docker-build-dca6ca7ad612
COPY pom.xml /tmp/
RUN mvn dependency:go-offline

# Now add the java code
ADD . /tmp/

# The maven install rule (in pom.xml) copies all dependent jar files into the target directory.
# Mvn clean will delete all temporary .class files from the build directory.  This makes the final image smaller.
RUN mkdir appdir && \
    mvn install && \
    cp target/*.jar appdir && \
    mvn clean

# This is a multi stage docker build.  Maven is not needed once the build is complete.
# Copy generated jar files into a working directory and run with a java jre.
FROM openjdk:8

WORKDIR /tmp

COPY --from=build /tmp/appdir/*.jar /tmp/

# The maven build bundles all dependency names into the manifest of the generated jar file.
# Run "ls /tmp" to see the set of runtime jars that are included.

# The following stopped working...
# ENTRYPOINT [ "java", "-jar", "IngestDemo-1.0.jar" ] 

# Set classpath explicitly
ENTRYPOINT [ "java", "-cp", "mysql-connector-java-8.0.22.jar:IngestDemo-1.0.jar", "org.cdluc3.IngestCLI" ] 
