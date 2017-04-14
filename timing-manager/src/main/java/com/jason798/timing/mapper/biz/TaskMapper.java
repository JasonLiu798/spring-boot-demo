package com.jason798.timing.mapper.biz;

import com.jason798.timing.domain.TaskDomain;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * task mapper
 */
public interface TaskMapper {
    TaskDomain getTask(Long tid);
    List<TaskDomain> getTasks(@Param("valid") String showValid,
                              @Param("showEnd") String showEnd);

}
