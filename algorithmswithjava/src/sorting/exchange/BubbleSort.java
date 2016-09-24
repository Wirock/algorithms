package sorting.exchange;
/**
 * 冒泡排序
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
public class BubbleSort {
	public void sort(int[] source){
		int length = source.length;
		//进行length-1次循环
		for(int i=1;i<=length-1;i++){
			boolean flag = false;
			//每一轮循环从左往右对相邻的两数进行比较，把大的放右边，
			//一轮下来最大的数在最右边
			for(int j=0;j<=length-1-i;++j){
				if(source[j]>source[j+1]){
					int temp = source[j];
					source[j] = source[j+1];
					source[j+1] = temp;
					flag=true;
				}
			}
			//当一轮中没有位移，则已经排好序了，无需多余操作
			if(flag==false)
				break;
		}
	}
	
	public static void main(String[] args) {
		int[] source = {43,-67,93,-32,12,64,21,9,-78,94,3};
		BubbleSort bs = new BubbleSort();
		System.out.println("before:");
		for(int s:source){
			System.out.print(s+" ");
		}
		System.out.println();
		bs.sort(source);
		System.out.println("after:");
		for(int s:source){
			System.out.print(s+" ");
		}
	}
}
