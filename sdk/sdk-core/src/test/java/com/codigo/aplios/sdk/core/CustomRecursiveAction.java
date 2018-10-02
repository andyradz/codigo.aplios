package com.codigo.aplios.sdk.core;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.concurrent.ForkJoinPool;
import java.util.concurrent.ForkJoinTask;
import java.util.concurrent.RecursiveAction;
import java.util.logging.Logger;

public class CustomRecursiveAction
        extends RecursiveAction {

    public static void main(final String[] args) {

        final ForkJoinPool pool = new ForkJoinPool();
        final CustomRecursiveAction app = new CustomRecursiveAction(
                "asdsadiuye9127983xu198 983has99e rweop ruodsfweqjqw jeojw qqpiepqwuru wru902u4 923283u982 uye89ywe cwioojioruur89232340238 er 309ue09	eu	eu	");
        pool.invoke(app);
        System.out.println(app.workLoad);
    }

    private String workLoad = "";

    private static final int THRESHOLD = 4;

    private static Logger logger = Logger.getAnonymousLogger();

    public CustomRecursiveAction(final String workLoad) {

        this.workLoad = workLoad;
    }

    @Override
    protected void compute() {

        if (this.workLoad.length() > CustomRecursiveAction.THRESHOLD)
            ForkJoinTask.invokeAll(this.createSubtasks());
        else
            this.processing(this.workLoad);

    }

    private Collection<CustomRecursiveAction> createSubtasks() {

        final List<CustomRecursiveAction> subtasks = new ArrayList<>();

        final String partOne = this.workLoad.substring(0, this.workLoad.length() / 2);
        final String partTwo = this.workLoad.substring(this.workLoad.length() / 2, this.workLoad.length());

        subtasks.add(new CustomRecursiveAction(partOne));
        subtasks.add(new CustomRecursiveAction(partTwo));

        return subtasks;
    }

    private void processing(final String work) {

        final String result = work.toUpperCase();
        CustomRecursiveAction.logger.info("This result - (" +
                 result + ") - was processed by " + Thread.currentThread()
                        .getName());
    }

}
