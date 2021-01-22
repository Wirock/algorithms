package sorting.exchange;
/**
 * 快速排序,荷兰国旗问题
 * 
 * 时间复杂度（n为元素个数）：
 * 	平均：O(nlogn)
 * 	最好：O(nlogn)
 * 	最坏：O(n^2)
 * 
 * 空间复杂度：O(nlogn)
 * 
 * 稳定性：不稳定
 */
public class QuickSort2 {
	public void quickSort(int[] array,int left,int right){
		if(left<right){
			int[] border = partition(array, left, right, array[left]);
			quickSort(array,left,border[0]);
			quickSort(array,border[1],right);
		}
	}
	private int[] partition(int[] array,int l,int r,int num){
		int left = l-1;//小于区的最后一个元素下标
		int right = r+1;//大于区的第一个元素下标
		int cur = l;
		while(cur<right){
			if(array[cur]<num){
				left++;
				swap(array,cur,left);
				cur++;
			}else if(array[cur]>num){
				right--;
				swap(array,cur,right);
			}else{
				cur++;
			}
		}
		return new int[]{left,right};
	}


	private void swap(int[] array,int a,int b){
		int temp = array[a];
		array[a] = array[b];
		array[b] = temp;
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] s=new int[]{31,43,353,5676,788,42,1,3,44535,656,120,56,99};
		QuickSort2 qs = new QuickSort2();
		qs.quickSort(s,0,s.length-1);
		for(int i:s){
			System.out.print(i+" ");
		}
	}

}
