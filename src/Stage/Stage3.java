package Stage;

import GUI.GameButton;
import GUI.StageNode;
import Hero.Hero;
import Monster.Bug;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Stage3 extends StageDefault {

	public Stage3(Hero player, Stage mainStage, Scene gameScene, StageNode stageNode, GameButton jobChangeButton) {
		super(player, mainStage, gameScene, stageNode, 30, jobChangeButton);
		Bug bug = new Bug();
		spawnMonster(bug);
	}

}
