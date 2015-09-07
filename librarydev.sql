-- MySQL dump 10.13  Distrib 5.6.23, for Win64 (x86_64)
--
-- Host: localhost    Database: librarydev

CREATE DATABASE IF NOT EXISTS `librarydev` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `librarydev`;

-- ------------------------------------------------------
-- Server version	5.6.23

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
-- Table structure for table `bookcopy`
--

DROP TABLE IF EXISTS `bookcopy`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `bookcopy` (
  `bkCopyID` int(11) NOT NULL AUTO_INCREMENT,
  `bk_id` int(11) NOT NULL,
  `bkCopyNum` int(11) NOT NULL,
  `bkCopyEd` varchar(60) NOT NULL,
  `bkCopyPublishYear` date NOT NULL,
  `bkCatNum` varchar(60) NOT NULL,
  `copyStat_id` int(11) NOT NULL,
  PRIMARY KEY (`bkCopyID`),
  KEY `bk_id` (`bk_id`),
  KEY `copyStat_id` (`copyStat_id`),
  KEY `bk_id_2` (`bk_id`),
  CONSTRAINT `bookcopy_ibfk_1` FOREIGN KEY (`bk_id`) REFERENCES `books` (`bk_id`),
  CONSTRAINT `bookcopy_ibfk_2` FOREIGN KEY (`copyStat_id`) REFERENCES `copy_status` (`copyStat_id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `bookcopy`
--

LOCK TABLES `bookcopy` WRITE;
/*!40000 ALTER TABLE `bookcopy` DISABLE KEYS */;
INSERT INTO `bookcopy` VALUES (1,1,1,'','1994-11-10','14JX2',2),(2,1,2,'','1994-11-10','1XTVA',3),(3,1,3,'','1994-11-10','2XABC',2),(4,2,1,'','2010-01-08','1',3),(5,2,2,'','2010-01-08','DCE12',1),(6,4,1,'','2010-01-01','96EDSweew',2),(7,4,2,'','2010-03-13','RCX59',1),(8,8,1,'','2014-01-01','36XPO4',2),(9,8,2,'','2013-06-21','98HGTI',1),(10,8,3,'','2013-06-21','78DWSE',3),(11,6,1,'','2014-01-01','CD234',1),(12,3,1,'','2011-01-01','XXX123',1);
/*!40000 ALTER TABLE `bookcopy` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `books`
--

DROP TABLE IF EXISTS `books`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `books` (
  `bk_id` int(11) NOT NULL AUTO_INCREMENT,
  `bk_title` varchar(200) NOT NULL,
  `bk_author` varchar(200) NOT NULL,
  `bk_publisher` varchar(200) NOT NULL,
  `bk_ISBN` varchar(200) NOT NULL,
  `bk_edition` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`bk_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `books`
--

LOCK TABLES `books` WRITE;
/*!40000 ALTER TABLE `books` DISABLE KEYS */;
INSERT INTO `books` VALUES (1,'Design Patterns: Elements of Reusable Object-Oriented Software','Erich Gamma','Addison Wesley Professional','9780201633610','7th Edition'),(2,'Financial Accounting','Bian Ji Jian Ji','China Financial Publishing House','9787500557791','4th Edition'),(3,'A Molecular Approach (3rd Edition)','Nivaldo Tro','Prentice Hall','9780321809247','3rd Edition'),(4,'Software Engineering','Ian Sommerville','Pearson','666666','9th Edition'),(5,'Campbell Biology','Jane B, Reece','Benjamin Cummings','9780321775658','10th Editiion'),(6,'Tourism: Principles, Practices, Philosophies ','Charles R. Goeldner','Wiley','9781118071779','5th Edition'),(7,'Introduction to Java Programming','Y. Daniel Liang','Pearson','9780132936521','9th Edition'),(8,'Software Engineering','Ian Sommerville','Pearson','0137035152','10th Edition'),(9,'Introduction to Java Programming','Y. Daniel Liang','Pearson','0132936526','8th Edition');
/*!40000 ALTER TABLE `books` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `copy_status`
--

DROP TABLE IF EXISTS `copy_status`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `copy_status` (
  `copyStat_id` int(11) NOT NULL AUTO_INCREMENT,
  `copyStat_desc` varchar(60) NOT NULL,
  PRIMARY KEY (`copyStat_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `copy_status`
--

LOCK TABLES `copy_status` WRITE;
/*!40000 ALTER TABLE `copy_status` DISABLE KEYS */;
INSERT INTO `copy_status` VALUES (1,'Available'),(2,'On Loan'),(3,'Reserved'),(4,'Overdue');
/*!40000 ALTER TABLE `copy_status` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `issue`
--

DROP TABLE IF EXISTS `issue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `issue` (
  `issueID` int(11) NOT NULL AUTO_INCREMENT,
  `bkCopyID` int(11) NOT NULL,
  `StudentID` varchar(60) NOT NULL,
  `checkInDate` date DEFAULT NULL,
  `issueDueDate` date NOT NULL,
  `checkOutDate` date NOT NULL,
  `dueID` int(11) NOT NULL,
  `issueFine` int(11) NOT NULL,
  PRIMARY KEY (`issueID`),
  KEY `bkCopyNum` (`bkCopyID`,`dueID`),
  KEY `bkCopyNum_2` (`bkCopyID`),
  KEY `dueID` (`dueID`),
  CONSTRAINT `issue_ibfk_1` FOREIGN KEY (`dueID`) REFERENCES `overdue` (`dueID`),
  CONSTRAINT `issue_ibfk_2` FOREIGN KEY (`bkCopyID`) REFERENCES `bookcopy` (`bkCopyID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `issue`
--

LOCK TABLES `issue` WRITE;
/*!40000 ALTER TABLE `issue` DISABLE KEYS */;
INSERT INTO `issue` VALUES (11,7,'s99999','2015-08-27','2015-08-14','2015-07-26',1,26),(12,1,'S11085678',NULL,'2015-09-10','2015-08-27',2,0),(13,11,'S11085678','2015-08-27','2015-08-10','2015-07-27',1,34);
/*!40000 ALTER TABLE `issue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `overdue`
--

DROP TABLE IF EXISTS `overdue`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `overdue` (
  `dueID` int(11) NOT NULL AUTO_INCREMENT,
  `dueDesc` varchar(60) NOT NULL,
  PRIMARY KEY (`dueID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `overdue`
--

LOCK TABLES `overdue` WRITE;
/*!40000 ALTER TABLE `overdue` DISABLE KEYS */;
INSERT INTO `overdue` VALUES (1,'Overdue'),(2,'On Time');
/*!40000 ALTER TABLE `overdue` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `reserve`
--

DROP TABLE IF EXISTS `reserve`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `reserve` (
  `reserveID` int(11) NOT NULL AUTO_INCREMENT,
  `bookCopyId` int(11) NOT NULL,
  `StudentID` varchar(60) NOT NULL,
  `reserveDate` date DEFAULT NULL,
  `pickupDate` date DEFAULT NULL,
  PRIMARY KEY (`reserveID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `reserve`
--

LOCK TABLES `reserve` WRITE;
/*!40000 ALTER TABLE `reserve` DISABLE KEYS */;
INSERT INTO `reserve` VALUES (1,7,'s99999','2015-08-01','2015-08-05'),(2,8,'S11067845','2015-08-27','2015-08-04'),(3,7,'S11085678','2015-08-27','2015-08-08'),(4,1,'S11085678','2015-08-27','2015-08-02');
/*!40000 ALTER TABLE `reserve` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `users`
--

DROP TABLE IF EXISTS `users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `users` (
  `id` int(11) DEFAULT NULL,
  `username` varchar(64) DEFAULT NULL,
  `email` varchar(64) DEFAULT NULL,
  `password` varchar(256) DEFAULT NULL,
  `role` char(1) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `users`
--

LOCK TABLES `users` WRITE;
/*!40000 ALTER TABLE `users` DISABLE KEYS */;
INSERT INTO `users` VALUES (1,'niumaia','niumaia@test.com','k4AXDX87vLHmzXC18klyPqt84vp6HlrPd+cnU4IggndUq8Vrikonz/pbdrXQUJlz','A'),(2,'shabnil','shabnil@test.com','Pm7i1EM7TxSkM1UCV9/NCkkl/lvkrhVFFKgnuv8iemPgQK/TI+3pqY54DCEmWxpJ','A'),(3,'akshay','akshay@test.com','TvOOUNoK05k7hJOzKncAxGcqazQcQbPKtx8hjpWIN2B7Virpucw0yMmIU028cqDZ','A'),(4,'sheevani','sheevani@test.com','LZTB3BboESlxrNMOvWk+pkQ7f9cLEgudj2ckAbcuaqmSyJ0R5wh4Qj5TT5II+iyh','C');
/*!40000 ALTER TABLE `users` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2015-09-04 10:48:44
