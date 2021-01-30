package View;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import GUI.GameButton;
import GUI.GameSubScene;
import GUI.InfoLabel;
import GUI.JOB;
import GUI.JobSelection;
import GUI.StageNode;
import GUI.Timer;
import Hero.Archer;
import Hero.Hero;
import Hero.Mage;
import Hero.Novice;
import Hero.Swordman;
import Stage.Stage1;
import Stage.Stage10;
import Stage.Stage2;
import Stage.Stage3;
import Stage.Stage4;
import Stage.Stage5;
import Stage.Stage6;
import Stage.Stage7;
import Stage.Stage8;
import Stage.Stage9;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.HBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public class GameViewManager {

	private static final int HEIGHT = 700;
	private static final int WIDTH = 1000;

	private Hero player;
	private AnchorPane gamePane;
	private Scene gameScene;
	private Stage mainStage;
	private Scene mainScene;
	private GameSubScene jobSelectionSubScene;
	private StatViewManager StatViewManager;
	private GameButton jobChangeButton;
	private StageNode stage1Node;
	private StageNode stage2Node;
	private StageNode stage3Node;
	private StageNode stage4Node;
	private StageNode stage5Node;
	private StageNode stage6Node;
	private StageNode stage7Node;
	private StageNode stage8Node;
	private StageNode stage9Node;
	private StageNode stage10Node;
	private Stage1 stage1;
	private Stage2 stage2;
	private Stage3 stage3;
	private Stage4 stage4;
	private Stage5 stage5;
	private Stage6 stage6;
	private Stage7 stage7;
	private Stage8 stage8;
	private Stage9 stage9;
	private Stage10 stage10;
	private Timer timer;

	private List<JobSelection> JobList;
	private JOB ChoosenJob;

	public GameViewManager() {
		gamePane = new AnchorPane();
		gameScene = new Scene(gamePane, WIDTH, HEIGHT);
		timer = new Timer();

		timer.startStopTiming();
		createMainMenuButton();
		createStatButton();
		createStageNodes(); 
		createJobChangeButton();
		createJobSelectionSubScene();
		drawStageSelect();
		createBackground();
	}

	public Scene createNewGame(Stage menuStage, String name) {

		this.mainStage = menuStage;
		this.mainScene = this.mainStage.getScene();
		this.mainStage.setScene(gameScene);
		this.player = new Novice();
		player.setName(name);
		StatViewManager = new StatViewManager(mainStage, gameScene, player, timer);
		return gameScene;

	}

	private void createJobSelectionSubScene() {
		jobSelectionSubScene = new GameSubScene();
		gamePane.getChildren().add(jobSelectionSubScene);

		InfoLabel selectJobLabel = new InfoLabel("-- Please select your job --", 140, 50);
		InfoLabel selectJobNoteLabel = new InfoLabel("** Your level will start at level 5 **", 180, 80);
		InfoLabel selectJobInfoLabel = new InfoLabel("Swordman \t\t\tMage     \t\t\t    Archer", 80, 120);
		selectJobInfoLabel.setFont(Font.font(17));
		selectJobNoteLabel.setFont(Font.font(15));
		jobSelectionSubScene.getPane().getChildren().addAll(selectJobLabel, selectJobNoteLabel, selectJobInfoLabel,
				createJobToSelect(), createJobSelectConfirm());

	}

	private HBox createJobToSelect() {
		HBox box = new HBox();
		box.setSpacing(130);
		JobList = new ArrayList<>();
		for (JOB job : JOB.values()) {
			JobSelection jobSelect = new JobSelection(job);
			JobList.add(jobSelect);
			box.getChildren().add(jobSelect);
			jobSelect.setOnMouseClicked(new EventHandler<MouseEvent>() {

				@Override
				public void handle(MouseEvent arg0) {
					for (JobSelection job : JobList) {
						job.setIsSquareChoosen(false);
					}
					jobSelect.setIsSquareChoosen(true);
					ChoosenJob = jobSelect.getJob();
				}
			});
		}
		box.setLayoutX(100);
		box.setLayoutY(150);
		return box;
	}

	private GameButton createJobSelectConfirm() {
		GameButton jobSelectConfirm = new GameButton("Done");
		jobSelectConfirm.setLayoutX(350);
		jobSelectConfirm.setLayoutY(320);
		drawJob();
		jobSelectConfirm.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (ChoosenJob == null) {
					jobSelectionSubScene.moveSubScene();
				}
				if (ChoosenJob != null) {
					Alert confirm = new Alert(AlertType.CONFIRMATION);
					confirm.setTitle("Paper Adventure");
					confirm.setHeaderText(null);
					confirm.setContentText(
							"Once your job is changing you can't change again. \n Are you sure with this selected job ");
					ButtonType buttonTypeYes = new ButtonType("Yes");
					ButtonType buttonTypeNo = new ButtonType("No", ButtonData.CANCEL_CLOSE);
					confirm.getButtonTypes().setAll(buttonTypeYes, buttonTypeNo);

					Optional<ButtonType> result = confirm.showAndWait();
					if (result.get() == buttonTypeYes) {
						String name = player.getName();
						switch (ChoosenJob) {
						case Archer:

							player = new Archer(name);
							jobSelectionSubScene.moveSubScene();
							jobChangeButton.setVisible(false);
							StatViewManager.changeJobStat(player);
							break;
						case Swordman:
							player = new Swordman(name);
							jobSelectionSubScene.moveSubScene();
							jobChangeButton.setVisible(false);
							StatViewManager.changeJobStat(player);
							break;
						case Mage:
							player = new Mage(name);
							jobSelectionSubScene.moveSubScene();
							jobChangeButton.setVisible(false);
							StatViewManager.changeJobStat(player);
							break;
						default:
							break;
						}
					}
				}

			}

		});
		return jobSelectConfirm;
	}

	private void createMainMenuButton() {
		GameButton mainMenuButton = new GameButton("MainMenu");
		mainMenuButton.setLayoutX(10);
		mainMenuButton.setLayoutY(640);
		gamePane.getChildren().add(mainMenuButton);
		mainMenuButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				mainStage.setScene(mainScene);
				timer.startStopTiming();

			}
		});

	}

	private void createJobChangeButton() {
		jobChangeButton = new GameButton("Job Change");
		jobChangeButton.setLayoutX(10);
		jobChangeButton.setLayoutY(520);
		jobChangeButton.setVisible(false);
		gamePane.getChildren().add(jobChangeButton);
		jobChangeButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				jobSelectionSubScene.moveSubScene();

			}
		});
	}

	private void createStatButton() {
		GameButton statButton = new GameButton("Stat");
		statButton.setLayoutX(10);
		statButton.setLayoutY(580);
		gamePane.getChildren().add(statButton);
		statButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				mainStage.setScene(StatViewManager.getStatScene());
				StatViewManager.updateStat();
			}
		});

	}

	public void updateHero(Hero player) {
		this.player = player;
	}

	public AnchorPane getGamePane() {
		return gamePane;
	}

	public Stage getMainStage() {
		return mainStage;
	}

	private void createStageNodes() {
		stage1Node = new StageNode("1", false, 90, 450);
		stage2Node = new StageNode("2", true, 180, 360);
		stage3Node = new StageNode("3", true, 270, 350);
		stage4Node = new StageNode("4", true, 360, 310);
		stage5Node = new StageNode("5", true, 450, 400);
		stage6Node = new StageNode("6", true, 540, 420);
		stage7Node = new StageNode("7", true, 630, 370);
		stage8Node = new StageNode("8", true, 720, 300);
		stage9Node = new StageNode("9", true, 810, 290);
		stage10Node = new StageNode("10", true, 890, 350);

		initializeStageNode();
		gamePane.getChildren().addAll(stage1Node, stage2Node, stage3Node, stage4Node, stage5Node, stage6Node,
				stage7Node, stage8Node, stage9Node, stage10Node);
	}

	private void initializeStageNode() {

		stage1Node.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				stage1 = new Stage1(player, mainStage, gameScene, stage2Node, jobChangeButton);
				mainStage.setScene(stage1.getStageScene());

			}
		});

		stage2Node.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (!stage2Node.isLocked()) {
					stage2 = new Stage2(player, mainStage, gameScene, stage3Node, jobChangeButton);
					mainStage.setScene(stage2.getStageScene());
				}
			}
		});

		stage3Node.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (!stage3Node.isLocked()) {
					stage3 = new Stage3(player, mainStage, gameScene, stage4Node, jobChangeButton);
					mainStage.setScene(stage3.getStageScene());
				}
			}
		});

		stage4Node.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (!stage4Node.isLocked()) {
					stage4 = new Stage4(player, mainStage, gameScene, stage5Node, jobChangeButton);
					mainStage.setScene(stage4.getStageScene());
				}
			}
		});

		stage5Node.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (!stage5Node.isLocked()) {
					stage5 = new Stage5(player, mainStage, gameScene, stage6Node, jobChangeButton);
					mainStage.setScene(stage5.getStageScene());
				}
			}
		});

		stage6Node.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (!stage6Node.isLocked()) {
					stage6 = new Stage6(player, mainStage, gameScene, stage7Node, jobChangeButton);
					mainStage.setScene(stage6.getStageScene());
				}
			}
		});

		stage7Node.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (!stage7Node.isLocked()) {
					stage7 = new Stage7(player, mainStage, gameScene, stage8Node, jobChangeButton);
					mainStage.setScene(stage7.getStageScene());
				}
			}
		});

		stage8Node.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (!stage8Node.isLocked()) {
					stage8 = new Stage8(player, mainStage, gameScene, stage9Node, jobChangeButton);
					mainStage.setScene(stage8.getStageScene());
				}
			}
		});

		stage9Node.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (!stage9Node.isLocked()) {
					stage9 = new Stage9(player, mainStage, gameScene, stage10Node, jobChangeButton);
					mainStage.setScene(stage9.getStageScene());
				}
			}
		});

		stage10Node.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (!stage10Node.isLocked()) {
					stage10 = new Stage10(player, mainStage, gameScene, stage10Node, jobChangeButton);
					mainStage.setScene(stage10.getStageScene());
				}
			}
		});
	}

	private void drawJob() {
		ImageView swordmanImage = new ImageView(
				new Image(ClassLoader.getSystemResource(JOB.Swordman.getUrl()).toString()));
		ImageView mageImage = new ImageView(new Image(ClassLoader.getSystemResource(JOB.Mage.getUrl()).toString()));
		ImageView archerImage = new ImageView(new Image(ClassLoader.getSystemResource(JOB.Archer.getUrl()).toString()));
		swordmanImage.setFitHeight(120);
		swordmanImage.setPreserveRatio(true);
		mageImage.setFitHeight(120);
		mageImage.setPreserveRatio(true);
		;
		archerImage.setFitHeight(120);
		archerImage.setPreserveRatio(true);
		swordmanImage.setLayoutX(70);
		swordmanImage.setLayoutY(200);
		archerImage.setLayoutX(410);
		archerImage.setLayoutY(200);
		mageImage.setLayoutX(255);
		mageImage.setLayoutY(200);

		jobSelectionSubScene.getPane().getChildren().addAll(swordmanImage, mageImage, archerImage);
	}

	private void drawStageSelect() {
		ImageView stageSelect = new ImageView(new Image(ClassLoader.getSystemResource("stage_select.png").toString()));
		stageSelect.setLayoutX(0);
		stageSelect.setLayoutY(20);
		gamePane.getChildren().add(stageSelect);
	}

	public Timer getTimer() {
		return timer;
	}

	private void createBackground() {
		Image backgroundImage = new Image(ClassLoader.getSystemResource("bgS.png").toString(), 1000, 700, false, true);
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT,
				BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
		gamePane.setBackground(new Background(background));
	}

}
