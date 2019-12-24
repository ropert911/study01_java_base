DROP DATABASE IF EXISTS mybatisDemo;
CREATE DATABASE mybatisDemo CHARACTER SET utf8;
USE mybatisDemo;

CREATE TABLE `user` (
  `id` int(10) unsigned NOT NULL AUTO_INCREMENT,
  `name` varchar(64) NOT NULL DEFAULT '',
  `dept` varchar(254) NOT NULL DEFAULT '',
  `website` varchar(254) DEFAULT '',
  `phone` varchar(16) NOT NULL DEFAULT '',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of user
-- ----------------------------
INSERT INTO `user` VALUES ('1', 'yiibai', 'Tech', 'http://www.yiibai.com', '13800009988');
INSERT INTO `user` VALUES ('2', 'xiaoqian2', 'Tech', 'http://www.demo.com', '13728567890');