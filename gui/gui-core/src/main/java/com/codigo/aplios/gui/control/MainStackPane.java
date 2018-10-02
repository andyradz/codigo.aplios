package com.codigo.aplios.gui.control;

import java.util.Calendar;
import javafx.animation.FadeTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.beans.value.ChangeListener;
import javafx.scene.layout.StackPane;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

/**
 * This is the main stack pane, which holds the three views ({@link MonthView},
 * {@link YearView}, {@link DecadesView}. It is responsible for showing and
 * hiding them.
 *
 * @author Christian Schudt
 */
final class MainStackPane
        extends StackPane {

    private final AnimatedStackPane monthView;

    private final AnimatedStackPane yearView;

    private final AnimatedStackPane decadesView;

    private final CalendarView calendarView;

    public MainStackPane(final CalendarView calendarView) {

        this.calendarView = calendarView;
        this.monthView = new AnimatedStackPane(new MonthView(calendarView), new MonthView(calendarView));
        this.yearView = new AnimatedStackPane(new YearView(calendarView), new YearView(calendarView));
        this.decadesView = new AnimatedStackPane(new DecadesView(calendarView), new DecadesView(calendarView));

        this.getChildren()
                .addAll(this.monthView, this.yearView, this.decadesView);

        calendarView.title.bind(this.monthView.actualPane.titleProperty());

        this.yearView.setVisible(false);
        this.decadesView.setVisible(false);

        // When the view changes.
        calendarView.currentlyViewing.addListener((ChangeListener<Number>)(observableValue, oldNumber, newNumber) -> {

            calendarView.title.unbind();
            switch (oldNumber.intValue()) {
                case Calendar.MONTH:
                    switch (newNumber.intValue()) {
                        // Switch from month to year.
                        case Calendar.YEAR:
                            calendarView.title.bind(MainStackPane.this.yearView.actualPane.titleProperty());
                            MainStackPane.this.showOrHide(MainStackPane.this.yearView, true);
                            break;
                    }
                    break;
                case Calendar.YEAR:
                    switch (newNumber.intValue()) {
                        // Switch from year to month.
                        case Calendar.MONTH:
                            MainStackPane.this.showOrHide(MainStackPane.this.yearView, false);
                            MainStackPane.this.monthView.requestFocus();
                            calendarView.title.bind(MainStackPane.this.monthView.actualPane.titleProperty());
                            break;
                        // Switch from decades to month.
                        case Calendar.ERA:
                            MainStackPane.this.showOrHide(MainStackPane.this.decadesView, true);
                            calendarView.title.bind(MainStackPane.this.decadesView.actualPane.titleProperty());
                            break;
                    }
                    break;
                case Calendar.ERA:
                    switch (newNumber.intValue()) {
                        // Switch from decades to year.
                        case Calendar.YEAR:
                            MainStackPane.this.showOrHide(MainStackPane.this.decadesView, false);
                            calendarView.title.bind(MainStackPane.this.yearView.actualPane.titleProperty());
                            break;

                    }
                    break;
            }
        });
    }

    /**
     * Shows or hides the pane with an animation.
     *
     * @param stackPane
     * The StackPane, which is shown or hidden.
     * @param show
     * True, when shown, false when hidden.
     */
    private void showOrHide(final AnimatedStackPane stackPane, final boolean show) {

        stackPane.setVisible(true);

        this.calendarView.ongoingTransitions.set(this.calendarView.ongoingTransitions.get() + 1);
        final TranslateTransition translateTransition = new TranslateTransition(Duration.seconds(0.5), stackPane);
        final FadeTransition fadeTransition = new FadeTransition(Duration.seconds(0.5), stackPane);

        this.setClip(new Rectangle(
                stackPane.getBoundsInLocal()
                        .getWidth(),
                stackPane.getBoundsInLocal()
                        .getHeight()));

        if (show) {
            translateTransition.setFromY(-this.getBoundsInLocal()
                    .getHeight());
            translateTransition.setToY(0);
            fadeTransition.setToValue(1);
            fadeTransition.setFromValue(0);

        } else {
            translateTransition.setToY(-this.getBoundsInLocal()
                    .getHeight());
            translateTransition.setFromY(0);
            fadeTransition.setToValue(0);
            fadeTransition.setFromValue(1);
        }

        final ParallelTransition parallelTransition = new ParallelTransition();
        parallelTransition.getChildren()
                .add(translateTransition);
        parallelTransition.getChildren()
                .add(fadeTransition);
        parallelTransition.play();
        parallelTransition.setOnFinished(actionEvent -> {

            if (!show) {
                MainStackPane.this.calendarView.mainNavigationPane.titleButton.requestFocus();
                stackPane.setVisible(false);
            }
            MainStackPane.this.calendarView.ongoingTransitions
                    .set(MainStackPane.this.calendarView.ongoingTransitions.get() - 1);
            if (MainStackPane.this.calendarView.ongoingTransitions.get() == 0)
                MainStackPane.this.setClip(null);
        });
    }

}
