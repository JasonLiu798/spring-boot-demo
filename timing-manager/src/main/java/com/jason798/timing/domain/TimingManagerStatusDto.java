package com.jason798.timing.domain;

import sf.aos.timing.task.*;
import sf.aos.util.collection.CollectionUtil;
import sf.aos.util.common.DateUtil;

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

    public String fmt() {
        StringBuilder sb = new StringBuilder();
        if (CollectionUtil.isEmpty(taskStatusDtoMap)) {
            return "no task runned";
        }
        sb.append("timing tasks count[").append(taskCount).append("]:").append("\n");
        for (Map.Entry<String, TaskStatusDto> entry : taskStatusDtoMap.entrySet()) {
            TaskStatusDto task = entry.getValue();
            sb.append("tid ").append(task.getTid()).append(",");
            sb.append("tkey ").append(task.getTkey()).append(",");
            sb.append("type ").append(task.getType()).append(",");

            sb.append("LastStart ").append(task.getLastStartTm()).append(",");
            sb.append("LastStop ").append(task.getLastStopTm()).append(",");

            sb.append("dealy ").append(task.getDelay()).append(",");
            sb.append("interval ").append(task.getInterval()).append(",");
            sb.append("isRunning ").append(task.isRunning()).append(",");

            if(task.getMaxRunCount()!=null){
                sb.append("RunMax ").append(task.getMaxRunCount()).append(",");
            }

            if(task.getCond()!=null) {
                sb.append("cond ").append(task.getCond()).append(",");
            }

            if (task.isRunned() != null) {
                sb.append("Runned ").append(task.isRunned()).append(",");
            }

            sb.append("RunCnt ").append(task.getRunnedCounter()).append("\n");

        }
        return sb.toString();
    }

    /**
     * @param task
     */
    public void addTaskStatus(BaseTask task) {
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

        if (task instanceof DelayTask) {
            DelayTask dtask = (DelayTask) task;
            statusDto.setRunned(dtask.isRunned());
        } else {
            statusDto.setRunned(null);
        }


        if(task instanceof FixRateCondTask){
            FixRateCondTask fc = (FixRateCondTask) task;
            statusDto.setCond(fc.getCond());
            statusDto.setMaxRunCount(fc.getMaxTime());
            //statusDto.set
        }

        statusDto.setLastStartTm(DateUtil.formatDefault(DateUtil.tsms2Date(task.getLastStartTime())));
        statusDto.setLastStopTm(DateUtil.formatDefault(DateUtil.tsms2Date(task.getLastStopTime())));

        statusDto.setRunning(task.isRunning());

        long ssInterval = Math.abs(task.getLastStartTime() - task.getLastStopTime());
        statusDto.setStartStopInterval(ssInterval);
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
