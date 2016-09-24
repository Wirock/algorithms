package sorting.distribution;

public class CountSort {
//��������Ԫ��ֻ����0��k֮�������
	public static void countSort(int[] array,int k){
		
		int[] temp = new int[array.length];
		for(int i=0;i<array.length;i++){
			temp[i] = array[i];
		}
		//ͳ��С�ڻ����index�����ĸ���
		//leCount[i]Ϊarray��С�ڵ���i�������ĸ���
		int[] leCount = new int[k+1];
		//ͳ�Ƶ��ڵ����ĸ���
		for(int i=0;i<array.length;i++){
			leCount[temp[i]]++;
		}
		//ͳ��С�ڵ��ڵ����ĸ���
		for(int i=1;i<=k;i++){
				leCount[i]+=leCount[i-1];
		}
		
		for(int i=temp.length-1;i>=0;i--){
			//ע��leCount[temp[i]]-1
			array[leCount[temp[i]]-1] = temp[i];
			leCount[temp[i]]--;

		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = {9,8,7,6,5,4,3,2,1,0,3,5,6,6};
		System.out.println("before:");
		for(int i=0;i<array.length;i++){
			System.out.print(array[i]+" ");
		}
		System.out.println();
		countSort(array,9);
		System.out.println("after:");
		for(int i=0;i<array.length;i++){
			System.out.print(array[i]+" ");
		}
	}

}
