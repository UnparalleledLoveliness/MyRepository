package com.example.demo.doMore;

import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 给定一个不含重复数字的数组 nums ，返回其 所有可能的全排列 。你可以 按任意顺序 返回答案。
 */
//todo
public class M46 {
  public List<List<Integer>> permute(int[] nums) {
    List<List<Integer>> result = new LinkedList<>();
    //nums转为list
    List<Integer> list = new ArrayList<>();
    for (int num : nums) {
      list.add(num);
    }
    dfs(list, result, nums.length, 0);
    return result;
  }

  void dfs(List<Integer> list, List<List<Integer>> res, int length, int cur) {
    if (cur == length) {
      res.add(new ArrayList<>(list));
      return;
    }

    for (int i = cur; i < length; i++) {
      Collections.swap(list, cur, i);
      dfs(list, res, length, cur + 1);
      Collections.swap(list, cur, i);
    }
  }

  public static void main(String[] args) {
    M46 m46 = new M46();
    int[] nums = new int[] {1, 2, 3, 4};
    System.out.println(m46.permute(nums));
  }
}
