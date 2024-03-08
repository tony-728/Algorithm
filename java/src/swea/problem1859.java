package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem1859 {
    /*
     * 1. 연속된 N일 동안의 물건의 매매가를 예측하여 알고 있다.
     * 2. 하루에 최대 1만큼 구입할 수 있다.
     * 3. 판매는 얼마든지 할 수 있다.
     * 위 조건으로 최대한의 이득을 얻고록 하자
     * 2<=N<=1_000_000
     * price <= 10_000
     *
     * 연속된 수가 있다. 수가 같거나 증가하다가 다시 작아지는 순간을 찾아한다.
     * 모든 가격을 알기 때문에 확인을 뒤에서 부터 진행한다.
     * 현재 판매 가격보다 큰 가격이 나오면 교체한다.
     */

    public static BufferedReader br;
    public static StringTokenizer st;
    public static StringBuilder sb;

    public static int testCase;

    public static int totalDay; // 전체 날짜
    public static int sellPrice; // 판매 가격
    public static int currPrice; // 현재 가격
    public static int[] priceList; // 날짜별 가격
    public static int sellDay; // 판매 날짜인덱스

    public static long totalProfit;

    public static void main(String args[]) throws IOException {

        br = new BufferedReader(new InputStreamReader(System.in));

        testCase = Integer.parseInt(br.readLine().trim());
        sb = new StringBuilder();

        for (int tc = 1; tc <= testCase; tc++) {
            sb.append("#").append(tc).append(" ");

            totalDay = Integer.parseInt(br.readLine().trim());
            priceList = new int[totalDay];

            st = new StringTokenizer(br.readLine().trim());

            // N일 동안의 가격 입력
            for (int day = 0; day < totalDay; day++) {
                priceList[day] = Integer.parseInt(st.nextToken());
            }

            // N일 동안 중 판매해야하는 날짜 찾기
            // 순회를 역순으로 한다.
            // 현재 판매 가격보다 더큰 가격이 나오면 판매 가격을 갱신한다.
            // 판매 가격에서 현재 가격을 뺀다.
            sellPrice = 0;

            totalProfit = 0L;
            long profit = 0L;
            for (int day = totalDay - 1; day > -1; day--) {
                currPrice = priceList[day];

                // 판매 가격보다 현재 판매 가격이 더 큰 경우
                // 판매 가격을 갱신한다.
                if (sellPrice < currPrice) {
                    sellPrice = currPrice;
                }

                profit = sellPrice - currPrice;
                totalProfit += profit;

            }
            sb.append(totalProfit).append("\n");

        }

        System.out.println(sb);
    }
}