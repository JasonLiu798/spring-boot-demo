package com.jason798.timing;

import com.jason798.timing.api.ITimingTaskCond;
import org.junit.*;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(defaultRollback = false, transactionManager = "transactionManager")
@ContextConfiguration(locations = {"classpath:test-service.xml"})
@Transactional
public class TimingManagerTest {

    @Autowired
    private TimingManagerImpl timingManager;

    @Test
    public void test(){
        timingManager.init();
        ExecutorService es = Executors.newFixedThreadPool(10);
//        GenTask t = timingManager.getTaskByKey("t1");
//        System.out.println("res:"+JSONFastJsonUtil.objectToJson(t));

    }

    public static class T1 implements ITimingTaskCond {
        private String key;
        private CountDownLatch latch;
        public T1(String key, TimingManagerImpl tm, CountDownLatch latch){
            this.key = key;
            this.manager = tm;
            this.latch=latch;
        }
        private TimingManagerImpl manager;

        @Override
        public void execute() {
            long id = Thread.currentThread().getId();
            try {
                latch.await();
                System.out.println(id+" runing T1 ");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        @Override
        public boolean cond() {
            return false;
        }
    }


    /*

    @Test
    public void testInit() throws Exception {
        es = Executors.newFixedThreadPool(10);
        CountDownLatch latch = new CountDownLatch(2);
        T1 t1 = new T1("t1",timingManager,latch);
        T1 t2 = new T1("t1",timingManager,latch);
        es.submit(t1);
        es.submit(t2);

        SystemUtil.sleep(100*1000);
    }

    public static class T1 implements Runnable{
        @Override
        public void run() {
            latch.countDown();
            long id = Thread.currentThread().getId();
            try {
                latch.await();
                System.out.println(id+" start to run");
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //manager.startTaskPre(key);
        }
        private String key;
        private CountDownLatch latch;
        public T1(String key, TimingManagerImpl tm, CountDownLatch latch){
            this.key = key;
            this.manager = tm;
            this.latch=latch;
        }
        private TimingManagerImpl manager;

    }
*/
    
    @Test
    public void testRegiste() throws Exception { 
        
    }
    
    @Test
    public void testTaskExist() throws Exception { 
        
    }
    
    @Test
    public void testStartTask() throws Exception { 
        
    }
    
    @Test
    public void testGetTaskForUpdate() throws Exception { 
        
    }
    
    @Test
    public void testSetTaskProcessing() throws Exception { 
        
    }
    
    @Test
    public void testSetTaskProcessed() throws Exception { 
        
    }
    
    @Test
    public void testSetTaskProcessStatus() throws Exception { 
        
    }
    
    @Test
    public void testDelay() throws Exception { 
        
    }
    
    @Test
    public void testDecreaseTaskCount() throws Exception { 
        
    }
    
    @Test
    public void testFixRate() throws Exception { 
        
    }
    
    @Test
    public void testGetTaskStatusDto() throws Exception { 
        
    }
    
    @Test
    public void testGetTaskStatusDtoFmt() throws Exception { 
        
    }
    
    @Test
    public void testGenRandomTid() throws Exception { 
        
    }
    
    @Test
    public void testGetTaskFuture() throws Exception { 
        
    }
    
    @Test
    public void testCancleFixRateTask() throws Exception { 
        
    }
    
    @Test
    public void testFixRate4test() throws Exception { 
        
    }
    
    @Test
    public void testDelay4test() throws Exception { 
        
    }
    
    @Test
    public void testBuild4test() throws Exception { 
        
    }
    
    @Test
    public void testDestroy() throws Exception { 
        
    }
    
      
    @Test
    public void testGetTid() throws Exception { 
                /* 
                try { 
                   Method method = TimingManager.getClass().getMethod("getTid", FixRateTask.class); 
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
    public static void beforeClass() throws Exception{
        
    }
    
    @Rule
    public final ExpectedException expectedException = ExpectedException.none();
} 
