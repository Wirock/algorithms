package datastructure.tree;

public class BinarySortTree<T extends Comparable<T>> {
    public final static int max = 40;
    //层次遍历时保存各个节点
    BinarySortTree[] elements = new BinarySortTree[max];
    int front;//层次遍历队首
    int rear;//层次遍历队尾
    public T data;
    public BinarySortTree parent, left, right;

    //构造叶子
    public BinarySortTree(T data) {
        this.data = data;
        left = null;
        right = null;
    }

    //构造树
    public BinarySortTree(T data, BinarySortTree left, BinarySortTree right) {
        this.data = data;
        this.left = left;
        this.right = right;
    }

    //重写toString
    public String toString() {
        return data.toString();
    }

    //先序遍历
    public void preOrder(BinarySortTree root) {
        if (root == null) {
            return;
        } else {
            System.out.print(root.data + " ");
            preOrder(root.left);
            preOrder(root.right);
        }
    }

    //中序遍历
    public void inOrder(BinarySortTree root) {
        if (root == null) {
            return;
        } else {
            inOrder(root.left);
            System.out.print(root.data + " ");
            inOrder(root.right);
        }
    }

    //后序遍历
    public void postOrder(BinarySortTree root) {
        if (root == null) {
            return;
        } else {
            postOrder(root.left);
            postOrder(root.right);
            System.out.print(root.data + " ");
        }
    }

    //层次遍历
    public void layerOrder(BinarySortTree root) {
        elements[0] = root;
        front = 0;
        rear = 1;

        while (front < rear) {
            if (elements[front] != null) {
                System.out.print(elements[front].data + " ");
                if (elements[front].left != null) {
                    elements[rear++] = elements[front].left;
                }
                if (elements[front].right != null) {
                    elements[rear++] = elements[front].right;
                }
            }
            front++;
        }
    }

    //返回叶子节点的数目
    public int leaves() {
        if (left == null && right == null) {
            return 1;
        } else {
            return (left == null ? 0 : left.leaves()) + (right == null ? 0 : right.leaves());
        }
    }

    //返回树的深度
    public int height() {
        int heightOfTree;
        int leftHeight = (left == null ? 0 : left.height());
        int rightHeight = (right == null ? 0 : right.height());
        heightOfTree = (leftHeight < rightHeight ? rightHeight : leftHeight);
        return 1 + heightOfTree;
    }

    //调换左右子树的位置
    public void exchangLeftRight() {
        if (left != null || right != null) {
            BinarySortTree temp = left;
            left = right;
            right = temp;
        }
    }

    //返回数据在树中的层次，若不在，则返回-1
    public int level(T object) {
        if (object == data) {
            return 1;
        }
        int leftLevel = (left == null) ? -1 : left.level(object);
        int rightLevel = (right == null) ? -1 : right.level(object);
        if (leftLevel < 0 && rightLevel < 0) {
            return -1;
        }
        return 1 + (leftLevel < rightLevel ? rightLevel : leftLevel);
    }

    //移除节点，同时删除树
    public void defoliate() {
        if (left == null && right == null) {
            data = null;
            return;
        }

        data = null;

        if (left != null) {
            left.defoliate();
            left = null;
        }


        if (right != null) {
            right.defoliate();
            right = null;
        }
    }

    //返回节点在树中的层次，若不在，则返回-1
    public int level(BinarySortTree node) {
        if (this == node) {
            return 1;
        }
        int leftLevel = (left == null) ? -1 : left.level(node);
        int rightLevel = (right == null) ? -1 : right.level(node);
        if (leftLevel < 0 && rightLevel < 0) {
            return -1;
        }
        return 1 + (leftLevel < rightLevel ? rightLevel : leftLevel);
    }

    //打印树
    public void printTree() {
        int height = height();
        int x = height;
        int y = new Double(Math.pow(2, height - 1)).intValue();
        Object[][] screen = new Object[x][y];
        fillScreen(screen, 0, 0, y - 1, this);
        for (int i = 0; i < screen.length; i++) {
            for (int j = 0; j < screen[i].length; j++) {
                System.out.print(screen[i][j] == null ? "  " : screen[i][j]);
            }
            System.out.println();
        }
    }

    private void fillScreen(Object[][] screen, int row, int columnBegin, int columnEnd, BinarySortTree node) {
        int mid = (columnBegin + columnEnd) / 2;
        screen[row][mid] = node.data;
        if (node.left != null) {
            fillScreen(screen, row + 1, columnBegin, mid, node.left);
        }
        if (node.right != null) {
            fillScreen(screen, row + 1, mid, columnEnd, node.right);
        }
    }

    //以下为BST的方法
    //查找一个节点
    public BinarySortTree search(T key) {
        if (key == null) {
            return null;
        }
        if (key.compareTo(this.data) < 0) {
            if (this.left == null) {
                return null;
            }
            return this.left.search(key);
        } else if (key.compareTo(this.data) > 0) {
            if (this.right == null) {
                return null;
            }
            return this.right.search(key);
        } else {
            return this;
        }
    }

    //插入一个节点
    public boolean insert(BinarySortTree node) {
        if (node == null || node.data == null) {
            return false;
        }
        if (node.data.compareTo(this.data) > 0) {
            if (right == null) {
                right = node;
                node.parent = this;
            } else {
                right.insert(node);
            }
            return true;
        } else if (node.data.compareTo(this.data) < 0) {
            if (left == null) {
                left = node;
                node.parent = this;
            } else {
                left.insert(node);
            }
            return true;
        } else {
            return false;//已存在，插入失败
        }
    }

    //删除一个节点,保留其子节点
    public boolean delete(T key) {
        if (key == null) {
            return false;
        }
        if (key.compareTo(this.data) > 0) {
            if (this.right == null) {
                return false;
            }
            return this.right.delete(key);
        } else if (key.compareTo(this.data) < 0) {
            if (this.left == null) {
                return false;
            }
            return this.left.delete(key);
        } else {
            //如果没有子节点直接删除
            if (this.left == null && this.right == null) {
                if (this.parent == null) {
                    this.data = null;
                } else if (this.parent.left == this) {
                    parent.left = null;
                } else if (this.parent.right == this) {
                    parent.right = null;
                }
            } else if (this.left == null) {//只有右子树
                BinarySortTree<T> node = this.right;
                this.data = node.data;
                this.left = node.left;
                this.right = node.right;
            } else if (this.right == null) {//只有左子树
                BinarySortTree<T> node = this.left;
                this.data = node.data;
                this.left = node.left;
                this.right = node.right;
            } else {//既有左子树，又有右子树
                //找到前继节点（后继节点也可）
                BinarySortTree<T> node = this.left;
                while (node.right != null) {
                    node = node.right;
                }
                this.data = node.data;
                node.delete(node.data);
            }

        }
        return true;
    }

    public static void main(String[] args) {
        BinarySortTree tree = new BinarySortTree(5);
        tree.insert(new BinarySortTree(1));
        tree.insert(new BinarySortTree(2));
        tree.insert(new BinarySortTree(3));
        tree.insert(new BinarySortTree(4));
        tree.insert(new BinarySortTree(6));
        tree.insert(new BinarySortTree(7));
        tree.insert(new BinarySortTree(8));
        tree.insert(new BinarySortTree(9));
        tree.insert(new BinarySortTree(10));
        tree.search(8).printTree();
        tree.delete(3);
        tree.delete(5);
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
        System.out.println("f所在层次：" + tree.level(7));
        System.out.println("这课二叉树的高度：" + tree.height());
        System.out.println("打印树：");
        tree.printTree();
    }
}
