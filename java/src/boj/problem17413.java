package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class problem17413 {
    /*
     * 
     * 문자열이 주어졌을 때 단어만 뒤집으려고 한다.
     * 문자열은 다음과 같은 규칙을 갖는다.
     * - 알파벳 소문자, 숫자, 공백, 특수문자('<', '>')로만 이루어져 있다.
     * - '<', '>'가 문자열에 있는 경우 번갈아가면서 등장하고 '<'이 먼저 등장한다.
     * 
     * 단어는 알파벳 소문자와 숫자로 이루어진 부분 문자열이다
     * <> 태그는 단어가 아니며 태그와 단어 사이에는 공백이 없다.
     * 
     * 
     * 입력받은 문자열을 순서대로 확인하면서 '<'가 등장하면 '>'가 등장할 때까지 뒤집기를 멈춘다.
     * 일반 문자열이 나오면 공백이 등장할 때까지 저장한 후에 공백이 등장하면 저장한 문자열을 뒤집는다.
     * 
     */

    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringBuilder answer = new StringBuilder();
    static StringBuilder temp = new StringBuilder();

    static boolean tagOpen;

    public static void main(String[] args) throws IOException {

        tagOpen = false;

        // 입력문자열을 받는다.
        String inputString = br.readLine();

        for (int idx = 0; idx < inputString.length(); idx++) {

            char currCharacter = inputString.charAt(idx);

            // 태그가 열러있고 현재 문자가 >이 아니면 정답에 추가
            if (tagOpen && currCharacter != '>') {
                answer.append(currCharacter);
            } else{
                // 현재 문자가 <이면 정답에 추가한 후 
                // 태그 열림 표시
                if (currCharacter == '<') {
                    // 대기문자열이 존재하면 대기문자열을 뒤집고 정답에 추가
                    // 대기문자열은 초기화
                    if (!temp.isEmpty()) {
                        answer.append(temp.reverse());
                        temp = new StringBuilder();
                    }
                    answer.append(currCharacter);
                    tagOpen = true;
                    // 현재 문자가 >이면 정답에 추가한 후 
                    // 태그 닫힘 표시
                } else if (currCharacter == '>') {
                    answer.append(currCharacter);
                    tagOpen = false;
                } else if (currCharacter == ' ') {
                    answer.append(temp.reverse());
                    temp = new StringBuilder();
                    answer.append(currCharacter);
                } else {
                    temp.append(currCharacter);
                }

            }

        }

        if(!temp.isEmpty()){
            answer.append(temp.reverse());
        }

        System.out.println(answer);
    }

}
