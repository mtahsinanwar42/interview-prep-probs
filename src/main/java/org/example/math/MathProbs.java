package org.example.math;

import java.util.HashSet;
import java.util.Set;

public class MathProbs {

    public static int pow(int x, int n) {
        if (n == 1 || x == 0) {
            return x;
        }

        if (n == 0 || x == 1) {
            return 1;
        }

        int prod = 1;

        for (int i = 1; i <= Math.abs(n); i++) {
            prod = prod * x;
        }

        return n > 0 ? prod : 1 / prod;
    }

    public static int sqrt(int x) {
        if (x == 0 || x == 1) {
            return x;
        }

        int l = 1, r = x / 2;

        while (l <= r) {
            int mid = (l + r) / 2;

            int prod = mid * mid;

            if (prod == x) {
                return mid;
            }

            if (prod > x) {
                r = mid - 1;
            } else {
                l = mid + 1;
            }
        }

        return r;
    }

    public boolean isPrime(int n) {
        if (n <= 1) {
            return false;
        }

        for (int i = 2; i <= sqrt(n); i++) {
            if (n % i == 0) {
                return false;
            }
        }

        return true;
    }

    public int gcd(int a, int b) {
        if (b == 0) {
            return a;
        }

        return gcd(b, a % b);
    }

    public static int parseInt(String str) {
        if (str == null || str.isEmpty()) {
            return 0;
        }

        int i = 0, sign = 1, res = 0;

        if (str.charAt(0) == '-') {
            sign = -1;
            i++;
        }

        for (; i < str.length(); i++) {
            res = res * 10 + str.charAt(i) - '0';
        }

        return res * sign;
    }

    public static String toBinary(int n) {
        String res = "";

        while (n != 0) {
            res = (n % 2) + res;
            n = n / 2;
        }

        return res;
    }

    public static int toDecimal(int n) {
        int res = 0;

        int i = 0;

        while (n != 0) {
            res += (n % 10) * pow(2, i);
            n = n / 10;
            i++;
        }

        return res;
    }

    public static int toReverse(int n) {
        int rev = 0;
        int MAX = Integer.MAX_VALUE;
        int MIN = Integer.MIN_VALUE;

        while (n != 0) {
            int digit = n % 10;

            if (rev > MAX / 10 || (rev == MAX / 10 && digit > 0)) {
                return 0;
            }
            if (rev < MIN / 10 || (rev == MIN / 10 && digit < 0)) {
                return 0;
            }

            rev = rev * 10 + digit;
            n = n/10;
        }

        return rev;
    }

    public static int maxCons1sInBinary(String binStr) {
        int max = Integer.MIN_VALUE;
        int count = 0;

        for (int i = 0; i < binStr.length(); i++) {
            if (binStr.charAt(i) == '1') {
                count++;

                if (count > max) {
                    max = count;
                }
            } else {
                count = 0;
            }
        }

        return max;
    }

    public static int factorial(int n) {
        int res = 1;

        for (int i = 1; i <= n; i++) {
            res *= i;
        }

        return res;
    }

    public static int[] fibonacci(int n) {
        int[] res = new int[n];
        res[0] = 1;
        res[1] = 1;

        for (int i = 2; i < n; i++) {
            res[i] = res[i - 1] + res[i - 2];
        }

        return res;
    }

    public static String intToRoman(int n) {
        int[] digits = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] codes = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        StringBuilder res = new StringBuilder();
        int i = 0;

        while (n != 0) {
            int times = n / digits[i];
            int tmp = times;

            for (; times >= 1; times--) {
                res.append(codes[i]);
            }

            n -= tmp * digits[i];
            i++;
        }

        return res.toString();
    }

    public static int romanToInt(String s) {
        int[] digits = {1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1};
        String[] codes = {"M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I"};
        int res = 0, i = 0;

        while (s.length() != 0) {
            while (s.startsWith(codes[i])) {
                res += digits[i];
                s = s.substring(codes[i].length());
            }
            i++;
        }

        return res;
    }

    public static String amPmToMilitary(String amPmTime) {
        String[] splitted = amPmTime.split("\\s+");
        String amOrPm = splitted[1];
        String[] hourMin = splitted[0].split(":");
        String hour = hourMin[0];
        String min = hourMin[1];
        String hourRes = hour, minRes = min;

        switch (amOrPm) {
            case "AM":
                if ("12".equals(hour)) {
                    hourRes = "00";
                }

                break;
            case "PM":
                int hourVal = Integer.parseInt(hour);

                if (hourVal < 12) {
                    int hourResVal = hourVal + 12;
                    hourRes = String.valueOf(hourResVal);
                }

                break;
        }

        return hourRes + ":" + minRes;
    }

    public static String militaryTimeToAmPm(String militaryTime) {
        String[] splitted = militaryTime.split(":");
        String hour = splitted[0];
        String min = splitted[1];

        String amOrPm = "AM";
        String hourRes = hour, minRes = min;

        if ("00".equals(hour)) {
            hourRes = "12";
        } else {
            int hourVal = Integer.parseInt(hour);

            if (hourVal >= 12) {
                if (hourVal != 12) {
                    int hourResVal = hourVal - 12;
                    hourRes = String.valueOf(hourResVal);
                }

                amOrPm = "PM";
            }
        }

        return hourRes + ":" + minRes + " " + amOrPm;
    }

    public static int noOf1Bits(int n) {
        int count = 0;

        while (n != 0) {
            n = n & (n - 1);
            count++;
        }

        return count;
    }

    public static int excelColToNumbers(String col) {
        int res = 0;

        for (int i = 0; i < col.length(); i++) {
            res = res * 26 + col.charAt(i) - 'A' + 1;
        }

        return res;
    }

    public static boolean isPowerOfN(int x, int n) {
        if (x == 0 || x == 1) {
            return true;
        }

        while (x != 0 && x % n != 0) {
            x = x / n;

            if (x == 1) {
                return true;
            }
        }

        return false;
    }

    public boolean isHappy(int n) {
        if (n == 1) {
            return true;
        }

        Set<Integer> visited = new HashSet<>();

        while (n != 1) {
            if (visited.contains(n)) {
                return false;
            }

            visited.add(n);
            n = getNext(n);
        }

        return true;
    }

    public static int getNext(int n) {
        if (n <= 1) {
            return 1;
        }

        int res = 0;

        while (n != 0) {
            res += (n % 10) * (n * 10);
            n = n / 10;
        }

        return res;
    }

    public static void main(String[] args) {
        System.out.println(parseInt("-1234"));
        System.out.println(militaryTimeToAmPm("23:01"));
    }
}
