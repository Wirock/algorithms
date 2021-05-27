package sorting.distribution;

/**
 * 基数排序
 *设桶数为m,排序数组长度为n,数组元素的最大十进制位数为k,时间复杂度为o(k(m+n))
 * 稳定
 */
public class RadixSort {
//d为输入数组元素的最大位数
	public static void radixSort(int[] array,int d){
		int n=1;//当前对比的数位，1为个位,10为十位，...
		int k=0;//array数组坐标
		int length = array.length;
		int[][] bucket = new int[10][length];//每个位的数字为0-9,分为10个桶
		int[] size = new int[10];//记录每个桶的元素个数
		while(n<=d){
			//放入分组数组
			for(int num:array){
				int digit = (num/n)%10;//获取当前要对比的位（个位，十位……）
				bucket[digit][size[digit]]=num;
				size[digit]++;
			}
			//放回原数组
			for(int i=0;i<10;i++){
				for(int j=0;j<size[i];j++){
					array[k]=bucket[i][j];
					k++;	
				}
			}
			//初始化下一次循环所用参数
			for(int i=0;i<10;i++){
				size[i]=0;
			}
			n*=10;
			k=0;
		}
	}
	public static void main(String[] args) {
		int[] array = {191,18,237,46,5,4,3,72,191,0,3,25,66,66};
		radixSort(array,3);
		for(int i=0;i<array.length;i++){
			System.out.print(array[i]+" ");
		}
	}

}
