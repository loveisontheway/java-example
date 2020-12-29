/*
Navicat MySQL Data Transfer

Source Server         : 127.0.0.1
Source Server Version : 50610
Source Host           : 127.0.0.1:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50610
File Encoding         : 65001

Date: 2020-12-29 17:39:08
*/

CREATE DATABASE IF NOT EXISTS `test` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `test`;

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for goods_store
-- ----------------------------
DROP TABLE IF EXISTS `goods_store`;
CREATE TABLE `goods_store` (
  `code` varchar(30) DEFAULT NULL,
  `store` int(10) DEFAULT NULL,
  `update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of goods_store
-- ----------------------------
INSERT INTO `goods_store` VALUES ('20201106105842', '0', '2020-11-06 17:42:46');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `iname` varchar(255) DEFAULT NULL,
  `remark` varchar(255) DEFAULT NULL,
  `sn` varchar(255) DEFAULT NULL,
  `pid` int(11) DEFAULT NULL,
  `node_type_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=30 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '系统管理员', null, '1', null, '67');
INSERT INTO `sys_role` VALUES ('4', 'ZXW', null, '11', null, '66');
INSERT INTO `sys_role` VALUES ('5', '管理员', null, 'gly', '4', '67');
INSERT INTO `sys_role` VALUES ('6', '研发中心', null, 'yfzx', '4', '66');
INSERT INTO `sys_role` VALUES ('8', '研发中心副总', null, 'yfzxfz', '6', '67');
INSERT INTO `sys_role` VALUES ('9', '软件部', null, 'rjb', '6', '66');
INSERT INTO `sys_role` VALUES ('10', '硬件部', null, 'yjb', '6', '66');
INSERT INTO `sys_role` VALUES ('11', '固件部', null, 'gjb', '6', '66');
INSERT INTO `sys_role` VALUES ('12', '生产运营部', null, 'scyyb', '6', '66');
INSERT INTO `sys_role` VALUES ('13', '产品工程部', null, 'cpgcb', '6', '66');
INSERT INTO `sys_role` VALUES ('14', '行政部', null, 'xzb', '6', '66');
INSERT INTO `sys_role` VALUES ('15', '财务部', null, 'cwb', '6', '66');
INSERT INTO `sys_role` VALUES ('16', '多媒体事业部', null, 'dmtsyb', '6', '66');
INSERT INTO `sys_role` VALUES ('17', '营销中心', null, 'yxzx', '4', '66');
INSERT INTO `sys_role` VALUES ('18', '销售总监', null, 'xszj', '17', '67');
INSERT INTO `sys_role` VALUES ('19', '销售总监助理', null, 'xszjzl', '17', '67');
INSERT INTO `sys_role` VALUES ('20', '部门经理', null, 'bmjl', '9', '67');
INSERT INTO `sys_role` VALUES ('21', '架构师', null, 'jgs', '9', '67');
INSERT INTO `sys_role` VALUES ('22', 'java工程师', null, 'java', '9', '67');
INSERT INTO `sys_role` VALUES ('23', 'C++工程师', null, 'c', '9', '67');
INSERT INTO `sys_role` VALUES ('24', 'UI设计师', null, 'UI', '9', '67');
INSERT INTO `sys_role` VALUES ('25', '市场营销', null, 'sxyx', '17', '66');
INSERT INTO `sys_role` VALUES ('26', '技术服务', null, 'jsfw', '17', '66');
INSERT INTO `sys_role` VALUES ('27', '客户经理', null, 'khjl', '25', '67');
INSERT INTO `sys_role` VALUES ('28', '销售代表', null, 'xsdb', '25', '67');
INSERT INTO `sys_role` VALUES ('29', '技术支持', null, 'jszc', '26', '67');

-- ----------------------------
-- Table structure for user_mp
-- ----------------------------
DROP TABLE IF EXISTS `user_mp`;
CREATE TABLE `user_mp` (
  `id` bigint(20) NOT NULL COMMENT '主键ID',
  `name` varchar(30) DEFAULT NULL COMMENT '姓名',
  `age` int(11) DEFAULT NULL COMMENT '年龄',
  `email` varchar(50) DEFAULT NULL COMMENT '邮箱',
  `create_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user_mp
-- ----------------------------
INSERT INTO `user_mp` VALUES ('1', 'Jone', '18', 'test1@baomidou.com', '2020-06-03 13:45:33');
INSERT INTO `user_mp` VALUES ('2', 'Jack', '20', 'test2@baomidou.com', '2020-06-03 13:45:36');
INSERT INTO `user_mp` VALUES ('3', 'Tom', '28', 'test3@baomidou.com', '2020-06-03 13:45:38');
INSERT INTO `user_mp` VALUES ('4', 'Sandy', '21', 'test4@baomidou.com', '2020-06-03 13:45:41');
INSERT INTO `user_mp` VALUES ('5', 'Billie', '24', 'test5@baomidou.com', '2020-06-03 13:45:45');
INSERT INTO `user_mp` VALUES ('1268026231002468353', '小西', '21', 'xiaoxi@163.com', '2020-06-03 06:09:15');
INSERT INTO `user_mp` VALUES ('1268027158165852162', '小东', '19', 'xiaodong@163.com', null);
INSERT INTO `user_mp` VALUES ('1268027767015280642', '小北', '29', 'xiaobei@163.com', null);
INSERT INTO `user_mp` VALUES ('1268028195681492993', '小北', '29', null, '2020-06-03 10:13:33');
INSERT INTO `user_mp` VALUES ('1268028810738438145', '小北', '29', 'xiaobei@163.com', null);
INSERT INTO `user_mp` VALUES ('1268057481104769026', '小北', '29', 'xiaobei@163.com', '2020-06-03 05:50:37');

-- ----------------------------
-- Table structure for veh_vehicle
-- ----------------------------
DROP TABLE IF EXISTS `veh_vehicle`;
CREATE TABLE `veh_vehicle` (
  `vehicle_id` int(32) NOT NULL COMMENT '车辆id',
  `license_plate_no` varchar(50) NOT NULL COMMENT '车牌号',
  `enterprise_id` int(32) NOT NULL COMMENT '所属企业，外键：ent_enterprise',
  `sort` int(20) DEFAULT NULL COMMENT '排序',
  `remark` varchar(200) DEFAULT NULL COMMENT '备注',
  `state` int(1) NOT NULL DEFAULT '1' COMMENT '状态，1:有效，0:无效',
  `creator` varchar(30) DEFAULT NULL COMMENT '创建者',
  `creator_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '创建时间，格式：YYYY-MM-DD hh:mm:ss',
  `mender` varchar(30) DEFAULT NULL COMMENT '修改者',
  `mender_time` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`vehicle_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COMMENT='车辆主表【veh_vehicle】';

-- ----------------------------
-- Records of veh_vehicle
-- ----------------------------

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