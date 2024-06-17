package com.example.demo.doMore;

public class E2 {
  /**
   * 给定一个字符串 s ，请你找出其中不含有重复字符的 最长子串长度。
   */

  //解法:滑动窗口
  public int lengthOfLongestSubstring(String s) {
    int len = s.length();
    char[] array = s.toCharArray();
    int left = 0;
    boolean[] isExist = new boolean[128];
    int result = 0;
    for (int right = 0; right < len; right++) {
      while (isExist[array[right]]) {
        isExist[array[left]] = false;
        left++;
      }
      isExist[array[right]] = true;
      result = Math.max(result, right - left + 1);

    }
    return result;
  }


  public static void main(String[] args) {
    String s = "pwwkew";
    E2 e2 = new E2();
    System.out.println(e2.lengthOfLongestSubstring(s));
  }
}
