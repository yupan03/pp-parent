/*
 Navicat Premium Data Transfer

 Source Server         : 127.0.0.1
 Source Server Type    : MySQL
 Source Server Version : 50729
 Source Host           : 127.0.0.1:3306
 Source Schema         : nacos-config

 Target Server Type    : MySQL
 Target Server Version : 50729
 File Encoding         : 65001

 Date: 18/03/2020 16:51:34
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for config_info
-- ----------------------------
DROP TABLE IF EXISTS `config_info`;
CREATE TABLE `config_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) COLLATE utf8_bin DEFAULT NULL,
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
  `src_user` text COLLATE utf8_bin COMMENT 'source user',
  `src_ip` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  `c_desc` varchar(256) COLLATE utf8_bin DEFAULT NULL,
  `c_use` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `effect` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `type` varchar(64) COLLATE utf8_bin DEFAULT NULL,
  `c_schema` text COLLATE utf8_bin,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfo_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB AUTO_INCREMENT=12 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info';

-- ----------------------------
-- Records of config_info
-- ----------------------------
BEGIN;
INSERT INTO `config_info` VALUES (1, 'pp-web.properties', 'DEFAULT_GROUP', 'spring.datasource.url=jdbc:mysql://localhost:3306/project?characterEncoding=utf8&useSSL=false&verifyServerCertificate=false&serverTimezone=UTC\r\nspring.datasource.driverClassName=com.mysql.cj.jdbc.Driver\r\nspring.datasource.username=root\r\nspring.datasource.password=123456\r\n\r\nspring.datasource.type=com.zaxxer.hikari.HikariDataSource\r\nspring.datasource.hikari.pool-name=KevinHikariPool\r\nspring.datasource.hikari.maximum-pool-size=12\r\nspring.datasource.hikari.connection-timeout=60000\r\nspring.datasource.hikari.minimum-idle=10\r\nspring.datasource.hikari.idle-timeout=50000\r\nspring.datasource.hikari.max-lifetime=540000\r\n\r\nspring.redis.host=localhost\r\nspring.redis.password= 123456\r\nspring.redis.database= 0', '2058840b71bd9f034f803d3bbfb6ea2b', '2019-08-14 17:43:54', '2019-08-14 17:43:54', NULL, '0:0:0:0:0:0:0:1', '', 'e683da2d-ef90-4d13-a127-9e640f78df60', NULL, NULL, NULL, 'text', NULL);
INSERT INTO `config_info` VALUES (2, 'gateway-router', 'DEFAULT_GROUP', '[{\r\n    \"id\": \"serviceId\",\r\n    \"order\": 0,\r\n    \"predicates\":[{\r\n        \"args\":{\"pattern\": \"/web/**\"},\r\n        \"name\": \"Path\"\r\n    }],\r\n    \"uri\": \"lb://account-service\"\r\n}]', '449f1a3f0ae77d575612e65bf3ccc384', '2019-09-26 17:51:47', '2019-09-26 17:51:47', NULL, '0:0:0:0:0:0:0:1', '', 'e683da2d-ef90-4d13-a127-9e640f78df60', '动态路由配置', NULL, NULL, 'json', NULL);
INSERT INTO `config_info` VALUES (3, 'gateway-rounts', 'DEFAULT_GROUP', '[\r\n    {\r\n        \"id\": \"web-service\",\r\n        \"uri\": \"lb://pp-system-admin\",\r\n        \"order\": 1,\r\n        \"predicates\": [{\r\n            \"args\": {\r\n                \"pattern\": \"/system/**\"\r\n            },\r\n            \"name\": \"Path\"\r\n        }],\r\n        \"filters\": [{\r\n            \"args\": {\r\n                \"parts\": \"1\"\r\n            },\r\n            \"name\": \"StripPrefix\"\r\n        }]\r\n    }\r\n]', '12ecddf932f9d562085f2c064b14a5cd', '2019-09-26 18:07:49', '2019-11-22 09:08:15', NULL, '0:0:0:0:0:0:0:1', '', '', 'null', 'null', 'null', 'null', 'null');
INSERT INTO `config_info` VALUES (11, 'gateway-security-dev.yaml', 'DEFAULT_GROUP', 'spring: \r\n  datasource: \r\n    url: jdbc:mysql://47.93.219.196:3306/project?characterEncoding=utf8&useSSL=false&verifyServerCertificate=false\r\n    driverClassName: com.mysql.cj.jdbc.Driver\r\n    username: server\r\n    password: server\r\n    type: com.zaxxer.hikari.HikariDataSource\r\n    hikari: \r\n      pool-name: KevinHikariPool\r\n      maximum-pool-size: 12\r\n      connection-timeout: 60000\r\n      minimum-idle: 10\r\n      idle-timeout: 50000\r\n      max-lifetime: 540000', '197b0cc574356ae25af099a850b713d4', '2019-09-26 20:32:25', '2019-09-26 20:32:25', NULL, '0:0:0:0:0:0:0:1', '', '', NULL, NULL, NULL, 'yaml', NULL);
COMMIT;

-- ----------------------------
-- Table structure for config_info_aggr
-- ----------------------------
DROP TABLE IF EXISTS `config_info_aggr`;
CREATE TABLE `config_info_aggr` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `datum_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'datum_id',
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT '内容',
  `gmt_modified` datetime NOT NULL COMMENT '修改时间',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfoaggr_datagrouptenantdatum` (`data_id`,`group_id`,`tenant_id`,`datum_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='增加租户字段';

-- ----------------------------
-- Table structure for config_info_beta
-- ----------------------------
DROP TABLE IF EXISTS `config_info_beta`;
CREATE TABLE `config_info_beta` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT 'content',
  `beta_ips` varchar(1024) COLLATE utf8_bin DEFAULT NULL COMMENT 'betaIps',
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
  `src_user` text COLLATE utf8_bin COMMENT 'source user',
  `src_ip` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfobeta_datagrouptenant` (`data_id`,`group_id`,`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info_beta';

-- ----------------------------
-- Table structure for config_info_tag
-- ----------------------------
DROP TABLE IF EXISTS `config_info_tag`;
CREATE TABLE `config_info_tag` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `tag_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'tag_id',
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext COLLATE utf8_bin NOT NULL COMMENT 'content',
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'md5',
  `gmt_create` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
  `src_user` text COLLATE utf8_bin COMMENT 'source user',
  `src_ip` varchar(20) COLLATE utf8_bin DEFAULT NULL COMMENT 'source ip',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_configinfotag_datagrouptenanttag` (`data_id`,`group_id`,`tenant_id`,`tag_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_info_tag';

-- ----------------------------
-- Table structure for config_tags_relation
-- ----------------------------
DROP TABLE IF EXISTS `config_tags_relation`;
CREATE TABLE `config_tags_relation` (
  `id` bigint(20) NOT NULL COMMENT 'id',
  `tag_name` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'tag_name',
  `tag_type` varchar(64) COLLATE utf8_bin DEFAULT NULL COMMENT 'tag_type',
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL COMMENT 'data_id',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'group_id',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `nid` bigint(20) NOT NULL AUTO_INCREMENT,
  PRIMARY KEY (`nid`),
  UNIQUE KEY `uk_configtagrelation_configidtag` (`id`,`tag_name`,`tag_type`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='config_tag_relation';

-- ----------------------------
-- Table structure for group_capacity
-- ----------------------------
DROP TABLE IF EXISTS `group_capacity`;
CREATE TABLE `group_capacity` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Group ID，空字符表示整个集群',
  `quota` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
  `usage` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
  `max_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数，，0表示使用默认值',
  `max_aggr_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_group_id` (`group_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='集群、各Group容量信息表';

-- ----------------------------
-- Table structure for his_config_info
-- ----------------------------
DROP TABLE IF EXISTS `his_config_info`;
CREATE TABLE `his_config_info` (
  `id` bigint(64) unsigned NOT NULL,
  `nid` bigint(20) unsigned NOT NULL AUTO_INCREMENT,
  `data_id` varchar(255) COLLATE utf8_bin NOT NULL,
  `group_id` varchar(128) COLLATE utf8_bin NOT NULL,
  `app_name` varchar(128) COLLATE utf8_bin DEFAULT NULL COMMENT 'app_name',
  `content` longtext COLLATE utf8_bin NOT NULL,
  `md5` varchar(32) COLLATE utf8_bin DEFAULT NULL,
  `gmt_create` datetime NOT NULL DEFAULT '2010-05-05 00:00:00',
  `gmt_modified` datetime NOT NULL DEFAULT '2010-05-05 00:00:00',
  `src_user` text COLLATE utf8_bin,
  `src_ip` varchar(20) COLLATE utf8_bin DEFAULT NULL,
  `op_type` char(10) COLLATE utf8_bin DEFAULT NULL,
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT '租户字段',
  PRIMARY KEY (`nid`),
  KEY `idx_gmt_create` (`gmt_create`),
  KEY `idx_gmt_modified` (`gmt_modified`),
  KEY `idx_did` (`data_id`)
) ENGINE=InnoDB AUTO_INCREMENT=31 DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='多租户改造';

-- ----------------------------
-- Records of his_config_info
-- ----------------------------
BEGIN;
INSERT INTO `his_config_info` VALUES (3, 24, 'gateway-rounts', 'DEFAULT_GROUP', '', '[\r\n    {\r\n        \"id\": \"web-service\",\r\n        \"uri\": \"lb://payment-service\",\r\n        \"order\": 1,\r\n        \"predicates\": [{\r\n            \"args\": {\r\n                \"pattern\": \"/web/**\"\r\n            },\r\n            \"name\": \"Path\"\r\n        }],\r\n        \"filters\": [{\r\n            \"args\": {\r\n                \"parts\": \"1\"\r\n            },\r\n            \"name\": \"StripPrefix\"\r\n        }]\r\n    }\r\n]', '1a44bd7953fb03aee4e2a809bf15cde2', '2010-05-05 00:00:00', '2019-11-22 08:51:08', NULL, '0:0:0:0:0:0:0:1', 'U', '');
INSERT INTO `his_config_info` VALUES (3, 25, 'gateway-rounts', 'DEFAULT_GROUP', '', '[\r\n    {\r\n        \"id\": \"web-service\",\r\n        \"uri\": \"lb://pp-system-admin\",\r\n        \"order\": 1\r\n    }\r\n]', '70d3775dd2bf1ad35f07d438e520356c', '2010-05-05 00:00:00', '2019-11-22 08:57:20', NULL, '0:0:0:0:0:0:0:1', 'U', '');
INSERT INTO `his_config_info` VALUES (3, 26, 'gateway-rounts', 'DEFAULT_GROUP', '', '[\r\n    {\r\n        \"id\": \"web-service\",\r\n        \"uri\": \"lb://payment-service\",\r\n        \"order\": 1,\r\n        \"predicates\": [{\r\n            \"args\": {\r\n                \"pattern\": \"/web/**\"\r\n            },\r\n            \"name\": \"Path\"\r\n        }],\r\n        \"filters\": [{\r\n            \"args\": {\r\n                \"parts\": \"1\"\r\n            },\r\n            \"name\": \"StripPrefix\"\r\n        }]\r\n    }\r\n]', '1a44bd7953fb03aee4e2a809bf15cde2', '2010-05-05 00:00:00', '2019-11-22 08:59:07', NULL, '0:0:0:0:0:0:0:1', 'U', '');
INSERT INTO `his_config_info` VALUES (3, 27, 'gateway-rounts', 'DEFAULT_GROUP', '', '[\r\n    {\r\n        \"id\": \"web-service\",\r\n        \"uri\": \"lb://payment-service\",\r\n        \"order\": 1,\r\n        \"predicates\": [],\r\n        \"filters\": []\r\n    }\r\n]', 'e18414b1cb258a0cc0ce240c63c1ea8f', '2010-05-05 00:00:00', '2019-11-22 09:00:23', NULL, '0:0:0:0:0:0:0:1', 'U', '');
INSERT INTO `his_config_info` VALUES (3, 28, 'gateway-rounts', 'DEFAULT_GROUP', '', '[\r\n    {\r\n        \"id\": \"web-service\",\r\n        \"uri\": \"lb://payment-service\",\r\n        \"order\": 1,\r\n        \"predicates\": [{\r\n            \"args\": {\r\n                \"pattern\": \"/web/**\"\r\n            },\r\n            \"name\": \"Path\"\r\n        }],\r\n        \"filters\": [{\r\n            \"args\": {\r\n                \"parts\": \"1\"\r\n            },\r\n            \"name\": \"StripPrefix\"\r\n        }]\r\n    }\r\n]', '1a44bd7953fb03aee4e2a809bf15cde2', '2010-05-05 00:00:00', '2019-11-22 09:01:55', NULL, '0:0:0:0:0:0:0:1', 'U', '');
INSERT INTO `his_config_info` VALUES (3, 29, 'gateway-rounts', 'DEFAULT_GROUP', '', '[\r\n    {\r\n        \"id\": \"web-service\",\r\n        \"uri\": \"lb://payment-service\",\r\n        \"order\": 1,\r\n        \"predicates\": [{\r\n            \"args\": {\r\n                \"pattern\": \"/\"\r\n            },\r\n            \"name\": \"Path\"\r\n        }]\r\n    }\r\n]', '98510c6375df247452148fe80af05297', '2010-05-05 00:00:00', '2019-11-22 09:07:17', NULL, '0:0:0:0:0:0:0:1', 'U', '');
INSERT INTO `his_config_info` VALUES (3, 30, 'gateway-rounts', 'DEFAULT_GROUP', '', '[\r\n    {\r\n        \"id\": \"web-service\",\r\n        \"uri\": \"lb://payment-service\",\r\n        \"order\": 1,\r\n        \"predicates\": [{\r\n            \"args\": {\r\n                \"pattern\": \"/web/**\"\r\n            },\r\n            \"name\": \"Path\"\r\n        }],\r\n        \"filters\": [{\r\n            \"args\": {\r\n                \"parts\": \"1\"\r\n            },\r\n            \"name\": \"StripPrefix\"\r\n        }]\r\n    }\r\n]', '1a44bd7953fb03aee4e2a809bf15cde2', '2010-05-05 00:00:00', '2019-11-22 09:08:15', NULL, '0:0:0:0:0:0:0:1', 'U', '');
COMMIT;

-- ----------------------------
-- Table structure for roles
-- ----------------------------
DROP TABLE IF EXISTS `roles`;
CREATE TABLE `roles` (
  `username` varchar(50) NOT NULL,
  `role` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of roles
-- ----------------------------
BEGIN;
INSERT INTO `roles` VALUES ('nacos', 'ROLE_ADMIN');
COMMIT;

-- ----------------------------
-- Table structure for tenant_capacity
-- ----------------------------
DROP TABLE IF EXISTS `tenant_capacity`;
CREATE TABLE `tenant_capacity` (
  `id` bigint(20) unsigned NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `tenant_id` varchar(128) COLLATE utf8_bin NOT NULL DEFAULT '' COMMENT 'Tenant ID',
  `quota` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '配额，0表示使用默认值',
  `usage` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '使用量',
  `max_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个配置大小上限，单位为字节，0表示使用默认值',
  `max_aggr_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '聚合子配置最大个数',
  `max_aggr_size` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '单个聚合数据的子配置大小上限，单位为字节，0表示使用默认值',
  `max_history_count` int(10) unsigned NOT NULL DEFAULT '0' COMMENT '最大变更历史数量',
  `gmt_create` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '创建时间',
  `gmt_modified` datetime NOT NULL DEFAULT '2010-05-05 00:00:00' COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='租户容量信息表';

-- ----------------------------
-- Table structure for tenant_info
-- ----------------------------
DROP TABLE IF EXISTS `tenant_info`;
CREATE TABLE `tenant_info` (
  `id` bigint(20) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `kp` varchar(128) COLLATE utf8_bin NOT NULL COMMENT 'kp',
  `tenant_id` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_id',
  `tenant_name` varchar(128) COLLATE utf8_bin DEFAULT '' COMMENT 'tenant_name',
  `tenant_desc` varchar(256) COLLATE utf8_bin DEFAULT NULL COMMENT 'tenant_desc',
  `create_source` varchar(32) COLLATE utf8_bin DEFAULT NULL COMMENT 'create_source',
  `gmt_create` bigint(20) NOT NULL COMMENT '创建时间',
  `gmt_modified` bigint(20) NOT NULL COMMENT '修改时间',
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_tenant_info_kptenantid` (`kp`,`tenant_id`),
  KEY `idx_tenant_id` (`tenant_id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin COMMENT='tenant_info';

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `username` varchar(50) NOT NULL,
  `password` varchar(500) NOT NULL,
  `enabled` tinyint(1) NOT NULL,
  PRIMARY KEY (`username`)
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

-- ----------------------------
-- Records of users
-- ----------------------------
BEGIN;
INSERT INTO `users` VALUES ('nacos', '$2a$10$EuWPZHzz32dJN7jexM34MOeYirDdFAZm2kuWj7VEOJhhZkDrxfvUu', 1);
COMMIT;

SET FOREIGN_KEY_CHECKS = 1;
