package contest.c104;

/**
 * @author shuaifeng.gao
 * @since 2018-11-04 20:05
 **/
public class Test {

    public static void main(String[] args){
        long s = System.currentTimeMillis();
        long sum = 0L;
        for (long i = 0; i < Integer.MAX_VALUE; i++){
            sum += i;
        }
        System.out.print(sum);
        System.out.println("spend time:" + ( System.currentTimeMillis() - s));
    }
}
