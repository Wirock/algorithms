package problem;
/*
 * 实现一个函数，将一个字符串中的空格替换成“%20”。
 * 例如，当字符串为We Are Happy.则经过替换之后的字符串为We%20Are%20Happy。
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
