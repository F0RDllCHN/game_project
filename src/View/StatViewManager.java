package View;

import GUI.GameButton;
import GUI.InfoLabel;
import GUI.Timer;
import Hero.Hero;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.stage.Stage;

public class StatViewManager {

	private static final int HEIGHT = 700;
	private static final int WIDTH = 1000;

	private Hero player;
	private AnchorPane statPane;
	private Scene statScene;
	private Stage statStage;
	private Timer timer;

	private Stage mainStage;
	private Scene gameScene;

	private InfoLabel name;
	private InfoLabel job;
	private InfoLabel exp;
	private InfoLabel hp;
	private InfoLabel mp;
	private InfoLabel level;
	private InfoLabel atk;
	private InfoLabel def;
	private InfoLabel intel;
	private InfoLabel skill;
	private InfoLabel skill1;
	private InfoLabel skill2;

	private ImageView playerImage;

	private boolean isSkill2Show = false;

	public StatViewManager(Stage mainStage, Scene gameScene, Hero player, Timer timer) {
		statPane = new AnchorPane();
		statScene = new Scene(statPane, WIDTH, HEIGHT);
		statStage = new Stage();
		statStage.setScene(statScene);
		this.gameScene = gameScene;
		this.mainStage = mainStage;
		this.player = player;
		this.timer = timer;
		createReturnButton();
		drawPlayer();
		StatShow();
		createBackground();

	}

	private void createReturnButton() {
		GameButton ReturnButton = new GameButton("Return");
		ReturnButton.setLayoutX(10);
		ReturnButton.setLayoutY(640);
		statPane.getChildren().add(ReturnButton);
		ReturnButton.setOnAction(new EventHandler<ActionEvent>() {

			@Override
			public void handle(ActionEvent arg0) {
				mainStage.setScene(gameScene);
			}
		});

	}

	public Scene getStatScene() {
		return statScene;
	}

	private void StatShow() {
		name = new InfoLabel("Name : " + player.getName(), 150, 150);
		job = new InfoLabel("Job : " + player.getJob(), 150, 190);
		level = new InfoLabel("Level : " + player.getLevel(), 650, 150);
		exp = new InfoLabel("Exp : " + player.getExp() + " / " + player.getMaxExp(), 650, 190);
		hp = new InfoLabel("Hp : " + player.getMaxHp(), 650, 230);
		mp = new InfoLabel("Mp : " + player.getMaxMp(), 650, 270);
		atk = new InfoLabel("Attack : " + player.getAtk(), 650, 310);
		def = new InfoLabel("Defence : " + player.getDef(), 650, 350);
		intel = new InfoLabel("Int : " + player.getInt(), 650, 390);
		skill = new InfoLabel("---- SKill ----", 630, 450);
		skill1 = new InfoLabel("\" " + player.getSkill1Name() + " \"", 640, 500);
		skill2 = new InfoLabel("\" " + player.getSkill2Name() + " \"", 640, 550);
		skill2.setVisible(false);

		statPane.getChildren().addAll(name, job, level, exp, hp, mp, atk, def, intel, skill, skill1, skill2,
				timer.getTimeGui());

	}

	public void updateStat() {
		name.setText("Name : " + player.getName());
		job.setText("Job : " + player.getJob());
		level.setText("Level : " + player.getLevel());
		exp.setText("Exp : " + player.getExp() + " / " + player.getMaxExp());
		hp.setText("Hp : " + player.getMaxHp());
		mp.setText("Mp : " + player.getMaxMp());
		atk.setText("Attack : " + player.getAtk());
		def.setText("Defence : " + player.getDef());
		intel.setText("Int : " + player.getInt());
		if (player.isUnlockSkill() && !isSkill2Show) {
			skill2.setVisible(true);
			isSkill2Show = true;
		}

	}

	public void changeJobStat(Hero player) {
		this.player = player;
		updateStat();
		skill1.setText("\" " + player.getSkill1Name() + " \"");
		skill2.setText("\" " + player.getSkill2Name() + " \"");
		isSkill2Show = false;
		statPane.getChildren().remove(playerImage);
		drawPlayer();
	}

	private void drawPlayer() {
		playerImage = new ImageView(new Image(ClassLoader.getSystemResource(player.getHeroImageUrl()).toString()));
		playerImage.setFitHeight(300);
		playerImage.setPreserveRatio(true);
		playerImage.setLayoutX(140);
		playerImage.setLayoutY(260);
		statPane.getChildren().add(playerImage);

	}

	private void createBackground() {
		Image backgroundImage = new Image(ClassLoader.getSystemResource("stat.png").toString(), 1000, 700, false, true);
		BackgroundImage background = new BackgroundImage(backgroundImage, BackgroundRepeat.REPEAT,
				BackgroundRepeat.REPEAT, BackgroundPosition.DEFAULT, null);
		statPane.setBackground(new Background(background));
	}

}
