package com.example.demo.doMore;

public class H1793MaxIMumScore {
  // 1793. 好子数组的最大分数
  /*
    给你一个整数数组 nums （下标从 0 开始）和一个整数 k 。
     一个子数组 (i, j) 的 分数 定义为 min(nums[i], nums[i+1], ..., nums[j]) * (j - i + 1) 。一个 好 子数组的两个端点下标需要满足 i <= k <= j 。
    请你返回 好 子数组的最大可能 分数 。
   */
  //解法:双指针,从k开始向两边扩展,找到最小值,然后计算分数,取最大值
    public int maximumScore(int[] nums, int k) {
            int n = nums.length;
            int res = nums[k], min = nums[k], i = k, j = k;
            while (i > 0 || j < n - 1) {
                if (i == 0) {
                    j++;
                } else if (j == n - 1) {
                    i--;
                } else if (nums[i - 1] < nums[j + 1]) {
                    j++;
                } else {
                    i--;
                }
                min = Math.min(min, Math.min(nums[i], nums[j]));
                res = Math.max(res, min * (j - i + 1));
            }
            return res;
        }

  public static void main(String[] args) {
    H1793MaxIMumScore solution = new H1793MaxIMumScore();
    int[] nums = {1, 4, 3, 7, 4, 5};
    int k = 3;
    int res = solution.maximumScore(nums, k);
    System.out.println(res);
  }
}
