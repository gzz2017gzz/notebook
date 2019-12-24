/*
Navicat MySQL Data Transfer

Source Server         : 本机
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : shiro

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2019-01-18 19:02:00
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `role`
-- ----------------------------
DROP TABLE IF EXISTS `role`;
CREATE TABLE `role` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `role` varchar(40) DEFAULT NULL COMMENT '角色',
  `name` varchar(40) DEFAULT NULL COMMENT '名称',
  `available` int(11) DEFAULT '0' COMMENT '是否可用',
  `role1` varchar(40) DEFAULT NULL COMMENT '角色',
  `role2` varchar(40) DEFAULT NULL COMMENT '角色',
  `role3` varchar(40) DEFAULT NULL COMMENT '角色',
  `role4` varchar(40) DEFAULT NULL COMMENT '角色',
  `role5` varchar(40) DEFAULT NULL COMMENT '角色',
  `role6` varchar(40) DEFAULT NULL COMMENT '角色',
  `role7` varchar(40) DEFAULT NULL COMMENT '角色',
  `role8` varchar(40) DEFAULT NULL COMMENT '角色',
  `role9` varchar(40) DEFAULT NULL COMMENT '角色',
  `role10` varchar(40) DEFAULT NULL COMMENT '角色',
  `role11` varchar(40) DEFAULT NULL COMMENT '角色',
  `role12` varchar(40) DEFAULT NULL COMMENT '角色',
  `role13` varchar(40) DEFAULT NULL COMMENT '角色',
  `role14` varchar(40) DEFAULT NULL COMMENT '角色',
  PRIMARY KEY (`id`)
) ENGINE=MyISAM AUTO_INCREMENT=100001 DEFAULT CHARSET=utf8 COMMENT='角色';

-- ----------------------------
-- Records of role
-- ----------------------------
