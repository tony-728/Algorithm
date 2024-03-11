package swea;

import java.util.Scanner;

public class problem1217 {
    /*
     * 두 개의 숫자 N, M이 주어질 때 N의 M 거듭제곱을 구하라
     * 
     * 총 10개의 테스트 케이스
     * 
     * 재귀함수를 이용하여 구하도록 함
     * 
     */

    static final int testCase = 10;
    static Scanner sc = new Scanner(System.in);

    static int under;
    static int over;
    static int answer;

    public static int pow(int under, int over, int result) {
        if (over == 0) {
            return result;
        }

        result = result * under;
        return pow(under, over - 1, result);
    }

    public static void main(String[] args) {

        for (int tc = 1; tc <= testCase; tc++) {
            sc.next();

            under = sc.nextInt();
            over = sc.nextInt();

            answer = pow(under, over, 1);

            System.out.println("#" + tc + " " + answer);
        }
    }

}
