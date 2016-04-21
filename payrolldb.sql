/*
Navicat MySQL Data Transfer

Source Server         : 123
Source Server Version : 50624
Source Host           : localhost:3306
Source Database       : payrolldb

Target Server Type    : MYSQL
Target Server Version : 50624
File Encoding         : 65001

Date: 2016-04-20 14:23:57
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `department_table`
-- ----------------------------
DROP TABLE IF EXISTS `department_table`;
CREATE TABLE `department_table` (
  `department_number` varchar(16) NOT NULL DEFAULT '',
  `department_name` varchar(30) DEFAULT NULL,
  `department_size` int(11) DEFAULT NULL,
  `department_manager` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`department_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department_table
-- ----------------------------

-- ----------------------------
-- Table structure for `employee_table`
-- ----------------------------
DROP TABLE IF EXISTS `employee_table`;
CREATE TABLE `employee_table` (
  `employ_number` varchar(16) NOT NULL DEFAULT '',
  `employ_name` varchar(30) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `education` varchar(30) DEFAULT NULL,
  `department_number` varchar(16) DEFAULT NULL,
  `department_name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`employ_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee_table
-- ----------------------------

-- ----------------------------
-- Table structure for `salary_table`
-- ----------------------------
DROP TABLE IF EXISTS `salary_table`;
CREATE TABLE `salary_table` (
  `employ_number` varchar(16) NOT NULL,
  `expect_salary` decimal(10,2) DEFAULT NULL,
  `actually_salary` decimal(10,2) DEFAULT NULL,
  `fine` decimal(10,2) DEFAULT '0.00',
  `overtime` int(11) DEFAULT '0',
  `overtime_salary` decimal(10,2) DEFAULT '0.00',
  `office_day` int(11) DEFAULT NULL,
  PRIMARY KEY (`employ_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of salary_table
-- ----------------------------
INSERT INTO `salary_table` VALUES ('1', '22.00', '33.00', '0.00', '0', '0.00', '21');
DELIMITER ;;
CREATE TRIGGER `department_number` BEFORE INSERT ON `department_table` FOR EACH ROW if(new.department_number='' or department_number=null)then
set new.department_number=uuid();
end if
;;
DELIMITER ;
DELIMITER ;;
CREATE TRIGGER `default_number` BEFORE INSERT ON `employee_table` FOR EACH ROW if(new.employ_number='' or new.employ_number=null)then
set new.employ_number=uuid();
end if
;;
DELIMITER ;
