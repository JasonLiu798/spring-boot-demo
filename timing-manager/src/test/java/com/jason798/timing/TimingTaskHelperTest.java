package com.jason798.timing;

import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;
import sf.aos.timing.task.BaseTask;
import sf.aos.timing.task.FixRateTask;

import javax.annotation.Resource;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = false, transactionManager = "transactionManager")
@ContextConfiguration(locations = {"classpath:test-service.xml"})
@Transactional
public class TimingTaskHelperTest {

    @Autowired
    TimingCoreHelper timingCoreHelper;

    @Autowired
    TimingTaskHelper timingTaskHelper;

    @Test
    public void testAddFixRateCondTask() throws Exception {
        //    public FixRateTask(Long tid,TimingCoreHelper helper,ITimingTask service) {
        BaseTask t = new FixRateTask(1L, timingCoreHelper);
        timingCoreHelper.saveHistory(t);
    }

    @Test
    public void testSaveHistory() throws Exception {
        //(Long delay, Long interval,Long maxTime
        timingTaskHelper.addFixRateCondTask(1000L, 2000L, 10L);
    }

    @Test
    public void testGetTaskByKey() throws Exception {

    }

    @Test
    public void testGetTaskById() throws Exception {

    }

    @Test
    public void testStartTask() throws Exception {

    }

    @Test
    public void testCheckTaskCanProcess() throws Exception {

    }

    @Test
    public void testStartTaskPre() throws Exception {

    }


    @Test
    public void testMutex() throws Exception { 
                /* 
                try { 
                   Method method = TimingTaskHelper.getClass().getMethod("mutex", GenTask.class); 
                   method.setAccessible(true); 
                   method.invoke(<Object>, <Parameters>); 
                } catch(NoSuchMethodException e) { 
                } catch(IllegalAccessException e) { 
                } catch(InvocationTargetException e) { 
                } 
                */
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
