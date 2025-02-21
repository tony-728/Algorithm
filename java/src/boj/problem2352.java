package boj;

import java.io.*;
import java.util.*;

public class problem2352 {
    /*
     * n개의 포트를 다른 n개의 포트와 연결해야 할 떄가 있다.
     * 
     * 연결선이 서로 겹치지않고 교차하지 않도록 하면서 최대 몇 개까지 연결할 수 있는지 구하라
     * 
     * 포트는 최대 4만개
     * 
     * 재귀 
     * - 시간초과
     * 
     * 최장 부분 수열문제
     * - LIS
     * - dp를 사용
     * dp테이블에 마지막 값보다 현재 포트가 크면 포트를 추가
     * dp테이블의 마지막 값보다 현재 포트가 작으면 dp 테이블에서 현재 포트가 위치할 수 있는 곳을 찾아서 갱신
     * 
     * 최종적으로 dp테이블의 길이가 LIS의 길이가 된다.
     * 
     * - dp 테이블을 탐색하는 것 때문에 n^2이 된다.
     * -> 문제에서 최대 4만이므로 16억으로 가능은 하다
     * -> dp 테이블에 값을 빨리 찾기 위한 방법으로 이진탐색을 적용하면 nlogn으로 가능하다
     * 
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int numOfPort;
    static int[] portList;
    static int[] dp;
    static int lengthOfDp;

    static void inputData() throws IOException {

        lengthOfDp = 0;

        numOfPort = Integer.parseInt(br.readLine().trim());

        portList = new int[numOfPort];
        dp = new int[numOfPort + 1];

        st = new StringTokenizer(br.readLine().trim());

        for (int idx = 0; idx < numOfPort; idx++) {
            portList[idx] = Integer.parseInt(st.nextToken());
        }
    }

    static int binarySearch(int left, int right, int key) {
        int mid = 0;

        while (left < right) {
            mid = (left + right) / 2;
            if (dp[mid] < key) {
                left = mid + 1;
            } else {
                right = mid;
            }
        }

        return right;
    }

    static void solution() {

        for (int idx = 0; idx < numOfPort; idx++) {
            // dp 테이블의 마지막 값보다 현재 포트가 크다
            if (portList[idx] > dp[lengthOfDp]) {
                // dp 테이블의 가장 뒤에 추가해야하므로 인덱스를 증가한 후 추가한다.
                dp[++lengthOfDp] = portList[idx];

                // dp 테이블의 마지막 값보다 현재 포트가 작다
                // dp 테이블에서 현재 포트의 최적의 위치를 찾아서 갱신
                // 이진탐색으로 탐색에 logn 만큼 쓰인다.
            } else {
                int portIdx = binarySearch(0, lengthOfDp, portList[idx]);
                dp[portIdx] = portList[idx];
            }
        }


    }

    public static void main(String[] args) throws IOException {
        inputData();

        solution();

        System.out.println(lengthOfDp);
    }
}
