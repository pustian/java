CREATE TABLE customer_merchant (
  id int NOT NULL AUTO_INCREMENT, 
  web varchar (2), 
  ip varchar (2), 
  create_time DateTime, 
  modified_time DateTime, 
  PRIMARY KEY (id)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;