# 알고리즘 문제 풀이 레포

## 코드 스니펫

### 순열

> 어떤 집합의 원소들을 특정한 순서대로 배열하는 것

순서가 중요하므로 (1, 2) != (2, 1)

$$_nP_r = \frac{n!}{(n-r)!}$$

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

### 조합

> 어떤 집합의 원소들을 순서 상관 없이 배열하는 것

순서가 상관 없으므로 (1, 2) == (2, 1)

$$_nC_r = \frac{_nP_r}{r!}$$

```java
class Solution {

	private int field;
	private int select;
	private int[] combinated;
	private boolean[] used;
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
		this.used = new boolean[field];
		this.result = new StringBuilder();
	}

	private void combinate(int cnt, int start) {
		if (cnt == select) {
			saveCombinated();
			return;
		}

		for (int i = start; i <= field; i++) {
			if (used[cnt]) continue;
			used[cnt] = true;
			combinated[cnt] = i;
			combinate(cnt + 1, i);
			used[cnt] = false;
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
		while (input[i - 1] >= input[j]) --j;

		// 3. i-1 자리와 j자리의 값 교환
		swap(i - 1, j);

		// 4. i - 1 자리의 한단계 큰수로 변화를 줬으니 i 꼭대기 위치부터 맨 뒤까지 가장 작은 수를 만듦(오름차순 정렬)
		int k = field.length - 1;
		while (i < k) swap(i++, k--);

		return true;
	}
}

public void swap(int i, int j) {
	int temp = field[i];
	field[i] = field[j];
	field[j] = temp;
}
```