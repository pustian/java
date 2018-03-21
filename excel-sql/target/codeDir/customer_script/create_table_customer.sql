CREATE TABLE customer (
  id int NOT NULL AUTO_INCREMENT, 
  customer_id char (24) NOT NULL, 
  phone_no char (11), 
  credentials_type char (1) NOT NULL, 
  credentials_no varchar (20) NOT NULL, 
  customer_name varchar (180) NOT NULL, 
  customer_type char (2), 
  customer_status char (1), 
  customer_access_rights_state varchar (20), 
  customer_authentication_state tinyint (1), 
  is_deleted tinyint (1), 
  memo varchar (200), 
  create_time DateTime, 
  modified_time DateTime, 
  PRIMARY KEY (id)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;