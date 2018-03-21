CREATE TABLE operator_logon (
  id int NOT NULL AUTO_INCREMENT, 
  operator_id char (32) NOT NULL, 
  customer_id char (24) NOT NULL, 
  login_no varchar (64), 
  login_type char (1), 
  passwd_login_flag char (1), 
  device char (1), 
  version varchar (8), 
  business_type char (1), 
  customer_role char (1), 
  login_status char (1) NOT NULL, 
  token varchar (200) NOT NULL, 
  create_time datetime, 
  modified_time datetime, 
  PRIMARY KEY (id)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;