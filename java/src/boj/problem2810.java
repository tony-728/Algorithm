package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class problem2810 {
    /*
     * N개의 자리
     * 인접한 좌석 사이에 컵홀더가 있다.
     * 양끝 좌석에는 컵홀더가 하나씩 더 있다.
     * 커플석 사이에는 컵홀더가 없다.
     * 
     * 컵홀더에 컵을 꽂을 수 있는 최대 사람의 수를 구하라
     * - 사람마다 컵을 하나씩 갖고 있다.
     * - 자신의 좌석 양 옆에 있는 컵홀더에만 컵을 꽂을 수 있다.
     * 
     * 좌석 기준으로 무조건 왼쪽에 컵을 놓는다.
     * - 옆에 L인 경우는 제외
     * 마지막 좌석인 경우는 오른쪽에 컵을 추가로 놓는다.
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static String seatList;

    static final char NOMAL = 'S';
    static final char COUPLE = 'L';

    static int numOfSeat;

    static int answer;

    static boolean coupleCheck = false;

    static char seat;

    public static void main(String[] args) throws IOException {

        numOfSeat = Integer.parseInt(br.readLine().trim());

        seatList = br.readLine().trim();

        for (int idx = 0; idx < numOfSeat; idx++) {

            seat = seatList.charAt(idx);

            // 커플석 체크
            if (seat == COUPLE) {
                coupleCheck = !coupleCheck;
            }

            // 첫번째 인덱스면 무조건 컵추가
            if (idx == 0) {
                answer++;
                continue;
            }

            // 커플석 확인
            if (seatList.charAt(idx - 1) == COUPLE && seat == COUPLE) {
                // 커플석끼리의 사이면 컵추가
                if (coupleCheck) {
                    answer++;
                }
                continue;
            }

            answer++;
        }

        // 마지막 좌석은 오른쪽에 무조건 컵을 추가할 수 있다.
        answer++;

        // 컵의 갯수는 인원의 수를 넘을 수 없다.
        if (answer > numOfSeat) {
            answer = numOfSeat;
        }

        System.out.print(answer);


    }
}
