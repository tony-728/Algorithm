package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem11501 {

    /*
     * 
     * 뒤에서부터 
     * 
     * 가장 큰 값을 저장
     * 
     * 작은 값이 나오면 계속 판매
     * 
     * 그러다 저장한 값보다 큰 값이 나오면 갱신 후 다시
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static StringBuilder sb = new StringBuilder();

    static int testCase;
    static int totalDay;
    static int[] stockList;

    static long result;

    static void inputTestCase() throws IOException {
        totalDay = Integer.parseInt(br.readLine().trim());

        result = 0;

        stockList = new int[totalDay];

        st = new StringTokenizer(br.readLine().trim());

        for (int d = 0; d < totalDay; d++) {
            stockList[d] = Integer.parseInt(st.nextToken());
        }
    }

    static void calculate() {

        int standard = 0;

        for (int idx = totalDay - 1; idx > -1; idx--) {
            if(stockList[idx] > standard){
                standard = stockList[idx];
                continue;
            }

            result += (standard - stockList[idx]);
        }

        sb.append(result).append("\n");
    }

    public static void main(String[] args) throws IOException {

        testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 0; tc < testCase; tc++) {
            inputTestCase();

            calculate();
        }

        System.out.print(sb);


    }
}
