-- phpMyAdmin SQL Dump
-- version 4.1.14
-- http://www.phpmyadmin.net
--
-- Host: 127.0.0.1
-- Generation Time: May 26, 2017 at 02:29 PM
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
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(20) NOT NULL,
  `state` varchar(20) NOT NULL,
  `city` varchar(20) NOT NULL,
  `street` varchar(30) NOT NULL,
  `number` int(4) NOT NULL,
  `description` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `apartmanisobe`
--

CREATE TABLE IF NOT EXISTS `apartmanisobe` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idapartman` int(11) unsigned NOT NULL,
  `idsoba` int(11) unsigned NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idapartman` (`idapartman`),
  KEY `idsoba` (`idsoba`),
  KEY `idapartman_2` (`idapartman`),
  KEY `idsoba_2` (`idsoba`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `korisnik`
--

CREATE TABLE IF NOT EXISTS `korisnik` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `username` varchar(20) NOT NULL,
  `password` varchar(20) NOT NULL,
  `name` varchar(20) NOT NULL,
  `lastname` varchar(20) NOT NULL,
  `phone` varchar(20) NOT NULL,
  `email` varchar(30) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `username` (`username`,`email`),
  KEY `id` (`id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

-- --------------------------------------------------------

--
-- Table structure for table `kupac`
--

CREATE TABLE IF NOT EXISTS `kupac` (
  `idkorisnik` int(11) unsigned NOT NULL,
  `brkartice` varchar(30) NOT NULL,
  `idrezervacija` int(11) unsigned NOT NULL,
  KEY `idrezervacija` (`idrezervacija`),
  KEY `idkorisnik` (`idkorisnik`),
  KEY `idrezervacija_2` (`idrezervacija`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `prodavac`
--

CREATE TABLE IF NOT EXISTS `prodavac` (
  `idkorisnik` int(11) unsigned NOT NULL,
  `brojterminala` int(20) NOT NULL,
  `idapartmana` int(11) unsigned DEFAULT NULL,
  UNIQUE KEY `idkorisnik` (`idkorisnik`,`idapartmana`),
  KEY `idkorisnik_2` (`idkorisnik`),
  KEY `idapartmana` (`idapartmana`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `rezervacija`
--

CREATE TABLE IF NOT EXISTS `rezervacija` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `idapartmanisobe` int(10) unsigned NOT NULL,
  `datumprijave` date NOT NULL,
  `datumodjave` date NOT NULL,
  PRIMARY KEY (`id`),
  KEY `idapartmanisobe` (`idapartmanisobe`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

-- --------------------------------------------------------

--
-- Table structure for table `soba`
--

CREATE TABLE IF NOT EXISTS `soba` (
  `id` int(11) unsigned NOT NULL AUTO_INCREMENT,
  `brosoba` int(2) NOT NULL,
  `opis` text NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1 AUTO_INCREMENT=1 ;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `apartmanisobe`
--
ALTER TABLE `apartmanisobe`
  ADD CONSTRAINT `apartmanisobe_ibfk_1` FOREIGN KEY (`idapartman`) REFERENCES `apartman` (`id`) ON DELETE CASCADE ON UPDATE CASCADE,
  ADD CONSTRAINT `apartmanisobe_ibfk_2` FOREIGN KEY (`idsoba`) REFERENCES `soba` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `kupac`
--
ALTER TABLE `kupac`
  ADD CONSTRAINT `kupac_ibfk_3` FOREIGN KEY (`idrezervacija`) REFERENCES `rezervacija` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `kupac_ibfk_2` FOREIGN KEY (`idkorisnik`) REFERENCES `korisnik` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `prodavac`
--
ALTER TABLE `prodavac`
  ADD CONSTRAINT `prodavac_ibfk_2` FOREIGN KEY (`idapartmana`) REFERENCES `apartman` (`id`) ON DELETE SET NULL ON UPDATE SET NULL,
  ADD CONSTRAINT `prodavac_ibfk_1` FOREIGN KEY (`idkorisnik`) REFERENCES `korisnik` (`id`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `rezervacija`
--
ALTER TABLE `rezervacija`
  ADD CONSTRAINT `rezervacija_ibfk_1` FOREIGN KEY (`idapartmanisobe`) REFERENCES `apartmanisobe` (`id`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
