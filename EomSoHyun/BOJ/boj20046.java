import java.io.*;
import java.util.*;

public class Main {
	
	static int m, n;
	static int[][] road, cost;

	public static void main(String[] args) throws IOException {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());
		
		m = Integer.parseInt(st.nextToken());
		n = Integer.parseInt(st.nextToken());
		road = new int[m][n];
		cost = new int[m][n];
		for (int i = 0; i < m; i++) {
			Arrays.fill(cost[i], Integer.MAX_VALUE);
		}
		
		for (int i = 0; i < m; i++) {
			st = new StringTokenizer(br.readLine());
			for (int j = 0; j < n; j++) {
				road[i][j] = Integer.parseInt(st.nextToken());
			}
		}
		
		if (road[0][0] == -1) {
			System.out.println(-1);
		}
		else {
			cost[0][0] = road[0][0];
			bfs();
			System.out.println(cost[m-1][n-1] == Integer.MAX_VALUE ? -1 : cost[m-1][n-1]);			
		}
		
		
	}
	
	public static void bfs() {
		PriorityQueue<int[]> pq = new PriorityQueue<int[]>(Comparator.comparingInt(o->o[2]));
		int[] dx = {0, 1, 0, -1};
		int[] dy = {1, 0, -1, 0};
		pq.offer(new int[] {0, 0, cost[0][0]});
		
		while (!pq.isEmpty()) {
			int[] xy = pq.poll();
			
			if (cost[xy[0]][xy[1]] < xy[2]) continue;
			
			if (xy[0] == m-1 && xy[1] == n-1) return;
			
			for (int i = 0; i < 4; i++) {
				int nx = xy[0] + dx[i];
				int ny = xy[1] + dy[i];
				
				if (0 <= nx && nx < m && 0 <= ny && ny < n) {
					if (road[nx][ny] != -1) {
						if (xy[2] + road[nx][ny] < cost[nx][ny]) {
							cost[nx][ny] = xy[2] + road[nx][ny];
							pq.offer(new int[] {nx, ny, cost[nx][ny]});
						}
					}
				}
			}
		}
		
	}

}


