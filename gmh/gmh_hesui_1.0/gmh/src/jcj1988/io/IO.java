package jcj1988.io;

import java.io.PrintStream;

/**
 * 输入输出类StdIO和ToFile的根类，提高代码重用性
 * @author jcj1988
 *
 */
public class IO implements IOable {
	IOimpl io = null;

	/**
	 * 构造函数
	 * @param out 输出流对象的引用
	 */
	public IO(PrintStream out){
		io = new IOimpl(out, System.in);
	}

	@Override
	public void close() {
		io.close();
	}

	@Override
	public boolean equals(Object obj) {
		return io.equals(obj);
	}

	@Override
	public void error(Exception e) {
		io.error(e);
	}

	@Override
	public int getChar() {
		return io.getChar();
	}

	@Override
	public int getInt() {
		return io.getInt();
	}

	@Override
	public String getString() {
		return io.getString();
	}

	@Override
	public int hashCode() {
		return io.hashCode();
	}

	@Override
	public void print(String s) {
		io.print(s);
	}

	@Override
	public void println(String s) {
		io.println(s);
	}

	@Override
	public void putChar(int i) {
		io.putChar(i);
	}

	@Override
	public void putInt(int i) {
		io.putInt(i);
	}

	@Override
	public String toString() {
		return io.toString();
	}

	@Override
	public void flush() {
		io.flush();
	}
}
