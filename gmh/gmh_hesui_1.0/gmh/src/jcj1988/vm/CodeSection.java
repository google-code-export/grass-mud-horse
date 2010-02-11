package jcj1988.vm;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;

import jcj1988.cfg.VMarg;
import jcj1988.io.IOable;
import jcj1988.manage.Manager;

/**
 * 虚拟机代码容器，负责保存整个gmh代码以及运行过程中的一些参数。
 * 由于属于参数传输容器类，可能日后改动较大，只要遵循可改不可删，只增不减的原则可以保证改动有最少的牵连
 * @author jcj1988
 * */
public class CodeSection {
	private String filename;
	private BufferedReader reader;
	private VMarg vmarg;
	private IOable io;
	private Manager mng;

	// Integer[] lineNums;
	// Integer[] colNums;
	
	/**
	 * 构造函数
	 * @param file 输入文件名
	 * @param io 标准输入输出接口
	 * @param vmarg 虚拟机参数对象引用
	 * @param mng 管理对象引用
	 */
	public CodeSection(String file, IOable io, VMarg vmarg, Manager mng) {
		this.io = io;
		this.vmarg = vmarg;
		this.mng = mng;
		try {
			reader = new BufferedReader(new FileReader(file));
		} catch (FileNotFoundException e) {
			// e.printStackTrace();
			io.error(e);
		}
	}

	/**
	 * 获得输入文件（gmh源码文件）名
	 * @return 输入文件的文件名
	 */
	public String getFilename() {
		return filename;
	}

	/**
	 * 获得虚拟机参数对象
	 * @return 虚拟机参数对象的引用
	 */
	public VMarg getVmarg() {
		return vmarg;
	}

	/**
	 * 获得输入文件输入流对象
	 * @return 输入流对象的引用
	 */
	public BufferedReader getReader() {
		return reader;
	}

	/**
	 * 获得标准输入输出对象引用
	 * @return 标准输入输出对象的引用
	 */
	public IOable getIo() {
		return io;
	}

	/**
	 * 获取管理对象引用
	 */
	public Manager getMng() {
		return mng;
	}
}
