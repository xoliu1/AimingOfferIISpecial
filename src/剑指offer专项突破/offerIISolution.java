package 剑指offer专项突破;


import DataStructure.ListNode;
import DataStructure.Node;
import DataStructure.TreeNode;

import java.util.*;


class offer {
    /**
     * 剑指 Offer II 001. 整数除法
     *
     * @param dividend
     * @param divisor
     * @return int
     * @author xoliu
     * @create 2023/10/16 16:19
     **/


    public int divide(int dividend, int divisor) {
        if (dividend == 0) {
            return 0;
        }
        if (dividend == Integer.MIN_VALUE && divisor == -1) {
            return Integer.MAX_VALUE;
        }
        boolean negative = (dividend ^ divisor) < 0;
        long t = Math.abs((long) dividend);
        long d = Math.abs((long) divisor);
        int res = 0;
        for (int i = 31; i >= 0; --i) {
            if ((t >> i) >= d) {
                res += 1 << i;  //结果加上2的i次方
                t -= d << i;
            }
        }
        return negative ? -res : res;
    }


    /**
     * 剑指 Offer II 002. 二进制加法
     *
     * @param a
     * @param b
     * @return java.lang.String
     * @author xoliu
     * @create 2023/10/16 16:19
     **/

    public String addBinary(String a, String b) {
        StringBuilder ans = new StringBuilder();
        int i = a.length() - 1, j = b.length() - 1;
        int carry = 0;
        while (i >= 0 || j >= 0) {
            int numa = i >= 0 ? a.charAt(i--) - '0' : 0;
            int numb = j >= 0 ? b.charAt(j--) - '0' : 0;
            int sum = numa + numb + carry;
            carry = sum >= 2 ? 1 : 0;
            sum = sum >= 2 ? sum - 2 : sum;
            ans.append(sum);
        }
        if (carry == 1) {
            ans.append(1);
        }
        return ans.reverse().toString();
    }

    /**
     * 剑指 Offer II 003. 前 n 个数字二进制中 1 的个数
     *
     * @param n
     * @return int[]
     * @author xoliu
     * @create 2023/10/16 16:19
     **/

    public int[] countBits(int n) {
        int[] ans = new int[n + 1];
        for (int i = 1; i <= n; ++i) {
            int j = i;
            while (j != 0) {
                ++ans[i];
                j &= j - 1;
            }
        }
        return ans;
    }

    /**
     * 剑指 Offer II 004. 只出现一次的数字
     *
     * @param nums
     * @return int
     * @author xoliu
     * @create 2023/10/16 15:22
     **/

    public int singleNumber(int[] nums) {
        int[] digit = new int[32];
        for (int i = 0; i < nums.length; ++i) {
            for (int j = 0; j < 32; ++j) {
                digit[i] += (nums[i] >> (31 - i) & 1);
            }
        }
        int ans = 0;
        for (int i = 0; i < digit.length; ++i) {
            ans = ans << 1 + digit[i] % 3;
        }
        return ans;
    }

    /**
     * 剑指 Offer II 005. 单词长度的最大乘积
     *
     * @param words
     * @return int
     * @author xoliu
     * @create 2023/10/16 15:22
     **/

    public int maxProduct(String[] words) {
        boolean[][] hashmap = new boolean[words.length][26];
        for (int i = 0; i < words.length; ++i) {
            for (char ch : words[i].toCharArray()) {
                hashmap[i][ch - 'a'] = true;    //没有出现的就是false
            }
        }
        int maxProduct = 0;
        for (int i = 0; i < words.length - 1; ++i) {
            for (int j = i + 1; j < words.length; ++j) {
                int idx = 0;
                while (idx < 26) {
                    if (hashmap[i][idx] && hashmap[j][idx]) {
                        break;  //一旦遇到同时true即同时出现的就break;
                    }
                    ++idx;
                }
                if (idx == 26) {
                    maxProduct = Math.max(words[i].length() * words[j].length(), maxProduct);
                }
            }
        }
        return maxProduct;
    }


    /**
     * 剑指 Offer II 006. 排序数组中两个数字之和
     *
     * @param numbers
     * @param target
     * @return int[]
     * @author xoliu
     * @create 2023/10/16 15:22
     **/

    public int[] twoSum(int[] numbers, int target) {
        int i = 0, j = numbers.length - 1;
        while (i < j) {
            if (numbers[i] + numbers[j] == target) {
                return new int[]{numbers[i], numbers[j]};
            } else if (numbers[i] + numbers[j] >= target) {
                --j;
            } else {
                ++i;
            }
        }
        return null;
    }

    /**
     * 剑指 Offer II 007. 数组中和为 0 的三个数
     *
     * @param nums
     * @return java.util.List<java.util.List < java.lang.Integer>>
     * @author xoliu
     * @create 2023/10/16 15:22
     **/

    //twosum的进化，且要进行去重处理
    public List<List<Integer>> threeSum(int[] nums) {
        LinkedList<List<Integer>> ans = new LinkedList<>();
        if (nums.length >= 3) {
            Arrays.sort(nums);
            int i = 0;
            while (i < nums.length - 2) {
                TwoSum(ans, nums, i);
                int tmp = nums[i];
                while (nums[i] == tmp && i < nums.length - 2) {
                    ++i;
                }
            }
        }
        return ans;
    }

    public void TwoSum(List<List<Integer>> ans, int[] nums, int idx) {
        int j = idx + 1, k = nums.length - 1;
        while (j < k) {
            if (nums[idx] + nums[j] + nums[k] == 0) {
                ans.add(Arrays.asList(nums[idx], nums[j], nums[k]));//还可以这么用！涨姿势！
                //进行去重，防止重复，让j往后走
                int tmp = nums[j];
                while (j < k && nums[j] == tmp) {
                    ++j;
                }
            } else if (nums[idx] + nums[j] + nums[k] <= 0) {
                ++j;
            } else {
                --k;
            }
        }
    }

    /**
     * 剑指 Offer II 008. 和大于等于 target 的最短子数组
     *
     * @param target
     * @param nums
     * @return int
     * @author xoliu
     * @create 2023/10/16 15:22
     **/

    public int minSubArrayLen(int target, int[] nums) {
        int i = 0, sum = 0, length = 0;
        for (int j = 0; j < nums.length; j++) {
            sum += nums[j];
            while (sum >= target) {
                if (length == 0) {
                    length = j - i + 1;
                } else {
                    length = Math.min(length, j - i + 1);
                }
                sum -= nums[i];
                ++i;
            }
        }
        return length;
    }

    /**
     * 剑指 Offer II 009. 乘积小于 K 的子数组
     *
     * @param a
     * @param k
     * @return int
     * @author xoliu
     * @create 2023/10/16 15:22
     **/

    public int numSubarrayProductLessThanK(int[] a, int k) {
        if (k == 1 || k == 0) {
            return 0;
        }
        int i = 0, j = 0, sum = 1;
        int ans = 0;
        while (j < a.length) {
            sum *= a[j];
            while (sum >= k) {
                sum /= a[i];
                ++i;
            }
            ans += j - i + 1;
            ++j;
        }
        return ans;
    }

    /**
     * 剑指 Offer II 010. 和为 k 的子数组
     *
     * @param nums
     * @param k
     * @return int
     * @author xoliu
     * @create 2023/10/16 15:21
     **/

    public int subarraySum(int[] nums, int k) {
        int sum = 0, cnt = 0;
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 1);
        //如果 sum == k ,即 containKey(0),也需要算上。
        for (int i = 0; i < nums.length; ++i) {
            sum += nums[i];
            if (map.containsKey(sum - k)) {
                cnt += map.get(sum - k);
            }
            map.put(sum, map.getOrDefault(sum, 0) + 1);
        }
        return cnt;
    }

    /**
     * 剑指 Offer II 011. 0 和 1 个数相同的子数组
     *
     * @param nums
     * @return int
     * @author xoliu
     * @create 2023/10/16 15:21
     **/

    public int findMaxLength(int[] nums) {
        for (int i = 0; i < nums.length; ++i) {
            if (nums[i] == 0) {
                --nums[i];
            }
        }
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, -1);
        int sum = 0;
        int ans = 0;
        for (int i = 0; i < nums.length; ++i) {
            sum += nums[i];
            if (map.containsKey(sum)) {
                ans = Math.max(ans, i - map.get(sum));
            } else {
                map.put(sum, i);
            }
        }
        return ans;

    }

    /**
     * 剑指 Offer II 012. 左右两边子数组的和相等
     *
     * @param nums
     * @return int
     * @author xoliu
     * @create 2023/10/16 15:21
     **/

    public int pivotIndex(int[] nums) {
        int sum = 0;
        for (int i = 0; i < nums.length; ++i) {
            sum += nums[i];
        }
        int tmp = 0;
        for (int i = 0; i < nums.length; ++i) {
            if (tmp == sum - nums[i] - tmp) {
                return i;
            }
            tmp += nums[i];
        }
        return -1;
    }

    /**
     * 剑指 Offer II 013. 二维子矩阵的和
     *
     * @param null
     * @author xoliu
     * @return null
     * @create 2023/10/16 15:21
     **/

    class NumMatrix {
        int[][] prefix;

        public NumMatrix(int[][] matrix) {
            if (matrix.length != 0) {
                int n = matrix[0].length, m = matrix.length;
                prefix = new int[m + 1][n + 1];
                for (int i = 1; i <= m; ++i) {
                    for (int j = 1; j <= n; ++j) {
                        prefix[i][j] = prefix[i - 1][j] + prefix[i][j - 1] - prefix[i - 1][j - 1] + matrix[i][j];
                    }
                }
            }
        }

        public int sumRegion(int x1, int y1, int x2, int y2) {
            return prefix[x2][y2] - prefix[x1 - 1][y2] - prefix[x2][y1 - 1] + prefix[x1 - 1][y1 - 1];
        }
    }


    /**
     * 剑指 Offer II 014. 字符串中的变位词
     *
     * @param s1
     * @param s2
     * @return boolean
     * @author xoliu
     * @create 2023/10/16 15:21
     **/

    public boolean checkInclusion(String s1, String s2) {
        int n1 = s1.length(), n2 = s2.length();
        if (n1 > n2) {
            return false;
        }
        int[] cnt = new int[26];
        for (int i = 0; i < n1; ++i) {
            --cnt[s1.charAt(i) - 'a'];
            ++cnt[s2.charAt(i) - 'a'];
        }//初始化cnt
        int dif = 0;//定义变化
        for (int i : cnt) {
            if (i != 0) {
                ++dif;
            }
        }
        if (dif == 0) {
            return true;
        }
        for (int i = n1; i < n2; ++i) {
            int x = s2.charAt(i - n1) - 'a';
            int y = s2.charAt(i) - 'a';
            if (x == y) {
                //两个字符相同，就不用操作
                continue;
            }
            //接下来是两个字符不相同的情况
            if (cnt[x] == 0) {
                ++dif;
                //如果在操作之前，要出去的字符之前为0，现在就多了一个difference
            }
            --cnt[x];
            //如果操作之后，为0，即少了一个difference
            if (cnt[x] == 0) {
                --dif;
            }
            /**对进入的y同理。*/
            if (cnt[y] == 0) {
                ++dif;
            }
            ++cnt[y];
            if (cnt[y] == 0) {
                --dif;
            }
            if (dif == 0) {
                return true;
            }
        }
        return false;
    }

    /**
     * 剑指 Offer II 016. 不含重复字符的最长子字符串
     *
     * @param s
     * @return int
     * @author xoliu
     * @create 2023/10/16 15:21
     **/

    public int lengthOfLongestSubstring(String s) {
        HashSet<Character> set = new HashSet<>();
        if (s.length() <= 1) {
            return s.length();
        }
        int maxLen = 1;
        int i = 0, j = 0;
        while (j < s.length()) {
            while (set.contains(s.charAt(j))) {
                set.remove(s.charAt(i));
                ++i;
            }
            set.add(s.charAt(j));
            maxLen = Math.max(maxLen, j - i + 1);
            ++j;
        }
        return maxLen;
    }

    /**
     * 剑指 Offer II 018. 有效的回文
     *
     * @param s
     * @return boolean
     * @author xoliu
     * @create 2023/10/16 15:21
     **/

    public boolean isPalindrome(String s) {
        s = s.toLowerCase();
        int i = 0, j = s.length() - 1;
        while (i < j) {
            while (i < j && Character.isLetterOrDigit(s.charAt(i))) {
                ++i;
            }
            while (i < j && Character.isLetterOrDigit(s.charAt(j))) {
                --j;
            }
            if (s.charAt(i) != s.charAt(j)) {
                return false;
            }
            ++i;
            --j;
        }
        return i == j;
    }

    /**
     * 剑指 Offer II 019. 最多删除一个字符得到回文
     *
     * @param s
     * @return boolean
     * @author xoliu
     * @create 2023/10/16 15:19
     **/

    public boolean validPalindrome(String s) {
        int left = 0, right = s.length() - 1;
        for (; left < s.length() / 2; ++left, --right) {
            if (s.charAt(left) != s.charAt(right)) {
                break;
            }
        }
        if (left == s.length() / 2) {
            return true;
        } else {
            return ispalindrome(s, left + 1, right) || ispalindrome(s, left, right - 1);
        }
    }

    public boolean ispalindrome(String s, int left, int right) {
        while (left < right) {
            if (s.charAt(left) != s.charAt(right)) {
                break;
            }
            ++left;
            --right;
        }
        return left >= right;

    }

    /**
     * 剑指 Offer II 020. 回文子字符串的个数
     *
     * @param s
     * @return int
     * @author xoliu
     * @create 2023/10/16 15:19
     **/

    public int countSubstrings(String s) {
        int n = s.length();
        boolean[][] dp = new boolean[n][n];
        //用于判断(i, j)是否为回文字符串
        int res = 0;
        for (int i = n - 1; i >= 0; --i) {
            for (int j = i; j < n; ++j) {
                if (s.charAt(i) == s.charAt(j) && (i == j || i == j - 1 || dp[i + 1][j - 1])) {
                    dp[i][j] = true;
                    ++res;
                }
            }
        }
        return res;
    }


    /**
     * LCR 021. 删除链表的倒数第 N 个结点
     *
     * @param head
     * @param n
     * @return ListNode
     * @author xoliu
     * @create 2023/10/16 15:17
     **/
    public ListNode removeNthFromEnd(ListNode head, int n) {
        ListNode dummy = new ListNode(0);
        dummy.next = head;
        ListNode front = head;
        ListNode back = dummy;
        for (int i = 0; i < n; ++i) {
            front = front.next;
        }
        while (front != null) {
            front = front.next;
            back = back.next;
        }
        back.next = back.next.next;
        return dummy.next;
    }

    /**
     * LCR 023. 相交链表
     *
     * @param headA
     * @param headB
     * @return DataStructure.ListNode
     * @author xoliu
     * @create 2023/10/17 11:26
     **/

    public ListNode getIntersectionNode(ListNode headA, ListNode headB) {
        if (headA == null || headB == null) {
            return null;
        }
        ListNode nodeA = headA, nodeB = headB;
        while (nodeA != nodeB) {
            nodeA = nodeA == null ? headB : nodeA.next;
            nodeB = nodeB == null ? headA : nodeB.next;
        }
        return nodeA;
    }

    /**
     * LCR 024. 反转链表
     *
     * @param head
     * @return DataStructure.ListNode
     * @author xoliu
     * @create 2023/10/17 11:32
     **/

    public ListNode reverseList(ListNode head) {
        if (head == null || head.next == null) {
            return head;
        }
        ListNode newNode = reverseList(head.next);
        head.next.next = head;
        head.next = null;
        return newNode;
    }

    /**
     * LCR 025. 两数相加 II
     *
     * @param l1
     * @param l2
     * @return DataStructure.ListNode
     * @author xoliu
     * @create 2023/10/17 11:38
     **/

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode n1 = reverseList(l1);
        ListNode n2 = reverseList(l2);
        ListNode res = new ListNode(0);
        ListNode head = res;
        int carry = 0;
        int temp = 0;
        while (n1 != null || n2 != null) {
            int sum = carry;
            if (n1 != null) {
                sum += n1.val;
                n1 = n1.next;
            }
            if (n2 != null) {
                sum += n2.val;
                n2 = n2.next;
            }

            ListNode newNode = new ListNode(sum % 10);
            carry = sum / 10;
            res.next = newNode;
            res = res.next;
            if (n1 == null && n2 == null) {
                temp = sum;
            }
        }
        if (temp >= 10) {
            temp /= 10;
            while (temp != 0) {
                res.next = new ListNode(temp % 10);
                res = res.next;
                temp /= 10;
            }
        }

        return reverseList(head.next);
    }

    /**
     * LCR 026. 重排链表
     *
     * @param head
     * @return void
     * @author xoliu
     * @create 2023/10/18 12:18
     **/
    public void reorderList(ListNode head) {
        ListNode slow = head, fast = slow;
        while (fast != null && fast.next != null) {
            slow = slow.next;
            fast = fast.next.next;
        }
        ListNode mid = reverseList(slow);
        ListNode p = head;
        while (p != null && p.next != mid) {
            ListNode temp1 = p.next;
            ListNode temp2 = mid.next;
            p.next = mid;
            mid.next = temp1;
            p = temp1;
            mid = temp2;
        }
    }


    /**
     * LCR 027. 回文链表
     *
     * @param head
     * @return boolean
     * @author xoliu
     * @create 2024/04/06 15:53
     **/
    public boolean isPalindrome(ListNode head) {
        if (head == null) return false;
        Deque<ListNode> stack = new ArrayDeque<>();
        ListNode cur = head;
        while (cur != null) {
            stack.push(cur);
            cur = cur.next;
        }
        while (head != null) {
            if (head.val != stack.pop().val) {
                return false;
            }
            head = head.next;
        }
        return true;

    }


    /**
     * LCR 028. 扁平化多级双向链表
     *
     * @param head
     * @return DataStructure.Node
     * @author xoliu
     * @create 2023/10/19 12:23
     **/
    public Node flatten(Node head) {
        flatHelper(head);
        return head;
    }

    public Node flatHelper(Node head) {
        Node node = head;//移动指针
        Node tempEnd = null;
        while (node != null) {
            Node next = node.next;//用于连接
            if (node.child != null) {
                Node childNode = node.child;
                Node childEnd = flatHelper(node.child);
                node.child = null;//置空
                node.next = childNode;
                childNode.prev = node;
                childEnd.next = next;
                if (next != null) {
                    next.prev = childEnd;
                }
                tempEnd = childEnd;
            } else {
                //没有子节点，直接更新tempEnd
                tempEnd = node;
            }
            node = next;
        }
        //返回值用来更新子节点的尾结点，所以返回tempEnd
        return tempEnd;
    }

    /**
     * LCR 029. 循环有序列表的插入
     *
     * @param head
     * @param insertVal
     * @return DataStructure.Node
     * @author xoliu
     * @create 2024/04/06 16:13
     **/
    public Node insert(Node head, int insertVal) {
        if (head == null) {
            Node node = new Node(insertVal);
            node.next = node;
            return node;
        }
        Node max = head, min = head;
        Node p = head.next;
        while (p != head) {
            if (p.val > max.val) max = p;
            if (p.val < min.val) min = p;
            p = p.next;
        }
        if (max == min) {
            max.next = new Node(insertVal, max.next);
        } else if (insertVal >= max.val || insertVal <= min.val) {
            while (max.next.val == max.val) max = max.next;
            max.next = new Node(insertVal, max.next);
        } else {
            while (min.next.val < insertVal) min = min.next;
            min.next = new Node(insertVal, min.next);
        }
        return head;
    }


    /**
     * LCR 033. 字母异位词分组
     *
     * @param strs
     * @return List<List < String>>
     * @author xoliu
     * @create 2023/11/16 22:29
     **/
    public List<List<String>> groupAnagrams(String[] strs) {
        Map<String, List<String>> map = new HashMap<String, List<String>>();
        for (String str : strs) {
            char[] array = str.toCharArray();
            Arrays.sort(array);
            String key = new String(array);
            List<String> list = map.getOrDefault(key, new ArrayList<String>());
            list.add(str);
            map.put(key, list);
        }
        return new ArrayList<List<String>>(map.values());
    }


    /**
     * LCR 034. 验证外星语词典
     *
     * @param words
     * @param order
     * @return boolean
     * @author xoliu
     * @create 2024/04/11 下午5:50
     **/
    public boolean isAlienSorted(String[] words, String order) {
        int[] tong = new int[order.length()];
        for (int i = 0; i < order.length(); ++i) {
            tong[order.charAt(i) - 'a'] = i;
        }
        for (int i = 0; i < words.length - 1; ++i) {
            if (!cmpTo(words[i], words[i + 1], tong)) {
                return false;
            }
        }
        return true;
    }

    private boolean cmpTo(String a, String b, int[] tong) {
        int i = 0;
        for (; i < a.length() && i < b.length(); ++i) {
            char ch1 = a.charAt(i);
            char ch2 = b.charAt(i);
            if (ch1 != ch2) {
                if (tong[ch1 - 'a'] > tong[ch2 - 'a']) {
                    return false;
                }
                if (tong[ch1 - 'a'] < tong[ch2 - 'a']) {
                    return true;
                }
            }
        }
        return i == a.length();
    }


    /**
     * LCR 035. 最小时间差
     *
     * @param timePoints
     * @return int
     * @author xoliu
     * @create 2024/04/11 下午6:09
     **/
    public int findMinDifference(List<String> timePoints) {
        if (timePoints.size() > 24 * 60) {
            return 0;
        }
        List<Integer> mins = new ArrayList<>();
        for (String t : timePoints) {
            String[] time = t.split(":");
            mins.add(Integer.parseInt(time[0]) * 60 + Integer.parseInt(time[1]));
        }
        Collections.sort(mins);
        mins.add(mins.get(0) + 24 * 60);
        int res = 24 * 60;
        for (int i = 1; i < mins.size(); ++i) {
            res = Math.min(res, mins.get(i) - mins.get(i - 1));
        }
        return res;
    }

    /**
     * LCR 036. 逆波兰表达式求值
     *
     * @param tokens
     * @return int
     * @author xoliu
     * @create 2024/04/26 下午11:44
     **/

    public int evalRPN(String[] tokens) {
        ArrayDeque<Integer> stk = new ArrayDeque<>();
        for (String token : tokens) {
            switch (token) {
                case "+":
                case "-":
                case "*":
                case "/":
                    int a = stk.pop();
                    int b = stk.pop();
                    stk.push(cal(b, a, token));
                    break;
                default:
                    //遇到数字放进去
                    stk.push(Integer.parseInt(token));
            }
        }
        return stk.pop();
    }

    private int cal(int a, int b, String token) {
        switch (token){
            case "+": return a + b;
            case "-": return a - b;
            case "*": return a * b;
            case "/": return a / b;
            default: return 0;
        }
    }

    /**
     * LCR 037. 行星碰撞
     *
     * @param asteroids
     * @return int[]
     * @author xoliu
     * @create 2024/04/11 下午8:34
     **/
    public int[] asteroidCollision(int[] asteroids) {
        Deque<Integer> stack = new ArrayDeque<>();
        for (int i : asteroids) {
            if (stack.isEmpty()) {
                stack.offerFirst(i);
                continue;
            }
            boolean bool = true;
            while (!stack.isEmpty() && bool) {
                int a = stack.peekFirst();
                if (a * i > 0) {
                    break;
                }
                if (a < 0 && i > 0) {
                    break;
                }
                if (a == -i && a > 0) {
                    stack.pollFirst();
                    bool = false;
                    break;
                } else if (a > -i) {
                    bool = false;
                } else {
                    stack.pollFirst();
                }
            }
            if (bool) {
                stack.offerFirst(i);
            }
        }
        int[] res = new int[stack.size()];
        int idx = -1;
        while (!stack.isEmpty()) {
            res[++idx] = stack.pollLast();
        }
        return res;
    }

    /**
     * LCR 039. 柱状图中最大的矩形
     *
     * @param heights
     * @return int
     * @author xoliu
     * @create 2023/11/09 18:13
     **/
    public int largestRectangleArea(int[] heights) {
        int n = heights.length;
        int[] tmp = new int[n + 2];
        System.arraycopy(heights, 0, tmp, 1, n);
        LinkedList<Integer> queue = new LinkedList<>();
        int res = 0;
        for (int i = 0; i < n + 2; ++i) {
            while (!queue.isEmpty() && tmp[queue.peekFirst()] > tmp[i]) {
                Integer idx = queue.pollFirst();
                res = Math.max(res, (i - queue.peekFirst() - 1) * tmp[idx]);
            }
            queue.offerFirst(i);
        }
        return res;
    }

    /**
     * LCR 040. 最大矩形
     * @param matrix
     * @return int
     * @author xoliu
     * @create 2024/04/27 上午12:06
     **/
    public int maximalRectangle(String[] matrix) {
        if (matrix == null || matrix.length == 0) {return 0;}
        int res = 0;
        int[] heights = new int[matrix[0].length()];//表示以当前行为底，最大的直方图矩形
        for (String row : matrix) {
            for (int i = 0; i < row.length(); ++i) {
                char ch = row.charAt(i);
                if (ch == '0') {
                    heights[i] = 0;
                    //为0则断
                }else{
                    ++heights[i];
                }
            }
            res = Math.max(res, largestRectangleArea(heights));
        }
        return res;
    }

    /**
     * LCR 041. 数据流中的移动平均值
     * @param null
     * @return null
     * @author xoliu
     * @create 2024/04/28 上午12:21
     **/


    class MovingAverage {
        int len;
        double sum = 0;
        LinkedList<Integer> list;

        public MovingAverage(int size) {
            list = new LinkedList<>();
            len = size;
        }

        public double next(int val) {
            list.add(val);
            sum += val;
            while(list.size() > len){
                sum -= list.removeFirst();
            }
            return sum / list.size();
        }
    }

    /**
     * LCR 042. 最近的请求次数
     * @param null
     * @return null
     * @author xoliu
     * @create 2024/04/28 下午10:55
     **/
    class RecentCounter {

        private Queue<Integer> queue;
        public RecentCounter() {
            queue = new LinkedList<>();
        }

        public int ping(int t) {
            queue.offer(t);
            while (queue.peek() + 3000 < t){
                queue.poll();
            }
            return queue.size();
        }
    }

    /**
     * LCR 043. 完全二叉树插入器
     * @param null
     * @return null
     * @author xoliu
     * @create 2024/04/28 上午12:34
     **/
    class CBTInserter {
        //在完全二叉树中添加节点时，需要按照广度优先搜索的顺序找出第1个缺少子节点的节点。

        private Queue<TreeNode> nodeQueue;
        private TreeNode root;
        public CBTInserter(TreeNode root) {
            this.root = root;
            nodeQueue = new LinkedList<>();
            nodeQueue.offer(root);
            //广度搜索留下缺少子节点的节点
            while (nodeQueue.peek().left != null && nodeQueue.peek().right != null) {
                TreeNode pollNode = nodeQueue.poll();
                nodeQueue.offer(pollNode.left);
                nodeQueue.offer(pollNode.right);
            }
        }

        public int insert(int v) {
            TreeNode node = new TreeNode(v);
            //第一个缺少子节点的节点
            TreeNode parent = nodeQueue.peek();
            if (parent.left == null) {
                parent.left = node;
            } else{
                nodeQueue.poll();
                parent.right = node;
                nodeQueue.offer(parent.left);
                nodeQueue.offer(parent.right);
            }
            return parent.val;
        }

        public TreeNode get_root() {
            return root;
        }
    }

    /**
     * LCR 044. 在每个树行中找最大值
     *
     * @param root
     * @return java.util.List<java.lang.Integer>
     * @author xoliu
     * @create 2023/11/06 15:30
     **/
    public List<Integer> largestValues(TreeNode root) {
        LinkedList<Integer> res = new LinkedList<>();
        if (root == null) {
            return res;
        }
        Queue<TreeNode> que = new LinkedList<>();
        que.offer(root);
        while (!que.isEmpty()) {
            int mx = Integer.MIN_VALUE;
            int size = que.size();
            while (size-- > 0) {
                TreeNode p = que.poll();
                mx = Math.max(mx, p.val);
                if (p.left != null) {
                    que.offer(p.left);
                }
                if (p.right != null) {
                    que.offer(p.right);
                }
            }
            res.add(mx);
        }
        return res;
    }

    /**
     * LCR 045. 找树左下角的值
     * @param root
     * @return int
     * @author xoliu
     * @create 2024/04/28 下午11:07
     **/
    public int findBottomLeftValue(TreeNode root) {
//        int res = root.val;
//        LinkedList<TreeNode> queue1 = new LinkedList<>();
//        LinkedList<TreeNode> queue2 = new LinkedList<>();
//        queue1.offer(root);
//        while (!queue1.isEmpty()) {
//            TreeNode node = queue1.poll();
//            if (node.left != null) {
//                queue2.offer(node.left);
//            }
//            if (node.right != null) {
//                queue2.offer(node.right);
//            }
//            if (queue1.isEmpty()) {
//                queue1 = queue2;
//                queue2 = new LinkedList<>();
//                if (!queue1.isEmpty()) res = queue1.peek().val;
//            }
//        }
//        return res;

        /**
         *使用广度优先搜索遍历每一层的节点。在遍历一个节点时，需要先把它的非空右子节点放入队列，
         * 然后再把它的非空左子节点放入队列，这样才能保证从右到左遍历每一层的节点。
         * 广度优先搜索所遍历的最后一个节点的值就是最底层最左边节点的值。
         *
         **/
        int res = root.val;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            TreeNode p = queue.poll();
            if (p.right != null) {
                queue.offer(p.right);
            }
            if (p.left != null) {
                queue.offer(p.left);
            }
            res = p.val;
        }
        return res;
    }

    /**
     * LCR 046. 二叉树的右视图
     *
     * @param root
     * @return List<Int>
     * @author xoliu
     * @create 2023/10/27 23:44
     **/
    public List<Integer> rightSideView(TreeNode root) {
        List<Integer> ans = new ArrayList<>();
        if (root == null) {
            return ans;
        }
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {
            int size = queue.size();
            for (int i = 0; i < size; ++i) {
                TreeNode p = queue.poll();
                if (p.left != null) {
                    queue.offer(p.left);
                }
                if (p.right != null) {
                    queue.offer(p.right);
                }
                if (i == size - 1) {
                    ans.add(p.val);
                }
            }
        }
        return ans;
    }

    /**
     * LCR 047. 二叉树剪枝
     * @param root
     * @return DataStructure.TreeNode
     * @author xoliu
     * @create 2024/04/30 上午12:14
     **/
    public TreeNode pruneTree(TreeNode root) {
        if (root == null) return root;

        //后序遍历
        root.left = pruneTree(root.left);
        root.right = pruneTree(root.right);
        if (root.left == null && root.right == null && root.val == 0){
            return null;
        }
        return root;
    }


    /**
     * LCR 048. 二叉树的序列化与反序列化
     * @param null
     * @return null
     * @author xoliu
     * @create 2024/04/30 上午12:44
     **/
    public class Codec {

        /** 前序遍历 要记录空节点，否则无法对应唯一性 */
        // Encodes a tree to a single string.
        public String serialize(TreeNode root) {
            if (root == null) return "#";//表示空
            String left = serialize(root.left);
            String right = serialize(root.right);
            return String.valueOf(root.val) + "," + left + "," + right;
        }

        // Decodes your encoded data to tree.
        public TreeNode deserialize(String data) {
            String[] nodes = data.split(",");
            int[] i = {0};
            return dfs2(nodes, i);
        }
        //如果函数dfs的第2个参数i是整数类型，那么即使在函数体内修改i的值，修改之后的值也不能传递给它的调用者。
        // 但把i定义为整数数组之后，可以修改整数数组中的数字，修改之后的数值就能传给它的调用者

        private TreeNode dfs2(String[] nodes, int[] i) {
            String s = nodes[i[0]];
            ++i[0];
            if (s.equals("#")) {
                return null;
            }
            TreeNode node = new TreeNode(Integer.parseInt(s));
            node.left = dfs2(nodes, i);
            node.right = dfs2(nodes, i);
            return node;
        }
    }

    /**
     * LCR 049. 求根节点到叶节点数字之和
     * @param root
     * @return int
     * @author xoliu
     * @create 2024/05/02 下午11:59
     **/
    public int sumNumbers(TreeNode root) {
        return sumNumberHelper(root, 0);
    }

    private int sumNumberHelper(TreeNode node, int res) {
        if (node == null) return 0;
        res = res * 10 + node.val;
        if (node.left == null && node.right == null) {return res;}
        return sumNumberHelper(node.left, res) + sumNumberHelper(node.right, res);
    }



    /**
     * LCR 050. 路径总和 III
     *
     * @param root
     * @param targetSum
     * @return int
     * @author xoliu
     * @create 2023/10/27 23:45
     **/
    private int pathNum1;

    public int pathSum(TreeNode root, int targetSum) {
        if (root == null) {
            return 0;
        }
        getSum(root, targetSum, 0);
        pathSum(root.left, targetSum);
        pathSum(root.right, targetSum);
        return pathNum1;
    }

    public void getSum(TreeNode root, int targetSum, long sum) {
        if (root == null) {
            return;
        }
        sum += root.val;
        if (sum == targetSum) {
            ++pathNum1;
        }
        getSum(root.left, targetSum, sum);
        getSum(root.right, targetSum, sum);
        sum -= root.val;
    }



    /**
     * LCR 051. 二叉树中的最大路径和
     *
     * @param root
     * @return int
     * @author xoliu
     * @create 2023/10/27 23:46
     **/
    int mx = Integer.MIN_VALUE;
    public int maxPathSum(TreeNode root) {
        dfs1(root);
        return mx;
    }

    private int dfs1(TreeNode root) {
        if (root == null) {
            return 0;
        }
        int left = Math.max(0, dfs1(root.left));
        int right = Math.max(0, dfs1(root.right));
        mx = Math.max(mx, root.val + left + right);
        return root.val + Math.max(left, right);
    }

    
    /**
     * LCR 052. 递增顺序搜索树
     * @param root
     * @return DataStructure.TreeNode
     * @author xoliu
     * @create 2024/05/03 上午12:13
     **/
    TreeNode head1 = null, pre1 = null;
    public TreeNode increasingBST(TreeNode root) {
        increasingBSTDfs(root);
        return head1;
    }

    private void increasingBSTDfs(TreeNode root) {
        if (root == null) {return;}
        increasingBSTDfs(root.left);
        if (pre1 != null) pre1.right = root;
        else head1 = root;
        pre1 = root;
        increasingBSTDfs(root.right);
        root.left = null;
    }

    /**
     * LCR 053. 二叉搜索树中的中序后继
     * @param root
     * @param p
     * @return DataStructure.TreeNode
     * @author xoliu
     * @create 2024/05/04 上午12:12
     **/
    public TreeNode inorderSuccessor(TreeNode root, TreeNode p) {
        int aimNum = p.val;
        TreeNode res = null;
        while (root != null){
            if (root.val > aimNum){
                res = root;
                root = root.left;
            }else {
                root = root.right;
            }
        }
        return res;
    }


    /**
     * LCR 054. 把二叉搜索树转换为累加树
     * @param root
     * @return DataStructure.TreeNode
     * @author xoliu
     * @create 2024/05/04 上午12:16
     **/
    int pre = 0;
    public TreeNode convertBST(TreeNode root) {
        if(root == null){
            return root;
        }
        MidTravel(root);
        return root;
    }
    public void MidTravel(TreeNode root){
        if (root == null){
            return;
        }
        MidTravel(root.right);
        pre += root.val;
        root.val = pre;
        MidTravel(root.left);
    }

    /**
     * LCR 62 实现 Trie (前缀树)
     *
     * @param null
     * @author xoliu
     * @return null
     * @create 2023/10/23 12:47
     **/

    class Trie {
        TrieNode root;

        class TrieNode {
            char val;
            boolean isEnd = false;
            TrieNode[] children;

            public TrieNode() {
                this.children = new TrieNode[26];
            }

            public TrieNode(char val) {
                this.val = val;
                this.children = new TrieNode[26];
            }
        }

        public Trie() {
            root = new TrieNode();
        }

        public void insert(String word) {
            TrieNode p = root;
            for (int i = 0; i < word.length(); ++i) {
                char ch = word.charAt(i);
                if (p.children[ch - 'a'] == null) {
                    p.children[ch - 'a'] = new TrieNode(ch);
                }
                p = p.children[ch - 'a'];
                if (i == word.length() - 1) {
                    p.isEnd = true;
                }
            }
        }

        public boolean search(String word) {
            TrieNode p = root;
            for (int i = 0; i < word.length(); ++i) {
                char ch = word.charAt(i);
                if (p.children[ch - 'a'] == null) {
                    return false;
                }
                p = p.children[ch - 'a'];
            }
            return p.isEnd;
        }

        public boolean startsWith(String prefix) {
            TrieNode p = root;
            for (int i = 0; i < prefix.length(); ++i) {
                char ch = prefix.charAt(i);
                if (p.children[ch - 'a'] == null) {
                    return false;
                }
                p = p.children[ch - 'a'];
            }
            return true;
        }
    }

    /**
     * LCR 074. 合并区间
     *
     * @param intervals
     * @return int[][]
     * @author xoliu
     * @create 2023/11/02 0:17
     **/
    public int[][] merge(int[][] intervals) {
        if (intervals.length == 0) {
            return new int[0][2];
        }
        Arrays.sort(intervals, new Comparator<int[]>() {
            @Override
            public int compare(int[] o1, int[] o2) {
                return o1[0] - o2[0];
            }
        });//按照左端点排序
        List<int[]> ans = new ArrayList<>();
        ans.add(intervals[0]);
        for (int i = 1; i < intervals.length; ++i) {
            int l = intervals[i][0], r = intervals[i][1];
            if (ans.get(ans.size() - 1)[1] < l) {
                ans.add(intervals[i]);
            } else {
                ans.get(ans.size() - 1)[1] = Math.max(ans.get(ans.size() - 1)[1], r);
            }
        }
        return ans.toArray(new int[ans.size()][2]);
    }


    /**
     * LCR 090. 打家劫舍 II
     *
     * @param nums
     * @return int
     * @author xoliu
     * @create 2023/11/16 0:20
     **/
    public int rob2(int[] nums) {
        if (nums.length == 1) return nums[0];
        return Math.max(
                rob2Helper(Arrays.copyOfRange(nums, 0, nums.length - 1)),
                rob2Helper(Arrays.copyOfRange(nums, 1, nums.length)));
    }

    public int rob2Helper(int[] nums) {
        int n = nums.length;
        int[] dp = new int[n + 1];
        dp[1] = nums[0];
        for (int i = 2; i <= n; i++)
            dp[i] = Math.max(dp[i - 2] + nums[i - 1], dp[i - 1]);
        return dp[n];
    }

}

