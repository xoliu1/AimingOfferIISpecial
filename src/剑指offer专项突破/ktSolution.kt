package 剑指offer专项突破

import DataStructure.TreeNode
import java.util.*
import kotlin.math.min


/**
 * 中序遍历模版
 * @param null
 * @return null
 * @author xoliu
 * @create 2024/05/05 下午4:50
 **/
fun inOrderTraversal(root: TreeNode?):List<Int> {
    var list = LinkedList<Int>()
    var stack = Stack<TreeNode>()
    var curr = root
    while (curr != null || stack.isNotEmpty()){
        while (curr != null){
            stack.push(curr)
            curr = curr.left
        }
        curr = stack.pop()
        list.add(curr.`val`)
        curr = curr.right
    }
    return list
}

/**
 * LCR 055. 二叉搜索树迭代器
 * @author xoliu
 * @create 2024/05/04 下午3:45
 **/
class BSTIterator(root: TreeNode?) {
    private var idx = 0
    private var list = mutableListOf<Int>()

    init {
        midTraversal(root)
    }

    private fun midTraversal(root: TreeNode?) {
        root ?: return//为空则返回
        midTraversal(root.left)
        list.add(root.`val`)
        midTraversal(root.right)
    }

    fun next() = list[idx++]

    fun hasNext() = list.size > idx

}

/**
 * LCR 056. 两数之和 IV - 输入二叉搜索树
 * @param null
 * @return null
 * @author xoliu
 * @create 2024/05/05 上午1:43
 **/
fun findTarget(root: TreeNode?, k: Int): Boolean {
    val stk = Stack<TreeNode>()
    val set = mutableSetOf<Int>()
    var cur = root
    while (cur != null || !stk.isEmpty()) {
        while (cur != null){
            stk.push(cur)
            cur = cur.left
        }
        //出来时cur为空
        cur = stk.pop()
        if (k - cur.`val` in set) return true
        set.add(cur.`val`)
        cur = cur.right
    }
    return false
}

/**
 * LCR 057. 存在重复元素 III
 * @param null
 * @return null
 * @author xoliu
 * @create 2024/05/05 下午4:53
 **/

fun containsNearbyAlmostDuplicate(nums: IntArray, k: Int, t: Int): Boolean {
    val treeSet = TreeSet<Long>()
    for ((index, num) in nums.withIndex()) {
        val ceiling = treeSet.ceiling(num.toLong())
        if (ceiling != null && ceiling - num <= t) return true

        val floor = treeSet.floor(num.toLong())
        if (floor != null && num - floor <= t) return true

        treeSet.add(num.toLong())

        // 保持滑动窗口
        if (index >= k) {
            treeSet.remove(nums[index - k].toLong())
        }
    }
    return false
}

/**
 * LCR 058. 我的日程安排表 I
 * @param null
 * @return null
 * @author xoliu
 * @create 2024/05/05 下午5:22
 **/
class MyCalendar() {
    //左开右闭[start, end)
    private val events = TreeMap<Int, Int>()
    fun book(start: Int, end: Int): Boolean {
        var entry = events.floorEntry(start)
        //找开始时间比较，前面的和后面的紧跟的的事件
        //这俩事件如果都不和该事件冲突，则不冲突
        if (entry != null && entry.value > start) return false

        entry = events.ceilingEntry(start)
        if (entry != null && entry.key < end) return false

        events.put(start, end)
        return true
    }
}

/**
 * LCR 059. 数据流中的第 K 大元素
 * @param null
 * @return null
 * @author xoliu
 * @create 2024/05/07 上午1:15
 **/
class KthLargest(k: Int, nums: IntArray) {
    private val minHeap = PriorityQueue<Int>()
    private val kth: Int = k

    init {
        for (num in nums) {
            add(num)
        }
    }

    fun add(`val`: Int): Int {
        minHeap.offer(`val`)
        if (minHeap.size > kth) {
            minHeap.poll()
        }
        return minHeap.peek()
    }
}

/**
 * LCR 061. 查找和最小的 K 对数字
 * @param null
 * @return null
 * @author xoliu
 * @create 2024/05/07 上午1:29
 **/
fun kSmallestPairs(nums1: IntArray, nums2: IntArray, k: Int): List<List<Int>> {
    val n = nums1.size
    val m = nums2.size
    val ans = ArrayList<List<Int>>(k)
    val pq = PriorityQueue<IntArray> { a, b -> a[0] - b[0] }

    for (i in 0 until minOf(n, k)) {
        pq.offer(intArrayOf(nums1[i] + nums2[0], i, 0))
    }

    while (pq.isNotEmpty() && ans.size < k) {
        val p = pq.poll()
        val i = p[1]
        val j = p[2]
        ans.add(listOf(nums1[i], nums2[j]))
        if (j + 1 < m) {
            pq.offer(intArrayOf(nums1[i] + nums2[j + 1], i, j + 1))
        }
    }

    return ans
}





















