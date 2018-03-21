CREATE TABLE customer_linkmam (
  id int NOT NULL AUTO_INCREMENT, 
  customer_id char (24) NOT NULL, 
  linkman_id int NOT NULL, 
  relation char (1) NOT NULL, 
  is_deleted tinyint (1) NOT NULL, 
  create_time DateTime NOT NULL, 
  modified_time DateTime NOT NULL, 
  PRIMARY KEY (id)
) ENGINE=INNODB DEFAULT CHARSET=UTF8;