package problem;
/*
 * ʵ��һ����������һ���ַ����еĿո��滻�ɡ�%20����
 * ���磬���ַ���ΪWe Are Happy.�򾭹��滻֮����ַ���ΪWe%20Are%20Happy��
 */
public class BlankReplace {
    public String replaceSpace(StringBuffer str) {
    	
    	return str.toString().replace(" ", "%20");
    }
	public static void main(String[] args) {
		BlankReplace br = new BlankReplace();
		System.out.println(br.replaceSpace(new StringBuffer("We Are Happy")));

	}

}
