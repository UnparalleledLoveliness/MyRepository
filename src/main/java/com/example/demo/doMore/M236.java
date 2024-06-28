package com.example.demo.doMore;

import java.util.Collections;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * 二叉树的最近公共祖先
 * 给定一个二叉树, 找到该树中两个指定节点的最近公共祖先。
 * 百度百科中最近公共祖先的定义为：“对于有根树 T 的两个节点 p、q，最近公共祖先表示为一个节点 x，满足 x 是 p、q 的祖先且 x 的深度尽可能大（一个节点也可以是它自己的祖先）。
 */

//解法
public class M236 {
  public TreeNode lowestCommonAncestor(TreeNode root, TreeNode p, TreeNode q) {
    Map<Integer, List<TreeNode>> high2TreeNodes = new HashMap<>();
    int pHigh = dfs(Collections.singletonList(root), p, high2TreeNodes, 0, -1);
    int qHigh = dfs(Collections.singletonList(root), q, high2TreeNodes, 0, -1);
    //默认p在q后面
    if (pHigh < qHigh) {
      return lowestCommonAncestor(root, q, p);
    }

    while (true) {
      if (pHigh < qHigh) {
        qHigh--;
        q = findFather(qHigh, high2TreeNodes, q);
        if (p == q) {
          return q;
        }
      } else {
        pHigh--;
        p = findFather(pHigh, high2TreeNodes, p);
        if (p == q) {
          return q;
        }
      }
    }
  }

  TreeNode findFather(int high, Map<Integer, List<TreeNode>> high2TreeNodes, TreeNode sunNode) {
    List<TreeNode> treeNodes = high2TreeNodes.get(high);
    if (treeNodes == null) {
      return null;
    }

    for (TreeNode treeNode : treeNodes) {
      if ((treeNode.left != null && treeNode.left.val == sunNode.val)
          || (treeNode.right != null && treeNode.right.val == sunNode.val)) {
        return treeNode;
      }
    }

    return null;
  }

  Integer dfs(List<TreeNode> roots, TreeNode t, Map<Integer, List<TreeNode>> map, int high, int result) {
    if (roots.isEmpty()) {
      return result;
    }

    map.put(high, roots);
    List<TreeNode> sunNodes = new LinkedList<>();
    for (TreeNode root : roots) {
      if (root.val == t.val) {
        result = high;
      }
      if (root.left != null) {
        sunNodes.add(root.left);
      }

      if (root.right != null) {
        sunNodes.add(root.right);
      }
    }

    return dfs(sunNodes, t, map, high + 1, result);
  }

  public static void main(String[] args) {
    M236 m236 = new M236();
    //[3,5,1,6,2,0,8,null,null,7,4]
    TreeNode n3 = new TreeNode(3);
    TreeNode n5 = new TreeNode(5);
    TreeNode n1 = new TreeNode(1);
    TreeNode n6 = new TreeNode(6);
    TreeNode n2 = new TreeNode(2);
    TreeNode n0 = new TreeNode(0);
    TreeNode n8 = new TreeNode(8);
    TreeNode n7 = new TreeNode(7);
    TreeNode n4 = new TreeNode(4);
    n3.left = n5;
    n3.right = n1;
    n5.left = n6;
    n5.right = n2;
    n1.left = n0;
    n1.right = n8;
    n2.left = n7;
    n2.right = n4;
    m236.lowestCommonAncestor(n3, n6, n0);
    //1,2,3,null,4
    TreeNode n1_1 = new TreeNode(1);
    TreeNode n2_1 = new TreeNode(2);
    TreeNode n3_1 = new TreeNode(3);
    TreeNode n4_1 = new TreeNode(4);
    n1_1.left = n2_1;
    n1_1.right = n3_1;
    n2_1.right = n4_1;
    TreeNode x = m236.lowestCommonAncestor(n1_1, n4_1, n3_1);
    System.out.println(x.val);
  }
}
