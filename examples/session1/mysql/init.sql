DROP TABLE IF EXISTS users;
CREATE TABLE users (
  id int NOT NULL AUTO_INCREMENT, 
  first_name varchar(40), 
  last_name varchar(40),
  PRIMARY KEY (id)
);

DROP TABLE IF EXISTS phone;
CREATE TABLE phone (
  user_id int, 
  phone varchar(40),
  FOREIGN KEY (user_id) REFERENCES users(id),
  UNIQUE KEY user_phone (user_id, phone)
);

DROP TABLE IF EXISTS email;
CREATE TABLE email (
  user_id int, 
  email varchar(255),
  FOREIGN KEY (user_id) REFERENCES users(id),
  UNIQUE KEY email (email)
);

GRANT ALL ON *.* to 'user'@'%';
