/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.codigo.aplios.gui.core.button;

import javafx.animation.FillTransition;
import javafx.animation.ParallelTransition;
import javafx.animation.TranslateTransition;
import javafx.application.Application;
import javafx.beans.binding.Bindings;
import javafx.beans.property.BooleanProperty;
import javafx.beans.property.SimpleBooleanProperty;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Font;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 *
 * @author andrzej.radziszewski
 */
public class SwitchButton
        extends Application {

    public static void main(final String[] args) {

        Application.launch(SwitchButton.class);
    }

    public static class ToogleButton
            extends Parent {

        private final BooleanProperty switchedOn;

        private final TranslateTransition translateAnimation;

        private final FillTransition fillAnimation;

        private final ParallelTransition animation;

        public ToogleButton() {

            this.translateAnimation = new TranslateTransition(Duration.seconds(0.25));
            this.fillAnimation = new FillTransition(Duration.seconds(0.25));
            this.animation = new ParallelTransition(this.translateAnimation, this.fillAnimation);

            this.switchedOn = new SimpleBooleanProperty();

            final Rectangle background = new Rectangle(100, 50);
            background.setArcHeight(50);
            background.setArcWidth(50);
            background.setFill(Color.WHITE);
            background.setStroke(Color.LIGHTGRAY);

            final Circle circle = new Circle(25);
            circle.setCenterX(25);
            circle.setCenterY(25);
            circle.setFill(Color.WHITE);
            circle.setStroke(Color.LIGHTGRAY);

            this.translateAnimation.setNode(circle);
            this.fillAnimation.setShape(background);

            this.getChildren()
                    .addAll(background, circle);

            this.switchedOn.addListener((obs, oldState, newState) -> {
                final boolean isOn = newState;
                this.translateAnimation.setToX(isOn ? 100 - 50 : 0);
                this.fillAnimation.setFromValue(isOn ? Color.WHITE : Color.LIGHTGREEN);
                this.fillAnimation.setToValue(isOn ? Color.LIGHTGREEN : Color.WHITE);
                this.animation.play();

            });

            this.setOnMouseClicked(event -> {
                this.switchedOn.set(!this.switchedOn.get());
            });
        }

        public BooleanProperty switchedOnProperty() {

            return this.switchedOn;
        }

    }

    public Parent createUI() {

        final Pane root = new Pane();
        root.setPrefSize(800, 600);

        final Rectangle bg = new Rectangle(800, 600);

        final ToogleButton toggleButton = new ToogleButton();
        toggleButton.setTranslateX(50);
        toggleButton.setTranslateY(50);

        final Text state = new Text();
        state.setFont(Font.font(18d));
        state.setFill(Color.WHITE);
        state.setTranslateX(100);
        state.setTranslateY(150);
        state.textProperty()
                .bind(Bindings.when(toggleButton.switchedOnProperty())
                        .then("ON")
                        .otherwise("OFF"));

        root.getChildren()
                .addAll(bg, toggleButton, state);

        return root;
    }

    // ----------------------------------------------------------------------------------------------------------------

    /*
     * (non-Javadoc)
     *
     * @see javafx.application.Application#start(javafx.stage.Stage)
     */
    @Override
    public void start(final Stage stage) throws Exception {

        stage.setScene(new Scene(this.createUI()));
        stage.show();
    }

}
