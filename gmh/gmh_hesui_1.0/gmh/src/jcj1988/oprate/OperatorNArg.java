package jcj1988.oprate;

/**
 * 无参数运算类（operator which Need Not Argument）
 * @author jcj1988
 * */
public class OperatorNArg implements Callable {
	Operatable op = null;
	String name=null;
	/**
	 * 无参数运算类的构造函数
	 * @param op 操作对象的引用
	 */
	public OperatorNArg(Operatable op) {
		this.op = op;
		this.name=op.getName();
	}

	@Override
	public void call() {
		op.execute();
	}
	
	@Override
	public String getName() {
		return this.name;
	}

	@Override
	public boolean isMark() {
		return false;
	}
}
