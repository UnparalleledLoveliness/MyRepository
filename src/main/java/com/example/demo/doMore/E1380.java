package com.example.demo.doMore;

import java.util.LinkedList;
import java.util.List;

public class E1380 {
  // 1380. 矩阵中的幸运数
    /*
        给定一个 m * n 的矩阵，如果矩阵中存在幸运数，那么幸运数是指矩阵中的一个元素，
        该元素同时满足两个条件：
        在同一行的元素中最小
        在同一列的元素中最大
        返回矩阵中所有幸运数的列表。幸运数可以按 任意顺序 返回。
    */
  //解法:遍历矩阵,找到每行最小值和每列最大值,判断是否相等
  public List<Integer> luckyNumbers(int[][] matrix) {
    List<Integer> result = new LinkedList<>();
    for (int[] rows : matrix) {
      int min = Integer.MAX_VALUE;
      int max = Integer.MIN_VALUE;
      int col = 0;
      for (int k = 0; k < rows.length; k++) {
        if (rows[k] < min) {
          min = rows[k];
          col = k;
        }
      }

      for (int[] cols : matrix) {
        max = Math.max(max, cols[col]);
      }
      if (min == max) {
        result.add(min);
      }
    }

    return result;
  }

  public static void main(String[] args) {
    E1380 solution = new E1380();
    int[][] matrix = {{1,10,4,2}, {9,3,8,7}, {15,16,17,12}};
    List<Integer> res = solution.luckyNumbers(matrix);
    System.out.println(res);
  }
}
