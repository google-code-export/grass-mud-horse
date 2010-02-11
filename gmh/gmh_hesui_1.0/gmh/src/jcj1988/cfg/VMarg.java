package jcj1988.cfg;

/**
 * 虚拟机参数类
 * 
 * @author jcj1988
 * 
 */
public class VMarg {
	private int heapMax;
	private int S;
	private int T;
	private int L;

	/**
	 * 构造函数
	 * 
	 * @param h
	 *            堆的最大容量，最大值和默认值都为65536
	 * @param s
	 *            命令字符S的Unicode编码（如当次字符为‘草’是，其值为33609）
	 * @param t
	 *            命令字符T的Unicode编码（如当次字符为‘泥’是，其值为27877）
	 * @param l
	 *            命令字符L的Unicode编码（如当次字符为‘马’是，其值为39532）
	 */
	public VMarg(int h, int s, int t, int l) {
		if (h > 0 && h < 65536)
			heapMax = h;
		else
			heapMax = 65536;
		S = s;
		T = t;
		L = l;
	}

/**
 * 获得堆的最大容量
 * @return 堆得最大容量
 */
	public int getHeapMax() {
		return heapMax;
	}

	/**
	 * 获得命令字符S的Unicode编码（如当次字符为‘草’是，其值为33609）
	 * @return 字符S的Unicode编码（如当次字符为‘草’是，其值为33609）
	 */
	public int getS() {
		return S;
	}

	/**
	 * 获得命令字符T的Unicode编码（如当次字符为‘泥’是，其值为27877）
	 * @return 字符T的Unicode编码（如当次字符为‘泥’是，其值为27877）
	 */
	public int getT() {
		return T;
	}

	/**
	 * 获得命令字符L的Unicode编码（如当次字符为‘马’是，其值为39532）
	 * @return 字符L的Unicode编码（如当次字符为‘马’是，其值为39532）
	 */
	public int getL() {
		return L;
	}
}
