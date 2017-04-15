package com.jason798.timing;

import com.jason798.common.SystemUtil;
import com.jason798.json.JSONFastJsonUtil;
import com.jason798.timing.api.RetCode;
import com.jason798.timing.api.TimingManager;
import com.jason798.timing.domain.TimingConstant;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = false, transactionManager = "transactionManager")
@ContextConfiguration(locations = {"classpath:test-service.xml"})
@Transactional
public class TimingManagerTest {

    @Autowired
    private TimingManager timingManager;

    @Test
    public void testExecCronTask() {
        timingManager.init();

        RetCode res = timingManager.execCronTask(1L);
        System.out.println("res:"+res);

        res = timingManager.execCronTask(2L);
        System.out.println("res:"+res);
        SystemUtil.sleepForever();

    }


    @Test
    public void reExecDynamicTask() {
        timingManager.init();
        RetCode res = timingManager.reExecDynamicTask(3L);
        System.out.println("res:" + res);

        SystemUtil.sleepForever();
    }


    @Test
    public void MTreExecDynamicTask() {
        timingManager.init();

        CountDownLatch l = new CountDownLatch(2);
        T1 t1 = new T1("t1", timingManager, l);
        T1 t2 = new T1("t2", timingManager, l);
        ExecutorService es = Executors.newFixedThreadPool(10);
        es.submit(t1);
        es.submit(t2);

        SystemUtil.sleepForever();
    }


    public static class T1 implements Runnable {
        private CountDownLatch latch;
        private TimingManager tm;
        private String key;

        public T1(String key, TimingManager manager, CountDownLatch latch) {
            this.key = key;
            this.tm = manager;
            this.latch = latch;
        }

        public void run() {
            long id = Thread.currentThread().getId();
            try {
                SystemUtil.sleep(4000);
                latch.countDown();
                latch.await();
                System.out.println(key+"thread start run " + id);
                RetCode res = tm.reExecDynamicTask(3L);
                System.out.println(key+"res:" + res);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }

        }
    }

    @Test
    public void testList(){
        List l = timingManager.getTaskList("Y","Y");
        System.out.println("res:"+l.size()+","+ JSONFastJsonUtil.objectToJson(l));

        l = timingManager.getTaskList("Y","N");
        System.out.println("res:"+l.size()+","+ JSONFastJsonUtil.objectToJson(l));

    }


    @Test
    public void testUpdateCron(){
        timingManager.init();

        TimingConstant.NOT_ALIVE_INTERVAL = 5L;


        RetCode res = timingManager.execCronTask(1L);
        System.out.println("exe cron eres:"+res);

        SystemUtil.sleep(28*1000);

        String newExpression = "*/5 * * * * ?";
        RetCode ures = timingManager.updateCronTask(1L,newExpression);

        System.out.println("ures:"+JSONFastJsonUtil.objectToJson(ures));

        SystemUtil.sleepForever();

    }


    @Before
    public void before() throws Exception {
    }

    @After
    public void after() throws Exception {
    }

    @BeforeClass
    public static void beforeClass() throws Exception {

    }

    @Rule
    public final ExpectedException expectedException = ExpectedException.none();
} 
