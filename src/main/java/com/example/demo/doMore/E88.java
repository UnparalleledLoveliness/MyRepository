package com.example.demo.doMore;

import java.util.LinkedList;
import java.util.List;

/**
 * 合并两个有序数组
 * 给你两个按 非递减顺序 排列的整数数组 nums1 和 nums2，另有两个整数 m 和 n ，分别表示 nums1 和 nums2 中的元素数目。
 * 请你 合并 nums2 到 nums1 中，使合并后的数组同样按 非递减顺序 排列。
 * 注意：最终，合并后数组不应由函数返回，而是存储在数组 nums1 中。为了应对这种情况，nums1 的初始长度为 m + n，其中前 m 个元素表示应合并的元素，后 n 个元素为 0 ，应忽略。nums2 的长度为 n 。
 */
public class E88 {
  public void merge(int[] nums1, int m, int[] nums2, int n) {
    List<Integer> result = new LinkedList<>();
    int i = 0;
    int j = 0;
    while (i < m && j < n) {
      if (nums1[i] < nums2[j]) {
        result.add(nums1[i]);
        i++;
      } else {
        result.add(nums2[j]);
        j++;
      }
    }

    if (i == m) {
      while (j < n) {
        result.add(nums2[j]);
        j++;
      }
    }

    if (j == n) {
      while (i < m) {
        result.add(nums1[i]);
        i++;
      }
    }

    int k = 0;
    for (Integer integer : result) {
      nums1[k] = integer;
      k++;
    }
  }

  public static void main(String[] args) {
    E88 e88=new E88();
  }
}
