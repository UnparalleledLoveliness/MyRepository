package com.example.demo.doMore;

/**
 * 200. 岛屿数量
 * 给你一个由 '1'（陆地）和 '0'（水）组成的的二维网格，请你计算网格中岛屿的数量。
 * 岛屿总是被水包围，并且每座岛屿只能由水平方向和/或竖直方向上相邻的陆地连接形成。
 * 此外，你可以假设该网格的四条边均被水包围。
 */
public class M200 {
  public int numIslands(char[][] grid) {
    int result = 0;
    int high = grid.length;
    int width = grid[0].length;
    for (int i = 0; i < high; i++) {
      for (int j = 0; j < width; j++) {
        if (grid[i][j] == '1') {
          result++;
          changeStatus(grid, i, j, width, high);
        }
      }
    }

    return result;
  }

  public void changeStatus(char[][] grid, int i, int j, int width, int high) {
    if (j >= width || i >= high || i < 0 || j < 0) {
      return;
    }

    if (grid[i][j] == '1') {
      grid[i][j] = '0';
      changeStatus(grid, i + 1, j, width, high);
      changeStatus(grid, i, j + 1, width, high);
      changeStatus(grid, i, j - 1, width, high);
      changeStatus(grid, i - 1, j, width, high);
    }
  }

  public static void main(String[] args) {
    //[["1","1","1","1","0"],["1","1","0","1","0"],["1","1","0","0","0"],["0","0","0","0","0"]]
    char[][] grid = new char[][] {
      new char[] {'1', '1', '1', '1', '0'},
      new char[] {'1', '1', '0', '1', '0'},
      new char[] {'1', '1', '0', '0', '0'},
      new char[] {'0', '0', '0', '0', '0'}
    };
    M200 m200 = new M200();
    System.out.println(m200.numIslands(grid));
  }
}
