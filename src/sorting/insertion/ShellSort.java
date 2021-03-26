package sorting.insertion;
/**
 * Shell排序
 * 
 * 时间复杂度（n为元素个数）：
 * 	平均：O(n^1.3)
 * 	最好：O(n)
 * 	最坏：O(n^2)
 * 
 * 空间复杂度：O(1)
 * 
 * 稳定性：不稳定
 */
public class ShellSort {
	public static void shellSort(int[] array){
		//间隔
		for(int gap = array.length/2;gap>0;gap=gap==2?1:(int)(gap/2.2)){
			//插入排序
			for(int i = gap;i<array.length;i++){
				for(int j=i;j>=gap;j-=gap){
					if(array[j]<array[j-gap]){
						int temp = array[j];
						array[j]=array[j-gap];
						array[j-gap]=temp;
					}
				}
			}
		}
	}
	public static void main(String[] args) {
		int[] array = {9,8,7,6,5,4,3,2,1,0,-1,-2,-3};
		shellSort(array);
		for(int i=0;i<array.length;i++){
			System.out.print(array[i]+" ");
		}
	}

}
