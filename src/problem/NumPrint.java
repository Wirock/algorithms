package problem;

import java.util.Scanner;
//Êı×Ö·½ĞÎĞıÎÑÅÅĞò
public class NumPrint {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Scanner in = new Scanner(System.in);
		if(in.hasNextInt())
			printNum(in.nextInt());
		in.close();
	}
	public static void printNum(int i){
		int num = 1;
		int[][] array = new int[i][i];
		int start = 0;
		int end = i-1;
		while(start<=end){
			num=sortSqure(num,start,end,array);
			start++;
			end--;
		}
		printArray(array);
	}
	public static int sortSqure(int num,int start,int end,int[][] array){
		if(start==end){
			array[start][start]=num;
			return -1;
		}else if(start<end){
			for(int i=start;i<end;i++){
				array[i][start]=num++;
			}
			for(int i=start;i<end;i++){
				array[end][i]=num++;
			}
			for(int i=end;i>start;i--){
				array[i][end]=num++;
			}
			for(int i=end;i>start;i--){
				array[start][i]=num++;
			}
			return num;
		}else{
			return -1;
		}
		
	}
	
	public static void printArray(int[][] array){
		for(int i=0;i<array.length;i++){
			for(int j=0;j<array[i].length;j++){
				if(array[i][j]<=9){
					System.out.print(0);
				}
				System.out.print(array[i][j]+" ");
			}
			System.out.println();
		}
	}
}
