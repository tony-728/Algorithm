package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class problem16637 {
    /*
     * 괄호 추가하기
     * 
     * 주어진 수식에 괄호를 추가하여 최대값을 구하라
     * 
     * 주어진 수식의 연산자 우선순위는 모두 동일하다 따라서 왼쪽부터 순서대로 계산한다.
     * 괄호에 있는 것은 먼저 계산한다.
     * 
     * 괄호를 추가할 때 규칙
     * 1. 괄호를 추가할 때는 괄호안에 연산자가 하나만 들어있어야 한다.
     * 2. 중첩된 괄호는 사용할 수 없다.
     * 
     * 
     * 재귀함수로 구현
     * 괄호를 넣는다. / 넣지 않는다.
     * - 괄호를 넣었다면 연산자가 등장하고 피연산자가 등장하면 괄호를 닫아야 한다.
     * - 괄호 안에 연산자는 한개
     * 
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int expressionLength;
    static String expression;
    static Deque<Character> expressionQueue;
    static Deque<String> stack;

    static int answer;

    public static void inputTestCase() throws IOException {
        answer = Integer.MIN_VALUE;
        expressionLength = Integer.parseInt(br.readLine().trim());

        // 수식 입력받기
        expression = br.readLine().trim();

        return;
    }

    public static void calculation() {
        // 만들어진 수식으로 계산을 진행함
        // System.out.print(expressionQueue.toString() + " ");

        stack = new ArrayDeque<>();

        for (Character value : expressionQueue) {
            // 피연산자
            if (Character.isDigit(value)) {
                if (stack.isEmpty()) {
                    stack.offer(value.toString());
                } else if (stack.peekLast().equals("+")) {
                    stack.pollLast(); // 연산자 빼기

                    int result = Integer.parseInt(stack.pollLast()) + Integer.parseInt(value.toString());

                    stack.offer(String.valueOf(result));
                } else if (stack.peekLast().equals("-")) {
                    stack.pollLast(); // 연산자 빼기

                    int result = Integer.parseInt(stack.pollLast()) - Integer.parseInt(value.toString());

                    stack.offer(String.valueOf(result));
                } else if (stack.peekLast().equals("*")) {
                    stack.pollLast(); // 연산자 빼기

                    int result = Integer.parseInt(stack.pollLast()) * Integer.parseInt(value.toString());

                    stack.offer(String.valueOf(result));
                } else if (stack.peekLast().equals("/")) {
                    stack.pollLast(); // 연산자 빼기

                    int result = Integer.parseInt(stack.pollLast()) / Integer.parseInt(value.toString());

                    stack.offer(String.valueOf(result));
                } else {
                    stack.offer(value.toString());
                }
            } else if (value == '(') {
                stack.offer(value.toString());

            } else if (value == ')') {
                String temp = stack.pollLast();
                stack.pollLast();
                stack.offer(temp);

                while (!stack.isEmpty()) {
                    temp = stack.pollLast();

                    if (!stack.isEmpty() && stack.peekLast().equals("+")) {
                        stack.pollLast();

                        int result = Integer.parseInt(stack.pollLast()) + Integer.parseInt(temp);

                        stack.offer(String.valueOf(result));

                    } else if (!stack.isEmpty() && stack.peekLast().equals("-")) {
                        stack.pollLast();

                        int result = Integer.parseInt(stack.pollLast()) - Integer.parseInt(temp);

                        stack.offer(String.valueOf(result));

                    } else if (!stack.isEmpty() && stack.peekLast().equals("*")) {
                        stack.pollLast();

                        int result = Integer.parseInt(stack.pollLast()) * Integer.parseInt(temp);

                        stack.offer(String.valueOf(result));

                    } else if (!stack.isEmpty() && stack.peekLast().equals("/")) {
                        stack.pollLast();

                        int result = Integer.parseInt(stack.pollLast()) / Integer.parseInt(temp);

                        stack.offer(String.valueOf(result));

                    }
                }

                stack.offer(temp);

            } else { // 연산자
                stack.offer(value.toString());
            }
        }

        int result = Integer.parseInt(stack.pollLast());
        // System.out.println(result);

        answer = Math.max(answer, result);

    }

    // 수식 조합 만들기
    /**
     * 
     * @param elementIdx      수식의 요소인덱스
     * @param openParenthesis 괄호가 열려있는지 확인
     * @param checkOperator   연산자를 넣었는지 확인
     */
    public static void makeExpression(int elementIdx, boolean openParenthesis, boolean checkOperator) {

        if (elementIdx == expressionLength) {

            // 괄호가 열려 있다면 괄호를 닫아야 함
            if (openParenthesis) {
                expressionQueue.offer(')');
            }

            calculation();

            if (openParenthesis) {
                expressionQueue.pollLast();
            }
            return;
        }

        // 괄호가 없고 피연산자이면
        // 괄호를 추가하고 피연산자를 큐에 추가한다.
        if (!openParenthesis && Character.isDigit(expression.charAt(elementIdx))) {
            expressionQueue.add('(');
            expressionQueue.add(expression.charAt(elementIdx));

            makeExpression(elementIdx + 1, true, checkOperator);

            // 처리한 식에 대한 후처리
            expressionQueue.pollLast();
            expressionQueue.pollLast();
        }

        // 괄호가 있고
        // 피연산자이고
        // 연산자를 한번 넣었으면
        // 피연산자를 큐에 추가하고
        // 괄호를 추가한다.
        if (openParenthesis && Character.isDigit(expression.charAt(elementIdx)) && checkOperator) {
            expressionQueue.add(expression.charAt(elementIdx));
            expressionQueue.add(')');

            makeExpression(elementIdx + 1, false, false);

            // 처리한 식에 대한 후처리
            expressionQueue.pollLast();
            expressionQueue.pollLast();
        }

        // 연산자이면 큐에 추가한다.
        if (!Character.isDigit(expression.charAt(elementIdx))) {
            expressionQueue.add(expression.charAt(elementIdx));

            makeExpression(elementIdx + 1, openParenthesis, true);

            // 처리한 식에 대한 후처리
            expressionQueue.pollLast();
        }

        // 괄호가 없을 때
        // 연산자, 피연산자를 큐에 추가한다.
        if (!openParenthesis) {
            expressionQueue.add(expression.charAt(elementIdx));

            makeExpression(elementIdx + 1, openParenthesis, checkOperator);

            // 처리한 식에 대한 후처리
            expressionQueue.pollLast();
        }

    }

    public static void main(String[] args) throws IOException {

        inputTestCase();

        expressionQueue = new ArrayDeque<>();
        makeExpression(0, false, false);

        System.out.println(answer);
    }

}
