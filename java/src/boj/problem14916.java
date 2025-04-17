package boj;

import java.io.*;

public class problem14916 {
    /*
     * 주어진 숫자를 2와 5의 최소 갯수로 나타내어라
     * 
     * 그리디
     * 
     * 2와 5는 서로소 이므로 5로 나눠떨어질 때까지 2를 빼면서 진행한다.
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int number = Integer.parseInt(br.readLine().trim());

        int answer = 0;

        while (number > 0) {
            if (number % 5 != 0) {
                number -= 2;
                answer++;

            } else {
                answer += number / 5;

                number %= 5;
            }
        }

        if (number < 0) {
            answer = -1;
        }

        System.out.println(answer);
    }
}
