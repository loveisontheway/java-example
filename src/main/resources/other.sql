/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50610
Source Host           : 127.0.0.1:3306
Source Database       : other

Target Server Type    : MYSQL
Target Server Version : 50610
File Encoding         : 65001

Date: 2020-12-29 17:31:27
*/

CREATE DATABASE IF NOT EXISTS `other` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `other`;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `iname` varchar(255) DEFAULT NULL,
  `org_id` int(11) DEFAULT NULL,
  `password` varchar(255) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `sn` varchar(255) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=13 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', '1', 'admin', '1', 'admin', '4');
