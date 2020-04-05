package com.xiaoqiang.leetcode.leetcode460;


import java.util.HashMap;
import java.util.Map;

/**
 * 460. LFU缓存
 * 设计并实现最不经常使用（LFU）缓存的数据结构。它应该支持以下操作：get 和 put。
 *
 * get(key) - 如果键存在于缓存中，则获取键的值（总是正数），否则返回 -1。
 * put(key, value) - 如果键不存在，请设置或插入值。当缓存达到其容量时，它应该在插入新项目之前，使最不经常使用的项目无效。在此问题中，当存在平局（即两个或更多个键具有相同使用频率）时，最近最少使用的键将被去除。
 *
 * 进阶：
 * 你是否可以在 O(1) 时间复杂度内执行两项操作？
 *
 * 示例：
 *
 * LFUCache cache = new LFUCache( 2  capacity (缓存容量) );
*/
//cache.put(1,1);
//cache.put(2,2);
//cache.get(1);       // 返回 1
//cache.put(3,3);    // 去除 key 2
//cache.get(2);       // 返回 -1 (未找到key 2)
//cache.get(3);       // 返回 3
//cache.put(4,4);    // 去除 key 1
//cache.get(1);       // 返回 -1 (未找到 key 1)
//cache.get(3);       // 返回 3
//cache.get(4);       // 返回 4


public class LFUCache {

    /**
     * 缓存容量大小
     */
    private  Integer capacity;

    /**
     * key 就是题目中的 key
     * value 是结点类
     */
    private Map<Integer, ListNode> map;

    /**
     * 访问次数哈希表，使用 ListNode[] 也可以，不过要占用很多空间
     */
    private Map<Integer, DoubleLinkedList> frequentMap;




    /**
     * 全局最高访问次数，删除最少使用访问次数的结点时会用到
     */
    private Integer maxFrequent = 1;


    public LFUCache(Integer capacity) {
        map = new HashMap<>(capacity);
        frequentMap = new HashMap<>();
        this.capacity = capacity;
    }

    /**
     *   get 一次操作，访问次数就增加 1；
     *   从原来的链表调整到访问次数更高的链表的表头
     * @param key
     * @return
     */
    public int get(int key) {
        if (capacity == 0){
            return -1;
        }
        if(map.containsKey(key)){
            ListNode node = removeListNode(key);

            int frequent = node.frequent;
            addListNodeToHead(frequent,node);
            return node.value;
        }else{
            return -1;
        }
    }

    public void put(int key, int value) {
        if(map.containsKey(key)){
            ListNode node = removeListNode(key);
            node.value = value;
            int frequent = node.frequent;
            addListNodeToHead(frequent,node);
            return;
        }
        // 如果 key 不存在
        // 1、如果满了，先删除访问次数最小的的末尾结点，再删除 map 里对应的 key
        if(map.size() == capacity){
            for (int i=0; i<= maxFrequent; i++){
                if(frequentMap.containsKey(i) && frequentMap.get(i).count > 0){
                    DoubleLinkedList doubleLinkedList = frequentMap.get(i);
                    ListNode removeNode = doubleLinkedList.removeTail();
                    map.remove(removeNode.key);
                    break;
                }
            }
        }
        ListNode newListNode = new ListNode(key,value);
        addListNodeToHead(1,newListNode);
        map.put(key,newListNode);
    }


    /**
     * 删除节点
     * @return
     */
    private ListNode removeListNode(int key){
        ListNode delNode = map.get(key);

        ListNode preNode = delNode.preNode;
        ListNode nextNode = delNode.nextNode;

        preNode.nextNode = nextNode;
        nextNode.preNode = preNode;

        delNode.preNode  = null;
        delNode.nextNode = null;

        frequentMap.get(delNode.frequent).count--;
        delNode.frequent++;

        maxFrequent = Math.max(maxFrequent,delNode.frequent);
        return  delNode;

    }


    private  void addListNodeToHead(int frequent, ListNode addNode){
        DoubleLinkedList doubleLinkedList ;
        if(frequentMap.containsKey(frequent)){
            doubleLinkedList = frequentMap.get(frequent);
        }else {
            doubleLinkedList = new DoubleLinkedList();
        }
        doubleLinkedList.addNodeToHead(addNode);
        frequentMap.put(frequent,doubleLinkedList);
    }


    /**
     * 结点类，是双向链表的组成部分
     */
    private class  ListNode{

        private int key;
        private  int value;
        private int frequent = 1;
        private ListNode preNode;
        private  ListNode nextNode;


        public  ListNode(int key, int value){
            this.key = key;
            this.value = value;
        }
    }


    private class DoubleLinkedList {
        private ListNode head; //虚拟头节点
        private ListNode tail;//虚拟尾节点

        private int count;

        public DoubleLinkedList() {
            this.head = new ListNode(-1, -1);
            this.tail = new ListNode(-1, -1);
            head.nextNode = tail;
            tail.preNode = head;
        }

        /**
         * 将结点 添加到双向列表开头
         */
        public void addNodeToHead(ListNode addNode) {
            ListNode oldNode = head.nextNode;
            head.nextNode = addNode;
            oldNode.preNode = addNode;

            addNode.preNode = head;
            addNode.nextNode = oldNode;
            count++;
        }

        /**
         * 删除末尾节点
         *
         * @return
         */
        public ListNode removeTail() {
            ListNode oldTail = tail.preNode;
            ListNode newTail = oldTail.preNode;
            newTail.nextNode = tail;
            tail.preNode = newTail;

            oldTail.preNode = null;
            oldTail.nextNode = null;
            count--;
            return oldTail;
        }

    }


    public static void main(String[] args) {
        LFUCache cache = new LFUCache(3);

        cache.put(1, 1);
        cache.put(2, 2);
        cache.put(3, 3);
        System.out.println(cache.map.keySet());

        cache.put(4, 4);
        System.out.println(cache.map.keySet());

        int res1 = cache.get(4);
        System.out.println(res1);

        int res2 = cache.get(3);
        System.out.println(res2);

        int res3 = cache.get(2);
        System.out.println(res3);

        int res4 = cache.get(1);
        System.out.println(res4);

        cache.put(5, 5);

        int res5 = cache.get(1);
        System.out.println(res5);

        int res6 = cache.get(2);
        System.out.println(res6);

        int res7 = cache.get(3);
        System.out.println(res7);

        int res8 = cache.get(4);
        System.out.println(res8);

        int res9 = cache.get(5);
        System.out.println(res9);
    }


}
