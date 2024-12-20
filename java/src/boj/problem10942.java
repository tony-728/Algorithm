package boj;

import java.io.*;
import java.util.*;

public class problem10942 {
    /*
     * N개의 자연수를 칠판에 적는다. 
     * M번 질문은 한다.
     * 
     * 각 질문은 두 정수 S와 E로 나타낼 수 있으며 S번째 수부터 E번째까지 수가 팰린드롬을 이루는지 물어보며
     * 명우는 각 질문에 대해 팰린드롬이다 아니다를 말한다.
     * 
     * ex) 칠판에 적은 수 
     * 1,2,1,3,1,2,1
     * 
     * S=1, E=3인 경우 
     * - 1,2,1 은 팰린드롬이다.
     * 
     * S=2, E=5인 경우
     * - 2,1,3,1은 팰린드롬이 아니다.
     * 
     * 자연수 N개와 질문 M개가 주어졌을 때 명우의 대답을 구하는 프로그램을 작성하시오
     * 
     * 입력값이 크기 때문에 한번의 반복으로 해결해야함함
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder answer = new StringBuilder();

    static int numOfNumber;
    static int[] numberList;

    static int numOfQuestion;

    static int start;
    static int end;

    static void checkPalindrome(int start, int end) {

        while(start <= end){

            if(numberList[start] != numberList[end]){
                answer.append(0).append("\n");
                return;
            }
            start++;
            end--;
        }

       answer.append(1).append("\n");
       return;

    }

    public static void main(String[] args) throws IOException {

        numOfNumber = Integer.parseInt(br.readLine().trim());

        numberList = new int[numOfNumber + 1];

        st = new StringTokenizer(br.readLine().trim());

        for (int idx = 1; idx < numOfNumber + 1; idx++) {
            int val = Integer.parseInt(st.nextToken());

            numberList[idx] = val;
        }

        numOfQuestion = Integer.parseInt(br.readLine().trim());

        for (int idx = 0; idx < numOfQuestion; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            start = Integer.parseInt(st.nextToken());
            end = Integer.parseInt(st.nextToken());

            checkPalindrome(start, end);

        }

        System.out.println(answer);

    }
}
