package sorting.merge;
/**
 * �鲢����
 * 
 * ʱ�临�Ӷȣ�nΪԪ�ظ�������
 * 	ƽ����O(nlogn)
 * 	��ã�O(nlogn)
 * 	���O(nlogn)
 * 
 * �ռ临�Ӷȣ�O(n)
 * 
 * �ȶ��ԣ��ȶ�
 */
public class MergeSort {
	
	public static void mergeSort(int[] array){
		if(array.length<=1){
			return;
		}
	
		int[] left = new int[array.length/2];
		int[] right = new int[(array.length+1)/2];
		for(int i=0;i<array.length/2;i++){
			left[i] = array[i];
			right[i] = array[i+array.length/2];
		}
		right[(array.length+1)/2-1] = array[array.length-1];
		
		mergeSort(left);
		mergeSort(right);
		merge(left,right,array);
	}
	
	public static void merge(int[] left,int[] right,int[] array){
		int i = 0;
		int j = 0;
		int k =0;
		while(k<array.length){
			if(i<left.length&&j<right.length){
				if(left[i]<=right[j]){
					array[k] = left[i];
					i++;
					k++;
				}else{
					array[k] = right[j];
					j++;
					k++;
				}    
			}else{
				if(i<left.length){
					array[k]=left[i];
					i++;
					k++;
				}else if(j<right.length){
					array[k]=right[j];
					j++;
					k++;
				}
			}
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = {9,8,7,6,5,4,3,2,1,0,-1,-2,-3};
		mergeSort(array);
		for(int i=0;i<array.length;i++){
			System.out.print(array[i]+" ");
		}
	}

}
