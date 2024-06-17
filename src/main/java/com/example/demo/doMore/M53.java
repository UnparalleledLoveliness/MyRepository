package com.example.demo.doMore;

import static org.apache.commons.lang3.math.NumberUtils.max;

/**
 * 给你一个整数数组 nums ，请你找出一个具有最大和的连续子数组（子数组最少包含一个元素），返回其最大和。
 * 子数组是数组中的一个连续部分。
 */

// 解法: 动态规划
public class M53 {
  public int maxSubArray(int[] nums) {
    int[] dp = new int[100000];
    dp[0] = nums[0];
    int result = dp[0];
    for (int i = 1; i < nums.length; i++) {
      dp[i] = max(dp[i - 1] + nums[i],nums[i]);
      result = Math.max(result, dp[i]);
    }

    return result;
  }

  public static void main(String[] args) {
    M53 m53 = new M53();
    int[] nums = new int[] {-2, 1, -3, 4, -1, 2, 1, -5, 4};
    System.out.println(m53.maxSubArray(nums));
  }
}
