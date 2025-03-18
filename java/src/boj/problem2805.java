package boj;

import java.io.*;
import java.util.*;

public class problem2805 {
    /*
     * 나무 M미터가 필요하다
     * 
     * 1. 절단기의 높이 H를 지정한다.
     * 2. 땅으로부터 H미터 위로 올라간다
     * 3. 한 줄에 연속해있는 나무를 모두 절단한다.
     * - 나무를 최대 높이가 H미터가 되게 만드는 것임
     * 
     * 절단기에 설정할 수 있는 높이는 양의 정수 또는 0
     * 
     * 나무를 적어도 M미터의 나무를 집에 가져가기 위해서 절단기에 설정할 수 있는 높이의 최대값을 구하라
     * 
     * 나무의 갯수는 최대 100만
     * 
     * 이진 탐색
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int numOfTree;
    static int target;
    static int[] arrOfTree;

    static int answer;

    static void inputData() throws IOException {

        answer = 0;

        st = new StringTokenizer(br.readLine().trim());

        numOfTree = Integer.parseInt(st.nextToken());
        target = Integer.parseInt(st.nextToken());

        arrOfTree = new int[numOfTree];

        st = new StringTokenizer(br.readLine().trim());
        for (int idx = 0; idx < numOfTree; idx++) {
            arrOfTree[idx] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arrOfTree);
    }

    static void solution() {

        int left = 0;
        int right = arrOfTree[numOfTree - 1];


        while (left <= right) {
            // 자료형
            // 최대 나무 길이가 20억
            // 20억(나무길이) - 10억(mid) 를 3번만 더해도 범위를 넘어감
            long count = 0;
            int mid = (left + right) / 2;

            // System.out.println("left " + left + " right " + right + " mid " + mid);

            for (int idx = 0; idx < numOfTree; idx++) {
                int diff = arrOfTree[idx] - mid;

                if (diff <= 0) {
                    continue;
                }

                count += diff;
            }

            // 충분히 가져갈 수 있다.
            if (count >= target) {
                // 정답 갱신
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
