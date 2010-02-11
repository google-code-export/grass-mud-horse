package jcj1988.factory;

import java.math.BigInteger;

import jcj1988.io.IOable;
import jcj1988.oprate.Arith;
import jcj1988.oprate.Flow;
import jcj1988.oprate.Heap;
import jcj1988.oprate.IO;
import jcj1988.oprate.Operatable;
import jcj1988.oprate.Stack;
import jcj1988.oprate.OpCode;
import jcj1988.vm.CodeSection;

/**
 * 虚拟机操作类工厂的一种实现方式，是通过类族和静态对象的方式实现的
 * 
 * @author jcj1988
 * */
public class Operators implements Factoryable {
	IOable stdio = null;
	Stack s = null;
	Arith a = null;
	Heap h = null;
	Flow f = null;
	IO io = null;

	@Override
	public Operatable Factory(String s) {
		Operatable r = null;
		try {
			r = (Operatable) Operators.class.getDeclaredField(s).get(this);
		} catch (Exception e) {
			e.printStackTrace();
			// stdio.error(e);
		}
		return r;
	}
	/**
	 * 丢弃栈顶元素
	 */
	public final OpCode S_POP = new OpCode("S_POP", false) {
		@Override
		public void execute() {
			s.pop();
		}
	};
	/**
	 * 压栈
	 */
	public final OpCode S_PUSH = new OpCode("S_PUSH", true) {
		@Override
		public void execute(BigInteger b) {
			s.push(b);
		}
	};
	/**
	 * 复制栈顶元素压栈
	 */
	public final OpCode S_DUP = new OpCode("S_DUP", false) {
		@Override
		public void execute() {
			s.dup();
		}
	};
	/**
	 * 复制第n个元素到栈顶
	 */
	public final OpCode S_COPY = new OpCode("S_COPY", true) {
		@Override
		public void execute(BigInteger b) {
			s.copy(b.intValue());
		}
	};
	/**
	 * 交换栈顶两个元素
	 */
	public final OpCode S_SWAP = new OpCode("S_SWAP", false) {
		@Override
		public void execute() {
			s.swap();
		}
	};
	/**
	 * 销毁栈顶的n个元素，但保持栈顶元素
	 */
	public final OpCode S_SLIDE = new OpCode("S_SLIDE", true) {
		@Override
		public void execute(BigInteger b) {
			s.slid(b.intValue());
		}
	};
	// Arithmetic
	/**
	 * 首次出栈为右值,再次出栈为左值,执行加操作，结果压栈
	 */
	public final OpCode A_ADD = new OpCode("A_ADD", false) {
		@Override
		public void execute() {
			a.add();
		}
	};
	/**
	 * 首次出栈为右值,再次出栈为左值,执行减操作，结果压栈
	 */
	public final OpCode A_SUB = new OpCode("A_SUB", false) {
		@Override
		public void execute() {
			a.sub();
		}
	};
	/**
	 * 首次出栈为右值,再次出栈为左值,执行乘操作，结果压栈
	 */
	public final OpCode A_MUL = new OpCode("A_MAL", false) {
		@Override
		public void execute() {
			a.mul();
		}
	};
	/**
	 * 首次出栈为右值,再次出栈为左值,执行整除操作，结果压栈
	 */
	public final OpCode A_DIV = new OpCode("A_DV", false) {
		@Override
		public void execute() {
			a.div();
		}
	};
	/**
	 * 首次出栈为右值,再次出栈为左值,执行取模操作，结果压栈
	 */
	public final OpCode A_MOD = new OpCode("A_MOD", false) {
		@Override
		public void execute() {
			a.mod();
		}
	};
	// Heap Access
	/**
	 * 将首次出栈元素存储到再次出栈元素所指定的堆地址中
	 */
	public final OpCode H_PUT = new OpCode("H_PUT", false) {
		@Override
		public void execute() {
			h.put();
		}
	};
	/**
	 * 将出栈元素所指定的堆位置处元素压栈
	 */
	public final OpCode H_GET = new OpCode("H_GET", false) {
		@Override
		public void execute() {
			h.get();
		}
	};
	// Flow Control
	/**
	 * 定义一个标记
	 */
	public final OpCode F_MARK = new OpCode("F_MARK", true) {
		@Override
		public void execute(BigInteger b) {
			f.mark(b);
		}

		@Override
		public boolean isMarkOpr() {
			return true;
		}
	};
	/**
	 *调用函数
	 */
	public final OpCode F_CALL = new OpCode("F_CALL", true) {
		@Override
		public void execute(BigInteger b) {
			f.call(b);
		}
	};
	/**
	 * 无条件跳转
	 */
	public final OpCode F_JMP = new OpCode("F_JMP", true) {
		@Override
		public void execute(BigInteger b) {
			f.jmp(b);
		}
	};
	/**
	 * 栈顶元素为0时跳转
	 */
	public final OpCode F_JZ = new OpCode("F_JZ", true) {
		@Override
		public void execute(BigInteger b) {
			f.jz(b);
		}
	};
	/**
	 * 栈顶元素为负时跳转
	 */	
	public final OpCode F_JNEG = new OpCode("F_JNEG", true) {
		@Override
		public void execute(BigInteger b) {
			f.jneg(b);
		}
	};
	/**
	 * 函数结束返回调用处
	 */
	public final OpCode F_RET = new OpCode("F_RET", false) {
		@Override
		public void execute() {
			f.ret();
		}
	};
	/**
	 * 无条件结束程序运行
	 */
	public final OpCode F_END = new OpCode("F_END", false) {
		@Override
		public void execute() {
			f.end();
		}
	};
	// I/O
	/**
	 * 输入数字压栈
	 */
	public final OpCode I_INT = new OpCode("F_INT", false) {
		@Override
		public void execute() {
			io.getInt();
		}
	};
	/**
	 * 输入字符压栈
	 */
	public final OpCode I_CHR = new OpCode("I_CHR", false) {
		@Override
		public void execute() {
			io.getChar();
		}
	};
	/**
	 * 输出栈顶数字
	 */
	public final OpCode O_INT = new OpCode("O_INT", false) {
		@Override
		public void execute() {
			io.putInt();
		}
	};
	/**
	 * 输出栈顶字符
	 */
	public final OpCode O_CHR = new OpCode("O_CHR", false) {
		@Override
		public void execute() {
			io.putChar();
		}
	};
	// EOF
	/**
	 * 文件结束
	 */
	public final OpCode X_EOF = new OpCode("X_EOF", false) {
		@Override
		public void execute() {
			// do nothing
			// System.out.println("OK!");//测试用
		}
	};

	/**
	 * 构造函数
	 * 
	 * @param cs
	 *            代码容器类对象
	 */
	public Operators(CodeSection cs) {
		this.stdio = cs.getIo();
		s = new Stack(stdio);
		a = new Arith(s);
		h = new Heap(cs.getVmarg().getHeapMax(), s);
		io = new IO(stdio, s, h);
		f = new Flow(cs.getMng(), s);
	}

	/**
	 * 获得栈对象
	 * 
	 * @return 栈对象的引用
	 */
	public Stack getStack() {
		return s;
	}

	/**
	 * 获得堆对象
	 * 
	 * @return 堆对象的引用
	 */
	public Heap getHeap() {
		return h;
	}

	/**
	 * 获得流对象
	 * 
	 * @return 流对象的引用
	 */
	public Flow getFlow() {
		return f;
	}

	/**
	 * 获得运算对象
	 * 
	 * @return 运算对象的引用
	 */
	public Arith getArith() {
		return a;
	}

	/**
	 * 获得输入输出对象
	 * 
	 * @return 输入输出对象的引用
	 */
	public IO getIo() {
		return io;
	}
}
