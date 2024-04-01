package com.example.demo.doMore;

public class E2908 {
    // 2908. 最小三元组
    /*
    给定一个长度为 n 的整数数组 nums ，找到最小的非空连续子数组的和，该子数组中至少包含三个元素。
    如果不存在满足条件的子数组，返回 -1 。
     */
    //解法:双指针,从左到右遍历,找到最小值,然后再找到最小值,然后计算和,取最小值
  public int minimumSum(int[] nums) {
    int minPre = nums[0];
    int result = 99999;
    for (int i = 1; i < nums.length - 1; i++) {
      if (nums[i] < minPre) {
        minPre = nums[i];
        continue;
      }

      int minNex = nums[i + 1];
      for (int j = i+1; j < nums.length; j++) {
        minNex = Math.min(minNex, nums[j]);
      }

      if (nums[i] > minPre && nums[i] > minNex) {
        result = Math.min(result, nums[i] + minPre + minNex);
      }
    }

    if (result == 99999) {
      return -1;
    }
    return result;
  }

  public static void main(String[] args) {
    E2908 e2908 = new E2908();
    int[] nums={5,4,8,7,10,2};
    e2908.minimumSum(nums);
  }
}
