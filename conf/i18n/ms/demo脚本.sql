SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for word_list
-- ----------------------------
DROP TABLE IF EXISTS `word_list`;
CREATE TABLE `word_list` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `word_type` varchar(128) CHARACTER SET utf8 NOT NULL COMMENT '标识',
  `sys_group` varchar(60) CHARACTER SET utf8 DEFAULT '' COMMENT '系统分组',
  `zh_cn` varchar(128) CHARACTER SET utf8 NOT NULL COMMENT '中文简体词条',
  `zh_hk` varchar(128) CHARACTER SET utf8 DEFAULT '' COMMENT '中文繁体',
  `zh_en` varchar(128) CHARACTER SET utf8 DEFAULT '' COMMENT '英文',
  `create_user` varchar(32) CHARACTER SET utf8 DEFAULT 'admin' COMMENT '创建者',
  `create_user_id` int(11) NOT NULL DEFAULT '-1' COMMENT '创建者主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(32) CHARACTER SET utf8 DEFAULT 'admin' COMMENT '修改人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `index_word_type` (`word_type`) USING BTREE,
  KEY `idx_zh_cn` (`zh_cn`),
  KEY `idx_create_time_and_update_time` (`create_time`,`update_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=8995 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='词库列表';

SET FOREIGN_KEY_CHECKS = 1;


SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for word_list_log
-- ----------------------------
DROP TABLE IF EXISTS `word_list_log`;
CREATE TABLE `word_list_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `word_type` varchar(128) CHARACTER SET utf8 NOT NULL COMMENT '标识',
  `sys_group` varchar(60) CHARACTER SET utf8 DEFAULT '' COMMENT '系统分组',
  `zh_cn` varchar(128) CHARACTER SET utf8 NOT NULL COMMENT '中文简体词条',
  `operation` varchar(32) CHARACTER SET utf8 DEFAULT '' COMMENT '操作类型',
  `ex_content` varchar(255) CHARACTER SET utf8 DEFAULT '' COMMENT '修改前内容',
  `content` varchar(255) CHARACTER SET utf8 DEFAULT '' COMMENT '修改后内容',
  `create_user` varchar(32) CHARACTER SET utf8 DEFAULT 'admin' COMMENT '创建者',
  `create_user_id` int(11) NOT NULL DEFAULT '-1' COMMENT '创建者主键',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_user` varchar(32) CHARACTER SET utf8 DEFAULT 'admin' COMMENT '修改人',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '修改时间',
  PRIMARY KEY (`id`),
  KEY `idx_zh_cn` (`zh_cn`),
  KEY `idx_create_time_and_update_time` (`create_time`,`update_time`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='词库列表';

SET FOREIGN_KEY_CHECKS = 1;

-- 管理员账号表
CREATE TABLE `t_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(32) NOT NULL COMMENT '用户名',
  `user_pwd` varchar(32) DEFAULT NULL COMMENT '用户密码',
  `status` tinyint(4) DEFAULT '1' COMMENT '用户状态：0-禁用；1-正常',
  `remark` varchar(20) DEFAULT NULL COMMENT '备注',
  `create_time` datetime NOT NULL COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  `user_type` tinyint(2) NOT NULL DEFAULT '0' COMMENT '0:后台用户,1:接口用户',
  `role_id` int(20) DEFAULT NULL COMMENT '角色id',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `pk_t_user_id` (`user_id`),
  UNIQUE KEY `uk_user_name` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COMMENT='用户表';

INSERT INTO `t_user` (`user_id`, `user_name`, `user_pwd`, `status`, `remark`, `create_time`, `update_time`, `user_type`, `role_id`) VALUES ('1', 'admin', 'MTIzNDU2', '1', NULL, '2016-04-11 11:14:03', '2018-11-05 19:28:27', '0', '1');
-- 管理员默认账号秘密 admin/123456


-- 角色表
CREATE TABLE `t_role` (
  `role_id` int(20) NOT NULL AUTO_INCREMENT COMMENT '角色id',
  `role_name` varchar(64) DEFAULT NULL COMMENT '角色名称',
  `enable` tinyint(4) DEFAULT '1' COMMENT '0:不可用,1:可用',
  `remark` varchar(100) DEFAULT '' COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`role_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8 COMMENT='角色表';
INSERT INTO `t_role` (`role_id`, `role_name`, `enable`, `remark`, `create_time`, `update_time`) VALUES ('1', 'admin', '1', '管理员', '2018-10-22 16:43:57', '2018-10-22 16:43:57');

-- 角色菜单分配表
CREATE TABLE `t_role_allocation` (
  `id` int(20) NOT NULL AUTO_INCREMENT,
  `role_id` int(20) NOT NULL COMMENT '角色id',
  `role_name` varchar(64) DEFAULT NULL COMMENT '角色名称',
  `uri` varchar(128) DEFAULT '' COMMENT '可访问uri',
  `menu_name` varchar(128) DEFAULT NULL COMMENT 'uri对应菜单名称',
  `enable` tinyint(4) DEFAULT '1' COMMENT '0:不可用,1:可用',
  `remark` varchar(100) DEFAULT '' COMMENT '备注',
  `create_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '创建时间',
  `update_time` datetime NOT NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP COMMENT '最后修改时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8 COMMENT='角色权限分配表';

INSERT INTO `t_role_allocation` (`id`, `role_id`, `role_name`, `uri`, `menu_name`, `enable`, `remark`, `create_time`, `update_time`) VALUES ('1', '1', 'admin', 'role/index', '角色管理', '1', '', '2018-10-22 15:00:18', '2018-10-22 15:00:18');
INSERT INTO `t_role_allocation` (`id`, `role_id`, `role_name`, `uri`, `menu_name`, `enable`, `remark`, `create_time`, `update_time`) VALUES ('2', '1', 'admin', 'roleAllocation/index', '角色分配', '1', '', '2018-10-22 15:00:34', '2018-10-22 15:00:34');
INSERT INTO `t_role_allocation` (`id`, `role_id`, `role_name`, `uri`, `menu_name`, `enable`, `remark`, `create_time`, `update_time`) VALUES ('3', '1', 'admin', 'to_userdetail', '用户管理', '1', '', '2018-10-22 16:34:50', '2018-10-22 16:34:50');
INSERT INTO `t_role_allocation` (`id`, `role_id`, `role_name`, `uri`, `menu_name`, `enable`, `remark`, `create_time`, `update_time`) VALUES ('4', '1', 'admin', 'to_word_list_log', '系统日志', '1', '', '2018-12-29 10:02:07', '2018-12-29 10:02:11');
INSERT INTO `t_role_allocation` (`id`, `role_id`, `role_name`, `uri`, `menu_name`, `enable`, `remark`, `create_time`, `update_time`) VALUES ('5', '1', 'admin', 'to_officialWebsite_customer', '客户信息', '1', '', '2018-12-29 10:02:26', '2018-12-29 10:03:02');
INSERT INTO `t_role_allocation` (`id`, `role_id`, `role_name`, `uri`, `menu_name`, `enable`, `remark`, `create_time`, `update_time`) VALUES ('6', '1', 'admin', 'to_officialWebsite_publishInfo', '公司信息发布', '1', '', '2018-12-29 10:03:10', '2018-12-29 10:03:31');
INSERT INTO `t_role_allocation` (`id`, `role_id`, `role_name`, `uri`, `menu_name`, `enable`, `remark`, `create_time`, `update_time`) VALUES ('7', '1', 'admin', 'to_officialWebsite_mainBusiness', '公司主营业务', '1', '', '2018-12-29 10:03:52', '2018-12-29 10:04:06');
INSERT INTO `t_role_allocation` (`id`, `role_id`, `role_name`, `uri`, `menu_name`, `enable`, `remark`, `create_time`, `update_time`) VALUES ('8', '1', 'admin', 'to_officialWebsite_TeamMembers', '公司主要成员', '1', '', '2018-12-29 10:04:27', '2018-12-29 10:04:30');

