学习笔记
#PriorityQueue
###JAVADOC这么说：
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


#Queue
###使用心得
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




