package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;

public class problem20310 {

    /*
     * 0과 1로 이루어진 문자열 S
     * 0, 1의 개수는 짝수
     * 
     * 0, 1의 개수를 절반을 제거하여 새로운 문자열 s`
     * 
     * s` 중 사전순으로 가장 빠른 것
     * 
     * 
     * 뒤에서 부터 0을 지운다.
     * 앞에서 부터 1을 지운다.
     * 
     */


    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static int zeroCount;
    static int oneCount;
    static int totalCount;

    static String inputString;

    static ArrayDeque<Character> result = new ArrayDeque<>();
    static ArrayDeque<Character> tempQ = new ArrayDeque<>();

    public static void main(String[] args) throws IOException {

        inputString = br.readLine().trim();

        for (int idx = 0; idx < inputString.length(); idx++) {
            char value = inputString.charAt(idx);

            result.offer(value);

            if (value == '0') {
                zeroCount++;
            } else {
                oneCount++;
            }

        }

        int halfZeroCount = zeroCount / 2;
        int halfOneCount = oneCount / 2;

        // 뒤에서부터 0 제거하기
        while (!result.isEmpty()) {
            char temp = result.pollLast();
            if (temp == '0' && halfZeroCount > 0) {
                halfZeroCount--;
                continue;
            }

            // 스택처럼 앞으로 넣어주어야함
            tempQ.offerFirst(temp);

        }

        // 앞에서부터 1제거하기
        while (!tempQ.isEmpty()) {

            // 스택처럼 앞에서부터 확인
            char temp = tempQ.pollFirst();

            if (temp == '1' && halfOneCount > 0) {
                halfOneCount--;
                continue;
            }

            result.offer(temp);
        }

        for (Character value : result) {
            System.out.print(value);
        }

    }
}
