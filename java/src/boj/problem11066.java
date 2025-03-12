package boj;

import java.io.*;
import java.util.*;

public class problem11066 {
    /*
     * 두 개의 파일을 합칠 때 필요한 비용이 두 파일의 크기의 합이다.
     * 
     * 최종적인 한 개의 파일을 완성하는데 필요한 비용의 총 합을 계산하라
     * - 파일을 합칠 때 소설의 여러 장들이 연속이 되도록 파일을 합쳐야 한다.
     * 
     * dp
     * - dp[i][j] = i to j 배열에 대해서 병합하는 최소값
     * - dp[1][N] = 0 to N-1 배열에 대해서 병합하는 최소값 == 전체 파일에 대한 병합값의 최소값
     * - dp[i][i] = 0 으로 이미 병합이 됨, 나 자신의 대한 병합 최소값은 0
     * 
     * 구간 길이를 1부터 N-1로 확장하는 구조로 bottom-up 구조로 해결할 수 있다.
     * 추가로 어떤 구간에 대해서 파일을 합치는 값을 구할 때 누적합으로 빠르게 구할 수 있다.
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int testCase;
    static int numOfFile;
    static int[] prefixSum;
    static int[][] dp;

    static void inputData() throws IOException {
        numOfFile = Integer.parseInt(br.readLine().trim());

        prefixSum = new int[numOfFile + 1];
        dp = new int[numOfFile + 1][numOfFile + 1];

        st = new StringTokenizer(br.readLine().trim());

        prefixSum[1] = Integer.parseInt(st.nextToken());

        // 파일의 누적합을 구한다.
        for (int idx = 2; idx <= numOfFile; idx++) {
            prefixSum[idx] = Integer.parseInt(st.nextToken()) + prefixSum[idx - 1];
        }
    }

    static void solution() {

        // 구간은 1부터 시작한다.
        for (int gap = 1; gap < numOfFile; gap++) {
            // 첫번째 챕터부터 마지막 챕터까지 순회
            for (int start = 1; start + gap <= numOfFile; start++) {
                int end = start + gap;

                // 초기값
                dp[start][end] = 987654321;

                // start 부터 end 까지 병합하는 최소값을 구한다.
                // 중간에 어디서 잘라서 병합는 것이 최소인지 찾는 반복문
                for (int mid = start; mid < end; mid++) {
                    // start to mid 병합값
                    // mid to end 병합값
                    // start에서 end까지 합치는 병합값(start to mid 와 mid to end를 병합해야 start to end의 병합이 된다.)
                    dp[start][end] = Math.min(dp[start][end], dp[start][mid] + dp[mid + 1][end]
                            + (prefixSum[end] - prefixSum[start - 1]));
                }

            }
        }

        sb.append(dp[1][numOfFile]).append("\n");
    }

    public static void main(String[] args) throws IOException {
        testCase = Integer.parseInt(br.readLine().trim());

        for (int idx = 0; idx < testCase; idx++) {
            inputData();

            solution();
        }

        System.out.println(sb);
    }
}
