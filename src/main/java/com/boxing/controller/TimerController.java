package com.boxing.controller;

import javafx.animation.Animation;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.Spinner;
import javafx.scene.control.SpinnerValueFactory;
import javafx.util.Duration;

public class TimerController {
    @FXML private Spinner<Integer> roundsSpinner, workSpinner, restSpinner;
    @FXML private Label phaseLabel, timeLabel, roundLabel;

    private Timeline clock;
    private int round, seconds;
    private boolean resting;

    @FXML
    private void initialize() {
        roundsSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(1, 30, 3));
        workSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(10, 600, 180, 10));
        restSpinner.setValueFactory(new SpinnerValueFactory.IntegerSpinnerValueFactory(5, 300, 60, 5));
    }

    @FXML
    private void onStart() {
        if (clock != null) clock.stop();
        round = 1;
        seconds = workSpinner.getValue();
        resting = false;
        showFight();
        clock = new Timeline(new KeyFrame(Duration.seconds(1), e -> tick()));
        clock.setCycleCount(Animation.INDEFINITE);
        clock.play();
    }

    @FXML
    private void onStop() {
        if (clock != null) clock.stop();
        setPhase("ПАУЗА", null);
    }

    private void tick() {
        seconds--;
        if (seconds <= 0) {
            if (!resting) {
                if (round >= roundsSpinner.getValue()) {
                    clock.stop();
                    setPhase("ФИНИШ 🏆", "phase-finish");
                    timeLabel.setText("00:00");
                    return;
                }
                resting = true;
                seconds = restSpinner.getValue();
                setPhase("ОТДЫХ", "phase-rest");
            } else {
                resting = false;
                round++;
                seconds = workSpinner.getValue();
                showFight();
            }
            java.awt.Toolkit.getDefaultToolkit().beep();
        }
        timeLabel.setText(format(seconds));
    }

    private void showFight() {
        setPhase("БОЙ!", "phase-fight");
        roundLabel.setText("Раунд " + round + " из " + roundsSpinner.getValue());
        timeLabel.setText(format(seconds));
    }

    private void setPhase(String text, String cssClass) {
        phaseLabel.setText(text);
        phaseLabel.getStyleClass().removeAll("phase-fight", "phase-rest", "phase-finish");
        if (cssClass != null) phaseLabel.getStyleClass().add(cssClass);
    }

    private String format(int s) { return String.format("%02d:%02d", s / 60, s % 60); }
}



