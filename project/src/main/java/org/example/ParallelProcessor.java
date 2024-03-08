package org.example;

import java.util.List;
import java.util.concurrent.*;
import java.util.stream.Collectors;
public class ParallelProcessor {
    private final ExecutorService executorService;

    public ParallelProcessor(int parallelism) {
        this.executorService = Executors.newFixedThreadPool(parallelism);
    }

    public void processTasks(List<Runnable> tasks) {
        List<Callable<Void>> callableTasks = tasks.stream()
                .map(task -> (Callable<Void>) () -> {
                    task.run();
                    return null;
                })
                .collect(Collectors.toList());

        try {
            executorService.invokeAll(callableTasks);
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            executorService.shutdown();
        }
    }

    public void awaitTermination() {
        try {
            executorService.awaitTermination(Long.MAX_VALUE, TimeUnit.NANOSECONDS);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
