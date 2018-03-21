CREATE TABLE operator_detail (
  id int NOT NULL AUTO_INCREMENT, 
  operator_id char (32) NOT NULL, 
  customer_id char (24), 
  login_passwd varchar (200) NOT NULL, 
  payment_passwd varchar (200) NOT NULL, 
  last_login_passwd varchar (200) NOT NULL, 
  last_payment_passwd varchar (200), 
  login_salt varchar (1), 
  payment_salt varchar (1), 
  create_time Datetime, 
  modified_time Datetime, 
  PRIMARY KEY (id)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;