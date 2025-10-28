package org.example.stackqueue;

public class Queue {

    private int[] queue;
    private int front;
    private int rear;
    private int size;

    public Queue(int capacity) {
        queue = new int[capacity];
        front = rear = size = 0;
    }

    public void enqueue(int item) {
        if (size == queue.length) {
            throw new IllegalStateException("Overflow");
        }

        queue[rear] = item;
        rear = (rear + 1) % queue.length;
        size++;
    }

    public int dequeue() {
        if (size == 0) {
            throw new IllegalStateException("Underflow");
        }

        int item = queue[front];
        front = (front + 1) % queue.length;
        size--;

        return item;
    }

    public int peek() {
        return queue[front];
    }

    public int size() {
        return size;
    }

    public boolean isEmpty() {
        return size == 0;
    }
}
