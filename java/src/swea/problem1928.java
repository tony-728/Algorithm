package swea;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/*
   사용하는 클래스명이 Solution 이어야 하므로, 가급적 Solution.java 를 사용할 것을 권장합니다.
   이러한 상황에서도 동일하게 java Solution 명령으로 프로그램을 수행해볼 수 있습니다.
 */
class problem1928 {
    public static void main(String args[]) throws Exception {
        /*
         * encoding
         * 1. 24bit buffer에 한 byte 씩 3byte의 문자를 넣는다.
         * 2. 버퍼 위쪽 부터 6비트씩 잘라 그 값을 읽고 각각의 값을 표에 문자로 encoding 한다.
         * 
         * decoding
         * 1. encoding된 문자를 24bit 버퍼에 한 byte씩 4byte의 문자를 넣는다.
         * 2. 버퍼 위쪽 부터 8비트씩 잘라 그 값을 읽고 아스키코드에 대응되는 문자로 decoding한다.
         * 
         */

        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        char[] buffer = new char[24];
        int T = Integer.parseInt(br.readLine().trim());

        for (int testCase = 1; testCase <= T; testCase++) {
            int bIdx = 0;
            List<String> charList = new ArrayList<>();
            String x = br.readLine().trim();

            for (int idx = 0; idx < x.length(); idx++) {
                // 영어 대문자, 소문자, 숫자에 따라서 encoding 값이 다르다.
                // 대문자 - 'A'
                // 소문자 - 71
                // 숫자 + 4
                // '+' + 19, '/' + 16
                int temp = 0;
                if (Character.isDigit(x.charAt(idx))) { // 숫자
                    temp = (int) x.charAt(idx) + 4;
                } else if (Character.isUpperCase((x.charAt(idx)))) { // 대문자
                    temp = (int) (x.charAt(idx) - 'A');
                } else if (Character.isLowerCase((x.charAt(idx)))) { // 소문자
                    temp = (int) x.charAt(idx) - 71;
                } else if (x.charAt(idx) == '+') { // '+'
                    temp = (int) x.charAt(idx) + 19;
                } else if (x.charAt(idx) == '/') { // '/'
                    temp = (int) x.charAt(idx) + 16;
                }

                // 6bit 2진수로 변환
                String binaryValue = String.format("%6s", Integer.toBinaryString(temp));

                // 24bit에 4개 문자열을 넣고
                if (bIdx < 24) {
                    for (int inIdx = 0; inIdx < binaryValue.length(); inIdx++) {
                        if (Character.isDigit(binaryValue.charAt(inIdx))) {
                            buffer[bIdx++] = binaryValue.charAt(inIdx);
                        } else { // 숫자가 아닌 빈칸이면 0을 넣는다.
                            buffer[bIdx++] = '0';
                        }
                    }
                }
                // 버퍼가 모두 찼으면 8bit씩 꺼내어 아스키 코드의 문자로 변환
                if (bIdx == 24) {
                    char[] arr = new char[8];
                    for (int outIdx = 0; outIdx < 24; outIdx++) {
                        if ((outIdx + 1) % 8 == 0) {
                            arr[outIdx % 8] = buffer[outIdx];
                            charList.add(Character.toString((char) Integer.parseInt(String.valueOf(arr), 2)));
                            arr = new char[8];

                        } else {
                            arr[outIdx % 8] = buffer[outIdx];
                        }
                    }
                    bIdx = 0;
                }

            }
            System.out.println("#" + testCase + " " + String.join("", charList));
        }
    }

}