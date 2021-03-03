package problem;
//O(N^2)
public class MaxSubsequenceSum {
	private static int seqStart = 0;
	private static int seqEnd = 0;
	public static int maxSubsequenceSum(int[] a){
		int maxSum = a[0];
		for(int i=0;i<a.length;i++){
			int thisSum = 0;
			for(int j=i;j<a.length;j++){
				thisSum += a[j];
				if(thisSum>maxSum){
					maxSum = thisSum;
					seqStart = i;
					seqEnd = j;
				}
			}
		}
		return maxSum;
	}
	//O(N)
	public static int AdvanceMaxSubsequenceSum(int[] a){
		int maxSum = a[0];
		int thisSum = 0;
		for(int i=0,j=0;j<a.length;j++){
			thisSum += a[j];
			if(thisSum>maxSum){
				maxSum = thisSum;
				seqStart = i;
				seqEnd = j;
			}//和最大的子序列不会以和为负的子序列开头
			else if(thisSum<0){
				i = j + 1;
				thisSum = 0;
			}
		}
		return maxSum;
	}
	
//分治法，O(NlogN)
	public static int DevidedMaxSubsequenceSum(int[] a,int left,int right){
		int maxLeftBorderSum = 0,maxRightBorderSum = 0;
		int leftBorderSum = 0,rightBorderSum = 0;
		int center = (left+right)/2;
		if(left==right){
			return a[left]>0?a[left]:0;
		}
		int maxLeftSum = DevidedMaxSubsequenceSum(a, left, center);
		int maxRightSum = DevidedMaxSubsequenceSum(a, center+1, right);
		for(int i =center;i>=left;i--){
			leftBorderSum  += a[i];
			if(leftBorderSum > maxLeftBorderSum){
				maxLeftBorderSum = leftBorderSum;
			}
		}
		for(int i=center;i<=right;i++){
			rightBorderSum+=a[i];
			if(rightBorderSum>maxRightBorderSum){
				maxRightBorderSum = rightBorderSum;
			}
		}
		return max3(maxLeftSum,maxRightSum,maxLeftBorderSum+maxRightBorderSum);
	}
	
	private static int max3(int x, int y, int z) {
		if(x>y){
			if(x>z){
				return x;
			}else{
				return z;
			}
		}else{
			if(y>z){
				return y;
			}else{
				return z;
			}
		}
	}
	public static void main(String[] args) {
//		int[] a = {-3,-4,5,-6,2,4,-3,4,1,-9,4,3,7,-3,5,-5,8};
		int[] a = {-3,-4,-5,-6,-2,-4,-3,-4,-1,-9,-4,-3,-7,-3,-5,-5,-8};
		//int maxSum=maxSubsequenceSum(a);
		int maxSum=AdvanceMaxSubsequenceSum(a);
//		int maxSum = DevidedMaxSubsequenceSum(a,0,a.length-1);
		System.out.println("和最大的子序列起始坐标为："+seqStart+",终止坐标为："+seqEnd+",和为："+maxSum);
	}

}
