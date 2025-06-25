package boj;

import java.io.*;
import java.util.*;

public class problem2504 {
    /*
     * (, ), [, ] 를 이용해서 만들어지는 괄호열 중에서 올바른 괄호열에 대해서 값을 구하라
     * - 한 쌍의 괄호로만 이루어진 ()와 []는 올바른 괄호열이다.
     * - 만일 x가 올바른 괄호열이면 (x)이나 [x]도 모두 올바른 괄호열이 된다.
     * - x와 y 모두 올바른 괄호열이라면 이들을 결합한 xy도 올바른 괄호열이 된다.
     * 
     * 1. ()인 괄호열의 값은 2이다.
     * 2. []인 괄호열의 값은 3이다.
     * 3. (x)의 괄호값은 2 * (x)로 계산
     * 4. [x]의 괄호값은 3 * (x)로 계산
     * 5. 올바른 괄호열 x와 y가 결합된 xy의 괄호값은 x + y로 계산
     * 
     * 올바르지 않은 괄호열은 0을 출력한다.
     * 
     * - 스택
     * - 구현
     */

    static int answer;

    static void check(Deque<Character> stack, char value) {
        int numValue = stack.pop() - '0';

        if (stack.isEmpty()) {
            stack.push((char) (numValue + '0'));
            stack.push(value);
            return;
        }

        if (stack.peek() == '(' && value == ')') {
            stack.pop();
            numValue *= 2;
            if (!stack.isEmpty() && (stack.peek() - '0') > 0) {
                int x = stack.pop() - '0';
                stack.push((char) ((numValue + x) + '0'));
            } else {
                stack.push((char) (numValue + '0'));
            }
        }

        if (stack.peek() == '[' && value == ']') {
            stack.pop();
            numValue *= 3;
            if (!stack.isEmpty() && (stack.peek() - '0') > 0) {
                int x = stack.pop() - '0';
                stack.push((char) ((numValue + x) + '0'));
            } else {
                stack.push((char) (numValue + '0'));
            }
        }
    }

    static void solution(String input) {
        answer = 0;
        int result = 1;

        Deque<Character> stack = new ArrayDeque<>();

        for (int idx = 0; idx < input.length(); idx++) {

            char value = input.charAt(idx);

            if (value == '(' ) {
                stack.push(value);
                result *= 2;
            } else if(value == '['){
                stack.push(value);
                result *= 3;
            } else if (value == ')') {
                if (stack.isEmpty() || stack.peek() != '(') {
                    answer = 0;
                    break;
                } else if(input.charAt(idx-1) == '('){
                    answer += result;
                }
                stack.pop();
                result /= 2;                
            } else if (value == ']') {
                if (stack.isEmpty() || stack.peek() != '[') {
                    answer = 0;
                    break;
                } else if(input.charAt(idx-1) == '['){
                    answer += result;
                }
                stack.pop();
                result /= 3; 
            }
        }

        if(!stack.isEmpty()){
            answer = 0;
        }
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String input = br.readLine().trim();

        solution(input);

        System.out.println(answer);
    }
}
