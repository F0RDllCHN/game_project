package Stage;

import GUI.GameButton;
import GUI.StageNode;
import Hero.Hero;
import Monster.EvilSnake;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Stage8 extends StageDefault {

	public Stage8(Hero player, Stage mainStage, Scene gameScene, StageNode stageNode, GameButton jobChangeButton) {
		super(player, mainStage, gameScene, stageNode, 80, jobChangeButton);
		EvilSnake evilSnake = new EvilSnake();
		spawnMonster(evilSnake);
	}

}
