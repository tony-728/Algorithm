package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem1952 {
    /*
     * 판매권
     * - 1일권
     * - 1달 이용권(매달 1일부터 시작)
     * - 3달 이용권(11,12월에도 구매 가능, 해를 넘겨서 사용할 수 는 없음)
     * - 1년 이용권
     * 
     * 판매권 가격과 1년 이용계획을 입력받았을 때 가장 적은 비용을 구하라
     * 
     * 
     * 1일, 1달, 3달, 1년 이용권을 모두 순서대로 체크하면서 최소 비용을 찾는다.
     * - dfs
     * - 1개월씩 확인을 하고 
     * - 3달은 현재 확인하고 있는 월 다음 월부터 3개월 동안 이용계획이 있는지 확인해야함
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int testCase;
    static final int PRICE_CATEGORY = 4;
    static final int NUM_OF_MONTH = 12;

    static final int YEAR = 3;
    static final int QUARTER = 2;
    static final int MONTH = 1;
    static final int DAY = 0;

    static int[] priceList;
    static int[] planningList;

    static int answer;

    static void inputTestCase() throws IOException {
        priceList = new int[PRICE_CATEGORY];
        planningList = new int[NUM_OF_MONTH];

        // 이용권 가격 입력
        st = new StringTokenizer(br.readLine().trim());
        for (int idx = 0; idx < PRICE_CATEGORY; idx++) {
            priceList[idx] = Integer.parseInt(st.nextToken());
        }

        // 12개월 이용 계획 입력
        st = new StringTokenizer(br.readLine().trim());
        for (int idx = 0; idx < NUM_OF_MONTH; idx++) {
            planningList[idx] = Integer.parseInt(st.nextToken());
        }

        answer = priceList[YEAR];
    }

    // 현재 유저가 있는지 확인
    static boolean checkUser(int month) {
        if (planningList[month] > 0) {
            return true;
        }

        return false;
    }

    static void find(int month, int totalPrice) {
        // 지금까지 가격이 최소 가격보다 크면 더이상 확인하지 않아도 됨
        if (totalPrice > answer) {
            return;
        }

        // 모든 월을 확인했을 때
        if (month >= 12) {
            answer = Math.min(answer, totalPrice);
            return;
        }

        // 현재 월에 사용자가 있는지
        if (checkUser(month)) {
            // 1일
            find(month + 1, totalPrice + (priceList[DAY] * planningList[month]));
            // 1달
            find(month + 1, totalPrice + priceList[MONTH]);
            // 3달
            find(month + 3, totalPrice + priceList[QUARTER]);
        } else {
            find(month + 1, totalPrice);
        }
    }


    // DP를 이용한 풀이
    static void findByDP() {
        int[] plan = new int[NUM_OF_MONTH];
        for (int i = 1; i <= 12; i++) {
            // 하루 이용권과 한달 이용권 중 최소 비용을 저장
            plan[i] = Math.min(plan[i - 1] + planningList[i - 1] * priceList[0],
                    plan[i - 1] + priceList[1]);

            // 3달 이용권을 이용할 경우를 고려
            if (i >= 3) {
                plan[i] = Math.min(plan[i], plan[i - 3] + priceList[2]);
            }
        }

        answer = Math.min(plan[11], priceList[3]);
    }



    public static void main(String[] args) throws IOException {

        testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= testCase; tc++) {
            inputTestCase();

            find(0, 0);

            sb.append(String.format("#%d %d\n", tc, answer));

        }

        System.out.println(sb);
    }
}
