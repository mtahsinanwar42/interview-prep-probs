package org.example.stackqueue;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class StackQueueProbs {

    private Queue<Integer> q1 = new LinkedList<>();
    private Queue<Integer> q2 = new LinkedList<>();
    private Stack<Integer> stack1 = new Stack<>();
    private Stack<Integer> stack2 = new Stack<>();

    // Stack Using Queue
    public void push(int item) {
        q1.add(item);
    }

    public int pop() {
        int item = -1;

        while (!q1.isEmpty()) {
            item = q1.poll();

            if (!q1.isEmpty()) {
                q2.add(item);
            }
        }

        // swap q1, q2
        Queue tmp = q1;
        q1 = q2;
        q2 = tmp;

        return item;
    }

    // Queue Using Stack
    public void enqueue(int item) {
        stack1.add(item);
    }

    public int dequeue() {
        if (stack2.isEmpty()) {
            while (!stack1.isEmpty()) {
                stack2.push(stack1.pop());
            }
        }

        return stack2.pop();
    }
}
