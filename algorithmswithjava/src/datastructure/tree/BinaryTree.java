package datastructure.tree;

public class BinaryTree {
	public final static int max = 40;
	//��α���ʱ��������ڵ�
	BinaryTree[] elements = new BinaryTree[40];
	int front;//��α�������
	int rear;//��α�����β
	private Object data;
	private BinaryTree left,right;
	
	//����Ҷ��
	public BinaryTree(Object data){
		this.data = data;
		left=null;
		right=null;
	}
	//������
	public BinaryTree(Object data,BinaryTree left,BinaryTree right){
		this.data = data;
		this.left = left;
		this.right = right;
	}
	//��дtoString
	public String toString(){
		return data.toString();
	}
	
	//�������
	public void preOrder(BinaryTree root){
		if(root==null){
			return;
		}else{
			System.out.print(root.data+" ");
			preOrder(root.left);
			preOrder(root.right);
		}
	}
	//�������
		public void inOrder(BinaryTree root){
			if(root==null){
				return;
			}else{
				inOrder(root.left);
				System.out.print(root.data+" ");
				inOrder(root.right);
			}
		}
	//�������
		public void postOrder(BinaryTree root){
			if(root==null){
				return;
			}else{
				postOrder(root.left);
				postOrder(root.right);
				System.out.print(root.data+" ");
			}
		}
	//��α���
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
		//����Ҷ�ӽڵ����Ŀ
		public int leaves(){
			if(left == null&&right == null){
				return 1;
			}else{
				return (left==null?0:left.leaves())+(right==null?0:right.leaves());
			}
		}
		//�����������
		public int height(){
			int heightOfTree;
			int leftHeight = (left==null?0:left.height());
			int rightHeight = (right==null?0:right.height());
			heightOfTree=(leftHeight<rightHeight?rightHeight:leftHeight);
			return 1+heightOfTree;
		}
		//��������������λ��
		public void exchangLeftRight(){
			if(left!=null||right!=null){
				BinaryTree temp = left;
				left = right;
				right = temp;
			}
		}
		//���ض��������еĲ�Σ������ڣ��򷵻�-1
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
		
		//�Ƴ��ڵ㣬ͬʱɾ����
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
			System.out.println("ǰ����������");
			tree.preOrder(tree);
			System.out.println();
			System.out.println("������������");
			tree.inOrder(tree);
			System.out.println();
			System.out.println("������������");
			tree.postOrder(tree);
			System.out.println();
			System.out.println("�������������");
			tree.layerOrder(tree);
			System.out.println();
			System.out.println("f���ڲ�Σ�"+tree.level("f"));
			System.out.println("��ζ������ĸ߶ȣ�"+tree.height());
		}
}
