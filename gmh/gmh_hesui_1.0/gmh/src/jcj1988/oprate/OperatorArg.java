package jcj1988.oprate;

import java.math.BigInteger;

/**
 * 含参操作类（Operator which have Argument）
 * @author jcj1988
 * */
public class OperatorArg implements Callable{
	Operatable op = null;
	BigInteger arg = null;
	String name=null;

	/**
	 * 含参操作类的构造函数
	 * @param op 操作对象的引用
	 * @param arg 运行时所需的参数
	 */
	public OperatorArg(Operatable op, BigInteger arg) {
		this.op = op;
		this.arg = arg;
		this.name=op.getName()+" "+arg;
	}

	@Override
	public void call() {
		op.execute(arg);
	}

	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public boolean isMark() {
		return op.isMarkOpr();
	}
}