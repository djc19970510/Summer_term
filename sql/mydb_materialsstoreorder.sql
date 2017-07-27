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
-- Table structure for table `materialsstoreorder`
--

DROP TABLE IF EXISTS `materialsstoreorder`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `materialsstoreorder` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '材料仓库出入ID',
  `Number` double NOT NULL COMMENT '材料仓库出入数量',
  `Date` datetime NOT NULL COMMENT '材料仓库出入时间',
  `MaterialsID` int(11) NOT NULL COMMENT '材料ID',
  `OrderID` int(11) NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `MaterialsAndStoreOrder_idx` (`MaterialsID`),
  CONSTRAINT `MaterialsAndStoreOrder` FOREIGN KEY (`MaterialsID`) REFERENCES `materials` (`ID`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `materialsstoreorder`
--

LOCK TABLES `materialsstoreorder` WRITE;
/*!40000 ALTER TABLE `materialsstoreorder` DISABLE KEYS */;
INSERT INTO `materialsstoreorder` VALUES (22,44,'2017-07-25 11:00:00',1,11),(23,-3,'2017-07-25 01:00:00',1,10),(24,-6,'2017-07-25 01:00:00',2,10),(25,-1,'2017-07-25 00:00:10',1,11),(26,-2,'2017-07-25 00:00:10',2,11),(27,3,'2017-07-25 01:10:00',74,12);
/*!40000 ALTER TABLE `materialsstoreorder` ENABLE KEYS */;
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
