package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem1244 {

    /*
     * 
     * 1부터 연속적으로 번호가 붙어있는 스위치가 있다.
     * 스위치는 켜져 있거나(1) 꺼져있는 상태(0)
     * 
     * 학생들에게 1이상 스위치 개수 이하 자연수를 하나씩 나눠줌
     * 
     * 남학생
     * 스위치 번호가 자기가 받은 수의 배수면 스위치 상태를 바꾼다.
     * 
     * 여학생
     * 자기가 받은 수와 같은 번호가 붙은 스위치를 중심으로 좌우가 대칭이면서
     * 가장 많은 스위치를 포함하는 구간을 찾아서 그 구간에 속한 스위치의 상태를 모두 바꾼다.
     * 구간에 속한 스위치 개수는 항상 홀수개
     * 
     * 학생 성별에 따라서 다르게 동작하도록 해야함
     * 
     */

    public static BufferedReader br;
    public static StringTokenizer st;
    public static int numOfSwitch;
    public static int[] switches;

    public static void changeSwitch(int gender, int number, int depth) {
        // 학생 성별에 따라서 기능이 다름

        // 남학생 스위치번호와 그 배수에 해당하는 스위치를 토글
        if (gender == 1) { // 남학생
            // 1. 기저조건
            if (number * depth > numOfSwitch) {
                return;
            }

            // 2. 전처리
            switches[number * depth] = switches[number * depth] == 0 ? 1 : 0;

            // 3. 재귀 호출
            changeSwitch(gender, number, depth + 1);

            // 여학생
            // 여학생 스위치번호를 기준으로 좌우 대칭인 구간 중 가장 넓은 구간을 찾아
            // 포함되는 스위치 토글
        } else if (gender == 2) { // 여학생

            switches[number] = switches[number] == 0 ? 1 : 0;

            int loop = 1;

            // number기준으로 좌우에 같은 값인지 확인해야함
            while (true) {
                int leftIdx = number - loop;
                int rightIdx = number + loop;

                // 스위치 범위 안에 있어야 함
                // 좌우 대칭을 확인하고 토글
                if (0 < leftIdx && leftIdx <= numOfSwitch && 0 < rightIdx && rightIdx <= numOfSwitch) {
                    if (switches[leftIdx] == switches[rightIdx]) {
                        switches[leftIdx] = switches[leftIdx] == 0 ? 1 : 0;
                        switches[rightIdx] = switches[rightIdx] == 0 ? 1 : 0;

                        loop++;
                    } else {
                        break;
                    }
                } else {
                    break;
                }
            }
        }
    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));

        // 스위치 개수 입력
        numOfSwitch = Integer.parseInt(br.readLine().trim());

        // 스위치 상태 입력
        switches = new int[numOfSwitch + 1];
        st = new StringTokenizer(br.readLine().trim());
        for (int idx = 1; idx <= numOfSwitch; idx++) {
            switches[idx] = Integer.parseInt(st.nextToken());
        }

        // 학생수 입력
        int numOfStudent = Integer.parseInt(br.readLine().trim());

        // 각 학생별로 성별과 받은 번호 입력
        for (int idx = 0; idx < numOfStudent; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            int gender = Integer.parseInt(st.nextToken());
            int number = Integer.parseInt(st.nextToken());

            changeSwitch(gender, number, 1);
        }

        // 스위치는 20개씩 출력한다.
        for (int idx = 1; idx <= numOfSwitch; idx++) {
            System.out.print(switches[idx] + " ");
            if (idx % 20 == 0) {
                System.out.println();

            }
        }

    }
}
