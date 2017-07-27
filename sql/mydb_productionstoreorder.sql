-- MySQL dump 10.13  Distrib 5.7.12, for Win64 (x86_64)
--
-- Host: 127.0.0.1    Database: mydb
-- ------------------------------------------------------
-- Server version	5.7.17-log

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `productionstoreorder`
--

DROP TABLE IF EXISTS `productionstoreorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `productionstoreorder` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '产品出入库iD',
  `ProductionID` int(11) NOT NULL COMMENT '产品iD',
  `Number` double NOT NULL COMMENT '产品数量',
  `Date` datetime NOT NULL COMMENT '出入库时间',
  `OrderID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `ProductionAndStoreOrder_idx` (`ProductionID`),
  CONSTRAINT `ProductionAndStoreOrder` FOREIGN KEY (`ProductionID`) REFERENCES `production` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `productionstoreorder`
--

LOCK TABLES `productionstoreorder` WRITE;
/*!40000 ALTER TABLE `productionstoreorder` DISABLE KEYS */;
INSERT INTO `productionstoreorder` VALUES (13,12,3,'2017-07-25 01:00:00',10),(14,12,1,'2017-07-25 00:00:10',11),(15,12,1,'2017-07-25 00:11:00',-1),(16,12,1,'2017-07-25 00:33:00',-1),(17,12,-1,'2017-07-25 22:00:00',-1),(18,12,-1,'2017-07-25 12:12:00',9);
/*!40000 ALTER TABLE `productionstoreorder` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2017-07-27 14:08:35
