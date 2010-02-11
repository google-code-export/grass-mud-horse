package jcj1988.manage;

import java.math.BigInteger;

import jcj1988.io.IOable;
import jcj1988.oprate.Operatable;
/**
 * gmh编译模式的管理类
 * @author jcj1988
 *
 */
public class CmpMng extends Manager {
	private IOable io=null;

	/**
	 * 构造函数
	 * @param io 输入输出对象的引用
	 */
	public CmpMng(IOable io) {
		this.io=io;
	}

	@Override
	public void manage(Operatable elem) {
		//i++;
		//io.println(i+":"+elem.getName());
		io.println(elem.getName());
	}

	@Override
	public void manage(Operatable elem, BigInteger b) {
		//i++;
		//io.println(i+":"+elem.getName()+" "+b);
		io.println(elem.getName()+" "+b);
	}

	@Override
	public void execute() {
		// do nothing
	}

}
