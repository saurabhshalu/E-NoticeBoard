-- phpMyAdmin SQL Dump
-- version 4.8.4
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1:3306
-- Generation Time: May 02, 2019 at 12:45 PM
-- Server version: 5.7.24
-- PHP Version: 7.2.14

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dbnotice`
--

-- --------------------------------------------------------

--
-- Table structure for table `tbladmin`
--

DROP TABLE IF EXISTS `tbladmin`;
CREATE TABLE IF NOT EXISTS `tbladmin` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uniqueid` varchar(30) NOT NULL,
  `password` varchar(30) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=4 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tbladmin`
--

INSERT INTO `tbladmin` (`id`, `uniqueid`, `password`) VALUES
(1, 'saurabh', 'saurabh@7110'),
(2, 'shivam', 'shivam@7092'),
(3, 'admin', 'adm1n');

-- --------------------------------------------------------

--
-- Table structure for table `tblbranch`
--

DROP TABLE IF EXISTS `tblbranch`;
CREATE TABLE IF NOT EXISTS `tblbranch` (
  `branchcode` int(11) NOT NULL,
  `branchname` varchar(75) NOT NULL,
  PRIMARY KEY (`branchcode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tblbranch`
--

INSERT INTO `tblbranch` (`branchcode`, `branchname`) VALUES
(2, 'Automobile Engineering'),
(6, 'Civil Engineering'),
(7, 'Computer Engineering'),
(9, 'Electrical Engineering'),
(11, 'Electronics & Communication Engineering'),
(16, 'Information Technology'),
(19, 'Mechanical Engineering');

-- --------------------------------------------------------

--
-- Table structure for table `tblcollege`
--

DROP TABLE IF EXISTS `tblcollege`;
CREATE TABLE IF NOT EXISTS `tblcollege` (
  `collegecode` int(11) NOT NULL,
  `collegename` varchar(150) NOT NULL,
  PRIMARY KEY (`collegecode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tblcollege`
--

INSERT INTO `tblcollege` (`collegecode`, `collegename`) VALUES
(13, 'Government Engineering College Gandinagar'),
(16, 'Government Engineering College Modasa'),
(17, 'Vishwakarma Government Engineering College, Chandkheda'),
(28, 'L. D. College Of Engineering, Ahmedabad'),
(32, 'L. J. Institute Of Engineering And Technology, Ahmedabad'),
(42, 'Sarvajanik College Of Engineering & Technology, Surat');

-- --------------------------------------------------------

--
-- Table structure for table `tblnotice`
--

DROP TABLE IF EXISTS `tblnotice`;
CREATE TABLE IF NOT EXISTS `tblnotice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `title` varchar(100) NOT NULL,
  `body` text NOT NULL,
  `attachment` varchar(35) NOT NULL COMMENT 't_id+c_id+last_id.extension',
  `semester` int(11) NOT NULL,
  `branchcode` int(11) NOT NULL,
  `collegecode` int(11) NOT NULL,
  `start_date` date NOT NULL,
  `end_date` date NOT NULL,
  `professorcode` varchar(20) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `professorcode` (`professorcode`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `tblprofessor`
--

DROP TABLE IF EXISTS `tblprofessor`;
CREATE TABLE IF NOT EXISTS `tblprofessor` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uniqueid` varchar(20) NOT NULL COMMENT 'professor_id',
  `name` varchar(30) NOT NULL,
  `phone` varchar(10) NOT NULL,
  `email` varchar(30) NOT NULL,
  `address` varchar(60) NOT NULL,
  `password` varchar(30) NOT NULL,
  `collegecode` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `professorid` (`uniqueid`),
  KEY `collegecode1` (`collegecode`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tblprofessor`
--

INSERT INTO `tblprofessor` (`id`, `uniqueid`, `name`, `phone`, `email`, `address`, `password`, `collegecode`) VALUES
(1, 'MKR', 'Mayuri Rathva', '0000000000', 'mayu00000@gmail.com', 'Ahmedabad', 'm00009', 16);

-- --------------------------------------------------------

--
-- Table structure for table `tblspecial`
--

DROP TABLE IF EXISTS `tblspecial`;
CREATE TABLE IF NOT EXISTS `tblspecial` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uniqueid` varchar(30) NOT NULL,
  `collegecode` int(11) NOT NULL,
  `status` tinyint(1) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tblspecial`
--

INSERT INTO `tblspecial` (`id`, `uniqueid`, `collegecode`, `status`) VALUES
(1, 'MKR', 16, 1);

-- --------------------------------------------------------

--
-- Table structure for table `tblstudent`
--

DROP TABLE IF EXISTS `tblstudent`;
CREATE TABLE IF NOT EXISTS `tblstudent` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `uniqueid` varchar(12) NOT NULL COMMENT 'enrollment number',
  `name` varchar(50) NOT NULL,
  `branchcode` int(10) NOT NULL,
  `semester` int(11) NOT NULL,
  `phone` varchar(10) NOT NULL,
  `email` varchar(30) NOT NULL,
  `address` varchar(100) NOT NULL,
  `password` varchar(30) NOT NULL,
  `collegecode` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  UNIQUE KEY `enrollment` (`uniqueid`),
  KEY `collegecode` (`collegecode`),
  KEY `branch` (`branchcode`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=latin1;

--
-- Dumping data for table `tblstudent`
--

INSERT INTO `tblstudent` (`id`, `uniqueid`, `name`, `branchcode`, `semester`, `phone`, `email`, `address`, `password`, `collegecode`) VALUES
(1, '111111111111', 'Saurabh Verma', 7, 6, '1111111111', 'saurabh@gmail.com', 'Pethapur, Gandhinagar', '123456', 16),
(2, '111111111112', 'Prajapati Mansi', 7, 6, '7016418822', 'mansi@gmail.com', 'Pawan City, Modasa', 'mansi', 16);

--
-- Constraints for dumped tables
--

--
-- Constraints for table `tblnotice`
--
ALTER TABLE `tblnotice`
  ADD CONSTRAINT `professorcode` FOREIGN KEY (`professorcode`) REFERENCES `tblprofessor` (`uniqueid`);

--
-- Constraints for table `tblprofessor`
--
ALTER TABLE `tblprofessor`
  ADD CONSTRAINT `collegecode1` FOREIGN KEY (`collegecode`) REFERENCES `tblcollege` (`collegecode`);

--
-- Constraints for table `tblstudent`
--
ALTER TABLE `tblstudent`
  ADD CONSTRAINT `branchcode` FOREIGN KEY (`branchcode`) REFERENCES `tblbranch` (`branchcode`),
  ADD CONSTRAINT `collegecode` FOREIGN KEY (`collegecode`) REFERENCES `tblcollege` (`collegecode`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
