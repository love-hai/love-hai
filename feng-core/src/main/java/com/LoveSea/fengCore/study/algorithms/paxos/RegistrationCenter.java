package com.LoveSea.fengCore.study.algorithms.paxos;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.locks.ReentrantLock;

/**
 * @author xiahaifeng
 */

public class RegistrationCenter {
    List<Worker> workers = new ArrayList<>();
    ReentrantLock lock = new ReentrantLock();

    public void register(Worker worker) {
        lock.lock();
        try {
            workers.add(worker);
        } finally {
            lock.unlock();
        }
    }

    public Worker[] getWorkers() {
        lock.lock();
        try {
            return workers.toArray(new Worker[0]);
        } finally {
            lock.unlock();
        }
    }

}