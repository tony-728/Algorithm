package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem3499 {
    /*
     * 
     * 퍼펙트 셔플
     * N개의 카드를 정확히 절반으로 나누고 나눈 것들에서 교대로 카드를 뽑아 새로운 덱을 만든다.
     * 
     * N이 홀수이면 먼저 놓는 쪽에 한 장이 더 들어가게 된다.
     * 
     * 
     * 테스트케이스가 주어진다.
     * 카드의 개수 N이 주어진다.
     * 카드가 놓인 순서대로 카드의 이름이 주어진다.
     * - 카드의 이름은 알파벳 대문자와 '-'만으로 이루어져 있다.
     * 
     * 
     * 카드 뭉치를 반으로 나눈다.
     * 두개로 나누어진 문자열 배열을 순서대로 꺼내와서 정답배열에 넣는다.
     * - 인덱스로 카드 뭉치를 나누어 접근한다.
     * 
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int testCase;
    static int numOfCard;

    static String[] deck;

    static String[] answer;

    public static void inputTestCase() throws IOException {
        numOfCard = Integer.parseInt(br.readLine().trim());

        deck = new String[numOfCard];
        answer = new String[numOfCard];

        st = new StringTokenizer(br.readLine().trim());

        // 카드 입력받기
        for (int idx = 0; idx < numOfCard; idx++) {
            deck[idx] = st.nextToken();
        }
    }

    public static void perfectSuffle() {
        int answerIdx = 0;

        int deckIdx = 0;

        while (true) {
            if (answerIdx == numOfCard) {
                break;
            }

            // 첫번째 뭉치
            answer[answerIdx++] = deck[deckIdx];

            if (answerIdx == numOfCard) {
                break;
            }

            // 두번째 뭉치
            // 카드 뭉치가 짝수 일때
            if (numOfCard % 2 == 0) {
                answer[answerIdx++] = deck[deckIdx + (numOfCard >> 1)];

                // 카드 뭉치가 홀수 일때
            } else {
                answer[answerIdx++] = deck[deckIdx + (numOfCard >> 1) + 1];

            }

            deckIdx++;
        }

    }

    public static void main(String[] args) throws IOException {
        testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= testCase; tc++) {
            inputTestCase();

            perfectSuffle();

            sb.append(String.format("#%d ", tc));
            for (String card : answer) {
                sb.append(card).append(" ");
            }
            sb.append("\n");
        }

        System.out.println(sb);
    }

}
