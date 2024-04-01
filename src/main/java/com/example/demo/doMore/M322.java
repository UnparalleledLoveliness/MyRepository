package com.example.demo.doMore;

import static org.apache.commons.lang3.math.NumberUtils.min;

import java.util.Arrays;

public class M322 {
  // 322. 零钱兑换
    /*
    给定不同面额的硬币 coins 和一个总金额 amount。
    编写一个函数来计算可以凑成总金额所需的最少的硬币个数。如果没有任何一种硬币组合能组成总金额，返回-1。
    你可以认为每种硬币的数量是无限的。
    */
  //解法:动态规划,dp[i]表示凑成i元所需的最少硬币数,dp[i]=min(dp[i],dp[i-coin]+1)
  public int coinChange(int[] coins, int amount) {
    int[] dp = new int[amount + 1];
    Arrays.fill(dp, amount + 1);
    dp[0] = 0;
    for (int i = 1; i <= amount; i++) {
      for (int coin : coins) {
        if (i >= coin) {
          dp[i] = min(dp[i], dp[i - coin] + 1);
        }
      }
    }
    return dp[amount] == amount + 1 ? -1 : dp[amount];
  }

  public static void main(String[] args) {
    M322 solution = new M322();
    int[] coins = {2};
    int amount = 3;
    int res = solution.coinChange(coins, amount);
    System.out.println(res);
  }
}
