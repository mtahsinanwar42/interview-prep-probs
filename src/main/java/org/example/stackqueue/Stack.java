package org.example.stackqueue;

public class Stack {

    private int[] stack;
    private int top;

    public Stack(int capacity) {
        stack = new int[capacity];
        this.top = 0;
    }

    public void push(int item) {
        if (top == stack.length) {
            throw new IllegalStateException("Overflow");
        }

        stack[top++] = item;
    }

    public int pop() {
        if (top == 0) {
            throw new IllegalStateException("Underflow");
        }

        return stack[--top];
    }

    public int peek() {
        return stack[top - 1];
    }

    public boolean isEmpty() {
        return top == 0;
    }
}
