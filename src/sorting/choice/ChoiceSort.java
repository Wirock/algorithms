
package sorting.choice;
/**
 * ֱ��ѡ������
 * 
 * ʱ�临�Ӷȣ�nΪԪ�ظ�������
 * 	ƽ����O(n^2)
 * 	��ã�O(n^2)
 * 	���O(n^2)
 * 
 * �ռ临�Ӷȣ�O(1)
 * 
 * �ȶ��ԣ����ȶ�
 */
public class ChoiceSort {

	public void choiceSort(int[] source){
		if(source==null||source.length<=0){
			return;
		}
		//ѡ����һ��ֵ��Ϊ��Сֵ����������֮���ֵ���жԱȣ����ҵ���һ������С��ֵ��
		//������µ�ֵ���Ϊ��С����������֮��������бȽϡ�
		//һ��ѭ�������ҳ�һ����Сֵ
		for(int i=0;i<source.length;i++){
			int min = i;
			for(int j=i+1;j<source.length;j++){
				if(source[min]>source[j]){
					min = j;
				}
			}
			if(i!=min){
				int temp = source[i];
				source[i]=source[min];
				source[min]=temp;
			}
		}
	}
	
	
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] source = {43,-67,93,-32,12,64,21,9,-78,94,3};
		ChoiceSort cs = new ChoiceSort();
		System.out.println("before:");
		for(int s:source){
			System.out.print(s+" ");
		}
		System.out.println();
		cs.choiceSort(source);
		System.out.println("after:");
		for(int s:source){
			System.out.print(s+" ");
		}
	}

}
