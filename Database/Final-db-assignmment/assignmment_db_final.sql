CREATE DATABASE  IF NOT EXISTS `assignment_db_final` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `assignment_db_final`;
-- MySQL dump 10.13  Distrib 8.0.24, for Win64 (x86_64)
--
-- Host: localhost    Database: assignment_db_final
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
-- Table structure for table `postare`
--

DROP TABLE IF EXISTS `postare`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `postare` (
  `postare_id` int NOT NULL AUTO_INCREMENT,
  `utilizator_id` bigint NOT NULL,
  `titlu` varchar(100) NOT NULL,
  `mesaj` tinytext NOT NULL,
  `data_postare` datetime NOT NULL,
  `data_actualizare` datetime DEFAULT NULL,
  PRIMARY KEY (`postare_id`),
  KEY `idx_utilizator_id` (`utilizator_id`) /*!80000 INVISIBLE */,
  CONSTRAINT `fk_utilizator_id` FOREIGN KEY (`utilizator_id`) REFERENCES `utilizator` (`utilizator_id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `postare`
--

LOCK TABLES `postare` WRITE;
/*!40000 ALTER TABLE `postare` DISABLE KEYS */;
INSERT INTO `postare` VALUES (1,1,'Oscar Wilde','Imprumuta intotdeauna bani de la un pesimist. Nu se va astepta niciodata sa ii idai inapoi','2021-02-07 00:00:00',NULL),(2,1,'Despre multumire','Sa multumesti pe toata lumea e imposibil, dar sa-i enervezi pe toti e floare la ureche','2021-01-05 00:00:00',NULL),(3,2,'Your shoes','You have to do what is right for you...No one walks in your shoes.','2021-03-05 00:00:00',NULL);
/*!40000 ALTER TABLE `postare` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prieteni`
--

DROP TABLE IF EXISTS `prieteni`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prieteni` (
  `prieteni_id` bigint NOT NULL AUTO_INCREMENT,
  `utilizator_id1` bigint NOT NULL,
  `utilizator_id2` bigint NOT NULL,
  `stare_prietenie` enum('In asteptare','Acceptat','Respins') NOT NULL,
  `data_prietenie` datetime NOT NULL,
  PRIMARY KEY (`prieteni_id`),
  KEY `idx_utilizator_id1` (`utilizator_id1`),
  KEY `idx_utilizator_id2` (`utilizator_id2`),
  CONSTRAINT `fk_utilizator_id1` FOREIGN KEY (`utilizator_id1`) REFERENCES `utilizator` (`utilizator_id`),
  CONSTRAINT `fk_utilizator_id2` FOREIGN KEY (`utilizator_id2`) REFERENCES `utilizator` (`utilizator_id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prieteni`
--

LOCK TABLES `prieteni` WRITE;
/*!40000 ALTER TABLE `prieteni` DISABLE KEYS */;
INSERT INTO `prieteni` VALUES (1,1,2,'Acceptat','2019-09-12 15:14:26'),(2,1,3,'In asteptare','2019-09-12 15:14:26'),(3,2,4,'Acceptat','2019-09-12 15:14:26'),(4,3,4,'Acceptat','2010-09-12 21:14:26');
/*!40000 ALTER TABLE `prieteni` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `utilizator`
--

DROP TABLE IF EXISTS `utilizator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `utilizator` (
  `utilizator_id` bigint NOT NULL AUTO_INCREMENT,
  `prenume` varchar(45) NOT NULL,
  `nume` varchar(45) NOT NULL,
  `gen` enum('M','F') NOT NULL,
  `data_nasterii` date NOT NULL,
  `locul_nasterii` varchar(45) DEFAULT NULL,
  `nume_utilizator` varchar(45) NOT NULL,
  `parola` varchar(30) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `telefon` varchar(15) DEFAULT NULL,
  `data_inregistrarii` datetime NOT NULL,
  `profil` text,
  `fotografie` mediumblob,
  `cv` mediumblob,
  PRIMARY KEY (`utilizator_id`),
  UNIQUE KEY `nume_utilizator_UNIQUE` (`nume_utilizator`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `telefon_UNIQUE` (`telefon`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utilizator`
--

LOCK TABLES `utilizator` WRITE;
/*!40000 ALTER TABLE `utilizator` DISABLE KEYS */;
INSERT INTO `utilizator` VALUES (1,'Ana','Ion','F','1985-05-29','Constanta','Ana29','Ana@29','anaion@gmail.com','07056980','2019-09-12 00:00:00','Happy',NULL,NULL),(2,'Petre','Ion','M','1987-12-01','Constanta','PetreIon','Petrisor87','petreion@gmail.com','07056260','2019-09-12 00:00:00','Happy',NULL,NULL),(3,'Iris','Anghel','F','1975-06-05','Alba Iulia','Iris','floaredeiris','irisanghel@gmail.com','0756895698','2010-05-01 00:00:00','Travelling...',NULL,NULL),(4,'Razvan','Gheorghe','M','1988-10-15','Baia Mare','Razvan','Razvan@1234','razvangheorghe@gmail.com','0758967412','2005-01-12 00:00:00','La meci',NULL,NULL);
/*!40000 ALTER TABLE `utilizator` ENABLE KEYS */;
UNLOCK TABLES;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-05-20 15:46:35
