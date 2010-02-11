package jcj1988.vm;

import java.io.Reader;

import jcj1988.analyze.Analyzable;
import jcj1988.analyze.VmAnalyzeImpl;
import jcj1988.analyze.CmdAnalyze;
import jcj1988.cfg.Config;
import jcj1988.cfg.VMarg;
import jcj1988.factory.Factoryable;
import jcj1988.factory.OpEnum;
import jcj1988.io.IOable;
import jcj1988.manage.CmpMng;
import jcj1988.manage.DebugMng;
import jcj1988.manage.Manager;
import jcj1988.manage.RunMng;
import jcj1988.oprate.Executable;
//import jcj1988.analyze.OpAnalyzeImpl;
//import java.util.Hashtable;
/**
 * gmh虚拟机的主类，负责调用其他各类来完成用户所指定的操作
 * @author jcj1988
 *
 */
public class GMH implements Executable {
	/**
	 * 虚拟机的输入输出接口
	 */
	public IOable io = null;
	/**
	 * 虚拟机的控制对象
	 */
	public Config cfg = null;
	/**
	 * 输入文件输入流对象
	 */
	Reader reader = null;
	/**
	 * 虚拟机参数对象
	 */
	public VMarg vmarg = null;
	/**
	 * 虚拟机代码容器对象
	 */
	CodeSection cs = null;
	/**
	 * 操作对象工厂
	 */
	public Factoryable fct = null;
	// public TriNode<Operatable> tree = null;
	/**
	 * 解析对象
	 */
	Analyzable alz = null;
	/**
	 * 调度管理对象
	 */
	Manager mng = null;
	/**
	 * 构造函数，通过命令分析对象对虚拟机进行初始化
	 * @param caz 传入的命令解析对象
	 */
	public GMH(CmdAnalyze caz) {
		io = caz.getIo();
		RunMode mode=caz.getMode();
		switch (mode) {
		case RUN:
			mng = new RunMng();
			break;
		case CMP:
			mng = new CmpMng(io);
			break;
		case DBG:
			mng = new DebugMng(io);
			break;
		}
		cfg = new Config(caz.getCfg());
		vmarg = cfg.getVMarg();
		cs = new CodeSection(caz.getSrc(), io, vmarg, mng);
		fct = new OpEnum(cs);// OpEnum(cs);Operators(cs);
		alz = new VmAnalyzeImpl(cs, cfg.buildCmdTree(fct), mng);
		//alz=new OpAnalyzeImpl(cs,cfg.getHashtable());
	}

	@Override
	public void execute() {
		alz.analyse();
		mng.execute();
		io.close();
	}
	
	/**
	 * gmh虚拟机的主函数
	 * @param args 命令行参数
	 */
	public static void main(String[] args) {
		CmdAnalyze caz=new CmdAnalyze(args);
		caz.analyse();	
		IOable io = caz.getIo();
		if(caz.getSrc()==null){
			io.print("没有必选参数'sourceFile',使用方法 GMH sourceFile [{/r|/c|/d}] [/o outputFile] [/p configFile]，如：\nGMH hworld.gmh /r /o out.txt\n");
		}else
			new GMH(caz).execute();
	}
}
