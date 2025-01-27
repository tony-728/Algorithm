package boj;

import java.io.*;
import java.util.*;


public class problem33151 {
    /*
     * K개의 동전을 NxN 크기의 격자판에 배치해서, 이 격자판을 마법사의 격자판으로 만들어라
     * - 격자판의 각 칸에 0개 이상의 동전을 배치해 격자판에서 모든 인접한 두 칸의 동전 개수의 차이가 정확히 1일 때
     * 이 격자판을 마법사의 격자판이라고 한다.
     * 
     * 
     * 마법사의 격자판을 만들 수 있는지 알려주고, 만들 수 있다면 격자판을 어떻게 만들어야 하는지 구하라
     * 
     * 구현
     * - 맵의 크기마다 코인 숫자에 대해서 가능한 최소 갯수와 최대 갯수가 존재
     * -- 이 때 불가능하면 -1
     * - 주어진 코인 갯수에 대해서 최소 갯수와 최대 갯수에 해당하는 코인의 값을 구한다.
     * -- 범위 안에 들어가면 정말 가능한지 확인 이 때 불가능하면 -1 가능하면 맵을 구성
     * 
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static int mapSize;
    static int numOfCoin;

    static void inputData() throws IOException {
        st = new StringTokenizer(br.readLine().trim());

        mapSize = Integer.parseInt(st.nextToken());
        numOfCoin = Integer.parseInt(st.nextToken());
    }

    static void makeMap(int coin, int numOfTemp, int boundaryIdx) {
        // coin: 채울 코인 값, 인접한 칸은 coin-1
        // numOfTemp: 중간에 다른 코인으로 채울 개수, 다른 코인은 coin+1
        // boundaryIdx가 1이면 0,0부터 / 0이면 0,1부터

        boolean flag = false;

        if (boundaryIdx == 1) {
            flag = true;
        }

        for (int rowIdx = 0; rowIdx < mapSize; rowIdx++) {
            for (int colIdx = 0; colIdx < mapSize; colIdx++) {
                if (flag) {
                    sb.append(coin).append(" ");
                    flag = !flag;
                } else {
                    if (numOfTemp > 0) {
                        sb.append(coin + 1).append(" ");
                        numOfTemp--;
                    } else {
                        sb.append(coin - 1).append(" ");
                    }
                    flag = !flag;

                }
            }
            if(mapSize % 2 == 0){
                flag = !flag;
            }
            sb.append("\n");
        }
    }

    static void solution() {
        // 주어진 격자판의 절반 갯수를 구한다.
        // 짝수면 나누어 떨어짐
        // 홀수면 소수점 버리는 값, 반올림 값

        // 절반은 최소로 요구하는 코인 갯수임
        // 이것보다 작으면 안되고
        // 이것보다 크다면 확인


        int total = mapSize * mapSize;
        int half = total / 2;

        int coin = 1;

        int minimum;
        int maximum;

        // 최소 코인 갯수보다 주어진 코인갯수가 적으면 안됨
        if ((half * coin) > numOfCoin) {
            sb.append(-1);
            return;
        }

        // 짝수인 경우
        if (mapSize % 2 == 0) {
            // 주어진 코인 갯수에 해당하는 최소 구간을 찾는다.
            while (true) {

                minimum = (half * coin) + half * (coin - 1);
                maximum = (half * coin) + half * (coin + 1);

                if (minimum <= numOfCoin && numOfCoin <= maximum) {
                    break;
                }

                coin++;
            }

            for (int idx = 0; idx <= half; idx++) {
                // 가능함
                // 가능한 모양을 출력
                if (minimum + (idx * 2) == numOfCoin) {
                    makeMap(coin, idx, 1);
                    return;
                }
            }

            // 홀수인 경우
        } else {

            int temp = half;
            int boundaryIdx = 0;

            // 주어진 코인 갯수에 해당하는 최소 구간을 찾는다.
            outLoop: while (true) {

                // 홀수는 2가지 경우가 발생한다.
                for (boundaryIdx = 0; boundaryIdx < 2; boundaryIdx++) {
                    temp = half + boundaryIdx;

                    minimum = (temp * coin) + (total - temp) * (coin - 1);
                    maximum = (temp * coin) + (total - temp) * (coin + 1);

                    if (minimum <= numOfCoin && numOfCoin <= maximum) {
                        break outLoop;
                    }
                }
                coin++;
            }

            // 홀수는 2번 체크해야함
            // temp % coin이 짝수면 앞쪽 => +1 확인, 홀수면 뒤쪽 => -1 확인
            for (int idx = 0; idx <= (total - temp); idx++) {
                if (minimum + (idx * 2) == numOfCoin) {
                    makeMap(coin, idx, boundaryIdx);
                    return;
                }
            }

            if (boundaryIdx == 0) {
                temp += 1;
            } else {
                temp -= 1;
            }

            minimum = temp * coin + (total - temp) * (coin - 1);
            maximum = temp * coin + (total - temp) * (coin + 1);

            for (int idx = 0; idx <= (total - temp); idx++) {
                if (minimum + (idx * 2) == numOfCoin) {
                    makeMap(coin, idx, boundaryIdx ^ 1);
                    return;
                }
            }
        }

        // 불가능함
        sb.append(-1);
        return;
    }


    public static void main(String[] args) throws IOException {
        inputData();

        solution();

        System.out.println(sb);

    }
}
