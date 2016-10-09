package problem;
//��һ����ά�����У�ÿһ�ж����մ����ҵ�����˳������
//ÿһ�ж����մ��ϵ��µ�����˳�����������һ��������
//����������һ����ά�����һ���������ж��������Ƿ��и�����
public class TargetSearch {
	
	public boolean find(int [][] array,int target) {
        for(int row=0;row<array.length;row++){
        	if(array[row].length>0){
	            if(target>=array[row][0]&&target<=array[row][array[row].length-1]){
	            	if(searchTarget(array[row],0,array[row].length-1,target)!=-1){
	            		return true;
	            	}
	            }
        	}
        }
		return false;
    }
    
    public int searchTarget(int[] array,int from,int to,int target){
        if(from <= to){
            int middle  = (from+to)/2;
            if(target==array[middle]){
                return middle;
            }else if(target<array[middle]){
                to = middle-1;
            }else{
             	from = middle+1;   
            }
            return searchTarget(array,from,to,target);
        }
        return -1;
    }
	public static void main(String[] args) {
		int[][] array = {{1,2,3,4,5},{6,7,8},{9,10,11,12},{13,14,15,16,17,18},{19,20}};
		TargetSearch tg = new TargetSearch();
		System.out.println(tg.find(array,11));
	}

}
