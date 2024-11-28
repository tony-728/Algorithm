package boj;

import java.io.*;
import java.util.*;

public class problem1138 {
    /*
     * N명의 사람이 한줄로 선다. 
     * 
     * 사람들은 자기보다 큰 사람이 왼쪽에 몇 명 있었는지만 기억한다.
     * n명의 사람이 있고 사람들의 키는 1부터 N까지 모두 다르다.
     * 
     * 사람들이 기억하는 정보가 주어질 때 줄을 어떻게 서야 하는지 출력하라
     * 
     * 키가 1인 사람부터 차례대로 자기보다 키가 큰 사람이 왼쪽에 몇 명이 있는지 주어진다.
     * 
     * 순서대로 1부터 왼쪽에 있는 사람 수만큼 이동 후 채워넣는다.
     * 이미 자리가 채워진 경우 한칸 이동한다.
     */


    static int numOfPerson;
    static int[] personList;

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int[] answer;

    public static void main(String[] args) throws IOException {
        numOfPerson = Integer.parseInt(br.readLine().trim());

        personList = new int[numOfPerson + 1];

        answer = new int[numOfPerson];

        st = new StringTokenizer(br.readLine().trim());

        for (int idx = 0; idx < numOfPerson; idx++) {
            personList[idx] = Integer.parseInt(st.nextToken());
        }


        for (int idx = 0; idx < numOfPerson; idx++) {
            int count = personList[idx];

            int answerIdx = 0;

            // 왼쪽 사람 카운트
            while (count > 0) {
                if (answer[answerIdx] == 0) {
                    count--;
                }
                answerIdx++;
            }

            // 빈자리가 나올 때까지 카운트
            while(answer[answerIdx] > 0){
                answerIdx++;
            }

            answer[answerIdx] = idx + 1;
        }

        for(Integer val: answer){
            sb.append(val).append(" ");
        }

        System.out.println(sb);
    }
}
