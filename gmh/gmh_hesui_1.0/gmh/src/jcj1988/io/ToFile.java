package jcj1988.io;

import java.io.FileNotFoundException;
import java.io.PrintStream;

/**
 * 输入输出类，输出流重定向到文件
 * @author jcj1988
 *
 */
public class ToFile extends IO {
	/**
	 * 构造函数
	 * @param file 输出文件的文件名，如果不存在，此文件会被新建
	 * @throws FileNotFoundException 尽管显式地抛出异常，但是此情况不会发生
	 */
	public ToFile(String file) throws FileNotFoundException{
		super(new PrintStream(file));
	}
}
