package boj;

import java.io.*;
import java.util.*;


public class problem10819 {
    /*
     * N개의 정수가 주어진다. 정수의 순서를 적절히 배치해서 주어진 식의 최대값을 구하라
     * 
     * N은 최대 8이므로 순열을 돌려도 괜찮을듯
     */

    static int numOfArray;
    static int[] arrOfNumber;

    static int[] selectList;
    static boolean[] isSelectList;

    static int answer;

    static void inputData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        numOfArray = Integer.parseInt(br.readLine().trim());
        arrOfNumber = new int[numOfArray];

        selectList = new int[numOfArray];
        isSelectList = new boolean[numOfArray];

        st = new StringTokenizer(br.readLine().trim());

        for (int idx = 0; idx < numOfArray; idx++) {
            arrOfNumber[idx] = Integer.parseInt(st.nextToken());
        }
    }

    static void solution(int selectIdx) {

        if (selectIdx == numOfArray) {
            int sum = 0;
            for (int idx = 0; idx < numOfArray - 1; idx++) {
                sum += Math.abs(selectList[idx] - selectList[idx + 1]);
            }

            if (answer < sum) {
                answer = sum;
            }

            return;
        }

        for (int idx = 0; idx < numOfArray; idx++) {

            if (isSelectList[idx]) {
                continue;
            }

            isSelectList[idx] = true;
            selectList[selectIdx] = arrOfNumber[idx];
            solution(selectIdx + 1);

            isSelectList[idx] = false;

        }


    }

    public static void main(String[] args) throws IOException {
        inputData();

        solution(0);

        System.out.println(answer);
    }
}
