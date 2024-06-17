package com.example.demo.doMore;

public class E2923 {
  /**
   * 2923. 找到冠军 I
   * 一场比赛中共有 n 支队伍，按从 0 到  n - 1 编号。
   * 给你一个下标从 0 开始、大小为 n * n 的二维布尔矩阵 grid 。对于满足 0 <= i, j <= n - 1 且 i != j 的所有 i, j ：如果 grid[i][j] == 1，那么 i 队比 j 队 强 ；否则，j 队比 i 队 强 。
   * 在这场比赛中，如果不存在某支强于 a 队的队伍，则认为 a 队将会是 冠军 。
   * 返回这场比赛中将会成为冠军的队伍。
   */

  //解法:遍历每一支队伍,判断是否是冠军
  public int findChampion(int[][] grid) {
    int n = grid.length;
    for (int i = 0; i < n; i++) {
      boolean isChampion = true;
      for (int j = 0; j < n; j++) {
        if (i != j && grid[i][j] == 0) {
          isChampion = false;
          break;
        }
      }
      if (isChampion) {
        return i;
      }
    }
    return -1;
  }
}
