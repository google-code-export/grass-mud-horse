package jcj1988.oprate;
/**
 * 调用操作类接口，对操作类再次封装的结果
 * @author jcj1988
 *
 */
public interface Callable{
	/**
	 * 调用操作
	 */
	public void call();
	/**
	 * 获得操作名称
	 * @return 操作的名称
	 */
	public String getName();
	/**
	 * 返回是否为Mark操作
	 * @return 判断是否为Mark操作的结果
	 */
	public boolean isMark();
}
