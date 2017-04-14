package com.jason798.timing.task;


import com.jason798.log.LogClient;
import com.jason798.log.LogConstant;
import com.jason798.timing.TimingInnerManager;
import com.jason798.timing.domain.TaskEnum;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * fix rate until reach the condition
 *
 * @author JasonLiu
 */
public class FixRateCondTask extends FixRateCntTask {

    protected Method condMethod;

    public FixRateCondTask(Long tid, TimingInnerManager helper, Object target, Method exeMethod, Method condMethod, Long maxTime) {
        super(tid, helper, target, exeMethod, maxTime);
        this.type = TaskEnum.FIXRATECOND;
        this.condMethod = condMethod;
    }

    /**
     * check condition reach
     */
    @Override
    public void after() {
        super.after();
        if (!this.end) {
            Boolean bres = null;
            try {
                Object res = condMethod.invoke(target,param);
                bres = (Boolean) res;
            } catch (IllegalAccessException | InvocationTargetException e) {
                LogClient.writeError(LogConstant.MODULE_TIMING, "task "+tid+",execute cond fail", e);

            }
            //bres == null,mean cond method execute fail
            //bres == true,mean condition=true
            //then stop task
            if (bres == null || bres == true) {
                this.end = true;
            }
        }
    }

    /**
     * ################## getter & setter ################################
     */

    public void setCondMethod(Method condMethod) {
        this.condMethod = condMethod;
    }

    //    public Method getCondMethod() {
//        return condMethod;
//    }

}
