package datastructure.graph;

public class Graph {
	private boolean[] visited;
	private int[][] graph;
	private final int n = 5;
	
	public Graph(){
		this.initGraph();
	}

	public void initGraph() {
		visited = new boolean[n];
		graph = new int[n][n];
		int i = 0;
		int j = 0;
		for(i=0;i<n;i++){
			visited[i] = false;
			for(j=0;j<n;j++){
				graph[i][j] = 0;
			}
		}
		
		//把可能通过过的设为1
		graph[0][1] = 1;
		graph[1][0] = 1;
		
		graph[0][2] = 1;
		graph[2][0] = 1;
		
		graph[0][4] = 1;
		graph[4][0] = 1;
		
		graph[1][2] = 1;
		graph[2][1] = 1;
		
		graph[1][3] = 1;
		graph[3][1] = 1;
		
		graph[2][4] = 1;
		graph[4][2] = 1;
	}
	
	public void showPath(int x){
		for(int i=0;i<n;i++){
			//x点到i点，如果可以通过而且未遍历过
			if(graph[x][i]==1&&visited[i]==false){
				visited[i] = true;//设置为到达
				System.out.print(i);//打印路径
				showPath(i);//递归遍历点
			}
		}
	}
	public static void main(String[] args) {
		int begin = 0;
		Graph graph = new Graph();
		graph.showPath(begin);
	}
}
