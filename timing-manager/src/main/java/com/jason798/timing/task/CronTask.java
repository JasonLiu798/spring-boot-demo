package com.jason798.timing.task;

import com.jason798.common.DateUtil;
import com.jason798.log.LogClient;
import com.jason798.timing.api.ITimingTask;
import com.jason798.timing.domain.CronExpression;
import com.jason798.timing.domain.TaskEnum;

import java.util.Date;

/**
 * cron execute task
 *
 * @author JasonLiu
 */
public class CronTask extends DelayTask {

    protected boolean runned = false;
    
    private CronExpression cronExpression;

    public CronTask(String tid, ITimingTask service,CronExpression e) {
        super(tid);
        this.type = TaskEnum.CRON;
        this.service = service;
        this.cronExpression = e;
    }

    @Override
    public void before() {
        super.before();
    }

    @Override
    public void run() {
        before();
        try {
            service.execute();
        }catch (Exception e){
            LogClient.writeError(CronTask.class,"cron task execute error",e);
        }
		runned = true;
        after();
    }

    @Override
    public void after(){
        super.after();
        
        Date now = new Date();
		Date next = cronExpression.getNextValidTimeAfter(now);
		
		//removeStatus();
		DateUtil.getIntervalSec(now,next);
		timingCoreHelper.submitDelayTask(service,this.delayTime);
        
    }

    
    

    /**
     * ############## getter & setter #################
     */
}
