-- phpMyAdmin SQL Dump
-- version 5.0.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Aug 24, 2021 at 10:59 PM
-- Server version: 10.4.11-MariaDB
-- PHP Version: 7.4.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET AUTOCOMMIT = 0;
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `empdb`
--

-- --------------------------------------------------------

--
-- Table structure for table `compliance`
--

CREATE TABLE `compliance` (
  `complianceid` int(10) NOT NULL,
  `rltype` varchar(15) NOT NULL,
  `details` varchar(250) NOT NULL,
  `createdate` date NOT NULL,
  `department_id` int(10) NOT NULL,
  `status` tinyint(4) NOT NULL DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `compliance`
--

INSERT INTO `compliance` (`complianceid`, `rltype`, `details`, `createdate`, `department_id`, `status`) VALUES
(1, 'Type 1', 'Regulation Description goes Here.\r\nThis is the description.', '2021-08-22', 1, 0);

-- --------------------------------------------------------

--
-- Table structure for table `department`
--

CREATE TABLE `department` (
  `department_id` int(11) NOT NULL,
  `department_nm` varchar(25) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `department`
--

INSERT INTO `department` (`department_id`, `department_nm`) VALUES
(1, 'IT'),
(2, 'Operations');

-- --------------------------------------------------------

--
-- Table structure for table `employees`
--

CREATE TABLE `employees` (
  `empid` int(10) NOT NULL,
  `firstname` varchar(45) NOT NULL,
  `lastname` varchar(45) NOT NULL,
  `dob` date NOT NULL,
  `email` varchar(100) NOT NULL,
  `department_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `employees`
--

INSERT INTO `employees` (`empid`, `firstname`, `lastname`, `dob`, `email`, `department_id`) VALUES
(1, 'Nishkarsh', 'Makhija', '1995-06-11', 'nishkarshit3018@gmail.com', 1),
(3, 'Nikhil', 'Makhija', '1995-06-11', 'ni@gmail.com', 1);

-- --------------------------------------------------------

--
-- Table structure for table `login_master`
--

CREATE TABLE `login_master` (
  `userid` int(10) NOT NULL,
  `password` varchar(50) NOT NULL,
  `role` varchar(20) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `login_master`
--

INSERT INTO `login_master` (`userid`, `password`, `role`) VALUES
(1, '202cb962ac5975b964b7152d234b70', 'admin'),
(3, '202cb962ac5975b964b7152d234b70', 'employee');

-- --------------------------------------------------------

--
-- Table structure for table `statusreport`
--

CREATE TABLE `statusreport` (
  `statusrptid` int(10) NOT NULL,
  `complianceid` int(11) NOT NULL,
  `empid` int(11) NOT NULL,
  `comments` text NOT NULL,
  `createdate` date NOT NULL,
  `department_id` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

--
-- Dumping data for table `statusreport`
--

INSERT INTO `statusreport` (`statusrptid`, `complianceid`, `empid`, `comments`, `createdate`, `department_id`) VALUES
(1, 1, 3, 'This is a comment', '2021-08-22', 1);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `compliance`
--
ALTER TABLE `compliance`
  ADD PRIMARY KEY (`complianceid`),
  ADD KEY `FK_k5dmh3lmrupvp95sc23094neu` (`department_id`);

--
-- Indexes for table `department`
--
ALTER TABLE `department`
  ADD PRIMARY KEY (`department_id`);

--
-- Indexes for table `employees`
--
ALTER TABLE `employees`
  ADD PRIMARY KEY (`empid`),
  ADD KEY `FK_hvs7qecyj0tfhgx75kdj0mxuk` (`department_id`);

--
-- Indexes for table `login_master`
--
ALTER TABLE `login_master`
  ADD PRIMARY KEY (`userid`);

--
-- Indexes for table `statusreport`
--
ALTER TABLE `statusreport`
  ADD PRIMARY KEY (`statusrptid`),
  ADD KEY `FK_on1nnti8whf7rhe5fia7geuw0` (`complianceid`),
  ADD KEY `FK_m35nstqmoju7qfh6x29i8is0w` (`department_id`),
  ADD KEY `FK_hp4gmlbsf29a8eteicjme3s6u` (`empid`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `compliance`
--
ALTER TABLE `compliance`
  MODIFY `complianceid` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- AUTO_INCREMENT for table `department`
--
ALTER TABLE `department`
  MODIFY `department_id` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT for table `employees`
--
ALTER TABLE `employees`
  MODIFY `empid` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `statusreport`
--
ALTER TABLE `statusreport`
  MODIFY `statusrptid` int(10) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `compliance`
--
ALTER TABLE `compliance`
  ADD CONSTRAINT `FK_k5dmh3lmrupvp95sc23094neu` FOREIGN KEY (`department_id`) REFERENCES `department` (`department_id`);

--
-- Constraints for table `employees`
--
ALTER TABLE `employees`
  ADD CONSTRAINT `FK_hvs7qecyj0tfhgx75kdj0mxuk` FOREIGN KEY (`department_id`) REFERENCES `department` (`department_id`);

--
-- Constraints for table `statusreport`
--
ALTER TABLE `statusreport`
  ADD CONSTRAINT `FK_hp4gmlbsf29a8eteicjme3s6u` FOREIGN KEY (`empid`) REFERENCES `employees` (`empid`),
  ADD CONSTRAINT `FK_m35nstqmoju7qfh6x29i8is0w` FOREIGN KEY (`department_id`) REFERENCES `department` (`department_id`),
  ADD CONSTRAINT `FK_on1nnti8whf7rhe5fia7geuw0` FOREIGN KEY (`complianceid`) REFERENCES `compliance` (`complianceid`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
