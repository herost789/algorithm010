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
	2.找到最近最简方法，将其拆解成可重复解决的问题
	(重复子问题)
	3.数学归纳法的思维
#####分治回溯就是一种特殊的递归或者是较为复杂的递归
按照DFS算法的策略，从跟结点出发搜索解空间树。首先根
结点成为活结点（指自身已生成但其孩子结点没有全部生
成的结点），同时也成为当前的扩展结点（指正在产生孩
子结点的结点）
在当前的扩展结点处，搜索向纵深方向移至一个新结点。
这个新结点就成为新的活结点，并成为当前扩展结点。
如果在当前的扩展结点处不能再向纵深方向移动，则当
前扩展结点就成了死结点（指其所有子结点均已产生的结
点）。此时应往回移动（回溯）至最近的一个活结点处，
并使这个活结点成为当前的扩展结点。回溯法以这种方式
递归地在解空间中搜索，直到找到所要求的解或解空间中
已无活结点为止。
