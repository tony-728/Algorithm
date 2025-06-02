package boj;

import java.io.*;
import java.util.*;

public class problem5397 {
    /*
     * 비밀번호를 입력한다. 화살표로 커서를 이동하거나 백스페이스로 입력한 문자를 지우기도 한다.
     * 
     * 최종 비밀번호를 구하라
     * 
     * stack과 lickedList로 구현할 수 있다.
     * arrayList가 아닌 linkedList로 구현해야한다. 왜냐하면 수정 삭제가 linkedList가 더 빠르기 때문
     */

    static void solutionByLinkedList() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 0; tc < testCase; tc++) {

            String password = br.readLine().trim();

            LinkedList<Character> passwordList = new LinkedList<>();
            ListIterator<Character> iter = passwordList.listIterator();

            for (int idx = 0; idx < password.length(); idx++) {

                char c = password.charAt(idx);

                switch (c) {
                    case '<':
                        if (iter.hasPrevious()) {
                            iter.previous();
                        }
                        break;
                    case '>':
                        if (iter.hasNext()) {
                            iter.next();
                        }
                        break;
                    case '-':
                        if (iter.hasPrevious()) {
                            iter.previous();
                            iter.remove();
                        }
                        break;
                    default:
                        iter.add(c);
                        break;
                }
            }

            for (Character c : passwordList) {
                sb.append(c);
            }
            sb.append("\n");
        }

        System.out.println(sb);

    }

    static void solutionByStack() throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringBuilder sb = new StringBuilder();

        int testCase = Integer.parseInt(br.readLine().trim());

        for (int tc = 0; tc < testCase; tc++) {

            String password = br.readLine().trim();

            Deque<Character> leftStack = new ArrayDeque<>();
            Deque<Character> rightStack = new ArrayDeque<>();

            for (int idx = 0; idx < password.length(); idx++) {

                char c = password.charAt(idx);

                switch (c) {
                    case '<':
                        if (!leftStack.isEmpty()) {
                            rightStack.push(leftStack.pop());
                        }
                        break;
                    case '>':
                        if (!rightStack.isEmpty()) {
                            leftStack.push(rightStack.pop());
                        }
                        break;
                    case '-':
                        if (!leftStack.isEmpty()) {
                            leftStack.pop();
                        }
                        break;
                    default:
                        leftStack.push(c);
                        break;
                }
            }

            while(!leftStack.isEmpty()) {
                rightStack.push(leftStack.pop());
            }
            while(!rightStack.isEmpty()){
                sb.append(rightStack.pop());
            }
            sb.append("\n");
        }

        System.out.println(sb);

    }

    public static void main(String[] args) throws IOException {

        solutionByStack();
    }
}
