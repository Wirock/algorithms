package sorting.distribution;

public class RadixSort {
//dΪ��������Ԫ�ص����λ��
	public static void radixSort(int[] array,int d){
		int n=1;//��ǰ�Աȵ���λ��1Ϊ��λ,10Ϊʮλ��...
		int k=0;//array��������
		int length = array.length;
		int[][] bucket = new int[10][length];//�������飬��Ԫ�طֵ���Ӧ������
		int[] order = new int[10];//��¼ÿһ�е���һ���������λ�����꣬������һ��������Ӧ�������1
		while(n<=d){
			//�����������
			for(int num:array){
				int digit = (num/n)%10;//��ȡ��ǰҪ�Աȵ�Ԫ��
				bucket[digit][order[digit]]=num;//����ָ��������
				order[digit]++;//����ָ����е���һ��
			}
			//�Ż�ԭ����
			for(int i=0;i<=9;i++){
				for(int j=0;j<order[i];j++){
					array[k]=bucket[i][j];
					k++;	
				}
			}
			//��ʼ����һ��ѭ�����ò���
			for(int i=0;i<10;i++){
				order[i]=0;
			}
			n*=10;
			k=0;
		}
	}
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] array = {191,18,237,46,5,4,3,72,191,0,3,25,66,66};
		radixSort(array,100);
		for(int i=0;i<array.length;i++){
			System.out.print(array[i]+" ");
		}
	}

}
