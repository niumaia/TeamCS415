-- phpMyAdmin SQL Dump
-- version 4.0.4
-- http://www.phpmyadmin.net
--
-- Host: localhost
-- Generation Time: Sep 17, 2015 at 08:41 AM
-- Server version: 5.6.12-log
-- PHP Version: 5.4.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Database: `uspread`
--
CREATE DATABASE IF NOT EXISTS `uspread` DEFAULT CHARACTER SET latin1 COLLATE latin1_swedish_ci;
USE `uspread`;

-- --------------------------------------------------------

--
-- Table structure for table `book`
--

CREATE TABLE IF NOT EXISTS `book` (
  `bk_id` int(11) NOT NULL AUTO_INCREMENT,
  `bk_title` varchar(200) NOT NULL,
  `bk_author` varchar(200) NOT NULL,
  `bk_publisher` varchar(200) NOT NULL,
  `bk_ISBN` varchar(200) NOT NULL,
  `bk_edition` varchar(200) NOT NULL,
  PRIMARY KEY (`bk_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `book`
--

INSERT INTO `book` (`bk_id`, `bk_title`, `bk_author`, `bk_publisher`, `bk_ISBN`, `bk_edition`) VALUES
(1, 'Harry Potter', 'JK Rowling', 'idk', '1234', '3'),
(2, 'Percy Jackson', 'xxx', 'idk', '123', '1');

-- --------------------------------------------------------

--
-- Table structure for table `bookcopy`
--

CREATE TABLE IF NOT EXISTS `bookcopy` (
  `bkCopyID` int(11) NOT NULL AUTO_INCREMENT,
  `bk_id` int(11) NOT NULL,
  `bkCopyNum` int(11) NOT NULL,
  `bkCopyEd` varchar(60) NOT NULL,
  `bkCopyPublishYear` date NOT NULL,
  `bkCatNum` varchar(60) NOT NULL,
  `copyStat_id` int(11) NOT NULL,
  PRIMARY KEY (`bkCopyID`),
  KEY `bk_id` (`bk_id`),
  KEY `copyStat_id` (`copyStat_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `bookcopy`
--

INSERT INTO `bookcopy` (`bkCopyID`, `bk_id`, `bkCopyNum`, `bkCopyEd`, `bkCopyPublishYear`, `bkCatNum`, `copyStat_id`) VALUES
(1, 1, 1, '3', '2005-09-01', '1111', 2),
(2, 1, 2, '3', '2005-09-01', '1211', 1);

-- --------------------------------------------------------

--
-- Table structure for table `copy_status`
--

CREATE TABLE IF NOT EXISTS `copy_status` (
  `copyStat_id` int(11) NOT NULL AUTO_INCREMENT,
  `copyStat_desc` varchar(60) NOT NULL,
  PRIMARY KEY (`copyStat_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=5 ;

--
-- Dumping data for table `copy_status`
--

INSERT INTO `copy_status` (`copyStat_id`, `copyStat_desc`) VALUES
(1, 'Available'),
(2, 'On Loan'),
(3, 'Reserved'),
(4, 'Overdue');

-- --------------------------------------------------------

--
-- Table structure for table `duestatus`
--

CREATE TABLE IF NOT EXISTS `duestatus` (
  `dueID` int(11) NOT NULL AUTO_INCREMENT,
  `dueDesc` varchar(60) NOT NULL,
  PRIMARY KEY (`dueID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `duestatus`
--

INSERT INTO `duestatus` (`dueID`, `dueDesc`) VALUES
(1, 'Overdue'),
(2, 'On Time');

-- --------------------------------------------------------

--
-- Table structure for table `issue`
--

CREATE TABLE IF NOT EXISTS `issue` (
  `issueID` int(11) NOT NULL AUTO_INCREMENT,
  `bkCopyID` int(11) NOT NULL,
  `StudentID` varchar(60) NOT NULL,
  `issueDate` date NOT NULL,
  `issueDueDate` date NOT NULL,
  `checkInDate` date DEFAULT NULL,
  `dueID` int(11) DEFAULT NULL,
  `issueFine` int(11) DEFAULT NULL,
  PRIMARY KEY (`issueID`),
  KEY `bkCopyID` (`bkCopyID`),
  KEY `dueID` (`dueID`),
  KEY `user_id` (`StudentID`),
  KEY `user_id_2` (`StudentID`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=3 ;

--
-- Dumping data for table `issue`
--

INSERT INTO `issue` (`issueID`, `bkCopyID`, `StudentID`, `issueDate`, `issueDueDate`, `checkInDate`, `dueID`, `issueFine`) VALUES
(2, 1, 's11079253', '2015-09-01', '2015-09-17', NULL, NULL, NULL);

-- --------------------------------------------------------

--
-- Table structure for table `reserve`
--

CREATE TABLE IF NOT EXISTS `reserve` (
  `reserveID` int(11) NOT NULL,
  `bkCopyID` int(11) NOT NULL,
  `StudentID` varchar(60) NOT NULL,
  `reserveDate` date NOT NULL,
  `pickupDate` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- --------------------------------------------------------

--
-- Table structure for table `users`
--

CREATE TABLE IF NOT EXISTS `users` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_fname` varchar(60) NOT NULL,
  `user_lname` varchar(60) NOT NULL,
  `user_dob` varchar(60) NOT NULL,
  `user_gender` varchar(60) NOT NULL,
  `user_address` varchar(60) NOT NULL,
  `user_contact` varchar(60) NOT NULL,
  `user_email` varchar(60) NOT NULL,
  `user_password` varchar(60) NOT NULL,
  `user_role` int(11) NOT NULL,
  `user_id` varchar(60) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `user_id` (`user_id`),
  KEY `user_id_2` (`user_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=2 ;

--
-- Dumping data for table `users`
--

INSERT INTO `users` (`id`, `user_fname`, `user_lname`, `user_dob`, `user_gender`, `user_address`, `user_contact`, `user_email`, `user_password`, `user_role`, `user_id`) VALUES
(1, 'akshay', 'deo', '30 Aug 1993', 'Male', 'Toorak', '9042847', 'akshaydeo123@gmail.com', '202cb962ac59075b964b07152d234b70', 1, 's11079253');

-- --------------------------------------------------------

--
-- Table structure for table `video`
--

CREATE TABLE IF NOT EXISTS `video` (
  `vid_id` int(11) NOT NULL,
  `vid_name` text CHARACTER SET utf8 NOT NULL,
  `vid_date_add` date NOT NULL,
  `vid_url` text CHARACTER SET utf8 NOT NULL,
  `vid_url_id` text CHARACTER SET utf8 NOT NULL,
  `vid_img` varchar(200) CHARACTER SET utf8 NOT NULL,
  PRIMARY KEY (`vid_date_add`),
  UNIQUE KEY `vid_id` (`vid_id`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Dumping data for table `video`
--

INSERT INTO `video` (`vid_id`, `vid_name`, `vid_date_add`, `vid_url`, `vid_url_id`, `vid_img`) VALUES
(1, 'Software Foundation Fiji', '2013-05-29', 'http://www.youtube.com/watch?v=iv1zO_eEd8c', 'iv1zO_eEd8c', '../images/img1.gif'),
(2, 'Equalizer', '2014-10-01', 'http://www.youtube.com/watch?v=jAI7rF0eQyQ', 'jAI7rF0eQyQ', ''),
(3, 'Dracular untold', '2014-10-04', 'http://www.youtube.com/watch?v=hZFZxEnB7ws', 'hZFZxEnB7ws', 'mnj'),
(4, 'The Hobbit', '2014-10-05', 'http://www.youtube.com/watch?v=ZSzeFFsKEt4', 'ZSzeFFsKEt4', '2wdads');

--
-- Constraints for dumped tables
--

--
-- Constraints for table `bookcopy`
--
ALTER TABLE `bookcopy`
  ADD CONSTRAINT `bookcopy_ibfk_1` FOREIGN KEY (`bk_id`) REFERENCES `book` (`bk_id`),
  ADD CONSTRAINT `bookcopy_ibfk_2` FOREIGN KEY (`copyStat_id`) REFERENCES `copy_status` (`copyStat_id`);

--
-- Constraints for table `issue`
--
ALTER TABLE `issue`
  ADD CONSTRAINT `issue_ibfk_1` FOREIGN KEY (`bkCopyID`) REFERENCES `bookcopy` (`bkCopyID`),
  ADD CONSTRAINT `issue_ibfk_2` FOREIGN KEY (`dueID`) REFERENCES `duestatus` (`dueID`),
  ADD CONSTRAINT `issue_ibfk_3` FOREIGN KEY (`StudentID`) REFERENCES `users` (`user_id`);

--
-- Constraints for table `users`
--
ALTER TABLE `users`
  ADD CONSTRAINT `users_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `users` (`user_id`);

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
