CREATE TABLE customer_log (
  id int NOT NULL AUTO_INCREMENT, 
  customer_id char (24) NOT NULL, 
  business_type char (1) NOT NULL, 
  business_role char (1) NOT NULL, 
  user_id int NOT NULL, 
  update_content varchar (200) NOT NULL, 
  create_time Datetime NOT NULL, 
  PRIMARY KEY (id)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;