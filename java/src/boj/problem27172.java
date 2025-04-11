package boj;

import java.io.*;
import java.util.*;

public class problem27172 {
    /*
     * 게임 규칙
     * - 게임을 시작하기 전 각 플레이어는 1-1,000,00 사이의 수가 적힌 서로 다른 카드를 한 장 갖는다.
     * - 매 턴마다 플레이어는 다른 플레이어와 결투한다.
     * - 결투
     *  - 서로 카드를 보여준다.
     *  - 플레이어의 카드에 적힌 수로 다른 플레이어의 카드의 수를 나눴을 때 나머지가 0이면 승리한다.
     *  - 플레이어의 카드에 적힌 수가 다른 플레이어의 카드에 적힌 수로 나눠 떨어지면 패배한다.
     *  - 둘 다 아니라면 무승부
     * - 승리하면 1점을 얻는다, 패배하면 1점을 잃는다.
     * - 본인을 제외한 다른 모든 플레이어와 정확히 한 번 결투하면 게임이 종료된다.
     * 
     * 게임이 종료된 후의 모든 플레이어의 점수를 구하라
     * 
     * 
     * 카드는 최대 10만장
     * 카드의 숫자는 1-100만
     * 
     * nlogn
     * 
     * 에라토스테네스의 채
     * 
     * 
     */

    static StringBuilder sb = new StringBuilder();

    static int numOfPlayer;
    static int[] arrOfCard;
    static int[] arrOfPlayerScore;
    static HashMap<Integer, Integer> hm = new HashMap<>();

    static void inputData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        numOfPlayer = Integer.parseInt(br.readLine().trim());
        arrOfCard = new int[numOfPlayer];
        arrOfPlayerScore = new int[numOfPlayer];

        st = new StringTokenizer(br.readLine().trim());

        for (int idx = 0; idx < numOfPlayer; idx++) {
            int card = Integer.parseInt(st.nextToken());
            arrOfCard[idx] = card;
            hm.put(card, idx);
        }

        // 카드 순서대로 정렬
        Arrays.sort(arrOfCard);
    }

    static void solution() {

        // N이 최대 10만
        // n^2은 안될듯....당연히 시간초과

        int maxValue = arrOfCard[numOfPlayer - 1];

        for (int leftIdx = 0; leftIdx < numOfPlayer - 1; leftIdx++) {

            int standardCard = arrOfCard[leftIdx];
            int standardCardIdx = hm.get(standardCard);

            for (int card = standardCard * 2; card <= maxValue; card = card + standardCard) {

                int cardIdx = hm.getOrDefault(card, -1);

                if (cardIdx == -1) {
                    continue;
                }

                arrOfPlayerScore[cardIdx] -= 1;
                arrOfPlayerScore[standardCardIdx] += 1;

            }
        }


        for (Integer score : arrOfPlayerScore) {
            sb.append(score).append(" ");
        }
    }

    public static void main(String[] args) throws IOException {
        inputData();

        solution();

        System.out.println(sb);

    }
}
