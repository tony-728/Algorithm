package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class problem1222 {
    /*
     * 문자로 된 계산식이 주어진다.
     * 
     * 이 계산식을 후위 표기식으로 바꾸어 계산하는 프로그램을 만들어라
     * 
     * 문자열을 구성하는 연산자는 + 하나만 있다
     * 숫자는 0부터 9까지
     * 
     * 총 10개의 테스트 케이스가 주어진다.
     * 
     */

    static final int testCase = 10;
    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int answer;

    public static void main(String[] args) throws IOException {

        for (int tc = 1; tc <= testCase; tc++) {
            int lengthOfExpression = Integer.parseInt(br.readLine().trim());

            String expression = br.readLine().trim();

            answer = 0;
            int temp = 0;

            for (int idx = 0; idx < lengthOfExpression; idx++) {
                if (expression.charAt(idx) == '+') {
                    answer += temp;
                } else {
                    temp = expression.charAt(idx) - '0';
                }
            }
            answer += temp;

            sb.append(String.format("#%d %d\n", tc, answer));
        }

        System.out.println(sb);
    }
}
