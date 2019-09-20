DROP TABLE IF EXISTS CIDRS;
 
CREATE TABLE CIDRS (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  cidr VARCHAR(20) NOT NULL
);


DROP TABLE IF EXISTS IP_LIST;
 
CREATE TABLE IP_LIST (
  id INT AUTO_INCREMENT  PRIMARY KEY,
  cidr_id int,
  foreign key (cidr_id) references CIDRS(id),
  ipaddress VARCHAR(15) NOT NULL,  -- add unique constraint
  status varchar(20) not null
);