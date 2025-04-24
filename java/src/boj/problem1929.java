package boj;

import java.io.*;
import java.util.*;

public class problem1929 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int M, N;

        st = new StringTokenizer(br.readLine().trim());

        M = Integer.parseInt(st.nextToken());
        N = Integer.parseInt(st.nextToken());

        StringBuilder sb = new StringBuilder();

        boolean[] visited = new boolean[N + 1];

        for (int idx = 2; idx <= N; idx++) {
            if (idx >= M && !visited[idx]) {
                sb.append(idx).append("\n");
            }
            for (int prime = idx + idx; prime <= N; prime = prime + idx) {
                visited[prime] = true;
            }
        }


        System.out.println(sb);
    }
}
