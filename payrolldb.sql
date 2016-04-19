/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : payrolldb

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2016-04-20 01:32:18
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for salary_table
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
