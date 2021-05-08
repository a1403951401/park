/*
 Navicat Premium Data Transfer

 Source Server         : java04.test.aamcn.com.cn
 Source Server Type    : MySQL
 Source Server Version : 50728
 Source Host           : java04.test.aamcn.com.cn:3306
 Source Schema         : silkroute

 Target Server Type    : MySQL
 Target Server Version : 50728
 File Encoding         : 65001

 Date: 08/05/2021 16:06:52
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for dcp_asset_oss
-- ----------------------------
DROP TABLE IF EXISTS `dcp_asset_oss`;
CREATE TABLE `dcp_asset_oss`  (
                                  `id` bigint(20) NOT NULL AUTO_INCREMENT,
                                  `file_name` varchar(200) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                                  `oss_key` varchar(500) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                                  `hash` varchar(100) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
                                  `size` bigint(20) NOT NULL,
                                  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1709 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = DYNAMIC;

SET FOREIGN_KEY_CHECKS = 1;
