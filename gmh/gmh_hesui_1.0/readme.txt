		gmh说明文件
文件介绍：
	gmh文件夹	gmh工程文件夹
	readme.txt	本帮助文件
	readme.htm	本帮助文件副本
	gmh.bat 	辅助运行的批处理文件（支持直接拖曳运行）
	doc文件夹	编译前的技术文档
	gmh.chm 	编译后的技术文档
	gmh.jar 	编译后的jar可执行文件
	config.xml	运行所需的默认配置文件
	其他.gmh文件	gmh语示例文件

所有系统，要运行此虚拟机的前提都是安装了jre，如果你觉得安装jdk是麻烦的，在此表示抱歉，因为项目跨平台的目的给某些用户带来了麻烦。但是我们却不会因此而担心，因为此gmh虚拟机和jre都是免费的。
此程序在windows下的运行方式比较简单：
	在命令行下转到当前目录后，使用如下命令（其中的gmh.bat、gmh.jar和config.xml为起效文件，并且必须在同一个目录）：
	使用方法 gmh sourceFile [{/r|/c|/d}] [/o outputFile] [/p configFile]
	必选参数：
		sourceFile gmh源码文件路径和文件名
	可选参数：
		运行模式参数：
			/r 运行模式
			/c 编译模式
			/d 调试模式
		输出重定向参数：
			/o outputFile outputFile为所要重定向的输出文件名
		配置文件参数：
			/p configFile configFile为所指定的配置文件的路径和文件名
	如果你对命令行较为陌生，并且仅仅是想运行gmh文件，不用担心，因为gmh批处理文件支持拖曳，你可以直接将gmh文件拖曳到gmh.bat图标上运行。
在其他系统下可能就不能运行bat文件了，运行方式可以参照gmh.bat文件的内容，先转到当前目录，然后使用方法仅仅是将上面介绍中的“gmh”命令改为“java -jar gmh.jar”即可。