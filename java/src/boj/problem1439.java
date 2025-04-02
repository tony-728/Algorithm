package boj;

import java.io.*;

public class problem1439 {
    /*
     * 주어진 문자열의 숫자를 모두 같게 만들려고 한다.
     * 숫자는 0, 1
     * 
     * 행동
     * - 연속된 하나 이상의 숫자를 모두 잡고 뒤집는 것
     * 
     * 문자열은 최대 100만
     * 
     * 그리디
     */
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        String s = br.readLine().trim();

        int count = 0;
        int reverseCount = 1;

        // 첫번째를 유지
        char origin = s.charAt(0);
        boolean originFlag = false;

        // 첫번째를 바꾼다.
        char reverse = s.charAt(0) == '1' ? '0' : '1';
        boolean reverseFlag = false;

        for (int idx = 1; idx < s.length(); idx++) {
            char val = s.charAt(idx);

            if (val != origin && !originFlag) {
                count++;
                originFlag = true;
            } else if (val == origin && originFlag) {
                originFlag = false;
            }

            if (val != reverse && !reverseFlag) {
                reverseCount++;
                reverseFlag = true;
            } else if (val == reverse && reverseFlag) {
                reverseFlag = false;
            }
        }

        System.out.println(count > reverseCount ? reverseCount : count);
    }
}
