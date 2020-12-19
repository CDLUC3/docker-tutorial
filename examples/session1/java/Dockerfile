# Build and install dependencies with maven.
FROM maven:3-jdk-8 as build

WORKDIR /tmp

ADD . /tmp

# The maven install rule (in pom.xml) copies all dependent jar files into the target directory.
# Mvn clean will delete all temporary .class files from the build directory.  This makes the final image smaller.
RUN mvn install && \
    mvn clean

# This is a multi stage docker build.  Maven is not needed once the build is complete.
# Copy generated jar files into a working directory and run with a java jre.
FROM openjdk:8

WORKDIR /tmp

COPY --from=build /tmp/target/*.jar /tmp/

# The maven build bundles all dependency names into the manifest of the generated jar file.
# Run "ls /tmp" to see the set of runtime jars that are included.
CMD java -jar IngestDemo-1.0.jar