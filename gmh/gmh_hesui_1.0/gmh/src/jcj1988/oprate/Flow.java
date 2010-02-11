package jcj1988.oprate;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Hashtable;

import jcj1988.manage.Manager;

/**
 * gmh中的流控制，与gmh中的操作一致，具体细节查看gmh说明。 
 * @author jcj1988
 * */
public class Flow {
	Manager mng = null;
	private ArrayList<Callable> opr = null;
	java.util.Stack<Integer> callStack = new java.util.Stack<Integer>();
	Stack opStack = null;
	private Hashtable<BigInteger, Integer> labels = new Hashtable<BigInteger, Integer>();

	/**
	 * 构造函数，负责Flow对象的初始化
	 * @param mng 虚拟机控制类
	 * @param s 虚拟机的栈对象
	 */
	public Flow(Manager mng, Stack s) {
		this.mng = mng;
		this.opr=this.mng.getOpr();
		this.opStack=s;
	}

	/**
	 * 标记操作
	 * @param b 标识符
	 */
	public void mark(BigInteger b) {
		labels.put(b, mng.getI());
	}

	/**
	 * 调用函数操作
	 * @param b 被调用处的标识符
	 */
	public void call(BigInteger b) {
		callStack.push(mng.getI());
		mng.setI(labels.get(b));
	}

	/**
	 * 无条件跳转到标识符处执行
	 * @param b 跳转处标识符
	 */
	public void jmp(BigInteger b) {
		mng.setI(labels.get(b));
	}

	/**
	 * 栈顶元素为0时跳转到所指示的标识符处
	 * @param b 所指定的标识符
	 */
	public void jz(BigInteger b) {
		if (opStack.pop().intValue() == 0) {
			mng.setI(labels.get(b));
		}
	}

	/**
	 * 栈顶元素小于0时跳转到所指示的标识符处
	 * @param b 所指定的标识符
	 */
	public void jneg(BigInteger b) {
		if (opStack.pop().signum() == -1) {
			mng.setI(labels.get(b));
		}
	}

	/**
	 * 函数结束返回调用处
	 */
	public void ret() {
		mng.setI(callStack.pop());
	}

	/**
	 * 无条件结束程序运行
	 */
	public void end() {
		mng.setI(opr.size());
	}
}
