INSERT INTO `roles` VALUES ('1', 'ROLE_ADMIN', '超级管理员');
INSERT INTO `roles` VALUES ('2', 'ROLE_USER', '普通用户');

INSERT INTO `users` VALUES ('1', '$2a$10$ITNAU4PB1U5FerakVkCapOjcuU5t6X07MbQnPaPepS15hestdOQfm', 'admin');

INSERT INTO `user_roles` VALUES ('1', '1');
INSERT INTO `user_roles` VALUES ('1', '2');
