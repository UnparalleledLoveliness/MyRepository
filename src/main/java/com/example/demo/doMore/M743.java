package com.example.demo.doMore;

/**
 * 网络延迟时间
 * 有 n 个网络节点，标记为 1 到 n。
 * 给你一个列表times，表示信号经过 有向 边的传递时间。times[i] = (ui, vi, wi)，其中ui是源节点，vi是目标节点，wi是一个信号从源节点传递到目标节点的时间。
 * 现在，从某个节点K 发出信号。需要多久才能使所有节点都收到信号？如果不能使所有节点收到信号，返回-1。
 */
public class M743 {
  public int networkDelayTime(int[][] times, int n, int k) {
  int [][] g = new int[n+1][n+1];
    for(int i = 1; i <= n; i++) {
      for (int j = 1; j <= n; j++) {
        g[i][j] = i == j ? 0 : Integer.MAX_VALUE/2;
      }
    }

    for (int[] time : times) {
      g[time[0]][time[1]] = time[2];
    }

    for (int i = 1; i <= n; i++) {
      for (int j = 1; j <= n; j++) {
        for (int l = 1; l <= n; l++) {
          g[j][l] = Math.min(g[j][l], g[j][i] + g[i][l]);
        }
      }
    }

    int result = 0;
    for (int i = 1; i <= n; i++) {
      if (g[k][i] == Integer.MAX_VALUE) {
        return -1;
      }
      result = Math.max(result, g[k][i]);
    }

    return result>10000?-1:result;
  }

    public static void main(String[] args) {
        M743 m743 = new M743();
      int x = m743.networkDelayTime(new int[][] {{1,2,1}}, 2, 2);
      System.out.println(x);
    }
}
