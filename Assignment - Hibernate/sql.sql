-- MySQL dump 10.13  Distrib 8.0.24, for Win64 (x86_64)
--
-- Host: localhost    Database: test
-- ------------------------------------------------------
-- Server version	8.0.24

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!50503 SET NAMES utf8 */;
/*!40103 SET @OLD_TIME_ZONE=@@TIME_ZONE */;
/*!40103 SET TIME_ZONE='+00:00' */;
/*!40014 SET @OLD_UNIQUE_CHECKS=@@UNIQUE_CHECKS, UNIQUE_CHECKS=0 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;
/*!40111 SET @OLD_SQL_NOTES=@@SQL_NOTES, SQL_NOTES=0 */;

--
-- Table structure for table `department`
--

DROP TABLE IF EXISTS `department`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `department` (
  `departmentId` int NOT NULL AUTO_INCREMENT,
  `name` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`departmentId`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `department`
--

LOCK TABLES `department` WRITE;
/*!40000 ALTER TABLE `department` DISABLE KEYS */;
INSERT INTO `department` VALUES (1,'Risk Management'),(2,'Investment'),(3,'Operations'),(4,'Juridic'),(5,'IT'),(6,'Audit'),(7,'CEO'),(8,'Sales'),(9,'Customer Service'),(10,'Finance'),(11,'Marketing'),(12,'HR'),(13,'Purchase');
/*!40000 ALTER TABLE `department` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `employee`
--

DROP TABLE IF EXISTS `employee`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `employee` (
  `id` int NOT NULL AUTO_INCREMENT,
  `name` varchar(45) DEFAULT NULL,
  `age` int DEFAULT '0',
  `address` varchar(80) DEFAULT NULL,
  `salary` double(16,2) DEFAULT '0.00',
  `departmentId` int DEFAULT '0',
  PRIMARY KEY (`id`),
  UNIQUE KEY `id_UNIQUE` (`id`),
  KEY `fk_department_id_idx` (`departmentId`) /*!80000 INVISIBLE */,
  CONSTRAINT `fk_department_id` FOREIGN KEY (`departmentId`) REFERENCES `department` (`departmentId`) ON DELETE SET NULL ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=33 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `employee`
--

LOCK TABLES `employee` WRITE;
/*!40000 ALTER TABLE `employee` DISABLE KEYS */;
INSERT INTO `employee` VALUES (2,'Pavel Sorin',56,'Str Fantomelor',5698.00,5),(3,'Istrate Gentiana',25,'Str Alba',5694.00,5),(9,'Apostolescu Ioana',45,'Str Zambilelor',3256.00,3),(10,'Ivan C Corina',32,'Str Zambetelor, nr 5, jud Galati, Galati',6610.00,2),(11,'Teodor Parascheva',28,'Str Aleea cu FLori, jud. Timis, Timisoara',6891.00,9),(13,'Pascu Ion',36,'Caldarusani 14',4990.00,12),(14,'Fagarasanu Denis',45,'Zambilelor 18',3984.00,10),(15,'Bizon Ioana',36,'Str Valea Cu Flori, nr 100, jud. Hunedoara',6895.00,11),(16,'Cana Valeriu',55,'Maracineni 25, Bucuresti',2350.00,7),(18,'Popescu Marinel',51,'Ion Ionescu de la Brad 51, sector 1',5166.00,8),(20,'Agache Ioana Gratiela',22,'Str Valea Cascadelor, nr 35, Sector 6, Bucuresti',7758.00,4),(21,'Cristescu Sorin',39,'Str Opal, nr 23, Iasi',10569.00,6),(22,'Cristescu Iris',36,'Str Margelelor',5698.00,3),(23,'Bisiceanu',43,'Str Porompom',3698.00,1),(25,'Popescu Ioana',56,'Str. Principa',5698.00,3),(26,'Manaila Elena Ioana',32,'Str Florilor',5698.00,4),(27,'Bisiceanu Liana',36,'Str. Florilor',9869.00,6),(28,'Cristescu Florin',39,'Str. Cascadelor 4',3456.00,9),(30,'Eugen Alexandru',45,'Str. Crizantemelor 3',4569.00,8),(32,'Ion Carla',56,'Str Alba',12345.00,6);
/*!40000 ALTER TABLE `employee` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping events for database 'test'
--

--
-- Dumping routines for database 'test'
--
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-10-20  4:17:10
