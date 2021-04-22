package search;

public class OrderSearch {
	//查找num中数字在array中第一次出现的坐标，没有则返回-1
	public static int orderSearch(int[] array,int des){
		for(int i=0;i<array.length;i++){
			if(des==array[i]){
				return i;
			}
		}
		return -1;
	}
	public static void main(String[] args) {
		int[] array = {191,18,237,46,5,4,3,72,191,0,3,25,66,66};
		int i = orderSearch(array,3);
		System.out.println(i);
	}

}
