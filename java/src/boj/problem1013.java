package boj;

import java.io.*;

public class problem1013 {
    /*
     * 전파의 기본 단위 {0, 1}
     * x+(): 임의의 개수(최소 1개) x의 반복으로 이루어진 전파의 집합
     * 
     * (xyx)+는 xyx의 반복으로 이루어진 집합
     * 
     * |: {x | y}는 x 혹은 y를 의미
     * 
     * 두 기호를 복합적으로 사용할 수 있다.
     * 
     * (100+1+ | 01)+ 에 대해서 
     * 주어진 패턴이 일치하는지 가려내라
     * 
     * 정규표현식
     * Pattern class를 상속받아서 사용할 수 있다.
     * String.matches() -> Pattern.matches(regex, str);
     * 
     * 정규표현식 모르면 맞아야지...
     * 
     */

    static int testCase;

    static StringBuilder sb = new StringBuilder();

    static final String YES = "YES";
    static final String NO = "NO";

    static final String TARGET_PATTERN = "(100+1+|01)+";

    static void inputData() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 0; tc < testCase; tc++) {
            String pattern = br.readLine().trim();

            // solution(pattern);
            reg(pattern);
        }
    }

    static void reg(String pattern) {
        if (pattern.matches(TARGET_PATTERN)) {
            sb.append(YES).append("\n");
        } else {
            sb.append(NO).append("\n");
        }
    }

    static void solution(String pattern) {

        // 패턴 플래그
        // true: 100+1+ / false: 01
        boolean patternOne = false;

        boolean isPass = false;

        int count = 0;

        if (pattern.charAt(0) == '1') {
            patternOne = true;
        } else {
            patternOne = false;
        }

        for (int idx = 1; idx < pattern.length(); idx++) {
            isPass = false;
            char p = pattern.charAt(idx);

            // System.out.println("p " + p + " idx " + idx + " count " + count);

            // 패턴1
            if (patternOne) {

                if (p == '0') {
                    count++;
                }

                // 0이 2개가 안됬는데 1이 나오면 안됨
                if (p == '1' && count < 2) {
                    break;

                } else if (p == '1' && count >= 2) {
                    isPass = true;

                    if (idx + 1 < pattern.length()) {
                        char preChar = pattern.charAt(idx - 1);
                        char postChar = pattern.charAt(idx + 1);

                        // 패턴1 -> 패턴2
                        if (preChar == '0' && postChar == '0') {
                            idx++;
                            patternOne = false;
                            isPass = false;
                            count = 0;

                        }

                        if (preChar == '1') {

                            if (postChar == '1') {
                                continue;
                            } else if (postChar == '0') {
                                idx++;

                                if (idx + 1 < pattern.length()) {
                                    postChar = pattern.charAt(idx + 1);

                                    // 패턴1 -> 패턴 2
                                    if (postChar == '1') {
                                        patternOne = false;

                                        // 패턴1 -> 패턴1
                                    } else {
                                        count = 1;
                                    }

                                    isPass = false;
                                } else {
                                    isPass = false;
                                    break;
                                }
                            }
                        }
                    }
                }

                // 패턴2
            } else {
                if (p == '1') {
                    isPass = true;

                    if (idx + 1 < pattern.length()) {

                        p = pattern.charAt(++idx);

                        // 패턴2 -> 패턴2
                        if (p == '0') {
                            patternOne = false;
                            isPass = false;

                            // 패턴2 -> 패턴1
                        } else {
                            patternOne = true;
                            count = 0;
                            isPass = false;
                        }
                    }

                    // System.out.println(
                    //         "pattern2 " + p + " isPass " + isPass + " patternOne " + patternOne);

                } else {
                    isPass = false;
                    break;
                }

            }
        }

        if (isPass) {
            sb.append(YES).append("\n");
        } else {
            sb.append(NO).append("\n");
        }

    }

    public static void main(String[] args) throws IOException {
        inputData();

        System.out.println(sb);
    }
}
