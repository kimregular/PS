# 알고리즘 문제 풀이 레포

<!-- TOC -->
* [알고리즘 문제 풀이 레포](#알고리즘-문제-풀이-레포)
  * [코드 스니펫](#코드-스니펫)
    * [순열](#순열)
    * [다음 순열](#다음-순열)
    * [이전 순열](#이전-순열)
    * [n번째 수열 구하기](#n번째-수열-구하기)
    * [주어진 순열이 몇 번째 순열인지 계산하기](#주어진-순열이-몇-번째-순열인지-계산하기)
    * [조합](#조합)
    * [조합 계산하기(재귀 + dp)](#조합-계산하기재귀--dp)
    * [LIS](#lis)
    * [개선된 LIS](#개선된-lis)
    * [이분탐색 원소 압축](#이분탐색-원소-압축)
    * [마름모로 배열 탐색](#마름모로-배열-탐색)
    * [N-Queen](#n-queen)
    * [2차원 누적합](#2차원-누적합)
<!-- TOC -->

## 코드 스니펫

### 순열

시간 복잡도 : $O(N!)$

> 어떤 집합의 원소들을 특정한 순서대로 배열하는 것

순서가 중요하므로 (1, 2) != (2, 1)

> $$_nP_r = \frac{n!}{(n-r)!}$$

> $$ _nP_r = n \times _{n-1}P_{r-1} $$

```java
class Solution {

	private int field;
	// 전체 원소 수
	private int select;
	// 선택할 원소 수
	private boolean[] used;
	// 원소 사용 여부 체크
	private int[] permutated;
	// 현재 순열 저장
	private StringBuilder answer;
	// 결과 누적

	public String solution(int[] input) {
		init(input); // 초기화
		getPermutations(0); // 순열 생성
		return answer.toString(); // 생성된 순열 출력
	}

	private void init(int[] input) {
		this.field = input[0];
		this.select = input[1];
		this.used = new boolean[field + 1];
		this.permutated = new int[select];
		this.answer = new StringBuilder();
	}

	public void getPermutations(int cnt) {
		if (cnt == select) {
			answer.append(savePermutated()).append("\n");
			return;
		}

		for (int i = 1; i <= field; i++) {
			if (used[i]) continue;
			// 이미 사용된 숫자는 스킵

			used[i] = true; // 사용 체크
			permutated[cnt] = i; // 현재 자리 할당
			getPermutations(cnt + 1); // 다음 자리 재귀 호출
			used[i] = false; // 백트래킹
		}
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

### [다음 순열  ](https://www.acmicpc.net/problem/10972)

시간 복잡도 : $O(N)$

> 지금 순열에서 사전 순으로 다음 순열을 생성한다.

`ex) [1, 2, 3] -> [1, 3, 2]`

1. 순열의 뒤에서부터 앞으로 가면서 처음으로 내림차순이 되는 지점(`i`)을 찾는다.

- `ex) [5, 1, 2, 4, 3] 의 경우 i = 2, arr[i] = 2`

2. 만약 그런 `i`가 없다면(완전히 내림차순 정렬되어 있다면), 이미 마지막 순열이므로
   `-1` 출력
3. 만약 그런 `i`가 있다면 다시 뒤에서부터 `arr[i]` 보다 큰 수 `j` 를
   찾아 스왑한다.

- `ex) [5, 1, 2, 4, 3] 의 경우 j = 4, arr[j] = 3`
- `스왑 후 -> [5, 1, 3, 4, 2]`

4. `i` 부터 끝까지의 구간을 오름차순으로 정렬한다.

- `ex) [5, 1, 3, 2, 4]`

```java  
class Solution {

	private int[] field;

	public String solution(int[] field) {
		init(field);
		return nextPermutate() ? getResult() : "-1";
	}

	private void init(int[] field) {
		this.field = field;
	}

	private boolean nextPermutate() {
		int i = field.length - 1;

		while (i > 0 && field[i - 1] >= field[i])
			--i;
		// 1. 뒤에서부터 처음으로 감소하는 지점 찾기

		if (i == 0) return false;
		// 2. 마지막 순열이면 false 출력

		int j = field.length - 1;
		while (field[i - 1] >= field[j]) --j;
		// 3. i - 1 보다 큰 값 중 가장 마지막 값 찾기

		swap(i - 1, j);
		// 4. 스왑

		int k = field.length - 1;
		while (i < k) swap(i++, k--);
		// i 부터 끝까지 뒤집기

		return true;
	}

	private void swap(int i, int j) {
		int temp = field[i];
		field[i] = field[j];
		field[j] = temp;
	}

	private String getResult() {
		StringBuilder result = new StringBuilder();
		for (int i : field) {
			result.append(i).append(" ");
		}
		return result.toString().trim();
	}
}
```

### [이전 순열](https://www.acmicpc.net/problem/10973)

시간 복잡도 : $O(N)$

> 지금 순열에서 사전 순으로 이전 순열을 생성한다.

`ex) [1, 3, 2] -> [1, 2, 3]`

1. 순열의 뒤에서부터 앞으로 가면서 처음으로 오름차순이 되는 지점(`i`)을 찾는다.

- `ex) [5, 1, 2, 4, 3] 의 경우 i = 3, arr[i] = 4`

2. 만약 그런 `i`가 없다면(뒤에서부터 완전히 내림차순 정렬되어 있다면), 이미처음
   순열이므로 `-1` 출력
3. 만약 그런 `i`가 있다면 다시 뒤에서부터 `arr[i]` 보다 작은 수 `j` 를
   찾아 스왑한다.

- `ex) [5, 1, 2, 4, 3] 의 경우 j = 3, arr[j] = 4`
- `스왑 후 -> [5, 1, 2, 3, 4]`

4. `i` 부터 끝까지의 구간을 오름차순으로 정렬한다.

- `ex) [5, 1, 2, 3, 4]`

```java  
class Solution {

	private int[] field;

	public String solution(int[] field) {
		init(field);
		return prevPermutation() ? getPermutated() : "-1";
	}

	private void init(int[] field) {
		this.field = field;
	}

	// 이전 순열을 만드는 핵심 로직
	private boolean prevPermutation() {
		int i = field.length - 1;

		while (i > 0 && field[i - 1] <= field[i])
			i--;
		// 1. 뒤에서부터 처음으로 오름차순이 깨지는 지점 찾기

		if (i == 0) return false;
		// 2. 완전히 오름차순이면 (이미 첫 번째 순열이면) false 반환

		int j = field.length - 1;
		while (field[i - 1] <= field[j]) j--;
		// 3. 뒤에서부터 field[i - 1]보다 작은 수를 찾아 스왑할 위치 j 결정

		swap(i - 1, j);
		// 4. i - 1과 j를 스왑

		int k = field.length - 1;
		while (i < k) swap(i++, k--);
		// 5. i부터 끝가지를 뒤집어서 정렬(내림차순 -> 오름차순)

		return true;
		// 이전 순열 생성 완료
	}

	private void swap(int i, int j) {
		int temp = field[i];
		field[i] = field[j];
		field[j] = temp;
	}

	private String getPermutated() {
		StringBuilder result = new StringBuilder();
		for (int f : field)
			result.append(f).append(" ");
		return result.toString();
	}
}
```

### [n번째 수열 구하기](https://www.acmicpc.net/problem/9742)

시간 복잡도 : $O(N^2)$

> 1부터 N까지 숫자로 만들 수 있는 모든 순열을 사전 순으로 정렬했을 때, K 번째에
> 해당하는 순열을 구하는 방법

사전 순 순열은 팩토리얼을 이용한 자리수 계산으로 직접 구할 수 있다.

N = 4, K = 9 라고 한다면

- 전체 수열 수 : $4!$ = 24
- 첫 자리에 올 수 있는 숫자 : [1, 2, 3, 4]
- 각 숫자가 첫 자리에 올 때마다 남는 경우의 수 : $3!$ = 6
- K = 9는 두 번째 그룹에 해당 (첫 자리는 2)

```java
class Solution {

	public String solution(List<String> testCases) {
		StringBuilder result = new StringBuilder();
		for (String testCase : testCases) {
			result.append(testCase).append(" = ").append(calc(testCase)).append("\n");
		}
		return result.toString();
	}

	private String calc(String testCase) {
		String[] input = testCase.split(" ");
		char[] field = input[0].toCharArray();
		int n = Integer.parseInt(input[1]);
		String permutation = getPermutationOf(field, n);
		return permutation != null ? permutation : "No permutation";
	}

	// 사전순으로 정렬된 순열 중 n 번째 순열을 직접 계산
	private String getPermutationOf(char[] field, int n) {
		List<Character> chars = new ArrayList<>();
		for (char c : field) chars.add(c);
		// 문자들을 리스트로 변환

		int total = factorial(chars.size());
		// 전체 가능한 순열의 수 계산
		if (n > total) return null;
		// 범위 초과 시 null 반환

		n--;
		// 0-based index로 변환

		StringBuilder result = new StringBuilder();

		while (!chars.isEmpty()) {
			// 각 자리에 올 문자를 선택하며 순열 구성
			int size = chars.size();
			int f = factorial(size - 1);
			// 현재 자리 이후에 남은 문자들로 만들 수 있는 순열의 개수
			// 그룹 크기
			int index = n / f;
			// 선택할 문자 인덱스

			result.append(chars.get(index));
			// 문자 추가
			chars.remove(index);
			// 선택한 문자는 제거

			n %= f;
			// 다음 자리에서 사용할 인덱스 갱신
		}
		return result.toString();
	}

	private int factorial(int size) {
		int result = 1;
		for (int i = 1; i <= size; i++) {
			result *= i;
		}
		return result;
	}
}
```

### [주어진 순열이 몇 번째 순열인지 계산하기](https://www.acmicpc.net/problem/1722)

시간 복잡도 : $O(N^2)$

- 각 자릿수마다 앞에 올 수 있는 더 작은 수의 개수를 계산
- 그 수보다 작은 값이 앞에 올 수 있는 경우의 수는
  `(작은 개수) * (남은 자리  순열 수)`
- 모든 자리에 계산을 하고 이를 누적해서 더하면 순열의 순서가 구해짐

```java

class Solution {

	private int[] field;

	public int solution(int[] field) {
		init(field);
		return getPermutationIndex();
	}

	private void init(int[] field) {
		this.field = field;
	}

	private int getPermutationIndex() {
		List<Integer> nums = new ArrayList<>();

		for (int i = 1; i <= field.length; i++)
			nums.add(i);

		int index = 1; // 1-based index

		for (int i = 0; i < field.length; i++) {
			int cur = field[i];

			for (int num : nums) {
				// 현재 숫자보다 작은 수가 남은 리스트에 몇개 남았는지 확인
				if (num == cur) break;
				index += factorial(n - i - 1);
				// 그 숫자를 첫 자리에 둘 때 나오는 경우의 수 추가
			}
			nums.remove(i); // 사용한 숫자 제거
		}

		return index;
	}

	private int factorial(int num) {
		int result = 1;
		for (int i = 1; i <= num; i++) {
			result *= i;
		}
		return result;
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

### LIS

> 수열에서 항상 오름차순으로 증가하는 부분 수열 중에서 가장 긴 길이를 구하는 문제

시간복잡도 : $O(n^2)$ (개선가능)

- 부분 수열은 연속되지 않아도 됨
- 배열의 순서는 유지해야 함

예시) [10, 20, 10, 30, 20, 50]

가능한 증가 부분 수열 예:

- [10, 20, 30, 50]
- [10, 20, 50]
- [10, 30, 50]

이 중 가장 긴 건 [10, 20, 30, 50]이고, 길이 4

```java
class Solution {

	private int[] field;
	private int[] dp;

	public int solution(int[] field) {
		init(field);
		return calc();
	}

	private void init(int[] field) {
		this.field = field;
		this.dp = new int[field.length];
	}

	private int calc() {
		int result = 0; // 최장증가부분수열의 길이
		for (int i = 0; i < field.length; i++) {
			dp[i] = 1;
			// 자신만 끝에 새웠을 경우의 최장길이 1
			for (int j = 0; j < i; j++) {
				// i 보다 앞에 있는 모든 대상에 대해 탐색
				if (field[j] < field[i] && dp[i] < dp[j] + 1) {
					// i 보다 앞에 있는 j의 값이 i 보다 작고
					// j 뒤에 i를 세우는 것이 더 최장을 만족한다면
					dp[i] = dp[j] + 1;
				}
			}
			result = Math.max(result, dp[i]);
		}
		return result;
	}
}
```

### 개선된 LIS

시간복잡도 : $O(n \log n)$

길이는 구하되 실제 수열은 구하지 않는다는 아이디어

- `dp[i]` : 길이가 `i + 1`인 증가 부분 수열의 마지막 값 중 최소값
- dp 배열은 실제 LIS는 아니지만, 길이를 구하는 데에만 사용됨
- 주어진 수열 arr의 각 `arr[i]`에 대해 dp에서 이 값이 들어갈 수 있는
  위치를 이진탐색해서 삽입

예시) arr = [10, 20, 10, 30, 20, 50]

초기 상태 :

- dp = []
- length = 0;

1. 10
    - `dp = [10]`, length = 1
2. 20
    - `dp = [10, 20]`, length = 2
3. 10
    - dp = [10, 20] -> 10은 dp[0]과 교체됨(같거나 더 작으면
      덮어쓰기)
    - dp = [10, 20]
4. 30
    - dp = [10, 20, 30], length = 3
5. 20
    - dp = [10, 20, 30] -> 20은 dp[1]과 교체됨
    - dp = [10, 20, 30]
6. 50
    - dp = [10, 20, 30, 50], length = 4

dp 배열은 LIS를 그대로 유지하지는 않지만, 항상 가능한 가장 작은 값을 유지한다.

- 작은 값일수록 이후에 더 많은 값이 이어질 수 있기 때문

따라서 길이만 알고 싶은 경우에는 dp의 실제 내용은 신경쓰지 않아도 된다!

```java
class Solution {

	private int[] field;
	private int[] dp;

	public int solution(int[] field) {
		init(field);
		return calc();
	}

	private void init(int[] field) {
		this.field = field;
		this.dp = new int[field.length];
	}

	private int calc() {
		int result = 0;
		for (int i = 0; i < field.length; i++) {
			// dp[0 ~ result) 중에서 field[i]가 들어갈 위치 찾기
			int pos = Arrays.binarySearch(dp, 0, result, field[i]);
			// int[] arr, int fromIndex, int toIndex, int key
			// 배열 arr의 fromIndex 부터 toIndex 사이에서 key 찾기
			// 찾으려는 key 없으면 '-키가 삽입될 위치 - 1' 반환
			if (pos < 0)
				pos = -pos - 1; // 삽입 위치 복원
			dp[pos] = field[i];

			if (pos == result)
				result++; // 가장 끝에 붙는 경우만 길이 증가
		}
		return result;
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