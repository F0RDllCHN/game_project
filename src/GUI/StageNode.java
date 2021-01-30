package GUI;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.Glow;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

public class StageNode extends Button {

	private static final String LOCKED_STAGE_IMAGE = "-fx-background-color: transparent; -fx-background-image: url('/GUI/resources/red_circle.png')";
	private static final String UNLOCKED_STAGE_IMAGE = "-fx-background-color: transparent; -fx-background-image: url('/GUI/resources/green_circle.png')";
	private boolean Locked;

	public StageNode(String text, boolean locked, int X, int Y) {
		this.Locked = locked;
		setText(text);
		setPrefHeight(36);
		setPrefWidth(36);
		setLayoutX(X);
		setLayoutY(Y);
		setStageFont();
		if (Locked == true) {
			setStyle(LOCKED_STAGE_IMAGE);
		} else {
			setStyle(UNLOCKED_STAGE_IMAGE);
		}
		initializeStageListener();
	}

	private void setStageFont() {
		setFont(Font.font("Verdana", 13));
	}

	private void initializeStageListener() {

		setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (!Locked)
					setEffect(new Glow());

			}
		});

		setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				setEffect(null);
			}
		});

	}

	public void stageNodeUnlock() {
		this.Locked = false;
		setStyle(UNLOCKED_STAGE_IMAGE);
	}

	public boolean isLocked() {
		return this.Locked;
	}
}
