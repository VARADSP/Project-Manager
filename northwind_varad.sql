-- MySQL dump 10.13  Distrib 5.7.32, for Linux (x86_64)
--
-- Host: localhost    Database: northwind_varad
-- ------------------------------------------------------
-- Server version	5.7.32

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
-- Table structure for table `customer`
--

DROP TABLE IF EXISTS `customer`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `customer` (
  `customerid` int(11) NOT NULL AUTO_INCREMENT,
  `customername` varchar(20) NOT NULL,
  `customeraddress` varchar(50) NOT NULL,
  `customeremail` varchar(20) NOT NULL,
  `customercontactnumber` int(10) NOT NULL,
  `customerloginpassword` varchar(20) NOT NULL,
  PRIMARY KEY (`customerid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `customer`
--

LOCK TABLES `customer` WRITE;
/*!40000 ALTER TABLE `customer` DISABLE KEYS */;
INSERT INTO `customer` VALUES (1,'Abishek Deshpande','Aurangabad','abhi@gmail.com',2147483647,'Abhi@123'),(2,'Vishwajeet Deulkar','Aurangabad','vish@gmail.com',2147483647,'Vish@123'),(3,'Vivek Deulkar','Aurangabad','vivi@gmail.com',2147483647,'Vivi@123'),(4,'Varad Deulkar','Aurangabad','varad@gmail.com',2147483647,'Vsp@123');
/*!40000 ALTER TABLE `customer` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `plan`
--

DROP TABLE IF EXISTS `plan`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `plan` (
  `planid` int(11) NOT NULL AUTO_INCREMENT,
  `planname` varchar(20) NOT NULL,
  `plantype` varchar(20) NOT NULL,
  `plantariff` varchar(20) NOT NULL,
  `planvalidity` varchar(20) NOT NULL,
  `planrental` varchar(20) NOT NULL,
  PRIMARY KEY (`planid`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `plan`
--

LOCK TABLES `plan` WRITE;
/*!40000 ALTER TABLE `plan` DISABLE KEYS */;
INSERT INTO `plan` VALUES (1,'ABC Plan1','BroadBand','12500','3 Months','6464'),(2,'ABC Plan2','BroadBand','14500','6 Months','3444'),(3,'ABC Plan3','BroadBand','17500','4 Months','3644'),(4,'ABC Plan4','BroadBand','27500','3 Months','5744');
/*!40000 ALTER TABLE `plan` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_application`
--

DROP TABLE IF EXISTS `project_application`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project_application` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `projectid` int(11) NOT NULL,
  `managerid` int(11) NOT NULL,
  `isapproved` tinyint(1) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `userid` (`userid`),
  KEY `projectid` (`projectid`),
  CONSTRAINT `project_application_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `project_users` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `project_application_ibfk_2` FOREIGN KEY (`projectid`) REFERENCES `project_projects` (`projectid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_application`
--

LOCK TABLES `project_application` WRITE;
/*!40000 ALTER TABLE `project_application` DISABLE KEYS */;
/*!40000 ALTER TABLE `project_application` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_projectrelations`
--

DROP TABLE IF EXISTS `project_projectrelations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project_projectrelations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) NOT NULL,
  `projectid` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `fkp_userid` (`userid`),
  KEY `fkp_projectid` (`projectid`),
  CONSTRAINT `fkp_projectid` FOREIGN KEY (`projectid`) REFERENCES `project_projects` (`projectid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fkp_userid` FOREIGN KEY (`userid`) REFERENCES `project_users` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_projectrelations`
--

LOCK TABLES `project_projectrelations` WRITE;
/*!40000 ALTER TABLE `project_projectrelations` DISABLE KEYS */;
INSERT INTO `project_projectrelations` VALUES (1,9,1),(2,13,2),(5,12,1);
/*!40000 ALTER TABLE `project_projectrelations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_projects`
--

DROP TABLE IF EXISTS `project_projects`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project_projects` (
  `projectid` int(11) NOT NULL AUTO_INCREMENT,
  `projectname` varchar(200) NOT NULL,
  `projectlocation` varchar(200) NOT NULL,
  `teamsize` int(11) DEFAULT NULL,
  `managerid` int(11) DEFAULT NULL,
  PRIMARY KEY (`projectid`),
  UNIQUE KEY `projectname` (`projectname`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_projects`
--

LOCK TABLES `project_projects` WRITE;
/*!40000 ALTER TABLE `project_projects` DISABLE KEYS */;
INSERT INTO `project_projects` VALUES (1,'Partout','Ahmedabad,Gujrat,India',1,9),(2,'OES','Ahmedabad,Gujrat,India',1,13);
/*!40000 ALTER TABLE `project_projects` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_userlist`
--

DROP TABLE IF EXISTS `project_userlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project_userlist` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `category` varchar(200) NOT NULL,
  `sex` varchar(200) NOT NULL,
  `address` varchar(200) NOT NULL,
  `emailid` varchar(200) NOT NULL,
  `isdisabled` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`userid`),
  CONSTRAINT `fk_userid` FOREIGN KEY (`userid`) REFERENCES `project_users` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_userlist`
--

LOCK TABLES `project_userlist` WRITE;
/*!40000 ALTER TABLE `project_userlist` DISABLE KEYS */;
INSERT INTO `project_userlist` VALUES (2,'Admin','admin','male','Aurangabad,Maharashtra','machinelearn81@gmail.com','false'),(9,'Varad Paralikar','manager','male','Aurangabad,Maharashtra,India','parlikarvarad@gmail.com','false'),(12,'Dharani Shankar','employee','male','Chennai','dharani@zohocorp.com','false'),(13,'Vishwajeet Deulkar','manager','male','Aurangabad,Maharashtra,India','vishwajeet@gmail.com',NULL),(14,'Abhishek Deshpande','employee','male','Aurangabad,Maharashtra,India','abhishek@gmail.com',NULL);
/*!40000 ALTER TABLE `project_userlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_userrelations`
--

DROP TABLE IF EXISTS `project_userrelations`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project_userrelations` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `userid` int(11) DEFAULT NULL,
  `managerid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `fk_userid2` (`userid`),
  KEY `fk_managerid` (`managerid`),
  CONSTRAINT `fk_managerid` FOREIGN KEY (`managerid`) REFERENCES `project_users` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `fk_userid2` FOREIGN KEY (`userid`) REFERENCES `project_users` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_userrelations`
--

LOCK TABLES `project_userrelations` WRITE;
/*!40000 ALTER TABLE `project_userrelations` DISABLE KEYS */;
/*!40000 ALTER TABLE `project_userrelations` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `project_users`
--

DROP TABLE IF EXISTS `project_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `project_users` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(50) NOT NULL,
  `password` varchar(50) NOT NULL,
  PRIMARY KEY (`userid`),
  UNIQUE KEY `username` (`username`)
) ENGINE=InnoDB AUTO_INCREMENT=15 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `project_users`
--

LOCK TABLES `project_users` WRITE;
/*!40000 ALTER TABLE `project_users` DISABLE KEYS */;
INSERT INTO `project_users` VALUES (2,'admin','Admin123@'),(9,'varad','Admin123@'),(12,'dharani','Admin123@'),(13,'vishwajeet','Admin123@'),(14,'abhishek','Admin123@');
/*!40000 ALTER TABLE `project_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `struts_userdetails`
--

DROP TABLE IF EXISTS `struts_userdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `struts_userdetails` (
  `userId` int(11) NOT NULL,
  `firstName` varchar(200) DEFAULT NULL,
  `middleName` varchar(200) DEFAULT NULL,
  `lastName` varchar(200) DEFAULT NULL,
  `gender` varchar(200) DEFAULT NULL,
  `email` varchar(200) DEFAULT NULL,
  `contactNo` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`userId`),
  CONSTRAINT `struts_userdetails_ibfk_1` FOREIGN KEY (`userId`) REFERENCES `struts_users` (`userid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `struts_userdetails`
--

LOCK TABLES `struts_userdetails` WRITE;
/*!40000 ALTER TABLE `struts_userdetails` DISABLE KEYS */;
INSERT INTO `struts_userdetails` VALUES (1,'varad','sanjay','paralikar','male','vsp@gmail.com','9988915899'),(2,'vsp','sanjay','paralikar','male','vsp@gmail.com','9988915899'),(3,'vsp3','sanjay','paralikar','male','vsp@gmail.com','9988915899');
/*!40000 ALTER TABLE `struts_userdetails` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `struts_userlist`
--

DROP TABLE IF EXISTS `struts_userlist`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `struts_userlist` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(200) NOT NULL,
  `category` varchar(200) NOT NULL,
  `sex` varchar(200) NOT NULL,
  `address` varchar(200) NOT NULL,
  `emailid` varchar(200) NOT NULL,
  `isDisabled` varchar(5) DEFAULT NULL,
  PRIMARY KEY (`userid`),
  CONSTRAINT `struts_userlist_ibfk_1` FOREIGN KEY (`userid`) REFERENCES `struts_users` (`userid`),
  CONSTRAINT `struts_userlist_ibfk_2` FOREIGN KEY (`userid`) REFERENCES `struts_users` (`userid`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `struts_userlist`
--

LOCK TABLES `struts_userlist` WRITE;
/*!40000 ALTER TABLE `struts_userlist` DISABLE KEYS */;
INSERT INTO `struts_userlist` VALUES (1,'Varad Paralikar','Admin','Male','B-6,Suyog Colony,Padmpura,Aurangabad-431001','parlikarvarad@gmail.com','false'),(2,'VSP V VSP','User','Female','GLS Road,Ahmedabad-38009','vsp123@gmail.com','false');
/*!40000 ALTER TABLE `struts_userlist` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `struts_users`
--

DROP TABLE IF EXISTS `struts_users`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `struts_users` (
  `userid` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(200) NOT NULL,
  `password` varchar(200) NOT NULL,
  PRIMARY KEY (`userid`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `struts_users`
--

LOCK TABLES `struts_users` WRITE;
/*!40000 ALTER TABLE `struts_users` DISABLE KEYS */;
INSERT INTO `struts_users` VALUES (1,'varad','varad'),(2,'vsp','vsp'),(3,'vsp1','vsp1');
/*!40000 ALTER TABLE `struts_users` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `subscription`
--

DROP TABLE IF EXISTS `subscription`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `subscription` (
  `subscriptionid` int(20) NOT NULL AUTO_INCREMENT,
  `sub_customerid` int(11) NOT NULL,
  `sub_planid` int(11) NOT NULL,
  `sub_startdate` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`subscriptionid`),
  KEY `sub_customerid` (`sub_customerid`),
  KEY `sub_planid` (`sub_planid`),
  CONSTRAINT `subscription_ibfk_1` FOREIGN KEY (`sub_customerid`) REFERENCES `customer` (`customerid`),
  CONSTRAINT `subscription_ibfk_2` FOREIGN KEY (`sub_planid`) REFERENCES `plan` (`planid`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `subscription`
--

LOCK TABLES `subscription` WRITE;
/*!40000 ALTER TABLE `subscription` DISABLE KEYS */;
INSERT INTO `subscription` VALUES (2,2,2,'2020-10-25 10:30:36'),(3,3,4,'2020-10-25 10:31:06'),(4,4,2,'2020-10-25 10:31:12'),(5,1,4,'2020-10-29 07:12:10'),(9,1,2,'2020-10-26 12:50:55');
/*!40000 ALTER TABLE `subscription` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `userdetails`
--

DROP TABLE IF EXISTS `userdetails`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!40101 SET character_set_client = utf8 */;
CREATE TABLE `userdetails` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `salutation` varchar(255) DEFAULT NULL,
  `firstname` varchar(255) DEFAULT NULL,
  `middleinitial` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `gender` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `dob` date DEFAULT NULL,
  `address` varchar(255) DEFAULT NULL,
  `userid` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `areaofinterest` varchar(255) DEFAULT NULL,
  `otherinterest` varchar(255) DEFAULT NULL,
  `contactno` varchar(12) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `userdetails`
--

LOCK TABLES `userdetails` WRITE;
/*!40000 ALTER TABLE `userdetails` DISABLE KEYS */;
INSERT INTO `userdetails` VALUES (1,'Mr.','Varad','Sanjay','Paralikar','Male','vsp@gmail.com','1997-03-17','navrangpura','varad','varad123','webProgramming','PS4',NULL),(2,'Mr.','vsp','vsp','vsp','male','vsp@gmail.com','1997-03-17','navrangpura,ahmedabad','vsp','vsp','swingProgramming','PSP',NULL);
/*!40000 ALTER TABLE `userdetails` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-08-01  1:14:33
