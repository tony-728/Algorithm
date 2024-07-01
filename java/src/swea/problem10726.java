package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem10726 {
    /*
     * M의 이진수 표현의 마지막 N비트가 모두 1로 켜져 있는지 확인
     * 마지막 N개의 비트가 모두 켜져 있다면 ON 아니면 OFF
     * 
     * 테스트 케이스가 주어진다.
     * N, M이 테스트 케이스만큼 주어진다.
     */


    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();
    static int testCase;
    static int bitSize;
    static int value;

    static final String ON = "ON";
    static final String OFF = "OFF";

    static String checkBit() throws IOException {
        st = new StringTokenizer(br.readLine().trim());

        bitSize = Integer.parseInt(st.nextToken());
        value = Integer.parseInt(st.nextToken());

        for (int idx = 0; idx < bitSize; idx++) {
            if ((value & (1 << idx)) != (1 << idx)) {
                return OFF;
            }
        }

        return ON;
    }

    public static void main(String[] args) throws IOException {
        testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= testCase; tc++) {

            sb.append("#").append(tc).append(" ");
            sb.append(checkBit()).append("\n");
        }

        System.out.println(sb);
    }
}
