学习笔记

状态转移方程串讲：
	递归的本质：寻找重复性->计算机指令集
	超哥感触：
		1.人肉递归低效很累
		2.找到最近最简方法，将其拆解成可重复解决问题
		3.数学归纳法思维
	在递归状态树中不断的淘汰次优解，就是一种动态规划
	动态规划和递归或者分治没有根本上的区别(关键看有无最优的子结构)
	拥有共性：找到重复子问题
	
	差异性：最优子结构，中途可以淘汰次优解
	动态规划状态转移方程：
		1.构造中间变量 (leetcode打家劫舍,通过第i天偷或不偷，构造二维数组，构造状态转移方程)
		
		2.构造状态转移方程
	高级动态规划复杂度分析：
		1.拥有更多的维度(二维，三维，或者更多，甚至需要压缩)
		2.状态方程更加复杂
		提高方法：多练，多思考，过遍数，五毒神掌
字符串算法：
	Python和java的String 是不可变
	String中最常用的遍历字符，java使用charAt(i)来遍历
	hashmap使用hash表实现，treemap使用二叉搜索树实现
Atoi代码示例

// Java
public int myAtoi(String str) {
    int index = 0, sign = 1, total = 0;
    //1. Empty string
    if(str.length() == 0) return 0;
    //2. Remove Spaces
    while(str.charAt(index) == ' ' && index < str.length())
        index ++;
    //3. Handle signs
    if(str.charAt(index) == '+' || str.charAt(index) == '-'){
        sign = str.charAt(index) == '+' ? 1 : -1;
        index ++;
    }
    
    //4. Convert number and avoid overflow
    while(index < str.length()){
        int digit = str.charAt(index) - '0';
        if(digit < 0 || digit > 9) break;
        //check if total will be overflow after 10 times and add digit
        if(Integer.MAX_VALUE/10 < total ||            
        	Integer.MAX_VALUE/10 == total && Integer.MAX_VALUE %10 < digit)
            return sign == 1 ? Integer.MAX_VALUE : Integer.MIN_VALUE;
        total = 10 * total + digit;
        index ++;
    }
    return total * sign;
}






自动检测
# Python
class Solution(object):
    def myAtoi(self, s):
        if len(s) == 0 : return 0
        ls = list(s.strip())
        
        sign = -1 if ls[0] == '-' else 1
        if ls[0] in ['-','+'] : del ls[0]
        ret, i = 0, 0
        while i < len(ls) and ls[i].isdigit() :
            ret = ret*10 + ord(ls[i]) - ord('0')
            i += 1
        return max(-2**31, min(sign * ret,2**31-1))


C++
//C/C++
int myAtoi(string str) {
   int res = 0;
   int sign = 1;
   size_t index = 0;
   if(str.find_first_not_of(' ') != string::npos) 
       index = str.find_first_not_of(' ');
   if(str[index] == '+' || str[index] == '-')
       sign = str[index] == '-' ? -1 : 1;
    
    while(index < str.size() && isdigit(str[index])) {
        res = res * 10 + (str[index++] - '0');
        if(res*sign > INT_MAX) return INT_MAX;
        if(res*sign < INT_MIN) return INT_MIN; 
    }

   return res*sign;
}


C++
class Solution {
public:
    int myAtoi(string str) {
        auto i = getStartIndexOfNonSpace(str);
        if (i < 0) return 0;
        
        auto sign = handleSign(str, i);       
        auto num = sign * convertStringToLong(str, i);
        return convertLongToInt(num);
    }
    
private:
    int getStartIndexOfNonSpace(const string &str) {
        for (int i = 0; i < str.size(); ++i) {
            if (str[i] != ' ') return i;
        }
        
        return -1;
    }
    
    int handleSign(const string &str, int &i) {
        if (str[i] == '-') {
            ++i;
            return -1;
        }
        
        if (str[i] == '+') ++i;
        return 1;
    }
    
    long convertStringToLong(const string &str, int i) {
        long num = 0;        
        while(i < str.size()) {
            if (num > INT_MAX || !isdigit(str[i])) break;            
            num = num * 10 + str[i] - '0';
            ++i;
        }
        
        return num;
    }
    
    int convertLongToInt(const long &num) {
        if (num < INT_MIN) return INT_MIN;
        if (num > INT_MAX) return INT_MAX;     
        return (int)num;        
    }
};




JavaScript
// JavaScript
function myAtoi(str) {
  let index = 0;
  let sign = 1;
  let total = 0;
  // 1. Empty String
  if (str.length === 0) return 0;

  // 2. trim
  while (str[index] === " " && index < str.length) {
    index++;
  }

  // 3. get sign
  if (str[index] === "+" || str[index] === "-") {
    sign = str[index] === "+" ? 1 : -1;
    index++;
  }

  // 4. covert
  while (index < str.length) {
    let digit = str[index].codePointAt(0) - "0".codePointAt(0);
    if (digit < 0 || digit > 9) break;
    total = total * 10 + digit;
    index++;
  }

  if (sign * total > 2 ** 31 - 1) {
    return 2 ** 31 - 1;
  } else {
  }

  return Math.max(Math.min(sign * total, 2 ** 31 - 1), -(2 ** 31)
  
  字符串反转系列问题
  异位词系列(Anagram)问题
  高级字符串算法：
	1.动态规划和字符串的结合，最长子串，子序列，编辑距离
	2.字符串+DP问题
最长回文子串：
	1.嵌套两层循环，枚举i，j(起点和终点)，判断该子串是否是回文
	2.中间向两边扩张
	3.DP[i][j]
子序列问题，可以使用动态规划
	dp[i][j]代表T前i字符串可以由S前j字符串组成最多个数
	动态方程：
		1.当S[j]==T[i],dp[i][j]=dp[i-1][j-1]+dp[i][j-1]
		2.当S[j]!=T[i],dp[i][j]=dp[i][j-1]
字符串匹配算法：
	1.暴力法(brute force BF)
	2.Rabin-Karp算法
	3.KMP算法
	4.Boyer-Moore算法：https://www.ruanyifeng.com/blog/2013/05/boyer-moore_string_search_algorithm.html
	5.Sunday算法：https://blog.csdn.net/u012505432/article/details/52210975
	字符串暴力法代码示例：
		// Java
public static int forceSearch(String txt, String pat) {
    int M = txt.length();
    int N = pat.length();
    for (int i = 0; i <= M - N; i++) {
        int j;
        for (j = 0; j < N; j++) {
            if (txt.charAt(i + j) != pat.charAt(j))
                break;
        }
        if (j == N) {
            return i;
        }
        // 更加聪明？ 
        // 1. 预先判断 hash(txt.substring(i, M)) == hash(pat)
        // 2. KMP 
    }
    return -1;
}


C++
//C/C++
int forceSearch(string text, string pattern) {
    int len_txt = text.length();
    int len_pat = pattern.length();

    for (int i = 0; i <= len_txt - len_pat; i++) {
        int j = 0;
        for (j = 0; j < len_pat; j++) {
            if (text[i + j] != pattern[j]) break;
        }
        if (j == len_pat) {
            return i;
        }
    }
    return -1;
}


Python
# Python
def forceSearch(txt, pat):
  n, m = len(txt), len(pat)
  for i in range(n-m+1):
    for j in range(m):
      if txt[i+j] != pat[j]:
        break
    if j == m:
      return i
  return -1 




JavaScript
// Javascript
function bf(text, pattern) {
  let n = text.length;
  let m = pattern.length;
  for (let i = 0; i < n - m + 1; i++) {
    let matched = true;
    for (let j = 0; j < m; j++) {
      if (source[i + j] !== pattern[j]) {
        matched = false;
        break;
      }
    }
    if (matched) return true;
  }
  return false;
}

console.log(bf("abcabcabx", "abcabx"));

	Robin-Karp算法代码示例：
	//Java
public final static int D = 256;
public final static int Q = 9997;

static int RabinKarpSerach(String txt, String pat) {
    int M = pat.length();
    int N = txt.length();
    int i, j;
    int patHash = 0, txtHash = 0;

    for (i = 0; i < M; i++) {
        patHash = (D * patHash + pat.charAt(i)) % Q;
        txtHash = (D * txtHash + txt.charAt(i)) % Q;
    }

    int highestPow = 1;  // pow(256, M-1)
    for (i = 0; i < M - 1; i++) 
        highestPow = (highestPow * D) % Q;

    for (i = 0; i <= N - M; i++) { // 枚举起点
        if (patHash == txtHash) {
            for (j = 0; j < M; j++) {
                if (txt.charAt(i + j) != pat.charAt(j))
                    break;
            }
            if (j == M)
                return i;
        }
        if (i < N - M) {
            txtHash = (D * (txtHash - txt.charAt(i) * highestPow) + txt.charAt(i + M)) % Q;
            if (txtHash < 0)
                txtHash += Q;
        }
    }

    return -1;
}




C++
//C/C++
const int D = 256;
const int Q = 9997;

int RabinKarpSerach(string txt, string pat) {
    int M = pat.length();
    int N = txt.length();
    int i, j;
    int patHash = 0, txtHash = 0;

    for (i = 0; i < M; i++) {
        patHash = (D * patHash + pat[i]) % Q;
        txtHash = (D * txtHash + txt[i]) % Q;
    }
    int highestPow = 1;  // pow(256, M-1)
    for (i = 0; i < M - 1; i++) 
        highestPow = (highestPow * D) % Q;

    for (i = 0; i <= N - M; i++) { // 枚举起点
        if (patHash == txtHash) {
            for (j = 0; j < M; j++) {
                if (txt[i + j] != pat[j])
                    break;
            }
            if (j == M)
                return i;
        }
        if (i < N - M) {
            txtHash = (D * (txtHash - txt[i] * highestPow) + txt[i + M]) % Q;
            if (txtHash < 0)
                txtHash += Q;
        }
    }

    return -1;
}




JavaScript
// JavaScript
function rabinKarpSearch(text, pattern) {
  const D = 256;
  const Q = 9997;

  let N = text.length;
  let M = pattern.length;
  let patHash = 0;
  let txtHash = 0;

  for (let i = 0; i < M; i++) {
    patHash = (D * patHash + pattern[i].codePointAt(0)) % Q;
    txtHash = (D * txtHash + text[i].codePointAt(0)) % Q;
  }

  let highestPow = 1; // 256 ** (M - 1);
  for (let i = 0; i < M - 1; i++) {
    highestPow = (highestPow * D) % Q;
  }

  let i, j;
  for (i = 0; i < N - M + 1; i++) {
    if (patHash === txtHash) {
      for (j = 0; j < M; j++) {
        if (pattern[j] !== text[i + j]) break;
      }
      if (j === M) return i;
    }
    if (i < N - M) {
      txtHash = (D * (txtHash - text[i].codePointAt(0) * highestPow) + text[i + M].codePointAt(0)) % Q;
      if (txtHash < 0) {
        txtHash += Q;
      }
    }
  }
  return -1;
}

console.log(rabinKarpSearch("abcabcabx", "abcabx"));




Plain  Text
Python
class Solution:
    def strStr(self, haystack: str, needle: str) -> int:
        d = 256
        q = 9997
        n = len(haystack)
        m = len(needle)
        h = pow(d,m-1)%q
        p = 0
        t = 0
        if m > n:
            return -1
        for i in range(m): # preprocessing
            p = (d*p+ord(needle[i]))%q
            t = (d*t+ord(haystack[i]))%q
        for s in range(n-m+1): # note the +1
            if p == t: # check character by character
                match = True
                for i in range(m):
                    if needle[i] != haystack[s+i]:
                        match = False
                        break
                if match:
                    return s
            if s < n-m:
                t = (t-h*ord(haystack[s]))%q
                t = (t*d+ord(haystack[s+m]))%q
                t = (t+q)%q
        return -1
	KMP算法视频：https://www.bilibili.com/video/av11866460?from=search&seid=17425875345653862171
	KMP字符串匹配算法(阮一峰)：http://www.ruanyifeng.com/blog/2013/05/Knuth%E2%80%93Morris%E2%80%93Pratt_algorithm.html
	Rabin-Karp算法的思想：
		1.假设子串的长度为M(pat),目标字符串的长度为N(txt)
		2.计算子串的hash值hash_pat
		3.计算目标字符串txt中每个长度为M的子串的hash值(共需要计算N-M+1次)
		4.比较hash值：如果hash值不同，字符串必然不匹配，如果hash值相同，还需要使用朴素算法再次判断
	KMP算法(Knuth-Morris-Pratt)的算法思想就是，当子串与目标字符串不匹配时，其实你已经知道了签名已经匹配成功那一部分字符
	(包括子串与目标字符串)。以阮一峰的文章为例，当空格与D不匹配时，你其实知道签名六个字符是"ABCDAB"。KMP算法的想法是，
	设法利用这个已知信息，不要把搜索位置移回已经比较过的位置，继续把他向后移，这样就提高了效率。