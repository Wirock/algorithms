package search;
/**
 * 二分查询
 * @author Administrator
 *
 */
public class BinarySearch {
	/**
	 * 二分查找
	 * @param array 进行查找的有序数组
	 * @param from 查找起始坐标
	 * @param to 查找终止坐标
	 * @param key 查找的数
	 * @return key在array中的坐标
	 */
	public static int binarySearch(int[] array,int from,int to,int key){
		if(from<0||to<0){
			throw new IllegalArgumentException("参数开始坐标和数组长度必须大于0");
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
