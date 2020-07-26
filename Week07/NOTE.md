学习笔记
Trie树 一定要手写如何构建前缀树。单词搜索。使用Trie树构建prefix，然后使用DFS求出结果，分析出他的时间复杂度然后放到笔记中

并查集，并查集模板
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

并查集的应用，微信朋友圈，群组功能
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
	
