/*
 Navicat Premium Data Transfer

 Source Server         : bird
 Source Server Type    : MySQL
 Source Server Version : 50639
 Source Host           : localhost:3306
 Source Schema         : wordedit

 Target Server Type    : MySQL
 Target Server Version : 50639
 File Encoding         : 65001

 Date: 28/05/2019 16:21:15
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for contrastlibrary
-- ----------------------------
DROP TABLE IF EXISTS `contrastlibrary`;
CREATE TABLE `contrastlibrary`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `wordurl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `wfid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `uid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of contrastlibrary
-- ----------------------------
INSERT INTO `contrastlibrary` VALUES ('c2e-157f84e44bdfhan53250', 'test1', 'D:\\wordfile\\word\\5df-96f5cd6c6136han1896.docx', '5df-96f5cd6c6136han1896', 'bb5-cace9d65da49han47267');
INSERT INTO `contrastlibrary` VALUES ('7dc-78536ed66bd3han38596', 'test2', 'D:\\wordfile\\word\\9ba-90b7ae64ef39han1223.docx', '9ba-90b7ae64ef39han1223', 'bb5-cace9d65da49han47267');
INSERT INTO `contrastlibrary` VALUES ('c70-7ad931186502han17779', 'test3...', 'D:\\wordfile\\word\\417-c66234369570han11485.docx', '417-c66234369570han11485', 'bb5-cace9d65da49han47267');
INSERT INTO `contrastlibrary` VALUES ('a76-d86e5125b408han38707', 'demo...', 'D:\\wordfile\\word\\020-1adddf0d7a99han61899.docx', '020-1adddf0d7a99han61899', 'bb5-cace9d65da49han47267');
INSERT INTO `contrastlibrary` VALUES ('f94-541cfebbe32fhan78395', '脚本案例王志远', 'D:\\wordfile\\word\\885-9fa67f4d5b54han91971.docx', '885-9fa67f4d5b54han91971', 'bb5-cace9d65da49han47267');

-- ----------------------------
-- Table structure for personal
-- ----------------------------
DROP TABLE IF EXISTS `personal`;
CREATE TABLE `personal`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pwd` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `utype` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pstatus` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of personal
-- ----------------------------
INSERT INTO `personal` VALUES ('011-ba7841fce379han39696', '的', '456', '0', '活跃');
INSERT INTO `personal` VALUES ('1', 'admin', 'admin', '1', '管理员');
INSERT INTO `personal` VALUES ('bb5-cace9d65da49han47267', 'rock', '123', '0', '活跃');
INSERT INTO `personal` VALUES ('e2a-f4ee8f5a5abehan76049', '查欢', '1233456', '0', '活跃');

-- ----------------------------
-- Table structure for wordcolumn
-- ----------------------------
DROP TABLE IF EXISTS `wordcolumn`;
CREATE TABLE `wordcolumn`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `cname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `cgrade` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fatherid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fathername` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `columntype` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `uid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of wordcolumn
-- ----------------------------
INSERT INTO `wordcolumn` VALUES ('14c-f93fb371adbdhan14212', '系统管理', 'first', '0', '0', '1', '1');
INSERT INTO `wordcolumn` VALUES ('20f-f1cdfe623611han66673', '计算机科学与技术', 'first', '0', '0', '0', '011-ba7841fce379han39696');
INSERT INTO `wordcolumn` VALUES ('228-1412ace9104ehan18526', '作业3', 'second', '34b-9a22191d060bhan70197', '我的作业', '0', 'bb5-cace9d65da49han47267');
INSERT INTO `wordcolumn` VALUES ('250-a3d1b0c04e42han3383', '软件工程1', 'first', '0', '0', '0', '011-ba7841fce379han39696');
INSERT INTO `wordcolumn` VALUES ('297-be7b57127b21han39852', '登录日志', 'second', 'daf-3e353ba9e880han90898', '日志文件', '1', '1');
INSERT INTO `wordcolumn` VALUES ('34b-9a22191d060bhan70197', '我的作业', 'first', '0', '0', '0', 'bb5-cace9d65da49han47267');
INSERT INTO `wordcolumn` VALUES ('389-921171493abbhan9494', '我的论文', 'first', '0', '0', '0', 'bb5-cace9d65da49han47267');
INSERT INTO `wordcolumn` VALUES ('4e7-0b57b11d3cffhan649', '信息工程', 'first', '0', '0', '0', '011-ba7841fce379han39696');
INSERT INTO `wordcolumn` VALUES ('647-f2c0bd793bechan65019', '开启自启', 'second', '14c-f93fb371adbdhan14212', '系统管理', '1', '1');
INSERT INTO `wordcolumn` VALUES ('6a4-a730fa06120chan12089', '论文草稿1', 'second', '389-921171493abbhan9494', '我的论文', '0', 'bb5-cace9d65da49han47267');
INSERT INTO `wordcolumn` VALUES ('72b-bc2b9c735aefhan77844', 'test', 'first', '0', '0', '0', 'bb5-cace9d65da49han47267');
INSERT INTO `wordcolumn` VALUES ('a62-41b623018274han65410', '软件一班', 'second', '250-a3d1b0c04e42han3383', '软件工程1', '0', '011-ba7841fce379han39696');
INSERT INTO `wordcolumn` VALUES ('bd9-d1fba83e6d6dhan40343', '作业2', 'second', '34b-9a22191d060bhan70197', '我的作业', '0', 'bb5-cace9d65da49han47267');
INSERT INTO `wordcolumn` VALUES ('cec-f6c4cca8c5e4han92190', '作业1', 'second', '34b-9a22191d060bhan70197', '我的作业', '0', 'bb5-cace9d65da49han47267');
INSERT INTO `wordcolumn` VALUES ('d20-6d263a5e24a5han95610', '网络工程', 'first', '0', '0', '0', '011-ba7841fce379han39696');
INSERT INTO `wordcolumn` VALUES ('daf-3e353ba9e880han90898', '日志文件', 'first', '0', '0', '1', '1');
INSERT INTO `wordcolumn` VALUES ('e7f-ba17b07e8677han26937', '论文草稿2', 'second', '389-921171493abbhan9494', '我的论文', '0', 'bb5-cace9d65da49han47267');
INSERT INTO `wordcolumn` VALUES ('f32-d101ee8a0dechan67905', '查', 'first', '0', '0', '0', 'e2a-f4ee8f5a5abehan76049');

-- ----------------------------
-- Table structure for wordfile
-- ----------------------------
DROP TABLE IF EXISTS `wordfile`;
CREATE TABLE `wordfile`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `wname` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `picurl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `readnumber` int(255) NULL DEFAULT NULL,
  `wordtype` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `pngurl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `worddate` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `wauthor` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `fathercolumn` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `uid` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `annotation` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `htmlurl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `wordurl` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `serveraddress` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of wordfile
-- ----------------------------
INSERT INTO `wordfile` VALUES ('00e-456d210db8cbhan27607', '脚本案例...', 'img/00e-456d210db8cbhan27607.png', 1, '科研教学类', 'png/00e-456d210db8cbhan27607.png', '2019-04-12', '赵鑫', 'cec-f6c4cca8c5e4han92190', 'bb5-cace9d65da49han47267', '添加批注', 'html/00e-456d210db8cbhan27607.html', 'D:\\wordfile\\word\\00e-456d210db8cbhan27607.docx', 'http://localhost:8081/');
INSERT INTO `wordfile` VALUES ('020-1adddf0d7a99han61899', 'demo...', 'img/020-1adddf0d7a99han61899.png', 10, '侃侃而谈类', 'png/020-1adddf0d7a99han61899.png', '2019-04-12', '王海涵', '6a4-a730fa06120chan12089', 'bb5-cace9d65da49han47267', '添加批注', 'html/020-1adddf0d7a99han61899.html', 'D:\\wordfile\\word\\020-1adddf0d7a99han61899.docx', 'http://localhost:8081/');
INSERT INTO `wordfile` VALUES ('18e-b0b0838d92cchan4069', '脚本案例...', 'img/18e-b0b0838d92cchan4069.png', 1, '科研教学类', 'png/18e-b0b0838d92cchan4069.png', '2019-04-13', '韩春阳', '6a4-a730fa06120chan12089', 'bb5-cace9d65da49han47267', '添加批注', 'html/18e-b0b0838d92cchan4069.html', 'D:\\wordfile\\word\\18e-b0b0838d92cchan4069.docx', 'http://localhost:8081/');
INSERT INTO `wordfile` VALUES ('1ba-ab15dd8e37f9han63631', 'demo...', 'img/1ba-ab15dd8e37f9han63631.png', 1, '科研教学类', 'png/1ba-ab15dd8e37f9han63631.png', '2019-04-12', '赵鑫', 'e7f-ba17b07e8677han26937', 'bb5-cace9d65da49han47267', '添加批注', 'html/1ba-ab15dd8e37f9han63631.html', 'D:\\wordfile\\word\\1ba-ab15dd8e37f9han63631.docx', 'http://localhost:8081/');
INSERT INTO `wordfile` VALUES ('282-21e890aee26ahan98734', 'demo...', 'img/282-21e890aee26ahan98734.png', 1, '科研教学类', 'png/282-21e890aee26ahan98734.png', '2019-04-12', '赵鑫', '228-1412ace9104ehan18526', 'bb5-cace9d65da49han47267', '添加批注', 'html/282-21e890aee26ahan98734.html', 'D:\\wordfile\\word\\282-21e890aee26ahan98734.docx', 'http://localhost:8081/');
INSERT INTO `wordfile` VALUES ('3db-4a72df083c66han93789', 'demo...', 'img/3db-4a72df083c66han93789.png', 20, '侃侃而谈类', 'png/3db-4a72df083c66han93789.png', '2019-04-12', '王海涵', '6a4-a730fa06120chan12089', 'bb5-cace9d65da49han47267', '添加批注', 'html/3db-4a72df083c66han93789.html', 'D:\\wordfile\\word\\3db-4a72df083c66han93789.docx', 'http://localhost:8081/');
INSERT INTO `wordfile` VALUES ('417-c66234369570han11485', 'test3...', 'img/417-c66234369570han11485.png', 1, '科研教学类', 'png/417-c66234369570han11485.png', '2019-05-06', '的', '647-f2c0bd793bechan65019', 'bb5-cace9d65da49han47267', '添加批注', 'html/417-c66234369570han11485.html', 'D:\\wordfile\\word\\417-c66234369570han11485.docx', 'http://localhost:8081/');
INSERT INTO `wordfile` VALUES ('4dd-2e4017f9c188han34588', '脚本案例...', 'img/4dd-2e4017f9c188han34588.png', 1, '科研教学类', 'png/4dd-2e4017f9c188han34588.png', '2019-04-12', '赵鑫', 'bd9-d1fba83e6d6dhan40343', 'bb5-cace9d65da49han47267', '添加批注', 'html/4dd-2e4017f9c188han34588.html', 'D:\\wordfile\\word\\4dd-2e4017f9c188han34588.docx', 'http://localhost:8081/');
INSERT INTO `wordfile` VALUES ('5df-96f5cd6c6136han1896', 'test1', 'img/5df-96f5cd6c6136han1896.png', 1, '辅助教材类', 'png/5df-96f5cd6c6136han1896.png', '2019-05-06', '韩', '297-be7b57127b21han39852', 'bb5-cace9d65da49han47267', '添加批注', 'html/5df-96f5cd6c6136han1896.html', 'D:\\wordfile\\word\\5df-96f5cd6c6136han1896.docx', 'http://localhost:8081/');
INSERT INTO `wordfile` VALUES ('623-804fe2314c9ehan9701', '脚本案例...', 'img/623-804fe2314c9ehan9701.png', 1, '科研教学类', 'png/623-804fe2314c9ehan9701.png', '2019-04-13', '韩春阳', 'bd9-d1fba83e6d6dhan40343', 'bb5-cace9d65da49han47267', '添加批注', 'html/623-804fe2314c9ehan9701.html', 'D:\\wordfile\\word\\623-804fe2314c9ehan9701.docx', 'http://localhost:8081/');
INSERT INTO `wordfile` VALUES ('636-5d27402a0484han74012', '脚本案例...', 'img/636-5d27402a0484han74012.png', 30, '侃侃而谈类', 'png/636-5d27402a0484han74012.png', '2019-04-12', '娃哈哈', 'e7f-ba17b07e8677han26937', 'bb5-cace9d65da49han47267', '添加批注', 'html/636-5d27402a0484han74012.html', 'D:\\wordfile\\word\\636-5d27402a0484han74012.docx', 'http://localhost:8081/');
INSERT INTO `wordfile` VALUES ('713-88060d7b4296han83317', 'demo...', 'img/713-88060d7b4296han83317.png', 8, '侃侃而谈类', 'png/713-88060d7b4296han83317.png', '2019-04-12', '王海涵', '6a4-a730fa06120chan12089', 'bb5-cace9d65da49han47267', '添加批注', 'html/713-88060d7b4296han83317.html', 'D:\\wordfile\\word\\713-88060d7b4296han83317.docx', 'http://localhost:8081/');
INSERT INTO `wordfile` VALUES ('885-9fa67f4d5b54han91971', '脚本案例王志远', 'img/885-9fa67f4d5b54han91971.png', 4, '辅助教材类', 'png/885-9fa67f4d5b54han91971.png', '2019-04-12', '二狗子', '6a4-a730fa06120chan12089', 'bb5-cace9d65da49han47267', '添加批注', 'html/885-9fa67f4d5b54han91971.html', 'D:\\wordfile\\word\\885-9fa67f4d5b54han91971.docx', 'http://localhost:8081/');
INSERT INTO `wordfile` VALUES ('9a1-22867cf9e8c0han38520', 'demo...', 'img/9a1-22867cf9e8c0han38520.png', 1, '科研教学类', 'png/9a1-22867cf9e8c0han38520.png', '2019-04-12', '赵鑫', 'cec-f6c4cca8c5e4han92190', 'bb5-cace9d65da49han47267', '添加批注', 'html/9a1-22867cf9e8c0han38520.html', 'D:\\wordfile\\word\\9a1-22867cf9e8c0han38520.docx', 'http://localhost:8081/');
INSERT INTO `wordfile` VALUES ('9ba-90b7ae64ef39han1223', 'test2', 'img/9ba-90b7ae64ef39han1223.png', 1, '辅助教材类', 'png/9ba-90b7ae64ef39han1223.png', '2019-05-06', '韩', '297-be7b57127b21han39852', 'bb5-cace9d65da49han47267', '添加批注', 'html/9ba-90b7ae64ef39han1223.html', 'D:\\wordfile\\word\\9ba-90b7ae64ef39han1223.docx', 'http://localhost:8081/');
INSERT INTO `wordfile` VALUES ('9c1-8da1a1cbfc36han84968', 'demo1', 'img/9c1-8da1a1cbfc36han84968.png', 2, '科研教学类', 'png/9c1-8da1a1cbfc36han84968.png', '2019-04-12', '阳爸爸', 'cec-f6c4cca8c5e4han92190', 'bb5-cace9d65da49han47267', '这个我好想不太会', 'html/9c1-8da1a1cbfc36han84968.html', 'D:\\wordfile\\word\\9c1-8da1a1cbfc36han84968.docx', 'http://localhost:8081/');
INSERT INTO `wordfile` VALUES ('a28-d034a5de1b1chan79087', '脚本案例...', 'img/a28-d034a5de1b1chan79087.png', 1, '科研教学类', 'png/a28-d034a5de1b1chan79087.png', '2019-04-12', '赵鑫', '228-1412ace9104ehan18526', 'bb5-cace9d65da49han47267', '查欢是傻逼', 'html/a28-d034a5de1b1chan79087.html', 'D:\\wordfile\\word\\a28-d034a5de1b1chan79087.docx', 'http://localhost:8081/');
INSERT INTO `wordfile` VALUES ('b11-06e6f977dbebhan68150', '脚本案例...', 'img/b11-06e6f977dbebhan68150.png', 1, '科研教学类', 'png/b11-06e6f977dbebhan68150.png', '2019-04-13', '韩春阳', 'e7f-ba17b07e8677han26937', 'bb5-cace9d65da49han47267', '添加批注', 'html/b11-06e6f977dbebhan68150.html', 'D:\\wordfile\\word\\b11-06e6f977dbebhan68150.docx', 'http://localhost:8081/');
INSERT INTO `wordfile` VALUES ('b75-06fd004be64ahan15170', 'Docker', 'img/b75-06fd004be64ahan15170.png', 1, '四川火锅类', 'png/b75-06fd004be64ahan15170.png', '2019-04-12', '查狗子', '6a4-a730fa06120chan12089', 'bb5-cace9d65da49han47267', '添加批注', 'html/b75-06fd004be64ahan15170.html', 'D:\\wordfile\\word\\b75-06fd004be64ahan15170.docx', 'http://localhost:8081/');
INSERT INTO `wordfile` VALUES ('c09-e26d591df0e6han84784', 'demo...', 'img/c09-e26d591df0e6han84784.png', 1, '科研教学类', 'png/c09-e26d591df0e6han84784.png', '2019-04-12', '赵鑫', 'bd9-d1fba83e6d6dhan40343', 'bb5-cace9d65da49han47267', '添加批注', 'html/c09-e26d591df0e6han84784.html', 'D:\\wordfile\\word\\c09-e26d591df0e6han84784.docx', 'http://localhost:8081/');

-- ----------------------------
-- Table structure for wordtype
-- ----------------------------
DROP TABLE IF EXISTS `wordtype`;
CREATE TABLE `wordtype`  (
  `id` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NOT NULL,
  `name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  `wtype` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE = InnoDB CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Compact;

-- ----------------------------
-- Records of wordtype
-- ----------------------------
INSERT INTO `wordtype` VALUES ('269-db1a60c893dfhan34825', '科研教学类', '0');
INSERT INTO `wordtype` VALUES ('39d-34e16f6830bahan62167', '若无其事类', '0');
INSERT INTO `wordtype` VALUES ('508-4759e5b3c5d1han66360', '侃侃而谈类', '0');
INSERT INTO `wordtype` VALUES ('ca3-4abe60d46121han65408', '四川火锅类', '0');
INSERT INTO `wordtype` VALUES ('f8d-6cd120693348han45535', '辅助教材类', '0');

SET FOREIGN_KEY_CHECKS = 1;
