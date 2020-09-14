package com.dong.HouseRobber;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        int[] nums = { 1, 2, 3, 0, 4 };
        System.out.println(rob(nums));
    }

    public static int rob(int[] nums) {
        if (nums.length == 0) {
            return 0;
        }
        // 子问题：
        // f(k) = 偷 [0..k) 房间中的最大金额

        // f(0) = 0
        // f(1) = nums[0]
        // f(k) = max{ rob(k-1), nums[k-1] + rob(k-2) }
        int n = nums.length;
        int[] dp = new int[n + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        for (int i = 2; i <= n; i++) {
            // 套用子问题的递推关系
            dp[i] = Math.max(dp[i - 1], nums[i - 1] + dp[i - 2]);
        }
        return dp[n];
    }

    /**
     * 使用 back 数组 DP 数组仍然是其原始的形式，记录 f(k) 的值，即「偷前 k 间房子的最大金额」； Back 数组中的值要么是 -1，要么是
     * -2。如果 back[k] = -1，表示 dp[k] 由 dp[k-1] 计算而来； 如果 back[k] = -2，表示 dp[k] 由
     * dp[k-2] 计算而来。
     */
    public static List<Integer> robForBack(int[] nums) {
        int N = nums.length;
        int[] dp = new int[N + 1];
        int[] back = new int[N + 1];
        dp[0] = 0;
        dp[1] = nums[0];
        back[1] = -2;
        for (int k = 2; k <= N; k++) {
            // 计算 DP 数组与 back 数组
            if (dp[k - 1] >= dp[k - 2] + nums[k - 1]) {
                dp[k] = dp[k - 1];
                back[k] = -1;
            } else {
                dp[k] = dp[k - 2] + nums[k - 1];
                back[k] = -2;
            }
        }

        // 根据 back 数组得到具体偷窃方案
        List<Integer> res = new ArrayList<>();
        int k = N; // 从 dp[N] 开始向前寻找
        while (k > 0) {
            if (back[k] == -2) {
                res.add(k - 1); // 确定要偷的房子
            }
            k = k + back[k]; // 根据 back 数组移动指针 k
        }
        Collections.reverse(res); // 由于是倒序向前寻找，最后还要反转一下
        return res;
    }
}