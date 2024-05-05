package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem1592 {

    /*
     * 
     * 원형으로 모여서 시계방향으로 1부터 N까지 적혀있는 자리에 앉는다.
     * 규칙
     * - 1번 자리에 앉은 사람이 공을 받는다.
     * - 공을 다른 사람에게 던진다.
     * - 다시 공을 받은 사람은 다시 공을 던진다.
     * 
     * 이를 반복한다.
     * 한 사람이 공을 M번 받았으면 게임은 종료
     * 공을 m번보다 적게 받은 사람이 공을 던질 때
     * 현재 공을 받은 횟수가 홀수번이면 자기의 현재 위치에서 시계 방향으로
     * L번째 있는 사람에게
     * 짝수번이면 자기의 현재 위치에서 반시계 방향으로
     * L번째 있는 사람에게 공을 던진다.
     * 
     * 총 공을 몇번 던지는지 구하는 프로그램
     * 
     * N, M, L이 입력으로 들어온다.
     * 
     * 
     * N개의 배열을 만든다. 0번부터 카운트를 올려준다.
     * 배열의 값중 M을 넘는 값이 있는지 확인한다.
     * 카운트를 올린 인덱스의 값이 홀수면 (+ L 만큼 더한 값에) N을 나눈 나머지가 새로운 인덱스가 된다.
     * 카운트를 올린 인덱스의 값이 짝수면 (- L 만큼 더한 값에 N을 더한값에) N을 나눈 나머지가 새로운 인덱스가 된다.
     * 
     * 
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;

    static int numOfPerson; // 총 사람
    static int maxCount; // 공 받는 카운트
    static int throwNumber; // 몇번째에게 공을 던질지

    static int[] personList;
    static int[] countList;
    static int throwIndex = 0;

    static int answer; // 총 공을 던지는 횟수

    static boolean checkCount() {

        for (int idx = 0; idx < numOfPerson; idx++) {
            if (countList[idx] >= maxCount) {
                return true;
            }
        }

        return false;
    }

    public static void main(String[] args) throws IOException {
        st = new StringTokenizer(br.readLine().trim());

        numOfPerson = Integer.parseInt(st.nextToken());
        maxCount = Integer.parseInt(st.nextToken());
        throwNumber = Integer.parseInt(st.nextToken());

        countList = new int[numOfPerson];

        countList[throwIndex] = 1;

        while (true) {
            // 한사람이 maxCount이상 공을 받았으면 종료됨
            if (checkCount()) {
                break;
            }

            // 짝수, 빼기
            if (countList[throwIndex] % 2 == 0) {
                throwIndex = (throwIndex - throwNumber + numOfPerson) % (numOfPerson);
                countList[throwIndex]++;
                answer++;

                // 홀수, 더하기
            } else {
                throwIndex = (throwIndex + throwNumber) % (numOfPerson);
                countList[throwIndex]++;
                answer++;

            }

        }
        System.out.println(answer);
    }
}
