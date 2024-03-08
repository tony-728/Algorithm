package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class problem2023 {

    /*
     * N자리 수 중
     * 왼쪽부터 1, 2, .., N자리에 수 모두가
     * 소수인 수를 찾자
     * 
     * 왼쪽자리부터 소수인 수를 넣어주고
     * 각 경우마다 자리를 늘려가며 만들 수 있는 수가 소수인지 확인한다.
     * 
     * 위 과정을 N자리가 될때까지 반복한다.
     */

    public static BufferedReader br;
    public static int N;
    public static int temp;

    public static List<Integer> intList = new ArrayList<>();

    // 소수 확인 메서드
    public static boolean isPrime(int number) {
        // 제곱근까지만 확인하면 되나

        if (number == 1) {
            return false;
        }

        for (int idx = 2; idx <= Math.sqrt(number); idx++) {
            if (number % idx == 0) {
                return false;
            }
        }

        return true;
    }

    public static void makePrimeNumber(int depth, int number) {
        // 1. 기저 조건
        if (depth == N) {
            intList.add(number);
            return;
        }

        // 2. 전처리
        for (int idx = 1; idx < 10; idx++) {
            temp = (number * 10) + idx;

            // temp 소수이면 다음으로
            // 3. 재귀 호출
            if (isPrime(temp)) {
                makePrimeNumber(depth + 1, temp);
            }
        }

    }

    public static void main(String[] args) throws IOException {
        br = new BufferedReader(new InputStreamReader(System.in));
        N = Integer.parseInt(br.readLine().trim());

        makePrimeNumber(0, 0);

        Collections.sort(intList);

        for (int number : intList) {
            System.out.println(number);
        }
    }

}
