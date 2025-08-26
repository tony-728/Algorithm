package boj;

import java.util.*;
import java.io.*;

public class problem11722 {
    /*
     * LIS를 뒤집어서 진행
     * 
     * 입력 배열에 뒤에서 부터 확인한다. LIS는 동일하게 진행함
     */
    static int sizeOfArray;
    static int[] array;
    static int answer;

    static void inputData() throws IOException {

        answer = 0;

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        StringTokenizer st;

        sizeOfArray = Integer.parseInt(br.readLine().trim());

        array = new int[sizeOfArray];

        st = new StringTokenizer(br.readLine().trim());

        for (int idx = 0; idx < sizeOfArray; idx++) {
            int value = Integer.parseInt(st.nextToken());
            array[idx] = value;
        }
    }

    static void solution() {
        List<Integer> list = new ArrayList<>();

        list.add(array[sizeOfArray - 1]);

        for (int idx = sizeOfArray - 2; idx >= 0; idx--) {

            int value = array[idx];

            if (list.get(list.size() - 1) < value) {
                list.add(value);
            } else {
                int LDSIdx = Collections.binarySearch(list, value);

                if (LDSIdx < 0) {
                    LDSIdx = (LDSIdx * -1) - 1;
                    list.set(LDSIdx, value);
                }
            }
        }

        answer = list.size();
    }

    public static void main(String[] args) throws IOException {
        inputData();
        solution();
        System.out.println(answer);

    }
}
