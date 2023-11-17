import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.*;

public class Main {

	static int n, removeNode, cnt;
	static List<List<Integer>> tree;
	static boolean[] nodes;

	public static void main(String[] args) throws Exception {
		BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
		StringTokenizer st = new StringTokenizer(br.readLine());

		n = Integer.parseInt(st.nextToken());
		tree = new ArrayList<>();

		for (int i = 0; i < n; i++) {
			tree.add(new ArrayList<>());
		}

		st = new StringTokenizer(br.readLine());
		for (int i = 0; i < n; i++) {
			int node = Integer.parseInt(st.nextToken());
			if (node == -1)
				continue;
			tree.get(node).add(i);
		}

		removeNode = Integer.parseInt(br.readLine());
		nodes = new boolean[n]; // true: 제거 노드
		remove(); // 노드 제거
		leaf();

		System.out.println(cnt);

	}

	public static void remove() {
		Queue<Integer> q = new LinkedList<>();
		q.offer(removeNode);
		nodes[removeNode] = true;
		while (!q.isEmpty()) {
			int v = q.poll();
			for (int w : tree.get(v)) {
				if (nodes[w])
					continue;
				q.offer(w);
				nodes[w] = true;
			}
		}
	}

	public static void leaf() {
		for (int i = 0; i < n; i++) {
			if (!nodes[i]) {
				if (tree.get(i).isEmpty()) {
					cnt++;
				} 
				else {
					// 자식 노드가 다 제거 됨
					boolean flag = true;
					for (int node : tree.get(i)) {
						if (!nodes[node]) {
							flag = false;
							break;
						}
					}
					if (flag)
						cnt++;
				}
			}

		}
	}

}
