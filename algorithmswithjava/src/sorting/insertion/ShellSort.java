package sorting.insertion;
/**
 * Shell����
 * 
 * ʱ�临�Ӷȣ�nΪԪ�ظ�������
 * 	ƽ����O(n^1.3)
 * 	��ã�O(n)
 * 	���O(n^2)
 * 
 * �ռ临�Ӷȣ�O(1)
 * 
 * �ȶ��ԣ����ȶ�
 */
public class ShellSort {
	public static void shellSort(int[] array){
		//���
		for(int gap = array.length/2;gap>0;gap=gap==2?1:(int)(gap/2.2)){
			//��������
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
		// TODO Auto-generated method stub
		int[] array = {9,8,7,6,5,4,3,2,1,0,-1,-2,-3};
		shellSort(array);
		for(int i=0;i<array.length;i++){
			System.out.print(array[i]+" ");
		}
	}

}
