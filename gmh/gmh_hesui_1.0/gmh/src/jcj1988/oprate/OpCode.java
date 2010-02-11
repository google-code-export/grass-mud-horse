package jcj1988.oprate;

import java.math.BigInteger;

/**
 * 操作类工厂类Opraters中操作对象的根类
 * @author jcj1988
 * **/
public class OpCode implements Operatable {
	private boolean needArg;
	private String name;

	/**
	 * 操作类工厂类Opraters中操作对象根类对象的构造函数
	 * @param name 操作名
	 * @param needArg 操作是否需要参数
	 */
	public OpCode(String name,boolean needArg) {
		this.needArg = needArg;
		this.name=name;
	}

	@Override
	public boolean isNeedArg() {
		return needArg;
	}

	@Override
	public boolean isMarkOpr() {
		return false;
	}

	@Override
	public void execute() {
	}

	@Override
	public void execute(BigInteger b) {
	}

	/**
	 * 获得操作名
	 */
	public String getName() {
		return name;
	}
}
