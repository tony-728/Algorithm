package boj;

import java.io.*;
import java.util.*;

public class problem10815 {
    /*
     * N개의 숫자카드가 주어질 때 M개의 정수에 대해서 존재유무 판단
     * 
     * 이진탐색
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int numOfCard;
        int numOfNumber;

        int[] arrOfCard;

        numOfCard = Integer.parseInt(br.readLine().trim());
        arrOfCard = new int[numOfCard];

        st = new StringTokenizer(br.readLine().trim());
        for (int idx = 0; idx < numOfCard; idx++) {
            arrOfCard[idx] = Integer.parseInt(st.nextToken());
        }

        StringBuilder sb = new StringBuilder();

        Arrays.sort(arrOfCard);

        numOfNumber = Integer.parseInt(br.readLine().trim());

        st = new StringTokenizer(br.readLine().trim());
        for (int idx = 0; idx < numOfNumber; idx++) {
            int card = Integer.parseInt(st.nextToken());

            boolean flag = false;

            // 이진 탐색
            int left = 0;
            int right = numOfCard - 1;
            while (left <= right) {
                int mid = (left + right) / 2;

                if (arrOfCard[mid] < card) {
                    left = mid + 1;
                } else if (arrOfCard[mid] > card) {
                    right = mid - 1;
                } else {
                    flag = true;
                    break;
                }
            }

            if (flag) {
                sb.append(1).append(" ");
            } else {
                sb.append(0).append(" ");

            }
        }

        System.out.println(sb);
    }
}
