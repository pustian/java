CREATE TABLE operator (
  id int NOT NULL AUTO_INCREMENT, 
  customer_id char (24) NOT NULL, 
  operator_name varchar (64), 
  error_login_time tinyint (1) NOT NULL, 
  business_type varchar (1) NOT NULL, 
  customer_role varchar (1) NOT NULL, 
  operator_status char (1), 
  operator_type char (1), 
  lock_time DateTime, 
  is_deleted tinyint (1), 
  create_time DateTime, 
  modified_time DateTime, 
  PRIMARY KEY (id)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;