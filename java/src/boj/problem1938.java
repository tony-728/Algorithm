package boj;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.PriorityQueue;

public class problem1938 {

	/*
	 * 3명의 사람으로 이루어진 통나무가 있다. 출발 위치에서 도착 위치로 이동하면 놀이가 끝난다. 통나무를 5가지 방법으로 이동할 수 있다.
	 * - 위로 한 칸 옮긴다.
	 * - 아래로 한 칸 옮긴다.
	 * - 왼쪽으로 한 칸 옮긴다.
	 * - 오른쪽으로 한 칸 옮긴다.
	 * - 중심점으로 90도 회전시킨다.
	 * - 회전을 할 때는 통나무가 둘러싸고 있는 3x3 정사각형의 구역에 단 하나의 나무도 없어야만 한다.
	 * 
	 * 출발위치에서 도착 위치로 이동할 때 필요한 최소 이동횟수를 구하라
	 * 
	 * 1은 이동할 수 없는 공간, 0은 이동할 수 있는 공간 통나무가 B, 도착위치는 E
	 * 
	 * 
	 * 5가지 종류로 dfs를 진행
	 * 상,하,좌,우,회전
	 * 
	 * 맵에 대한 방문처리는 가로,세로,2개의 비트를 활용한다.
	 * 2개의 비트를 활용하는 이유는 통나무의 중심만 위치하는 경우를 생각할 때
	 * 같은 위치더라도 가로로 위치하는 경우와 세로로 위치하는 경우가 다르기 때문에 두 경우를 각각 고려해야한다.
	 * 
	 * 방문 확인 시 3개의 나무 중 하나라도 방문하지 않은 곳에 갈 경우 전체 나무는 새로운 곳으로 가는 것이기 때문에
	 * 이동할 수 있도록 해야한다.
	 * 
	 * 통나무가 도착점에 도착할 때 바로 종료하기 위해서 우선순위큐를 활용한다.
	 * 
	 */

	static class Location {
		int rowIdx;
		int colIdx;

		public Location(int rowIdx, int colIdx) {
			this.rowIdx = rowIdx;
			this.colIdx = colIdx;
		}

		@Override
		public String toString() {
			return "Location [rowIdx=" + rowIdx + ", colIdx=" + colIdx + "]";
		}

	}

	static class Train implements Comparable<Train> {

		Location[] locList = new Location[LENGTH];

		int cost;

		public Train() {
		}

		public void setLocation(Location location, int idx) {
			locList[idx] = location;
		}

		public void setCost(int cost) {
			this.cost = cost;
		}

		public int getStatus() {
			// 가로인 경우 = 0
			if (locList[0].rowIdx == locList[2].rowIdx) {
				return 0;
				// 세로인 경우 = 1
			} else {
				return 1;
			}

		}

		@Override
		public int compareTo(Train o) {
			return this.cost - o.cost;
		}

		public void show() {
			for (Location loc : locList) {
				System.out.print(loc);
			}
			System.out.println();
		}

	}

	static class End {
		Location[] locList = new Location[LENGTH];

		public void setLocation(Location location, int idx) {
			locList[idx] = location;
		}
	}

	static final char ZERO = '0';

	static final char C_TRAIN = 'B';
	static final char C_END = 'E';
	static final int WALL = 1;
	static final int BLANK = 0;
	static final int TRAIN = 2;
	static final int END = 5;

	static final int CENTER = 1; // 통나무 중심 위치
	static final int LENGTH = 3; // 통나무 길이

	static final int[] deltaX = { -1, 0, 1, 0, 2 };
	static final int[] deltaY = { 0, -1, 0, 1, 2 };

	static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

	static int mapSize;
	static int[][] map;
	static boolean[][][] visited;

	static Train Start = new Train();
	static End endLocation = new End();

	static int answer;

	public static void inputTestCase() throws IOException {

		mapSize = Integer.parseInt(br.readLine().trim());

		map = new int[mapSize][mapSize];
		// 마지막 차원의 크기를 2로 하는 이유는 통나무는 가로 혹은 세로로 맵에 위치할 수 있다.
		// 중심만 봤을 때 가로로 놓았을 때와 세로로 놓았을 때가 다르기 때문에 2개의 비트만을 활용한다.
		visited = new boolean[mapSize][mapSize][1 << 2];

		int tIdx = 0;
		int eIdx = 0;

		for (int rowIdx = 0; rowIdx < mapSize; rowIdx++) {

			String row = br.readLine().trim();

			for (int colIdx = 0; colIdx < mapSize; colIdx++) {
				char value = row.charAt(colIdx);

				// 숫자인 경우
				if (Character.isDigit(value)) {
					map[rowIdx][colIdx] = value - ZERO;
				} else if (value == C_TRAIN) {
					map[rowIdx][colIdx] = TRAIN;

					Start.setLocation(new Location(rowIdx, colIdx), tIdx++);

				} else if (value == C_END) {
					map[rowIdx][colIdx] = END;

					endLocation.setLocation(new Location(rowIdx, colIdx), eIdx++);
				}

			}
		}
	}

	public static boolean checkVisited(Train train) {

		int status = train.getStatus();
		int visitedCount = 0;
		// 하나의 나무라도 방문하지 않은 곳으로 간다면 이동해야한다.
		for (int idx = 0; idx < LENGTH; idx++) {

			Location location = train.locList[idx];

			if (visited[location.rowIdx][location.colIdx][1 << status] == true) {
				visitedCount++;
			}
		}

		if (visitedCount == 3) {
			return true;
		} else {
			return false;
		}
	}

	public static boolean checkEnd(Train train) {

		for (int idx = 0; idx < LENGTH; idx++) {

			Location tLocation = train.locList[idx];
			Location eLocation = endLocation.locList[idx];

			// 종료지점과 단 하나의 위치라도 다르면 안된다.
			if (tLocation.rowIdx != eLocation.rowIdx || tLocation.colIdx != eLocation.colIdx) {
				return false;
			}
		}
		return true;

	}

	public static void goEnd() {

		PriorityQueue<Train> queue = new PriorityQueue<>();

		int status = Start.getStatus();
		for (int idx = 0; idx < LENGTH; idx++) {

			Location location = Start.locList[idx];

			visited[location.rowIdx][location.colIdx][1 << status] = true;
		}
		queue.offer(Start);

		while (!queue.isEmpty()) {

			Train train = queue.poll();

			// train.show();

			// 도착지와 일치한지 확인
			if (checkEnd(train)) {
				answer = train.cost;
				break;
			}

			Location first = train.locList[CENTER - 1];
			Location center = train.locList[CENTER];
			Location last = train.locList[CENTER + 1];

			for (int dir = 0; dir < deltaX.length; dir++) {
				Train newTrain = new Train();

				// 회전하는 경우
				if (deltaX[dir] == 2) {

					// 3x3 영역에 아무것도 없어야 한다.
					// 기차가 세로인지 가로인지 확인

					boolean rotationFlag = true;

					// 행이 같다는 것은 가로
					if (first.rowIdx == last.rowIdx) {

						int startRowIdx = first.rowIdx - 1;
						int startColIdx = first.colIdx;

						for (int rowIdx = 0; rowIdx < LENGTH; rowIdx++) {
							for (int colIdx = 0; colIdx < LENGTH; colIdx++) {

								int newRowIdx = startRowIdx + rowIdx;
								int newColIdx = startColIdx + colIdx;

								// 범위 확인
								if (newRowIdx < 0 || newRowIdx >= mapSize || newColIdx < 0 || newColIdx >= mapSize) {
									rotationFlag = false;
									break;
								}

								// 장애물이 있는지 확인
								if (map[newRowIdx][newColIdx] == WALL) {
									rotationFlag = false;
									break;
								}
							}
						}

						if (rotationFlag) {
							// 회전하기
							Location newFirst = new Location(first.rowIdx - 1, center.colIdx);
							Location newLast = new Location(last.rowIdx + 1, center.colIdx);

							newTrain.setLocation(newFirst, 0);
							newTrain.setLocation(center, 1);
							newTrain.setLocation(newLast, 2);

							newTrain.setCost(train.cost + 1);

							// 방문확인
							if (checkVisited(newTrain)) {
								continue;
							}

							status = newTrain.getStatus();

							for (int idx = 0; idx < LENGTH; idx++) {
								Location location = newTrain.locList[idx];
								visited[location.rowIdx][location.colIdx][1 << status] = true;
							}

							queue.offer(newTrain);

						}

						// 행이 다르다는 것은 세로
					} else if (first.colIdx == last.colIdx) {
						int startRowIdx = first.rowIdx;
						int startColIdx = first.colIdx - 1;

						outLoop: for (int rowIdx = 0; rowIdx < LENGTH; rowIdx++) {
							for (int colIdx = 0; colIdx < LENGTH; colIdx++) {

								int newRowIdx = startRowIdx + rowIdx;
								int newColIdx = startColIdx + colIdx;

								// 범위 확인
								if (newRowIdx < 0 || newRowIdx >= mapSize || newColIdx < 0 || newColIdx >= mapSize) {
									rotationFlag = false;
									break outLoop;
								}

								// 장애물이 있는지 확인
								if (map[newRowIdx][newColIdx] == WALL) {
									rotationFlag = false;
									break outLoop;
								}
							}
						}

						if (rotationFlag) {
							// 회전하기
							Location newFirst = new Location(center.rowIdx, first.colIdx - 1);
							Location newLast = new Location(center.rowIdx, last.colIdx + 1);

							newTrain.setLocation(newFirst, 0);
							newTrain.setLocation(center, 1);
							newTrain.setLocation(newLast, 2);

							newTrain.setCost(train.cost + 1);

							// 방문확인
							if (checkVisited(newTrain)) {
								continue;
							}

							status = newTrain.getStatus();

							for (int idx = 0; idx < LENGTH; idx++) {
								Location location = newTrain.locList[idx];
								visited[location.rowIdx][location.colIdx][1 << status] = true;
							}

							queue.offer(newTrain);

						}
					}

				} else {

					Deque<Location> locationQueue = new ArrayDeque<>();
					boolean flag = true;

					for (int idx = 0; idx < LENGTH; idx++) {

						Location location = train.locList[idx];

						int newRowIdx = location.rowIdx + deltaX[dir];
						int newColIdx = location.colIdx + deltaY[dir];

						// 기차의 하나라도 이동할 수 없으면 안된다.

						// 범위 확인
						if (newRowIdx < 0 || newRowIdx >= mapSize || newColIdx < 0 || newColIdx >= mapSize) {
							flag = false;
							break;
						}

						// 벽확인
						if (map[newRowIdx][newColIdx] == WALL) {
							flag = false;
							break;
						}

						locationQueue.offer(new Location(newRowIdx, newColIdx));

					}

					// 하나라도 조건을 만족하지 못하면 안된다.
					if (flag) {

						for (int idx = 0; idx < LENGTH; idx++) {
							Location loc = locationQueue.poll();
							newTrain.setLocation(loc, idx);
						}

						status = newTrain.getStatus();

						// 방문확인
						if (checkVisited(newTrain)) {
							continue;
						}

						for (int idx = 0; idx < LENGTH; idx++) {
							Location location = newTrain.locList[idx];
							visited[location.rowIdx][location.colIdx][1 << status] = true;
						}

						newTrain.setCost(train.cost + 1);

						queue.offer(newTrain);
					}
				}
			}
		}
	}

	public static void main(String[] args) throws IOException {

		inputTestCase();

		goEnd();

		if (answer == Integer.MAX_VALUE) {
			answer = 0;
		}

		System.out.println(answer);

	}

}
