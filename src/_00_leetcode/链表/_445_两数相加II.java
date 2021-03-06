package _00_leetcode.链表;

import java.util.Stack;

/**
 * https://leetcode-cn.com/problems/add-two-numbers-ii/
 * 两数相加 II
 * 给你两个 非空 链表来代表两个非负整数。数字最高位位于链表开始位置。它们的每个节点只存储一位数字。
 * 将这两数相加会返回一个新的链表。
 * 你可以假设除了数字 0 之外，这两个数字都不会以零开头
 * 进阶：
 * 如果输入链表不能修改该如何处理？换句话说，你不能对列表中的节点进行翻转。
 *
 * 示例：
 * 输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
 * 输出：7 -> 8 -> 0 -> 7
 */
public class _445_两数相加II {
    /**
     * 官方解法：
     * 利用两个栈，把所有数字压入栈中，再依次取出相加。计算过程中需要注意进位的情况。
     * 复杂度：O(max(m,n))，其中 m 与 n 分别为两个链表的长度。我们需要遍历每个链表。
     * 执行用时：5 ms
     */
    public static ListNode addTwoNumbers1(ListNode l1, ListNode l2) {
        Stack<Integer> stack1 = new Stack<>();
        Stack<Integer> stack2 = new Stack<>();
        while (l1 != null) {
            stack1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            stack2.push(l2.val);
            l2 = l2.next;
        }

        ListNode node = null;
        int ten = 0;
        while (!stack1.isEmpty() || !stack2.isEmpty() || ten != 0) {
            int val1 = stack1.isEmpty() ? 0 : stack1.pop();
            int val2 = stack2.isEmpty() ? 0 : stack2.pop();
            int val = val1 + val2 + ten;
            ten = val / 10;
            val %= 10;
            ListNode newNode = new ListNode(val);
            newNode.next = node;
            node = newNode;
        }

        return node;
    }

    /**
     * 自己方案
     * 递归解法
     * 执行用时：2 ms
     */
    public static ListNode addTwoNumbers2(ListNode l1, ListNode l2) {
        int j = 0; int k = 0;
        ListNode node1 = l1; ListNode node2 = l2;
        while (node1 != null) {
            j++;
            node1 = node1.next;
        }
        while (node2 != null) {
            k++;
            node2 = node2.next;
        }

        // 短的链表前面补0
        ListNode zero = new ListNode(0);
        ListNode node = zero;
        for (int i = 0; i < Math.abs(j - k); i++) {
            node.next = new ListNode(0);
            node = node.next;
        }
        node.next = j > k ? l2 : l1;

        ListNode head = null;
        if (j > k) head = addTwoListNode(l1, zero.next);
        else head = addTwoListNode(zero.next, l2);

        if (ten != 0) { // 处理 ten 有值的情况
            ListNode next = head;
            head = new ListNode(ten);
            head.next = next;
        }

        return head;
    }

    static int ten = 0;
    private static ListNode addTwoListNode(ListNode l1, ListNode l2) {
        if (l1 == null && l2 == null) return null;

        ListNode node = addTwoListNode(l1.next, l2.next);
        int val = l1.val + l2.val + ten;
        ten = val / 10;
        val %= 10;
        ListNode newNode = new ListNode(val);
        newNode.next = node;
        node = newNode;
        return node;
    }

    public static void main(String[] args) {
        // 输入：(7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
        // 输出：7 -> 8 -> 0 -> 7
        ListNode node1 = new ListNode(3, null);
        ListNode node2 = new ListNode(4, node1);
        ListNode node3 = new ListNode(2, node2);
        ListNode node4 = new ListNode(7, node3);

        ListNode node5 =  new ListNode(4, null);
        ListNode node6  = new ListNode(6, node5);
        ListNode node7 = new ListNode(5, node6);

        ListNode head = addTwoNumbers2(node4, node7);
        ListNode.printList(head);
    }
}
