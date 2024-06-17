package com.example.demo.doMore;

public class H25 {
  /*
  给你链表的头节点 head ，每 k 个节点一组进行翻转，请你返回修改后的链表。
k 是一个正整数，它的值小于或等于链表的长度。如果节点总数不是 k 的整数倍，那么请将最后剩余的节点保持原有顺序。
你不能只是单纯的改变节点内部的值，而是需要实际进行节点交换。
   */
  public ListNode reverseKGroup(ListNode head, int k) {
    ListNode dummy = new ListNode(0);       // 此处只是设置一个哨兵节点
    dummy.next = head;      // 哨兵节点的下一个指向首节点

    ListNode pre = dummy;       // 上一段的最后一个节点 节点初始化
    ListNode end = dummy;       // 本段最后一个节点

    while (end.next != null) {
      // 此处是为了找到其中的 k 个子节点
      for(int i = 0 ; i < k && end != null; i++){
        end = end.next;
      }

      if(end == null){        // 如果直接到头了，那就说明没有满足 k 个
        break;
      }

      ListNode start = pre.next;           // 此处是为记录原始未反转段的起始节点
      ListNode nextStart = end.next;       // 记录下一个阶段  起始点

      end.next = null;                // 此处是为了进行后面的反转操作，断开此处链接,让后面反转操作知道截断点在哪里
      pre.next = reverse(start);      // 反转操作

      start.next = nextStart;         // 反转之后，start节点实际是已经最后一个节点了，为了和后面的划分段链接，让他的下一个节点连接上下一段的起始点即可
      pre = start;                    // pre再次来到下一段的上一个节点，也就是本段的结尾点
      end = pre;                      // 结束点，准备开始下一段的循环找 k 长度的段操作

    }

    return dummy.next;           // 返回最开始的哨兵
  }

    private ListNode reverse(ListNode head) {
        ListNode pre = null;
        ListNode cur = head;
        while (cur != null) {
        ListNode next = cur.next;
        cur.next = pre;
        pre = cur;
        cur = next;
        }
        return pre;
    }

  public static void main(String[] args) {
    ListNode n1 = new ListNode(1);
    ListNode n2 = new ListNode(2);
    ListNode n3 = new ListNode(3);
    ListNode n4 = new ListNode(4);
    ListNode n5 = new ListNode(5);
    n1.next = n2;
    n2.next = n3;
    n3.next = n4;
    n4.next = n5;
    H25 h25 = new H25();
    ListNode listNode = h25.reverseKGroup(n1, 2);
  }
}
