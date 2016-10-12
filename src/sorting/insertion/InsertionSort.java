package sorting.insertion;
/**
 * ֱ�Ӳ�������
 * 
 * ʱ�临�Ӷȣ�nΪԪ�ظ�������
 * 	ƽ����O(n^2)
 * 	��ã�O(n)
 * 	���O(n^2)
 * 
 * �ռ临�Ӷȣ�O(1)
 * 
 * �ȶ��ԣ��ȶ�
 */
public class InsertionSort {
	public static void insertionSort(int[] array){
		for(int i=1;i<array.length;i++){
			int j = i;
			//�Ƚ�ǰ���������һ��˳�����飬��������ٲ��뵽�ʵ�λ��
			while(j>=1&&array[j]<array[j-1]){
					int temp = array[j-1];
					array[j-1] = array[j];
					array[j] = temp;
					j--;
				
			}
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = {9,8,7,6,5,4,3,2,1,0,-1,-2,-3};
		insertionSort(array);
		for(int i=0;i<array.length;i++){
			System.out.print(array[i]+" ");
		}
	}

}
