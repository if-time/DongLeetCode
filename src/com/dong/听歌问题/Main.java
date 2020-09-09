package com.dong.听歌问题;

import java.util.*;

public class Main {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n = sc.nextInt();

        int[] nLength = new int[n + 1];
        for (int i = 0; i < n; i++) {
            nLength[i] = sc.nextInt();
        }
        int m = sc.nextInt();

        Arrays.sort(nLength);

        int res = 0;
        for (int i = 0; i <= n; i++) {
            res += nLength[i];
        }
        System.out.println(res);
        int change = m;
        for (int i = 0; i <= n; i++) {
            change = change - nLength[i];
        }
        if (res < m) {

        } else {

        }
        System.out.println(res);
        sc.close();
    }
}
