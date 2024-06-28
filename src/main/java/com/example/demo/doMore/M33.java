package com.example.demo.doMore;

/**
 * 33. 搜索旋转排序数组
 * 在传递给函数之前，nums 在预先未知的某个下标 k（0 <= k < nums.length）上进行了 旋转，使数组变为 [nums[k], nums[k+1], ..., nums[n-1], nums[0], nums[1], ..., nums[k-1]]（下标 从 0 开始 计数）。例如， [0,1,2,4,5,6,7] 在下标 3 处经旋转后可能变为 [4,5,6,7,0,1,2] 。
 * 给你 旋转后 的数组 nums 和一个整数 target ，如果 nums 中存在这个目标值 target ，则返回它的下标，否则返回 -1 。
 * 你必须设计一个时间复杂度为 O(log n) 的算法解决此问题。
 */
public class M33 {
  public int search(int[] nums, int target) {
    for (int i = 0; i < nums.length; i++) {
      if (nums[i] == target) {
        return i;
      }

      if (nums[i] > target || ((i + 1 < nums.length) && nums[i + 1] < nums[i])) {
        break;
      }
    }

    for (int i = nums.length - 1; i >= 1; i--) {
      if (nums[i] == target) {
        return i;
      }

      if (nums[i] < target || nums[i - 1] > nums[i]) {
        break;
      }
    }

    return -1;
  }

  public static void main(String[] args) {
    M33 m33=new M33();
    m33.search(new int[]{1},1);
  }
}
