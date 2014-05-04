/*
Navicat MySQL Data Transfer

Source Server         : 101.102
Source Server Version : 50535
Source Host           : 192.168.101.102:3306
Source Database       : oec

Target Server Type    : MYSQL
Target Server Version : 50535
File Encoding         : 65001

Date: 2014-01-18 14:32:47
*/

SET FOREIGN_KEY_CHECKS=0;
-- ----------------------------
-- Table structure for `a_authentication`
-- ----------------------------
DROP TABLE IF EXISTS `a_authentication`;
CREATE TABLE `a_authentication` (
  `Auth_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '认证ID',
  `Auth_Name` varchar(100) NOT NULL COMMENT '认证名称',
  `Cert_Name` varchar(100) NOT NULL COMMENT '证书名称',
  `Cert_Content` varchar(500) NOT NULL COMMENT '认证介绍',
  `Cert_Unit` varchar(32) NOT NULL COMMENT '发证单位',
  `Tra_Price` float NOT NULL COMMENT '培训价格',
  `Exam_Fee` float NOT NULL COMMENT '报考费',
  `Sort_ID` int(11) DEFAULT NULL COMMENT '认证培训类ID',
  `Auth_Level` int(11) DEFAULT NULL COMMENT '认证级别',
  `Auth_Ctime` datetime DEFAULT NULL COMMENT '创建时间',
  `Auth_Utime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`Auth_ID`),
  KEY `FK_Reference_51` (`Sort_ID`),
  CONSTRAINT `FK_Reference_51` FOREIGN KEY (`Sort_ID`) REFERENCES `a_authentication_type` (`Sort_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of a_authentication
-- ----------------------------
INSERT INTO `a_authentication` VALUES ('1', 'Oracle认证', 'fds', 'dsaf', 'fds', '234', '43', null, null, null, null);
INSERT INTO `a_authentication` VALUES ('2', '仓储管理员资质认证', 'fds', 'fdgf', 'gfd', '345', '543', null, null, null, null);
INSERT INTO `a_authentication` VALUES ('3', '计算机等级考试证', 'hg', 'hfgh', 'fgfh', '345', '345', null, null, null, null);

-- ----------------------------
-- Table structure for `a_authentication_image`
-- ----------------------------
DROP TABLE IF EXISTS `a_authentication_image`;
CREATE TABLE `a_authentication_image` (
  `Image_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '认证图片ID',
  `Auth_ID` varchar(32) NOT NULL COMMENT '认证ID',
  `Image_Type` varchar(50) DEFAULT NULL COMMENT '认证图片类型',
  `Image_Url` varchar(50) DEFAULT NULL COMMENT '认证图片地址',
  `Image_Ctime` datetime DEFAULT NULL COMMENT '创建时间',
  `Image_Utime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`Image_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of a_authentication_image
-- ----------------------------
INSERT INTO `a_authentication_image` VALUES ('1', '1', 'png', 'images/course/carouselBtn.png', null, null);
INSERT INTO `a_authentication_image` VALUES ('2', '2', 'png', 'images/course/carouselBtn.png', null, null);
INSERT INTO `a_authentication_image` VALUES ('3', '3', 'png', 'images/course/carouselBtn.png', null, null);

-- ----------------------------
-- Table structure for `a_authentication_type`
-- ----------------------------
DROP TABLE IF EXISTS `a_authentication_type`;
CREATE TABLE `a_authentication_type` (
  `Sort_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '认证培训类ID',
  `Sort_Name` varchar(50) NOT NULL COMMENT '认证培训类名',
  `TraType_ID` int(11) DEFAULT NULL COMMENT '培训分类ID',
  `Sort_Ctime` datetime DEFAULT NULL COMMENT '创建时间',
  `Sort_Utime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`Sort_ID`),
  KEY `FK_Reference_50` (`TraType_ID`),
  CONSTRAINT `FK_Reference_50` FOREIGN KEY (`TraType_ID`) REFERENCES `train_type` (`TraType_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of a_authentication_type
-- ----------------------------

-- ----------------------------
-- Table structure for `a_user_account`
-- ----------------------------
DROP TABLE IF EXISTS `a_user_account`;
CREATE TABLE `a_user_account` (
  `ACCOUNT_ID` varchar(32) NOT NULL,
  `USER_ACCOUNT` varchar(0) NOT NULL,
  `USER_ID` varchar(32) NOT NULL,
  `ACCOUNT_BLANCE` float NOT NULL,
  `ACCOUNT_STATE` char(2) DEFAULT NULL,
  PRIMARY KEY (`ACCOUNT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of a_user_account
-- ----------------------------

-- ----------------------------
-- Table structure for `a_user_pay_detail`
-- ----------------------------
DROP TABLE IF EXISTS `a_user_pay_detail`;
CREATE TABLE `a_user_pay_detail` (
  `ID` varchar(32) NOT NULL,
  `Account_ID` varchar(32) DEFAULT NULL,
  `Pay_Price` float NOT NULL,
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_41` (`Account_ID`),
  CONSTRAINT `FK_Reference_41` FOREIGN KEY (`Account_ID`) REFERENCES `a_user_account` (`ACCOUNT_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of a_user_pay_detail
-- ----------------------------

-- ----------------------------
-- Table structure for `c_announcement`
-- ----------------------------
DROP TABLE IF EXISTS `c_announcement`;
CREATE TABLE `c_announcement` (
  `Ann_ID` int(11) NOT NULL COMMENT '课程公告ID',
  `Course_ID` int(11) DEFAULT NULL COMMENT '课程ID',
  `Ann_Content` varchar(500) DEFAULT NULL COMMENT '公告内容',
  `Ann_Ctime` datetime NOT NULL COMMENT '创建时间',
  `Ann_Utime` datetime DEFAULT NULL COMMENT '修改时间',
  `Ann_Itime` datetime NOT NULL COMMENT '失效时间',
  PRIMARY KEY (`Ann_ID`),
  KEY `FK_Reference_8` (`Course_ID`),
  CONSTRAINT `FK_Reference_8` FOREIGN KEY (`Course_ID`) REFERENCES `c_course` (`Cour_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of c_announcement
-- ----------------------------

-- ----------------------------
-- Table structure for `c_course`
-- ----------------------------
DROP TABLE IF EXISTS `c_course`;
CREATE TABLE `c_course` (
  `Cour_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '课程ID',
  `Cour_Name` varchar(50) DEFAULT NULL COMMENT '课程名称',
  `Cour_Num` varchar(50) DEFAULT NULL COMMENT '课程编号',
  `Cour_Introduction` varchar(500) DEFAULT NULL COMMENT '课程简介',
  `User_ID` int(11) DEFAULT NULL COMMENT '用户ID',
  `Cour_Tag` varchar(500) DEFAULT NULL COMMENT '课程目标',
  `Cour_Title_Page` varchar(500) DEFAULT NULL COMMENT '课程封面',
  `Cour_IsPublic` char(1) DEFAULT NULL COMMENT '公开标识',
  `Cour_Level` int(11) DEFAULT NULL COMMENT '课程级别',
  `Cour_Password` varchar(32) DEFAULT NULL COMMENT '课程密码',
  `Cour_LiveType` smallint(1) DEFAULT NULL COMMENT '直播标识 1.直播 2.点播',
  `Cour_IsDibbling` char(1) DEFAULT NULL COMMENT '点播标识',
  `Cour_Stime` datetime DEFAULT NULL COMMENT '开课时间',
  `Cour_Etime` datetime DEFAULT NULL COMMENT '结课时间',
  `Cour_Week` int(11) DEFAULT NULL COMMENT '课程周期',
  `Cour_CheckStatus` int(11) DEFAULT NULL COMMENT '审核状态',
  `Cour_ScoreMethod` varchar(500) DEFAULT NULL COMMENT '评分方法',
  `Cour_Class` int(11) DEFAULT NULL COMMENT '课时',
  `Cour_Credit` float DEFAULT NULL COMMENT '课程学分',
  `Cour_BuyCount` int(11) DEFAULT NULL COMMENT '已购买人数',
  `Cour_Attention` int(11) DEFAULT NULL COMMENT '已关注人数',
  `Cour_Price` float DEFAULT NULL COMMENT '课程价格',
  `Cour_PreviewUrl` varchar(200) DEFAULT NULL COMMENT '预览视频URL',
  `Cour_Ctime` datetime DEFAULT NULL COMMENT '创建时间',
  `Cour_Utime` datetime DEFAULT NULL COMMENT '修改时间',
  `School_ID` int(11) DEFAULT NULL COMMENT '学校ID',
  PRIMARY KEY (`Cour_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=utf8 COMMENT='''学校ID''';

-- ----------------------------
-- Records of c_course
-- ----------------------------
INSERT INTO `c_course` VALUES ('1', 'spring精髓课程', null, 'spring基础到高级演练', '8', 'spring演练', 'http://www.baidu.com/img/bdlogo.gif', '1', null, null, null, '1', '2014-01-15 10:51:09', '2014-01-15 10:51:09', '6', null, null, '40', '30', null, null, null, null, null, null, '1');
INSERT INTO `c_course` VALUES ('2', '证券市场基础', null, '证券市场基础知识主要介绍证券行业的运行机构，运行法规，及证券的定义及相关产品，如债券、股票、基金及相关衍生品。此科目是证券考试的必考基础科目，也是掌握另外四门专业课程的基础内容。要求熟练掌握证券和证券市场的基础知识、基本理论、主要法规和职业道德规范；掌握证券中介机构的主要业务和风险监管', '11', '中华中医药大学课程目标', 'http://www.baidu.com/img/bdlogo.gif', '1', null, null, null, '1', '2013-12-01 10:18:38', '2014-01-08 10:18:49', '5', null, null, '45', '23', null, null, '8', null, '2014-01-07 13:12:12', '2014-01-07 13:12:12', '2');
INSERT INTO `c_course` VALUES ('3', '中华中医药', null, '中华中医课程介绍', '11', '中华中医药课程目标', 'http://www.baidu.com/img/bdlogo.gif', '1', null, null, null, '1', '2014-01-08 11:04:12', '2014-01-08 11:04:15', '2', null, null, '50', '20', null, null, '0', null, '2014-01-07 13:28:07', '2014-01-07 13:28:07', '1');
INSERT INTO `c_course` VALUES ('4', 'javaweb开发', null, 'java基础开发', '2', 'javaweb基础开发', 'http://www.baidu.com/img/bdlogo.gif', '1', null, null, null, '1', '2014-01-06 17:16:16', '2014-01-08 17:16:19', '3', null, null, '33', '23', null, null, null, null, '2014-01-08 17:16:34', '2014-01-08 17:16:38', '3');
INSERT INTO `c_course` VALUES ('5', 'struts', null, 'struts', '3', '框架基础知识', 'http://www.baidu.com/img/bdlogo.gif', '1', null, null, null, '1', '2014-01-07 17:18:12', '2014-01-08 17:18:16', '4', null, null, '33', '33', null, null, null, null, '2014-01-06 17:18:28', '2014-01-08 17:18:32', '1');
INSERT INTO `c_course` VALUES ('6', 'Photoshop CS6 全案例轻松自学教程', null, '简介', '4', '介绍  介绍 介绍  介绍', 'http://www.baidu.com/img/bdlogo.gif', '1', null, null, null, '1', '2014-01-07 17:18:12', '2014-01-08 17:18:16', '4', null, '批改提交作业和上交实验', '33', '33', null, null, null, null, '2014-01-06 17:18:28', '2014-01-08 17:18:32', '4');
INSERT INTO `c_course` VALUES ('7', '软件测试', null, '测试基础课程', '5', '测试', 'http://www.baidu.com/img/bdlogo.gif', '1', null, null, null, '1', '2014-01-10 10:27:24', '2014-01-15 10:27:26', '5', null, null, null, null, null, null, null, null, null, null, '1');
INSERT INTO `c_course` VALUES ('8', '操作系统', null, 'window', '6', '操作系统', 'http://www.baidu.com/img/bdlogo.gif', '1', null, null, null, '1', '2014-01-14 10:27:28', '2014-01-20 10:27:30', '5', null, null, null, null, null, null, null, null, null, null, '2');
INSERT INTO `c_course` VALUES ('9', '数据结构', null, '数据结构', '7', '数据结构', 'http://www.baidu.com/img/bdlogo.gif', '1', null, null, null, '1', '2014-01-10 10:50:30', '2014-01-26 10:50:33', '4', null, null, null, null, null, null, null, null, null, null, '2');
INSERT INTO `c_course` VALUES ('10', '硬件管理', null, '硬件基础课程', '8', '硬件组装维护', 'http://www.baidu.com/img/bdlogo.gif', '1', null, null, null, '1', '2014-01-15 10:51:09', '2014-01-15 10:51:09', '3', null, null, '30', '30', null, null, null, null, null, null, '3');
INSERT INTO `c_course` VALUES ('11', 'JAVA高级进阶', null, 'JAVA高级进阶', '11', 'JAVA高级进阶', 'http://a1.att.hudong.com/74/33/300001382835131838336102600_950.jpg', '1', null, null, null, '1', '2014-01-14 10:51:09', '2014-01-14 10:51:12', '5', null, null, null, null, null, null, null, null, null, null, '1');
INSERT INTO `c_course` VALUES ('12', '领域分析', null, '领域分析设计', '1', '领域分析', 'http://www.baidu.com/img/bdlogo.gif', '1', null, null, null, '1', '2014-01-15 10:51:09', '2014-01-15 10:51:09', '3', null, null, '30', '30', null, null, null, null, null, null, '4');
INSERT INTO `c_course` VALUES ('13', '注册国际投资', null, '注册投资理念', '10', '注册投资理念', 'http://www.baidu.com/img/bdlogo.gif', '1', null, null, null, '1', '2014-01-15 10:51:09', '2014-01-15 10:51:09', '3', null, null, '30', '30', null, null, null, null, null, null, '1');
INSERT INTO `c_course` VALUES ('14', '金融分析', null, '金融分析基础', '2', '金融分析基础', 'http://www.baidu.com/img/bdlogo.gif', '1', null, null, null, '1', '2014-01-15 10:51:09', '2014-01-15 10:51:09', '3', null, null, '30', '30', null, null, null, null, null, null, '3');
INSERT INTO `c_course` VALUES ('15', 'CFA教程', null, 'CFA教程', '3', 'CFA教程', 'http://www.baidu.com/img/bdlogo.gif', '1', null, null, null, '1', '2014-01-15 10:51:09', '2014-01-15 10:51:09', '3', null, null, '30', '30', null, null, null, null, null, null, '1');
INSERT INTO `c_course` VALUES ('23', '测试课程1', null, 'fdsafdas', '2', null, '/res/img/course/20140116134911240.jpg', '1', null, null, '1', null, null, null, null, null, null, '0', '10', null, null, '10', '/res/img/video/20140116134911241.flv', '2014-01-16 13:49:16', '2014-01-16 13:49:16', null);
INSERT INTO `c_course` VALUES ('24', '大', null, 'fdasf', '2', null, '/res/img/course/20140116144322335.jpg', '1', null, null, '1', null, null, null, null, null, null, '0', '10', null, null, '10', '/res/img/video/20140116144322336.flv', '2014-01-16 14:43:22', '2014-01-16 14:43:22', null);
INSERT INTO `c_course` VALUES ('25', '测试课程2', null, 'daf', '2', null, '/res/img/course/20140116145041982.jpg', '1', null, null, '1', null, '2014-01-16 19:57:49', null, null, null, null, '0', '20', null, null, '30', '/res/img/video/20140116145041983.flv', '2014-01-16 14:50:42', '2014-01-16 14:50:42', '1');
INSERT INTO `c_course` VALUES ('26', '测试课程3', null, '测试课程3 简介', '2', null, '/res/img/course/20140116200449400.jpg', '1', null, null, '2', null, null, null, null, null, '测试课程3 评分方式', '0', '10', null, null, '20', '/res/img/video/20140116200449405.flv', '2014-01-16 20:04:49', '2014-01-16 20:04:49', '1');
INSERT INTO `c_course` VALUES ('27', '测试课程4', null, '测试课程4简介', '2', null, '/res/img/course/20140117084721021.jpg', '1', null, null, '2', null, null, null, null, null, '测试课程4评分标准', '0', '15', null, null, '15', '/res/img/video/20140117084721034.flv', '2014-01-17 08:47:21', '2014-01-17 08:47:21', null);
INSERT INTO `c_course` VALUES ('28', '测试课程5', null, '大', '2', null, '/res/img/course/20140117085036385.jpg', '1', null, null, '1', null, null, null, null, null, '发大水', '0', '50', null, null, '50', '/res/img/video/20140117085036401.flv', '2014-01-17 08:50:36', '2014-01-17 08:50:36', null);
INSERT INTO `c_course` VALUES ('29', '测试课程10', null, '测试课程10简介', '2', null, '/res/img/course/20140118103653779.jpg', '1', null, null, '2', null, null, null, null, null, '测试课程10评分标准', '0', '20', null, null, '20', '/res/video/course/20140118103653780.flv', null, null, null);
INSERT INTO `c_course` VALUES ('30', '测试课程11', null, '测试课程11 简介', '2', null, '/res/img/course/20140118104223745.jpg', '1', null, null, '1', null, null, null, null, null, '测试课程 评分标准', '0', '10', null, null, '10', '/res/video/course/20140118104224193.flv', null, null, null);
INSERT INTO `c_course` VALUES ('31', '测试课程12', null, '测试课程12的发达', '2', null, '/res/img/course/20140118104923636.jpg', '1', null, null, '1', null, null, null, null, null, '测试课程12得法', '0', '10', null, null, '50', '/res/video/course/20140118104923826.flv', null, null, '2');
INSERT INTO `c_course` VALUES ('32', '测试课程15', null, '测试课程15 简介', '2', null, '/res/img/course/20140118105540992.jpg', '1', null, null, '1', null, null, null, null, null, '测试课程15 课程评分标准', '0', '30', null, null, '50', '/res/video/course/20140118105541138.flv', null, null, '2');
INSERT INTO `c_course` VALUES ('33', '测试课程20', null, '测试课程20测试课程20', '2', null, '/res/img/course/20140118111259271.jpg', '1', null, null, '1', null, null, null, null, null, '测试课程20测试课程20测试课程20', '0', '30', null, null, '40', '/res/video/course/20140118111259273.flv', null, null, '2');
INSERT INTO `c_course` VALUES ('34', '测试课程35', null, '测试课程35测试课程35', '2', null, '/res/img/course/20140118112208073.jpg', '1', null, null, '2', null, null, null, null, null, '测试课程35测试课程35测试课程35', '0', '20', null, null, '50', '/res/video/course/20140118112208078.flv', null, null, '2');
INSERT INTO `c_course` VALUES ('35', '测试课程100', null, '测试课程100测试课程100测试课程100', '2', null, '/res/img/course/20140118112753693.jpg', '1', null, null, '1', null, null, null, null, null, '测试课程100测试课程100', '0', '30', null, null, '15', '/res/video/course/20140118112753695.flv', null, null, '2');
INSERT INTO `c_course` VALUES ('36', '测试课程105', null, '测试课程105 测试课程105', '2', null, null, '1', null, null, '1', null, null, null, null, null, '测试课程105 测试课程105 测试课程105', '0', '20', null, null, '30', null, '2014-01-18 14:12:10', '2014-01-18 14:12:10', '2');
INSERT INTO `c_course` VALUES ('37', '测试课程119', null, '测试课程119 测试课程119', '2', null, '/res/img/course/37/20140118142212732.jpg', '1', null, null, '1', null, null, null, null, null, '测试课程119测试课程119测试课程119', '0', '50', null, null, '50', '/res/video/course/37/20140118142213564.flv', '2014-01-18 14:22:11', '2014-01-18 14:22:19', '2');

-- ----------------------------
-- Table structure for `c_course_detail`
-- ----------------------------
DROP TABLE IF EXISTS `c_course_detail`;
CREATE TABLE `c_course_detail` (
  `Sec_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `Cour_ID` int(11) DEFAULT NULL COMMENT '课程ID',
  `Sec_Name` varchar(255) DEFAULT NULL COMMENT '章节名称',
  `Sec_Title` varchar(255) DEFAULT NULL COMMENT '章节标题',
  `Sec_Introduction` varchar(500) DEFAULT NULL COMMENT '章节介绍',
  `Sec_Start_Time` datetime DEFAULT NULL COMMENT '章节开始时间',
  `Sec_Time` int(11) DEFAULT NULL COMMENT '章节时长',
  `Sec_Type` char(1) NOT NULL COMMENT '点播 or  直播',
  `Sec_Num` int(11) DEFAULT NULL COMMENT '章节编号',
  `Sec_PID` int(11) DEFAULT NULL COMMENT '父章节ID',
  `Sec_Sequence` int(11) DEFAULT NULL COMMENT '章节序号',
  `Sec_Price` float DEFAULT NULL COMMENT '章节价格',
  `Sec_Ctime` datetime DEFAULT NULL COMMENT '创建时间',
  `Sec_Utime` datetime DEFAULT NULL COMMENT '修改时间',
  `Sec_Url` varchar(255) DEFAULT NULL COMMENT '视频URL',
  `Sec_CoreKnowledge` varchar(255) DEFAULT NULL COMMENT '核心知识点',
  PRIMARY KEY (`Sec_ID`),
  KEY `Cour_ID` (`Cour_ID`),
  CONSTRAINT `c_course_detail_ibfk_1` FOREIGN KEY (`Cour_ID`) REFERENCES `c_course` (`Cour_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=61 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of c_course_detail
-- ----------------------------
INSERT INTO `c_course_detail` VALUES ('2', '6', ' 基本工具 一', ' 基本工具 一', ' 基本工具 一 描述', '2014-01-17 13:51:33', '45', '1', '2', '6', '1', null, '2014-01-22 13:52:04', '2014-01-09 13:52:06', null, null);
INSERT INTO `c_course_detail` VALUES ('3', '6', '基本工具 二', '基本工具二', '基本工具 二 描述', '2014-01-09 13:53:22', '50', '1', '3', '6', '2', null, '2014-01-09 13:53:37', '2014-01-09 13:53:40', null, null);
INSERT INTO `c_course_detail` VALUES ('4', '6', '基本工具 第一章 第一小节', '基本工具 第一小节', '基本工具 第一小节', '2014-01-09 13:54:31', '50', '1', '1', '2', '1', null, '2014-01-09 13:54:41', '2014-01-09 13:54:43', '/res/video/0001140108.flv', '基本');
INSERT INTO `c_course_detail` VALUES ('5', '6', '基本工具 第一章  第二小节', '基本工具 第二小节', '基本工具 第二小节', '2014-01-09 13:55:33', '50', '1', '3', '2', '2', null, '2014-01-09 13:55:46', '2014-01-09 13:55:48', '/res/video/22222.flv', null);
INSERT INTO `c_course_detail` VALUES ('6', '2', '证券市场基础 第一章 ', '证券市场基础  第一小节', '证券市场基础  基本描述', '2014-01-05 16:19:38', null, '1', '1', '2', '3', null, null, null, null, null);
INSERT INTO `c_course_detail` VALUES ('7', '2', '证券市场基础 第二章 ', '证券市场基础  第一小节', '证券市场基础  基本概念', '2014-01-20 16:19:35', null, '1', '3', '2', '3', null, null, null, null, null);
INSERT INTO `c_course_detail` VALUES ('9', '2', '证券市场基础 第一章  第一节', '第一章  第一节', '第一章  第一节', '2014-01-14 15:41:04', '33', '1', '2', '6', '2', null, '2014-01-14 15:41:48', '2014-01-14 15:41:50', null, null);
INSERT INTO `c_course_detail` VALUES ('10', '2', '证券市场基础 第一章  第二节', '第一章  第二节', '第一章 第二节', '2014-01-14 15:43:00', '33', '1', '2', '6', '2', null, '2014-01-14 15:43:17', '2014-01-14 15:43:19', null, null);
INSERT INTO `c_course_detail` VALUES ('11', '2', '证券市场基础 第二章  第一节', '第二章  第一节', '第二章  第一节', '2014-01-14 15:43:50', '22', '1', '2', '7', '2', null, '2014-01-14 15:44:13', '2014-01-14 15:44:18', null, null);
INSERT INTO `c_course_detail` VALUES ('12', '6', '基本工具 第二章 第一小节', '第二章 第一小节', '第二章 第一小节', '2014-01-14 17:09:16', '22', '1', '2', '3', '3', null, '2014-01-14 17:09:49', '2014-01-14 17:09:52', null, null);
INSERT INTO `c_course_detail` VALUES ('13', '8', '操作系统 第一章', null, null, null, null, '1', null, null, null, null, null, null, null, null);
INSERT INTO `c_course_detail` VALUES ('14', '9', '数据结构 第一章', null, null, null, null, '1', null, null, null, null, null, null, null, null);
INSERT INTO `c_course_detail` VALUES ('30', '26', '第一章', '章1', null, null, '0', '2', '0', '26', '1', '0', '2014-01-16 20:05:55', '2014-01-16 20:05:55', '/res/resource.flv', '基础语法');
INSERT INTO `c_course_detail` VALUES ('31', '26', '节11', '节11', null, null, '0', '2', '0', '30', '1', '0', '2014-01-16 20:05:55', '2014-01-18 09:03:15', '/res/video/course/26/20140118102959028.flv', 'fdas');
INSERT INTO `c_course_detail` VALUES ('32', '26', '第二章', '章2', null, null, '0', '2', '0', '26', '2', '0', '2014-01-16 20:05:55', '2014-01-16 20:05:55', '/res/ss.flv', '基础语法');
INSERT INTO `c_course_detail` VALUES ('33', '26', '节21', '节21', null, null, '0', '2', '0', '32', '1', '0', '2014-01-16 20:05:55', '2014-01-17 15:29:35', '/res/video/course/26/20140117152929705.flv', '核心知识点');
INSERT INTO `c_course_detail` VALUES ('34', '26', '节22', '节22', null, null, '0', '2', '0', '32', '2', '0', '2014-01-16 20:05:55', '2014-01-18 08:54:19', '/res/video/course/26/20140118103252299.flv', '打发');
INSERT INTO `c_course_detail` VALUES ('35', '26', '章3', '章3', null, null, '0', '2', '0', '26', '3', '0', '2014-01-16 20:05:55', '2014-01-16 20:05:55', null, null);
INSERT INTO `c_course_detail` VALUES ('36', '26', '节31', '节31', null, null, '0', '2', '0', '35', '1', '0', '2014-01-16 20:05:55', '2014-01-16 20:05:55', '/res/video/course/26/20140118102844991.flv', null);
INSERT INTO `c_course_detail` VALUES ('37', '26', '节32', '节32', null, null, '0', '2', '0', '35', '2', '0', '2014-01-16 20:05:55', '2014-01-18 09:04:34', '/res/video/course/26/20140118090434422.flv', 'dafdasf');
INSERT INTO `c_course_detail` VALUES ('38', '26', '节33', '节33', null, null, '0', '2', '0', '35', '3', '0', '2014-01-16 20:05:55', '2014-01-18 09:09:40', '/res/video/course/26/20140118090940640.flv', 'da ');
INSERT INTO `c_course_detail` VALUES ('39', '29', '10-章一', null, null, null, '0', '2', '0', '29', '1', '0', '2014-01-18 10:38:59', '2014-01-18 10:38:59', null, null);
INSERT INTO `c_course_detail` VALUES ('40', '29', '10-章二', null, null, null, '0', '2', '0', '29', '2', '0', '2014-01-18 10:38:59', '2014-01-18 10:38:59', null, null);
INSERT INTO `c_course_detail` VALUES ('41', '32', '章一 ', null, null, null, '0', '2', '0', '32', '1', '0', '2014-01-18 11:11:49', '2014-01-18 11:11:49', null, null);
INSERT INTO `c_course_detail` VALUES ('42', '32', '章二', null, null, null, '0', '2', '0', '32', '2', '0', '2014-01-18 11:11:49', '2014-01-18 11:11:49', null, null);
INSERT INTO `c_course_detail` VALUES ('43', '34', '章一', null, null, null, '0', '2', '0', '34', '1', '0', '2014-01-18 11:22:48', '2014-01-18 11:22:48', null, null);
INSERT INTO `c_course_detail` VALUES ('44', '34', '章一 节一', null, null, null, '0', '2', '0', '43', '1', '0', '2014-01-18 11:22:49', '2014-01-18 11:22:49', null, null);
INSERT INTO `c_course_detail` VALUES ('45', '34', '章一 节二', null, null, null, '0', '2', '0', '43', '2', '0', '2014-01-18 11:22:49', '2014-01-18 11:22:49', null, null);
INSERT INTO `c_course_detail` VALUES ('46', '34', '章一 节三', null, null, null, '0', '2', '0', '43', '3', '0', '2014-01-18 11:22:49', '2014-01-18 11:22:49', null, null);
INSERT INTO `c_course_detail` VALUES ('47', '34', '章二', null, null, null, '0', '2', '0', '34', '2', '0', '2014-01-18 11:22:48', '2014-01-18 11:22:49', null, null);
INSERT INTO `c_course_detail` VALUES ('48', '34', '章二 节一', null, null, null, '0', '2', '0', '47', '1', '0', '2014-01-18 11:22:49', '2014-01-18 11:22:49', null, null);
INSERT INTO `c_course_detail` VALUES ('49', '34', '章二 节二', null, null, null, '0', '2', '0', '47', '2', '0', '2014-01-18 11:22:49', '2014-01-18 11:22:49', null, null);
INSERT INTO `c_course_detail` VALUES ('50', '34', '章二 节三', null, null, null, '0', '2', '0', '47', '3', '0', '2014-01-18 11:22:49', '2014-01-18 11:22:49', null, null);
INSERT INTO `c_course_detail` VALUES ('51', '35', '章一', null, null, null, '0', '2', '0', '35', '1', '0', '2014-01-18 11:28:18', '2014-01-18 11:28:18', null, null);
INSERT INTO `c_course_detail` VALUES ('52', '35', '章一 节一', null, null, null, '0', '2', '0', '51', '1', '0', '2014-01-18 11:28:18', '2014-01-18 11:28:18', null, null);
INSERT INTO `c_course_detail` VALUES ('53', '35', '章二', null, null, null, '0', '2', '0', '35', '2', '0', '2014-01-18 11:28:18', '2014-01-18 11:28:18', null, null);
INSERT INTO `c_course_detail` VALUES ('54', '35', '章二 节一', null, null, null, '0', '2', '0', '53', '1', '0', '2014-01-18 11:28:18', '2014-01-18 11:28:18', null, null);
INSERT INTO `c_course_detail` VALUES ('55', '35', '章二 节二', null, null, null, '0', '2', '0', '53', '2', '0', '2014-01-18 11:28:18', '2014-01-18 11:28:18', '/res/video/course/35/20140118112833658.jpg', null);
INSERT INTO `c_course_detail` VALUES ('56', '37', '章一', null, null, null, '0', '2', '0', '37', '1', '0', '2014-01-18 14:23:48', '2014-01-18 14:23:48', null, null);
INSERT INTO `c_course_detail` VALUES ('57', '37', '章一 节一', null, null, null, '0', '2', '0', '56', '1', '0', '2014-01-18 14:23:48', '2014-01-18 14:23:48', '/res/video/course/37/20140118142438176.flv', ' 节一 核心知识点');
INSERT INTO `c_course_detail` VALUES ('58', '37', '章二', null, null, null, '0', '2', '0', '37', '2', '0', '2014-01-18 14:23:48', '2014-01-18 14:23:48', null, null);
INSERT INTO `c_course_detail` VALUES ('59', '37', '章二 节一', null, null, null, '0', '2', '0', '58', '1', '0', '2014-01-18 14:23:48', '2014-01-18 14:23:48', '/res/video/course/37/20140118142522017.flv', '章二 节一核心知识点');
INSERT INTO `c_course_detail` VALUES ('60', '37', '章二 节二', null, null, null, '0', '2', '0', '58', '2', '0', '2014-01-18 14:23:48', '2014-01-18 14:23:48', '/res/video/course/37/20140118142543158.flv', '章二 节二  核心知识点');

-- ----------------------------
-- Table structure for `c_course_plan`
-- ----------------------------
DROP TABLE IF EXISTS `c_course_plan`;
CREATE TABLE `c_course_plan` (
  `ID` varchar(32) NOT NULL,
  `PLAN_TIME` datetime DEFAULT NULL COMMENT '课程计划时间',
  `PLAN_ACTIVITY` varchar(255) DEFAULT NULL COMMENT '课程计划活动',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of c_course_plan
-- ----------------------------

-- ----------------------------
-- Table structure for `c_course_price`
-- ----------------------------
DROP TABLE IF EXISTS `c_course_price`;
CREATE TABLE `c_course_price` (
  `ID` varchar(32) NOT NULL,
  `PRICE` varchar(32) NOT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of c_course_price
-- ----------------------------

-- ----------------------------
-- Table structure for `c_forums`
-- ----------------------------
DROP TABLE IF EXISTS `c_forums`;
CREATE TABLE `c_forums` (
  `FORUMS_ID` varchar(32) NOT NULL,
  `FORUMS_TITLE` varchar(255) NOT NULL COMMENT '主题',
  `FORUMS_CONTENT` varchar(500) NOT NULL COMMENT '内容',
  `FORUMS_TIME` datetime NOT NULL COMMENT '发布时间',
  `FORUMS_OWNER` varchar(50) DEFAULT NULL COMMENT '发布者',
  PRIMARY KEY (`FORUMS_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of c_forums
-- ----------------------------

-- ----------------------------
-- Table structure for `c_posts`
-- ----------------------------
DROP TABLE IF EXISTS `c_posts`;
CREATE TABLE `c_posts` (
  `ID` varchar(32) NOT NULL,
  `POSTS_CONTENT` varchar(255) DEFAULT NULL COMMENT '帖子内容',
  `POSTS_TIME` datetime DEFAULT NULL COMMENT '发帖时间',
  `POSTS_OWNER` varchar(32) DEFAULT NULL COMMENT '发帖人',
  `CITE_ID` varchar(32) DEFAULT NULL COMMENT '引用贴ID',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of c_posts
-- ----------------------------

-- ----------------------------
-- Table structure for `e_exam`
-- ----------------------------
DROP TABLE IF EXISTS `e_exam`;
CREATE TABLE `e_exam` (
  `Exam_ID` int(11) NOT NULL COMMENT '考试ID',
  `Paper_ID` int(11) DEFAULT NULL COMMENT '试卷ID',
  `Exam_Name` varchar(50) DEFAULT NULL COMMENT '考试名称、作业名称；',
  `Exam_Type` int(2) NOT NULL COMMENT '考试类型 1：课程考试；2：课程练习；3：课程作业；4：认证考试',
  `User_ID` varchar(32) NOT NULL COMMENT '出卷人ID',
  `Exam_CourseID` int(11) DEFAULT NULL COMMENT '课程ID',
  `Exam_Sec_ID` int(11) DEFAULT NULL COMMENT '章节ID',
  `Exam_Duration` float DEFAULT NULL COMMENT '考试时长',
  `Exam_Address` varchar(50) DEFAULT NULL COMMENT '考试地点',
  `Exam_LastTime` datetime DEFAULT NULL COMMENT '提交期限',
  `Exam_MarType` char(2) DEFAULT NULL COMMENT '考试评分方式',
  `Exam_Level` char(2) DEFAULT NULL COMMENT '考试难度',
  `Exam_Count` int(11) DEFAULT NULL COMMENT '已参加人数',
  `Exam_PassCount` char(2) DEFAULT NULL COMMENT '通过人数',
  `Exam_Content` varchar(500) DEFAULT NULL COMMENT '考试概述',
  `Exam_Price` float DEFAULT NULL COMMENT '价格',
  `Exam_Ctime` datetime DEFAULT NULL COMMENT '创建时间',
  `Exam_Utime` datetime DEFAULT NULL COMMENT '修改时间',
  `Exam_Time` datetime DEFAULT NULL COMMENT '考试时间',
  PRIMARY KEY (`Exam_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of e_exam
-- ----------------------------
INSERT INTO `e_exam` VALUES ('1', '1', '某某课程考试', '1', '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `e_exam` VALUES ('2', '2', '某某课程章节练习', '2', '1', '1', '1', null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `e_exam` VALUES ('3', '3', '某某课程作业', '3', '1', '1', null, null, null, null, null, null, null, null, null, null, null, null, null);
INSERT INTO `e_exam` VALUES ('4', '4', '会计师认证考试', '4', '1', null, null, null, null, null, null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `e_exam_answer_detail`
-- ----------------------------
DROP TABLE IF EXISTS `e_exam_answer_detail`;
CREATE TABLE `e_exam_answer_detail` (
  `Exam_Answer_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '作答明细ID',
  `UserID` int(11) DEFAULT NULL COMMENT '学生考试ID',
  `Exam_ID` int(11) DEFAULT NULL,
  `Exam_Option_ID` int(11) DEFAULT NULL COMMENT '试卷试题项ID',
  `Exam_Answer` varchar(500) NOT NULL COMMENT '做题答案',
  `Exam_Option_Score` float NOT NULL COMMENT '选项总分',
  `Exam_Answer_Score` float NOT NULL COMMENT '得分',
  `Mark_State` char(2) NOT NULL COMMENT '批改状态',
  `Exam_Answer_Ctime` datetime DEFAULT NULL COMMENT '创建时间',
  `Exam_Answer_Utime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`Exam_Answer_ID`),
  KEY `FK_Reference_27` (`UserID`),
  KEY `FK_Reference_28` (`Exam_Option_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of e_exam_answer_detail
-- ----------------------------

-- ----------------------------
-- Table structure for `e_exam_option`
-- ----------------------------
DROP TABLE IF EXISTS `e_exam_option`;
CREATE TABLE `e_exam_option` (
  `Exam_Opt_ID` int(11) NOT NULL COMMENT '试卷试题项ID',
  `Exam_Ques_ID` int(11) DEFAULT NULL COMMENT '试卷试题ID',
  `Ques_ID` int(11) DEFAULT NULL COMMENT '试题ID',
  `Exam_Opt_Num` int(11) NOT NULL COMMENT '试卷试题项序号',
  `Exam_Opt_Score` float NOT NULL COMMENT '试卷试题项分数',
  `Exam_Opt_Ctime` datetime DEFAULT NULL COMMENT '创建时间',
  `Exam_Opt_Utime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`Exam_Opt_ID`),
  KEY `FK_Reference_22` (`Exam_Ques_ID`),
  KEY `FK_Reference_23` (`Ques_ID`),
  CONSTRAINT `FK_Reference_22` FOREIGN KEY (`Exam_Ques_ID`) REFERENCES `e_exam_question` (`Exam_Ques_ID`),
  CONSTRAINT `FK_Reference_23` FOREIGN KEY (`Ques_ID`) REFERENCES `e_question` (`Ques_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of e_exam_option
-- ----------------------------
INSERT INTO `e_exam_option` VALUES ('1', '1', '1', '1', '5', '2014-01-18 12:16:24', '2014-01-18 12:16:27');
INSERT INTO `e_exam_option` VALUES ('2', '1', '2', '2', '5', null, null);
INSERT INTO `e_exam_option` VALUES ('3', '2', '3', '1', '5', null, null);
INSERT INTO `e_exam_option` VALUES ('4', '3', '4', '1', '5', null, null);
INSERT INTO `e_exam_option` VALUES ('5', '4', '5', '1', '50', null, null);

-- ----------------------------
-- Table structure for `e_exam_paper`
-- ----------------------------
DROP TABLE IF EXISTS `e_exam_paper`;
CREATE TABLE `e_exam_paper` (
  `Paper_ID` int(11) NOT NULL COMMENT '试卷ID',
  `Paper_Name` varchar(100) NOT NULL COMMENT '试卷名称',
  `Paper_Total_Score` float DEFAULT NULL COMMENT '试卷总分',
  `User_ID` int(11) NOT NULL COMMENT '创建人ID',
  `Paper_Type` int(11) NOT NULL COMMENT '试卷类型1：课程考试；2：认证考试',
  `Paper_State` char(2) NOT NULL COMMENT '试卷状态',
  `Paper_Ctime` datetime NOT NULL COMMENT '创建时间',
  `Paper_Utime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`Paper_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of e_exam_paper
-- ----------------------------
INSERT INTO `e_exam_paper` VALUES ('1', '冯楚福测试课程考试试卷', '100', '1', '1', '1', '2014-01-18 11:26:35', '2014-01-18 11:26:39');
INSERT INTO `e_exam_paper` VALUES ('2', '冯楚福测试课程作业试卷', '100', '1', '1', '1', '2014-01-18 11:31:46', '2014-01-18 11:31:49');
INSERT INTO `e_exam_paper` VALUES ('3', '冯楚福测试课程练习试卷', '100', '1', '1', '1', '2014-01-18 11:32:51', '2014-01-18 11:32:53');
INSERT INTO `e_exam_paper` VALUES ('4', '冯楚福测试认证考试试卷1', '120', '1', '2', '1', '2014-01-18 11:33:43', '2014-01-18 11:33:46');

-- ----------------------------
-- Table structure for `e_exam_question`
-- ----------------------------
DROP TABLE IF EXISTS `e_exam_question`;
CREATE TABLE `e_exam_question` (
  `Exam_Ques_ID` int(11) NOT NULL COMMENT '试卷试题ID',
  `Paper_ID` int(11) NOT NULL COMMENT '试卷ID',
  `Exam_Ques_Num` int(11) NOT NULL COMMENT '试卷试题类型的显示顺序',
  `Exam_Ques_Type` int(11) NOT NULL COMMENT '试卷试题类型1：单选，2多选3判断4简答',
  `Exam_Ques_Score` float NOT NULL COMMENT '试卷某类试题的总分数',
  `Exam_Ques_Ctime` datetime DEFAULT NULL COMMENT '创建时间',
  `Exam_Ques_Utime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`Exam_Ques_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of e_exam_question
-- ----------------------------
INSERT INTO `e_exam_question` VALUES ('1', '1', '1', '1', '15', '2014-01-18 11:53:44', '2014-01-18 11:53:47');
INSERT INTO `e_exam_question` VALUES ('2', '1', '2', '2', '10', null, null);
INSERT INTO `e_exam_question` VALUES ('3', '1', '3', '3', '5', null, null);
INSERT INTO `e_exam_question` VALUES ('4', '1', '4', '4', '50', null, null);

-- ----------------------------
-- Table structure for `e_exam_student`
-- ----------------------------
DROP TABLE IF EXISTS `e_exam_student`;
CREATE TABLE `e_exam_student` (
  `ID` int(11) NOT NULL COMMENT '学生考试ID',
  `Exam_ID` int(11) DEFAULT NULL COMMENT '考试ID',
  `User_ID` int(11) NOT NULL COMMENT '学生ID',
  `Exam_Time` datetime NOT NULL COMMENT '开始考试时间',
  `Exam_Etime` datetime NOT NULL COMMENT '交卷时间',
  `Exam_Score` float DEFAULT NULL COMMENT '得分',
  `Exam_Ctime` datetime DEFAULT NULL COMMENT '创建时间',
  `Exam_Utime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of e_exam_student
-- ----------------------------
INSERT INTO `e_exam_student` VALUES ('1', '1', '2', '2014-01-18 14:27:49', '2014-01-18 14:27:52', '78', null, null);

-- ----------------------------
-- Table structure for `e_exam_target`
-- ----------------------------
DROP TABLE IF EXISTS `e_exam_target`;
CREATE TABLE `e_exam_target` (
  `Target_ID` int(11) NOT NULL COMMENT '考试对象ID',
  `Exam_ID` int(11) DEFAULT NULL COMMENT '考试ID',
  `Class_ID` int(11) NOT NULL COMMENT '班级/课程ID',
  `User_ID` int(11) NOT NULL COMMENT '用户ID',
  `Target_Ctime` datetime DEFAULT NULL COMMENT '创建时间',
  `Target_Utime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`Target_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of e_exam_target
-- ----------------------------

-- ----------------------------
-- Table structure for `e_question`
-- ----------------------------
DROP TABLE IF EXISTS `e_question`;
CREATE TABLE `e_question` (
  `Ques_ID` int(11) NOT NULL COMMENT '试题ID',
  `Ques_Content` varchar(100) DEFAULT NULL COMMENT '试题内容',
  `Ques_Type` int(2) NOT NULL COMMENT '试题类型1：单选题；2：多选题；3：判断题；4：简答题；',
  `Ques_State` char(2) NOT NULL COMMENT '试题状态',
  `User_ID` int(11) NOT NULL COMMENT '创建人ID',
  `Ques_PID` int(11) NOT NULL COMMENT '父试题项ID',
  `Ques_Ctime` datetime DEFAULT NULL COMMENT '创建时间',
  `Ques_Utime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`Ques_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of e_question
-- ----------------------------
INSERT INTO `e_question` VALUES ('1', '请问世界上最长的河流是哪条河', '1', '1', '1', '0', null, null);
INSERT INTO `e_question` VALUES ('2', '世界上最高的山峰', '1', '1', '1', '0', null, null);
INSERT INTO `e_question` VALUES ('3', '以下哪些是NBA现役球员', '2', '1', '1', '0', null, null);
INSERT INTO `e_question` VALUES ('4', '姚明是休斯顿火箭队退役的吗？', '3', '1', '1', '0', null, null);
INSERT INTO `e_question` VALUES ('5', '请简要描述下NBA季后赛的出线规则', '4', '1', '1', '0', null, null);

-- ----------------------------
-- Table structure for `e_question_option`
-- ----------------------------
DROP TABLE IF EXISTS `e_question_option`;
CREATE TABLE `e_question_option` (
  `Opt_ID` int(11) NOT NULL COMMENT '试题项ID',
  `Ques_ID` int(11) DEFAULT NULL COMMENT '试题ID',
  `Opt_Num` char(2) DEFAULT NULL COMMENT '试题项I编号',
  `Opt_Content` varchar(100) NOT NULL COMMENT '试题项内容',
  `Opt_Answer` varchar(200) DEFAULT NULL COMMENT '答案',
  `Opt_IsRight` char(2) DEFAULT NULL COMMENT '是否正确选项',
  `Opt_Order` int(2) DEFAULT NULL COMMENT '试题项顺序',
  `Opt_Ctime` datetime DEFAULT NULL COMMENT '创建时间',
  `Opt_Utime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`Opt_ID`),
  KEY `FK_Reference_20` (`Ques_ID`),
  CONSTRAINT `FK_Reference_20` FOREIGN KEY (`Ques_ID`) REFERENCES `e_question` (`Ques_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of e_question_option
-- ----------------------------
INSERT INTO `e_question_option` VALUES ('1', '1', '4', '尼罗河', null, '1', '1', null, null);
INSERT INTO `e_question_option` VALUES ('2', '1', '4', '亚马逊河', null, '0', '2', null, null);
INSERT INTO `e_question_option` VALUES ('3', '1', '4', '长江', null, '0', '3', null, null);
INSERT INTO `e_question_option` VALUES ('4', '1', '4', '黄河', null, '0', '4', null, null);

-- ----------------------------
-- Table structure for `m_message`
-- ----------------------------
DROP TABLE IF EXISTS `m_message`;
CREATE TABLE `m_message` (
  `Ms_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '消息ID',
  `Ms_Type` int(11) DEFAULT NULL COMMENT '消息类型',
  `Ms_Title` varchar(50) DEFAULT NULL COMMENT '主题',
  `Ms_Content` varchar(200) DEFAULT NULL COMMENT '内容',
  `Ms_Ctime` datetime DEFAULT NULL COMMENT '记录时间',
  PRIMARY KEY (`Ms_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of m_message
-- ----------------------------
INSERT INTO `m_message` VALUES ('1', '1', '恭喜您已升级为国泰安网络大学高级学员', '恭喜您已升级为国泰安网络大学高级学员', '2014-01-07 13:46:25');
INSERT INTO `m_message` VALUES ('2', '2', 'XXX课程上课通知', 'XXX课程上课通知', '2014-01-13 11:46:38');

-- ----------------------------
-- Table structure for `m_message_user`
-- ----------------------------
DROP TABLE IF EXISTS `m_message_user`;
CREATE TABLE `m_message_user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户_消息通知ID',
  `Ms_ID` int(11) DEFAULT NULL COMMENT '消息ID',
  `User_ID` int(11) DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_71` (`Ms_ID`),
  CONSTRAINT `FK_Reference_71` FOREIGN KEY (`Ms_ID`) REFERENCES `m_message` (`Ms_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of m_message_user
-- ----------------------------
INSERT INTO `m_message_user` VALUES ('1', '1', '1');
INSERT INTO `m_message_user` VALUES ('2', '2', '1');
INSERT INTO `m_message_user` VALUES ('3', '2', '2');

-- ----------------------------
-- Table structure for `m_my_collect`
-- ----------------------------
DROP TABLE IF EXISTS `m_my_collect`;
CREATE TABLE `m_my_collect` (
  `Collect_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `Ref_ID` int(50) DEFAULT NULL COMMENT '关联ID',
  `Collect_Type` int(11) DEFAULT NULL COMMENT '关联类型 1.岗位收藏 2.课程收藏',
  `User_ID` int(11) DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`Collect_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of m_my_collect
-- ----------------------------
INSERT INTO `m_my_collect` VALUES ('1', '2', '1', '1');
INSERT INTO `m_my_collect` VALUES ('2', '3', '2', '2');
INSERT INTO `m_my_collect` VALUES ('3', '1', '1', '1');
INSERT INTO `m_my_collect` VALUES ('4', '2', '2', '1');

-- ----------------------------
-- Table structure for `m_my_note`
-- ----------------------------
DROP TABLE IF EXISTS `m_my_note`;
CREATE TABLE `m_my_note` (
  `Note_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '笔记ID',
  `Sec_ID` int(32) DEFAULT NULL COMMENT '章节ID',
  `Cour_ID` int(50) DEFAULT NULL COMMENT '课程ID',
  `Note_Name` varchar(100) DEFAULT NULL COMMENT '笔记名称',
  `Note_SubmitTime` datetime DEFAULT NULL COMMENT '提交时间',
  `Note_Content` varchar(200) DEFAULT NULL COMMENT '笔记内容',
  `User_ID` int(11) DEFAULT NULL COMMENT '用户ID',
  `VideoTime` varchar(100) DEFAULT NULL COMMENT '视频时间',
  `Note_Ctime` datetime DEFAULT NULL COMMENT '创建时间',
  `Note_Utime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`Note_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=14 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of m_my_note
-- ----------------------------
INSERT INTO `m_my_note` VALUES ('8', '4', '6', null, '2014-01-14 17:57:03', '我的笔记2', '2', '00:49', '2014-01-14 17:57:03', '2014-01-14 17:57:03');
INSERT INTO `m_my_note` VALUES ('9', '4', '6', null, '2014-01-14 17:57:07', '我的笔记3', '2', '00:53', '2014-01-14 17:57:07', '2014-01-14 17:57:07');
INSERT INTO `m_my_note` VALUES ('10', '5', '6', null, '2014-01-14 17:57:19', '我的笔记7', '2', '01:05', '2014-01-14 17:57:19', '2014-01-14 17:57:19');
INSERT INTO `m_my_note` VALUES ('11', '5', '6', null, '2014-01-14 17:57:21', '我的笔记6', '2', '01:08', '2014-01-14 17:57:21', '2014-01-14 17:57:21');
INSERT INTO `m_my_note` VALUES ('12', '5', '6', null, '2014-01-14 17:58:51', '我的笔记9', '2', '02:00', '2014-01-14 17:58:51', '2014-01-14 17:58:51');
INSERT INTO `m_my_note` VALUES ('13', '5', '6', null, '2014-01-14 17:59:26', '笔记 笔记 笔记', '2', '01:51', '2014-01-14 17:59:26', '2014-01-14 17:59:26');

-- ----------------------------
-- Table structure for `m_my_task`
-- ----------------------------
DROP TABLE IF EXISTS `m_my_task`;
CREATE TABLE `m_my_task` (
  `Task_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '作业ID',
  `Cour_ID` int(11) DEFAULT NULL COMMENT '课程ID',
  `Sec_ID` int(11) DEFAULT NULL COMMENT '章节ID',
  `Task_Name` varchar(50) DEFAULT NULL COMMENT '作业说明',
  `Task_Content` varchar(500) DEFAULT NULL COMMENT '作业内容',
  `User_ID` int(11) NOT NULL COMMENT '用户ID',
  `Task_Evaluate` varchar(100) DEFAULT NULL COMMENT '评价',
  `Task_Score` int(11) DEFAULT NULL COMMENT '评分',
  `Task_SubmitTime` datetime DEFAULT NULL COMMENT '提交截止时间',
  `TaskType` int(11) NOT NULL COMMENT '1:练习；2：作业；3：考试',
  PRIMARY KEY (`Task_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of m_my_task
-- ----------------------------
INSERT INTO `m_my_task` VALUES ('1', '26', '6', '我的作业1', '我的作业', '2', '21', '32', '2014-01-06 19:49:47', '0');
INSERT INTO `m_my_task` VALUES ('2', '13', '6', '我的作业2', '我的作业', '2', '21', '21', '2014-01-15 09:30:00', '0');
INSERT INTO `m_my_task` VALUES ('3', '12', '0', '我的作业3', null, '2', '21', '312', '2014-01-11 13:00:00', '0');

-- ----------------------------
-- Table structure for `m_my_taskcontent`
-- ----------------------------
DROP TABLE IF EXISTS `m_my_taskcontent`;
CREATE TABLE `m_my_taskcontent` (
  `TaskContentID` int(11) NOT NULL COMMENT '作业题目ID',
  `Task_ID` int(11) DEFAULT NULL COMMENT '作业ID',
  `TaskContent_Name` varchar(100) DEFAULT NULL COMMENT '作业题目名称',
  `TaskContent_SubmitTime` datetime DEFAULT NULL COMMENT '提交时间',
  `TaskContent_LimitTime` varchar(20) DEFAULT NULL COMMENT '限定时间',
  `TaskContent_Type` smallint(6) DEFAULT NULL COMMENT '状态',
  `TaskContent_Answer` varchar(100) DEFAULT NULL COMMENT '答案',
  PRIMARY KEY (`TaskContentID`),
  KEY `FK_Reference_58` (`Task_ID`),
  CONSTRAINT `FK_Reference_58` FOREIGN KEY (`Task_ID`) REFERENCES `m_my_task` (`Task_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of m_my_taskcontent
-- ----------------------------

-- ----------------------------
-- Table structure for `o_sale_activity`
-- ----------------------------
DROP TABLE IF EXISTS `o_sale_activity`;
CREATE TABLE `o_sale_activity` (
  `ACTIVITY_ID` varchar(32) NOT NULL COMMENT '活动ID',
  `ORDER_NUM` varchar(50) DEFAULT NULL COMMENT '订单号',
  `ACTIVITY_TYPE` char(1) NOT NULL COMMENT '活动类型:折扣、减价、优惠券等',
  `ACTIVITY_PRICE` varchar(32) NOT NULL COMMENT '优惠金额',
  PRIMARY KEY (`ACTIVITY_ID`),
  KEY `FK_Reference_32` (`ORDER_NUM`),
  CONSTRAINT `FK_Reference_32` FOREIGN KEY (`ORDER_NUM`) REFERENCES `o_sale_order` (`ORDER_NUM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of o_sale_activity
-- ----------------------------

-- ----------------------------
-- Table structure for `o_sale_order`
-- ----------------------------
DROP TABLE IF EXISTS `o_sale_order`;
CREATE TABLE `o_sale_order` (
  `ORDER_NUM` varchar(50) NOT NULL COMMENT '订单号',
  `COURSE_ID` varchar(50) DEFAULT NULL COMMENT '课程ID',
  `SECTION_NUM` varchar(32) DEFAULT NULL COMMENT '章节编号',
  `ORDER_PRICE` varchar(32) NOT NULL COMMENT '订单价格',
  `DISCOUNT_PRICE` varchar(32) DEFAULT NULL COMMENT '折扣价格',
  `ORDER_STATE` varchar(2) NOT NULL COMMENT '订单状态',
  `ORDER_TIME` datetime DEFAULT NULL COMMENT '订单时间',
  `UPDATE_TIME` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`ORDER_NUM`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of o_sale_order
-- ----------------------------

-- ----------------------------
-- Table structure for `o_shop_car`
-- ----------------------------
DROP TABLE IF EXISTS `o_shop_car`;
CREATE TABLE `o_shop_car` (
  `ID` varchar(32) NOT NULL,
  `COURSE_ID` varchar(32) NOT NULL,
  `SECTION_NUM` varchar(32) DEFAULT NULL,
  `COURSE_NAME` varchar(20) NOT NULL,
  `COURSE_PRICE` char(2) NOT NULL,
  `COURSE_TAG` char(10) DEFAULT NULL,
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of o_shop_car
-- ----------------------------

-- ----------------------------
-- Table structure for `p_job`
-- ----------------------------
DROP TABLE IF EXISTS `p_job`;
CREATE TABLE `p_job` (
  `Job_ID` int(11) NOT NULL COMMENT '岗位ID',
  `Pro_ID` int(11) DEFAULT NULL COMMENT '职业ID',
  `Job_Name` varchar(50) NOT NULL COMMENT '岗位名称',
  `Job_Description` varchar(255) DEFAULT NULL COMMENT '岗位介绍',
  `Job_PID` int(11) DEFAULT NULL COMMENT '父岗位ID',
  `Job_CollectCount` int(11) NOT NULL DEFAULT '0' COMMENT '岗位收藏数',
  `Job_Detail` varchar(500) NOT NULL COMMENT '岗位详情',
  `Job_Develpping` varchar(500) DEFAULT NULL COMMENT '岗位发展方向',
  `Job_Duty` varchar(500) DEFAULT NULL COMMENT '岗位职责',
  `Job_Demand` varchar(500) DEFAULT NULL COMMENT '岗位需求',
  `Job_Level` int(11) DEFAULT NULL COMMENT '岗位级别',
  `Job_Wage` varchar(200) DEFAULT NULL COMMENT '薪资行情',
  `Job_Ctime` datetime DEFAULT NULL COMMENT '创建时间',
  `Job_Utime` datetime DEFAULT NULL COMMENT '修改时间',
  `Job_Image` varchar(200) DEFAULT NULL COMMENT '岗位图片',
  `Job_Recommend` int(11) DEFAULT NULL COMMENT '岗位推荐',
  PRIMARY KEY (`Job_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of p_job
-- ----------------------------
INSERT INTO `p_job` VALUES ('1', '1', '平面设计师', '好的平面设计师需要的是综合的素质，绘画以及会使用电脑软件只是最基础的技能。因此想成为一名优秀的平面设计师，一定要有不断学习的习惯，要有追求创新的原动力。内功还没练到家就追求经济利益和社会影响最多昙花一现，难以取得真正的成就。', '5', '2', '平面设计师是在二度空间的平面材质上，运用各种视觉元素的组合及编排来表现其设计理念 及形象的方式。\r\n	一般人认知的平面设计师是把文字、照片或图案等视觉元素加以适当的影像处理及版面安排，而表现在报纸\r\n	杂志、书籍、海报、传单、标志、VI等等纸质媒体上，也就是在纸质媒体上进行美术设计及版面编排。', '平面设计师是在二度空间的平面材质上，运用各种视觉元素的组合及编排来表现其设计理念 及形象的方式。\r\n一般人认知的平面设计师是把文字、照片或图案等视觉元素加以适当的影像处理及版面安排，而表现在报纸\r\n杂志、书籍、海报、传单、标志、VI等等纸质媒体上，也就是在纸质媒体上进行美术设计及版面编排。</p>\r\n<p>平面设计师是在二度空间的平面材质上，运用各种视觉元素的组合及编排来表现其设计理念 及形象的方式。\r\n一般人认知的平面设计师是把文字、照片或图案等视觉元素加以适当的影像处理及版面安排，而表现在报纸\r\n杂志、书籍、海报、传单、标志、VI等等纸质媒体上，也就是在纸质媒体上进行美术设计及版面编排。</p>\r\n<p>平面设计师是在二度空间的平面材质上，运用各种视觉元素的组合及编排来表现其设计理念 及形象的方式。\r\n一般人认知的平面设计师是把文字、照片或图案等视觉元素加以适当的影像处理及版面安排，而表现在报纸\r\n杂志、书籍、海报、传单、标志、VI等等纸质媒体上，也就是在纸质媒体上进行美术设计及版面编排。', '1、根据内容需要，进行平面媒体的版面编排；<br>2、完成平面宣传品的创意设计；<br>3、执行公司开发项目的VI设计发展和导入；<br>4、完成会展、活动的整体布局，灯光舞美、气氛模拟设计；<br>5、完成对照片、图片的后期处理', '1、根据内容需要，进行平面媒体的版面编排；<br>2、完成平面宣传品的创意设计；<br>3、执行公司开发项目的VI设计发展和导入；<br>4、完成会展、活动的整体布局，灯光舞美、气氛模拟设计；<br>5、完成对照片、图片的后期处理', '454', '1、根据内容需要，进行平面媒体的版面编排；<br>2、完成平面宣传品的创意设计；<br>3、执行公司开发项目的VI设计发展和导入；<br>4、完成会展、活动的整体布局，灯光舞美、气氛模拟设计；<br>5、完成对照片、图片的后期处理', null, null, 'images/course/carouselBtn.png', '1');
INSERT INTO `p_job` VALUES ('2', '1', '产品策划师', '产品策划', '5', '3', 'ddd', null, null, null, null, null, null, null, 'images/course/carouselBtn.png', '1');
INSERT INTO `p_job` VALUES ('3', '1', '测试工程师', 'test', '5', '3', 'test', null, null, null, null, null, null, null, 'images/course/carouselBtn.png', '1');
INSERT INTO `p_job` VALUES ('4', '1', '需求分析师', 'tt', '5', '23', 'dd', null, null, null, null, null, null, null, 'images/course/carouselBtn.png', null);
INSERT INTO `p_job` VALUES ('5', '1', '软件类', '软件类岗位', null, '0', '软件相关岗位群', null, null, null, null, null, null, null, 'images/course/carouselBtn.png', null);
INSERT INTO `p_job` VALUES ('6', '1', '硬件类', '硬件类岗位', null, '0', '硬件相关岗位群', null, null, null, null, null, null, null, 'images/course/carouselBtn.png', null);
INSERT INTO `p_job` VALUES ('7', '1', '系统运维工程师', '系统运维工程', '6', '0', '系统运维工程师', null, null, null, null, null, null, null, null, null);
INSERT INTO `p_job` VALUES ('8', '2', '银行岗位群', '银行岗位群', null, '0', '银行岗位群', null, null, null, null, null, null, null, null, null);
INSERT INTO `p_job` VALUES ('9', '2', '证券岗位群', '证券岗位群', null, '0', '证券岗位群', null, null, null, null, null, null, null, null, null);
INSERT INTO `p_job` VALUES ('10', '2', '保险岗位群', '保险岗位群', null, '0', '保险岗位群', null, null, null, null, null, null, null, null, null);
INSERT INTO `p_job` VALUES ('11', '2', '银行柜员\r\n银行柜员', '站柜台滴', '8', '0', '站柜台滴', null, null, null, null, null, null, null, null, null);
INSERT INTO `p_job` VALUES ('12', '2', '大堂经理', '大堂经理', '8', '0', '大堂经理', null, null, null, null, null, null, null, null, null);
INSERT INTO `p_job` VALUES ('13', '2', '证券研究员', '证券研究员', '9', '0', '证券研究员', null, null, null, null, null, null, null, null, null);

-- ----------------------------
-- Table structure for `p_praisedetail`
-- ----------------------------
DROP TABLE IF EXISTS `p_praisedetail`;
CREATE TABLE `p_praisedetail` (
  `prai_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '赞的明细ID',
  `Answ_ID` int(11) DEFAULT NULL COMMENT '回答ID',
  `User_ID` int(11) NOT NULL COMMENT '用户ID',
  `prai_Ctime` datetime DEFAULT NULL COMMENT '创建时间',
  `prai_Utime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`prai_ID`),
  KEY `FK_Reference_72` (`Answ_ID`),
  CONSTRAINT `FK_Reference_72` FOREIGN KEY (`Answ_ID`) REFERENCES `q_answer` (`Answ_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of p_praisedetail
-- ----------------------------
INSERT INTO `p_praisedetail` VALUES ('1', '5', '2', '2014-01-17 15:32:22', '2014-01-17 15:32:22');
INSERT INTO `p_praisedetail` VALUES ('2', '3', '2', '2014-01-17 15:32:25', '2014-01-17 15:32:25');
INSERT INTO `p_praisedetail` VALUES ('3', '2', '2', '2014-01-17 17:31:01', '2014-01-17 17:31:01');
INSERT INTO `p_praisedetail` VALUES ('4', '4', '2', '2014-01-17 17:31:03', '2014-01-17 17:31:03');

-- ----------------------------
-- Table structure for `p_profession`
-- ----------------------------
DROP TABLE IF EXISTS `p_profession`;
CREATE TABLE `p_profession` (
  `Pro_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '职业ID',
  `Pro_Name` varchar(50) NOT NULL COMMENT '职业名称',
  `Rro_Description` varchar(255) NOT NULL COMMENT '职业描述',
  `Pro_Ctime` datetime NOT NULL COMMENT '创建时间',
  `Pro_Utime` datetime DEFAULT NULL COMMENT '修改时间',
  `Pro_Recommend` int(11) DEFAULT NULL COMMENT '是否推荐',
  PRIMARY KEY (`Pro_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of p_profession
-- ----------------------------
INSERT INTO `p_profession` VALUES ('1', 'IT', 'IT信息行业', '2014-01-08 10:58:56', null, '1');
INSERT INTO `p_profession` VALUES ('2', '金融', '各种金融行业', '2014-01-08 10:59:30', null, null);
INSERT INTO `p_profession` VALUES ('3', '物流行业', '物流行业', '2014-01-09 15:08:29', '2014-01-09 15:08:32', null);
INSERT INTO `p_profession` VALUES ('4', '创就业', '创就业', '2014-01-09 15:41:15', '2014-01-09 15:41:18', '1');

-- ----------------------------
-- Table structure for `q_answer`
-- ----------------------------
DROP TABLE IF EXISTS `q_answer`;
CREATE TABLE `q_answer` (
  `Answ_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '回答ID',
  `Ques_ID` int(11) DEFAULT NULL COMMENT '问题ID',
  `Answ_Content` varchar(2000) DEFAULT NULL COMMENT '回答内容',
  `User_ID` int(11) DEFAULT NULL COMMENT '回复用户ID',
  `Answ_PraiseCount` int(11) DEFAULT NULL COMMENT '赞的次数',
  `Answ_LookLimit` int(11) DEFAULT NULL COMMENT '浏览权限',
  `Answ_Ctime` datetime NOT NULL COMMENT '创建时间',
  `Answ_Utime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`Answ_ID`),
  KEY `FK_Reference_66` (`Ques_ID`),
  CONSTRAINT `FK_Reference_66` FOREIGN KEY (`Ques_ID`) REFERENCES `q_question` (`QuesID`)
) ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of q_answer
-- ----------------------------
INSERT INTO `q_answer` VALUES ('1', '9', '非常好非常好非常好非常好非常好非常好非常好非常好非常好', '1', '201', null, '2014-01-14 17:34:14', '2014-01-14 17:34:16');
INSERT INTO `q_answer` VALUES ('2', '2', '哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈哈', '1', '343', null, '2014-01-15 15:35:01', '2014-01-15 15:35:04');
INSERT INTO `q_answer` VALUES ('3', '1', '哦哦哦哦哦哦哦哦哦哦哦哦哦哦哦', '2', '34266', null, '2014-01-15 15:36:28', '2014-01-15 15:36:31');
INSERT INTO `q_answer` VALUES ('4', '3', '额额额额额额额额额额额额额额额额额额额额额额额额额额额额额额额额额额额额额额额额额额额额额额额额额额额额额额额额额', '3', '44', null, '2014-01-15 15:45:10', '2014-01-15 15:45:12');
INSERT INTO `q_answer` VALUES ('5', '9', '大学大学大学大学大学大学大学大学大学大学大学大学大学大学大学', '4', '5485', null, '2014-01-15 15:45:40', '2014-01-15 15:45:42');
INSERT INTO `q_answer` VALUES ('6', '1', '网络网络网络网络网络网络网络', '5', '55', null, '2014-01-15 15:46:13', '2014-01-15 15:46:15');
INSERT INTO `q_answer` VALUES ('7', '1', 'fdsdfdsfds', '6', '43', null, '2014-01-15 15:46:33', '2014-01-15 15:46:46');

-- ----------------------------
-- Table structure for `q_attention`
-- ----------------------------
DROP TABLE IF EXISTS `q_attention`;
CREATE TABLE `q_attention` (
  `ID` int(11) NOT NULL COMMENT '主键ID',
  `Ques_ID` int(11) DEFAULT NULL COMMENT '问题ID',
  `User_ID` int(11) DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of q_attention
-- ----------------------------

-- ----------------------------
-- Table structure for `q_question`
-- ----------------------------
DROP TABLE IF EXISTS `q_question`;
CREATE TABLE `q_question` (
  `QuesID` int(11) NOT NULL AUTO_INCREMENT COMMENT '问题ID',
  `Course_ID` int(50) DEFAULT NULL COMMENT '课程ID',
  `Sec_ID` int(11) DEFAULT NULL COMMENT '章节ID',
  `UserID` int(11) DEFAULT NULL COMMENT '用户ID',
  `Job_ID` int(11) DEFAULT NULL COMMENT '岗位ID(岗位群ID)',
  `Job_GroupID` int(11) DEFAULT NULL COMMENT '岗位群ID//弃用.不要进行任何操作',
  `Ques_Content` varchar(200) DEFAULT NULL COMMENT '提问内容',
  `Ques_Description` varchar(500) DEFAULT NULL COMMENT '问题说明',
  `Ques_Status` smallint(6) DEFAULT NULL COMMENT '回答状态',
  `Ques_Type` int(11) DEFAULT NULL COMMENT '问题类型',
  `Ques_AnswerCount` int(11) DEFAULT NULL COMMENT '回答次数',
  `Ques_AttentionCount` int(11) DEFAULT NULL COMMENT '关注人数',
  `Ques_Label` varchar(50) DEFAULT NULL COMMENT '问题标签',
  `Ques_AnswerPerson` varchar(50) DEFAULT NULL COMMENT '指定答疑人',
  `Ques_LookLimit` int(11) unsigned DEFAULT '0' COMMENT '浏览权限',
  `Ques_AnswerLimit` int(11) unsigned DEFAULT '0' COMMENT '回答权限',
  `Ques_Price` float DEFAULT NULL COMMENT '价格设定',
  `Ques_IsChoiceness` smallint(2) unsigned DEFAULT '0' COMMENT '是否精选 0.普通 1.精选',
  `Ques_Ctime` datetime NOT NULL COMMENT '创建时间',
  `Ques_Utime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`QuesID`),
  KEY `FK_Reference_61` (`UserID`),
  CONSTRAINT `FK_Reference_61` FOREIGN KEY (`UserID`) REFERENCES `u_user` (`User_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=71 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of q_question
-- ----------------------------
INSERT INTO `q_question` VALUES ('1', '1', null, '1', '5', null, '女子被闪电击中瞬间(图)', null, '1', '1', '300', '23', '测试', null, null, null, null, '1', '2014-01-09 14:50:28', '2014-01-09 14:50:31');
INSERT INTO `q_question` VALUES ('2', '6', '4', '2', '5', null, '罗德曼送金正恩名酒(图)', null, '0', '0', '0', '0', null, null, null, null, '0', '1', '2014-01-14 18:51:22', '2014-01-14 18:51:22');
INSERT INTO `q_question` VALUES ('3', '6', '4', '2', '5', null, '北京36岁副局级官员住房50多平:已不奢望分房', null, '0', '0', '0', '0', null, null, null, null, '0', '1', '2014-01-14 18:51:46', '2014-01-14 18:51:46');
INSERT INTO `q_question` VALUES ('4', '1', '1', '1', '5', null, '法媒:中国今年或选某大老虎作为反腐突破口', 'eeeeeee', '0', '1', '20', '21', '2', 'bbb', '1', '1', '20', null, '2014-01-14 18:54:49', '2014-01-14 18:54:49');
INSERT INTO `q_question` VALUES ('5', '1', '1', '1', '6', null, '春节四大晚会3台确定停办 央视春晚成独苗', 'eeeeeee', '0', '1', '20', '21', '2', 'bbb', '1', '1', '20', null, '2014-01-15 09:43:29', '2014-01-15 09:43:29');
INSERT INTO `q_question` VALUES ('6', '1', '1', '1', '6', null, '马云所创江南会金庸所创云松书舍等会所关停', 'eeeeeee', '0', '1', '20', '21', '2', 'bbb', '1', '1', '20', null, '2014-01-15 09:44:16', '2014-01-15 09:44:16');
INSERT INTO `q_question` VALUES ('7', '1', '1', '1', '6', null, '四川政协主席李崇禧被免职 官商勾结\"生意经\"', 'eeeeeee', '0', '1', '20', '21', '2', 'bbb', '1', '1', '20', null, '2014-01-15 09:58:27', '2014-01-15 09:58:27');
INSERT INTO `q_question` VALUES ('8', '1', '1', '1', '6', null, '北京石景山大火起因：值班员摁掉火警打游戏', 'eeeeeee', '0', '1', '20', '21', '2', 'bbb', '1', '1', '20', null, '2014-01-15 10:04:18', '2014-01-15 10:04:18');
INSERT INTO `q_question` VALUES ('9', '2', '1', '1', '5', null, '林志玲回应广告影响身心健康：小学生别看了', 'eeeeeee', '0', '1', '20', '21', '2', 'bbb', '1', '1', '20', null, '2014-01-15 10:04:19', '2014-01-15 10:04:19');
INSERT INTO `q_question` VALUES ('70', '6', '4', '2', '5', null, 'gggggg', null, '0', '0', '0', '0', null, null, '0', '0', '0', '0', '2014-01-17 15:33:00', '2014-01-17 15:33:00');

-- ----------------------------
-- Table structure for `q_question_user`
-- ----------------------------
DROP TABLE IF EXISTS `q_question_user`;
CREATE TABLE `q_question_user` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `Ques_ID` int(11) DEFAULT NULL COMMENT '问题ID',
  `Teacher_ID` int(11) DEFAULT NULL COMMENT '邀请回答老师的ID',
  `Status` int(11) DEFAULT '0' COMMENT '状态:0-未回答，1-已回答',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of q_question_user
-- ----------------------------

-- ----------------------------
-- Table structure for `re_course_job`
-- ----------------------------
DROP TABLE IF EXISTS `re_course_job`;
CREATE TABLE `re_course_job` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `Course_ID` int(11) DEFAULT NULL COMMENT '课程ID',
  `Pro_ID` int(11) DEFAULT NULL COMMENT '行业ID',
  `Job_ID` int(11) DEFAULT NULL COMMENT '岗位ID',
  `Cour_job_Recommend` int(11) DEFAULT NULL COMMENT '岗位推荐课程',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=28 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of re_course_job
-- ----------------------------
INSERT INTO `re_course_job` VALUES ('1', '6', null, '1', '1');
INSERT INTO `re_course_job` VALUES ('2', '4', null, '1', '3');
INSERT INTO `re_course_job` VALUES ('3', '5', null, '1', '2');
INSERT INTO `re_course_job` VALUES ('4', '7', null, '1', '4');
INSERT INTO `re_course_job` VALUES ('5', '8', null, '1', null);
INSERT INTO `re_course_job` VALUES ('6', '10', null, '2', null);
INSERT INTO `re_course_job` VALUES ('7', '1', null, '2', '1');
INSERT INTO `re_course_job` VALUES ('8', '3', null, '2', null);
INSERT INTO `re_course_job` VALUES ('9', '2', null, '3', null);
INSERT INTO `re_course_job` VALUES ('10', '13', null, '3', null);
INSERT INTO `re_course_job` VALUES ('11', '14', null, '3', null);
INSERT INTO `re_course_job` VALUES ('12', '9', null, '2', null);
INSERT INTO `re_course_job` VALUES ('13', '11', null, '2', '1');
INSERT INTO `re_course_job` VALUES ('14', '12', null, '2', '1');
INSERT INTO `re_course_job` VALUES ('15', '15', null, '4', '1');
INSERT INTO `re_course_job` VALUES ('16', '6', '1', '2', null);
INSERT INTO `re_course_job` VALUES ('17', '6', '0', '0', '0');
INSERT INTO `re_course_job` VALUES ('18', '6', '0', '4', '0');
INSERT INTO `re_course_job` VALUES ('19', '32', '0', '2', '0');
INSERT INTO `re_course_job` VALUES ('20', '32', '0', '3', '0');
INSERT INTO `re_course_job` VALUES ('21', '33', '0', '2', '0');
INSERT INTO `re_course_job` VALUES ('22', '33', '0', '3', '0');
INSERT INTO `re_course_job` VALUES ('23', '34', '0', '1', '0');
INSERT INTO `re_course_job` VALUES ('24', '35', '0', '2', '0');
INSERT INTO `re_course_job` VALUES ('25', '37', '0', '1', '0');
INSERT INTO `re_course_job` VALUES ('26', '37', '0', '2', '0');
INSERT INTO `re_course_job` VALUES ('27', '37', '0', '3', '0');

-- ----------------------------
-- Table structure for `re_course_question`
-- ----------------------------
DROP TABLE IF EXISTS `re_course_question`;
CREATE TABLE `re_course_question` (
  `CouQu_ID` varchar(32) NOT NULL COMMENT '主键',
  `Section_Num` varchar(32) DEFAULT NULL COMMENT '章节编号',
  `Course_ID` varchar(50) DEFAULT NULL COMMENT '课程ID',
  `Question_ID` varchar(32) DEFAULT NULL COMMENT '试题ID',
  `CouQu_Ctime` datetime DEFAULT NULL COMMENT '创建时间',
  `CouQu_Utime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`CouQu_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of re_course_question
-- ----------------------------

-- ----------------------------
-- Table structure for `re_course_resource`
-- ----------------------------
DROP TABLE IF EXISTS `re_course_resource`;
CREATE TABLE `re_course_resource` (
  `CourRe_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `ReSource_ID` int(11) DEFAULT NULL COMMENT '资源ID',
  `Course_ID` int(11) DEFAULT NULL COMMENT '课程ID',
  `Section_ID` int(11) DEFAULT NULL COMMENT '章节ID',
  `CourRe_Ctime` datetime DEFAULT NULL COMMENT '创建时间',
  `CourRe_Utime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`CourRe_ID`),
  KEY `FK_Reference_42` (`ReSource_ID`),
  KEY `FK_Reference_43` (`Course_ID`),
  CONSTRAINT `FK_Reference_42` FOREIGN KEY (`ReSource_ID`) REFERENCES `r_resource` (`ReSour_ID`),
  CONSTRAINT `FK_Reference_43` FOREIGN KEY (`Course_ID`) REFERENCES `c_course` (`Cour_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=181 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of re_course_resource
-- ----------------------------
INSERT INTO `re_course_resource` VALUES ('123', '79', '3', '3', null, null);
INSERT INTO `re_course_resource` VALUES ('124', '79', '6', '5', null, null);
INSERT INTO `re_course_resource` VALUES ('125', '79', '7', '0', null, null);
INSERT INTO `re_course_resource` VALUES ('126', '80', '26', '34', null, null);
INSERT INTO `re_course_resource` VALUES ('127', '81', '26', '34', null, null);
INSERT INTO `re_course_resource` VALUES ('128', '82', '26', '38', null, null);
INSERT INTO `re_course_resource` VALUES ('129', '83', '26', '38', null, null);
INSERT INTO `re_course_resource` VALUES ('130', '84', '26', '31', null, null);
INSERT INTO `re_course_resource` VALUES ('131', '85', '26', '31', null, null);
INSERT INTO `re_course_resource` VALUES ('132', '86', '26', '37', null, null);
INSERT INTO `re_course_resource` VALUES ('133', '87', '26', '37', null, null);
INSERT INTO `re_course_resource` VALUES ('134', '88', '26', '31', null, null);
INSERT INTO `re_course_resource` VALUES ('135', '89', '26', '38', null, null);
INSERT INTO `re_course_resource` VALUES ('136', '90', '26', '37', null, null);
INSERT INTO `re_course_resource` VALUES ('137', '91', '26', '38', null, null);
INSERT INTO `re_course_resource` VALUES ('138', '92', '26', '38', null, null);
INSERT INTO `re_course_resource` VALUES ('139', '93', '26', '38', null, null);
INSERT INTO `re_course_resource` VALUES ('140', '94', '26', '36', null, null);
INSERT INTO `re_course_resource` VALUES ('141', '95', '26', '36', null, null);
INSERT INTO `re_course_resource` VALUES ('142', '96', '26', '31', null, null);
INSERT INTO `re_course_resource` VALUES ('146', '100', '26', '31', null, null);
INSERT INTO `re_course_resource` VALUES ('149', '103', '26', '36', null, null);
INSERT INTO `re_course_resource` VALUES ('150', '104', '26', '36', null, null);
INSERT INTO `re_course_resource` VALUES ('152', '106', '26', '31', null, null);
INSERT INTO `re_course_resource` VALUES ('159', '119', '32', '0', null, null);
INSERT INTO `re_course_resource` VALUES ('160', '120', '32', '0', null, null);
INSERT INTO `re_course_resource` VALUES ('161', '121', '33', '0', null, null);
INSERT INTO `re_course_resource` VALUES ('162', '122', '33', '0', null, null);
INSERT INTO `re_course_resource` VALUES ('163', '123', '34', '0', null, null);
INSERT INTO `re_course_resource` VALUES ('164', '124', '34', '0', null, null);
INSERT INTO `re_course_resource` VALUES ('165', '125', '35', '0', null, null);
INSERT INTO `re_course_resource` VALUES ('166', '126', '35', '0', null, null);
INSERT INTO `re_course_resource` VALUES ('169', '129', '35', '55', null, null);
INSERT INTO `re_course_resource` VALUES ('171', '131', '35', '55', null, null);
INSERT INTO `re_course_resource` VALUES ('172', '132', '37', '0', null, null);
INSERT INTO `re_course_resource` VALUES ('173', '133', '37', '0', null, null);
INSERT INTO `re_course_resource` VALUES ('174', '134', '37', '57', null, null);
INSERT INTO `re_course_resource` VALUES ('175', '135', '37', '57', null, null);
INSERT INTO `re_course_resource` VALUES ('176', '136', '37', '59', null, null);
INSERT INTO `re_course_resource` VALUES ('177', '137', '37', '59', null, null);
INSERT INTO `re_course_resource` VALUES ('178', '138', '37', '59', null, null);
INSERT INTO `re_course_resource` VALUES ('179', '139', '37', '60', null, null);
INSERT INTO `re_course_resource` VALUES ('180', '140', '37', '60', null, null);

-- ----------------------------
-- Table structure for `re_course_school`
-- ----------------------------
DROP TABLE IF EXISTS `re_course_school`;
CREATE TABLE `re_course_school` (
  `SCHOOL_ID` varchar(50) NOT NULL COMMENT '学校ID',
  `COLLEGE_ID` varchar(50) DEFAULT NULL COMMENT '院系ID',
  `VOCATIONAL_ID` varchar(50) DEFAULT NULL COMMENT '专业ID'
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of re_course_school
-- ----------------------------
INSERT INTO `re_course_school` VALUES ('1', '1', '1');
INSERT INTO `re_course_school` VALUES ('2', '2', '2');
INSERT INTO `re_course_school` VALUES ('3', '3', '3');
INSERT INTO `re_course_school` VALUES ('4', '4', '4');

-- ----------------------------
-- Table structure for `re_job_authentication`
-- ----------------------------
DROP TABLE IF EXISTS `re_job_authentication`;
CREATE TABLE `re_job_authentication` (
  `JobAuth_ID` int(32) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `Job_ID` varchar(32) DEFAULT NULL COMMENT '岗位ID',
  `Auth_ID` varchar(32) DEFAULT NULL COMMENT '认证ID',
  `JobAuth_Ctime` datetime DEFAULT NULL COMMENT '创建时间',
  `JobAuth_Utime` datetime DEFAULT NULL COMMENT '修改时间',
  `JobAuth_Recommend` int(11) DEFAULT NULL COMMENT '岗位推荐认证',
  PRIMARY KEY (`JobAuth_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of re_job_authentication
-- ----------------------------
INSERT INTO `re_job_authentication` VALUES ('1', '1', '1', '2014-01-09 20:00:24', '2014-01-09 20:00:26', '3');
INSERT INTO `re_job_authentication` VALUES ('2', '1', '2', null, null, '2');
INSERT INTO `re_job_authentication` VALUES ('3', '1', '3', null, null, '1');

-- ----------------------------
-- Table structure for `r_resource`
-- ----------------------------
DROP TABLE IF EXISTS `r_resource`;
CREATE TABLE `r_resource` (
  `ReSour_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '资源ID',
  `Data_Url` varchar(255) NOT NULL COMMENT '资料URL',
  `Data_Isdownload` char(1) NOT NULL COMMENT '下载标识',
  `Source_Type` char(20) NOT NULL COMMENT '资源类型',
  `ReSour_Ctime` datetime DEFAULT NULL COMMENT '创建时间',
  `ReSour_Utime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`ReSour_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=141 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of r_resource
-- ----------------------------
INSERT INTO `r_resource` VALUES ('79', 'www.baidu.com', '1', '1', null, null);
INSERT INTO `r_resource` VALUES ('80', '/res/video/course/26/20140118085419241.flv', '1', '1', '2014-01-18 08:54:19', '2014-01-18 08:54:19');
INSERT INTO `r_resource` VALUES ('81', '/res/aid/course/26/20140118085419391.flv', '2', '2', '2014-01-18 08:54:19', '2014-01-18 08:54:19');
INSERT INTO `r_resource` VALUES ('82', '/res/video/course/26/20140118085646473.flv', '1', '1', '2014-01-18 08:56:46', '2014-01-18 08:56:46');
INSERT INTO `r_resource` VALUES ('83', '/res/aid/course/26/20140118085646556.flv', '2', '2', '2014-01-18 08:56:46', '2014-01-18 08:56:46');
INSERT INTO `r_resource` VALUES ('84', '/res/video/course/26/20140118090315980.jpg', '1', '1', '2014-01-18 09:03:16', '2014-01-18 09:03:16');
INSERT INTO `r_resource` VALUES ('85', '/res/aid/course/26/20140118090316098.flv', '2', '2', '2014-01-18 09:03:16', '2014-01-18 09:03:16');
INSERT INTO `r_resource` VALUES ('86', '/res/video/course/26/20140118090434422.flv', '1', '1', '2014-01-18 09:04:34', '2014-01-18 09:04:34');
INSERT INTO `r_resource` VALUES ('87', '/res/aid/course/26/20140118090434495.jpg', '2', '2', '2014-01-18 09:04:34', '2014-01-18 09:04:34');
INSERT INTO `r_resource` VALUES ('88', '/res/aid/course/26/20140118090652679.flv', '2', '2', '2014-01-18 09:06:52', '2014-01-18 09:06:52');
INSERT INTO `r_resource` VALUES ('89', '/res/aid/course/26/20140118090820165.flv', '2', '2', '2014-01-18 09:08:20', '2014-01-18 09:08:20');
INSERT INTO `r_resource` VALUES ('90', '/res/aid/course/26/20140118090856687.flv', '2', '2', '2014-01-18 09:08:56', '2014-01-18 09:08:56');
INSERT INTO `r_resource` VALUES ('91', '/res/video/course/26/20140118090940640.flv', '1', '1', '2014-01-18 09:09:40', '2014-01-18 09:09:40');
INSERT INTO `r_resource` VALUES ('92', '/res/aid/course/26/20140118090940766.flv', '2', '2', '2014-01-18 09:09:40', '2014-01-18 09:09:40');
INSERT INTO `r_resource` VALUES ('93', '/res/aid/course/26/20140118090946139.flv', '2', '2', '2014-01-18 09:09:46', '2014-01-18 09:09:46');
INSERT INTO `r_resource` VALUES ('94', '/res/video/course/26/20140118101353723.jpg', '1', '1', null, null);
INSERT INTO `r_resource` VALUES ('95', '/res/aid/course/26/20140118101405941.flv', '2', '2', null, null);
INSERT INTO `r_resource` VALUES ('96', '/res/aid/course/26/20140118101518304.jpg', '2', '2', null, null);
INSERT INTO `r_resource` VALUES ('100', '/res/video/course/26/20140118102216432.flv', '1', '1', null, null);
INSERT INTO `r_resource` VALUES ('103', '/res/aid/course/26/20140118102845085.flv', '2', '2', null, null);
INSERT INTO `r_resource` VALUES ('104', '/res/aid/course/26/20140118102855069.flv', '2', '2', null, null);
INSERT INTO `r_resource` VALUES ('106', '/res/aid/course/26/20140118102959107.flv', '2', '2', null, null);
INSERT INTO `r_resource` VALUES ('113', '/res/img/course/20140118103653779.jpg', '1', '1', null, null);
INSERT INTO `r_resource` VALUES ('114', '/res/video/course/20140118103653780.flv', '1', '2', null, null);
INSERT INTO `r_resource` VALUES ('115', '/res/img/course/20140118104223745.jpg', '1', '1', null, null);
INSERT INTO `r_resource` VALUES ('116', '/res/video/course/20140118104224193.flv', '1', '2', null, null);
INSERT INTO `r_resource` VALUES ('117', '/res/img/course/20140118104923636.jpg', '1', '1', null, null);
INSERT INTO `r_resource` VALUES ('118', '/res/video/course/20140118104923826.flv', '1', '2', null, null);
INSERT INTO `r_resource` VALUES ('119', '/res/img/course/20140118105540992.jpg', '1', '1', null, null);
INSERT INTO `r_resource` VALUES ('120', '/res/video/course/20140118105541138.flv', '1', '2', null, null);
INSERT INTO `r_resource` VALUES ('121', '/res/img/course/20140118111259271.jpg', '1', '1', null, null);
INSERT INTO `r_resource` VALUES ('122', '/res/video/course/20140118111259273.flv', '1', '2', null, null);
INSERT INTO `r_resource` VALUES ('123', '/res/img/course/20140118112208073.jpg', '1', '1', null, null);
INSERT INTO `r_resource` VALUES ('124', '/res/video/course/20140118112208078.flv', '1', '2', null, null);
INSERT INTO `r_resource` VALUES ('125', '/res/img/course/20140118112753693.jpg', '1', '1', null, null);
INSERT INTO `r_resource` VALUES ('126', '/res/video/course/20140118112753695.flv', '1', '2', null, null);
INSERT INTO `r_resource` VALUES ('129', '/res/video/course/35/20140118112833658.jpg', '1', '1', null, null);
INSERT INTO `r_resource` VALUES ('131', '/res/aid/course/35/20140118112842696.flv', '2', '2', null, null);
INSERT INTO `r_resource` VALUES ('132', '/res/img/course/37/20140118142212732.jpg', '1', '1', null, null);
INSERT INTO `r_resource` VALUES ('133', '/res/video/course/37/20140118142213564.flv', '1', '2', null, null);
INSERT INTO `r_resource` VALUES ('134', '/res/video/course/37/20140118142438176.flv', '1', '1', null, null);
INSERT INTO `r_resource` VALUES ('135', '/res/aid/course/37/20140118142438235.flv', '2', '2', null, null);
INSERT INTO `r_resource` VALUES ('136', '/res/video/course/37/20140118142522017.flv', '1', '1', null, null);
INSERT INTO `r_resource` VALUES ('137', '/res/aid/course/37/20140118142522093.flv', '2', '2', null, null);
INSERT INTO `r_resource` VALUES ('138', '/res/aid/course/37/20140118142526179.jpg', '2', '2', null, null);
INSERT INTO `r_resource` VALUES ('139', '/res/video/course/37/20140118142543158.flv', '1', '1', null, null);
INSERT INTO `r_resource` VALUES ('140', '/res/aid/course/37/20140118142543228.flv', '2', '2', null, null);

-- ----------------------------
-- Table structure for `s_class`
-- ----------------------------
DROP TABLE IF EXISTS `s_class`;
CREATE TABLE `s_class` (
  `Cla_ID` varchar(32) NOT NULL COMMENT '班级ID',
  `Cla_Name` varchar(100) NOT NULL COMMENT '班级名称',
  `Voc_ID` varchar(32) DEFAULT NULL COMMENT '专业ID',
  PRIMARY KEY (`Cla_ID`),
  KEY `FK_Reference_35` (`Voc_ID`),
  CONSTRAINT `FK_Reference_35` FOREIGN KEY (`Voc_ID`) REFERENCES `s_vocational` (`Voc_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_class
-- ----------------------------
INSERT INTO `s_class` VALUES ('1', '计算机0409班', '1');
INSERT INTO `s_class` VALUES ('2', '计算机0410班', '1');

-- ----------------------------
-- Table structure for `s_faculty`
-- ----------------------------
DROP TABLE IF EXISTS `s_faculty`;
CREATE TABLE `s_faculty` (
  `Fac_ID` varchar(32) NOT NULL COMMENT '院系编号',
  `Fac_Name` varchar(200) NOT NULL COMMENT '院系名称',
  `Sch_ID` varchar(32) DEFAULT NULL COMMENT '学校ID',
  `Fac_Description` varchar(250) DEFAULT NULL COMMENT '院系简介',
  `Fac_Ctime` datetime NOT NULL COMMENT '生成时间',
  PRIMARY KEY (`Fac_ID`),
  KEY `FK_Reference_33` (`Sch_ID`),
  CONSTRAINT `FK_Reference_33` FOREIGN KEY (`Sch_ID`) REFERENCES `s_school` (`Sch_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_faculty
-- ----------------------------
INSERT INTO `s_faculty` VALUES ('1', '信息学院', '1', '信息科学与工程学院', '2014-01-13 19:54:38');
INSERT INTO `s_faculty` VALUES ('2', '文学院', '1', '文学院', '2014-01-13 19:54:58');

-- ----------------------------
-- Table structure for `s_school`
-- ----------------------------
DROP TABLE IF EXISTS `s_school`;
CREATE TABLE `s_school` (
  `Sch_ID` varchar(32) NOT NULL COMMENT '学校ID',
  `Sch_Name` varchar(100) NOT NULL COMMENT '学校名称',
  `Sch_Description` varchar(250) DEFAULT NULL COMMENT '学校简介',
  `Sch_Address` varchar(250) DEFAULT NULL COMMENT '学校地址',
  `Sch_Www` varchar(50) DEFAULT NULL COMMENT '学校网址',
  `Sch_Logo` varchar(100) DEFAULT NULL COMMENT '学校图片',
  `Sch_Ctime` datetime DEFAULT NULL COMMENT '生成时间',
  PRIMARY KEY (`Sch_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_school
-- ----------------------------
INSERT INTO `s_school` VALUES ('1', '国安泰网络大学', '国泰安网络大学', '深圳市南山区', 'www.gtadata.com', 'guotaian.png', '2014-01-07 15:08:31');
INSERT INTO `s_school` VALUES ('2', '湖南大学', '湖南大学', '湖南长沙', 'www.hunan.com', 'guotaian.png', '2014-01-15 17:23:31');
INSERT INTO `s_school` VALUES ('3', '北京大学', '北京大学', '北京朝阳区', 'www.beijin.com', 'guotaian.png', '2014-01-15 17:24:31');
INSERT INTO `s_school` VALUES ('4', '清华大学', '清华大学', '北京朝阳区', 'www.beijin.com', 'guotaian.png', '2014-01-15 17:24:31');

-- ----------------------------
-- Table structure for `s_vocational`
-- ----------------------------
DROP TABLE IF EXISTS `s_vocational`;
CREATE TABLE `s_vocational` (
  `Voc_ID` varchar(32) NOT NULL COMMENT '专业ID',
  `Voc_Name` varchar(50) NOT NULL COMMENT '专业名称',
  `Fac_ID` varchar(32) DEFAULT NULL COMMENT '学院编号',
  `Sch_ID` varchar(32) DEFAULT NULL COMMENT '学校ID',
  `Voc_Description` varchar(250) DEFAULT NULL COMMENT '专业简介',
  `Voc_Ctime` datetime NOT NULL COMMENT '生成时间',
  PRIMARY KEY (`Voc_ID`),
  KEY `FK_Reference_34` (`Fac_ID`),
  KEY `FK_Reference_36` (`Sch_ID`),
  CONSTRAINT `FK_Reference_34` FOREIGN KEY (`Fac_ID`) REFERENCES `s_faculty` (`Fac_ID`),
  CONSTRAINT `FK_Reference_36` FOREIGN KEY (`Sch_ID`) REFERENCES `s_school` (`Sch_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of s_vocational
-- ----------------------------
INSERT INTO `s_vocational` VALUES ('1', '计算机科学与技术专业', '1', '1', '计算机科学与技术专业出程序员屌丝', '2014-01-13 19:56:34');
INSERT INTO `s_vocational` VALUES ('2', '软件工程专业', '1', '1', '软件工程学软件研发流程与管理', '2014-01-13 19:57:28');

-- ----------------------------
-- Table structure for `train_type`
-- ----------------------------
DROP TABLE IF EXISTS `train_type`;
CREATE TABLE `train_type` (
  `TraType_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '培训分类ID',
  `TraType_Name` varchar(50) NOT NULL COMMENT '培训分类名称',
  `TraType_Ctime` datetime DEFAULT NULL COMMENT '创建时间',
  `TraType_Utime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`TraType_ID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of train_type
-- ----------------------------

-- ----------------------------
-- Table structure for `u_learn_detail`
-- ----------------------------
DROP TABLE IF EXISTS `u_learn_detail`;
CREATE TABLE `u_learn_detail` (
  `Learn_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '学生状态ID',
  `Course_ID` int(11) DEFAULT NULL COMMENT '课程ID',
  `Sec_ID` varchar(32) DEFAULT NULL COMMENT '章节编号',
  `User_ID` int(11) DEFAULT NULL COMMENT '用户ID',
  `Learn_state` int(11) NOT NULL COMMENT '学习状态',
  `Learn_Ctime` datetime DEFAULT NULL COMMENT '创建时间',
  `Learn_Utime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`Learn_ID`),
  KEY `FK_Reference_67` (`Course_ID`),
  CONSTRAINT `FK_Reference_67` FOREIGN KEY (`Course_ID`) REFERENCES `c_course` (`Cour_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of u_learn_detail
-- ----------------------------
INSERT INTO `u_learn_detail` VALUES ('1', '6', '4', '2', '0', '2014-01-09 18:54:34', '2014-01-09 18:54:36');
INSERT INTO `u_learn_detail` VALUES ('3', '6', '5', '2', '0', '2014-01-15 15:14:26', '2014-01-15 15:14:28');

-- ----------------------------
-- Table structure for `u_permission`
-- ----------------------------
DROP TABLE IF EXISTS `u_permission`;
CREATE TABLE `u_permission` (
  `PermissionID` int(11) NOT NULL COMMENT '用户ID',
  `PermissionName` varchar(20) NOT NULL COMMENT '权限名称',
  `PermissionNote` varchar(50) DEFAULT NULL COMMENT '权限注释',
  PRIMARY KEY (`PermissionID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of u_permission
-- ----------------------------

-- ----------------------------
-- Table structure for `u_role_permission`
-- ----------------------------
DROP TABLE IF EXISTS `u_role_permission`;
CREATE TABLE `u_role_permission` (
  `RolePermissionID` int(11) NOT NULL COMMENT '角色权限ID',
  `RoleID` int(11) NOT NULL COMMENT '角色ID',
  `PermissionID` int(11) NOT NULL COMMENT '权限ID',
  `RolePermissionNote` varchar(50) DEFAULT NULL COMMENT '角色权限注释',
  PRIMARY KEY (`RolePermissionID`),
  KEY `FK_Reference_55` (`RoleID`),
  KEY `FK_Reference_56` (`PermissionID`),
  CONSTRAINT `FK_Reference_55` FOREIGN KEY (`RoleID`) REFERENCES `u_userrole` (`RoleID`),
  CONSTRAINT `FK_Reference_56` FOREIGN KEY (`PermissionID`) REFERENCES `u_permission` (`PermissionID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of u_role_permission
-- ----------------------------

-- ----------------------------
-- Table structure for `u_role_user`
-- ----------------------------
DROP TABLE IF EXISTS `u_role_user`;
CREATE TABLE `u_role_user` (
  `UserRoleID` int(11) NOT NULL COMMENT '用户ID',
  `UserID` int(11) DEFAULT NULL COMMENT '用户ID',
  `RoleID` int(11) DEFAULT NULL COMMENT '用户ID',
  PRIMARY KEY (`UserRoleID`),
  KEY `FK_Reference_53` (`UserID`),
  KEY `FK_Reference_54` (`RoleID`),
  CONSTRAINT `FK_Reference_53` FOREIGN KEY (`UserID`) REFERENCES `u_user` (`User_ID`),
  CONSTRAINT `FK_Reference_54` FOREIGN KEY (`RoleID`) REFERENCES `u_userrole` (`RoleID`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of u_role_user
-- ----------------------------

-- ----------------------------
-- Table structure for `u_student`
-- ----------------------------
DROP TABLE IF EXISTS `u_student`;
CREATE TABLE `u_student` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT 'ID',
  `UserID` int(11) NOT NULL COMMENT '用户ID',
  `StudyGoal` varchar(100) DEFAULT NULL COMMENT '学习目标',
  `StudyCredit` float DEFAULT NULL COMMENT '用户学分',
  `Education` varchar(50) DEFAULT NULL COMMENT '教育程度',
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_74` (`UserID`),
  CONSTRAINT `FK_Reference_74` FOREIGN KEY (`UserID`) REFERENCES `u_user` (`User_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=22 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of u_student
-- ----------------------------
INSERT INTO `u_student` VALUES ('1', '1', '学会所有互联网开发语言', '89', '大专');
INSERT INTO `u_student` VALUES ('13', '44', null, '0', '4');
INSERT INTO `u_student` VALUES ('14', '45', null, '0', '1');
INSERT INTO `u_student` VALUES ('15', '46', null, '0', '1');
INSERT INTO `u_student` VALUES ('16', '47', null, '0', '3');
INSERT INTO `u_student` VALUES ('17', '48', null, '0', '1');
INSERT INTO `u_student` VALUES ('18', '49', null, '0', '1');
INSERT INTO `u_student` VALUES ('19', '50', null, '0', '2');
INSERT INTO `u_student` VALUES ('20', '51', null, '0', '1');
INSERT INTO `u_student` VALUES ('21', '52', null, '0', '1');

-- ----------------------------
-- Table structure for `u_teacher`
-- ----------------------------
DROP TABLE IF EXISTS `u_teacher`;
CREATE TABLE `u_teacher` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '老师ID',
  `User_ID` int(11) DEFAULT NULL COMMENT '用户ID',
  `School` varchar(50) DEFAULT NULL COMMENT '所属学校',
  `Job_GroupID` int(11) DEFAULT NULL COMMENT '老师对应的岗位群ID',
  `Job_ID` int(11) DEFAULT NULL COMMENT '老师对应的岗位ID',
  `Major` varchar(50) DEFAULT NULL COMMENT '专业',
  `Course` varchar(50) DEFAULT NULL COMMENT '课程',
  `TeacherLevel` varchar(100) DEFAULT NULL COMMENT '教师等级',
  `Introduce` varchar(200) DEFAULT NULL COMMENT '教师介绍',
  `IsRecommended` int(1) unsigned DEFAULT '0' COMMENT '是否推荐答疑',
  PRIMARY KEY (`ID`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of u_teacher
-- ----------------------------
INSERT INTO `u_teacher` VALUES ('1', '1', '深圳大学', '5', null, '计算机专业', null, '智能机器人研究博士', '学以思网络课堂联合创始人、CEO。20年职场经验，曾任职于两家国际上市公司，专注于帮助年青朋友更好成长', '1');
INSERT INTO `u_teacher` VALUES ('2', '2', '深圳大学', '5', null, '计算机专业', null, '生化武器研究博士', null, '1');
INSERT INTO `u_teacher` VALUES ('3', '3', '深圳大学', '5', null, '计算机专业', null, '生化武器研究博士', null, '1');
INSERT INTO `u_teacher` VALUES ('4', '4', '深圳大学', '5', null, '计算机专业', null, '生化武器研究博士', null, '1');
INSERT INTO `u_teacher` VALUES ('5', '5', '深圳大学', '5', null, '计算机专业', null, '生化武器研究博士', null, '1');
INSERT INTO `u_teacher` VALUES ('6', '6', '深圳大学', '5', null, '计算机专业', null, '生化武器研究博士', null, '1');
INSERT INTO `u_teacher` VALUES ('7', '7', '深圳大学', '5', null, '计算机专业', null, '智能机器人研究博士', null, '1');
INSERT INTO `u_teacher` VALUES ('8', '8', '深圳大学', '5', null, '计算机专业', null, '生化武器研究博士', null, '1');

-- ----------------------------
-- Table structure for `u_user`
-- ----------------------------
DROP TABLE IF EXISTS `u_user`;
CREATE TABLE `u_user` (
  `User_ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `RoleID` int(11) DEFAULT NULL COMMENT '角色ID',
  `User_Name` varchar(100) NOT NULL COMMENT '用户姓名',
  `User_Mobile` varchar(11) DEFAULT NULL COMMENT '用户手机号码',
  `User_Email` varchar(100) DEFAULT NULL COMMENT '用户邮箱',
  `User_Password` varchar(50) DEFAULT NULL COMMENT '用户密码',
  `User_Type` int(2) DEFAULT NULL COMMENT '用户类型：1.学生 2.教师 3.后台管理人员',
  `User_HeadPic` varchar(100) DEFAULT NULL COMMENT '用户头像',
  `User_LoginInfo` varchar(1000) DEFAULT NULL COMMENT '登录信息',
  `User_ResetPwdCode` varchar(100) DEFAULT NULL,
  PRIMARY KEY (`User_ID`),
  KEY `FK_Reference_64` (`RoleID`),
  CONSTRAINT `FK_Reference_64` FOREIGN KEY (`RoleID`) REFERENCES `u_userrole` (`RoleID`)
) ENGINE=InnoDB AUTO_INCREMENT=53 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of u_user
-- ----------------------------
INSERT INTO `u_user` VALUES ('1', null, '张进', null, '495190197.com', 'gg', '2', 'images/answerCenter/faq_head_tmp.jpg', null, null);
INSERT INTO `u_user` VALUES ('2', null, '黄碧云', null, 'gg.com', '654321', '2', 'images/answerCenter/faq_head_tmp.jpg', null, null);
INSERT INTO `u_user` VALUES ('3', null, '覃秉忠', null, 'gg.com', 'gg', '2', 'images/answerCenter/faq_head_tmp.jpg', null, null);
INSERT INTO `u_user` VALUES ('4', null, '欧阳洋', null, 'gg.com', 'gg', '2', 'images/answerCenter/faq_head_tmp.jpg', null, null);
INSERT INTO `u_user` VALUES ('5', null, '冯楚福', null, 'gg.com', 'gg', '2', 'images/answerCenter/faq_head_tmp.jpg', null, null);
INSERT INTO `u_user` VALUES ('6', null, '刘祚家', null, 'dd.com', 'dd', '2', 'images/answerCenter/faq_head_tmp.jpg', null, null);
INSERT INTO `u_user` VALUES ('7', null, '缪佳', null, 'dd.com', 'dd', '2', 'images/answerCenter/faq_head_tmp.jpg', null, null);
INSERT INTO `u_user` VALUES ('8', null, '董升', null, 'ss', 'ss', '2', 'images/answerCenter/faq_head_tmp.jpg', null, null);
INSERT INTO `u_user` VALUES ('9', null, '赵二元', null, '', 'dd', '1', '124595.jpg', null, null);
INSERT INTO `u_user` VALUES ('10', null, '陈思捷', null, 'a', 'a', '1', '1372473470.jpg', null, null);
INSERT INTO `u_user` VALUES ('11', null, '刘德华', null, 'a', 'a', '2', '124595.jpg', null, null);
INSERT INTO `u_user` VALUES ('12', null, '赵文卓', null, 'nemo@163.com', 'nemo', '1', null, null, null);
INSERT INTO `u_user` VALUES ('13', null, '11', null, '11', '11', '1', null, null, null);
INSERT INTO `u_user` VALUES ('14', null, 'fd', null, 'fd', 'df', '1', null, null, null);
INSERT INTO `u_user` VALUES ('15', null, 'qq', null, 'qq', 'qq', '1', null, null, null);
INSERT INTO `u_user` VALUES ('16', null, 'ww', null, 'ww', 'ww', '1', null, null, null);
INSERT INTO `u_user` VALUES ('17', null, 'ee', null, 'ee', 'ee', '1', null, null, null);
INSERT INTO `u_user` VALUES ('18', null, 'rr', null, 'rr', 'UU8bQ59AT4b3cJD6ntyWzg==', '1', null, null, null);
INSERT INTO `u_user` VALUES ('19', null, 'nemo', null, 'nemo', '5Yf2FG6/ve/cAoxZFkPyIA==', '1', null, null, null);
INSERT INTO `u_user` VALUES ('20', null, 'admin', null, 'admin', 'ISMvKXpXpadDiUoOSoAfww==', '1', null, null, null);
INSERT INTO `u_user` VALUES ('21', null, 'asdfas', null, 'asdfasd', 'kS7IA7LOSeSlQQaNSVq1cA==', '1', null, null, null);
INSERT INTO `u_user` VALUES ('22', null, 'nemo1', null, 'nemo1@163.com', '4QrcOUm6Wau+VuBX8g+IPg==', null, null, null, null);
INSERT INTO `u_user` VALUES ('23', null, 'nemo1', null, 'nemo1@163.com', '4QrcOUm6Wau+VuBX8g+IPg==', null, null, null, null);
INSERT INTO `u_user` VALUES ('24', null, 'nishi', null, 'nishi@163.com', 'RvlMjeFPs2aAhQdo/xt/Kg==', null, null, null, null);
INSERT INTO `u_user` VALUES ('25', null, 'dasd', null, '123@123.com', '4QrcOUm6Wau+VuBX8g+IPg==', null, null, null, null);
INSERT INTO `u_user` VALUES ('26', null, 'qwe', null, 'qwe@123.com', 'RvlMjeFPs2aAhQdo/xt/Kg==', null, null, null, null);
INSERT INTO `u_user` VALUES ('37', '1', 'mnmn', null, 'mnmn@163.com', 'RvlMjeFPs2aAhQdo/xt/Kg==', null, null, null, null);
INSERT INTO `u_user` VALUES ('38', '1', 'mimi', null, 'mimik@163.com', 'uC+sMNNrn9xkLhvG4XE4bQ==', null, null, null, null);
INSERT INTO `u_user` VALUES ('39', '1', 'wo_shid', null, 'nemi@123.com', 'RvlMjeFPs2aAhQdo/xt/Kg==', null, null, null, null);
INSERT INTO `u_user` VALUES ('40', '1', 'nimi', null, 'nimi@123.com', 'RvlMjeFPs2aAhQdo/xt/Kg==', null, null, null, null);
INSERT INTO `u_user` VALUES ('44', '1', '22222', null, '2222222@qq.com', '4QrcOUm6Wau+VuBX8g+IPg==', null, null, null, null);
INSERT INTO `u_user` VALUES ('45', '1', 'miaojia', null, 'miaojia@163.com', 'twjD9bCUvZYU/dhcDVXfsQ==', null, null, null, null);
INSERT INTO `u_user` VALUES ('46', '1', 'nihao', null, 'nihao@123.com', 'RvlMjeFPs2aAhQdo/xt/Kg==', null, null, null, null);
INSERT INTO `u_user` VALUES ('47', '1', 'sf3k', null, 'sf3k@123.com', 'RvlMjeFPs2aAhQdo/xt/Kg==', null, null, null, null);
INSERT INTO `u_user` VALUES ('48', '1', 'www', null, 'ww@123.com', 'RvlMjeFPs2aAhQdo/xt/Kg==', null, null, null, null);
INSERT INTO `u_user` VALUES ('49', '1', 'jasonmiao', null, 'jason1990825@163.com', 'rvlmjefps2aahqdo/xt/kg==', null, null, null, 'jason1990825@163.com');
INSERT INTO `u_user` VALUES ('50', '1', 'miaojia90', null, 'miaojia90@163.com', 'rvlmjefps2aahqdo/xt/kg==', null, null, null, '');
INSERT INTO `u_user` VALUES ('51', '1', 'yy', null, 'yangyang.ou@gtafe.com', '1vprseeajokuzst3srioag==', null, null, null, '');
INSERT INTO `u_user` VALUES ('52', '1', 'chm', null, 'chenhaiming@163.com', 'gdgjiuwuntebsczicowehg==', null, null, null, 'oeFybxGBbLStvXlfEHZwpA==');

-- ----------------------------
-- Table structure for `u_userrole`
-- ----------------------------
DROP TABLE IF EXISTS `u_userrole`;
CREATE TABLE `u_userrole` (
  `RoleID` int(11) NOT NULL AUTO_INCREMENT COMMENT '用户ID',
  `RoleName` varchar(20) NOT NULL COMMENT '角色名称',
  `RoleNote` varchar(20) DEFAULT NULL COMMENT '角色注释',
  PRIMARY KEY (`RoleID`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of u_userrole
-- ----------------------------
INSERT INTO `u_userrole` VALUES ('1', '学生', '323');

-- ----------------------------
-- Table structure for `u_user_course`
-- ----------------------------
DROP TABLE IF EXISTS `u_user_course`;
CREATE TABLE `u_user_course` (
  `ID` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键ID',
  `Cour_ID` int(11) DEFAULT NULL COMMENT '课程ID',
  `User_ID` int(11) DEFAULT NULL COMMENT '用户ID',
  `Progress` float DEFAULT '0' COMMENT '学习进度',
  `UserCour_Ctime` datetime DEFAULT NULL COMMENT '创建时间',
  `UserCour_Utime` datetime DEFAULT NULL COMMENT '修改时间',
  PRIMARY KEY (`ID`),
  KEY `FK_Reference_65` (`Cour_ID`),
  KEY `FK_Reference_70` (`User_ID`),
  CONSTRAINT `FK_Reference_65` FOREIGN KEY (`Cour_ID`) REFERENCES `c_course` (`Cour_ID`),
  CONSTRAINT `FK_Reference_70` FOREIGN KEY (`User_ID`) REFERENCES `u_user` (`User_ID`)
) ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of u_user_course
-- ----------------------------
INSERT INTO `u_user_course` VALUES ('1', '2', '1', '0', '2014-01-21 20:32:36', '2014-01-30 20:32:40');
INSERT INTO `u_user_course` VALUES ('2', '26', '2', '0', '2014-01-06 09:30:20', '2014-01-27 09:30:24');
INSERT INTO `u_user_course` VALUES ('3', '4', '3', '0', '2014-01-06 16:20:51', '2014-01-30 16:20:54');
INSERT INTO `u_user_course` VALUES ('4', '5', '4', '0', '2014-01-07 16:21:00', '2014-01-29 16:21:04');
INSERT INTO `u_user_course` VALUES ('5', '6', '5', '0', '2014-01-05 16:21:14', '2014-01-31 16:21:16');
INSERT INTO `u_user_course` VALUES ('6', '7', '6', '0', '2014-01-13 16:21:23', '2014-01-27 16:21:26');
INSERT INTO `u_user_course` VALUES ('7', '8', '7', '0', '2014-01-13 16:22:03', '2014-01-31 16:22:08');
INSERT INTO `u_user_course` VALUES ('8', '9', '8', '20', '2014-01-12 16:22:16', '2014-01-27 16:22:19');
INSERT INTO `u_user_course` VALUES ('9', '10', '9', '0', '2014-01-12 16:22:26', '2014-01-26 16:22:28');
INSERT INTO `u_user_course` VALUES ('10', '11', '10', '0', '2014-01-06 16:22:34', '2014-01-28 16:22:37');
INSERT INTO `u_user_course` VALUES ('11', '12', '11', '0', '2014-01-14 19:36:20', '2014-01-14 19:36:23');
INSERT INTO `u_user_course` VALUES ('12', '13', '2', '0', '2014-01-15 09:40:50', '2014-01-15 09:40:51');
INSERT INTO `u_user_course` VALUES ('13', '6', '2', '0', '2014-01-13 10:42:54', '2014-01-21 10:43:01');
INSERT INTO `u_user_course` VALUES ('14', '14', '2', '0', '2014-01-18 10:42:56', '2014-01-19 10:43:04');
INSERT INTO `u_user_course` VALUES ('15', null, null, '0', '2014-01-12 10:42:59', '2014-01-17 10:43:06');
