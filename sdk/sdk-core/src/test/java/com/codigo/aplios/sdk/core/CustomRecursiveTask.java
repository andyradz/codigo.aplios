package com.codigo.aplios.sdk.core;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveTask;

public class CustomRecursiveTask
        extends RecursiveTask<Integer> {

    public static void main(final String[] args) {

        final CustomRecursiveTask fibonacciCal = new CustomRecursiveTask(
                new int[]{1, 4, 2, 3, 2, 3, 12, 23, 12, 21, 12, 12, 22, 12, 23, 12, 23, 12, 2, 2, 13,});
        final ForkJoinPool pool = new ForkJoinPool();
        final int i = pool.invoke(fibonacciCal);
        System.out.println(i);
    }

    private static final long serialVersionUID = -6322072991843588219L;

    private static final int THRESHOLD = 20;

    private final int[] arr;

    public CustomRecursiveTask(final int[] arr) {

        this.arr = arr;
    }

    @Override
    protected Integer compute() {

        if (this.arr.length > CustomRecursiveTask.THRESHOLD)
            return ForkJoinTask.invokeAll(this.createSubtasks())
                    .stream()
                    .mapToInt(ForkJoinTask::join)
                    .sum();
        else
            return this.processing(this.arr);

    }

    private Collection<CustomRecursiveTask> createSubtasks() {

        final List<CustomRecursiveTask> dividedTasks = new ArrayList<>();

        dividedTasks.add(new CustomRecursiveTask(Arrays.copyOfRange(this.arr, 0, this.arr.length / 2)));
        dividedTasks.add(new CustomRecursiveTask(Arrays.copyOfRange(this.arr, this.arr.length / 2, this.arr.length)));

        return dividedTasks;
    }

    private Integer processing(final int[] arr) {

        return Arrays.stream(arr)
                .filter(a -> (a > 10) && (a < 27))
                .map(a -> a * 10)
                .sum();
    }

}
