package boj;

import java.io.*;
import java.util.*;

public class problem18111 {
    /*
     * 
     * NxM
     * - 맨 왼쪽 위의 좌표는 (0,0)
     * 
     * 집터 내의 땅의 높이를 일정하게 바꾸는 것이다.
     * 
     * 작업
     * 1. 좌표(i,j) 위에 있는 블록을 제거하여 인벤토리에 넣는다.
     * 2. 인벤토리에서 블록 하나를 꺼내어 좌표(i,j) 위에 있는 블록 위에 놓는다.
     * 
     * 작업1은 2초가 걸린다.
     * 작업2는 1초가 걸린다.
     * 
     * 땅 고르기 작업에 걸리는 최소 시간과 그 경우의 땅 높이를 구하라
     * 
     * 작업을 시작할 때 인벤토리에는 B개의 블록이 있다.
     * 땅의 높이는 256블록을 초과할 수 없고 음수가 될 수 없다.
     * 
     * 완탐
     * 맵 크기가 최대 500이고
     * 높이가 최대 256이므로 500*500*256 가능
     * n^3이 가능할듯
     */

    static int rowSize;
    static int colSize;
    static int[][] map;
    static int inventory;

    static StringBuilder sb = new StringBuilder();

    static void inputData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine().trim());

        rowSize = Integer.parseInt(st.nextToken());
        colSize = Integer.parseInt(st.nextToken());
        inventory = Integer.parseInt(st.nextToken());

        map = new int[rowSize][colSize];

        for (int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
            st = new StringTokenizer(br.readLine().trim());
            for (int colIdx = 0; colIdx < colSize; colIdx++) {
                int block = Integer.parseInt(st.nextToken());
                map[rowIdx][colIdx] = block;
            }
        }
    }

    static void solution() {
        int answerHeight = 0;
        int answerTime = 987654321;

        for (int height = 0; height <= 256; height++) {
            int localTime = 0;
            int localInventory = inventory;

            for (int rowIdx = 0; rowIdx < rowSize; rowIdx++) {
                for (int colIdx = 0; colIdx < colSize; colIdx++) {
                    // 현재 높이에서 평탄화된 높이만큼의 블록 확인
                    int block = map[rowIdx][colIdx] - height;

                    // 평탄화 높이가 더 크다 블록 추가
                    // 인벤토리에서 블록을 가져다가 사용함
                    if (block < 0) {
                        localTime -= block; // block이 음수이므로 -로 계산
                        localInventory += block;

                        // 평탄화 높이가 더 낮다 블록 제거
                    } else if (block > 0) {
                        localTime += (block * 2); // 제거에는 2초가 걸림
                        localInventory += block;
                    }

                }
            }

            if (localInventory >= 0) {
                // 시간이 같다면 높이가 높은게 정답
                if (answerTime >= localTime) {
                    answerHeight = height;
                    answerTime = localTime;
                }
            }

        }

        sb.append(answerTime).append(" ").append(answerHeight);
    }

    public static void main(String[] args) throws IOException {
        inputData();

        solution();

        System.out.println(sb);
    }
}
