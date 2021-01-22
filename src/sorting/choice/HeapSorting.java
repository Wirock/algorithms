package sorting.choice;
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
			ArrayUtils.exchangeElements(array, i, 0);
			maxHeap(array,i,0);
		}
	}
	
	private static void buildMaxHeap(int[] array){
		if(array==null||array.length<=0){
			return;
		}
		int half = array.length/2;
		for(int i=half;i>=0;i--){
			maxHeap(array,array.length,i);
		}
	}
	
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
			ArrayUtils.exchangeElements(array, index,largest);
			//交换array[largest]和array[index]中的数字，
			maxHeap(array, heapSize,largest);
		}

	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = {9,8,-7,6,-5,4,3,2,1,0,-1,-2,-3};
		System.out.println("Before heap:");
		ArrayUtils.printArray(array);
		heapSort(array);
		System.out.println("After heap:");
		ArrayUtils.printArray(array);
	}
}

class ArrayUtils{
	//打印数组
	public static void printArray(int[] array){
		System.out.print("{");
		for(int i=0;i<array.length;i++){
			System.out.print(array[i]);
			if(i<array.length-1){
				System.out.print(",");
			}
		}
		System.out.println("}");
	}
	//交换两个坐标对应的数
	public static void exchangeElements(int[] array,int index1,int index2){
		int temp = array[index1];
		array[index1] =  array[index2];
		array[index2] = temp;
	}
}

