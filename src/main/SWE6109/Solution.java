package main.SWE6109;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.StringTokenizer;

public class Solution {

	static int arr[][], num, dup[][];
	static boolean check[][];
	static void mv(int dir) {
		if (dir == 0) {
			for (int j = 0; j < num; j++) {
				int cnt = 0;
				for (int i = 0; i < num; i++) {
					if (arr[i][j] == 0)
						continue;
					else {
						dup[cnt][j] = arr[i][j];
						if (cnt > 0 && dup[cnt - 1][j] == dup[cnt][j] && !check[cnt-1][j]) {
							dup[cnt - 1][j] *= 2;
							dup[cnt][j] = 0;
							check[cnt-1][j]=true;
						} else
							cnt++;
					}
				}
			}
		} else if (dir == 1) {
			for(int i=0;i<num;i++) {
				int cnt = num-1;
				for(int j=num-1;j>=0;j--) {
					if(arr[i][j]==0) continue;
					else {
						dup[i][cnt]=arr[i][j];
						if(cnt!=num-1 && dup[i][cnt+1]==dup[i][cnt] && !check[i][cnt+1]) {
							dup[i][cnt+1]*=2;
							dup[i][cnt]=0;
							check[i][cnt+1]=true;
						}
						else
							cnt--;
					}
				}
			}
		} else if (dir == 2) {
			for (int j = 0; j < num; j++) {
				int cnt = num-1;
				for (int i = num-1; i >=0; i--) {
					if (arr[i][j] == 0)		continue;
					else {
						dup[cnt][j] = arr[i][j];
						if (cnt !=num-1 && dup[cnt + 1][j] == dup[cnt][j] && !check[cnt+1][j]) {
							dup[cnt + 1][j] *= 2;
							dup[cnt][j] = 0;
							check[cnt+1][j]=true;
						} else
							cnt--;
					}
				}
			}
		} else if (dir == 3) {
			for(int i=0;i<num;i++) {
				int cnt = 0;
				for(int j=0;j<num;j++) {
					if(arr[i][j]==0) continue;
					else {
						dup[i][cnt]=arr[i][j];
						if(cnt!=0 && dup[i][cnt-1]==dup[i][cnt] && !check[i][cnt-1]) {
							dup[i][cnt-1]*=2;
							dup[i][cnt]=0;
							check[i][cnt-1]=true;
						}
						else
							cnt++;
					}
				}
			}
		}
	}

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		int test = Integer.parseInt(br.readLine().trim());
		for (int t = 1; t <= test; t++) {
			String s = br.readLine();
			String str;
			int order = 0;
			StringTokenizer st = new StringTokenizer(s);
			num = Integer.parseInt(st.nextToken());
			str = st.nextToken();
			if (str.equals("up"))
				order = 0;
			else if (str.equals("right"))
				order = 1;
			else if (str.equals("down"))
				order = 2;
			else if (str.equals("left"))
				order = 3;
			arr = new int[num][num];
			dup = new int[num][num];
			check = new boolean[num][num];
			for(int i=0;i<num;i++) {
				s = br.readLine();
				st = new StringTokenizer(s);
				for(int j=0;j<num;j++)
					arr[i][j] = Integer.parseInt(st.nextToken());
			}
			mv(order);
			System.out.println("#"+t);
			for(int i=0;i<num;i++) {
				for(int j=0;j<num;j++)
					System.out.print(dup[i][j]+" ");
				System.out.println();
			}
		}
	}
}
