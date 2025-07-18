package boj;

import java.io.*;
import java.util.*;

public class problem1476 {

    /*
     * 수를 3개 이용해서 연도를 나타낸다.
     * 
     * 지구: E, 태양: S, 달: M
     * 1<= E <= 15
     * 1<= S <= 28
     * 1<= M <= 19
     * 
     * 1년은 1 1 1
     * 1년이 지날 때마다 세 수는 모두 1씩 증가한다.
     * 수의 범위가 넘어가면 다시 1이 된다.
     * 
     * 15년은 15 15 15
     * 16년은 1 15 15
     * 
     * E S M이 주어졌을 때 지구의 연도를 구하라
     * 
     * 모든 수를 확인해야한다. 대신 15, 28, 19의 최소공배수는 7980이므로 7980까지만 확인하면 된다.
     * 
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine().trim());

        int E = Integer.parseInt(st.nextToken());
        int S = Integer.parseInt(st.nextToken());
        int M = Integer.parseInt(st.nextToken());

        int answer = 0;

        int maxE = 15;
        int maxS = 28;
        int maxM = 19;

        for (int idx = 1; idx <= 7980; idx++) {
            int modE = idx % maxE;
            int modS = idx % maxS;
            int modM = idx % maxM;

            if (modE == 0) {
                modE = maxE;
            }

            if (modS == 0) {
                modS = maxS;
            }

            if (modM == 0) {
                modM = maxM;
            }

            if (modE == E && modS == S && modM == M) {
                answer = idx;
                break;
            }

        }

        System.out.println(answer);
    }
}
