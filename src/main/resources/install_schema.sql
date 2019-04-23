
CREATE TABLE `customer` (
  `customerid` bigint(20) PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(50) NOT NULL,
  `phno` varchar(50) NOT NULL,
  `address` varchar(50) NOT NULL,
  `servicetype` varchar(50) NOT NULL
);

CREATE TABLE `counter` (
  `counterid` bigint(20) PRIMARY KEY AUTO_INCREMENT,
  `countertype` varchar(50) DEFAULT NULL
  );

CREATE TABLE `service` (
  `serviceid` bigint(20) PRIMARY KEY AUTO_INCREMENT,
  `servicename` varchar(50) NOT NULL
  );

CREATE TABLE `token` (
  `tokenid` bigint(20) PRIMARY KEY AUTO_INCREMENT,
  `status` varchar(50) NOT NULL,
  `counterid` bigint(20) DEFAULT NULL,
  `serviceid` bigint(20) DEFAULT NULL,
  `customerid` bigint(20) NOT NULL,
  CONSTRAINT `token_customer_FK` FOREIGN KEY (`customerid`) REFERENCES `customer` (`customerid`),
  CONSTRAINT `token_service_FK` FOREIGN KEY (`serviceid`) REFERENCES `service` (`serviceid`),
  CONSTRAINT `token_counter_FK` FOREIGN KEY (`counterid`) REFERENCES `counter` (`counterid`)
);

insert into counter values (1, 'regular');
insert into counter values (2, 'regular');
insert into counter values (3, 'regular');
insert into counter values (4, 'prime');
insert into counter values (5, 'prime');
insert into counter values (6, 'regular');

insert into service values (1, 'S1');
insert into service values (2, 'S2');
insert into service values (3, 'S3');
insert into service values (4, 'S4');
insert into service values (5, 'S5');
insert into service values (6, 'S6');

