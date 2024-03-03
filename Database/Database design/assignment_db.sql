CREATE DATABASE  IF NOT EXISTS `assignment_db` /*!40100 DEFAULT CHARACTER SET utf8mb4 COLLATE utf8mb4_0900_ai_ci */ /*!80016 DEFAULT ENCRYPTION='N' */;
USE `assignment_db`;
-- MySQL dump 10.13  Distrib 8.0.24, for Win64 (x86_64)
--
-- Host: localhost    Database: assignment_db
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
-- Table structure for table `cv_utilizator`
--

DROP TABLE IF EXISTS `cv_utilizator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `cv_utilizator` (
  `utilizator_cv_id` bigint NOT NULL AUTO_INCREMENT,
  `varsta` tinyint unsigned NOT NULL,
  `tara` varchar(45) NOT NULL,
  `oras` varchar(45) NOT NULL,
  `loc_munca` varchar(90) DEFAULT NULL,
  `facultate` varchar(90) DEFAULT NULL,
  `liceu` varchar(90) DEFAULT NULL,
  `interese` varchar(100) DEFAULT NULL,
  `hobby` varchar(100) DEFAULT NULL,
  `skills` varchar(100) DEFAULT NULL,
  `despre_mine` text,
  `utilizator_id` bigint NOT NULL,
  PRIMARY KEY (`utilizator_cv_id`),
  UNIQUE KEY `utilizator_id_UNIQUE` (`utilizator_id`),
  KEY `idx_utilizator_id` (`utilizator_id`),
  CONSTRAINT `fk_utilizator_id` FOREIGN KEY (`utilizator_id`) REFERENCES `utilizator` (`utilizator_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `cv_utilizator`
--

LOCK TABLES `cv_utilizator` WRITE;
/*!40000 ALTER TABLE `cv_utilizator` DISABLE KEYS */;
/*!40000 ALTER TABLE `cv_utilizator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `fotografii`
--

DROP TABLE IF EXISTS `fotografii`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `fotografii` (
  `fotografii_id` int NOT NULL AUTO_INCREMENT,
  `fotografie` blob,
  `fotografie_profil` tinyint(1) DEFAULT NULL,
  `like_status` tinyint(1) DEFAULT NULL,
  `data_postare_poza` datetime NOT NULL,
  `utilizator_id` bigint NOT NULL,
  PRIMARY KEY (`fotografii_id`),
  UNIQUE KEY `utilizator_id_UNIQUE` (`utilizator_id`),
  CONSTRAINT `fk_utilizator_fotografii_id` FOREIGN KEY (`utilizator_id`) REFERENCES `utilizator` (`utilizator_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `fotografii`
--

LOCK TABLES `fotografii` WRITE;
/*!40000 ALTER TABLE `fotografii` DISABLE KEYS */;
/*!40000 ALTER TABLE `fotografii` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `postare_utilizator`
--

DROP TABLE IF EXISTS `postare_utilizator`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `postare_utilizator` (
  `postare_utilizator_id` int NOT NULL AUTO_INCREMENT,
  `utilizator_id` bigint NOT NULL,
  `utilizator_trimitator_id` bigint NOT NULL,
  `mesaj` tinytext,
  `data_postare` datetime NOT NULL,
  `data_actualizare` datetime DEFAULT NULL,
  PRIMARY KEY (`postare_utilizator_id`),
  UNIQUE KEY `utilizator_id_UNIQUE` (`utilizator_id`),
  UNIQUE KEY `utilizator_trimitator_id_UNIQUE` (`utilizator_trimitator_id`),
  KEY `idx_utilizator_id` (`utilizator_id`),
  KEY `idx_utilizator_trimitator_id` (`utilizator_trimitator_id`),
  CONSTRAINT `fk_utilizator_postare_id` FOREIGN KEY (`utilizator_id`) REFERENCES `utilizator` (`utilizator_id`),
  CONSTRAINT `fk_utilizator_trimitator_id` FOREIGN KEY (`utilizator_trimitator_id`) REFERENCES `utilizator` (`utilizator_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `postare_utilizator`
--

LOCK TABLES `postare_utilizator` WRITE;
/*!40000 ALTER TABLE `postare_utilizator` DISABLE KEYS */;
/*!40000 ALTER TABLE `postare_utilizator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `prieteni`
--

DROP TABLE IF EXISTS `prieteni`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `prieteni` (
  `prieteni_id` bigint NOT NULL,
  `utilizator_id1` bigint NOT NULL,
  `utilizator_id2` bigint NOT NULL,
  `stare_prietenie` enum('In asteptare','Acceptat','Respins') NOT NULL,
  `data_prietenie` datetime NOT NULL,
  PRIMARY KEY (`prieteni_id`),
  UNIQUE KEY `utilizator_id1_UNIQUE` (`utilizator_id1`),
  UNIQUE KEY `utilizator_id2_UNIQUE` (`utilizator_id2`),
  KEY `idx_utilizator_id1` (`utilizator_id1`),
  KEY `idx_utilizator_id2` (`utilizator_id2`),
  CONSTRAINT `fk_utilizator_index1` FOREIGN KEY (`utilizator_id1`) REFERENCES `utilizator` (`utilizator_id`),
  CONSTRAINT `fk_utilizator_index2` FOREIGN KEY (`utilizator_id2`) REFERENCES `utilizator` (`utilizator_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `prieteni`
--

LOCK TABLES `prieteni` WRITE;
/*!40000 ALTER TABLE `prieteni` DISABLE KEYS */;
/*!40000 ALTER TABLE `prieteni` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Table structure for table `stare`
--

DROP TABLE IF EXISTS `stare`;
/*!40101 SET @saved_cs_client     = @@character_set_client */;
/*!50503 SET character_set_client = utf8mb4 */;
CREATE TABLE `stare` (
  `stare_id` bigint NOT NULL AUTO_INCREMENT,
  `utilizator_id` bigint NOT NULL,
  `titlu` varchar(100) NOT NULL,
  `continut` text NOT NULL,
  `data_stare` datetime NOT NULL,
  PRIMARY KEY (`stare_id`),
  UNIQUE KEY `utilizator_id_UNIQUE` (`utilizator_id`),
  KEY `idx_utilizator_id` (`utilizator_id`),
  CONSTRAINT `fk_utilizatorstare_id` FOREIGN KEY (`utilizator_id`) REFERENCES `utilizator` (`utilizator_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `stare`
--

LOCK TABLES `stare` WRITE;
/*!40000 ALTER TABLE `stare` DISABLE KEYS */;
/*!40000 ALTER TABLE `stare` ENABLE KEYS */;
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
  PRIMARY KEY (`utilizator_id`),
  UNIQUE KEY `nume_utilizator_UNIQUE` (`nume_utilizator`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `telefon_UNIQUE` (`telefon`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utilizator`
--

LOCK TABLES `utilizator` WRITE;
/*!40000 ALTER TABLE `utilizator` DISABLE KEYS */;
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

-- Dump completed on 2021-05-14 16:02:32
