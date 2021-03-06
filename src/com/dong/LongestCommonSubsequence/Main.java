package com.dong.LongestCommonSubsequence;

/**
 * 给定两个字符串 s 和 t，返回这两个字符串的最长公共子序列的长度。 若这两个字符串没有公共子序列，则返回 0。
 * 一个字符串的子序列是指这样一个新的字符串： 它是由原字符串在不改变字符的相对顺序的情况下删除某些字符（也可以不删除任何字符）后组成的新字符串。
 * 例如，"ace" 是 "abcde" 的子序列。
 * 
 * @date 2020年9月10日
 * @author dongsiyuan
 */
public class Main {
    public static void main(String[] args) {
        Main main = new Main();
        System.out.println(main.longestCommonSubsequenceLength("afdsfasdf", "asfascec"));
        ;
    }

    public int longestCommonSubsequenceLength(String s, String t) {
        if (s.isEmpty() || t.isEmpty()) {
            return 0;
        }
        // 子问题：
        // f(i, j) = s[0..i) 和 t[0..j) 的最长公共子序列

        // f(0, *) = 0
        // f(*, 0) = 0
        // f(i, j) = f(i-1, j-1) + 1, if s[i-1] == t[j-1]
        // max{ f(i-1, j), f(i, j-1) }, otherwise

        int m = s.length();
        int n = t.length();
        int[][] dp = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                } else {
                    if (s.charAt(i - 1) == t.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                    } else {
                        dp[i][j] = Math.max(dp[i - 1][j], dp[i][j - 1]);
                    }
                }
            }
        }
        return dp[m][n];
    }

    int NONE = 0; // 回退终点
    int UPPER_LEFT = 1; // 向左上回退
    int UPPER = 2; // 向上回退
    int LEFT = 3; // 向左回退

    public String longestCommonSubsequence(String s, String t) {
        if (s.isEmpty() || t.isEmpty()) {
            return "";
        }
        int m = s.length();
        int n = t.length();
        int[][] dp = new int[m + 1][n + 1];
        // back 数组的取值只可能是 UPPER_LEFT、UPPER、LEFT 或 NONE
        int[][] back = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            for (int j = 0; j <= n; j++) {
                // 计算 DP 数组与 back 数组
                if (i == 0 || j == 0) {
                    dp[i][j] = 0;
                    back[i][j] = NONE;
                } else {
                    if (s.charAt(i - 1) == t.charAt(j - 1)) {
                        dp[i][j] = dp[i - 1][j - 1] + 1;
                        back[i][j] = UPPER_LEFT;
                    } else if (dp[i - 1][j] > dp[i][j - 1]) {
                        dp[i][j] = dp[i - 1][j];
                        back[i][j] = UPPER;
                    } else {
                        dp[i][j] = dp[i][j - 1];
                        back[i][j] = LEFT;
                    }
                }
            }
        }
        // 根据 back 数组回退，得到具体的 LCS
        int i = m;
        int j = n;
        StringBuilder sb = new StringBuilder();
        while (i > 0 && j > 0) {
            if (back[i][j] == UPPER_LEFT) {
                sb.append(s.charAt(i - 1));
                i--;
                j--;
            } else if (back[i][j] == UPPER) {
                i--;
            } else if (back[i][j] == LEFT) {
                j--;
            } else {
                break;
            }
        }
        return sb.reverse().toString();
    }
}
