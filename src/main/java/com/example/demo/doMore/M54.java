package com.example.demo.doMore;

import java.util.LinkedList;
import java.util.List;

public class M54 {
  public List<Integer> spiralOrder(int[][] matrix) {
    List<Integer> result = new LinkedList<>();
    int length = matrix.length;
    int width = matrix[0].length;
    getCurNum(0, 0, length, width, matrix, result, 0);
    return result;
  }

  private void getCurNum(int curX, int curY, int length, int width, int[][] matrix, List<Integer> result, int way) {
    if (curX == -1 || curX == length || curY == -1 || curY == width || matrix[curX][curY] == -1) {
      return;
    }

    result.add(matrix[curX][curY]);
    matrix[curX][curY] = -1;

    int nextX = curX;
    int nextY = curY;
    if (way == 0) {
      nextY += 1;
    } else if (way == 1) {
      nextX += 1;
    } else if (way == 2) {
      nextY -= 1;
    } else if (way == 3) {
      nextX -= 1;
    }

    if (nextX == -1 || nextX == length || nextY == -1 || nextY == width || matrix[nextX][nextY] == -1) {
      way++;
      if (way == 4) {
        way = 0;
      }

      if (way == 0) {
        curY += 1;
      } else if (way == 1) {
        curX += 1;
      } else if (way == 2) {
        curY -= 1;
      } else if (way == 3) {
        curX -= 1;
      }
      getCurNum(curX, curY, length, width, matrix, result, way);
    } else {
      getCurNum(nextX, nextY, length, width, matrix, result, way);
    }
  }

  public static void main(String[] args) {
    M54 m54 = new M54();
    int[][] matrix = new int[][] {{1}};
    System.out.println(m54.spiralOrder(matrix));
  }
}
