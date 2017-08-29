/*
You are given two linked lists representing two non-negative numbers.
The most significant digit comes first and each of their nodes contain a single digit. Add the two numbers and return it as a linked list.

You may assume the two numbers do not contain any leading zero, except the number 0 itself.

Follow up:
What if you cannot modify the input lists? In other words, reversing the lists is not allowed.

Example:
Input: (7 -> 2 -> 4 -> 3) + (5 -> 6 -> 4)
Output: 7 -> 8 -> 0 -> 7

idea:
Sum of Two Integers
Add Two Numbers

similar to first Add Two Numbers,
the only difference is that this one's input is reversed, so use stack
*/
class ListNode {
    int val;
    ListNode next;
    ListNode(int x) { val = x; }
}

public class AddTwoNumbers {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        Stack<Integer> s1 = new Stack<Integer>();
        Stack<Integer> s2 = new Stack<Integer>();
        while (l1 != null) {
            s1.push(l1.val);
            l1 = l1.next;
        }
        while (l2 != null) {
            s2.push(l2.val);
            l2 = l2.next;
        }

        int sum = 0;
        int carry = 0;
        ListNode ret = new ListNode(0);

        while (!s1.empty() || !s2.empty()) {
            sum = carry + (s1.empty() ? 0 : s1.pop()) + (s2.empty() ? 0 : s2.pop());
            carry = sum / 10;
            ret.val = sum % 10;
            ListNode newHead = new ListNode(carry);
            newHead.next = ret;
            ret = newHead;
        }
        
        return ret.val == 0 ? ret.next : ret;
    }
}