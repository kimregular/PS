# 알고리즘 문제 풀이 레포

## 코드 스니펫

### 순열

어떤 집합의 원소들을 특정한 순서대로 배열하는 것

$$ _{n}P_{r} = \frac{n!}{(n-r)!} $$

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