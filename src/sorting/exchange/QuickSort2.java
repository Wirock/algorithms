package sorting.exchange;
/**
 * 荷兰国旗问题
 * 快速排序优化,分治成三个部分，小于哨兵的区域，等于哨兵的区域，大于哨兵的区域。
 * 递归对小于区和大于区进行分治，就能得到排好序的数组。
 * 比普通快排优化的地方是避免了对相等部分的重复处理
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
	//分治，将数组分成 小于num，等于num，大于num三个部分，返回数组为三个区间的分解点（小于区的最后一个数和大于区的第一个数的坐标）
	private int[] partition(int[] array,int le,int r,int num){
		//循环后结束后为小于区的最后一个元素下标,最开始时在最左端前一位，循环结束后<le,表示在le和r之间没有小于区
		int left = le-1;
		//循环后结束后为大于区的第一个元素下标，最开始在最右端后一位，循环结束后>r,表示在le和r之间没有大于区
		int right = r+1;
		int cur = le;//当前指针，cur的每一步都保证cur之前的数<=num
		while(cur<right){
			if(array[cur]<num){
				left++;
				if(cur!=left)swap(array,cur,left);
				cur++;//left<=cur,由于cur之前的数<=num,所以换到cur处的数<=num,处理下一位
			}else if(array[cur]>num){
				right--;
				swap(array,cur,right);//交换后cur不变，因为换到cur处的数也可能大于num
			}else{
				cur++;//cur处的数=num,无需处理，跳到下一位
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
		int[] s=new int[]{31,43,353,5676,788,42,1,3,44535,656,120,56,99};
		QuickSort2 qs = new QuickSort2();
		qs.quickSort(s,0,s.length-1);
		for(int i:s){
			System.out.print(i+" ");
		}
	}

}
