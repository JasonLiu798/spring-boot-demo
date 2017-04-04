package com.jason798.timing;

import com.jason798.character.StringCheckUtil;
import com.jason798.collection.CollectionUtil;
import com.jason798.common.CommonConstant;
import com.jason798.common.DateUtil;
import com.jason798.common.SystemUtil;
import com.jason798.net.IPUtil;
import com.jason798.number.RandomUtil;
import com.jason798.timing.domain.TimingConstant;
import com.jason798.timing.domain.gen.GenTask;
import com.jason798.timing.domain.gen.GenTaskExample;
import com.jason798.timing.domain.gen.GenTaskManager;
import com.jason798.timing.mapper.gen.GenTaskManagerMapper;
import com.jason798.timing.mapper.gen.GenTaskMapper;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;

/**
 * common functions
 */
@Component
public class TimingCommonHelper {

    @Resource
    private GenTaskMapper genTaskMapper;

    @Resource
    private GenTaskManagerMapper genTaskManagerMapper;

    /**
     * db registe
     */
    public String registe() {
        Long ipLong = IPUtil.getOneRandomIpLong();
        String pid = SystemUtil.getPid();

        String id = String.format("ip-%s,pid-%s", ipLong, pid);
        //this.managerId = id;

        GenTaskManager m = new GenTaskManager();
        m.setName(id);
        m.setLastUpdateTm(DateUtil.getNowTS());
        genTaskManagerMapper.insertSelective(m);
        return id;
    }


    /**
     * task exist
     *
     * @param key
     * @return
     */
    public boolean taskExist(String key) {
        GenTaskExample param = new GenTaskExample();
        param.createCriteria().andValidEqualTo(TimingConstant.VALID).andTkeyEqualTo(key);
        if (genTaskMapper.countByExample(param) > 0) {
            return true;
        }
        return false;
    }


    /**
     * @param key
     * @return
     */
    public GenTask getTaskByKey(String key) {
        if (StringCheckUtil.isEmpty(key)) {
            return null;
        }
        GenTaskExample param = new GenTaskExample();
        param.createCriteria().andValidEqualTo(TimingConstant.VALID).andTkeyEqualTo(key);
        List<GenTask> l = genTaskMapper.selectByExample(param);
        if (!CollectionUtil.isEmpty(l)) {
            return l.get(0);
        }
        return null;
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
