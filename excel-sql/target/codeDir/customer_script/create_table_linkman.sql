CREATE TABLE linkman (
  id int NOT NULL AUTO_INCREMENT, 
  name varchar (120) NOT NULL, 
  phone varchar (16) NOT NULL, 
  province char (2) NOT NULL, 
  city char (2) NOT NULL, 
  district char (2) NOT NULL, 
  address varchar (200) NOT NULL, 
  is_deleted tinyint (1) NOT NULL, 
  create_time DateTime, 
  modified_time DateTime, 
  PRIMARY KEY (id)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;