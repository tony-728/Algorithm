package boj;

import java.io.*;
import java.util.*;

public class problem1052 {
    /*
     * 지민이는 N개의 물병을 갖고 있다.
     * 각 물병에는 물을 무한대로 부을 수 있다.
     * - 처음 모든 물병은 물이 1리터 있다.
     * - N의 최대값은 1000만
     * 
     * 물병을 다른 장소로 옮기려고 한다.
     * - 한 번에 k개의 물병을 옮길 수 있다.
     * 
     * 물병의 물을 적절히 재분배해서 k개를 넘지 않는 비어있지 않은 물병을 만들려고 한다.
     * 
     * 재분배 규칙
     * - 같은 양의 물이 들어있는 물병 두개를 고른다.
     * - 한 개의 물병에 다른 한쪽에 있는 물을 모두 붓는다.
     * - 필요한 만큼 반복한다.
     * 
     * 상점에서 물병을 살 수 있다.
     * - 상점에서 사는 물병도 물이 1리터 있다.
     * 
     * 상점에서 사야하는 물병의 최솟값을 구하라
     * 
     * 완탐
     * - 2개씩 묶여서 하나의 물병을 만들 수 있다.
     * - 주어진 물병을 2로 나눈 나머지가 1일 때가 주어진 목표 물병개수보다 작거나 같을 때까지 반복한다.
     * 
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int numOfBottle;
    static int targetBottle;

    static int answer;

    static void solution() {

        int addBottle = 0;

        while (true) {

            int bottle = numOfBottle + addBottle;

            int count = 0;

            // 이 계산이 2진수의 1의 개수를 구하는 것과 동일하므로 함수를 사용할 수 있다.
            count = Integer.bitCount(bottle);
            // while (bottle > 0) {
            //     if (bottle % 2 == 1) {
            //         count++;
            //     }

            //     bottle /= 2;
            // }

            if (count <= targetBottle) {
                break;
            }

            addBottle++;
        }

        answer = addBottle;
    }


    public static void main(String[] args) throws IOException {
        answer = 0;

        st = new StringTokenizer(br.readLine().trim());

        numOfBottle = Integer.parseInt(st.nextToken());
        targetBottle = Integer.parseInt(st.nextToken());

        solution();

        System.out.println(answer);

    }
}
