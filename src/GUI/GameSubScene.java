package GUI;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.util.Duration;

public class GameSubScene extends SubScene {

	private static final String BACKGROUND_IMAGE = "panel_beigeLight.png";

	private boolean isHidden;

	public GameSubScene() {
		super(new AnchorPane(), 600, 400);
		prefWidth(600);
		prefHeight(400);

		BackgroundImage image = new BackgroundImage(
				new Image(ClassLoader.getSystemResource(BACKGROUND_IMAGE).toString(), 600, 400, false, true),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);

		AnchorPane root2 = (AnchorPane) this.getRoot();
		root2.setBackground(new Background(image));

		isHidden = true;

		setLayoutX(1204);
		setLayoutY(250);
	}

	public void moveSubScene() {
		TranslateTransition transition = new TranslateTransition();
		transition.setDuration(Duration.seconds(0.3));
		transition.setNode(this);

		if (isHidden) {
			transition.setToX(-1000);
			isHidden = false;
		} else {
			transition.setToX(0);
			isHidden = true;
		}
		transition.play();
	}

	public AnchorPane getPane() {
		return (AnchorPane) this.getRoot();
	}

}
