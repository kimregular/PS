# 알고리즘 문제 풀이 레포

<!-- TOC -->
* [알고리즘 문제 풀이 레포](#알고리즘-문제-풀이-레포)
  * [코드 스니펫](#코드-스니펫)
    * [순열](#순열)
    * [다음 순열](#다음-순열)
    * [조합](#조합)
    * [조합 계산하기(재귀 + dp)](#조합-계산하기재귀--dp)
    * [이분탐색 원소 압축](#이분탐색-원소-압축)
    * [마름모로 배열 탐색](#마름모로-배열-탐색)
    * [N-Queen](#n-queen)
    * [2차원 누적합](#2차원-누적합)
<!-- TOC -->

## 코드 스니펫

### 순열

> 어떤 집합의 원소들을 특정한 순서대로 배열하는 것

순서가 중요하므로 (1, 2) != (2, 1)

> $$_nP_r = \frac{n!}{(n-r)!}$$

> $$_nP_r = n \times _{n-1}P_{r-1}$$

```java
class Solution {

	private int field;
	private int select;
	private boolean[] used;
	private int[] permutated;
	private StringBuilder answer;

	public String solution(int[] input) {
		init(input);
		getPermutations(0);
		return answer.toString();
	}

	public void getPermutations(int cnt) {
		if (cnt == select) {
			answer.append(savePermutated()).append("\n");
			return;
		}

		for (int i = 1; i <= field; i++) {
			if (used[i]) continue;
			used[i] = true;
			permutated[cnt] = i;
			getPermutations(cnt + 1);
			used[i] = false;
		}
	}

	private void init(int[] input) {
		this.field = input[0];
		this.select = input[1];
		this.used = new boolean[field + 1];
		this.permutated = new int[select];
		this.answer = new StringBuilder();
	}

	private String savePermutated() {
		StringBuilder result = new StringBuilder();
		for (int i : permutated) {
			result.append(i).append(" ");
		}
		return result.toString();
	}
}
```

### 다음 순열

> 지금 순열에서 사전 순으로 다음 순열을 생성한다.

1. 배열을 오름차순으로 정렬
2. 뒤쪽부터 탐색하며 교환위치(i - 1) 찾기(i : 꼭대기)
3. 뒤쪽부터 탐색하며 교환위치(i - 1)와 교환할 큰 값 위치(j) 찾기
4. 두 위치 값(i - 1, j) 교환
5. 꼭대기위치부터 맨 뒤까지 오름차순 정렬

활용하면 조합을 구할 수도 있다.

```java
import java.util.Arrays;

class Solution {

	int[] field;
	StringBuilder result;

	public String solution(int[] field) {
		init(field);
		Arrays.sort(field); // 정렬 필수!

		do {
			savePermutation();
		} while (nextPermutate());
	}

	private void init(int[] field) {
		this.field = field;
		this.result = new StringBuilder();
	}

	private void savePermutation() {
		for (int i : field) {
			result.append(i).append(" ");
		}
		result.append("\n");
	}

	private boolean nextPermutate() {
		// 현상태의 순열에서 사전식으로 다음 순열이 존재하면 true, 아니면 false

		// 1. 뒤쪽부터 탐색하며 꼭대기(i) 찾기, 교환위치(i-1)을 찾기위해 실행
		int i = field.length - 1;
		while (i > 0 && field[i - 1] >= field[i])
			--i;

		if (i == 0) return false;
		// 교환자리가 없다(가장 큰 순열 상태)

		// 2. i - 1 교환자리의 값과 교환할 한단계 큰 수를 뒤에서부터 찾기
		int j = field.length - 1;
		while (field[i - 1] >= field[j]) --j;

		// 3. i-1 자리와 j자리의 값 교환
		swap(i - 1, j);

		// 4. i - 1 자리의 한단계 큰수로 변화를 줬으니 i 꼭대기 위치부터 맨 뒤까지 가장 작은 수를 만듦(오름차순 정렬)
		int k = field.length - 1;
		while (i < k) swap(i++, k--);

		return true;
	}

	public void swap(int i, int j) {
		int temp = field[i];
		field[i] = field[j];
		field[j] = temp;
	}
}
```

### 조합

> 어떤 집합의 원소들을 순서 상관 없이 배열하는 것

순서가 상관 없으므로 (1, 2) == (2, 1)

$$_nC_r = \frac{_nP_r}{r!} = \frac{n!}{r!(n - r)!}$$

```java
class Solution {
	private int field;
	private int select;
	private int[] combinated;
	private StringBuilder result;

	public String solution(int[] input) {
		init(input);
		combinate(0, 1);
		return result.toString();
	}

	private void init(int[] input) {
		this.field = input[0];
		this.select = input[1];
		this.combinated = new int[select];
		this.result = new StringBuilder();
	}

	private void combinate(int cnt, int start) {
		if (cnt == select) {
			saveCombinated();
			return;
		}

		for (int i = start; i <= field; i++) {
			combinated[cnt] = i;
			combinate(cnt + 1, i + 1); // i + 1을 넘겨서 중복을 방지해야 함
		}
	}

	private void saveCombinated() {
		for (int i : combinated) {
			result.append(i).append(" ");
		}
		result.append("\n");
	}
}

```

### 조합 계산하기(재귀 + dp)

- 파스칼의 삼각형

$$_nC_r = _{n-1}C_{r-1} + _{n-1}C_r$$

> `n개 중 나를 고르고(n - 1), 나머지 조합 구하기 (r - 1)` +
`n개 중 나를 고르지 않고(n - 1), 나머지 조합 구하기(r)`

* 나를 고르지 않은 경우에는 r이 줄어들지 않는다!

```java
class Solution {

	private BigInteger[][] dp;

	public BigInteger solution(int[] input) {
		init(input);
		return calc(input[0], input[1]);
	}

	private void init(int[] input) {
		this.dp = new BigInteger[input[0] + 1][input[1] + 1];
	}

	private BigInteger calc(int n, int r) {
		if (r == 0 || n == r) // nC0 이거나 nCn 이면 1이다.
			return BigInteger.ONE;
		if (dp[n][r] != null) return dp[n][r];
		dp[n][r] = calc(n - 1, r - 1).add(calc(n - 1, r));
		// nCr = n-1Cr-1 + n-1Cr
		return dp[n][r];
	}
}

```

### 이분탐색 원소 압축

```java
class Solution {

	private int[] field;

	public String solution(int[] field, int[] targets) {
		init(field);
		StringBuilder result = new StringBuilder();
		for (int target : targets) {
			result.append(getUpperBound(target) - getLowerBound(target)).append(" ");
		}

		return result.toString();
	}

	private void init(int[] field) {
		Arrays.sort(field);
		this.field = field;
	}

	private int getUpperBound(int target) {
		int lp = 0;
		int rp = field.length - 1;

		while (lp <= rp) {
			int mid = (lp + rp) / 2;

			if (field[mid] <= target) {
				lp = mid + 1;
			} else {
				rp = mid - 1;
			}
		}
		return lp - 1;
	}

	private int getLowerBound(int target) {
		int lp = 0;
		int rp = field.length - 1;

		while (lp <= rp) {
			int mid = (lp + rp) / 2;

			if (field[mid] < target) {
				lp = mid + 1;
			} else {
				rp = mid - 1;
			}
		}
		return lp - 1;
	}
}
```

### 마름모로 배열 탐색

```java
class Resolver {

	public int resolve(int[][] field) {
		int result = 0;
		int center = field.length / 2;

		for (int i = 0; i < field.length; i++) {
			int start = Math.abs(center - i);
			int end = field.length - start;

			for (int j = start; j < end; j++) {
				result += field[i][j];
			}
		}

		return result;
	}
}
```

### N-Queen

```java
class Solution {

	private int N;
	private boolean[] col;
	private boolean[] slash;
	private boolean[] bSlash;
	private int result;

	public int solution(int N) {
		init(N);
		setQueens(1);
		return result;
	}

	private void init(int N) {
		this.N = N;
		this.col = new boolean[N + 1]; // 0은 사용하지 않는다.
		this.slash = new boolean[N + N + 1];
		this.bSlash = new boolean[N + N];
		this.result = 0;
	}

	private void setQueens(int rowNum) {
		if (rowNum == col.length) {
			result++;
			return;
		}

		for (int nextCol = 1; nextCol <= N; nextCol++) {
			if (isUnavailable(rowNum, nextCol))
				continue;
			col[nextCol] = slash[rowNum + nextCol] = bSlash[rowNum - nextCol + N] = true;
			setQueens(rowNum + 1);
			col[nextCol] = slash[rowNum + nextCol] = bSlash[rowNum - nextCol + N] = false;
		}
	}

	private boolean isUnavailable(int rowNum, int nextCol) {
		return col[nextCol] || slash[rowNum + nextCol] || bSlash[rowNum - nextCol + N];
	}
}
```

### 2차원 누적합

```java
class Solution {

	private int[][] accumulatedField;

	public String solution(int[][] field, int[][] ranges) {
		init(field);

		StringBuilder result = new StringBuilder();
		for (int[] range : ranges) {
			result.append(getValueOf(range)).append("\n");
		}
		return result.toString().trim();
	}

	private void init(int[][] field) {
		int n = field.length;
		this.accumulatedField = new int[n + 1][n + 1];

		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= n; j++) {
				accumulatedField[i][j] = field[i - 1][j - 1]
						+ accumulatedField[i - 1][j]
						+ accumulatedField[i][j - 1]
						- accumulatedField[i - 1][j - 1];
			}
		}
	}

	private int getValueOf(int[] range) {
		int startX = range[0];
		int startY = range[1];
		int endX = range[2];
		int endY = range[3];

		return accumulatedField[endX][endY]
				- accumulatedField[startX - 1][endY]
				- accumulatedField[endX][startY - 1]
				+ accumulatedField[startX - 1][startY - 1];
	}
}

```