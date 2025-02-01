package boj;

import java.util.*;
import java.io.*;

public class problem20115 {
    /*
     * 임의의 두 에너지 드링크를 합치는 과정
     * 1. 임의의 서로 다른 두 에너지 드링크를 고른다.
     * 2. 한쪽 에너지 드링크를 다른 에너지 드링크에 모두 붓는다. 
     * - 단, 붓는 과정에서 원래 양의 절반을 바닥에 흘리게 된다.
     * 3. 다 붓고 남은 빈 에너지 드링크는 버린다.
     * 4. 1-3 과정을 에너지 드링크가 하나만 남을 때까지 반복한다.
     * 
     * 합쳐진 에너지 드링크의 양을 최대로 하려고 한다.
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static int numOfDrink;
    static int[] drinkList;
    static double answer;

    static void inputData() throws IOException {
        numOfDrink = Integer.parseInt(br.readLine().trim());
        drinkList = new int[numOfDrink];

        st = new StringTokenizer(br.readLine().trim());

        for (int idx = 0; idx < numOfDrink; idx++) {
            drinkList[idx] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(drinkList);
    }

    static void solution() {
        // 정렬 후 가장 큰 값을 제외하고 나머지 값을 절반으로 나눈 값을 더한다.
        for (int idx = 0; idx < numOfDrink; idx++) {
            if (idx == numOfDrink - 1) {
                answer += drinkList[idx];
            } else {
                answer += drinkList[idx] / 2.0;
            }
        }
    }

    public static void main(String[] args) throws IOException {
        inputData();

        solution();

        System.out.println(answer);
    }
}
