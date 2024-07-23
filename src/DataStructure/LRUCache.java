package DataStructure;

import java.util.*;

public class LRUCache {

    class DLinkedNode{
        int key, value;
        DLinkedNode next, prev;

        public DLinkedNode(int key, int value) {
            this.key = key;
            this.value = value;
        }
        public DLinkedNode(){}
    }

    int size;
    int capacity;
    DLinkedNode head,tail;
    HashMap<Integer, DLinkedNode> cache = new HashMap<>();
    public LRUCache(int capacity) {
        size = 0;
        this.capacity = capacity;
        head = new DLinkedNode();
        tail = new DLinkedNode();
        head.next = tail;
        tail.prev = head;
    }

    public int get(int key) {
        DLinkedNode node = cache.get(key);
        if (node == null){
            return -1;
        }
        moveToHead(node);
        return node.value;
    }

    private void moveToHead(DLinkedNode node) {
        removeNode(node);
        addToHead(node);
    }

    private void addToHead(DLinkedNode node) {
        node.next = head.next;
        node.prev = head;
        head.next.prev = node;
        head.next = node;
    }

    private void removeNode(DLinkedNode node) {
        node.prev.next = node.next;
        node.next.prev = node.prev;
    }

    public void put(int key, int value) {
        DLinkedNode node = cache.get(key);
        if (node == null){
            //不存在，添加，并且判断超额？
            DLinkedNode p = new DLinkedNode(key, value);
            cache.put(key, p);
            addToHead(p);
            ++size;
            if (size > capacity){
                DLinkedNode last = tail.prev;
                removeNode(last);
                cache.remove(last.key);
                --size;
            }
        }else{
            //更新值和索引
            node.value = value;
            moveToHead(node);
        }
    }
}