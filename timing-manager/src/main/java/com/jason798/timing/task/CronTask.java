package com.jason798.timing.task;

import com.jason798.common.DateUtil;
import com.jason798.timing.TimingCoreHelper;
import com.jason798.timing.api.ITimingTask;
import com.jason798.timing.domain.CronExpression;
import com.jason798.timing.domain.TaskEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.text.ParseException;
import java.util.Date;

/**
 * cron execute task
 *
 * @author JasonLiu
 */
public class CronTask extends FixRateTask {
    private static Logger LOG = LoggerFactory.getLogger(CronTask.class);

    /**
     * cron expression
     */
    private CronExpression cronExpression;

    public CronTask(Long tid, TimingCoreHelper helper, ITimingTask service, String cronExpressionStr) throws ParseException {
        super(tid, helper, service);
        this.type = TaskEnum.CRON;
        this.cronExpression = new CronExpression(cronExpressionStr);
    }

    @Override
    public void before() {
        super.before();
    }

    @Override
    public void execute() {
        service.execute();
    }

    /**
     * calc cron expression next run time
     * todo: if execute time overlaps the next run time
     *
     * @return
     */
    public Long cron2delay() {
        Date now = new Date();
        Date next = cronExpression.getNextValidTimeAfter(now);
        if (LOG.isDebugEnabled()) {
            LOG.debug("cron2delay now {},next {}", now, next);
        }
        return DateUtil.getIntervalMs(now, next);
    }

    /**
     * reput task to shedual pool
     */
    @Override
    public void after() {
        super.after();
        this.delayTime = cron2delay();
        LOG.debug("cron task next delay {}", this.delayTime);
        boolean res = timingCoreHelper.reSubmitCronTask(this);
        LOG.debug("cron task reput res {}", res);
    }


    /**
     * ############## getter & setter #################
     */
    public CronExpression getCronExpression() {
        return cronExpression;
    }

    public void setCronExpression(CronExpression cronExpression) {
        this.cronExpression = cronExpression;
    }
}
