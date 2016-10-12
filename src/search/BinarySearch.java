package search;
/**
 * ���ֲ�ѯ
 * @author Administrator
 *
 */
public class BinarySearch {
	/**
	 * ���ֲ���
	 * @param array���в��ҵ���������
	 * @param from������ʼ����
	 * @param to������ֹ����
	 * @param key���ҵ���
	 * @return key��array�е�����
	 */
	public static int binarySearch(int[] array,int from,int to,int key){
		if(from<0||to<0){
			throw new IllegalArgumentException("������ʼ��������鳤�ȱ������0");
		}
		if(from<=to){
			int middle = (from+to)/2;
			int temp = array[middle];
			if(temp>key){
				to = middle-1;
			}else if(temp<key){
				from = middle + 1;
			}else{
				return middle;
			}
		}
		return binarySearch(array,from,to,key);
	}

	
	public static void main(String[] args) {
		int[] array = {0,1,2,3,4,5,6,7,8,9};
		int i = binarySearch(array,0,array.length-1,5);
		System.out.println(i);
	}

}
