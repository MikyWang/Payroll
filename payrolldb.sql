/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : payrolldb

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2016-04-29 01:25:58
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for admin_table
-- ----------------------------
DROP TABLE IF EXISTS `admin_table`;
CREATE TABLE `admin_table` (
  `admin_number` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `admin_name` varchar(255) DEFAULT NULL,
  `department_number` varchar(16) DEFAULT NULL,
  `department_name` varchar(255) DEFAULT NULL,
  `employee_number` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`admin_number`)
) ENGINE=InnoDB AUTO_INCREMENT=135671 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of admin_table
-- ----------------------------
INSERT INTO `admin_table` VALUES ('135670', 'admin', '27a191b7-09ea-11', 'POS', '1321761');

-- ----------------------------
-- Table structure for department_table
-- ----------------------------
DROP TABLE IF EXISTS `department_table`;
CREATE TABLE `department_table` (
  `department_number` varchar(16) NOT NULL DEFAULT '',
  `department_name` varchar(30) DEFAULT NULL,
  `department_size` int(11) DEFAULT NULL,
  `department_manager` bigint(20) DEFAULT NULL,
  PRIMARY KEY (`department_number`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of department_table
-- ----------------------------
INSERT INTO `department_table` VALUES ('27a191b7-09ea-11', 'POS', '10', '1321761');

-- ----------------------------
-- Table structure for employee_table
-- ----------------------------
DROP TABLE IF EXISTS `employee_table`;
CREATE TABLE `employee_table` (
  `employ_number` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `employ_name` varchar(30) DEFAULT NULL,
  `sex` varchar(10) DEFAULT NULL,
  `education` varchar(30) DEFAULT NULL,
  `department_number` varchar(16) DEFAULT NULL,
  `department_name` varchar(30) DEFAULT NULL,
  PRIMARY KEY (`employ_number`)
) ENGINE=InnoDB AUTO_INCREMENT=1321771 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of employee_table
-- ----------------------------
INSERT INTO `employee_table` VALUES ('1321761', 'miky', '男', '本科生', '27a191b7-09ea-11', 'POS');
INSERT INTO `employee_table` VALUES ('1321762', '乐子', '男', '本科生', '27a191b7-09ea-11', 'POS');
INSERT INTO `employee_table` VALUES ('1321763', '张三', '男', '本科生', '27a191b7-09ea-11', 'POS');
INSERT INTO `employee_table` VALUES ('1321764', '李四', '男', '本科生', '27a191b7-09ea-11', 'POS');
INSERT INTO `employee_table` VALUES ('1321765', '王麻子', '男', '本科生', '27a191b7-09ea-11', 'POS');
INSERT INTO `employee_table` VALUES ('1321766', '王八蛋', '男', '本科生', '27a191b7-09ea-11', 'POS');
INSERT INTO `employee_table` VALUES ('1321767', '李狗蛋', '男', '本科生', '27a191b7-09ea-11', 'POS');
INSERT INTO `employee_table` VALUES ('1321768', '赵铁柱', '男', '本科生', '27a191b7-09ea-11', 'POS');
INSERT INTO `employee_table` VALUES ('1321769', '赵日天', '男', '本科生', '27a191b7-09ea-11', 'POS');
INSERT INTO `employee_table` VALUES ('1321770', '叶良辰', '男', '本科生', '27a191b7-09ea-11', 'POS');

-- ----------------------------
-- Table structure for salary_table
-- ----------------------------
DROP TABLE IF EXISTS `salary_table`;
CREATE TABLE `salary_table` (
  `employ_number` bigint(20) NOT NULL,
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
INSERT INTO `salary_table` VALUES ('1321761', '1000.00', '1200.00', '100.00', '10', '300.00', '30');
INSERT INTO `salary_table` VALUES ('1321762', '1000.00', '1200.00', '100.00', '10', '300.00', '30');
INSERT INTO `salary_table` VALUES ('1321763', '1000.00', '1200.00', '100.00', '10', '300.00', '30');
INSERT INTO `salary_table` VALUES ('1321764', '1000.00', '1200.00', '100.00', '10', '300.00', '30');
INSERT INTO `salary_table` VALUES ('1321765', '1000.00', '1200.00', '100.00', '10', '300.00', '30');
INSERT INTO `salary_table` VALUES ('1321766', '1000.00', '1200.00', '100.00', '10', '300.00', '30');
INSERT INTO `salary_table` VALUES ('1321767', '1000.00', '1200.00', '100.00', '10', '300.00', '30');
INSERT INTO `salary_table` VALUES ('1321768', '1000.00', '1200.00', '100.00', '10', '300.00', '30');
INSERT INTO `salary_table` VALUES ('1321769', '1000.00', '1200.00', '100.00', '10', '300.00', '30');
INSERT INTO `salary_table` VALUES ('1321770', '1000.00', '1200.00', '100.00', '10', '300.00', '30');

-- ----------------------------
-- Table structure for user_table
-- ----------------------------
DROP TABLE IF EXISTS `user_table`;
CREATE TABLE `user_table` (
  `user_id` bigint(20) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `power` varchar(255) DEFAULT NULL,
  PRIMARY KEY (`user_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_table
-- ----------------------------
INSERT INTO `user_table` VALUES ('135670', 'admin', '管理员');
INSERT INTO `user_table` VALUES ('1321761', '123456', '员工');
INSERT INTO `user_table` VALUES ('1321762', '123456', '员工');
INSERT INTO `user_table` VALUES ('1321763', '123456', '员工');
INSERT INTO `user_table` VALUES ('1321764', '123456', '员工');
INSERT INTO `user_table` VALUES ('1321765', '123456', '员工');
INSERT INTO `user_table` VALUES ('1321766', '123456', '员工');
INSERT INTO `user_table` VALUES ('1321767', '123456', '员工');
INSERT INTO `user_table` VALUES ('1321768', '123456', '员工');
INSERT INTO `user_table` VALUES ('1321769', '123456', '员工');
INSERT INTO `user_table` VALUES ('1321770', '123456', '员工');
DROP TRIGGER IF EXISTS `setNumber`;
DELIMITER ;;
CREATE TRIGGER `setNumber` BEFORE INSERT ON `department_table` FOR EACH ROW if(new.department_number='' or new.department_number=null)then
set new.department_number=uuid();
end if
;;
DELIMITER ;
