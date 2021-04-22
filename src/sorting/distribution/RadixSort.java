package sorting.distribution;

/**
 * 基数排序
 */
public class RadixSort {
//d为输入数组元素的最大位数
	public static void radixSort(int[] array,int d){
		int n=1;//当前对比的数位，1为个位,10为十位，...
		int k=0;//array数组坐标
		int length = array.length;
		int[][] bucket = new int[10][length];//分组数组，将元素分到对应的行里
		int[] order = new int[10];//记录每一行的下一个数放入的位置坐标，当加入一个数，对应的坐标加1
		while(n<=d){
			//放入分组数组
			for(int num:array){
				int digit = (num/n)%10;//获取当前要对比的元素
				bucket[digit][order[digit]]=num;//放入指定的行中
				order[digit]++;//坐标指向改行的下一个
			}
			//放回原数组
			for(int i=0;i<=9;i++){
				for(int j=0;j<order[i];j++){
					array[k]=bucket[i][j];
					k++;	
				}
			}
			//初始化下一次循环所用参数
			for(int i=0;i<10;i++){
				order[i]=0;
			}
			n*=10;
			k=0;
		}
	}
	
	public static void main(String[] args) {
		int[] array = {191,18,237,46,5,4,3,72,191,0,3,25,66,66};
		radixSort(array,100);
		for(int i=0;i<array.length;i++){
			System.out.print(array[i]+" ");
		}
	}

}
