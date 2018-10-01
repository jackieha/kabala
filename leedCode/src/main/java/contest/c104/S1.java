package main.java.contest.c104;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.TreeSet;

/**
 * 给定一副牌，每张牌上都写着一个整数。
 *
 * 此时，你需要选定一个数字 X，使我们可以将整副牌按下述规则分成 1 组或更多组：
 *
 * 每组都有 X 张牌。
 * 组内所有的牌上都写着相同的整数。
 * 仅当你可选的 X >= 2 时返回 true。
 *
 * <h https://leetcode-cn.com/contest/weekly-contest-104/problems/x-of-a-kind-in-a-deck-of-cards/></h>
 * @author shuaifeng.gao
 * @since 2018-10-01 17:01
 **/
public class S1 {
    public boolean hasGroupsSizeX(int[] deck) {
        if (deck == null || deck.length <= 1){
            return false;
        }
        Map<Integer, Integer> sizeMap = new HashMap<>();
        for (int i : deck) {
            Integer size = 1;
            if (sizeMap.get(i) != null){
                size = sizeMap.get(i) + 1;
            }
            sizeMap.put(i, size);
        }
        List<Integer> sizeList = new ArrayList<>(sizeMap.values());
        Collections.sort(sizeList);
        Integer minSize = sizeList.get(0);
        for (Integer size : sizeList) {
            if (size.equals(1) || gcd(minSize, size) <= 1) {
                return false;
            }
        }
        return true;
    }

    private int gcd(int a, int b){
        while(a != 0 && b != 0){
            if (a > b){
                a = a % b;
            }else {
                b = b % a;
            }
        }
        return a > b ? a : b;
    }

    public static void main(String[] args) {
        int[] d = {1,1,1,1,2,2,2,2,2,2};
//        int[] d = {3,3};
        S1 s1 = new S1();
        System.out.println(s1.hasGroupsSizeX(d));
        System.out.println(s1.gcd(12, 13));
    }
}
