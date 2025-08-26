package boj;

import java.io.*;
import java.util.*;

public class problem29278 {

    static final int PUSH = 1;
    static final int POP = 2;
    static final int SIZE = 3;
    static final int ISEMPTY = 4;
    static final int PEEK = 5;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        StringTokenizer st;

        int numOfCommand = Integer.parseInt(br.readLine().trim());

        Deque<Integer> q = new ArrayDeque<>();

        StringBuilder sb = new StringBuilder();

        for (int idx = 0; idx < numOfCommand; idx++) {
            st = new StringTokenizer(br.readLine().trim());

            int command = Integer.parseInt(st.nextToken());

            switch (command) {
                case PUSH:
                    int value = Integer.parseInt(st.nextToken());
                    q.push(value);
                    break;

                case POP:
                    if (q.isEmpty()) {
                        sb.append(-1).append("\n");
                    } else {
                        sb.append(q.pop()).append("\n");
                    }
                    break;

                case SIZE:
                    sb.append(q.size()).append("\n");
                    break;

                case ISEMPTY:
                    if (q.isEmpty()) {
                        sb.append(1).append("\n");
                    } else {
                        sb.append(0).append("\n");
                    }
                    break;

                case PEEK:
                    if (q.isEmpty()) {
                        sb.append(-1).append("\n");
                    } else {
                        sb.append(q.peek()).append("\n");
                    }
                    break;
            }
        }

        System.out.println(sb);

    }
}
