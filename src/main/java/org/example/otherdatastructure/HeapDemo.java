package org.example.otherdatastructure;

import java.util.PriorityQueue;

public class HeapDemo {
    public static void main(String[] args) {
        // Create a Min-Heap (default natural ordering)
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();

        // Insert elements
        minHeap.add(5);
        System.out.println("Heap after insertions: " + minHeap);
        minHeap.add(2);
        System.out.println("Heap after insertions: " + minHeap);
        minHeap.add(8);
        System.out.println("Heap after insertions: " + minHeap);
        minHeap.add(1);
        System.out.println("Heap after insertions: " + minHeap);
        minHeap.add(3);

        System.out.println("Heap after insertions: " + minHeap);

        // Remove the minimum element (poll)
        int min1 = minHeap.poll(); // removes 1
        System.out.println("Removed min: " + min1);
        System.out.println("Heap now: " + minHeap);

        int min2 = minHeap.poll(); // removes 2
        System.out.println("Removed min: " + min2);
        System.out.println("Heap now: " + minHeap);

        // Peek the minimum without removing
        int minPeek = minHeap.peek(); // should be 3 now
        System.out.println("Current min (peek): " + minPeek);

        // Insert another element
        minHeap.add(0);
        System.out.println("Heap after adding 0: " + minHeap);

        // Remove min again
        int min3 = minHeap.poll(); // removes 0
        System.out.println("Removed min: " + min3);
        System.out.println("Heap now: " + minHeap);
    }
}
