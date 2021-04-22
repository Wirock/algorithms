package sorting.distribution;

/**
 * 计数排序
 */
public class CountSort {
//输入数组元素只能是0到k之间的整数
	public static void countSort(int[] array,int k){
		
		int[] temp = new int[array.length];
		for(int i=0;i<array.length;i++){
			temp[i] = array[i];
		}
		//统计小于或等于index的数的个数
		//leCount[i]为array中小于等于i的整数的个数
		int[] leCount = new int[k+1];
		//统计等于的数的个数
		for(int i=0;i<array.length;i++){
			leCount[temp[i]]++;
		}
		//统计小于等于的数的个数
		for(int i=1;i<=k;i++){
				leCount[i]+=leCount[i-1];
		}
		
		for(int i=temp.length-1;i>=0;i--){
			//注意leCount[temp[i]]-1
			array[leCount[temp[i]]-1] = temp[i];
			leCount[temp[i]]--;

		}
		
	}
	public static void main(String[] args) {
		int[] array = {9,8,7,6,5,4,3,2,1,0,3,5,6,6};
		System.out.println("before:");
		for(int i=0;i<array.length;i++){
			System.out.print(array[i]+" ");
		}
		System.out.println();
		countSort(array,9);
		System.out.println("after:");
		for(int i=0;i<array.length;i++){
			System.out.print(array[i]+" ");
		}
	}

}
