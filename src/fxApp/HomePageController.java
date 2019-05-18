package fxApp;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.util.Duration;
import logic.DataStorage;

public class HomePageController implements Initializable {
	private long initialTime;
	private String separator = ":";

	private long timeOffset = 0;
	Timeline fiveSecondsWonder;

	private long alottedTime = 30;

	private boolean lapButtonPressedOnce = false;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		timerLabel.setText("0:00");
	}

	@FXML
	private void startButtonPressed() {
		System.out.println("Start Pressed");
		timerLabel.setText("0:00");
		DataStorage.spacebarPressed();
		initialTime = System.currentTimeMillis();

		fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				long time = (System.currentTimeMillis() - initialTime) / 1000;
				int seconds = Math.round(time) % 60;
				separator = ":";
				if (seconds < 10) {

					separator = separator + 0;
				}

				timerLabel.setText((int) time / 60 + separator + seconds);

			}
		}));
		fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
		fiveSecondsWonder.play();
		// TimerTask task = new TimerTask() {
		// public void run() {

		//
		// }
		//
		// };
		// Timer timer = new Timer();
		// timer.scheduleAtFixedRate(task, 0, 1000l);

	}

	@FXML
	private void lapButtonPressed() {

		DataStorage.spacebarPressed();
		// Countdown timer
		if (!lapButtonPressedOnce) {
			lapButtonPressedOnce = true;
		} else {
			timeOffset += alottedTime;
		}

		System.out.println("Lap Pressed");

		initialTime = System.currentTimeMillis();

		fiveSecondsWonder = new Timeline(new KeyFrame(Duration.seconds(1), new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent event) {
				long time = timeOffset + (System.currentTimeMillis() - initialTime) / 1000;
				int seconds = Math.round(time) % 60;
				separator = ":";
				if (seconds < 10) {

					separator = separator + 0;
				}

				timerLabel.setText((int) time / 60 + separator + seconds);

			}
		}));
		fiveSecondsWonder.setCycleCount(Timeline.INDEFINITE);
		fiveSecondsWonder.play();
	}

	private void stopButtonPressed() {
		fiveSecondsWonder.stop();
	}
	
	@FXML
	private void startStopPressed() {
		if(startStopButton.getText().equals("Start")) {
			startStopButton.setText("Stop");
			startButtonPressed();
		} else {
			startStopButton.setText("Start");
			stopButtonPressed();
		}
	}

	@FXML
	private Label timerLabel;

	@FXML
	private Label elapsedTimeLabel;
	
	@FXML
	private Button startStopButton;

}
