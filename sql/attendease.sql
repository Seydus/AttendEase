-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Jan 23, 2024 at 11:33 AM
-- Server version: 10.4.28-MariaDB
-- PHP Version: 8.2.4

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `attendease`
--

-- --------------------------------------------------------

--
-- Table structure for table `attendancerecord`
--

CREATE TABLE `attendancerecord` (
  `RecordID` int(11) NOT NULL,
  `Date` date NOT NULL,
  `Status` enum('present','absent','late') NOT NULL,
  `UserID` int(11) NOT NULL,
  `CourseID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `course`
--

CREATE TABLE `course` (
  `CourseID` int(11) NOT NULL,
  `CourseName` varchar(50) NOT NULL,
  `OverseerID` int(11) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `role`
--

CREATE TABLE `role` (
  `RoleID` int(11) NOT NULL,
  `RoleName` enum('Admin','Student','Faculty') DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `role`
--

INSERT INTO `role` (`RoleID`, `RoleName`) VALUES
(1, 'Student'),
(2, 'Faculty'),
(3, 'Admin');

-- --------------------------------------------------------

--
-- Table structure for table `user`
--

CREATE TABLE `user` (
  `UserID` int(11) NOT NULL,
  `Username` varchar(255) DEFAULT NULL,
  `FirstName` varchar(255) DEFAULT NULL,
  `LastName` varchar(255) DEFAULT NULL,
  `Email` varchar(255) DEFAULT NULL,
  `Password` varchar(255) DEFAULT NULL,
  `RoleID` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Dumping data for table `user`
--

INSERT INTO `user` (`UserID`, `Username`, `FirstName`, `LastName`, `Email`, `Password`, `RoleID`) VALUES
(12, 'asdasd', 'adasda', 'asdads', 'ads@gmail.com', '$2a$10$.BUDgsZvAgUFRfm6EQ/NR.SjGSriRaHQdvF4w6K/HgYzJ3A/ua8J6', NULL),
(13, 'asdasd', 'adasda', 'asdads', 'ads@gmail.com', '$2a$10$0dZwZRUAX.cAYmnkgzDDbOfSyvHE9dgY67znqTJlyRUQnbBzr8zpq', NULL),
(14, 'asdad', 'asda', 'asda', 'asdad@gmail.com', '$2a$10$tegd1JubxMusoMStOJ9KHOAnHDfKtTQLWeRKCNWopEE4MK6uk3w6m', NULL),
(15, 'adad', 'adad', 'asdad', 'adsad@gmail.com', '$2a$10$SgnM8yxsV8DbUE7kCzyV8eUPUuFevSd/K0ujNMjvSKwlGj.ioDak6', NULL),
(16, 'dads', 'asda', 'asda', 'asdad@gmail.com', '$2a$10$Dbk7mH0Qp9miCXpB4KBPf.BHEKQxZNDet9Cq./6UeRurxptsCFVaC', NULL),
(17, 'qeqeqe', 'eqweq', 'qweqe', 'qweqewe@gmail.com', '$2a$10$qFj.4HYDI2eVf3GdtdciBuN2oOVOpWS6Sfuu7wqZrFSvXwemUsxVW', NULL),
(18, 'qewqweq', 'qweqwe', 'qeqwe', 'qqew@gmail.com', '$2a$10$us3jdLPzHwf4w4zFi.212.smrkTKsbmR0zCZ50wn5UTi6iHkXRGpW', NULL),
(19, 'qweqwe', 'qweqe', 'qewqew', 'qweqw@gmail.com', '$2a$10$c.3JzJZMLr1XhAbCkiuvL.1DlVFVM17FkGU/M.K9Kzdzk/.YWGyo2', NULL),
(20, 'asdad', 'asd', 'ASD', 'ASDAD@GMAIL.COM', '$2a$10$IobQQMERCTSYTn.A.BHrG.fA6Mz8dK7NZPnihSUc2kuk0aPTWlprO', NULL);

--
-- Indexes for dumped tables
--

--
-- Indexes for table `attendancerecord`
--
ALTER TABLE `attendancerecord`
  ADD PRIMARY KEY (`RecordID`),
  ADD KEY `CourseID` (`CourseID`),
  ADD KEY `UserID` (`UserID`);

--
-- Indexes for table `course`
--
ALTER TABLE `course`
  ADD PRIMARY KEY (`CourseID`),
  ADD KEY `OverseerID` (`OverseerID`);

--
-- Indexes for table `role`
--
ALTER TABLE `role`
  ADD PRIMARY KEY (`RoleID`);

--
-- Indexes for table `user`
--
ALTER TABLE `user`
  ADD PRIMARY KEY (`UserID`),
  ADD KEY `RoleID` (`RoleID`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `attendancerecord`
--
ALTER TABLE `attendancerecord`
  MODIFY `RecordID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `course`
--
ALTER TABLE `course`
  MODIFY `CourseID` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT for table `role`
--
ALTER TABLE `role`
  MODIFY `RoleID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT for table `user`
--
ALTER TABLE `user`
  MODIFY `UserID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `attendancerecord`
--
ALTER TABLE `attendancerecord`
  ADD CONSTRAINT `attendancerecord_ibfk_1` FOREIGN KEY (`CourseID`) REFERENCES `course` (`CourseID`),
  ADD CONSTRAINT `attendancerecord_ibfk_2` FOREIGN KEY (`UserID`) REFERENCES `user` (`UserID`);

--
-- Constraints for table `course`
--
ALTER TABLE `course`
  ADD CONSTRAINT `course_ibfk_1` FOREIGN KEY (`OverseerID`) REFERENCES `user` (`UserID`);

--
-- Constraints for table `user`
--
ALTER TABLE `user`
  ADD CONSTRAINT `user_ibfk_1` FOREIGN KEY (`RoleID`) REFERENCES `role` (`RoleID`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
