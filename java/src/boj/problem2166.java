package boj;

import java.io.*;
import java.util.*;

public class problem2166 {
    /*
     * 2차원 평면상에 N개의 점으로 이루어진 다각형이 있다. 
     * 이 다각형의 면적을 구하라
     * 
     * 다각형의 넓이 구하기
     * - 신발끈 공식이라고 불리는 알고리즘을 이용해서 다각형의 넓이를 구할 수 있다.
     * 신발끈 공식의 한계는
     * 1. 주어진 좌표들은 다각형의 시계 또는 반시계 방향으로 차례대로 주어져야 한다.
     * 2. 주어준 좌표들이 중간에 접촉하거나 이루는 선분들이 교차하면 안된다.
     * 
     * 주어진 좌표는 절대값이 10만을 넘지 않는다.
     * 10만 * 10만 100억이므로 int 범위를 넘는다.
     * 
     */

    static int numOfPoint;
    static int[][] arrOfPoint;

    static double answer;

    static void inputData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        numOfPoint = Integer.parseInt(br.readLine().trim());
        arrOfPoint = new int[numOfPoint][2];

        for (int idx = 0; idx < numOfPoint; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            int x = Integer.parseInt(st.nextToken());
            int y = Integer.parseInt(st.nextToken());

            arrOfPoint[idx][0] = x;
            arrOfPoint[idx][1] = y;
        }

        // 입력이 다각형을 이루는 순서대로 N개의 좌표가 주어지므로 정렬하지 않아도 된다.
    }

    static void solution() {
        // 신발끈 공식
        // (x1*y2 + x2*y3 + ... + xi*y(i+1)) - (x2*y1 + x3*y2 + ... + xi*y(i-1)) / 2
        // 마지막 인덱스는 다시 처음 인덱스와 계산해야함

        long result = 0L;
        long result2 = 0L;

        for (int idx = 0; idx < numOfPoint; idx++) {
            result += ((long) arrOfPoint[idx][0]) * arrOfPoint[(idx + 1) % numOfPoint][1];
            result2 += ((long) arrOfPoint[(idx + 1) % numOfPoint][0]) * arrOfPoint[idx][1];
        }

        answer = Math.abs(result - result2) / 2.0;

    }

    public static void main(String[] args) throws IOException {
        inputData();

        solution();

        System.out.println(String.format("%.1f", answer));
    }
}
