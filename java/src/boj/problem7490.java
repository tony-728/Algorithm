package boj;

import java.io.*;
import java.util.*;

public class problem7490 {
    /*
     * 1부터 N까지 수를 오름차순으로 쓴 수열이 있다.
     * +, -, ' '(공백)을 삽입
     * - 공백은 숫자를 이어 붙이는 것
     * 
     * 삽입한 수식의 값이 0이 될 수 있는지 확인한다.
     * 
     * 재귀함수
     * 
     */

    static int n;
    static StringBuilder sb = new StringBuilder();

    static void solution(int number, Deque<String> equation) {

        if (number == n + 1) {

            int result = 0;

            int operator = -1;

            StringBuilder answer = new StringBuilder();

            Iterator<String> iter = equation.iterator();

            String value = "";

            while (iter.hasNext()) {
                String next = iter.next();

                if (next.charAt(0) == '+') {
                    
                    if (operator == 0) {
                        result = result + Integer.parseInt(value);
                        
                    } else if (operator == 1) {
                        result = result - Integer.parseInt(value);
                        
                    } else if (operator == -1) {
                        result = Integer.parseInt(value);
                    }
                    
                    operator = 0;
                    value = "";

                } else if (next.charAt(0) == '-') {
                    
                    if (operator == 0) {
                        result = result + Integer.parseInt(value);
                        
                    } else if (operator == 1) {
                        result = result - Integer.parseInt(value);
                        
                    } else if (operator == -1) {
                        result = Integer.parseInt(value);
                    }
                    
                    operator = 1;
                    value = "";

                } else if (next.charAt(0) == ' ') {
                    answer.append(next);
                    next = iter.next();
                    value += next;

                } else {
                    value += next;

                }

                answer.append(next);
            }

            if (operator == 0) {
                result = result + Integer.parseInt(value);
            } else if (operator == 1) {
                result = result - Integer.parseInt(value);
            } else{
                result = Integer.parseInt(value);
            }

            // System.out.print(" = " + result);
            // System.out.println();

            if(result == 0){
                sb.append(answer).append("\n");
            }

            return;
        }
        
        // 합치기
        equation.addLast(" ");
        equation.addLast("" + number);
        solution(number + 1, equation);
        equation.pollLast();
        equation.pollLast();

        // 덧셈
        equation.addLast("+");
        equation.addLast("" + number);
        solution(number + 1, equation);
        equation.pollLast();
        equation.pollLast();

        // 뺄셈
        equation.addLast("-");
        equation.addLast("" + number);
        solution(number + 1, equation);
        equation.pollLast();
        equation.pollLast();
    }

    public static void main(String[] args) throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int testCase = Integer.parseInt(br.readLine().trim());

        for (int idx = 0; idx < testCase; idx++) {
            n = Integer.parseInt(br.readLine().trim());

            Deque<String> q = new ArrayDeque<>();

            q.addLast(Integer.toString(1));

            solution(2, q);

            sb.append("\n");
        }

        System.out.println(sb);

    }
}
