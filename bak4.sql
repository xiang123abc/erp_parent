/*
SQLyog v10.2 
MySQL - 5.7.23 : Database - erp_ts
*********************************************************************
*/

/*!40101 SET NAMES utf8 */;

/*!40101 SET SQL_MODE=''*/;

/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;
CREATE DATABASE /*!32312 IF NOT EXISTS*/`erp_ts` /*!40100 DEFAULT CHARACTER SET utf8 */;

USE `erp_ts`;

/*Table structure for table `dep` */

DROP TABLE IF EXISTS `dep`;

CREATE TABLE `dep` (
  `UUID` varchar(24) NOT NULL,
  `NAME` varchar(30) DEFAULT NULL,
  `depcode` varchar(24) NOT NULL,
  `TELE` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `dep` */

insert  into `dep`(`UUID`,`NAME`,`depcode`,`TELE`) values ('2','lisi36','','15196352590'),('3','wangwu3','','15176842590'),('4','张三2','','15170536294'),('5','丽水','','16170536294'),('6','力士3','','15185536294'),('88','丽水','','15185536294');

/*Table structure for table `emp` */

DROP TABLE IF EXISTS `emp`;

CREATE TABLE `emp` (
  `UUID` varchar(24) NOT NULL,
  `empcode` varchar(24) DEFAULT NULL,
  `NAME` varchar(30) DEFAULT NULL,
  `TELE` varchar(30) DEFAULT NULL,
  `BIRTHDAY` date DEFAULT NULL,
  `SEX` int(11) DEFAULT NULL,
  `PWD` varchar(30) DEFAULT NULL,
  `note` varchar(30) DEFAULT NULL,
  `address` varchar(30) DEFAULT NULL,
  `USERNAME` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`UUID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `emp` */

insert  into `emp`(`UUID`,`empcode`,`NAME`,`TELE`,`BIRTHDAY`,`SEX`,`PWD`,`note`,`address`,`USERNAME`) values ('1',NULL,'王五2','15151555',NULL,0,NULL,'备注2','上海',NULL),('2',NULL,'李四','15151555',NULL,0,NULL,'备注2','上海',NULL),('4',NULL,'张三','15151555',NULL,0,NULL,'备注2','上海',NULL),('6',NULL,'小白','15151555',NULL,0,'abc123','备注2',NULL,'xiaoyan'),('9HCnirZ3Q2CGnkDMnOZ1Xg',NULL,'aaa','c',NULL,0,NULL,'备注2','c',NULL);

/*Table structure for table `goods` */

DROP TABLE IF EXISTS `goods`;

CREATE TABLE `goods` (
  `uuid` varchar(24) DEFAULT NULL,
  `goodscode` varchar(24) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `origin` varchar(30) DEFAULT NULL,
  `producer` varchar(30) DEFAULT NULL,
  `unit` varchar(30) DEFAULT NULL,
  `inprice` float(8,2) DEFAULT NULL,
  `outprice` float(8,2) DEFAULT NULL,
  `goodstypecode` varchar(24) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `goods` */

insert  into `goods`(`uuid`,`goodscode`,`name`,`origin`,`producer`,`unit`,`inprice`,`outprice`,`goodstypecode`) values ('1',NULL,'水蜜桃','北京','北京水果之乡','斤',2.34,4.23,'1'),('2',NULL,'大鸭梨','北京','北京水果之乡','斤',1.11,3.55,'1'),('3',NULL,'猕猴桃','北京','北京水果之乡','斤',6.33,9.02,'1'),('4',NULL,'网格门',NULL,NULL,NULL,NULL,NULL,''),('5',NULL,'三合一圆弧',NULL,NULL,NULL,NULL,NULL,''),('6',NULL,'玻璃',NULL,NULL,NULL,NULL,NULL,''),('7',NULL,'百叶门',NULL,NULL,NULL,NULL,NULL,'');

/*Table structure for table `goodstype` */

DROP TABLE IF EXISTS `goodstype`;

CREATE TABLE `goodstype` (
  `uuid` varchar(24) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `goodstypecode` varchar(24) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `goodstype` */

insert  into `goodstype`(`uuid`,`name`,`goodstypecode`) values ('a','对开','1'),('b','左开','2'),('c',NULL,'3'),('d','不开','4'),('e','见光门','5'),('f','假门','6');

/*Table structure for table `orderdetail` */

DROP TABLE IF EXISTS `orderdetail`;

CREATE TABLE `orderdetail` (
  `note` varchar(60) DEFAULT NULL,
  `area` double(10,2) DEFAULT NULL,
  `weight` int(10) DEFAULT NULL,
  `height` int(10) DEFAULT NULL,
  `uuid` varchar(24) NOT NULL,
  `orderdetailcode` varchar(24) DEFAULT NULL,
  `goodscode` varchar(24) DEFAULT NULL,
  `goodstype` varchar(15) NOT NULL,
  `goodsname` varchar(30) DEFAULT NULL,
  `price` float(10,2) DEFAULT NULL,
  `num` int(11) DEFAULT NULL,
  `money` float(10,2) DEFAULT NULL,
  `endtime` date DEFAULT NULL,
  `ender` varchar(24) DEFAULT NULL,
  `storecode` varchar(24) DEFAULT NULL,
  `state` char(1) DEFAULT NULL,
  `orderscode` varchar(24) DEFAULT NULL,
  `code1` varchar(24) DEFAULT NULL,
  `code2` varchar(24) DEFAULT NULL,
  PRIMARY KEY (`uuid`),
  KEY `orderdetail_ibfk_1` (`orderscode`),
  CONSTRAINT `orderdetail_ibfk_1` FOREIGN KEY (`orderscode`) REFERENCES `orders` (`orderscode`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `orderdetail` */

insert  into `orderdetail`(`note`,`area`,`weight`,`height`,`uuid`,`orderdetailcode`,`goodscode`,`goodstype`,`goodsname`,`price`,`num`,`money`,`endtime`,`ender`,`storecode`,`state`,`orderscode`,`code1`,`code2`) values ('',0.13,234,23,'2YUteR7OTwGW8cp8YdquyQ',NULL,NULL,'不开','木板3',424.00,24,54.78,NULL,NULL,NULL,'0','c8032',NULL,NULL),('',1.23,2323,23,'9cDkpfXWRGeboQy4Y2zcHA',NULL,NULL,'不开','大板',233.00,23,286.33,NULL,NULL,NULL,'0','C8033',NULL,NULL),('',10.00,10000,100,'dEWJY-8IQTmHa4s13fzBvQ',NULL,NULL,'不开','大板2',10.00,10,0.00,NULL,NULL,NULL,'0','C8034',NULL,NULL),('',1.00,1000,100,'ELQ3QTjzQgOnkJIAKhckSw',NULL,NULL,'对开','大板1',10.00,10,10.00,NULL,NULL,NULL,'0','C8034',NULL,NULL),('',1000.00,1000,1000,'j_o72paJQyaE7RBqjLcXUg',NULL,NULL,'左开','大板31',1.00,1000,1000.00,NULL,NULL,NULL,'0','C8036',NULL,NULL),('',4000.00,2000,2000,'WhINU9zFTbi1mEBcok4sUw',NULL,NULL,'见光门','大板32',1.00,1000,4000.00,NULL,NULL,NULL,'0','C8036',NULL,NULL),('',0.02,32,23,'zpj99Iz1QN2m75K-OHpWcg',NULL,NULL,'左开','木板3',32.00,23,0.54,NULL,NULL,NULL,'0','c8031',NULL,NULL);

/*Table structure for table `orders` */

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `uuid` varchar(24) DEFAULT NULL,
  `orderscode` varchar(24) NOT NULL,
  `color` varchar(24) DEFAULT NULL,
  `model` varchar(24) DEFAULT NULL,
  `material` varchar(24) DEFAULT NULL,
  `starttime` date DEFAULT NULL,
  `endtime` date DEFAULT NULL,
  `type` varchar(1) DEFAULT NULL,
  `starter` varchar(24) DEFAULT NULL,
  `ender` varchar(24) DEFAULT NULL,
  `suppliercode` varchar(24) DEFAULT NULL,
  `address` varchar(24) DEFAULT NULL,
  `totalarea` double(10,4) DEFAULT NULL,
  `totalmoney` double(10,2) DEFAULT NULL,
  `state` varchar(1) DEFAULT NULL,
  `sn` varchar(24) DEFAULT NULL,
  PRIMARY KEY (`orderscode`),
  UNIQUE KEY `orderscode` (`orderscode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `orders` */

insert  into `orders`(`uuid`,`orderscode`,`color`,`model`,`material`,`starttime`,`endtime`,`type`,`starter`,`ender`,`suppliercode`,`address`,`totalarea`,`totalmoney`,`state`,`sn`) values ('z6wBxdWlSb2GXAIZJXH3CA','c8031','655-6','大板','木板3','2018-08-23',NULL,'0',NULL,NULL,'a002','上海',0.0169,0.54,'0',NULL),('2Gjtbp45Sb6hAnYGi6LQUQ','c8032','655-6','大板','木板3','2018-08-23',NULL,'0',NULL,NULL,'a001','上海',0.1292,54.78,'0',NULL),('fdXVHJDLT7KwGGwVGmxRPA','C8033','655-6','大板','木板3','2018-08-23',NULL,'0',NULL,NULL,'a001','上海',1.2289,286.33,'0',NULL),('8MYmsA2iQ5eiZLOrImjamg','C8034','655-6','大板','木板3','2018-08-23',NULL,'0',NULL,NULL,'a002','上海',11.0000,10.00,'0',NULL),('4JBNIIDiTzaAR6QRZuOWdA','C8036','655-6','大板3','木板3','2018-08-23',NULL,'0',NULL,NULL,'a001','上海',5000.0000,5000.00,'0',NULL);

/*Table structure for table `supplier` */

DROP TABLE IF EXISTS `supplier`;

CREATE TABLE `supplier` (
  `uuid` varchar(24) DEFAULT NULL,
  `name` varchar(30) DEFAULT NULL,
  `address` varchar(100) DEFAULT NULL,
  `contact` varchar(30) DEFAULT NULL,
  `tele` varchar(30) DEFAULT NULL,
  `email` varchar(100) DEFAULT NULL,
  `type` char(1) DEFAULT NULL,
  `suppliercode` varchar(30) DEFAULT NULL,
  UNIQUE KEY `USERCODE` (`suppliercode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `supplier` */

insert  into `supplier`(`uuid`,`name`,`address`,`contact`,`tele`,`email`,`type`,`suppliercode`) values ('10000','张三','北京',NULL,'10086',NULL,'1','a001'),('10001','李四','上海',NULL,'10010',NULL,'1','a002');

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;
