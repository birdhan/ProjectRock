/*
Navicat MySQL Data Transfer

Source Server         : localhost_3306
Source Server Version : 50639
Source Host           : localhost:3306
Source Database       : buy_tivket

Target Server Type    : MYSQL
Target Server Version : 50639
File Encoding         : 65001

Date: 2018-06-30 17:33:49
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for movie
-- ----------------------------
DROP TABLE IF EXISTS `movie`;
CREATE TABLE `movie` (
  `id` int(10) NOT NULL AUTO_INCREMENT,
  `movie_name` varchar(20) DEFAULT NULL,
  `movie_type` varchar(20) DEFAULT NULL,
  `movie_img` varchar(50) DEFAULT NULL,
  `movie_price` double(10,0) DEFAULT NULL,
  `movie_sketch` varchar(150) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=43 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of movie
-- ----------------------------
INSERT INTO `movie` VALUES ('28', '侏罗纪世界2', '动作/科幻/冒险', '7955386cf0a24d469988b5144071655f.jpg', '29', '侏罗纪世界主题公园及豪华度假村被失控的恐龙们摧毁已有三年。如今，纳布拉尔岛已经被人类遗弃，岛上幸存的恐龙们在丛林中自给自足。 ');
INSERT INTO `movie` VALUES ('29', '超人总动员2', '喜剧/动作/动画/冒险', 'c25cc63258014e2aa0cfd0a7b6c57b9d.jpg', '29', '超人家族时隔14年强势回归！这次站在聚光灯下的是弹力女超人海伦，超能先生巴鲍勃则在家照料巴小倩和巴小飞，过起了“正常人”的居家生活。');
INSERT INTO `movie` VALUES ('30', ' 超时空同居', '喜剧 / 奇幻', '031a8e096beb491e95551afbaaa8f493.jpg', '29', '来自2018年谷小焦与1999年陆鸣，两人时空重叠意外住在同一个房间。从互相嫌弃到试图“共谋大业”，阴差阳错发生了一系列好笑的事情。');
INSERT INTO `movie` VALUES ('31', '生存家族', '喜剧 / 家庭', 'b31defce5767464ba34baa4c65b30b6c.jpg', '24', '《生存家族》讲述了这么一个故事，突然有一天家里的电器全部无法运转，车，自来水，电池，手机和电视等等都不能使用了，在东京生活的铃木');
INSERT INTO `movie` VALUES ('32', '复仇者联盟3：无限战争', '动作 / 科幻 / 奇幻 / 冒险', '468d0050fd8c477089a74779f6ab1225.jpg', '29', '《复仇者联盟3：无限战争》是漫威电影宇宙10周年的历史性集结，将为影迷们带来史诗版的终极对决。面对灭霸突然发起的闪电袭击，复仇者联盟及其所有超级英雄盟友必须全力以赴，才能阻止他对全宇宙造成毁灭性的打击。');
INSERT INTO `movie` VALUES ('33', '寂静之地', '惊悚 / 恐怖', '1febfa85e72d4443a94f035f916d3f8b.jpg', '24', '父亲铺了沙子路来消音，全家人必须使用手语，还有用来沟通的照明系统…等，才能避免可怕的事情发生，因为猎杀他们的怪物无所不在，这是真正的挑战，他们必须克服生死难关，想尽办法活下去。');
INSERT INTO `movie` VALUES ('34', '哆啦A梦：大雄的金银岛', '动画 / 奇幻 / 冒险', 'cd9147013ecb48459ec663365f98a8fa.jpg', '24', '大雄兴致盎然，又在胖虎、小夫以及静香面前吹嘘要找到海盗的宝藏。当然，最终他还是要依赖忠实的好朋友哆啦A梦。哆啦A梦拿出了秘密道具“金银岛地图”，大雄则极其巧合地在太平洋中心找到了一座藏有金银财宝的新的岛屿。');
INSERT INTO `movie` VALUES ('35', '完美陌生人', '剧情 / 喜剧', 'df938c415ebf4b9aa6db5d5800da4629.jpg', '29', '三对处于各个婚姻阶段的伴侣和一个宅男,七人聚在一起吃晚餐。女主人提议下拍板决定当夜所有人分享每一个电话、每一条短信、邮件的内容,由此许多秘密开始不再是秘密,他们之间的关系也开始发生波动。');
INSERT INTO `movie` VALUES ('36', '头号玩家', '动作 / 科幻 / 冒险', '4d7eabd6952a43c1828ca5ca6351e112.jpg', '29', '詹姆斯哈利迪一手建造了名为“绿洲”的虚拟现实游戏世界，临终前，他宣布自己在游戏中设置了一个彩蛋，找到这枚彩蛋的人即可成为绿洲的继承人。要找到这枚彩蛋，必须先获得三把钥匙，而寻找钥匙的线索就隐藏在詹姆斯的过往之中。 ');
INSERT INTO `movie` VALUES ('37', '忌日快乐', '悬疑 / 惊悚 / 恐怖', 'aaebe9547bcc4a8093cddd71d8526cf5.jpg', '29', '生日当晚惨遭杀害的泰莉，一觉醒来仍然生勾勾，她以为只是生日前夕的恶梦，怎知醒来后的经历跟梦境完全一样，生日当晚再惨遭杀害，然后又一觉醒来…');
INSERT INTO `movie` VALUES ('38', '昆池岩', '恐怖', '9bb90ff1fbd74148af24c47467842ee8.jpg', '29', '早已废弃的昆池岩医院被CNN票选为全球七大恐怖之地之一，更由此吸引无数寻求刺激的青年男女来此探险。三个月前，两名青少年来此探险，结果在直播中遭遇诡异事件，下落不明...');
INSERT INTO `movie` VALUES ('39', '黑豹', '动作 / 科幻 / 冒险', 'd6a489d279b0457aa436e038cf498685.jpg', '24', '特查拉在其父亲——前瓦坎达国王去世之后，回到了这个科技先进但与世隔绝的非洲国家，继任成为新一任“黑豹”及国王。当旧敌重现时，作为“黑豹”及国王的特查拉身陷两难境地，眼看着瓦坎达及全世界陷于危难之中。');
INSERT INTO `movie` VALUES ('40', '环太平洋：雷霆再起', '动作 / 科幻 / 冒险', 'e9cde22574e04eefa35723a616a53bbf.jpg', '24', '在对抗外星人的入侵并成功封住虫洞的十年后，人类从废墟中站起来。他们忘记了恐惧，纵情狂欢，就在这关键时刻，一个与复仇流浪者极其相似的黑色机甲突然出现，并向人类展开了无情杀戮。');
INSERT INTO `movie` VALUES ('41', '野生狼性', '恐怖 / 奇幻', '3ee37cfa76054754ba27b4d270854c43.jpg', '24', '青少女安娜自出生以来就被父亲隔离起来抚养，他尽一切可能隐瞒她的身世。当一位女警官艾伦介入她的生活中，对她的生活背景展开调查后，安娜愈加发现自己对于森林的向往，同时挣扎着抵抗她体内不断增长的嗜血欲望。');
INSERT INTO `movie` VALUES ('42', '寻梦环游记', '喜剧 / 动画 / 音乐 / 奇幻', 'ded5867a3bf649afb682bcf932ca5218.jpg', '29', '热爱音乐的米格尔不幸地出生在一个视音乐为洪水猛兽的大家庭之中，一家人只盼着米格尔快快长大，好继承家里传承了数代的制鞋产业。一年一度的亡灵节即将来临，每逢这一天，去世的亲人们的魂魄便可凭借着摆在祭坛上的照片返回现世和生者团圆。');

-- ----------------------------
-- Table structure for relate
-- ----------------------------
DROP TABLE IF EXISTS `relate`;
CREATE TABLE `relate` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `u_id` int(5) NOT NULL,
  `m_id` int(5) NOT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of relate
-- ----------------------------
INSERT INTO `relate` VALUES ('1', '1', '1');
INSERT INTO `relate` VALUES ('2', '1', '2');
INSERT INTO `relate` VALUES ('3', '0', '0');
INSERT INTO `relate` VALUES ('4', '0', '0');
INSERT INTO `relate` VALUES ('5', '0', '1');
INSERT INTO `relate` VALUES ('6', '0', '8');
INSERT INTO `relate` VALUES ('7', '0', '1');
INSERT INTO `relate` VALUES ('8', '2', '1');
INSERT INTO `relate` VALUES ('9', '2', '9');
INSERT INTO `relate` VALUES ('10', '4', '20');
INSERT INTO `relate` VALUES ('11', '4', '24');
INSERT INTO `relate` VALUES ('12', '4', '23');
INSERT INTO `relate` VALUES ('13', '4', '42');
INSERT INTO `relate` VALUES ('14', '4', '38');
INSERT INTO `relate` VALUES ('15', '4', '28');
INSERT INTO `relate` VALUES ('16', '5', '29');
INSERT INTO `relate` VALUES ('17', '5', '32');
INSERT INTO `relate` VALUES ('18', '2', '32');
INSERT INTO `relate` VALUES ('19', '2', '31');
INSERT INTO `relate` VALUES ('20', '2', '36');

-- ----------------------------
-- Table structure for users
-- ----------------------------
DROP TABLE IF EXISTS `users`;
CREATE TABLE `users` (
  `id` int(5) NOT NULL AUTO_INCREMENT,
  `username` varchar(10) DEFAULT NULL,
  `password` varchar(10) DEFAULT NULL,
  `sex` varchar(2) DEFAULT NULL,
  `age` int(5) DEFAULT NULL,
  `tel` varchar(15) DEFAULT NULL,
  `address` varchar(20) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of users
-- ----------------------------
INSERT INTO `users` VALUES ('1', 'yang', '123', 'n', '12', '反反复复付', '三生三世');
INSERT INTO `users` VALUES ('2', 'a', 'a', 'a', '21', 'a', 'a');
INSERT INTO `users` VALUES ('3', '啊', '啊', '啊', '25', '反反复复', '发广告过过');
INSERT INTO `users` VALUES ('4', '1', '1', 'na', '12', '1525343', '24');
INSERT INTO `users` VALUES ('5', 'wl', '123', 'n', '12', '454545', 'rtyertrterte');
INSERT INTO `users` VALUES ('6', '时代', '阿萨德', null, '21', '阿斯顿发', null);
