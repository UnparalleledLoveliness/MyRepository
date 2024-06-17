package com.example.demo.doMore;

/**
 * 最长回文子串
 * 给你一个字符串 s，找到 s 中最长的回文子串
 */

// 解法: 中心扩展
public class M5 {
  public String longestPalindrome(String s) {
    String result1 = s.substring(0, 1);
    String result2 = s.substring(0, 1);
    char[] array = s.toCharArray();
    int max = 0;
    int max2 = 0;
    for (int i = 1; i < array.length; i++) {
      boolean flag = false;
      boolean flag2 = false;
      int l = i - 1;
      int r = i + 1;
      int r2 = i;
      int l2 = i - 1;
      while (l >= 0 && r < array.length && array[l] == array[r]) {
        l--;
        r++;
        flag = true;
      }
      while (l2 >= 0 && r2 < array.length && array[l2] == array[r2]) {
        l2--;
        r2++;
        flag2 = true;
      }

      if(flag){
        if(r - l - 1 > max) {
          max = r - l - 1;
          result1 = s.substring(l + 1, r);
        }
      }

      if(flag2){
        if(r2 - l2 - 1 > max2) {
          max2 = r2 - l2 - 1;
          result2 = s.substring(l2 + 1, r2);
        }
      }
    }

    return result1.length() > result2.length() ? result1 : result2;
  }

  public static void main(String[] args) {
    M5 m5 = new M5();
    System.out.println(m5.longestPalindrome("abadd"));
  }
}
