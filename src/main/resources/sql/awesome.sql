/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50632
Source Host           : localhost:3306
Source Database       : mybatis

Target Server Type    : MYSQL
Target Server Version : 50632
File Encoding         : 65001

Date: 2017-06-17 13:31:29
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for persistent_logins
-- ----------------------------
DROP TABLE IF EXISTS `persistent_logins`;
CREATE TABLE `persistent_logins` (
  `username` varchar(64) NOT NULL,
  `series` varchar(64) NOT NULL,
  `token` varchar(64) NOT NULL,
  `last_used` timestamp NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`series`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of persistent_logins
-- ----------------------------

-- ----------------------------
-- Table structure for sys_department
-- ----------------------------
DROP TABLE IF EXISTS `sys_department`;
CREATE TABLE `sys_department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL COMMENT '部门名称',
  `parent` int(11) DEFAULT NULL COMMENT '父级部门',
  `description` varchar(500) DEFAULT '' COMMENT '模块描述',
  `level` int(11) NOT NULL DEFAULT '1' COMMENT '层级',
  `order` int(11) DEFAULT '1' COMMENT '排序',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '1 有效  0 无效',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_department
-- ----------------------------
INSERT INTO `sys_department` VALUES ('1', '以萨', '0', '', '1', '1', '1', null, '2017-06-11 11:58:47');
INSERT INTO `sys_department` VALUES ('2', '技术中心', '1', '', '2', '1', '1', null, '2017-06-11 11:58:51');
INSERT INTO `sys_department` VALUES ('7', '22是', '1', '22', '2', '2', '1', null, '2017-06-14 10:43:34');
INSERT INTO `sys_department` VALUES ('9', '22', '7', '22', '3', '1', '1', null, null);

-- ----------------------------
-- Table structure for sys_model
-- ----------------------------
DROP TABLE IF EXISTS `sys_model`;
CREATE TABLE `sys_model` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '模块名称',
  `abbr` varchar(32) NOT NULL COMMENT '简写,建议  MODEL_开头，后面加对应模块简写',
  `description` varchar(500) DEFAULT '' COMMENT '模块描述',
  `parent` int(11) NOT NULL DEFAULT '0' COMMENT '父节点，0为根节点',
  `url` varchar(255) NOT NULL COMMENT '模块url',
  `order` int(11) NOT NULL DEFAULT '0' COMMENT '排序',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=11 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_model
-- ----------------------------
INSERT INTO `sys_model` VALUES ('1', '根模块', '', '根模块', '0', '', '0');
INSERT INTO `sys_model` VALUES ('2', '后台管理', '', '后台管理模块', '1', '', '2');
INSERT INTO `sys_model` VALUES ('3', '前台管理', '', '前台管理模块', '1', '', '0');
INSERT INTO `sys_model` VALUES ('4', '用户管理', '', '用户管理模块', '2', '/system/user/list', '10');
INSERT INTO `sys_model` VALUES ('5', '资源管理', '', '资源管理模块', '2', '/system/resource/list', '20');
INSERT INTO `sys_model` VALUES ('6', '角色管理', '', '角色管理模块', '2', '/system/role/list', '30');
INSERT INTO `sys_model` VALUES ('7', '222', '', '11', '1', '', '1');
INSERT INTO `sys_model` VALUES ('8', '2222', '', '111', '1', '111111111', '1');
INSERT INTO `sys_model` VALUES ('10', '1112', 'MODEL_11', '111', '1', '11111111111', '1');

-- ----------------------------
-- Table structure for sys_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_resource`;
CREATE TABLE `sys_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `url` varchar(255) DEFAULT NULL,
  `type` varchar(32) DEFAULT '' COMMENT '资源类型  url 或者 menu',
  `model` int(11) DEFAULT NULL COMMENT '模块id',
  `status` int(1) DEFAULT '1' COMMENT '状态，1有效 0无效',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=48 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_resource
-- ----------------------------
INSERT INTO `sys_resource` VALUES ('1', '用户-列表页', '/system/user/list', 'menu', '4', '1', '2017-06-12 16:51:02', '2017-06-16 10:11:09');
INSERT INTO `sys_resource` VALUES ('2', '用户-列表页数据', '/system/user/loadData', 'url', '4', '1', '2017-06-12 16:50:51', '2017-06-12 16:52:38');
INSERT INTO `sys_resource` VALUES ('3', '用户-添加用户', '/system/user/add', 'url', '4', '1', '2017-06-12 16:50:30', '2017-06-12 16:52:38');
INSERT INTO `sys_resource` VALUES ('4', '用户-编辑用户', '/system/user/edit', 'url', '4', '1', '2017-06-12 16:50:30', '2017-06-12 16:52:38');
INSERT INTO `sys_resource` VALUES ('5', '用户-保存用户', '/system/user/save', 'url', '4', '1', '2017-06-12 16:50:29', '2017-06-12 16:52:38');
INSERT INTO `sys_resource` VALUES ('6', '用户-删除用户', '/system/user/delete', 'url', '4', '1', '2017-06-12 16:50:28', '2017-06-12 16:52:38');
INSERT INTO `sys_resource` VALUES ('7', '部门-部门树数据', '/system/department/treeData', 'url', '4', '1', '2017-06-12 16:51:10', '2017-06-12 16:52:38');
INSERT INTO `sys_resource` VALUES ('8', '部门-添加部门', '/system/department/add', 'url', '4', '1', '2017-06-12 16:51:11', '2017-06-12 16:52:38');
INSERT INTO `sys_resource` VALUES ('9', '部门-编辑部门', '/system/department/edit', 'url', '4', '1', '2017-06-12 16:51:12', '2017-06-12 16:52:38');
INSERT INTO `sys_resource` VALUES ('10', '部门-保存部门', '/system/department/save', 'url', '4', '1', '2017-06-12 16:51:14', '2017-06-12 16:52:38');
INSERT INTO `sys_resource` VALUES ('11', '部门-删除部门', '/system/department/delete', 'url', '4', '1', '2017-06-12 16:51:16', '2017-06-12 16:52:38');
INSERT INTO `sys_resource` VALUES ('12', '模块-模块树数据', '/system/model/treeData', 'url', '5', '1', '2017-06-12 16:51:41', '2017-06-12 16:52:38');
INSERT INTO `sys_resource` VALUES ('13', '模块-添加模块', '/system/model/add', 'url', '5', '1', '2017-06-12 16:51:42', '2017-06-12 16:52:38');
INSERT INTO `sys_resource` VALUES ('14', '模块-编辑模块', '/system/model/edit', 'url', '5', '1', '2017-06-12 16:51:42', '2017-06-12 16:52:38');
INSERT INTO `sys_resource` VALUES ('15', '模块-保存模块', '/system/model/save', 'url', '5', '1', '2017-06-12 16:51:44', '2017-06-12 16:52:38');
INSERT INTO `sys_resource` VALUES ('16', '模块-删除模块', '/system/model/delete', 'url', '5', '1', '2017-06-12 16:51:45', '2017-06-12 16:52:38');
INSERT INTO `sys_resource` VALUES ('17', '模块-模块资源树数据', '/system/model/resource/treeData', 'url', '5', '1', '2017-06-12 16:51:46', '2017-06-12 16:52:38');
INSERT INTO `sys_resource` VALUES ('18', '资源-列表页', '/system/resource/list', 'menu', '5', '1', '2017-06-12 16:51:58', '2017-06-12 16:52:38');
INSERT INTO `sys_resource` VALUES ('19', '资源-列表页数据', '/system/resource/loadData', 'url', '5', '1', '2017-06-12 16:51:50', '2017-06-12 16:52:38');
INSERT INTO `sys_resource` VALUES ('20', '资源-添加资源', '/system/resource/add', 'url', '5', '1', '2017-06-12 16:51:51', '2017-06-12 16:52:38');
INSERT INTO `sys_resource` VALUES ('21', '资源-编辑资源', '/system/resource/edit', 'url', '5', '1', '2017-06-12 16:51:51', '2017-06-12 16:52:38');
INSERT INTO `sys_resource` VALUES ('22', '资源-保存资源', '/system/resource/save', 'url', '5', '1', '2017-06-12 16:51:54', '2017-06-12 16:52:38');
INSERT INTO `sys_resource` VALUES ('23', '资源-删除资源', '/system/resource/delete', 'url', '5', '1', '2017-06-12 16:52:01', '2017-06-12 16:52:38');
INSERT INTO `sys_resource` VALUES ('24', '角色-列表页', '/system/role/list', 'menu', '6', '1', '2017-06-12 16:52:18', '2017-06-12 16:52:38');
INSERT INTO `sys_resource` VALUES ('25', '角色-列表页数据', '/system/role/loadData', 'url', '6', '1', '2017-06-12 16:52:03', '2017-06-12 16:52:38');
INSERT INTO `sys_resource` VALUES ('26', '角色-添加角色', '/system/role/add', 'url', '6', '1', '2017-06-12 16:52:04', '2017-06-12 16:52:38');
INSERT INTO `sys_resource` VALUES ('27', '角色-编辑角色', '/system/role/edit', 'url', '6', '1', '2017-06-12 16:52:04', '2017-06-12 16:52:38');
INSERT INTO `sys_resource` VALUES ('28', '角色-保存角色', '/system/role/save', 'url', '6', '1', '2017-06-12 16:52:05', '2017-06-12 16:52:38');
INSERT INTO `sys_resource` VALUES ('29', '角色-删除角色', '/system/role/delete', 'url', '6', '1', '2017-06-12 16:52:06', '2017-06-12 16:52:38');
INSERT INTO `sys_resource` VALUES ('30', '角色-资源列表', '/system/role/resource/list', 'url', '6', '1', '2017-06-12 16:52:06', '2017-06-12 16:52:38');
INSERT INTO `sys_resource` VALUES ('31', '角色-角色资源关系保存', '/system/role/resource/save', 'url', '6', '1', '2017-06-12 16:52:12', '2017-06-12 17:07:15');
INSERT INTO `sys_resource` VALUES ('32', '用户-获取授权角色信息', '/system/user/role/list', 'url', '4', '1', '2017-06-13 16:44:01', '2017-06-17 09:50:04');
INSERT INTO `sys_resource` VALUES ('33', '用户-授权角色保存', '/system/user/role/save', 'url', '4', '1', '2017-06-13 18:36:24', '2017-06-17 09:47:45');
INSERT INTO `sys_resource` VALUES ('34', '角色-查询关联人员数', '/system/role/user/count', 'url', '6', '1', '2017-06-15 14:17:54', '2017-06-15 14:18:19');
INSERT INTO `sys_resource` VALUES ('40', '资源-模块树页面', '/system/model/tree', 'url', '5', '1', '2017-06-15 14:17:54', '2017-06-16 11:17:27');
INSERT INTO `sys_resource` VALUES ('41', '用户-授权模块页面', '/system/user/model', 'url', '4', '1', '2017-06-17 09:49:06', '2017-06-17 10:15:18');
INSERT INTO `sys_resource` VALUES ('42', '资源-移动资源保存', '/system/resource/move', 'url', '5', '1', '2017-06-15 14:17:54', '2017-06-16 11:18:22');
INSERT INTO `sys_resource` VALUES ('43', '用户-授权模块保存', '/system/user/model/save', 'url', '4', '1', '2017-06-17 09:49:06', '2017-06-17 10:15:13');
INSERT INTO `sys_resource` VALUES ('44', '用户-表单提交时唯一性验证', '/system/user/formValidate', 'url', '4', '1', '2017-06-17 09:49:06', '2017-06-17 13:15:32');
INSERT INTO `sys_resource` VALUES ('46', '角色-表单提交时唯一性验证', '/system/role/formValidate', 'url', '6', '1', '2017-06-17 09:49:06', '2017-06-17 13:15:49');
INSERT INTO `sys_resource` VALUES ('47', '资源-表单提交时唯一性验证', '/system/resource/formValidate', 'url', '5', '1', '2017-06-17 09:49:06', '2017-06-17 13:15:49');

-- ----------------------------
-- Table structure for sys_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_role`;
CREATE TABLE `sys_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) NOT NULL COMMENT '角色名称',
  `description` varchar(500) DEFAULT '' COMMENT '角色描述',
  `type` varchar(32) DEFAULT '' COMMENT '角色类型 system 系统内置不可删除 或者 custom自定义的',
  `status` int(1) DEFAULT '1' COMMENT '状态，1有效 0无效',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role
-- ----------------------------
INSERT INTO `sys_role` VALUES ('1', '管理员', '后台管理员', 'system', '1', '2017-06-10 13:07:24', '2017-06-15 11:41:40');
INSERT INTO `sys_role` VALUES ('2', 'ROLE_TEST', '111', '', '1', '2017-06-10 13:07:17', '2017-06-12 16:53:25');
INSERT INTO `sys_role` VALUES ('4', '111', '2222', null, '1', null, '2017-06-13 18:02:16');
INSERT INTO `sys_role` VALUES ('5', '111111', '111', null, '1', null, '2017-06-15 11:50:36');
INSERT INTO `sys_role` VALUES ('8', '111111111', '11', 'custom', '1', null, null);
INSERT INTO `sys_role` VALUES ('9', '444', '', 'custom', '1', null, null);
INSERT INTO `sys_role` VALUES ('10', '55', '', 'custom', '1', null, null);
INSERT INTO `sys_role` VALUES ('11', '管理员2', '', 'custom', '1', null, null);
INSERT INTO `sys_role` VALUES ('12', '管理员8', '', 'custom', '1', null, null);
INSERT INTO `sys_role` VALUES ('13', 'fanxw', '11', 'custom', '1', null, null);
INSERT INTO `sys_role` VALUES ('14', '管理员15', '', 'custom', '1', null, null);
INSERT INTO `sys_role` VALUES ('15', '管理员23', '', 'custom', '1', null, null);

-- ----------------------------
-- Table structure for sys_role_resource
-- ----------------------------
DROP TABLE IF EXISTS `sys_role_resource`;
CREATE TABLE `sys_role_resource` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL,
  `resource_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`),
  KEY `rid` (`role_id`) USING BTREE,
  KEY `mid` (`resource_id`) USING BTREE,
  CONSTRAINT `sys_role_resource_ibfk_1` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`),
  CONSTRAINT `sys_role_resource_ibfk_2` FOREIGN KEY (`resource_id`) REFERENCES `sys_resource` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=657 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_role_resource
-- ----------------------------
INSERT INTO `sys_role_resource` VALUES ('616', '1', '1');
INSERT INTO `sys_role_resource` VALUES ('617', '1', '2');
INSERT INTO `sys_role_resource` VALUES ('618', '1', '3');
INSERT INTO `sys_role_resource` VALUES ('619', '1', '4');
INSERT INTO `sys_role_resource` VALUES ('620', '1', '5');
INSERT INTO `sys_role_resource` VALUES ('621', '1', '6');
INSERT INTO `sys_role_resource` VALUES ('622', '1', '7');
INSERT INTO `sys_role_resource` VALUES ('623', '1', '8');
INSERT INTO `sys_role_resource` VALUES ('624', '1', '9');
INSERT INTO `sys_role_resource` VALUES ('625', '1', '10');
INSERT INTO `sys_role_resource` VALUES ('626', '1', '11');
INSERT INTO `sys_role_resource` VALUES ('627', '1', '32');
INSERT INTO `sys_role_resource` VALUES ('628', '1', '33');
INSERT INTO `sys_role_resource` VALUES ('629', '1', '41');
INSERT INTO `sys_role_resource` VALUES ('630', '1', '43');
INSERT INTO `sys_role_resource` VALUES ('631', '1', '44');
INSERT INTO `sys_role_resource` VALUES ('632', '1', '12');
INSERT INTO `sys_role_resource` VALUES ('633', '1', '13');
INSERT INTO `sys_role_resource` VALUES ('634', '1', '14');
INSERT INTO `sys_role_resource` VALUES ('635', '1', '15');
INSERT INTO `sys_role_resource` VALUES ('636', '1', '16');
INSERT INTO `sys_role_resource` VALUES ('637', '1', '17');
INSERT INTO `sys_role_resource` VALUES ('638', '1', '18');
INSERT INTO `sys_role_resource` VALUES ('639', '1', '19');
INSERT INTO `sys_role_resource` VALUES ('640', '1', '20');
INSERT INTO `sys_role_resource` VALUES ('641', '1', '21');
INSERT INTO `sys_role_resource` VALUES ('642', '1', '22');
INSERT INTO `sys_role_resource` VALUES ('643', '1', '23');
INSERT INTO `sys_role_resource` VALUES ('644', '1', '40');
INSERT INTO `sys_role_resource` VALUES ('645', '1', '42');
INSERT INTO `sys_role_resource` VALUES ('646', '1', '47');
INSERT INTO `sys_role_resource` VALUES ('647', '1', '24');
INSERT INTO `sys_role_resource` VALUES ('648', '1', '25');
INSERT INTO `sys_role_resource` VALUES ('649', '1', '26');
INSERT INTO `sys_role_resource` VALUES ('650', '1', '27');
INSERT INTO `sys_role_resource` VALUES ('651', '1', '28');
INSERT INTO `sys_role_resource` VALUES ('652', '1', '29');
INSERT INTO `sys_role_resource` VALUES ('653', '1', '30');
INSERT INTO `sys_role_resource` VALUES ('654', '1', '31');
INSERT INTO `sys_role_resource` VALUES ('655', '1', '34');
INSERT INTO `sys_role_resource` VALUES ('656', '1', '46');

-- ----------------------------
-- Table structure for sys_user
-- ----------------------------
DROP TABLE IF EXISTS `sys_user`;
CREATE TABLE `sys_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `username` varchar(32) NOT NULL COMMENT '登录名',
  `password` varchar(56) NOT NULL COMMENT '登录密码',
  `name` varchar(32) DEFAULT '' COMMENT '姓名',
  `sex` varchar(32) DEFAULT '' COMMENT '性别',
  `email` varchar(255) DEFAULT NULL COMMENT '邮箱',
  `phone` varchar(32) DEFAULT '' COMMENT '电话',
  `department` int(11) DEFAULT NULL COMMENT '所在部门',
  `status` int(1) NOT NULL DEFAULT '1' COMMENT '1 有效  0 无效',
  `create_date` datetime DEFAULT NULL COMMENT '创建时间',
  `update_date` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user
-- ----------------------------
INSERT INTO `sys_user` VALUES ('1', 'admin', 'CE182F3D22615862F6CC204667DA04A4292E8DDB092F05FAF4258522', '管理员', '', null, '', '1', '1', '2017-06-08 16:39:59', '2017-06-14 20:29:42');
INSERT INTO `sys_user` VALUES ('2', 'fxw', 'B8F6538085B2336D50B7D8FB111B2D1BB20E7D785280EDD18A116C78', '范小蛙', '', null, '', '2', '1', '2017-06-08 15:19:39', '2017-06-14 20:29:47');
INSERT INTO `sys_user` VALUES ('3', 'fxw2', '3A41FBD9AFAC9BA835019E4888F049A116616C30091E350DAFDFCD11', '范信息', '', null, '', '9', '1', '2017-06-08 16:40:01', '2017-06-15 08:42:44');
INSERT INTO `sys_user` VALUES ('4', 'fxw3', '3A41FBD9AFAC9BA835019E4888F049A116616C30091E350DAFDFC11B', '范子涵', '', null, '', '2', '1', '2017-06-08 16:40:04', '2017-06-15 08:42:42');
INSERT INTO `sys_user` VALUES ('5', 'test', '59DDB6DD90960AC415724CCB2558F9EACF93DC9C40408DFB11257BB9', '饭饭', '男', '', '', '1', '1', null, '2017-06-15 08:42:41');
INSERT INTO `sys_user` VALUES ('25', '22222', 'FBEEB6ED60B8420835481BC6E111AF9E704E4D92382C71DB483907AD', '222222', '男', '', '', '9', '1', null, null);

-- ----------------------------
-- Table structure for sys_user_model
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_model`;
CREATE TABLE `sys_user_model` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL COMMENT '用户id',
  `model_id` int(11) NOT NULL COMMENT '模块id',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_model
-- ----------------------------
INSERT INTO `sys_user_model` VALUES ('7', '1', '1');
INSERT INTO `sys_user_model` VALUES ('8', '1', '3');
INSERT INTO `sys_user_model` VALUES ('9', '1', '7');
INSERT INTO `sys_user_model` VALUES ('10', '1', '2');
INSERT INTO `sys_user_model` VALUES ('11', '1', '4');
INSERT INTO `sys_user_model` VALUES ('12', '1', '5');
INSERT INTO `sys_user_model` VALUES ('13', '1', '6');

-- ----------------------------
-- Table structure for sys_user_role
-- ----------------------------
DROP TABLE IF EXISTS `sys_user_role`;
CREATE TABLE `sys_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `role_id` int(11) NOT NULL,
  PRIMARY KEY (`id`),
  KEY `u_fk` (`user_id`) USING BTREE,
  KEY `r_fk` (`role_id`) USING BTREE,
  CONSTRAINT `sys_user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `sys_user_role_ibfk_1
sys_user_role_ibfk_1` FOREIGN KEY (`user_id`) REFERENCES `sys_user` (`id`),
  CONSTRAINT `sys_user_role_ibfk_2` FOREIGN KEY (`role_id`) REFERENCES `sys_role` (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_user_role
-- ----------------------------
INSERT INTO `sys_user_role` VALUES ('2', '2', '2');
INSERT INTO `sys_user_role` VALUES ('3', '1', '1');
INSERT INTO `sys_user_role` VALUES ('4', '1', '2');
INSERT INTO `sys_user_role` VALUES ('5', '5', '1');
