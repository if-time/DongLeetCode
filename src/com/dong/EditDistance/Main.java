package com.dong.EditDistance;

import java.util.Scanner;

/**
 * 给定字符串 s 和 t，将 s 转换成 t。你可以进行三种操作：插入一个字符、删除一个字符、替换一个字符。
 * 计算将 s 将转换成 t 的最小操作数。
 * 输入：word1 = "horse", word2 = "ros"
 * 输出：3
 * 解释：
 * horse -> rorse (将 'h' 替换为 'r')
 * rorse -> rose (删除 'r')
 * rose -> ros (删除 'e')
 *
 * @author dongsiyuan
 * @date 2020年9月8日
 */
public class Main {
    public static void main(String[] args) {
        Scanner in = new Scanner(System.in);
        String s = in.nextLine();
        String t = in.nextLine();
        System.out.println(minDistance(s, t));
        in.close();
    }

    public static int minDistance(String s, String t) {
        // 子问题：
        // f(i, j) = s[0..i) 和 t[0..j) 的编辑距离

        // f(0, j) = j
        // f(i, 0) = i
        // f(i, j) = f(i-1, j-1), if s[i-1] == t[j-1]
        //           max: f(i-1, j) + 1
        //                f(i, j-1) + 1
        //                f(i-1, j-1) + 1, if s[i-1] != t[j-1]
        int n = s.length();
        int m = t.length();
        int[][] dp = new int[n + 1][m + 1];
        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= m; j++) {
                if (i == 0) {
                    dp[i][j] = j;
                } else if (j == 0) {
                    dp[i][j] = i;
                } else {
                    if (s.charAt(i - 1) == t.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1];
                    } else {
                        dp[i][j] = 1 + min(dp[i - 1][j],  // 删除操作
                                dp[i][j - 1],  // 插入操作
                                dp[i - 1][j - 1] // 替换操作
                        );
                    }
                }
            }
        }
        return dp[n][m];
    }

    private static int min(int i, int i1, int i2) {
        return Math.min(i, Math.min(i1, i2));
    }
}
