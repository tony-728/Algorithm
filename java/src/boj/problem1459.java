package boj;

import java.io.*;
import java.util.*;

public class problem1459 {

    /*
     * 도시의 크기는 무한대
     * 도시의 세로 도로는 모든 정수 x좌표가 있다.
     * 도시의 가로  도로는 모즌 정수 y좌표마다 있다.
     * 
     * 세준은 0,0에 있다.
     * (x,y)에 위치한 집으로 가려고 한다.
     * 
     * 세준이 걸을 수 있는 방법은 2가지
     * - 도로를 따라서 가로나 세로를 한 블록 움직여서 이번 사거리에서 저 사거리로 이동
     * - 블록을 대각선으로 가로지르는 방법
     * 
     * 집까지 가는 최소시간
     * 
     * 
     * 대각선으로 가는 것 / 가로,세로로 가는 것 중 빠른 경로를 찾는다.
     * 
     * 
     */


    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static long strightTime;
    static long diagonalTime;

    static long[] home;

    static long answer;

    static void inputData() throws IOException {
        st = new StringTokenizer(br.readLine().trim());

        long x = Long.parseLong(st.nextToken());
        long y = Long.parseLong(st.nextToken());

        strightTime = Long.parseLong(st.nextToken());
        diagonalTime = Long.parseLong(st.nextToken());

        home = new long[] {x, y};
    }

    public static void main(String[] args) throws IOException {

        inputData();

        // 대각, 가로+세로 중 작은 값을 찾는다.
        long minDiagnoalTime = Math.min(strightTime * 2, diagonalTime);

        // 대각과 직선 중 작은 값을 찾는다.
        long minTime = Math.min(strightTime, diagonalTime);

        // 세준의 위치와 목적지에 위치를 비교한다.
        long minLoc = Math.min(home[0], home[1]);

        long maxLoc = Math.max(home[0], home[1]);

        // 대각선까지 최단거리로 이동
        answer += minLoc * minDiagnoalTime;

        // 대각으로 이동 후 남은 거리
        long temp = maxLoc - minLoc;

        // 남은 거리가 홀수면 직선으로 한번 이동
        if (temp % 2 == 1) {
            answer += strightTime;
            temp -= 1;
        }

        // 남은 거리는 최단 경로로 이동
        answer += (temp * minTime);


        System.out.println(answer);
    }
}
