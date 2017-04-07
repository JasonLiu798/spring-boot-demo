package com.jason798.timing.task;


import com.jason798.timing.TimingContext;
import com.jason798.timing.TimingCoreHelper;
import com.jason798.timing.api.ITimingTask;
import com.jason798.timing.domain.TimingConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * monitor thread
 *
 * @author JasonLiu
 */
public class MonitorTask implements ITimingTask {

    private static Logger LOG = LoggerFactory.getLogger(MonitorTask.class);

    private static long interval = 1000;
    private static long delay = 1000;

//    @Override
//    public String getTid() {
//        return TimingConstant.MONITOR_THREAD_ID;
//    }

    @Override
    public void execute() {
        String status = TimingContext.getStatusFmt();
        LOG.debug("timing status:" + status);
    }

    public static FixRateTask build(TimingCoreHelper helper){
        ITimingTask service = new MonitorTask();
        FixRateTask t = new FixRateTask(TimingConstant.MONITOR_THREAD_ID,helper,service );
        t.setDelayTime(delay);
        t.setInterval(interval);
        t.setPersist(false);//no persist
        return t;
    }
}
