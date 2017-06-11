-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: Jun 11, 2017 at 02:12 PM
-- Server version: 5.6.17
-- PHP Version: 5.5.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `stanovi`
--

-- --------------------------------------------------------

--
-- Table structure for table `apartman`
--

CREATE TABLE IF NOT EXISTS `apartman` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `city` varchar(255) DEFAULT NULL,
  `description` longtext,
  `name` varchar(255) DEFAULT NULL,
  `number` int(11) DEFAULT NULL,
  `state` varchar(255) DEFAULT NULL,
  `street` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `apartman`
--

INSERT INTO `apartman` (`id`, `city`, `description`, `name`, `number`, `state`, `street`) VALUES
(1, 'Novi Sad', '', 'jednosoban', 24, 'Srbija', 'Vojvodjanska'),
(2, 'Kragujevac', 'Dodji i uzmi', 'jednosoban', 155, 'Srbija', 'Kragujevacka'),
(3, 'Zagreb', 'Lip i jeftin.. par tisuca.', 'trosoban', 47, 'Hrvatska', 'Zagrebacka Arena'),
(4, 'Bograd', 'Jako lep, NOV NOV!', 'dvosoban', 33, 'Srbija', 'Ustanicka');

-- --------------------------------------------------------

--
-- Table structure for table `apartmanisobe`
--

CREATE TABLE IF NOT EXISTS `apartmanisobe` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `idapartman` int(11) DEFAULT NULL,
  `idsoba` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_apartmanisobe_idapartman` (`idapartman`),
  KEY `FK_apartmanisobe_idsoba` (`idsoba`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `apartmanisobe`
--

INSERT INTO `apartmanisobe` (`id`, `idapartman`, `idsoba`) VALUES
(1, 3, 3),
(2, 2, 5),
(3, 3, 4),
(4, 4, 2),
(5, 4, 1),
(6, 4, 6);

-- --------------------------------------------------------

--
-- Table structure for table `korisnik`
--

CREATE TABLE IF NOT EXISTS `korisnik` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `DTYPE` varchar(31) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `lastname` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=13 ;

--
-- Dumping data for table `korisnik`
--

INSERT INTO `korisnik` (`id`, `DTYPE`, `email`, `lastname`, `name`, `password`, `phone`, `username`) VALUES
(1, 'Kupac', 'mitar@gmail.com', 'Mitric', 'Mitar', 'mitar', '066/8701871', 'mitar'),
(2, 'Kupac', 'katica@gmail.com', 'Katic', 'Katica', 'katica', '066/8701871', 'katica'),
(3, 'Kupac', 'darinka@gmail.com', 'Darinkic', 'Darinka', 'darinka', '066/8701871', 'darinka'),
(4, 'Kupac', 'miraleb@gmail.com', 'Miralebovic', 'Miraleb', 'miraleb', '066/8701871', 'miraleb'),
(5, 'Kupac', 'griza@gmail.com', 'Grizic', 'Griza', 'griza', '066/8701871', 'griza'),
(6, 'Kupac', 'vujko@gmail.com', 'Vujkovic', 'Vujko', 'vujko', '066/8701871', 'vujko'),
(7, 'Kupac', 'zivka@gmail.com', 'Zivkic', 'Zivka', 'zivka', '066/8701871', 'zivka'),
(8, 'Kupac', 'zlatimir@gmail.com', 'Zlatic', 'Zlatimir', 'zlatimir', '066/8701871', 'zlatimir'),
(9, 'Kupac', 'evnoje@gmail.com', 'Evnojic', 'Evnoje', 'evnoje', '066/8701871', 'evnoje'),
(10, 'Prodavac', 'mita@gmail.com', 'Mitic', 'Mita', 'mita', '066/8701871', 'mita'),
(11, 'Prodavac', 'milojica@gmail.com', 'Milojevic', 'Milojica', 'milojica', '066/8701871', 'milojica'),
(12, 'Prodavac', 'krsto@gmail.com', 'Krstajic', 'Krsto', 'krsto', '066/8701871', 'krsto');

-- --------------------------------------------------------

--
-- Table structure for table `kupac`
--

CREATE TABLE IF NOT EXISTS `kupac` (
  `id` int(11) NOT NULL,
  `brkartice` varchar(255) DEFAULT NULL,
  `idrezervacija` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_kupac_idrezervacija` (`idrezervacija`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `kupac`
--

INSERT INTO `kupac` (`id`, `brkartice`, `idrezervacija`) VALUES
(1, '766663161371', NULL),
(2, '990363161371', NULL),
(3, '785553161371', NULL),
(4, '275433161371', NULL),
(5, '215363161371', NULL),
(6, '115363161371', NULL),
(7, '032563161371', NULL),
(8, '634563161371', NULL),
(9, '233363161371', NULL);

-- --------------------------------------------------------

--
-- Table structure for table `prodavac`
--

CREATE TABLE IF NOT EXISTS `prodavac` (
  `id` int(11) NOT NULL,
  `brojterminala` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `prodavac`
--

INSERT INTO `prodavac` (`id`, `brojterminala`) VALUES
(10, 34),
(11, 35),
(12, 33);

-- --------------------------------------------------------

--
-- Table structure for table `prodavciapartmani`
--

CREATE TABLE IF NOT EXISTS `prodavciapartmani` (
  `apartmanid` int(11) NOT NULL,
  `prodavacid` int(11) NOT NULL,
  PRIMARY KEY (`apartmanid`,`prodavacid`),
  KEY `FK_prodavciapartmani_prodavacid` (`prodavacid`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `rezervacija`
--

CREATE TABLE IF NOT EXISTS `rezervacija` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `datumodjave` date DEFAULT NULL,
  `datumprijave` date DEFAULT NULL,
  `idapartmanisobe` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `FK_rezervacija_idapartmanisobe` (`idapartmanisobe`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `soba`
--

CREATE TABLE IF NOT EXISTS `soba` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `brosoba` int(11) DEFAULT NULL,
  `opis` longtext,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=7 ;

--
-- Dumping data for table `soba`
--

INSERT INTO `soba` (`id`, `brosoba`, `opis`) VALUES
(1, 3, 'lepa soba za par sa decom.'),
(2, 1, 'soba za studenta'),
(3, 4, 'soba za celu familiju'),
(4, 1, 'soba za jednu osobu sa kerom.'),
(5, 2, 'soba za dva sabana.'),
(6, 2, 'romanticna soba');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `apartmanisobe`
--
ALTER TABLE `apartmanisobe`
  ADD CONSTRAINT `FK_apartmanisobe_idsoba` FOREIGN KEY (`idsoba`) REFERENCES `soba` (`id`),
  ADD CONSTRAINT `FK_apartmanisobe_idapartman` FOREIGN KEY (`idapartman`) REFERENCES `apartman` (`id`);

--
-- Constraints for table `kupac`
--
ALTER TABLE `kupac`
  ADD CONSTRAINT `FK_kupac_idrezervacija` FOREIGN KEY (`idrezervacija`) REFERENCES `rezervacija` (`id`),
  ADD CONSTRAINT `FK_kupac_id` FOREIGN KEY (`id`) REFERENCES `korisnik` (`id`);

--
-- Constraints for table `prodavac`
--
ALTER TABLE `prodavac`
  ADD CONSTRAINT `FK_prodavac_id` FOREIGN KEY (`id`) REFERENCES `korisnik` (`id`);

--
-- Constraints for table `prodavciapartmani`
--
ALTER TABLE `prodavciapartmani`
  ADD CONSTRAINT `FK_prodavciapartmani_prodavacid` FOREIGN KEY (`prodavacid`) REFERENCES `korisnik` (`id`),
  ADD CONSTRAINT `FK_prodavciapartmani_apartmanid` FOREIGN KEY (`apartmanid`) REFERENCES `apartman` (`id`);

--
-- Constraints for table `rezervacija`
--
ALTER TABLE `rezervacija`
  ADD CONSTRAINT `FK_rezervacija_idapartmanisobe` FOREIGN KEY (`idapartmanisobe`) REFERENCES `apartmanisobe` (`id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
