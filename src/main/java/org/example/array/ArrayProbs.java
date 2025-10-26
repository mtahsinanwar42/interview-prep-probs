package org.example.array;

import java.util.*;

public class ArrayProbs {

    public static boolean binarySearch(int[] arr, int num) {
        if (arr == null || arr.length == 0) {
            return false;
        }

        int n = arr.length;
        int low = 0, high = n - 1;

        while (low <= high) {
            int mid = (low + high) / 2;

            if (arr[mid] == num) {
                return true;
            }

            if (num > arr[mid]) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }

        // return high for finding insert position

        return false;
    }

    public static int[] bubbleSort(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }

        for (int i = 0; i < arr.length - 1; i++) {
            for (int j = 0; j < arr.length - (i + 1); j++) {
                if (arr[j] > arr[j + 1]) {
                    int temp = arr[j];
                    arr[j] = arr[j + 1];
                    arr[j + 1] = temp;
                    // can also be done with a ^= b; b ^= a; a ^= b. OR a = b -a, b = b -a, a = a + b
                }
            }
        }

        return arr;
    }

    public static void mergeSort(int[] arr, int l, int r) {
        if (l < r) {
            int m = (l + r) / 2;

            mergeSort(arr, l, m);
            mergeSort(arr, m + 1, r);
            merge(arr, l, m, r);
        }
    }

    public static void merge(int[] arr, int l, int m, int r) {
        int n1 = m - l + 1, n2 = r - m;

        int[] L = new int[n1];
        int[] R = new int[n2];

        for (int i = 0; i < n1; i++) {
            L[i] = arr[l + i];
        }

        for (int i = 0; i < n2; i++) {
            R[i] = arr[m + 1 + i];
        }

        int i = 0, j = 0, k = l;

        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k++] = L[i++];
            } else if (L[i] > R[j]) {
                arr[k++] = R[j++];
            }
        }

        while (i < n1) {
            arr[k++] = L[i++];
        }

        while (j < n2) {
            arr[k++] = R[j++];
        }
    }

    public static int[] reverseArray(int[] arr) {
        if (arr == null || arr.length == 0) {
            return new int[0];
        }

        int n = arr.length;

        for (int i = 0; i < n / 2; i++) {
            // swap(arr[i], arr[n - (i + 1)]);
            int temp = arr[i];
            arr[i] = arr[n - 1 - i];
            arr[n - (i + 1)] = temp;
        }

        return arr;
    }

    public static int[] twoSumIndices(int[] arr, int target) {
        if (arr == null || arr.length < 2) return new int[0];

        Map<Integer, Integer> indexOf = new HashMap<>();
        for (int i = 0; i < arr.length; i++) {
            int need = target - arr[i];
            if (indexOf.containsKey(need)) {
                return new int[]{indexOf.get(need), i};
            }
            indexOf.put(arr[i], i);
        }

        return new int[0]; // not found
    }

    public static int[] threeSumIndices(int[] arr, int target) {
        if (arr == null || arr.length < 3) {
            return new int[0];
        }

        int n = arr.length;
        Integer[] arrIdx = new Integer[arr.length];

        for (int i = 0; i < n; i++) {
            arrIdx[i] = i;
        }

        Arrays.sort(arrIdx, Comparator.comparingInt(idx -> arr[idx]));

        for (int i = 0; i < n - 2; i++) {
            int left = i + 1, right = n - 1;

            while (left < right) {
                int sum = arr[arrIdx[i]] + arr[arrIdx[left]] + arr[arrIdx[right]];

                if (sum == target) {
                    return new int[]{arrIdx[i], arrIdx[left], arrIdx[right]};
                } else if (sum > target) {
                    right--;
                } else {
                    left++;
                }
            }
        }

        return new int[0];
    }

    public void mergeTwoSortedArray(int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null) {
            return;
        }

        int n1 = arr1.length, n2 = arr2.length;
        int[] arr = new int[arr1.length + arr2.length];
        int i = 0, j = 0, k = 0;

        while (i < n1 && j < n2) {
            if (arr1[i] < arr2[j]) {
                arr[k++] = arr1[i++];
            } else if (arr1[i] > arr2[j]) {
                arr[k++] = arr2[j++];
            } else {
                arr[k++] = arr1[i++];
                j++;
            }
        }

        while (i < n1) {
            arr[k++] = arr1[i++];
        }

        while (j < n2) {
            arr[k++] = arr2[j++];
        }
    }

    public static boolean checkSortedAndRotated(int[] arr) {
        if (arr == null || arr.length == 0) {
            return false;
        }

        int n = arr.length, minE = Integer.MAX_VALUE, minI = -1;

        for (int i = 0; i < n; i++) {
            if (minE > arr[i]) {
                minE = arr[i];
                minI = i;
            }
        }

        boolean f1 = true;

        for (int i = 0; i < minI - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                f1 = false;
            }
        }

        boolean f2 = true;

        for (int i = minI; i < n - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                f2 = false;
            }
        }

        if (minI == 0) {
            // sorted but not rotated
            return false;
        }

        if (f1 && f2 && arr[0] > arr[n - 1]) {
            return true;
        }

        return false;
    }

    public static boolean sameElementsInN(int[] arr1, int[] arr2) {
        if (arr1 == null || arr2 == null || arr1.length != arr2.length) {
            return false;
        }

        Map<Integer, Integer> map = new HashMap<>();

        for (int i = 0; i < arr1.length; i++) {
            map.put(arr1[i], map.getOrDefault(arr1[i], 0) + 1);
        }

        for (int i = 0; i < arr2.length; i++) {
            int count = map.getOrDefault(arr2[i], 0);

            if (count == 0) {
                return false;
            } else if (count == 1) {
                map.remove(arr2[i]);
            } else {
                map.put(arr2[i], count - 1);
            }
        }

        return map.isEmpty();
    }

    public static int findMostFrequent(int[] arr) {
        if (arr == null || arr.length == 0) {
            return 0;
        }

        Map<Integer, Integer> map = new HashMap<>();
        int maxElem = Integer.MIN_VALUE;
        int maxCount = Integer.MIN_VALUE;

        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], map.getOrDefault(arr[i], 0) + 1);

            if (maxCount < map.get(arr[i])) {
                maxCount = map.get(arr[i]);
                maxElem = arr[i];
            }
        }

        return maxElem;
    }

    public static void commonAndUniques2(int[] arr1, int[] arr2) {
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        int n1 = arr1.length, n2 = arr2.length, i = 0, j = 0;
        List<Integer> commonList = new ArrayList<>();
        List<Integer> uniqueList = new ArrayList<>();

        while (i < n1 && j < n2) {
            if (arr1[i] == arr2[j]) {
                commonList.add(arr1[i++]);
                j++;
            } else if (arr1[i] < arr2[j]) {
                uniqueList.add(arr1[i++]);
            } else {
                uniqueList.add(arr2[j++]);
            }
        }

        while (i < n1) {
            uniqueList.add(arr1[i++]);
        }

        while (j < n2) {
            uniqueList.add(arr2[j++]);
        }
    }

    public static void commonAndUniques3(int[] arr1, int[] arr2, int[] arr3) {
        Arrays.sort(arr1);
        Arrays.sort(arr2);
        Arrays.sort(arr3);

        int n1 = arr1.length, n2 = arr2.length, n3 = arr3.length, i = 0, j = 0, k = 0;
        List<Integer> commonList = new ArrayList<>();
        List<Integer> uniqueList = new ArrayList<>();

        while (i < n1 && j < n2 && k < n3) {
            if (arr1[i] == arr2[j] && arr2[j] == arr3[k]) {
                commonList.add(arr1[i++]);
                j++;
                k++;
            } else if (arr1[i] < arr2[j]) {
                uniqueList.add(arr1[i++]);
            } else if (arr2[j] < arr3[k]) {
                uniqueList.add(arr2[j++]);
            } else {
                uniqueList.add(arr3[k++]);
            }
        }

        while (i < n1) {
            uniqueList.add(arr1[i++]);
        }

        while (j < n2) {
            uniqueList.add(arr2[j++]);
        }

        while (k < n3) {
            uniqueList.add(arr3[k++]);
        }
    }

    public static int divideIntoTwoAsEqualSummed(int[] arr) {
        int[] arr1 = new int[arr.length];
        int[] arr2 = new int[arr.length];

        int sum = 0;

        for (int i = 0; i < arr.length; i++) {
            sum += arr[i];
            arr1[i] = sum;
        }

        sum = 0;

        for (int i = arr.length - 1; i >= 0; i++) {
            sum += arr[i];
            arr2[i] = sum;
        }

        for (int i = 0; i < arr1.length; i++) {
            if (arr1[i] == arr2[i + 1]) {
                return i;
            }
        }

        return -1;
    }

    public static int maxGap(int[] arr) {
        int maxDiff = Integer.MIN_VALUE;

        Arrays.sort(arr);

        for (int i = 0; i < arr.length - 1; i++) {
            int diff = Math.abs(arr[i] - arr[i + 1]);

            if (maxDiff < diff) {
                maxDiff = diff;
            }
        }

        return maxDiff;
    }

    public static int findMissingPositiveInt(int[] arr) {
        int max = Integer.MIN_VALUE;
        Map<Integer, Boolean> map = new HashMap<>();

        for (int i = 0; i < arr.length; i++) {
            map.put(arr[i], true);

            if (max < arr[i]) {
                max = arr[i];
            }
        }

        if (max < 0) {
            return 1;
        }

        for (int i = 0; i < max; i++) {
            if (!map.containsKey(arr[i])) {
                return i;
            }
        }

        return max + 1;
    }

    public static int[] rotateArray(int[] arr, int d) {
        int n = arr.length;

        // new arr always needed
        int[] rotatedArr = new int[n];

        for (int i = 0; i < n; i++) {
            rotatedArr[i] = arr[(i + d) % n];
        }

        return rotatedArr;
    }

    public static int removeDupsSorted(int[] arr) { // return new length
        int n = arr.length;
        int count = 0;

        for (int i = 0; i < n; i++) {
            if (i < n - 1 && arr[i] == arr[i + 1]) {
                continue;
            }

            arr[count++] = arr[i];
        }

        return count;
    }

    public static int maxContSubArray(int[] arr) {
        int n = arr.length;
        int i = 0, sum = 0, maxSum = Integer.MIN_VALUE;

        while (i < n) {
            sum += arr[i];

            if (sum < 0) {
                sum = 0;
            }

            if (sum > maxSum) {
                maxSum = sum;
            }

            i++;
        }

        if (maxSum == 0) {
            maxSum = Arrays.stream(arr).max().getAsInt();
        }

        return maxSum;
    }

    public static int[] plusOne(int[] arr) {
        int n = arr.length;

        for (int i = n - 1; i >= 0; i++) {
            if (arr[i] < 9) {
                arr[i] = arr[i] + 1;
                return arr;
            }

            arr[i] = 0;
        }

        int[] newArr = new int[n + 1];
        newArr[0] = 1;

        return newArr;
    }

    public static int climbStairs(int height, int maxSteps) {
        int[] arr = new int[height + 1];
        arr[0] = 1;
        arr[1] = 1;

        for (int i = 2; i <= height; i++) {
            int step = 1;

            while (step <= maxSteps && step <= i) {
                arr[i] += arr[i - step];
                step++;
            }
        }

        return arr[height];
    }

    public int maxProfit(int[] prices) {
        int maxProfit = 0;
        int min = prices[0];

        for (int price : prices) {
            int profit = price - min;
            min = Math.min(min, price);
            maxProfit = Math.max(maxProfit, profit);
        }

        return maxProfit;
    }

    public static int[] moveNsToLast(int[] arr, int num) {
        int n = arr.length;
        int left = 0;

        for (int i = 0; i < n; i++) {
            if (arr[i] != num) {
                // swap arr[i], arr[left]
                int tmp = arr[i];
                arr[i] = arr[left];
                arr[left] = tmp;
                left++;
            }
        }

        return arr;
    }

    public static boolean checkInRotatedSortedArray(int[] arr, int num) {
        int n = arr.length, l = 0, r = n - 1;

        while (l <= r) {
            int mid = (l + r) / 2;

            if (arr[mid] == num) {
                return true;
            } else if (arr[l] <= arr[mid]) {
                if (num >= arr[l] && num <= arr[mid]) {
                    r = mid - 1;
                } else {
                    l = mid + 1;
                }
            } else {
                if (num >= arr[mid] && num <= arr[r]) {
                    l = mid + 1;
                } else {
                    r = mid - 1;
                }
            }
        }

        return false;
    }

    public boolean validateSubSequence(int[] arr, int[] sequence) {
        int n = arr.length;
        int m = sequence.length;
        int arrIdx = 0, seqIdx = 0;

        while (arrIdx < n && seqIdx < m) {
            if (arr[arrIdx] == sequence[seqIdx]) {
                seqIdx++;
            }

            arrIdx++;
        }

        return seqIdx == m;
    }

    public int nonConsCoinChange(int[] coins) {
        Arrays.sort(coins);

        int change = 0;
        for (int coin : coins) {
            if (coin > change + 1) {
                return change + 1;
            }

            change += coin;
        }

        return change + 1;
    }

    public int minDiffBetween2Arr(int[] arr1, int[] arr2) {
        Arrays.sort(arr1);
        Arrays.sort(arr2);

        int n = arr1.length;
        int m = arr2.length;
        int i = 0, j = 0, k = 0;
        int minDiff = Integer.MAX_VALUE;

        while (i < n && j < m) {
            int diff = Math.abs(arr1[i] - arr2[j]);

            if (arr1[i] < arr2[j]) {
                i++;
            } else if (arr1[i] > arr2[j]) {
                j++;
            } else {
                i++;
                j++;
            }

            if (minDiff > diff) {
                minDiff = diff;
            }
        }

        return minDiff;
    }

    public boolean isMonotonic(int[] arr) {
        boolean increasing = true, decreasing = true;

        for (int i = 0; i < arr.length - 1; i++) {
            if (arr[i] > arr[i + 1]) {
                increasing = false;
            } else if (arr[i] < arr[i + 1]) {
                decreasing = false;
            }
        }

        return increasing || decreasing;
    }

    public int[] find3Max(int[] arr) {
        int n = arr.length;
        int max1 = Integer.MIN_VALUE, max2 = Integer.MIN_VALUE, max3 = Integer.MIN_VALUE;

        for (int i = 0; i < n; i++) {
            if (max1 < arr[i]) {
                max3 = max2;
                max2 = max1;
                max1 = arr[i];
            } else if (max2 < arr[i]) {
                max3 = arr[i];
                max2 = arr[i];
            } else if (max3 < arr[i]) {
                max3 = arr[i];
            }
        }

        return new int[]{max1, max2, max3};
    }

    public static int[] findNMax(int[] arr, int N) {
        int n = arr.length;

        if (N >= n) {
            // sort arr
            // reverse arr
        }

        PriorityQueue<Integer> minHeap = new PriorityQueue<>(N);

        for (int num : arr) {
            if (minHeap.size() < N) {
                minHeap.add(num);
            } else if (num > minHeap.peek()) {
                minHeap.poll();
                minHeap.add(num);
            }
        }

        int[] res = new int[N];

        for (int i = N - 1; i >= 0; i--) {
            res[i] = minHeap.poll();
        }

        return res;
    }

    public static int[] findNMin(int[] arr, int N) {
        int n = arr.length;

        if (N >= n) {
            // sort arr
            // reverse arr
        }

        PriorityQueue<Integer> maxHeap = new PriorityQueue<>(N, Collections.reverseOrder());

        for (int num : arr) {
            if (maxHeap.size() < N) {
                maxHeap.add(num);
            } else if (num < maxHeap.peek()) {
                maxHeap.poll();
                maxHeap.add(num);
            }
        }

        int[] res = new int[N];

        for (int i = N - 1; i >= 0; i--) {
            res[i] = maxHeap.poll();
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(moveNsToLast(new int[]{3, 3, 1, 2, 3}, 3)));
        System.out.println(checkInRotatedSortedArray(new int[]{4, 5, 6, 1, 2, 3}, 6));
        System.out.println(Arrays.toString(findNMin(new int[]{20, 10, 15, 11, 12}, 3)));
    }
}
