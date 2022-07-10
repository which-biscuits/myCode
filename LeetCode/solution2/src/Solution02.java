/**
 * @author 11412
 */
public class Solution02 {
    /**
        给你两个 非空 的链表，表示两个非负的整数。它们每位数字都是按照 逆序 的方式存储的，并且每个节点只能存储 一位 数字。
        请你将两个数相加，并以相同形式返回一个表示和的链表。
        你可以假设除了数字 0 之外，这两个数都不会以 0 开头。
     */
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        int up = 0;
        int answer;
        ListNode result = l1;
        ListNode last = l1;
        while (l1 != null && l2 != null) {
            answer = l1.val + l2.val + up;
            up = answer / 10;
            l1.val = answer % 10;
            last = l1;
            l1 = l1.next;
            l2 = l2.next;
        }
        if (l2 != null && up == 0) {
            last.next = l2;
            return result;
        } else if (l2 != null) {
            last.next = l2;
            do {
                answer = l2.val + up;
                up = answer / 10;
                l2.val = answer % 10;
                last = l2;
                l2 = l2.next;
            } while (l2 != null);
        }
        if (l1 != null && up != 0) {
            do {
                answer = l1.val + up;
                up = answer / 10;
                l1.val = answer % 10;
                last = l1;
                l1 = l1.next;
            } while (l1 != null);
        }
        if (up != 0) {
            last.next = new ListNode(up);
        }
        return result;
    }

    public static void main(String[] args) {
        Solution02 solution02 = new Solution02();
        ListNode l1 = new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9, new ListNode(9)))));
        ListNode l2 = new ListNode(9, new ListNode(9, new ListNode(9)));
        ListNode l3 = solution02.addTwoNumbers(l1, l2);
        do {
            System.out.println(l3.val);
            l3 = l3.next;
        } while (l3 != null);
    }
}
class ListNode {
    int val;
    ListNode next;
    ListNode() {}
    ListNode(int val) { this.val = val; }
    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
}
