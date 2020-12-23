create or replace table users (
  id int NOT NULL AUTO_INCREMENT, 
  first_name varchar(40), 
  last_name varchar(40),
  PRIMARY KEY (id)
);

create or replace table contact (
  user_id int, 
  email varchar(255),
  phone varchar(40),
  FOREIGN KEY (user_id) REFERENCES users(id)
);

GRANT ALL ON *.* to 'user'@'%';
