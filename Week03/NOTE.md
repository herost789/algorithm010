学习笔记
###递归的实现，特性以及思维要点
#####递归-循环，字节码上看是相似的
	向下递归到不同的层，向上又回到原来一层
	用参数来传递不同层之间的变量
	经典递归，计算n！
	递归代码模板
	1. 递归终结条件 recursion terminator
	public void recur(int level,int param){
		if level > MAX_LEVEL
		process_result
		return;
		}
	2. 处理当前层逻辑 process logic in current level
		process(level,param)
	3. 下探到下一层 drill down 
		recur(level+1,newParam)
	4. 清理当前层
	
		// Java
	public void recur(int level, int param) { 
	  // terminator 
	  if (level > MAX_LEVEL) { 
		// process result 
		return; 
	  }
	  // process current logic 
	  process(level, param); 
	  // drill down 
	  recur( level: level + 1, newParam); 
	  // restore current status 
	 
	}
#####递归思维要点
	1.不要进行人肉递归(最大误区)
	2.找到最近最简方法，将其拆解成可重复解决的问题(重复子问题)
	3.数学归纳法的思维
#####分治回溯就是一种特殊的递归或者是较为复杂的递归