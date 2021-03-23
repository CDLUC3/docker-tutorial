# Use the mysql base image
FROM mysql

# For this lesson, we are not worried about creating a secure instance
ENV MYSQL_USER=user
ENV MYSQL_PASSWORD=password
ENV MYSQL_ROOT_PASSWORD=password
ENV MYSQL_DATABASE=userdb

# Copy an initialization script into the image to build tables
COPY init.sql /docker-entrypoint-initdb.d/start.sql

# Set the timezone base on our target audience
# Mar 2021 - the following is no longer working
# ENV TZ=America/Los_Angeles
# RUN ln -snf /usr/share/zoneinfo/$TZ /etc/localtime && echo $TZ > /etc/timezone

# Expose port 3306 to make the database accessible to other docker containers
EXPOSE 3306 33060

# Run mysql using utf8
CMD ["mysqld", "--character-set-server=utf8mb4", "--collation-server=utf8mb4_unicode_ci"]
