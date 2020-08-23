学习总结：
# PriorityQueue
### JAVADOC这么说：
无界的PriorityQueue基于一个priority Heap
元素的优先级是由他天生的comparable或者comparator来
确定的。由这两个中哪个确定是由使用哪个构造方法决定的  
PriorityQueue不允许null元素存在，Priority的元素
顺序依赖自然排序并且不允许不可比较的对象存在.这样做
可能会造成ClassCastException  
PriorityQueue的顺序依据指定的排序，如果排序需要的是
最小值，那么队列的头部就是最小值，这个关系可以任意打
破，获取操作访问的是队列的头部元素  
PriorityQueue无界，但是他有一个内部的空间来管理在
队列中存储元素的数组大小。他至少是和队列大小一样，同
时还会随着元素的增加，自动增加空间  
这个类和他的迭代器实现了全部Collection和Iterator的
方法，他的迭代器保证遍历元素使用任何特别的顺序，如
果你需要有序的遍历，可以使用Arrays.sort  
注意这个实现并没有加锁，所以多线程不能并发的同时修
改队列，作为替代，可以使用PriorityBlockingQueue  
这个实现提供了O(logn)的入队出队操作包括offer，poll
，remove，add。这个类属于Collections框架  
优先队列是基于二叉堆实现的。如果使用数组来表示二叉堆，
对Q[n]的孩子分别是队列Q[2*n+1]和队列Q[2*(n+1)]。
默认的初始容量为11  


# Queue
### 使用心得
queue是一个接口属于Collection接口，下面有三种实现  
	1.Deque双端队列  
	2.BlockingQueue阻塞队列  
	3.AbstractQueue非阻塞队列  
LinkedList实现了Deque。常用的queue的实现为Deque
Queue是实现了先入先出，主要的方法为add,offer均为
添加操当因为容量问题添加失败时，add会抛出
IllegalStateException异常，而offer不会抛出异常
remove删除队头元素，如果为空，则返回null，peek和
poll都是返回队头元素，区别是，如果队列为空那么poll
会抛出NoSuchElementException异常

### HashMap
#### 基本概念
它根据键的hashCode值来进行存储，大多数情况下可以直接
定位到它的值。不考虑哈希冲突的情况下，仅需一次定位即
可完成，时间复杂度为O(1)  
从结构实现上来看(jdk1.8)HashMap是数组+链表+红黑树
实现的。HashMap有一个非常重要的字段Node[] table，
即哈希桶数组，他是一个Node的数组。Node是HashMap的
一个内部类，实现了Map.Entry接口，本质就是一个映射
(键值对)。如果哈希桶很大，即使差的Hash算法也会比较
分散，如果哈希桶很小，即使好的Hash算法也会出现较多
碰撞，所以就需要在空间成本和时间成本之间进行权衡，
通过Hash算法和扩容因子来进行控制。threshold=length
*Loadfactor，modCount用来记录HashMap内部结构发生
变化的次数，主要用于迭代快速失败。size为HashMap内部
实际存储键值对的数量。length为HashMap中Node[]数组的
长度，HashMap数组的长度length必须是2的n次方。  
#### 具体实现
##### hash算法本质上就三步：  
1. 取Key的hash索引值 h=hashcode()，调用hashcode
2. 高位运算 hash=h^(h>>>16)，计算Hash
3. 取模运算 (n-1)&hash，计算下标
##### HashMap的put方法
1. 判断键值对数组table[i]是否为空或null，否则执行
resize()进行扩容
2. 根据键值Key计算hash值得到插入的数组索引i，如果
table[i]==null转向6.如果table[i]不为空转向3
3. 判断table[i]的首个元素是否和key一样，如果相同
直接覆盖value，否则转向4.这里的相同指的是hashCode
和equals
4. 判断table[i]是否为treeNode，即table[i]是否为
红黑树，如果是红黑树直接插入键值对，否则转向5
5. 遍历table[i]，判断链表长度是否为8，大于8的话
将链表转化为红黑树，在红黑树中执行插入操作，否则
进行链表的插入操作，遍历过程中若发现key已经存在直
接覆盖value即可
6. 插入成功后，判断实际存在的键值对数量size是否超
过了最大容量threshold，如果超过，则进行扩容
##### 扩容机制
扩容就是重新计算容量，方法是使用一个新的数组代替已
有的较小的数组。  
学习笔记
### 递归的实现，特性以及思维要点
#### 递归-循环，字节码上看是相似的
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
### 递归思维要点
	1. 不要进行人肉递归(最大误区)
	2. 找到最近最简方法，将其拆解成可重复解决的问题
	(重复子问题)
	3. 数学归纳法的思维
### 分治回溯就是一种特殊的递归或者是较为复杂的递归
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
### 深度优先和广度优先
搜索--非智能搜索，是一种暴力搜索，本质是将所有的节
点都遍历一次且仅遍历一次。  
DFS使用模板进行编码，使用递归来编写。先写递归结束
的条件，然后写本次处理的逻辑，最后写下转。  
代码模板  
if node in visited return//递归终止条件，当节点
已经加入则返回，什么都不做   
visited.add(node)//当前层逻辑，就是将访问的节点
添加到已访问的集合中  
dfs(node.left)  
dfs(node.right)//下转，分别遍历左子树和右子树  
如果是图，就是联通的相邻节点，如果是多叉树，这里
就是对所有children节点进行递归  
深度优先遍历的顺序和前序遍历一致
BFS使用队列进行编码  
def BFS(graph,start,end)  
queue=[]  
queue.append([start])  
visited.add(start)  
while queue:  
      node=queue.pop()  
      visited.add(node)  
      process(node)  
      nodes=generate_related_nodes(node)  
      queue.push(nodes)
      
    

### 贪心算法
贪心算法是一种每一步选择中都采取在当前状态下最好或
最优(即最有利)的选择，从而希望全局的结果是最好或者
最优 

注意贪心算法与动态规划不同，贪心算法不能回退。  
动态规划则会保存以前的结果，并根据以前的结果对当前
进行选择，有回退功能  
贪心：当下做最优选择，无法回退
回溯：可以回退
动态规划：最优判断+回退
贪心法一般可以解决一些最优化的问题，如求图中的最小
树，求哈夫曼编码等。一旦一个问题可以使用贪心算法来
求解，那么贪心法是解决这个问题的最好办法。但是一般
工程或者生活中的问题，贪心法一般不能得到我们需要的
结果。由于贪心法的高效性及其所求答案比较接近最优结果
贪心法也可以用做辅助算法，或者直接解决一些要求结果不
特别精确的问题  
### 动态规划：
	动态规划和递归或者分治没有根本上的区别(关键是看有无最优的子结构)
	共性：找到重复子问题
	差异性：最优子结构，中途可以淘汰次优解
	递归时加入缓存，提高性能，被称为记忆化搜索
	进行分治和记忆化。然后进行动态规划。高级阶段，自顶向下进行性动态规划
	状态转移方程
	opt[i,j]=opt[i+1,j]+opt[i,j+1]
	完整逻辑：
	if(a[i,j]=='空地') otp[i,j]=opt[i+1,j]+opt[i,j+1]
	else:opt[i,j]=0
	动态规划关键点：
		1.最优子结构opt[n]=best_of(opt[n-1],opt[n-2],...)
		2.储存中间状态：opt[i]
		3.递推公式(美其名曰：状态转移方程或者DP方程)
		Fib:opt[i]=opt[n-1]+opt[n-2]
		二维路径：opt[i,j]=opt[i+1][j]+opt[i][j+1](且判断a[i][j]是否是空地)
		uniquePaths
		最长公共子序列
		对于两个字符串，使用二维数组，一个在行上排下来，一个在列上排下来
		动态规划，定义出他的状态是很困难的，需要多加练习
		动态规划小结：
			1.打破自己的思维惯性，形成机器思维
			2.理解复杂逻辑的关键
			3.也是职业进阶的要点要领
			mit动态规划
	状态转移方程构造：
	    表示各个阶段所对应的状态
	    状态和行为有关
	    整个最值问题的结果可能直接是dp数据的最后一个元素
	    也有可能是dp数组的最值，需要结合题意以及dp[i]的含义分析  
	    
### Trie树  
一定要手写如何构建前缀树。单词搜索。使用Trie树构建prefix，然后使用DFS求出结果，分析出他的时间复杂度然后放到笔记中
    
### 并查集，并查集模板
    // Java
    class UnionFind { 
    	private int count = 0; 
    	private int[] parent; 
    	public UnionFind(int n) { 
    		count = n; 
    		parent = new int[n]; 
    		for (int i = 0; i < n; i++) { 
    			parent[i] = i;
    		}
    	} 
    	public int find(int p) { 
    		while (p != parent[p]) { 
    			parent[p] = parent[parent[p]]; 
    			p = parent[p]; 
    		}
    		return p; 
    	}
    	public void union(int p, int q) { 
    		int rootP = find(p); 
    		int rootQ = find(q); 
    		if (rootP == rootQ) return; 
    		parent[rootP] = rootQ; 
    		count--;
    	}
    }
    
    
    # Python 
    def init(p): 
    	# for i = 0 .. n: p[i] = i; 
    	p = [i for i in range(n)] 
     
    def union(self, p, i, j): 
    	p1 = self.parent(p, i) 
    	p2 = self.parent(p, j) 
    	p[p1] = p2 
     
    def parent(self, p, i): 
    	root = i 
    	while p[root] != root: 
    		root = p[root] 
    	while p[i] != i: # 路径压缩 ?
    		x = i; i = p[i]; p[x] = root 
    	return root
    	
    	
    //C/C++
    class UnionFind {
    public:
        UnionFind(vector<vector<char>>& grid) {
            count = 0;
            int m = grid.size();
            int n = grid[0].size();
            for (int i = 0; i < m; ++i) {
                for (int j = 0; j < n; ++j) {
                    if (grid[i][j] == '1') {
                        parent.push_back(i * n + j);
                        ++count;
                    }
                    else {
                        parent.push_back(-1);
                    }
                    rank.push_back(0);
                }
            }
        }
    
    //递归
        int find(int i) {
            if (parent[i] != i) {
                parent[i] = find(parent[i]);
            }
            return parent[i];
        }
    
    
        void unite(int x, int y) {
            int rootx = find(x);
            int rooty = find(y);
            if (rootx != rooty) {
                if (rank[rootx] < rank[rooty]) {
                    swap(rootx, rooty);
                }
                parent[rooty] = rootx;
                if (rank[rootx] == rank[rooty]) rank[rootx] += 1;
                --count;
            }
        }
    
    
        int getCount() const {
            return count;
        }
    
    
    private:
        vector<int> parent;
        vector<int> rank;
        int count;
    };
### 并查集的应用  
    微信朋友圈，群组功能
    并查集的初始方式，int[] parent最常用的就是parent[i]=i 
    并查集模板记忆熟练，然后判断什么问题可以使用并查集来解决
    
    高级搜索：是对DFS和BFS的进阶
     包括：
    	1.剪枝
    	2.双向BFS
    	3.启发式搜索(A*)
    初级搜索：
    	1.朴素搜索
    	2.优化方式：不重复(fibonacci)利用顺推或者使用中间数组存储，剪枝(生成括号问题)没有必要性的搜索进行剪掉
    	3.搜索方向：
    		DFS:深度优先搜索
    		BFS:广度优先搜索
    		双向搜索，启发式搜索
    	做搜索问题，要在脑中建立搜索树
    剪枝：
    	1.将之前计算过的，存储在缓存中，这样就减少了手动计算的次数
    	2.分支不好，较差的分支，也可以剪掉
    	深蓝就是利用深度搜索，可以列出所有可能的结果，从而做出最优选择
    	alphaGo利用的也是深度搜索的思想，但是使用的是深度学习，进行剪枝来，计算每一步获胜的概率，做出最优的选择
    回溯法：
    	回溯法采用试错的思想，他尝试分步的去解决一个问题，在分步解决问题的过程当中，
    	当他通过尝试发现现有的分步答案不能得到有效的正确的解答的时候，他将取消上一步甚至是上几步的计算，再通过其他的可能的分步解答再次尝试寻找问题的答案。
    	回溯法通常用最简单的递归方法来实现，在反复重复上述的步骤之后可能出现两种情况：
    		1.找到一个可能存在的正确答案
    		2.在尝试了所有可能的分步方法后宣告该问题没有答案
    	在最坏情况下，回溯法会导致一次复杂度为指数级的计算
    用DP来解决括号生成问题
    双向BFS模板。word ladder1和word ladder2是非常高频的题目
    在List中查找元素的时间复杂度是O(n)，在HashSet里面的时间复杂度是O(1)的
    启发式搜索(Heuristic Search A*),根据搜索的条件优化搜索的方向，也就是一边搜索一般有所谓的思考在里面
    A*代码模板
    	# Python
    	def AstarSearch(graph, start, end):
    	pq = collections.priority_queue() # 优先级 —> 估价函数
    	pq.append([start]) 
    	visited.add(start)
    	while pq: 
    		node = pq.pop() # can we add more intelligence here ?
    		visited.add(node)
    		process(node) 
    		nodes = generate_related_nodes(node) 
       unvisited = [node for node in nodes if node not in visited]
    		pq.push(unvisited)
    	
    	
    	//Java
    	public class AStar
    	{
    		public final static int BAR = 1; // 障碍值
    		public final static int PATH = 2; // 路径
    		public final static int DIRECT_VALUE = 10; // 横竖移动代价
    		public final static int OBLIQUE_VALUE = 14; // 斜移动代价
    		
    		Queue<Node> openList = new PriorityQueue<Node>(); // 优先队列(升序)
    		List<Node> closeList = new ArrayList<Node>();
    		
    		/**
    		 * 开始算法
    		 */
    		public void start(MapInfo mapInfo)
    		{
    			if(mapInfo==null) return;
    			// clean
    			openList.clear();
    			closeList.clear();
    			// 开始搜索
    			openList.add(mapInfo.start);
    			moveNodes(mapInfo);
    		}
    	
    
    		/**
    		 * 移动当前结点
    		 */
    		private void moveNodes(MapInfo mapInfo)
    		{
    			while (!openList.isEmpty())
    			{
    				Node current = openList.poll();
    				closeList.add(current);
    				addNeighborNodeInOpen(mapInfo,current);
    				if (isCoordInClose(mapInfo.end.coord))
    				{
    					drawPath(mapInfo.maps, mapInfo.end);
    					break;
    				}
    			}
    		}
    		
    		/**
    		 * 在二维数组中绘制路径
    		 */
    		private void drawPath(int[][] maps, Node end)
    		{
    			if(end==null||maps==null) return;
    			System.out.println("总代价：" + end.G);
    			while (end != null)
    			{
    				Coord c = end.coord;
    				maps[c.y][c.x] = PATH;
    				end = end.parent;
    			}
    		}
    	
    
    		/**
    		 * 添加所有邻结点到open表
    		 */
    		private void addNeighborNodeInOpen(MapInfo mapInfo,Node current)
    		{
    			int x = current.coord.x;
    			int y = current.coord.y;
    			// 左
    			addNeighborNodeInOpen(mapInfo,current, x - 1, y, DIRECT_VALUE);
    			// 上
    			addNeighborNodeInOpen(mapInfo,current, x, y - 1, DIRECT_VALUE);
    			// 右
    			addNeighborNodeInOpen(mapInfo,current, x + 1, y, DIRECT_VALUE);
    			// 下
    			addNeighborNodeInOpen(mapInfo,current, x, y + 1, DIRECT_VALUE);
    			// 左上
    			addNeighborNodeInOpen(mapInfo,current, x - 1, y - 1, OBLIQUE_VALUE);
    			// 右上
    			addNeighborNodeInOpen(mapInfo,current, x + 1, y - 1, OBLIQUE_VALUE);
    			// 右下
    			addNeighborNodeInOpen(mapInfo,current, x + 1, y + 1, OBLIQUE_VALUE);
    			// 左下
    			addNeighborNodeInOpen(mapInfo,current, x - 1, y + 1, OBLIQUE_VALUE);
    		}
    	
    
    		/**
    		 * 添加一个邻结点到open表
    		 */
    		private void addNeighborNodeInOpen(MapInfo mapInfo,Node current, int x, int y, int value)
    		{
    			if (canAddNodeToOpen(mapInfo,x, y))
    			{
    				Node end=mapInfo.end;
    				Coord coord = new Coord(x, y);
    				int G = current.G + value; // 计算邻结点的G值
    				Node child = findNodeInOpen(coord);
    				if (child == null)
    				{
    					int H=calcH(end.coord,coord); // 计算H值
    					if(isEndNode(end.coord,coord))
    					{
    						child=end;
    						child.parent=current;
    						child.G=G;
    						child.H=H;
    					}
    					else
    					{
    						child = new Node(coord, current, G, H);
    					}
    					openList.add(child);
    				}
    				else if (child.G > G)
    				{
    					child.G = G;
    					child.parent = current;
    					openList.add(child);
    				}
    			}
    		}
    	
    
    		/**
    		 * 从Open列表中查找结点
    		 */
    		private Node findNodeInOpen(Coord coord)
    		{
    			if (coord == null || openList.isEmpty()) return null;
    			for (Node node : openList)
    			{
    				if (node.coord.equals(coord))
    				{
    					return node;
    				}
    			}
    			return null;
    		}
    	
    
    	
    
    		/**
    		 * 计算H的估值：“曼哈顿”法，坐标分别取差值相加
    		 */
    		private int calcH(Coord end,Coord coord)
    		{
    			return Math.abs(end.x - coord.x)
    					+ Math.abs(end.y - coord.y);
    		}
    		
    		/**
    		 * 判断结点是否是最终结点
    		 */
    		private boolean isEndNode(Coord end,Coord coord)
    		{
    			return coord != null && end.equals(coord);
    		}
    	
    
    		/**
    		 * 判断结点能否放入Open列表
    		 */
    		private boolean canAddNodeToOpen(MapInfo mapInfo,int x, int y)
    		{
    			// 是否在地图中
    			if (x < 0 || x >= mapInfo.width || y < 0 || y >= mapInfo.hight) return false;
    			// 判断是否是不可通过的结点
    			if (mapInfo.maps[y][x] == BAR) return false;
    			// 判断结点是否存在close表
    			if (isCoordInClose(x, y)) return false;
    	
    
    			return true;
    		}
    	
    
    		/**
    		 * 判断坐标是否在close表中
    		 */
    		private boolean isCoordInClose(Coord coord)
    		{
    			return coord!=null&&isCoordInClose(coord.x, coord.y);
    		}
    	
    
    		/**
    		 * 判断坐标是否在close表中
    		 */
    		private boolean isCoordInClose(int x, int y)
    		{
    			if (closeList.isEmpty()) return false;
    			for (Node node : closeList)
    			{
    				if (node.coord.x == x && node.coord.y == y)
    				{
    					return true;
    				}
    			}
    			return false;
    		}
    	}
    	估价函数：
    		启发式函数：h(n):他用来屏加哪些节点最优希望的是一个我们要找的节点，h(n)会返回一个非负实数，也可以认为是从节点n的目标节点路径的估计成本
    		启发式函数是一种告知搜索方向的方法。他提供了一种明智的方法来猜测哪个邻居节点会导向一个目标
    		曼哈顿举例，坐标差的绝对值.A*搜索不适用queue而改用priority queue
    AVL树，红黑树，B树，B+树
    	二叉树遍历：前序遍历：根左右，中序遍历：左根右，后序遍历：左右根
    	树和链表没有本质区别，链表有左右指向，即成为了树
    二叉搜索树：也称为有序二叉树，排序二叉树，是指一棵空树或者具有下列性值的二叉树：
    	1.左子树上所有节点的值均小于他的根节点的值
    	2.右子树上所有节点的值均大于他的根节点的值
    	3.以此类推：左右子树也分别为二叉查找树(这就是重复性)
    	中序遍历：升序排列
    保证性能的关键：
    	1.保证二维维度！->左右子树节点平衡(recursively)
    	2.Balanced
    学完保证学会2-3树，AVL树，B树，红黑树。高级的话了解Splay tree和treap
    AVL树：
    	1.发明者G.M.Adelson-Velsky和Evgenii Landis
    	2.Balance Factor(平衡因子)：
    	是他的左子树的高度减去他的右子树的高度(有时相反).balance factor={-1,0,1}
    	二叉搜索树的查询效率只与高度有关，和他的节点个数没有关系
    	3.通过旋转操作来进行平衡(四种)
    	1.左旋，子树形态：右右子树
    	2.右旋，子树形态：左左子树
    	3.左右旋，子树形态：左右子树，先进行一次左旋
    	4.右左旋，子树形态：右左子树
    	如果左旋或右旋的节点有左右子树，左旋或者右旋以后，夹在中间的子树需要还一个父亲节点
    因为平衡二叉树每添加一个节点可能都需要进行重新的平衡，所以进行平衡的操作很多，这时候我们就引入了近似平衡二叉树
    近似平衡二叉树，他是进行取舍，一种近似平衡二叉树的实现就是红黑树
    红黑树是一种近似平衡的二叉树(Banary Search Tree)，他能够确保任何一个节点的左右子树的高度差小于两倍。以下五点性质就是为了保证高度差小于两倍
    具体来说，红黑树是满足如下条件的二叉搜索树：
    	1.每个结点要么是红色，要么是黑色
    	2.根结点是黑色
    	3.每个叶节点(NIL结点，空结点)
    	4.不能有相邻的两个红色结点
    	5.从任一结点到其每个叶子的所有路径都包含相同数目的黑色结点
    	最关键的是4.5两条
    AVL和红黑树的对比：
    	1.查找性能AVL更好，因为他是严格平衡的，树高更低
    	2.红黑树提供您更快的插入和删除操作，因为AVL的旋转操作更多
    	3.AVL需要存储每个结点的平衡因子和或高度，所以需要每个结点存储一个Integer
    	而红黑树每个结点只需要1位信息存储0或者1(表示黑，或者红)
    	4.如果在读操作很多，写操作很少的情况下就使用AVL，如果插入操作较多或者插入和查询一半一半就使用红黑树
    	红黑树一般会在高级语言的库里面，比如map，multimap，multisetin c++
    学习笔记
    
### 位运算
    十进制<->二进制，如何转换
    位运算符
    左移<< 0011=>0110
    右移>> 0110=>0011
    64位的计算机，一个整型由64个二进制位来表示
    或运算| 0011|1011=>1011
    与运算& 0011&1011=>0011
   	按位取反~ 0011=>1100
   	按位异或(相同为0，不同为1)^ 0011^1011=>1000
   	XOR-异或：x^0=x,	x^1s=~x,//注意1s=~0，	x^(~x)=1s
   			  x^x=0,	c=a^b=>a^c=b,b^c=a//交换两个数
    			  a^b^c=a^(b^c)=(a^b)^c//集合性
    	指定位置的位运算：
    		1.将x最右边的n位清零：x&(~0<<n)
    		2.获取x的第n位值(0或者1)：(x>>n&1)
    		3获取x的第n位的幂值：x&(1<<n)
    		4.仅将第n位置为1：x|(1<<n)
    		5.仅将第n位置为0：x&(~(1<<n))
    		6.将x最高位至第n位(含)清零：x&((1<<n)-1)
    	判断奇偶：
    		1.x%2==1->(x&1)==1
    		2.x%2==0->(x&1)==0
    		3.x>>1->x/2
    		即x=x/2->x=x>>1
    		4.x=x&(x-1)清零最低为的1
    		x&-x=>得到最低为的1
    		x&~x=>0
    	n皇后位运算解决代码：
    	# Python
    def totalNQueens(self, n): 
    	if n < 1: return [] 
    	self.count = 0 
    	self.DFS(n, 0, 0, 0, 0) 
    	return self.count
    def DFS(self, n, row, cols, pie, na): 
    	# recursion terminator 
    	if row >= n: 
    		self.count += 1 
    		return
    	bits = (~(cols | pie | na)) & ((1 << n) — 1)  # 得到当前所有的空位
    	while bits: 
    		p = bits & —bits # 取到最低位的1
    		bits = bits & (bits — 1) # 表示在p位置上放入皇后
    		self.DFS(n, row + 1, cols | p, (pie | p) << 1, (na | p) >> 1) 
            # 不需要revert  cols, pie, na 的状态
    		// Java
    	class Solution {
    		private int size; 
    		private int count;
    		private void solve(int row, int ld, int rd) { 
    			if (row == size) { 
    				count++; 
    				return; 
    			}
    			int pos = size & (~(row | ld | rd)); 
    			while (pos != 0) { 
    				int p = pos & (-pos); 
    				pos -= p; // pos &= pos - 1; 
    				solve(row | p, (ld | p) << 1, (rd | p) >> 1); 
    			} 
    		} 
    	public int totalNQueens(int n) { 
    		count = 0; 
    		size = (1 << n) - 1; 
    		solve(0, 0, 0); 
    		return count; 
    	  } 
    	}
    	
    	//C/C++
    
    	class Solution {
    	public:
    		int totalNQueens(int n) {
    			dfs(n, 0, 0, 0, 0);
    			
    			return this->res;
    		}
    		
    		void dfs(int n, int row, int col, int ld, int rd) {
    			if (row >= n) { res++; return; }
    			
    			// 将所有能放置 Q 的位置由 0 变成 1，以便进行后续的位遍历
    			int bits = ~(col | ld | rd) & ((1 << n) - 1);
    			while (bits > 0) {
    				int pick = bits & -bits; // 注: x & -x
    				dfs(n, row + 1, col | pick, (ld | pick) << 1, (rd | pick) >> 1);
    				bits &= bits - 1; // 注: x & (x - 1)
    			}
    		}
    
    
    	private:
    		int res = 0;
    	};
    	判断是否是2的幂次，需要保证所有的二进制位中有且仅有1个二进制位是1
### 布隆过滤器bloom filter
布隆过滤器的原理实现：https://www.cnblogs.com/cpselvis/p/6265825.html
布隆过滤器解决缓存击穿，垃圾邮件识别，集合判重https://blog.csdn.net/tianyaleixiaowu/article/details/74721877
布隆过滤器实现实例https://www.geeksforgeeks.org/bloom-filters-introduction-and-python-implementation/
高性能布隆过滤器python：https://github.com/jhgg/pybloof
布隆过滤器java1：https://github.com/lovasoa/bloomfilter/blob/master/src/main/java/BloomFilter.java
布隆过滤器java2：https://github.com/Baqend/Orestes-Bloomfilter
    	hashtable：拉链存储重复元素
    	布隆过滤器进行插入操作完成后，当一个测试元素的二进制位中，有一个是0可以说这个元素肯定不再布隆过滤器中
    	但是当测试元素所有的二进制位为1时，我们只能说他可能在布隆过滤器中，因为他的所有的位可能跨越多个插入元素所在的位
    	布隆过滤器一般是在最外面当作一个缓存来使用
    	布隆过滤器的应用：
    	1.比特币网络，判定结点是否在分布式系统中
    	2.分布式系统
    	3.Redis缓存
    	4.垃圾邮件，评论等的过滤
    LRU Cache的实现，应用和题解
    	人类的记忆可以类比缓存
    LRU Cache
    	两个要素：大小，替换策略
    	HashTable+DoubleLinkedList
    	O(1)查询
    	O(1)修改、更新
    # Python 
    
    class LRUCache(object): 
    
    	def __init__(self, capacity): 
    		self.dic = collections.OrderedDict() 
    		self.remain = capacity
    
    	def get(self, key): 
    		if key not in self.dic: 
    			return -1 
    		v = self.dic.pop(key) 
    		self.dic[key] = v   # key as the newest one 
    		return v 
    
    	def put(self, key, value): 
    		if key in self.dic: 
    			self.dic.pop(key) 
    		else: 
    			if self.remain > 0: 
    				self.remain -= 1 
    			else:   # self.dic is full
    				self.dic.popitem(last=False) 
    		self.dic[key] = value
    
    
    C++
    //C/C++
    
    struct CacheNode {
        int key, value;
        CacheNode *pre, *next;
          
        CacheNode(int key_ = 0, int value_ = 0) 
            : key(key_), value(value_), pre(NULL), next(NULL) {}
    };
    
    class LRUCache {
    public:
        LRUCache(int capacity) 
            : _capacity(capacity), _head(new CacheNode()), _tail(_head) {}
        
        int get(int key) {
            auto it = _cache.find(key);
            if (it == _cache.end()) return -1;
            
            moveToTail(it->second);
            return it->second->value;
        }
        
        void put(int key, int value) {
            auto it = _cache.find(key);
            
            if (it != _cache.end()) {
                it->second->value = value;
                moveToTail(it->second);
            }
            else if ((int)_cache.size() < _capacity) {
                auto node = new CacheNode(key, value);
                addToTail(node);          
                _cache[key] = node;
            }
            else {
                // Reuse existing node
                _cache.erase(_head->next->key);
                moveToTail(_head->next);
                _tail->key = key; _tail->value = value;
                _cache[key] = _tail;
            }
        }
        
        ~LRUCache() {
            auto pCurr = _head;
            while (pCurr != NULL) {
                auto next = pCurr->next;
                delete pCurr;
                pCurr = next;
            }
        }
        
    private:
        const int _capacity;
        CacheNode *const _head, *_tail;
        unordered_map<int, CacheNode*> _cache;
        
        void moveToTail(CacheNode *node) {
            if (node == _tail) return;
            
            node->pre->next = node->next;
            node->next->pre = node->pre;
            
            addToTail(node);
        }
        
        void addToTail(CacheNode *node) {
            node->next = _tail->next;
            _tail->next = node;
            node->pre = _tail;
            _tail = node;                   
        }
    };
    
    
    Java
    /**
     * 使用 哈希表 + 双端链表 实现
     */
    class LinkedNode {
      constructor(key = 0, val = 0) {
        this.key = key
        this.val = val
        this.prev = null
        this.next = null
      }
    }
    
    
    class LinkedList {
      constructor() {
        this.head = new LinkedNode()
        this.tail = new LinkedNode()
        this.head.next = this.tail
        this.tail.prev = this.head
      }
    
    
      insertFirst(node) {
        node.next = this.head.next
        node.prev = this.head
        this.head.next.prev = node
        this.head.next = node
      }
    
    
      remove(node) {
        node.prev.next = node.next
        node.next.prev = node.prev
      }
    
    
      removeLast() {
        if (this.tail.prev === this.head) return null
        let last = this.tail.prev
        this.remove(last)
        return last
      }
    }
    
    
    /**
     * @param {number} capacity
     */
    var LRUCache = function(capacity) {
      this.capacity = capacity
      this.keyNodeMap = new Map()
      this.cacheLink = new LinkedList()
    };
    
    
    /** 
     * @param {number} key
     * @return {number}
     */
    LRUCache.prototype.get = function(key) {
      if (!this.keyNodeMap.has(key)) return -1
      let val = this.keyNodeMap.get(key).val
      this.put(key, val)
      return val
    };
    
    
    /** 
     * @param {number} key 
     * @param {number} value
     * @return {void}
     */
    LRUCache.prototype.put = function(key, value) {
      let size = this.keyNodeMap.size
      if (this.keyNodeMap.has(key)) {
        this.cacheLink.remove(this.keyNodeMap.get(key)); 
        --size 
      }
      if (size >= this.capacity) {
        this.keyNodeMap.delete(this.cacheLink.removeLast().key)
      }
      let node = new LinkedNode(key, value)
      this.keyNodeMap.set(key, node)
      this.cacheLink.insertFirst(node)
    };
    
    
    
    
    Java
    class LRUCache {
        /**
         * 缓存映射表
         */
        private Map<Integer, DLinkNode> cache = new HashMap<>();
        /**
         * 缓存大小
         */
        private int size;
        /**
         * 缓存容量
         */
        private int capacity;
        /**
         * 链表头部和尾部
         */
        private DLinkNode head, tail;
    
        public LRUCache(int capacity) {
            //初始化缓存大小，容量和头尾节点
            this.size = 0;
            this.capacity = capacity;
            head = new DLinkNode();
            tail = new DLinkNode();
            head.next = tail;
            tail.prev = head;
        }
    
        /**
         * 获取节点
         * @param key 节点的键
         * @return 返回节点的值
         */
        public int get(int key) {
            DLinkNode node = cache.get(key);
            if (node == null) {
                return -1;
            }
            //移动到链表头部
             (node);
            return node.value;
        }
    
        /**
         * 添加节点
         * @param key 节点的键
         * @param value 节点的值
         */
        public void put(int key, int value) {
            DLinkNode node = cache.get(key);
            if (node == null) {
                DLinkNode newNode = new DLinkNode(key, value);
                cache.put(key, newNode);
                //添加到链表头部
                addToHead(newNode);
                ++size;
                //如果缓存已满，需要清理尾部节点
                if (size > capacity) {
                    DLinkNode tail = removeTail();
                    cache.remove(tail.key);
                    --size;
                }
            } else {
                node.value = value;
                //移动到链表头部
                moveToHead(node);
            }
        }
    
        /**
         * 删除尾结点
         *
         * @return 返回删除的节点
         */
        private DLinkNode removeTail() {
            DLinkNode node = tail.prev;
            removeNode(node);
            return node;
        }
    
        /**
         * 删除节点
         * @param node 需要删除的节点
         */
        private void removeNode(DLinkNode node) {
            node.next.prev = node.prev;
            node.prev.next = node.next;
        }
    
        /**
         * 把节点添加到链表头部
         *
         * @param node 要添加的节点
         */
        private void addToHead(DLinkNode node) {
            node.prev = head;
            node.next = head.next;
            head.next.prev = node;
            head.next = node;
        }
    
        /**
         * 把节点移动到头部
         * @param node 需要移动的节点
         */
        private void moveToHead(DLinkNode node) {
            removeNode(node);
            addToHead(node);
        }
    
        /**
         * 链表节点类
         */
        private static class DLinkNode {
            Integer key;
            Integer value;
            DLinkNode prev;
            DLinkNode next;
    
            DLinkNode() {
            }
    
            DLinkNode(Integer key, Integer value) {
                this.key = key;
                this.value = value;
            }
        }
    }
    
    
    
    
    JavaScript
    // JavaScript
    class LRUCache {
      constructor(capacity) {
        this.capacity = capacity;
        this.cache = new Map();
      }
    
      get(key) {
        if (!this.cache.has(key)) return -1;
        
        let value = this.cache.get(key);
        this.cache.delete(key);
        this.cache.set(key, value);
      }
    
      put(key, value) {
        if (this.cache.has(key)) {
          this.cache.delete(key);
        } else {
          if (this.cache.size >= this.capacity) {
            // Map 中新 set 的元素会放在后面
            let firstKey = this.cache.keys().next();
            this.cache.delete(firstKey.value);
          }
        }
        this.cache.set(key, value);
      }
    }
    
    十大经典排序算法：https://www.cnblogs.com/onepixel/p/7674659.html
    快排代码展示：
    	/ Java
    public static void quickSort(int[] array, int begin, int end) {
        if (end <= begin) return;
        int pivot = partition(array, begin, end);
        quickSort(array, begin, pivot - 1);
        quickSort(array, pivot + 1, end);
    }
    static int partition(int[] a, int begin, int end) {
        // pivot: 标杆位置，counter: 小于pivot的元素的个数
        int pivot = end, counter = begin;
        for (int i = begin; i < end; i++) {
            if (a[i] < a[pivot]) {
                int temp = a[counter]; a[counter] = a[i]; a[i] = temp;
                counter++;
            }
        }
        int temp = a[pivot]; a[pivot] = a[counter]; a[counter] = temp;
        return counter;
    }
    
    
    Go
    //C/C++
    int random_partition(vector<int>& nums, int l, intr) {
      int random_pivot_index = rand() % (r - l +1) + l;  //随机选择pivot
      int pivot = nums[random_pivot_index];
      swap(nums[random_pivot_index], nums[r]);
      int i = l - 1;
      for (int j=l; j<r; j++) {
        if (nums[j] < pivot) {
          i++;
          swap(nums[i], nums[j]);
        }
      }
      int pivot_index = i + 1;
      swap(nums[pivot_index], nums[r]);
      return pivot_index;
    }
    void random_quicksort(vector<int>& nums, int l, int r) {
      if (l < r) {
        int pivot_index = random_partition(nums, l, r);
        random_quicksort(nums, l, pivot_index-1);
        random_quicksort(nums, pivot_index+1, r);
      }
    }
    
    
    Python
    #Python
    
    def quick_sort(begin, end, nums):
        if begin >= end:
            return
        pivot_index = partition(begin, end, nums)
        quick_sort(begin, pivot_index-1, nums)
        quick_sort(pivot_index+1, end, nums)
        
    def partition(begin, end, nums):
        pivot = nums[begin]
        mark = begin
        for i in range(begin+1, end+1):
            if nums[i] < pivot:
                mark +=1
                nums[mark], nums[i] = nums[i], nums[mark]
        nums[begin], nums[mark] = nums[mark], nums[begin]
        return mark
    
    
    JavaScript
    // JavaScript
    const quickSort = (nums, left, right) => {
      if (nums.length <= 1) return nums
      if (left < right) {
        index = partition(nums, left, right)
        quickSort(nums, left, index-1)
        quickSort(nums, index+1, right)
      }
    }
          
    const partition = (nums, left, right) => {
      let pivot = left, index = left + 1
      for (let i = index; i <= right; i++) {
        if (nums[i] < nums[pivot]) {
          [nums[i], nums[index]] = [nums[index], nums[i]]
          index++
        }
      }
      [nums[pivot], nums[index-1]] = [nums[index-1], nums[pivot]]
      return index -1
    }
    归并排序代码展示：
    		// Java
    
    public static void mergeSort(int[] array, int left, int right) {
        if (right <= left) return;
        int mid = (left + right) >> 1; // (left + right) / 2
    
        mergeSort(array, left, mid);
        mergeSort(array, mid + 1, right);
        merge(array, left, mid, right);
    }
    
    public static void merge(int[] arr, int left, int mid, int right) {
            int[] temp = new int[right - left + 1]; // 中间数组
            int i = left, j = mid + 1, k = 0;
    
            while (i <= mid && j <= right) {
                temp[k++] = arr[i] <= arr[j] ? arr[i++] : arr[j++];
            }
    
            while (i <= mid)   temp[k++] = arr[i++];
            while (j <= right) temp[k++] = arr[j++];
    
            for (int p = 0; p < temp.length; p++) {
                arr[left + p] = temp[p];
            }
            // 也可以用 System.arraycopy(a, start1, b, start2, length)
        }
    
    
    
    
    C++
    C/C++
    
    void mergeSort(vector<int> &nums, int left, int right) {
    	if (left >= right) return;
    	
    	int mid = left + (right - left) / 2;
    	mergeSort(nums, left, mid);
    	mergeSort(nums, mid+1, right);
    	
    	merge(nums, left, mid, right);
    }
    
    
    void merge(vector<int> &nums, int left, int mid, int right) {
    	vector<int> tmp(right-left+1);
    	int i = left, j = mid+1, k = 0;
    	
    	while (i <= mid && j <= right) {
    		tmp[k++] = nums[i] < nums[j] ? nums[i++] : nums[j++];
    	}
    	while (i <= mid) tmp[k++] = nums[i++];
    	while (j <= right) tmp[k++] = nums[j++];
    	
    	for (i = left, k = 0; i <= right;) nums[i++] = tmp[k++];
    }
    
    
    
    
    
    
    
    Python
    #Python
    
    def mergesort(nums, left, right):
        if right <= left:
            return
        mid = (left+right) >> 1
        mergesort(nums, left, mid)
        mergesort(nums, mid+1, right)
        merge(nums, left, mid, right)
    
    def merge(nums, left, mid, right):
        temp = []
        i = left
        j = mid+1
        while i <= mid and j <= right:
            if nums[i] <= nums[j]:
                temp.append(nums[i])
                i +=1
            else:
                temp.append(nums[j])
                j +=1
        while i<=mid:
            temp.append(nums[i])
            i +=1
        while j<=right:
            temp.append(nums[j])
            j +=1
        nums[left:right+1] = temp
    
    
    
    
    
    
    JavaScript
    // JavaScript
    const mergeSort = (nums) => {
      if (nums.length <= 1) return nums
      let mid = Math.floor(nums.length/2), 
          left = nums.slice(0, mid), 
          right = nums.slice(mid)
      return merge(mergeSort(left), mergeSort(right))
    }
    
    const merge(left, right) => {
      let result = []
      while(left.length && right.length) {
        result.push(left[0] <= right[0] ? left.shift() : right.shift()
      }
      while(left.length) result.push(left.shift())
      while(right.length) result.push(right.shift())
      return result
    }
    
    堆排序代码展示：
    		// Java
    static void heapify(int[] array, int length, int i) {
        int left = 2 * i + 1, right = 2 * i + 2；
        int largest = i;
        if (left < length && array[left] > array[largest]) {
            largest = left;
        }
        if (right < length && array[right] > array[largest]) {
            largest = right;
        }
        if (largest != i) {
            int temp = array[i]; array[i] = array[largest]; array[largest] = temp;
            heapify(array, length, largest);
        }
    }
    public static void heapSort(int[] array) {
        if (array.length == 0) return;
        int length = array.length;
        for (int i = length / 2-1; i >= 0; i-) 
            heapify(array, length, i);
        for (int i = length - 1; i >= 0; i--) {
            int temp = array[0]; array[0] = array[i]; array[i] = temp;
            heapify(array, i, 0);
        }
    }
    
    
    Python
    #Python
    
    def heapify(parent_index, length, nums):
        temp = nums[parent_index]
        child_index = 2*parent_index+1
        while child_index < length:
            if child_index+1 < length and nums[child_index+1] > nums[child_index]:
                child_index = child_index+1
            if temp > nums[child_index]:
                break
            nums[parent_index] = nums[child_index]
            parent_index = child_index
            child_index = 2*parent_index + 1
        nums[parent_index] = temp
    
    
    def heapsort(nums):
        for i in range((len(nums)-2)//2, -1, -1):
            heapify(i, len(nums), nums)
        for j in range(len(nums)-1, 0, -1):
            nums[j], nums[0] = nums[0], nums[j]
            heapify(0, j, nums)
    
    
    
    
    C++
    C/C++
    
    void heapify(vector<int> &array, int length, int i) {
        int left = 2 * i + 1, right = 2 * i + 2;
        int largest = i;
    
        if (left < length && array[left] > array[largest]) {
            largest = left;
        }
        if (right < length && array[right] > array[largest]) {
            largest = right;
        }
    
        if (largest != i) {
            int temp = array[i]; array[i] = array[largest]; array[largest] = temp;
            heapify(array, length, largest);
        }
    
    
        return ;
    }
    
    void heapSort(vector<int> &array) {
        if (array.size() == 0) return ;
    
        int length = array.size();
        for (int i = length / 2 - 1; i >= 0; i--) 
            heapify(array, length, i);
    
        for (int i = length - 1; i >= 0; i--) {
            int temp = array[0]; array[0] = array[i]; array[i] = temp;
            heapify(array, i, 0);
        }
    
        return ;
    }
    
    
    
    
    Plain  Text
    // Javascript
    function heapSort(arr) {
      if (arr.length === 0) return;
      let len = arr.length;
      // 建堆
      for (let i = Math.floor(len / 2) - 1; i >= 0; i--) {
        heapify(arr, len, i);
      }
      // 排序
      for (let i = len - 1; i >= 0; i--) {
        // 堆顶元素与最后一个互换
        [arr[0], arr[i]] = [arr[i], arr[0]];
        // 对 0 ～ i 的数组建堆
        heapify(arr, i, 0);
      }
    }
    function heapify(arr, len, i) {
      let left = i * 2 + 1;
      let right = i * 2 + 2;
      let largest = i;
      if (left < len && arr[left] > arr[largest]) {
        largest = left;
      }
      if (right < len && arr[right] > arr[largest]) {
        largest = right;
      }
      if (largest !== i) {
        [arr[i], arr[largest]] = [arr[largest], arr[i]];
        heapify(arr, len, largest);
      }
    }
    排序算法的划分：	
    	1.比较类排序(传入comparator)：通过比较来决定元素间的相对次序，由于其时间复杂度不能图片O(nlogn)，因此也称为非线性时间比较排序。
    	重点需要看nlogn的排序，快速排序，堆排序，归并排序这三种。
    	2.非比较类排序：不通过比较来决定元素间的相对次序，他可以突破基于比较排序的时间下界，以线性时间运行，因此也称为线性时间非比较类排序
    	初级排序-O(n^2)--三者异曲同工
    		1.选择排序，每次找最小值，然后放到待排序数组的起始位置
    		2.插入排序，从前到后逐步构建有序序列，对于未排序数据，在已排序序列中从后向前扫描，找到相应位置并插入
    		3.冒泡排序，嵌套循环，每次查看相邻元素如果逆序则交换
    		可以认为冒泡排序和选择排序是相逆的，冒泡排序每次会把最大的元素放到最后，而选择排序，则通过一次对比将最小的元素放在了开始
    	高级排序-O(nlogn)
    		1.快速排序：数组取标杆pivot,将小元素防pivot左边，大元素放右侧，然后依次对右边和右边的子数组继续快排;以达到整个序列有序
    		很多的库函数的排序都是使用快排，如java
    		2.归并排序：
    			1.把长度为n的输入序列分成两个长度为n/2的子序列
    			2.对这两个子序列分别采用归并排序
    			3.将两个排序好的子序列合并成一个最终的排序序列
    		归并排序需要一段额外的内存空间，大小为right-left+1
    		两个有序数组合并的模板：
    			a[i]为开始到mid的一个数组，a[j]为mid+1到结束的另一个数组，temp为临时数组，用来合并a[i]和a[j]
    			int i=left; j=mid+1;k=0;
    			while(i<=mid&&j<=right){
    			//想temp数组中按顺序添加元素，如果从a[i]中，取该元素，则a[i++]，如果从a[j]中取元素则a[j++]
    			//常写代码，推荐这种方式，逻辑清晰，简单明了
    				temp[k++]=arr[i]<=arr[j]?arr[i++]:arr[j++];
    			}
    			while(i<=mid) temp[k++]=arr[i++];
    			while(j<=right) temp[k++]=arr[j++];
    	归并和快排具有相似性，但步骤顺序相反
    	归并：先排序左右子数组，然后合并两个有序数组
    	快排：先调配出左右子数组，然后对于左右子数组进行排序
    		3.堆排序-堆插入
    			1.数组元素依次建立小顶堆
    			2.依次取堆顶元素，并删除
    	特殊排序-o(n)
    		1.计数排序(Counting Sort)
    		计数排序要求输入的数据必须是有确定范围的整数。将输入的数据值转化为键存储在额外开辟的数组空间中，然后依次把计数大于1的填充回原数组
    		2.桶排序(Bucket Sort)
    		桶排序的工作原理：假设输入数据服从均匀分步，将数据分到有限数量的桶里，每个桶再分别排序(有可能再使用别的排序算法或是以递归方式继续使用桶排序进行排序)
    		3.基数排序(Radix Sort)
    		基数排序是按照低为先排序，然后收集，再按照高位排序，然后再收集，以此类推，直到最高位。有时候有些属性是有优先级顺序的，先按低优先级排序，再按高优先级排序
    		9种经典排序算法可视化动画-https://www.bilibili.com/video/av25136272
    		6分支看完15种排序算法动画展示-https://www.bilibili.com/video/av63851336
    		树状数组！可以自己学习
    	
