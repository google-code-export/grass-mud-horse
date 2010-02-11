package jcj1988.factory;

import java.math.BigInteger;

import jcj1988.io.IOable;
import jcj1988.oprate.Arith;
import jcj1988.oprate.Flow;
import jcj1988.oprate.Heap;
import jcj1988.oprate.IO;
import jcj1988.oprate.Operatable;
import jcj1988.oprate.Stack;
import jcj1988.vm.CodeSection;

/**
 * 虚拟机操作类工厂的另一种实现方式，是通过继承了操作接口的枚举类来实现的
 * 
 * @author jcj1988
 * */
public class OpEnum implements Factoryable {
	/**
	 * 程序输入输出对象
	 */
	public static IOable std = null;
	/**
	 * 栈对象
	 */
	public static Stack s = null;
	/**
	 * 堆对象
	 */
	public static Heap h = null;
	/**
	 * 流控制对象
	 */
	public static Flow f = null;
	/**
	 * 运算对象
	 */
	public static Arith a = null;
	/**
	 * 输入输出操作对象
	 */
	public static IO io = null;

	/**
	 * 构造函数
	 * 
	 * @param cs
	 *            代码容器类对象
	 */
	public OpEnum(CodeSection cs) {
		std = cs.getIo();
		s = new Stack(std);
		a = new Arith(s);
		h = new Heap(cs.getVmarg().getHeapMax(), s);
		io = new IO(std, s, h);
		f = new Flow(cs.getMng(), s);
	}

	@Override
	public Operatable Factory(String s) {
		OpCode r = null;
		try {
			r = (OpCode) OpCode.class.getDeclaredField(s).get(this);
		} catch (Exception e) {
			e.printStackTrace();
			// std.error(e);
		}
		// 早先的方法
		// OpCode[] oc = OpCode.values();
		// for (int i = 0; i < oc.length; i++) {
		// if (oc[i].name().equals(s)) {
		// r = oc[i];
		// break;
		// }
		// }
		return r;
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

	/**
	 * 操作枚举类（内部类）
	 * 
	 * @author jcj1988
	 */
	public enum OpCode implements Operatable {
		// Stack Manipulation
		/**
		 * 丢弃栈顶元素
		 */
		S_POP(false) {
			@Override
			public void execute() {
				s.pop();
			}
		},
		/**
		 * 压栈
		 */
		S_PUSH(true) {
			@Override
			public void execute(BigInteger b) {
				s.push(b);
			}
		},
		/**
		 * 复制栈顶元素压栈
		 */
		S_DUP(false) {
			@Override
			public void execute() {
				s.dup();
			}
		},
		/**
		 * 复制第n个元素到栈顶
		 */
		S_COPY(true) {
			@Override
			public void execute(BigInteger b) {
				s.copy(b.intValue());
			}
		},
		/**
		 * 交换栈顶两个元素
		 */
		S_SWAP(false) {
			@Override
			public void execute() {
				s.swap();
			}
		},
		/**
		 * 销毁栈顶的n个元素，但保持栈顶元素
		 */
		S_SLIDE(true) {
			@Override
			public void execute(BigInteger b) {
				s.slid(b.intValue());
			}
		},
		// Arithmetic
		/**
		 * 首次出栈为右值,再次出栈为左值,执行加操作，结果压栈
		 */
		A_ADD(false) {
			@Override
			public void execute() {
				a.add();
			}
		},
		/**
		 * 首次出栈为右值,再次出栈为左值,执行减操作，结果压栈
		 */
		A_SUB(false) {
			@Override
			public void execute() {
				a.sub();
			}
		},
		/**
		 * 首次出栈为右值,再次出栈为左值,执行乘操作，结果压栈
		 */
		A_MUL(false) {
			@Override
			public void execute() {
				a.mul();
			}
		},
		/**
		 * 首次出栈为右值,再次出栈为左值,执行整除操作，结果压栈
		 */
		A_DIV(false) {
			@Override
			public void execute() {
				a.div();
			}
		},
		/**
		 * 首次出栈为右值,再次出栈为左值,执行取模操作，结果压栈
		 */
		A_MOD(false) {
			@Override
			public void execute() {
				a.mod();
			}
		},
		// Heap Access
		/**
		 * 将首次出栈元素存储到再次出栈元素所指定的堆地址中
		 */
		H_PUT(false) {
			@Override
			public void execute() {
				h.put();
			}
		},
		/**
		 * 将出栈元素所指定的堆位置处元素压栈
		 */
		H_GET(false) {
			@Override
			public void execute() {
				h.get();
			}
		},
		// Flow Control
		/**
		 * 定义一个标记
		 */
		F_MARK(true) {
			@Override
			public void execute(BigInteger b) {
				f.mark(b);
			}

			@Override
			public boolean isMarkOpr() {
				return true;
			}
		},
		/**
		 *调用函数
		 */
		F_CALL(true) {
			@Override
			public void execute(BigInteger b) {
				f.call(b);
			}
		},
		/**
		 * 无条件跳转
		 */
		F_JMP(true) {
			@Override
			public void execute(BigInteger b) {
				f.jmp(b);
			}
		},
		/**
		 * 栈顶元素为0时跳转
		 */
		F_JZ(true) {
			@Override
			public void execute(BigInteger b) {
				f.jz(b);
			}
		},
		/**
		 * 栈顶元素为负时跳转
		 */
		F_JNEG(true) {
			@Override
			public void execute(BigInteger b) {
				f.jneg(b);
			}
		},
		/**
		 * 函数结束返回调用处
		 */
		F_RET(false) {
			@Override
			public void execute() {
				f.ret();
			}
		},
		/**
		 * 无条件结束程序运行
		 */
		F_END(false) {
			@Override
			public void execute() {
				f.end();
			}
		},
		// I/O
		/**
		 * 输入数字压栈
		 */
		I_INT(false) {
			@Override
			public void execute() {
				io.getInt();
			}
		},
		/**
		 * 输入字符压栈
		 */
		I_CHR(false) {
			@Override
			public void execute() {
				io.getChar();
			}
		},
		/**
		 * 输出栈顶数字
		 */
		O_INT(false) {
			@Override
			public void execute() {
				io.putInt();
			}
		},
		/**
		 * 输出栈顶字符
		 */
		O_CHR(false) {
			@Override
			public void execute() {
				io.putChar();
			}
		},
		// EOF
		/**
		 * 文件结束
		 */
		X_EOF(false) {
			@Override
			public void execute() {
				// do nothing
				// System.out.println("OK!");//测试用
			}
		};
		/**
		 * 是否需要参数
		 */
		public boolean needArg;

		// public String name;

		@Override
		public boolean isNeedArg() {
			return needArg;
		}

		/**
		 * 构造函数
		 * 
		 * @param needArg
		 *            是否需要参数
		 */
		private OpCode(boolean needArg) {
			this.needArg = needArg;
		}

		@Override
		public boolean isMarkOpr() {
			return false;
		}

		@Override
		public String getName() {
			return name();
		}

		@Override
		public void execute() {
			// do nothing
		}

		@Override
		public void execute(BigInteger b) {
			// do nothing
		}

	}
}
