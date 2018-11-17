package contest.c104;

import java.util.Arrays;
import java.util.EmptyStackException;

/**
 * @author shuaifeng.gao
 * @since 2018-11-04 21:29
 **/
public class Stack {
    private Object[] elements;
    private int size = 0;
    private static final int DEFAULT_INITAL_CAPACITY = 16;

    public Stack(){
        elements = new Object[DEFAULT_INITAL_CAPACITY];
    }

    public void push(Object e){
        // ensure space for at least one more element
        if (elements.length == size){
            elements = Arrays.copyOf(elements, 2 * size + 1);
        }
        elements[size++] = e;
    }

    public Object pop(){
        if (size == 0){
            throw new EmptyStackException();
        }
        Object res = elements[--size];
        elements[size] = null; // 清除过期对象的引用
        return res;
    }

    @Override
    protected void finalize() throws Throwable {
        super.finalize();
    }
}
