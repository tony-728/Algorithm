package swea;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class problem1873 {
	/*
	 *
	 * .	평지(전차가 들어갈 수 있다.)
	 * *	벽돌로 만들어진 벽
	 * #	강철로 만들어진 벽
	 * -	물(전차는 들어갈 수 없다.)
	 * ^	위쪽을 바라보는 전차(아래는 평지이다.)
	 * v	아래쪽을 바라보는 전차(아래는 평지이다.)
	 * <	왼쪽을 바라보는 전차(아래는 평지이다.)
	 * >	오른쪽을 바라보는 전차(아래는 평지이다.)
	 * 
	 * 
	 * U	Up : 전차가 바라보는 방향을 위쪽으로 바꾸고, 한 칸 위의 칸이 평지라면 위 그 칸으로 이동한다.
	 * D	Down : 전차가 바라보는 방향을 아래쪽으로 바꾸고, 한 칸 아래의 칸이 평지라면 그 칸으로 이동한다.
	 * L	Left : 전차가 바라보는 방향을 왼쪽으로 바꾸고, 한 칸 왼쪽의 칸이 평지라면 그 칸으로 이동한다.
	 * R	Right : 전차가 바라보는 방향을 오른쪽으로 바꾸고, 한 칸 오른쪽의 칸이 평지라면 그 칸으로 이동한다.
	 * S	Shoot : 전차가 현재 바라보고 있는 방향으로 포탄을 발사한다.
	 * 
	 * 포탄을 발사하면 벽에 충돌하거나 맵 밖으로 나갈 때까지 진행한다.
	 * 벽돌 벽이면 평지로 바뀐다.
	 * 
	 * 맵에 존재하는 전차는 하나이다.
	 * 
	 * 맵이 입력으로 주어진다.
	 * 1. 전차에 위치를 찾아야 한다.
	 * 
	 * 명령이 주어진다
	 * - 명령에 따라서 전차에 맞는 행동을 한다.
	 */

	public static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
	public static StringTokenizer st;
	public static StringBuilder sb;

	public static int testCase;
	public static int height;
	public static int width;
	public static char[][] map;
	public static int[] tankPosition;

	public static int numOfCommand;
	public static char[] commandList;
	public static char command;

	public static final char HEAD_UP = '^';
	public static final char HEAD_DOWN = 'v';
	public static final char HEAD_LEFT = '<';
	public static final char HEAD_RIGHT = '>';

	public static final char LAND = '.';
	public static final char BLOCK = '*';
	public static final char STEEL_BLOCK = '#';
	public static final char WATER = '-';


	public static void move(int head) {

		// 현재 탱크 위치 불러오기
		// 탱크 방향 바꾸기
		int tankRowIdx = tankPosition[0];
		int tankColIdx = tankPosition[1];

		int newTankRowIdx = 0;
		int newTankColIdx = 0;

		char tankHead = ' ';

		// 탱크의 헤드 조정 및 새로운 위치 설정
		if (head == 0) {
			tankHead = HEAD_UP;
			newTankRowIdx = tankRowIdx - 1;
			newTankColIdx = tankColIdx;

		} else if (head == 1) {
			tankHead = HEAD_DOWN;
			newTankRowIdx = tankRowIdx + 1;
			newTankColIdx = tankColIdx;

		} else if (head == 2) {
			tankHead = HEAD_LEFT;
			newTankRowIdx = tankRowIdx;
			newTankColIdx = tankColIdx - 1;

		} else if (head == 3) {
			tankHead = HEAD_RIGHT;
			newTankRowIdx = tankRowIdx;
			newTankColIdx = tankColIdx + 1;

		}

		map[tankRowIdx][tankColIdx] = tankHead;

		// 탱크를 위가 평지라면 움직인다.
		// 범위 확인
		if (0 <= newTankRowIdx && newTankRowIdx < height && 0 <= newTankColIdx
				&& newTankColIdx < width) {
			// 평지이면 이동
			if (map[newTankRowIdx][newTankColIdx] == LAND) {
				map[newTankRowIdx][newTankColIdx] = map[tankRowIdx][tankColIdx];

				// 탱크 위치 갱신
				tankPosition[0] = newTankRowIdx;
				tankPosition[1] = newTankColIdx;

				map[tankRowIdx][tankColIdx] = LAND;
			}
		}

		return;
	}

	public static void shoot() {

		// 탱크가 바라보고 있는 방향으로 포탄을 쏜다
		// 맵 끝까지 포탄이 날아가고 방향에 벽이 있으면 종료
		// 벽돌 벽이면 평지로 바꿈

		// 현재 탱크 위치 불러오기
		int tankRowIdx = tankPosition[0];
		int tankColIdx = tankPosition[1];
		char tankHead = map[tankRowIdx][tankColIdx];

		int moveRow = 0;
		int moveCol = 0;

		// 탱크가 보고 있는 방향에 따라서 포탄이 날아감
		if (tankHead == HEAD_UP) {
			moveRow = -1;
			moveCol = 0;
		} else if (tankHead == HEAD_DOWN) {
			moveRow = 1;
			moveCol = 0;
		} else if (tankHead == HEAD_LEFT) {
			moveRow = 0;
			moveCol = -1;
		} else if (tankHead == HEAD_RIGHT) {
			moveRow = 0;
			moveCol = 1;
		}

		int shotRowIdx = tankRowIdx + moveRow;
		int shotColIdx = tankColIdx + moveCol;

		// 포탄 이동
		while (true) {

			// 범위를 넘어가면 종료
			if (shotRowIdx < 0 || shotRowIdx >= height || shotColIdx < 0 || shotColIdx >= width) {
				break;
			}

			// 평지면 다음으로
			if (map[shotRowIdx][shotColIdx] == LAND || map[shotRowIdx][shotColIdx] == WATER) {
				shotRowIdx += moveRow;
				shotColIdx += moveCol;
			} else if (map[shotRowIdx][shotColIdx] == BLOCK) { // 벽돌 벽
				// 벽돌 벽이면 평지로 바꿈
				map[shotRowIdx][shotColIdx] = LAND;
				break;
			} else if (map[shotRowIdx][shotColIdx] == STEEL_BLOCK) { // 강철 벽
				break;
			}
		}

		return;
	}

	public static void main(String[] args) throws IOException {
		testCase = Integer.parseInt(br.readLine().trim());
		sb = new StringBuilder();

		for (int tc = 1; tc <= testCase; tc++) {
			st = new StringTokenizer(br.readLine().trim());

			height = Integer.parseInt(st.nextToken());
			width = Integer.parseInt(st.nextToken());

			map = new char[height][width];

			// 맵 입력받기
			for (int rowIdx = 0; rowIdx < height; rowIdx++) {
				char[] row = br.readLine().trim().toCharArray();
				for (int colIdx = 0; colIdx < width; colIdx++) {
					// 맵에서 탱크 위치 찾기
					if (row[colIdx] == '<' || row[colIdx] == '>' || row[colIdx] == '^'
							|| row[colIdx] == 'v') {
						tankPosition = new int[] {rowIdx, colIdx};
					}
					map[rowIdx][colIdx] = row[colIdx];
				}
			}

			// 명령어 입력받기
			numOfCommand = Integer.parseInt(br.readLine().trim());
			commandList = br.readLine().trim().toCharArray();


			// 명령어 실행
			for (int commandIdx = 0; commandIdx < numOfCommand; commandIdx++) {
				command = commandList[commandIdx];

				switch (command) {
					case 'U':
						move(0);
						break;
					case 'D':
						move(1);
						break;
					case 'L':
						move(2);
						break;
					case 'R':
						move(3);
						break;
					case 'S':
						shoot();
						break;
				}
			}

			sb.append(String.format("#%d ", tc));

			for (int rowIdx = 0; rowIdx < height; rowIdx++) {
				for (int colIdx = 0; colIdx < width; colIdx++) {
					sb.append(map[rowIdx][colIdx]);
				}
				sb.append("\n");
			}
		}

		System.out.println(sb);
	}
}
