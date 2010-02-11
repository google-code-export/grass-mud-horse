package jcj1988.analyze;

import java.math.BigInteger;
import java.util.Hashtable;
import java.util.Scanner;

import jcj1988.cfg.VMarg;
import jcj1988.io.IOable;

import jcj1988.vm.CodeSection;
//import jcj1988.vm.TriNode;
//import jcj1988.manage.Manager;
//import jcj1988.oprate.Operatable;
//import java.io.Reader;

/**
 * 伪汇编转换为gmh语，此类仅仅是将伪汇编与gmh之间进行机械的映射，不对其进行任何语法检查，此类运行机制较为粗糙。
 * 但是这样机械的转化也可能是提高效率的一种方法（也可能当前的算法还不是……）。
 * 
 * @author jcj1988
 * 
 */
public class OpAnalyzeImpl implements Analyzable {
	private Hashtable<String, String> ht;

	private IOable io;
	Scanner sc;
	private VMarg vmarg = null;
	private int S;
	private int T;
	private int L;

	/**
	 * 构造函数
	 * 
	 * @param cs
	 *            虚拟机代码容器对象
	 * @param ht
	 *            命令、伪汇编哈希映射表对象
	 */
	public OpAnalyzeImpl(CodeSection cs, Hashtable<String, String> ht) {
		this.ht = ht;

		vmarg = cs.getVmarg();
		S = vmarg.getS();
		T = vmarg.getT();
		L = vmarg.getL();
		io = cs.getIo();
		sc = new Scanner(cs.getReader());
		// Analyze();
	}

	@Override
	public void analyse() {
		String s;
		StringBuffer n;
		String o;
		try {
			while (sc.hasNext()) {
				s = sc.next();
				o = ht.get(s);
				if (o != null)
					io.print(o);
				else {
					try {
						BigInteger b = new BigInteger(s);
						if (b.signum() == -1) {
							io.print(String.valueOf((char) T));
							b = b.negate();
						} else
							io.print(String.valueOf((char) S));
						n = new StringBuffer();
						for (int i = b.bitLength() - 1; i >= 0; i--) {
							if (b.testBit(i)) {
								n = n.append((char) T);
								b.clearBit(i);
							} else
								n = n.append((char) S);
						}
						io.print(n.append((char) L).toString());
					} catch (NumberFormatException e) {
						io.print(s);
					}
				}
			}
		} catch (Exception e) {
			// e.printStackTrace();
			io.error(e);
		}
	}
}
