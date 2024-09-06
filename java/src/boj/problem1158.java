package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.LinkedList;
import java.util.List;
import java.util.StringTokenizer;

public class problem1158 {
    /*
     * 1번부터 N번까지 N명의 사람이 원을 이루면서 앉아있고, 양의 정수 K(≤ N)가 주어진다.
     * 이제 순서대로 K번째 사람을 제거한다.
     * 한 사람이 제거되면 남은 사람들로 이루어진 원을 따라 이 과정을 계속해 나간다.
     * 
     */

    public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
    public static StringTokenizer st;
    public static StringBuilder sb = new StringBuilder();

    public static int elementCount;
    public static int outCount;
    public static int count;
    public static int index;
    public static List<Integer> elementList;

    static void inputTestCase() throws IOException {
        st = new StringTokenizer(br.readLine().trim());

        elementCount = Integer.parseInt(st.nextToken());
        outCount = Integer.parseInt(st.nextToken());

        elementList = new LinkedList<>();

        for (int idx = 1; idx <= elementCount; idx++) {
            elementList.add(idx);
        }

        sb.append("<");
    }

    static void makeList() {
        count = 0;
        index = 0;
        // 한사람이 남을 때까지 진행한다.
        while (elementList.size() > 1) {
            index = ++index % elementList.size(); // 리스트의 범위를 벗어나지 않도록 나머지로 계산
            count++; // 나가는 카운트를 계산

            // 나가는 카운트가 될 때
            if (count == outCount) {
                index--; // 현재 인덱스를 감소, 자리를 빼 빈 인덱스를 맞추기 위함
                // 인덱스가 음수일 땐 마지막 인덱스를 가리키도록 함
                if (index < 0) {
                    index = elementList.size() - 1;
                }
                sb.append(elementList.get(index)).append(", ");
                elementList.remove(index);
                count = 0;

            }
        }
        sb.append(elementList.get(0)).append(">");
    }

    public static void main(String[] args) throws IOException {
        inputTestCase();
        makeList();

        System.out.println(sb);
    }

}
