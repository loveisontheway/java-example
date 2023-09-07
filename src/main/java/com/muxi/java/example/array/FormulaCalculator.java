package com.muxi.java.example.array;

import java.math.BigDecimal;
import java.util.*;

public class FormulaCalculator {

    public static BigDecimal calculate(String formula) {
        List<String> postfixExpression = convertToPostfix(formula);
        if (postfixExpression.size() == 1 && postfixExpression.get(0).equals("Invalid")) {
            return null;
        } else {
            return evaluatePostfixExpression(postfixExpression);
        }
    }

    private static List<String> convertToPostfix(String formula) {
        formula = formula.replaceAll("\\s", ""); // 去除空格

        List<String> postfix = new ArrayList<>();
        Stack<String> stack = new Stack<>();

        int i = 0;
        while (i < formula.length()) {
            StringBuilder token = new StringBuilder();

            if (Character.isDigit(formula.charAt(i)) || formula.charAt(i) == '.') {
                while (i < formula.length() &&
                        (Character.isDigit(formula.charAt(i)) || formula.charAt(i) == '.')) {
                    token.append(formula.charAt(i));
                    i++;
                }
                postfix.add(token.toString());
            } else if (isOperator(formula.charAt(i))) {
                while (!stack.isEmpty() && isOperator(stack.peek().charAt(0)) &&
                        hasHigherPrecedence(stack.peek().charAt(0), formula.charAt(i))) {
                    postfix.add(stack.pop());
                }
                stack.push(String.valueOf(formula.charAt(i)));
                i++;
            } else if (isOpenParenthesis(formula.charAt(i))) {
                stack.push(String.valueOf(formula.charAt(i)));
                i++;
            } else if (isCloseParenthesis(formula.charAt(i))) {
                while (!stack.isEmpty() && !isOpenParenthesis(stack.peek().charAt(0))) {
                    postfix.add(stack.pop());
                }
                stack.pop(); // 弹出对应的左括号
                i++;
            } else {
                postfix.clear();
                postfix.add("Invalid"); // 遇到无效字符时返回标志值 "Invalid"
                break;
            }
        }

        while (!stack.isEmpty()) {
            postfix.add(stack.pop());
        }

        return postfix;
    }

    private static boolean isOperator(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }

    private static boolean hasHigherPrecedence(char op1, char op2) {
        if ((op1 == '*' || op1 == '/') && (op2 == '+' || op2 == '-')) {
            return true;
        } else if ((op1 == '+' || op1 == '-') && (op2 == '*' || op2 == '/')) {
            return false;
        } else {
            return false;
        }
    }

    private static boolean isOpenParenthesis(char c) {
        return c == '(' || c == '[' || c == '{';
    }

    private static boolean isCloseParenthesis(char c) {
        return c == ')' || c == ']' || c == '}';
    }

    private static BigDecimal evaluatePostfixExpression(List<String> postfixExpression) {
        Stack<BigDecimal> stack = new Stack<>();

        for (String token : postfixExpression) {
            if (isNumeric(token)) {
                stack.push(new BigDecimal(token));
            } else if (isOperator(token.charAt(0))) {
                BigDecimal operand2 = stack.pop();
                BigDecimal operand1 = stack.pop();
                BigDecimal result = performOperation(operand1, operand2, token.charAt(0));
                stack.push(result);
            }
        }

        return stack.pop();
    }

    private static boolean isNumeric(String str) {
        try {
            new BigDecimal(str);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    private static BigDecimal performOperation(BigDecimal operand1, BigDecimal operand2, char operator) {
        switch (operator) {
            case '+':
                return operand1.add(operand2);
            case '-':
                return operand1.subtract(operand2);
            case '*':
                return operand1.multiply(operand2);
            case '/':
                return operand1.divide(operand2);
            default:
                throw new IllegalArgumentException("Invalid operator: " + operator);
        }
    }

    public static void main(String[] args) {
        String formula = "{[(2   +3)  *3]  -(8*5)}+(20-10)*3";

        BigDecimal result = calculate(formula);
        if (Objects.equals(result, null)) {
            System.out.println("Error: Invalid formula");
        } else {
            System.out.println("Result: " + result);
        }
    }
}