package jcj1988.oprate;

import java.math.BigInteger;

import jcj1988.io.IOable;

/**
 * gmh的输入输出类，与gmh中的操作一致，具体细节查看gmh说明。 减少数据结构对注意力的分散,更好的体现层次，使结构清晰
 * @author jcj1988
 * */
public class IO {
	/** 输入输出接口 */
	IOable io = null;
	Stack s = null;
	Heap h = null;

	/**
	 * gmhIO的构造函数
	 * */
	public IO(IOable io, Stack s, Heap h) {
		this.io = io;
		this.s = s;
		this.h = h;
	}

	/**
	 * 输出一个字符
	 */
	public void putChar() {
		//io.putChar(s.peek().intValue());
		io.putChar(s.pop().intValue());
	}

	/**
	 * 获得一个字符
	 */
	public void getChar() {
		//h.set(s.peek().intValue(), BigInteger.valueOf(io.getChar()));
		h.set(s.pop().intValue(), BigInteger.valueOf(io.getChar()));
	}
	
	/**
	 * 输出一个整数
	 */
	public void putInt() {
		//io.putInt(s.peek().intValue());
		io.putInt(s.pop().intValue());
	}

	/**
	 * 获得一个整数
	 */
	public void getInt() {
		//h.set(s.peek().intValue(), BigInteger.valueOf(io.getInt()));
		h.set(s.pop().intValue(), BigInteger.valueOf(io.getInt()));
	}
}
