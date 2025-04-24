package boj;

import java.io.*;
import java.util.*;

public class problem15650 {

    static int N, M;
    static StringBuilder sb = new StringBuilder();
    static int[] selectList;
    static boolean[] visited;

    static void combi(int elementIdx, int selectIdx) {
        if (selectIdx >= M) {
            for (int idx = 0; idx < M; idx++) {
                if (selectList[idx] > 0) {
                    sb.append(selectList[idx]).append(" ");
                }
            }
            sb.append("\n");
            return;
        }
        
        if (elementIdx > N) {
            return;
        }
        // 선택했다.
        selectList[selectIdx] = elementIdx;
        combi(elementIdx + 1, selectIdx + 1);

        // 선택하지 않았다.
        selectList[selectIdx] = 0;
        combi(elementIdx + 1, selectIdx);

    }

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        st = new StringTokenizer(br.readLine().trim());

        N = Integer.parseInt(st.nextToken());
        M = Integer.parseInt(st.nextToken());

        selectList = new int[N];
        visited = new boolean[N];

        combi(1, 0);

        System.out.println(sb);
    }
}
