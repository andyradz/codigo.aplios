package com.codigo.aplios.gui.control;

import java.util.Calendar;
import java.util.Date;
import javafx.animation.Interpolator;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * The StackPane which manages two {@link DatePane}, which are necessary for the
 * animation.
 *
 * @author Christian Schudt
 */
final class AnimatedStackPane
        extends StackPane {

    DatePane animatePane;

    DatePane actualPane;

    private final static Double SLIDE_ANIMATION_DURATION = 0.7;

    public AnimatedStackPane(final DatePane actualPane, final DatePane animatePane) {

        // The first MonthView.
        this.animatePane = animatePane;

        // Set it invisible as long as it is not needed.
        animatePane.setVisible(false);

        // The second MonthView.
        this.actualPane = actualPane;

        this.getChildren()
                .add(animatePane);
        this.getChildren()
                .add(actualPane);
        this.getStyleClass()
                .add("calendar-daypane");

        // Listen to changes of the calendar date, if it changes, check if the new date
        // has another month and move the panes accordingly.
        actualPane.calendarView.calendarDate.addListener((ChangeListener<Date>)(observableValue, oldDate, newDate) ->
        {

            final Calendar calendar = actualPane.calendarView.getCalendar();

            calendar.setTime(oldDate);
            final int oldYear = calendar.get(Calendar.YEAR);
            final int oldMonth = calendar.get(Calendar.MONTH);

            calendar.setTime(newDate);
            final int newYear = calendar.get(Calendar.YEAR);
            final int newMonth = calendar.get(Calendar.MONTH);

            // move the panes, if necessary.
            if ((AnimatedStackPane.this.getWidth() > 0) && (actualPane.calendarView.ongoingTransitions.get() == 0))
                if ((newYear > oldYear) || ((newYear == oldYear) && (newMonth > oldMonth)))
                    AnimatedStackPane.this.slideLeftRight(-1, oldDate);
                else if ((newYear < oldYear) || ((newYear == oldYear) && (newMonth < oldMonth)))
                    AnimatedStackPane.this.slideLeftRight(1, oldDate);
        });
    }

    private ParallelTransition slideTransition;

    /**
     * Slides the panes from left to right or vice versa.
     *
     * @param direction
     * The direction, either 1 (moves to right) or -1 (moves to left).
     * @param oldDate
     * The date, which the {@link #animatePane} gets set.
     */
    private void slideLeftRight(final int direction, final Date oldDate) {

        // Stop any previous animation.
        if (this.slideTransition != null)
            this.slideTransition.stop();

        final TranslateTransition transition1 = new TranslateTransition(
                Duration.seconds(AnimatedStackPane.SLIDE_ANIMATION_DURATION), this.animatePane);
        final TranslateTransition transition2 = new TranslateTransition(
                Duration.seconds(AnimatedStackPane.SLIDE_ANIMATION_DURATION), this.actualPane);

        // Set the animatePane to visible.
        this.animatePane.setVisible(true);

        // Set the offset depending on the direction.‚‚
        this.animatePane.setDate(oldDate);

        // Set the clip, so that the translate transition stays within the clip.
        // Use the bounds from one pane.
        this.setClip(new Rectangle(
                this.animatePane.getBoundsInLocal()
                        .getWidth(),
                this.animatePane.getBoundsInLocal()
                        .getHeight()));

        // Move the old pane away from 0. (I added 1px, so that both panes overlap,
        // which makes it look a little smoother).
        transition1.setFromX(-direction * 1);
        // and either to right or to left.
        transition1.setToX((this.getLayoutBounds()
                .getWidth() *
                direction) + (-direction * 1));

        // Move new pane from left or right
        transition2.setFromX(-this.getBoundsInParent()
                .getWidth() * direction);

        // Move the new pane to 0
        transition2.setToX(0);

        this.slideTransition = new ParallelTransition();
        this.slideTransition.getChildren()
                .addAll(transition1, transition2);
        this.slideTransition.setInterpolator(Interpolator.EASE_OUT);

        this.slideTransition.play();
        this.slideTransition.setOnFinished(actionEvent -> {
            // When we are finished, set the animate pane to invisible and remove the clip.
            AnimatedStackPane.this.animatePane.setVisible(false);
            // If the calendar gets resized, we don't have any clip.
            AnimatedStackPane.this.setClip(null);
        });
    }

}
