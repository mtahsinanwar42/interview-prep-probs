package org.example.string;

import java.util.*;

public class StringProbs {

    public static String[] getLPSAndSPS(String str) { // can try O(n^2) later
        if (str == null || str.isEmpty()) {
            return new String[0];
        }

        List<String> palindromeSubStrList = new ArrayList<>();

        for (int i = 0; i < str.length(); i++) {
            for (int j = i; j < str.length(); j++) {
                if (isPanlindrome(str.substring(i, j + 1))) {
                    palindromeSubStrList.add(str.substring(i, j + 1));
                }
            }
        }

        String lps = null, sps = null;
        int maxLen = Integer.MIN_VALUE, minLen = Integer.MAX_VALUE;

        for (String s : palindromeSubStrList) {
            if (maxLen < s.length()) {
                maxLen = s.length();
                lps = s;
            }

            if (minLen > s.length()) {
                minLen = s.length();
                sps = s;
            }
        }

        return new String[]{lps, sps};
    }

    public static boolean isPanlindrome(String str) {
        if (str == null || str.isEmpty()) {
            return false;
        }

        if (str.length() == 1) {
            return true;
        }

        for (int i = 0; i < str.length() / 2; i++) {
            if (str.charAt(i) != str.charAt(str.length() - (i + 1))) {
                return false;
            }
        }

        return true;
    }

    public static boolean isAnagram(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() != str2.length()) {
            return false;
        }

        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < str1.length(); i++) {
            map.put(str1.charAt(i), map.getOrDefault(str1.charAt(i), 0) + 1);
        }

        for (int i = 0; i < str2.length(); i++) {
            int count = map.getOrDefault(str2.charAt(i), 0);

            if (count == 0) {
                return false;
            } else if (count == 1) {
                map.remove(str2.charAt(i));
            } else {
                map.put(str2.charAt(i), count - 1);
            }
        }

        return map.isEmpty();
    }

    public static void commonAndUniques(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return;
        }

        List<Character> commonList = new ArrayList<>();
        List<Character> uniqueList = new ArrayList<>();
        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < str1.length(); i++) {
            map.put(str1.charAt(i), map.getOrDefault(str1.charAt(i), 0) + 1);
        }

        for (int i = 0; i < str2.length(); i++) {
            int count = map.getOrDefault(str2.charAt(i), 0);

            if (count == 0) {
                uniqueList.add(str2.charAt(i));
            } else if (count == 1) {
                commonList.add(str2.charAt(i));
                map.remove(str2.charAt(i));
            } else {
                commonList.add(str2.charAt(i));
                map.put(str2.charAt(i), count - 1);
            }
        }

        for (Map.Entry<Character, Integer> entry : map.entrySet()) {
            // handles same chars in str1
            for (int i = 0; i < entry.getValue(); i++) {
                uniqueList.add(entry.getKey());
            }
        }
    }

    public static void commonAndUniquesDistinctStrs(String str1, String str2) {
        if (str1 == null || str2 == null) {
            return;
        }

        Set<Character> str1Set = new HashSet<>();
        Set<Character> str2Set = new HashSet<>();

        for (int i = 0; i < str1.length(); i++) {
            str1Set.add(str1.charAt(i));
        }

        for (int i = 0; i < str2.length(); i++) {
            str2Set.add(str2.charAt(i));
        }

        Set<Character> commonSet = new HashSet<>(str1Set);
        commonSet.retainAll(str2Set); // intersect

        Set<Character> uniqueSet = new HashSet<>(str1Set);
        uniqueSet.addAll(str2Set);
        uniqueSet.removeAll(commonSet);
    }

    public static String removeAllDups(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        Map<Character, Boolean> visited = new HashMap<>();
        StringBuilder res = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            if (!visited.containsKey(str.charAt(i))) {
                visited.put(str.charAt(i), true);
                res.append(str.charAt(i));
            }
        }

        return res.toString();
    }


    public static String removeConsDups(String str) {
        if (str == null || str.isEmpty()) {
            return str;
        }

        StringBuilder res = new StringBuilder();

        for (int i = 0; i < str.length(); i++) {
            if (i < str.length() - 1 && str.charAt(i) == str.charAt(i + 1)) {
                continue;
            }

            res.append(str.charAt(i));
        }

        return res.toString();
    }

    public static boolean isRotated(String str1, String str2) {
        if (str1 == null || str2 == null || str1.length() != str2.length()) {
            return false;
        }

        str1 += str1;

        return str1.indexOf(str2) == -1;
    }

    public static boolean validParantheses(String str) {
        if (str == null || str.length() == 0) {
            return false;
        }

        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            char top = stack.isEmpty() ? '#' : stack.peek();

            if (top == '(' && str.charAt(i) == ')') {
                stack.pop();
            } else {
                stack.push(str.charAt(i));
            }
        }

        return stack.isEmpty();
    }

    public static String longestCommonPrefix(String[] strs) {
        if (strs == null || strs.length == 0) {
            return "";
        }

        Arrays.sort(strs);

        int min = Math.min(strs[0].length(), strs[strs.length - 1].length());
        int i = 0;
        StringBuilder res = new StringBuilder();

        while (i < min && strs[0].charAt(i) == strs[strs.length - 1].charAt(i)) {
            i++;
        }

        return strs[0].substring(0, i);
    }

    public static int indexOf(String str1, String str2) {
        if (str1 == null || str2 == null || str2.length() > str1.length()) {
            return -1;
        }

        int m = str1.length();
        int n = str2.length();

        for (int i = 0; i < m - n + 1; i++) {
            String sub = str1.substring(i, i + n);

            if (sub.equals(str2)) {
                return i;
            }
        }

        return -1;
    }

    public static boolean canGenDoc(String chars, String doc) {
        if (chars == null || chars.isEmpty() || doc == null || doc.isEmpty() || doc.length() > chars.length()) {
            return false;
        }

        Map<Character, Integer> map = new HashMap<>();

        for (int i = 0; i < chars.length(); i++) {
            map.put(chars.charAt(i), map.getOrDefault(chars.charAt(i), 0) + 1);
        }

        for (int i = 0; i < doc.length(); i++) {
            int count = map.getOrDefault(doc.charAt(i), 0);

            if (count == 0) {
                return false;
            } else {
                map.put(doc.charAt(i), count - 1);
            }
        }

        return true;
    }

    public static int getLongestSubStrLenWithoutRepeatChars(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }

        Map<Character, Integer> indexOf = new HashMap<>();
        int max = 0, left = 0;

        for (int i = 0; i < str.length(); i++) {
            char c = str.charAt(i);
            int prev = indexOf.getOrDefault(c, -1);

            if (prev != -1 && left < prev + 1) {
                left = prev + 1;
            }

            indexOf.put(c, i);
            max = Math.max(max, i - left + 1);
        }

        return max;
    }

    public static void main(String[] args) {

    }
}
