package boj;

import java.io.*;
import java.util.*;

public class problem1715 {

    /*
     * 두 묶음의 숫자 카드가 있다.
     * 각 묶음의 카드의 수를 A, B라면
     * 두 묶음을 합쳐서 하나로 만드는 데 A+B번의 비교를 해야 한다.
     * 
     * N개의 숫자 카드 묶음이 각각 주어질 때 최소 비교 횟수
     * 
     * 카드 묶음 최대는 1000개
     * 
     * 그리디
     * 
     * 합을 구하고 그 합을 다시 덱에 넣어서 최소를 다시 꺼낸다.
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    static int numOfDeck;
    static PriorityQueue<Long> deckList = new PriorityQueue<>();
    static long answer;

    static void inputData() throws IOException {
        numOfDeck = Integer.parseInt(br.readLine().trim());

        for (int idx = 0; idx < numOfDeck; idx++) {
            deckList.add(Long.parseLong(br.readLine().trim()));
        }
    }

    static void solution() {

        while (!deckList.isEmpty()) {
            long left = deckList.poll();

            if (deckList.isEmpty()) {
                break;
            }

            long right = deckList.poll();

            long sum = left + right;

            answer += sum;

            deckList.add(sum);
        }

    }

    public static void main(String[] args) throws IOException {
        inputData();

        solution();

        System.out.println(answer);

    }
}
