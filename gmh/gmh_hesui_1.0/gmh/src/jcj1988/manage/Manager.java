package jcj1988.manage;

import java.math.BigInteger;
import java.util.ArrayList;

import jcj1988.oprate.Callable;
import jcj1988.oprate.Executable;
import jcj1988.oprate.Operatable;

/**
 * 所有控制类的根类
 * 
 * @author jcj1988
 * 
 */
public abstract class Manager implements Executable {
	/**
	 * 非含参的操作对象的控制过程
	 * 
	 * @param elem
	 *            操作对象的引用
	 */
	public abstract void manage(Operatable elem);

	/**
	 * 含参的操作对象的控制过程
	 * 
	 * @param elem
	 *            操作对象的引用
	 * @param b
	 *            操作运行所需的参数
	 */
	public abstract void manage(Operatable elem, BigInteger b);

	protected int i;

	@Override
	public void execute() {
		// do nothing
	}

	/**
	 * 获得i的值（i一般为运行位置指针）
	 * @return i的值
	 */
	public int getI() {
		// do nothing
		return i;
	}

	/**
	 * 设置i的值（i一般为运行位置指针）
	 * @param i 要设置的值
	 */
	public void setI(int i) {
		// do nothing
		this.i = i;
	}

	/**
	 * 获得操作对象表（操作对象组成的线性表）
	 * @return 操作对象表的引用
	 */
	public ArrayList<Callable> getOpr() {
		// do nothing
		return null;
	}
}
