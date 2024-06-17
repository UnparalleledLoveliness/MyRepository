package com.example.demo.doMore;

import java.util.Arrays;

public class M2007 {
  // 2007. 从双倍数组中还原原数组
    /*
    一个整数数组 original 可以转变成一个 双倍 数组 changed ，转变方式为将 original 中每个元素 值乘以 2 加入数组中，然后将所有元素 随机打乱 。
    给你一个数组 changed ，如果 change 是 双倍 数组，那么请你返回 original数组，否则请返回空数组。original 的元素可以以 任意 顺序返回。
     */
  //解法:遍历数组,找到最大值,然后遍历数组,找到最大值的位置,然后还原数组
  public int[] findOriginalArray(int[] changed) {
    int n = changed.length;
    Arrays.sort(changed);
    int[] cnt = new int[changed[n - 1] + 1];
    for (int x : changed) {
      ++cnt[x];
    }
    int[] ans = new int[n >> 1];
    int i = 0;
    for (int x : changed) {
      if (cnt[x] == 0) {
        continue;
      }
      --cnt[x];
      int y = x * 2;
      if (y >= cnt.length || cnt[y] <= 0) {
        return new int[0];
      }
      --cnt[y];
      ans[i++] = x;
    }
    return ans;
  }

  public static void main(String[] args) {
    M2007 m2007 = new M2007();
    int[] changed = {0, 0, 0, 0,};
    System.out.println(Arrays.toString(m2007.findOriginalArray(changed)));
    System.out.println(0 % 2);
  }
}
