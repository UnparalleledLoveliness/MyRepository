package com.example.demo.doMore;

import java.util.HashMap;
import java.util.Map;
import java.util.Stack;

/**
 * 20. 有效的括号
 * 给定一个只包括 '('，')'，'{'，'}'，'['，']' 的字符串 s ，判断字符串是否有效。
 * 有效字符串需满足：
 * 1. 左括号必须用相同类型的右括号闭合。
 * 2. 左括号必须以正确的顺序闭合。
 * 示例 1：
 * 输入：s = "()"
 * 输出：true
 */
public class E20 {
  public boolean isValid(String s) {
    if (s.length() % 2 != 0) {
      return false;
    }

    Map<Character, Character> map = new HashMap<>();
    map.put('(', ')');
    map.put('{', '}');
    map.put('[', ']');

    char[] array = s.toCharArray();
    Stack<Character> stack = new Stack<>();
    for (char c : array) {
      if (map.containsKey(c)) {
        stack.push(c);
      } else {
        if(stack.empty()){
          return false;
        }
        Character r = map.get(stack.pop());
        if (c != r) {
          return false;
        }
      }
    }

    return stack.empty();
  }

  public static void main(String[] args) {
    E20 e = new E20();
    System.out.println(e.isValid("()[]{}"));
  }
}
