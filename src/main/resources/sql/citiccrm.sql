/*
Navicat MySQL Data Transfer

Source Server         : better
Source Server Version : 50506
Source Host           : localhost:3306
Source Database       : citiccrm

Target Server Type    : MYSQL
Target Server Version : 50506
File Encoding         : 65001

Date: 2021-10-22 11:40:04
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `t_customer`
-- ----------------------------
DROP TABLE IF EXISTS `t_customer`;
CREATE TABLE `t_customer` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `khno` varchar(20) DEFAULT NULL,
  `name` varchar(20) DEFAULT NULL,
  `area` varchar(20) DEFAULT NULL,
  `cus_manager` varchar(20) DEFAULT NULL,
  `level` varchar(30) DEFAULT NULL,
  `myd` varchar(30) DEFAULT NULL,
  `xyd` varchar(30) DEFAULT NULL,
  `address` varchar(500) DEFAULT NULL,
  `post_code` varchar(50) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `fax` varchar(20) DEFAULT NULL,
  `web_site` varchar(20) DEFAULT NULL,
  `yyzzzch` varchar(50) DEFAULT NULL,
  `fr` varchar(20) DEFAULT NULL,
  `zccj` varchar(20) DEFAULT NULL,
  `nyye` varchar(20) DEFAULT NULL,
  `khyh` varchar(50) DEFAULT NULL,
  `khzh` varchar(50) DEFAULT NULL,
  `dsdjh` varchar(50) DEFAULT NULL,
  `gsdjh` varchar(50) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `is_valid` int(11) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=40 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_customer
-- ----------------------------
INSERT INTO `t_customer` VALUES ('1', 'KH21321321', '北京大牛科技', '北京', 'test', '战略合作伙伴', '☆☆☆', '☆☆☆', '北京海淀区双榆树东里15号', '100027', '010-62263393', '010-62263393', 'www.daniu.com', '420103000057404', '赵飞翔', '1000', '5000', '中国银行', '6225231243641', '4422214321321', '4104322332', '0', '1', '2021-10-01 11:28:43', '2021-10-17 18:42:19');
INSERT INTO `t_customer` VALUES ('2', 'KH20150526073022', '风驰科技', '北京', 'test', '大客户', '☆☆☆☆', '☆☆☆☆', '321', '21', '321', '321', '321', '321', '码云', '', '21', '321', '321', '321', '3213', '0', '1', '2017-01-16 12:15:19', '2016-11-28 11:46:24');
INSERT INTO `t_customer` VALUES ('20', 'KH201709181013450', '腾讯', '测试', 'test', '大客户', '☆☆☆☆☆', '☆☆☆☆', '深圳', '', '13327792156', '', '', null, '赵飞翔', '', '', '', '', '', '', '0', '1', '2017-01-16 10:13:57', '2020-02-19 10:30:26');
INSERT INTO `t_customer` VALUES ('21', 'KH201709181112739', '阿里巴巴', '北京', 'test01', '战略合作伙伴', '☆☆☆☆☆', '☆☆☆☆☆', '浙江杭州', '324324', '23424324324', '2343', 'www.alibaba.com', '232432', '码云', '100', '100000', '杭州', '23432432', '4324324', '234324234', '0', '1', '2017-01-16 11:12:16', '2017-09-18 11:25:25');
INSERT INTO `t_customer` VALUES ('22', 'KH20171021105508617', '中国工商银行', '上海', 'test', '战略合作伙伴', '☆☆☆☆☆', '☆☆☆☆☆', '浦东', '201600', '18920156732', '12312321', 'www.icbc.com', '12323', '吴三强', '1000000', '100000', '工商', '212321', '', '', '0', '1', '2017-01-16 10:55:09', '2020-11-14 02:28:37');
INSERT INTO `t_customer` VALUES ('35', 'KH1605353772927', '腾讯科技', '', '', '战略合作伙伴', null, '', '', '', '13787654345', '', '', null, '马化腾', '', '', '', '', '', '', '0', '1', '2020-11-14 19:36:13', '2020-11-14 20:21:04');
INSERT INTO `t_customer` VALUES ('36', 'KH1634263089314', '网易科技', null, null, '重点开发客户', null, null, null, null, '15852304638', null, null, null, '张朝阳', null, null, null, null, null, null, '0', '1', '2021-10-15 09:58:09', '2021-10-15 09:58:09');
INSERT INTO `t_customer` VALUES ('37', 'KH1634263149891', '网易', null, null, '普通客户', null, null, null, null, '15852304631', null, null, null, '丁磊', null, null, null, null, null, null, '0', '1', '2021-10-15 09:59:09', '2021-10-15 09:59:09');
INSERT INTO `t_customer` VALUES ('38', 'KH1634264217356', '腾讯金融', '', '', '合作伙伴', null, '', '', '', '15852304638', '', '', null, 'pony马', null, '', '', '', '', '', '0', '1', '2021-10-15 10:16:57', '2021-10-15 10:16:57');
INSERT INTO `t_customer` VALUES ('39', 'KH1634264322059', '小米', '北京', '王翔', '合作伙伴', null, '', '北京', '', '15852304638', '', 'xiaomi.com', null, '雷布斯', null, '', '', '', '', '', '0', '1', '2021-10-15 10:18:42', '2021-10-15 12:09:40');

-- ----------------------------
-- Table structure for `t_customer_contact`
-- ----------------------------
DROP TABLE IF EXISTS `t_customer_contact`;
CREATE TABLE `t_customer_contact` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cus_id` int(11) DEFAULT NULL,
  `contact_time` datetime DEFAULT NULL,
  `address` varchar(500) DEFAULT NULL,
  `overview` varchar(100) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `is_valid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_customer_contact
-- ----------------------------
INSERT INTO `t_customer_contact` VALUES ('1', '1', '2015-05-14 05:00:00', '1', '2', '2021-10-15 17:26:06', '2021-10-15 17:26:08', '1');
INSERT INTO `t_customer_contact` VALUES ('2', '1', '2015-05-06 00:00:00', '12', '22', '2021-10-15 17:26:17', '2021-10-15 17:26:11', '1');
INSERT INTO `t_customer_contact` VALUES ('3', '1', '2015-08-22 00:00:00', '珠江路2', '吃饭2', '2021-10-15 17:26:15', '2021-10-15 17:26:13', '1');
INSERT INTO `t_customer_contact` VALUES ('4', '1', '2016-09-01 00:00:00', '112', '233', '2016-09-01 09:53:39', '2016-09-01 09:53:39', '0');

-- ----------------------------
-- Table structure for `t_customer_linkman`
-- ----------------------------
DROP TABLE IF EXISTS `t_customer_linkman`;
CREATE TABLE `t_customer_linkman` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cus_id` int(11) DEFAULT NULL,
  `link_name` varchar(20) DEFAULT NULL,
  `sex` varchar(20) DEFAULT NULL,
  `zhiwei` varchar(50) DEFAULT NULL,
  `office_phone` varchar(50) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `is_valid` int(11) DEFAULT '1',
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=27 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_customer_linkman
-- ----------------------------
INSERT INTO `t_customer_linkman` VALUES ('1', '1', '李伟', '男', '总裁', '15852304638', '15852304638', '1', '2021-10-15 18:51:18', '2021-10-17 16:40:05');
INSERT INTO `t_customer_linkman` VALUES ('2', '1', '王伟', '男', '副总经理', '15852304638', '15852304638', '1', '2021-10-15 19:25:12', '2021-10-15 19:25:15');
INSERT INTO `t_customer_linkman` VALUES ('20', '1', '测试', '男', '总裁', '15852304638', '15852304638', '1', '2021-10-17 14:05:19', '2021-10-17 14:05:19');
INSERT INTO `t_customer_linkman` VALUES ('21', '1', '测试2', '男', '测试', '15852304638', '15852304638', '1', '2021-10-17 14:30:54', '2021-10-17 14:30:54');
INSERT INTO `t_customer_linkman` VALUES ('22', '1', '测试3', '男', '高管', '15852304638', '15852304638', '1', '2021-10-17 16:49:48', '2021-10-17 16:49:48');

-- ----------------------------
-- Table structure for `t_customer_loss`
-- ----------------------------
DROP TABLE IF EXISTS `t_customer_loss`;
CREATE TABLE `t_customer_loss` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cus_no` varchar(40) DEFAULT NULL,
  `cus_name` varchar(20) DEFAULT NULL,
  `cus_manager` varchar(20) DEFAULT NULL,
  `last_order_time` date DEFAULT NULL,
  `confirm_loss_time` date DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `loss_reason` varchar(1000) DEFAULT NULL,
  `is_valid` int(11) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=406 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_customer_loss
-- ----------------------------
INSERT INTO `t_customer_loss` VALUES ('401', 'KH20150526073022', '风驰科技', 'test', null, null, '0', null, '1', '2021-10-18 11:09:00', '2021-10-18 11:09:00');
INSERT INTO `t_customer_loss` VALUES ('402', 'KH201709181013450', '腾讯', 'test', '2020-09-03', null, '0', null, '1', '2021-10-18 11:09:00', '2021-10-18 11:09:00');
INSERT INTO `t_customer_loss` VALUES ('403', 'KH201709181112739', '阿里巴巴', 'test01', '2018-10-01', null, '0', null, '1', '2021-10-18 11:09:00', '2021-10-18 11:09:00');
INSERT INTO `t_customer_loss` VALUES ('404', 'KH20171021105508617', '中国工商银行', 'test', '2019-11-11', '2021-10-18', '1', '测试', '1', '2021-10-18 11:09:00', '2021-10-18 18:56:54');
INSERT INTO `t_customer_loss` VALUES ('405', 'KH1605353772927', '腾讯科技', '', null, '2021-10-18', '1', '测试', '1', '2021-10-18 11:09:00', '2021-10-18 19:01:55');

-- ----------------------------
-- Table structure for `t_customer_order`
-- ----------------------------
DROP TABLE IF EXISTS `t_customer_order`;
CREATE TABLE `t_customer_order` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `cus_id` int(11) DEFAULT NULL,
  `order_no` varchar(40) DEFAULT NULL,
  `order_date` datetime DEFAULT NULL,
  `address` varchar(200) DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `is_valid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_customer_order
-- ----------------------------
INSERT INTO `t_customer_order` VALUES ('5', '20', '201910021001', '2020-09-03 14:56:10', '上海松江区', '1', '2016-01-29 14:56:15', '2016-11-29 14:56:17', '1');
INSERT INTO `t_customer_order` VALUES ('6', '20', '202001022534', '2020-06-16 14:56:26', '杭州市滨江大道', '1', '2016-02-29 14:56:30', '2016-11-29 14:56:32', '1');
INSERT INTO `t_customer_order` VALUES ('7', '21', '201911021082', '2018-10-01 17:27:31', '上海浦东', '1', '2019-09-01 17:27:13', '2017-01-01 17:27:21', '1');
INSERT INTO `t_customer_order` VALUES ('8', '22', '201909021001', '2019-11-11 10:09:32', '北京海淀', '1', '2019-11-09 10:09:36', '2019-11-09 10:09:39', '1');

-- ----------------------------
-- Table structure for `t_customer_reprieve`
-- ----------------------------
DROP TABLE IF EXISTS `t_customer_reprieve`;
CREATE TABLE `t_customer_reprieve` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `loss_id` int(11) DEFAULT NULL,
  `measure` varchar(500) DEFAULT NULL,
  `is_valid` int(11) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=72 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_customer_reprieve
-- ----------------------------
INSERT INTO `t_customer_reprieve` VALUES ('44', '401', '请客户吃饭', '1', '2017-05-25 17:06:05', '2017-09-19 11:49:37');
INSERT INTO `t_customer_reprieve` VALUES ('45', '401', '客户请客', '1', '2017-05-25 00:00:00', '2017-09-19 11:49:36');
INSERT INTO `t_customer_reprieve` VALUES ('47', '402', '请马云吃顿饭_河马生鲜', '1', '2017-09-19 11:17:04', '2017-09-19 11:49:26');
INSERT INTO `t_customer_reprieve` VALUES ('49', '402', '请老马喝喝茶，聊聊天', '1', '2017-10-21 00:00:00', '2017-10-21 00:00:00');
INSERT INTO `t_customer_reprieve` VALUES ('50', '402', '请老马喝喝茶，聊聊天', '1', '2017-10-21 18:10:35', '2017-10-21 18:10:35');
INSERT INTO `t_customer_reprieve` VALUES ('66', '403', '和客户再次沟通', '1', '2020-11-17 17:26:18', '2020-11-17 17:27:41');
INSERT INTO `t_customer_reprieve` VALUES ('67', '404', '请客吃饭', '1', '2020-11-17 17:51:08', '2020-11-17 18:19:37');
INSERT INTO `t_customer_reprieve` VALUES ('68', '405', '和客户再次沟通', '1', '2020-11-17 17:51:19', '2020-11-17 18:19:40');
INSERT INTO `t_customer_reprieve` VALUES ('69', '401', '测试', '1', '2021-10-18 16:38:21', '2021-10-18 16:49:34');
INSERT INTO `t_customer_reprieve` VALUES ('70', '401', '测试22', '1', '2021-10-18 17:29:39', '2021-10-18 17:40:36');
INSERT INTO `t_customer_reprieve` VALUES ('71', '401', '测试111', '0', '2021-10-18 17:34:46', '2021-10-18 18:03:44');

-- ----------------------------
-- Table structure for `t_customer_serve`
-- ----------------------------
DROP TABLE IF EXISTS `t_customer_serve`;
CREATE TABLE `t_customer_serve` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `serve_type` varchar(30) DEFAULT NULL,
  `overview` varchar(500) DEFAULT NULL,
  `customer` varchar(30) DEFAULT NULL,
  `state` varchar(20) DEFAULT NULL,
  `service_request` varchar(500) DEFAULT NULL,
  `create_people` varchar(100) DEFAULT NULL,
  `assigner` varchar(100) DEFAULT NULL,
  `assign_time` datetime DEFAULT NULL,
  `service_proce` varchar(500) DEFAULT NULL,
  `service_proce_people` varchar(20) DEFAULT NULL,
  `service_proce_time` datetime DEFAULT NULL,
  `service_proce_result` varchar(500) DEFAULT NULL,
  `myd` varchar(50) DEFAULT NULL,
  `is_valid` int(11) DEFAULT '1',
  `update_date` datetime DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=97 DEFAULT CHARSET=utf8 ROW_FORMAT=DYNAMIC;

-- ----------------------------
-- Records of t_customer_serve
-- ----------------------------
INSERT INTO `t_customer_serve` VALUES ('74', '6', 'crm 有待改进', '腾讯', 'fw_005', '', 'admin', '42', '2020-02-20 16:32:57', '234234343423432', 'admin', '2020-02-20 18:32:35', '满意', '☆☆☆☆☆', '1', '2020-02-20 20:49:12', '2020-01-18 09:31:53');
INSERT INTO `t_customer_serve` VALUES ('75', '6', 'crm 有待改进', '腾讯', 'fw_005', '', 'admin', '42', '2020-01-18 11:01:20', '客服需求已解决 等待反馈', 'admin', '2020-01-18 11:55:23', '满意', '☆☆☆☆☆', '1', '2020-01-18 12:09:00', '2020-01-18 10:20:10');
INSERT INTO `t_customer_serve` VALUES ('76', '6', 'this is test...', '腾讯', 'fw_005', 'this is test...', 'admin', '42', '2020-02-20 16:33:06', '23423423432', 'admin', '2020-02-20 18:32:46', '满意', '☆☆☆☆', '1', '2020-02-20 20:49:19', '2020-02-20 15:10:50');
INSERT INTO `t_customer_serve` VALUES ('82', '6', 'Crm系统系统上线时间?', '腾讯', 'fw_003', '这是服务测试', 'admin', '42', '2020-02-28 11:13:21', 'Crm即将上线', 'admin', '2020-02-28 11:34:32', null, null, '1', '2020-02-28 11:34:32', '2020-02-28 09:57:18');
INSERT INTO `t_customer_serve` VALUES ('83', '6', '了解了解', '百度', 'fw_003', '了解一下', 'admin', '42', '2020-10-26 21:53:05', '服务处理...', null, '2020-11-20 15:40:34', null, null, '1', '2020-11-20 15:40:34', '2020-10-26 21:52:45');
INSERT INTO `t_customer_serve` VALUES ('84', '7', '测试', '腾讯', 'fw_005', '测试', 'admin', '42', '2020-11-19 18:07:06', '服务处理测试...', 'admin', '2020-11-20 15:43:14', '满意', '☆☆☆☆☆', '1', '2020-11-20 16:03:05', '2020-10-26 21:53:33');
INSERT INTO `t_customer_serve` VALUES ('85', '6', '111', '腾讯', 'fw_002', '111', 'admin', '42', '2020-11-19 18:08:07', null, null, null, null, null, '1', '2020-11-19 18:08:07', '2020-10-26 21:54:00');
INSERT INTO `t_customer_serve` VALUES ('86', '6', '添加服务测试。。。', '百度', 'fw_002', '添加服务测试', 'admin', '42', '2021-10-19 13:03:26', null, null, null, null, null, '1', '2021-10-19 13:03:26', '2020-11-19 15:39:14');
INSERT INTO `t_customer_serve` VALUES ('87', '6', '添加服务测试...', '百度', 'fw_002', '添加服务测试', 'admin', '42', '2021-10-19 13:03:34', null, null, null, null, null, '1', '2021-10-19 13:03:34', '2020-11-19 15:40:32');
INSERT INTO `t_customer_serve` VALUES ('88', '7', 'Test。。。', '腾讯', 'fw_002', 'Test', 'admin', '10', '2021-10-19 13:03:29', null, null, null, null, null, '1', '2021-10-19 13:03:29', '2020-11-19 15:41:08');
INSERT INTO `t_customer_serve` VALUES ('90', '6', '', '网易', 'fw_002', '测试', 'admin', '10', '2021-10-19 13:03:31', null, null, null, null, null, '1', '2021-10-19 13:03:31', '2021-10-19 09:58:30');
INSERT INTO `t_customer_serve` VALUES ('91', '6', '', '网易', 'fw_002', '测试2', null, '10', '2021-10-19 12:53:14', null, null, null, null, null, '1', '2021-10-19 12:53:14', '2021-10-19 09:59:59');
INSERT INTO `t_customer_serve` VALUES ('92', '6', '测试fff', '网易', 'fw_002', '测试fff', 'admin', '10', '2021-10-19 13:00:37', null, null, null, null, null, '1', '2021-10-19 13:00:37', '2021-10-19 10:13:18');
INSERT INTO `t_customer_serve` VALUES ('93', '7', '测试投诉', '网易', 'fw_002', '测试投诉', 'admin', '10', '2021-10-19 13:00:22', null, null, null, null, null, '1', '2021-10-19 13:00:22', '2021-10-19 12:25:47');
INSERT INTO `t_customer_serve` VALUES ('94', '7', '测试投诉222', '网易', 'fw_003', '测试投诉', 'admin', '10', '2021-10-19 12:46:28', '测试处理人', 'admin', '2021-10-19 15:38:57', null, null, '1', '2021-10-19 15:38:57', '2021-10-19 12:26:17');
INSERT INTO `t_customer_serve` VALUES ('95', '8', '测试投诉3', '网易', 'fw_004', '测试投诉', 'admin', '10', '2021-10-19 12:44:49', '测试服务处理', 'admin', '2021-10-19 15:35:35', '满意', '☆☆☆☆☆', '1', '2021-10-19 15:35:35', '2021-10-19 12:27:59');
INSERT INTO `t_customer_serve` VALUES ('96', '7', '测试服务分配', '风驰科技', 'fw_001', '测试服务分配', 'admin', null, null, null, null, null, null, null, '1', '2021-10-19 13:04:48', '2021-10-19 13:04:48');

-- ----------------------------
-- Table structure for `t_cus_dev_plan`
-- ----------------------------
DROP TABLE IF EXISTS `t_cus_dev_plan`;
CREATE TABLE `t_cus_dev_plan` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `sale_chance_id` int(11) DEFAULT NULL,
  `plan_item` varchar(100) DEFAULT NULL,
  `plan_date` datetime DEFAULT NULL,
  `exe_affect` varchar(100) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  `is_valid` int(11) DEFAULT '1',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=140 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_cus_dev_plan
-- ----------------------------
INSERT INTO `t_cus_dev_plan` VALUES ('69', '99', 'test01', '2017-02-28 00:00:00', 'test01', '2017-02-28 00:00:00', '2017-02-28 21:06:24', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('70', '99', 'test02', '2017-02-28 00:00:00', 'test02', '2017-02-28 00:00:00', '2017-02-28 21:06:25', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('71', '99', 'test03', '2017-02-28 00:00:00', 'test03', '2017-02-28 16:44:17', '2017-02-28 16:44:17', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('72', '99', 'test06', '2017-02-27 00:00:00', 'test06', '2017-02-28 00:00:00', '2017-02-28 16:48:11', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('73', '99', 'test05', '2017-02-22 00:00:00', 'test05', '2017-02-28 00:00:00', '2017-02-28 16:48:10', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('74', '99', '23424', '2017-02-23 00:00:00', '234324', '2017-02-28 21:08:02', '2017-02-28 21:08:02', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('75', '98', '123213', '2017-04-10 00:00:00', '21321', '2017-04-10 19:06:06', '2017-04-10 19:06:06', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('76', '100', 'test', '2017-04-11 00:00:00', 'qqqq', '2017-04-11 16:37:24', '2019-09-23 17:28:43', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('77', '100', '345435', '2017-04-11 00:00:00', '345435', '2017-04-11 00:00:00', '2017-04-11 16:52:06', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('78', '100', '456546', '2017-04-27 00:00:00', '456546', '2017-04-11 00:00:00', '2017-04-11 16:52:18', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('79', '98', '567657', '2017-04-10 00:00:00', '567657', '2017-04-11 16:52:13', '2017-04-11 16:52:13', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('80', '98', 'test20', '2017-05-20 00:00:00', 'ok', '2017-05-23 16:22:51', '2017-05-23 16:32:34', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('81', '98', '234343', '2017-05-22 00:00:00', 'ok', '2017-05-23 16:28:41', '2017-05-23 16:28:41', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('82', '98', '345435', '2017-05-30 00:00:00', '345435', '2017-05-23 16:37:05', '2017-05-23 16:37:05', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('83', '98', '345435', '2017-05-31 00:00:00', '345435', '2017-05-23 16:37:13', '2017-05-23 16:37:13', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('85', '98', '111', '2017-09-16 00:44:58', 'qqq', '2017-09-16 00:44:58', '2017-09-16 00:44:58', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('86', '98', '111', '2017-09-16 00:45:41', 'qqq', '2017-09-16 00:45:41', '2017-09-16 00:45:41', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('87', '100', '111', '2017-09-16 00:00:00', 'qqq', '2017-09-16 00:45:50', '2017-09-16 00:45:50', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('88', '100', '111', '2017-09-16 00:45:55', 'qqq', '2017-09-16 00:45:55', '2017-09-16 00:45:55', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('89', '100', '3434543', '2017-09-16 00:00:00', 'ok', '2017-09-16 11:42:03', '2017-09-16 11:42:03', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('90', '100', '34353', '2017-09-16 00:00:00', 'ok', '2017-09-16 11:43:28', '2017-09-16 11:43:28', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('91', '100', '678678', '2017-09-02 00:00:00', '678678', '2017-09-16 11:44:16', '2017-09-16 11:44:16', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('92', '100', 'aaaa', '2017-09-16 00:00:00', '678678', '2017-09-16 11:59:24', '2017-09-16 11:59:24', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('93', '100', 'abc', '2017-09-16 00:00:00', '678678', '2017-09-16 11:59:56', '2017-09-16 11:59:56', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('95', '100', 'aaaa', '2017-10-20 00:00:00', 'ok', '2017-10-20 17:28:20', '2017-10-20 17:28:20', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('96', '100', 'test02', '2017-10-18 00:00:00', 'test02', '2017-10-20 17:28:45', '2017-10-20 17:33:42', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('97', '100', 'test', '2018-01-11 00:00:00', 'ok', '2018-01-11 00:00:00', '2018-01-11 11:29:07', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('98', '100', 'test02', '2018-01-11 00:00:00', 'ok', '2018-01-11 00:00:00', '2018-01-11 11:29:06', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('99', '100', 'test03', '2018-01-11 00:00:00', 'ok', '2018-01-11 00:00:00', '2018-01-11 11:29:05', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('100', '100', 'test04', '2018-01-11 00:00:00', 'ok', '2018-01-11 00:00:00', '2018-01-11 11:29:06', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('101', '100', 'test05', '2018-01-11 00:00:00', 'ok', '2018-01-11 11:02:44', '2018-01-11 11:02:44', '0');
INSERT INTO `t_cus_dev_plan` VALUES ('102', '100', 'test07', '2018-01-11 00:00:00', 'ok', '2018-01-11 00:00:00', '2018-01-11 11:09:15', '0');
INSERT INTO `t_cus_dev_plan` VALUES ('103', '100', 'aaaa', '2018-01-11 00:00:00', 'ok', '2018-01-11 00:00:00', '2018-01-11 11:07:45', '0');
INSERT INTO `t_cus_dev_plan` VALUES ('104', '100', '133', '2018-05-02 00:00:00', '1', '2018-05-02 00:00:00', '2018-05-02 09:23:31', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('105', '100', '3', '2018-05-09 00:00:00', '3', '2018-05-02 09:23:43', '2018-05-02 09:23:43', '0');
INSERT INTO `t_cus_dev_plan` VALUES ('106', '100', 'test', '2019-09-23 00:00:00', 'ok', '2019-09-23 17:20:51', '2019-09-23 17:20:51', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('107', '100', 'test', '2019-09-23 00:00:00', '123213', '2019-09-23 17:21:12', '2019-09-23 17:21:12', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('108', '100', 'test002', '2019-09-23 00:00:00', 'ok', '2019-09-23 17:23:33', '2019-09-23 17:28:17', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('109', '100', 'test00001', '2019-09-22 00:00:00', 'ok', '2019-09-23 17:24:41', '2019-09-23 17:28:28', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('110', '100', 'aaaa', '2019-09-23 00:00:00', 'ok', '2019-09-23 17:29:40', '2019-09-23 17:29:40', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('111', '100', 'aaaa', '2019-11-04 00:00:00', 'aaaa', '2019-11-05 00:00:00', '2019-11-05 16:08:35', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('112', '100', 'test', '2019-11-03 00:00:00', 'test', '2019-11-05 16:09:26', '2019-11-05 16:09:26', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('113', '100', 'test01', '2019-11-01 00:00:00', 'test01', '2019-11-05 16:10:34', '2019-11-05 16:10:34', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('114', '100', 'test02', '2019-11-02 00:00:00', 'test02', '2019-11-05 16:11:34', '2019-11-05 16:11:34', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('116', '98', 'test01', '2019-12-05 00:00:00', 'test', '2019-12-06 00:00:00', '2019-12-06 11:17:58', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('117', '98', 'test02', '2019-12-06 00:00:00', 'test02', '2019-12-06 00:00:00', '2019-12-06 11:17:43', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('118', '98', 'aaaa', '2019-12-06 00:00:00', 'aaaa', '2019-12-06 11:37:13', '2019-12-06 11:37:13', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('119', '98', 'aaaa', '2019-12-06 00:00:00', 'aaaa', '2019-12-06 11:37:44', '2019-12-06 11:37:44', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('120', '100', 'test', '2020-01-11 00:00:00', 'test', '2020-01-11 11:46:09', '2020-01-11 11:46:09', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('121', '100', 'test05', '2020-01-03 00:00:00', 'test05', '2020-01-11 00:00:00', '2020-01-11 11:48:26', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('122', '100', 'test01', '2020-01-02 00:00:00', 'test01', '2020-01-11 00:00:00', '2020-01-11 11:48:54', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('123', '100', 'test', '2020-01-11 00:00:00', 'test', '2020-01-11 12:02:24', '2020-01-11 12:02:24', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('124', '124', 'test', '2020-01-10 00:00:00', 'test23423', '2020-02-18 22:41:13', '2020-02-18 22:43:20', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('125', '98', 'test', '2020-01-10 00:00:00', 'testasd', '2020-02-18 22:48:28', '2020-02-18 22:48:34', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('126', '98', 'test', '2020-01-10 00:00:00', 'test23423', '2020-02-18 22:48:41', '2020-02-18 22:48:41', '0');
INSERT INTO `t_cus_dev_plan` VALUES ('127', '124', 'test', '2020-01-10 00:00:00', 'test23423', '2020-02-18 22:49:14', '2020-02-18 22:49:14', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('128', '97', 'test', '2020-01-10 00:00:00', 'test23423', '2020-02-23 23:29:27', '2020-02-23 23:29:27', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('129', '97', 'test01', '2020-01-20 00:00:00', 'ok', '2020-03-14 16:37:35', '2020-03-14 16:46:39', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('130', '130', 'aaaa', '2020-11-11 00:00:00', 'aaaa', '2020-11-04 20:15:06', '2020-11-04 20:15:06', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('131', '130', 'aaaa', '2020-11-12 00:00:00', 'aaaa', '2020-11-04 20:15:50', '2020-11-04 20:15:50', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('132', '127', 'aaaa', '2020-12-13 00:00:00', 'aaaa', '2020-11-04 21:12:29', '2020-11-04 21:48:19', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('133', '127', 'aaaa', '2020-12-13 00:00:00', 'ok', '2020-11-05 10:29:47', '2020-11-05 10:30:10', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('134', '130', '计划A', '2020-11-11 00:00:00', null, '2021-10-10 13:42:59', '2021-10-10 13:42:59', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('135', '130', '计划B', '2020-11-12 00:00:00', '成功', '2021-10-10 13:43:49', '2021-10-10 13:43:49', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('136', '128', '测试1', '2021-11-11 00:00:00', '测试1', '2021-10-10 16:24:10', '2021-10-10 16:24:10', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('137', '128', '计划BB', '2021-11-15 00:00:00', 'postman测试更新', '2021-10-10 16:25:25', '2021-10-10 18:26:02', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('138', '1', '总结测试', '2021-11-11 00:00:00', '成功', '2021-10-10 18:49:24', '2021-10-10 18:49:24', '1');
INSERT INTO `t_cus_dev_plan` VALUES ('139', '128', 'ff', '2021-11-11 00:00:00', '测试1', '2021-10-11 10:28:06', '2021-10-11 10:28:10', '0');

-- ----------------------------
-- Table structure for `t_datadic`
-- ----------------------------
DROP TABLE IF EXISTS `t_datadic`;
CREATE TABLE `t_datadic` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `data_dic_name` varchar(50) DEFAULT NULL,
  `data_dic_value` varchar(50) DEFAULT NULL,
  `is_valid` tinyint(4) DEFAULT '1',
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE,
  KEY `FK_t_datadic` (`data_dic_value`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=9 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_datadic
-- ----------------------------
INSERT INTO `t_datadic` VALUES ('1', '客户等级', '普通客户', '1', '2020-02-20 10:04:27', '2020-02-20 10:04:48');
INSERT INTO `t_datadic` VALUES ('2', '客户等级', '重点开发客户', '1', '2020-02-20 10:04:30', '2020-02-20 10:04:51');
INSERT INTO `t_datadic` VALUES ('3', '客户等级', '大客户', '1', '2020-02-20 10:04:33', '2020-02-20 10:04:53');
INSERT INTO `t_datadic` VALUES ('4', '客户等级', '合作伙伴', '1', '2020-02-20 10:04:35', '2020-02-20 10:04:56');
INSERT INTO `t_datadic` VALUES ('5', '客户等级', '战略合作伙伴', '1', '2020-02-20 10:04:37', '2020-02-20 10:04:59');
INSERT INTO `t_datadic` VALUES ('6', '服务类型', '咨询', '1', '2020-02-20 10:04:40', '2020-02-20 10:05:01');
INSERT INTO `t_datadic` VALUES ('7', '服务类型', '建议', '1', '2020-02-20 10:04:43', '2020-02-20 10:05:04');
INSERT INTO `t_datadic` VALUES ('8', '服务类型', '投诉', '1', '2020-02-20 10:04:45', '2016-08-24 15:48:46');

-- ----------------------------
-- Table structure for `t_log`
-- ----------------------------
DROP TABLE IF EXISTS `t_log`;
CREATE TABLE `t_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `description` varchar(255) DEFAULT NULL,
  `method` varchar(255) DEFAULT NULL,
  `type` varchar(255) DEFAULT NULL,
  `request_ip` varchar(255) DEFAULT NULL,
  `exception_code` varchar(255) DEFAULT NULL,
  `exception_detail` varchar(255) DEFAULT NULL,
  `params` text,
  `create_date` datetime DEFAULT NULL,
  `execute_time` int(11) DEFAULT NULL,
  `create_man` varchar(255) DEFAULT NULL,
  `result` longtext,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_log
-- ----------------------------

-- ----------------------------
-- Table structure for `t_module`
-- ----------------------------
DROP TABLE IF EXISTS `t_module`;
CREATE TABLE `t_module` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `module_name` varchar(255) DEFAULT NULL COMMENT '资源名称',
  `module_style` varchar(255) DEFAULT NULL COMMENT '模块样式',
  `url` varchar(255) DEFAULT NULL COMMENT '地址',
  `parent_id` int(11) DEFAULT NULL,
  `parent_opt_value` varchar(255) DEFAULT NULL,
  `grade` int(11) DEFAULT NULL COMMENT '等级',
  `opt_value` varchar(255) DEFAULT NULL COMMENT '权限值',
  `orders` int(11) DEFAULT NULL,
  `is_valid` tinyint(4) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=118 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_module
-- ----------------------------
INSERT INTO `t_module` VALUES ('1', '营销管理', '', '#', '-1', null, '0', '10', '1', '1', '2017-09-28 00:00:00', '2020-02-17 15:46:59');
INSERT INTO `t_module` VALUES ('2', '营销机会管理', '', 'saleChance/index', '1', null, '1', '1010', '1', '1', '2017-09-28 00:00:00', '2020-02-17 15:47:26');
INSERT INTO `t_module` VALUES ('3', '营销机会管理查询', '', '#', '2', null, '2', '101001', '2', '1', '2017-09-28 00:00:00', '2020-02-17 15:47:51');
INSERT INTO `t_module` VALUES ('4', '营销机会管理添加', '', '#', '2', null, '2', '101002', '2', '1', '2017-09-28 00:00:00', '2017-09-28 00:00:00');
INSERT INTO `t_module` VALUES ('5', '营销机会管理删除', '', '#', '2', null, '2', '101003', '3', '1', '2017-09-28 00:00:00', '2017-09-28 00:00:00');
INSERT INTO `t_module` VALUES ('6', '客户开发计划', '', 'cus_dev_plan/index', '1', null, '1', '1020', '2', '1', '2017-09-28 00:00:00', '2017-09-28 00:00:00');
INSERT INTO `t_module` VALUES ('7', '查看详情', '', '#', '6', null, '2', '102001', '1', '1', '2017-09-28 00:00:00', '2017-09-28 00:00:00');
INSERT INTO `t_module` VALUES ('8', '客户管理', '', 'customer/index', '-1', null, '0', '20', '3', '1', '2017-07-01 00:00:00', '2017-07-01 00:00:00');
INSERT INTO `t_module` VALUES ('9', '客户信息管理', '', 'customer/index', '8', null, '1', '2010', '1', '1', '2017-09-06 00:00:00', '2017-09-06 00:00:00');
INSERT INTO `t_module` VALUES ('10', '创建', '', '#', '9', null, '2', '201001', '1', '1', '2017-07-01 00:00:00', '2017-07-01 00:00:00');
INSERT INTO `t_module` VALUES ('11', '修改', '', '#', '9', null, '2', '201002', '2', '1', '2017-07-01 00:00:00', '2017-07-01 00:00:00');
INSERT INTO `t_module` VALUES ('12', '客户流失管理', '', 'customer_loss/index', '8', null, '1', '2020', '2', '1', '2017-08-17 00:00:00', '2017-08-17 00:00:00');
INSERT INTO `t_module` VALUES ('13', '暂缓流失', '', 'openCustomerReprieve', '12', null, '2', '202001', '1', '1', '2017-09-23 00:00:00', '2017-09-23 00:00:00');
INSERT INTO `t_module` VALUES ('14', '统计报表', '', '#', '-1', null, '0', '40', '4', '1', '2017-08-15 00:00:00', '2017-08-15 00:00:00');
INSERT INTO `t_module` VALUES ('15', '客户贡献分析', '', 'report/1', '14', null, '1', '4010', '1', '1', '2017-08-15 00:00:00', '2017-08-15 00:00:00');
INSERT INTO `t_module` VALUES ('16', '服务管理', '', '#', '-1', null, '0', '30', '3', '1', '2017-08-18 00:00:00', '2017-08-18 00:00:00');
INSERT INTO `t_module` VALUES ('17', '基础数据管理', '', '#', '-1', null, '0', '50', '5', '1', '2017-08-18 00:00:00', '2017-08-18 00:00:00');
INSERT INTO `t_module` VALUES ('18', '系统管理', '', '#', '-1', null, '0', '60', '6', '1', '2017-08-18 00:00:00', '2017-08-18 00:00:00');
INSERT INTO `t_module` VALUES ('19', '删除', '', '#', '9', null, '2', '201003', '3', '1', '2017-08-18 00:00:00', '2017-08-18 00:00:00');
INSERT INTO `t_module` VALUES ('26', '用户管理', '', 'user/index', '18', null, '1', '6010', null, '1', '2017-10-24 16:54:12', '2017-10-24 16:54:12');
INSERT INTO `t_module` VALUES ('27', '角色管理', '', 'role/index', '18', null, '1', '6020', null, '1', '2018-01-13 11:29:17', '2018-01-13 11:29:19');
INSERT INTO `t_module` VALUES ('28', '资源管理', '', 'module/index/1', '18', null, '1', '6030', null, '1', '2018-01-13 11:29:40', '2018-01-13 11:29:42');
INSERT INTO `t_module` VALUES ('35', '服务分配', '', null, '16', null, '1', '3020', null, '1', '2018-01-16 09:22:26', '2018-01-16 09:22:28');
INSERT INTO `t_module` VALUES ('36', '服务处理', '', null, '16', null, '1', '3030', null, '1', '2018-01-16 09:22:47', '2018-01-16 09:22:50');
INSERT INTO `t_module` VALUES ('37', '服务反馈', '', null, '16', null, '1', '3040', null, '1', '2018-01-16 09:23:11', '2018-01-16 09:23:13');
INSERT INTO `t_module` VALUES ('38', '服务归档', '', null, '16', null, '1', '3050', null, '1', '2018-01-16 09:23:37', '2018-01-16 09:23:39');
INSERT INTO `t_module` VALUES ('39', '客户构成分析', '', null, '14', null, null, '4020', null, '1', '2018-01-16 14:57:24', '2018-01-16 14:57:27');
INSERT INTO `t_module` VALUES ('40', '客户服务分析', '', null, '14', null, null, '4030', null, '1', '2018-01-16 16:14:48', '2018-01-16 16:14:50');
INSERT INTO `t_module` VALUES ('44', '营销机会管理修改', '', null, '2', null, '2', '101004', null, '1', '2019-09-25 15:22:12', '2020-01-15 10:43:09');
INSERT INTO `t_module` VALUES ('102', '数据字典管理', '', 'sale_chance/xxx', '17', null, '1', '5010', null, '1', '2019-09-26 11:07:00', '2019-09-26 11:07:00');
INSERT INTO `t_module` VALUES ('103', '产品信息查询', '', '#', '17', null, '2', '5020', null, '1', '2019-09-26 11:13:14', '2019-09-26 11:13:14');
INSERT INTO `t_module` VALUES ('109', '客户类别分析', '', 'report/r01', '14', null, '1', '4060', null, '1', '2019-11-09 16:31:58', '2019-11-09 16:31:58');
INSERT INTO `t_module` VALUES ('110', '测试管理', null, 'test/index', '1', null, '1', '1030', null, '1', '2021-10-13 20:02:56', '2021-10-13 20:02:56');
INSERT INTO `t_module` VALUES ('111', '财务管理', null, 'test/caiwu', '1', null, '1', '1040', null, '1', '2021-10-13 20:07:29', '2021-10-13 20:07:29');
INSERT INTO `t_module` VALUES ('113', '财务管理', null, 'test/caiwu', '-1', null, '0', '104001', null, '0', '2021-10-13 20:12:27', '2021-10-14 16:46:15');
INSERT INTO `t_module` VALUES ('114', '财务管理2', null, 'test/caiwu2', '-1', null, '0', '1040012', null, '0', '2021-10-13 20:13:12', '2021-10-14 16:46:28');
INSERT INTO `t_module` VALUES ('115', '测试模块', '', null, '-1', null, '0', '80', null, '0', '2021-10-14 15:02:05', '2021-10-14 16:46:03');
INSERT INTO `t_module` VALUES ('116', '测试子菜单2', '', 'test01/index', '115', null, '1', '8010', null, '0', '2021-10-14 15:04:04', '2021-10-14 16:45:59');
INSERT INTO `t_module` VALUES ('117', '测试子菜单0223', '', null, '116', null, '2', '801004', null, '0', '2021-10-14 15:04:30', '2021-10-14 16:45:56');

-- ----------------------------
-- Table structure for `t_order_details`
-- ----------------------------
DROP TABLE IF EXISTS `t_order_details`;
CREATE TABLE `t_order_details` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `order_id` int(11) DEFAULT NULL,
  `goods_name` varchar(100) DEFAULT NULL,
  `goods_num` int(11) DEFAULT NULL,
  `unit` varchar(20) DEFAULT NULL,
  `price` float DEFAULT NULL,
  `sum` float DEFAULT NULL,
  `is_valid` int(11) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_order_details
-- ----------------------------
INSERT INTO `t_order_details` VALUES ('1', '5', '联想笔记本', '2', '台', '4900', '9800', '1', '2016-11-29 14:59:32', '2016-11-29 14:59:34');
INSERT INTO `t_order_details` VALUES ('2', '5', '惠普音响', '4', '台', '200', '800', '1', '2017-03-01 11:32:34', '2017-03-01 11:32:36');
INSERT INTO `t_order_details` VALUES ('3', '8', '罗技键盘', '10', '个', '90', '900', '1', '2017-03-01 11:32:39', '2017-03-01 11:32:41');
INSERT INTO `t_order_details` VALUES ('4', '6', '艾利鼠标', '20', '个', '20', '400', '1', '2017-03-01 11:32:46', '2017-03-01 11:32:48');
INSERT INTO `t_order_details` VALUES ('5', '7', '东芝U盘', '5', '个', '105', '525', '1', '2017-03-01 11:32:51', '2017-03-01 11:32:53');
INSERT INTO `t_order_details` VALUES ('6', '7', '充电器', '1', '个', '30', '30', '1', '2017-03-01 11:32:55', '2017-03-01 11:32:57');

-- ----------------------------
-- Table structure for `t_permission`
-- ----------------------------
DROP TABLE IF EXISTS `t_permission`;
CREATE TABLE `t_permission` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) DEFAULT NULL COMMENT '角色ID',
  `module_id` int(11) DEFAULT NULL COMMENT '模块ID',
  `acl_value` varchar(255) DEFAULT NULL COMMENT '权限值',
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=1247 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_permission
-- ----------------------------
INSERT INTO `t_permission` VALUES ('31', '20', '18', '60', '2021-10-13 14:44:06', '2021-10-13 14:44:06');
INSERT INTO `t_permission` VALUES ('32', '20', '26', '6010', '2021-10-13 14:44:06', '2021-10-13 14:44:06');
INSERT INTO `t_permission` VALUES ('33', '20', '27', '6020', '2021-10-13 14:44:06', '2021-10-13 14:44:06');
INSERT INTO `t_permission` VALUES ('34', '20', '28', '6030', '2021-10-13 14:44:06', '2021-10-13 14:44:06');
INSERT INTO `t_permission` VALUES ('1215', '1', '1', '10', '2021-10-15 17:39:30', '2021-10-15 17:39:30');
INSERT INTO `t_permission` VALUES ('1216', '1', '2', '1010', '2021-10-15 17:39:30', '2021-10-15 17:39:30');
INSERT INTO `t_permission` VALUES ('1217', '1', '3', '101001', '2021-10-15 17:39:30', '2021-10-15 17:39:30');
INSERT INTO `t_permission` VALUES ('1218', '1', '4', '101002', '2021-10-15 17:39:30', '2021-10-15 17:39:30');
INSERT INTO `t_permission` VALUES ('1219', '1', '5', '101003', '2021-10-15 17:39:30', '2021-10-15 17:39:30');
INSERT INTO `t_permission` VALUES ('1220', '1', '44', '101004', '2021-10-15 17:39:30', '2021-10-15 17:39:30');
INSERT INTO `t_permission` VALUES ('1221', '1', '6', '1020', '2021-10-15 17:39:30', '2021-10-15 17:39:30');
INSERT INTO `t_permission` VALUES ('1222', '1', '7', '102001', '2021-10-15 17:39:30', '2021-10-15 17:39:30');
INSERT INTO `t_permission` VALUES ('1223', '1', '8', '20', '2021-10-15 17:39:30', '2021-10-15 17:39:30');
INSERT INTO `t_permission` VALUES ('1224', '1', '9', '2010', '2021-10-15 17:39:30', '2021-10-15 17:39:30');
INSERT INTO `t_permission` VALUES ('1225', '1', '10', '201001', '2021-10-15 17:39:30', '2021-10-15 17:39:30');
INSERT INTO `t_permission` VALUES ('1226', '1', '11', '201002', '2021-10-15 17:39:30', '2021-10-15 17:39:30');
INSERT INTO `t_permission` VALUES ('1227', '1', '19', '201003', '2021-10-15 17:39:30', '2021-10-15 17:39:30');
INSERT INTO `t_permission` VALUES ('1228', '1', '12', '2020', '2021-10-15 17:39:30', '2021-10-15 17:39:30');
INSERT INTO `t_permission` VALUES ('1229', '1', '13', '202001', '2021-10-15 17:39:30', '2021-10-15 17:39:30');
INSERT INTO `t_permission` VALUES ('1230', '1', '14', '40', '2021-10-15 17:39:30', '2021-10-15 17:39:30');
INSERT INTO `t_permission` VALUES ('1231', '1', '15', '4010', '2021-10-15 17:39:30', '2021-10-15 17:39:30');
INSERT INTO `t_permission` VALUES ('1232', '1', '39', '4020', '2021-10-15 17:39:30', '2021-10-15 17:39:30');
INSERT INTO `t_permission` VALUES ('1233', '1', '40', '4030', '2021-10-15 17:39:30', '2021-10-15 17:39:30');
INSERT INTO `t_permission` VALUES ('1234', '1', '109', '4060', '2021-10-15 17:39:30', '2021-10-15 17:39:30');
INSERT INTO `t_permission` VALUES ('1235', '1', '16', '30', '2021-10-15 17:39:30', '2021-10-15 17:39:30');
INSERT INTO `t_permission` VALUES ('1236', '1', '35', '3020', '2021-10-15 17:39:30', '2021-10-15 17:39:30');
INSERT INTO `t_permission` VALUES ('1237', '1', '36', '3030', '2021-10-15 17:39:30', '2021-10-15 17:39:30');
INSERT INTO `t_permission` VALUES ('1238', '1', '37', '3040', '2021-10-15 17:39:30', '2021-10-15 17:39:30');
INSERT INTO `t_permission` VALUES ('1239', '1', '38', '3050', '2021-10-15 17:39:30', '2021-10-15 17:39:30');
INSERT INTO `t_permission` VALUES ('1240', '1', '17', '50', '2021-10-15 17:39:30', '2021-10-15 17:39:30');
INSERT INTO `t_permission` VALUES ('1241', '1', '102', '5010', '2021-10-15 17:39:30', '2021-10-15 17:39:30');
INSERT INTO `t_permission` VALUES ('1242', '1', '103', '5020', '2021-10-15 17:39:30', '2021-10-15 17:39:30');
INSERT INTO `t_permission` VALUES ('1243', '1', '18', '60', '2021-10-15 17:39:30', '2021-10-15 17:39:30');
INSERT INTO `t_permission` VALUES ('1244', '1', '26', '6010', '2021-10-15 17:39:30', '2021-10-15 17:39:30');
INSERT INTO `t_permission` VALUES ('1245', '1', '27', '6020', '2021-10-15 17:39:30', '2021-10-15 17:39:30');
INSERT INTO `t_permission` VALUES ('1246', '1', '28', '6030', '2021-10-15 17:39:30', '2021-10-15 17:39:30');

-- ----------------------------
-- Table structure for `t_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_role`;
CREATE TABLE `t_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) DEFAULT NULL,
  `role_remark` varchar(255) DEFAULT NULL COMMENT '备注',
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  `is_valid` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=21 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_role
-- ----------------------------
INSERT INTO `t_role` VALUES ('1', '系统管理员', '系统管理员', '2016-12-01 00:00:00', '2020-02-24 15:53:12', '1');
INSERT INTO `t_role` VALUES ('2', '销售1', '销售', '2016-12-01 00:00:00', '2021-10-12 19:50:27', '1');
INSERT INTO `t_role` VALUES ('3', '客户经理', '客户经理', '2017-10-23 09:15:10', '2020-02-24 15:53:29', '1');
INSERT INTO `t_role` VALUES ('14', '技术经理', '技术经理', '2020-11-10 14:34:00', '2020-11-10 14:34:00', '1');
INSERT INTO `t_role` VALUES ('17', '人事', '人事', '2020-11-10 14:34:42', '2020-11-10 15:53:03', '1');
INSERT INTO `t_role` VALUES ('18', '测试人员', '测试人员', '2021-10-08 21:00:47', '2021-10-08 21:00:51', '1');
INSERT INTO `t_role` VALUES ('19', '测试经理', '测试经理', '2021-10-08 21:01:08', '2021-10-08 21:01:10', '1');
INSERT INTO `t_role` VALUES ('20', '经理2号', '测试', '2021-10-08 21:01:20', '2021-10-12 20:10:10', '1');

-- ----------------------------
-- Table structure for `t_sale_chance`
-- ----------------------------
DROP TABLE IF EXISTS `t_sale_chance`;
CREATE TABLE `t_sale_chance` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `change_source` varchar(300) DEFAULT NULL,
  `customer_name` varchar(100) DEFAULT NULL,
  `cgji` int(11) DEFAULT NULL,
  `overview` varchar(300) DEFAULT NULL,
  `link_man` varchar(100) DEFAULT NULL,
  `link_phone` varchar(100) DEFAULT NULL,
  `description` varchar(1000) DEFAULT NULL,
  `create_man` varchar(100) DEFAULT NULL,
  `assign_man` varchar(100) DEFAULT NULL,
  `assign_time` datetime DEFAULT NULL,
  `state` int(11) DEFAULT NULL,
  `dev_result` int(11) DEFAULT NULL,
  `is_valid` int(11) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_time` datetime DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=140 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_sale_chance
-- ----------------------------
INSERT INTO `t_sale_chance` VALUES ('1', '官网', '测试更新', '40', '测试机会数据', '测试', '15700008929', '测试机会数据', 'admin', '10', '2021-10-08 21:31:21', '1', '2', '1', '2019-11-05 10:28:41', '2021-10-08 21:31:21');
INSERT INTO `t_sale_chance` VALUES ('98', '官网', '阿里云uim', '50', '测试机会数据', '马小云', '15710218920', '测试机会数据', 'admin', '10', '2021-10-01 16:24:38', '1', '3', '1', '2019-11-05 11:07:48', '2021-10-09 16:14:39');
INSERT INTO `t_sale_chance` VALUES ('99', '官网', '阿里云', '50', '测试机会数据', '马小云', '15710218920', '测试机会数据', 'admin', '10', '2021-10-01 16:24:38', '1', '3', '1', '2019-11-05 11:07:48', '2020-02-23 15:02:12');
INSERT INTO `t_sale_chance` VALUES ('100', '同事介绍', '百度', '70', '测试机会数据', '李彦宏', '18090261546', '测试机会数据', 'admin', '10', '2021-10-13 21:16:10', '0', '1', '1', '2020-03-12 22:35:44', '2020-03-12 22:35:44');
INSERT INTO `t_sale_chance` VALUES ('101', '同事介绍', '百度', '20', '测试机会数据', '马小云', '18690251466', '测试机会数据', 'admin', '10', '2021-10-28 21:16:04', '0', '1', '1', '2020-03-13 10:22:31', '2020-03-13 12:01:32');
INSERT INTO `t_sale_chance` VALUES ('102', '官网', '百度', '20', '测试机会数据', '马小云', '15710218920', '测试机会数据', 'admin', '42', '2021-10-06 21:16:01', '0', '1', '1', '2020-03-13 10:22:58', '2020-03-13 10:52:47');
INSERT INTO `t_sale_chance` VALUES ('103', '同事介绍', '百度', '0', '测试机会数据', '马小云', '15710218929', '测试机会数据', 'admin', '10', '2020-03-13 14:57:06', '1', '1', '1', '2020-03-13 14:51:22', '2020-03-13 14:57:06');
INSERT INTO `t_sale_chance` VALUES ('128', '同事介绍', '百度', '0', '测试机会数据', '马小云', '15710218929', '测试机会数据', 'admin', '10', '2020-03-19 20:17:56', '1', '2', '1', '2020-03-19 20:17:56', '2020-03-19 20:17:56');
INSERT INTO `t_sale_chance` VALUES ('129', '互联网', '腾讯', '0', '测试机会数据', '马小腾', '15898765432', '测试机会数据', 'admin', '42', '2021-10-13 21:16:34', '0', '1', '1', '2020-11-03 15:54:46', '2020-11-03 15:54:46');
INSERT INTO `t_sale_chance` VALUES ('130', '互联网', '百度', '50', '测试机会数据', 'fff', '15852304638', '测试机会数据', 'admin', '42', '2021-10-08 20:17:09', '1', '1', '1', '2020-11-03 15:56:03', '2021-10-08 20:17:09');
INSERT INTO `t_sale_chance` VALUES ('131', '广告', '腾讯', '80', '测试', '马小腾', '15876543212', 'Test', 'admin', '42', '2021-09-14 21:16:37', '0', '1', '2', '2020-11-03 17:20:26', '2020-11-03 17:20:26');
INSERT INTO `t_sale_chance` VALUES ('132', '官网', '腾讯', '70', '测试', '马小腾', '18876476567', '222', 'admin', '42', '2020-11-04 10:33:33', '1', '1', '1', '2020-11-03 19:54:07', '2020-11-04 10:33:33');
INSERT INTO `t_sale_chance` VALUES ('135', '广告', 'ff', '10', '测试', 'f', '15852304638', '测试', 'admin', '', null, '0', '1', '1', '2021-10-02 13:42:18', '2021-10-02 13:42:18');
INSERT INTO `t_sale_chance` VALUES ('136', '官网', '阿里', '80', '测试', 'f', '15852304638', '这是', 'admin', '', null, '0', '1', '1', '2021-10-02 13:44:55', '2021-10-11 10:27:45');
INSERT INTO `t_sale_chance` VALUES ('137', '官网', '阿里', '70', '测试', 'f', '15852304638', '测试添加成功弹出', 'admin', '', null, '0', '1', '0', '2021-10-02 13:46:18', '2021-10-02 13:46:18');
INSERT INTO `t_sale_chance` VALUES ('138', '广告', 'f', '70', '测试', 'f', '15852304638', '测试 成功笑脸', 'admin', '', null, '0', '1', '0', '2021-10-02 13:54:15', '2021-10-02 13:54:15');
INSERT INTO `t_sale_chance` VALUES ('139', '官网', '测试', '50', '测试', 'test', '15852304638', '11', 'admin', '10', '2021-10-11 10:27:20', '1', '1', '0', '2021-10-11 10:27:20', '2021-10-11 10:27:20');

-- ----------------------------
-- Table structure for `t_user`
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `user_name` varchar(20) DEFAULT NULL,
  `user_pwd` varchar(100) DEFAULT NULL,
  `true_name` varchar(20) DEFAULT NULL,
  `email` varchar(30) DEFAULT NULL,
  `phone` varchar(20) DEFAULT NULL,
  `is_valid` int(11) DEFAULT '1',
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=51 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('10', 'admin', 'gnzLDuqKcGxMNKFokfhOew==', 'admin', '126@126.com', '13327792157', '1', '2016-12-01 12:05:49', '2021-10-11 15:32:09');
INSERT INTO `t_user` VALUES ('42', 'scott', '4QrcOUm6Wau+VuBX8g+IPg==', 'scott', '234@126.com', '13327792157', '1', '2017-09-09 00:14:53', '2021-10-19 12:44:17');
INSERT INTO `t_user` VALUES ('43', '张三1', '4QrcOUm6Wau+VuBX8g+IPg==', '张三', 'zhangsan1@163.com', '15852304638', '1', '2021-10-11 11:04:18', '2021-10-11 11:28:22');
INSERT INTO `t_user` VALUES ('44', 'abc', '4QrcOUm6Wau+VuBX8g+IPg==', '王五', 'wangwu@163.com', '15852304638', '1', '2021-10-11 11:21:36', '2021-10-11 14:28:00');
INSERT INTO `t_user` VALUES ('45', 'abcd', '4QrcOUm6Wau+VuBX8g+IPg==', 'abcd', 'abcd@163.com', '15852304638', '1', '2021-10-11 15:45:57', '2021-10-12 17:32:32');
INSERT INTO `t_user` VALUES ('50', 'test001', '4QrcOUm6Wau+VuBX8g+IPg==', '测试1号', 'test001@163.com', '15852304638', '1', '2021-10-12 17:24:05', '2021-10-12 17:27:09');

-- ----------------------------
-- Table structure for `t_user_role`
-- ----------------------------
DROP TABLE IF EXISTS `t_user_role`;
CREATE TABLE `t_user_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  `create_date` datetime DEFAULT NULL,
  `update_date` datetime DEFAULT NULL,
  PRIMARY KEY (`id`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=24 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of t_user_role
-- ----------------------------
INSERT INTO `t_user_role` VALUES ('2', '10', '1', '2021-10-08 20:57:25', '2021-10-08 20:57:28');
INSERT INTO `t_user_role` VALUES ('3', '10', '3', '2021-10-08 20:57:35', '2021-10-08 20:57:38');
INSERT INTO `t_user_role` VALUES ('4', '10', '2', '2021-10-11 16:14:18', '2021-10-11 16:14:20');
INSERT INTO `t_user_role` VALUES ('22', '42', '2', '2021-10-19 12:44:17', '2021-10-19 12:44:17');
INSERT INTO `t_user_role` VALUES ('23', '42', '3', '2021-10-19 12:44:17', '2021-10-19 12:44:17');
