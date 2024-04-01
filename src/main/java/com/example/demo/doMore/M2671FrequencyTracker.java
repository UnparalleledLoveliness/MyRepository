package com.example.demo.doMore;

public class M2671FrequencyTracker {
  //无聊，水题
  int frequency[];
  int countTag[];

  public void FrequencyTracker() {
    frequency = new int[10001];
    countTag = new int[10001];
  }

  public void add(int number) {
    if (countTag[frequency[number]] > 0) {
      countTag[frequency[number]]--;
    }
    int count = ++frequency[number];
    countTag[count] += 1;
  }

  public void deleteOne(int number) {
    int count = frequency[number];
    if (countTag[frequency[number]] > 0) {
      countTag[frequency[number]]--;
    }
    if (count > 0) {
      frequency[number]--;
    } else {
      frequency[number] = 0;
    }
    countTag[frequency[number]]++;
  }

  public boolean hasFrequency(int frequency) {
    return countTag[frequency] >= 1;
  }

  public static void main(String[] args) {
    M2671FrequencyTracker solution = new M2671FrequencyTracker();
    solution.FrequencyTracker();
    solution.add(5);
    solution.add(5);
    System.out.println(solution.hasFrequency(1));
    System.out.println(solution.hasFrequency(2));
    solution.add(6);
    solution.add(5);
    solution.add(1);
  }
}
