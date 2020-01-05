/*
 Navicat Premium Data Transfer

 Source Server         : esms
 Source Server Type    : MySQL
 Source Server Version : 50722
 Source Host           : localhost:3306
 Source Schema         : esms

 Target Server Type    : MySQL
 Target Server Version : 50722
 File Encoding         : 65001

 Date: 13/08/2018 16:27:18
*/

SET NAMES utf8mb4;
SET FOREIGN_KEY_CHECKS = 0;

-- ----------------------------
-- Table structure for department
-- ----------------------------
DROP TABLE IF EXISTS `department`;
CREATE TABLE `department`  (
  `d_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `d_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '部门名称',
  `d_remark` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '备注',
  `d_isdel` int(11) NULL DEFAULT NULL COMMENT '可用状态，0不可用，1可用',
  PRIMARY KEY (`d_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 25 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of department
-- ----------------------------
INSERT INTO `department` VALUES (10, '财务部', '组织公司资产资金、成本费用、收入往来、退税纳税等财务核算和财务管理工作', 1);
INSERT INTO `department` VALUES (11, '市场部', '组织市场调查和研究，掌握市场供求信息和行业动态，不定期编制市场动态分析报告，为公司经营决策提供依据。', 1);
INSERT INTO `department` VALUES (13, '行政部', '负责公司日常行政后勤管理、党务及工、青、妇群众团体管理工作。', 1);
INSERT INTO `department` VALUES (14, '人事部', '负责公司人力资源管理与开发。', 1);
INSERT INTO `department` VALUES (16, '开发部', '负责产品设计研发、技术开发等。', 1);
INSERT INTO `department` VALUES (19, '营销部', '产品销售、业务洽谈。', 1);
INSERT INTO `department` VALUES (20, '售后服务部', '负责客户维护、提供产品售后服务。', 1);
INSERT INTO `department` VALUES (21, '设计部', '负责产品设计实现。', 1);
INSERT INTO `department` VALUES (22, '测试维护部', '负责产品测试、检查，维护产品质量。', 1);

-- ----------------------------
-- Table structure for employee
-- ----------------------------
DROP TABLE IF EXISTS `employee`;
CREATE TABLE `employee`  (
  `e_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `e_account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '工号',
  `e_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  `e_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '员工姓名',
  `e_idcard` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '身份证号码',
  `e_sex` varchar(11) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '性别',
  `e_dagree` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '学历',
  `e_birthday` date NULL DEFAULT NULL COMMENT '生日',
  `e_email` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '邮箱',
  `e_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '电话',
  `e_hometown` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '住址',
  `e_rank` int(11) NULL DEFAULT NULL COMMENT '职称，外键',
  `e_head_path` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '头像',
  `e_urgent_person` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '紧急联系人',
  `e_urgent_phone` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '紧急联系人方式',
  `p_id` int(11) NULL DEFAULT NULL COMMENT '岗位id，外键',
  `d_id` int(11) NULL DEFAULT NULL COMMENT '部门id，外键',
  `e_base_pay` double(11, 2) NULL DEFAULT NULL COMMENT '基本工资',
  `e_isdel` int(11) NULL DEFAULT NULL COMMENT '是否在职，0离职，1在职',
  `e_entry_time` date NULL DEFAULT NULL COMMENT '入职时间',
  `e_leave_time` date NULL DEFAULT NULL COMMENT '离职时间',
  PRIMARY KEY (`e_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 45 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of employee
-- ----------------------------
INSERT INTO `employee` VALUES (11, '18080833', 'CE5CEF2C9F0CDF2616FE08C525EBB0FD', '梁培珊', '440681199609065212', '女', '本科', '1996-09-01', '2513567599@163.com', '17876235355', '广东省佛山市顺德区你自己找', 15, '/img/head/2018-08-13369.2875781231495.jpg', '她粑粑', '17876235355', 8, 22, 7500.00, 1, '2018-08-08', '2018-08-13');
INSERT INTO `employee` VALUES (12, '18081302', '73569809D1CFABBB3E2A5218586E9F1C', '丘佳倩', '445222199703294545', '女', '本科', '1997-03-29', '17876253646@qq.com', '17876253588', '广东省肇庆市肇院小区11栋318', 16, '/img/head/2018-08-1325.681911954282487.jpg', 'mami', '17878787878', 9, 22, 7000.00, 1, '2018-02-13', NULL);
INSERT INTO `employee` VALUES (13, '18080809', '73569809D1CFABBB3E2A5218586E9F1C', '刘文晴', '441882201808080000', '女', '本科', '1998-01-01', 'venki@foxmail.com', '17876253509', '广东省肇庆市肇庆学院', 23, '/img/head/2018-08-13915.8155639988832.jpg', '刘文晴', '17876253509', 9, 21, 7000.00, 1, '2015-08-01', NULL);
INSERT INTO `employee` VALUES (14, '18081001', '73569809D1CFABBB3E2A5218586E9F1C', '刘宇', '441623199610181286', '男', '本科', '1996-10-18', '953215743@qq.com', '18125784010', '广东省广州市越秀区人民小区', 16, '/img/head/2018-08-13800.0985428174017.jpg', '刘彬', '17876253121', 10, 16, 8000.00, 1, '2018-08-10', NULL);
INSERT INTO `employee` VALUES (15, '18080810', '73569809D1CFABBB3E2A5218586E9F1C', '张杨洋', '441882201808090001', '男', '本科', '1998-08-01', 'zhangsan@qq.com', '17876253510', '广东省肇庆市肇庆学院', 17, '/img/head/2018-08-13385.0469940398732.jpg', '张三', '17876253410', 24, 16, 8000.00, 1, '2015-09-01', NULL);
INSERT INTO `employee` VALUES (16, '18080811', '73569809D1CFABBB3E2A5218586E9F1C', '李林峰', '441882201808110002', '男', '本科', '1999-02-01', 'lilinfeng@qq.com', '17876253511', '广东省肇庆市肇庆学院', 15, '/img/head/2018-08-13722.5758658452411.jpg', '李四', '17876253411', 24, 22, 6500.00, 1, '2015-09-01', NULL);
INSERT INTO `employee` VALUES (17, '18081002', '73569809D1CFABBB3E2A5218586E9F1C', '罗航', '442524199407062088', '男', '本科', '1994-07-06', 'luohang0706@163.com', '13590813876', '广东省梅州市兴宁市兴南大道中宁江新城', 28, '/img/head/2018-08-13737.783349514442.PNG', '罗彬', '13684044815', 19, 19, 6000.00, 1, '2018-08-10', NULL);
INSERT INTO `employee` VALUES (18, '18080812', '73569809D1CFABBB3E2A5218586E9F1C', '王洋', '441882201808120003', '男', '本科', '2018-05-01', 'wangyang@qq.com', '17876253512', '广东省肇庆市肇庆学院', 28, '/img/head/2018-08-13448.30988559556863.jpg', '王五', '17876253512', 16, 11, 6000.00, 1, '2018-06-01', NULL);
INSERT INTO `employee` VALUES (19, '18022101', '73569809D1CFABBB3E2A5218586E9F1C', '杨候紫', '445222199302271345', '女', '研究生', '1993-02-27', '17876254635@qq.com', '17876254635', '广东省揭阳市某小区18栋302', 22, '/img/head/2018-08-13442.7875916394904.jpg', '小丘', '17876256453', 20, 14, 10000.00, 1, '2018-07-13', NULL);
INSERT INTO `employee` VALUES (20, '18080813', '73569809D1CFABBB3E2A5218586E9F1C', '钱亦兴', '441882201808130004', '男', '本科', '1998-04-01', 'qianyixing@qq.com', '17876253513', '广东省 肇庆市肇庆学院', 28, '/img/head/2018-08-13701.7836576620281.jpg', '钱六', '17876253613', 21, 14, 5000.00, 1, '2015-09-01', NULL);
INSERT INTO `employee` VALUES (21, '14071501', '73569809D1CFABBB3E2A5218586E9F1C', '张曌', '341568199104011688', '男', '硕士', '1991-04-01', 'zhangzhao0401@hotmail.com', '18026920868', '四川省成都市天府新区幸福路', 28, '/img/head/2018-08-13386.5402238473168.PNG', '张庆延', '18826401828', 34, 13, 20000.00, 1, '2014-07-15', NULL);
INSERT INTO `employee` VALUES (22, '18080814', '73569809D1CFABBB3E2A5218586E9F1C', '赵敏', '441882201808140004', '女', '本科', '1997-09-13', 'zhaomin@qq.com', '17876253514', '广东省肇庆市肇庆学院', 28, '/img/head/2018-08-13905.9935000567011.jpg', '赵乾', '17876253414', 12, 10, 6300.00, 1, '2015-07-13', NULL);
INSERT INTO `employee` VALUES (23, '180807', '73569809D1CFABBB3E2A5218586E9F1C', '倪卓栏', '441221199408071234', '女', '本科', '1994-08-01', '1978835852@qq.com', '17876253382', '广东省揭阳市', 28, '/img/head/2018-08-1353.41618548555738.jpg', '倪好', '13988385580', 14, 10, 3500.00, 0, '2018-08-07', '2018-08-13');
INSERT INTO `employee` VALUES (24, '18051205', '73569809D1CFABBB3E2A5218586E9F1C', '张小迪', '445222199505051212', '女', '本科', '1995-05-05', '17876254611@qq.com', '17876254611', '广东省广州市白云区某果园', 28, '/img/head/2018-08-13435.8427919928054.PNG', '小巴', '17876254612', 37, 20, 5000.00, 1, '2018-04-17', NULL);
INSERT INTO `employee` VALUES (25, '17040811', '73569809D1CFABBB3E2A5218586E9F1C', '何菲', '385416199512228130', '女', '大专', '1995-12-22', '168561694@qq.com', '17876381296', '广西省玉林市陆川县源河村何家集', 28, '/img/head/2018-08-13903.8879192749371.PNG', '何英平', '17852389364', 37, 20, 5000.00, 1, '2017-04-08', NULL);
INSERT INTO `employee` VALUES (26, '18080911', '73569809D1CFABBB3E2A5218586E9F1C', '倪妮', '445111199407051234', '女', '本科', '1994-07-05', '1978835852@qq.com', '17876253382', '广东省揭阳市', 28, '/img/head/2018-08-1356.60479626373882.jpg', '倪好', '13976255852', 14, 10, 3500.00, 1, '2018-08-09', NULL);
INSERT INTO `employee` VALUES (27, '16012304', '73569809D1CFABBB3E2A5218586E9F1C', '莫建轩', '440536199002261413', '男', '博士', '1990-02-26', '743421331@qq.com', '17876258512', '广东省肇庆市端州区东岗村', 12, '/img/head/2018-08-13724.6964111060594.jpg', '莫正言', '17612745321', 11, 16, 18000.00, 1, '2018-06-06', NULL);
INSERT INTO `employee` VALUES (28, '18081113', '73569809D1CFABBB3E2A5218586E9F1C', '邓小伦', '441221199005124567', '男', '本科', '1990-05-12', '145852145@qq.com', '13927053384', '广东省深圳市', 6, '/img/head/2018-08-13321.15582664686036.jpg', '邓伦', '13545658871', 11, 22, 6000.00, 1, '2018-08-11', NULL);
INSERT INTO `employee` VALUES (29, '15090302', '73569809D1CFABBB3E2A5218586E9F1C', '林哼哼', '440689977652301568', '女', '本科', '1997-08-31', '126456@163.com', '17876542535', '广东省肇庆市端州区肇庆学院', 28, '/img/head/2018-08-13967.5213973314058.jpg', '张三', '13546785012', 34, 13, 4560.00, 1, '2015-09-03', NULL);
INSERT INTO `employee` VALUES (30, '17032802', '73569809D1CFABBB3E2A5218586E9F1C', '吴婷', '442531199511010305', '女', '本科', '1995-11-01', '15654913@qq.com', '18012394122', '广东省清远市城西大道明珠苑', 28, '/img/head/2018-08-13241.89169805219058.jpg', '吴泉峰', '18175754182', 22, 14, 6500.00, 1, '2017-03-28', NULL);
INSERT INTO `employee` VALUES (31, '16031208', '73569809D1CFABBB3E2A5218586E9F1C', '张庭', '452012369852034565', '男', '本科', '1981-04-22', '413546546@qq.com', '13756984562', '香港九龙', 28, '/img/head/2018-08-13517.0042615005489.jpg', '王琦', '13456985321', 16, 11, 14200.00, 1, '2016-03-12', NULL);
INSERT INTO `employee` VALUES (32, '18081301', '73569809D1CFABBB3E2A5218586E9F1C', '谢斐', '44521119970810458x', '女', '大专', '1997-08-10', 'xiefei@163.com', '13576258872', '广东省湛江市', 28, '/img/head/2018-08-13766.982056183347.jpeg', '谢绵', '18754826281', 37, 20, 3000.00, 1, '2018-08-13', NULL);
INSERT INTO `employee` VALUES (33, '15081302', '73569809D1CFABBB3E2A5218586E9F1C', '王七一', '445222199006070711', '男', '研究生', '1990-06-06', '17876256455@qq.com', '17876256455', '北京市故宫隔壁', 12, '/img/head/2018-08-13112.36718408230372.PNG', '梦梦', '17876256455', 10, 21, 11000.00, 1, '2015-08-13', NULL);
INSERT INTO `employee` VALUES (34, '18071902', '73569809D1CFABBB3E2A5218586E9F1C', '林逸', '440685511926357021', '女', '本科', '2000-12-01', 'ida@qq.com', '18675946859', '广东省云浮市东大街', 28, '/img/head/2018-08-1324.13466224751659.png', '艾达', '17876253535', 21, 14, 4300.00, 1, '2018-07-19', NULL);
INSERT INTO `employee` VALUES (35, '18080214', '73569809D1CFABBB3E2A5218586E9F1C', '赵鑫', '445112199507112323', '男', '本科', '1995-07-11', 'zhaoxin@163.com', '17889253387', '广东省佛山市', 16, '/img/head/2018-08-13259.4837096893765.jpeg', '赵伟', '13945256681', 10, 16, 4000.00, 1, '2018-08-02', NULL);
INSERT INTO `employee` VALUES (36, '14101202', '73569809D1CFABBB3E2A5218586E9F1C', '张汉良', '446581197910319025', '男', '硕士', '1979-10-31', '123544@163.com', '13459875654', '广东省广州市天河区阳光小区八栋1002', 18, '/img/head/2018-08-13864.6213865616921.jpg', '梁培珊', '18675180907', 11, 16, 16500.00, 1, '2014-10-12', NULL);
INSERT INTO `employee` VALUES (37, '170902', '73569809D1CFABBB3E2A5218586E9F1C', '梁漂亮', '236578954621025423', '女', '本科', '1990-03-28', 'lbeautiful@163.com', '17854965423', '湖南省城中心', 23, '/img/head/2018-08-13578.5935285696738.jpg', '梁王', '13598765434', 9, 21, 6500.00, 0, '2017-09-02', '2018-08-13');
INSERT INTO `employee` VALUES (38, '15090205', '73569809D1CFABBB3E2A5218586E9F1C', '杨凌云', '441594199303084235', '男', '硕士', '1993-03-08', '561896796@qq.com', '17841239421', '广东省河源市紫金县环城北路', 28, '/img/head/2018-08-13581.7745264147674.jpg', '杨帆', '17645259118', 13, 10, 14000.00, 1, '2015-09-02', NULL);
INSERT INTO `employee` VALUES (39, '18081006', '73569809D1CFABBB3E2A5218586E9F1C', '孟琪琪', '445331199412104562', '女', '本科', '1994-12-10', '359883586@qq.com', '18917523266', '广东省中山市', 15, '/img/head/2018-08-13784.3667171342782.jpg', '孟江', '18716628261', 9, 22, 4000.00, 1, '2018-08-10', NULL);
INSERT INTO `employee` VALUES (40, '17090333', '73569809D1CFABBB3E2A5218586E9F1C', '梁漂亮', '442653310258950126', '女', '本科', '1990-08-14', 'lbeautiful@163.com', '17895686354', '湖南省市中心', 23, '/img/head/2018-08-13735.3421510445431.jpg', '梁帅气', '18675180604', 9, 21, 6500.00, 1, '2017-09-03', NULL);
INSERT INTO `employee` VALUES (41, '11091202', '73569809D1CFABBB3E2A5218586E9F1C', '李丽海', '445222198908162233', '女', '大专', '1989-08-15', '13876625460@qq.com', '13876625460', '广东省惠州市小金口超市', 28, '/img/head/2018-08-13736.7609357343375.PNG', '米丽', '13876625464', 19, 19, 5000.00, 1, '2015-08-28', NULL);
INSERT INTO `employee` VALUES (42, '18081008', '73569809D1CFABBB3E2A5218586E9F1C', '吴蕾', '44133119950815123x', '女', '本科', '1995-08-15', 'wulei123@163.com', '17888253362', '广东省广州市', 28, '/img/head/2018-08-13755.2230895952057.jpg', '吴斌', '17836266682', 17, 11, 3000.00, 1, '2018-08-10', NULL);
INSERT INTO `employee` VALUES (43, '08080122', '73569809D1CFABBB3E2A5218586E9F1C', '豪豪', '445222201506171706', '男', '幼儿园', '2015-06-17', '17876252525@qq.com', '17876252525', '广东省肇庆市某小区1栋101', 28, '/img/head/2018-08-13979.5486498219902.PNG', 'hismom', '17876253535', 37, 20, 1000.00, 1, '2018-08-01', NULL);
INSERT INTO `employee` VALUES (44, '05010122', '73569809D1CFABBB3E2A5218586E9F1C', '姚期智', '445222198903032211', '男', '博士', '1989-03-03', '17025513212@qq.com', '17025513212', '北京市中关村隔壁', 7, '/img/head/2018-08-13564.7927499681208.jpg', 'Alice', '17025513211', 11, 21, 40000.00, 1, '2017-06-06', NULL);

-- ----------------------------
-- Table structure for key_value
-- ----------------------------
DROP TABLE IF EXISTS `key_value`;
CREATE TABLE `key_value`  (
  `kv_id` int(11) NOT NULL AUTO_INCREMENT,
  `kv_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '键',
  `kv_value` double(11, 0) NULL DEFAULT NULL COMMENT '值',
  PRIMARY KEY (`kv_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 12 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of key_value
-- ----------------------------
INSERT INTO `key_value` VALUES (1, 'food_pay', 200);
INSERT INTO `key_value` VALUES (2, 'traffic_pay', 200);
INSERT INTO `key_value` VALUES (3, 'late_buckle_pay', -20);
INSERT INTO `key_value` VALUES (4, 'early_buckle_pay', -20);
INSERT INTO `key_value` VALUES (5, 'missionallowance', 50);
INSERT INTO `key_value` VALUES (6, 'full_attendance_pay', 200);

-- ----------------------------
-- Table structure for monthly_attendance
-- ----------------------------
DROP TABLE IF EXISTS `monthly_attendance`;
CREATE TABLE `monthly_attendance`  (
  `ma_id` int(11) NOT NULL AUTO_INCREMENT,
  `e_id` int(11) NULL DEFAULT NULL COMMENT '员工id,外键',
  `attendance_time` date NULL DEFAULT NULL COMMENT '某年某月的出勤情况',
  `sick_leave_num` int(11) NULL DEFAULT NULL COMMENT '病假天数',
  `overtime_hour` double(11, 2) NULL DEFAULT NULL COMMENT '平时加班小时',
  `weekend_hour` double(11, 2) NULL DEFAULT NULL COMMENT '周末加班小时',
  `holiday_hour` double(11, 2) NULL DEFAULT NULL COMMENT '节假日加班小时',
  `late_num` int(11) NULL DEFAULT NULL COMMENT '迟到次数',
  `early_num` int(11) NULL DEFAULT NULL COMMENT '早退次数',
  `absence_num` int(11) NULL DEFAULT NULL COMMENT '缺勤天数',
  `business_travel_num` int(11) NULL DEFAULT NULL COMMENT '出差天数',
  `compassionate_leave_num` int(11) NULL DEFAULT NULL COMMENT '事假天数',
  PRIMARY KEY (`ma_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 19 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for position
-- ----------------------------
DROP TABLE IF EXISTS `position`;
CREATE TABLE `position`  (
  `p_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `p_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '岗位名称',
  `p_duty` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '岗位描述',
  `p_post_pay` double(11, 2) NULL DEFAULT NULL COMMENT '岗位补贴',
  `p_isdel` int(11) NULL DEFAULT NULL COMMENT '是否可用，0不可用，1可用',
  PRIMARY KEY (`p_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 40 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of position
-- ----------------------------
INSERT INTO `position` VALUES (8, '配置管理员', '负责最后的编译及提交到中心服务器', 200.00, 1);
INSERT INTO `position` VALUES (9, 'QA', '负责软件测试及产品中测', 300.00, 1);
INSERT INTO `position` VALUES (10, 'RD', '负责软件设计及实施', 500.00, 1);
INSERT INTO `position` VALUES (11, '项目经理', '负责整体项目规划', 800.00, 1);
INSERT INTO `position` VALUES (12, '财务总监', '负责整个财务的运作和公司的财务管理', 300.00, 1);
INSERT INTO `position` VALUES (13, '财务经理', '负责公司的整个财务经营状况', 200.00, 1);
INSERT INTO `position` VALUES (14, '会计', '负责总账和业务往来帐', 100.00, 1);
INSERT INTO `position` VALUES (15, '出纳', '负责现金日记账和银行往来帐', 100.00, 1);
INSERT INTO `position` VALUES (16, '市场部经理', '全面负责市场部门的业务及人员管理', 600.00, 1);
INSERT INTO `position` VALUES (17, '市场调研人员', '负责市场调研计划的制定及实施', 200.00, 1);
INSERT INTO `position` VALUES (18, '市场推广制作人员', '制定年度推广计划', 200.00, 1);
INSERT INTO `position` VALUES (19, '营销人员', '负责对公司产品价值实现过程中各销售环节实行管理、监督、协调、服务', 260.00, 1);
INSERT INTO `position` VALUES (20, '人事部经理', '负责公司人力资源工作的规划，建立、执行招聘、培训、考勤、劳动纪律等人事程序或规章制度', 500.00, 1);
INSERT INTO `position` VALUES (21, '人事专员', '负责招聘及办理入职手续，负责人事档案的管理、保管、用工合同的签订', 200.00, 1);
INSERT INTO `position` VALUES (22, '薪酬福利专员', '负责福利社保工资', 100.00, 1);
INSERT INTO `position` VALUES (23, '培训经理', '负责计划新人培训及员工学习', 300.00, 1);
INSERT INTO `position` VALUES (24, '培训师', '负责实施新人培训及员工学习', 280.00, 1);
INSERT INTO `position` VALUES (34, '行政部总监', '对行政部工作全面负责、组织安排协调本部门职责范围内的工作', 600.00, 1);
INSERT INTO `position` VALUES (35, '行政专员', '协助做好公司公司日常行政事务的组织协调工作', 200.00, 1);
INSERT INTO `position` VALUES (36, '售后服务经理', '全面主持售后服务工作，完善售后服务各部门的规章管理制度', 500.00, 1);
INSERT INTO `position` VALUES (37, '售后接待', '接待请求售后的客户，听取客户意见并向上级汇报', 200.00, 1);

-- ----------------------------
-- Table structure for rank_bonus
-- ----------------------------
DROP TABLE IF EXISTS `rank_bonus`;
CREATE TABLE `rank_bonus`  (
  `rb_id` int(11) NOT NULL AUTO_INCREMENT,
  `rank_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职称名称',
  `rb_bonus` int(11) NULL DEFAULT NULL COMMENT '奖金',
  PRIMARY KEY (`rb_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 29 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of rank_bonus
-- ----------------------------
INSERT INTO `rank_bonus` VALUES (6, '信息系统项目管理师', 200);
INSERT INTO `rank_bonus` VALUES (7, '系统分析师', 200);
INSERT INTO `rank_bonus` VALUES (12, '系统架构设计师', 200);
INSERT INTO `rank_bonus` VALUES (13, '网络规划设计师', 200);
INSERT INTO `rank_bonus` VALUES (14, '系统规划与管理师', 200);
INSERT INTO `rank_bonus` VALUES (15, '软件评测师', 150);
INSERT INTO `rank_bonus` VALUES (16, '软件设计师', 150);
INSERT INTO `rank_bonus` VALUES (17, '网络工程师', 150);
INSERT INTO `rank_bonus` VALUES (18, '系统集成项目管理工程师', 150);
INSERT INTO `rank_bonus` VALUES (19, '信息安全工程师', 150);
INSERT INTO `rank_bonus` VALUES (20, '网络管理员', 100);
INSERT INTO `rank_bonus` VALUES (21, '电子商务技术员', 100);
INSERT INTO `rank_bonus` VALUES (22, '信息系统运行管理员', 100);
INSERT INTO `rank_bonus` VALUES (23, '网页制作员', 100);
INSERT INTO `rank_bonus` VALUES (24, '信息处理技术员', 100);
INSERT INTO `rank_bonus` VALUES (28, '无', 0);

-- ----------------------------
-- Table structure for salary
-- ----------------------------
DROP TABLE IF EXISTS `salary`;
CREATE TABLE `salary`  (
  `s_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `e_id` int(11) NULL DEFAULT NULL COMMENT '员工id',
  `d_id` int(11) NULL DEFAULT NULL COMMENT '部门ID',
  `s_time` date NULL DEFAULT NULL COMMENT '时间某年某月',
  `s_state` int(11) NULL DEFAULT NULL COMMENT '状态0暂存，1已发',
  `base_pay` double(11, 2) NULL DEFAULT NULL COMMENT '基本工资',
  `food_pay` double(11, 2) NULL DEFAULT NULL COMMENT '餐饮补贴',
  `post_pay` double(11, 2) NULL DEFAULT NULL COMMENT '岗位补贴',
  `working_year_pay` double(11, 2) NULL DEFAULT NULL COMMENT '工龄奖金',
  `rank_pay` double(11, 2) NULL DEFAULT NULL COMMENT '职称奖金',
  `traffic_pay` double(11, 2) NULL DEFAULT NULL COMMENT '交通补贴',
  `persion_pay` double(11, 2) NULL DEFAULT NULL COMMENT '养老保险',
  `medical_pay` double(11, 2) NULL DEFAULT NULL COMMENT '医疗保险',
  `unemployment_pay` double(11, 2) NULL DEFAULT NULL COMMENT '失业保险',
  `injury_pay` double(11, 2) NULL DEFAULT NULL COMMENT '工伤保险',
  `birth_pay` double(11, 2) NULL DEFAULT NULL COMMENT '生育保险',
  `housing_pay` double(11, 2) NULL DEFAULT NULL COMMENT '住房公积金',
  `late_pay` double(11, 2) NULL DEFAULT NULL COMMENT '迟到罚金',
  `early_pay` double(11, 2) NULL DEFAULT NULL COMMENT '早退罚金',
  `overtime_pay` double(11, 2) NULL DEFAULT NULL COMMENT '加班奖金',
  `sick_pay` double(11, 2) NULL DEFAULT NULL COMMENT '病假扣额',
  `thing_pay` double(11, 2) NULL DEFAULT NULL COMMENT '事假扣额',
  `business_travel_pay` double(11, 2) NULL DEFAULT NULL COMMENT '出差补贴',
  `full_attendance_pay` double(11, 2) NULL DEFAULT NULL COMMENT '全勤奖',
  `rissue_pay` double(11, 2) NULL DEFAULT NULL COMMENT '补发金额',
  `individual_income_tax` double(11, 2) NULL DEFAULT NULL COMMENT '个人所得税',
  `should_pay` double(11, 2) NULL DEFAULT NULL COMMENT '应发工资',
  `actual_pay` double(11, 2) NULL DEFAULT NULL COMMENT '实发工资',
  PRIMARY KEY (`s_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 191 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for system_manager
-- ----------------------------
DROP TABLE IF EXISTS `system_manager`;
CREATE TABLE `system_manager`  (
  `sm_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `sm_account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `sm_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`sm_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of system_manager
-- ----------------------------
INSERT INTO `system_manager` VALUES (1, 'admin', 'BA5424BD07CD19D61C072815EDE38F49');

-- ----------------------------
-- Table structure for working_years_bonus
-- ----------------------------
DROP TABLE IF EXISTS `working_years_bonus`;
CREATE TABLE `working_years_bonus`  (
  `wyb_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '工龄表主键',
  `wyb_year` int(11) NULL DEFAULT NULL COMMENT '年份\r\n',
  `wyb_bonus` double(11, 2) NULL DEFAULT NULL COMMENT '奖金',
  PRIMARY KEY (`wyb_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 35 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of working_years_bonus
-- ----------------------------
INSERT INTO `working_years_bonus` VALUES (2, 0, 0.00);
INSERT INTO `working_years_bonus` VALUES (3, 1, 100.00);
INSERT INTO `working_years_bonus` VALUES (4, 2, 200.00);
INSERT INTO `working_years_bonus` VALUES (5, 3, 300.00);
INSERT INTO `working_years_bonus` VALUES (6, 4, 400.00);
INSERT INTO `working_years_bonus` VALUES (7, 5, 500.00);
INSERT INTO `working_years_bonus` VALUES (8, 6, 500.00);
INSERT INTO `working_years_bonus` VALUES (9, 7, 500.00);
INSERT INTO `working_years_bonus` VALUES (10, 8, 500.00);
INSERT INTO `working_years_bonus` VALUES (11, 9, 500.00);
INSERT INTO `working_years_bonus` VALUES (12, 10, 500.00);
INSERT INTO `working_years_bonus` VALUES (26, 11, 500.00);
INSERT INTO `working_years_bonus` VALUES (27, 12, 500.00);
INSERT INTO `working_years_bonus` VALUES (28, 13, 500.00);
INSERT INTO `working_years_bonus` VALUES (29, 15, 500.00);
INSERT INTO `working_years_bonus` VALUES (30, 16, 500.00);
INSERT INTO `working_years_bonus` VALUES (31, 17, 500.00);
INSERT INTO `working_years_bonus` VALUES (32, 18, 500.00);
INSERT INTO `working_years_bonus` VALUES (33, 19, 500.00);
INSERT INTO `working_years_bonus` VALUES (34, 20, 500.00);

SET FOREIGN_KEY_CHECKS = 1;
