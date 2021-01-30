package GUI;

import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.scene.control.TextField;
import javafx.geometry.Insets;
import javafx.scene.control.Label;

public class GameInput extends VBox {

	private TextField textField;

	public GameInput(String title, String promptText) {

		this.setPadding(new Insets(10));
		this.setSpacing(90);
		Label label = new Label(title);
		label.setFont(new Font(30));
		this.textField = new TextField();
		this.textField.setPromptText(promptText);
		this.textField.setPrefHeight(30);
		this.getChildren().addAll(label, textField);
		this.setLayoutX(200);
		this.setLayoutY(50);

	}

	public String getText() {
		return textField.getText().trim();
		// TODO Implements getText

	}

}
