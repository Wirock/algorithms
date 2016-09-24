package search;

public class HashSearch {  
	  
    public static void main(String[] args) {  
        //������ȡ�෨��  
        int hashLength = 13;  
  
        int [] array  = { 13, 29, 27, 28, 26, 30, 38 };  
  
        //��ϣ����  
        int[] hash = new int[hashLength];  
        //����hash  
        for (int i = 0; i < array.length; i++)  
        {  
            insertHash(hash, hashLength, array[i]);  
        }  
          
        int result = searchHash(hash,hashLength, 29);  
  
        if (result != -1)  
            System.out.println("�Ѿ����������ҵ�������λ��Ϊ��" + result);  
        else  
            System.out.println("û�д�ԭʼ");  
    }  
  
    /**** 
     * Hash��������� 
     *  
     * @param hash 
     * @param hashLength 
     * @param key 
     * @return 
     */  
    public static int searchHash(int[] hash, int hashLength, int key) {  
        // ��ϣ����  
        int hashAddress = key % hashLength;  
  
        // ָ��hashAdrress��Ӧֵ���ڵ����ǹؼ�ֵ�����ÿ���Ѱַ�����  
        while (hash[hashAddress] != 0 && hash[hashAddress] != key) {  
            hashAddress = (++hashAddress) % hashLength;  
        }  
  
        // ���ҵ��˿��ŵ�Ԫ����ʾ����ʧ��  
        if (hash[hashAddress] == 0)  
            return -1;  
        return hashAddress;  
  
    }  
  
    /*** 
     * ���ݲ���Hash�� 
     *  
     * @param hash ��ϣ�� 
     * @param hashLength 
     * @param data 
     */  
    public static void insertHash(int[] hash, int hashLength, int data) {  
        // ��ϣ����  
        int hashAddress = data % hashLength;  
  
        // ���key���ڣ���˵���Ѿ�������ռ�ã���ʱ��������ͻ  
        while (hash[hashAddress] != 0) {  
            // �ÿ���Ѱַ���ҵ�  
            hashAddress = (++hashAddress) % hashLength;  
        }  
  
        // ��data�����ֵ���  
        hash[hashAddress] = data;  
    }  
}  