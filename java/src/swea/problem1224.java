package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class problem1224 {
    /*
     * 입력받는 문자열을 괄호와 우선순위에 맞게 계산하기
     * 
     * 총 10개의 테스트케이스
     * 
     * 1. 덧셈일 때
     * - 스택에 넣어준다.
     * 2. 곱하기 일 때
     * - 스택에 넣어준다.
     * 3. 여는 괄호일 때
     * - 스택에 넣어준다.
     * 4. 닫는 괄호일 때
     * - 여는 괄호가 등장할 때까지 등장하는 식을 계산하여 스택에 넣어준다.
     * 5. 숫자일 때
     * - 스택에 가장 위에 연산자가 더하기 일 때
     * - 현재 숫자를 스택에 넣어준다.
     * - 스택에 가장 위에 연산자가 곱하기 일 때
     * - 곱하기를 pop하고 다음 가장 위에 숫자와 현재 숫자를 곱한 값을 다시 스택에 넣어준다.
     * 
     */

    static final int testCase = 10;
    // static final int testCase = 1;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static Deque<String> stack;

    public static void main(String[] args) throws IOException {
        for (int tc = 1; tc <= testCase; tc++) {
            int lengthOfExpression = Integer.parseInt(br.readLine().trim());

            stack = new ArrayDeque<>();

            String expression = br.readLine().trim();

            for (int idx = 0; idx < lengthOfExpression; idx++) {
                // 닫는 괄호
                if (expression.charAt(idx) == ')') {
                    int temp = 0;
                    // 여는 괄호가 나올 때까지 반복하면서 곱셈 연산자는 계산 후 스택에 넣어줌
                    while (!stack.peek().equals("(")) {
                        if (stack.peek().equals("+")) {
                            stack.pop();
                            int temp2 = Integer.parseInt(stack.pop());

                            stack.push(String.valueOf(temp + temp2));

                        } else {
                            temp = Integer.parseInt(stack.pop());
                        }

                    }
                    stack.pop(); // 열린 괄호 빼주기

                    // 열린 괄호까지 계산 후 스택에 맨 위에 연산자가 곱셈일 경우
                    // 해당 연산까지 후 스택에 넣어줌
                    if (!stack.isEmpty() && stack.peek().equals("*")) {
                        stack.pop();
                        int temp2 = Integer.parseInt(stack.pop());

                        stack.push(String.valueOf(temp * temp2));
                    } else {
                        stack.push(String.valueOf(temp));
                    }

                    // 숫자일 때
                } else if (Character.isDigit(expression.charAt(idx))) {
                    // 곱셈일 때는 연산 후 결과를 스택에 넣어줌
                    if (stack.peek().equals("*")) {

                        stack.pop();
                        int temp = Integer.parseInt(stack.pop());

                        stack.push(String.valueOf(temp * (expression.charAt(idx) - '0')));

                    } else {
                        stack.push(String.valueOf(expression.charAt(idx)));
                    }
                    // 여는 괄호, 연산자
                } else {
                    stack.push(String.valueOf(expression.charAt(idx)));
                }
            }

            sb.append(String.format("#%d %s\n", tc, stack.pop()));
        }
        System.out.println(sb);
    }
}
