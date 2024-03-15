package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.StringTokenizer;

public class problem1860 {
    /*
     * N명의 사람
     * 
     * 0초부터 붕어빵을 만든다.
     * M초의 시간을 들이면 K개의 붕어빵을 만들 수 있다.
     * 
     * 0초 이후에 손님들이 언제 도착하는지 주어지면 모든 손님들에게
     * 기다리는 시간없이 붕어빵을 제공할 수 있는지 판별하자
     * 
     * 
     * 테스트케이스가 주어진다.
     * N명의 사람, 붕어빵만드는데 걸리는 시간 M, 총만드는 붕어빵 개수 K
     * 
     * N개의 정수(각 사람이 언제 도착하는지)
     * 
     * 
     * 손님을 시간을 기준으로 정렬한다.
     * 1. 손님을 기준으로 반복문을 돌린다.
     * 2. 손님이 오는 시간보다 현재까지 붕어빵을 만드는 데 필요한 시간이 작거나 같은 경우
     * 2-1. 손님이 오는 시간보다 붕어빵을 만드는데 걸리는 시간이 클 때까지 반복하면서 붕어빵을 만든다.
     * 2-2. 2-1 반복문이 끝나면 붕어빵 카운트를 1감소시킨다. -> 마지막에 만든 붕어빵은 카운트하지 않기 위해
     * 3. 현재까지 붕어빵을 만드는데 걸린 시간보다 손님이 오는 시간이 작을 동안 손님에게 붕어빵을 준다.
     * 3-1. 붕어빵을 만드는데 걸린 시간이 작은데 줄 붕어빵이 없는 경우 미션 실패
     * 3-2. 붕어빵을 만드는데 걸린 시간보다 손님이 오는 시간이 클 경우 2번부터 반복한다.
     * 3-2-1. 2번부터 반복할 때 2-2.에서 카운트는 줄였는데 시간을 줄이지 않았으므로 현재까지 걸린 시간에서 붕어빵을 만드는데 걸린
     * 시간을 뺀 후 반복한다.
     * 
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int testCase;
    static int numOfPerson;
    static int timeForBread;
    static int bread;

    static int[] personList;
    static boolean[] visited;

    static final String P = "Possible";
    static final String IMP = "Impossible";

    public static void inputTestCase() throws IOException {
        st = new StringTokenizer(br.readLine().trim());

        // 사람수, 빵만드는데 걸리는 시간, 총만드는 붕어빵 개수
        numOfPerson = Integer.parseInt(st.nextToken());
        timeForBread = Integer.parseInt(st.nextToken());
        bread = Integer.parseInt(st.nextToken());

        personList = new int[numOfPerson];
        visited = new boolean[numOfPerson];

        // 사람이 도착하는 시간
        st = new StringTokenizer(br.readLine().trim());
        for (int idx = 0; idx < numOfPerson; idx++) {
            personList[idx] = Integer.parseInt(st.nextToken());
        }

        // 도착하는 순서대로 정렬
        Arrays.sort(personList);
    }

    public static boolean checkSoldOut() {

        // 붕어빵은 0초부터 만들 수 있다.
        // 손님은 0초 이후부터 온다.
        int time = 0;
        int breadCount = 0;

        // 고객 순서대로 확인
        for (int idx = 0; idx < numOfPerson; idx++) {

            // 붕어빵을 만드는 시간에 손님이 오는 경우
            // 붕어빵을 만든 후에 손님에게 제공할 수 있다.
            if (time <= personList[idx]) {

                // 넘친 시간에 만든 붕어빵을 제외한 것만큼
                // 다시 만들기 위해 붕어빵 추가
                if (idx != 0) {
                    time -= timeForBread;
                }

                // 붕어빵을 만드는데 걸리는 시간이 손님이 오는 시간보다 작거나 같은 경우
                // 계속 붕어빵을 만든다.
                while (time <= personList[idx]) {
                    time += timeForBread;
                    breadCount += bread;
                }

                // 넘친 시간에 만든 붕어빵은 제외
                breadCount -= bread;
            }

            // 손님에게 줄 붕어빵이 없으면 미션 실패
            if (breadCount > 0) {
                breadCount--;
            } else {
                return false;
            }

        }

        return true;
    }

    public static void main(String[] args) throws IOException {
        testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 1; tc <= testCase; tc++) {

            inputTestCase();

            if (checkSoldOut()) {
                sb.append(String.format("#%d %s\n", tc, P));
            } else {
                sb.append(String.format("#%d %s\n", tc, IMP));
            }

        }

        System.out.println(sb);
    }
}
