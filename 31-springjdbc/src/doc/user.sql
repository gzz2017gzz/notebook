/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50168
Source Host           : localhost:3306
Source Database       : test

Target Server Type    : MYSQL
Target Server Version : 50168
File Encoding         : 65001

Date: 2019-12-23 23:55:43
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `user`
-- ----------------------------
DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(32) DEFAULT NULL COMMENT '用户名',
  `age` int(32) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', '1', '1');
INSERT INTO `user` VALUES ('2', '王五光', '10');
INSERT INTO `user` VALUES ('3', '李四光', '20');
INSERT INTO `user` VALUES ('4', '王五', '10');
INSERT INTO `user` VALUES ('5', '李四光', '20');
INSERT INTO `user` VALUES ('6', '王五', '10');
