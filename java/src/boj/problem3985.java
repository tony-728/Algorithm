package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem3985 {
    /*
     * N길이 케잌을 1미터 단위로 자른다.
     * 1번부터 N까지
     * 
     * P, K번호를 써서 내면 p부터 k까지 
     * 1번 사람부터 받게 되는데 이미 다른 사람이 가져간 조각은 제외하고 받게된다.
     * 
     * 최종적으로 원하는 조각을 가장 많이 받은 사람의 번호를 출력
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int inputLength;
    static int numOfPerson;

    static int[] cakeList;
    static int maxCount;
    static int expectedMaxCount;

    static int answer = Integer.MAX_VALUE;
    static int expected = Integer.MAX_VALUE;

    static void inputTestCase() throws IOException {

        maxCount = 0;
        expectedMaxCount = 0;

        inputLength = Integer.parseInt(br.readLine().trim());
        numOfPerson = Integer.parseInt(br.readLine().trim());

        cakeList = new int[inputLength + 1];

        for (int idx = 0; idx < numOfPerson; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            int left = Integer.parseInt(st.nextToken());
            int right = Integer.parseInt(st.nextToken());

            // 기대값이 가장 큰 사람 번호
            if ((right - left) > expectedMaxCount) {
                expected = idx + 1;
                expectedMaxCount = right - left;
            }

            int count = 0;

            for (int start = left; start < right + 1; start++) {
                if (cakeList[start] == 0) {
                    cakeList[start] = idx + 1;
                    count++;
                }
            }

            // 실제 가장 많은 값을 케잌을 가져간 사람
            if (maxCount < count) {
                maxCount = count;
                answer = idx + 1;
            }

        }
    }

    public static void main(String[] args) throws IOException {
        inputTestCase();

        System.out.println(expected);
        System.out.println(answer);
    }
}
