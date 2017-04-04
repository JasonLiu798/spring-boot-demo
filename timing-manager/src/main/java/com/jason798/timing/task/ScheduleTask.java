package com.jason798.timing.task;

import com.jason798.timing.api.ITimingTask;
import com.jason798.timing.domain.TimingConstant;

/**
 * schedule task
 */
public class ScheduleTask implements ITimingTask {
	@Override
	public String getTid() {
		return TimingConstant.SCHEDULE_THREAD_ID;
	}
	
	@Override
	public void execute() {
		
	}
	
	
	
}
