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
-- Temporary view structure for view `dateutilizator`
--

DROP TABLE IF EXISTS `dateutilizator`;
/*!50001 DROP VIEW IF EXISTS `dateutilizator`*/;
SET @saved_cs_client     = @@character_set_client;
/*!50503 SET character_set_client = utf8mb4 */;
/*!50001 CREATE VIEW `dateutilizator` AS SELECT 
 1 AS `prenume`,
 1 AS `nume`,
 1 AS `data_nasterii`,
 1 AS `locul_nasterii`*/;
SET character_set_client = @saved_cs_client;

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
  FULLTEXT KEY `IDX_MESAJ` (`mesaj`),
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
  `data_nasterii` date NOT NULL DEFAULT '0000-00-00',
  `locul_nasterii` varchar(45) DEFAULT NULL,
  `nume_utilizator` varchar(45) NOT NULL,
  `parola` varchar(30) NOT NULL,
  `email` varchar(45) DEFAULT NULL,
  `telefon` varchar(15) DEFAULT NULL,
  `data_inregistrarii` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `profil` text,
  `fotografie` mediumblob,
  `cv` mediumblob,
  PRIMARY KEY (`utilizator_id`),
  UNIQUE KEY `nume_utilizator_UNIQUE` (`nume_utilizator`),
  UNIQUE KEY `email_UNIQUE` (`email`),
  UNIQUE KEY `telefon_UNIQUE` (`telefon`),
  KEY `IDX_NUME` (`nume`,`prenume`),
  KEY `IDX_UTILIZATOR` (`nume_utilizator`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_0900_ai_ci;
/*!40101 SET character_set_client = @saved_cs_client */;

--
-- Dumping data for table `utilizator`
--

LOCK TABLES `utilizator` WRITE;
/*!40000 ALTER TABLE `utilizator` DISABLE KEYS */;
INSERT INTO `utilizator` VALUES (1,'Ana','Ion','F','1985-05-29','Constanta','Ana29','Ana@29','anaion@gmail.com','07056980','2019-09-12 00:00:00','Happy',NULL,NULL),(2,'Petre','Ion','M','1987-12-01','Constanta','PetreIon','Petrisor87','petreion@gmail.com','07056260','2019-09-12 00:00:00','Happy',NULL,NULL),(3,'Iris','Anghel','F','1975-06-05','Alba Iulia','Iris','floaredeiris','irisanghel@gmail.com','0756895698','2010-05-01 00:00:00','Travelling...',NULL,NULL),(4,'Razvan','Gheorghe','M','1988-10-15','Baia Mare','Razvan','Razvan@1234','razvangheorghe@gmail.com','0758967412','2005-01-12 00:00:00','La meci',NULL,NULL),(5,'Iris','Pop','F','1985-02-12','Iasi','floare-de-iris','iris55688','pop.iris@mail.com','0756125897','2008-08-12 00:00:00',NULL,NULL,NULL),(6,'Vlad','Alexandrescu','M','1988-09-12','Timis','vladut','sda5463','vlad_alex@gmail.com','0745781236','2001-03-30 00:00:00','coffee time',NULL,NULL);
/*!40000 ALTER TABLE `utilizator` ENABLE KEYS */;
UNLOCK TABLES;

--
-- Dumping routines for database 'assignment_db_final'
--
/*!50003 DROP FUNCTION IF EXISTS `nr_prieteni` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` FUNCTION `nr_prieteni`(new_utilizator_id int) RETURNS int
    DETERMINISTIC
begin
set @count_prieteni1 = 0;
set @count_prieteni2 = 0;
select count(utilizator_id1) from prieteni where new_utilizator_id = utilizator_id1 into @count_prieteni1;
select count(utilizator_id2) from prieteni where new_utilizator_id = utilizator_id2 into @count_prieteni2;
return @count_prieteni1 + @count_prieteni2;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `introducere_utilizator` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `introducere_utilizator`(new_prenume varchar(45), new_nume varchar(45), new_gen enum('M','F'), new_data_nasterii date, new_locul_nasterii varchar(45), new_nume_utilizator varchar(45), new_parola varchar(30), new_email varchar(45), new_telefon varchar(15), new_data_inregistrarii datetime)
begin
insert into utilizator (prenume, nume, gen, data_nasterii, locul_nasterii, nume_utilizator, parola, email, telefon, data_inregistrarii) values (new_prenume, new_nume, new_gen, new_data_nasterii, new_locul_nasterii, new_nume_utilizator, new_parola, new_email, new_telefon, new_data_inregistrarii);
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `modificare_utilizator` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `modificare_utilizator`(new_utilizator_id int, new_prenume varchar(45), new_nume varchar(45), new_gen enum('M','F',''), new_data_nasterii date, new_locul_nasterii varchar(45), new_nume_utilizator varchar(45), new_parola varchar(30), new_email varchar(45), new_telefon varchar(15), new_data_inregistrarii datetime, new_profil text, new_fotografie mediumblob, new_cv mediumblob)
begin

if new_prenume!='' then update utilizator set prenume=new_prenume where utilizator_id=new_utilizator_id; end if;
if new_nume!='' then update utilizator set nume=new_nume where utilizator_id=new_utilizator_id; end if;
if new_gen!='' then update utilizator set gen=new_gen where utilizator_id=new_utilizator_id; end if;
if new_data_nasterii!='0000-00-00' then update utilizator set data_nasterii=new_data_nasterii where utilizator_id=new_utilizator_id; end if;
if new_locul_nasterii!='' then update utilizator set locul_nasterii=new_locul_nasterii where utilizator_id=new_utilizator_id; end if;
if new_nume_utilizator!='' then update utilizator set nume_utilizator=new_nume_utilizator where utilizator_id=new_utilizator_id; end if;
if new_parola!='' then update utilizator set parola=new_parola where utilizator_id=new_utilizator_id; end if;
if new_email!='' then update utilizator set email=new_email where utilizator_id=new_utilizator_id; end if;
if new_telefon!='' then update utilizator set telefon=new_telefon where utilizator_id=new_utilizator_id; end if;
if new_data_inregistrarii!='0000-00-00 00:00:00' then update utilizator set data_inregistrarii=new_data_inregistrarii where utilizator_id=new_utilizator_id; end if;
if new_profil!='' then update utilizator set profil=new_profil where utilizator_id=new_utilizator_id; end if;
if new_fotografie!='' then update utilizator set fotografie=new_fotografie where utilizator_id=new_utilizator_id; end if;
if new_cv!='' then update utilizator set cv=new_cv where utilizator_id=new_utilizator_id; end if;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;
/*!50003 DROP PROCEDURE IF EXISTS `sterge_utilizator` */;
/*!50003 SET @saved_cs_client      = @@character_set_client */ ;
/*!50003 SET @saved_cs_results     = @@character_set_results */ ;
/*!50003 SET @saved_col_connection = @@collation_connection */ ;
/*!50003 SET character_set_client  = utf8mb4 */ ;
/*!50003 SET character_set_results = utf8mb4 */ ;
/*!50003 SET collation_connection  = utf8mb4_0900_ai_ci */ ;
/*!50003 SET @saved_sql_mode       = @@sql_mode */ ;
/*!50003 SET sql_mode              = 'STRICT_TRANS_TABLES,NO_ENGINE_SUBSTITUTION' */ ;
DELIMITER ;;
CREATE DEFINER=`root`@`localhost` PROCEDURE `sterge_utilizator`(new_utilizator_id int)
begin
delete from utilizator where utilizator_id=new_utilizator_id;
end ;;
DELIMITER ;
/*!50003 SET sql_mode              = @saved_sql_mode */ ;
/*!50003 SET character_set_client  = @saved_cs_client */ ;
/*!50003 SET character_set_results = @saved_cs_results */ ;
/*!50003 SET collation_connection  = @saved_col_connection */ ;

--
-- Final view structure for view `dateutilizator`
--

/*!50001 DROP VIEW IF EXISTS `dateutilizator`*/;
/*!50001 SET @saved_cs_client          = @@character_set_client */;
/*!50001 SET @saved_cs_results         = @@character_set_results */;
/*!50001 SET @saved_col_connection     = @@collation_connection */;
/*!50001 SET character_set_client      = utf8mb4 */;
/*!50001 SET character_set_results     = utf8mb4 */;
/*!50001 SET collation_connection      = utf8mb4_0900_ai_ci */;
/*!50001 CREATE ALGORITHM=UNDEFINED */
/*!50013 DEFINER=`root`@`localhost` SQL SECURITY DEFINER */
/*!50001 VIEW `dateutilizator` AS select `utilizator`.`prenume` AS `prenume`,`utilizator`.`nume` AS `nume`,`utilizator`.`data_nasterii` AS `data_nasterii`,`utilizator`.`locul_nasterii` AS `locul_nasterii` from `utilizator` */;
/*!50001 SET character_set_client      = @saved_cs_client */;
/*!50001 SET character_set_results     = @saved_cs_results */;
/*!50001 SET collation_connection      = @saved_col_connection */;
/*!40103 SET TIME_ZONE=@OLD_TIME_ZONE */;

/*!40101 SET SQL_MODE=@OLD_SQL_MODE */;
/*!40014 SET FOREIGN_KEY_CHECKS=@OLD_FOREIGN_KEY_CHECKS */;
/*!40014 SET UNIQUE_CHECKS=@OLD_UNIQUE_CHECKS */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
/*!40111 SET SQL_NOTES=@OLD_SQL_NOTES */;

-- Dump completed on 2021-06-01 16:27:13
