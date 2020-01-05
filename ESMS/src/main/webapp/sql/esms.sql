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

 Date: 04/08/2018 14:20:35
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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
  `e_sex` int(11) NULL DEFAULT NULL COMMENT '性别',
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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for key_value
-- ----------------------------
DROP TABLE IF EXISTS `key_value`;
CREATE TABLE `key_value`  (
  `kv_id` int(11) NOT NULL AUTO_INCREMENT,
  `kv_key` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '键',
  `kv_value` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '值',
  PRIMARY KEY (`kv_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 5 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of key_value
-- ----------------------------
INSERT INTO `key_value` VALUES (1, 'de', '21d25');

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 2 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Records of position
-- ----------------------------
INSERT INTO `position` VALUES (0, '', '', NULL, NULL);

-- ----------------------------
-- Table structure for rank_bonus
-- ----------------------------
DROP TABLE IF EXISTS `rank_bonus`;
CREATE TABLE `rank_bonus`  (
  `rb_id` int(11) NOT NULL AUTO_INCREMENT,
  `rank_name` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '职称名称',
  `rb_bonus` int(11) NULL DEFAULT NULL COMMENT '奖金',
  PRIMARY KEY (`rb_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

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
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for system_manager
-- ----------------------------
DROP TABLE IF EXISTS `system_manager`;
CREATE TABLE `system_manager`  (
  `sm_id` int(11) NOT NULL AUTO_INCREMENT COMMENT 'id',
  `sm_account` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '账号',
  `sm_password` varchar(255) CHARACTER SET utf8 COLLATE utf8_general_ci NULL DEFAULT NULL COMMENT '密码',
  PRIMARY KEY (`sm_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

-- ----------------------------
-- Table structure for working_years_bonus
-- ----------------------------
DROP TABLE IF EXISTS `working_years_bonus`;
CREATE TABLE `working_years_bonus`  (
  `wyb_id` int(11) NOT NULL AUTO_INCREMENT COMMENT '工龄表主键',
  `wyb_year` int(11) NULL DEFAULT NULL COMMENT '年份\r\n',
  `wyb_bonus` double(11, 2) NULL DEFAULT NULL COMMENT '奖金',
  PRIMARY KEY (`wyb_id`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 1 CHARACTER SET = utf8 COLLATE = utf8_general_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;
