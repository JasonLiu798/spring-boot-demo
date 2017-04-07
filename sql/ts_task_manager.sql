
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `ts_task_manager`
-- ----------------------------
DROP TABLE IF EXISTS `ts_task_manager`;
CREATE TABLE `ts_task_manager` (
  `MID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '管理模块id',
  `NAME` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '名称',
  `ALIVE_TM` bigint(20) DEFAULT NULL COMMENT '存活时间',
  `LAST_UPDATE_TM` bigint(20) DEFAULT NULL COMMENT '最近存活时间',
  PRIMARY KEY (`MID`)
) ENGINE=InnoDB AUTO_INCREMENT=29 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of ts_task_manager
-- ----------------------------
INSERT INTO `ts_task_manager` VALUES ('1', 'ip-175508585,pid-7872', null, '1491460766');
INSERT INTO `ts_task_manager` VALUES ('2', 'ip-175508585,pid-18388', null, '1491460999');
INSERT INTO `ts_task_manager` VALUES ('11', 'ip-175508585,pid-16640', null, '1491471564');
INSERT INTO `ts_task_manager` VALUES ('14', 'ip-175508585,pid-5288', null, '1491475961');
INSERT INTO `ts_task_manager` VALUES ('17', 'ip-175508585,pid-18148', null, '1491476707');
INSERT INTO `ts_task_manager` VALUES ('18', 'ip-175508585,pid-18860', null, '1491476804');
INSERT INTO `ts_task_manager` VALUES ('19', 'ip-175508585,pid-19472', null, '1491478651');
INSERT INTO `ts_task_manager` VALUES ('24', 'ip-175508585,pid-21896', null, '1491545992');
INSERT INTO `ts_task_manager` VALUES ('25', 'ip-175508585,pid-26008', null, '1491550163');
INSERT INTO `ts_task_manager` VALUES ('28', 'ip-175508585,pid-25004', null, '1491550480');
