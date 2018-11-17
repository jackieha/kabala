package contest.c104;

import java.util.concurrent.TimeUnit;

/**
 * @author shuaifeng.gao
 * @since 2018-11-11 17:12
 **/
public class StopThread {

    // 使用volatile修饰用于通信的变量
    private static volatile boolean stopFlag;
    public static void main(String[] args) throws InterruptedException {
        new Thread(new Runnable() {
            @Override
            public void run() {
                int i = 0;
                while (!stopFlag){
                    System.out.println(i++);
                }
            }
        }).start();
        TimeUnit.SECONDS.sleep(1);
        stopFlag = true;
    }
}
