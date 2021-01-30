package GUI;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.text.Font;

public class GameButton extends Button {

	private static final String BUTTON_PRESSED_STYLE = "-fx-background-color: transparent; -fx-background-image: url('/GUI/resources/buttonLong_grey_pressed.png');";
	private static final String BUTTON_FREE_STYLE = "-fx-background-color: transparent; -fx-background-image: url('/GUI/resources/buttonLong_grey.png');";

	public GameButton(String text) {
		setText(text);
		setButtonFont();
		setPrefWidth(190);
		setPrefHeight(49);
		setStyle(BUTTON_FREE_STYLE);
		initializeButtonListener();
	}

	private void setButtonFont() {
		setFont(Font.font("Verdana", 23));
	}

	private void setButtonPressedStyle() {
		setStyle(BUTTON_PRESSED_STYLE);
		setPrefHeight(45);
		setLayoutY(getLayoutY() + 4);
	}

	private void setButtonReleasedStyle() {
		setStyle(BUTTON_FREE_STYLE);
		setPrefHeight(49);
		setLayoutY(getLayoutY() - 4);
	}

	private void initializeButtonListener() {
		setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (event.getButton().equals(MouseButton.PRIMARY)) {
					setButtonPressedStyle();
				}
			}
		});

		setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				if (event.getButton().equals(MouseButton.PRIMARY)) {
					setButtonReleasedStyle();
				}
			}
		});

		setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent event) {
				setEffect(new DropShadow());

			}
		});

		setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				setEffect(null);
			}
		});

	}
}
