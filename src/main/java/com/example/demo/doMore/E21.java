package com.example.demo.doMore;

/**
 * 合并两个有序链表
 * 将两个升序链表合并为一个新的 升序 链表并返回。新链表是通过拼接给定的两个链表的所有节点组成的。
 */
public class E21 {
  public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
    if (list1 == null) {
      return list2;
    }

    if (list2 == null) {
      return list1;
    }
    ListNode head;
    ListNode other;
    if (list1.val > list2.val) {
      head = list2;
      other = list1;
    } else {
      head = list1;
      other = list2;
    }

    ListNode result = head;

    while (head.next != null) {
      if (head.next.val > other.val) {
        ListNode next = head.next;
        head.next = other;
        other = next;
      }
      head = head.next;
    }

    head.next = other;
    return result;
  }

  public static void main(String[] args) {
    ListNode n1 = new ListNode(1);
    ListNode n2 = new ListNode(2);
    ListNode n4 = new ListNode(4);
    ListNode n11 = new ListNode(1);
    ListNode n33 = new ListNode(3);
    ListNode n44 = new ListNode(4);
    n1.next = n2;
    n2.next = n4;
    n11.next = n33;
    n33.next = n44;
    E21 e21 = new E21();
    ListNode listNode = e21.mergeTwoLists(n1, n11);
    System.out.println(1);
  }
}
