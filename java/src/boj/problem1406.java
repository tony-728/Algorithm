package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.StringTokenizer;

public class problem1406 {
    /*
     * 커서가 있다.
     * 커서는 문장의 맨 앞, 맨 뒤 또는 중간 임의의 곳에 위치할 수 있다.
     * 길이가 L인 문자열이 현재 편집기에 입력되어 있으면 커서가 위치할 수 있는 곳은 L+1가지 경우가 있다.
     * 
     * L: 커서를 왼쪽으로 한 칸 옮김(커서가 문장의 맨 앞이면 무시)
     * D: 커서를 오른쪽으로 한 칸 옮김
     * B: 커서 왼쪽에 있는 문자를 삭제함
     * P $: $라는 문자를 커서 왼쪽에 추가함
     * 
     * 문자열이 입력된다. 
     * 문자열의 길이는 N 영어 소문자로 이루져 있다., 길이는 100,000을 넘지 않는다.
     * 
     * 명령어의 개수 M
     * 명령어가 주어진다.
     * 
     * 커서는 문장의 맨 뒤에 위치하고 있다.
     * 
     * iterator를 사용해하는 문제 
     * 
     */


    static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    static StringTokenizer st;
    static StringBuilder sb = new StringBuilder();

    static final String MOVE_LEFT = "L";
    static final String MOVE_RIGHT = "D";
    static final String DELETE = "B";
    static final String ADD = "P";

    static String inputString;
    static int numOfCommand;
    static String command;
    static char extraCharacter;

    static List<Character> inputList = new LinkedList<>();

    public static void main(String[] args) throws IOException {

        // 문자열을 입력
        inputString = br.readLine().trim();

        // 입력받은 문자열을 링크드리스트에 저장
        for (int idx = 0; idx < inputString.length(); idx++) {
            inputList.add(inputString.charAt(idx));
        }

        // 리스트에 iterator 객체 생성
        // 포인터와 비슷한 개념
        ListIterator<Character> cursor = inputList.listIterator(inputString.length());

        // 실행할 명령어 개수 입력
        numOfCommand = Integer.parseInt(br.readLine().trim());

        for (int idx = 0; idx < numOfCommand; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            command = st.nextToken();

            if (command.equals(MOVE_LEFT)) {
                // 왼쪽으로 갈 수 있으면 이동한다.
                if (cursor.hasPrevious()) {
                    cursor.previous();
                }

            } else if (command.equals(MOVE_RIGHT)) {
                // 오른쪽으로 갈 수 있으면 이동한다.
                if (cursor.hasNext()) {
                    cursor.next();
                }

            } else if (command.equals(DELETE)) {
                // 왼쪽으로 갈 수 있으면 이동한다.
                // 이동 후에 문자를 제거
                if (cursor.hasPrevious()) {
                    cursor.previous();
                    cursor.remove();
                }

            } else if (command.equals(ADD)) {
                // 오른쪽에 문자 추가
                extraCharacter = st.nextToken().charAt(0);

                cursor.add(extraCharacter);
            }

        }

        for(Character c: inputList){
            sb.append(c);
        }

        System.out.println(sb);

    }


}
