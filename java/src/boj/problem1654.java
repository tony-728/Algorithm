package boj;

import java.util.*;
import java.io.*;

public class problem1654 {
    /*
     * N개의 랜선을 만들어야 한다.
     * 
     * K개의 랜선을 가지고 있다.
     * - 서로 다른 길이를 갖고 있다.
     * 
     * 랜선을 모두 N개의 같은 길이의 랜선으로 만들고 싶다.
     * - K개의 랜선을 잘라서 만들어야 한다.
     * - 이미 자른 랜선은 붙일 수 없다.
     * - 기존 K개의 랜선으로 N개의 랜선을 만들 수 없는 경우는 없다.
     * - 자를 때 항상 정수길이만큼 자른다
     * - N개보다 많이 만드는 것도 N개를 만드는 것에 포함된다.
     * 
     * 만들 수 있는 최대 랜선의 길이를 구하라
     * 
     * 완탐
     * 이진탐색
     * 
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int numOfLAN;
    static int target;
    static int[] arrOfLAN;
    static long answer;

    static void inputData() throws IOException {
        answer = 0;

        st = new StringTokenizer(br.readLine().trim());

        numOfLAN = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());

        arrOfLAN = new int[numOfLAN];

        for (int idx = 0; idx < numOfLAN; idx++) {
            arrOfLAN[idx] = Integer.parseInt(br.readLine().trim());
        }

        Arrays.sort(arrOfLAN);
    }

    static void solution() {
        // 랜선의 길이를 더하는 것은 범위를 무조건 초과하기 때문에 방법이 아님
        // 가장 작은 랜선의 길이보다 작은 값이 답이 된다.
        // 답이 될 수도 있다.
        // 10, 10, 100 이 주어졌을 때 8개를 만들려고 하면 12가 최대 랜선의 길이

        // 랜선의 최대값 부터 1까지 모든 값을 확인
        // n^2 으로 시간 초과

        // nlogn으로 줄여야 함
        // 이진 탐색, 1부터 x 값 사이에 답이 존재한다. -> 이진탐색으로 답을 찾아간다.

        long right = arrOfLAN[numOfLAN - 1];
        long left = 1;

        while (left <= right) {
            // long이어야 하는 이유 랜선의 최대값이 21억인데 1을 더하면 int 범위에서 오버플로우 발생
            long mid = (left + right) / 2;

            int count = 0;
            for (int idx = 0; idx < numOfLAN; idx++) {
                count += (arrOfLAN[idx] / mid);
            }

            if (count >= target) {
                answer = Math.max(answer, mid);
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        inputData();

        solution();

        System.out.println(answer);
    }
}
