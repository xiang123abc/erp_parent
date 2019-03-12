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

insert  into `emp`(`UUID`,`empcode`,`NAME`,`TELE`,`BIRTHDAY`,`SEX`,`PWD`,`note`,`address`,`USERNAME`) values ('1',NULL,'王五2','15151555',NULL,0,NULL,'备注2','上海',NULL),('2',NULL,'李四','15151555',NULL,0,NULL,'备注2','上海',NULL),('4',NULL,'张三','15151555',NULL,0,NULL,'备注2','上海',NULL),('6',NULL,'晓燕','15151555',NULL,0,'xiaoyan','备注2','上海','xiaoyan'),('9HCnirZ3Q2CGnkDMnOZ1Xg',NULL,'aaa','c',NULL,0,NULL,'备注2','c',NULL);

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

insert  into `goods`(`uuid`,`goodscode`,`name`,`origin`,`producer`,`unit`,`inprice`,`outprice`,`goodstypecode`) values ('1',NULL,'水蜜桃','北京','北京水果之乡','斤',2.34,4.23,'1'),('2',NULL,'大鸭梨','北京','北京水果之乡','斤',1.11,3.55,'1'),('3',NULL,'猕猴桃','北京','北京水果之乡','斤',6.33,9.02,'1'),(NULL,NULL,NULL,NULL,NULL,NULL,NULL,NULL,'');

/*Table structure for table `goodstype` */

DROP TABLE IF EXISTS `goodstype`;

CREATE TABLE `goodstype` (
  `uuid` varchar(24) NOT NULL,
  `name` varchar(30) DEFAULT NULL,
  `goodstypecode` varchar(24) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `goodstype` */

insert  into `goodstype`(`uuid`,`name`,`goodstypecode`) values ('','对开','1'),('','抽屉','2');

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

insert  into `orderdetail`(`note`,`area`,`weight`,`height`,`uuid`,`orderdetailcode`,`goodscode`,`goodstype`,`goodsname`,`price`,`num`,`money`,`endtime`,`ender`,`storecode`,`state`,`orderscode`,`code1`,`code2`) values ('',0.01,4,3,'21r6CJ-sQ-SyTyajcTRzTA',NULL,NULL,'对开','大板',66.00,5,0.40,NULL,NULL,NULL,'0','C8009',NULL,NULL),('',3.07,31,23,'56__re2zQyWQ2fPdkPNXZg',NULL,NULL,'对开','大板',23.00,43,70.52,NULL,NULL,NULL,'0','C80012',NULL,NULL),('',0.11,23,23,'63CeTYNaRGSw6glyYgslCw',NULL,NULL,'对开','大板',23.00,2,2.43,NULL,NULL,NULL,'0','C80015',NULL,NULL),('',47.22,43,323,'6Qlv5hEiSnSglxGjTuGwRA',NULL,NULL,'对开','大板',343.00,34,16197.35,NULL,NULL,NULL,'0','C80014',NULL,NULL),('',11.27,23,213,'7j9dnlgXRvi1J4ZdLg0bzA',NULL,NULL,'对开','椅子',23.00,23,259.16,NULL,NULL,NULL,'0','C8004',NULL,NULL),('',53.90,434,23,'HwOKNrafQTaH74dy9bnHyw',NULL,NULL,'对开','大板',5.00,54,269.51,NULL,NULL,NULL,'0','C8006',NULL,NULL),('',0.00,4,3,'iZYkrXoHRvS_0F0IQ3g_ww',NULL,NULL,'对开','大板',55.00,4,0.26,NULL,NULL,NULL,'0','C8008',NULL,NULL),('',53.78,433,23,'M3V_iPm2TI2nV6i35xOnMQ',NULL,NULL,'对开','大板',23.00,54,1236.91,NULL,NULL,NULL,'0','C80011',NULL,NULL),('',0.01,4,3,'MDc_8iy1Qpu2M4VX8Xr1Ew',NULL,NULL,'对开','大板',55.00,5,0.33,NULL,NULL,NULL,'0','C8007',NULL,NULL),('',3.16,23,32,'ohqNaaByTVeJTo-FWAl8nw',NULL,NULL,'对开','大板',34.00,43,107.60,NULL,NULL,NULL,'0','C80013',NULL,NULL),('',47.20,43,343,'OJlDKupsQzabCLVLqvuIng',NULL,NULL,'对开','大板',21.00,32,991.13,NULL,NULL,NULL,'0','C80010',NULL,NULL),('',0.45,23,98,'tA80uDgITxKLs1sWBlHyLA',NULL,NULL,'对开','桌子',3.00,2,1.35,NULL,NULL,NULL,'0','C8004',NULL,NULL);

/*Table structure for table `orders` */

DROP TABLE IF EXISTS `orders`;

CREATE TABLE `orders` (
  `uuid` varchar(24) DEFAULT NULL,
  `orderscode` varchar(24) NOT NULL,
  `color` varchar(24) DEFAULT NULL,
  `model` varchar(24) DEFAULT NULL,
  `material` varchar(24) DEFAULT NULL,
  `createtime` date DEFAULT NULL,
  `checktime` date DEFAULT NULL,
  `starttime` date DEFAULT NULL,
  `endtime` date DEFAULT NULL,
  `type` varchar(1) DEFAULT NULL,
  `creater` varchar(24) DEFAULT NULL,
  `checker` varchar(24) DEFAULT NULL,
  `starter` varchar(24) DEFAULT NULL,
  `ender` varchar(24) DEFAULT NULL,
  `suppliercode` varchar(24) DEFAULT NULL,
  `totalmoney` double(10,2) DEFAULT NULL,
  `state` varchar(1) DEFAULT NULL,
  `sn` varchar(24) DEFAULT NULL,
  `code1` varchar(24) DEFAULT NULL,
  `code2` varchar(24) DEFAULT NULL,
  PRIMARY KEY (`orderscode`),
  UNIQUE KEY `orderscode` (`orderscode`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

/*Data for the table `orders` */

insert  into `orders`(`uuid`,`orderscode`,`color`,`model`,`material`,`createtime`,`checktime`,`starttime`,`endtime`,`type`,`creater`,`checker`,`starter`,`ender`,`suppliercode`,`totalmoney`,`state`,`sn`,`code1`,`code2`) values ('mEtlH1c4SAiMr_DKBSY67A','C80010','655-6','大板','木板3',NULL,NULL,'2018-08-10',NULL,'0',NULL,NULL,NULL,NULL,'a002',991.13,'0',NULL,NULL,NULL),('qPiAFB_vT3iv7zJjpovPfQ','C80011','655-6','大板','木板3',NULL,NULL,'2018-08-10',NULL,'0',NULL,NULL,NULL,NULL,'a002',1236.91,'0',NULL,NULL,NULL),('LWtdIPqbSmW8QZGJn7CFNA','C80012','655-6','大板','木板3',NULL,NULL,'2018-08-10',NULL,'0',NULL,NULL,NULL,NULL,'a001',70.52,'0',NULL,NULL,NULL),('-roKU3LySn6vF_oTlDKPWw','C80013','655-6','大板','木板3',NULL,NULL,'2018-08-10',NULL,'0',NULL,NULL,NULL,NULL,'a002',107.60,'0',NULL,NULL,NULL),('W80BmWDiRBeTXkgIuswDxg','C80014','655-6','大板','木板3',NULL,NULL,'2018-08-10',NULL,'0',NULL,NULL,NULL,NULL,'a002',16197.35,'0',NULL,NULL,NULL),('t58M_7vpRfyCo6-OCMFitQ','C80015','655-6','大板','木板3',NULL,NULL,'2018-08-10',NULL,'0',NULL,NULL,NULL,NULL,'a001',2.43,'0',NULL,NULL,NULL),('lwi4BQCdTKmjvPCdVDPv1A','C8004','655-6','大板','木板3',NULL,NULL,'2018-08-09',NULL,'0',NULL,NULL,NULL,NULL,'a001',260.51,'0',NULL,NULL,NULL),('MRYpm_slQr2HYyyETeT4aQ','C8006','655-6','大板','木板3',NULL,NULL,'2018-08-09',NULL,'0',NULL,NULL,NULL,NULL,'a002',269.51,'0',NULL,NULL,NULL),('QuBo5ZW5TZq6lzqm74CYHQ','C8007','655-6','大板','木板3',NULL,NULL,'2018-08-10',NULL,'0',NULL,NULL,NULL,NULL,'a001',0.33,'0',NULL,NULL,NULL),('pHl7ArCuQHqMIo0RgYSmvw','C8008','655-6','大板','木板3',NULL,NULL,'2018-08-10',NULL,'0',NULL,NULL,NULL,NULL,'a001',0.26,'0',NULL,NULL,NULL),('eaNtU3QzRJSRqij5qoA-3Q','C8009','655-6','大板','木板3',NULL,NULL,'2018-08-10',NULL,'0',NULL,NULL,NULL,NULL,'a002',0.40,'0',NULL,NULL,NULL);

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
