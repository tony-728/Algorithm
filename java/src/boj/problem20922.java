package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem20922 {
    /*
     * 투 포인터 문제
     * 
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;


    static int length;
    static int standard;
    static int[] numberList;
    static int[] numberCount = new int[100001];

    static int result;


    static void inputTestCase() throws IOException {

        result = 0;
        st = new StringTokenizer(br.readLine().trim());

        length = Integer.parseInt(st.nextToken());
        standard = Integer.parseInt(st.nextToken());

        numberList = new int[length];

        st = new StringTokenizer(br.readLine().trim());
        for (int idx = 0; idx < length; idx++) {
            numberList[idx] = Integer.parseInt(st.nextToken());
        }

    }

    static void solution() {

        int start = 0;
        int end = 0;


        while (end < length) {

            while(end < length && numberCount[numberList[end]]+ 1 <= standard){
                numberCount[numberList[end]]++;
                end++;
            }

            int len = end - start;
            result = Math.max(result, len);
            numberCount[numberList[start]]--;
            start++;
        }
    }

    public static void main(String[] args) throws IOException {

        inputTestCase();
        solution();

        System.out.println(result);

    }
}
