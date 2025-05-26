package boj;

import java.io.*;
import java.util.*;

public class problem3020 {
    /*
     * 동굴의 길이는 N, 높이는 H
     * 
     * 첫번째 장애물은 항상 석순, 그 다음에는 종유석과 석순이 번갈아가면서 등장한다.
     * 
     * 지나갈 구간을 정하면 일직선으로 지나가면서 만나는 모든 장애물을 파괴한다.
     * 
     * 길이 최대 20만, 높이 최대 50만
     * N*H는 100억
     * 
     * 
     * nlogn으로 풀어야 함
     * 
     * 1. 이진탐색
     * 2. 누적함
     * 
     * 석순과 종유석을 구분하여 배열에 저장한다.
     * 두 배열을 정렬한 후 높이가 1부터 N까지 탐색을 이진탐색을 시작한다.
     * - 찾은 인덱스의 값 뒤로는 현재 탐색 높이에 모두 걸리는 장애물이다.
     * - 이 장애물의 합의 최소를 구하면 된다.
     * 
     */

    static int lengthOfCave;
    static int heightOfCave;
    static int[] upCave;
    static int[] downCave;

    static int answer;

    static StringBuilder sb = new StringBuilder();

    static void inputDataBinearySearch() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine().trim());

        lengthOfCave = Integer.parseInt(st.nextToken()); // 항상 짝수
        heightOfCave = Integer.parseInt(st.nextToken());

        upCave = new int[lengthOfCave >> 1];
        downCave = new int[lengthOfCave >> 1];

        for (int idx = 0; idx < lengthOfCave >> 1; idx++) {
            int downHeight = Integer.parseInt(br.readLine().trim());
            int upHeight = Integer.parseInt(br.readLine().trim());

            downCave[idx] = downHeight;
            upCave[idx] = upHeight;
        }

    }

    static void solutionBinearySearch() {
        // 이진탐색 풀이
        // 석순, 종유석 배열을 입력받고 정렬한다.
        // 높이 1부터 최대 높이까지 이진탐색으로 작으면서 가장큰 값을 찾는다.
        // 동굴 전체 길이 에서 그 값을 뺀 길이가 장애물이 파괴되는 갯수이다.
        // 파괴하는 갯수의 최소와 갯수를 찾으면 된다.

        Arrays.sort(upCave);
        Arrays.sort(downCave);

        int min = lengthOfCave;
        answer = 0;

        for (int height = 1; height < heightOfCave + 1; height++) {
            int result = binearySearch(0, lengthOfCave >> 1, height, downCave)
                    + binearySearch(0, lengthOfCave >> 1, heightOfCave - height + 1, upCave);

            if (min == result) {
                answer++;
            }

            if (min > result) {
                min = result;
                answer = 1;
            }
        }

        sb.append(min).append(" ").append(answer);
    }

    static int binearySearch(int left, int right, int height, int[] cave) {
        while (left < right) {
            int mid = (left + right) / 2;

            if (cave[mid] >= height) {
                right = mid;
            } else {
                left = mid + 1;
            }
        }

        // 뒤에 남은 갯수만큼 파괴한다.
        return cave.length - right;
    }

    static void inputDataPrefixSum() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine().trim());

        lengthOfCave = Integer.parseInt(st.nextToken()); // 항상 짝수
        heightOfCave = Integer.parseInt(st.nextToken());

        upCave = new int[heightOfCave + 2];
        downCave = new int[heightOfCave + 2];

        for (int idx = 1; idx <= lengthOfCave >> 1; idx++) {
            int downHeight = Integer.parseInt(br.readLine().trim());
            int upHeight = heightOfCave - Integer.parseInt(br.readLine().trim()) + 1;

            downCave[downHeight]++;
            upCave[upHeight]++;
        }
    }


    static void solutionPrefixSum() {
        // 누적합

        for (int idx = 1; idx < heightOfCave + 1; idx++) {
            downCave[idx] += downCave[idx - 1];
        }

        for (int idx = heightOfCave; idx >= 1; idx--) {
            upCave[idx] += upCave[idx + 1];
        }

        int min = lengthOfCave;
        int answer = 0;
        for (int idx = 1; idx < heightOfCave + 1; idx++) {
            int diff = (downCave[heightOfCave] - downCave[idx - 1]) + (upCave[1] - upCave[idx + 1]);

            if (diff < min) {
                min = diff;
                answer = 1;
            } else if (diff == min)
                answer++;
        }

        sb.append(min).append(" ").append(answer);

    }

    public static void main(String[] args) throws IOException {
        inputDataPrefixSum();

        solutionPrefixSum();

        System.out.println(sb);

    }
}
