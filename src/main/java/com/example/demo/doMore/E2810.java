package com.example.demo.doMore;

public class E2810 {
  // 2810. 故障键盘
    /*
    你的笔记本键盘存在故障，每当你在上面输入字符 'i' 时，它会反转你所写的字符串。而输入其他字符则可以正常工作。
    给你一个下标从 0 开始的字符串 s ，请你用故障键盘依次输入每个字符。
    返回最终笔记本屏幕上输出的字符串。
     */
  //解法:遍历字符串,遇到i则反转,否则正常输入
  public String finalString(String s) {
    char[] arrays = s.toCharArray();
    StringBuilder temp = new StringBuilder();
    for (char array : arrays) {
      if (array == 'i') {
        temp.reverse();
      } else {
        temp.append(array);
      }
    }
    return temp.toString();
  }

  public static void main(String[] args) {
    E2810 e2810 = new E2810();
    String s1 = e2810.finalString("string");
    System.out.println(s1);
  }
}
