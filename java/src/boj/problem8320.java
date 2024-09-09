package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class problem8320 {
    /*
     * 길이가 1인 정사각형 n개
     * 정사각형을 이용해서 만들 수 있는 직사각형의 개수
     * 
     * n의 약수 중 1과 n을 제외한 수 + (1~N) + (1~N의 제곱이 N보다 작은 수)
     * 1,2,3,4,5,6
     * 23, 22
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static int inputValue;

    static int count;

    public static void main(String[] args) throws IOException {
        inputValue = Integer.parseInt(br.readLine().trim());

        if (inputValue == 1) {
            count = 1;
        }

        for (int rowSize = 1; rowSize < inputValue; rowSize++) {
            for (int colSize = rowSize; colSize <= inputValue; colSize++) {
                if (rowSize * colSize <= inputValue) {
                    count++;
                }
            }
        }


        System.out.println(count);
    }
}
