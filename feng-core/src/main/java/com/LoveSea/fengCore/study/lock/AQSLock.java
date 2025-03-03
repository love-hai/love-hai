package com.LoveSea.fengCore.study.lock;

import java.util.concurrent.atomic.AtomicBoolean;
import java.util.concurrent.atomic.AtomicReference;
import java.util.concurrent.locks.LockSupport;

/**
 * @author xiahaifeng
 */

public class AQSLock {

    AtomicBoolean flag = new AtomicBoolean(false);
    Thread owner = null;
    AtomicReference<Node> head = new AtomicReference<>(new Node());
    AtomicReference<Node> tail = new AtomicReference<>(head.get());

    public void lock() {
        if (this.owner == Thread.currentThread()) {
            return;
        }
        Node current = new Node();
        current.thread = Thread.currentThread();
        while (true) {
            Node tailNode = tail.get();
            if (tail.compareAndSet(tailNode, current)) {
                tailNode.next = current;
                current.prev = tailNode;
                break;
            }
        }
        while (true) {
            if (current.prev == head.get() && flag.compareAndSet(false, true)) {
                owner = Thread.currentThread();
                head.set(current);
                current.prev.next = null;
                current.prev = null;
                System.out.println(owner.getName() + "获得锁");
                return;
            }
            LockSupport.park();
        }
    }

    public void unlock() {
        if (owner != Thread.currentThread()) {
            System.out.println(owner.getName() + "是锁的拥有者");
            throw new IllegalArgumentException(Thread.currentThread().getName() + "不是锁的拥有者");
        }
        Node headNode = head.get();
        Node next = headNode.next;
        this.owner = null;
        flag.set(false);
        if (next != null) {
            LockSupport.unpark(next.thread);
        }
    }

    static class Node {
        Node prev;
        Node next;
        Thread thread;
    }
}