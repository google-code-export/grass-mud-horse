package jcj1988.oprate;

import java.math.BigInteger;
/**
 * 所有操作类的接口，统一操作类族，以方便建立工厂和统一方式调用
 * @author jcj1988
 * */
public interface Operatable extends Executable{
	/**
	 * 需要参数的运行
	 * @param b 参数
	 */
	public void execute(BigInteger b);

	/**
	 * 返回操作是否需要参数
	 * @return 判断结果
	 */
	public boolean isNeedArg();
	
	/**
	 * 返回是否是Mark操作
	 * @return 判断结构
	 */
	public boolean isMarkOpr();
	
	/**
	 * 获得操作名称
	 * @return 操作名称
	 */
	public String getName();
}