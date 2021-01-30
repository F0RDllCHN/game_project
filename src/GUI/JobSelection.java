package GUI;

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class JobSelection extends VBox {

	private ImageView squareImage;

	private static final String SQUARE_NOT_CHOOSEN = "grey_box.png";
	private static final String SQUARE_CHOOSEN = "green_boxCheckmark.png";

	private JOB job;

	private boolean isSquareChoosen;

	public JobSelection(JOB job) {
		squareImage = new ImageView(new Image(ClassLoader.getSystemResource(SQUARE_NOT_CHOOSEN).toString()));
		isSquareChoosen = false;
		this.job = job;
		this.setAlignment(Pos.CENTER);
		this.setSpacing(20);
		this.getChildren().add(squareImage);
	}

	public JOB getJob() {
		return job;
	}

	public boolean getIsSquareChoosen() {
		return isSquareChoosen;

	}

	public void setIsSquareChoosen(boolean isSquareChoosen) {
		this.isSquareChoosen = isSquareChoosen;
		String imageToSet = this.isSquareChoosen ? SQUARE_CHOOSEN : SQUARE_NOT_CHOOSEN;
		squareImage.setImage(new Image(ClassLoader.getSystemResource(imageToSet).toString()));
	}
}
