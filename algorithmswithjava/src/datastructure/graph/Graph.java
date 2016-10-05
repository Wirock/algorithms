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
		
		//�ѿ���ͨ��������Ϊ1
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
			//x�㵽i�㣬�������ͨ������δ������
			if(graph[x][i]==1&&visited[i]==false){
				visited[i] = true;//����Ϊ����
				System.out.print(i);//��ӡ·��
				showPath(i);//�ݹ������
			}
		}
	}
	public static void main(String[] args) {
		int begin = 0;
		Graph graph = new Graph();
		graph.showPath(begin);
	}
}
