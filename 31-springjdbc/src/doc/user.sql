DROP TABLE IF EXISTS `user`;
CREATE TABLE `user` (
  `id` int(20) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `name` varchar(32) DEFAULT NULL COMMENT '用户名',
  `age` int(32) DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`id`)
) DEFAULT CHARSET=utf8;
 
INSERT INTO `user` VALUES ('1', '1', '1');
INSERT INTO `user` VALUES ('2', '王五光', '10');
INSERT INTO `user` VALUES ('3', '李四光', '20');
INSERT INTO `user` VALUES ('4', '王五', '10');
INSERT INTO `user` VALUES ('5', '李四光', '20');
INSERT INTO `user` VALUES ('6', '王五', '10');
