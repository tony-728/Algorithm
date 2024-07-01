package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class problem14229 {
    /*
     * 백만개의 정수를 오름차순으로 정렬한 후 50만번째 수를 출력하라
     * 
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static final int ARRAY_SIZE = 1000000;
    static final int ANSWER_IDX = 500000;

    static int[] array = new int[ARRAY_SIZE];

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine().trim());

        for (int idx = 0; idx < ARRAY_SIZE; idx++) {
            array[idx] = Integer.parseInt(st.nextToken());
        }

        Arrays.sort(array);

        System.out.println(array[ANSWER_IDX]);

    }
}
