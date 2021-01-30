package Stage;

import GUI.GameButton;
import GUI.StageNode;
import Hero.Hero;
import Monster.DevilMushroom;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Stage4 extends StageDefault {

	public Stage4(Hero player, Stage mainStage, Scene gameScene, StageNode stageNode, GameButton jobChangeButton) {
		super(player, mainStage, gameScene, stageNode, 40, jobChangeButton);
		DevilMushroom devilMushroom = new DevilMushroom();
		spawnMonster(devilMushroom);
	}

}
