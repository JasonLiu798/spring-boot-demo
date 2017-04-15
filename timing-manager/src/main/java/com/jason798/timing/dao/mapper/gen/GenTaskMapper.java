package com.jason798.timing.dao.mapper.gen;

import com.jason798.timing.domain.gen.GenTask;
import com.jason798.timing.domain.gen.GenTaskExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GenTaskMapper {
    long countByExample(GenTaskExample example);

    int insert(GenTask record);

    int insertSelective(GenTask record);

    List<GenTask> selectByExample(GenTaskExample example);

    GenTask selectByPrimaryKey(Long tid);

    int updateByExampleSelective(@Param("record") GenTask record, @Param("example") GenTaskExample example);

    int updateByExample(@Param("record") GenTask record, @Param("example") GenTaskExample example);

    int updateByPrimaryKeySelective(GenTask record);

    int updateByPrimaryKey(GenTask record);
}