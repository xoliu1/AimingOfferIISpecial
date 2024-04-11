package others;

import java.util.*;

/**
 * LCR 030. O(1) 时间插入、删除和获取随机元素
 * @return null
 * @author xoliu
 * @create 2024/04/07 14:25
 **/

public class RandomizedSet {
    HashMap<Integer, Integer> map;
    List<Integer> nums;
    //map: <数值，数组对应位置>
    //nums：<数值>
    /** Initialize your data structure here. */
    public RandomizedSet() {
        map = new HashMap<>();
        nums = new ArrayList<>();
    }

    /** Inserts a value to the set. Returns true if the set did not already contain the specified element. */
    public boolean insert(int val) {
        if (!map.containsKey(val)) return false;
        map.put(val, nums.size());
        nums.add(val);
        return true;
    }

    /** Removes a value from the set. Returns true if the set contained the specified element. */
    public boolean remove(int val) {
        if (!map.containsKey(val)) return false;
        int index = map.get(val);
        map.put(nums.get(nums.size() - 1), index);
        map.remove(val);
        nums.set(index, nums.get(nums.size() - 1));
        nums.remove(nums.size() - 1);
        return true;
    }

    /** Get a random element from the set. */
    public int getRandom() {
        int i = new Random().nextInt(nums.size());
        return nums.get(i);
    }
}

/**
 * Your RandomizedSet object will be instantiated and called as such:
 * RandomizedSet obj = new RandomizedSet();
 * boolean param_1 = obj.insert(val);
 * boolean param_2 = obj.remove(val);
 * int param_3 = obj.getRandom();
 */
