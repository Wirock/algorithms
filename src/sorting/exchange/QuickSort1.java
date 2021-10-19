package sorting.exchange;

import java.util.Random;

/**
 * 快速排序
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
public class QuickSort1 {
	//分治
	public int partition(int[] s,int left,int right){
		//随机选取一个哨兵
		swap(s,left,new Random().nextInt(right-left+1)+left);
		int pivot = s[left];
		int k = left;
		for(int i=left+1;i<=right;i++){
			if(s[i]<pivot){
				swap(s,i,++k);
			}
		}

		swap(s,left,k);
		return k;
	}

	private void swap(int[] s,int i,int j){
		int temp = s[i];
		s[i] = s[j];
		s[j] = temp;
	}



	//快速排序法（分治法）
	//挖坑填数
	//选取s[l]作为基准，让它左边的所有数都小于它，右边的数都大于它
	//l为基准坐标，
	public void quickSort(int[] s,int l,int r){
		if(l<r){
			int i =partition(s,l,r);
			quickSort(s,l,i-1);
			quickSort(s,i+1,r);
		}
	}
	public static void main(String[] args) {
		int[] s=new int[]{31,43,353,5676,788,42,1,3,44535,656,120,56,99};
		QuickSort1 qs = new QuickSort1();
		qs.quickSort(s,0,s.length-1);
		for(int i:s){
			System.out.print(i+" ");
		}
	}

}
