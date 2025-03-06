package main.SWEA1873;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	public static void main(String[] args) {
		new Solution().run();
	}

	private void run() {

		StringBuilder result = new StringBuilder();
		Resolver r = new Resolver();

		try (BufferedReader br = new BufferedReader(new InputStreamReader(System.in))) {

			int TEST_CASES = Integer.parseInt(br.readLine());
			for (int TEST_CASE = 0; TEST_CASE < TEST_CASES; TEST_CASE++) {
				Input ip = readInput(br);
				result.append("#").append(TEST_CASE + 1).append(" ").append(r.resolve(ip.field, ip.orders)).append("\n");
			}

			System.out.println(result.toString().trim());

		} catch (IOException e) {
			throw new RuntimeException(e);
		}
	}

	private Input readInput(BufferedReader br) throws IOException {
		StringTokenizer st = new StringTokenizer(br.readLine());
		int height = Integer.parseInt(st.nextToken());
		int width = Integer.parseInt(st.nextToken());

		char[][] field = new char[height][width];

		for (int i = 0; i < height; i++) {
			String token = br.readLine();
			for (int j = 0; j < width; j++) {
				field[i][j] = token.charAt(j);
			}
		}

		br.readLine();
		char[] orders = br.readLine().toCharArray();

		return new Input(field, orders);
	}

	private static class Input {

		final char[][] field;
		final char[] orders;

		public Input(char[][] field, char[] orders) {
			this.field = field;
			this.orders = orders;
		}
	}
}

class Resolver {

	private Field field;
	private Tank tank;

	public String resolve(char[][] field, char[] orders) {
		init(field);
		calc(orders);
		return this.field.toString(tank.standingHere(), tank.getDirection());
	}

	private void init(char[][] field) {
		for (int i = 0; i < field.length; i++) {
			for (int j = 0; j < field[i].length; j++) {
				if (Tank.isHere(field[i][j])) {
					this.tank = new Tank(field[i][j], i, j);
					field[i][j] = '.';
					this.field = new Field(field);
				}
			}
		}
	}

	private void calc(char[] orders) {
		for (char order : orders) {
			if (Order.isDirectionChangeRequest(order)) {
				if(field.isPlain(tank.standingHere(), order)) {
					tank.turnAndMove(order);
				}else {
					tank.turn(order);
				}
			} else {
				field.demolish(tank.standingHere(), tank.getDirection());
			}
		}
	}
}

class Field {

	char[][] field;

	public Field(char[][] field) {
		this.field = field;
	}

	public boolean isPlain(int[] location, char direction) {
		int[] nDirection = Order.getDirectionArr(direction);
		int nx = location[0] + nDirection[0];
		int ny = location[1] + nDirection[1];
		return isWithinField(nx, ny) && field[nx][ny] == '.';
	}

	public boolean isPlain(int x, int y) {
		return field[x][y] == '.';
	}

	public boolean isBrick(int x, int y) {
		return field[x][y] == '*';
	}

	public boolean isSteal(int x, int y) {
		return field[x][y] == '#';
	}

	public boolean isWater(int x, int y) {
		return field[x][y] == '-';
	}

	private boolean isWithinField(int x, int y) {
		return 0 <= x && x < field.length && 0 <= y && y < field[x].length;
	}

	public void demolish(int[] location, char direction) {
		int x = location[0];
		int y = location[1];
		int[] nDirection = Order.getDirectionArr(direction);
		while (true) {
			x += nDirection[0];
			y += nDirection[1];

			if(!isWithinField(x, y)) break;
			if(isSteal(x, y)) break;
			if(isPlain(x, y) || isWater(x, y)) continue;
			if(isBrick(x, y)){
				field[x][y] = '.';
				break;
			}

		}
	}

	public String toString(int[] location, char direction) {
		field[location[0]][location[1]] = direction;
		StringBuilder result = new StringBuilder();
		for (char[] chars : field) {
			for (char aChar : chars) {
				result.append(aChar);
			}
			result.append("\n");
		}
		result.setLength(result.length() - 1);
		return result.toString();
	}
}

class Tank {

	private char direction;
	private int x;
	private int y;

	public Tank(char direction, int x, int y) {
		this.direction = direction;
		this.x = x;
		this.y = y;
	}

	public char getDirection() {
		if (direction == 'U') {
			direction = '^';
		} else if (direction == 'D') {
			direction = 'v';
		} else if (direction == 'L') {
			direction = '<';
		} else if (direction == 'R') {
			direction = '>';
		}
		return direction;
	}

	public static boolean isHere(char ch) {
		return ch == '<' || ch == '>' || ch == '^' || ch == 'v';
	}

	public int[] standingHere() {
		return new int[]{x, y};
	}

	public void turnAndMove(char direction) {
		int[] nDirection = Order.getDirectionArr(direction);
		this.direction = direction;
		this.x += nDirection[0];
		this.y += nDirection[1];
	}

	public void turn(char direction) {
		char nDirection;
		if(direction == 'U') {
			nDirection = '^';
		} else if (direction == 'D') {
			nDirection = 'v';
		} else if (direction == 'L') {
			nDirection = '<';
		} else {
			nDirection = '>';
		}
		this.direction = nDirection;
	}
}

abstract class Order {

	private static final char[] direction = {'U', 'D', 'L', 'R'};

	public static boolean isDirectionChangeRequest(char order) {
		for (char c : direction) {
			if(c == order) return true;
		}
		return false;
	}

	public static int[] getDirectionArr(char d) {
		if (d == '<' || d == 'L') {
			return new int[]{0, -1};
		} else if (d == '>' || d == 'R') {
			return new int[]{0, 1};
		} else if (d == 'v' || d == 'D') {
			return new int[]{1, 0};
		} else if (d == '^' || d == 'U') {
			return new int[]{-1, 0};
		} else {
			return null;
		}
	}
}

