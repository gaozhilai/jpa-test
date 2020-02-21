/*
 Navicat Premium Data Transfer

 Source Server         : 本地
 Source Server Type    : MySQL
 Source Server Version : 50726
 Source Host           : localhost:3306
 Source Schema         : jpa_test

 Target Server Type    : MySQL
 Target Server Version : 50726
 File Encoding         : 65001

 Date: 21/02/2020 18:33:08
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for jpa_test
-- ----------------------------
DROP TABLE IF EXISTS `jpa_test`;
CREATE TABLE `jpa_test`  (
  `id` bigint(20) NOT NULL AUTO_INCREMENT,
  `first_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `last_name` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `sex` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `email` varchar(32) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `age` int(12) NOT NULL,
  `valid` bit(1) NULL DEFAULT NULL COMMENT '0假, 1真',
  `birthday` timestamp(0) NULL DEFAULT NULL,
  `version` int(11) NOT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 11 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of jpa_test
-- ----------------------------
INSERT INTO `jpa_test` VALUES (6, '小明', '朱', '男', 'zhuxiaoming@qq.com', 23, NULL, '2020-02-21 11:22:50', 0);
INSERT INTO `jpa_test` VALUES (7, '三', '张', '男', 'zhangsan@qq.com', 25, NULL, NULL, 0);
INSERT INTO `jpa_test` VALUES (8, '桃红', '王', '女', 'wangtaohong@qq.com', 32, b'0', '2020-02-19 11:22:57', 0);
INSERT INTO `jpa_test` VALUES (9, '元璋', '朱', '男', NULL, 999, b'1', NULL, 0);
INSERT INTO `jpa_test` VALUES (10, '斯丹妮', '李', '女', 'LiSidanni@qq.com', 18, b'1', NULL, 0);

SET FOREIGN_KEY_CHECKS = 1;
