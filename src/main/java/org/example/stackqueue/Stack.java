package org.example.stackqueue;

public class Stack {

    private int[] data;
    private int top;

    public Stack(int capacity) {
        data = new int[capacity];
        this.top = 0;
    }

    public void push(int item) {
        if (top == data.length) {
            throw new IllegalStateException("Overflow");
            // call resize() for dynamic array
        }

        data[top++] = item;
    }

    public int pop() {
        if (top == 0) {
            throw new IllegalStateException("Underflow");
        }

        return data[--top];
    }

    private void resize() {
        int[] newData = new int[data.length * 2];

        for (int i = 0; i < data.length; i++) {
            newData[i] = data[i];
        }

        data = newData;
    }

    public int peek() {
        return data[top - 1];
    }

    public boolean isEmpty() {
        return top == 0;
    }

    public int getSize() {
        return top;
    }

    public int getCapacity() {
        return data.length;
    }
}
