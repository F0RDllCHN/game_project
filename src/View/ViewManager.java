package View;

import java.util.ArrayList;
import java.util.List;

import Exception.GameException;
import GUI.GameButton;
import GUI.GameInput;
import GUI.GameSubScene;
import GUI.InfoLabel;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.stage.Stage;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.effect.DropShadow;

public class ViewManager {

	private static final int HEIGHT = 700;
	private static final int WIDTH = 1000;
	private AnchorPane mainPane;
	private Scene mainScene;
	private Scene gameScene;
	private Stage mainStage;
	private String Name;
	private boolean canLoad = false;
	private GameInput nameInput;

	private GameSubScene howSubScene;
	private GameSubScene startSubScene;
	private GameSubScene sceneToHide;

	private GameViewManager gameManager;

	private InfoLabel howToPlay;

	private List<GameButton> menuButtons;

	public ViewManager() {
		menuButtons = new ArrayList<>();
		mainPane = new AnchorPane();
		mainScene = new Scene(mainPane, WIDTH, HEIGHT);
		mainStage = new Stage();
		mainStage.setScene(mainScene);
		createButtons();
		createBackground();
		createSubScene();
		createStartScene();
		drawLogo();

	}

	private void showSubScene(GameSubScene subScene) {
		if (sceneToHide != null) {
			sceneToHide.moveSubScene();
		}
		subScene.moveSubScene();
		this.sceneToHide = subScene;

	}

	private void setName(String name) throws GameException {
		this.Name = name;
		if (nameInput.getText().equals("")) {
			throw new GameException("Name can not be empty");
		}
		if (nameInput.getText().length()>8) {
			throw new GameException("Name length can not be more than 8");
		}
	}

	private void createSubScene() {
		howSubScene = new GameSubScene();
		mainPane.getChildren().add(howSubScene);
		howToPlay = new InfoLabel(
				"Paper adventure is an rpg game.\nYou will be an adventure\nand your role is to clear all dungeon.\nYou can change job when your level reach 5. \nDefeat monsters and become stronger.",
				50, 100);
		howSubScene.getPane().getChildren().add(howToPlay);
		howSubScene.getPane().getChildren().add(createReturnButton(350, 300));

	}

	private void createStartScene() {
		startSubScene = new GameSubScene();
		mainPane.getChildren().add(startSubScene);
		startSubScene.getPane().getChildren().add(createReturnButton(100, 300));
		startSubScene.getPane().getChildren().add(createStartButton(330, 300));
		nameInput = new GameInput("Character Name", "Please type your name.");
		startSubScene.getPane().getChildren().add(nameInput);

	}

	private GameButton createStartButton(int X, int Y) {
		GameButton startButton = new GameButton("Start");
		startButton.setLayoutX(X);
		startButton.setLayoutY(Y);
		startButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {

				try {
					setName(nameInput.getText());
					gameManager = new GameViewManager();
					gameScene = gameManager.createNewGame(mainStage, Name);
					sceneToHide.moveSubScene();
					sceneToHide = null;
					canLoad = true;

				} catch (GameException e) {
					Alert noNameInputAlert = new Alert(AlertType.ERROR);
					noNameInputAlert.setHeaderText(e.getMessage());
					noNameInputAlert.setContentText(null);
					noNameInputAlert.showAndWait();
				}
			}
		});

		return startButton;

	}

	private GameButton createReturnButton(int X, int Y) {
		GameButton returnButton = new GameButton("Return");
		returnButton.setLayoutX(X);
		returnButton.setLayoutY(Y);
		returnButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				sceneToHide.moveSubScene();
				sceneToHide = null;

			}
		});
		return returnButton;
	}

	public Stage getMainStage() {
		return mainStage;
	}

	private void addMenuButton(GameButton button) {
		button.setLayoutX(405);
		button.setLayoutY(300 + menuButtons.size() * 100);
		menuButtons.add(button);
		mainPane.getChildren().add(button);
	}

	private void createButtons() {
		createNewGameButton();
		createLoadButton();
		createHowButton();
		createExitButton();
	}

	private void createNewGameButton() {
		GameButton newGameButton = new GameButton("New Game");
		addMenuButton(newGameButton);
		newGameButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				showSubScene(startSubScene);

			}
		});
	}

	private void createLoadButton() {
		GameButton loadButton = new GameButton("Continue");
		addMenuButton(loadButton);
		loadButton.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				if (canLoad) {
					loadButton.setEffect(new DropShadow());
				} else {
					loadButton.setEffect(null);
				}

			}
		});

		loadButton.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				loadButton.setEffect(null);

			}
		});

		loadButton.setOnMousePressed(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				if (canLoad) {
					loadButton.setStyle(
							"-fx-background-color: transparent; -fx-background-image: url('/GUI/resources/buttonLong_grey_pressed.png');");
					loadButton.setPrefHeight(45);
					loadButton.setLayoutY(loadButton.getLayoutY() + 4);
				}

			}
		});
		loadButton.setOnMouseReleased(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				if (canLoad) {
					loadButton.setStyle(
							"-fx-background-color: transparent; -fx-background-image: url('/GUI/resources/buttonLong_grey.png');");
					loadButton.setPrefHeight(49);
					loadButton.setLayoutY(loadButton.getLayoutY() - 4);
				}

			}
		});

		loadButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (canLoad) {
					mainStage.setScene(gameScene);
					gameManager.getTimer().startStopTiming();
				}

			}
		});
	}

	private void createHowButton() {
		GameButton howButton = new GameButton("How to play");
		addMenuButton(howButton);

		howButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				showSubScene(howSubScene);

			}
		});
	}

	private void createExitButton() {
		GameButton exitButton = new GameButton("Exit");
		addMenuButton(exitButton);

		exitButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				mainStage.close();

			}
		});
	}

	private void createBackground() {
		Image backgroundImage = new Image(ClassLoader.getSystemResource("paper_background.png").toString(), 1000, 1000,
				false, true);
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT,
				BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
		mainPane.setBackground(new Background(background));
	}

	private void drawLogo() {
		ImageView Logo = new ImageView(new Image(ClassLoader.getSystemResource("Logo.png").toString()));
		Logo.setLayoutX(35);
		Logo.setLayoutY(50);
		mainPane.getChildren().add(Logo);
	}

}
