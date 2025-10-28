package org.example.linkedlist;

import java.util.HashMap;
import java.util.Map;

public class LinkedListProbs {

    public static void insertAtHead(int num, LinkedList head) {
        LinkedList tmp = new LinkedList();
        tmp.value = num;
        tmp.next = null;

        if (head == null) {
            head = tmp;
            return;
        }

        tmp.next = head;
        head = tmp;
        /* works for stack push too*/
    }

    public static void insertAtEnd(int num, LinkedList head) {
        LinkedList tmp = new LinkedList();
        tmp.value = num;
        tmp.next = null;

        if (head == null) {
            head = tmp;
            return;
        }

        LinkedList current = head;

        while (current.next != null) {
            current = current.next;
        }

        current.next = tmp;
    }

    public static void insertAfterN(int n, LinkedList head) {
        LinkedList tmp = new LinkedList();
        tmp.value = n;
        tmp.next = null;

        if (head == null) {
            head = tmp;
            return;
        }

        LinkedList current = head;

        while (current.value != n) {
            current = current.next;
        }

        tmp.next = current.next;
        current.next = tmp;
    }

    public static void delete(int n, LinkedList head) {
        if (n == head.value) {
            head = head.next;
            return;
        }

        LinkedList current = head;
        LinkedList parent = null;

        while (current.value != n) {
            parent = current;
            current = current.next;
        }

        parent.next = current.next;
    }

    public static void deleteNth(int n, LinkedList head) {
        if (n == 1) {
            head = head.next;
            return;
        }

        LinkedList current = head;
        LinkedList parent = null;
        int count = 1;

        while (count != n) {
            parent = current;
            current = current.next;
            count++;
        }

        parent.next = current.next;
    }

    public static void deleteNthFromEnd(LinkedList head, int n) {
        int size = getLength(head);

        if (size == 1) {
            head = head.next;
            return;
        }

        LinkedList current = head;
        LinkedList parent = null;
        int count = 0;

        while (current != null) {
            parent = current;
            current = current.next;
            count++;

            if (size - n == count) {
                parent.next = current.next;
                break;
            }
        }
    }

    public static void deleteDuplicates(LinkedList head) {
        if (head == null || head.next == null) {
            return;
        }

        LinkedList current = head;
        LinkedList parent = null;
        Map<Integer, Integer> map = new HashMap<>();

        while (current != null) {
            parent = current;
            int count = map.getOrDefault(current.value, 0);

            if (count > 1) {
                parent.next = current.next;
            }

            map.put(current.value, count + 1);
            current = current.next;
        }
    }

    public static void deleteWithoutVar(LinkedList current) {
        current.value = current.next.value;
        current.next = current.next.next;
    }

    public static void reverseList(LinkedList head) {
        LinkedList current = head, nxt = null, parent = null;
        String s = "";

        while (current != null) {
            nxt = current.next;
            current.next = parent;
            parent = current;
            current = nxt;
            s = parent.value + s;
        }

        head = parent;
    }

    public static void reverseFromMtoN(LinkedList head, int m, int n) {
        LinkedList current = head, nxt = null, currentM = head, parentM = null, parent = null;
        int count = 1;

        while (count <= m) {
            parentM = currentM;
            currentM = currentM.next;
            count++;
        }

        while (count <= n) {
            nxt = current.next;
            current.next = parent;
            parent = current;
            current = nxt;
            count++;
        }
        LinkedList tmp = current;
        current = null;

        parentM.next = parent;
        currentM.next = tmp;
    }

    public static int getLength(LinkedList head) {
        if (head == null) {
            return 0;
        }

        LinkedList current = head;
        int count = 0;

        while (current != null) {
            current = current.next;
            count++;
        }

        return count;
    }

    public static boolean cycleDetection(LinkedList head) {
        if (head == null || head.next == null) {
            return false;
        }

        LinkedList tortoise = head, hare = head;

        while (tortoise != null && hare != null && hare.next != null) {
            tortoise = tortoise.next;
            hare = hare.next.next;

            if (tortoise == hare) {
                return true;

                // starting point
//                tortoise = head;
//
//                while (hare != tortoise) {
//                    hare = hare.next;
//                    tortoise = tortoise.next;
//                }
//
//                return tortoise;
            }
        }

        return false;
    }

    public LinkedList addTwoNumbers(LinkedList head1, LinkedList head2) {
        LinkedList p1 = head1, p2 = head2, newHead = new LinkedList();
        LinkedList p3 = newHead;
        int carry = 0;

        while (p1 != null || p2 != null) {
            if (p1 != null) {
                carry += p1.value;
                p1 = p1.next;
            }

            if (p2 != null) {
                carry += p2.value;
                p2 = p2.next;
            }

            LinkedList temp = new LinkedList();
            temp.value = carry % 10;
            temp.next = null;
            p3.next = temp;

            carry /= 10;
            p3 = p3.next;
        }

        if (carry == 1) {
            LinkedList temp = new LinkedList();
            temp.value = 1;
            temp.next = null;
            p3.next = temp;
        }

        return newHead.next;
    }

    public static int getIntersectNode(LinkedList head1, LinkedList head2) {
        int c1 = getLength(head1);
        int c2 = getLength(head2);

        if (c1 > c2) {
            int d = c1 - c2;
            return getIntersection(head1, head2, d);
        } else {
            int d = c2 - c1;
            return getIntersection(head2, head1, d);
        }
    }

    private static int getIntersection(LinkedList head1, LinkedList head2, int d) {
        LinkedList current1 = head1, current2 = head2;

        for (int i = 0; i < d; i++) {
            if (current1 == null) {
                return -1;
            }

            current1 = current1.next;
        }

        while (current1 != null && current2 != null) {
            if (current1.value == current2.value) {
                return current1.value;
            }

            current1 = current1.next;
            current2 = current2.next;
        }

        return -1;
    }

    public static LinkedList swapNodes(LinkedList head) {
        LinkedList dummy = new LinkedList(-1);
        dummy.next = head;

        LinkedList parent = dummy, current = head;

        while (current != null) {
            LinkedList first = current;
            LinkedList second = current.next;

            // par -> first -> second -> next
            parent.next = second;
            first.next = second.next;
            second.next = first;

            parent = first;
            current = first.next;
        }

        return dummy.next;
    }

    public static LinkedList sortedMerge(LinkedList head1, LinkedList head2) {
        if (head1 == null) {
            return head2;
        }

        if (head2 == null) {
            return head1;
        }

        boolean ascending = true;

        if (head1.next != null) {
            ascending = head1.value <= head1.next.value;
        }

        if (head2.next != null) {
            ascending = head2.value <= head2.next.value;
        }

        LinkedList head;

        if (compare(head1.value, head2.value, ascending) <= 0) {
            head = head1;
            head1 = head1.next;
        } else {
            head = head2;
            head2 = head2.next;
        }

        LinkedList tail = head;

        while (head1 != null && head2 != null) {
            if (compare(head1.value, head2.value, ascending) <= 0) {
                tail.next = head1;
                head1 = head1.next;
            } else {
                tail.next = head2;
                head2 = head2.next;
            }

            tail = tail.next;
        }

        tail.next = head1 != null ? head1 : head2;

        return head;
    }


    public static int compare(int x, int y, boolean ascending) {
        return ascending ? Integer.compare(x, y) : Integer.compare(y, x);
    }

    public static void main(String[] args) {
    }
}
