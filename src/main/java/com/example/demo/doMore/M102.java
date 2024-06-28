package com.example.demo.doMore;

import java.util.Collections;
import java.util.LinkedList;
import java.util.List;

/**
 * 给你二叉树的根节点 root ，返回其节点值的层序遍历 。 （即逐层地，从左到右访问所有节点）。
 */
public class M102 {
  public List<List<Integer>> levelOrder(TreeNode root) {
    if(root== null) {
      return Collections.emptyList();
    }
    List<List<Integer>> result = new LinkedList<>();
    getNodes(Collections.singletonList(root), result);
    return result;
  }

  private void getNodes(List<TreeNode> roots, List<List<Integer>> result) {
    if (roots.isEmpty()) {
      return;
    }

    List<Integer> node = new LinkedList<>();
    List<TreeNode> nextLayerNode = new LinkedList<>();
    for (TreeNode root : roots) {
      if (root == null) {
        continue;
      }
      node.add(root.val);
      if (root.left != null) {
        nextLayerNode.add(root.left);
      }

      if (root.right != null) {
        nextLayerNode.add(root.right);
      }
    }

    result.add(node);
    getNodes(nextLayerNode, result);
  }

  public static void main(String[] args) {
    //[3,9,20,null,null,15,7]
    TreeNode n3 = new TreeNode(3);
    TreeNode n9 = new TreeNode(9);
    TreeNode n20 = new TreeNode(20);
    TreeNode n15 = new TreeNode(15);
    TreeNode n7 = new TreeNode(7);
    n3.left = n9;
    n3.right = n20;
    n20.left = n15;
    n20.right = n7;
    M102 m102 = new M102();
    List<List<Integer>> lists = m102.levelOrder(n3);
  }
}
