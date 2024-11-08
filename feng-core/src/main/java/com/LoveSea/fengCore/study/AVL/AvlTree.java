package com.LoveSea.fengCore.study.AVL;

import lombok.extern.slf4j.Slf4j;

/**
 * @author xiahaifeng
 */
@Slf4j
public class AvlTree {

    int capacity = 8;
    float DEFAULT_LOAD_FACTOR = 0.25f;
    // 扩容倍数
    int DEFAULT_EXPAND_FACTOR = 2;
    // 写入的长度
    int size = 0;

    public static void main(String[] args) {
        AvlTree avlTree = new AvlTree();
        avlTree.add(1);
        avlTree.add(6);
        avlTree.add(7);
        avlTree.add(8);
        avlTree.add(9);
        avlTree.add(18);
        avlTree.add(0);
        avlTree.remove(7);
        log.info("end");
    }

    private AvlTreeNode[] avlTreeNodes = new AvlTreeNode[capacity];

    void add(int data) {
        try{
            AvlTreeNode avlTreeNode = new AvlTreeNode();
            avlTreeNode.setData(data);
            add(avlTreeNode);
        }catch (Exception e){
            log.error("添加失败 data:{}",data,e);
        }
    }

    void add(AvlTreeNode addNode) {
        resize();
        int index = 0;
        AvlTreeNode avlTreeNode = avlTreeNodes[index];
        while (null != avlTreeNode) {
            if (addNode.getData() < avlTreeNode.getData()) {
                index = leftChild(index);
            } else if (addNode.getData() > avlTreeNode.getData()) {
                index= rightChild(index);
            }
            if(index>=capacity){
                break;
            }
            avlTreeNode = avlTreeNodes[index];
        }
        avlTreeNodes[index] = addNode;
        size++;
        addBalance(index);
    }

    AvlTreeNode find(int data) {
        int index = 0;
        AvlTreeNode avlTreeNode = avlTreeNodes[index];
        while (null != avlTreeNode) {
            if (data < avlTreeNode.getData()) {
                index = leftChild(index);
                avlTreeNode = avlTreeNodes[index];
            } else if (data > avlTreeNode.getData()) {
                index = rightChild(index);
                avlTreeNode = avlTreeNodes[index];
            } else {
                return avlTreeNode;
            }
        }
        return null;
    }

    AvlTreeNode remove(int data) {
        int index = 0;
        AvlTreeNode avlTreeNode = avlTreeNodes[index];
        while (null != avlTreeNode) {
            if (data < avlTreeNode.getData()) {
                index = leftChild(index);
                avlTreeNode = avlTreeNodes[index];
            } else if (data > avlTreeNode.getData()) {
                index = rightChild(index);
                avlTreeNode = avlTreeNodes[index];
            } else {
                break;
            }
        }
        if (null == avlTreeNode) {
            return null;
        }
        return removeIndex(index);
    }

    AvlTreeNode removeIndex(int index) {
        if (avlTreeNodes[index] == null) {
            throw new IllegalArgumentException("index:" + index + " element is null");
        }
        AvlTreeNode avlTreeNode = avlTreeNodes[index];
        avlTreeNodes[index] = null;
        size--;
        if (null != avlTreeNodes[leftChild(index)] && null != avlTreeNodes[rightChild(index)]) {
            int minIndex = findMinNode(rightChild(index));
            avlTreeNodes[index] = removeIndex(minIndex);
            size++;
        } else if (null != avlTreeNodes[leftChild(index)]) {
            move(leftChild(index), index);
            addBalance(index);
        } else if (null != avlTreeNodes[rightChild(index)]) {
            move(rightChild(index), index);
            addBalance(index);
        }
        addBalance(index);
        return avlTreeNode;
    }

    int findMinNode(int index) {
        while (null != avlTreeNodes[leftChild(index)])
            index = leftChild(index);
        return index;
    }


    boolean isLeaf(int index) {
        if (leftChild(index) >= capacity && rightChild(index) >= capacity) {
            return true;
        }
        return null == avlTreeNodes[leftChild(index)] && null == avlTreeNodes[rightChild(index)];
    }

    // 平衡二叉树
    void addBalance(int index) {
        if (index == 0)
            return;
        if (index >= capacity)
            return;
        int parentIndex = parent(index);
        int balanceType = balanceType(parentIndex);
        switch (balanceType) {
            case LL -> rightRotate(leftChild(parentIndex));
            case RR -> leftRotate(rightChild(parentIndex));
            case LR -> {
                leftRotate(rightChild(leftChild(parentIndex)));
                rightRotate(leftChild(parentIndex));
            }
            case RL -> {
                rightRotate(leftChild(rightChild(parentIndex)));
                leftRotate(rightChild(parentIndex));
            }
            default -> {
            }
        }
        addBalance(parent(index));
    }

    void rightRotate(int index) {
        if (index == 0)
            throw new IllegalArgumentException("pivot can not be 0");
        int parentIndex = parent(index);
        // 将父节点变成左节点
        int pl = leftChild(parentIndex);
        int plr = rightChild(pl);
        int pll = leftChild(pl);
        int pr = rightChild(parentIndex);
        int prr = rightChild(pr);
        int prl = leftChild(pr);
        move(pr, prr);
        avlTreeNodes[pr] = avlTreeNodes[parentIndex];
        avlTreeNodes[parentIndex] = avlTreeNodes[index];
        move(plr, prl);
        move(pll, pl);
    }

    void leftRotate(int index) {
        if (index == 0)
            throw new IllegalArgumentException("pivot can not be 0");
        int parentIndex = parent(index);
        // 将父节点变成右节点
        int pl = leftChild(parentIndex);
        int plr = rightChild(pl);
        int pll = leftChild(pl);
        int pr = rightChild(parentIndex);
        int prr = rightChild(pr);
        int prl = leftChild(pr);
        move(pl, pll);
        avlTreeNodes[pl] = avlTreeNodes[parentIndex];
        avlTreeNodes[parentIndex] = avlTreeNodes[index];
        move(prl, plr);
        move(prr, pr);
    }

    void move(int oldIndex, int newIndex) {
        if (oldIndex == newIndex)
            return;
        if (oldIndex < newIndex) {
            if (newIndex >= capacity)
                return;
            if (avlTreeNodes[oldIndex] == null)
                return;
            move(rightChild(oldIndex), rightChild(newIndex));
            move(leftChild(oldIndex), leftChild(newIndex));
            avlTreeNodes[newIndex] = avlTreeNodes[oldIndex];
            avlTreeNodes[oldIndex] = null;
        }
        if (oldIndex > newIndex) {
            if (oldIndex >= capacity)
                return;
            if (avlTreeNodes[oldIndex] == null)
                return;
            avlTreeNodes[newIndex] = avlTreeNodes[oldIndex];
            avlTreeNodes[oldIndex] = null;
            move(rightChild(oldIndex), rightChild(newIndex));
            move(leftChild(oldIndex), leftChild(newIndex));
        }
    }

    // 一样高
    final int SH = 0;
    // LL 形式
    final int LL = 1;
    // RR 形式
    final int RR = 2;
    // LR 形式
    final int LR = 3;
    // RL 形式
    final int RL = 4;


    int balanceType(int index) {
        int leftHeight = getLeftHeight(index);
        int rightHeight = getRightHeight(index);
        if (leftHeight - rightHeight > 1) {
            int leftLeftHeight = getLeftHeight(leftChild(index));
            int leftRightHeight = getRightHeight(leftChild(index));
            if (leftLeftHeight > leftRightHeight)
                return LL;
            else
                return LR;
        }
        if (rightHeight - leftHeight > 1) {
            int rightLeftHeight = getLeftHeight(rightChild(index));
            int rightRightHeight = getRightHeight(rightChild(index));
            if (rightLeftHeight > rightRightHeight)
                return RL;
            else
                return RR;
        }
        return SH;
    }

    int getLeftHeight(int index) {
        int leftIndex = leftChild(index);
        if (leftIndex >= capacity)
            return 0;
        AvlTreeNode avlTreeNode = avlTreeNodes[leftIndex];
        if (null == avlTreeNode)
            return 0;
        return Math.max(getLeftHeight(leftIndex), getRightHeight(leftIndex)) + 1;
    }

    int getRightHeight(int index) {
        int rightIndex = rightChild(index);
        if (rightIndex >= capacity)
            return 0;
        AvlTreeNode avlTreeNode = avlTreeNodes[rightIndex];
        if (null == avlTreeNode)
            return 0;
        return Math.max(getLeftHeight(rightIndex), getRightHeight(rightIndex)) + 1;
    }

    void resize() {
        if (size < capacity * DEFAULT_LOAD_FACTOR) {
            return;
        }
        AvlTreeNode[] newAvlTreeNodes = new AvlTreeNode[capacity * DEFAULT_EXPAND_FACTOR];
        System.arraycopy(avlTreeNodes, 0, newAvlTreeNodes, 0, capacity);
        capacity *= 2;
        avlTreeNodes = newAvlTreeNodes;
    }


    private int leftChild(int i) {
        return 2 * i + 1;
    }

    private int rightChild(int i) {
        return 2 * i + 2;
    }

    private int parent(int i) {
        if (i == 0)
            throw new IllegalArgumentException("root node can not be 0");
        return (i - 1) / 2;
    }


}