
package sorting.choice;
/**
 * 直接选择排序
 * 
 * 时间复杂度（n为元素个数）：
 * 	平均：O(n^2)
 * 	最好：O(n^2)
 * 	最坏：O(n^2)
 * 
 * 空间复杂度：O(1)
 * 
 * 稳定性：不稳定
 */
public class ChoiceSort {

	public void choiceSort(int[] source){
		if(source==null||source.length<=0){
			return;
		}
		//选定第一个值作为最小值，依次与它之后的值进行对比，当找到第一个比它小的值，
		//将这个新的值标记为最小，继续与它之后的数进行比较。
		//一轮循环可以找出一个最小值
		for(int i=0;i<source.length;i++){
			int min = i;
			for(int j=i+1;j<source.length;j++){
				if(source[min]>source[j]){
					min = j;
				}
			}
			if(i!=min){
				int temp = source[i];
				source[i]=source[min];
				source[min]=temp;
			}
		}
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] source = {43,-67,93,-32,12,64,21,9,-78,94,3};
		ChoiceSort cs = new ChoiceSort();
		System.out.println("before:");
		for(int s:source){
			System.out.print(s+" ");
		}
		System.out.println();
		cs.choiceSort(source);
		System.out.println("after:");
		for(int s:source){
			System.out.print(s+" ");
		}
	}

}
