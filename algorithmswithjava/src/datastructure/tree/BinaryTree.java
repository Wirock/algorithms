package datastructure.tree;

public class BinaryTree {
	public final static int max = 40;
	//层次遍历时保存各个节点
	BinaryTree[] elements = new BinaryTree[40];
	int front;//层次遍历队首
	int rear;//层次遍历队尾
	private Object data;
	private BinaryTree left,right;
	
	//构造叶子
	public BinaryTree(Object data){
		this.data = data;
		left=null;
		right=null;
	}
	//构造树
	public BinaryTree(Object data,BinaryTree left,BinaryTree right){
		this.data = data;
		this.left = left;
		this.right = right;
	}
	//重写toString
	public String toString(){
		return data.toString();
	}
	
	//先序遍历
	public void preOrder(BinaryTree root){
		if(root==null){
			return;
		}else{
			System.out.print(root.data+" ");
			preOrder(root.left);
			preOrder(root.right);
		}
	}
	//中序遍历
		public void inOrder(BinaryTree root){
			if(root==null){
				return;
			}else{
				inOrder(root.left);
				System.out.print(root.data+" ");
				inOrder(root.right);
			}
		}
	//后序遍历
		public void postOrder(BinaryTree root){
			if(root==null){
				return;
			}else{
				postOrder(root.left);
				postOrder(root.right);
				System.out.print(root.data+" ");
			}
		}
	//层次遍历
		public void layerOrder(BinaryTree root){
			elements[0] = root;
			front = 0;
			rear = 1;
			
			while(front<rear){
				if(elements[front]!=null){
					System.out.print(elements[front].data+" ");
					if(elements[front].left!=null){
						elements[rear++] = elements[front].left;
					}
					if(elements[front].right!=null){
						elements[rear++] = elements[front].right;
					}
				}
				front++;
			}
		}
		//返回叶子节点的数目
		public int leaves(){
			if(left == null&&right == null){
				return 1;
			}else{
				return (left==null?0:left.leaves())+(right==null?0:right.leaves());
			}
		}
		//返回树的深度
		public int height(){
			int heightOfTree;
			int leftHeight = (left==null?0:left.height());
			int rightHeight = (right==null?0:right.height());
			heightOfTree=(leftHeight<rightHeight?rightHeight:leftHeight);
			return 1+heightOfTree;
		}
		//调换左右子树的位置
		public void exchangLeftRight(){
			if(left!=null||right!=null){
				BinaryTree temp = left;
				left = right;
				right = temp;
			}
		}
		//返回对象在树中的层次，若不在，则返回-1
		public int level(Object object){
			if(object==data){
				return 1;
			}
			int leftLevel = (left==null)?-1:left.level(object);
			int rightLevel = (right==null)?-1:right.level(object);
			if(leftLevel<0&&rightLevel<0){
				return -1;
			}	
			return 1+(leftLevel<rightLevel?rightLevel:leftLevel);
		}
		
		//移除节点，同时删除树
		public void defoliate(){
			if(left==null&&right==null){
				data=null;
				return;
			}
			
			data = null;
			
			if(left!=null){
				left.defoliate();
				left = null;
			}

			
			if(right!=null){
				right.defoliate();
				right=null;
			}
		}
		public static void main(String[] args) {
			BinaryTree e = new BinaryTree("e");
			BinaryTree g = new BinaryTree("g");
			BinaryTree h = new BinaryTree("h");
			BinaryTree i = new BinaryTree("i");
			BinaryTree d = new BinaryTree("d",null,g);
			BinaryTree f = new BinaryTree("f",h,i);
			BinaryTree b = new BinaryTree("b",d,e);
			BinaryTree c = new BinaryTree("c",f,null);
			BinaryTree tree = new BinaryTree("a",b,c);
			System.out.println("前序遍历结果：");
			tree.preOrder(tree);
			System.out.println();
			System.out.println("中序遍历结果：");
			tree.inOrder(tree);
			System.out.println();
			System.out.println("后序遍历结果：");
			tree.postOrder(tree);
			System.out.println();
			System.out.println("层次序遍历结果：");
			tree.layerOrder(tree);
			System.out.println();
			System.out.println("f所在层次："+tree.level("f"));
			System.out.println("这课二叉树的高度："+tree.height());
		}
}
