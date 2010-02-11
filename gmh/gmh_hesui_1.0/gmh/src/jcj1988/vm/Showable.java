package jcj1988.vm;

/**
 * 展示接口
 * 
 * @author jcj1988
 * 
 */
public interface Showable {
	/**
	 * 向用户展示对象内容，一般情况下比toString方法更进一步， 不仅仅是使得此字符输出，而且使用户更清晰明确的窥探对象的内部情况
	 */
	public void show();
}
