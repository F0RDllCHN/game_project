package GUI;

import javafx.scene.control.Label;
import javafx.scene.text.Font;

public class InfoLabel extends Label {

	public InfoLabel(String text, int X, int Y) {
		setLayoutX(X);
		setLayoutY(Y);
		setText(text);
		setWrapText(true);
		setLabelFont();
	}

	private void setLabelFont() {
		setFont(Font.font("Verdana", 23));
	}

}
