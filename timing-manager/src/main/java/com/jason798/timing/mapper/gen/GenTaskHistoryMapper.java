package com.jason798.timing.mapper.gen;

import com.jason798.timing.domain.gen.GenTaskHistory;
import com.jason798.timing.domain.gen.GenTaskHistoryExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface GenTaskHistoryMapper {
    long countByExample(GenTaskHistoryExample example);

    int insert(GenTaskHistory record);

    int insertSelective(GenTaskHistory record);

    List<GenTaskHistory> selectByExample(GenTaskHistoryExample example);

    GenTaskHistory selectByPrimaryKey(Long htid);

    int updateByExampleSelective(@Param("record") GenTaskHistory record, @Param("example") GenTaskHistoryExample example);

    int updateByExample(@Param("record") GenTaskHistory record, @Param("example") GenTaskHistoryExample example);

    int updateByPrimaryKeySelective(GenTaskHistory record);

    int updateByPrimaryKey(GenTaskHistory record);
}