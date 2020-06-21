学习笔记  
###HashMap
####基本概念
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
####具体实现
#####hash算法本质上就三步：  
1. 取Key的hash索引值 h=hashcode()，调用hashcode
2. 高位运算 hash=h^(h>>>16)，计算Hash
3. 取模运算 (n-1)&hash，计算下标
#####HashMap的put方法
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
#####扩容机制
扩容就是重新计算容量，方法是使用一个新的数组代替已
有的较小的数组。