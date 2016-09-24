package sorting.choice;
/**
 * ������
 * 
 * ʱ�临�Ӷȣ�nΪԪ�ظ�������
 * 	ƽ����O(nlogn)
 * 	��ã�O(nlogn)
 * 	���O(nlogn)
 * 
 * �ռ临�Ӷȣ�O(1)
 * 
 * �ȶ��ԣ����ȶ�
 */
public class HeapSorting {	
	public static void heapSort(int[] array){
		if(array==null||array.length<=0){
			return;
		}
		buildMaxHeap(array);
		//ÿ�ΰ�������ŵ���󣬶������Ľڵ�������ж�����
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
			//����array[largest]��array[index]�е����֣�
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
	//��ӡ����
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
	//�������������Ӧ����
	public static void exchangeElements(int[] array,int index1,int index2){
		int temp = array[index1];
		array[index1] =  array[index2];
		array[index2] = temp;
	}
}

