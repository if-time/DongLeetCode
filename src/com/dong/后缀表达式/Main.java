package com.dong.后缀表达式;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Queue;
import java.util.Stack;

/**
 * @author dongsiyuan
 * @date 2020年9月7日
 */
public class Main {

    public static void main(String[] args) {

    }

    /**
     * 解析计算给定的后缀表达式
     *
     * @param rpnExpression 后缀表达式
     */
    public int compute(String[] rpnExpression) {
        Objects.requireNonNull(rpnExpression, "后缀表达式为空");
        Stack<Integer> stack = new Stack<>();
        int value1, value2;
        for (String string : rpnExpression) {
            switch (string) {
                case "+": {
                    if (2 > stack.size()) {
                        throw new RuntimeException("解析异常");
                    }
                    value2 = stack.pop();
                    value1 = stack.pop();
                    stack.push(value1 + value2);
                    break;
                }
                case "-": {
                    if (2 > stack.size()) {
                        throw new RuntimeException("解析异常");
                    }
                    value2 = stack.pop();
                    value1 = stack.pop();
                    stack.push(value1 - value2);
                    break;
                }
                case "*": {
                    if (2 > stack.size()) {
                        throw new RuntimeException("解析异常");
                    }
                    value2 = stack.pop();
                    value1 = stack.pop();
                    stack.push(value1 * value2);
                    break;
                }
                case "/": {
                    if (2 > stack.size()) {
                        throw new RuntimeException("解析异常");
                    }
                    value2 = stack.pop();
                    value1 = stack.pop();
                    stack.push(value1 / value2);
                    break;
                }
                default: {
                    stack.push(Integer.parseInt(string));
                    break;
                }
            }
        }
        if (1 != stack.size()) {
            throw new RuntimeException("解析异常");
        }
        return stack.pop();
    }

    /**
     * 将中缀表达式转换为后缀表达式
     * @param expressionArray
     */
    public String[] parse2rpn(String[] expressionArray) {
        Objects.requireNonNull(expressionArray, "算术表达式数组为空");
        Queue<String> queue = new LinkedList<>();
        Stack<String> stack = new Stack<>();
        for (String string : expressionArray) {
            if (!"+".equals(string) &&
                    !"-".equals(string) &&
                    !"*".equals(string) &&
                    !"/".equals(string) &&
                    !"(".equals(string) &&
                    !")".equals(string)) {
                // 非操作符直接输出到队列
                queue.offer(string);
                continue;
            }
            if ("+".equals(string) || "-".equals(string)) {
                while (true) {
                    // 加减符号只有在空栈，或者栈顶操作符为'('的情况下能够入栈
                    if ((0 >= stack.size() || "(".equals(stack.peek()))) {
                        stack.push(string);
                        break;
                    } else {
                        queue.offer(stack.pop());
                    }
                }
                continue;
            }
            if ("*".equals(string) || "/".equals(string)) {
                while (true) {
                    // 乘除符号只要栈顶符号不是乘除符号，都能入栈
                    if ((0 >= stack.size() ||
                            (!"*".equals(stack.peek()) && !"/".equals(stack.peek())))) {
                        stack.push(string);
                        break;
                    } else {
                        queue.offer(stack.pop());
                    }
                }
                continue;
            }
            // 左括号任何情况直接入栈
            if ("(".equals(string)) {
                stack.push(string);
                continue;
            }
            while (true) {
                if (0 >= stack.size()) {
                    throw new RuntimeException("表达式异常");
                }
                if (!"(".equals(stack.peek())) {
                    queue.offer(stack.pop());
                } else {
                    stack.pop();
                    break;
                }
            }
        }
        while (0 < stack.size()) {
            queue.offer(stack.pop());
        }
        return queue.toArray(new String[0]);
    }
}
