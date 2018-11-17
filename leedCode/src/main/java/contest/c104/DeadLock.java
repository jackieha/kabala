package contest.c104;

import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author shuaifeng.gao
 * @since 2018-11-11 18:34
 **/
public class DeadLock {

    private int l = 0;

    public synchronized void add(){
        System.out.println(l++);
    }

    public synchronized void sub(){
        if (l == 0){
            ExecutorService e = Executors.newSingleThreadExecutor();
            try {
                e.submit(new Runnable() {
                    @Override
                    public void run() {
                        add();
                    }
                }).get();
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            } catch (ExecutionException e1) {
                e1.printStackTrace();
            }
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e1) {
                e1.printStackTrace();
            }
        }
    }

    public static void main(String[] args) {
        DeadLock d = new DeadLock();
        d.sub();
    }
}

