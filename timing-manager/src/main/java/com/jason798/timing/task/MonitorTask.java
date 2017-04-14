package com.jason798.timing.task;

import com.jason798.timing.TimingContext;
import com.jason798.timing.TimingInnerManager;
import com.jason798.timing.api.ITimingTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * monitor thread
 *
 * @author JasonLiu
 */
public class MonitorTask implements ITimingTask {
    private static Logger LOG = LoggerFactory.getLogger(MonitorTask.class);

    @Override
    public void execute() {
        String status = TimingContext.getStatusFmt(false);
        LOG.debug("timing status:" + status);
    }

}
