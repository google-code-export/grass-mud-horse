package jcj1988.cfg;

import java.util.Hashtable;

import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import jcj1988.factory.Factoryable;
import jcj1988.oprate.Operatable;
import jcj1988.vm.TriNode;

/**
 * 配置类，主要负责从xml中获取配置设置
 * 
 * @author Administrator
 * 
 */
public final class Config {
	DomParse dp = null;
	Document doc = null;
	NodeList root = null;
	TriNode<Operatable> tree = new TriNode<Operatable>(null);
	VMarg vmarg = null;
	Factoryable fct = null;
	Node cmd = null;
	int heapMax = 0;
	int s = 0;
	int t = 0;
	int l = 0;
	StringBuffer str = null;
	Hashtable<String, String> hashtable = new Hashtable<String, String>();

	/**
	 * 构造函数
	 * @param xmlName xml文件名
	 */
	public Config(String xmlName) {
		dp = new DomParse(xmlName);
		doc = dp.getDocument();
		root = dp.getBoot().item(0).getChildNodes();

		Node n = null;
		// NodeList nl = null;
		for (int i = 0; i < root.getLength(); i++) {
			n = root.item(i);
			if (n.getNodeName().equals("SOURSE")) {
				// TODO: 以后再说
				n.getChildNodes();
			} else if (n.getNodeName().equals("VMARG")) {// 根据xml获得虚拟机配置
				setVMarg(n);
			} else if (n.getNodeName().equals("CMD"))
				cmd = n;// 为创建一个命令树做准备
		}
	}

	/**
	 * 通过xml设置虚拟机参数
	 * @param n xml中的虚拟机参数节点
	 */
	private void setVMarg(Node n) {
		try {
			heapMax = Integer.parseInt(n.getAttributes()
					.getNamedItem("HEAPMAX").getNodeValue());
		} catch (Exception e) {
			heapMax = 65536;
		}

		NodeList nl = n.getChildNodes();
		try {
			for (int j = 0; j < nl.getLength(); j++) {
				n = nl.item(j);
				if (n.getNodeType() == Node.ELEMENT_NODE) {
					if (n.getNodeName().equals("S")) {
						s = Integer.parseInt(n.getTextContent());
					} else if (n.getNodeName().equals("T")) {
						t = Integer.parseInt(n.getTextContent());
					} else if (n.getNodeName().equals("L")) {
						l = Integer.parseInt(n.getTextContent());
					}
				}
			}
		} catch (Exception e) {
			s = 33609;
			t = 27877;
			l = 39532;
		}

		vmarg = new VMarg(heapMax, s, t, l);

	}

	/**
	 * 通过操作类工厂构建一个命令树
	 * @param f 构建命令树所需的操作类工厂
	 * @return 构建好的命令树对象
	 */
	public TriNode<Operatable> buildCmdTree(Factoryable f) {
		this.fct = f;
		buildCmdTree(cmd, tree);
		return tree;
	}

	/**
	 * 通过xml的节点创建一个命令树 
	 * @param cmd 当前文档节点
	 * @param tr 要构造的树的根节点
	 */
	private void buildCmdTree(Node cmd, TriNode<Operatable> tr) {
		Node n = null;
		NodeList nl = cmd.getChildNodes();
		boolean hasChild = false;// 记录是否有孩子
		for (int i = 0; i < nl.getLength(); i++) {
			n = nl.item(i);
			// System.out.println(n.getNodeName());
			TriNode<Operatable> tn = new TriNode<Operatable>(null);
			if (n.getNodeType() == Node.ELEMENT_NODE) {
				if (n.getNodeName().equals("S")) {
					tr.setLeft(tn);
					buildCmdTree(n, tn);
					hasChild = true;
				} else if (n.getNodeName().equals("T")) {
					tr.setMiddle(tn);
					buildCmdTree(n, tn);
					hasChild = true;
				} else if (n.getNodeName().equals("L")) {
					tr.setRight(tn);
					buildCmdTree(n, tn);
					hasChild = true;
				}
			}
		}
		if (!hasChild) {
			tr.setElem(fct.Factory(cmd.getTextContent()));
		}
	}

	/**
	 * 获取命令与伪汇编的哈希表映射
	 * @return 哈希表对象
	 */
	public Hashtable<String, String> getHashtable() {
		str = new StringBuffer();
		getHashtable(cmd);
		System.out.println(hashtable.get("S_DUP"));
		return hashtable;
	}

	/**
	 * 通过xml的节点创建一个映射表,此函数存在问题……不能正确解析xml有待解决…… 
	 * @param cmd 当前文档节点
	 */
	private void getHashtable(Node cmd) {
		Node n = null;
		NodeList nl = cmd.getChildNodes();
		boolean hasChild = false;// 记录是否有孩子
		for (int i = 0; i < nl.getLength(); i++) {
			n = nl.item(i);
			// System.out.println(n.getNodeName());
			if (n.getNodeType() == Node.ELEMENT_NODE) {
				if (n.getNodeName().equals("S")) {
					str.append((char) s);
					getHashtable(n);
					hasChild = true;
				} else if (n.getNodeName().equals("T")) {
					str.append((char) t);
					getHashtable(n);
					hasChild = true;
				} else if (n.getNodeName().equals("L")) {
					str.append((char) l);
					getHashtable(n);
					hasChild = true;
				}
			}
		}
		if (!hasChild) {
			// hashtable.put(fct.Factory(cmd.getTextContent()), str);
			hashtable.put(cmd.getTextContent(), str.toString());
			str = new StringBuffer();
		}

	}

	/**
	 * 获得虚拟机参数对象
	 * @return 虚拟机参数对象的引用
	 */
	public VMarg getVMarg() {
		return vmarg;
	}
}
