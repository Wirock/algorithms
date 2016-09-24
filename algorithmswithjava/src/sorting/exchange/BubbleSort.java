package sorting.exchange;
/**
 * ð������
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
public class BubbleSort {
	public void sort(int[] source){
		int length = source.length;
		//����length-1��ѭ��
		for(int i=1;i<=length-1;i++){
			boolean flag = false;
			//ÿһ��ѭ���������Ҷ����ڵ��������бȽϣ��Ѵ�ķ��ұߣ�
			//һ�����������������ұ�
			for(int j=0;j<=length-1-i;++j){
				if(source[j]>source[j+1]){
					int temp = source[j];
					source[j] = source[j+1];
					source[j+1] = temp;
					flag=true;
				}
			}
			//��һ����û��λ�ƣ����Ѿ��ź����ˣ�����������
			if(flag==false)
				break;
		}
	}
	
	public static void main(String[] args) {
		int[] source = {43,-67,93,-32,12,64,21,9,-78,94,3};
		BubbleSort bs = new BubbleSort();
		System.out.println("before:");
		for(int s:source){
			System.out.print(s+" ");
		}
		System.out.println();
		bs.sort(source);
		System.out.println("after:");
		for(int s:source){
			System.out.print(s+" ");
		}
	}
}
