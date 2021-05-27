package sorting.choice;

import java.util.Arrays;

/**
 * 堆排序
 * 
 * 时间复杂度（n为元素个数）：
 * 	平均：O(nlogn)
 * 	最好：O(nlogn)
 * 	最坏：O(nlogn)
 * 
 * 空间复杂度：O(1)
 * 
 * 稳定性：不稳定
 */
public class HeapSorting {	
	public static void heapSort(int[] array){
		if(array==null||array.length<=0){
			return;
		}
		buildMaxHeap(array);
		//每次把最大数放到最后，对其他的节点继续进行堆排序
		for(int i=array.length-1;i>=1;i--){
			exchangeElements(array, i, 0);
			maxHeap(array,i,0);
		}
	}

	//构建最大堆，堆中每个节点的值大于它的所有子孙节点
	private static void buildMaxHeap(int[] array){
		if(array==null||array.length<=0){
			return;
		}
		int half = array.length/2;//最后一个叶子节点的父节点
		for(int i=half;i>=0;i--){//从下往上，保证被操作的节点的左右子树都是最大堆
			maxHeap(array,array.length,i);
		}
	}

	//堆顶节点被替换后，重塑最大堆。前提是堆顶的左右子树都是最大堆。重新选出最大节点，让堆顶节点下沉到属于它的层级。
	private static void maxHeap(int[] array,int heapSize,int index){
		int left = index*2+1;
		int right =index*2+2;
		int largest = index;
		if(left<heapSize&&array[left]>array[largest]){
			largest = left;
		}
		if(right<heapSize&&array[right]>array[largest]){
			largest = right;
		}
		if(index!=largest){
			exchangeElements(array, index,largest);
			//交换array[largest]和array[index]中的数字，
			maxHeap(array, heapSize,largest);
		}

	}

	private static void exchangeElements(int[] array,int index1,int index2){
		int temp = array[index1];
		array[index1] =  array[index2];
		array[index2] = temp;
	}

	public static void main(String[] args) {
		int[] array = {9,8,-7,6,-5,4,3,2,1,0,-1,-2,-3};
		System.out.println("Before heap:");
		System.out.println(Arrays.toString(array));
		heapSort(array);
		System.out.println("After heap:");
		System.out.println(Arrays.toString(array));
	}
}

