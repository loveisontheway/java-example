package com.muxi.java.example.array;

import java.util.stream.IntStream;

public class Range {
    public static void main(String[] args) {
        String codeToCheck = "PT14-116";
        String rangeStart = "PT11-114";
        String rangeEnd = "PT15-130";

        boolean isInRange = isCodeInRange(codeToCheck, rangeStart, rangeEnd);

        if (isInRange) {
            System.out.println(codeToCheck + " is within the range.");
        } else {
            System.out.println(codeToCheck + " is outside the range.");
        }
    }

    private static boolean isCodeInRange(String number, String rangeStart, String rangeEnd) {

        String[] numberParts = number.split("-");
        String[] startParts = rangeStart.split("-");
        String[] endParts = rangeEnd.split("-");

        String prefixNumber = numberParts[0];
        String prefixStart = startParts[0];
        String prefixEnd = endParts[0];

        String suffixNumber = numberParts[1];
        String suffixStart = startParts[1];
        String suffixEnd = endParts[1];

        if (!prefixNumber.equals(prefixStart) || !prefixNumber.equals(prefixEnd)) {
            int prefixNumberVal = Range.getPureNum(prefixNumber);
            int prefixStartVal = Range.getPureNum(prefixStart);
            int prefixEndVal = Range.getPureNum(prefixEnd);
            return IntStream.rangeClosed(prefixStartVal, prefixEndVal).anyMatch(n -> n == prefixNumberVal);
        }

        int suffixNumberVal = Range.getPureNum(suffixNumber);
        int suffixStartVal = Range.getPureNum(suffixStart);
        int suffixEndVal = Range.getPureNum(suffixEnd);

        return IntStream.rangeClosed(suffixStartVal, suffixEndVal).anyMatch(n -> n == suffixNumberVal);
    }

    private static int getPureNum(String str) {
        int num = str.chars()
                .filter(Character::isDigit)
                .map(Character::getNumericValue)
                .reduce(0, (a, b) -> a * 10 + b);
        return num;
    }
}
