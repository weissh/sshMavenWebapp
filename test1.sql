-- phpMyAdmin SQL Dump
-- version 4.0.9
-- http://www.phpmyadmin.net
--
-- 主机: 127.0.0.1
-- 生成日期: 2014-01-22 11:33:32
-- 服务器版本: 5.5.34
-- PHP 版本: 5.4.22

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- 数据库: `test1`
--

-- --------------------------------------------------------

--
-- 表的结构 `dss_question`
--

CREATE TABLE IF NOT EXISTS `dss_question` (
  `question_id` int(11) NOT NULL AUTO_INCREMENT,
  `data_date` varchar(20) COLLATE utf8_bin NOT NULL,
  `ques_prov` varchar(10) COLLATE utf8_bin NOT NULL,
  `ques_content` varchar(200) COLLATE utf8_bin NOT NULL,
  `ques_reply` varchar(200) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`question_id`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=3 ;

--
-- 转存表中的数据 `dss_question`
--

INSERT INTO `dss_question` (`question_id`, `data_date`, `ques_prov`, `ques_content`, `ques_reply`) VALUES
(1, '201401122', '安徽', 'tes', 'no');

-- --------------------------------------------------------

--
-- 表的结构 `student`
--

CREATE TABLE IF NOT EXISTS `student` (
  `unicode` int(11) NOT NULL AUTO_INCREMENT,
  `cno` int(11) NOT NULL,
  `cname` varchar(20) COLLATE utf8_bin NOT NULL,
  `cteacher` varchar(20) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`unicode`)
) ENGINE=InnoDB  DEFAULT CHARSET=utf8 COLLATE=utf8_bin AUTO_INCREMENT=118 ;

--
-- 转存表中的数据 `student`
--

INSERT INTO `student` (`unicode`, `cno`, `cname`, `cteacher`) VALUES
(1, 12, '张韬1', 'dd'),
(2, 11, '张韬', 'abc'),
(3, 11, '再找找', '张韬'),
(27, 12, 'abc', 'abcd'),
(28, 11, '再找找', '张韬'),
(29, 11, '再找找', '张韬'),
(30, 11, '再找找', '张韬'),
(31, 11, '再找找', '张韬'),
(32, 11, '再找找', '张韬'),
(33, 11, '再找找', '张韬'),
(34, 11, '再找找', '张韬'),
(35, 11, '再找找', '张韬'),
(36, 11, '再找找', '张韬'),
(37, 11, '再找找', '张韬'),
(38, 11, '再找找', '张韬'),
(39, 11, '再找找', '张韬'),
(40, 11, '再找找', '张韬'),
(41, 11, '再找找', '张韬'),
(42, 11, '再找找', '张韬'),
(43, 11, '再找找', '张韬'),
(44, 11, '再找找', '张韬'),
(45, 11, '再找找', '张韬'),
(46, 11, '再找找', '张韬'),
(47, 11, '再找找', '张韬'),
(48, 11, '再找找', '张韬'),
(49, 11, '再找找', '张韬'),
(50, 11, '再找找', '张韬'),
(51, 11, '再找找', '张韬'),
(52, 11, '再找找', '张韬'),
(53, 11, '再找找', '张韬'),
(54, 11, '再找找', '张韬'),
(55, 11, '再找找', '张韬'),
(56, 11, '再找找', '张韬'),
(57, 11, '再找找', '张韬'),
(58, 11, '再找找', '张韬'),
(59, 11, '再找找', '张韬'),
(60, 11, '再找找', '张韬'),
(61, 11, '再找找', '张韬'),
(62, 11, '再找找', '张韬'),
(63, 11, '再找找', '张韬'),
(64, 11, '再找找', '张韬'),
(65, 11, '再找找', '张韬'),
(66, 11, '再找找', '张韬'),
(67, 11, '再找找', '张韬'),
(68, 11, '再找找', '张韬'),
(69, 11, '再找找', '张韬'),
(70, 11, '再找找', '张韬'),
(71, 11, '再找找', '张韬'),
(72, 11, '再找找', '张韬'),
(73, 11, '再找找', '张韬'),
(74, 11, '再找找', '张韬'),
(75, 11, '再找找', '张韬'),
(76, 11, '再找找', '张韬'),
(77, 11, '再找找', '张韬'),
(78, 11, '再找找', '张韬'),
(79, 11, '再找找', '张韬'),
(80, 11, '再找找', '张韬'),
(81, 11, '再找找', '张韬'),
(82, 11, '再找找', '张韬'),
(83, 11, '再找找', '张韬'),
(84, 11, '再找找', '张韬'),
(85, 11, '再找找', '张韬'),
(86, 11, '再找找', '张韬'),
(87, 11, '再找找', '张韬'),
(88, 11, '再找找', '张韬'),
(89, 11, '再找找', '张韬'),
(90, 11, '再找找', '张韬'),
(91, 11, '再找找', '张韬'),
(92, 11, '再找找', '张韬'),
(93, 11, '再找找', '张韬'),
(94, 11, '再找找', '张韬'),
(95, 11, '再找找', '张韬'),
(96, 11, '再找找', '张韬'),
(97, 11, '再找找', '张韬'),
(98, 11, '再找找', '张韬'),
(99, 11, '再找找', '张韬'),
(100, 11, '再找找', '张韬'),
(101, 11, '再找找', '张韬'),
(102, 11, '再找找', '张韬'),
(103, 11, '再找找', '张韬'),
(104, 11, '再找找', '张韬'),
(105, 11, '再找找', '张韬'),
(106, 11, '再找找', '张韬'),
(107, 11, '再找找', '张韬'),
(108, 11, '再找找', '张韬'),
(109, 11, '再找找', '张韬'),
(110, 11, '再找找', '张韬'),
(111, 11, '再找找', '张韬'),
(112, 11, '再找找', '张韬'),
(113, 11, '再找找', '张韬'),
(114, 11, '再找找', '张韬'),
(115, 11, '再找找', '张韬'),
(116, 11, '再找找', '张韬'),
(117, 11, '再找找', '张韬');

-- --------------------------------------------------------

--
-- 表的结构 `tbl_permission`
--

CREATE TABLE IF NOT EXISTS `tbl_permission` (
  `PERMISSION_ID` varchar(25) COLLATE utf8_bin NOT NULL,
  `PERMISSION_NAME` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`PERMISSION_ID`),
  UNIQUE KEY `PERMISSION_NAME_UNIQUE` (`PERMISSION_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- 转存表中的数据 `tbl_permission`
--

INSERT INTO `tbl_permission` (`PERMISSION_ID`, `PERMISSION_NAME`) VALUES
('user:*', 'CREATE'),
('3', 'DELETE'),
('2', 'QUERY'),
('4', 'UPDATE');

-- --------------------------------------------------------

--
-- 表的结构 `tbl_permission_role`
--

CREATE TABLE IF NOT EXISTS `tbl_permission_role` (
  `ROLE_ID` int(11) NOT NULL,
  `PERMISSION_ID` varchar(45) COLLATE utf8_bin NOT NULL,
  KEY `fk_TBL_ROLE_has_TBL_PERMISSION_TBL_ROLE1_idx` (`ROLE_ID`),
  KEY `fk_TBL_ROLE_has_TBL_PERMISSION_TBL_ROLE2` (`PERMISSION_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- 转存表中的数据 `tbl_permission_role`
--

INSERT INTO `tbl_permission_role` (`ROLE_ID`, `PERMISSION_ID`) VALUES
(1, 'user:*'),
(1, '2'),
(1, '3'),
(1, '4');

-- --------------------------------------------------------

--
-- 表的结构 `tbl_role`
--

CREATE TABLE IF NOT EXISTS `tbl_role` (
  `ROLE_ID` int(11) NOT NULL,
  `ROLE_NAME` varchar(45) COLLATE utf8_bin DEFAULT NULL,
  PRIMARY KEY (`ROLE_ID`),
  UNIQUE KEY `ROLE_NAME_UNIQUE` (`ROLE_NAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- 转存表中的数据 `tbl_role`
--

INSERT INTO `tbl_role` (`ROLE_ID`, `ROLE_NAME`) VALUES
(1, 'ADMIN'),
(2, 'USER');

-- --------------------------------------------------------

--
-- 表的结构 `tbl_user`
--

CREATE TABLE IF NOT EXISTS `tbl_user` (
  `USER_ID` varchar(32) COLLATE utf8_bin NOT NULL,
  `USER_USERNAME` varchar(45) COLLATE utf8_bin NOT NULL,
  `USER_PASSWORD` char(32) COLLATE utf8_bin NOT NULL,
  PRIMARY KEY (`USER_ID`),
  UNIQUE KEY `USER_USERNAME_UNIQUE` (`USER_USERNAME`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- 转存表中的数据 `tbl_user`
--

INSERT INTO `tbl_user` (`USER_ID`, `USER_USERNAME`, `USER_PASSWORD`) VALUES
('1', 'yingzhuo', '9e21b083aff2ea3acdd9cf9df5a91141'),
('4028fb8143614cae0143614cc3340001', 'zhangtao', 'aaaa');

-- --------------------------------------------------------

--
-- 表的结构 `tbl_user_role`
--

CREATE TABLE IF NOT EXISTS `tbl_user_role` (
  `USER_ID` varchar(32) COLLATE utf8_bin NOT NULL,
  `ROLE_ID` int(11) NOT NULL,
  PRIMARY KEY (`USER_ID`,`ROLE_ID`),
  KEY `fk_TBL_USER_has_TBL_ROLE_TBL_ROLE1_idx` (`ROLE_ID`),
  KEY `fk_TBL_USER_has_TBL_ROLE_TBL_USER_idx` (`USER_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

--
-- 转存表中的数据 `tbl_user_role`
--

INSERT INTO `tbl_user_role` (`USER_ID`, `ROLE_ID`) VALUES
('1', 1);

--
-- 限制导出的表
--

--
-- 限制表 `tbl_permission_role`
--
ALTER TABLE `tbl_permission_role`
  ADD CONSTRAINT `fk_TBL_ROLE_has_TBL_PERMISSION_TBL_ROLE2` FOREIGN KEY (`PERMISSION_ID`) REFERENCES `tbl_permission` (`PERMISSION_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_TBL_ROLE_has_TBL_PERMISSION_TBL_ROLE1` FOREIGN KEY (`ROLE_ID`) REFERENCES `tbl_role` (`ROLE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

--
-- 限制表 `tbl_user_role`
--
ALTER TABLE `tbl_user_role`
  ADD CONSTRAINT `fk_TBL_USER_has_TBL_ROLE_TBL_ROLE1` FOREIGN KEY (`ROLE_ID`) REFERENCES `tbl_role` (`ROLE_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION,
  ADD CONSTRAINT `fk_TBL_USER_has_TBL_ROLE_TBL_USER` FOREIGN KEY (`USER_ID`) REFERENCES `tbl_user` (`USER_ID`) ON DELETE NO ACTION ON UPDATE NO ACTION;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
