/*
 Navicat Premium Data Transfer

 Source Server         : 云服务器
 Source Server Type    : MySQL
 Source Server Version : 80027
 Source Host           : 124.223.157.20:3306
 Source Schema         : test

 Target Server Type    : MySQL
 Target Server Version : 80027
 File Encoding         : 65001

 Date: 27/10/2022 15:04:51
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for weather
-- ----------------------------
DROP TABLE IF EXISTS `weather`;
CREATE TABLE `weather`  (
  `id` int NOT NULL AUTO_INCREMENT COMMENT '组件ID',
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '用户名',
  `mail` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `title` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '信息标题',
  `prompt` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '雨天提示',
  `city_id` int NULL DEFAULT NULL COMMENT '城市ID',
  `create_time` datetime NULL DEFAULT NULL COMMENT '创建时间',
  `is_delete` tinyint(1) NULL DEFAULT 1 COMMENT '是否禁用0为禁用',
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1331 CHARACTER SET = utf8 COLLATE = utf8_general_ci COMMENT = '天气表' ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
