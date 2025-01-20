package boj;

import java.io.*;
import java.util.*;

public class problem2531 {
    /*
     * 1. 벨트의 임의의 한 위치부터 k개의 접시를 연속해서 먹을 경우 할인된 정액 가격으로 제공
     * 2. 고객에게 초밥의 종류 하나가 쓰인 쿠폰을 발행
     * - 1번 행상에 참가할 경우 이 쿠폰에 적혀진 종류의 초밥 하나를 무료로 제공
     * - 이 번호에 적힌 초밥이 현재 벨트 위에 없을 경우, 요리사가 새로 만들어서 제공한다.
     * 
     * 벨트의 상태
     * 메뉴에 있는 초밥의 가짓수, 연속해서 먹는 접시 개수, 쿠폰 번호가 주어졌을 때
     * 손님이 먹을 수 있는 초밥 가짓수의 최대값
     * 
     * (접시 개수) * (연속접시)
     * 30,000 * 3,000 = 90,000,000
     */


    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int numOfPlate;
    static int numOfSusi;
    static int maxCount;
    static int coupon;

    static int[] susiList;
    static boolean[] visited;

    static int answer;

    static void inputData() throws IOException {
        answer = 0;

        st = new StringTokenizer(br.readLine().trim());

        numOfPlate = Integer.parseInt(st.nextToken());
        numOfSusi = Integer.parseInt(st.nextToken());
        maxCount = Integer.parseInt(st.nextToken());
        coupon = Integer.parseInt(st.nextToken());

        susiList = new int[numOfPlate];

        visited = new boolean[numOfSusi + 1];

        visited[coupon] = true;

        for (int idx = 0; idx < numOfPlate; idx++) {
            int susi = Integer.parseInt(br.readLine().trim());
            susiList[idx] = susi;
        }
    }

    static void solution() {

        int count = 0;

        for (int idx = 0; idx < numOfPlate; idx++) {
            count = 1;
            visited = new boolean[numOfSusi + 1];

            visited[coupon] = true;

            for (int sudiIdx = idx; sudiIdx < idx + maxCount; sudiIdx++) {
                int susi = susiList[sudiIdx % numOfPlate];

                if (!visited[susi]) {
                    count++;
                    visited[susi] = true;
                }
            }

            answer = Math.max(answer, count);

        }

    }

    public static void main(String[] args) throws IOException {
        inputData();

        solution();

        System.out.println(answer);
    }
}
