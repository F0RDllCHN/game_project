package Stage;

import GUI.GameButton;
import GUI.InfoLabel;
import GUI.StageNode;
import Hero.Hero;
import Monster.Monster;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;

public abstract class StageDefault {

	private static final int HEIGHT = 700;
	private static final int WIDTH = 1000;

	private Monster monster;
	private Hero player;
	private AnchorPane stagePane;
	private Scene stageScene;
	private Scene GameScene;
	private Stage mainStage;
	private StageNode stageNode;
	private InfoLabel playerInfo;
	private Label monsterHp;
	private VBox monsterInfo;
	private InfoLabel turnInfo;
	private InfoLabel skill1Des;
	private InfoLabel skill2Des;
	private GameButton jobChangeButton;
	private int turn = 1;
	private int expDrop;

	public StageDefault(Hero player, Stage mainStage, Scene gameScene, StageNode stageNode, int expDrop,
			GameButton jobChangeButton) {
		this.GameScene = gameScene;
		this.player = player;
		this.expDrop = expDrop;
		this.mainStage = mainStage;
		this.stageNode = stageNode;
		this.jobChangeButton = jobChangeButton;
		stagePane = new AnchorPane();
		stageScene = new Scene(stagePane, WIDTH, HEIGHT);
		createSkillDes();
		createSkill1Button();
		drawPlayer();
		if (player.isUnlockSkill()) {
			createSkill2Button();
		}
		createBackground();

	}

	private void createSkillDes() {
		skill1Des = new InfoLabel(player.getSkill1Des(), 630, 620);
		skill2Des = new InfoLabel(player.getSkill2Des(), 630, 620);
		skill1Des.setVisible(false);
		skill2Des.setVisible(false);
		skill1Des.setFont(Font.font(15));
		skill2Des.setFont(Font.font(15));
		stagePane.getChildren().addAll(skill1Des, skill2Des);
	}

	private void createSkill1Button() {
		GameButton skill1 = new GameButton(player.getSkill1Name());
		skill1.setLayoutX(400);
		skill1.setLayoutY(580);
		stagePane.getChildren().add(skill1);
		skill1.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				skill1.setEffect(new DropShadow());
				skill1Des.setVisible(true);

			}
		});

		skill1.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				skill1.setEffect(null);
				skill1Des.setVisible(false);
			}
		});

		skill1.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				player.attackWithSkill1(monster);
				updateMonsterInfo();
				updateTurnInfo();
				if (monster.isDefeated()) {
					Win();
				} else {
					monster.attack(player);
					if (player.isDefeated()) {
						Lose();
					}
					updatePlayerInfo();

				}

			}
		});

	}

	private void createSkill2Button() {
		GameButton skill2 = new GameButton(player.getSkill2Name());
		skill2.setLayoutX(400);
		skill2.setLayoutY(640);
		stagePane.getChildren().add(skill2);
		skill2.setOnMouseEntered(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				skill2.setEffect(new DropShadow());
				skill2Des.setVisible(true);

			}
		});

		skill2.setOnMouseExited(new EventHandler<MouseEvent>() {

			@Override
			public void handle(MouseEvent arg0) {
				skill2.setEffect(null);
				skill2Des.setVisible(false);

			}
		});

		skill2.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				if (player.getCurrentMp() >= player.getSkill2MpCost()) {
					updateTurnInfo();

					player.attackWithSkill2(monster);
					updateMonsterInfo();

					if (monster.isDefeated()) {
						Win();

					} else {
						monster.attack(player);
						if (player.isDefeated()) {
							Lose();
						}
						updatePlayerInfo();
					}
				} else {
					Alert noMp = new Alert(AlertType.INFORMATION);
					noMp.setTitle("Paper Adventure");
					noMp.setHeaderText(null);
					noMp.setContentText("Not have enough Mp to use \" " + player.getSkill2Name() + " \"");
					noMp.showAndWait();
				}
			}
		});
	}

	private int getExpDrop() {
		return this.expDrop;
	}

	private int getTurn() {
		return this.turn;
	}

	public Scene getStageScene() {
		return stageScene;
	}

	public Stage getMainStage() {
		return mainStage;
	}

	protected void spawnMonster(Monster monster) {
		this.monster = monster;
		createInfo();
		drawMonster(monster.getUrl());
	}

	public void unlockedNextStage() {
		stageNode.stageNodeUnlock();
	}

	private void createInfo() {
		playerInfo = new InfoLabel("Hp : " + player.getCurrentHp() + " / " + player.getMaxHp() + "\nMp : "
				+ player.getCurrentMp() + " / " + player.getMaxMp(), 200, 600);
		monsterInfo = new VBox();
		Label monsterName = new Label("" + monster.getName());
		monsterName.setAlignment(Pos.TOP_CENTER);
		monsterName.setFont(Font.font(23));
		monsterHp = new Label("Hp : " + monster.getCurrentHp() + " / " + monster.getCurrentHp());
		monsterHp.setFont(Font.font(23));
		monsterInfo.getChildren().addAll(monsterName, monsterHp);
		monsterInfo.setLayoutX(550);
		monsterInfo.setLayoutY(50);
		turnInfo = new InfoLabel("Turn : " + turn, 450, 300);
		stagePane.getChildren().addAll(playerInfo, monsterInfo, turnInfo);

	}

	private void drawMonster(String monsterUrl) {
		ImageView monsterImage = new ImageView(new Image(ClassLoader.getSystemResource(monsterUrl).toString()));
		monsterImage.setPreserveRatio(true);
		monsterImage.setFitWidth(200);
		monsterImage.setLayoutX(750);
		monsterImage.setLayoutY(50);
		stagePane.getChildren().add(monsterImage);
	}

	private void updatePlayerInfo() {
		playerInfo.setText("Hp : " + player.getCurrentHp() + " / " + player.getMaxHp() + "\nMp : "
				+ player.getCurrentMp() + " / " + player.getMaxMp());

	}

	private void updateMonsterInfo() {
		monsterHp.setText("Hp : " + monster.getCurrentHp() + " / " + monster.getMaxHp());
	}

	private void updateTurnInfo() {
		turn++;
		turnInfo.setText("Turn : " + getTurn());
	}

	private void Win() {
		Alert win = new Alert(AlertType.INFORMATION);
		win.setTitle("Paper Adventure");
		win.setContentText("   You get " + getExpDrop() + " Exp");
		win.setHeaderText("     ------ VICTORY !! -----");
		win.showAndWait();
		player.recovery();
		unlockedNextStage();
		mainStage.setScene(GameScene);
		player.recieveExp(getExpDrop());
		if (player.getLevel() >= 5 && player.getJob() == "Novice") {
			jobChangeButton.setVisible(true);
		}
	}

	private void Lose() {
		Alert lose = new Alert(AlertType.INFORMATION);
		lose.setTitle("Paper Adventure");
		lose.setHeaderText("     ----- Failed !! -----");
		lose.setContentText("   You lose all Exp");
		lose.showAndWait();
		player.recovery();
		player.setExp(0);
		mainStage.setScene(GameScene);

	}

	private void drawPlayer() {
		ImageView playerImage = new ImageView(
				new Image(ClassLoader.getSystemResource(player.getHeroImageUrl()).toString()));
		playerImage.setFitHeight(200);
		playerImage.setPreserveRatio(true);
		playerImage.setLayoutX(50);
		playerImage.setLayoutY(480);
		stagePane.getChildren().add(playerImage);

	}

	private void createBackground() {
		Image backgroundImage = new Image(ClassLoader.getSystemResource("StageBg.png").toString(), 1000, 700, false,
				true);
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT,
				BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
		stagePane.setBackground(new Background(background));
	}

}
