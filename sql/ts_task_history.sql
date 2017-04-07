
SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `ts_task_history`
-- ----------------------------
DROP TABLE IF EXISTS `ts_task_history`;
CREATE TABLE `ts_task_history` (
  `htid` bigint(20) NOT NULL AUTO_INCREMENT COMMENT '主键',
  `tid` bigint(20) DEFAULT NULL COMMENT '任务id',
  `TYPE` varchar(32) COLLATE utf8mb4_bin DEFAULT NULL COMMENT 'C-crontab，DCV',
  `PROCESSOR` varchar(128) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '执行的进程',
  `THREAD` varchar(256) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '执行的线程',
  `START_TM` bigint(20) DEFAULT NULL COMMENT '运行时间',
  `END_TM` bigint(20) DEFAULT NULL COMMENT '结束时间',
  `EXE_STATUS` char(255) COLLATE utf8mb4_bin DEFAULT 'N' COMMENT '运行结果，N-正常，E-异常',
  `EXE_CNT` bigint(20) DEFAULT NULL COMMENT '运行次数',
  `MEMO` varchar(2000) COLLATE utf8mb4_bin DEFAULT NULL COMMENT '运行备注',
  PRIMARY KEY (`htid`)
) ENGINE=InnoDB AUTO_INCREMENT=79 DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_bin;
