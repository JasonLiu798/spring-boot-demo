package com.jason798.timing.mapper.gen;

import com.jason798.timing.domain.gen.GenTaskManager;
import com.jason798.timing.domain.gen.GenTaskManagerExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GenTaskManagerMapper {
    long countByExample(GenTaskManagerExample example);

    int insert(GenTaskManager record);

    int insertSelective(GenTaskManager record);

    List<GenTaskManager> selectByExample(GenTaskManagerExample example);

    GenTaskManager selectByPrimaryKey(Long mid);

    int updateByExampleSelective(@Param("record") GenTaskManager record, @Param("example") GenTaskManagerExample example);

    int updateByExample(@Param("record") GenTaskManager record, @Param("example") GenTaskManagerExample example);

    int updateByPrimaryKeySelective(GenTaskManager record);

    int updateByPrimaryKey(GenTaskManager record);
}