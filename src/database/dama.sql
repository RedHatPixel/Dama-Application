-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Host: 127.0.0.1
-- Generation Time: Feb 01, 2025 at 07:42 AM
-- Server version: 10.4.32-MariaDB
-- PHP Version: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Database: `dama`
--

-- --------------------------------------------------------

--
-- Table structure for table `account`
--

CREATE TABLE `account` (
  `AccountID` int(11) NOT NULL,
  `Username` varchar(50) NOT NULL,
  `Email` varchar(320) NOT NULL,
  `Password` varchar(100) NOT NULL,
  `VerifyCode` varchar(50) NOT NULL,
  `Status` varchar(50) NOT NULL DEFAULT 'NonVerified',
  `DateCreated` datetime NOT NULL DEFAULT current_timestamp(),
  `LastLogin` datetime DEFAULT NULL,
  `IsAdmin` tinyint(1) NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `history`
--

CREATE TABLE `history` (
  `HistoryID` int(11) NOT NULL,
  `AccountID` int(11) NOT NULL,
  `GameType` varchar(50) NOT NULL DEFAULT 'Infinite',
  `PlayerOneName` varchar(100) NOT NULL,
  `PlayerTwoName` varchar(100) NOT NULL,
  `PlayerOneScore` int(11) NOT NULL,
  `PlayerTwoScore` int(11) NOT NULL,
  `GameStatus` varchar(50) NOT NULL DEFAULT 'Draw',
  `GameDate` datetime NOT NULL DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Table structure for table `profile`
--

CREATE TABLE `profile` (
  `ProfileID` int(11) NOT NULL,
  `AccountID` int(11) NOT NULL,
  `AboutMe` varchar(255) NOT NULL,
  `ProfilePicture` varchar(255) NOT NULL DEFAULT 'user'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Indexes for dumped tables
--

--
-- Indexes for table `account`
--
ALTER TABLE `account`
  ADD PRIMARY KEY (`AccountID`);

--
-- Indexes for table `history`
--
ALTER TABLE `history`
  ADD PRIMARY KEY (`HistoryID`),
  ADD KEY `AccountID` (`AccountID`) USING BTREE;

--
-- Indexes for table `profile`
--
ALTER TABLE `profile`
  ADD PRIMARY KEY (`ProfileID`),
  ADD UNIQUE KEY `AccountID` (`AccountID`);
ALTER TABLE `profile` ADD FULLTEXT KEY `AboutMe` (`AboutMe`);

--
-- AUTO_INCREMENT for dumped tables
--

--
-- AUTO_INCREMENT for table `account`
--
ALTER TABLE `account`
  MODIFY `AccountID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=18;

--
-- AUTO_INCREMENT for table `history`
--
ALTER TABLE `history`
  MODIFY `HistoryID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;

--
-- AUTO_INCREMENT for table `profile`
--
ALTER TABLE `profile`
  MODIFY `ProfileID` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=6;

--
-- Constraints for dumped tables
--

--
-- Constraints for table `history`
--
ALTER TABLE `history`
  ADD CONSTRAINT `history_ibfk_1` FOREIGN KEY (`AccountID`) REFERENCES `account` (`AccountID`) ON DELETE CASCADE ON UPDATE CASCADE;

--
-- Constraints for table `profile`
--
ALTER TABLE `profile`
  ADD CONSTRAINT `profile_ibfk_1` FOREIGN KEY (`AccountID`) REFERENCES `account` (`AccountID`) ON DELETE CASCADE ON UPDATE CASCADE;
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
