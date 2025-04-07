package boj;

import java.io.*;
import java.util.*;

public class problem18870 {
    /*
     * N개의 좌표가 있다.
     * 
     * x를 압축한 결과는 xi > xj를 만족하는 서로 다른 좌표의 개수와 같아야 한다.
     * 
     * N개의 좌표를 압축한 결과를 출력하라
     * 
     * 
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int numOfLoc = Integer.parseInt(br.readLine().trim());
        int[] arrOfLoc = new int[numOfLoc];
        int[] sortedArrOfLoc = new int[numOfLoc];

        st = new StringTokenizer(br.readLine().trim());

        for (int idx = 0; idx < numOfLoc; idx++) {
            int loc = Integer.parseInt(st.nextToken());
            arrOfLoc[idx] = loc;
            sortedArrOfLoc[idx] = loc;
        }

        Arrays.sort(sortedArrOfLoc);

        HashMap<Integer, Integer> hm = new HashMap<Integer, Integer>();

        int hmIdx = 0;

        for (int idx = 0; idx < numOfLoc; idx++) {
            if (hm.getOrDefault(sortedArrOfLoc[idx], -1) == -1) {
                hm.put(sortedArrOfLoc[idx], hmIdx++);
            }
        }

        StringBuilder sb = new StringBuilder();
        for (int idx = 0; idx < numOfLoc; idx++) {
            sb.append(hm.get(arrOfLoc[idx])).append(" ");
        }

        System.out.println(sb);
    }
}
