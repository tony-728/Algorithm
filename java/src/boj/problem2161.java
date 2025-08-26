package boj;

import java.util.*;
import java.io.*;

public class problem2161 {
    /*
     * 제일 위에 카드 버리고 다음 카드는 아래로
     * 
     * 위 순서를 카드가 1장 남을 때까지 반복
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        int numOfCard = Integer.parseInt(br.readLine().trim());

        Deque<Integer> q = new ArrayDeque<>();

        for (int idx = 1; idx <= numOfCard; idx++) {
            q.add(idx);
        }

        StringBuilder sb = new StringBuilder();
        while (!q.isEmpty()) {
            int card = q.pop();

            sb.append(card).append(" ");

            if (q.isEmpty()) {
                break;
            }

            if (q.size() == 1) {
                sb.append(q.pop());
                break;
            }

            card = q.pop();

            q.addLast(card);
        }

        System.out.println(sb);
    }
}