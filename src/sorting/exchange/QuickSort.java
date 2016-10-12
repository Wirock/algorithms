package sorting.exchange;
/**
 * ��������
 * 
 * ʱ�临�Ӷȣ�nΪԪ�ظ�������
 * 	ƽ����O(nlogn)
 * 	��ã�O(nlogn)
 * 	���O(n^2)
 * 
 * �ռ临�Ӷȣ�O(nlogn)
 * 
 * �ȶ��ԣ����ȶ�
 */
public class QuickSort {
	//lΪҪ�������鲿�ֵ������꣬��Ϊβ����
	public int adjustArrays(int[] s,int l,int r){
		int i = l,j=r;
		int x = s[l];//s[l]=s[i]�ǵ�һ����
		//һ��ʼi�������꣬j��β���ֻ꣬Ҫ���������������2��������л���
		while(i<j){
			//����������С��x��������s[i]��jΪ��ǰҪ��x�Աȵ���������
			//�ҵ����������һ��С��x����������j
			while(i<j&&s[j]>=x){
				j--;
			}
			//�����������ڵ�ǰ�Ŀ�s[i]���ұߣ����������
			if(i<j){
				s[i]=s[j];//��s[j]�s[i]�У�s[j]���γ�һ���¿�
				i++;//��s[i]�Ѿ���ã���������һ����ʼ�������¿�s[j]
			}
		
			//�������Ҵ��ڻ����x��������s[j]���ҵ���һ������x����������i
			while(i<j&&s[i]<x){
				i++;
			}
			
			if(i<j){
				s[j]=s[i];//��s[i]�s[j]�У�s[i]���γ�һ���¿�
				j--;
			}
			
		}
		//��i��j���㣬��i=jʱ��ɷ��飬��ʱs[i]��ߵ���ȫС��x���ұߵ���ȫ����x
		s[i]=x;//��x���Ϸֽ���
		return i;//���طֽ������
	}
	//�������򷨣����η���
	//�ڿ�����
	//ѡȡs[l]��Ϊ��׼��������ߵ���������С�������ұߵ�����������
	//lΪ��׼���꣬
	public void quickSort(int[] s,int l,int r){
		if(l<r){
			int i =adjustArrays(s,l,r);
			//�ֽ��s[i]�Ѿ�ȷ�������������ߵ����ٷֱ���з���
			//���޷��ٷ�ʱ��adjustArrays�����ٽ��в������ʿ����õݹ�ķ���
			quickSort(s,l,i-1);
			quickSort(s,i+1,r);
		}
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		int[] s=new int[]{31,43,353,5676,788,42,1,3,44535,656,120,56,99};
		QuickSort qs = new QuickSort();
		qs.quickSort(s,0,s.length-1);
		for(int i:s){
			System.out.print(i+" ");
		}
	}

}
