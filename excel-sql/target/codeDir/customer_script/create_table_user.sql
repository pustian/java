CREATE TABLE user (
  id int NOT NULL AUTO_INCREMENT, 
  customer_id char (24) NOT NULL, 
  business_type char (1) NOT NULL, 
  business_role char (1) NOT NULL, 
  phone_no char (11) NOT NULL, 
  user_name varchar (24), 
  create_time DateTime, 
  modified_time DateTime, 
  PRIMARY KEY (id)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;