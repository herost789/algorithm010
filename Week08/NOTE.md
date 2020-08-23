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