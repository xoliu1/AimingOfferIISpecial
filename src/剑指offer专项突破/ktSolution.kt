package 剑指offer专项突破

import DataStructure.TreeNode
import java.util.Stack

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