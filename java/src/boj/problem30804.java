package boj;

import java.io.*;
import java.util.*;

public class problem30804 {
    /*
     * N개의 과일이 있는 탕후루
     * 과일의 종류는 1부터 9까지
     * 
     * 탕후루 앞부터 1,2,...,N번 과일이 있다.
     * 
     * 과일을 두 종류 이하로 사용해주세요
     * 
     * 막대의 앞쪽과 뒤쪽에서 몇 개의 과일을 빼서 두 종류 이하의 과일만 남긴다.
     * - 앞에서 a개 , 뒤에서 b개를 빼면 총 N-(a+b)개가 꽂혀있다.
     * 
     * 이렇게 만들 수 있는 과일을 두 종류 이하로 사용한 탕후루에서 
     * 과일의 개수가 가장 많은 탕후루의 과일 개수를 구하라
     * 
     * 투포인터
     * 시작, 종류 포인터를 0으로 설정하고 과일 종류가 2개이하이면 종료 포인터를 계속 증가시켜서 확인한다.
     * 종류를 2개 초과하면 시작 포인터를 증기시킨다.
     * 
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int numOfCandy;
    static int[] candyList;
    static int[] fruitsList = new int[10];

    static int answer;

    static void inputData() throws IOException {

        answer = 0;

        numOfCandy = Integer.parseInt(br.readLine().trim());

        candyList = new int[numOfCandy];

        st = new StringTokenizer(br.readLine().trim());

        for (int idx = 0; idx < numOfCandy; idx++) {
            int candy = Integer.parseInt(st.nextToken());

            candyList[idx] = candy;
        }
    }

    static boolean checkFruits(int[] fruitsList) {

        int count = 0;

        for (int idx = 1; idx < 10; idx++) {
            if (fruitsList[idx] > 0) {
                count++;
            }
        }

        return count <= 2 ? true : false;
    }

    static void countFruits(int[] fruitsList) {
        int count = 0;
        for (int idx = 1; idx < 10; idx++) {
            count += fruitsList[idx];
        }

        answer = Math.max(answer, count);
    }

    static void solution() {

        int start = 0;
        int end = 0;

        fruitsList[candyList[end++]]++;

        while (start != numOfCandy) {

            // 과일 종류가 2개 이하이면 과일 갯수 확인
            if (checkFruits(fruitsList)) {
                countFruits(fruitsList);

                if (end == numOfCandy) {
                    fruitsList[candyList[start++]]--;

                    continue;
                }
                fruitsList[candyList[end++]]++;

                // 과일 종류가 2개 초과면 시작 인덱스 감소
            } else {
                fruitsList[candyList[start++]]--;
            }
        }

    }

    public static void main(String[] args) throws IOException {
        inputData();

        solution();

        System.out.println(answer);

    }
}
