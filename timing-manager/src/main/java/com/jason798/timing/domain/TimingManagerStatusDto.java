package com.jason798.timing.domain;


import com.jason798.collection.CollectionUtil;
import com.jason798.common.DateUtil;
import com.jason798.json.JSONFastJsonUtil;
import com.jason798.timing.task.BaseTimingTask;
import com.jason798.timing.task.DelayTask;
import com.jason798.timing.task.FixRateCondTask;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

/**
 * task status
 *
 * @author JasonLiu
 */
public class TimingManagerStatusDto implements Serializable {

    /**
     * task status map
     */
    private Map<String, TaskStatusDto> taskStatusDtoMap = new HashMap<>();
    /**
     * manual set
     */
    private int taskCount;

    public TimingManagerStatusDto() {
    }

    public String fmtJson(){
        if (CollectionUtil.isEmpty(taskStatusDtoMap)) {
            return "";
        }
        return JSONFastJsonUtil.objectToJson(taskStatusDtoMap);
    }

    public String fmt() {
        StringBuilder sb = new StringBuilder();
        if (CollectionUtil.isEmpty(taskStatusDtoMap)) {
            return "no task runned";
        }
        sb.append("timing tasks count[").append(taskCount).append("]:").append("\n");
        for (Map.Entry<String, TaskStatusDto> entry : taskStatusDtoMap.entrySet()) {
            TaskStatusDto task = entry.getValue();
            sb.append("TID ").append(task.getTid()).append(",");
            sb.append("KEY ").append(task.getTkey()).append(",");
            sb.append("TP ").append(task.getType()).append(",");
            sb.append("CNT ").append(task.getRunnedCounter()).append(",");


            sb.append("LStart ").append(task.getLastStartTm()).append(",");
            sb.append("LStop ").append(task.getLastStopTm()).append(",");

            sb.append("DELAY ").append(task.getDelay()).append(",");
            sb.append("INTVL ").append(task.getInterval()).append(",");


            if(task.getMaxRunCount()!=null){
                sb.append("MAX ").append(task.getMaxRunCount()).append(",");
            }

            if(task.getCond()!=null) {
                sb.append("COND ").append(task.getCond()).append(",");
            }

            if (task.isEnd() != null) {
                sb.append("END ").append(task.isEnd()).append(",");
            }

            sb.append("EXE ").append(task.isRunning()).append("\n");

        }
        return sb.toString();
    }

    /**
     * @param task
     */
    public void addTaskStatus(BaseTimingTask task) {
        TaskStatusDto statusDto = new TaskStatusDto();
        Long taskId = task.getTid();
        String key = task.getKey();
        //basis info
        statusDto.setTid(taskId);
        statusDto.setTkey(task.getKey());
        statusDto.setType(String.valueOf(task.getType()));

        //status info
        statusDto.setRunnedCounter(task.getRunnedCounter());
        statusDto.setDelay(task.getDelayTime());
        statusDto.setInterval(task.getInterval());

        if(task instanceof DelayTask) {
            statusDto.setEnd(task.isEnd());
        }

        if(task instanceof FixRateCondTask){
            FixRateCondTask fc = (FixRateCondTask) task;
            statusDto.setMaxRunCount(fc.getMaxTime());
        }

        Long lastStart = task.getLastStartTime();
        Long lastStop = task.getLastStopTime();
        if( lastStart!=null) {
//            statusDto.setLastStartTm(DateUtil.formatDefaultShort(DateUtil.tsms2Date(task.getLastStartTime())));
        }
        if(lastStop!=null) {
//            statusDto.setLastStopTm(DateUtil.formatDefaultShort(DateUtil.tsms2Date(task.getLastStopTime())));
        }

        if(lastStart!=null && lastStop!=null) {
            long ssInterval = Math.abs(task.getLastStartTime() - task.getLastStopTime());
            statusDto.setStartStopInterval(ssInterval);
        }

        statusDto.setRunning(task.isRunning());

        taskStatusDtoMap.put(key, statusDto);
    }

    public Map<String, TaskStatusDto> getTaskStatusDtoMap() {
        return taskStatusDtoMap;
    }

    public void setTaskStatusDtoMap(Map<String, TaskStatusDto> taskStatusDtoMap) {
        this.taskStatusDtoMap = taskStatusDtoMap;
    }

    public int getTaskCount() {
        return taskCount;
    }

    public void setTaskCount(int taskCount) {
        this.taskCount = taskCount;
    }
}
