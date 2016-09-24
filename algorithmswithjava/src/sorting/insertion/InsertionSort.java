package sorting.insertion;
/**
 * 直接插入排序
 * 
 * 时间复杂度（n为元素个数）：
 * 	平均：O(n^2)
 * 	最好：O(n)
 * 	最坏：O(n^2)
 * 
 * 空间复杂度：O(1)
 * 
 * 稳定性：稳定
 */
public class InsertionSort {
	public static void insertionSort(int[] array){
		for(int i=1;i<array.length;i++){
			int j = i;
			//先将前两个数组成一个顺序数组，后面的数再插入到适当位置
			while(j>=1&&array[j]<array[j-1]){
					int temp = array[j-1];
					array[j-1] = array[j];
					array[j] = temp;
					j--;
				
			}
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = {9,8,7,6,5,4,3,2,1,0,-1,-2,-3};
		insertionSort(array);
		for(int i=0;i<array.length;i++){
			System.out.print(array[i]+" ");
		}
	}

}
