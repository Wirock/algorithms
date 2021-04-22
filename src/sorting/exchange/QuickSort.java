package sorting.exchange;
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
public class QuickSort {
	//l为要整理数组部分的首坐标，认为尾坐标
	public int adjustArrays(int[] s,int left,int right){
		int i = left,j=right;
		int x = s[left];//s[left]是第一个坑
		//一开始i是首坐标，j是尾坐标，只要待整理的数至少有2个，则进行划分
		while(i<j){
			//从右向左找小于x的数来填s[i]，j为当前要与x对比的数的坐标
			//找到从右向左第一个小于x的数的坐标j
			while(i<j&&s[j]>=x){
				j--;
			}
			//如果这个坐标在当前的坑s[i]的右边，则用它填坑
			if(i<j){
				s[i]=s[j];//将s[j]填到s[i]中，s[j]就形成一个新坑
				i++;//坑s[i]已经填好，从它的下一个开始找数填新坑s[j]
			}
		
			//从左到右找大于或等于x的数来填s[j]，找到第一个大于x的数的坐标i
			while(i<j&&s[i]<x){
				i++;
			}
			
			if(i<j){
				s[j]=s[i];//将s[i]填到s[j]中，s[i]就形成一个新坑
				j--;
			}
			
		}
		//当i和j交汇，即i=j时完成分组，此时s[i]左边的数全小于x，右边的数全大于x
		s[i]=x;//用x填上分界点坑
		return i;//返回分界点坐标
	}
	//快速排序法（分治法）
	//挖坑填数
	//选取s[l]作为基准，让它左边的所有数都小于它，右边的数都大于它
	//l为基准坐标，
	public void quickSort(int[] s,int l,int r){
		if(l<r){
			int i =adjustArrays(s,l,r);
			//分界点s[i]已经确定，对左右两边的数再分别进行分组
			//当无法再分时，adjustArrays将不再进行操作，故可以用递归的方法
			quickSort(s,l,i-1);
			quickSort(s,i+1,r);
		}
	}
	public static void main(String[] args) {
		int[] s=new int[]{31,43,353,5676,788,42,1,3,44535,656,120,56,99};
		QuickSort qs = new QuickSort();
		qs.quickSort(s,0,s.length-1);
		for(int i:s){
			System.out.print(i+" ");
		}
	}

}
