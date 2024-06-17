package com.example.demo.doMore;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 15. 三数之和
 * 给你一个整数数组 nums ，判断是否存在三元组 [nums[i], nums[j], nums[k]] 满足 i != j、i != k 且 j != k ，同时还满足 nums[i] + nums[j] + nums[k] == 0 。请
 * <p>
 * 你返回所有和为 0 且不重复的三元组。
 */

//解法:双指针,固定一个数,然后双指针找另外两个数
public class M15 {
  public List<List<Integer>> threeSum(int[] nums) {
    Set<List<Integer>> result = new HashSet<>();
    Arrays.sort(nums);
    for (int i = 0; i < nums.length - 1; i++) {
      int l = i + 1;
      int r = nums.length - 1;
      if (nums[i] > 0) {
        continue;
      }

      while (l < r) {
        if (nums[i] + nums[l] + nums[r] == 0) {
          result.add(Arrays.asList(nums[i], nums[l], nums[r]));
          l++;
        } else if (nums[i] + nums[l] + nums[r] > 0) {
          r--;
        } else {
          l++;
        }
      }
    }

    return result.stream().toList();
  }

  public static void main(String[] args) {
    M15 m15 = new M15();
    int[] nums = new int[] {-4, -2, -2, -2, 0, 1, 2, 2, 2, 3, 3, 4, 4, 6, 6};

    System.out.println(m15.threeSum(nums));
  }
}
