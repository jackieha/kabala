package contest.c104;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

/**
 * @author shuaifeng.gao
 * @since 2018-11-11 20:24
 **/
public class CountDownLatchDemo {

    public static long time(Executor executor, int concurrency, final Runnable action) throws InterruptedException {
        final CountDownLatch ready = new CountDownLatch(concurrency);
        final CountDownLatch start = new CountDownLatch(1);
        final CountDownLatch done = new CountDownLatch(concurrency);
        for (int i = 0; i < concurrency; i++) {
            executor.execute(new Runnable() {
                @Override
                public void run() {
                    // 告诉计时器，当前线程ready
                    ready.countDown();
                    try {
                        // 等待所有线程ready后的开始信号
                        start.await();
                        action.run();
                    } catch (InterruptedException e) {
                        Thread.currentThread().interrupt();
                    }finally {
                        // 告诉计时器，当前线程执行任务完成
                        done.countDown();
                    }
                }
            });
        }
        // 等待所有线程ready。每个线程ready都会减1，当所有线程ready后计数为0，会唤醒当前线程
        ready.await();
        long startNanos = System.nanoTime();
        // 释放开始信号！所有线程都开始执行任务
        start.countDown();
        // 等待所有线程done.
        done.await();
        return System.nanoTime() - startNanos;
    }

    public static void main(String[] args) throws InterruptedException {
        Executor e = Executors.newCachedThreadPool();
        Runnable r = new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 100; i ++)
                System.out.println(Thread.currentThread().getName() + i);
            }
        };
        System.out.println(time(e, 5, r));
    }
}
