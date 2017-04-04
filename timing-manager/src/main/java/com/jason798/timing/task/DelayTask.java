package com.jason798.timing.task;

import com.jason798.log.LogClient;
import com.jason798.timing.api.ITimingTask;
import com.jason798.timing.domain.TaskEnum;

/**
 * delay execute task
 *
 * @author JasonLiu
 */
public class DelayTask extends BaseTask {

    protected ITimingTask service;

    protected boolean runned = false;
	
	public DelayTask(String tid) {
		super(tid);
		this.tid =tid;
		this.type = TaskEnum.DELAY;
	}
	
    public DelayTask(String tid, ITimingTask service) {
        super(tid);
        this.type = TaskEnum.DELAY;
        this.service = service;
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
            LogClient.writeError(DelayTask.class,"delay task execute error",e);
        }
        after();
        runned = true;
    }

    @Override
    public void after(){
        super.after();
        removeStatus();
    }


    /**
     * ############## getter & setter #################
     */
    public void setService(ITimingTask service) {
        this.service = service;
    }
    public ITimingTask getService() {
        return service;
    }

    public boolean isRunned() {
        return runned;
    }

    public void setRunned(boolean runned) {
        this.runned = runned;
    }
}
