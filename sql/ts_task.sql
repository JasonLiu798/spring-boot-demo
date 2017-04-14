

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `ts_task`
-- ----------------------------
DROP TABLE IF EXISTS `ts_task`;
CREATE TABLE `ts_task` (
  `TID` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `TKEY` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '唯一编号',
  `TSERVICE` varchar(512) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '业务类',
  `PARAM` varchar(2048) COLLATE utf8mb4_bin DEFAULT NULL,
  `CONF_TYPE` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '任务类型',
  `CONF_CRON_EXPRESSION` varchar(64) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'cron表达式，类型为cron用',
  `CONF_DELAY_TM` bigint(20) DEFAULT NULL COMMENT '延迟执行时间，单位毫秒',
  `CONF_INTERVAL_TM` bigint(20) DEFAULT NULL COMMENT '间隔时间，单位毫秒',
  `CONF_EXE_TIMES` bigint(20) DEFAULT '1' COMMENT '最多执行次数，-1，永久执行（建议用cron）',
  `PROCESSOR` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL,
  `TMUTEX` char(1) COLLATE utf8mb4_bin DEFAULT 'N' COMMENT '锁',
  `MUTEX_TM` bigint(20) DEFAULT '0' COMMENT '加锁时间',
  `TSTATUS` char(1) COLLATE utf8mb4_bin DEFAULT 'N' COMMENT '状态，F-初始化，I-初始化，P-等待执行，E-执行中，N-结束',
  `ALIVE_TM` bigint(20) DEFAULT '0',
  `VALID` char(1) COLLATE utf8mb4_bin DEFAULT 'Y' COMMENT '是否有效，Y，N',
  PRIMARY KEY (`TID`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;

-- ----------------------------
-- Records of ts_task
-- ----------------------------
INSERT INTO `ts_task` VALUES ('1', 't1', 'testCron', null, 'CRON', '*/5 * * * * ?', null, null, '1', 'ip-175508582,pid-24488', 'N', '1492165350', 'W', '1492169530', 'Y');
INSERT INTO `ts_task` VALUES ('3', '3', 'fixratecond', null, 'FIXRATECONDPARAM', null, '3000', '3000', '3', 'ip-175508582,pid-3444', 'N', '1492158762', 'D', '1492158771', 'Y');
