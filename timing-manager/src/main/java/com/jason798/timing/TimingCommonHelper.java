package com.jason798.timing;

import com.jason798.common.DateUtil;
import com.jason798.common.SystemUtil;
import com.jason798.net.IPUtil;
import com.jason798.number.RandomUtil;
import com.jason798.timing.domain.gen.GenTaskManager;
import com.jason798.timing.mapper.gen.GenTaskManagerMapper;
import org.springframework.stereotype.Component;


import javax.annotation.Resource;

/**
 * common functions
 */
@Component
public class TimingCommonHelper {

    @Resource
    private GenTaskManagerMapper genTaskManagerMapper;

    /**
     * db registe
     */
    public String registe() {
        Long ipLong = IPUtil.getOneRandomIpLong();
        String pid = SystemUtil.getPid();

        String id = String.format("ip-%s,pid-%s", ipLong, pid);

        GenTaskManager m = new GenTaskManager();
        m.setName(id);
        m.setLastUpdateTm(DateUtil.getNowTS());
        genTaskManagerMapper.insertSelective(m);
        return id;
    }


    /**
     * alive update
     * @param id
     */
    public void alive(Long id){
        GenTaskManager manager = new GenTaskManager();
        manager.setMid(id);
        Long now = DateUtil.getNowTS();
        manager.setLastUpdateTm(now);
        genTaskManagerMapper.updateByPrimaryKeySelective(manager);
    }




    /**
     * generate random tie
     *
     * @return
     */
    public String genRandomTid() {
        return RandomUtil.generateMD5();
    }

}
