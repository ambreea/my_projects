-- phpMyAdmin SQL Dump
-- version 5.0.2
-- https://www.phpmyadmin.net/
--
-- Host: localhost
-- Generation Time: Jun 02, 2021 at 03:10 PM
-- Server version: 10.4.13-MariaDB
-- PHP Version: 7.4.7

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `assignment_db_final`
--
CREATE DATABASE IF NOT EXISTS `assignment_db_final` DEFAULT CHARACTER SET utf8 COLLATE utf8_unicode_ci;
USE `assignment_db_final`;

DELIMITER $$
--
-- Procedures
--
DROP PROCEDURE IF EXISTS `introducere_utilizator`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `introducere_utilizator` (`new_prenume` VARCHAR(45), `new_nume` VARCHAR(45), `new_gen` ENUM('M','F'), `new_data_nasterii` DATE, `new_locul_nasterii` VARCHAR(45), `new_nume_utilizator` VARCHAR(45), `new_parola` VARCHAR(30), `new_email` VARCHAR(45), `new_telefon` VARCHAR(15), `new_data_inregistrarii` DATETIME)  begin
insert into utilizator (prenume, nume, gen, data_nasterii, locul_nasterii, nume_utilizator, parola, email, telefon, data_inregistrarii) values (new_prenume, new_nume, new_gen, new_data_nasterii, new_locul_nasterii, new_nume_utilizator, new_parola, new_email, new_telefon, new_data_inregistrarii);
end$$

DROP PROCEDURE IF EXISTS `modificare_utilizator`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `modificare_utilizator` (`new_utilizator_id` INT, `new_prenume` VARCHAR(45), `new_nume` VARCHAR(45), `new_gen` ENUM('M','F',''), `new_data_nasterii` DATE, `new_locul_nasterii` VARCHAR(45), `new_nume_utilizator` VARCHAR(45), `new_parola` VARCHAR(30), `new_email` VARCHAR(45), `new_telefon` VARCHAR(15), `new_data_inregistrarii` DATETIME, `new_profil` TEXT, `new_fotografie` MEDIUMBLOB, `new_cv` MEDIUMBLOB)  begin

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
end$$

DROP PROCEDURE IF EXISTS `sterge_utilizator`$$
CREATE DEFINER=`root`@`localhost` PROCEDURE `sterge_utilizator` (IN `new_utilizator_id` INT)  begin

IF EXISTS(SELECT 1 FROM utilizator WHERE utilizator_id=new_utilizator_id) THEN
DELETE FROM utilizator WHERE utilizator_id=new_utilizator_id;
SELECT "Stergerea datelor utilizatorului au fost realizate cu succes";
ELSE
SELECT "Valoarea id-ului nu a fost gasita";
END IF;

end$$

--
-- Functions
--
DROP FUNCTION IF EXISTS `nr_prieteni`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `nr_prieteni` (`new_utilizator_id` INT) RETURNS INT(11) begin
set @count_prieteni1 = 0;
set @count_prieteni2 = 0;
select count(utilizator_id1) from prieteni where new_utilizator_id = utilizator_id1 into @count_prieteni1;
select count(utilizator_id2) from prieteni where new_utilizator_id = utilizator_id2 into @count_prieteni2;
return @count_prieteni1 + @count_prieteni2;
end$$

DROP FUNCTION IF EXISTS `number_friends`$$
CREATE DEFINER=`root`@`localhost` FUNCTION `number_friends` (`id_param` INT(11)) RETURNS INT(11) BEGIN
IF exists(SELECT 1 FROM utilizator WHERE utilizator_id = id_param) THEN
SELECT COUNT(*) INTO @numar_prieteni FROM prieteni WHERE (utilizator_id1 = id_param OR utilizator_id2 = id_param) AND stare_prietenie = 'Acceptat';
RETURN @numar_prieteni;
ELSE
RETURN -1;
END IF;
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Stand-in structure for view `dateutilizator`
-- (See below for the actual view)
--
DROP VIEW IF EXISTS `dateutilizator`;
CREATE TABLE `dateutilizator` (
`prenume` varchar(45)
,`nume` varchar(45)
,`data_nasterii` date
,`locul_nasterii` varchar(45)
);

-- --------------------------------------------------------

--
-- Table structure for table `postare`
--

DROP TABLE IF EXISTS `postare`;
CREATE TABLE `postare` (
  `postare_id` int(11) NOT NULL,
  `utilizator_id` bigint(20) NOT NULL,
  `titlu` varchar(100) COLLATE utf8_unicode_ci NOT NULL,
  `mesaj` tinytext COLLATE utf8_unicode_ci NOT NULL,
  `data_postare` datetime NOT NULL,
  `data_actualizare` datetime DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `postare`
--

INSERT INTO `postare` (`postare_id`, `utilizator_id`, `titlu`, `mesaj`, `data_postare`, `data_actualizare`) VALUES
(3, 2, 'Your shoes', 'You have to do what is right for you...No one walks in your shoes.', '2021-03-05 00:00:00', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `prieteni`
--

DROP TABLE IF EXISTS `prieteni`;
CREATE TABLE `prieteni` (
  `prieteni_id` bigint(20) NOT NULL,
  `utilizator_id1` bigint(20) NOT NULL,
  `utilizator_id2` bigint(20) NOT NULL,
  `stare_prietenie` enum('In asteptare','Acceptat','Respins') COLLATE utf8_unicode_ci NOT NULL,
  `data_prietenie` datetime NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `prieteni`
--

INSERT INTO `prieteni` (`prieteni_id`, `utilizator_id1`, `utilizator_id2`, `stare_prietenie`, `data_prietenie`) VALUES
(1, 3, 5, 'In asteptare', '2021-06-04 15:07:59'),
(3, 2, 4, 'Acceptat', '2019-09-12 15:14:26');

--
-- Triggers `prieteni`
--
DROP TRIGGER IF EXISTS `prevent_duplicates_prieteni_insert`;
DELIMITER $$
CREATE TRIGGER `prevent_duplicates_prieteni_insert` BEFORE INSERT ON `prieteni` FOR EACH ROW BEGIN

IF EXISTS(SELECT 1 
              FROM prieteni
              WHERE new.utilizator_id1 = new.utilizator_id2) THEN
SIGNAL SQLSTATE '45000' 
SET MESSAGE_TEXT = 'valorile id-urile nu pot fi la fel';
END IF;

IF EXISTS(SELECT prieteni_id
              FROM prieteni
              WHERE (`prieteni`.utilizator_id1 = new.utilizator_id1 AND `prieteni`.utilizator_id2 = new.utilizator_id2)) THEN
SIGNAL SQLSTATE '45000' 
SET MESSAGE_TEXT = 'valorile id-urile se repeta';
END IF;

IF EXISTS(SELECT prieteni_id
              FROM prieteni
              WHERE (`prieteni`.utilizator_id1 = new.utilizator_id2 AND `prieteni`.utilizator_id2 = new.utilizator_id1)) THEN
SIGNAL SQLSTATE '45000' 
SET MESSAGE_TEXT = 'valorile id-urile se repeta intr-o pereche';
END IF;
END
$$
DELIMITER ;
DROP TRIGGER IF EXISTS `prevent_duplicates_prieteni_update`;
DELIMITER $$
CREATE TRIGGER `prevent_duplicates_prieteni_update` BEFORE UPDATE ON `prieteni` FOR EACH ROW BEGIN

IF EXISTS(SELECT 1 
              FROM prieteni
              WHERE new.utilizator_id1 = new.utilizator_id2) THEN
SIGNAL SQLSTATE '45000' 
SET MESSAGE_TEXT = 'valorile id-urile nu pot fi la fel';
END IF;

IF EXISTS(SELECT prieteni_id
              FROM prieteni
              WHERE (`prieteni`.utilizator_id1 = new.utilizator_id1 AND `prieteni`.utilizator_id2 = new.utilizator_id2)) THEN
SIGNAL SQLSTATE '45000' 
SET MESSAGE_TEXT = 'valorile id-urile se repeta';
END IF;

IF EXISTS(SELECT prieteni_id
              FROM prieteni
              WHERE (`prieteni`.utilizator_id1 = new.utilizator_id2 AND `prieteni`.utilizator_id2 = new.utilizator_id1)) THEN
SIGNAL SQLSTATE '45000' 
SET MESSAGE_TEXT = 'valorile id-urile se repeta intr-o pereche';
END IF;
END
$$
DELIMITER ;

-- --------------------------------------------------------

--
-- Table structure for table `utilizator`
--

DROP TABLE IF EXISTS `utilizator`;
CREATE TABLE `utilizator` (
  `utilizator_id` bigint(20) NOT NULL,
  `prenume` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `nume` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `gen` enum('M','F') COLLATE utf8_unicode_ci NOT NULL,
  `data_nasterii` date NOT NULL DEFAULT '0000-00-00',
  `locul_nasterii` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `nume_utilizator` varchar(45) COLLATE utf8_unicode_ci NOT NULL,
  `parola` varchar(30) COLLATE utf8_unicode_ci NOT NULL,
  `email` varchar(45) COLLATE utf8_unicode_ci DEFAULT NULL,
  `telefon` varchar(15) COLLATE utf8_unicode_ci DEFAULT NULL,
  `data_inregistrarii` datetime NOT NULL DEFAULT '0000-00-00 00:00:00',
  `profil` text COLLATE utf8_unicode_ci DEFAULT NULL,
  `fotografie` mediumblob DEFAULT NULL,
  `cv` mediumblob DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_unicode_ci;

--
-- Dumping data for table `utilizator`
--

INSERT INTO `utilizator` (`utilizator_id`, `prenume`, `nume`, `gen`, `data_nasterii`, `locul_nasterii`, `nume_utilizator`, `parola`, `email`, `telefon`, `data_inregistrarii`, `profil`, `fotografie`, `cv`) VALUES
(2, 'Petre', 'Ion', 'M', '1987-12-01', 'Constanta', 'PetreIon', 'Petrisor87', 'petreion@gmail.com', '07056260', '2019-09-12 00:00:00', 'Happy', NULL, NULL),
(3, 'Iris', 'Anghel', 'F', '1975-06-05', 'Alba Iulia', 'Iris', 'floaredeiris', 'irisanghel@gmail.com', '0756895698', '2010-05-01 00:00:00', 'Travelling...', NULL, NULL),
(4, 'Razvan', 'Gheorghe', 'M', '1988-10-15', 'Baia Mare', 'Razvan', 'Razvan@1234', 'razvangheorghe@gmail.com', '0758967412', '2005-01-12 00:00:00', 'La meci', NULL, NULL),
(5, 'Iris', 'Pop', 'F', '1985-02-12', 'Iasi', 'floare-de-iris', 'iris55688', 'pop.iris@mail.com', '0756125897', '2008-08-12 00:00:00', NULL, NULL, NULL),
(6, 'Vlad', 'Alexandrescu', 'M', '1988-09-12', 'Timis', 'vladut', 'sda5463', 'vlad_alex@gmail.com', '0745781236', '2001-03-30 00:00:00', 'coffee time', NULL, NULL);

-- --------------------------------------------------------

--
-- Structure for view `dateutilizator`
--
DROP TABLE IF EXISTS `dateutilizator`;

DROP VIEW IF EXISTS `dateutilizator`;
CREATE ALGORITHM=UNDEFINED DEFINER=`root`@`localhost` SQL SECURITY DEFINER VIEW `dateutilizator`  AS  select `utilizator`.`prenume` AS `prenume`,`utilizator`.`nume` AS `nume`,`utilizator`.`data_nasterii` AS `data_nasterii`,`utilizator`.`locul_nasterii` AS `locul_nasterii` from `utilizator` ;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `postare`
--
ALTER TABLE `postare`
  ADD PRIMARY KEY (`postare_id`),
  ADD KEY `idx_utilizator_id` (`utilizator_id`);
ALTER TABLE `postare` ADD FULLTEXT KEY `IDX_MESAJ` (`mesaj`);

--
-- Indexes for table `prieteni`
--
ALTER TABLE `prieteni`
  ADD PRIMARY KEY (`prieteni_id`),
  ADD KEY `idx_utilizator_id1` (`utilizator_id1`),
  ADD KEY `idx_utilizator_id2` (`utilizator_id2`);

--
-- Indexes for table `utilizator`
--
ALTER TABLE `utilizator`
  ADD PRIMARY KEY (`utilizator_id`),
  ADD UNIQUE KEY `nume_utilizator_UNIQUE` (`nume_utilizator`),
  ADD UNIQUE KEY `email_UNIQUE` (`email`),
  ADD UNIQUE KEY `telefon_UNIQUE` (`telefon`),
  ADD KEY `IDX_NUME` (`nume`,`prenume`),
  ADD KEY `IDX_UTILIZATOR` (`nume_utilizator`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `postare`
--
ALTER TABLE `postare`
  MODIFY `postare_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `prieteni`
--
ALTER TABLE `prieteni`
  MODIFY `prieteni_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;

--
-- AUTO_INCREMENT for table `utilizator`
--
ALTER TABLE `utilizator`
  MODIFY `utilizator_id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `postare`
--
ALTER TABLE `postare`
  ADD CONSTRAINT `postare_ibfk_1` FOREIGN KEY (`utilizator_id`) REFERENCES `utilizator` (`utilizator_id`) ON DELETE CASCADE;

--
-- Constraints for table `prieteni`
--
ALTER TABLE `prieteni`
  ADD CONSTRAINT `prieteni_ibfk_1` FOREIGN KEY (`utilizator_id1`) REFERENCES `utilizator` (`utilizator_id`) ON DELETE CASCADE,
  ADD CONSTRAINT `prieteni_ibfk_2` FOREIGN KEY (`utilizator_id2`) REFERENCES `utilizator` (`utilizator_id`) ON DELETE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
