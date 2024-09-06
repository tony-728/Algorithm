package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class problem2839 {
    /*
     * 3, 5 kg로 주어진 무게를 딱 맞춰서 채울 때
     * 사용한 개수의 최소값을 구한다. 만약 딱 맞추지 못했을 땐 -1
     * 
     * 풀이
     * - 주어진 무게가 5로 나눠떨어지면 끝
     * - 5로 나눠떨어지지 않으면 3을 뺀다.
     * - 반복
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static final int FIVE_KG = 5;
    static final int THREE_KG = 3;

    static int inputValue;

    static int answer = 0;

    public static void main(String[] args) throws IOException {
        inputValue = Integer.parseInt(br.readLine().trim());

        while (true) {
            // 딱 맞춰 만든 경우
            if (inputValue == 0) {
                break;
            }

            // 못만드는 경우
            if (inputValue < 0) {
                answer = -1;
                break;
            }

            // 5로 나눠떨어지면 끝
            if (inputValue % FIVE_KG == 0) {
                answer += (inputValue / FIVE_KG);
                break;
                // 안되면 -3 봉지개수 증가
            } else {
                inputValue -= THREE_KG;
                answer++;
            }
        }

        System.out.println(answer);
    }
}
