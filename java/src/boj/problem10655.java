package boj;

import java.io.*;
import java.util.*;

public class problem10655 {
    /*
     * N개의 체크포인트가 있다.
     * - 모든 체크포인트를 순서대로 방문한 후 N번 체크포인트에서 끝나야지 마라톤이 끝난다.
     * 
     * 중간에 있는 체크포인트 한개를 건너뛴다.
     * - 1, N번은 안된다.
     * - 건너뛰는 체크포인트는 1개이다.
     * 
     * 달려야하는 최소 거리
     * 거리는 멘하탄 거리로 측정한다.
     * 
     * 완탐
     * - 체크포인트 별로 누적합을 구한다.
     * - 모든 체크포인트에 대해서 경우를 따진다.
     * - 생략하는 체크포인트는 누적합을 이용해서 중간 경로를 빠르게 구한다.
     */

    static class CheckPoint {
        int rowIdx;
        int colIdx;

        public CheckPoint(int rowIdx, int colIdx) {
            this.rowIdx = rowIdx;
            this.colIdx = colIdx;
        }
    }

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int numOfCheckPoint;
    static CheckPoint[] checkPointList;
    static int[] sumList;

    static int answer;

    static void inputData() throws IOException {

        answer = Integer.MAX_VALUE;

        numOfCheckPoint = Integer.parseInt(br.readLine().trim());

        checkPointList = new CheckPoint[numOfCheckPoint];
        sumList = new int[numOfCheckPoint];

        for (int idx = 0; idx < numOfCheckPoint; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            int row = Integer.parseInt(st.nextToken());
            int col = Integer.parseInt(st.nextToken());

            checkPointList[idx] = new CheckPoint(row, col);

            if (idx == 0) {
                continue;
            }

            // 전처리, 체크포인트 거리의 누적합
            int distance = Math.abs(row - checkPointList[idx - 1].rowIdx)
                    + Math.abs(col - checkPointList[idx - 1].colIdx);

            sumList[idx] = sumList[idx - 1] + distance;
        }
    }

    static int getDistance(CheckPoint left, CheckPoint right) {

        return Math.abs(left.rowIdx - right.rowIdx) + Math.abs(left.colIdx - right.colIdx);
    }

    static void solution() {

        // first, last checkpoint를 제외한 체크포인트에 대해서 최소값을 구한다.
        for (int idx = 1; idx < numOfCheckPoint - 1; idx++) {
            int leftSum = sumList[idx - 1];

            int rightSum = sumList[numOfCheckPoint - 1] - sumList[idx + 1];

            CheckPoint left = checkPointList[idx - 1];
            CheckPoint right = checkPointList[idx + 1];

            int newDistance = getDistance(left, right);

            newDistance = newDistance + leftSum + rightSum;

            answer = Math.min(answer, newDistance);
        }

    }

    public static void main(String[] args) throws IOException {
        inputData();

        solution();

        System.out.println(answer);
    }
}
