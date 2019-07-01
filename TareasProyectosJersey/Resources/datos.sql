-- phpMyAdmin SQL Dump
-- version 4.0.2
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Feb 09, 2019 at 08:33 AM
-- Server version: 5.6.11-log
-- PHP Version: 5.4.15

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `tareas_proyectos`
--
CREATE DATABASE IF NOT EXISTS `tareas_proyectos` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `tareas_proyectos`;

-- --------------------------------------------------------

--
-- Table structure for table `proyecto`
--

DROP TABLE IF EXISTS `tarea`;
DROP TABLE IF EXISTS `proyecto`;
DROP TABLE IF EXISTS `usuario`;


CREATE TABLE IF NOT EXISTS `proyecto` (
  `pid` int(11) NOT NULL AUTO_INCREMENT,
  `fecha_fin` datetime DEFAULT NULL,
  `fecha_inicio` datetime DEFAULT NULL,
  `titulo` varchar(255) DEFAULT NULL,
  `responsable` int(11) NOT NULL,
  PRIMARY KEY (`pid`),
  KEY `FKtekngwrd5frq6d9axvrgfmqjm` (`responsable`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Truncate table before insert `proyecto`
--

TRUNCATE TABLE `proyecto`;
--
-- Dumping data for table `proyecto`
--

INSERT INTO `proyecto` (`pid`, `fecha_fin`, `fecha_inicio`, `titulo`, `responsable`) VALUES
(1, '2019-02-08 00:00:00', '2019-04-24 00:00:00', 'Aplicación Cli Medicos', 1),
(2, '2018-12-04 00:00:00', '2019-05-30 00:00:00', 'Rest API Medicos', 1);

-- --------------------------------------------------------

--
-- Table structure for table `tarea`
--

CREATE TABLE IF NOT EXISTS `tarea` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `duracion` double DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `proyecto` int(11) NOT NULL,
  `responsable` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `FK5iofeueonbswdnvl65vtjgovk` (`proyecto`),
  KEY `FK2mq9ouerae2p5x38kmsscenfr` (`responsable`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Truncate table before insert `tarea`
--

TRUNCATE TABLE `tarea`;
--
-- Dumping data for table `tarea`
--

INSERT INTO `tarea` (`id`, `duracion`, `nombre`, `proyecto`, `responsable`) VALUES
(1, 10, 'Crear proyecto', 1, 1);

-- --------------------------------------------------------

--
-- Table structure for table `usuario`
--

CREATE TABLE IF NOT EXISTS `usuario` (
  `uid` int(11) NOT NULL AUTO_INCREMENT,
  `email` varchar(255) DEFAULT NULL,
  `foto` varchar(255) DEFAULT NULL,
  `nombre` varchar(255) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`uid`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=4 ;

--
-- Truncate table before insert `usuario`
--

TRUNCATE TABLE `usuario`;
--
-- Dumping data for table `usuario`
--

INSERT INTO `usuario` (`uid`, `email`, `foto`, `nombre`,`password`) VALUES
(1, 'r@r.com', 'r.jgpg', 'Ricardo','rcrd'),
(2, 'a@a.com', 'ak.jpg', 'Ana K.','nk'),
(3, 'p@p.com', 'p.jpg', 'Petra','ptr');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `proyecto`
--
ALTER TABLE `proyecto`
  ADD CONSTRAINT `FKtekngwrd5frq6d9axvrgfmqjm` FOREIGN KEY (`responsable`) REFERENCES `usuario` (`uid`);

--
-- Constraints for table `tarea`
--
ALTER TABLE `tarea`
  ADD CONSTRAINT `FK2mq9ouerae2p5x38kmsscenfr` FOREIGN KEY (`responsable`) REFERENCES `usuario` (`uid`),
  ADD CONSTRAINT `FK5iofeueonbswdnvl65vtjgovk` FOREIGN KEY (`proyecto`) REFERENCES `proyecto` (`pid`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
