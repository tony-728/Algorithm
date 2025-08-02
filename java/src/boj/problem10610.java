package boj;

import java.io.*;
import java.util.*;

public class problem10610 {
    /**
     * 양수 N이 있다.
     * 길에서 찾은 수를 섞어 30의 배수가 되는 가장 큰 수를 만들고 싶다.
     * 
     * 만들고 싶은 수가 있으면 그 수를 출력 없다면 -1
     * 
     * 30은 10의 배수이고 3의 배수이어야 한다.
     * 
     * 10의 배수는 마지막 값이 0이어어한다.
     * 3의 배수는 모든 자리의 합이 3의 배수
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String N = br.readLine().trim();
        int lengthOfN = N.length();

        int[] arrOfValue = new int[lengthOfN];

        int sum = 0;
        for (int idx = 0; idx < lengthOfN; idx++) {
            int value = N.charAt(idx) - '0';
            arrOfValue[idx] = value;
            sum += value;
        }

        Arrays.sort(arrOfValue);

        StringBuilder sb = new StringBuilder();
        if (arrOfValue[0] == 0 && (sum % 3 == 0)) {
            for (int idx = 0; idx < lengthOfN; idx++) {
                sb.append(arrOfValue[idx]);
            }
            sb.reverse();
        } else {
            sb.append(-1);
        }

        System.out.println(sb);

    }
}
