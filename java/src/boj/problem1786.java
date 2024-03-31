package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class problem1786 {
    /*
     * KMP 알고리즘 구현하기
     * 
     * 
     * 1. 부분일치 테이블 만들기
     * 2. 본문 문자열과 패턴을 부분일치 테이블을 이용하여 매칭하기
     * 
     * 
     * 첫번째 줄에 T: 본문에 해당하는 문자열이 주어진다.
     * 두번째 줄에 P: 패턴에 해당하는 문자열이 주어진다.
     * 
     * T에 P가 몇 번 나타나는지 출력하고
     * 다음 줄에 P가 나타나는 위치를 공백으로 구분하여 출력한다.
     * 
     * 
     * 1. 입력받은 패턴으로 부분일치 테이블을 만든다.
     * 2. 부분일치 테이블을 이용하여 본문에 패턴을 매칭시킨다.
     * 
     * 
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder sb = new StringBuilder();

    static String originText;
    static String pattern;

    static int[] submatchingTable;

    static int answer;

    // 부분일치 테이블 만들기
    public static void makeSubmatchingTable() {

        submatchingTable = new int[pattern.length()];

        int prefixIdx = 0;

        for (int idx = 1; idx < pattern.length(); idx++) {
            // 접두사와 접미사가 한번이라도 일치했었고 현재 비교 중인 문자가 다른경우
            // 현재 인덱스-1에 해당하는 테이블값이 prefixidx가 된다.
            // 원본의 접미사와 패턴의 접두사가 같아지거나 아예 처음 문자로 맞춰질 때까지 반복
            while (prefixIdx > 0 && pattern.charAt(idx) != pattern.charAt(prefixIdx)) {
                prefixIdx = submatchingTable[prefixIdx - 1];
            }

            // 접미사와 접두사에 문자가 같다.
            if (pattern.charAt(idx) == pattern.charAt(prefixIdx)) {
                submatchingTable[idx] = ++prefixIdx;

            }
        }

    }

    public static void search() {

        int originLength = originText.length();
        int patternLength = pattern.length();

        int patternIdx = 0;

        // 원본 텍스트만큼 반복한다.
        for (int originIdx = 0; originIdx < originLength; originIdx++) {

            // 접두사와 접미사가 한번이라도 일치했었고 현재 비교 중인 문자가 다른경우
            // 현재 인덱스-1에 해당하는 테이블값이 patternIdx가 된다.
            // 원본의 접미사와 패턴의 접두사가 같아지거나 아예 처음 문자로 맞춰질 때까지 반복
            while (patternIdx > 0 && originText.charAt(originIdx) != pattern.charAt(patternIdx)) {
                patternIdx = submatchingTable[patternIdx - 1];
            }

            // 현재 비교하는 문자가 동일하면
            if (originText.charAt(originIdx) == pattern.charAt(patternIdx)) {

                // 현재까지 비교한 문자열 인덱스와 패턴의 길이가 일치하면
                // 패턴이 일치하는 구간을 찾음
                // 위치 저장
                if (patternIdx == patternLength - 1) {
                    sb.append(originIdx - patternLength + 2).append(" ");
                    answer++;

                    patternIdx = submatchingTable[patternIdx];

                    // 현재 비교하는 부분만 일치하는 경우
                    // 패턴의 다음 문자를 비교하기 위해 패턴의 인덱스 증가
                } else {
                    patternIdx++;
                }

            }
        }

    }

    public static void main(String[] args) throws IOException {

        answer = 0;

        originText = br.readLine();
        pattern = br.readLine();

        makeSubmatchingTable();

        search();

        System.out.println(answer);
        System.out.println(sb);
    }
}
