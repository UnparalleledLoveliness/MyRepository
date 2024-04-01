package com.example.demo.doMore;

public class M518 {
  // 518. 零钱兑换 II
        /*
            给你一个整数数组 coins 表示不同面额的硬币，另给一个整数 amount 表示总金额。
            请你计算并返回可以凑成总金额的硬币组合数。假设每一种面额的硬币有无限个。
            题目数据保证结果符合 32 位带符号整数。
        */
  //解法:动态规划,dp[i]表示凑成金额i的硬币组合数,dp[i]=dp[i]+dp[i-coin]
  public int change(int amount, int[] coins) {
    int[] dp = new int[amount + 1];
    dp[0] = 1; //初始化
    for (int coin : coins) {
      for (int i = coin; i <= amount; i++) {
        dp[i] += dp[i - coin];
      }
    }

    return dp[amount];
  }

  public static void main(String[] args) {
    M518 solution = new M518();
    int[] coins = {1, 2, 5};
    int amount = 5;
    int res = solution.change(amount, coins);
    System.out.println(res);
  }

  //疑问：为什么 coins 和 amount 的循环不可以交换
  //答：因为 dp[i] = dp[i] + dp[i - coin]，如果交换的话，dp[i] 的值会被覆盖，导致结果错误
}
