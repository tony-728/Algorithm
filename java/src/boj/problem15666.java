package boj;

import java.io.*;
import java.util.*;

public class problem15666 {
    /*
     * N개의 자연수, M개의 자연수가 주였을 때 조건을 만족하는 길이가 M인 수열을 모두 구하라
     * - N개 자연수 m개를 고른 수열
     * - 같은 수를 여러 번 골라도 된다.
     * - 고른 수열은 비내림차순
     * 
     * 중복조합 + set
     */

    static int elementSize;
    static int selectSize;
    static int[] arrOfElement;
    static int[] arrOfSelect;

    static Set<String> hSet = new HashSet<>();
    static StringBuilder sb = new StringBuilder();

    static void inputData() throws IOException {

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st = new StringTokenizer(br.readLine().trim());

        elementSize = Integer.parseInt(st.nextToken());
        selectSize = Integer.parseInt(st.nextToken());

        st = new StringTokenizer(br.readLine().trim());

        arrOfElement = new int[elementSize];
        arrOfSelect = new int[selectSize];

        for (int idx = 0; idx < elementSize; idx++) {
            arrOfElement[idx] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(arrOfElement);
    }

    static void solution(int elementIdx, int selectIdx) {

        if (elementIdx == elementSize) {
            return;
        }

        if (selectIdx == selectSize) {
            if (!hSet.contains(Arrays.toString(arrOfSelect))) {
                hSet.add(Arrays.toString(arrOfSelect));
                for (Integer value : arrOfSelect) {
                    sb.append(value).append(" ");
                }
                sb.append("\n");
            }
            return;
        }


        arrOfSelect[selectIdx] = arrOfElement[elementIdx];
        solution(elementIdx, selectIdx + 1);

        arrOfSelect[selectIdx] = arrOfElement[elementIdx];
        solution(elementIdx + 1, selectIdx);
    }

    public static void main(String[] args) throws IOException {
        inputData();

        solution(0, 0);

        System.out.println(sb);
    }
}
