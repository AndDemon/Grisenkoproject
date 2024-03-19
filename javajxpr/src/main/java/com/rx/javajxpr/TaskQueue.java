package com.rx.javajxpr;

import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class TaskQueue {
    private final BlockingQueue<Runnable> queue = new LinkedBlockingQueue<>();
    private final Thread workerThread;

    public TaskQueue() {
        workerThread = new Thread(() -> {
            try {
                while (true) {
                    Runnable task = queue.take();
                    task.run();
                }
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });
        workerThread.start();
    }

    public void submitTask(Runnable task) {
        queue.add(task);
    }

    public void shutdown() {
        workerThread.interrupt();
    }
}
