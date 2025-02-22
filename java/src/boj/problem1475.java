package boj;

import java.io.*;

public class problem1475 {
    /*
     * 0-9 숫자가 한 세트
     * 
     * 방번호가 주어졌을 때 필요한 세트의 개수의 최솟값을 출력
     * - 6과 9는 서로 교환이 가능하다
     * 
     * 같은 숫자가 중복된 횟수
     * 
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int[] visited = new int[10];

    static int count = 0;


    public static void main(String[] args) throws IOException {

        int answer = 0;

        String number = br.readLine().trim();

        for (int idx = 0; idx < number.length(); idx++) {
            int x = number.charAt(idx) - '0';

            if (x == 6 || x == 9) {
                visited[6]++;
            } else {
                visited[x]++;
            }
        }

        visited[6] = (visited[6] + 1) / 2;

        for (int idx = 0; idx < 10; idx++) {
            answer = Math.max(answer, visited[idx]);
        }

        System.out.println(answer);
    }
}
