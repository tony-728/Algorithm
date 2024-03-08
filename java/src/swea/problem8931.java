package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;

public class problem8931 {
    /*
     * 테스트케이스가 주어진다.
     * 
     * 입력받는 수 K가 주어진다.
     * 
     * K만큼 숫자를 입력받는다.
     * 
     * 0이 아닌 정수면 스택에 저장한다.
     * 0이면 스택에 저장된 수를 꺼낸다.
     * 
     * 스택에 남은 수를 모두 더한 값이 정답이 된다.
     * 
     */
    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringBuilder sb = new StringBuilder();
    public static int testCase;
    public static int K;
    public static Deque<Integer> stack = new ArrayDeque<>();
    public static int answer;

    public static void main(String[] args) throws IOException {
        testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= testCase; tc++) {
            answer = 0;
            K = Integer.parseInt(br.readLine().trim());

            for (int idx = 0; idx < K; idx++) {
                int number = Integer.parseInt(br.readLine().trim());

                if (number == 0) {
                    stack.pollLast();
                } else {
                    stack.add(number);
                }
            }

            while (!stack.isEmpty()) {
                answer += stack.poll();
            }
            sb.append(String.format("#%d %d\n", tc, answer));
        }

        System.out.println(sb);
    }
}