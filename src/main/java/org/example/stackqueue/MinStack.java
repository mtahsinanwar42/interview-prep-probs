package org.example.stackqueue;

public class MinStack {

    private int[] stack;
    private int top;
    private int min;

    public MinStack(int capacity) {
        stack = new int[capacity];
        top = 0;
        min = Integer.MAX_VALUE;
    }

    public void push(int item) {
        if (top == stack.length) {
            throw new IllegalStateException("Overflow");
        }

        if (top == 0) {
            min = item;
            stack[top++] = item;
        } else {
            if (min > item) {
                stack[top++] = min;
                min = item;
            }

            stack[top++] = item;
        }
    }

    public int pop() {
        if (top == 0) {
            throw new IllegalStateException("Underflow");
        }

        int item = stack[--top];

        if (item == min && top > 0) {
            min = stack[--top];
        }

        return item;
    }

    public int peek() {
        if (top == 0) {
            throw new IllegalStateException("Underflow");
        }

        return stack[top - 1];
    }

    public int min() {
        if (top == 0) {
            throw new IllegalStateException("Underflow");
        }

        return min;
    }

    public int size() {
        return top - 1;
    }

    public boolean isEmpty() {
        return top == 0;
    }
}
