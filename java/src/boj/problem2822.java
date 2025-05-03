package boj;

import java.io.*;
import java.util.*;

public class problem2822 {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        PriorityQueue<int[]> scoreList = new PriorityQueue<>((int[] o1, int[] o2) -> {
            return o2[1] - o1[1];
        });

        for (int idx = 1; idx <= 8; idx++) {
            int score = Integer.parseInt(br.readLine().trim());

            scoreList.add(new int[] {idx, score});
        }

        int idx = 0;
        int answer = 0;
        int[] arrOfIndex = new int[5];
        StringBuilder sb = new StringBuilder();

        while (idx < 5) {
            int[] score = scoreList.poll();

            answer += score[1];
            arrOfIndex[idx++] = score[0];
        }

        sb.append(answer).append("\n");

        Arrays.sort(arrOfIndex);
        for (Integer index : arrOfIndex) {
            sb.append(index).append(" ");
        }

        System.out.println(sb);
    }
}
