package boj;

import java.io.*;

public class problem2089 {
    /*
     * -2진법은 부호없는 2진수로 표현이 된다.
     * 
     * -2진법으로 10진수를 표현해야한다.
     * 
     * - -2진법에서는 (-2)^0 = 1, (-2)^1 = -2, (-2)^2 = 4, (-2)^3 = -8을 표현한다
     * 
     * - 2진법으로 바꾸는 것은 동일하지만 
     * -2진법이므로 2가 아닌 -2로 나눠준다.
     * 나머지가 홀수인 경우는 -1을 빼 주어야 올바르게 나온다.
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();
    static int number;

    public static void main(String[] args) throws IOException {
        number = Integer.parseInt(br.readLine());

        // 0일 때 처리를 해야함
        if (number == 0) {
            sb.append("0");
        }

        while (number != 0) {

            if (number % -2 == 0) {
                sb.append("0");
                number /= -2;
            } else {
                sb.append("1");
                number = (number - 1) / -2;
            }
        }

        System.out.println(sb.reverse());
    }
}
