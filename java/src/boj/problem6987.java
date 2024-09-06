package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem6987 {
    /*
     * 
     * 총 6개의 나라가 있다.
     * 리그이기 때문에
     * 1. 한 나라는 다른 모든 나라와 경기를 해야한다. 각 나라는 5번에 경기를 함
     * 2. 경기를 한 나라와는 다시 경기하지 않는다.
     * 
     * 경기를 하면 3가지 경우가 등장한다.
     * - 승, 무, 패
     * 
     * 따라서 6개의 나라끼리 리그 경기를 하면 총 15경기를 하게 된다.
     * 3가지 경우로 15경기를 하므로 3^15의 경우의 수가 존재한다.
     * 
     * 입력받은 각 나라의 승, 무, 패 수가 경우의 수에 존재하는지 확인하는 문제
     * 
     * 경기를 진행하면서 맞붙는 두 나라가 승패, 무무, 패승이 각각 순서대로 가능한지 확인하고
     * - 가능한지 확인하기 위해 입력받은 나라의 결과 카운트를 확인
     * 가능하면 다음경기로 재귀
     * - 가능하면 알맞는 결과 카운트 감소
     * 호출한 재귀가 끝나면 다른 결과도 확인할 수 있도록 결과를 복구
     * - 결과 카운트 복구
     * 
     * 이렇게 진행한 경기수가 15가 되면 그 결과는 가능한 결과이다.
     * 
     * 
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static final int TEST_CASE = 4;
    static final int TOTAL_TEAM_COUNT = 6;
    static final int TOTAL_PLAY_COUNT = 15;
    static final int LEFT_TIME = 0;
    static final int RIGHT_TEAM = 1;

    static int answer;

    static int[][] matches = new int[TOTAL_PLAY_COUNT][2]; // 총경기수, 경기참여나라(한 경기는 두 나라가 한다.)
    static int[][] results;

    static boolean isPossible; // 입력 결과가 합당한지 확인
    static boolean fiveMatchCheck; // 각 나라가 5번에 경기를 하는지 확인

    static void inputTestCase() throws IOException {
        // 경기 결과를 저장
        // 승무패 순서
        results = new int[3][TOTAL_TEAM_COUNT];

        isPossible = false;
        fiveMatchCheck = true;

        // 경기 결과 입력
        st = new StringTokenizer(br.readLine().trim());

        for (int teamIdx = 0; teamIdx < TOTAL_TEAM_COUNT; teamIdx++) {
            int winCount = Integer.parseInt(st.nextToken());
            int tieCount = Integer.parseInt(st.nextToken());
            int loseCount = Integer.parseInt(st.nextToken());

            results[0][teamIdx] = winCount;
            results[1][teamIdx] = tieCount;
            results[2][teamIdx] = loseCount;

            // 기본적으로 5경기가 되는지 확인
            if (winCount + tieCount + loseCount != 5) {
                fiveMatchCheck = false;
            }
        }
    }

    static void initMatch() {
        // 확인할 매칭 만들기
        // 모든 나라는 5번에 경기를 하기 때문에 미리 매칭을 만들어도 됨
        int matchIdx = 0;
        for (int leftTeamIdx = 0; leftTeamIdx < TOTAL_TEAM_COUNT - 1; leftTeamIdx++) {
            for (int rightTeamIdx =
                    leftTeamIdx + 1; rightTeamIdx < TOTAL_TEAM_COUNT; rightTeamIdx++) {
                matches[matchIdx][0] = leftTeamIdx;
                matches[matchIdx][1] = rightTeamIdx;

                matchIdx++;
            }
        }
    }


    static void checkMatch(int count) {

        // 이미 경기가 합당한지 확인이 완료됨
        if (isPossible)
            return;

        // 15번에 경기를 할 수 있음
        if (count == TOTAL_PLAY_COUNT) {
            isPossible = true;
            return;
        }

        int leftTeam = matches[count][LEFT_TIME];
        int rightTeam = matches[count][RIGHT_TEAM];

        // 각 결과에 승, 무, 패가 남아 있어야 함
        // 승 : 패
        if (results[0][leftTeam] > 0 && results[2][rightTeam] > 0) {
            results[0][leftTeam]--;
            results[2][rightTeam]--;

            checkMatch(count + 1);

            results[0][leftTeam]++;
            results[2][rightTeam]++;
        }

        // 무 : 무
        if (results[1][leftTeam] > 0 && results[1][rightTeam] > 0) {
            results[1][leftTeam]--;
            results[1][rightTeam]--;

            checkMatch(count + 1);

            results[1][leftTeam]++;
            results[1][rightTeam]++;
        }

        // 패 : 승
        if (results[2][leftTeam] > 0 && results[0][rightTeam] > 0) {
            results[2][leftTeam]--;
            results[0][rightTeam]--;

            checkMatch(count + 1);

            results[2][leftTeam]++;
            results[0][rightTeam]++;
        }
    }

    public static void main(String[] args) throws IOException {
        initMatch();

        for (int tc = 0; tc < TEST_CASE; tc++) {
            inputTestCase();

            if (fiveMatchCheck) {
                checkMatch(0);
            }

            if (isPossible) {
                answer = 1;
            } else if (!isPossible || !fiveMatchCheck) {
                answer = 0;
            }

            sb.append(answer).append(" ");
        }

        System.out.println(sb);
    }
}
