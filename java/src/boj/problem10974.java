package boj;

import java.io.*;

public class problem10974 {
    /*
     * N이 주어졌을 때 1부터 N까지의 수로 이루어진 순열을 사전순으로 출력하는 프로그래
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int selectCount;
    static int[] selectList;
    static int[] elementList;
    static boolean[] visited;

    static void permutation(int selectIdx) {

        if (selectIdx == selectCount) {
            for (int idx = 0; idx < selectCount; idx++) {
                sb.append(selectList[idx]).append(" ");
            }
            sb.append("\n");
            return;
        }

        for (int idx = 0; idx < selectCount; idx++) {
            if (visited[idx]) {
                continue;
            }

            selectList[selectIdx] = elementList[idx];
            visited[idx] = true;

            permutation(selectIdx + 1);

            visited[idx] = false;
        }


    }

    public static void main(String[] args) throws IOException {

        selectCount = Integer.parseInt(br.readLine().trim());
        selectList = new int[selectCount];
        elementList = new int[selectCount];
        visited = new boolean[selectCount];


        for (int idx = 0; idx < selectCount; idx++) {
            elementList[idx] = idx + 1;
        }

        permutation(0);

        System.out.println(sb);

    }
}
