package boj;

import java.io.*;
import java.util.*;

public class problem2164 {
    /*
     * N장의 카드
     * 1부터 N까지 번호가 있다.
     * 1번 카드가 제일 위에 N번 카드가 제일 아래인 상태
     * 
     * 1. 제일 위에 있는 카드를 버린다.
     * 2. 그 다음, 제일 위에 있는 카드를 제일 아래에 있는 카드 밑으로 옮긴다.
     * 
     * N이 주어질 때 제일 마지막에 남게 되는 카드를 구하라
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int numOfCard;

    public static void main(String[] args) throws IOException {
        numOfCard = Integer.parseInt(br.readLine().trim());

        Deque<Integer> q = new ArrayDeque<>();

        for (int idx = 1; idx <= numOfCard; idx++) {
            q.addLast(idx);
        }

        int card = 0;

        while (!q.isEmpty()) {
            card = q.pollFirst();

            if(q.isEmpty()){
                break;
            }

            card = q.pollFirst();

            q.addLast(card);
        }

        System.out.println(card);

    }
}
